package tests.autocorrelationApproaches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


//import org.snirh.extremos_unb.deteccao.testes.MagnitudeTendencia;

import types.DadoTemporal;


public class TFPW {

public Map<String,DadoTemporal> executar_v2 (Map<String,DadoTemporal> serieMapa) {
		
		
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
    	 
       /* String resultado=TesteCorrelacaoLag1Tradicional.executar(r1Original, n);
		
		if(resultado.equals("NS")) {
			return serieMapa;
		}*/
    	    	
    	 Map<Integer,DadoTemporal> mapaAnoDadosOriginal=facOriginal.getMapaAnoDados();
    	 Map<Integer,Integer>mapaAnoOrdem=PegarOrdemCronologicaAnoSerieTemporal.pegarMapaAnoOrdem(dadostot);  	
    	 
    	 Map<String,DadoTemporal> Yt=new HashMap<String,DadoTemporal>(); //Serie com tendencia (original) removida
    	 Map<Integer,Double> Ylt=new HashMap<Integer,Double>(); // Serie com autocorrelação removida
    	 Map<Integer,Double> Y=new HashMap<Integer,Double>(); //Serie com adicao da tendencia mas sem correlacao
    	 Map<String,DadoTemporal> serieMapaY=new HashMap<String,DadoTemporal>(); //Serie MAPA com adicao da tendencia mas sem correlacao
    	/**
    	 * Calcula o b de sen (slope ou declividade)
    	 */
    	 double bsemCronologia=MagnitudeTendencia.bSen(dadostot);
    	 double b=MagnitudeTendencia.bSenRespeitandoCronologia(dadostot);
      	Set<Integer> chaves = mapaAnoDadosOriginal.keySet();
	  	for (Integer ano : chaves){	
    		DadoTemporal dado=new DadoTemporal();
    		//double t=ano; //Importante para considerar a distancia entre dois anos que entre eles exista falha
    		double t=mapaAnoOrdem.get(ano);
    		double xt=mapaAnoDadosOriginal.get(ano).getValor();
    		double yt=xt-b*t;
    			    		
    		
    		dado.setData(mapaAnoDadosOriginal.get(ano).getData());
    		dado.setValor(yt);
    	    Yt.put(mapaAnoDadosOriginal.get(ano).getDataStr(), dado);
    	}
    	
    	int klag=1;
    	FuncaoAutoCorrelacaoAnual fac =new FuncaoAutoCorrelacaoAnual(Yt);
		double r1=fac.correlLagMapa(1);
        String resultado=TesteCorrelacaoLag1Tradicional.executar(r1, n);
		
		if(resultado.equals("NS")) {
			return serieMapa;
		}
		
		ArrayList<Integer>ano=fac.getAno();
		Map<Integer,DadoTemporal> mapaAnoDados=fac.getMapaAnoDados();
      for (int i=0;i<ano.size();i++){
   	   	Integer anolag=ano.get(i)-klag;
	  		if(mapaAnoDados.containsKey(anolag)){
	  			if(mapaAnoDados.get(anolag).getValor() != -99999. || mapaAnoDados.get(anolag).getValor() > 0){
	  				double yt=mapaAnoDados.get(ano.get(i)).getValor();
	  				double yt1=mapaAnoDados.get(anolag).getValor();
	  				double ylt=yt-r1*yt1;
	  				Ylt.put(ano.get(i), ylt);
	  			}
	  		}
    	}
    	
      
      Set<Integer> chavesAno = Ylt.keySet();
	  	for (Integer anoFinal : chavesAno){
	  		double t=mapaAnoOrdem.get(anoFinal);
	  		double ylt=Ylt.get(anoFinal);
	  		double y=ylt+b*t;
	  		Y.put(anoFinal, y);
	  		DadoTemporal dado=new DadoTemporal();
	  		String datastr="01/01/"+anoFinal;
	  		dado.setData(datastr);
	  		dado.setValor(y);
	  		serieMapaY.put(datastr, dado);
	  	}
		return serieMapaY;
    	
    	
    }
	
	
	public Map<String,DadoTemporal> executar (Map<String,DadoTemporal> serieMapa) {
		
		
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
    	 
        String resultado=TesteCorrelacaoLag1Tradicional.executar(r1Original, n);
		
		if(resultado.equals("NS")) {
			return serieMapa;
		}
    	    	
    	 Map<Integer,DadoTemporal> mapaAnoDadosOriginal=facOriginal.getMapaAnoDados();
    	 Map<Integer,Integer>mapaAnoOrdem=PegarOrdemCronologicaAnoSerieTemporal.pegarMapaAnoOrdem(dadostot);  	
    	 
    	 Map<String,DadoTemporal> Yt=new HashMap<String,DadoTemporal>(); //Serie com tendencia (original) removida
    	 Map<Integer,Double> Ylt=new HashMap<Integer,Double>(); // Serie com autocorrelação removida
    	 Map<Integer,Double> Y=new HashMap<Integer,Double>(); //Serie com adicao da tendencia mas sem correlacao
    	 Map<String,DadoTemporal> serieMapaY=new HashMap<String,DadoTemporal>(); //Serie MAPA com adicao da tendencia mas sem correlacao
    	/**
    	 * Calcula o b de sen (slope ou declividade)
    	 */
    	 double bsemCronologia=MagnitudeTendencia.bSen(dadostot);
    	 double b=MagnitudeTendencia.bSenRespeitandoCronologia(dadostot);
      	Set<Integer> chaves = mapaAnoDadosOriginal.keySet();
	  	for (Integer ano : chaves){	
    		DadoTemporal dado=new DadoTemporal();
    		//double t=ano; //Importante para considerar a distancia entre dois anos que entre eles exista falha
    		double t=mapaAnoOrdem.get(ano);
    		double xt=mapaAnoDadosOriginal.get(ano).getValor();
    		double yt=xt-b*t;
    			    		
    		
    		dado.setData(mapaAnoDadosOriginal.get(ano).getData());
    		dado.setValor(yt);
    	    Yt.put(mapaAnoDadosOriginal.get(ano).getDataStr(), dado);
    	}
    	
    	int klag=1;
    	FuncaoAutoCorrelacaoAnual fac =new FuncaoAutoCorrelacaoAnual(Yt);
		double r1=fac.correlLagMapa(1);
		ArrayList<Integer>ano=fac.getAno();
		Map<Integer,DadoTemporal> mapaAnoDados=fac.getMapaAnoDados();
      for (int i=0;i<ano.size();i++){
   	   	Integer anolag=ano.get(i)-klag;
	  		if(mapaAnoDados.containsKey(anolag)){
	  			if(mapaAnoDados.get(anolag).getValor() != -99999. || mapaAnoDados.get(anolag).getValor() > 0){
	  				double yt=mapaAnoDados.get(ano.get(i)).getValor();
	  				double yt1=mapaAnoDados.get(anolag).getValor();
	  				double ylt=yt-r1*yt1;
	  				Ylt.put(ano.get(i), ylt);
	  			}
	  		}
    	}
    	
      
      Set<Integer> chavesAno = Ylt.keySet();
	  	for (Integer anoFinal : chavesAno){
	  		double t=mapaAnoOrdem.get(anoFinal);
	  		double ylt=Ylt.get(anoFinal);
	  		double y=ylt+b*t;
	  		Y.put(anoFinal, y);
	  		DadoTemporal dado=new DadoTemporal();
	  		String datastr="01/01/"+anoFinal;
	  		dado.setData(datastr);
	  		dado.setValor(y);
	  		serieMapaY.put(datastr, dado);
	  	}
		return serieMapaY;
    	
    	
    }
	
public ArrayList<Double> executar(ArrayList<Double> amostra){
		
		ArrayList<Double> amostraDetrend=new ArrayList<Double>();
		double bsen=MagnitudeTendencia.bSenDouble(amostra);
		
		for(int i=0;i<amostra.size();i++) {
			int t=i+1;
			double xt=amostra.get(i);
			double xtmod=xt-bsen*t;
			amostraDetrend.add(xtmod);
		}
			
		
		PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostraDetrend);
		double r1=estimarRo1.getR1amostral();
		double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1, n);
		
		if(resultado.equals("NS")) {
			return amostra;
		}
		
		ArrayList<Double> amostraDetrendPW=new ArrayList<Double>();
		for(int i=1;i<amostraDetrend.size();i++) {
			//int t=i+1;
			double xtmodmenos1=amostraDetrend.get(i-1);
			double xtmod=amostraDetrend.get(i);
			double ytmod=xtmod-r1*xtmodmenos1;
			amostraDetrendPW.add(ytmod);
			
		}
		ArrayList<Double> amostraTFPW=new ArrayList<Double>();
		for(int i=0;i<amostraDetrendPW.size();i++) {
			int t=i+1;
			double ytmod=amostraDetrendPW.get(i);
			double ytmod2=ytmod+bsen*t;
			amostraTFPW.add(ytmod2);
			
		}
		
		
		return amostraTFPW;
		
	}
	

//Saulo 01/12/2021 - fazr o teste de independencia na amostra original e depois aplicar direto o TFPW
//Era assim no inicioo que estava sendo feito
public ArrayList<Double> executar_V2(ArrayList<Double> amostra){
	
	
	PegarRoLag1 estimarRo1_original=new PegarRoLag1();
	estimarRo1_original.executar(amostra);
	double r1_original=estimarRo1_original.getR1amostral();
	int n=amostra.size();
	String resultado=TesteCorrelacaoLag1Tradicional.executar(r1_original, n);
	if(resultado.equals("NS")) {
		return amostra;
	}
	
	ArrayList<Double> amostraDetrend=new ArrayList<Double>();
	double bsen=MagnitudeTendencia.bSenDouble(amostra);
	
	for(int i=0;i<amostra.size();i++) {
		int t=i+1;
		double xt=amostra.get(i);
		double xtmod=xt-bsen*t;
		amostraDetrend.add(xtmod);
	}
		
	
	PegarRoLag1 estimarRo1=new PegarRoLag1();
	estimarRo1.executar(amostraDetrend);
	double r1=estimarRo1.getR1amostral();
	double r1corrigido=estimarRo1.getR1AmostralCorrigido();
	//int n=amostra.size();
	//String resultado=TesteCorrelacaoLag1Tradicional.executar(r1, n);
	
	//if(resultado.equals("NS")) {
	//	return amostra;
	//}
	
	ArrayList<Double> amostraDetrendPW=new ArrayList<Double>();
	for(int i=1;i<amostraDetrend.size();i++) {
		//int t=i+1;
		double xtmodmenos1=amostraDetrend.get(i-1);
		double xtmod=amostraDetrend.get(i);
		double ytmod=xtmod-r1*xtmodmenos1;
		amostraDetrendPW.add(ytmod);
		
	}
	ArrayList<Double> amostraTFPW=new ArrayList<Double>();
	for(int i=0;i<amostraDetrendPW.size();i++) {
		int t=i+1;
		double ytmod=amostraDetrendPW.get(i);
		double ytmod2=ytmod+bsen*t;
		amostraTFPW.add(ytmod2);
		
	}
	
	
	return amostraTFPW;
	
}
}
