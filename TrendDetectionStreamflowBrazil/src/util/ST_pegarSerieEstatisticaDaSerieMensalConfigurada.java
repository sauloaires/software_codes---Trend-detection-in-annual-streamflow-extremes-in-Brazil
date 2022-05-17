package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.ConfiguraSeries;
import types.DadoTemporal;
import types.InventarioHidrologico;
import types.SerieTemporal;
import types.VariavelHidrologica;



public class ST_pegarSerieEstatisticaDaSerieMensalConfigurada {

	/**
	 * 
	 * @param tammin - tamanho minimo de series por ano (em anos)
	 * @param tipoSerie - tipo de série CF, SF, CFT
	 * @param tolFalha - Tolerancia percentual de considerar serie com falhas
	 * @param mesIni - Mes inicial a ser iniciado a série - 1 (jan) a 12 (dez)
	 * @param mesFim - Mes final a ser considerado a série- 1 (jan) a 12 (dez)
	 * @param anoIni - Ano inicial a ser iniciado a série
	 * @param anoFim - Ano final a ser considerado a série
	 * @param st - objeto serie temporal em analise
	 * codEstatistica - codigo da estatistica que se deseja obter
	 * @return
	 */
	
	
	
public static  Map<String,DadoTemporal> serieEstatistica(int tammin,int tipoSerie,double tolFalha,int codEstatistica,int mesIni, int mesFim,int anoIni,int anoFim,SerieTemporal st) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
		Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
		//int [] ano = verificarFalhaAno(anoIni,anoFim);
		int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
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
		
		int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
		double toleranciaMaxFalha=tolFalha;
		Map<String,DadoTemporal> dados_anual;
		Map<String,DadoTemporal> dados_filtro;
		//Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
		
		ArrayList<String> serieData = new ArrayList<String>();
		dados_anual=new HashMap<String,DadoTemporal>();
		double totdados=0;
		double totdias=0;
		int itamanho=0;
		while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
				
			    dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
	 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
	 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
	 	      	 	       
	 	        DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
	 	        Double valorEstatistica=valorEstDT.getValor();
	 	        String datastr =formatter.format(utilDateIni);
		     	if(valorEstatistica != -99999.0){
				     	DadoTemporal dt = new DadoTemporal();
					     	if(valorEstDT.getData()==null){
					     		dt.setData(utilDateIni);	
					     	}else{
					     		dt.setData(valorEstDT.getData());
					     	}
				     	dt.setValor(valorEstatistica);
				     	dadosEstatistica.put(datastr,dt);
				     	serieData.add(datastr);
				     	
				     	if(dados_filtro.size() > 0 ){
				 	    itamanho++;	
				 	    }
		     	}
		     	
	 	        
	 	        totdados=dados_filtro.size()+totdados;
	 	        totdias=ndiasSemFalha+totdias;
	 	        //dados_anual_tot.putAll(dados_filtro);
	 	        clStart.add(Calendar.YEAR, 1);
				clStartfim.add(Calendar.YEAR, 1);
				utilDateIni=clStart.getTime();
				utilDateIniMesFim=clStartfim.getTime();
						
				if(mesFim == 2){
				//utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
				}
				ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
		}
		
		dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
		double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
		dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
		
		DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
	    Double valorEstatistica=valorEstDT.getValor();
	    String datastr =formatter.format(utilDateIni);
     	if(valorEstatistica != -99999.0){
		     	DadoTemporal dt = new DadoTemporal();
			     	if(valorEstDT.getData()==null){
			     		dt.setData(utilDateIni);	
			     	}else{
			     		dt.setData(valorEstDT.getData());
			     	}
		     	dt.setValor(valorEstatistica);
		     	dadosEstatistica.put(datastr,dt);
		     	serieData.add(datastr);
		     	
		     	if(dados_filtro.size() > 0 ){
		 	    itamanho++;	
		 	    }
     	}
	    
	    totdados=dados_filtro.size()+totdados;
	    totdias=ndiasSemFalha+totdias;
	    	    
	    if(itamanho >= tammin){
	    	return dadosEstatistica;	
	    }else{
	    	return null;
	    }
		
		
	}	
	
	
public static  Map<String,DadoTemporal> serieEstatistica(ConfiguraSeries configuraSeries,SerieTemporal st) {
	
	int tammin=configuraSeries.getTamanhoMinimo();
	int tipoSerie=configuraSeries.getTipoSerieFalha();
	double tolFalha=configuraSeries.getTolFalhaMax();
	int mesIni=configuraSeries.getMesIni();
	int mesFim=configuraSeries.getMesFim();
	int anoIni=configuraSeries.getAnoIniSubConjunto();
	int anoFim=configuraSeries.getAnoFimSubConjunto();
	int codEstatistica=configuraSeries.getCodEstatistica();
	
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
	Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
	//int [] ano = verificarFalhaAno(anoIni,anoFim);
	int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
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
	
	int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	double toleranciaMaxFalha=tolFalha;
	Map<String,DadoTemporal> dados_anual;
	Map<String,DadoTemporal> dados_filtro;
	//Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
	
	ArrayList<String> serieData = new ArrayList<String>();
	dados_anual=new HashMap<String,DadoTemporal>();
	double totdados=0;
	double totdias=0;
	int itamanho=0;
	while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
			
		    dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
 	      	 	       
 	        DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
 	        Double valorEstatistica=valorEstDT.getValor();
 	        String datastr =formatter.format(utilDateIni);
	     	if(valorEstatistica != -99999.0){
			     	DadoTemporal dt = new DadoTemporal();
				     	if(valorEstDT.getData()==null){
				     		dt.setData(utilDateIni);	
				     	}else{
				     		dt.setData(valorEstDT.getData());
				     	}
			     	dt.setValor(valorEstatistica);
			     	dadosEstatistica.put(datastr,dt);
			     	serieData.add(datastr);
			     	
			     	if(dados_filtro.size() > 0 ){
			 	    itamanho++;	
			 	    }
	     	}
	     	
 	        
 	        totdados=dados_filtro.size()+totdados;
 	        totdias=ndiasSemFalha+totdias;
 	        //dados_anual_tot.putAll(dados_filtro);
 	        clStart.add(Calendar.YEAR, 1);
			clStartfim.add(Calendar.YEAR, 1);
			utilDateIni=clStart.getTime();
			utilDateIniMesFim=clStartfim.getTime();
					
			if(mesFim == 2){
			//utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
			}
			ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	}
	
	dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
	double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
	dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
	
	DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
    Double valorEstatistica=valorEstDT.getValor();
    String datastr =formatter.format(utilDateIni);
 	if(valorEstatistica != -99999.0){
	     	DadoTemporal dt = new DadoTemporal();
		     	if(valorEstDT.getData()==null){
		     		dt.setData(utilDateIni);	
		     	}else{
		     		dt.setData(valorEstDT.getData());
		     	}
	     	dt.setValor(valorEstatistica);
	     	dadosEstatistica.put(datastr,dt);
	     	serieData.add(datastr);
	     	
	     	if(dados_filtro.size() > 0 ){
	 	    itamanho++;	
	 	    }
 	}
    
    totdados=dados_filtro.size()+totdados;
    totdias=ndiasSemFalha+totdias;
    	    
    if(itamanho >= tammin){
    	return dadosEstatistica;	
    }else{
    	return null;
    }
	
	
}
	



public static VariavelHidrologica serieEstatisticaVhid(ConfiguraSeries configuraSeries,VariavelHidrologica vhid) {
	
	
	VariavelHidrologica vhidAnual=new VariavelHidrologica();
	Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
	serieMapa=ST_pegarSerieEstatisticaDaSerieMensalConfigurada.serieEstatistica(configuraSeries, vhid.getSerietemporal());
	
	
	    InventarioHidrologico invhid = new InventarioHidrologico();
        invhid.setBaciaCodigo(vhid.getInvhidro().getBaciaCodigo());
        invhid.setSubBaciaCodigo(vhid.getInvhidro().getSubBaciaCodigo());
		invhid.setEstacao_codigo(vhid.getInvhidro().getEstacao_codigo());
		invhid.setLatitude(vhid.getInvhidro().getLatitude());
		invhid.setLongitude(vhid.getInvhidro().getLongitude());
		invhid.setAltitude(vhid.getInvhidro().getAltitude());
		invhid.setAreaDrenagem(vhid.getInvhidro().getAreaDrenagem());
		invhid.setOrigemSerie(vhid.getInvhidro().getOrigemSerie());
		invhid.setDescricaoOrigemSerie("ESTATISTICA - "+vhid.getInvhidro().getDescricaoOrigemSerie());
		invhid.setDiscretizaçãoTemporária("ANUAL");
		invhid.setTipodeDado(vhid.getInvhidro().getTipodeDado());
		invhid.setMesAnoHidro(vhid.getInvhidro().getMesAnoHidro());

    SerieTemporal serietemp = new SerieTemporal ();
	ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
		
	Set<String> chavesMax =  serieMapa.keySet();
  	for (String data : chavesMax){
  	DadoTemporal dado = serieMapa.get(data);
  	dados.add(dado);
   	}

	 Collections.sort(dados);
	 Calendar dataInicialSerie= Calendar.getInstance();;
	 Calendar dataFinalSerie= Calendar.getInstance();;
	 dataInicialSerie.setTime(dados.get(0).getData());
	 dataFinalSerie.setTime(dados.get(dados.size()-1).getData());
	 serietemp.setDataInicialSerie(dataInicialSerie);
	 serietemp.setDataFinalSerie(dataFinalSerie);
	 serietemp.setDados(dados);	
	 serietemp.setaMapaStrDadoTemporal();
	 vhidAnual.setInvhidro(invhid);
	 vhidAnual.setSerietemporal(serietemp);
	 
	
	
	
	
	
	
	
	
	
	return vhidAnual;
	
	
}
	


public static  Map<String,DadoTemporal> serieEstatistica(SerieTemporal st,int codEstatistica) {
	
	int tammin=5;
	int tipoSerie=1;
	double tolFalha=0;
	int mesIni=1;
	int mesFim=12;
	int anoIni=-99999;
	int anoFim=-99999;
	
	/*int tammin=configuraSeries.getTamanhoMinimo();
	int tipoSerie=configuraSeries.getTipoSerieFalha();
	double tolFalha=configuraSeries.getTolFalhaMax();
	int mesIni=configuraSeries.getMesIni();
	int mesFim=configuraSeries.getMesFim();
	int anoIni=configuraSeries.getAnoIniSubConjunto();
	int anoFim=configuraSeries.getAnoFimSubConjunto();
	int codEstatistica=configuraSeries.getCodEstatistica();*/
	
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
	Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
	//int [] ano = verificarFalhaAno(anoIni,anoFim);
	int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
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
	
	int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	double toleranciaMaxFalha=tolFalha;
	Map<String,DadoTemporal> dados_anual;
	Map<String,DadoTemporal> dados_filtro;
	//Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
	
	ArrayList<String> serieData = new ArrayList<String>();
	dados_anual=new HashMap<String,DadoTemporal>();
	double totdados=0;
	double totdias=0;
	int itamanho=0;
	while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
			
		    dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
 	      	 	       
 	        DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
 	        Double valorEstatistica=valorEstDT.getValor();
 	        String datastr =formatter.format(utilDateIni);
	     	if(valorEstatistica != -99999.0){
			     	DadoTemporal dt = new DadoTemporal();
				     	if(valorEstDT.getData()==null){
				     		dt.setData(utilDateIni);	
				     	}else{
				     		dt.setData(valorEstDT.getData());
				     	}
			     	dt.setValor(valorEstatistica);
			     	dadosEstatistica.put(datastr,dt);
			     	serieData.add(datastr);
			     	
			     	if(dados_filtro.size() > 0 ){
			 	    itamanho++;	
			 	    }
	     	}
	     	
 	        
 	        totdados=dados_filtro.size()+totdados;
 	        totdias=ndiasSemFalha+totdias;
 	        //dados_anual_tot.putAll(dados_filtro);
 	        clStart.add(Calendar.YEAR, 1);
			clStartfim.add(Calendar.YEAR, 1);
			utilDateIni=clStart.getTime();
			utilDateIniMesFim=clStartfim.getTime();
					
			if(mesFim == 2){
			//utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
			}
			ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	}
	
	dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
	double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
	dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
	
	DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
    Double valorEstatistica=valorEstDT.getValor();
    String datastr =formatter.format(utilDateIni);
 	if(valorEstatistica != -99999.0){
	     	DadoTemporal dt = new DadoTemporal();
		     	if(valorEstDT.getData()==null){
		     		dt.setData(utilDateIni);	
		     	}else{
		     		dt.setData(valorEstDT.getData());
		     	}
	     	dt.setValor(valorEstatistica);
	     	dadosEstatistica.put(datastr,dt);
	     	serieData.add(datastr);
	     	
	     	if(dados_filtro.size() > 0 ){
	 	    itamanho++;	
	 	    }
 	}
    
    totdados=dados_filtro.size()+totdados;
    totdias=ndiasSemFalha+totdias;
    	    
    if(itamanho >= tammin){
    	return dadosEstatistica;	
    }else{
    	return null;
    }
	
	
}


public static VariavelHidrologica serieEstatisticaVhid(VariavelHidrologica vhid,int codEstatistica) {
	
	
	VariavelHidrologica vhidAnual=new VariavelHidrologica();
	Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
	serieMapa=ST_pegarSerieEstatisticaDaSerieMensalConfigurada.serieEstatistica(vhid.getSerietemporal(),codEstatistica);
	
	
	    InventarioHidrologico invhid = new InventarioHidrologico();
        invhid.setBaciaCodigo(vhid.getInvhidro().getBaciaCodigo());
        invhid.setSubBaciaCodigo(vhid.getInvhidro().getSubBaciaCodigo());
		invhid.setEstacao_codigo(vhid.getInvhidro().getEstacao_codigo());
		invhid.setLatitude(vhid.getInvhidro().getLatitude());
		invhid.setLongitude(vhid.getInvhidro().getLongitude());
		invhid.setAltitude(vhid.getInvhidro().getAltitude());
		invhid.setAreaDrenagem(vhid.getInvhidro().getAreaDrenagem());
		invhid.setOrigemSerie(vhid.getInvhidro().getOrigemSerie());
		invhid.setDescricaoOrigemSerie("ESTATISTICA - "+vhid.getInvhidro().getDescricaoOrigemSerie());
		invhid.setDiscretizaçãoTemporária("ANUAL");
		invhid.setTipodeDado(vhid.getInvhidro().getTipodeDado());
		invhid.setMesAnoHidro(vhid.getInvhidro().getMesAnoHidro());

    SerieTemporal serietemp = new SerieTemporal ();
	ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
		
	if(serieMapa != null){
	
	Set<String> chavesMax =  serieMapa.keySet();
  	for (String data : chavesMax){
  	DadoTemporal dado = serieMapa.get(data);
  	dados.add(dado);
   	}

	 Collections.sort(dados);
	 Calendar dataInicialSerie= Calendar.getInstance();;
	 Calendar dataFinalSerie= Calendar.getInstance();;
	 dataInicialSerie.setTime(dados.get(0).getData());
	 dataFinalSerie.setTime(dados.get(dados.size()-1).getData());
	 serietemp.setDataInicialSerie(dataInicialSerie);
	 serietemp.setDataFinalSerie(dataFinalSerie);
	 serietemp.setDados(dados);	
	 serietemp.setaMapaStrDadoTemporal();
	 vhidAnual.setInvhidro(invhid);
	 vhidAnual.setSerietemporal(serietemp);
	 
	}else{
		return null;
	}
	
	
	
	
	
	
	
	
	return vhidAnual;
	
	
}

public static SerieTemporal serieEstatisticaVhid(SerieTemporal st,int codEstatistica) {
  
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  VariavelHidrologica vhidAnual=new VariavelHidrologica();
  Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
  serieMapa=ST_pegarSerieEstatisticaDaSerieMensalConfigurada.serieEstatistica(st,codEstatistica);
  
  
     /* InventarioHidrologico invhid = new InventarioHidrologico();
        invhid.setBaciaCodigo(vhid.getInvhidro().getBaciaCodigo());
        invhid.setSubBaciaCodigo(vhid.getInvhidro().getSubBaciaCodigo());
    invhid.setEstacao_codigo(vhid.getInvhidro().getEstacao_codigo());
    invhid.setLatitude(vhid.getInvhidro().getLatitude());
    invhid.setLongitude(vhid.getInvhidro().getLongitude());
    invhid.setAltitude(vhid.getInvhidro().getAltitude());
    invhid.setAreaDrenagem(vhid.getInvhidro().getAreaDrenagem());
    invhid.setOrigemSerie(vhid.getInvhidro().getOrigemSerie());
    invhid.setDescricaoOrigemSerie("ESTATISTICA - "+vhid.getInvhidro().getDescricaoOrigemSerie());
    invhid.setDiscretizaçãoTemporária("ANUAL");
    invhid.setTipodeDado(vhid.getInvhidro().getTipodeDado());
    invhid.setMesAnoHidro(vhid.getInvhidro().getMesAnoHidro());*/

    SerieTemporal serietemp = new SerieTemporal ();
  ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
    
  if(serieMapa != null){
  
  Set<String> chavesMax =  serieMapa.keySet();
    for (String data : chavesMax){
    DadoTemporal dado = serieMapa.get(data);
    dados.add(dado);
    }

   Collections.sort(dados);
   Calendar dataInicialSerie= Calendar.getInstance();;
   Calendar dataFinalSerie= Calendar.getInstance();;
   dataInicialSerie.setTime(dados.get(0).getData());
   dataFinalSerie.setTime(dados.get(dados.size()-1).getData());
   serietemp.setDataInicialSerie(dataInicialSerie);
   serietemp.setDataFinalSerie(dataFinalSerie);
   serietemp.setDados(dados); 
   serietemp.setaMapaStrDadoTemporal();
   
   Date dfim=dataFinalSerie.getTime();
    String dataFimstr=formatter.format(dfim);
    serietemp.setDataFinalSerieStr(dataFimstr);
    Date dini=dataInicialSerie.getTime();
      String dataInistr=formatter.format(dini);
      serietemp.setDataInicialSerieStr(dataInistr);
   //vhidAnual.setInvhidro(invhid);
   //vhidAnual.setSerietemporal(serietemp);
   
  }else{
    return null;
  }
  
  
  
  
  
  
  
  
  return serietemp;
  
  
}


public static SerieTemporal serieEstatisticaVhid(SerieTemporal st,int codEstatistica,int mesIni,int mesFim) {
  
  
  VariavelHidrologica vhidAnual=new VariavelHidrologica();
  Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
  serieMapa=ST_pegarSerieEstatisticaDaSerieMensalConfigurada.serieEstatistica(st,codEstatistica,mesIni,mesFim);
    
    SerieTemporal serietemp = new SerieTemporal ();
  ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
    
  if(serieMapa != null){
  
  Set<String> chavesMax =  serieMapa.keySet();
    for (String data : chavesMax){
    DadoTemporal dado = serieMapa.get(data);
    dados.add(dado);
    }

   Collections.sort(dados);
   Calendar dataInicialSerie= Calendar.getInstance();;
   Calendar dataFinalSerie= Calendar.getInstance();;
   dataInicialSerie.setTime(dados.get(0).getData());
   dataFinalSerie.setTime(dados.get(dados.size()-1).getData());
   serietemp.setDataInicialSerie(dataInicialSerie);
   serietemp.setDataFinalSerie(dataFinalSerie);
   serietemp.setDados(dados); 
   serietemp.setaMapaStrDadoTemporal();
   //vhidAnual.setInvhidro(invhid);
   //vhidAnual.setSerietemporal(serietemp);
   
  }else{
    return null;
  }
  
  
  
  return serietemp;
  
  
}


public static SerieTemporal serieEstatisticaVhid(ConfiguraSeries configuraSeries,SerieTemporal st) {
	
	
	Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
	serieMapa=ST_pegarSerieEstatisticaDaSerieMensalConfigurada.serieEstatistica(configuraSeries,st);
	if(serieMapa == null)	return null;
    
    SerieTemporal serietemp = new SerieTemporal ();
	ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
		
	Set<String> chavesMax =  serieMapa.keySet();
  	for (String data : chavesMax){
  	DadoTemporal dado = serieMapa.get(data);
  	dados.add(dado);
   	}

	 Collections.sort(dados);
	 Calendar dataInicialSerie= Calendar.getInstance();;
	 Calendar dataFinalSerie= Calendar.getInstance();;
	 dataInicialSerie.setTime(dados.get(0).getData());
	 dataFinalSerie.setTime(dados.get(dados.size()-1).getData());
	 serietemp.setDataInicialSerie(dataInicialSerie);
	 serietemp.setDataFinalSerie(dataFinalSerie);
	 serietemp.setDados(dados);	
	 serietemp.setaMapaStrDadoTemporal();
	 //vhidAnual.setInvhidro(invhid);
	 //vhidAnual.setSerietemporal(serietemp);

	
	return serietemp;
	
	
}





public static SerieTemporal serieEstatisticaVhid(SerieTemporal st,int codEstatistica,int mesIni,int mesFim,int anoIni,int anoFim) {
  
  
  VariavelHidrologica vhidAnual=new VariavelHidrologica();
  Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
  serieMapa=ST_pegarSerieEstatisticaDaSerieMensalConfigurada.serieEstatistica(st,codEstatistica,mesIni,mesFim,anoIni, anoFim);
    
    SerieTemporal serietemp = new SerieTemporal ();
  ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
    
  if(serieMapa != null){
  
  Set<String> chavesMax =  serieMapa.keySet();
    for (String data : chavesMax){
    DadoTemporal dado = serieMapa.get(data);
    dados.add(dado);
    }

   Collections.sort(dados);
   Calendar dataInicialSerie= Calendar.getInstance();;
   Calendar dataFinalSerie= Calendar.getInstance();;
   dataInicialSerie.setTime(dados.get(0).getData());
   dataFinalSerie.setTime(dados.get(dados.size()-1).getData());
   serietemp.setDataInicialSerie(dataInicialSerie);
   serietemp.setDataFinalSerie(dataFinalSerie);
   serietemp.setDados(dados); 
   serietemp.setaMapaStrDadoTemporal();
   //vhidAnual.setInvhidro(invhid);
   //vhidAnual.setSerietemporal(serietemp);
   
  }else{
    return null;
  }
  
  
  
  return serietemp;
  
  
}
public static SerieTemporal serieEstatisticaVhid(int anoIni,int anoFim,SerieTemporal st,int codEstatistica) {
  
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  VariavelHidrologica vhidAnual=new VariavelHidrologica();
  Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
  serieMapa=ST_pegarSerieEstatisticaDaSerieMensalConfigurada.serieEstatistica(anoIni,anoFim,st,codEstatistica);
  
  
     /* InventarioHidrologico invhid = new InventarioHidrologico();
        invhid.setBaciaCodigo(vhid.getInvhidro().getBaciaCodigo());
        invhid.setSubBaciaCodigo(vhid.getInvhidro().getSubBaciaCodigo());
    invhid.setEstacao_codigo(vhid.getInvhidro().getEstacao_codigo());
    invhid.setLatitude(vhid.getInvhidro().getLatitude());
    invhid.setLongitude(vhid.getInvhidro().getLongitude());
    invhid.setAltitude(vhid.getInvhidro().getAltitude());
    invhid.setAreaDrenagem(vhid.getInvhidro().getAreaDrenagem());
    invhid.setOrigemSerie(vhid.getInvhidro().getOrigemSerie());
    invhid.setDescricaoOrigemSerie("ESTATISTICA - "+vhid.getInvhidro().getDescricaoOrigemSerie());
    invhid.setDiscretizaçãoTemporária("ANUAL");
    invhid.setTipodeDado(vhid.getInvhidro().getTipodeDado());
    invhid.setMesAnoHidro(vhid.getInvhidro().getMesAnoHidro());*/

    SerieTemporal serietemp = new SerieTemporal ();
  ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
    
  if(serieMapa != null){
  
  Set<String> chavesMax =  serieMapa.keySet();
    for (String data : chavesMax){
    DadoTemporal dado = serieMapa.get(data);
    dados.add(dado);
    }

   Collections.sort(dados);
   Calendar dataInicialSerie= Calendar.getInstance();;
   Calendar dataFinalSerie= Calendar.getInstance();;
   dataInicialSerie.setTime(dados.get(0).getData());
   dataFinalSerie.setTime(dados.get(dados.size()-1).getData());
   serietemp.setDataInicialSerie(dataInicialSerie);
   serietemp.setDataFinalSerie(dataFinalSerie);
   serietemp.setDados(dados); 
   serietemp.setaMapaStrDadoTemporal();
   
   Date dfim=dataFinalSerie.getTime();
    String dataFimstr=formatter.format(dfim);
    serietemp.setDataFinalSerieStr(dataFimstr);
    Date dini=dataInicialSerie.getTime();
      String dataInistr=formatter.format(dini);
      serietemp.setDataInicialSerieStr(dataInistr);
   //vhidAnual.setInvhidro(invhid);
   //vhidAnual.setSerietemporal(serietemp);
   
  }else{
    return null;
  }
  
  
  
  
  
  
  
  
  return serietemp;
  
  
}


public static  Map<String,DadoTemporal> serieEstatistica(SerieTemporal st,int codEstatistica,int mesIni,int mesFim) {
  
  int tammin=5;
  int tipoSerie=1;
  double tolFalha=0;
  //int mesIni=1;
  //int mesFim=12;
  int anoIni=-99999;
  int anoFim=-99999;
  
  /*int tammin=configuraSeries.getTamanhoMinimo();
  int tipoSerie=configuraSeries.getTipoSerieFalha();
  double tolFalha=configuraSeries.getTolFalhaMax();
  int mesIni=configuraSeries.getMesIni();
  int mesFim=configuraSeries.getMesFim();
  int anoIni=configuraSeries.getAnoIniSubConjunto();
  int anoFim=configuraSeries.getAnoFimSubConjunto();
  int codEstatistica=configuraSeries.getCodEstatistica();*/
  
  
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
  Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
  //int [] ano = verificarFalhaAno(anoIni,anoFim);
  int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
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
  
  int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
  double toleranciaMaxFalha=tolFalha;
  Map<String,DadoTemporal> dados_anual;
  Map<String,DadoTemporal> dados_filtro;
  //Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
  
  ArrayList<String> serieData = new ArrayList<String>();
  dados_anual=new HashMap<String,DadoTemporal>();
  double totdados=0;
  double totdias=0;
  int itamanho=0;
  while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
      
        dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
          double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
          dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
                   
          DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
          Double valorEstatistica=valorEstDT.getValor();
          String datastr =formatter.format(utilDateIni);
        if(valorEstatistica != -99999.0){
            DadoTemporal dt = new DadoTemporal();
              if(valorEstDT.getData()==null){
                dt.setData(utilDateIni);  
              }else{
                dt.setData(valorEstDT.getData());
              }
            dt.setValor(valorEstatistica);
            dadosEstatistica.put(datastr,dt);
            serieData.add(datastr);
            
            if(dados_filtro.size() > 0 ){
            itamanho++; 
            }
        }
        
          
          totdados=dados_filtro.size()+totdados;
          totdias=ndiasSemFalha+totdias;
          //dados_anual_tot.putAll(dados_filtro);
          clStart.add(Calendar.YEAR, 1);
      clStartfim.add(Calendar.YEAR, 1);
      utilDateIni=clStart.getTime();
      utilDateIniMesFim=clStartfim.getTime();
          
      if(mesFim == 2){
      //utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
      }
      ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
  }
  
  dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
  double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
  dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
  
  DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
    Double valorEstatistica=valorEstDT.getValor();
    String datastr =formatter.format(utilDateIni);
  if(valorEstatistica != -99999.0){
        DadoTemporal dt = new DadoTemporal();
          if(valorEstDT.getData()==null){
            dt.setData(utilDateIni);  
          }else{
            dt.setData(valorEstDT.getData());
          }
        dt.setValor(valorEstatistica);
        dadosEstatistica.put(datastr,dt);
        serieData.add(datastr);
        
        if(dados_filtro.size() > 0 ){
        itamanho++; 
        }
  }
    
    totdados=dados_filtro.size()+totdados;
    totdias=ndiasSemFalha+totdias;
          
    if(itamanho >= tammin){
      return dadosEstatistica;  
    }else{
      return null;
    }
  
  
}

public static  Map<String,DadoTemporal> serieEstatistica(SerieTemporal st,int codEstatistica,int mesIni,int mesFim,int anoIni,int anoFim) {
  
  int tammin=1;
  int tipoSerie=1;
  double tolFalha=0;
  //int mesIni=1;
  //int mesFim=12;
  //int anoIni=-99999;
  //int anoFim=-99999;
  
  /*int tammin=configuraSeries.getTamanhoMinimo();
  int tipoSerie=configuraSeries.getTipoSerieFalha();
  double tolFalha=configuraSeries.getTolFalhaMax();
  int mesIni=configuraSeries.getMesIni();
  int mesFim=configuraSeries.getMesFim();
  int anoIni=configuraSeries.getAnoIniSubConjunto();
  int anoFim=configuraSeries.getAnoFimSubConjunto();
  int codEstatistica=configuraSeries.getCodEstatistica();*/
  
  
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
  Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
  //int [] ano = verificarFalhaAno(anoIni,anoFim);
  int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
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
  
  int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
  double toleranciaMaxFalha=tolFalha;
  Map<String,DadoTemporal> dados_anual;
  Map<String,DadoTemporal> dados_filtro;
  //Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
  
  ArrayList<String> serieData = new ArrayList<String>();
  dados_anual=new HashMap<String,DadoTemporal>();
  double totdados=0;
  double totdias=0;
  int itamanho=0;
  while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
      
        dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
          double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
          dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
                   
          DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
          Double valorEstatistica=valorEstDT.getValor();
          String datastr =formatter.format(utilDateIni);
        if(valorEstatistica != -99999.0){
            DadoTemporal dt = new DadoTemporal();
              if(valorEstDT.getData()==null){
                dt.setData(utilDateIni);  
              }else{
                dt.setData(valorEstDT.getData());
              }
            dt.setValor(valorEstatistica);
            dadosEstatistica.put(datastr,dt);
            serieData.add(datastr);
            
            if(dados_filtro.size() > 0 ){
            itamanho++; 
            }
        }
        
          
          totdados=dados_filtro.size()+totdados;
          totdias=ndiasSemFalha+totdias;
          //dados_anual_tot.putAll(dados_filtro);
          clStart.add(Calendar.YEAR, 1);
      clStartfim.add(Calendar.YEAR, 1);
      utilDateIni=clStart.getTime();
      utilDateIniMesFim=clStartfim.getTime();
          
      if(mesFim == 2){
      //utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
      }
      ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
  }
  
  dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
  double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
  dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
  
  DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
    Double valorEstatistica=valorEstDT.getValor();
    String datastr =formatter.format(utilDateIni);
  if(valorEstatistica != -99999.0){
        DadoTemporal dt = new DadoTemporal();
          if(valorEstDT.getData()==null){
            dt.setData(utilDateIni);  
          }else{
            dt.setData(valorEstDT.getData());
          }
        dt.setValor(valorEstatistica);
        dadosEstatistica.put(datastr,dt);
        serieData.add(datastr);
        
        if(dados_filtro.size() > 0 ){
        itamanho++; 
        }
  }
    
    totdados=dados_filtro.size()+totdados;
    totdias=ndiasSemFalha+totdias;
          
    if(itamanho >= tammin){
      return dadosEstatistica;  
    }else{
      return null;
    }
  
  
}

public static  Map<String,DadoTemporal> serieEstatistica(ArrayList<DadoTemporal> dados,int codEstatistica) {
	
	int tammin=1;
	
	int tipoSerie=1; //sem falha
	double tolFalha=1;
	int mesIni=1;
	int mesFim=12;
	int anoIni=-99999;
	int anoFim=-99999;
	
	SerieTemporal st=new SerieTemporal();
	st.setDados(dados);
	st.setaMapaStrDadoTemporal();
	
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
	Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
	//int [] ano = verificarFalhaAno(anoIni,anoFim);
	int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
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
	
	int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	double toleranciaMaxFalha=tolFalha;
	Map<String,DadoTemporal> dados_anual;
	Map<String,DadoTemporal> dados_filtro;
	//Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
	
	ArrayList<String> serieData = new ArrayList<String>();
	dados_anual=new HashMap<String,DadoTemporal>();
	double totdados=0;
	double totdias=0;
	int itamanho=0;
	while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
			
		    dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
 	      	 	       
 	        DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
 	        Double valorEstatistica=valorEstDT.getValor();
 	        String datastr =formatter.format(utilDateIni);
	     	if(valorEstatistica != -99999.0){
			     	DadoTemporal dt = new DadoTemporal();
				     	if(valorEstDT.getData()==null){
				     		dt.setData(utilDateIni);	
				     	}else{
				     		dt.setData(valorEstDT.getData());
				     	}
			     	dt.setValor(valorEstatistica);
			     	dadosEstatistica.put(datastr,dt);
			     	serieData.add(datastr);
			     	
			     	if(dados_filtro.size() > 0 ){
			 	    itamanho++;	
			 	    }
	     	}
	     	
 	        
 	        totdados=dados_filtro.size()+totdados;
 	        totdias=ndiasSemFalha+totdias;
 	        //dados_anual_tot.putAll(dados_filtro);
 	        clStart.add(Calendar.YEAR, 1);
			clStartfim.add(Calendar.YEAR, 1);
			utilDateIni=clStart.getTime();
			utilDateIniMesFim=clStartfim.getTime();
					
			if(mesFim == 2){
			//utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
			}
			ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	}
	
	dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
	double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
	dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
	
	DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
    Double valorEstatistica=valorEstDT.getValor();
    String datastr =formatter.format(utilDateIni);
 	if(valorEstatistica != -99999.0){
	     	DadoTemporal dt = new DadoTemporal();
		     	if(valorEstDT.getData()==null){
		     		dt.setData(utilDateIni);	
		     	}else{
		     		dt.setData(valorEstDT.getData());
		     	}
	     	dt.setValor(valorEstatistica);
	     	dadosEstatistica.put(datastr,dt);
	     	serieData.add(datastr);
	     	
	     	if(dados_filtro.size() > 0 ){
	 	    itamanho++;	
	 	    }
 	}
    
    totdados=dados_filtro.size()+totdados;
    totdias=ndiasSemFalha+totdias;
    	    
    if(itamanho >= tammin){
    	return dadosEstatistica;	
    }else{
    	return null;
    }
	
	
}


public static  Map<String,DadoTemporal> serieEstatistica(Map<String,DadoTemporal> dados,int codEstatistica) {
	
	int tammin=1;
	
	int tipoSerie=1; //sem falha
	double tolFalha=1;
	int mesIni=1;
	int mesFim=12;
	int anoIni=-99999;
	int anoFim=-99999;
	
	//SerieTemporal st=new SerieTemporal();
	//st.setDados(dados);
	//st.setaMapaStrDadoTemporal();
	
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
	Map<String,DadoTemporal> mapaStrDadosger=dados;
	//int [] ano = verificarFalhaAno(anoIni,anoFim);
	int [] ano =ST_verificarAno.anos(mapaStrDadosger, anoIni, anoFim);
	if(ano[0]>ano[1])return dadosEstatistica;
	Date utilDateIni =ST_pegarDataInicioFiltroMes.data(mapaStrDadosger, mesIni, mesFim,ano[0],ano[1]);
	Date utilDateIniMesFim =ST_pegarDataMesFinalFiltroMes.data(mapaStrDadosger, mesIni, mesFim, ano[0],ano[1]);
	Date utilDateFim = ST_pegarDataFinalFiltroMes.data(mapaStrDadosger,mesIni, mesFim,ano[0],ano[1]);
	
	Calendar clStart =Calendar.getInstance();
	Calendar clEnd =Calendar.getInstance();
	Calendar clStartfim =Calendar.getInstance();
	
	clEnd.setTime(utilDateFim);
	clStart.setTime(utilDateIni);
	clStartfim.setTime(utilDateIniMesFim);
	
	int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	double toleranciaMaxFalha=tolFalha;
	Map<String,DadoTemporal> dados_anual;
	Map<String,DadoTemporal> dados_filtro;
	//Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
	
	ArrayList<String> serieData = new ArrayList<String>();
	dados_anual=new HashMap<String,DadoTemporal>();
	double totdados=0;
	double totdias=0;
	int itamanho=0;
	while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
			
		    dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
 	      	 	       
 	        DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
 	        Double valorEstatistica=valorEstDT.getValor();
 	        String datastr =formatter.format(utilDateIni);
	     	if(valorEstatistica != -99999.0){
			     	DadoTemporal dt = new DadoTemporal();
				     	if(valorEstDT.getData()==null){
				     		dt.setData(utilDateIni);	
				     	}else{
				     		dt.setData(valorEstDT.getData());
				     	}
			     	dt.setValor(valorEstatistica);
			     	dadosEstatistica.put(datastr,dt);
			     	serieData.add(datastr);
			     	
			     	if(dados_filtro.size() > 0 ){
			 	    itamanho++;	
			 	    }
	     	}
	     	
 	        
 	        totdados=dados_filtro.size()+totdados;
 	        totdias=ndiasSemFalha+totdias;
 	        //dados_anual_tot.putAll(dados_filtro);
 	        clStart.add(Calendar.YEAR, 1);
			clStartfim.add(Calendar.YEAR, 1);
			utilDateIni=clStart.getTime();
			utilDateIniMesFim=clStartfim.getTime();
					
			if(mesFim == 2){
			//utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
			}
			ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	}
	
	dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
	double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
	dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
	
	DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
    Double valorEstatistica=valorEstDT.getValor();
    String datastr =formatter.format(utilDateIni);
 	if(valorEstatistica != -99999.0){
	     	DadoTemporal dt = new DadoTemporal();
		     	if(valorEstDT.getData()==null){
		     		dt.setData(utilDateIni);	
		     	}else{
		     		dt.setData(valorEstDT.getData());
		     	}
	     	dt.setValor(valorEstatistica);
	     	dadosEstatistica.put(datastr,dt);
	     	serieData.add(datastr);
	     	
	     	if(dados_filtro.size() > 0 ){
	 	    itamanho++;	
	 	    }
 	}
    
    totdados=dados_filtro.size()+totdados;
    totdias=ndiasSemFalha+totdias;
    	    
    if(itamanho >= tammin){
    	return dadosEstatistica;	
    }else{
    	return null;
    }
	
	
}


public static  Map<String,DadoTemporal> serieEstatisticaAnoHidrologico(ConfiguraSeries configuraSeries,SerieTemporal st) {
	
	int tammin=configuraSeries.getTamanhoMinimo();
	int tipoSerie=configuraSeries.getTipoSerieFalha();
	double tolFalha=configuraSeries.getTolFalhaMax();
	int mesIni=configuraSeries.getMesIni();
	int mesFim=configuraSeries.getMesFim();
	int anoIni=configuraSeries.getAnoIniSubConjunto();
	int anoFim=configuraSeries.getAnoFimSubConjunto();
	int codEstatistica=configuraSeries.getCodEstatistica();
	
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
	Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
	//int [] ano = verificarFalhaAno(anoIni,anoFim);
	int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
	if(ano[0]>ano[1])return dadosEstatistica;
	Date utilDateIni =ST_pegarDataInicioFiltroMes.data(st, mesIni, mesFim,ano[0],ano[1]);
	Date utilDateIniMesFim =ST_pegarDataMesFinalFiltroMes.data(st, mesIni, mesFim, ano[0],ano[1]);
	Date utilDateFim = ST_pegarDataFinalFiltroMes.dataHidrologica(st,mesIni, mesFim,ano[0],ano[1]);
	
	Calendar clStart =Calendar.getInstance();
	Calendar clEnd =Calendar.getInstance();
	Calendar clStartfim =Calendar.getInstance();
	
	clEnd.setTime(utilDateFim);
	clStart.setTime(utilDateIni);
	clStartfim.setTime(utilDateIniMesFim);
	
	int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	double toleranciaMaxFalha=tolFalha;
	Map<String,DadoTemporal> dados_anual;
	Map<String,DadoTemporal> dados_filtro;
	//Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
	
	ArrayList<String> serieData = new ArrayList<String>();
	dados_anual=new HashMap<String,DadoTemporal>();
	double totdados=0;
	double totdias=0;
	int itamanho=0;
	while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
			
		    dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
 	      	 	       
 	        DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
 	        Double valorEstatistica=valorEstDT.getValor();
 	        String datastr =formatter.format(utilDateIni);
	     	if(valorEstatistica != -99999.0){
			     	DadoTemporal dt = new DadoTemporal();
				     	if(valorEstDT.getData()==null){
				     		dt.setData(utilDateIni);	
				     	}else{
				     		dt.setData(valorEstDT.getData());
				     	}
			     	dt.setValor(valorEstatistica);
			     	dadosEstatistica.put(datastr,dt);
			     	serieData.add(datastr);
			     	
			     	if(dados_filtro.size() > 0 ){
			 	    itamanho++;	
			 	    }
	     	}
	     	
 	        
 	        totdados=dados_filtro.size()+totdados;
 	        totdias=ndiasSemFalha+totdias;
 	        //dados_anual_tot.putAll(dados_filtro);
 	        clStart.add(Calendar.YEAR, 1);
			clStartfim.add(Calendar.YEAR, 1);
			utilDateIni=clStart.getTime();
			utilDateIniMesFim=clStartfim.getTime();
					
			if(mesFim == 2){
			//utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
			}
			ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
	}
	
	dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
	double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
	dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
	
	DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
    Double valorEstatistica=valorEstDT.getValor();
    String datastr =formatter.format(utilDateIni);
 	if(valorEstatistica != -99999.0){
	     	DadoTemporal dt = new DadoTemporal();
		     	if(valorEstDT.getData()==null){
		     		dt.setData(utilDateIni);	
		     	}else{
		     		dt.setData(valorEstDT.getData());
		     	}
	     	dt.setValor(valorEstatistica);
	     	dadosEstatistica.put(datastr,dt);
	     	serieData.add(datastr);
	     	
	     	if(dados_filtro.size() > 0 ){
	 	    itamanho++;	
	 	    }
 	}
    
    totdados=dados_filtro.size()+totdados;
    totdias=ndiasSemFalha+totdias;
    	    
    if(itamanho >= tammin){
    	return dadosEstatistica;	
    }else{
    	return null;
    }
	
	
}

public static  Map<String,DadoTemporal> serieEstatistica(int anoIni,int anoFim,SerieTemporal st,int codEstatistica) {
  
  int tammin=5;
  int tipoSerie=1;
  double tolFalha=0;
  int mesIni=1;
  int mesFim=12;
  
  
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
  Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
  //int [] ano = verificarFalhaAno(anoIni,anoFim);
  int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
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
  
  int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
  double toleranciaMaxFalha=tolFalha;
  Map<String,DadoTemporal> dados_anual;
  Map<String,DadoTemporal> dados_filtro;
  //Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
  
  ArrayList<String> serieData = new ArrayList<String>();
  dados_anual=new HashMap<String,DadoTemporal>();
  double totdados=0;
  double totdias=0;
  int itamanho=0;
  while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
      
        dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
          double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
          dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
                   
          DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
          Double valorEstatistica=valorEstDT.getValor();
          String datastr =formatter.format(utilDateIni);
        if(valorEstatistica != -99999.0){
            DadoTemporal dt = new DadoTemporal();
              if(valorEstDT.getData()==null){
                dt.setData(utilDateIni);  
              }else{
                dt.setData(valorEstDT.getData());
              }
            dt.setValor(valorEstatistica);
            dadosEstatistica.put(datastr,dt);
            serieData.add(datastr);
            
            if(dados_filtro.size() > 0 ){
            itamanho++; 
            }
        }
        
          
          totdados=dados_filtro.size()+totdados;
          totdias=ndiasSemFalha+totdias;
          //dados_anual_tot.putAll(dados_filtro);
          clStart.add(Calendar.YEAR, 1);
      clStartfim.add(Calendar.YEAR, 1);
      utilDateIni=clStart.getTime();
      utilDateIniMesFim=clStartfim.getTime();
          
      if(mesFim == 2){
      //utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
      }
      ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
  }
  
  dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
  double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
  dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
  
  DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
    Double valorEstatistica=valorEstDT.getValor();
    String datastr =formatter.format(utilDateIni);
  if(valorEstatistica != -99999.0){
        DadoTemporal dt = new DadoTemporal();
          if(valorEstDT.getData()==null){
            dt.setData(utilDateIni);  
          }else{
            dt.setData(valorEstDT.getData());
          }
        dt.setValor(valorEstatistica);
        dadosEstatistica.put(datastr,dt);
        serieData.add(datastr);
        
        if(dados_filtro.size() > 0 ){
        itamanho++; 
        }
  }
    
    totdados=dados_filtro.size()+totdados;
    totdias=ndiasSemFalha+totdias;
          
    if(itamanho >= tammin){
      return dadosEstatistica;  
    }else{
      return null;
    }
  
  
}










public static  Map<String,DadoTemporal> serieEstatistica(int tammin,int anoIni,int anoFim,SerieTemporal st,int codEstatistica) {
  
  //int tammin=5;
  int tipoSerie=1;
  double tolFalha=0;
  int mesIni=1;
  int mesFim=12;
  
  
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
  Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
  //int [] ano = verificarFalhaAno(anoIni,anoFim);
  int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
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
  
  int ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
  double toleranciaMaxFalha=tolFalha;
  Map<String,DadoTemporal> dados_anual;
  Map<String,DadoTemporal> dados_filtro;
  //Map<String,DadoTemporal> dados_anual_tot =new HashMap<String,DadoTemporal>();
  
  ArrayList<String> serieData = new ArrayList<String>();
  dados_anual=new HashMap<String,DadoTemporal>();
  double totdados=0;
  double totdias=0;
  int itamanho=0;
  while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
      
        dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
          double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
          dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
                   
          DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
          Double valorEstatistica=valorEstDT.getValor();
          String datastr =formatter.format(utilDateIni);
        if(valorEstatistica != -99999.0){
            DadoTemporal dt = new DadoTemporal();
              if(valorEstDT.getData()==null){
                dt.setData(utilDateIni);  
              }else{
                dt.setData(valorEstDT.getData());
              }
            dt.setValor(valorEstatistica);
            dadosEstatistica.put(datastr,dt);
            serieData.add(datastr);
            
            if(dados_filtro.size() > 0 ){
            itamanho++; 
            }
        }
        
          
          totdados=dados_filtro.size()+totdados;
          totdias=ndiasSemFalha+totdias;
          //dados_anual_tot.putAll(dados_filtro);
          clStart.add(Calendar.YEAR, 1);
      clStartfim.add(Calendar.YEAR, 1);
      utilDateIni=clStart.getTime();
      utilDateIniMesFim=clStartfim.getTime();
          
      if(mesFim == 2){
      //utilDateIniMesFim=st.verficiarNdiasFevereiro(utilDateIniMesFim, mesFim);
      }
      ndiasSemFalha=Dif.meses(utilDateIni, utilDateIniMesFim)+1;
  }
  
  dados_anual= ST_valoresMensaisIntervaloDataSemFalhaMapa.serieMensal(utilDateIni, utilDateIniMesFim, mapaStrDadosger);
  double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
  dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas);
  
  DadoTemporal valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dados_filtro);
    Double valorEstatistica=valorEstDT.getValor();
    String datastr =formatter.format(utilDateIni);
  if(valorEstatistica != -99999.0){
        DadoTemporal dt = new DadoTemporal();
          if(valorEstDT.getData()==null){
            dt.setData(utilDateIni);  
          }else{
            dt.setData(valorEstDT.getData());
          }
        dt.setValor(valorEstatistica);
        dadosEstatistica.put(datastr,dt);
        serieData.add(datastr);
        
        if(dados_filtro.size() > 0 ){
        itamanho++; 
        }
  }
    
    totdados=dados_filtro.size()+totdados;
    totdias=ndiasSemFalha+totdias;
          
    if(itamanho >= tammin){
      return dadosEstatistica;  
    }else{
      return null;
    }
  
  
}



public static SerieTemporal serieEstatisticaVhid(int tammin,int anoIni,int anoFim,SerieTemporal st,int codEstatistica) {
  
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  VariavelHidrologica vhidAnual=new VariavelHidrologica();
  Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
  serieMapa=ST_pegarSerieEstatisticaDaSerieMensalConfigurada.serieEstatistica(tammin,anoIni,anoFim,st,codEstatistica);
  
    SerieTemporal serietemp = new SerieTemporal ();
  ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
    
  if(serieMapa != null){
  
  Set<String> chavesMax =  serieMapa.keySet();
    for (String data : chavesMax){
    DadoTemporal dado = serieMapa.get(data);
    dados.add(dado);
    }

   Collections.sort(dados);
   Calendar dataInicialSerie= Calendar.getInstance();;
   Calendar dataFinalSerie= Calendar.getInstance();;
   dataInicialSerie.setTime(dados.get(0).getData());
   dataFinalSerie.setTime(dados.get(dados.size()-1).getData());
   serietemp.setDataInicialSerie(dataInicialSerie);
   serietemp.setDataFinalSerie(dataFinalSerie);
   serietemp.setDados(dados); 
   serietemp.setaMapaStrDadoTemporal();
   
   Date dfim=dataFinalSerie.getTime();
    String dataFimstr=formatter.format(dfim);
    serietemp.setDataFinalSerieStr(dataFimstr);
    Date dini=dataInicialSerie.getTime();
      String dataInistr=formatter.format(dini);
      serietemp.setDataInicialSerieStr(dataInistr);
   //vhidAnual.setInvhidro(invhid);
   //vhidAnual.setSerietemporal(serietemp);
   
  }else{
    return null;
  }
  
  
  
  
  
  
  
  
  return serietemp;
  
  
}
}
