package util;

import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;



public class Permanencia {
	private ArrayList<Double> resultado=new ArrayList<Double>();
	private double [] valor;
	private double [] permanencia;
	private double [] estatistica=new double[8];
	DescriptiveStatistics dsc = new DescriptiveStatistics(); 
	
	
	public  Permanencia(double [] valor,double [] permanencia) {
	 this.valor = new double [valor.length];
	 this.valor =valor;
	 this.permanencia=permanencia;
	 
	 
	 
     
		     for (int j = 0; j < this.valor.length;j++){
		     this.dsc.addValue(this.valor[j]);
		     }
		     
		     this.estatistica[0]=this.dsc.getN();
		     this.estatistica[1]=this.dsc.getMean();
		     this.estatistica[2]=this.dsc.getStandardDeviation();
		     this.estatistica[3]=this.estatistica[2]/this.estatistica[1];
		     this.estatistica[4]=this.dsc.getSkewness();
		     this.estatistica[5]=this.dsc.getKurtosis();
		     this.estatistica[6]=this.dsc.getMin();
			 this.estatistica[7]=this.dsc.getMax();
			 
			
	 }
	
     
	  
	 public double[] getEstatistica() {
		return estatistica;
	}



	public void setEstatistica(double[] estatistica) {
		this.estatistica = estatistica;
	}



	public Permanencia(ArrayList<Double> valor,ArrayList<Double> permanencia) {
		 this.valor = new double [valor.size()];
		 for (int i=0;i<valor.size();i++){
		 this.valor[i]=	 valor.get(i);
		 }
		 this.permanencia = new double [permanencia.size()];
		 for (int i=0;i<permanencia.size();i++){
		 this.permanencia[i]=	 permanencia.get(i);
		 }
		
		 
	     
	     for (int j = 0; j < this.valor.length;j++){
	    	 this.dsc.addValue(this.valor[j]);
	     }
	     
	     this.estatistica[0]=this.dsc.getN();
	     this.estatistica[1]=this.dsc.getMean();
	     this.estatistica[2]=this.dsc.getStandardDeviation();
	     this.estatistica[3]=this.estatistica[2]/this.estatistica[1];
	     this.estatistica[4]=this.dsc.getSkewness();
	     this.estatistica[5]=this.dsc.getKurtosis();
	     this.estatistica[6]=this.dsc.getMin();
		 this.estatistica[7]=this.dsc.getMax();
		 
		 
	 }
	 
	 
	
	 
		
	
	public ArrayList<Double> permanencia (){
		
		
		double perman=0;
        Percentile pct = new Percentile();
        
        int ipos = 0;
        for (int i=0;i<this.permanencia.length;i++){
	        perman=this.permanencia[i];
	        if(perman != 0){
	        this.resultado.add(this.Q(perman));	
	        }else{
	        this.resultado.add(this.estatistica[7]);
	        }
	        System.out.println("Permanencia = "+(perman)+"  valor = "+this.resultado.get(ipos));
		    ipos++;
        }
			
		
		return this.resultado;
		
	}
	
	
	
	 public Permanencia(ArrayList<Double> valor) {
		 
		 if(valor.size() == 0){
			 System.out.println();
		 }
		 if(valor.size() != 0){
			 
		 
		 this.valor = new double [valor.size()];
		 for (int i=0;i<valor.size();i++){
		 this.valor[i]=	 valor.get(i);
		 }
		 double disc=0;
		 for (int i=0;i<28;i++){
		 this.permanencia = new double [28];
		 this.permanencia[i]=disc;
		 if(disc < 90){
			 disc=disc+5;	 
		 }else{
			 disc=disc+1; 
		 }
		}
	    
	     for (int j = 0; j < this.valor.length;j++){
	     this.dsc.addValue(this.valor[j]);
	     }
		 this.estatistica[0]=this.dsc.getN();
	     this.estatistica[1]=this.dsc.getMean();
	     this.estatistica[2]=this.dsc.getStandardDeviation();
	     this.estatistica[3]=this.estatistica[2]/this.estatistica[1];
	     this.estatistica[4]=this.dsc.getSkewness();
	     this.estatistica[5]=this.dsc.getKurtosis();
	     this.estatistica[6]=this.dsc.getMin();
		 this.estatistica[7]=this.dsc.getMax();
		 }
		 
		 
	 }
		
	public double Q(double perm){
	
		   double value=0;
		   
		   if(this.valor.length == 0){
			   System.out.println(); 
			   
		   }
		   double [] ordem=new double[this.valor.length];
		   double [] valorord=new double[this.valor.length];
		   double [] valororddesc=new double[this.valor.length];
		   valorord=this.dsc.getSortedValues();
	       
		   /**
		    * Passa para ordem decrescente
		    */
	       int j=valorord.length-1;
		       for (int i=0;i<valorord.length;i++){
		       valororddesc[i]=valorord[j];
		       j--;  
		       }
	       /**
	        * Posição de Plotagem da permanencia "california" i/n
	        */
	       for (int i=0;i<valorord.length;i++){
		   ordem[i]=(((i+1)*1.0)/(valorord.length*1.0))*100;  
		   }
		   
	       /**
	        * Procurar os dois intervalos entre a permanecia que se deseja;
	        */
	       
	       
	       int i1=0;
	       while(perm > ordem[i1]){
	       i1++; 
	       }
	      
	       if(i1-1 > 0){
	       double xi=ordem[i1-1];
	       double xi1=ordem[i1];
	       double yi=valororddesc[i1-1];
	       double yi1=valororddesc[i1];
	       double xip=perm;
	       double yip=yi+((xip-xi)*((yi1-yi)/(xi1-xi)));
	       value=yip;
           }else{
        	   value=valororddesc[i1];   
           }
	       System.out.println("o valor interpolado é:  "+value);
	       //LinearInterpolation aa = new LinearInterpolation(ordem, valororddesc);
		   //value = aa.interpolate((perm/100.00));
	      
	 		
		return value;
		
	}
	 
       public double Q2 (double perm){
		
		    double perman=0;
		    double value=0;
            Percentile pct = new Percentile();
            perman=100-perm;
	        double pos=((this.valor.length*1.0)/((this.valor.length*1.0)+1))*perman;
	        if(perman != 0){
	        value =pct.evaluate(this.valor,pos);
	        }else{
	        value =this.estatistica[0];	
	        }
	  		
		return value;
		
	}
   public double Qsimplificado (double perm){
		double p=100-perm;
		double valorunico = this.dsc.getPercentile(p);
		return valorunico;
	
	}
   
   
	 
	public static void main(String[] args) {
	
		ArrayList<Double> valteste=new ArrayList<Double>();
		
		for (int j = 0; j < 322;j++){
			double val=j+1;
			valteste.add(val);	
		}
		
		
		
		Permanencia per = new Permanencia (valteste);
		
		double q=per.Q(95);
		//double q2=per.Qsimplificado(94);
		
		double q3=per.Q2(95);
		System.out.println(q+"    "+q3);
			
		
		
	}
}
