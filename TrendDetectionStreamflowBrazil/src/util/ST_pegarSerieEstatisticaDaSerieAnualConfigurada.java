package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.ConfiguraSeries;
import types.DadoTemporal;
import types.InventarioHidrologico;
import types.SerieTemporal;
import types.VariavelHidrologica;

/*import org.snirh.extremos_unb.tipos.DadoTemporal;
import org.snirh.extremos_unb.tipos.InventarioHidrologico;
import org.snirh.extremos_unb.tipos.SerieTemporal;
import org.snirh.extremos_unb.tipos.VariavelHidrologica;
import org.snirh.extremos_unb.util.ConfiguraSeries;
import org.snirh.extremos_unb.util.ST_pegarSerieEstatisticaDaSerieAnualConfigurada;
import org.snirh.extremos_unb.util.ST_verificarAno;*/

public class ST_pegarSerieEstatisticaDaSerieAnualConfigurada {
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
	Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Map<String,DadoTemporal> dados = new HashMap<String,DadoTemporal>();
    Set<String> chaves = mapaStrDadosger.keySet();
    
    int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
	if(ano[0]>ano[1])return dados;
	
	  	for (String data : chaves){
	  		String [] datastr=data.split("/");
	  		int anoatual=Integer.parseInt(datastr[2]);
	  		Double valor = mapaStrDadosger.get(data).getValor();
	  		if((valor != -99999 || valor > 0) && (anoatual >= ano[0]) && (anoatual <= ano[1])){
	  		dados.put(data,mapaStrDadosger.get(data));	
	  		}
	  	}
		
	  	 if(dados.size() >= tammin){
	     	return dados;	
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
	
	
	Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Map<String,DadoTemporal> dados = new HashMap<String,DadoTemporal>();
    Set<String> chaves = mapaStrDadosger.keySet();
    
    int [] ano =ST_verificarAno.anos(st, anoIni, anoFim);
	if(ano[0]>ano[1])return dados;
	
	  	for (String data : chaves){
	  		String [] datastr=data.split("/");
	  		int anoatual=Integer.parseInt(datastr[2]);
	  		Double valor = mapaStrDadosger.get(data).getValor();
	  		if((valor != -99999 || valor > 0) && (anoatual >= ano[0]) && (anoatual <= ano[1])){
	  		dados.put(data,mapaStrDadosger.get(data));	
	  		}
	  	}
		
	  	 if(dados.size() >= tammin){
	     	return dados;	
	     }else{
	     	return null;
	     }
	
}
	
public static VariavelHidrologica serieEstatisticaVhid(ConfiguraSeries configuraSeries,VariavelHidrologica vhid) {
	
	
	VariavelHidrologica vhidAnual=new VariavelHidrologica();
	Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
	serieMapa=ST_pegarSerieEstatisticaDaSerieAnualConfigurada.serieEstatistica(configuraSeries, vhid.getSerietemporal());
	
	
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
	


public static  Map<String,DadoTemporal> serieEstatistica(int tammin,int codEstatistica,int anoIni,int anoFim,Map<String,DadoTemporal> mapaStrDadosger) {
	//Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Map<String,DadoTemporal> dados = new HashMap<String,DadoTemporal>();
    Set<String> chaves = mapaStrDadosger.keySet();
    
    int [] ano =ST_verificarAno.anos(mapaStrDadosger, anoIni, anoFim);
	if(ano[0]>ano[1])return dados;
	
	  	for (String data : chaves){
	  		String [] datastr=data.split("/");
	  		int anoatual=Integer.parseInt(datastr[2]);
	  		Double valor = mapaStrDadosger.get(data).getValor();
	  		if((valor != -99999 || valor > 0) && (anoatual >= ano[0]) && (anoatual <= ano[1])){
	  		dados.put(data,mapaStrDadosger.get(data));	
	  		}
	  	}
		
	  	 if(dados.size() >= tammin){
	     	return dados;	
	     }else{
	     	return null;
	     }
	 
		
	}	
}
