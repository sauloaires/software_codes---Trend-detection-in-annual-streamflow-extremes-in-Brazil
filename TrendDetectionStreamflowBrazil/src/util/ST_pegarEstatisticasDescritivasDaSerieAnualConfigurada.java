package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import types.DadoTemporal;
import types.SerieTemporal;



public class ST_pegarEstatisticasDescritivasDaSerieAnualConfigurada {
	/**
	 * Serie anual entre um ano inicial e um ano final
	 * @param anoIni
	 * @param anoFim
	 * @param st
	 * @return
	 */
	
	
	public static  DescriptiveStatistics dsc(int anoIni,int anoFim,SerieTemporal st) {
		//Map<String,DadoTemporal> dados = new HashMap<String,DadoTemporal>();
		
		DescriptiveStatistics dsc = new DescriptiveStatistics();
		Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
		int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
		if(ano[0]>ano[1])return dsc;
		
	    Set<String> chaves = mapaStrDadosger.keySet();
		  	for (String data : chaves){
		  		String [] datastr=data.split("/");
		  		int anoatual=Integer.parseInt(datastr[2]);
		  		Double valor = mapaStrDadosger.get(data).getValor();
		  		if((valor != -99999 || valor > 0) && (anoatual >= ano[0]) && (anoatual <= ano[1])){
		  		//dados.put(data,mapaStrDadosger.get(data));	
		  		dsc.addValue(valor);
		  		}
		  	}
			return dsc;

	}
		
		
	public static  DescriptiveStatistics dscPosterior(int anosepara,SerieTemporal st) {
		
		Map<String,DadoTemporal> dados = new HashMap<String,DadoTemporal>();
		DescriptiveStatistics dsc = new DescriptiveStatistics();
	    Set<String> chaves = st.getMapaStrDados().keySet();
		  	for (String data : chaves){
		  		String [] datastr=data.split("/");
		  		int anoatual=Integer.parseInt(datastr[2]);
		  		Double valor = st.getMapaStrDados().get(data).getValor();
		  		if((valor != -99999 || valor > 0) && (anoatual >= anosepara)){
		  		//dados.put(data,st.getMapaStrDados().get(data));	
		  		dsc.addValue(valor);
		  		}
		  	}
			return dsc;

	}

	public static  DescriptiveStatistics dscAnterior(int anosepara,SerieTemporal st) {
		
		Map<String,DadoTemporal> dados = new HashMap<String,DadoTemporal>();
		DescriptiveStatistics dsc = new DescriptiveStatistics();
	    Set<String> chaves = st.getMapaStrDados().keySet();
		  	for (String data : chaves){
		  		String [] datastr=data.split("/");
		  		int anoatual=Integer.parseInt(datastr[2]);
		  		Double valor = st.getMapaStrDados().get(data).getValor();
		  		if((valor != -99999 || valor > 0) && (anoatual < anosepara)){
		  		//dados.put(data,st.getMapaStrDados().get(data));	
		  		dsc.addValue(valor);
		  		}
		  	}
			return dsc;

	}
	
	
	

	public static DescriptiveStatistics dsc(SerieTemporal st) {
		
		Map<String,DadoTemporal> dados = new HashMap<String,DadoTemporal>();
		DescriptiveStatistics dsc = new DescriptiveStatistics();
	    Set<String> chaves = st.getMapaStrDados().keySet();
		  	for (String data : chaves){
		  		Double valor = st.getMapaStrDados().get(data).getValor();
		  		if(valor != -99999. || valor > 0){
		  		//dados.put(data,st.getMapaStrDados().get(data));	
		  		dsc.addValue(valor);
		  		}else{
		  		  System.out.println();
		  		}
		  	}
			return dsc;

	}
	
public static DescriptiveStatistics dsc(Map<String,DadoTemporal> serieMapa) {
				
		DescriptiveStatistics dsc = new DescriptiveStatistics();
	    Set<String> chaves = serieMapa.keySet();
		  	for (String data : chaves){
		  		Double valor = serieMapa.get(data).getValor();
		  		if(valor != -99999. || valor > 0){
		  		dsc.addValue(valor);
		  		}
		  	}
			return dsc;

	}
	
	
public static DescriptiveStatistics dsc(ArrayList<DadoTemporal> dados) {
		
		//Map<String,DadoTemporal> dados = new HashMap<String,DadoTemporal>();
		DescriptiveStatistics dsc = new DescriptiveStatistics();
	   // Set<String> chaves = st.getMapaStrDados().keySet();
		  	//for (String data : chaves){
		for (int i=0;i<dados.size();i++){
		  		Double valor = dados.get(i).getValor();
		  		if(valor != -99999 || valor > 0){
		  		//dados.put(data,st.getMapaStrDados().get(data));	
		  		dsc.addValue(valor);
		  		}
		  	}
			return dsc;

	}





public static DescriptiveStatistics dscGeral (ArrayList<Double> dados) {
	
	
	DescriptiveStatistics dsc = new DescriptiveStatistics();
   
	   for (int i=0;i<dados.size();i++){
	  		Double valor = dados.get(i);
	  		if(valor != -99999 || valor > 0){
	  		dsc.addValue(valor);
	  		}
	  	}
		return dsc;

}



public static DescriptiveStatistics dsc(int anoIni,int anoFim,Map<String,DadoTemporal> serieMapa) {
	
	DescriptiveStatistics dsc = new DescriptiveStatistics();
	int [] ano =ST_verificarAno.anos(serieMapa, anoIni, anoFim);
    Set<String> chaves = serieMapa.keySet();
	  	for (String data : chaves){
	  		String [] datastr=data.split("/");
	  		int anoatual=Integer.parseInt(datastr[2]);
	  		Double valor = serieMapa.get(data).getValor();
	  		if((valor != -99999 || valor > 0) && (anoatual >= ano[0]) && (anoatual <= ano[1])){
	  		dsc.addValue(valor);
	  		}
	  	}
		return dsc;

}
}
