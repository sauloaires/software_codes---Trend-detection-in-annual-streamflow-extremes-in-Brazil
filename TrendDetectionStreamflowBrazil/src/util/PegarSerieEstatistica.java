package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



import types.DadoTemporal;
import types.ResultOutliers;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;


public class PegarSerieEstatistica {

	public  Map<String,DadoTemporal> pegarSerieEstatistica(VariavelHidrologica vhid,SimulationDataExtremos simulationData,int anoIni,int anoFim)  {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Map<String,DadoTemporal> estatistica = new HashMap<String,DadoTemporal>();
			
			String tipoSeriestr=simulationData.getTipoSerieHidroMaximos();
			int tipoSerie=simulationData.getTipoSerieFalhaEstacionaridade();
			double tolFalha=simulationData.getTolFalhaMax();
			int mesIni=simulationData.getMesIniEstacionaridade();
			int mesFim=simulationData.getMesFimEstacionaridade();
			int tamanhoMinimoSerie=simulationData.getTamMinSerietotEstacionaridade();
			int codEstatistica=simulationData.getCodEstatisticaSelecionadaEstacionaridade();
			
			
			
		    Map<String,DadoTemporal> mapaStrDadosger=vhid.getSerietemporal().getMapaStrDados();
			String disctemp=vhid.getInvhidro().getDiscretiza��oTempor�ria();
			if(disctemp.equals("D") || disctemp.equals("DIARIO") || disctemp.equals("Diario")){
				
				if(simulationData.getTipoEstatisticaSelecionadaEstacionaridade().equals("PERMANENCIA")){
					double percentil=simulationData.getPercentil();	
					
					//estatistica=vhid.getSerietemporal().serieDiariaPermanenciaSazonalAnualMapa(percentil,tipoSerie, tolFalha, codEstatistica, mesIni, mesFim,anoIni,anoFim, mapaStrDadosger);
					estatistica=ST_PegarSerieDiariaPermanencia.executar(percentil, tipoSerie, tolFalha, codEstatistica, mesIni, mesFim, anoIni, anoFim, vhid.getSerietemporal());
					
				}else if(simulationData.getTipoEstatisticaSelecionadaEstacionaridade().equals("MMmin")){
					int tamMM=simulationData.getTamanhoMediaMovelMin();
					//estatistica=vhid.getSerietemporal().serieDiariaMediaMovelSazonalAnualMapa(tamMM,tipoSerie, tolFalha, codEstatistica, mesIni, mesFim,anoIni,anoFim, mapaStrDadosger);
					estatistica=ST_PegarSerieDiariaMediaMovel.executar(tamMM, tipoSerie, tolFalha, mesIni, mesFim, anoIni, anoFim,vhid.getSerietemporal());
				}else if(simulationData.getTipoEstatisticaSelecionadaEstacionaridade().equals("NDlim")){
					double valorLimiar=simulationData.getValorLimiar();
					String sentido="MAIOR";
					
					//estatistica=vhid.getSerietemporal().serieDiariaNdiasSazonalAnualMapa(valorLimiar,sentido,tipoSerie, tolFalha, codEstatistica, mesIni, mesFim,anoIni,anoFim, mapaStrDadosger);
					estatistica=ST_serieDiariaNdiasSazonalAnualMapa.executar(valorLimiar, sentido, tipoSerie, tolFalha, codEstatistica, mesIni, mesFim, anoIni, anoFim, vhid.getSerietemporal());
					
				}else{
					//estatistica=vhid.getSerietemporal().serieDiariaEstatisticaSazonalAnualMapa(tipoSerie, tolFalha, codEstatistica, mesIni, mesFim,anoIni,anoFim, mapaStrDadosger);
					int tammin=0;
					//int tammin=tamanhoMinimoSerie;
					estatistica=ST_pegarSerieEstatisticaDaSerieDiariaConfigurada.serieEstatistica(tammin, tipoSerie, tolFalha, codEstatistica, mesIni, mesFim, anoIni, anoFim, vhid.getSerietemporal());
				}
					
					
			}else if(disctemp.equals("M") || disctemp.equals("MENSAL") || disctemp.equals("Mensal")){
				if(simulationData.getTipoEstatisticaSelecionadaEstacionaridade().equals("PERMANENCIA")){
					double percentil=simulationData.getPercentil();
					//estatistica=vhid.getSerietemporal().serieMensalPermanenciaSazonalAnualMapa(percentil,tipoSerie, tolFalha, codEstatistica, mesIni, mesFim,anoIni,anoFim, mapaStrDadosger);
					
				}else{
					int tammin=1;
					//estatistica=vhid.getSerietemporal().serieMensalEstatisticaSazonalAnualMapa(tipoSerie, tolFalha, codEstatistica, mesIni, mesFim,anoIni,anoFim, mapaStrDadosger);	
					estatistica=ST_pegarSerieEstatisticaDaSerieMensalConfigurada.serieEstatistica(tammin, tipoSerie, tolFalha, codEstatistica, mesIni, mesFim, anoIni, anoFim, vhid.getSerietemporal());
				}
	    	}else if(disctemp.equals("A") || disctemp.equals("ANUAL")) {
				//estatistica = vhid.getSerietemporal().serieAnualSemFalhaMapa(anoIni,anoFim, mapaStrDadosger);	
				//int tammin=tamanhoMinimoSerie;
	    		int tammin=1;
				estatistica=ST_pegarSerieEstatisticaDaSerieAnualConfigurada.serieEstatistica(tammin, codEstatistica, anoIni, anoFim, mapaStrDadosger);
			}
				
			// Verificar Outlier e remover os selecionados;
			//if(estatistica == null){
			Map<String,DadoTemporal> estatisticaSemOutlier =this.pegarSerieMaxSemOutlier(vhid, simulationData,estatistica);
			Map<String,DadoTemporal> estatisticaFinal = new HashMap<String,DadoTemporal>();
			estatisticaFinal =estatisticaSemOutlier;
			//}
			
			boolean derivMediaMovel=false;
			if(derivMediaMovel){
					Set<String> chaves =  estatisticaSemOutlier.keySet();
				    double [] valor=new double[chaves.size()];
				    String [] datas=new String[chaves.size()];
					ArrayList<DadoTemporal> dadostemp=new ArrayList<DadoTemporal>();
				    for (String data : chaves){
				  	DadoTemporal dado = estatisticaSemOutlier.get(data);
				  	dadostemp.add(dado);
				  	}
				    Collections.sort(dadostemp); 
				    for (int j=0;j<dadostemp.size();j++){
				    valor[j]=dadostemp.get(j).getValor();
				    datas[j]=formatter.format(dadostemp.get(j).getData());  
				    }
				    
				    int tamMM=simulationData.getTamanhoMediaMovel(); 	        
				    MediaMovel mm = new  MediaMovel(valor,tamMM,datas);
				  	double [] serieMM = mm.calcmedia();
				  	ArrayList<String> datasMM = mm.getDatamovel();
				   
				  		
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
				  			
						estatisticaFinal.put(datasMM.get(j), dado);	
						
						
				  		}
			
			
			}
			
			
			
			
			
			
			
			boolean normalizar=false;
			if(normalizar){
			
				//ArrayList <Double> valores=vhid.getSerietemporal().pegarArrayListValores(estatisticaFinal);
				//ArrayList <String> datas=vhid.getSerietemporal().pegarArrayListDatas(estatisticaFinal);
				//double [] valvetor=vhid.getSerietemporal().obterVetorDeArrayTipo2(valores);
				//BoxCox bc = new BoxCox(valvetor);	
				//double [] dadosTransformados=bc.transformedData();
				//ArrayList <Double> valTransformados=vhid.getSerietemporal().obterArraydeVetor(dadosTransformados);
				//Map<String,DadoTemporal> maximoSemOutlierNormalizado=vhid.getSerietemporal().criarMapaDadoTemporal(datas, valTransformados);
				//estatisticaFinal=maximoSemOutlierNormalizado;
			}
			
			
			
			
			
			
			
			
			return estatisticaFinal;
		}	
	
private Map<String,DadoTemporal> pegarSerieMaxSemOutlier(VariavelHidrologica vhid,SimulationDataExtremos simulationData,Map<String,DadoTemporal> maximo){
		
		Map<String,DadoTemporal> maximoSemOutlier = new HashMap<String,DadoTemporal>();
		Set<String> chaves =   maximo.keySet();
		
		
	  	for (String data : chaves){
	  	DadoTemporal dado = maximo.get(data);
	  	DadoTemporal dadoSemOut =new DadoTemporal();
	  	dadoSemOut.setData(dado.getData());
	  	dadoSemOut.setValor(dado.getValor());
	   	maximoSemOutlier.put(data, dadoSemOut);
	  	}
		
		
	  	
	  	if(simulationData.getResultOutlierMapa() != null) {
	  		
	  	if(simulationData.getResultOutlierMapa().isEmpty() != true){
	  	
	  	Set<String> chavesResOut =  simulationData.getResultOutlierMapa().keySet();
		
			  	for (String cod :  chavesResOut){
			   	ResultOutliers rout = simulationData.getResultOutlierMapa().get(cod);
			  	/**
			  	 * Verificar se o usuario optou por excluir um eventual outlier 
			  	 */
					  	  if(rout.isExcluirSerie()){
					  		/**
					  		 * Verificar se o cÃ³digo deste outlier que se quer excluir Ã© igual ao da estaÃ§Ã£o em anÃ¡lise
					  		 */
					      	if(rout.getCodigo().equals(vhid.getInvhidro().getEstacao_codigo())){
								/**
					  			 * por fim verificar se de fato foram utilizadas as mesmas configuraÃ§Ãµes para seleÃ§Ã£o da sÃ©rie de mÃ¡ximos e anÃ¡lise de outliers
					  			 */
					  			String codigoOutlierMapa=this.pegarCodigoMapaResultOutlier(simulationData, rout.getCodigo(), rout.getDatastr(), rout.getMetodo());
						  		if(simulationData.getResultOutlierMapa().containsKey(codigoOutlierMapa)){
					  			maximoSemOutlier.remove(rout.getDatastr());
					  			}
					  		}
					  	 }
			  	
			  	
			  	}
	  	
	  	}
	  	
	  	}
		return maximoSemOutlier;
		
	}

public String pegarCodigoMapaResultOutlier(SimulationDataExtremos simulationData,String codigo,String dataout, String metodo)  {
	String config=this.configSerieMaximos(simulationData);
	String codigoOutlierMapa=codigo+"-"+dataout+"-"+metodo+"-"+config;
	return codigoOutlierMapa;
	
	
	
}

public String configSerieMaximos(SimulationDataExtremos simulationData)  {
	
	String config=new String();
	String tipoSeriestr=simulationData.getTipoSerieHidroMaximos();
	double tolFalha=simulationData.getTolFalhaMax();
	int mesIni=simulationData.getMesIniEstacionaridade();
	int mesFim=simulationData.getMesFimEstacionaridade();
	int tamanhoMinimoSerie=simulationData.getTamMinSerietotEstacionaridade();
	int anoIni=simulationData.getAnoIniSerie1Estacionaridade();
    int anoFim=simulationData.getAnoFimSerie1Estacionaridade();
	
	config=tipoSeriestr+"-"+tolFalha+"-"+mesIni+"-"+anoIni+"-"+"-"+mesFim+"-"+anoFim+"-"+tamanhoMinimoSerie;

	return config;
	
}
}
