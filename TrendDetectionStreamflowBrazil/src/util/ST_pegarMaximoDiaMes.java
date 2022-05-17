package util;

import java.util.Calendar;

public class ST_pegarMaximoDiaMes {
	public static int ndias(int year,int mes) {  
	   // Calendar cal = (Calendar) Calendar.getInstance().clone();  
	      
	    //cal.set(Calendar.YEAR, year);  
	 //   cal.set(Calendar.MONTH, mes-1); // Fevereiro  
	   // return cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
	    
	    
	    Calendar clStart = Calendar.getInstance();  
        clStart.set(year,mes-1,1);
		return clStart.getActualMaximum(Calendar.DAY_OF_MONTH); 
	    
	}

}
