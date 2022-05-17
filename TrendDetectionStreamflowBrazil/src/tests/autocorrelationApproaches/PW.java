package tests.autocorrelationApproaches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.DadoTemporal;

//import org.snirh.extremos_unb.deteccao.testes.FuncaoAutoCorrelacaoAnual;
//import org.snirh.extremos_unb.deteccao.testes.MagnitudeTendencia;
//import org.snirh.extremos_unb.tipos.DadoTemporal;
//import org.snirh.extremos_unb.util.PegarOrdemCronologicaAnoSerieTemporal;

public class PW {
	
	
	
	public Map<String,DadoTemporal> executar (Map<String,DadoTemporal> serieMapa) {
		
		ArrayList<DadoTemporal> dadostot = new ArrayList<DadoTemporal>();
  		Set<String> chavesMax =  serieMapa.keySet();
	  	for (String data : chavesMax){
	  	DadoTemporal dado = serieMapa.get(data);
	  	dadostot.add(dado);
	   	}
	  	//
    	Collections.sort(dadostot);
		
		
    	//FuncaoAutoCorrelacaoAnual facOriginal =new FuncaoAutoCorrelacaoAnual(dadostot);
    	//double r1Original=facOriginal.correlLagMapa(1);
    	//int n=facOriginal.getSeries1().length+1;
    	 
    	//double r1AmostralCorrigido=(n*r1Original+2)/(n-4);
		
		
    	FuncaoAutoCorrelacaoAnual facOriginal =new FuncaoAutoCorrelacaoAnual(dadostot);
    	 Map<Integer,DadoTemporal> mapaAnoDadosOriginal=facOriginal.getMapaAnoDados();
    	 Map<Integer,Integer>mapaAnoOrdem=PegarOrdemCronologicaAnoSerieTemporal.pegarMapaAnoOrdem(dadostot);  	
    	 
    	 Map<String,DadoTemporal> Yt=new HashMap<String,DadoTemporal>(); //Serie com tendencia (original) removida
    	 Map<Integer,Double> Ylt=new HashMap<Integer,Double>(); // Serie com autocorrelação removida
    	 Map<Integer,Double> Y=new HashMap<Integer,Double>(); //Serie com adicao da tendencia mas sem correlacao
    	 Map<String,DadoTemporal> serieMapaY=new HashMap<String,DadoTemporal>(); //Serie MAPA com adicao da tendencia mas sem correlacao
    	/**
    	 * Calcula o b de sen (slope ou declividade)
    	 */
    	// double bsemCronologia=MagnitudeTendencia.bSen(this.dadostot);
    	// double b=MagnitudeTendencia.bSenRespeitandoCronologia(this.dadostot);
    	 double b=0.0;//Por isso que é PW 01/12/2021
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
		int n=fac.getSeries1().length+1;
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
	
	
public ArrayList<Double> executar(ArrayList<Double> amostra){
		
	
			PegarRoLag1 estimarRo1=new PegarRoLag1();
			estimarRo1.executar(amostra);
			double r1=estimarRo1.getR1amostral();
			double r1corrigido=estimarRo1.getR1AmostralCorrigido();
			int n=amostra.size();
			String resultado=TesteCorrelacaoLag1Tradicional.executar(r1, n);
			
			if(resultado.equals("NS")) {
				return amostra;
			}
	
			ArrayList<Double> amostraPW=new ArrayList<Double>();
			for(int i=1;i<amostra.size();i++) {
				//int t=i+1;
				double xtmodmenos1=amostra.get(i-1);
				double xtmod=amostra.get(i);
				double ytmod=xtmod-r1*xtmodmenos1;
				amostraPW.add(ytmod);
				
			}
			
		
		return amostraPW;
		
	}

}
