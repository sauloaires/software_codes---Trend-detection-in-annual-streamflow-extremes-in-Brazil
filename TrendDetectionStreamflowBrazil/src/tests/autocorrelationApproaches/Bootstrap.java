package tests.autocorrelationApproaches;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*import org.snirh.extremos_unb.deteccao.testes.FuncaoAutoCorrelacaoAnual;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.MannKendallTest;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.bootstrap.Bootstrap;
import org.snirh.extremos_unb.tipos.DadoTemporal;
import org.snirh.extremos_unb.tipos.InventarioHidrologico;
import org.snirh.extremos_unb.tipos.SerieTemporal;
import org.snirh.extremos_unb.tipos.VariavelHidrologica;
import org.snirh.extremos_unb.util.Esco7;*/

import flanagan.analysis.Stat;
import flanagan.math.PsRandom;
import tests.MannKendallTest;
import types.DadoTemporal;
import types.InventarioHidrologico;
import types.SerieTemporal;
import types.VariavelHidrologica;
import util.Esco7;

public class Bootstrap {
	double [] valuetot;
	double [] valuex;
	double [] valuey;
	double [][] valuetotbootstrap;
	double [][] estatbootstrap;
	double [][] resultestteste;
	double [][] resultesttesteord;
	double [][] valorescriticos;
	int divisao;
	int namostras;

	private ArrayList<DadoTemporal> dadostot;
	private String [] dataStr;
    private int [] dataAno;
		
    private ArrayList<DadoTemporal> dados1;
	private int [] dataAno1;
	private String [] dataStr1;
	
	private int [] dataAno2;
	private String [] dataStr2;
	private ArrayList<DadoTemporal> dados2;
	
	
	private ArrayList<Map<String,DadoTemporal>> seriesBootstrapMapa;
	private Map<String,DadoTemporal> serieOriginalMapa;
	private int tipoHipotese;
	private double nivelSignificancia;
	private ArrayList<Double> resultadoTeste;
	private ArrayList<ArrayList<Double>> resultTotTestes;
	
	public ArrayList<Double> getResultadoTeste() {
		return resultadoTeste;
	}

	public void setResultadoTeste(ArrayList<Double> resultadoTeste) {
		this.resultadoTeste = resultadoTeste;
	}

	public Map<String,DadoTemporal> getSerieOriginalMapa() {
		return serieOriginalMapa;
	}

	public void setSerieOriginalMapa(Map<String,DadoTemporal> serieOriginalMapa) {
		this.serieOriginalMapa = serieOriginalMapa;
	}

	public ArrayList<Map<String,DadoTemporal>> getSeriesBootstrapMapa() {
		return seriesBootstrapMapa;
	}

	public void setSeriesBootstrapMapa(ArrayList<Map<String,DadoTemporal>> seriesBootstrapMapa) {
		this.seriesBootstrapMapa = seriesBootstrapMapa;
	}

	public String[] getDataStr() {
		return dataStr;
	}

	public void setDataStr(String[] dataStr) {
		this.dataStr = dataStr;
	}

	public int[] getDataAno() {
		return dataAno;
	}

	public void setDataAno(int[] dataAno) {
		this.dataAno = dataAno;
	}

	    
	public ArrayList<DadoTemporal> getDadostot() {
		return dadostot;
	}

	public void setDadostot(ArrayList<DadoTemporal> dadostot) {
		this.dadostot = dadostot;
	}

	public ArrayList<DadoTemporal> getDados1() {
		return dados1;
	}

	public void setDados1(ArrayList<DadoTemporal> dados1) {
		this.dados1 = dados1;
	}

	public ArrayList<DadoTemporal> getDados2() {
		return dados2;
	}

	public void setDados2(ArrayList<DadoTemporal> dados2) {
		this.dados2 = dados2;
	}

	public int[] getDataAno1() {
		return dataAno1;
	}

	public void setDataAno1(int[] dataAno1) {
		this.dataAno1 = dataAno1;
	}

	public int[] getDataAno2() {
		return dataAno2;
	}

	public void setDataAno2(int[] dataAno2) {
		this.dataAno2 = dataAno2;
	}

	public String[] getDataStr1() {
		return dataStr1;
	}

	public void setDataStr1(String[] dataStr1) {
		this.dataStr1 = dataStr1;
	}

	public String[] getDataStr2() {
		return dataStr2;
	}

	public void setDataStr2(String[] dataStr2) {
		this.dataStr2 = dataStr2;
	}

	public  Bootstrap(){
		
		
	}
	
	public  Bootstrap(Map<String,DadoTemporal> serieMapa){
    	
		this.setSerieOriginalMapa(serieMapa);
		ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
  		Set<String> chavesMax =  serieMapa.keySet();
	  	
  		for (String data : chavesMax){
	  	DadoTemporal dado = serieMapa.get(data);
	  	dados.add(dado);
	   	}
	  	
	  	
    	Collections.sort(dados);
      	this.setDadostot(dados);
      	this.valuetot=new double[this.dadostot.size()];
      	this.dataAno=new int[this.dadostot.size()];
      	this.dataStr=new String [this.dadostot.size()];
      	for (int i=0;i<this.dadostot.size();i++){
      	this.valuetot[i]=this.dadostot.get(i).getValor();
      	this.dataStr[i]=this.dadostot.get(i).getDataStr();
      	this.dataAno[i]=this.dadostot.get(i).pegarAno();
      	}
  	
      	
    }
	
	public  Bootstrap(ArrayList<Double> serie){
		ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
        this.valuetot=new double[serie.size()];
        this.dataAno=new int[serie.size()];
        this.dataStr=new String [serie.size()];
        int anoficticio=1900;
        for (int i=0;i<serie.size();i++){
        String data="01/01/"+anoficticio;
        DadoTemporal dado =	new DadoTemporal();
        dado.setData(data);	
        dado.setValor(serie.get(i));
        
        dados.add(dado);
        this.dataStr[i]=data;
      	this.dataAno[i]=anoficticio;
        this.valuetot[i]=serie.get(i);
        
        anoficticio++;
        }
    
        Collections.sort(dados);
      	this.setDadostot(dados);
    }
	
	
	 public  Bootstrap(double [] valuetot){
		this.valuetot=new double[valuetot.length];
		this.valuetot= valuetot;
		int metade = valuetot.length/2;
		this.valuex=new double[metade];
		this.valuey=new double[valuetot.length-metade];
		for (int i=0;i<metade;i++){
		this.valuex[i]=this.valuetot[i];
		}
		int i1=0;
		for (int i=metade;i<valuetot.length;i++){
		this.valuey[i1]=this.valuetot[i];
		i1++;
		}
		
		
	}
	
    public  Bootstrap(double [] valuex,double [] valuey){
    	this.valuex=new double[valuex.length];
		this.valuex= valuex;
		this.valuey=new double[valuey.length];
		this.valuey= valuey;
		this.valuetot=new double[valuex.length+valuey.length];
		int k=0;
		for (int i=0;i<valuex.length;i++){
			this.valuetot[k]=this.valuex[i];	
		k++;
		}
		for (int i=0;i<valuey.length;i++){
			this.valuetot[k]=this.valuey[i];	
		k++;
		}
		
	}
	
    public  Bootstrap(double [] valuetot, int divisao){
		this.valuetot=new double[valuetot.length];
		this.valuetot= valuetot;
		this.divisao=divisao;
		int metade = this.divisao; //divisão é a posição no vetor onde que se dividir a série
		this.valuex=new double[metade];
		for (int i=0;i<metade;i++){
		this.valuex[i]=this.valuetot[i];
		}
		
		  for (int i=metade;i<valuetot.length;i++){
			this.valuey[i]=this.valuetot[i];
		}
		
		
	}
  
    
 public void obterSerieBootstrapBlocoCronologicoAno (int namostras, int L) {
    	
    	this.namostras=namostras;
    	PsRandom ran = new PsRandom();
    	//int N = this.dadostot.size();
    	//Map<Integer,Integer>mapaOrdemAno=PegarOrdemCronologicaAnoSerieTemporal.pegarMapaAnoOrdem(this.dadostot);
    	FuncaoAutoCorrelacaoAnual facOriginal =new FuncaoAutoCorrelacaoAnual(this.dadostot);
   	    Map<Integer,DadoTemporal> mapaAnoDadosOriginal=facOriginal.getMapaAnoDados();
   	    Map<Integer,ArrayList<DadoTemporal>> blocos=this.pegarBlocosDeSerieTemporal(mapaAnoDadosOriginal, L);
   	     int N = blocos.size();
    	ArrayList<Map<String,DadoTemporal>> seriesBoot = new ArrayList<Map<String,DadoTemporal>>();
    	for (int i=0;i<namostras;i++){
    		Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>() ;
    		int k=0;
    		Integer anoFicticio=1900;
    		ArrayList<String>datasLidas=new ArrayList<String>();
    		while(k < this.dadostot.size()){
    		//for (int k=0;k<this.dadostot.size();k++){
    			int indsort = ran.nextInteger(N-1);
    			//int ano=mapaOrdemAno.get(indsort);
    			if(indsort == 0){
    				System.out.println();
    			}
    			
    			ArrayList<DadoTemporal> dadosEmBloco=blocos.get(indsort);
    			
    			for (int j=0;j<dadosEmBloco.size();j++){
    				String dataFicticia="01/01/"+anoFicticio;
    				DadoTemporal dado = new DadoTemporal();
        			dado.setValor(dadosEmBloco.get(j).getValor());
        			String datastr="";
        			if(k > this.dadostot.size()-1){
        				String [] dataVetor=datasLidas.get(k-1).split("/");
        				Integer anoatual=Integer.parseInt(dataVetor[2]);
        				anoatual++;
        				datastr="01/01/"+anoatual;
        			}else{
        				datastr=this.dataStr[k];	
        			}
        			datasLidas.add(datastr);
        			dado.setData(datastr);
        			serieMapa.put(datastr, dado);
    				//serieMapa.put(dataFicticia, dadosEmBloco.get(j));
    				anoFicticio++;
    				k++;
    			}
    		
    		}
    		seriesBoot.add(serieMapa);	
    		System.out.println("Numero de amostras =   "+(i+1));
    	}
    
    	this.setSeriesBootstrapMapa(seriesBoot);
    
	    }
    
       
 public ArrayList<VariavelHidrologica> obterVhidSerieBootstrapBlocoCronologicoAno (int namostras, int L,VariavelHidrologica vhid) {
   
   ArrayList<VariavelHidrologica> seriesBootVhid = new ArrayList<VariavelHidrologica>();
   this.namostras=namostras;
   PsRandom ran = new PsRandom();
   //int N = this.dadostot.size();
   //Map<Integer,Integer>mapaOrdemAno=PegarOrdemCronologicaAnoSerieTemporal.pegarMapaAnoOrdem(this.dadostot);
   FuncaoAutoCorrelacaoAnual facOriginal =new FuncaoAutoCorrelacaoAnual(this.dadostot);
     Map<Integer,DadoTemporal> mapaAnoDadosOriginal=facOriginal.getMapaAnoDados();
     Map<Integer,ArrayList<DadoTemporal>> blocos=this.pegarBlocosDeSerieTemporal(mapaAnoDadosOriginal, L);
      int N = blocos.size();
   ArrayList<Map<String,DadoTemporal>> seriesBoot = new ArrayList<Map<String,DadoTemporal>>();
   for (int i=0;i<namostras;i++){
     Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>() ;
     int k=0;
     Integer anoFicticio=1900;
     ArrayList<String>datasLidas=new ArrayList<String>();
     while(k < this.dadostot.size()){
     //for (int k=0;k<this.dadostot.size();k++){
       int indsort = ran.nextInteger(N-1);
       //int ano=mapaOrdemAno.get(indsort);
       if(indsort == 0){
         System.out.println();
       }
       
       ArrayList<DadoTemporal> dadosEmBloco=blocos.get(indsort);
       
       for (int j=0;j<dadosEmBloco.size();j++){
         String dataFicticia="01/01/"+anoFicticio;
         DadoTemporal dado = new DadoTemporal();
           dado.setValor(dadosEmBloco.get(j).getValor());
           String datastr="";
           if(k > this.dadostot.size()-1){
             String [] dataVetor=datasLidas.get(k-1).split("/");
             Integer anoatual=Integer.parseInt(dataVetor[2]);
             anoatual++;
             datastr="01/01/"+anoatual;
           }else{
             datastr=this.dataStr[k];  
           }
           datasLidas.add(datastr);
           dado.setData(datastr);
           serieMapa.put(datastr, dado);
         //serieMapa.put(dataFicticia, dadosEmBloco.get(j));
         anoFicticio++;
         k++;
       }
     
     }
     seriesBootVhid.add(pegarVhidSubSerie(serieMapa,vhid));
     //seriesBoot.add(serieMapa);  
     System.out.println("Numero de amostras =   "+(i+1));
   }
  return seriesBootVhid;
 
   //this.setSeriesBootstrapMapa(seriesBoot);
 
   }
 
 
 public static VariavelHidrologica pegarVhidSubSerie(Map<String,DadoTemporal> serieMapa,VariavelHidrologica vhid){
   VariavelHidrologica vhidAnual=new VariavelHidrologica();
   ArrayList<DadoTemporal> dadosTemp=new ArrayList<DadoTemporal>();
   Set<String> chaves=  serieMapa.keySet();
   for (String data : chaves){
     dadosTemp.add(serieMapa.get(data));
   }
   
    SerieTemporal serietemp = new SerieTemporal ();
    Collections.sort(dadosTemp);
    Calendar dataInicialSerie= Calendar.getInstance();;
    Calendar dataFinalSerie= Calendar.getInstance();;
    dataInicialSerie.setTime(dadosTemp.get(0).getData());
    dataFinalSerie.setTime(dadosTemp.get(dadosTemp.size()-1).getData());
    serietemp.setDataInicialSerie(dataInicialSerie);
    serietemp.setDataFinalSerie(dataFinalSerie);
    serietemp.setDados(dadosTemp); 
    serietemp.setaMapaStrDadoTemporal();
    vhidAnual.setInvhidro(pegarInventarioVhid(vhid));
    vhidAnual.setSerietemporal(serietemp);
   return vhidAnual;
 }
 
 
 
 public static InventarioHidrologico pegarInventarioVhid(VariavelHidrologica vhid){
   
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
   return invhid;
 }
 
 
 
 
 
 
 
 
 
 
 public Map<Integer,ArrayList<DadoTemporal>> pegarBlocosDeSerieTemporal(Map<Integer,DadoTemporal> mapaAnoDados,  int L){
	 
	 Map<Integer,ArrayList<DadoTemporal>> blocos=new HashMap<Integer,ArrayList<DadoTemporal>>();
	 Set<Integer> chaves =mapaAnoDados.keySet();
	 ArrayList<Integer> anosOrdenado=new ArrayList<Integer>();
	 anosOrdenado.addAll(chaves);
	 Collections.sort(anosOrdenado);
	 
	 Integer nblocos=0;
	 for (int i=0;i<anosOrdenado.size();i++){
		 int ano=anosOrdenado.get(i);
		 ArrayList<DadoTemporal> dadosEmBloco=new ArrayList<DadoTemporal>();
		 dadosEmBloco.add(mapaAnoDados.get(ano));
		 int iano=1;
		 for (int j=i+1;j<i+L;j++){
			 int klag=iano;
			 Integer anolag=ano+klag;
		     //Integer anolag=anosOrdenado.get(j);;
				 if(mapaAnoDados.containsKey(anolag)){
					if(mapaAnoDados.get(anolag).getValor() != -99999. || mapaAnoDados.get(anolag).getValor() >= 0){
						dadosEmBloco.add(mapaAnoDados.get(anolag));
					}
				 }
				 iano++; 
		 }
		 if(dadosEmBloco.size() == L){
			 blocos.put(nblocos, dadosEmBloco);
			 nblocos++;
		 } 
	 }
 
	return blocos;
	
 }
 
 
 
 /**
  * Nao precisou pois encontrou forma mais facil de fazer os blocos
  * @param namostras
  */
 /*public ArrayList<DadoTemporal> pegarValorEmBloco(Map<Integer,DadoTemporal> mapaAnoDados, int ano, int L){
	 
	 ArrayList<DadoTemporal> dadosEmBloco=new ArrayList<DadoTemporal>();
	 dadosEmBloco.add(mapaAnoDados.get(ano));
	 
	 
	 boolean verificarAnosParaTras=true;
	  if(verificarAnosParaTras){
	   for (int i=1;i<L;i++){
		   int klag=i;
		     Integer anolag=ano-klag;
				 if(mapaAnoDados.containsKey(anolag)){
					if(mapaAnoDados.get(anolag).getValor() != -99999. || mapaAnoDados.get(anolag).getValor() > 0){
						dadosEmBloco.add(mapaAnoDados.get(anolag));
					}
				 }
		
		 }
		 
		 if(dadosEmBloco.size() == L){
			 return dadosEmBloco;
		 }else{
			 
			 for (int i=1;i<dadosEmBloco.size();i++){
				 dadosEmBloco.remove(i);
			 }
		 }
	
	 }
	 
	 
		 boolean verificarAnosParaFrente=true;
		 if(verificarAnosParaFrente){
			 for (int i=1;i<L;i++){
				
				 int klag=i;
			     Integer anolag=ano+klag;
					 if(mapaAnoDados.containsKey(anolag)){
						if(mapaAnoDados.get(anolag).getValor() != -99999. || mapaAnoDados.get(anolag).getValor() > 0){
							dadosEmBloco.add(mapaAnoDados.get(anolag));
						}
					 }
			
			 }
			 
			 if(dadosEmBloco.size() == L){
				 return dadosEmBloco;
			 }else{
				
				 for (int i=1;i<dadosEmBloco.size();i++){
					 dadosEmBloco.remove(i);
				 }
			 }
		
		 }
		 
		 
		 boolean verificarAnosCentrado=true;
		 
		 if(verificarAnosCentrado){
			 
			 int klag=0;
			 int klagFrente=0;
			 int klagTras=0;
			 for (int i=1;i<L;i++){
				 if((i  % 2) == 0){
					 klagFrente++;
					 klag=klagFrente;
				 }else{
					 klagTras--;
					 klag=klagTras;
				 }
				 //int klag=i+1;	 
			     Integer anolag=ano+klag;
					 if(mapaAnoDados.containsKey(anolag)){
						if(mapaAnoDados.get(anolag).getValor() != -99999. || mapaAnoDados.get(anolag).getValor() > 0){
							dadosEmBloco.add(mapaAnoDados.get(anolag));
						}
					 }
			
			 }
			 
			 if(dadosEmBloco.size() == L){
				 return dadosEmBloco;
			 }
			 
		 }
		 
		 
		 
	return null;
	 
	 
	 
 }*/
 
 
 public ArrayList<ArrayList<Double>> pegarSerieBootstrapArray (int namostras) {
   
   this.namostras=namostras;
   PsRandom ran = new PsRandom();
   int N = this.valuetot.length;
   
   ArrayList<ArrayList<Double>> seriesBoot = new ArrayList<ArrayList<Double>>();
   for (int i=0;i<namostras;i++){
     
     Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>() ; 
     ArrayList<Double> serien=new ArrayList<Double>();
     Integer anoFicticio=1900;
     for (int k=0;k<this.valuetot.length;k++){
       int indsort = ran.nextInteger(N-1);
       if(indsort == 0){
         System.out.println();
       }
       serien.add(this.valuetot[indsort]);
       DadoTemporal dado = new DadoTemporal();
       dado.setValor(this.valuetot[indsort]);
       /**
      * Saulo 26/08/2016 - Correcao pois a serieMapa ficava menor que o tamanho desejado quando repetia a data no mapa
      */
      //String dataFicticia="01/01/"+anoFicticio;
      //dado.setData(this.dataStr[k]);
      //serieMapa.put(this.dataStr[k], dado);
       
     }
     seriesBoot.add(serien); 
     System.out.println("Numero de amostras =   "+(i+1));
   }
 

return seriesBoot;
 
   }
    
      public void obterSerieBootstrap (int namostras) {
    	
    	this.namostras=namostras;
    	PsRandom ran = new PsRandom();
    	int N = this.valuetot.length;
    	
    	ArrayList<Map<String,DadoTemporal>> seriesBoot = new ArrayList<Map<String,DadoTemporal>>();
    	for (int i=0;i<namostras;i++){
    		Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>() ;	
    		Integer anoFicticio=1900;
    		for (int k=0;k<this.valuetot.length;k++){
    			int indsort = ran.nextInteger(N-1);
    			
    			if(indsort == 0){
    				System.out.println();
    			}
    			DadoTemporal dado = new DadoTemporal();
    			dado.setValor(this.valuetot[indsort]);
    			/**
    			 * Saulo 26/08/2016 - nao precisa corrigir a data pos ele sempre coloca data diferente
    			 */
    			String dataFicticia="01/01/"+anoFicticio;
    			//dado.setData(this.dataStr[k]);
    			//serieMapa.put(this.dataStr[k], dado);
    			
    			dado.setData(this.dataStr[k]);
    			serieMapa.put(this.dataStr[k], dado);
    			anoFicticio++;
    		}
    		seriesBoot.add(serieMapa);	
    		System.out.println("Numero de amostras =   "+(i+1));
    	}
    
    	this.setSeriesBootstrapMapa(seriesBoot);
    
	    }
  
      
      
      
      
      public ArrayList<double[]> pegarSerieBootstrap (int namostras) {
      	
      	this.namostras=namostras;
      	PsRandom ran = new PsRandom();
      	int N = this.valuetot.length;
      	
      	ArrayList<double[]> seriesBoot = new ArrayList<double[]>();
      	for (int i=0;i<namostras;i++){
      		
      		Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>() ;	
      		double[] serien=new double[N];
      		Integer anoFicticio=1900;
      		for (int k=0;k<this.valuetot.length;k++){
      			int indsort = ran.nextInteger(N-1);
      			if(indsort == 0){
      				System.out.println();
      			}
      			serien[k]=this.valuetot[indsort];
      			DadoTemporal dado = new DadoTemporal();
      			dado.setValor(this.valuetot[indsort]);
      			/**
    			 * Saulo 26/08/2016 - Correcao pois a serieMapa ficava menor que o tamanho desejado quando repetia a data no mapa
    			 */
    			String dataFicticia="01/01/"+anoFicticio;
    			//dado.setData(this.dataStr[k]);
    			//serieMapa.put(this.dataStr[k], dado);
      			dado.setData(this.dataStr[k]);
      			serieMapa.put(this.dataStr[k], dado);
      			
      		}
      		seriesBoot.add(serien);	
      		System.out.println("Numero de amostras =   "+(i+1));
      	}
      
      //	this.setSeriesBootstrapMapa(seriesBoot);
		return seriesBoot;
      
  	    }
      
      
      
      public ArrayList<double[]> pegarSerieBootstrap (int namostras,int N) {
        	
        	this.namostras=namostras;
        	
        	int n = this.valuetot.length;
        	
        	ArrayList<double[]> seriesBoot = new ArrayList<double[]>();
        	PsRandom ran = new PsRandom();
        	for (int i=0;i<namostras;i++){
        		
        		//Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>() ;	
        		double[] serien=new double[N];
        		for (int k=0;k<N;k++){
        			
        			int indsort = ran.nextInteger(n-1);
        			if(indsort == 0){
        				System.out.println();
        			}
        			serien[k]=this.valuetot[indsort];
        			//DadoTemporal dado = new DadoTemporal();
        			//dado.setValor(this.valuetot[indsort]);
        			
        			//dado.setData(this.dataStr[k]);
        			//serieMapa.put(this.dataStr[k], dado);
        			
        		}
        		seriesBoot.add(serien);	
        		System.out.println("Numero de amostras =   "+(i+1));
        	}
        
        //	this.setSeriesBootstrapMapa(seriesBoot);
  		return seriesBoot;
        
    	    }
      
      public  ArrayList<double []> obterDistribValorCritico(int tipoHipotese, double nivelSignificancia){
        	this.tipoHipotese=tipoHipotese;
        	this.nivelSignificancia=nivelSignificancia;
    		
    		
    		
    		ArrayList<double []> valoresCriticos=new ArrayList<double []>();
    		ArrayList<ArrayList<Double>> resultTotTestes = new ArrayList<ArrayList<Double>>();
    		
    		
    		
    		//int ntestes=14;
    		int ntestes=1;//Saulo - 21/04/2020
    		for (int i=0;i<ntestes;i++){
    		ArrayList<Double> r = new ArrayList<Double>();
    		resultTotTestes.add(r);
    		}
    		
    		
    		
    		for (int i=0;i<this.namostras;i++){
    		
    	     Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();	
    	     Map<String,DadoTemporal> serieMapa1=new HashMap<String,DadoTemporal>();
    	     Map<String,DadoTemporal> serieMapa2=new HashMap<String,DadoTemporal>();
    		 serieMapa=this.getSeriesBootstrapMapa().get(i);
    		 //int flagsent=1;
    		 //serieMapa1=this.pegarParteDaSerie(serieMapa, anodiv, flagsent);
    		 //flagsent=2;
   		     //serieMapa2=this.pegarParteDaSerie(serieMapa, anodiv, flagsent);
    		     		 
   		    boolean fazerMK=true;
    		if(fazerMK){
    			MannKendallTest mk = new MannKendallTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
    			double cf=1;
      			mk.teste1(cf);	
      			//result.add(mk.getEstattesteBootstrap());
      			//resultTotTestes.get(0).add(mk.getEstattesteBootstrap());
      			resultTotTestes.get(0).add(mk.getEstatteste());//Saulo 21/04/2020
    		}
    		
    		    
  			
    		/*if(opTestes.get(1)){    
    		Spearman sp = new Spearman(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  			sp.trend();	
  			resultTotTestes.get(1).add(sp.getEstattesteBootstrap());
    		}
  						
    		
  			if(opTestes.get(2)){    
    		RegressaoLinearTest rl = new RegressaoLinearTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  		    rl.teste1();
  		     resultTotTestes.get(2).add(rl.getEstattesteBootstrap());
    		}
    		    
  		    
  		    
  		    if(opTestes.get(3)){   
    		Testet tt = new Testet(serieMapa1,serieMapa2,this.tipoHipotese,this.nivelSignificancia);
  			tt.teste1();
  			resultTotTestes.get(3).add(tt.getEstattesteBootstrap());
    		}	
    		    	
  			if(opTestes.get(4)){
      		DistributionFreeCUSUMTest df = new DistributionFreeCUSUMTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  			df.teste1();
  			resultTotTestes.get(4).add(df.getEstattesteBootstrap());
    		}	
      		
  			if(opTestes.get(5)){
      		CumulativeDeviationTest cd = new CumulativeDeviationTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  			cd.teste1();
  			resultTotTestes.get(5).add(cd.getEstatteste());
    		}
      			
      		
  			if(opTestes.get(6)){  
      		WorsleyLikelihoodRatioTest wr = new WorsleyLikelihoodRatioTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  			wr.teste1();
  			resultTotTestes.get(6).add(wr.getEstatteste());
    		}
      			
      	
  			if(opTestes.get(7)){	   
        	MannWhitney mw = new MannWhitney(serieMapa1,serieMapa2,this.tipoHipotese,this.nivelSignificancia);
  			mw.rankSum();
  			resultTotTestes.get(7).add(mw.getEstattesteBootstrap());
    		}	
        			      			
  			if(opTestes.get(8)){
            TesteF tf = new TesteF(serieMapa1,serieMapa2,this.tipoHipotese,this.nivelSignificancia);
  			tf.teste1();	
  			resultTotTestes.get(8).add(tf.getEstattesteBootstrap());
    		}	
  			
         
  			if(opTestes.get(9)){	
          	MedianCrossingTest mc = new MedianCrossingTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  			mc.teste1();
  			resultTotTestes.get(9).add(mc.getEstatteste());
    		}	
          		
          
  			if(opTestes.get(10)){
          	NERC_aleatoriedade nc = new NERC_aleatoriedade(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  			nc.teste1();
  			resultTotTestes.get(10).add(nc.getEstatteste());
    		}
          		
  			if(opTestes.get(11)){
          	RankDifferenceTest rd = new RankDifferenceTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  			rd.teste1();
  			resultTotTestes.get(11).add(rd.getEstatteste());
    		}	
          		
  			if(opTestes.get(12)){
            AutocorrelationTest ac = new AutocorrelationTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  			ac.teste1();
  			resultTotTestes.get(12).add(ac.getEstatteste());
    		}	
              	
          
  			if(opTestes.get(13)){   	
    		WaldWolowitz ww = new WaldWolowitz(serieMapa,this.tipoHipotese,this.nivelSignificancia);
  			ww.teste1();
  			resultTotTestes.get(13).add(ww.getEstatteste());
    		}*/            
    		
    		
    		System.out.println("Numero de amostras =   "+(i+1));
    		
    		}
    		
    		
    		System.out.println();
    		double ns=-99999.0;
    		
    		//this.setResultadoTeste(result);
    		this.setResultTotTestes(resultTotTestes);
    		//int ntestes=14;
    		if(this.tipoHipotese ==1){
    			for (int i=0;i<ntestes;i++){
    			
    				
    			//if(opTestes.get(i)){
    				double [] vcrit=new double [2];
    				ArrayList<Double> result = new ArrayList<Double>();
    				result =resultTotTestes.get(i);
        			ns=(100-this.nivelSignificancia)/100.0;
        			vcrit[0]=this.obterVcritico(result, ns);
        			valoresCriticos.add(vcrit);
    			//}
    				
    			
    			
    			}
        		
        	}else if(this.tipoHipotese ==2){
        		
        		
        		for (int i=0;i<ntestes;i++){	
        		//if(opTestes.get(i)){
    				double [] vcrit=new double [2];
    				ArrayList<Double> result = new ArrayList<Double>();
    				result =resultTotTestes.get(i);
    				ns=(this.nivelSignificancia)/100.0;
        			vcrit[0]=this.obterVcritico(result, ns);
        			valoresCriticos.add(vcrit);
    			//}
        		
        		}
        		
        	}else{
        		for (int i=0;i<ntestes;i++){
        		
        		//if(opTestes.get(i)){
        			
		        		if(i == 5 || i == 6 || i > 8){
		        			double [] vcrit=new double [2];
		    				ArrayList<Double> result = new ArrayList<Double>();
		    				result =resultTotTestes.get(i);
		        			ns=(100-this.nivelSignificancia)/100.0;
		        			vcrit[0]=this.obterVcritico(result, ns);	
		        			vcrit[1]=vcrit[0];
		        			valoresCriticos.add(vcrit);
		        		}else{
		        			double [] vcrit=new double [2];
		    				ArrayList<Double> result = new ArrayList<Double>();
		    				result =resultTotTestes.get(i);
		        			double nsBicaudal=this.nivelSignificancia/2.0;
		         		    ns=(100-nsBicaudal)/100.0;
		         		    vcrit[1]=this.obterVcritico(result, ns);
		         		    ns=(nsBicaudal)/100.0;
		      		        vcrit[0]=this.obterVcritico(result, ns);
		      		      valoresCriticos.add(vcrit);
		        		}
        		//}
        		
        		}  
        		
        		
        	}
    		
    			    		
    		
  		return valoresCriticos;
    		
    		
      	  
    		
    	}
      
  
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      public  double [] obterDistribValorCritico(int opcaoTeste, int tipoHipotese, double nivelSignificancia, int anodiv){
      	this.tipoHipotese=tipoHipotese;
      	this.nivelSignificancia=nivelSignificancia;
  		double [] vcrit=new double [2];
  		
  		 ArrayList<Double> result = new ArrayList<Double>();
  		for (int i=0;i<this.namostras;i++){
  		
  	     Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();	
  	     Map<String,DadoTemporal> serieMapa1=new HashMap<String,DadoTemporal>();
  	     Map<String,DadoTemporal> serieMapa2=new HashMap<String,DadoTemporal>();
  		 serieMapa=this.getSeriesBootstrapMapa().get(i);
  		 int flagsent=1;
  		 serieMapa1=this.pegarParteDaSerie(serieMapa, anodiv, flagsent);
  		 flagsent=2;
 		 serieMapa2=this.pegarParteDaSerie(serieMapa, anodiv, flagsent);
  		 
  		 
  		 
  		switch(opcaoTeste){
  		  		
  		    case 0:
  		    double CF=1;    
  		    MannKendallTest mk = new MannKendallTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			mk.teste1(CF);	
			result.add(mk.getEstattesteBootstrap());
  		    
			break;
  		    
  		   /* case 1:
  		    
  		    Spearman sp = new Spearman(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			sp.trend();	
			result.add(sp.getEstattesteBootstrap());
						
  		    break;
  		    
  		    case 2:
  		    
  		    RegressaoLinearTest rl = new RegressaoLinearTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
		    rl.teste1();
		    result.add(rl.getEstattesteBootstrap());
  		    	
  		    break;
  		    
  		    case 3:
		         
  		    Testet tt = new Testet(serieMapa1,serieMapa2,this.tipoHipotese,this.nivelSignificancia);
			tt.teste1();
			result.add(tt.getEstattesteBootstrap());	
  		    	
    		break;
    		    
    		case 4:
    		  
    		DistributionFreeCUSUMTest df = new DistributionFreeCUSUMTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			df.teste1();
			result.add(df.getEstattesteBootstrap());	
    		
    		break;
    		    
    		case 5:
    		  
    		CumulativeDeviationTest cd = new CumulativeDeviationTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			cd.teste1();
			result.add(cd.getEstatteste());	
    			
    		break;
    		
    		case 6:
		       
    		WorsleyLikelihoodRatioTest wr = new WorsleyLikelihoodRatioTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			wr.teste1();
			result.add(wr.getEstatteste());	
    			
    		break;
      		    
      		case 7:
      		   
      		MannWhitney mw = new MannWhitney(serieMapa1,serieMapa2,this.tipoHipotese,this.nivelSignificancia);
			mw.rankSum();
			result.add(mw.getEstattesteBootstrap());	
      			      			
      		break;
      		    
      		case 8:
    		 
      		TesteF tf = new TesteF(serieMapa1,serieMapa2,this.tipoHipotese,this.nivelSignificancia);
			tf.teste1();	
			result.add(tf.getEstattesteBootstrap());	
			
        	break;
        		    
        	case 9:
        		
        	MedianCrossingTest mc = new MedianCrossingTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			mc.teste1();
			result.add(mc.getEstatteste());	
        		
        	break;
        		    
        	case 10:
        	
        	NERC_aleatoriedade nc = new NERC_aleatoriedade(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			nc.teste1();
			result.add(nc.getEstatteste());	
        		
        	break;
    	
        	case 11:
	          
        	RankDifferenceTest rd = new RankDifferenceTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			rd.teste1();
			result.add(rd.getEstatteste());	
        		
            break;
            		    
            case 12:
            
            AutocorrelationTest ac = new AutocorrelationTest(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			ac.teste1();
			result.add(ac.getEstatteste());	
            	
            break;
    		
    		
  		    default:
  		    	
  		    WaldWolowitz ww = new WaldWolowitz(serieMapa,this.tipoHipotese,this.nivelSignificancia);
			ww.teste1();
			result.add(ww.getEstatteste());	*/
  		            
  		}
  		
  		
  		System.out.println("Numero de amostras =   "+(i+1));
  		
  		}
  		
  		
  		System.out.println();
  		double ns=-99999.0;
  		
  		this.setResultadoTeste(result);
  		
  		if(this.tipoHipotese ==1){
  			ns=(100-this.nivelSignificancia)/100.0;
  			vcrit[0]=this.obterVcritico(result, ns);	
  			
      		//this.setTipoHipoteseStr("Unicauldal a direita");
      	}else if(this.tipoHipotese ==2){
      		
      		ns=(this.nivelSignificancia)/100.0;
  			vcrit[0]=this.obterVcritico(result, ns);
      		//this.setTipoHipoteseStr("Unicauldal a esquerda");
      	}else{
      		
      		
      		if(opcaoTeste == 5 || opcaoTeste == 6 || opcaoTeste > 8){
      			ns=(100-this.nivelSignificancia)/100.0;
      			vcrit[0]=this.obterVcritico(result, ns);
      			vcrit[1]=vcrit[0];
      		}else{
      			double nsBicaudal=this.nivelSignificancia/2.0;
       		    ns=(100-nsBicaudal)/100.0;
       		    vcrit[1]=this.obterVcritico(result, ns);
       		    ns=(nsBicaudal)/100.0;
    		    vcrit[0]=this.obterVcritico(result, ns);
      		}
      		
      		
   		    
      		//this.setTipoHipoteseStr("Bicauldal");
      		
      	}
  		
  		
  		
  		//vcrit[0]=this.obterVcritico(result, ns);
  		
  		
		return vcrit;
  		
  		
    	  
  		
  	}
  
      public double [] obterIntervaloConfianca(ArrayList<Double> distribVcrit, double ns){
    	  double nsBicaudal=ns/2.0;
		    double nsSup=(100-nsBicaudal)/100.0;
		    double nsInf=(nsBicaudal)/100.0;
    		ArrayList<Double> distribVcritOrdenado = new ArrayList<Double>();
    		 double [] vcrit=new double [2];
    		 double [] valoresVcrit=new double[distribVcrit.size()]; 
    		 for (int i=0;i<distribVcrit.size();i++){
    			 valoresVcrit[i]=distribVcrit.get(i);
    			 
    		 }
    		
    		 int[] indices=new int [distribVcrit.size()];
    		 indices = Esco7.ordenar(distribVcrit.size(), valoresVcrit);
    		 /**
    		  * distribuição empirica dos resultados - futuramente gráfico
    		  */
    		 for (int i=0;i<distribVcrit.size();i++){
    		 distribVcritOrdenado.add(valoresVcrit[indices[i]]);	
    		 }
    		 
    		/**
    		 * ns é um número de 0.0 - 0.9999 e corresponde ao nivel de significancia adotado quando se que bicauldal deve-se somar os valores extremos
    		 * o unicaudal a direita sao os valores mais altos, ex: 0.95 que seria o ns 5% unicauldal a direita da interface, o bicauldal deve-se avaliar o vcrit 0.975 e o vcrit 0.025 o 
    		 * unicaudal a esquerda é 0.05
    		 */
    		 
    		 int n=distribVcrit.size();
    		 int indporcentoInf=(int) ((n*nsInf)-1);
    		 int indporcentoSup=(int) ((n*nsSup)-1);
    		 vcrit[0]=valoresVcrit[indices[indporcentoInf]];
    		 vcrit[1]=valoresVcrit[indices[indporcentoSup]];  		 
    		 return vcrit;
    		
    		
    	}
      public double obterVcritico(ArrayList<Double> distribVcrit, double ns){
  		ArrayList<Double> distribVcritOrdenado = new ArrayList<Double>();
  		 double vcrit=-99999.0;
  		 double [] valoresVcrit=new double[distribVcrit.size()]; 
  		 for (int i=0;i<distribVcrit.size();i++){
  			 valoresVcrit[i]=distribVcrit.get(i);
  			 
  		 }
  		
  		 int[] indices=new int [distribVcrit.size()];
  		 indices = Esco7.ordenar(distribVcrit.size(), valoresVcrit);
  		 /**
  		  * distribuição empirica dos resultados - futuramente gráfico
  		  */
  		 for (int i=0;i<distribVcrit.size();i++){
  		 distribVcritOrdenado.add(valoresVcrit[indices[i]]);	
  		 }
  		 
  		/**
  		 * ns é um número de 0.0 - 0.9999 e corresponde ao nivel de significancia adotado quando se que bicauldal deve-se somar os valores extremos
  		 * o unicaudal a direita sao os valores mais altos, ex: 0.95 que seria o ns 5% unicauldal a direita da interface, o bicauldal deve-se avaliar o vcrit 0.975 e o vcrit 0.025 o 
  		 * unicaudal a esquerda é 0.05
  		 */
  		 
  		 int n=distribVcrit.size();
  		 int indporcento=(int) ((n*ns)-1);
  		 vcrit=valoresVcrit[indices[indporcento]];
  		   		 
  		 return vcrit;
  		
  		
  	} 
      
      public double obterPvalue(ArrayList<Double> distribVcrit, double vcrit){
    	  
    	  ArrayList<Double> distribVcritOrdenado = new ArrayList<Double>();
   		 double pvalue=-99999.0;
   		 double [] valoresVcrit=new double[distribVcrit.size()]; 
   		 for (int i=0;i<distribVcrit.size();i++){
   			 valoresVcrit[i]=distribVcrit.get(i);
   			 
   		 }
   		
   		 int[] indices=new int [distribVcrit.size()];
   		 indices = Esco7.ordenar(distribVcrit.size(), valoresVcrit);
   		 /**
   		  * distribuição empirica dos resultados - futuramente gráfico
   		  */
   		 for (int i=0;i<distribVcrit.size();i++){
   		 distribVcritOrdenado.add(valoresVcrit[indices[i]]);	
   		 }
   		 int n=distribVcritOrdenado.size();
   		 int ind=0;
   		 double valor=distribVcritOrdenado.get(ind);
   		 while(vcrit > valor && ind < (n-1)){
   		 
   		 ind++;	
   		 valor=distribVcritOrdenado.get(ind);
   		 
   		 /*if(ind == 998){
   		 System.out.println();
   		 }*/
   		 
   		 System.out.println(ind);
   		 }
   		 
   		 
   		 
   		 double posDistrib=(ind+ind-1.0)/2.0;
   		 if(ind == n-1 || ind == 0){
   		 posDistrib=n;	 
  		 }
   		 double pval=(posDistrib/n*1.0)*100.0;
   		 
   		 if(pval > 50){
   			 
   		 pvalue=100-pval;
   		  
   		 }else{
   			 
   		 pvalue=pval*1.0;
   		 
   		 }
   		 
   		 
   		 return pvalue;
    	  
    	  
      }
      
      
      public Map<String,DadoTemporal> pegarParteDaSerie(Map<String,DadoTemporal> serieMapa, int anodiv, int flagsent){
    	  
    	    Map<String,DadoTemporal> serieM1=new HashMap<String,DadoTemporal>();
		  
    	  	ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
    		Set<String> chavesMax =  serieMapa.keySet();
  	  	
    		for (String data : chavesMax){
  	  	    DadoTemporal dado = serieMapa.get(data);
  	  	    dados.add(dado);
  	   		}
  	  	
  	  	
      	    Collections.sort(dados);
        	
        	int k=0;
        	
        	for (int i=0;i<dados.size();i++){
        		 
        		if(flagsent == 1){
        			
        			if(dados.get(i).pegarAno() < anodiv){
        				
        				DadoTemporal dado1 = new DadoTemporal();
        				dado1.setValor(dados.get(i).getValor());
        				dado1.setData(dados.get(i).getData());
        				serieM1.put(dados.get(i).getDataStr(), dado1);
        			}
        			
        		}else{
        				if(dados.get(i).pegarAno() >= anodiv){
        				
        				DadoTemporal dado1 = new DadoTemporal();
        				dado1.setValor(dados.get(i).getValor());
        				dado1.setData(dados.get(i).getData());
        				serieM1.put(dados.get(i).getDataStr(), dado1);
        			}
        			
        				
        		}
        		
        		
        	}
    	  
        	
        	
        	
        	
    	  return serieM1; 
    	
      }
  
  
    public void ObterSerie (int namostras) {
    	
    	/*Stat stx = new Stat(this.valuex);
		stx.setDenominatorToNminusOne();
		double xmedia =stx.mean_as_double();
		double xdesvpad =stx.standardDeviation_as_double();
		
		Stat sty = new Stat(this.valuey);
		sty.setDenominatorToNminusOne();
		double ymedia =sty.mean_as_double(); 
		double ydesvpad =sty.standardDeviation_as_double();*/
		 
    	this.namostras=namostras;
    	Stat sttotal = new Stat(this.valuetot);
		//sttotal.setDenominatorToNminusOne();
		sttotal.setDenominatorToN();
		double totmedia =sttotal.mean_as_double(); 
		double totdesvpad =sttotal.standardDeviation_as_double();
		int N = this.valuetot.length;		
		
		
		
		PsRandom ran = new PsRandom();
		//int namostras=1000;
	     this.valuetotbootstrap=new double [namostras][N];
	     this.estatbootstrap=new double [namostras][3];
		for (int i=0;i<namostras;i++){
			double [] valuetotbootstrap2=new double [N];
			for (int k=0;k<this.valuetot.length;k++){
			int indsort = ran.nextInteger(N-1);
			this.valuetotbootstrap[i][k]=this.valuetot[indsort];
			valuetotbootstrap2[k]=this.valuetot[indsort];
			}
			Stat st = new Stat(valuetotbootstrap2);
			this.estatbootstrap[i][0]=st.mean_as_double();
			this.estatbootstrap[i][1]=st.standardDeviation_as_double();
			this.estatbootstrap[i][2]=st.median_as_double();
		}
		
		
		 
		 
	    }
    
    public double[][] getResultestteste() {
		return resultestteste;
	}

	public void setResultestteste(double[][] resultestteste) {
		this.resultestteste = resultestteste;
	}

	
	
	
	
	
	
	public void testesbooststrap () {
    	
      int ntestes=14;	 
      this.resultestteste= new double [this.namostras][ntestes];
      this.resultesttesteord= new double [this.namostras][ntestes];
     for (int i=0;i<this.namostras;i++){
    	 double [] valueboot=new double [this.valuetot.length];
    		
    	    for (int k=0;k<this.valuetot.length;k++){
    		valueboot[k]=this.valuetotbootstrap[i][k];	
    		}
    		
    	    
    	    
    	    //testes TENDENCIA
    		MannKendallTest mk = new MannKendallTest(valueboot);
    		double CF=1;
			mk.teste1(CF);	
			this.resultestteste[i][0]=mk.getEstatteste();
			
			/*Spearman sp = new Spearman(valueboot);
			sp.teste1();
			this.resultestteste[i][1]=sp.getEstatteste();
			
			RegressaoLinearTest rl = new RegressaoLinearTest(valueboot);
			rl.teste1();
			this.resultestteste[i][2]=rl.getEstatteste();
			
			//testes MÉDIA
			
			DistributionFreeCUSUMTest df = new DistributionFreeCUSUMTest(valueboot);
			df.teste1();
			this.resultestteste[i][3]=df.getEstatteste();
			
			CumulativeDeviationTest cd = new CumulativeDeviationTest(valueboot);
			cd.teste1();
			this.resultestteste[i][4]=cd.getEstatteste();
			
			
			WorsleyLikelihoodRatioTest wr = new WorsleyLikelihoodRatioTest(valueboot);
			wr.teste1();
			this.resultestteste[i][5]=wr.getEstatteste();
			
			
			MannWhitney mw = new MannWhitney(valueboot);
			mw.teste1();
			this.resultestteste[i][6]=mw.getEstatteste();
			
			
			Testet tt = new Testet(valueboot);
			tt.teste1();
			this.resultestteste[i][7]=tt.getEstatteste();
			
			
			//testes VARIANCIA
			
			TesteF tf = new TesteF(valueboot);
			tf.teste1();
			this.resultestteste[i][8]=tf.getEstatteste();
			
			
			//testes INDEPENDENCIA
			
			MedianCrossingTest mc = new MedianCrossingTest(valueboot);
			mc.teste1();
			this.resultestteste[i][9]=mc.getEstatteste();
			
			
			NERC_aleatoriedade nc = new NERC_aleatoriedade(valueboot);
			nc.teste1();
			this.resultestteste[i][10]=nc.getEstatteste();
			
			
			RankDifferenceTest rd = new RankDifferenceTest(valueboot);
			rd.teste1();
			this.resultestteste[i][11]=rd.getEstatteste();
			
			
			AutocorrelationTest ac = new AutocorrelationTest(valueboot);
			ac.teste1();
			this.resultestteste[i][12]=ac.getEstatteste();
			
			
			WaldWolowitz ww = new WaldWolowitz(valueboot);
			ww.teste1();
			this.resultestteste[i][13]=ww.getEstatteste();*/
			
			System.out.println("Numero de amostras  =  "+i);
			
    		
    		
    	}
    	
     
     this.valorescriticos=new double[3][ntestes];
     for (int k=0;k<ntestes;k++){
    	 double [] conjuntoteste=new double[this.namostras];
	     for (int i=0;i<this.namostras;i++){
	      conjuntoteste[i]=this.resultestteste[i][k];	 
	     }
     
	    int[] indices=new int [this.namostras];
		indices = Esco7.ordenar(conjuntoteste.length, conjuntoteste) ;   
	     
			for (int i=0;i<this.namostras;i++){
			this.resultesttesteord[i][k]=conjuntoteste[indices[i]];	
			}
			int ind1porcento=(int) ((this.namostras*0.99)-1);
			int ind5porcento=(int) ((this.namostras*0.95)-1);
			int ind10porcento=(int) ((this.namostras*0.90)-1);
			
			
			
			this.valorescriticos[0][k]=conjuntoteste[indices[ind1porcento]];
			this.valorescriticos[1][k]=conjuntoteste[indices[ind5porcento]];
			this.valorescriticos[2][k]=conjuntoteste[indices[ind10porcento]];
			
	        System.out.println("Novo Vcrit para NS de 1% = "+this.valorescriticos[0][k]);
	        System.out.println("Novo Vcrit para NS de 5% = "+this.valorescriticos[1][k]);
	        System.out.println("Novo Vcrit para NS de 10% = "+this.valorescriticos[2][k]);
			
			
     }
    	
    	
    	
    	
    }
    
	
	
	
	
	
	
    public double[][] getValuetotbootstrap() {
		return valuetotbootstrap;
	}

	public void setValuetotbootstrap(double[][] valuetotbootstrap) {
		this.valuetotbootstrap = valuetotbootstrap;
	}

	public double[][] getEstatbootstrap() {
		return estatbootstrap;
	}

	public void setEstatbootstrap(double[][] estatbootstrap) {
		this.estatbootstrap = estatbootstrap;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String dir="C:\\OpenJump150\\Exemplos\\ArqTestes\\";
		String filename="QmensaisPonteNovaParaopeba.dat";
		StringTokenizer tok;
		double [][] value=new double[1000][1000];
		
		 
			BufferedReader file;
			try {
			file = new BufferedReader(new FileReader(dir + filename));
			String str = file.readLine();
			str = file.readLine();
			tok = new StringTokenizer(str);
			
			int ngauges = Integer.valueOf(tok.nextToken(" ").trim());
			int [] nvalues=new int [1000];
			for (int i = 0; i < ngauges; i++){
			str = file.readLine();
			tok = new StringTokenizer(str);
			int cod = Integer.valueOf(tok.nextToken(" ").trim());
			str = file.readLine();
			tok = new StringTokenizer(str);
			nvalues [i] = Integer.valueOf(tok.nextToken(" ").trim());
			str = file.readLine();
			tok = new StringTokenizer(str);
					for (int j = 0; j < nvalues[i]; j++){
					value[i][j]=Double.valueOf(tok.nextToken("	").trim());	
					}
			
			}
			//cod=35 - sugar creek -exemplo RAO
			double [] param=new double[10];
			int cod=0;
			double [] x = new double [nvalues [cod]];
			for (int j = 0; j < nvalues[cod]; j++){
			x[j]=value[cod][j];	
			System.out.println(x[j]);
			}
			int n=nvalues[cod];
			
			Bootstrap tt = new Bootstrap(x);
			int namostras=1000;
			tt.ObterSerie(namostras);
			tt.testesbooststrap();
			
			
			
			
			
			
			
			
			
		
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

	public ArrayList<ArrayList<Double>> getResultTotTestes() {
		return resultTotTestes;
	}

	public void setResultTotTestes(ArrayList<ArrayList<Double>> resultTotTestes) {
		this.resultTotTestes = resultTotTestes;
	}
	 

}
