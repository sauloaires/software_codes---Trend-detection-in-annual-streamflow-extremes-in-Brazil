package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.DadoTemporal;
import types.ResultOutliers;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;

/*import org.snirh.extremos_unb.deteccao.util.ResultOutliers;
import org.snirh.extremos_unb.tipos.DadoTemporal;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.extremos_unb.tipos.VariavelHidrologica;*/

public class PegarSerieMaxSemOutlier {
public Map<String,DadoTemporal> pegarSerieMaxSemOutlier(VariavelHidrologica vhid,SimulationDataExtremos simulationData,Map<String,DadoTemporal> maximo){
		
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
