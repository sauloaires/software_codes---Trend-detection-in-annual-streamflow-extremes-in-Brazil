package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import types.DadoTemporal;



public class ST_valoresMensaisIntervaloDataSemFalhaMapa {
	public static  Map<String,DadoTemporal> serieMensal(Date dini, Date dfim,Map<String,DadoTemporal> mapaStrDadosger) {
		   
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		   Map<String,DadoTemporal> dados=new HashMap<String,DadoTemporal>();     
		   Calendar clStart =Calendar.getInstance();
		   clStart.setTime(dini);
		   Calendar clEnd =Calendar.getInstance();
		   clEnd.setTime(dfim);
		   int count = 0;
		 while (clStart.get(Calendar.MONTH) != clEnd.get(Calendar.MONTH) || clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
			  
			 Date datual=clStart.getTime();
			  String datastratual =formatter.format(datual);
			  if(mapaStrDadosger.containsKey(datastratual)){
			 	  if(mapaStrDadosger.get(datastratual).getValor() != -99999 || mapaStrDadosger.get(datastratual).getValor() > 0){
				 //System.out.println(datastratual + "   -    "+ mapaStrDadosger.get(datastratual).getValor());		  
			     // dados.add(mapaStrDadosger.get(datastratual).getValor());
			      String datastr =formatter.format(mapaStrDadosger.get(datastratual).getData());	
			      dados.put(datastr, mapaStrDadosger.get(datastratual));
			      
			      count++;  
			      }
		   }
		 
		  clStart.add(Calendar.MONTH, 1);   
		 
		 
		 }  
		 
		 /**
		  * incluir o valor da data final do intervalo
		  */
		   Date datual=clStart.getTime();
		   String datastratual =formatter.format(datual);
		   if(mapaStrDadosger.containsKey(datastratual)){
			   
			   if(mapaStrDadosger.get(datastratual).getValor() != -99999 || mapaStrDadosger.get(datastratual).getValor() > 0){  
		       //dados.add(mapaStrDadosger.get(datastratual).getValor());
		       
		       String datastr =formatter.format(mapaStrDadosger.get(datastratual).getData());	
			   dados.put(datastr, mapaStrDadosger.get(datastratual));
			   }
		   
		   }
		 return dados;

		 
		}	
}
