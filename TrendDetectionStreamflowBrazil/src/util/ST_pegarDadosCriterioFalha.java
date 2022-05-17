package util;

import java.util.HashMap;
import java.util.Map;

import types.DadoTemporal;



public class ST_pegarDadosCriterioFalha {
public static Map<String,DadoTemporal> mapa(Map<String,DadoTemporal> dados,int tipoSerie,double toleranciaMaxFalha, double percentualDeFalhas){
		
		Map<String,DadoTemporal> dadosComFalha=new HashMap<String,DadoTemporal>();
		Map<String,DadoTemporal> dadosSemFalha=new HashMap<String,DadoTemporal>();
		Map<String,DadoTemporal> dadosComFalhaPercMaxTolerado=new HashMap<String,DadoTemporal>();
		
		  if(percentualDeFalhas == 0){
			   dadosSemFalha.putAll(dados);
			   dadosComFalha.putAll(dados);
		       dadosComFalhaPercMaxTolerado.putAll(dados);
		   }else if(percentualDeFalhas < toleranciaMaxFalha){
			  dadosComFalhaPercMaxTolerado.putAll(dados);
			  dadosComFalha.putAll(dados);
		   }else{
			  dadosComFalha.putAll(dados);   
		   }
		
		
		     if(tipoSerie == 0){
		 		 return dadosComFalha;  
		 	  }else if(tipoSerie == 1){
		 		 return dadosSemFalha;  
		 	  }else if(tipoSerie == 2){
		 		 return dadosComFalhaPercMaxTolerado;
		 	  }
		 
		 
		   return dadosComFalha;
		      
	}
}
