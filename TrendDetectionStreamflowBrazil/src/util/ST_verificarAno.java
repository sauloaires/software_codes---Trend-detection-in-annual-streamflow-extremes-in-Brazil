package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import types.DadoTemporal;

//import org.snirh.extremos_unb.util.ST_seriesOutrosFormatos;

import types.SerieTemporal;

public class ST_verificarAno {
public static int[] anos(SerieTemporal st,int anoIni, int anoFim){
		
		int [] ano = new int [2];
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =st.getDataInicialSerie().getTime();
		Date dfimSerie =st.getDataFinalSerie().getTime();
		
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
		String datastrfim =formatter.format(dfimSerie);
		String[] strfim = datastrfim.split("/");
		if(anoIni == -99999){
		anoIni=Integer.parseInt(strini[2]);	
		}
		
		if(anoFim == -99999){
		anoFim=Integer.parseInt(strfim[2]);	
		}
		ano[0]=anoIni;
		ano[1]=anoFim;
		
		
		return ano;
		
		
		
		
	}
	
	
	
public static int[] anos(Map<String,DadoTemporal> mapaStrDadosGer,int anoIni, int anoFim){
		
	     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	   ArrayList<String> datasstr= ST_seriesOutrosFormatos.pegarArrayListDataOrdenada(mapaStrDadosGer);
	
	    Date diniSerie =null;
		Date dfimSerie=null;
		try {
			diniSerie =formatter.parse(datasstr.get(0));
			dfimSerie = formatter.parse(datasstr.get(datasstr.size()-1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		int [] ano = new int [2];
		
		//Date diniSerie =st.getDataInicialSerie().getTime();
		//Date dfimSerie =st.getDataFinalSerie().getTime();
		
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
		String datastrfim =formatter.format(dfimSerie);
		String[] strfim = datastrfim.split("/");
		if(anoIni == -99999){
		anoIni=Integer.parseInt(strini[2]);	
		}
		
		if(anoFim == -99999){
		anoFim=Integer.parseInt(strfim[2]);	
		}
		ano[0]=anoIni;
		ano[1]=anoFim;
		
		
		return ano;
		
		
		
		
	}


public static int[] anos(Map<String,DadoTemporal> mapaStrDadosGer){
	int anoIni= -99999;
	int anoFim= -99999;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

  ArrayList<String> datasstr= ST_seriesOutrosFormatos.pegarArrayListDataOrdenada(mapaStrDadosGer);

   Date diniSerie =null;
	Date dfimSerie=null;
	try {
		diniSerie =formatter.parse(datasstr.get(0));
		dfimSerie = formatter.parse(datasstr.get(datasstr.size()-1));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	int [] ano = new int [2];
	
	//Date diniSerie =st.getDataInicialSerie().getTime();
	//Date dfimSerie =st.getDataFinalSerie().getTime();
	
	String datastrini =formatter.format(diniSerie);
	String[] strini = datastrini.split("/");
	String datastrfim =formatter.format(dfimSerie);
	String[] strfim = datastrfim.split("/");
	if(anoIni == -99999){
	anoIni=Integer.parseInt(strini[2]);	
	}
	
	if(anoFim == -99999){
	anoFim=Integer.parseInt(strfim[2]);	
	}
	ano[0]=anoIni;
	ano[1]=anoFim;
	
	
	return ano;
	
	
	
	
}
}
