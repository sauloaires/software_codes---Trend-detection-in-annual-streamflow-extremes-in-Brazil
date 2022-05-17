package tests.autocorrelationApproaches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

//import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;
//import org.snirh.extremos_unb.deteccao.testes.MagnitudeTendencia;
//import org.snirh.extremos_unb.tipos.DadoTemporal;
//import org.snirh.extremos_unb.util.PegarOrdemCronologicaAnoSerieTemporal;

import jsc.distributions.StudentsT;
import types.DadoTemporal;

public class MagnitudeTendencia {
	private double [] series1;
	 private double [] series2;
	
	 private ArrayList<Double>bserieOrdenado;
	private  MagnitudeTendencia(double [] series1,double [] series2){
		this.series1=series1;
		this.series2=series2;
	}
	public  MagnitudeTendencia(){
		
	}
	
public ArrayList<Double> getBserieOrdenado() {
   return bserieOrdenado;
 }
public double bSenDoubleInstanciado(ArrayList<Double>dados){
   
   double bsen=-99999.0;
   double [] xdata = new double [dados.size()];
     
     for (int j = 0; j < dados.size(); j++) {
      xdata[j] =j+1;
     } 
   
     
     ArrayList<Double>bserie=new ArrayList<Double>();
     this.bserieOrdenado=new ArrayList<Double>();
     for (int l=0;l<dados.size()-1;l++){
         for (int j=l+1;j<dados.size();j++){
           //double xl=series[l];
           double xl=dados.get(l);
          // double xj=series[j];
           double xj=dados.get(j);
           double b=(xj-xl)/(j-l);
           bserie.add(b);
           this.bserieOrdenado.add(b);
           }
      }
     
     double [] bvetor=new double[bserie.size()];
     for (int i=0;i<bserie.size();i++){
       bvetor[i]=bserie.get(i);
     }
     
   double median = StatUtils.percentile(bvetor, 50); 
   bsen=median;  
   Collections.sort(this.bserieOrdenado);  
   
   
   
   return bsen;
   
   
 }
	
public double bSenDoubleInstanciado(ArrayList<Double>dados,ArrayList<Double>ti){
 
 double bsen=-99999.0;
 double [] xdata = new double [dados.size()];
   
   for (int j = 0; j < ti.size(); j++) {
    xdata[j] =ti.get(j);
   } 
 
   
   ArrayList<Double>bserie=new ArrayList<Double>();
   this.bserieOrdenado=new ArrayList<Double>();
   for (int l=0;l<dados.size()-1;l++){
       for (int j=l+1;j<dados.size();j++){
         //double xl=series[l];
         double tl=ti.get(l);
         double xl=dados.get(l);
        // double xj=series[j];
         double tj=ti.get(j);
         double xj=dados.get(j);
         double b=(xj-xl)/(tj-tl);
         bserie.add(b);
         this.bserieOrdenado.add(b);
         }
    }
   
   double [] bvetor=new double[bserie.size()];
   for (int i=0;i<bserie.size();i++){
     bvetor[i]=bserie.get(i);
   }
   
 double median = StatUtils.percentile(bvetor, 50); 
 bsen=median;  
 Collections.sort(this.bserieOrdenado);  
 
 
 
 return bsen;
 
 
}


	public static double bSenRespeitandoCronologia(Map<String,DadoTemporal> serieMapa){
		ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
		Set<String> chavesMax =  serieMapa.keySet();
		  	for (String data : chavesMax){
		  	DadoTemporal dado = serieMapa.get(data);
		  	dados.add(dado);
		   	}
 	
	  	Collections.sort(dados);
	    Map<Integer,Integer>mapaAnoOrdem=PegarOrdemCronologicaAnoSerieTemporal.pegarMapaAnoOrdem(dados);
	    
		double bsen=-99999.0;
		
		double [] xdata = new double [dados.size()];
		ArrayList<Integer>anos=new ArrayList<Integer>();
		/**
 		 * Saulo - 06/05/2020 - corrigir pra quando tiver falha ele nao verifica o ano repetido
 		 */
		int i1verificaAno=0;
			for (int j = 0; j < dados.size(); j++) {
				Integer anoatual=dados.get(j).pegarAno();
			   // xdata[j] =mapaAnoOrdem.get(ano);;
				if(dados.get(j).getValor() != -99999 || dados.get(j).getValor() > 0){
					anos.add(anoatual);
			  			if(i1verificaAno > 0){
			  				if(anos.get(i1verificaAno).equals(anos)){
				  				Integer anoNovo=anos.get(i1verificaAno)+1;
				  				anos.remove(i1verificaAno);
				  				anos.add(anoNovo);
				  	 	     }
			  				
			  			}
			  			i1verificaAno++;
			  		}
			}	
		
			//ArrayList<Integer>anos=new ArrayList<Integer>();	
			for (int j = 0; j < anos.size(); j++) {
				Integer ano=anos.get(j);
			    xdata[j] =mapaAnoOrdem.get(ano);
			}
			
			
			ArrayList<Double>bserie=new ArrayList<Double>();
			for (int l=0;l<dados.size()-1;l++){
				  for (int j=l+1;j<dados.size();j++){
					  if(dados.get(j).getValor() != -99999 || dados.get(j).getValor() > 0){
					  double xl=dados.get(l).getValor();
					  double l1=xdata[l];
					  double xj=dados.get(j).getValor();
					  double j1=xdata[j];
					 // double b=(xj-xl)/(j-l);
					  double b=(xj-xl)/(j1-l1);
					  bserie.add(b);
					  }
			   	  }
		   }
			 
			double [] bvetor=new double[bserie.size()];
			ArrayList<Double> valor=new ArrayList<Double>();
			DescriptiveStatistics dsc = new  DescriptiveStatistics();
			for (int i=0;i<bserie.size();i++){
				bvetor[i]=bserie.get(i);
				dsc.addValue(bserie.get(i));
				valor.add(bserie.get(i));
			}
			
		double median = StatUtils.percentile(bvetor, 50);	
		//double median2 =dsc.getPercentile(50);
		//Permanencia pm=new Permanencia(valor);
		//double median3=pm.Q(50.0);
		
		
		bsen=median;	
			
		return bsen;
		
		
	}	
	
public static double bSenRespeitandoCronologia(ArrayList<DadoTemporal>dados){
	    Map<Integer,Integer>mapaAnoOrdem=PegarOrdemCronologicaAnoSerieTemporal.pegarMapaAnoOrdem(dados);
	    
		double bsen=-99999.0;
		
		double [] xdata = new double [dados.size()];
		ArrayList<Integer>anos=new ArrayList<Integer>();
		/**
 		 * Saulo - 06/05/2020 - corrigir pra quando tiver falha ele nao verifica o ano repetido
 		 */
		int i1verificaAno=0;
			for (int j = 0; j < dados.size(); j++) {
				Integer anoatual=dados.get(j).pegarAno();
			   // xdata[j] =mapaAnoOrdem.get(ano);;
				if(dados.get(j).getValor() != -99999 || dados.get(j).getValor() > 0){
					anos.add(anoatual);
			  			if(i1verificaAno > 0){
			  				if(anos.get(i1verificaAno).equals(anos)){
				  				Integer anoNovo=anos.get(i1verificaAno)+1;
				  				anos.remove(i1verificaAno);
				  				anos.add(anoNovo);
				  	 	     }
			  				
			  			}
			  			i1verificaAno++;
			  		}
			}	
		
			//ArrayList<Integer>anos=new ArrayList<Integer>();	
			for (int j = 0; j < anos.size(); j++) {
				Integer ano=anos.get(j);
			    xdata[j] =mapaAnoOrdem.get(ano);
			}
			
			
			ArrayList<Double>bserie=new ArrayList<Double>();
			for (int l=0;l<dados.size()-1;l++){
				  for (int j=l+1;j<dados.size();j++){
					  if(dados.get(j).getValor() != -99999 || dados.get(j).getValor() > 0){
					  double xl=dados.get(l).getValor();
					  double l1=xdata[l];
					  double xj=dados.get(j).getValor();
					  double j1=xdata[j];
					 // double b=(xj-xl)/(j-l);
					  double b=(xj-xl)/(j1-l1);
					  bserie.add(b);
					  }
			   	  }
		   }
			 
			double [] bvetor=new double[bserie.size()];
			ArrayList<Double> valor=new ArrayList<Double>();
			DescriptiveStatistics dsc = new  DescriptiveStatistics();
			for (int i=0;i<bserie.size();i++){
				bvetor[i]=bserie.get(i);
				dsc.addValue(bserie.get(i));
				valor.add(bserie.get(i));
			}
			
		double median = StatUtils.percentile(bvetor, 50);	
		//double median2 =dsc.getPercentile(50);
		//Permanencia pm=new Permanencia(valor);
		//double median3=pm.Q(50.0);
		
		
		bsen=median;	
			
		return bsen;
		
		
	}
	
	
	
	
	
	
	
	public static double bSen(ArrayList<DadoTemporal>dados){
		
		double bsen=-99999.0;
		double [] xdata = new double [dados.size()];
			
			for (int j = 0; j < dados.size(); j++) {
			 xdata[j] =j+1;
			}	
		
			
			ArrayList<Double>bserie=new ArrayList<Double>();
			for (int l=0;l<dados.size()-1;l++){
				  for (int j=l+1;j<dados.size();j++){
					  //double xl=series[l];
					  double xl=dados.get(l).getValor();
					 // double xj=series[j];
					  double xj=dados.get(j).getValor();
					  double b=(xj-xl)/(j-l);
					  bserie.add(b);
			   	  }
		   }
			
			double [] bvetor=new double[bserie.size()];
			for (int i=0;i<bserie.size();i++){
				bvetor[i]=bserie.get(i);
			}
			
		double median = StatUtils.percentile(bvetor, 50);	
		bsen=median;	
			
		return bsen;
		
		
	}
	
	
public static double bSenDouble(ArrayList<Double>dados){
   
   double bsen=-99999.0;
   double [] xdata = new double [dados.size()];
     
     for (int j = 0; j < dados.size(); j++) {
      xdata[j] =j+1;
     } 
   
     
     ArrayList<Double>bserie=new ArrayList<Double>();
     for (int l=0;l<dados.size()-1;l++){
         for (int j=l+1;j<dados.size();j++){
           //double xl=series[l];
           double xl=dados.get(l);
          // double xj=series[j];
           double xj=dados.get(j);
           double b=(xj-xl)/(j-l);
           bserie.add(b);
           }
      }
     
     double [] bvetor=new double[bserie.size()];
     for (int i=0;i<bserie.size();i++){
       bvetor[i]=bserie.get(i);
     }
     
   double median = StatUtils.percentile(bvetor, 50); 
   bsen=median;  
     
   return bsen;
   
   
 }
	
	
	
	
public static double bSen(double [] series){
		
		double bsen=-99999.0;
		double [] xdata = new double [series.length];
			
			for (int j = 0; j < series.length; j++) {
			 xdata[j] =j+1;
			}	
		
			
			ArrayList<Double>bserie=new ArrayList<Double>();
			for (int l=0;l<series.length-1;l++){
				  for (int j=l+1;j<series.length;j++){
					  double xl=series[l];
					  double xj=series[j];
					  double b=(xj-xl)/(j-l);
					  bserie.add(b);
			   	  }
		   }
			
			double [] bvetor=new double[bserie.size()];
			for (int i=0;i<bserie.size();i++){
				bvetor[i]=bserie.get(i);
			}
			
		double median = StatUtils.percentile(bvetor, 50);	
		bsen=median;	
			
		return bsen;
		
		
	}
	
	
 public static double bSenPadronizado(double [] series){
		
		double bsen=-99999.0;
		double [] xdata = new double [series.length];
		double [] ydata = new double [series.length];
		double media=StatUtils.mean(series);
		double dp=Math.sqrt(StatUtils.variance(series));
		//DescriptiveStatistics dsct = DescriptiveStatistics.newInstance();
			for (int j = 0; j < series.length; j++) {
			 xdata[j] =j+1;
			 //dsct.addValue(j+1);
			 ydata[j]=(series[j]-media)/dp;
			}	
		   //double t=dsct.getPercentile(0.5);
			
			DescriptiveStatistics dsc = new  DescriptiveStatistics();
			ArrayList<Double>bserie=new ArrayList<Double>();
			
			for (int l=0;l<series.length-1;l++){
				  for (int j=l+1;j<series.length;j++){
					  double xl=ydata[l];
					  double xj=ydata[j];
					  double b=(xj-xl)/(j-l);
					  bserie.add(b);
					  dsc.addValue(b);
			   	  }
		   }
			
			double [] bvetor=new double[bserie.size()];
			for (int i=0;i<bserie.size();i++){
				bvetor[i]=bserie.get(i);
			}
			
		double median = StatUtils.percentile(bvetor, 50);	
		double mediant1 =dsc.getPercentile(0.5);
		double mediant2 =dsc.getPercentile(50);
		bsen=median;	
			
		return bsen;
		
		
	}
	
	
	/**
	 * 
	 * @param b0 = Coefiente Linear - Intercepto do bsen
	 * @param bsen = Coeficiente Angular
	 * @param dados
	 * @return
	 */
 public static double pegarErroPadraobSen(double b0,double bsen,ArrayList<Double>dados){
   
   int N=dados.size();
   double erroPadraoBsen=-99999.0;
   double [] xdata = new double [dados.size()];
   //DescriptiveStatistics dsc = new  DescriptiveStatistics();
   DescriptiveStatistics dscsX=new  DescriptiveStatistics();
   DescriptiveStatistics dscsY=new  DescriptiveStatistics();
   DescriptiveStatistics dscsei2=new  DescriptiveStatistics();
     for (int j = 0; j < dados.size(); j++) {
      xdata[j] =j+1;
      dscsX.addValue(xdata[j]);
      dscsY.addValue(dados.get(j));
      double ei=dados.get(j)-(b0+xdata[j]*bsen);
      double ei2=Math.pow(ei, 2);
      dscsei2.addValue(ei2);
     }
     double se2=dscsei2.getSum()/(N-2);
     DescriptiveStatistics dscsX2=new  DescriptiveStatistics();
     for (int j = 0; j < dados.size(); j++) {
       double difx2=Math.pow(xdata[j]-dscsX.getMean(), 2);
       dscsX2.addValue(difx2);
     }
     
     double valor=se2/dscsX2.getSum();
     double sb=Math.sqrt(valor);
     erroPadraoBsen=sb;
     
     return erroPadraoBsen;
   
 }
	
 
public static double pegarB0(double bsen,ArrayList<Double>dados){
   
   int N=dados.size();
   double b0=-99999.0;
   double [] xdata = new double [dados.size()];
   DescriptiveStatistics dscsX=new  DescriptiveStatistics();
   DescriptiveStatistics dscsY=new  DescriptiveStatistics();
    for (int j = 0; j < dados.size(); j++) {
      xdata[j] =j+1;
      dscsX.addValue(xdata[j]);
      dscsY.addValue(dados.get(j));
     }
     
     b0=dscsY.getMean()-bsen*dscsX.getMean();
     return b0;
     
}
 









public static double pegarErroPadraobSen(double b0,double bsen,ArrayList<Double>dados,ArrayList<Double>dadosX){
 
 int N=dados.size();
 double erroPadraoBsen=-99999.0;
 double [] xdata = new double [dados.size()];
 DescriptiveStatistics dscsX=new  DescriptiveStatistics();
 DescriptiveStatistics dscsY=new  DescriptiveStatistics();
 DescriptiveStatistics dscsei2=new  DescriptiveStatistics();
   for (int j = 0; j < dados.size(); j++) {
    xdata[j] =dadosX.get(j);
    dscsX.addValue(xdata[j]);
    dscsY.addValue(dados.get(j));
    double ei=dados.get(j)-(b0+xdata[j]*bsen);
    double ei2=Math.pow(ei, 2);
    dscsei2.addValue(ei2);
   }
   double somase=dscsei2.getSum();
   double se2=dscsei2.getSum()/(N-2);
   DescriptiveStatistics dscsX2=new  DescriptiveStatistics();
   for (int j = 0; j < dados.size(); j++) {
     double difx2=Math.pow(xdata[j]-dscsX.getMean(), 2);
     dscsX2.addValue(difx2);
   }
   
   double valor=se2/dscsX2.getSum();
   double sb=Math.sqrt(valor);
   erroPadraoBsen=sb;
       
   return erroPadraoBsen;
 
}


public static double pegarB0(double bsen,ArrayList<Double>dados,ArrayList<Double>dadosX){
 
 int N=dados.size();
 double b0=-99999.0;
 double [] xdata = new double [dados.size()];
 DescriptiveStatistics dscsX=new  DescriptiveStatistics();
 DescriptiveStatistics dscsY=new  DescriptiveStatistics();
  for (int j = 0; j < dados.size(); j++) {
    xdata[j] =dadosX.get(j);
    dscsX.addValue(xdata[j]);
    dscsY.addValue(dados.get(j));
   }
   
   b0=dscsY.getMean()-bsen*dscsX.getMean();
   return b0;
   
}



public static ArrayList<Double> pegarArrayResiduo(double b0,double bsen,ArrayList<Double>serieY,ArrayList<Double>serieX){
 
 int N=serieY.size();
 double erroPadraoBsen=-99999.0;
 ArrayList<Double>serieEi=new ArrayList<Double>();
 
   for (int j = 0; j < serieY.size(); j++) {
    double ei=serieY.get(j)-(b0+serieX.get(j)*bsen);
    serieEi.add(ei);
  }
  
   return serieEi;
 
}







 
 public static void main(String[] arg){
   
   ArrayList<Double>dados=new ArrayList<Double>();
   dados.add(33.);
   dados.add(24.);
   dados.add(39.);
   dados.add(31.);
   dados.add(23.);
   
   ArrayList<Double>dadosX=new ArrayList<Double>();
   dadosX.add(12.);
   dadosX.add(8.);
   dadosX.add(14.);
   dadosX.add(10.);
   dadosX.add(6.);
   
   double bsen=2.05;
   double b0=MagnitudeTendencia.pegarB0(bsen, dados, dadosX);
   
   double sb=MagnitudeTendencia.pegarErroPadraobSen(b0, bsen, dados, dadosX);
   SimpleRegression regsim =new SimpleRegression();
   for (int j = 0; j < dados.size(); j++) {
     
     regsim.addData(dadosX.get(j), dados.get(j));
     
     System.out.println();
   }
   double b02=regsim.getIntercept();
   double b12=regsim.getSlope();
   double sb2=regsim.getSlopeStdErr();
   double ic=regsim.getSlopeConfidenceInterval(0.05);
   double se2=regsim.getSumSquaredErrors();
   
   int nteste=5;
   StudentsT tstudent = new StudentsT(nteste-2);
   double alfa=0.05;
   double alfa_div_2=alfa/2.0;
   double nsfinal=(1-alfa_div_2);
   double talfa=tstudent.inverseCdf(nsfinal);
   double icfixo=talfa*sb;
   double ics=bsen+talfa*sb;
   double ici=bsen-talfa*sb;
   System.out.println("finalizou");
 }
	
}
