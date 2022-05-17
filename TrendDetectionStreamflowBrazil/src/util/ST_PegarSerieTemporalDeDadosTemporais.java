package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import types.DadoTemporal;
import types.SerieTemporal;



public class ST_PegarSerieTemporalDeDadosTemporais {
	public static SerieTemporal st(Map<String,DadoTemporal> dadosMapa){
		SerieTemporal st =new SerieTemporal();
		ArrayList<DadoTemporal> dados=new ArrayList<DadoTemporal>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        Set<String> chavesData = dadosMapa.keySet();
	  	for (String data : chavesData){
	  	dados.add(dadosMapa.get(data));
	   	}
        
        
		 int ndados=dados.size();
		 Collections.sort(dados);
		 Calendar dataInicialSerie= Calendar.getInstance();
		 Calendar dataFinalSerie= Calendar.getInstance();
		 dataInicialSerie.setTime(dados.get(0).getData());
		 dataFinalSerie.setTime(dados.get(ndados-1).getData());
		 
		 Date dini=dataInicialSerie.getTime();
		 String dataInistr=formatter.format(dini);
		 Date dfim=dataFinalSerie.getTime();
		 String dataFimstr=formatter.format(dfim);
				 
		 st.setDados(dados);
		 st.setDataInicialSerie(dataInicialSerie);
		 st.setDataFinalSerie(dataFinalSerie);
		 st.setaMapaStrDadoTemporal();
		
		 return st;
		 
		 
	}
}
