package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dif {


	public static int dias(Calendar c1, Calendar c2) {
		long m1 = c1.getTimeInMillis();
		long m2 = c2.getTimeInMillis();
		return (int) ((m2 - m1) / (24*60*60*1000));
		}			


		public static int meses(Date dateStart, Date dateEnd) {
			     int count = 0;  
		        Calendar clStart = Calendar.getInstance();  
		        clStart.setTime(dateStart);  
     	        Calendar clEnd = Calendar.getInstance();  
		        clEnd.setTime(dateEnd);  
     	        while (clStart.get(Calendar.MONTH) != clEnd.get(Calendar.MONTH) || clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
		            clStart.add(Calendar.MONTH, 1);  
		            count++;  
		        }  
		  return count;
	   }
	
	
		public static int dias(Date dateStart, Date dateEnd) {
		     int count = 0;  
	        Calendar clStart = Calendar.getInstance();  
	        clStart.setTime(dateStart);  
	        Calendar clEnd = Calendar.getInstance();  
	        clEnd.setTime(dateEnd);
	        //Calendar.DAY_OF_YEAR
	       
	        while (clStart.get(Calendar.DAY_OF_MONTH) != clEnd.get(Calendar.DAY_OF_MONTH) || clStart.get(Calendar.MONTH) != clEnd.get(Calendar.MONTH) || clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
	            clStart.add(Calendar.DAY_OF_YEAR, 1);  
	            count++;  
	        }  
	        
	       return count;
     }
		
		
		
		public static int dias(String dataIni, String dataFim) {
		     int count = 0; 
		     
		     /**
				 * Saulo - Alteração para evitar problema com hora em java - 18/03/2016
				 * lemabrando que pega a diferenca entre duas datas nao o numero de dias, para pegar o numero de dias deve somar mais 1 ao count
				 */
		     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String[] str = dataIni.split("/");
				int ano=Integer.parseInt(str[2]);
			    int mes=Integer.parseInt(str[1]);
			    int dia=Integer.parseInt(str[0]);
		        Calendar clStart = Calendar.getInstance();  
		        clStart.set(ano,mes-1,dia);
		        
		        
		        String[] strFim = dataFim.split("/");
				int anoFim=Integer.parseInt(strFim[2]);
			    int mesFim=Integer.parseInt(strFim[1]);
			    int diaFim=Integer.parseInt(strFim[0]);  
	        Calendar clEnd = Calendar.getInstance();  
	        clEnd.set(anoFim,mesFim-1,diaFim);
	      //clEnd.setTime(dateEnd);
	        //Calendar.DAY_OF_YEAR
	       
	        while (clStart.get(Calendar.DAY_OF_MONTH) != clEnd.get(Calendar.DAY_OF_MONTH) || clStart.get(Calendar.MONTH) != clEnd.get(Calendar.MONTH) || clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
	            clStart.add(Calendar.DAY_OF_YEAR, 1);  
	            count++;  
	        }  
	        
	       return count;
    }	
		
		
		
		
		public static int anos(Date dateStart, Date dateEnd) {
		     int count = 0;  
	        Calendar clStart = Calendar.getInstance();  
	        clStart.setTime(dateStart);  
	        Calendar clEnd = Calendar.getInstance();  
	        clEnd.setTime(dateEnd);
	        //Calendar.DAY_OF_YEAR
	        while (clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
	            clStart.add(Calendar.YEAR, 1);  
	            count++;  
	        }  
	       return count;
    }

}
