package types;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;

import util.Dif;
import util.ST_valoresDiariosIntervaloDataSemFalhaMapa;
import util.ST_verificarNdiasFevereiro;

//import org.snirh.extremos_unb.tipos.DadoTemporal;
//import org.snirh.extremos_unb.util.Dif;
//import org.snirh.extremos_unb.util.ST_valoresDiariosIntervaloDataSemFalhaMapa;
//import org.snirh.extremos_unb.util.ST_verificarNdiasFevereiro;


	public class SerieTemporal {

		private ArrayList<DadoTemporal>dados;
		private String nomedasérie;
		private String discretizaTemporal;
		private JFileChooser chooser;
		private Map<Date,DadoTemporal>mapaDados;
		private Map<Calendar,DadoTemporal>mapaCalDados;
		private Map<String,DadoTemporal>mapaStrDados;
		private Calendar dataInicialSerie;
		private Calendar dataFinalSerie;
		private ArrayList<String>serieDatas;
	    private ArrayList<DadoTemporal>dadosAdmMLT;
		private Map<String,DadoTemporal>mapaStrDadosAdmMLT;
		private Double percfalhatemp;
		
		private String dataInicialSerieStr;
		private String dataFinalSerieStr;
		
	   private int nanos;
	   private int nanosSemFalha;
		
	   private Double [][] estatisticaSerieTemporal;
	   
	   private String [] nomeEstatistica;
	   
	   /**
	    * Saulo 08/05/2014 para máximos
	    */
	   //private Momentos momentos;
	  // private MomentosL momentosL;
	   //private DescriptiveStatistics dsc;
	   
	   
	   private int nanosConsSemFalha;
	   private int[] nanosMesConsSemFalha;
	   
	   /**
		 * numero de anos completo e sem falha nos período selecionados de restrição - Saulo - 16_01_2017
		 */
	   private int nanosSemFalhaPeriodoTotalRestricao;
	   
	   
	   
	   /**
	    * Saulo - 2019
	    */
	   private double ndadosBrutos;
	   private double percBrutos;
	   private double percConsistidos;
	   private double ndadosConsistidos;
	   private double ntotalComfalha=0.0;
	   private double ntotalSemfalha=0.0;
	   private double ntotal=0.0;
	   private double percFalhas = 0.0;
	   
	   
	   public void setaMapaStrDadoTemporal(){
			
			this.mapaStrDados=new HashMap<String,DadoTemporal>();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			for(int i=0;i < this.dados.size();i++ ){
			String datastr =formatter.format(this.dados.get(i).getData());	
			this.mapaStrDados.put(datastr, this.dados.get(i));
			//System.out.println(datastr);
			}
			
			System.out.println();
		}
	   
	   
	   public Double pegarValorAnoSerieAnual(int ano){
		   Double valor=-99999.;
		   for(int i=0;i<this.dados.size();i++){
			   if(this.dados.get(i).eDoAno(ano)){
				   valor=this.dados.get(i).getValor();
			   }
		   }
		   
		   
		return valor;
		 
	   }
	   
	   
	   public Date pegarDateAnoSerieAnual(int ano){
		   Date data=null;
		   for(int i=0;i<this.dados.size();i++){
			   if(this.dados.get(i).eDoAno(ano)){
				   data=this.dados.get(i).getData();
			   }
		   }
		   
		   
		return data;
		 
	   }
	   
	   public Double[][] getEstatisticaSerieTemporal() {
			return estatisticaSerieTemporal;
		}


		public void setEstatisticaSerieTemporal(Double[][] estatisticaSerieTemporal) {
			this.estatisticaSerieTemporal = estatisticaSerieTemporal;
		}


	   public int getNanos() {
			return nanos;
		}


		public void setNanos(int nanos) {
			this.nanos = nanos;
		}


		public int getNanosSemFalha() {
			return nanosSemFalha;
		}


		public void setNanosSemFalha(int nanosSemFalha) {
			this.nanosSemFalha = nanosSemFalha;
		}
		
		
		
		public static int getMaxDiasMes(int year,int mes) {  
		    Calendar cal = (Calendar) Calendar.getInstance().clone();  
		      
		    cal.set(Calendar.YEAR, year);  
		    cal.set(Calendar.MONTH, mes-1); // Fevereiro  
		    return cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
		}
		
		
		
		
	  public String getDataInicialSerieStr() {
			return dataInicialSerieStr;
		}

		public void setDataInicialSerieStr(String dataInicialSerieStr) {
			this.dataInicialSerieStr = dataInicialSerieStr;
		}


		public String getDataFinalSerieStr() {
			return dataFinalSerieStr;
		}


		public void setDataFinalSerieStr(String dataFinalSerieStr) {
			this.dataFinalSerieStr = dataFinalSerieStr;
		}

		
		
		




	  public Map<Date,DadoTemporal> getMapaDados() {
			return mapaDados;
		}

		public void setMapaDados(Map<Date,DadoTemporal> mapaDados) {
			this.mapaDados = mapaDados;
		}

		public Map<Calendar,DadoTemporal> getMapaCalDados() {
			return mapaCalDados;
		}

		public void setMapaCalDados(Map<Calendar,DadoTemporal> mapaCalDados) {
			this.mapaCalDados = mapaCalDados;
		}

		public Calendar getDataInicialSerie() {
			return dataInicialSerie;
		}

		public void setDataInicialSerie(Calendar dataInicialSerie) {
			this.dataInicialSerie = dataInicialSerie;
		}

		public Calendar getDataFinalSerie() {
			return dataFinalSerie;
		}

		public void setDataFinalSerie(Calendar dataFinalSerie) {
			this.dataFinalSerie = dataFinalSerie;
		}





		public ArrayList<String> getSerieDatas() {
			return serieDatas;
		}


		public void setSerieDatas(ArrayList<String> serieDatas) {
			this.serieDatas = serieDatas;
		}

		public ArrayList<DadoTemporal> getDadosAdmMLT() {
			return dadosAdmMLT;
		}

		public void setDadosAdmMLT(ArrayList<DadoTemporal> dadosAdmMLT) {
			this.dadosAdmMLT = dadosAdmMLT;
		}

		public Map<String,DadoTemporal> getMapaStrDadosAdmMLT() {
			return mapaStrDadosAdmMLT;
		}

		public void setMapaStrDadosAdmMLT(Map<String,DadoTemporal> mapaStrDadosAdmMLT) {
			this.mapaStrDadosAdmMLT = mapaStrDadosAdmMLT;
		}
	  
	  	
		public Map<String, DadoTemporal> getMapaStrDados() {
		return mapaStrDados;
	}

	public void setMapaStrDados(Map<String, DadoTemporal> mapaStrDados) {
		this.mapaStrDados = mapaStrDados;
		
		if(this.dados == null){
			this.dados=new ArrayList<DadoTemporal>();
			Set<String> chaves = this.mapaStrDados.keySet();
		    for (String data : chaves){
		    	DadoTemporal dado = new DadoTemporal();
	 		  	dado= this.mapaStrDados.get(data);
	 		  	this.dados.add(dado);
			}
		
			Collections.sort(this.dados);
		}
		
		
	}

		public ArrayList<DadoTemporal> getDados() {
			return dados;
		}

		public void setDados(ArrayList<DadoTemporal> dados) {
			this.dados = dados;
		}

		public String getNomedasérie() {
			return nomedasérie;
		}

		public void setNomedasérie(String nomedasérie) {
			this.nomedasérie = nomedasérie;
		}
		
		
		
		public int IndiceData (Date data){
			int indice=-99999;
			for(int i=1;i < dados.size();i++ ){
				if(data.equals(dados.get(i).getData())){
				indice=i;	
				break;
				}
		
			}
		return indice;
		}
		
		
		public double ValorData (Date data){
			double valor=-99999;
			valor=dados.get(this.IndiceData(data)).getValor();
			return valor;
		}

		public String getDiscretizaTemporal() {
			return discretizaTemporal;
		}

		public void setDiscretizaTemporal(String discretizaTemporal) {
			this.discretizaTemporal = discretizaTemporal;
		}
		
		
		
		public void setaMapaDadoTemporal(){
			
			this.mapaDados=new HashMap<Date,DadoTemporal>();
			for(int i=0;i < this.dados.size();i++ ){
			this.mapaDados.put(this.dados.get(i).getData(), this.dados.get(i));
			}
			
		}
		
	   		

		public Double getPercfalhatemp() {
			return percfalhatemp;
		}



		public void setPercfalhatemp(Double percfalhatemp) {
			this.percfalhatemp = percfalhatemp;
		}


		public String [] getNomeEstatistica() {
			return nomeEstatistica;
		}


		public void setNomeEstatistica(String [] nomeEstatistica) {
			this.nomeEstatistica = nomeEstatistica;
		}


		


		public int getNanosConsSemFalha() {
			return nanosConsSemFalha;
		}


		public void setNanosConsSemFalha(int nanosConsSemFalha) {
			this.nanosConsSemFalha = nanosConsSemFalha;
		}


		public int[] getNanosMesConsSemFalha() {
			return nanosMesConsSemFalha;
		}


		public void setNanosMesConsSemFalha(int[] nanosMesConsSemFalha) {
			this.nanosMesConsSemFalha = nanosMesConsSemFalha;
		}


	public   Map<String,DadoTemporal> serieDiariaIntervaloMensalMapa(int mesIni, int mesFim,int anoIni,int anoFim,Map<String,DadoTemporal> mapaStrDadosger) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Map<String,DadoTemporal> dadosEstatistica = new HashMap<String,DadoTemporal>();
			
			int [] ano = verificarFalhaAno(anoIni,anoFim);
			if(ano[0]>ano[1])return dadosEstatistica;
			Date utilDateIni = this.pegarDataInicioFiltroMes(mesIni, mesFim,ano[0],ano[1]);
			Date utilDateIniMesFim = this.pegarDataMesFinalFiltroMes(mesIni, mesFim,ano[0],ano[1]);
			Date utilDateFim = this.pegarDataFinalFiltroMes(mesIni, mesFim,ano[0],ano[1]);
			
			Calendar clStart =Calendar.getInstance();
			Calendar clEnd =Calendar.getInstance();
			Calendar clStartfim =Calendar.getInstance();
			
			clEnd.setTime(utilDateFim);
			clStart.setTime(utilDateIni);
			clStartfim.setTime(utilDateIniMesFim);
			
			
			
			int ndiasSemFalha=Dif.dias(utilDateIni, utilDateIniMesFim)+1;
			//double toleranciaMaxFalha=tolFalha;
			Map<String,DadoTemporal> dados_anual;
			Map<String,DadoTemporal> dados_filtro;
			ArrayList<String> serieData = new ArrayList<String>();
			dados_anual=new HashMap<String,DadoTemporal>();
			double totdados=0;
			double totdias=0;
			while (clStartfim.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {
					//dados_anual=new HashMap<String,DadoTemporal>();
		 	        dados_anual=ST_valoresDiariosIntervaloDataSemFalhaMapa.serieDiaria(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
		 	        
		 	        
		 	        double percentualDeFalhas=100.0-((dados_anual.size()*1.0/ndiasSemFalha*1.0)*100.0);
		 	        this.setPercfalhatemp(percentualDeFalhas);
		 	        totdados=dados_anual.size()+totdados;
		 	        totdias=dados_anual.size()+totdias;
		 	        
		 	        
		 	        clStart.add(Calendar.YEAR, 1);
					clStartfim.add(Calendar.YEAR, 1);
					utilDateIni=clStart.getTime();
					utilDateIniMesFim=clStartfim.getTime();
							
					if(mesFim == 2){
					utilDateIniMesFim=ST_verificarNdiasFevereiro.verificarNdiasFevereiro(utilDateIniMesFim, mesFim);
					}
					ndiasSemFalha=Dif.dias(utilDateIni, utilDateIniMesFim)+1;
			}
			
			    
				//dados_anual=new HashMap<String,DadoTemporal>();
			//dados_anual=this.valoresIntervaloDataSemFalhaMapa(utilDateIni, utilDateIniMesFim,mapaStrDadosger);
				
			//double percentualDeFalhas=100.0-((totdados*1.0/totdias*1.0)*100.0);
			//this.setPercfalhatemp(percentualDeFalhas);
			
			
			return dados_anual;

		}	


	public int[] verificarFalhaAno(int anoIni, int anoFim){
		
		int [] ano = new int [2];
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =this.getDataInicialSerie().getTime();
		Date dfimSerie =this.getDataFinalSerie().getTime();
		
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
		String datastrfim =formatter.format(dfimSerie);
		String[] strfim = datastrfim.split("/");
		if(anoIni == -99999){
		anoIni=Integer.parseInt(strini[2]);	
		}
		
		if(anoFim == -99999){
		anoFim=Integer.parseInt(strfim[2]);	
		}
		ano[0]=anoIni;
		ano[1]=anoFim;
		
		
		return ano;
		
		
		
		
	}


	public Date pegarDataInicioFiltroMes(int mesIni,int mesFim,int anoIni, int anoFIm){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =this.getDataInicialSerie().getTime();
		Date dfimSerie =this.getDataFinalSerie().getTime();
		Date utilDateIni = null;
		
		
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
		String datastrInicial = null;
		if(mesIni == mesFim){
			mesFim=mesIni;
			datastrInicial="01/"+mesIni+"/"+anoIni;
		}else{
				
			if(mesIni > mesFim)	{
				int anoInicial=anoIni;
				anoInicial=anoInicial-1;
				datastrInicial="01/"+mesIni+"/"+anoInicial;
					
			}else{
				datastrInicial="01/"+mesIni+"/"+anoIni;
			}
	   }
		
		try {
			utilDateIni = formatter.parse(datastrInicial);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateIni;	

	}


	public Date pegarDataMesFinalFiltroMes(int mesIni,int mesFim,int anoIni, int anoFIm){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =this.getDataInicialSerie().getTime();
		Date dfimSerie =this.getDataFinalSerie().getTime();
		Date utilDateIniMesFim = null;	
		String datastrini =formatter.format(diniSerie);
		String[] strini = datastrini.split("/");
			
		String datastrInicial = null;
		String datastrInicialMesFinal = null;
			
		if(mesIni == mesFim){
				int ano=anoIni;
			    int ndias=getMaxDiasMes(ano,mesFim);
				datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoIni;
		}else{
				if(mesIni > mesFim)	{
					int ano=anoIni;
					int anoMesFim=ano;
			        int ndias=getMaxDiasMes(anoMesFim,mesFim);
					datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoMesFim;;
				}else{
					int ano=anoIni;
			        int ndias=getMaxDiasMes(ano,mesFim);
					datastrInicialMesFinal=ndias+"/"+mesFim+"/"+anoIni;
				}
		}
		
		try {
			utilDateIniMesFim = formatter.parse(datastrInicialMesFinal);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateIniMesFim;	

	}

	public Date pegarDataFinalFiltroMes(int mesIni,int mesFim,int anoIni, int anoFIm){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diniSerie =this.getDataInicialSerie().getTime();
		Date dfimSerie =this.getDataFinalSerie().getTime();
		
		Date utilDateFim = null;
		String datastrfim =formatter.format(dfimSerie);
		String[] strfim = datastrfim.split("/");
		String datastrFinal = null;
		
		
		if(mesIni == mesFim){
			int anoFinal=anoFIm;
			int anoFinalMesFim=anoFinal;
		    int ndiasFinal=getMaxDiasMes(anoFinalMesFim,mesFim);
			datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFIm;
		}else{
		
			if(mesIni > mesFim)	{
				int anoFinal=anoFIm;
				int anoFinalMesFim=anoFinal+1;
		        int ndiasFinal=getMaxDiasMes(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFinalMesFim;	
			}else{
				int anoFinal=anoFIm;
				int anoFinalMesFim=anoFinal;
		        int ndiasFinal=getMaxDiasMes(anoFinalMesFim,mesFim);
				datastrFinal=ndiasFinal+"/"+mesFim+"/"+anoFIm;	
			}
			
			
		}
		
		try {
			utilDateFim = formatter.parse(datastrFinal);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return utilDateFim;
		

	}




	public int pegarAnoMetadeSerie(int anoIni,int anoFim){
		int anoMetade = 0;
		
		int [] ano = new int [2];
		int nanos=anoFim-anoIni+1;
		int metade=nanos/2;
		if(metade%2 == 0){
			anoMetade=anoIni;
		}else{
			anoMetade=anoIni-1;	
		}

		for (int i=0;i<metade;i++){
		anoMetade++;		
		}
			 
		return anoMetade;
		
	}


	public int pegarAnoMetadeSerie(String datastrini,String datastrfim){
		int anoMetade = 0;
		
		int [] ano = new int [2];
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String[] strini = datastrini.split("/");
		String[] strfim = datastrfim.split("/");
		
		int anoIni=Integer.parseInt(strini[2]);
		int anoFim=Integer.parseInt(strfim[2]);
		
		int nanos=anoFim-anoIni+1;
		int metade=nanos/2;
		
		
		if(metade%2 == 0){
			anoMetade=anoIni;
		}else{
			anoMetade=anoIni-1;	
		}
		
		
		
		for (int i=0;i<metade;i++){
		anoMetade++;		
		}
			 
		return anoMetade;
		
	}

	public int pegarAnoMetadeSerie(){
	int anoMetade = 0;

	int [] ano = new int [2];
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date diniSerie =this.getDataInicialSerie().getTime();
	Date dfimSerie =this.getDataFinalSerie().getTime();

	String datastrini =formatter.format(diniSerie);
	String[] strini = datastrini.split("/");
	String datastrfim =formatter.format(dfimSerie);
	String[] strfim = datastrfim.split("/");

	int anoIni=Integer.parseInt(strini[2]);
	int anoFim=Integer.parseInt(strfim[2]);

	int nanos=anoFim-anoIni+1;
	int metade=nanos/2;


	if(metade%2 == 0){
		anoMetade=anoIni;
	}else{
		anoMetade=anoIni-1;	
	}



	for (int i=0;i<metade;i++){
	anoMetade++;		
	}
		 
	return anoMetade;

	}




	public Date verficiarNdiasFevereiro(Date utilDateIniMesFim, int mesFim){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fev =formatter.format(utilDateIniMesFim);
		String[] fevstr = fev.split("/");
		Date utilDateNovoIniMesFim = null;
		int anoFinal=Integer.parseInt(fevstr[2]);
		int ndiasFinal=getMaxDiasMes(anoFinal,mesFim);
		int diasAtual=Integer.parseInt(fevstr[0]);
		if(diasAtual != ndiasFinal){
				String novaDataStr=ndiasFinal+"/"+mesFim+"/"+fevstr[2];
				try {
				utilDateNovoIniMesFim=formatter.parse(novaDataStr);
				} catch (ParseException e) {
				e.printStackTrace();
				}
		}else{
			utilDateNovoIniMesFim=utilDateIniMesFim;	
		}
		return utilDateNovoIniMesFim;
		
	}








	public int getNanosSemFalhaPeriodoTotalRestricao() {
		return nanosSemFalhaPeriodoTotalRestricao;
	}


	public void setNanosSemFalhaPeriodoTotalRestricao(
			int nanosSemFalhaPeriodoTotalRestricao) {
		this.nanosSemFalhaPeriodoTotalRestricao = nanosSemFalhaPeriodoTotalRestricao;
	}


	public double getNdadosBrutos() {
	  return ndadosBrutos;
	}


	public void setNdadosBrutos(double ndadosBrutos) {
	  this.ndadosBrutos = ndadosBrutos;
	}


	public double getPercBrutos() {
	  return percBrutos;
	}


	public void setPercBrutos(double percBrutos) {
	  this.percBrutos = percBrutos;
	}


	public double getPercConsistidos() {
	  return percConsistidos;
	}


	public void setPercConsistidos(double percConsistidos) {
	  this.percConsistidos = percConsistidos;
	}


	public double getNdadosConsistidos() {
	  return ndadosConsistidos;
	}


	public void setNdadosConsistidos(double ndadosConsistidos) {
	  this.ndadosConsistidos = ndadosConsistidos;
	}


	public double getNtotalComfalha() {
	  return ntotalComfalha;
	}


	public void setNtotalComfalha(double ntotalComfalha) {
	  this.ntotalComfalha = ntotalComfalha;
	}


	public double getNtotalSemfalha() {
	  return ntotalSemfalha;
	}


	public void setNtotalSemfalha(double ntotalSemfalha) {
	  this.ntotalSemfalha = ntotalSemfalha;
	}


	public double getNtotal() {
	  return ntotal;
	}


	public void setNtotal(double ntotal) {
	  this.ntotal = ntotal;
	}


	public double getPercFalhas() {
	  return percFalhas;
	}


	public void setPercFalhas(double percFalhas) {
	  this.percFalhas = percFalhas;
	}
	
	
}
