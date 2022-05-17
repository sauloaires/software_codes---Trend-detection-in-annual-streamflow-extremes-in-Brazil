package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.ResultEstacionaridade;
import types.ResultadoFDR;
import types.SimulationDataExtremos;
import util.Esco7;
import util.PegarResultadosEstacionaridadeMapa;

/*import org.snirh.extremos_unb.deteccao.testes.ResultEstacionaridade;
import org.snirh.extremos_unb.deteccao.testes.ResultadoFDR;
import org.snirh.extremos_unb.deteccao.util.PegarResultadosEstacionaridadeMapa;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.util.sort.Esco7;*/

public class DefinirFDR {
	private SimulationDataExtremos simulationData;
	private double nsCorrigido;
	
	public DefinirFDR(SimulationDataExtremos simulationData){
		
		this.simulationData= simulationData;
	}
	
	
	
	public ArrayList<ResultadoFDR> definirResultadosFDREstacaoPorTeste(String nomeTeste, double nsig){
		
		//HashMap<String, ResultadoFDR> resultadoFDR=new HashMap<String, ResultadoFDR>();
		HashMap<String,ResultEstacionaridade> resultEstacTesteEstacao =PegarResultadosEstacionaridadeMapa.executar(this.simulationData, nomeTeste);
		ArrayList<ResultadoFDR>resultadoFDR=new ArrayList<ResultadoFDR>();
		
		ArrayList<String> codigos=new ArrayList<String>();
		ArrayList<Double> pvalues=new ArrayList<Double>();
		
		Set<String> chaves = resultEstacTesteEstacao.keySet();
	  	for (String codigo : chaves){
	  		codigos.add(codigo);
	  		pvalues.add(resultEstacTesteEstacao.get(codigo).getPvalue());
	  	}
			  	
	  	ArrayList<Integer> indArrayNovo=Esco7.ordenar(pvalues);
	  	int iordem=1;
	  	int n=indArrayNovo.size();
	  	double nsCorrigido=nsig;
	  	for(int i=0;i<indArrayNovo.size();i++){
	  		ResultadoFDR resfdr=new ResultadoFDR();
	  		
	  		String codigo=codigos.get(indArrayNovo.get(i));
	  		double pvalue=pvalues.get(indArrayNovo.get(i));
	  		double q=nsig*iordem/n;
	  		String resTeste="NS";
	  		if(pvalue < q){
	  			nsCorrigido=q;
	  			resTeste="S";
	  		}
	  		boolean falsoPositivo=false;
	  		if(pvalue < nsig && resTeste.equals("NS")){
	  			falsoPositivo=true;
	  		}
	  		
	  		resfdr.setCodigo(codigo);
	  		resfdr.setOrdemi(iordem);
	  		resfdr.setPvalue(pvalue);
	  		resfdr.setQ(q);
	  		resfdr.setResultFDR(resTeste);
	  		resfdr.setFalsoPositivo(falsoPositivo);
	  		resfdr.setResbsen(resultEstacTesteEstacao.get(codigo).getResbsen());
	  		
	  		
	  		resultadoFDR.add(resfdr);
	  		iordem++;	
	  	}
	  	
	  	this.setNsCorrigido(nsCorrigido);
	  	
	  	
		return resultadoFDR;
	  	
	  	
	}

public HashMap<String, ResultadoFDR> definirResultadosFDREstacaoPorTesteMapa(String nomeTeste, double nsig){
		
		HashMap<String, ResultadoFDR> resultadoFDR=new HashMap<String, ResultadoFDR>();
		HashMap<String,ResultEstacionaridade> resultEstacTesteEstacao =PegarResultadosEstacionaridadeMapa.executar(this.simulationData, nomeTeste);
		//ArrayList<ResultadoFDR>resultadoFDR=new ArrayList<ResultadoFDR>();
		
		ArrayList<String> codigos=new ArrayList<String>();
		ArrayList<Double> pvalues=new ArrayList<Double>();
		
		Set<String> chaves = resultEstacTesteEstacao.keySet();
	  	for (String codigo : chaves){
	  		codigos.add(codigo);
	  		pvalues.add(resultEstacTesteEstacao.get(codigo).getPvalue());
	  	}
	  	ArrayList<Integer> indArrayNovo=new ArrayList<Integer>();
	  	if(pvalues.size() > 1){
	  	indArrayNovo=Esco7.ordenar(pvalues);
	  	}else{
	  		indArrayNovo.add(0);	
	  	}
	  	int iordem=1;
	  	int n=indArrayNovo.size();
	  	double nsCorrigido=nsig;
	  	for(int i=0;i<indArrayNovo.size();i++){
	  		ResultadoFDR resfdr=new ResultadoFDR();
	  		
	  		String codigo=codigos.get(indArrayNovo.get(i));
	  		double pvalue=pvalues.get(indArrayNovo.get(i));
	  		double q=nsig*iordem/n;
	  		String resTeste="NS";
	  		if(pvalue < q){
	  			nsCorrigido=q;
	  			resTeste="S";
	  		}
	  		boolean falsoPositivo=false;
	  		if(pvalue < nsig && resTeste.equals("NS")){
	  			falsoPositivo=true;
	  		}
	  		
	  		resfdr.setCodigo(codigo);
	  		resfdr.setOrdemi(iordem);
	  		resfdr.setPvalue(pvalue);
	  		resfdr.setQ(q);
	  		resfdr.setResultFDR(resTeste);
	  		resfdr.setFalsoPositivo(falsoPositivo);
	  		resfdr.setResbsen(resultEstacTesteEstacao.get(codigo).getResbsen());
	  		
	  		
	  		resultadoFDR.put(codigo, resfdr);
	  		iordem++;	
	  	}
	  	
	  	this.setNsCorrigido(nsCorrigido);
	  	
	  	
		return resultadoFDR;
	  	
	  	
	}





public HashMap<String, ResultadoFDR> definirResultadosFDREstacaoPorTesteMapa(Map<String, Map<String,ResultEstacionaridade>> resultEstacionaridadeTipo2,String nomeTeste,double nsig){
	
	HashMap<String, ResultadoFDR> resultadoFDR=new HashMap<String, ResultadoFDR>();
	HashMap<String,ResultEstacionaridade> resultEstacTesteEstacao =PegarResultadosEstacionaridadeMapa.executar(resultEstacionaridadeTipo2, nomeTeste);
	//ArrayList<ResultadoFDR>resultadoFDR=new ArrayList<ResultadoFDR>();
	
	ArrayList<String> codigos=new ArrayList<String>();
	ArrayList<Double> pvalues=new ArrayList<Double>();
	
	Set<String> chaves = resultEstacTesteEstacao.keySet();
  	for (String codigo : chaves){
  		codigos.add(codigo);
  		pvalues.add(resultEstacTesteEstacao.get(codigo).getPvalue());
  	}
  	ArrayList<Integer> indArrayNovo=new ArrayList<Integer>();
  	if(pvalues.size() > 1){
  	indArrayNovo=Esco7.ordenar(pvalues);
  	}else{
  		indArrayNovo.add(0);	
  	}
  	int iordem=1;
  	int n=indArrayNovo.size();
  	double nsCorrigido=nsig;
  	for(int i=0;i<indArrayNovo.size();i++){
  		ResultadoFDR resfdr=new ResultadoFDR();
  		
  		String codigo=codigos.get(indArrayNovo.get(i));
  		double pvalue=pvalues.get(indArrayNovo.get(i));
  		double q=nsig*iordem/n;
  		String resTeste="NS";
  		if(pvalue < q){
  			nsCorrigido=q;
  			resTeste="S";
  		}
  		boolean falsoPositivo=false;
  		if(pvalue < nsig && resTeste.equals("NS")){
  			falsoPositivo=true;
  		}
  		
  		resfdr.setCodigo(codigo);
  		resfdr.setOrdemi(iordem);
  		resfdr.setPvalue(pvalue);
  		resfdr.setQ(q);
  		resfdr.setResultFDR(resTeste);
  		resfdr.setFalsoPositivo(falsoPositivo);
  		resfdr.setResbsen(resultEstacTesteEstacao.get(codigo).getResbsen());
  		
  		
  		resultadoFDR.put(codigo, resfdr);
  		iordem++;	
  	}
  	
  	this.setNsCorrigido(nsCorrigido);
  	
  	
	return resultadoFDR;
  	
  		
	
}




	public double getNsCorrigido() {
		return nsCorrigido;
	}



	public void setNsCorrigido(double nsCorrigido) {
		this.nsCorrigido = nsCorrigido;
	}
	
	
}
