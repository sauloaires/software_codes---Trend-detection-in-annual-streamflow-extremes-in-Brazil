package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.ResultEstacionaridade;
import types.ResultadoFDR;
import types.SimulationDataExtremos;

/*import org.snirh.extremos_unb.deteccao.testes.DefinirFDR;
import org.snirh.extremos_unb.deteccao.testes.ResultEstacionaridade;
import org.snirh.extremos_unb.deteccao.testes.ResultadoFDR;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;*/

public class AnaliseResultadoFDR {
private SimulationDataExtremos simulationData;
	
	public AnaliseResultadoFDR(SimulationDataExtremos simulationData){
		this.simulationData=simulationData;
		
		
	}
	
	
public void executar(double nsig){
    	
    	
          String [] nometesteTotal=this.pegarSiglaTeste();
          ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
          int k1=0;
          while(this.simulationData.getVariaveisHidr().get(k1).getResultestacionaridade().size() <= 0){
       	   k1++;
          }
   	       resultestacionaridade = this.simulationData.getVariaveisHidr().get(k1).getResultestacionaridade();
   	       String [] nometeste=new String [resultestacionaridade.size()];
   	       
		   	 for(int j=0;j<resultestacionaridade.size();j++){
		   		nometeste[j]=resultestacionaridade.get(j).getNometeste();
		   	 }
   	    
		         
        for(int j=0;j<nometeste.length;j++){
        	
        	 DefinirFDR definirFDR=new DefinirFDR(this.simulationData);
			 //double nsig=5.0;
			 HashMap<String, ResultadoFDR> resultadoFDRMapa=definirFDR.definirResultadosFDREstacaoPorTesteMapa(nometeste[j], nsig);
        
		        for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
		        	String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
		        	if(this.simulationData.getVariaveisHidr().get(i).isSelecionada() && this.simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin()){
		        		this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j).setResultadoFDR(resultadoFDRMapa.get(codigo));
		        		String resulTestLocal=this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j).getResultadoteste();
		        		if(!resulTestLocal.equals("NS") && resultadoFDRMapa.get(codigo).getResultFDR().equals("NS")){
		        			this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j).setResultadoteste("NS");
		        			this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j).setMetodoObterValCritico("FDR");
		        		}
		        		
			   	     }
			   	    
		        }
        
        }
        
        
        
}
	


public boolean executar(Map<String, Map<String,ResultEstacionaridade>> resultEstacionaridadeTipo2,double nsig){
	
	boolean executouFDR=false;
    String [] nometesteTotal=this.pegarSiglaTeste();
    ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
    /*int k1=0;
    while(this.simulationData.getVariaveisHidr().get(k1).getResultestacionaridade().size() <= 0){
 	   k1++;
    }*/
    
    
    int k1=0;
    Set<String> chaves =  resultEstacionaridadeTipo2.keySet();
    if(chaves.size() > 0){
    String codigoInicial=null;
    for (String codigo: chaves){
    	codigoInicial=codigo;
    	break;
    }
    
   
    
       /*resultestacionaridade = this.simulationData.getVariaveisHidr().get(k1).getResultestacionaridade();
	       String [] nometeste=new String [resultestacionaridade.size()];
	       
	   	 for(int j=0;j<resultestacionaridade.size();j++){
	   		nometeste[j]=resultestacionaridade.get(j).getNometeste();
	   	 }*/
	    
    Set<String> chavesTeste = resultEstacionaridadeTipo2.get(codigoInicial).keySet();
    String [] nometeste=new String [chavesTeste.size()];
    int k=0;
    for (String nmteste: chavesTeste){
	   		nometeste[k]=nmteste;
	   		k++;
	   	 }
    
    
  for(int j=0;j<nometeste.length;j++){
  	
  	 DefinirFDR definirFDR=new DefinirFDR(this.simulationData);
		 //double nsig=5.0;
		 HashMap<String, ResultadoFDR> resultadoFDRMapa=definirFDR.definirResultadosFDREstacaoPorTesteMapa(resultEstacionaridadeTipo2,nometeste[j], nsig);
  
	       // for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
		 for (String codigo: chaves){
	        	//String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
	        	//if(this.simulationData.getVariaveisHidr().get(i).isSelecionada() && this.simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin()){
			        resultEstacionaridadeTipo2.get(codigo).get(nometeste[j]).setResultadoFDR(resultadoFDRMapa.get(codigo));
	        		//this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j).setResultadoFDR(resultadoFDRMapa.get(codigo));
	        		String resulTestLocal=resultEstacionaridadeTipo2.get(codigo).get(nometeste[j]).getResultadoteste();
	        		if(!resulTestLocal.equals("NS") && resultadoFDRMapa.get(codigo).getResultFDR().equals("NS")){
	        			resultEstacionaridadeTipo2.get(codigo).get(nometeste[j]).setResultadoteste("NS");
	        			resultEstacionaridadeTipo2.get(codigo).get(nometeste[j]).setMetodoObterValCritico("FDR");
	        		}
	        		
		   	    // }
		   	    
	        }
  
  }
  executouFDR=true;
    }
  
  
return executouFDR;
  
  
  
}

	
public String [] pegarSiglaTeste(){
	
	int ntestes=14;
								
	String [] nometeste=new String [ntestes];
	nometeste[0]="MK";
	nometeste[1]="SR";
	nometeste[2]="LR";
	nometeste[3]="TT";
	nometeste[4]="DC";
	nometeste[5]="CD";
	nometeste[6]="WR";
	nometeste[7]="MW";
	nometeste[8]="TF";
	nometeste[9]="MC";
	nometeste[10]="TP";
	nometeste[11]="RD";
	nometeste[12]="AC";
	nometeste[13]="WW";
	return nometeste;
}	
	
}
