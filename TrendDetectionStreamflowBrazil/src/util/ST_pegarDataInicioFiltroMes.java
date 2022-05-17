package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import types.DadoTemporal;
import types.SerieTemporal;



public class ST_pegarDataInicioFiltroMes {
	public static Date data(SerieTemporal st,int mesIni,int mesFim,int anoIni, int anoFIm){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =st.getDataInicialSerie().getTime();
		Date dfimSerie =st.getDataFinalSerie().getTime();
		Date utilDateIni = null;
		
		
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
		String datastrInicial = null;
		if(mesIni == mesFim){
			mesFim=mesIni;
			datastrInicial="01/"+mesIni+"/"+anoIni;
		}else{
				
			if(mesIni > mesFim)	{
				int anoInicial=anoIni;
				anoInicial=anoInicial-1;
				datastrInicial="01/"+mesIni+"/"+anoInicial;
					
			}else{
				datastrInicial="01/"+mesIni+"/"+anoIni;
			}
	   }
		
		try {
			utilDateIni = formatter.parse(datastrInicial);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateIni;	

	}
	
	
	
	
	
	
	
	
	
	public static Date data(SerieTemporal st,int mesIni,int mesFim){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =st.getDataInicialSerie().getTime();
		Date dfimSerie =st.getDataFinalSerie().getTime();
		Date utilDateIni = null;
		
		
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
		String datastrInicial = null;
		if(mesIni == mesFim){
			mesFim=mesIni;
			datastrInicial="01/"+mesIni+"/"+strini[2];
		}else{
				
			if(mesIni > mesFim)	{
				int anoInicial=Integer.parseInt(strini[2]);
				anoInicial=anoInicial-1;
				datastrInicial="01/"+mesIni+"/"+anoInicial;
					
			}else{
				datastrInicial="01/"+mesIni+"/"+strini[2];
			}
	   }
		
		try {
			utilDateIni = formatter.parse(datastrInicial);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateIni;	

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
		Date utilDateIni = null;
		
		
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
		String datastrInicial = null;
		if(mesIni == mesFim){
			mesFim=mesIni;
			datastrInicial="01/"+mesIni+"/"+anoIni;
		}else{
				
			if(mesIni > mesFim)	{
				int anoInicial=anoIni;
				anoInicial=anoInicial-1;
				datastrInicial="01/"+mesIni+"/"+anoInicial;
					
			}else{
				datastrInicial="01/"+mesIni+"/"+anoIni;
			}
	   }
		
		try {
			utilDateIni = formatter.parse(datastrInicial);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateIni;	

	}
}
