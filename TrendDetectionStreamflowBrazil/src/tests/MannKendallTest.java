package tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

//import org.snirh.extremos_unb.deteccao.testes.MannKendallTest;
//import org.snirh.extremos_unb.deteccao.testes.VarianciaTieValuesMannKendall;
//import org.snirh.extremos_unb.tipos.DadoTemporal;
//import org.snirh.util.sort.Esco7;

import flanagan.analysis.Stat;
import types.DadoTemporal;
import util.Esco7;

public class MannKendallTest {

	
	double [] valuetot;
	double [] valuex;
	double [] valuey;
	double  pvalue;
	double  estatteste;
	
	double  valorcriticoteste;
	String  resultadoteste;
	int divisao;
	
	private String sentidoTendencia;
	private String resultesteTexto;
	private String [] dataStr;
    private int [] dataAno;
    ArrayList<DadoTemporal> dadostot;
	ArrayList<DadoTemporal> dadosx;
	ArrayList<DadoTemporal> dadosy;
	
	
	/**
	 * 0 - Bicauldal
	 * 1 - Unicauldal a direita
	 * 2 - Unicauldal a esquerda
	 */
	private int tipoHipotese;
	private String tipoHipoteseStr;
	/**
	 * 1 - 100%
	 */
	
	private double nivelSignificancia;
	private final String HIPOTESE_NULA="There is no trend in the data";
	private final String HIPOTESE_ALTERNATIVA_BICAULDAL="There is a trend in the data";
	private final String HIPOTESE_ALTERNATIVA_UNICAULDAL_DIREITA="There is a increase trend in the data";
	private final String HIPOTESE_ALTERNATIVA_UNICAULDAL_ESQUERDA="There is a decrease trend in the data";
	
	
	private double  estattesteBootstrap;
	
	
	private double S; //estatistica S do teste MK
	
	
	public double getEstattesteBootstrap() {
		return estattesteBootstrap;
	}

	public void setEstattesteBootstrap(double estattesteBootstrap) {
		this.estattesteBootstrap = estattesteBootstrap;
	}

	public String getTipoHipoteseStr() {
		return tipoHipoteseStr;
	}

	public void setTipoHipoteseStr(String tipoHipoteseStr) {
		this.tipoHipoteseStr = tipoHipoteseStr;
	}
		
	
	public String getSentidoTendencia() {
		return sentidoTendencia;
	}

	public void setSentidoTendencia(String sentidoTendencia) {
		this.sentidoTendencia = sentidoTendencia;
	}
	
	public ArrayList<DadoTemporal> getDadostot() {
		return dadostot;
	}

	public void setDadostot(ArrayList<DadoTemporal> dadostot) {
		this.dadostot = dadostot;
	}

	public ArrayList<DadoTemporal> getDadosx() {
		return dadosx;
	}

	public void setDadosx(ArrayList<DadoTemporal> dadosx) {
		this.dadosx = dadosx;
	}

	public ArrayList<DadoTemporal> getDadosy() {
		return dadosy;
	}

	public void setDadosy(ArrayList<DadoTemporal> dadosy) {
		this.dadosy = dadosy;
	}

	public String [] getDataStr() {
		return dataStr;
	}

	public void setDataStr(String [] dataStr) {
		this.dataStr = dataStr;
	}

	public String getResultesteTexto() {
		return resultesteTexto;
	}

	public void setResultesteTexto(String resultesteTexto) {
		this.resultesteTexto = resultesteTexto;
	}
	
		
	public  MannKendallTest(ArrayList<Double> serieArray, int tipoHipotese, double nivelSignificancia){
    this.tipoHipotese=tipoHipotese;
    this.nivelSignificancia=nivelSignificancia;
    
      this.valuetot=new double[serieArray.size()];
      this.dataAno=new int[serieArray.size()];
      this.dataStr=new String [serieArray.size()];
      for (int i=0;i<serieArray.size();i++){
      this.valuetot[i] =serieArray.get(i);
      }
  
      if(this.tipoHipotese ==1){
        this.setTipoHipoteseStr("Unicauldal a direita");
      }else if(this.tipoHipotese ==2){
        this.setTipoHipoteseStr("Unicauldal a esquerda");
      }else{
        this.setTipoHipoteseStr("Bicauldal");
      }
      this.dividirSerie();
  }
	
	
	
	
    public  MannKendallTest(Map<String,DadoTemporal> serieMapa, int tipoHipotese, double nivelSignificancia){
    	this.tipoHipotese=tipoHipotese;
    	this.nivelSignificancia=nivelSignificancia;
    	ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
  		Set<String> chavesMax =  serieMapa.keySet();
	  	for (String data : chavesMax){
	  	DadoTemporal dado = serieMapa.get(data);
	  	dados.add(dado);
	   	}
	  	
    	Collections.sort(dados);
      	this.setDadostot(dados);
      	this.valuetot=new double[this.dadostot.size()];
      	this.dataAno=new int[this.dadostot.size()];
      	this.dataStr=new String [this.dadostot.size()];
      	for (int i=0;i<this.dadostot.size();i++){
      		
      	//int valorInt=(int)Math.round(this.dadostot.get(i).getValor());
      	//this.valuetot[i]=valorInt;
      	this.valuetot[i]=this.dadostot.get(i).getValor();
      	this.dataStr[i]=this.dadostot.get(i).getDataStr();
      	this.dataAno[i]=this.dadostot.get(i).pegarAno();
      	}
  	
      	if(this.tipoHipotese ==1){
      		this.setTipoHipoteseStr("Unicauldal a direita");
      	}else if(this.tipoHipotese ==2){
      		this.setTipoHipoteseStr("Unicauldal a esquerda");
      	}else{
      		this.setTipoHipoteseStr("Bicauldal");
      	}
      	this.dividirSerie();
    }
        
    
    
    public void dividirSerie(){
    	int metade = this.valuetot.length/2;
		this.valuex=new double[metade];
		this.valuey=new double[this.valuetot.length-metade];
		for (int i=0;i<metade;i++){
		this.valuex[i]=this.valuetot[i];
		}
		int i1=0;
		for (int i=metade;i<this.valuetot.length;i++){
		this.valuey[i1]=this.valuetot[i];
		i1++;
		}
		
	}
    
   
    
     public boolean anoDivExiste(){
	
    	 return false;
    	 
     }
	
     public  MannKendallTest(double [] valuetot){
 		
 		this.valuetot=new double[valuetot.length];
 		this.valuetot= valuetot;
 		this.dividirSerie();
 		this.tipoHipotese=0;
	    this.nivelSignificancia=5.0;
 	}
	
	
public  MannKendallTest(ArrayList<Double> valuetotlist, int tipoHipotese){
       
       this.valuetot=new double[valuetotlist.size()];
       for(int i=0;i<valuetotlist.size();i++){
         this.valuetot[i] =valuetotlist.get(i);
       }
       
      // this.valuetot= valuetot;
       this.dividirSerie();
       this.tipoHipotese=tipoHipotese;
         this.nivelSignificancia=5.0;
       
     }
	
	
public  MannKendallTest(ArrayList<Double> valuetotlist){
  
  this.valuetot=new double[valuetotlist.size()];
  for(int i=0;i<valuetotlist.size();i++){
    this.valuetot[i] =valuetotlist.get(i);
  }
  
 // this.valuetot= valuetot;
  this.dividirSerie();
  this.tipoHipotese=0;
    this.nivelSignificancia=5.0;
  
}
	
	
	public double getPvalue() {
		return pvalue;
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
	
		
    public  MannKendallTest(double [] valuex,double [] valuey){
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
		
		this.tipoHipotese=0;
	     this.nivelSignificancia=5.0;
		
	}
	
    public  MannKendallTest(double [] valuetot, int divisao){
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
				 
		  this.tipoHipotese=0;
	      this.nivelSignificancia=5.0;
		
	}
	
    public void teste1 (double [] vcritico) {
    	
    	   	
    	
    	
		Stat sttotal = new Stat(this.valuetot);
		//sttotal.setDenominatorToNminusOne();
		sttotal.setDenominatorToN();
		double totmedia =sttotal.mean_as_double(); 
		double totdesvpad =sttotal.standardDeviation_as_double();
		int N = this.valuetot.length;
		int[] Ri=new int [N];
		
				
		double x=0.0;
		int[] indices=new int [this.valuetot.length];
		indices = Esco7.ordenar(this.valuetot.length, this.valuetot) ;
		for (int i=0;i<this.valuetot.length;i++){
			if(indices[i] <= this.valuex.length-1){
				Ri[indices[i]]=i+1;
			}else{
				Ri[indices[i]]=i+1;
			}
		}
		
		for (int i=0;i<this.valuetot.length-1;i++){
			if(this.valuetot[indices[i]] == this.valuetot[indices[i+1]]){
				Ri[indices[i+1]]=Ri[indices[i]];
			}
			
		}
		double S=0.0;
		double soma =0;
		for (int i=0;i<this.valuetot.length-1;i++){
			
			  for (int j=i+1;j<this.valuetot.length;j++){
				  x=0.0;
				  x=Ri[j]-Ri[i];
				     if(x > 0){
				     soma=soma+1; 
				     }else if(x<0){
				     soma=soma-1;	 
				     }else{
				     soma=soma+0; 
				     }
		     
		   	}
			 // System.out.println("Soma acumulada  "+soma);	 
	 }
		 S=soma;
	     double desvpadtest=Math.sqrt((N*(N-1)*(2*N+5))/18);
         double zcrit = Math.abs(S)/desvpadtest;
         double zcritBoot = S/desvpadtest;
         this.setEstatteste(zcrit);
         this.setEstattesteBootstrap(zcritBoot);
         
         if(S <0){
        	this.setSentidoTendencia("Decrease"); 
         }else{
        	 this.setSentidoTendencia("Increase"); 
         }
                   
         
         //Quando o teste for bicaudal deve multiplicar por 2
		 
         if(this.tipoHipotese >0){
        	 double pvalue = (1-Stat.gaussianCDF(0.0, 1.0, zcrit));
    		 this.setPvalue(pvalue);
    		 double ns=(100-this.nivelSignificancia)/100.0;
    		 double nsgui=(this.nivelSignificancia)/100.0;
    		 double vcritteste=vcritico[0];
    		 
    		 //double vcritteste=Stat.gaussianInverseCDF(ns);	 
        	 
    		 if(this.tipoHipotese == 1){
        		
        		 if(S <0){
        			 this.setValorcriticoteste(vcritteste);
        			 this.setResultadoteste("NS");
        			 this.setResultesteTexto("The null hypothesis that the data do not show an INCREASING trend cannot be rejected.");
        		 }else if(zcritBoot > vcritteste){
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("S("+this.nivelSignificancia+")");
    					 this.setResultesteTexto("reject the null hypothesis that the data show an INCREASING trend at the level of "+this.nivelSignificancia+"%");
    			}else{
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("NS");
    					 this.setResultesteTexto("The null hypothesis that the data do not show an INCREASING trend cannot be rejected.");
    			}
        		 
        	 }else{
        		 
        		 if(S > 0){
        			 this.setValorcriticoteste(vcritteste);
        			 this.setResultadoteste("NS");
        			 this.setResultesteTexto("The null hypothesis that the data do not show a DECREASING trend cannot be rejected");
        		 }else if(zcritBoot < vcritteste){
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("S("+this.nivelSignificancia+")");
    					 this.setResultesteTexto("reject the null hypothesis that the data show an DECREASING trend at the level of "+this.nivelSignificancia+"%");
    			}else{
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("NS");
    					 this.setResultesteTexto("The null hypothesis that the data do not show an DECREASING trend cannot be rejected.");
    			} 
        		 
        	 }
    		 
         }else{
        	 
        	 double pvalue = (1-Stat.gaussianCDF(0.0, 1.0, zcrit))*2.0;
    		 this.setPvalue(pvalue);
    		 double nsBicaudal=this.nivelSignificancia/2.0;
    		 double ns=(100-nsBicaudal)/100.0;
    		 double nsgui=(this.nivelSignificancia)/100.0;
    		 double vcritinf=vcritico[0];
    		 double vcritsup=vcritico[1];
    				 if(zcritBoot < vcritinf){
    					 this.setValorcriticoteste(vcritinf);
    					 this.setResultadoteste("S("+this.nivelSignificancia+")");
    					 this.setResultesteTexto("reject the null hypothesis that the data show a trend at the level of "+this.nivelSignificancia+"%");
    				 }else if(zcritBoot > vcritsup){
    					 this.setValorcriticoteste(vcritsup);
    					 this.setResultadoteste("S("+this.nivelSignificancia+")");
    					 this.setResultesteTexto("reject the null hypothesis that the data show a trend at the level of "+this.nivelSignificancia+"%");
    				
    				 }else{
    					 this.setValorcriticoteste(vcritsup);
    					 this.setResultadoteste("NS");
    					 this.setResultesteTexto("One cannot reject the null hypothesis that the data do not show a trend.");
    				 }
           }
		 
         
        
	    }
    
    
public void teste1 () {
    	
    	 	
		Stat sttotal = new Stat(this.valuetot);
		//sttotal.setDenominatorToNminusOne();
		sttotal.setDenominatorToN();
		double totmedia =sttotal.mean_as_double(); 
		double totdesvpad =sttotal.standardDeviation_as_double();
		int N = this.valuetot.length;
		int[] Ri=new int [N];
		
				
		double x=0.0;
		int[] indices=new int [this.valuetot.length];
		indices = Esco7.ordenar(this.valuetot.length, this.valuetot) ;
		for (int i=0;i<this.valuetot.length;i++){
			if(indices[i] <= this.valuex.length-1){
				Ri[indices[i]]=i+1;
			}else{
				Ri[indices[i]]=i+1;
			}
		}
		
		for (int i=0;i<this.valuetot.length-1;i++){
			if(this.valuetot[indices[i]] == this.valuetot[indices[i+1]]){
				Ri[indices[i+1]]=Ri[indices[i]];
			}
			
		}
		double S=0.0;
		double soma =0;
		for (int i=0;i<this.valuetot.length-1;i++){
			
			  for (int j=i+1;j<this.valuetot.length;j++){
				  x=0.0;
				  x=Ri[j]-Ri[i];
				     if(x > 0){
				     soma=soma+1; 
				     }else if(x<0){
				     soma=soma-1;	 
				     }else{
				     soma=soma+0; 
				     }
		     
		   	}
			 // System.out.println("Soma acumulada  "+soma);	 
	 }
		 S=soma;
		 
		 this.S=S;
		 double parteSemTiedValue=(N*(N-1)*(2*N+5));
		 double parteComTiedValue=0.0;
		 boolean pegarParteTiedValue=true;
		 if(pegarParteTiedValue){
		   parteComTiedValue=VarianciaTieValuesMannKendall.executar(valuetot);
		 }
	   //double desvpadtest=Math.sqrt((N*(N-1)*(2*N+5))/18);
		 double desvpadtest=Math.sqrt((parteSemTiedValue-parteComTiedValue)/18.0);
	     
	     /**
	      * Saulo 31/01/218 - DesvPadSnovo considerando
	      */
	     
        // double zcrit = Math.abs(S)/desvpadtest;
	     double zcrit = -99999.;
       double zcritBoot = S/desvpadtest;
         
         this.setEstattesteBootstrap(zcritBoot);
         if(S <0){
        	 zcrit = (S+1)/desvpadtest;
        	this.setSentidoTendencia("Decrease"); 
         }else if(S >0){
        	 zcrit = (S-1)/desvpadtest;
        	this.setSentidoTendencia("Increase"); 
         }else{
        	 this.setSentidoTendencia("Decrease"); 
        	 zcrit =0; 
         }
          
         this.setEstatteste(zcrit);      
         //Quando o teste for bicaudal deve multiplicar por 2
		 
         if(this.tipoHipotese >0){
        	 double pvalue = (1-Stat.gaussianCDF(0.0, 1.0, zcrit));
    		 this.setPvalue(pvalue);
    		 double ns=(100-this.nivelSignificancia)/100.0;
    		 double nsgui=(this.nivelSignificancia)/100.0;
    		 double vcritteste=Stat.gaussianInverseCDF(ns);	 
        	 
    		 if(this.tipoHipotese == 1){
        		
        		 if(S <0){
        			 this.setValorcriticoteste(vcritteste);
        			 this.setResultadoteste("NS");
        			 this.setResultesteTexto("The null hypothesis that the data do not show an INCREASING trend cannot be rejected");
        		 }else if(zcrit > vcritteste){
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("S("+this.nivelSignificancia+")");
    					 this.setResultesteTexto("reject the null hypothesis that the data show an INCREASING trend at the level of "+this.nivelSignificancia+"%");
    			}else{
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("NS");
    					 this.setResultesteTexto("The null hypothesis that the data do not show an INCREASING trend cannot be rejected");
    			}
        		 
        	 }else{
        		 
        		 if(S > 0){
        			 this.setValorcriticoteste(vcritteste);
        			 this.setResultadoteste("NS");
        			 this.setResultesteTexto("The null hypothesis that the data do not show a DECREASING trend cannot be rejected");
        		 }else if(zcrit > vcritteste){
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("S("+this.nivelSignificancia+")");
    					 this.setResultesteTexto("reject the null hypothesis that the data show an DECREASING trend at the level of "+this.nivelSignificancia+"% ");
    			}else{
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("NS");
    					 this.setResultesteTexto("The null hypothesis that the data do not show an DECREASING trend cannot be rejected");
    			} 
        		 
        	 }
    		 
         }else{
        	 double pvalVerdadeiro=Stat.gaussianCDF(0.0, 1.0, zcritBoot);
        	 
        	 if(pvalVerdadeiro > 0.5){
        		// System.out.println("Cauda superior");
        	 }else{
        		 //System.out.println("Cauda inferior");
        	 }
        	 zcrit=Math.abs(zcrit);
        	// double pvalueteste = (1-Stat.gaussianCDF(0.0, 1.0, 1.96))*2.0;
        	 double pvalue = (1-Stat.gaussianCDF(0.0, 1.0, zcrit))*2.0;
    		 this.setPvalue(pvalue);
    		 double nsBicaudal=this.nivelSignificancia/2.0;
    		 double ns=(100-nsBicaudal)/100.0;
    		 double nsgui=(this.nivelSignificancia)/100.0;
    		 double vcritteste=Stat.gaussianInverseCDF(ns);
    				 if(zcrit > vcritteste){
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("S("+this.nivelSignificancia+")");
    					 this.setResultesteTexto("reject the null hypothesis that the data show a trend at the level of "+this.nivelSignificancia+"%");
    				 }else{
    					 this.setValorcriticoteste(vcritteste);
    					 this.setResultadoteste("NS");
    					 this.setResultesteTexto("One cannot reject the null hypothesis that the data do not show a trend");
    				 }
           }
		 
         
         		
	    }


public void teste1 (double CF) {
	
 	
	Stat sttotal = new Stat(this.valuetot);
	//sttotal.setDenominatorToNminusOne();
	sttotal.setDenominatorToN();
	double totmedia =sttotal.mean_as_double(); 
	double totdesvpad =sttotal.standardDeviation_as_double();
	int N = this.valuetot.length;
	int[] Ri=new int [N];
	
			
	double x=0.0;
	int[] indices=new int [this.valuetot.length];
	indices = Esco7.ordenar(this.valuetot.length, this.valuetot) ;
	for (int i=0;i<this.valuetot.length;i++){
		if(indices[i] <= this.valuex.length-1){
			Ri[indices[i]]=i+1;
		}else{
			Ri[indices[i]]=i+1;
		}
	}
	
	for (int i=0;i<this.valuetot.length-1;i++){
		if(this.valuetot[indices[i]] == this.valuetot[indices[i+1]]){
			Ri[indices[i+1]]=Ri[indices[i]];
		}
		
	}
	double S=0.0;
	double soma =0;
	for (int i=0;i<this.valuetot.length-1;i++){
		
		  for (int j=i+1;j<this.valuetot.length;j++){
			  x=0.0;
			  x=Ri[j]-Ri[i];
			     if(x > 0){
			     soma=soma+1; 
			     }else if(x<0){
			     soma=soma-1;	 
			     }else{
			     soma=soma+0; 
			     }
	     
	   	}
		 // System.out.println("Soma acumulada  "+soma);	 
 }
	 S=soma;
	 
	 this.S=S;
	 double parteSemTiedValue=(N*(N-1)*(2*N+5));
	 double parteComTiedValue=0.0;
	 boolean pegarParteTiedValue=true;
	 if(pegarParteTiedValue){
	   parteComTiedValue=VarianciaTieValuesMannKendall.executar(valuetot);
	 }
   //double desvpadtest=Math.sqrt((N*(N-1)*(2*N+5))/18);
	 /**
	  * Saulo - 21/04/2020 - Considerar a correção da variancia
	  */
	 double varianciaS=((parteSemTiedValue-parteComTiedValue)/18.0)*CF;
	 double desvpadtest=Math.sqrt(varianciaS);
     
     /**
      * Saulo 31/01/218 - DesvPadSnovo considerando
      */
     
    // double zcrit = Math.abs(S)/desvpadtest;
     double zcrit = -99999.;
   double zcritBoot = S/desvpadtest;
     
     this.setEstattesteBootstrap(zcritBoot);
     if(S <0){
    	 zcrit = (S+1)/desvpadtest;
    	this.setSentidoTendencia("Decrease"); 
     }else if(S >0){
    	 zcrit = (S-1)/desvpadtest;
    	this.setSentidoTendencia("Increase"); 
     }else{
    	 this.setSentidoTendencia("Decrease"); 
    	 zcrit =0; 
     }
      
     this.setEstatteste(zcrit);      
     //Quando o teste for bicaudal deve multiplicar por 2
	 
     if(this.tipoHipotese >0){
    	 double pvalue = (1-Stat.gaussianCDF(0.0, 1.0, zcrit));
		 this.setPvalue(pvalue);
		 double ns=(100-this.nivelSignificancia)/100.0;
		 double nsgui=(this.nivelSignificancia)/100.0;
		 double vcritteste=Stat.gaussianInverseCDF(ns);	 
    	 
		 if(this.tipoHipotese == 1){
    		
    		 if(S <0){
    			 this.setValorcriticoteste(vcritteste);
    			 this.setResultadoteste("NS");
    			 this.setResultesteTexto("The null hypothesis that the data do not show an INCREASING trend cannot be rejected");
    		 }else if(zcrit > vcritteste){
					 this.setValorcriticoteste(vcritteste);
					 this.setResultadoteste("S("+this.nivelSignificancia+")");
					 this.setResultesteTexto("reject the null hypothesis that the data show an INCREASING trend at the level of  "+this.nivelSignificancia+"%");
			}else{
					 this.setValorcriticoteste(vcritteste);
					 this.setResultadoteste("NS");
					 this.setResultesteTexto("The null hypothesis that the data do not show an INCREASING trend cannot be rejected");
			}
    		 
    	 }else{
    		 
    		 if(S > 0){
    			 this.setValorcriticoteste(vcritteste);
    			 this.setResultadoteste("NS");
    			 this.setResultesteTexto("The null hypothesis that the data do not show a DECREASING trend cannot be rejected");
    		 }else if(zcrit > vcritteste){
					 this.setValorcriticoteste(vcritteste);
					 this.setResultadoteste("S("+this.nivelSignificancia+")");
					 this.setResultesteTexto("reject the null hypothesis that the data show an DECREASING trend at the level of "+this.nivelSignificancia+"%");
			}else{
					 this.setValorcriticoteste(vcritteste);
					 this.setResultadoteste("NS");
					 this.setResultesteTexto("The null hypothesis that the data do not show an DECREASING trend cannot be rejected");
			} 
    		 
    	 }
		 
     }else{
    	 double pvalVerdadeiro=Stat.gaussianCDF(0.0, 1.0, zcritBoot);
    	 
    	 if(pvalVerdadeiro > 0.5){
    		// System.out.println("Cauda superior");
    	 }else{
    		 //System.out.println("Cauda inferior");
    	 }
    	 zcrit=Math.abs(zcrit);
    	// double pvalueteste = (1-Stat.gaussianCDF(0.0, 1.0, 1.96))*2.0;
    	 double pvalue = (1-Stat.gaussianCDF(0.0, 1.0, zcrit))*2.0;
		 this.setPvalue(pvalue);
		 double nsBicaudal=this.nivelSignificancia/2.0;
		 double ns=(100-nsBicaudal)/100.0;
		 double nsgui=(this.nivelSignificancia)/100.0;
		 double vcritteste=Stat.gaussianInverseCDF(ns);
				 if(zcrit > vcritteste){
					 this.setValorcriticoteste(vcritteste);
					 this.setResultadoteste("S("+this.nivelSignificancia+")");
					 this.setResultesteTexto("reject the null hypothesis that the data show a trend at the level of"+this.nivelSignificancia+"%");
				 }else{
					 this.setValorcriticoteste(vcritteste);
					 this.setResultadoteste("NS");
					 this.setResultesteTexto("One cannot reject the null hypothesis that the data do not show a trend.");
				 }
       }
	 
     
     		
    }

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String dir="C:\\OpenJump150\\Exemplos\\ArqTestes\\";
		//String filename="QmensaisPonteNovaParaopeba.dat";
		String filename="QmensaisPonteNovaParaopebasemdecimais.dat";
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
			//int cod=13;
			double [] x = new double [nvalues [cod]];
			for (int j = 0; j < nvalues[cod]; j++){
			x[j]=value[cod][j];	
			System.out.println(x[j]);
			}
			int n=nvalues[cod];
			
			MannKendallTest WW = new MannKendallTest(x);
			double nivelsig=0.5;
			WW.teste1();
			
			
			
		
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}



  public double getS() {
    return S;
  }

  public void setS(double s) {
    S = s;
  }

	
}
