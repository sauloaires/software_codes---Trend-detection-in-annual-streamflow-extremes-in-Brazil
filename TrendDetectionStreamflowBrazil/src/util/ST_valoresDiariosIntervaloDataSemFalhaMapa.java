package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import types.DadoTemporal;


public class ST_valoresDiariosIntervaloDataSemFalhaMapa {
	public static Map<String,DadoTemporal> serieDiaria(Date dini, Date dfim,Map<String,DadoTemporal> mapaStrDadosger) {
		   
		   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		   Map<String,DadoTemporal> dados=new HashMap<String,DadoTemporal>();     
		   Calendar clStart =Calendar.getInstance();
		   clStart.setTime(dini);
		   Calendar clEnd =Calendar.getInstance();
		   clEnd.setTime(dfim);
		   int count = 0;
		 while (clStart.get(Calendar.DAY_OF_MONTH) != clEnd.get(Calendar.DAY_OF_MONTH) || clStart.get(Calendar.MONTH) != clEnd.get(Calendar.MONTH) || clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
			  
			  Date datual=clStart.getTime();
			  String datastratual =formatter.format(datual);
			  if(mapaStrDadosger.containsKey(datastratual)){
			  //saulo 11/10/2017
			 	  //if(mapaStrDadosger.get(datastratual).getValor() != -99999 || mapaStrDadosger.get(datastratual).getValor() > 0){
			    if(mapaStrDadosger.get(datastratual).getValor() >= 0.0){
			 		  if(mapaStrDadosger.get(datastratual).getValor() == 0){
			 			  System.out.println();
			 		  }
			 		  
				// System.out.println(datastratual + "   -    "+  mapaStrDadosger.get(datastratual).getValor());		  
			     // dados.add(mapaStrDadosger.get(datastratual).getValor());
			      String datastr =formatter.format(mapaStrDadosger.get(datastratual).getData());	
			      dados.put(datastr, mapaStrDadosger.get(datastratual));
			      
			      count++;  
			      }/*else{
			    	   
			    	   String datastr =datastratual;
			    	   DadoTemporal dt=new DadoTemporal();
			    	   dt.setData(datual);
			    	   dt.setValor(-99999.0);
					  dados.put(datastr,dt);   
			       }*/
			 	  
			 	  
		       }/*else{
		    	   
		    	   String datastr =datastratual;
		    	   DadoTemporal dt=new DadoTemporal();
		    	   dt.setData(datual);
		    	   dt.setValor(-99999.0);
				  dados.put(datastr,dt);   
		       }*/
		 
		  clStart.add(Calendar.DAY_OF_YEAR, 1);   
		}
		 
		 /**
		  * incluir o valor da data final do intervalo
		  */
		   Date datual=clStart.getTime();
		   String datastratual =formatter.format(datual);
		   if(mapaStrDadosger.containsKey(datastratual)){
			   
		   //saulo 11/10/2017
         //if(mapaStrDadosger.get(datastratual).getValor() != -99999 || mapaStrDadosger.get(datastratual).getValor() > 0){
         if(mapaStrDadosger.get(datastratual).getValor() >= 0.0){
		       
		       String datastr =formatter.format(mapaStrDadosger.get(datastratual).getData());	
			   dados.put(datastr, mapaStrDadosger.get(datastratual));
			   }/*else{
		    	   
		    	   String datastr =datastratual;
		    	   DadoTemporal dt=new DadoTemporal();
		    	   dt.setData(datual);
		    	   dt.setValor(-99999.0);
				  dados.put(datastr,dt);   
		       }*/
		   
		   }/*else{
	    	   
	    	   String datastr =datastratual;
	    	   DadoTemporal dt=new DadoTemporal();
	    	   dt.setData(datual);
	    	   dt.setValor(-99999.0);
			  dados.put(datastr,dt);   
	       }*/
		   
		   
		 return dados;

		}
	
	public static ArrayList<DadoTemporal> serieDiariaArray(Date dini, Date dfim,Map<String,DadoTemporal> mapaStrDadosger) {
		   
		   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		   ArrayList<DadoTemporal> dados=new ArrayList<DadoTemporal>();     
		   Calendar clStart =Calendar.getInstance();
		   clStart.setTime(dini);
		   Calendar clEnd =Calendar.getInstance();
		   clEnd.setTime(dfim);
		   int count = 0;
		 while (clStart.get(Calendar.DAY_OF_MONTH) != clEnd.get(Calendar.DAY_OF_MONTH) || clStart.get(Calendar.MONTH) != clEnd.get(Calendar.MONTH) || clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
			  
			  Date datual=clStart.getTime();
			  String datastratual =formatter.format(datual);
			  if(mapaStrDadosger.containsKey(datastratual)){
			  //saulo 11/10/2017
          //if(mapaStrDadosger.get(datastratual).getValor() != -99999 || mapaStrDadosger.get(datastratual).getValor() > 0){
          if(mapaStrDadosger.get(datastratual).getValor() >= 0.0){
				 System.out.println(datastratual + "   -    "+  mapaStrDadosger.get(datastratual).getValor());		  
			     // dados.add(mapaStrDadosger.get(datastratual).getValor());
			      String datastr =formatter.format(mapaStrDadosger.get(datastratual).getData());	
			      dados.add(mapaStrDadosger.get(datastratual));
			      
			      count++;  
			      }			 	  
		       }		 
		  clStart.add(Calendar.DAY_OF_YEAR, 1);   
		}
		 
		 /**
		  * incluir o valor da data final do intervalo
		  */
		   Date datual=clStart.getTime();
		   String datastratual =formatter.format(datual);
		   if(mapaStrDadosger.containsKey(datastratual)){
			   
		   //saulo 11/10/2017
         //if(mapaStrDadosger.get(datastratual).getValor() != -99999 || mapaStrDadosger.get(datastratual).getValor() > 0){
         if(mapaStrDadosger.get(datastratual).getValor() >= 0.0){  
		      
		       String datastr =formatter.format(mapaStrDadosger.get(datastratual).getData());	
		       dados.add(mapaStrDadosger.get(datastratual));
			   }
		   
		   }
		 return dados;

		}
	
	
	
	
	
	public static Map<String,DadoTemporal> serieDiariaValoresNaoNulos(Date dini, Date dfim,Map<String,DadoTemporal> mapaStrDadosger) {
		   
		   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		   Map<String,DadoTemporal> dados=new HashMap<String,DadoTemporal>();     
		   Calendar clStart =Calendar.getInstance();
		   clStart.setTime(dini);
		   Calendar clEnd =Calendar.getInstance();
		   clEnd.setTime(dfim);
		   int count = 0;
		 while (clStart.get(Calendar.DAY_OF_MONTH) != clEnd.get(Calendar.DAY_OF_MONTH) || clStart.get(Calendar.MONTH) != clEnd.get(Calendar.MONTH) || clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
			  
			  Date datual=clStart.getTime();
			  String datastratual =formatter.format(datual);
			  if(mapaStrDadosger.containsKey(datastratual)){
			  //saulo 11/10/2017
          //if(mapaStrDadosger.get(datastratual).getValor() != -99999 || mapaStrDadosger.get(datastratual).getValor() > 0){
          if(mapaStrDadosger.get(datastratual).getValor() >= 0.0){
			 		  
			 		  if(mapaStrDadosger.get(datastratual).getValor() == 0){
			 			  System.out.println();
			 		  }
			 		  
				 System.out.println(datastratual + "   -    "+  mapaStrDadosger.get(datastratual).getValor());		  
			     // dados.add(mapaStrDadosger.get(datastratual).getValor());
			      String datastr =formatter.format(mapaStrDadosger.get(datastratual).getData());	
			      dados.put(datastr, mapaStrDadosger.get(datastratual));
			      
			      count++;  
			      }
		       }
		 
		  clStart.add(Calendar.DAY_OF_YEAR, 1);   
		}
		 
		 /**
		  * incluir o valor da data final do intervalo
		  */
		   Date datual=clStart.getTime();
		   String datastratual =formatter.format(datual);
		   if(mapaStrDadosger.containsKey(datastratual)){
			   
		   //saulo 11/10/2017
         //if(mapaStrDadosger.get(datastratual).getValor() != -99999 || mapaStrDadosger.get(datastratual).getValor() > 0){
         if(mapaStrDadosger.get(datastratual).getValor() >= 0.0){ 
		       //dados.add(mapaStrDadosger.get(datastratual).getValor());
		       
		       String datastr =formatter.format(mapaStrDadosger.get(datastratual).getData());	
			   dados.put(datastr, mapaStrDadosger.get(datastratual));
			   }/*else{
		    	   
		    	   String datastr =datastratual;
		    	   DadoTemporal dt=new DadoTemporal();
		    	   dt.setData(datual);
		    	   dt.setValor(-99999.0);
				  dados.put(datastr,dt);   
		       }*/
		   
		   }/*else{
	    	   
	    	   String datastr =datastratual;
	    	   DadoTemporal dt=new DadoTemporal();
	    	   dt.setData(datual);
	    	   dt.setValor(-99999.0);
			  dados.put(datastr,dt);   
	       }*/
		   
		   
		 return dados;

		}

}
