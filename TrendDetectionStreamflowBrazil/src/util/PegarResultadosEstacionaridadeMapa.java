package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.InventarioHidrologico;
import types.ResultEstacionaridade;
import types.SimulationDataExtremos;

/*import org.snirh.extremos_unb.deteccao.testes.ResultEstacionaridade;
import org.snirh.extremos_unb.deteccao.util.PegarNomesTestes;
import org.snirh.extremos_unb.tipos.InventarioHidrologico;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;*/

public class PegarResultadosEstacionaridadeMapa {

	
	public static HashMap<String,ResultEstacionaridade> executar(SimulationDataExtremos simulationData,String nomeTeste){
		
		
		HashMap<String,ResultEstacionaridade> resultEstacTesteEstacao =new HashMap<String,ResultEstacionaridade>();
		 String [] nometesteTotal=PegarNomesTestes.siglaTeste();
        ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
        int k1=0;
        while(simulationData.getVariaveisHidr().get(k1).getResultestacionaridade().size() <= 0){
     	   k1++;
        }
 	       resultestacionaridade = simulationData.getVariaveisHidr().get(k1).getResultestacionaridade();
	       String [] nometeste=new String [resultestacionaridade.size()];
		   	 for(int j=0;j<resultestacionaridade.size();j++){
		   		nometeste[j]=resultestacionaridade.get(j).getNometeste();
		   	 }
	          
     for(int j=0;j<nometeste.length;j++){
      	 if(nomeTeste.equals(nometeste[j])){
		        for(int i=0;i<simulationData.getVariaveisHidr().size();i++){
			   	   	if(simulationData.getVariaveisHidr().get(i).isSelecionada() && simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin()){
		        	  	   	String codigo=String.valueOf(simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
					   	   	resultEstacTesteEstacao.put(codigo, simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j));
				    }
			   	    
		        }
    	 }
     
     }
     
	return resultEstacTesteEstacao;
		
	}
	
	
	public Map<String,Map<String,ResultEstacionaridade>> executar(SimulationDataExtremos simulationData){
			
		 String [] nometesteTotal=PegarNomesTestes.siglaTeste();
         ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
 	       resultestacionaridade = simulationData.getVariaveisHidr().get(0).getResultestacionaridade();
 	       String [] nometeste=new String [resultestacionaridade.size()];
		   	 for(int j=0;j<resultestacionaridade.size();j++){
		   		nometeste[j]=resultestacionaridade.get(j).getNometeste();
		   	 }
 	    
	
      Map<String,Map<String,ResultEstacionaridade>> resultEstacTesteEstacao =new HashMap<String,Map<String,ResultEstacionaridade>>();
      Map<String,InventarioHidrologico> inventario=new HashMap<String,InventarioHidrologico>();
      String nomeIndice="";
      for(int j=0;j<nometeste.length;j++){
      
		        for(int i=0;i<simulationData.getVariaveisHidr().size();i++){
			   	    
		        	if(simulationData.getVariaveisHidr().get(i).isSelecionada() && simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin()){
		        		
					   	   	String codigo=String.valueOf(simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
					   	   	if(j == 0){
					   	    inventario.put(codigo, simulationData.getVariaveisHidr().get(i).getInvhidro());
					   	    nomeIndice=simulationData.getVariaveisHidr().get(i).getInvhidro().getDescricaoOrigemSerie();
					   	   	}
					   	   	if(resultEstacTesteEstacao.containsKey(nometeste[j])){
					   	   	resultEstacTesteEstacao.get(nometeste[j]).put(codigo, simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j));
					   	   	}else{
					   	   	Map<String,ResultEstacionaridade> resestac=new HashMap<String,ResultEstacionaridade>();	
					   	    resultEstacTesteEstacao.put(nometeste[j], resestac);
					   	    resultEstacTesteEstacao.get(nometeste[j]).put(codigo,simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j));
					   	   	}
			   	     }
			   	    
		        }
      
      }
	return resultEstacTesteEstacao;
		
	}
	
	
	
	
	
	
public static HashMap<String,ResultEstacionaridade> executar(Map<String, Map<String,ResultEstacionaridade>> resultEstacionaridadeTipo2,String nomeTeste){
		
		
		HashMap<String,ResultEstacionaridade> resultEstacTesteEstacao =new HashMap<String,ResultEstacionaridade>();
		 String [] nometesteTotal=PegarNomesTestes.siglaTeste();
        ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
        int k1=0;
        Set<String> chaves =  resultEstacionaridadeTipo2.keySet();
        String codigoInicial=null;
        for (String codigo: chaves){
        	codigoInicial=codigo;
        	break;
        }
       // while(simulationData.getVariaveisHidr().get(k1).getResultestacionaridade().size() <= 0){
     	//   k1++;
       // }
        Set<String> chavesTeste = resultEstacionaridadeTipo2.get(codigoInicial).keySet();
	       String [] nometeste=new String [chavesTeste.size()];
	       int k=0;
	       for (String nmteste: chavesTeste){
		   		nometeste[k]=nmteste;
		   		k++;
		   	 }
	          
     for(int j=0;j<nometeste.length;j++){
      	 if(nomeTeste.equals(nometeste[j])){
		       // for(int i=0;i<simulationData.getVariaveisHidr().size();i++){
      		   for (String codigo: chaves){
			   	   	//if(simulationData.getVariaveisHidr().get(i).isSelecionada() && simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin()){
		        	  	   	//String codigo=String.valueOf(simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
					   	   	resultEstacTesteEstacao.put(codigo,resultEstacionaridadeTipo2.get(codigo).get(nometeste[j]));
				    //}
			   	    
		        }
    	 }
     
     }
     
	return resultEstacTesteEstacao;
		
	}
	
	
}
