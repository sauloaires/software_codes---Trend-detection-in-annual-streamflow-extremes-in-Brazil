package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.DadoTemporal;
import types.SerieTemporal;



public class ST_serieDiariaNdiasSazonalAnualMapa {
public static Map<String,DadoTemporal> executar(double limval,String sentValor,int tipoSerie,double tolFalha,int codEstatistica,int mesIni, int mesFim,int anoIni,int anoFim,SerieTemporal st) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
		Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
		int [] ano = ST_verificarAno.anos(st, anoIni, anoFim);
		if(ano[0]>ano[1])return dadosEstatistica;
		Date utilDateIni =ST_pegarDataInicioFiltroMes.data(st, mesIni, mesFim,ano[0],ano[1]);
		Date utilDateIniMesFim =ST_pegarDataMesFinalFiltroMes.data(st, mesIni, mesFim, ano[0],ano[1]);
		Date utilDateFim = ST_pegarDataFinalFiltroMes.data(st,mesIni, mesFim,ano[0],ano[1]);
		
		Calendar clStart =Calendar.getInstance();
		Calendar clEnd =Calendar.getInstance();
		Calendar clStartfim =Calendar.getInstance();
		
		clEnd.setTime(utilDateFim);
		clStart.setTime(utilDateIni);
		clStartfim.setTime(utilDateIniMesFim);
		
		
		
		int ndiasSemFalha=Dif.dias(utilDateIni, utilDateIniMesFim)+1;
		double toleranciaMaxFalha=tolFalha;
		Map<String,DadoTemporal> dados_anual;
		Map<String,DadoTemporal> dados_filtro;
		ArrayList<String> serieData = new ArrayList<String>();
		
		while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
				dados_anual=new HashMap<String,DadoTemporal>();
	 	        dados_anual=ST_valoresDiariosIntervaloDataSemFalhaMapa.serieDiaria(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
	 	        
	 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
	 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas); 
	 	          	    	  
	 	       Double valorEstatistica=-99999.0;
		         
		         
		         
		        if(dados_filtro.size() > 0){
	 	        
	 	        Set<String> chaves =  dados_filtro.keySet();
	 		  	int idias=0;
		 	        for (String data : chaves){
		 		  	DadoTemporal dado = new DadoTemporal();
		 		  	dado=dados_filtro.get(data);
		 		  		if(sentValor.equals("MAIOR")){
		 		  			if(dado.getValor() >= limval){
		 		  				idias++;
		 		  			}
		 		  		}else{
		 		  			if(dado.getValor() <= limval){
		 		  				idias++;
		 		  			}
		 		  		}
		 		  	
		 		  	}
		 	       valorEstatistica=idias*1.0;   
	 	        }
	 	         	       	        
	 	        
	 	        String datastr =formatter.format(utilDateIni);
		     	if(valorEstatistica != -99999.0){
		     	DadoTemporal dt = new DadoTemporal();
			     	//if(valorEstDT.getData()==null){
			     		dt.setData(utilDateIni);	
			     	//}else{
			     	//	dt.setData(valorEstDT.getData());
			     	//}
		     	dt.setValor(valorEstatistica);
		     	dadosEstatistica.put(datastr,dt);
		     	serieData.add(datastr);
		     	}
		     	clStart.add(Calendar.YEAR, 1);
				clStartfim.add(Calendar.YEAR, 1);
				utilDateIni=clStart.getTime();
				utilDateIniMesFim=clStartfim.getTime();
						
				if(mesFim == 2){
				utilDateIniMesFim=ST_verificarNdiasFevereiro.verificarNdiasFevereiro(utilDateIniMesFim, mesFim);
				}
				ndiasSemFalha=Dif.dias(utilDateIni, utilDateIniMesFim)+1;
		}
		
		
		    dados_anual=new HashMap<String,DadoTemporal>();
	        dados_anual=ST_valoresDiariosIntervaloDataSemFalhaMapa.serieDiaria(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
	         double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
			 Double valorEstatistica=-99999.0;
	         
	         
	         
		        if(dados_filtro.size() > 0){
		        
		        Set<String> chaves =  dados_filtro.keySet();
			  	int idias=0;
		 	        for (String data : chaves){
		 		  	DadoTemporal dado = new DadoTemporal();
		 		  	dado=dados_filtro.get(data);
		 		  		if(sentValor.equals("MAIOR")){
		 		  			if(dado.getValor() >= limval){
		 		  				idias++;
		 		  			}
		 		  		}else{
		 		  			if(dado.getValor() <= limval){
		 		  				idias++;
		 		  			}
		 		  		}
		 		  	
		 		  	}
		 	       valorEstatistica=idias*1.0;   
		        }
		        
		        
		        String datastr =formatter.format(utilDateIni);
	     	if(valorEstatistica != -99999.0){
	     	DadoTemporal dt = new DadoTemporal();
		     	//if(valorEstDT.getData()==null){
		     		dt.setData(utilDateIni);	
		     	//}else{
		     	//	dt.setData(valorEstDT.getData());
		     	//}
	     	dt.setValor(valorEstatistica);
	     	dadosEstatistica.put(datastr,dt);
	     	serieData.add(datastr);
	     	}
		    //this.setSerieDatas(serieData);
		    return dadosEstatistica;

	}
}
