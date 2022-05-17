package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import types.DadoTemporal;
import types.SerieTemporal;


//import org.snirh.extremos_unb.util.ST_pegarMaximoDiaMes;


public class ST_pegarDataMesFinalFiltroMes {
	public static Date data(SerieTemporal st,int mesIni,int mesFim,int anoIni, int anoFIm){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =st.getDataInicialSerie().getTime();
		Date dfimSerie =st.getDataFinalSerie().getTime();
		Date utilDateIniMesFim = null;	
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
			
		String datastrInicial = null;
		String datastrInicialMesFinal = null;
			
		if(mesIni == mesFim){
				int ano=anoIni;
			    int ndias=ST_pegarMaximoDiaMes.ndias(ano,mesFim);
				datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoIni;
		}else{
				if(mesIni > mesFim)	{
					int ano=anoIni;
					int anoMesFim=ano;
			        int ndias=ST_pegarMaximoDiaMes.ndias(anoMesFim,mesFim);
					datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoMesFim;;
				}else{
					int ano=anoIni;
			        int ndias=ST_pegarMaximoDiaMes.ndias(ano,mesFim);
					datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoIni;
				}
		}
		
		try {
			utilDateIniMesFim = formatter.parse(datastrInicialMesFinal);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateIniMesFim;	

	}


	public static Date data(SerieTemporal st,int mesIni,int mesFim){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =st.getDataInicialSerie().getTime();
		Date dfimSerie =st.getDataFinalSerie().getTime();
		Date utilDateIniMesFim = null;	
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
			
		String datastrInicial = null;
		String datastrInicialMesFinal = null;
			
		if(mesIni == mesFim){
				int ano=Integer.parseInt(strini[2]);
			    int ndias=ST_pegarMaximoDiaMes.ndias(ano,mesFim);
				datastrInicialMesFinal=ndias+"/"+mesFim+"/"+strini[2];
		}else{
				if(mesIni > mesFim)	{
					int ano=Integer.parseInt(strini[2]);
					int anoMesFim=ano;
			        int ndias=ST_pegarMaximoDiaMes.ndias(anoMesFim,mesFim);
					datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoMesFim;;
				}else{
					int ano=Integer.parseInt(strini[2]);
			        int ndias=ST_pegarMaximoDiaMes.ndias(ano,mesFim);
					datastrInicialMesFinal=ndias+"/"+mesFim+"/"+strini[2];
				}
		}
		
		try {
			utilDateIniMesFim = formatter.parse(datastrInicialMesFinal);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateIniMesFim;	

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
		Date utilDateIniMesFim = null;	
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
			
		String datastrInicial = null;
		String datastrInicialMesFinal = null;
			
		if(mesIni == mesFim){
				int ano=anoIni;
			    int ndias=ST_pegarMaximoDiaMes.ndias(ano,mesFim);
				datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoIni;
		}else{
				if(mesIni > mesFim)	{
					int ano=anoIni;
					int anoMesFim=ano;
			        int ndias=ST_pegarMaximoDiaMes.ndias(anoMesFim,mesFim);
					datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoMesFim;;
				}else{
					int ano=anoIni;
			        int ndias=ST_pegarMaximoDiaMes.ndias(ano,mesFim);
					datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoIni;
				}
		}
		
		try {
			utilDateIniMesFim = formatter.parse(datastrInicialMesFinal);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateIniMesFim;	

	}
}
