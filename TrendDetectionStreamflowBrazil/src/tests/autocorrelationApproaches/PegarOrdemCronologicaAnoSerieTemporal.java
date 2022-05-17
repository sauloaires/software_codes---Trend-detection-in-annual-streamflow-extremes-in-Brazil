package tests.autocorrelationApproaches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import types.DadoTemporal;



public class PegarOrdemCronologicaAnoSerieTemporal {
public static Map<Integer,Integer> pegarMapaAnoOrdem(Map<String,DadoTemporal> mapaStrDados){
		
		Map<Integer,Integer>mapaAnoOrdem=new HashMap<Integer,Integer>();
		
		ArrayList<DadoTemporal>dados=new ArrayList<DadoTemporal>();	
		Set<String> chaves = mapaStrDados.keySet();
	  	for (String data : chaves){
	  		//String [] datastr=data.split("/");
	  		//Integer anoatual=Integer.parseInt(datastr[2]);
	  		DadoTemporal dado = new DadoTemporal();
 		  	dado=mapaStrDados.get(data);
 		  	dados.add(dado);
 		}
	  	
	  	Collections.sort(dados);
	  	int i1verificaAno=0;
	  	ArrayList<Integer>ano=new ArrayList<Integer>();
	   	for(int i=0;i<dados.size();i++){
	  		String data =dados.get(i).getDataStr();
	  		String [] datastr=data.split("/");
	  		Integer anoatual=Integer.parseInt(datastr[2]);
	  		if(dados.get(i).getValor() != -99999 || dados.get(i).getValor() > 0){
	  		ano.add(anoatual);
	  			if(i1verificaAno > 0){
	  				if(ano.get(i1verificaAno).equals(ano.get(i1verificaAno-1))){
		  				Integer anoNovo=ano.get(i1verificaAno)+1;
		  				ano.remove(i1verificaAno);
		  				ano.add(anoNovo);
		  	 	  }
	  				
	  			}
	  			i1verificaAno++;	
	  		}
	
	  	}
	  	
	  	
	   	Collections.sort(ano);	
	  	int anoIni=ano.get(0);
	  	int anoFim=ano.get(ano.size()-1);
	  	Integer ordem=1;
	  	for(int i=anoIni;i<=anoFim;i++){
	  		mapaAnoOrdem.put(anoIni, ordem);
	  		ordem++;	
	  	}
	   			
		return mapaAnoOrdem;
		
	}
	
public static Map<Integer,Integer> pegarMapaAnoOrdem(ArrayList<DadoTemporal>dados){
		
		Map<Integer,Integer>mapaAnoOrdem=new HashMap<Integer,Integer>();
		
	  	Collections.sort(dados);
	  		
	  	ArrayList<Integer>ano=new ArrayList<Integer>();
	  	/**
  		 * Saulo - 06/05/2020 - corrigir pra quando tiver falha ele nao verifica o ano repetido
  		 */
	  	 int i1verificaAno=0;
	   	for(int i=0;i<dados.size();i++){
	  		String data =dados.get(i).getDataStr();
	  		String [] datastr=data.split("/");
	  		Integer anoatual=Integer.parseInt(datastr[2]);
	  		if(dados.get(i).getValor() != -99999 || dados.get(i).getValor() > 0){
	  		ano.add(anoatual);
	  			if(i1verificaAno > 0){
	  				if(ano.get(i1verificaAno).equals(ano.get(i1verificaAno-1))){
		  				Integer anoNovo=ano.get(i1verificaAno)+1;
		  				ano.remove(i1verificaAno);
		  				ano.add(anoNovo);
		  	 	    }
	  				
	  			}
	  			i1verificaAno++;	
	  		}
	
	  	}
	  		  	
	   	Collections.sort(ano);	
	  	int anoIni=ano.get(0);
	  	int anoFim=ano.get(ano.size()-1);
	  	Integer ordem=1;
	  	for(int i=anoIni;i<=anoFim;i++){
	  		if(ano.contains(i)) {//Saulo - 06/05/2020 - 
	  		mapaAnoOrdem.put(i, ordem);
	  		
	  		}
	  		ordem++;//Saulo - 06/05/2020 - Ele nao coloca o ano inexiste mais tem q considerar a ordem
	  	}
	   			
		return mapaAnoOrdem;
		
	}



public static Map<Integer,Integer> pegarMapaAnoOrdem(int anoIni,int anoFim){
	
	Map<Integer,Integer>mapaAnoOrdem=new HashMap<Integer,Integer>();
	  
  	Integer ordem=1;
  	for(int i=anoIni;i<=anoFim;i++){
  		mapaAnoOrdem.put(anoIni, ordem);
  		ordem++;	
  	}
   			
	return mapaAnoOrdem;
	
}



public static Map<Integer,Integer> pegarMapaOrdemAno(ArrayList<DadoTemporal>dados){
	
	Map<Integer,Integer>mapaOrdemAno=new HashMap<Integer,Integer>();
	
  	Collections.sort(dados);
  		
  	ArrayList<Integer>ano=new ArrayList<Integer>();
  	/**
		 * Saulo - 06/05/2020 - corrigir pra quando tiver falha ele nao verifica o ano repetido
		 */
  	 int i1verificaAno=0;
   	for(int i=0;i<dados.size();i++){
  		String data =dados.get(i).getDataStr();
  		String [] datastr=data.split("/");
  		Integer anoatual=Integer.parseInt(datastr[2]);
  		if(dados.get(i).getValor() != -99999 || dados.get(i).getValor() > 0){
  		ano.add(anoatual);
  			if(i1verificaAno > 0){
  				if(ano.get(i1verificaAno).equals(ano.get(i1verificaAno-1))){
	  				Integer anoNovo=ano.get(i1verificaAno)+1;
	  				ano.remove(i1verificaAno);
	  				ano.add(anoNovo);
	  	 	}
  				
  			}
  			i1verificaAno++;	
  		}

  	}
  		  	
   	Collections.sort(ano);	
  	int anoIni=ano.get(0);
  	int anoFim=ano.get(ano.size()-1);
  	Integer ordem=1;
  	for(int i=anoIni;i<=anoFim;i++){
  		mapaOrdemAno.put(ordem,i);
  		ordem++;	
  	}
   			
	return mapaOrdemAno;
	
}
}
