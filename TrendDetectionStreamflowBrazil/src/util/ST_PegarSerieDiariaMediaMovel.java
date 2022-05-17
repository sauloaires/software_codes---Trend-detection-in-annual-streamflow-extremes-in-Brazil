package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.DadoTemporal;
import types.SerieTemporal;

/*import org.snirh.extremos_unb.tipos.DadoTemporal;
import org.snirh.extremos_unb.tipos.SerieTemporal;
import org.snirh.extremos_unb.util.Dif;
import org.snirh.extremos_unb.util.MediaMovel;
import org.snirh.extremos_unb.util.ST_PegarSerieTemporalDeDadosTemporais;
import org.snirh.extremos_unb.util.ST_estatisticaDaSerie;
import org.snirh.extremos_unb.util.ST_pegarDadosCriterioFalha;
import org.snirh.extremos_unb.util.ST_pegarDataFinalFiltroMes;
import org.snirh.extremos_unb.util.ST_pegarDataInicioFiltroMes;
import org.snirh.extremos_unb.util.ST_pegarDataMesFinalFiltroMes;
import org.snirh.extremos_unb.util.ST_valoresDiariosIntervaloDataSemFalhaMapa;
import org.snirh.extremos_unb.util.ST_verificarAno;
import org.snirh.extremos_unb.util.ST_verificarNdiasFevereiro;*/

public class ST_PegarSerieDiariaMediaMovel {
	private int tamMM;
	private int tipoSerie;
	private double tolFalha;
	private int mesIni;
	private int mesFim;
	private int anoIni;
	private int anoFim;
	private SerieTemporal st;
	
	
	
	
	public ST_PegarSerieDiariaMediaMovel(int tamMM,int tipoSerie,double tolFalha,int mesIni, int mesFim,int anoIni,int anoFim,SerieTemporal st){
	    this.tamMM=tamMM;
	    this.tipoSerie=tipoSerie;
	    this.tolFalha=tolFalha;
	    this.mesIni=mesIni;
	    this.mesFim=mesFim;
	    this.anoIni=anoIni;
	    this.anoFim=anoFim;
	    this.st=st;
	
     }
	
	
	public ST_PegarSerieDiariaMediaMovel(int tamMM,int tipoSerie,double tolFalha,int mesIni, int mesFim,SerieTemporal st){
	    this.tamMM=tamMM;
	    this.tipoSerie=tipoSerie;
	    this.tolFalha=tolFalha;
	    this.mesIni=mesIni;
	    this.mesFim=mesFim;
	    this.anoIni=-99999;
	    this.anoFim=-99999;
	    this.st=st;
	}
	
	
	public ST_PegarSerieDiariaMediaMovel(int tamMM,int tipoSerie,double tolFalha,SerieTemporal st){
	    this.tamMM=tamMM;
	    this.tipoSerie=tipoSerie;
	    this.tolFalha=tolFalha;
	    this.mesIni=1;
	    this.mesFim=12;
	    this.anoIni=-99999;
	    this.anoFim=-99999;
	    this.st=st;
	}
	
	
	public ST_PegarSerieDiariaMediaMovel(int tamMM,SerieTemporal st){
	    this.tamMM=tamMM;
	    this.tipoSerie=1;//sem falha SF
	    this.tolFalha=0.0;
	    this.mesIni=1;
	    this.mesFim=12;
	    this.anoIni=-99999;
	    this.anoFim=-99999;
	    this.st=st;
	}
	
	
	
	public ST_PegarSerieDiariaMediaMovel(int tamMM,int tipoSerie,double tolFalha,Map<String,DadoTemporal> dadosMapa){
	    this.tamMM=tamMM;
	    this.tipoSerie=tipoSerie;
	    this.tolFalha=tolFalha;
	    this.mesIni=1;
	    this.mesFim=12;
	    this.anoIni=-99999;
	    this.anoFim=-99999;
	     this.st=ST_PegarSerieTemporalDeDadosTemporais.st(dadosMapa);
	    }
	
	
	
	public ST_PegarSerieDiariaMediaMovel(int tamMM,Map<String,DadoTemporal> dadosMapa){
	    this.tamMM=tamMM;
	    this.tipoSerie=1;//sem falha SF
	    this.tolFalha=0.0;
	    this.mesIni=1;
	    this.mesFim=12;
	    this.anoIni=-99999;
	    this.anoFim=-99999;
	     this.st=ST_PegarSerieTemporalDeDadosTemporais.st(dadosMapa);
	    }
	
	
	

	
	public Map<String,DadoTemporal> executar(){
		
		
		    int codEstatistica=6;//minimas
		    Map<String,DadoTemporal> mapaStrDadosger=this.st.getMapaStrDados();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();	
			int [] ano =ST_verificarAno.anos(this.st, this.anoIni, this.anoFim);
			if(ano[0]>ano[1])return dadosEstatistica;
			Date utilDateIni =ST_pegarDataInicioFiltroMes.data(this.st, this.mesIni, this.mesFim,ano[0],ano[1]);
			Date utilDateIniMesFim =ST_pegarDataMesFinalFiltroMes.data(this.st, this.mesIni, this.mesFim, ano[0],ano[1]);
			Date utilDateFim = ST_pegarDataFinalFiltroMes.data(this.st,this.mesIni, this.mesFim,ano[0],ano[1]);
			
			Calendar clStart =Calendar.getInstance();
			Calendar clEnd =Calendar.getInstance();
			Calendar clStartfim =Calendar.getInstance();
			
			clEnd.setTime(utilDateFim);
			clStart.setTime(utilDateIni);
			clStartfim.setTime(utilDateIniMesFim);
				
			int ndiasSemFalha=Dif.dias(utilDateIni, utilDateIniMesFim)+1;
			double toleranciaMaxFalha=this.tolFalha;
			Map<String,DadoTemporal> dados_anual;
			Map<String,DadoTemporal> dados_filtro;
			Map<String,DadoTemporal> dados_anual_comFalha;
			ArrayList<String> serieData = new ArrayList<String>();
			while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
			        dados_anual=new HashMap<String,DadoTemporal>();
		 	        dados_anual=ST_valoresDiariosIntervaloDataSemFalhaMapa.serieDiaria(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
		 	        
		 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
		 	        //dados_anual_comFalha=valoresIntervaloDataComFalhaMapa(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
		 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, this.tipoSerie, toleranciaMaxFalha, percentualDeFalhas); 
		 	       
		 	        
		 	       Double valorEstatistica=-99999.0;
		 	       DadoTemporal valorEstDT = null;
			         
			         
			        if(dados_filtro.size() > 0){
		 	        ArrayList<Double> serie=new ArrayList<Double>(); 
		 	        Set<String> chaves =  dados_filtro.keySet();
		 	        double [] valor=new double[chaves.size()];
		 	        String [] datas=new String[chaves.size()];
		 	       	int i1=0;
		 	       ArrayList<DadoTemporal> dadostemp=new ArrayList<DadoTemporal>();
		 	        for (String data : chaves){
				  	DadoTemporal dado = dados_filtro.get(data);
				  	dadostemp.add(dado);
				  	}
		 	       Collections.sort(dadostemp); 
		 	      for (int j=0;j<dadostemp.size();j++){
		 	       	 valor[j]=dadostemp.get(j).getValor();
					 datas[j]=formatter.format(dadostemp.get(j).getData());  
		 	       }
		 	      	        
		 	    	MediaMovel mm = new  MediaMovel(valor,this.tamMM,datas);
		 	  		double [] serieMM = mm.calcmedia();
		 	  		ArrayList<String> datasMM = mm.getDatamovel();
		 	        Map<String,DadoTemporal> dadosMMmapa = new HashMap<String,DadoTemporal>();
		 	  		
		 	  		for (int j=0;j<datasMM.size();j++){
		 	  			DadoTemporal dado = new DadoTemporal();
		 	  			dado.setValor(serieMM [j]);
		 	  			Date dini;
						try {
							dini = formatter.parse(datasMM.get(j));
							dado.setData(dini);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		 	  			
						dadosMMmapa.put(datasMM.get(j), dado);	
						
						
		 	  		}
		 	  		
		 	  		System.out.println("Media Movel = "+this.tamMM);
		 	  		valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dadosMMmapa);
		 	  		valorEstatistica=valorEstDT.getValor();
		 	  		}
			        
			        
		 	        String datastr =formatter.format(utilDateIni);
			     	if(valorEstatistica != -99999.0){
			     	DadoTemporal dt = new DadoTemporal();
				     	if(valorEstDT.getData()==null){
				     		dt.setData(utilDateIni);	
				     	}else{
				     		datastr =formatter.format(valorEstDT.getData());
				     		dt.setData(valorEstDT.getData());
				     	}
			     	dt.setValor(valorEstatistica);
			     	dadosEstatistica.put(datastr,dt);
			     	serieData.add(datastr);
			     	}
			     	
			     	clStart.add(Calendar.YEAR, 1);
					clStartfim.add(Calendar.YEAR, 1);
					utilDateIni=clStart.getTime();
					utilDateIniMesFim=clStartfim.getTime();
							
					if(this.mesFim == 2){
					utilDateIniMesFim=ST_verificarNdiasFevereiro.verificarNdiasFevereiro(utilDateIniMesFim, this.mesFim);
					}
					ndiasSemFalha=Dif.dias(utilDateIni, utilDateIniMesFim)+1;
			}
			
			
				dados_anual=new HashMap<String,DadoTemporal>();
				dados_anual=ST_valoresDiariosIntervaloDataSemFalhaMapa.serieDiaria(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
				double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
				
				
				//dados_anual_comFalha=valoresIntervaloDataComFalhaMapa(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
	 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, this.tipoSerie, toleranciaMaxFalha, percentualDeFalhas); 
	 	       
	 	        
	 	       Double valorEstatistica=-99999.0;
	 	       DadoTemporal valorEstDT = null;
		         
		         
		        if(dados_filtro.size() > 0){ 
				
				
				 ArrayList<Double> serie=new ArrayList<Double>(); 
		 	        Set<String> chaves =  dados_filtro.keySet();
		 	        double [] valor=new double[chaves.size()];
		 	        String [] datas=new String[chaves.size()];
		 	       	int i1=0;
		 	       ArrayList<DadoTemporal> dadostemp=new ArrayList<DadoTemporal>();
		 	        for (String data : chaves){
				  	DadoTemporal dado = dados_filtro.get(data);
				  	dadostemp.add(dado);
				  	}
		 	       Collections.sort(dadostemp); 
		 	      for (int j=0;j<dadostemp.size();j++){
		 	       	 valor[j]=dadostemp.get(j).getValor();
					 datas[j]=formatter.format(dadostemp.get(j).getData());  
		 	       }
		 	      	        
		 	    	MediaMovel mm = new  MediaMovel(valor,this.tamMM,datas);
		 	  		double [] serieMM = mm.calcmedia();
		 	  		ArrayList<String> datasMM = mm.getDatamovel();
		 	        Map<String,DadoTemporal> dadosMMmapa = new HashMap<String,DadoTemporal>();
		 	  		
		 	  		for (int j=0;j<datasMM.size();j++){
		 	  			DadoTemporal dado = new DadoTemporal();
		 	  			dado.setValor(serieMM [j]);
		 	  			Date dini;
						try {
							dini = formatter.parse(datasMM.get(j));
							dado.setData(dini);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		 	  			
						dadosMMmapa.put(datasMM.get(j), dado);	
				    }
				valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dadosMMmapa);
	 	        valorEstatistica=valorEstDT.getValor();
		        }
		        
		        
	 	        String datastr =formatter.format(utilDateIni);
		     	if(valorEstatistica != -99999.0){
		     	DadoTemporal dt = new DadoTemporal();
			     	if(valorEstDT.getData()==null){
			     		dt.setData(utilDateIni);	
			     	}else{
			     		datastr =formatter.format(valorEstDT.getData());
			     		dt.setData(valorEstDT.getData());
			     	}
		     	dt.setValor(valorEstatistica);
		     	dadosEstatistica.put(datastr,dt);
		     	serieData.add(datastr);
		     	}
			     //this.setSerieDatas(serieData);
			    return dadosEstatistica;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public static Map<String,DadoTemporal> executar(int tamMM,int tipoSerie,double tolFalha,int mesIni, int mesFim,int anoIni,int anoFim,SerieTemporal st) {
	    
	    int codEstatistica=6;//minimas
	    Map<String,DadoTemporal> mapaStrDadosger=st.getMapaStrDados();
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();	
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
			
		int ndiasSemFalha=Dif.dias(utilDateIni, utilDateIniMesFim)+1;
		double toleranciaMaxFalha=tolFalha;
		Map<String,DadoTemporal> dados_anual;
		Map<String,DadoTemporal> dados_filtro;
		Map<String,DadoTemporal> dados_anual_comFalha;
		ArrayList<String> serieData = new ArrayList<String>();
		while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
		        dados_anual=new HashMap<String,DadoTemporal>();
	 	        dados_anual=ST_valoresDiariosIntervaloDataSemFalhaMapa.serieDiaria(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
	 	        
	 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
	 	        //dados_anual_comFalha=valoresIntervaloDataComFalhaMapa(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
	 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas); 
	 	       
	 	        
	 	       Double valorEstatistica=-99999.0;
	 	       DadoTemporal valorEstDT = null;
		         
		         
		        if(dados_filtro.size() > 0){
	 	        ArrayList<Double> serie=new ArrayList<Double>(); 
	 	        Set<String> chaves =  dados_filtro.keySet();
	 	        double [] valor=new double[chaves.size()];
	 	        String [] datas=new String[chaves.size()];
	 	       	int i1=0;
	 	       ArrayList<DadoTemporal> dadostemp=new ArrayList<DadoTemporal>();
	 	        for (String data : chaves){
			  	DadoTemporal dado = dados_filtro.get(data);
			  	dadostemp.add(dado);
			  	}
	 	       Collections.sort(dadostemp); 
	 	      for (int j=0;j<dadostemp.size();j++){
	 	       	 valor[j]=dadostemp.get(j).getValor();
				 datas[j]=formatter.format(dadostemp.get(j).getData());  
	 	       }
	 	      	        
	 	    	MediaMovel mm = new  MediaMovel(valor,tamMM,datas);
	 	  		double [] serieMM = mm.calcmedia();
	 	  		ArrayList<String> datasMM = mm.getDatamovel();
	 	        Map<String,DadoTemporal> dadosMMmapa = new HashMap<String,DadoTemporal>();
	 	  		
	 	  		for (int j=0;j<datasMM.size();j++){
	 	  			DadoTemporal dado = new DadoTemporal();
	 	  			dado.setValor(serieMM [j]);
	 	  			Date dini;
					try {
						dini = formatter.parse(datasMM.get(j));
						dado.setData(dini);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 	  			
					dadosMMmapa.put(datasMM.get(j), dado);	
					
					
	 	  		}
	 	  		
	 	  		System.out.println("Media Movel = "+tamMM);
	 	  		valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dadosMMmapa);
	 	  		valorEstatistica=valorEstDT.getValor();
	 	  		}
		        
		        
	 	        String datastr =formatter.format(utilDateIni);
		     	if(valorEstatistica != -99999.0){
		     	DadoTemporal dt = new DadoTemporal();
			     	if(valorEstDT.getData()==null){
			     		dt.setData(utilDateIni);	
			     	}else{
			     		datastr =formatter.format(valorEstDT.getData());
			     		dt.setData(valorEstDT.getData());
			     	}
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
			
			
			//dados_anual_comFalha=valoresIntervaloDataComFalhaMapa(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
 	        dados_filtro =ST_pegarDadosCriterioFalha.mapa(dados_anual, tipoSerie, toleranciaMaxFalha, percentualDeFalhas); 
 	       
 	        
 	       Double valorEstatistica=-99999.0;
 	       DadoTemporal valorEstDT = null;
	         
	         
	        if(dados_filtro.size() > 0){ 
			
			
			 ArrayList<Double> serie=new ArrayList<Double>(); 
	 	        Set<String> chaves =  dados_filtro.keySet();
	 	        double [] valor=new double[chaves.size()];
	 	        String [] datas=new String[chaves.size()];
	 	       	int i1=0;
	 	       ArrayList<DadoTemporal> dadostemp=new ArrayList<DadoTemporal>();
	 	        for (String data : chaves){
			  	DadoTemporal dado = dados_filtro.get(data);
			  	dadostemp.add(dado);
			  	}
	 	       Collections.sort(dadostemp); 
	 	      for (int j=0;j<dadostemp.size();j++){
	 	       	 valor[j]=dadostemp.get(j).getValor();
				 datas[j]=formatter.format(dadostemp.get(j).getData());  
	 	       }
	 	      	        
	 	    	MediaMovel mm = new  MediaMovel(valor,tamMM,datas);
	 	  		double [] serieMM = mm.calcmedia();
	 	  		ArrayList<String> datasMM = mm.getDatamovel();
	 	        Map<String,DadoTemporal> dadosMMmapa = new HashMap<String,DadoTemporal>();
	 	  		
	 	  		for (int j=0;j<datasMM.size();j++){
	 	  			DadoTemporal dado = new DadoTemporal();
	 	  			dado.setValor(serieMM [j]);
	 	  			Date dini;
					try {
						dini = formatter.parse(datasMM.get(j));
						dado.setData(dini);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 	  			
					dadosMMmapa.put(datasMM.get(j), dado);	
			    }
			valorEstDT=ST_estatisticaDaSerie.estatCod(codEstatistica,dadosMMmapa);
 	        valorEstatistica=valorEstDT.getValor();
	        }
	        
	        
 	        String datastr =formatter.format(utilDateIni);
	     	if(valorEstatistica != -99999.0){
	     	DadoTemporal dt = new DadoTemporal();
		     	if(valorEstDT.getData()==null){
		     		dt.setData(utilDateIni);	
		     	}else{
		     		datastr =formatter.format(valorEstDT.getData());
		     		dt.setData(valorEstDT.getData());
		     	}
	     	dt.setValor(valorEstatistica);
	     	dadosEstatistica.put(datastr,dt);
	     	serieData.add(datastr);
	     	}
		     //this.setSerieDatas(serieData);
		    return dadosEstatistica;

	}
	
}
