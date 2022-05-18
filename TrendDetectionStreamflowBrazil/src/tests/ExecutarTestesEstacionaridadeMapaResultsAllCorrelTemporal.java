package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import tests.autocorrelationApproaches.MagnitudeTendencia;
import types.DadoTemporal;
import types.ResultEstacionaridade;
import types.ResultadoBsen;
import types.SimulationDataExtremos;
import util.PegarSerieEstatistica;
import util.ST_pegarEstatisticasDescritivasDaSerieAnualConfigurada;
import util.ST_verificarAno;

public class ExecutarTestesEstacionaridadeMapaResultsAllCorrelTemporal {

	
	private SimulationDataExtremos simulationData;
    //private PanelTestesEstatisticos pnt;
	
	public  ExecutarTestesEstacionaridadeMapaResultsAllCorrelTemporal(SimulationDataExtremos simulationData){
		
		this.simulationData=simulationData;
		// this.pnt=pnt;
		
	}
	
public Map<String, Map<String,ResultEstacionaridade>> executarTestes(){
		
		String tiposerie=null;
	    String nometeste=null;
	    double nivelsignificancia=simulationData.getNivelSignificancia();
	    String [] nometeste2=pegarNomeTesteSigla();
	 	String textointerface= "Iniciando cálculo ....";
	    int progress = 0;
        int nestac=this.simulationData.getVariaveisHidr().size();
        //zerar resultados anteriores
        for (int i = 0; i < this.simulationData.getVariaveisHidr().size(); i++) {
       	this.simulationData.getVariaveisHidr().get(i).setResultestacionaridade(null);
        }
        
        ArrayList<double []> valoresCriticos = new ArrayList<double []>();
        ArrayList<ArrayList<Double>> distribTotTestes = new ArrayList<ArrayList<Double>>();
        
        Map<String, ArrayList<ResultEstacionaridade>>resultEstacionaridadeTipo1=new  HashMap<String, ArrayList<ResultEstacionaridade>>();
        Map<String, Map<String,ResultEstacionaridade>> resultEstacionaridadeTipo2=new  HashMap<String, Map<String,ResultEstacionaridade>>();
        
        
		for (int i = 0; i < this.simulationData.getVariaveisHidr().size(); i++) {
			
			Map<String,DadoTemporal> serieMapaOriginal=this.pegarSeriesMapasOriginais(i).get(0);
			String codigo= this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo();
			
			if(this.simulationData.getVariaveisHidr().get(i).isSelecionada()){
			Map<String,ResultEstacionaridade> resultestacionaridadeTipo2=new HashMap<String,ResultEstacionaridade>();
			ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();	
			int tamanhoMinimoSerie=this.simulationData.getTamMinSerietotEstacionaridade();
			int tipoHipotese=this.simulationData.getTipoHipoteseEstacionaridade();
			int tamanhoMinimoSerie1=5;
			int tamanhoMinimoSerie2=5;
			
			    Map<String,DadoTemporal> serieMapaTFPW=new HashMap<String,DadoTemporal>();
			    double CF=1;
		    	boolean autoCorrelacao=this.simulationData.isConsiderarAutoCorrelacao();
		    	//String nomeAbordagemAC="TFPWcunbPW";
		    	//String nomeAbordagemAC="TFPWcunbPW";//MK para nao considerar nenhuma abordagem
		    	if(autoCorrelacao){
		    		String nomeAbordagemAC=this.getApproachName();
		    		SelecionarAbordagemCorrelacaoSerial selAbordagem=new SelecionarAbordagemCorrelacaoSerial();
		    		selAbordagem.executarSerieMapa(nomeAbordagemAC, serieMapaOriginal);
		    		serieMapaTFPW=selAbordagem.getSerieMapaFinal();
		    		CF=selAbordagem.getCF();
		      	}
		    	
		    	
		    	Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
		    	serieMapa=serieMapaOriginal;
		    	
		    	if(serieMapa.size() >= tamanhoMinimoSerie){ 
		    		
		    		
					    this.simulationData.getVariaveisHidr().get(i).setAtendeRestricaoTamMin(true);
					    double bsen=MagnitudeTendencia.bSenRespeitandoCronologia(serieMapa);
				    	DescriptiveStatistics dsc = ST_pegarEstatisticasDescritivasDaSerieAnualConfigurada.dsc(serieMapa);
				    	double media=dsc.getMean();
				    	double nanos=dsc.getN();
				    	//double bsenRel=((bsen/media)*100.0);
				    	double bsenRel=((bsen/media)*100.0)*10.0;//Decada
				    	int [] anoIniFim = ST_verificarAno.anos(serieMapa);
				    	double nanosPeriodo=anoIniFim[1]-anoIniFim[0]+1;
				    	ResultadoBsen resbsen =new ResultadoBsen();
				    	resbsen.setBsen(bsen);
						resbsen.setMedia(media);
						resbsen.setBsenRel(bsenRel);
						resbsen.setNanos(nanos);
						resbsen.setNanosPeriodo(nanosPeriodo);
										
						double bsenRelAnual=((bsen/media)*1.0)*1.0;//Decada
						resbsen.setBsenRelAnual(bsenRelAnual);
						double cv=dsc.getStandardDeviation()/dsc.getMean();
						resbsen.setCv(cv);
						Integer tamanhon =(int) dsc.getN();
						resbsen.setTamanhon(tamanhon);
				    	boolean fazerMK=true;
						//if(this.pnt.getCheckEstacionaridadeTendencia()[0].isSelected()){	
								if(fazerMK){
							    	int opcaoTeste=0;
							    	nometeste=nometeste2[opcaoTeste];
							    	String tipoTeste="TENDENCIA";
							    	ResultEstacionaridade resultest=null;
							    	MannKendallTest mk = new MannKendallTest(serieMapa,tipoHipotese,nivelsignificancia);
					    	        ResultEstacionaridade resultestOriginal = this.executarMK(mk, tiposerie, nometeste, tipoTeste,resbsen);
					    	        resultest = resultestOriginal;
					    	        double pvalori=resultest.getPvalue();
					    	        if(!resultestOriginal.getResultadoteste().equals("NS") && serieMapaTFPW.size() > 0){
					    	          		MannKendallTest mk3 = new MannKendallTest(serieMapaTFPW,tipoHipotese,nivelsignificancia);
						    	        	resultest = this.executarMK(mk3, tiposerie, nometeste, tipoTeste,resbsen,CF);
						    	            System.out.println();
					    	        }
					    	        resultestacionaridadeTipo2.put(nometeste, resultest);
							      	resultestacionaridade.add(resultest);
								}
				    	
						
					
		    	
		    	 resultEstacionaridadeTipo2.put(codigo, resultestacionaridadeTipo2);
				}else{
				this.simulationData.getVariaveisHidr().get(i).setAtendeRestricaoTamMin(false);
				}
		    	
		    	
		    	resultEstacionaridadeTipo1.put(codigo, resultestacionaridade);
				this.simulationData.getVariaveisHidr().get(i).setResultestacionaridade(resultestacionaridade);
				  
				double progress2 = ((i+1)*1.0/(this.simulationData.getVariaveisHidr().size()*1.0))*100;
			    progress=(int) progress2;
			    //setProgress(Math.min(progress, 100));
			    textointerface= "Aguarde..executando  o cálculo da estação  "+(i+1)+"/"+nestac+"";
			      //publish(textointerface);
				System.out.println(textointerface);
		     }else {
		    	 System.out.println("not select gauge");
		     }
		    	
		      //System.out.println("Estac i"+i+" codigo = "+codigo);		    	
		}
         
		
		boolean fazerFDR=this.simulationData.isFazerFDR();
		
		//System.out.println(resultEstacionaridadeTipo2.get("245003").get("MK").getResultadoteste());
				
		if(fazerFDR){
			
			if(this.simulationData.isFazerFDRClassico()){
			AnaliseResultadoFDR analiseResultadoFDR=new AnaliseResultadoFDR(this.simulationData);
			boolean executouFDR=analiseResultadoFDR.executar(resultEstacionaridadeTipo2,nivelsignificancia);
					if(!executouFDR){
						System.out.println("nao teve estação");
					}
			}
		
		}
		
		//System.out.println(resultEstacionaridadeTipo2.get("245003").get("MK").getResultadoteste());
		
		
		return resultEstacionaridadeTipo2;
		
}


public ResultEstacionaridade executarMK(MannKendallTest teste,String tiposerie,String nometeste,String tipoTeste,ResultadoBsen resbsen){
	ResultEstacionaridade resultest = new ResultEstacionaridade(nometeste,tiposerie);
	
	teste.teste1();
	resultest.setEstatteste(teste.getEstatteste());
	resultest.setPvalue(teste.getPvalue()*100.0);
	//resultest.setPvalue(teste.getPvalue());
	resultest.setValorcriticoteste(teste.getValorcriticoteste());
	resultest.setResultadoteste(teste.getResultadoteste());
	//resultest.setTipoTeste("TENDENCIA");
	resultest.setTipoTeste(tipoTeste);
	resultest.setMetodoObterValCritico("Teórico");
	resultest.setSentidoTendencia(teste.getSentidoTendencia());
	resultest.setResultadoDescritivoTeste(teste.getResultesteTexto());
	
	
	//resultest.setSentidoMediaAntiga(teste.getSentidoMediaAnterior());
	//resultest.setSentidoMediaRecente(teste.getSentidoMediaPosterior());
	//resultest.setAnoMudanca(teste.getAnoIniSerie2());
	resultest.setResbsen(resbsen);
	
	return resultest;
	
	
}

public ResultEstacionaridade executarMK(MannKendallTest teste,String tiposerie,
		String nometeste,String tipoTeste,ResultadoBsen resbsen, double CF){
	ResultEstacionaridade resultest = new ResultEstacionaridade(nometeste,tiposerie);
	
	teste.teste1(CF);
	resultest.setEstatteste(teste.getEstatteste());
	resultest.setPvalue(teste.getPvalue()*100.0);
	//resultest.setPvalue(teste.getPvalue());
	resultest.setValorcriticoteste(teste.getValorcriticoteste());
	resultest.setResultadoteste(teste.getResultadoteste());
	//resultest.setTipoTeste("TENDENCIA");
	resultest.setTipoTeste(tipoTeste);
	resultest.setMetodoObterValCritico("Teórico");
	resultest.setSentidoTendencia(teste.getSentidoTendencia());
	resultest.setResultadoDescritivoTeste(teste.getResultesteTexto());
	
	
	//resultest.setSentidoMediaAntiga(teste.getSentidoMediaAnterior());
	//resultest.setSentidoMediaRecente(teste.getSentidoMediaPosterior());
	//resultest.setAnoMudanca(teste.getAnoIniSerie2());
	resultest.setResbsen(resbsen);
	
	return resultest;
	
	
}


public String getApproachName() {
	
	String nomeAbordagemAC="";
	
	if(simulationData.isFazerPW()) {
		nomeAbordagemAC="PW";
	}else if(simulationData.isFazerTFPW()) {
		nomeAbordagemAC="TFPW";
	}else if(simulationData.isFazerMTFPW()) {
		nomeAbordagemAC="TFPWcunbPW";
	}else if(simulationData.isFazerVCPW()) {
		nomeAbordagemAC="VCPW";
	}else if(simulationData.isFazerVC()) {
		nomeAbordagemAC="VC_CF1";
	}
	
	return nomeAbordagemAC;
	
}

private String [] pegarNomeTesteSigla(){
	int ntestes=14;
	String [] nometeste2=new String [ntestes];
	nometeste2[0]="MK";
	nometeste2[1]="SR";
	nometeste2[2]="LR";
	nometeste2[3]="TT";
	nometeste2[4]="DC";
	nometeste2[5]="CD";
	nometeste2[6]="WR";
	nometeste2[7]="MW";
	nometeste2[8]="TF";
	nometeste2[9]="MC";
	nometeste2[10]="TP";
	nometeste2[11]="RD";
	nometeste2[12]="AC";
	nometeste2[13]="WW";
	return nometeste2;
}

private ArrayList<Map<String,DadoTemporal>> pegarSeriesMapasOriginais(int i){
	
	//PanelImportarDados pid=new PanelImportarDados();
	int anoIniserieTot=this.simulationData.getAnoIniSubConjunto();	
	int anoFimserieTot=this.simulationData.getAnoFimSubConjunto();
	ArrayList<Map<String,DadoTemporal>> series=new ArrayList<Map<String,DadoTemporal>>();
	PegarSerieEstatistica pid=new PegarSerieEstatistica();
	Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
	serieMapa=pid.pegarSerieEstatistica(this.simulationData.getVariaveisHidr().get(i),this.simulationData, anoIniserieTot, anoFimserieTot);
	series.add(serieMapa);
	//series.add(serie1Mapa);
	//series.add(serie2Mapa);
	return series;
}
			
}
