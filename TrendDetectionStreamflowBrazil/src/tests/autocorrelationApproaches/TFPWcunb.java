package tests.autocorrelationApproaches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.DadoTemporal;



public class TFPWcunb {

	
	
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
    	//double r1Original=facOriginal.correlLagMapa(1);
    	//int n=facOriginal.getSeries1().length+1;
    	 //int n=dadostot.size();
    	//double r1AmostralCorrigido=(n*r1Original+2)/(n-4);
    	 
        //String resultado=TesteCorrelacaoLag1Tradicional.executar(r1AmostralCorrigido, n);
		
		//if(resultado.equals("NS")) {
		//	return serieMapa;
		//}
    	    	
    	 Map<Integer,DadoTemporal> mapaAnoDadosOriginal=facOriginal.getMapaAnoDados();
    	 Map<Integer,Integer>mapaAnoOrdem=PegarOrdemCronologicaAnoSerieTemporal.pegarMapaAnoOrdem(dadostot);  	
    	 
    	 Map<String,DadoTemporal> Yt=new HashMap<String,DadoTemporal>(); //Serie com tendencia (original) removida
    	 Map<Integer,Double> Ylt=new HashMap<Integer,Double>(); // Serie com autocorrelação removida
    	 Map<Integer,Double> Y=new HashMap<Integer,Double>(); //Serie com adicao da tendencia mas sem correlacao
    	 Map<String,DadoTemporal> serieMapaY=new HashMap<String,DadoTemporal>(); //Serie MAPA com adicao da tendencia mas sem correlacao
    	/**
    	 * Calcula o b de sen (slope ou declividade)
    	 */
    	// double bsemCronologia=MagnitudeTendencia.bSen(dadostot);
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
		double r1Original=fac.correlLagMapa(1);
		int n=fac.getSeries1().length+1;
		double r1AmostralCorrigido=(n*r1Original+2)/(n-4);
   	     String resultado=TesteCorrelacaoLag1Tradicional.executar(r1Original, n);
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
	  				double ylt=yt-r1AmostralCorrigido*yt1;
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
		//double r1=estimarRo1.getR1amostral();
		double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1corrigido, n);
		
		if(resultado.equals("NS")) {
			return amostra;
		}
		
		ArrayList<Double> amostraDetrendPW=new ArrayList<Double>();
		for(int i=1;i<amostraDetrend.size();i++) {
			//int t=i+1;
			double xtmodmenos1=amostraDetrend.get(i-1);
			double xtmod=amostraDetrend.get(i);
			double ytmod=xtmod-r1corrigido*xtmodmenos1;
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
