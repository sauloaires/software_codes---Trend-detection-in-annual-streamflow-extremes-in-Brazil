package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import types.DadoTemporal;



public class ST_estatisticaDaSerie {
	public static DadoTemporal estatCod(int codEstatistica, Map<String,DadoTemporal> dados_anual) {
		DadoTemporal valordt = new DadoTemporal();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date utilDateIni = null;
		Double valor=null;
		
		
		if(dados_anual.size() == 0){
	 	valordt.setValor(-99999.0);
	  	return valordt;
	 	}
		
		DescriptiveStatistics dsc = new DescriptiveStatistics();
		Set<String> chaves =  dados_anual.keySet();
	  	for (String data : chaves){
	  	DadoTemporal dado = dados_anual.get(data);
	  	dsc.addValue(dado.getValor());
	  	}
		
	  	
	  	
		switch (codEstatistica) {
	 	case 0:
	    valor=dsc.getSum();
	    valordt.setValor(valor);
	 	//System.out.println("Soma");
	 	break;
	 	case 1:
	 	valor=dsc.getMean();
	 	valordt.setValor(valor);
	  //  System.out.println("Média");
	    break;
	 	case 2:
	 	valor=dsc.getStandardDeviation();
	 	valordt.setValor(valor);
		System.out.println("Desvio Padrão");
		break;
	 	case 3:
	    valor=dsc.getSkewness();
	    valordt.setValor(valor);
		System.out.println("Assimetria");
		break;
	 	case 4:
	    valor=dsc.getKurtosis();
	    valordt.setValor(valor);
		System.out.println("Curtose");
		break;
	 	case 5:
	    valor=dsc.getMax();
	    valordt.setValor(valor);
	    Set<String> chavesmax =  dados_anual.keySet();
	  	for (String data : chavesmax){
	  	DadoTemporal dado = dados_anual.get(data);
		  	if(valor.equals(dado.getValor())){
		  	utilDateIni=dado.getData();	
		  	break;
		  	}
	  	}
	  	valordt.setData(utilDateIni);
	  	System.out.println("Maximo");
		break;
	 	case 6:
		valor=dsc.getMin();
		 valordt.setValor(valor);
		Set<String> chavesmin =  dados_anual.keySet();
		  	for (String data : chavesmin){
		  	DadoTemporal dado = dados_anual.get(data);
			  	if(valor.equals(dado.getValor())){
			  	utilDateIni=dado.getData();	
			  	break;
			  	}
		  	}
		 
		  	if(utilDateIni == null){
		  		System.out.println();
		  	}
		 valordt.setData(utilDateIni);
		System.out.println("Minimo");
		break;
		
		
	 	case 7:
		valor=(double) dsc.getN();
		valordt.setValor(valor);
		System.out.println("Tamanho da Série");
		break;
		
	 	
	 	case 8:
			valor=(double) dsc.getPercentile(50);
			valordt.setValor(valor);
			System.out.println("Mediana");
			break;
	 	
	 	default:
	 	valor=dsc.getMean();
	 	valordt.setValor(valor);
	 	System.out.println("Média");
	 	}
		
		
		
		
		return valordt;
		
		
	}
}
