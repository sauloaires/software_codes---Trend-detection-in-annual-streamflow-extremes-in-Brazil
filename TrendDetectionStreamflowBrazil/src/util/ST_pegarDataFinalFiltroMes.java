package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import types.DadoTemporal;
import types.SerieTemporal;



public class ST_pegarDataFinalFiltroMes {
	public static Date dataHidrologica(SerieTemporal st,int mesIni,int mesFim,int anoIni, int anoFIm){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =st.getDataInicialSerie().getTime();
		Date dfimSerie =st.getDataFinalSerie().getTime();
		
		Date utilDateFim = null;
		String datastrfim =formatter.format(dfimSerie);
		String[] strfim = datastrfim.split("/");
		String datastrFinal = null;
		
		
		if(mesIni == mesFim){
			int anoFinal=anoFIm;
			int anoFinalMesFim=anoFinal;
		    int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
			datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFIm;
		}else{
		
			if(mesIni > mesFim)	{
				int anoFinal=anoFIm;
				//int anoFinalMesFim=anoFinal+1;
				int anoFinalMesFim=anoFinal;
				
		        int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFinalMesFim;	
			}else{
				int anoFinal=anoFIm;
				int anoFinalMesFim=anoFinal;
		        int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFIm;	
			}
			
			
		}
		
		try {
			utilDateFim = formatter.parse(datastrFinal);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateFim;
		

	}
	
	
	
	
	public static Date data(SerieTemporal st,int mesIni,int mesFim,int anoIni, int anoFIm){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =st.getDataInicialSerie().getTime();
		Date dfimSerie =st.getDataFinalSerie().getTime();
		
		Date utilDateFim = null;
		String datastrfim =formatter.format(dfimSerie);
		String[] strfim = datastrfim.split("/");
		String datastrFinal = null;
		
		
		if(mesIni == mesFim){
			int anoFinal=anoFIm;
			int anoFinalMesFim=anoFinal;
		    int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
			datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFIm;
		}else{
		
			if(mesIni > mesFim)	{
				int anoFinal=anoFIm;
				int anoFinalMesFim=anoFinal+1;
				
				
		        int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFinalMesFim;	
			}else{
				int anoFinal=anoFIm;
				int anoFinalMesFim=anoFinal;
		        int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFIm;	
			}
			
			
		}
		
		try {
			utilDateFim = formatter.parse(datastrFinal);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateFim;
		

	}



	public static Date data(SerieTemporal st,int mesIni,int mesFim){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =st.getDataInicialSerie().getTime();
		Date dfimSerie =st.getDataFinalSerie().getTime();
		
		Date utilDateFim = null;
		String datastrfim =formatter.format(dfimSerie);
		String[] strfim = datastrfim.split("/");
		String datastrFinal = null;
		
		
		if(mesIni == mesFim){
			int anoFinal=Integer.parseInt(strfim[2]);
			int anoFinalMesFim=anoFinal;
		    int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
			datastrFinal=ndiasFinal+"/"+mesFim+"/"+strfim[2];
		}else{
		
			if(mesIni > mesFim)	{
				int anoFinal=Integer.parseInt(strfim[2]);
				int anoFinalMesFim=anoFinal+1;
		        int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFinalMesFim;	
			}else{
				int anoFinal=Integer.parseInt(strfim[2]);
				int anoFinalMesFim=anoFinal;
		        int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+strfim[2];	
			}
			
			
		}
		
		try {
			utilDateFim = formatter.parse(datastrFinal);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateFim;
		

	}
	
	
	
	public static Date data(Map<String,DadoTemporal> mapaStrDadosGer,int mesIni,int mesFim,int anoIni, int anoFIm){
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
	//Date diniSerie =st.getDataInicialSerie().getTime();
	//Date dfimSerie =st.getDataFinalSerie().getTime();
		Date utilDateFim = null;
		String datastrfim =formatter.format(dfimSerie);
		String[] strfim = datastrfim.split("/");
		String datastrFinal = null;
		
		
		if(mesIni == mesFim){
			int anoFinal=anoFIm;
			int anoFinalMesFim=anoFinal;
		    int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
			datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFIm;
		}else{
		
			if(mesIni > mesFim)	{
				int anoFinal=anoFIm;
				int anoFinalMesFim=anoFinal+1;
		        int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFinalMesFim;	
			}else{
				int anoFinal=anoFIm;
				int anoFinalMesFim=anoFinal;
		        int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFIm;	
			}
			
			
		}
		
		try {
			utilDateFim = formatter.parse(datastrFinal);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateFim;
		

	}
}
