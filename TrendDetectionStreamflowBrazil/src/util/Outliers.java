package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import types.DadoTemporal;

/*import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.snirh.extremos_unb.deteccao.testes.Outliers;
import org.snirh.extremos_unb.tipos.DadoTemporal;*/

public class Outliers {
	double [] valuetot;
	double [] valuex;
	double [] valuey;
	double  pvalue;
	double  estatteste;
	double  valorcriticoteste;
	String  resultadoteste;
	int divisao;
	double limsup;
	double liminf;
	
	public double getPvalue() {
		return pvalue;
	}

	public double getLimsup() {
		return limsup;
	}

	public void setLimsup(double limsup) {
		this.limsup = limsup;
	}

	public double getLiminf() {
		return liminf;
	}

	public void setLiminf(double liminf) {
		this.liminf = liminf;
	}

	public void setPvalue(double pvalue) {
		this.pvalue = pvalue;
	}

	public double getEstatteste() {
		return estatteste;
	}

	public void setEstatteste(double estatteste) {
		this.estatteste = estatteste;
	}

	public double getValorcriticoteste() {
		return valorcriticoteste;
	}

	public void setValorcriticoteste(double valorcriticoteste) {
		this.valorcriticoteste = valorcriticoteste;
	}

	public String getResultadoteste() {
		return resultadoteste;
	}

	public void setResultadoteste(String resultadoteste) {
		this.resultadoteste = resultadoteste;
	}
	
	
	
	
	public  Outliers(ArrayList<Double> valuetot){
		this.valuetot=new double[valuetot.size()];
		for (int i=0;i<valuetot.size();i++){
		this.valuetot[i]=valuetot.get(i);
		}
		
		
		int metade = valuetot.size()/2;
		this.valuex=new double[metade];
		this.valuey=new double[valuetot.size()-metade];
		for (int i=0;i<metade;i++){
		this.valuex[i]=this.valuetot[i];
		}
		int i1=0;
		for (int i=metade;i<valuetot.size();i++){
		this.valuey[i1]=this.valuetot[i];
		i1++;
		}
		
		
	}
	
	
	
	
	
	
	public  Outliers(double [] valuetot){
		this.valuetot=new double[valuetot.length];
		this.valuetot= valuetot;
		int metade = valuetot.length/2;
		this.valuex=new double[metade];
		this.valuey=new double[valuetot.length-metade];
		for (int i=0;i<metade;i++){
		this.valuex[i]=this.valuetot[i];
		}
		int i1=0;
		for (int i=metade;i<valuetot.length;i++){
		this.valuey[i1]=this.valuetot[i];
		i1++;
		}
		
		
	}
	
    public  Outliers(double [] valuex,double [] valuey){
    	this.valuex=new double[valuex.length];
		this.valuex= valuex;
		this.valuey=new double[valuey.length];
		this.valuey= valuey;
		this.valuetot=new double[valuex.length+valuey.length];
		int k=0;
		for (int i=0;i<valuex.length;i++){
			this.valuetot[k]=this.valuex[i];	
		k++;
		}
		for (int i=0;i<valuey.length;i++){
			this.valuetot[k]=this.valuey[i];	
		k++;
		}
		
	}
	
    public  Outliers(double [] valuetot, int divisao){
		this.valuetot=new double[valuetot.length];
		this.valuetot= valuetot;
		this.divisao=divisao;
		int metade = this.divisao; //divisão é a posição no vetor onde que se dividir a série
		this.valuex=new double[metade];
		for (int i=0;i<metade;i++){
		this.valuex[i]=this.valuetot[i];
		}
		
		for (int i=metade;i<valuetot.length;i++){
		this.valuey[i]=this.valuetot[i];
		}
		
		
	}
	
       public void testeGrubbsBeck () {
    	
    	
        	 DescriptiveStatistics dsc1 = new DescriptiveStatistics();
        	 for(int j=0;j<this.valuetot.length;j++){
        	 dsc1.addValue(Math.log(this.valuetot[j]));
        	 }
        	 
        	 double m=dsc1.getMean();
        	 double st=dsc1.getStandardDeviation();
        	 
        	 double N=this.valuetot.length;
        	 //N=85;
        	 double kn= (-3.62201)+(6.28446*Math.pow(N, 0.25))-(2.49835*Math.pow(N, 0.50))+(0.491436*Math.pow(N, 0.75))-(0.037911*N);
        	 
        	 double xh=Math.exp(m+kn*st);
        	 this.setLimsup(xh);
        	 
        	 double xl=Math.exp(m-kn*st);
        	 this.setLiminf(xl);
        	 
		
	   }
    
       public void testeBoxPlot () {
    	     DescriptiveStatistics dsc = new DescriptiveStatistics();
    	     for(int j=0;j<this.valuetot.length;j++){
          	 dsc.addValue(this.valuetot[j]);
          	 }
    	   
    	   double q25 = dsc.getPercentile(25.0);
    	   double q75 = dsc.getPercentile(75.0);
    	   //double q95 = dsc.getPercentile(95.0);
    	   //double q100 = dsc.getPercentile(100.0);
    	   
    	   double iqr=q75-q25;
    	   double xl=q25-3*iqr;
    	   this.setLiminf(xl);
    	   double xh=q75+3*iqr;
    	   this.setLimsup(xh);
    	   
    	   
    	     
       }
       public boolean eOutlierPeloTesteGrubbsBeck (double valor) {
    	   
    	   boolean eoutlier=false;
		   this.testeGrubbsBeck();
    	   if(valor > this.liminf && valor < this.limsup){
    	   eoutlier=false   ;
    	   }else{
    	   eoutlier=true; 
    	   }
    	   return eoutlier;
    	   
    	  
       }
       
      public boolean eOutlierPeloTesteBoxPlot(double valor) {
    	   
    	   boolean eoutlier=false;
		   this.testeGrubbsBeck();
    	   if(valor > this.liminf && valor < this.limsup){
    	   eoutlier=false   ;
    	   }else{
    	   eoutlier=true; 
    	   }
    	   return eoutlier;
    	   
    	  
       }
    
      
      
      public static  Map<String,DadoTemporal> getSerieMapaSemOutliersGB(Map<String,DadoTemporal> serieAnalisada){
    	  DescriptiveStatistics dsc = new DescriptiveStatistics();
    	  Map<String,DadoTemporal> serieSemOutliers =new HashMap<String,DadoTemporal>();
    	  Set<String> chaves = serieAnalisada.keySet();
    	  //valuetot=new double[chaves.size()];
		  	for (String data : chaves){
		  		String [] datastr=data.split("/");
		  		Integer anoatual=Integer.parseInt(datastr[2]);
		  		DadoTemporal dado = new DadoTemporal();
		  		dado=serieAnalisada.get(data);
	 		  	if(dado.getValor() != -99999 || dado.getValor() > 0){
	 		  	dsc.addValue(Math.log(dado.getValor()));
	 		  	}
		  		
		  	}
    	  
       	 double m=dsc.getMean();
       	 double st=dsc.getStandardDeviation();
       	 double N=dsc.getN();
       	 double kn= (-3.62201)+(6.28446*Math.pow(N, 0.25))-(2.49835*Math.pow(N, 0.50))+(0.491436*Math.pow(N, 0.75))-(0.037911*N);
       	 
       	 double xh=Math.exp(m+kn*st);
       	 double limsup=xh;
       	 double xl=Math.exp(m-kn*st);
       	 double liminf=xl;
    	  
       	for (String data : chaves){
	  		DadoTemporal dadoSemOutliers = new DadoTemporal();
	  		double valor=serieAnalisada.get(data).getValor();
	  		//if(valor != -99999 || valor > 0){
 		  	if(valor != -99999 || valor >= 0){
 		  		if(valor >= liminf && valor <= limsup){
 		  			dadoSemOutliers.setData(data);
 		  			dadoSemOutliers.setValor(valor);
 		  			serieSemOutliers.put(data, dadoSemOutliers);
 		  		}
 		  	
 		  	}
	  		
	  	}
    	  
    	  
    	  
    	  return serieSemOutliers;
    	  
    	  
      }
            
      
      public static  Map<String,DadoTemporal> getSerieMapaSemOutliersGBeBP(Map<String,DadoTemporal> serieAnalisada){
    	  DescriptiveStatistics dsc = new DescriptiveStatistics();
    	  DescriptiveStatistics dscLog = new DescriptiveStatistics();
    	  Map<String,DadoTemporal> serieSemOutliers =new HashMap<String,DadoTemporal>();
    	  Set<String> chaves = serieAnalisada.keySet();
    	  //valuetot=new double[chaves.size()];
		  	for (String data : chaves){
		  		String [] datastr=data.split("/");
		  		Integer anoatual=Integer.parseInt(datastr[2]);
		  		DadoTemporal dado = new DadoTemporal();
		  		dado=serieAnalisada.get(data);
	 		  	if(dado.getValor() != -99999 || dado.getValor() > 0){
	 		  	dsc.addValue(dado.getValor());
	 		  	dscLog.addValue(Math.log(dado.getValor()));
	 		  	}
		  		
		  	}
    	  
       	 double m=dscLog.getMean();
       	 double st=dscLog.getStandardDeviation();
       	 double N=dscLog.getN();
       	 double kn= (-3.62201)+(6.28446*Math.pow(N, 0.25))-(2.49835*Math.pow(N, 0.50))+(0.491436*Math.pow(N, 0.75))-(0.037911*N);
       	 
       	 double xh=Math.exp(m+kn*st);
       	 Double limsup=xh;
       	 double xl=Math.exp(m-kn*st);
       	 Double liminf=xl;
    	  
       	 
       double q25 = dsc.getPercentile(25.0);
  	   double q75 = dsc.getPercentile(75.0);
  	   
  	   
  	   double iqr=q75-q25;
  	   double xlBP=q25-3*iqr;
  	   double liminfBP=xlBP;
  	   double xhBP=q75+3*iqr;
  	   double limsupBP=xhBP;
      
  	   
  	   if(limsup.isNaN()){
  	     
  	   }
  	    
  	   if(liminf.isNaN()){
         
       }
       	for (String data : chaves){
	  		DadoTemporal dadoSemOutliers = new DadoTemporal();
	  		double valor=serieAnalisada.get(data).getValor();
	  		//if(valor != -99999 || valor >= 0){
 		  	if(valor != -99999 || valor >= 0){//Saulo 13/04/2020 - Tem que ver se e mesmo apenas maior que zero ou tem q ser maior ou igual a zero
 		  		if((valor >= liminf && valor <= limsup) && (valor >= liminfBP && valor <= limsupBP)){
 		  			dadoSemOutliers.setData(data);
 		  			dadoSemOutliers.setValor(valor);
 		  			serieSemOutliers.put(data, dadoSemOutliers);
 		  		}else{
 		  			System.out.println("E outliers");
 		  		}
 		  	
 		  	}
	  		
	  	}
    	  
    	  
    	  
    	  return serieSemOutliers;
    	  
    	  
      }
      
      
      
      
      
      
      
      
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*String dir="C:\\OpenJump150\\Exemplos\\ArqTestes\\";
		String filename="QmensaisPonteNovaParaopeba.dat";
		StringTokenizer tok;
		double [][] value=new double[1000][1000];
		
		 
			BufferedReader file;
			try {
			file = new BufferedReader(new FileReader(dir + filename));
			String str = file.readLine();
			str = file.readLine();
			tok = new StringTokenizer(str);
			
			int ngauges = Integer.valueOf(tok.nextToken(" ").trim());
			int [] nvalues=new int [1000];
			for (int i = 0; i < ngauges; i++){
			str = file.readLine();
			tok = new StringTokenizer(str);
			int cod = Integer.valueOf(tok.nextToken(" ").trim());
			str = file.readLine();
			tok = new StringTokenizer(str);
			nvalues [i] = Integer.valueOf(tok.nextToken(" ").trim());
			str = file.readLine();
			tok = new StringTokenizer(str);
					for (int j = 0; j < nvalues[i]; j++){
					value[i][j]=Double.valueOf(tok.nextToken("	").trim());	
					}
			
			}
			//cod=35 - sugar creek -exemplo RAO
			double [] param=new double[10];
			int cod=0;
			double [] x = new double [nvalues [cod]];
			for (int j = 0; j < nvalues[cod]; j++){
			x[j]=value[cod][j];	
			System.out.println(x[j]);
			}*/
			
		
		    double [] x = new double [100];
		    for (int j = 0; j < x.length; j++){
		     x[j]=j+1;
		    }
		    	
		    //int n=nvalues[cod];
			
			Outliers ols = new Outliers(x);
			
			ols.testeGrubbsBeck();
			double xinf=ols.getLiminf();
			double xsup=ols.getLimsup();
			System.out.println("liminf = "+xinf+"  limsup = "+xsup);
			ols.testeBoxPlot();
			xinf=ols.getLiminf();
			xsup=ols.getLimsup();
			System.out.println("liminf = "+xinf+"  limsup = "+xsup);
	
			
	

}
	 
}
