package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import types.DadoTemporal;


public class ST_seriesOutrosFormatos {
public static double [] pegarVetor(Map<String,DadoTemporal>  mapaDadosTemporal){
		
		double [] vetor=new double [mapaDadosTemporal.size()];
		
		    Set<String> chavesData = mapaDadosTemporal.keySet();
		    int i=0;
		  	for (String data : chavesData){
		  	vetor[i]=mapaDadosTemporal.get(data).getValor();
		  	i++;
		  	}
		
		return vetor;
			
	}

	
    public static double [] pegarVetor(ArrayList<Double>  array){
		
		    double [] vetor=new double [array.size()];
		  	for (int i=0;i<array.size();i++){
		  	vetor[i]=array.get(i);
		  	}
		
		return vetor;
			
	}
	
	
	public static ArrayList<Double> pegarArrayList(Map<String,DadoTemporal>  mapaDadosTemporal){
		
		    ArrayList<Double> array=new ArrayList<Double>();
		    Set<String> chavesData = mapaDadosTemporal.keySet();
		    int i=0;
		  	for (String data : chavesData){
		  	array.add(mapaDadosTemporal.get(data).getValor());
		  	i++;
		  	}
		
		return array;
			
	}
	
	
	public static ArrayList<String> pegarArrayListDataOrdenada(Map<String,DadoTemporal>  mapaDadosTemporal){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<Date>dataSerie=new ArrayList<Date>();
	    ArrayList<String> arrayDatasOrdenadas=new ArrayList<String>();
	    Set<String> chavesData = mapaDadosTemporal.keySet();
	    //int i=0;
	  	    for (String data : chavesData){
	  		Date utilDateIni = null;
			try {
			utilDateIni = formatter.parse(data);
			} catch (ParseException e) {
			e.printStackTrace();
			}
		  	dataSerie.add(utilDateIni);
		  	}
		
		  	Collections.sort(dataSerie);
		  	
		  	for (int i=0;i<dataSerie.size();i++){
		  		arrayDatasOrdenadas.add(formatter.format(dataSerie.get(i)))	;
		  		
		  	}
		  	
		  	
	
	return arrayDatasOrdenadas;
		
}
	
	public static ArrayList<Double> pegarArrayListValoresDataOrdenada(Map<String,DadoTemporal>  mapaDadosTemporal){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<Date>dataSerie=new ArrayList<Date>();
	    ArrayList<String> arrayDatasOrdenadas=new ArrayList<String>();
	    ArrayList<Double> arrayValoresDatasOrdenadas=new ArrayList<Double>();
	    Set<String> chavesData = mapaDadosTemporal.keySet();
	    //int i=0;
	  	    for (String data : chavesData){
	  		Date utilDateIni = null;
			try {
			utilDateIni = formatter.parse(data);
			} catch (ParseException e) {
			e.printStackTrace();
			}
		  	dataSerie.add(utilDateIni);
		  	}
		
		  	Collections.sort(dataSerie);
		  	
		  	for (int i=0;i<dataSerie.size();i++){
		  		arrayDatasOrdenadas.add(formatter.format(dataSerie.get(i)))	;
		  		
		  	}
		  	
		  	
		  	for (int i=0;i<arrayDatasOrdenadas.size();i++){
		  		if(mapaDadosTemporal.get(arrayDatasOrdenadas.get(i)).getValor() != -99999){
		  			arrayValoresDatasOrdenadas.add(mapaDadosTemporal.get(arrayDatasOrdenadas.get(i)).getValor());
		  		}
		  		
		  	}
		  	
		  	
		  	
	return arrayValoresDatasOrdenadas;
		
}
}
