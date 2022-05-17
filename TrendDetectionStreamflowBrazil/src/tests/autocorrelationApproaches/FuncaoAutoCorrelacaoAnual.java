package tests.autocorrelationApproaches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import types.DadoTemporal;


public class FuncaoAutoCorrelacaoAnual {
	 private double [][] seriesEmparelhadas;
		
	 private double [] series1;
	 private double [] series2;
	
	private Map<String,DadoTemporal> mapaStrDados;
	private Map<Integer,DadoTemporal> mapaAnoDados;
	public Map<Integer, DadoTemporal> getMapaAnoDados() {
		return mapaAnoDados;
	}



	private ArrayList<Integer>ano;
	
	public ArrayList<Integer> getAno() {
		return ano;
	}



	private int klag;
	private int[]klags;
	
	public  FuncaoAutoCorrelacaoAnual(double [][] seriesEmparelhadas){
		   
	    this.seriesEmparelhadas=seriesEmparelhadas;
	    int ncols=this.seriesEmparelhadas[0].length;
		this.series1=new double [this.seriesEmparelhadas.length]; 
		this.series2=new double [this.seriesEmparelhadas.length];
		
		for (int i=0;i<this.seriesEmparelhadas.length;i++){
		this.series1[i]=this.seriesEmparelhadas[i][0];
		this.series2[i]=this.seriesEmparelhadas[i][1];
		}
	    
		//RealMatrix matrix = MatrixUtils.createRealMatrix(this.seriesEmparelhadas);
		
		
		
		}
	
	
	public  FuncaoAutoCorrelacaoAnual(double [] series1,double [] series2){
		this.series1=series1;
		this.series2=series2;
	}
	
	public double [] correlLags(int [] klags){
		   this.klags=klags;
		   double [] correl=new double[this.klags.length];
		
		   for (int i=0;i<this.klags.length;i++){
		   correl[i]=this.correlLag(this.klags[i]);
		   }
		   
		   
		   
		   return correl;
		  	   
	   }
	
	 public double correlLag(int klag){
		   
		   double correl=-99999.0;
		  
		   	PearsonsCorrelation pcor=new PearsonsCorrelation();
		   	correl=pcor.correlation(this.series1, this.series2);
		   	
		   	
		   	return correl;
		   
		   
	   }
	
	
	
	 public FuncaoAutoCorrelacaoAnual(ArrayList<DadoTemporal>dados){
		   
		  
			Map<Integer,DadoTemporal> mapaAnoDados=new HashMap<Integer,DadoTemporal>();
			
			  	/**
			  	 * Saulo - 25/08/2016 - Correção para nao repetir ano (quando for usado ano hidrologico por exemplo)
			  	 */
			  
			  	Collections.sort(dados);
			  	 ArrayList<Integer>ano=new ArrayList<Integer>();
			  	int i1verificaAno=0;
			  	for(int i=0;i<dados.size();i++){
			  		String data =dados.get(i).getDataStr();
			  		String [] datastr=data.split("/");
			  		Integer anoatual=Integer.parseInt(datastr[2]);
			  	
			  		/**
			  		 * Saulo - 06/05/2020 - corrigir pra quando tiver falha ele nao verifica o ano repetido
			  		 */
			  		if(dados.get(i).getValor() != -99999 || dados.get(i).getValor() > 0){
			  		ano.add(anoatual);
			  		
			  			if(i1verificaAno > 0){
			  				if(ano.get(i1verificaAno).equals(ano.get(i1verificaAno-1))){
				  				Integer anoNovo=ano.get(i1verificaAno)+1;
				  				ano.remove(i1verificaAno);
				  				ano.add(anoNovo);
				  				mapaAnoDados.put(anoNovo, dados.get(i));
				  			}else{
				  				//ano.add(anoatual);
				  				mapaAnoDados.put(anoatual, dados.get(i));
				  			}
			  			}else{
			  				mapaAnoDados.put(anoatual, dados.get(i));	
			  			}
			  			i1verificaAno++;	
			  		}
			  		
			  		
			  	}
		  	
			  	
			  	this.ano=ano;
			  	this.mapaAnoDados=mapaAnoDados;
			 
			   	
	   }
	   
	
	
	
	 public FuncaoAutoCorrelacaoAnual(ArrayList<Double>dados,int anoIni){
     
     
     Map<Integer,DadoTemporal> mapaAnoDados=new HashMap<Integer,DadoTemporal>();
     
         /**
          * Saulo - 25/08/2016 - Correção para nao repetir ano (quando for usado ano hidrologico por exemplo)
          */
       
         //Collections.sort(dados);
          ArrayList<Integer>ano=new ArrayList<Integer>();
          int anovar=anoIni;
         for(int i=0;i<dados.size();i++){
           String data ="01/01/"+anovar;
           String [] datastr=data.split("/");
           Integer anoatual=Integer.parseInt(datastr[2]);
           DadoTemporal dt=new DadoTemporal();
           dt.setData(data);
           dt.setValor(dados.get(i));
           //if(dados.get(i).getValor() != -99999 || dados.get(i).getValor() > 0){ //restricao para chuva e vazao
           ano.add(anoatual);
           
             if(i > 0){
               if(ano.get(i).equals(ano.get(i-1))){
                 Integer anoNovo=ano.get(i)+1;
                 ano.remove(i);
                 ano.add(anoNovo);
                 mapaAnoDados.put(anoNovo, dt);
               }else{
                 //ano.add(anoatual);
                 mapaAnoDados.put(anoatual, dt);
               }
             }else{
               mapaAnoDados.put(anoatual, dt); 
             }
             
           //}
             anovar++;
           
         }
       
         
         this.ano=ano;
         this.mapaAnoDados=mapaAnoDados;
      
         
    }
	
   
   
	
   public FuncaoAutoCorrelacaoAnual(Map<String,DadoTemporal> mapaStrDados){
	   
	    this.mapaStrDados= mapaStrDados;
       
		
		
		
		Map<Integer,DadoTemporal> mapaAnoDados=new HashMap<Integer,DadoTemporal>();
		ArrayList<DadoTemporal>dados=new ArrayList<DadoTemporal>();	
				
		
		
		    Set<String> chaves = mapaStrDados.keySet();
		  	for (String data : chaves){
		  		//String [] datastr=data.split("/");
		  		//Integer anoatual=Integer.parseInt(datastr[2]);
		  		DadoTemporal dado = new DadoTemporal();
	 		  	dado=mapaStrDados.get(data);
	 		  	dados.add(dado);
	 		  	/**
	 		  	 * Considerar Apenas dados sem Falha
	 		  	 */
	 		  	if(dado.getValor() != -99999 || dado.getValor() > 0){
	 		  		//ano.add(anoatual);
			  		//mapaAnoDados.put(anoatual, dado);	
	 		  	}
		  		
		  	}
		  	/**
		  	 * Saulo - 25/08/2016 - Correção para nao repetir ano (quando for usado ano hidrologico por exemplo)
		  	 */
		  	//Collections.sort(ano);
		  	Collections.sort(dados);
		  	 ArrayList<Integer>ano=new ArrayList<Integer>();
		  	//ArrayList <Integer> anoSerie=new ArrayList <Integer>();
		  	 int i1verificaAno=0;
		  	for(int i=0;i<dados.size();i++){
		  		String data =dados.get(i).getDataStr();
		  		String [] datastr=data.split("/");
		  		Integer anoatual=Integer.parseInt(datastr[2]);
		  	
		  		/**
		  		 * Saulo - 06/05/2020 - corrigir pra quando tiver falha ele nao verifica o ano repetido
		  		 */
		  		if(dados.get(i).getValor() != -99999 || dados.get(i).getValor() > 0){
		  		ano.add(anoatual);
		  		
		  			if(i1verificaAno > 0){
		  				if(ano.get(i1verificaAno).equals(ano.get(i1verificaAno-1))){
			  				Integer anoNovo=ano.get(i1verificaAno)+1;
			  				ano.remove(i1verificaAno);
			  				ano.add(anoNovo);
			  				mapaAnoDados.put(anoNovo, dados.get(i));
			  			}else{
			  				//ano.add(anoatual);
			  				mapaAnoDados.put(anoatual, dados.get(i));
			  			}
		  			}else{
		  				mapaAnoDados.put(anoatual, dados.get(i));	
		  			}
		  			i1verificaAno++;	
		  		}
		  		
		  		
		  	}
	  	
		  	
		  	this.ano=ano;
		  	this.mapaAnoDados=mapaAnoDados;
		 
		   	
   }
   
   
   
   public double correlLagMapa(int klag){
	   
	   double correl=-99999.0;
	   
	   /**
	  	 * Generalizar para poder pegar amostrar emparelhadas com lag
	  	 */
	    ArrayList<Double>dados=new ArrayList<Double>();
	    ArrayList<Double>dadosLag=new ArrayList<Double>();
	    
	  	for (int i=0;i<this.ano.size();i++){
	  	
	  		Integer anolag=this.ano.get(i)-klag;
	  		if(this.mapaAnoDados.containsKey(anolag)){
	  			if(this.mapaAnoDados.get(anolag).getValor() != -99999 || this.mapaAnoDados.get(anolag).getValor() > 0){
	  				dados.add(this.mapaAnoDados.get(this.ano.get(i)).getValor());
	  				dadosLag.add(this.mapaAnoDados.get(anolag).getValor());
	  				
	  			}
	  			
	  		}
	  		
	  	}
	  	
	  	
	  	this.seriesEmparelhadas=new double [dados.size()][2];  
	  	this.series1=new double [dados.size()];
	  	this.series2=new double [dados.size()];
	  	
	   	for (int i=0;i<dados.size();i++){
	   		this.seriesEmparelhadas[i][0]=dados.get(i);
	   		this.seriesEmparelhadas[i][1]=dadosLag.get(i);
 			
 			this.series1[i]=dados.get(i);
 			this.series2[i]=dadosLag.get(i);
 			
	  		}
		
	   	PearsonsCorrelation pcor=new PearsonsCorrelation();
	   	correl=pcor.correlation(this.series1, this.series2);
	   	
	   	
	   	return correl;
	   
	   
   }
   
   
   
   public double [] correlLagsMapa(int [] klags){
	   this.klags=klags;
	   double [] correl=new double[this.klags.length];
	
	   for (int i=0;i<this.klags.length;i++){
	   correl[i]=this.correlLagMapa(this.klags[i]);
	   }
	   
	   
	   
	   return correl;
	  	   
   }
   
   
public double correlLagMapaSemRestricao(int klag){
     
     double correl=-99999.0;
     
     /**
       * Generalizar para poder pegar amostrar emparelhadas com lag
       */
      ArrayList<Double>dados=new ArrayList<Double>();
      ArrayList<Double>dadosLag=new ArrayList<Double>();
      
      for (int i=0;i<this.ano.size();i++){
      
        Integer anolag=this.ano.get(i)-klag;
        if(this.mapaAnoDados.containsKey(anolag)){
          //if(this.mapaAnoDados.get(anolag).getValor() != -99999 || this.mapaAnoDados.get(anolag).getValor() > 0){
            dados.add(this.mapaAnoDados.get(this.ano.get(i)).getValor());
            dadosLag.add(this.mapaAnoDados.get(anolag).getValor());
            
          //}
          
        }
        
      }
      
      
      this.seriesEmparelhadas=new double [dados.size()][2];  
      this.series1=new double [dados.size()];
      this.series2=new double [dados.size()];
      
      for (int i=0;i<dados.size();i++){
        this.seriesEmparelhadas[i][0]=dados.get(i);
        this.seriesEmparelhadas[i][1]=dadosLag.get(i);
      
      this.series1[i]=dados.get(i);
      this.series2[i]=dadosLag.get(i);
      
        }
    
      PearsonsCorrelation pcor=new PearsonsCorrelation();
      correl=pcor.correlation(this.series1, this.series2);
      
      
      return correl;
     
     
   }


public double[] getSeries1() {
	return series1;
}


public void setSeries1(double[] series1) {
	this.series1 = series1;
}


public double[] getSeries2() {
	return series2;
}


public void setSeries2(double[] series2) {
	this.series2 = series2;
}
   
   
}
