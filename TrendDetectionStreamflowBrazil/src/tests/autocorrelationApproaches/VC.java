package tests.autocorrelationApproaches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import tests.MannKendallTest;
import types.DadoTemporal;



public class VC {

	/**
	 * Variance correct 
	 */
	/**
	 * Rao e Hamed (1998)
	 * @param amostra
	 * @return
	 */
	
public double executar_CF1 (Map<String,DadoTemporal> serieMapa) {
		
	double CF=1;
		ArrayList<DadoTemporal> dadostot = new ArrayList<DadoTemporal>();
  		Set<String> chavesMax =  serieMapa.keySet();
	  	for (String data : chavesMax){
	  	DadoTemporal dado = serieMapa.get(data);
	  	dadostot.add(dado);
	   	}
	  	//
    	Collections.sort(dadostot);
		
		
    	FuncaoAutoCorrelacaoAnual facOriginal =new FuncaoAutoCorrelacaoAnual(dadostot);
    	double r1Original=facOriginal.correlLagMapa(1);
    	//int n=facOriginal.getSeries1().length+1;
    	 int n=dadostot.size();
    	double r1AmostralCorrigido=(n*r1Original+2)/(n-4);
    	String resultado=TesteCorrelacaoLag1Tradicional.executar(r1AmostralCorrigido, n);
		if(resultado.equals("NS")) {
			return CF;
		}
        
		double r1Rank=PegarRoRankDadoRoSerie.executar(r1AmostralCorrigido);
		
		double termo1=2.0/((n)*(n-1)*(n-2));
		double termo2=(n-1)*(n-2)*(n-3)*r1Rank;
		CF=1.0+termo1*termo2;
				
		return CF;
}
	
	
public double executar_CF2 (Map<String,DadoTemporal> serieMapa) {
	
	double CF=1;
		ArrayList<DadoTemporal> dadostot = new ArrayList<DadoTemporal>();
  		Set<String> chavesMax =  serieMapa.keySet();
	  	for (String data : chavesMax){
	  	DadoTemporal dado = serieMapa.get(data);
	  	dadostot.add(dado);
	   	}
	  	//
    	Collections.sort(dadostot);
		
		
    	FuncaoAutoCorrelacaoAnual facOriginal =new FuncaoAutoCorrelacaoAnual(dadostot);
    	double r1Original=facOriginal.correlLagMapa(1);
    	int n=facOriginal.getSeries1().length+1;
    	 //int n=dadostot.size();
    	double r1AmostralCorrigido=(n*r1Original+2)/(n-4);
    	String resultado=TesteCorrelacaoLag1Tradicional.executar(r1AmostralCorrigido, n);
		if(resultado.equals("NS")) {
			return CF;
		}
        
		//double r1Rank=PegarRoRankDadoRoSerie.executar(r1AmostralCorrigido);
		
		CF=1.0+2.0*r1AmostralCorrigido-((2.0/n)*r1AmostralCorrigido);
				
		return CF;
}
	
public double executar_CF3 (Map<String,DadoTemporal> serieMapa) {
	
	double CF=1;
		ArrayList<DadoTemporal> dadostot = new ArrayList<DadoTemporal>();
  		Set<String> chavesMax =  serieMapa.keySet();
	  	for (String data : chavesMax){
	  	DadoTemporal dado = serieMapa.get(data);
	  	dadostot.add(dado);
	   	}
	  	//
    	Collections.sort(dadostot);
		
		
    	FuncaoAutoCorrelacaoAnual facOriginal =new FuncaoAutoCorrelacaoAnual(dadostot);
    	double r1Original=facOriginal.correlLagMapa(1);
    	int n=facOriginal.getSeries1().length+1;
    	 //int n=dadostot.size();
    	double r1AmostralCorrigido=(n*r1Original+2)/(n-4);
    	String resultado=TesteCorrelacaoLag1Tradicional.executar(r1AmostralCorrigido, n);
		if(resultado.equals("NS")) {
			return CF;
		}
        
		//double r1Rank=PegarRoRankDadoRoSerie.executar(r1AmostralCorrigido);
		
		double termo1=Math.pow(r1AmostralCorrigido, (n+1));
		double termo2=n*Math.pow(r1AmostralCorrigido, 2.0);
		double termo3=(n-1)*r1AmostralCorrigido;
		double termo4=Math.pow((r1AmostralCorrigido-1), 2.0)*n;
		
		CF=1+2.0*((termo1*termo2*termo3)/termo4);
				
		return CF;
}


















	public double executar_CF1(ArrayList<Double> amostra) {
		double CF=1;
		PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostra);
		//double r1=estimarRo1.getR1amostral();
		double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1corrigido, n);
		
		if(resultado.equals("NS")) {
			return CF;
		}
		double r1Rank=PegarRoRankDadoRoSerie.executar(r1corrigido);
		
		double termo1=2.0/((n)*(n-1)*(n-2));
		double termo2=(n-1)*(n-2)*(n-3)*r1Rank;
		CF=1.0+termo1*termo2;
				
		return CF;
		
	}
	
	/**
	 * Yue e Wang (2004) 
	 * @param amostra
	 * @return
	 */
	public double executar_CF2(ArrayList<Double> amostra) {
		double CF=1;
		PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostra);
		//double r1=estimarRo1.getR1amostral();
		double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1corrigido, n);
		
		if(resultado.equals("NS")) {
			return CF;
		}
		//double r1Rank=PegarRoRankDadoRoSerie.executar(r1corrigido);
				
		CF=1.0+2.0*r1corrigido-((2.0/n)*r1corrigido);
				
		return CF;
		
	}
	
	/**
	 * Matalas e Langbein (1962), Lettemaier (1976) e Yue e Wang (2004) 
	 * @param amostra
	 * @return
	 */
	public double executar_CF3(ArrayList<Double> amostra) {
		double CF=1;
		PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostra);
		//double r1=estimarRo1.getR1amostral();
		double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1corrigido, n);
		
		if(resultado.equals("NS")) {
			return CF;
		}
		//double r1Rank=PegarRoRankDadoRoSerie.executar(r1corrigido);
		
		double termo1=Math.pow(r1corrigido, (n+1));
		double termo2=n*Math.pow(r1corrigido, 2.0);
		double termo3=(n-1)*r1corrigido;
		double termo4=Math.pow((r1corrigido-1), 2.0)*n;
		
		CF=1+2.0*((termo1*termo2*termo3)/termo4);
				
		return CF;
		
	}
	
	
	
	public double executar_CF4(ArrayList<Double> amostra) {
		ArrayList<Double> amostraFinal=new ArrayList<Double> ();
		boolean foiSignificativo=false;
		MannKendallTest mkteste=new MannKendallTest(amostra);
        mkteste.teste1();
        double pvalueferahmk=mkteste.getPvalue();
        double alfa=0.05;
         if(pvalueferahmk < alfa){
          foiSignificativo=true;
         }
	
	   	if(foiSignificativo) {
	   		amostraFinal=this.fazerDetrend(amostra);
	     	}else {
	   		amostraFinal=amostra;
	   	}
		
		
		double CF=1;
		PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostraFinal);
		//double r1=estimarRo1.getR1amostral();
		double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1corrigido, n);
		
		if(resultado.equals("NS")) {
			return CF;
		}
		double r1Rank=PegarRoRankDadoRoSerie.executar(r1corrigido);
		
		double termo1=2.0/((n)*(n-1)*(n-2));
		double termo2=(n-1)*(n-2)*(n-3)*r1Rank;
		CF=1.0+termo1*termo2;
				
		return CF;
		
	}
	
	public double executar_CF5(ArrayList<Double> amostra) {
		
		ArrayList<Double> amostraFinal=new ArrayList<Double> ();
		boolean foiSignificativo=false;
		MannKendallTest mkteste=new MannKendallTest(amostra);
        mkteste.teste1();
        double pvalueferahmk=mkteste.getPvalue();
        double alfa=0.05;
         if(pvalueferahmk < alfa){
          foiSignificativo=true;
         }
	
	   	if(foiSignificativo) {
	   		amostraFinal=this.fazerDetrend(amostra);
	     	}else {
	   		amostraFinal=amostra;
	   	}
		
		double CF=1;
		PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostraFinal);
		//double r1=estimarRo1.getR1amostral();
		double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1corrigido, n);
		
		if(resultado.equals("NS")) {
			return CF;
		}
		//double r1Rank=PegarRoRankDadoRoSerie.executar(r1corrigido);
				
		CF=1.0+2.0*r1corrigido-((2.0/n)*r1corrigido);
				
		return CF;
		
	}
	
	
	public double executar_CF6(ArrayList<Double> amostra) {
		double CF=1;
		PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostra);
		double r1=estimarRo1.getR1amostral();
		//double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1, n);
		
		if(resultado.equals("NS")) {
			return CF;
		}
		double r1Rank=PegarRoRankDadoRoSerie.executar(r1);
		
		double termo1=2.0/((n)*(n-1)*(n-2));
		double termo2=(n-1)*(n-2)*(n-3)*r1Rank;
		CF=1.0+termo1*termo2;
				
		return CF;
		
	}
	
	
	
public ArrayList<Double> fazerDetrend(ArrayList<Double> amostra){
		
		ArrayList<Double> amostraDetrend=new ArrayList<Double>();
		double bsen=MagnitudeTendencia.bSenDouble(amostra);
		
		for(int i=0;i<amostra.size();i++) {
			int t=i+1;
			double xt=amostra.get(i);
			double yt=xt-bsen*t;
			amostraDetrend.add(yt);
		}
				
		
		return amostraDetrend;
		
	}
	
	
	
}
