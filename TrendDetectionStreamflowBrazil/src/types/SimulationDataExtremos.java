package types;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;



import com.vividsolutions.jump.feature.FeatureCollection;
import com.vividsolutions.jump.workbench.model.Category;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;

public class SimulationDataExtremos {

	//public SimulationDataExtremos {}
		
 // private FeatureCollection fcbaseHintegrada;
 // private FeatureCollection fcbaseOttobacias;
  
  private boolean consultaLatLonEstacao;
  //private ConfiguracaoGUILatLon configLatLon;
  
	private Map<String,ArrayList<Double>> dadosOttobaciasBD;


	private Map<String,ArrayList<Double>> indiceOttobaciasBD;
	private ArrayList<String> nomeCampoIndice1;
	
	private int nmaxTesteOutlier;
	private double limValor;
	private double raioMax;
	private double lInfREL;
	private double lSupREL;
	
	
	/**
	 * Saulo - 10/04/2017
	 */
  private String TipoVarHidro;
  private String tipoCota;
  private String NivelConsistencia;
  private String nomeNivelConsistencia;

  
  
  
//	 private Map <String, ResultOutliers> resultOutlierMapa;
	
  private Map <String, ResultOutliers> resultOutlierMapa;
	public int getNmaxTesteOutlier() {
		return nmaxTesteOutlier;
	}

	public void setNmaxTesteOutlier(int nmaxTesteOutlier) {
		this.nmaxTesteOutlier = nmaxTesteOutlier;
	}

	public double getLimValor() {
		return limValor;
	}

	public void setLimValor(double limValor) {
		this.limValor = limValor;
	}

	public double getRaioMax() {
		return raioMax;
	}

	public void setRaioMax(double raioMax) {
		this.raioMax = raioMax;
	}

	public double getlInfREL() {
		return lInfREL;
	}

	public void setlInfREL(double lInfREL) {
		this.lInfREL = lInfREL;
	}

	public double getlSupREL() {
		return lSupREL;
	}

	public void setlSupREL(double lSupREL) {
		this.lSupREL = lSupREL;
	}


	private String indiceCodigoInterpolacao;
	
	
	private  ArrayList<VariavelHidrologica> variaveisHidrologicasComVies;
	private  ArrayList<VariavelHidrologica> variaveisHidrologicasSemVies;
	private  ArrayList<VariavelHidrologica> variaveisHidrologicasViesCorrigido;
	private  ArrayList<VariavelHidrologica> variaveisHidrologicasComViesFiltrado;
	private  ArrayList<VariavelHidrologica> variaveisHidrologicasSemViesFiltrado;
	private ArrayList<VariavelHidrologica> variaveisHidrologicasSemViesPreenchido;
	
	
	private int ncorr;
	private int lag;
	private int ndist;
	
	
	
	
	public int getNcorr() {
		return ncorr;
	}

	public void setNcorr(int ncorr) {
		this.ncorr = ncorr;
	}

	public int getLag() {
		return lag;
	}

	public void setLag(int lag) {
		this.lag = lag;
	}

	public int getNdist() {
		return ndist;
	}

	public void setNdist(int ndist) {
		this.ndist = ndist;
	}












	//protected Category category = null;
	private FeatureCollection fcbaseHintegrada;
	private FeatureCollection fcbaseOttobacias;
	private  ArrayList<VariavelHidrologica> variaveisHidrologicasPrecipitacao;
	private  ArrayList<VariavelHidrologica> variaveisHidrologicasEvapotranspiracao;
	private  ArrayList<VariavelHidrologica> variaveisHidrologicasVazao;
    

	private boolean desenharShapesSecundarios;
	private boolean desenharShapesPrincipais;
    
    private String operacaoSelecionada;
	
	
	
    public FeatureCollection getFcbaseHintegrada() {
		return fcbaseHintegrada;
	}

	

	public void setFcbaseHintegrada(FeatureCollection fcbaseHintegrada) {
		this.fcbaseHintegrada = fcbaseHintegrada;
	}

	public FeatureCollection getFcbaseOttobacias() {
		return fcbaseOttobacias;
	}

	public void setFcbaseOttobacias(FeatureCollection fcbaseOttobacias) {
		this.fcbaseOttobacias = fcbaseOttobacias;
	}

	public ArrayList<VariavelHidrologica> getVariaveisHidrologicasPrecipitacao() {
		return variaveisHidrologicasPrecipitacao;
	}

	public void setVariaveisHidrologicasPrecipitacao(
			ArrayList<VariavelHidrologica> variaveisHidrologicasPrecipitacao) {
		this.variaveisHidrologicasPrecipitacao = variaveisHidrologicasPrecipitacao;
	}

	public ArrayList<VariavelHidrologica> getVariaveisHidrologicasEvapotranspiracao() {
		return variaveisHidrologicasEvapotranspiracao;
	}

	public void setVariaveisHidrologicasEvapotranspiracao(
			ArrayList<VariavelHidrologica> variaveisHidrologicasEvapotranspiracao) {
		this.variaveisHidrologicasEvapotranspiracao = variaveisHidrologicasEvapotranspiracao;
	}

	public ArrayList<VariavelHidrologica> getVariaveisHidrologicasVazao() {
		return variaveisHidrologicasVazao;
	}

	public void setVariaveisHidrologicasVazao(
			ArrayList<VariavelHidrologica> variaveisHidrologicasVazao) {
		this.variaveisHidrologicasVazao = variaveisHidrologicasVazao;
	}


	
	private boolean apenasInventario;
	
	
	
	protected PlugInContext context = null;
	protected Category category = null;
	
	
	
	
	/*
	 * Módulo mar,criarbancodedados,disphidr
	 */
	
	public PlugInContext getContext() {
		return context;
	}

	public void setContext(PlugInContext context) {
		this.context = context;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	private  String dirTodosTemplateXls=null;
	private  String FilenameTemplateXls=null;
    private  String dirTemplateXls=null;

	private  String dirSalvarArquivos=null;
	private  String dirLeituraArquivos=null;
    private  String FilenameCRU=null;
    private  String dirCRU=null;
    private  String FilenameOBS=null;
    private  String dirOBS=null;




    /**
     * Data inicial e final para um arquivo .dat do tipo 2 que considerar todas a series o mesmo conjunto de dados
     * Saulo 25/04/2014
     */

    private String dataArqIni;
    private String dataArqFim;
    private boolean selecionarDataArq;
       
    private double raioMaximoPrecMedia;
    private int npontosInterpIPCC;
    private Double resolLatyKm;
	private Double resolLaty;
	private Double resolLonxKm;
	private Double resolLonx;
	
    

    private String disctemporal;
	
	private String generalDataDir = null;
	private static SimulationDataExtremos singleton;
	
    private  String dataDirBD=null;
    private  String FilenameBD=null;
	
    
    private String nomeCampoBDTipoVarHidro;
    private int TamanhoMediaMovel;
    private double TempoRetorno;
    //codigo das estações a serem pesquisadas
    private String [] Estacoes;
	private String [] Bacia;
	private String [] SubBacia;
  
    
	private static ArrayList<VariavelHidrologica> VariaveisHidr;
	//private static ArrayList<Map<String,VariavelHidrologica>> VariaveisHidrIndicesExtremos;
	/**
	 * Cada indice tem-se um arquivo
	 */
	private static Map<String,ArrayList<VariavelHidrologica>> VariaveisHidrIndicesExtremos;
	
	private static ArrayList<VariavelHidrologica> VariaveisHidrFiltrada;
	
	
    
   


	private static Map<String,VariavelHidrologica> VariaveisHidrMapa;
    private static Map<String,ArrayList<VariavelHidrologica>> VariaveisCLIMapa;
    private static ArrayList<VariavelHidrologica> VariaveisHidrInterpolada;
   
    private static Map<String,VariavelHidrologica> VariaveisHidrPrecMediaBacia;
    private static Map<String,Map<String,VariavelHidrologica>>varHidrModCliPrecMediaBacia;
    
   
    private static ArrayList<VariavelHidrologica> VariaveisHidrCota;
    
    private static ArrayList<InventarioHidrologico> inventarioHidrologico;
    
    
    private String tipoDadoCota;
    
      
    private int mesIni;
    private int mesFim;
    
    private int anoDeSeparacaoPeriodo; 
    private int mesIniEstacionaridade;
    private int mesFimEstacionaridade;
    private Double tolFalhaMax;
    private int TipoSerieFalhaEstacionaridade;
    
    private int anoIniSubConjunto;
    private int anoFimSubConjunto;
    
    
    private int anoIniSerie1Estacionaridade;
    private int anoFimSerie1Estacionaridade;
    private int anoIniSerie2Estacionaridade;
    private int anoFimSerie2Estacionaridade;
    private boolean periodoTotalSerie;
    
    private int tamMinSerie1Estacionaridade;
    private int tamMinSerie2Estacionaridade;
    private int tamMinSerietotEstacionaridade;
        
    private boolean fazerPW;
    private boolean fazerTFPW;
    private boolean fazerMTFPW;
    private boolean fazerVC;
    private boolean fazerVCPW;
    private boolean considerarAutoCorrelacao;
    private boolean fazerFDR;
    private boolean fazerFDRClassico;
    

    private String dirTemplateExtremosUNB;
    private String dirOutputExtremosUNB;
    

	public String getDirTemplateExtremosUNB() {
		return dirTemplateExtremosUNB;
	}

	public void setDirTemplateExtremosUNB(String dirTemplateExtremosUNB) {
		this.dirTemplateExtremosUNB = dirTemplateExtremosUNB;
	}

	public String getDirOutputExtremosUNB() {
		return dirOutputExtremosUNB;
	}

	public void setDirOutputExtremosUNB(String dirOutputExtremosUNB) {
		this.dirOutputExtremosUNB = dirOutputExtremosUNB;
	}


	private boolean admMLT;
	private boolean mediaMovelEstacionaridade;
    private int tamMediaMovel;
    private File [] files;
    private String [] filenames;
    
    
    private int tipoHipoteseEstacionaridade;
    private double nivelSignificancia;
    private boolean bootstrap;
    private double nivelSignificanciaBootstrap;
      
    private FeatureCollection fcbaseLayerBaciaCRU;
    
    private FeatureCollection fcbaseLayerGradePontos;
    
             
    private String tipoEstatisticaSelecionadaEstacionaridade;
    private int codEstatisticaSelecionadaEstacionaridade;
	    
    private double valorLimiar;
    private double percentil;
    private int tamanhoMediaMovelMin; 
    private int nSeriesSelecionadas;  
    
    private boolean fuller;
    
    
    
    public boolean isFazerPW() {
		return fazerPW;
	}

	public void setFazerPW(boolean fazerPW) {
		this.fazerPW = fazerPW;
	}

	public boolean isFazerTFPW() {
		return fazerTFPW;
	}

	public void setFazerTFPW(boolean fazerTFPW) {
		this.fazerTFPW = fazerTFPW;
	}
    
    public String[] getFilenames() {
		return filenames;
	}

	public void setFilenames(String[] filenames) {
		this.filenames = filenames;
	}

	public int getAnoIniSerie1Estacionaridade() {
		return anoIniSerie1Estacionaridade;
	}

	public void setAnoIniSerie1Estacionaridade(int anoIniSerie1Estacionaridade) {
		this.anoIniSerie1Estacionaridade = anoIniSerie1Estacionaridade;
	}

	public int getAnoFimSerie1Estacionaridade() {
		return anoFimSerie1Estacionaridade;
	}

	public void setAnoFimSerie1Estacionaridade(int anoFimSerie1Estacionaridade) {
		this.anoFimSerie1Estacionaridade = anoFimSerie1Estacionaridade;
	}

	public int getAnoIniSerie2Estacionaridade() {
		return anoIniSerie2Estacionaridade;
	}

	public boolean isMediaMovelEstacionaridade() {
		return mediaMovelEstacionaridade;
	}

	public void setMediaMovelEstacionaridade(boolean mediaMovelEstacionaridade) {
		this.mediaMovelEstacionaridade = mediaMovelEstacionaridade;
	}

	public void setAnoIniSerie2Estacionaridade(int anoIniSerie2Estacionaridade) {
		this.anoIniSerie2Estacionaridade = anoIniSerie2Estacionaridade;
	}

	public int getAnoFimSerie2Estacionaridade() {
		return anoFimSerie2Estacionaridade;
	}

	public void setAnoFimSerie2Estacionaridade(int anoFimSerie2Estacionaridade) {
		this.anoFimSerie2Estacionaridade = anoFimSerie2Estacionaridade;
	}

	public int getTamMinSerie1Estacionaridade() {
		return tamMinSerie1Estacionaridade;
	}

	public void setTamMinSerie1Estacionaridade(int tamMinSerie1Estacionaridade) {
		this.tamMinSerie1Estacionaridade = tamMinSerie1Estacionaridade;
	}

	public int getTamMinSerie2Estacionaridade() {
		return tamMinSerie2Estacionaridade;
	}

	public void setTamMinSerie2Estacionaridade(int tamMinSerie2Estacionaridade) {
		this.tamMinSerie2Estacionaridade = tamMinSerie2Estacionaridade;
	}

	public int getTamMinSerietotEstacionaridade() {
		return tamMinSerietotEstacionaridade;
	}

	public void setTamMinSerietotEstacionaridade(int tamMinSerietotEstacionaridade) {
		this.tamMinSerietotEstacionaridade = tamMinSerietotEstacionaridade;
	}

	public int getTamMediaMovel() {
		return tamMediaMovel;
	}

	public void setTamMediaMovel(int tamMediaMovel) {
		this.tamMediaMovel = tamMediaMovel;
	}

	public boolean isAdmMLT() {
		return admMLT;
	}

	public void setAdmMLT(boolean admMLT) {
		this.admMLT = admMLT;
	}





	
    
    /**
     * Vazao = 1;
     * Chuva = 2;
     */
    private int codTipoVarHidro;
    
    
    private boolean eixoLogaritmicoMaximos;
    
    
    
	 //CheckBox dos filtros
    
    private boolean SetadocheckboxFiltroEstac;
	private boolean SetadocheckboxFiltroSubBac;
	private boolean SetadocheckboxFiltroLayer;
	private boolean SetadocheckboxFiltroArea;
	private boolean SetadocheckboxFiltroData;
	private boolean SetadocheckboxFiltroCodigoRio;
	private boolean SetadocheckboxFiltroAdicionais;
	
	private boolean SetadocheckboxFiltroTamanhoMinimo;
	private boolean SetadocheckboxFiltroPeriodo;
	private boolean SetadocheckboxFiltroTamanhoMinimoApenasRestricao;
	private boolean SetadocheckboxFiltroPeriodoApenasRestricao;
	
	private int TipoSerieFalhaTamanhoMinimoHIDROPG;
	private int TipoSerieFalhaPeriodoHIDROPG;
	private int tamMinSerietotEstacionaridadeHIDROPG;
	private boolean setadoCheckBoxAnosContinuos;
	
	private Double tolFalhaMaxPeriodoHIDROPG;
	private Double tolFalhaMaxTamanhoMinimoHIDROPG;
	private int mesIniHIDROPG;
    private int mesFimHIDROPG;
	
	
    private Map<Integer,Integer> anoEstacao;
    
	
	private ArrayList<String> periodosSeremConsideradasHIDROPG;
	
	private  Map<String,ArrayList<String>> periodosSeremConsideradas;
	
	
	private String [] CodigoRio;
    
    private String AreaInicial;
    private String AreaFinal;
    private String DataInicial;
    private String DataFinal;
    
    private String TipoSerieInventario;
   
    private FeatureCollection fcbase;
	private double bufferHIDROPG;
    
    private int TipoSerieFalhaTamanhoMinimoINMETPG;
	private int TipoSerieFalhaPeriodoINMETPG;
	private int tamMinSerietotEstacionaridadeINMETPG;
	private Double tolFalhaMaxPeriodoINMETPG;
	private Double tolFalhaMaxTamanhoMinimoINMETPG;
	private int mesIniINMETPG;
    private int mesFimINMETPG;
	private ArrayList<String> periodosSeremConsideradasINMETPG;
    
    
    
    
    
	public int getTipoSerieFalhaTamanhoMinimoINMETPG() {
		return TipoSerieFalhaTamanhoMinimoINMETPG;
	}

	public void setTipoSerieFalhaTamanhoMinimoINMETPG(
			int tipoSerieFalhaTamanhoMinimoINMETPG) {
		TipoSerieFalhaTamanhoMinimoINMETPG = tipoSerieFalhaTamanhoMinimoINMETPG;
	}

	public int getTipoSerieFalhaPeriodoINMETPG() {
		return TipoSerieFalhaPeriodoINMETPG;
	}

	public void setTipoSerieFalhaPeriodoINMETPG(int tipoSerieFalhaPeriodoINMETPG) {
		TipoSerieFalhaPeriodoINMETPG = tipoSerieFalhaPeriodoINMETPG;
	}

	public int getTamMinSerietotEstacionaridadeINMETPG() {
		return tamMinSerietotEstacionaridadeINMETPG;
	}

	public void setTamMinSerietotEstacionaridadeINMETPG(
			int tamMinSerietotEstacionaridadeINMETPG) {
		this.tamMinSerietotEstacionaridadeINMETPG = tamMinSerietotEstacionaridadeINMETPG;
	}

	public Double getTolFalhaMaxPeriodoINMETPG() {
		return tolFalhaMaxPeriodoINMETPG;
	}

	public void setTolFalhaMaxPeriodoINMETPG(Double tolFalhaMaxPeriodoINMETPG) {
		this.tolFalhaMaxPeriodoINMETPG = tolFalhaMaxPeriodoINMETPG;
	}

	public Double getTolFalhaMaxTamanhoMinimoINMETPG() {
		return tolFalhaMaxTamanhoMinimoINMETPG;
	}

	public void setTolFalhaMaxTamanhoMinimoINMETPG(
			Double tolFalhaMaxTamanhoMinimoINMETPG) {
		this.tolFalhaMaxTamanhoMinimoINMETPG = tolFalhaMaxTamanhoMinimoINMETPG;
	}

	public int getMesIniINMETPG() {
		return mesIniINMETPG;
	}

	public void setMesIniINMETPG(int mesIniINMETPG) {
		this.mesIniINMETPG = mesIniINMETPG;
	}

	public int getMesFimINMETPG() {
		return mesFimINMETPG;
	}

	public void setMesFimINMETPG(int mesFimINMETPG) {
		this.mesFimINMETPG = mesFimINMETPG;
	}

	public ArrayList<String> getPeriodosSeremConsideradasINMETPG() {
		return periodosSeremConsideradasINMETPG;
	}

	public void setPeriodosSeremConsideradasINMETPG(
			ArrayList<String> periodosSeremConsideradasINMETPG) {
		this.periodosSeremConsideradasINMETPG = periodosSeremConsideradasINMETPG;
	}

	public String getAreaInicial() {
		return AreaInicial;
	}

	public void setAreaInicial(String areaInicial) {
		AreaInicial = areaInicial;
	}

	public String getAreaFinal() {
		return AreaFinal;
	}

	public void setAreaFinal(String areaFinal) {
		AreaFinal = areaFinal;
	}

	public String getDataInicial() {
		return DataInicial;
	}

	public void setDataInicial(String dataInicial) {
		DataInicial = dataInicial;
	}

	public String getDataFinal() {
		return DataFinal;
	}

	public void setDataFinal(String dataFinal) {
		DataFinal = dataFinal;
	}

	public boolean isSetadocheckboxFiltroEstac() {
		return SetadocheckboxFiltroEstac;
	}

	public void setSetadocheckboxFiltroEstac(boolean setadocheckboxFiltroEstac) {
		SetadocheckboxFiltroEstac = setadocheckboxFiltroEstac;
	}

	public boolean isSetadocheckboxFiltroSubBac() {
		return SetadocheckboxFiltroSubBac;
	}

	public void setSetadocheckboxFiltroSubBac(boolean setadocheckboxFiltroSubBac) {
		SetadocheckboxFiltroSubBac = setadocheckboxFiltroSubBac;
	}

	public boolean isSetadocheckboxFiltroLayer() {
		return SetadocheckboxFiltroLayer;
	}

	public void setSetadocheckboxFiltroLayer(boolean setadocheckboxFiltroLayer) {
		SetadocheckboxFiltroLayer = setadocheckboxFiltroLayer;
	}

	public boolean isSetadocheckboxFiltroArea() {
		return SetadocheckboxFiltroArea;
	}

	public void setSetadocheckboxFiltroArea(boolean setadocheckboxFiltroArea) {
		SetadocheckboxFiltroArea = setadocheckboxFiltroArea;
	}

	public boolean isSetadocheckboxFiltroData() {
		return SetadocheckboxFiltroData;
	}

	public void setSetadocheckboxFiltroData(boolean setadocheckboxFiltroData) {
		SetadocheckboxFiltroData = setadocheckboxFiltroData;
	}

	
	
	
	public static SimulationDataExtremos getInstance(){
		if (singleton == null) {
			singleton = new SimulationDataExtremos();
			return singleton;
		}else{
			return singleton;
		}
	}
			
	

	public void setGeneralDataDir(String generalDataDir) {
		this.generalDataDir = generalDataDir;
	}


	

	public  String getDataDirBD() {
		return this.dataDirBD;
	}

	public  void setDataDirBD(String dataDirBD) {
		this.dataDirBD = dataDirBD;
	}

	public  String getFilenameBD() {
		return this.FilenameBD;
	}

	public  void setFilenameBD(String filenameBD) {
		this.FilenameBD = filenameBD;
	}

	

	public String [] getEstacoes() {
		return this.Estacoes;
	}

	public  void setEstacoes(String [] estacoes) {
		this.Estacoes = estacoes;
	}

	public  String [] getBacia() {
		return this.Bacia;
	}

	public  void setBacia(String [] bacia) {
		this.Bacia = bacia;
	}

	public  String [] getSubBacia() {
		return this.SubBacia;
	}

	public  void setSubBacia(String [] subBacia) {
		this.SubBacia = subBacia;
	}

	public int getTamanhoMediaMovel() {
		return TamanhoMediaMovel;
	}

	public void setTamanhoMediaMovel(int tamanhoMediaMovel) {
		TamanhoMediaMovel = tamanhoMediaMovel;
	}

	public double getTempoRetorno() {
		return TempoRetorno;
	}

	public void setTempoRetorno(double tempoRetorno) {
		TempoRetorno = tempoRetorno;
	}

	

	public static ArrayList<VariavelHidrologica> getVariaveisHidr() {
		return VariaveisHidr;
	}

	public static void setVariaveisHidr(ArrayList<VariavelHidrologica> variaveisHidr) {
		VariaveisHidr = variaveisHidr;
	}

	
	public static VariavelHidrologica pegarVariaveisHidrDoCodigo(String codigo) {
		//VariavelHidrologica vhid=new VariavelHidrologica();
		for(int igauges=0;igauges<getVariaveisHidr().size();igauges++){
			String cod=String.valueOf(getVariaveisHidr().get(igauges).getInvhidro().getEstacao_codigo());
			if(codigo.equals(cod)){
				return getVariaveisHidr().get(igauges);
			}
			
		}
		return null;
	}
	
	
	
	
	private int [][]modelsOptions=new int [30][6];
	private double [] tempoderetorno=new double [30];
	private int ngauges;
	private int nmodels;
	
	private int ngaugesmax=500;
	private int nmodelssmax;
	private int nvaluesTRsmax;
	private int nParamsmax=6;
		
    
    private String TipoSerieHidroMaximos;

	private int nvaluesTR;
	private int [] nParamModels=new int [nvaluesTRsmax];
	
	
		
	
	public int[][] getModelsOptions() {
		return modelsOptions;
	}

	public void setModelsOptions(int[][] modelsOptions) {
		this.modelsOptions = modelsOptions;
	}

	public double[] getTempoderetorno() {
		return tempoderetorno;
	}

	public void setTempoderetorno(double[] tempoderetorno) {
		this.tempoderetorno = tempoderetorno;
	}

	public int getNgauges() {
		return ngauges;
	}

	public void setNgauges(int ngauges) {
		this.ngauges = ngauges;
	}

	public int getNmodels() {
		return nmodels;
	}

	public void setNmodels(int nmodels) {
		this.nmodels = nmodels;
	}

	public int getNvaluesTR() {
		return nvaluesTR;
	}

	public void setNvaluesTR(int nvaluesTR) {
		this.nvaluesTR = nvaluesTR;
	}

	public int[] getnParamModels() {
		return nParamModels;
	}

	public void setnParamModels(int[] nParamModels) {
		this.nParamModels = nParamModels;
	}

	
	public int getNgaugesmax() {
		return ngaugesmax;
	}

	public void setNgaugesmax(int ngaugesmax) {
		this.ngaugesmax = ngaugesmax;
	}

	public int getNmodelssmax() {
		return nmodelssmax;
	}

	public void setNmodelssmax(int nmodelssmax) {
		this.nmodelssmax = nmodelssmax;
	}

	public int getNvaluesTRsmax() {
		return nvaluesTRsmax;
	}

	public void setNvaluesTRsmax(int nvaluesTRsmax) {
		this.nvaluesTRsmax = nvaluesTRsmax;
	}

	public int getnParamsmax() {
		return nParamsmax;
	}

	public void setnParamsmax(int nParamsmax) {
		this.nParamsmax = nParamsmax;
	}

	

	public String getTipoSerieHidroMaximos() {
		return TipoSerieHidroMaximos;
	}

	public void setTipoSerieHidroMaximos(String tipoSerieHidroMaximos) {
		TipoSerieHidroMaximos = tipoSerieHidroMaximos;
	}

	public String getTipoSerieInventario() {
		return TipoSerieInventario;
	}

	public void setTipoSerieInventario(String tipoSerieInventario) {
		TipoSerieInventario = tipoSerieInventario;
	}

	public boolean isSetadocheckboxFiltroCodigoRio() {
		return SetadocheckboxFiltroCodigoRio;
	}

	public void setSetadocheckboxFiltroCodigoRio(
			boolean setadocheckboxFiltroCodigoRio) {
		SetadocheckboxFiltroCodigoRio = setadocheckboxFiltroCodigoRio;
	}

	public String [] getCodigoRio() {
		return CodigoRio;
	}

	public void setCodigoRio(String [] codigoRio) {
		CodigoRio = codigoRio;
	}

	public FeatureCollection getFcbase() {
		return fcbase;
	}

	public void setFcbase(FeatureCollection fcbase) {
		this.fcbase = fcbase;
	}

	public static ArrayList<InventarioHidrologico> getInventarioHidrologico() {
		return inventarioHidrologico;
	}

	public static void setInventarioHidrologico(ArrayList<InventarioHidrologico> inventarioHidrologico) {
		SimulationDataExtremos.inventarioHidrologico = inventarioHidrologico;
	}

	

	public int getAnoDeSeparacaoPeriodo() {
		return anoDeSeparacaoPeriodo;
	}

	public void setAnoDeSeparacaoPeriodo(int anoDeSeparacaoPeriodo) {
		this.anoDeSeparacaoPeriodo = anoDeSeparacaoPeriodo;
	}

	public int getMesIniEstacionaridade() {
		return mesIniEstacionaridade;
	}

	public void setMesIniEstacionaridade(int mesIniEstacionaridade) {
		this.mesIniEstacionaridade = mesIniEstacionaridade;
	}

	public int getMesFimEstacionaridade() {
		return mesFimEstacionaridade;
	}

	public void setMesFimEstacionaridade(int mesFimEstacionaridade) {
		this.mesFimEstacionaridade = mesFimEstacionaridade;
	}

	public Double getTolFalhaMax() {
		return tolFalhaMax;
	}

	public void setTolFalhaMax(Double tolFalhaMax) {
		this.tolFalhaMax = tolFalhaMax;
	}

	public String getNomeCampoBDTipoVarHidro() {
		return nomeCampoBDTipoVarHidro;
	}

	public void setNomeCampoBDTipoVarHidro(String nomeCampoBDTipoVarHidro) {
		this.nomeCampoBDTipoVarHidro = nomeCampoBDTipoVarHidro;
	}

	public int getTipoSerieFalhaEstacionaridade() {
		return TipoSerieFalhaEstacionaridade;
	}

	public void setTipoSerieFalhaEstacionaridade(
			int tipoSerieFalhaEstacionaridade) {
		TipoSerieFalhaEstacionaridade = tipoSerieFalhaEstacionaridade;
	}

	public int getCodTipoVarHidro() {
		return codTipoVarHidro;
	}

	public void setCodTipoVarHidro(int codTipoVarHidro) {
		this.codTipoVarHidro = codTipoVarHidro;
	}

	public boolean isEixoLogaritmicoMaximos() {
		return eixoLogaritmicoMaximos;
	}

	public void setEixoLogaritmicoMaximos(boolean eixoLogaritmicoMaximos) {
		this.eixoLogaritmicoMaximos = eixoLogaritmicoMaximos;
	}

	public boolean isPeriodoTotalSerie() {
		return periodoTotalSerie;
	}

	public void setPeriodoTotalSerie(boolean periodoTotalSerie) {
		this.periodoTotalSerie = periodoTotalSerie;
	}

	public String getTipoDadoCota() {
		return tipoDadoCota;
	}

	public void setTipoDadoCota(String tipoDadoCota) {
		this.tipoDadoCota = tipoDadoCota;
	}

	
	
	public FeatureCollection getFcbaseLayerBaciaCRU() {
		return fcbaseLayerBaciaCRU;
	}

	public void setFcbaseLayerBaciaCRU(FeatureCollection fcbaseLayerBaciaCRU) {
		this.fcbaseLayerBaciaCRU = fcbaseLayerBaciaCRU;
	}

	

	public int getTipoHipoteseEstacionaridade() {
		return tipoHipoteseEstacionaridade;
	}

	public void setTipoHipoteseEstacionaridade(int tipoHipoteseEstacionaridade) {
		this.tipoHipoteseEstacionaridade = tipoHipoteseEstacionaridade;
	}

	public double getNivelSignificancia() {
		return nivelSignificancia;
	}

	public void setNivelSignificancia(double nivelSignificancia) {
		this.nivelSignificancia = nivelSignificancia;
	}

	public boolean isBootstrap() {
		return bootstrap;
	}

	public void setBootstrap(boolean bootstrap) {
		this.bootstrap = bootstrap;
	}

	public double getNivelSignificanciaBootstrap() {
		return nivelSignificanciaBootstrap;
	}

	public void setNivelSignificanciaBootstrap(double nivelSignificanciaBootstrap) {
		this.nivelSignificanciaBootstrap = nivelSignificanciaBootstrap;
	}

	public File [] getFiles() {
		return files;
	}

	public void setFiles(File [] files) {
		this.files = files;
	}

	public String getTipoEstatisticaSelecionadaEstacionaridade() {
		return tipoEstatisticaSelecionadaEstacionaridade;
	}

	public void setTipoEstatisticaSelecionadaEstacionaridade(
			String tipoEstatisticaSelecionadaEstacionaridade) {
		this.tipoEstatisticaSelecionadaEstacionaridade = tipoEstatisticaSelecionadaEstacionaridade;
	}

	public int getCodEstatisticaSelecionadaEstacionaridade() {
		return codEstatisticaSelecionadaEstacionaridade;
	}

	public void setCodEstatisticaSelecionadaEstacionaridade(
			int codEstatisticaSelecionadaEstacionaridade) {
		this.codEstatisticaSelecionadaEstacionaridade = codEstatisticaSelecionadaEstacionaridade;
	}

	public int getTamanhoMediaMovelMin() {
		return tamanhoMediaMovelMin;
	}

	public void setTamanhoMediaMovelMin(int tamanhoMediaMovelMin) {
		this.tamanhoMediaMovelMin = tamanhoMediaMovelMin;
	}

	public double getPercentil() {
		return percentil;
	}

	public void setPercentil(double percentil) {
		this.percentil = percentil;
	}

	public double getValorLimiar() {
		return valorLimiar;
	}

	public void setValorLimiar(double valorLimiar) {
		this.valorLimiar = valorLimiar;
	}

	public int getnSeriesSelecionadas() {
		return nSeriesSelecionadas;
	}

	public void setnSeriesSelecionadas(int nSeriesSelecionadas) {
		this.nSeriesSelecionadas = nSeriesSelecionadas;
	}

	public int getAnoIniSubConjunto() {
		return anoIniSubConjunto;
	}

	public void setAnoIniSubConjunto(int anoIniSubConjunto) {
		this.anoIniSubConjunto = anoIniSubConjunto;
	}

	public int getAnoFimSubConjunto() {
		return anoFimSubConjunto;
	}

	public void setAnoFimSubConjunto(int anoFimSubConjunto) {
		this.anoFimSubConjunto = anoFimSubConjunto;
	}

	public boolean isFuller() {
		return fuller;
	}

	public void setFuller(boolean fuller) {
		this.fuller = fuller;
	}

	public int getMesIni() {
		return mesIni;
	}

	public void setMesIni(int mesIni) {
		this.mesIni = mesIni;
	}

	public int getMesFim() {
		return mesFim;
	}

	public void setMesFim(int mesFim) {
		this.mesFim = mesFim;
	}

	public boolean isSetadocheckboxFiltroAdicionais() {
		return SetadocheckboxFiltroAdicionais;
	}

	public void setSetadocheckboxFiltroAdicionais(
			boolean setadocheckboxFiltroAdicionais) {
		SetadocheckboxFiltroAdicionais = setadocheckboxFiltroAdicionais;
	}

	public static ArrayList<VariavelHidrologica> getVariaveisHidrCota() {
		return VariaveisHidrCota;
	}

	public static void setVariaveisHidrCota(ArrayList<VariavelHidrologica> variaveisHidrCota) {
		VariaveisHidrCota = variaveisHidrCota;
	}

	
	public static Map<String,VariavelHidrologica> getVariaveisHidrMapa() {
		return VariaveisHidrMapa;
	}

	public static void setVariaveisHidrMapa(Map<String,VariavelHidrologica> variaveisHidrMapa) {
		VariaveisHidrMapa = variaveisHidrMapa;
	}

	

	public String getDisctemporal() {
		return disctemporal;
	}

	public void setDisctemporal(String disctemporal) {
		this.disctemporal = disctemporal;
	}

	public int getNpontosInterpIPCC() {
		return npontosInterpIPCC;
	}

	public void setNpontosInterpIPCC(int npontosInterpIPCC) {
		this.npontosInterpIPCC = npontosInterpIPCC;
	}

	

	public static Map<String,ArrayList<VariavelHidrologica>> getVariaveisCLIMapa() {
		return VariaveisCLIMapa;
	}

	public static void setVariaveisCLIMapa(Map<String,ArrayList<VariavelHidrologica>> variaveisCLIMapa) {
		VariaveisCLIMapa = variaveisCLIMapa;
	}

	
	public static ArrayList<VariavelHidrologica> getVariaveisHidrInterpolada() {
		return VariaveisHidrInterpolada;
	}

	public static void setVariaveisHidrInterpolada(
			ArrayList<VariavelHidrologica> variaveisHidrInterpolada) {
		VariaveisHidrInterpolada = variaveisHidrInterpolada;
	}

	public double getRaioMaximoPrecMedia() {
		return raioMaximoPrecMedia;
	}

	public void setRaioMaximoPrecMedia(double raioMaximoPrecMedia) {
		this.raioMaximoPrecMedia = raioMaximoPrecMedia;
	}

	public Double getResolLatyKm() {
		return resolLatyKm;
	}

	public void setResolLatyKm(Double resolLatyKm) {
		this.resolLatyKm = resolLatyKm;
	}

	public Double getResolLaty() {
		return resolLaty;
	}

	public void setResolLaty(Double resolLaty) {
		this.resolLaty = resolLaty;
	}

	public Double getResolLonxKm() {
		return resolLonxKm;
	}

	public void setResolLonxKm(Double resolLonxKm) {
		this.resolLonxKm = resolLonxKm;
	}

	public Double getResolLonx() {
		return resolLonx;
	}

	public void setResolLonx(Double resolLonx) {
		this.resolLonx = resolLonx;
	}

	public static Map<String,VariavelHidrologica> getVariaveisHidrPrecMediaBacia() {
		return VariaveisHidrPrecMediaBacia;
	}

	public static void setVariaveisHidrPrecMediaBacia(
			Map<String,VariavelHidrologica> variaveisHidrPrecMediaBacia) {
		VariaveisHidrPrecMediaBacia = variaveisHidrPrecMediaBacia;
	}

	public static Map<String,Map<String,VariavelHidrologica>> getVarHidrModCliPrecMediaBacia() {
		return varHidrModCliPrecMediaBacia;
	}

	public static void setVarHidrModCliPrecMediaBacia(
			Map<String,Map<String,VariavelHidrologica>> varHidrModCliPrecMediaBacia) {
		SimulationDataExtremos.varHidrModCliPrecMediaBacia = varHidrModCliPrecMediaBacia;
	}

	public String getDataArqIni() {
		return dataArqIni;
	}

	public void setDataArqIni(String dataArqIni) {
		this.dataArqIni = dataArqIni;
	}

	public String getDataArqFim() {
		return dataArqFim;
	}

	public void setDataArqFim(String dataArqFim) {
		this.dataArqFim = dataArqFim;
	}

	public FeatureCollection getFcbaseLayerGradePontos() {
		return fcbaseLayerGradePontos;
	}

	public void setFcbaseLayerGradePontos(FeatureCollection fcbaseLayerGradePontos) {
		this.fcbaseLayerGradePontos = fcbaseLayerGradePontos;
	}

	public String getDirSalvarArquivos() {
		return dirSalvarArquivos;
	}

	public void setDirSalvarArquivos(String dirSalvarArquivos) {
		this.dirSalvarArquivos = dirSalvarArquivos;
	}

	public String getFilenameCRU() {
		return FilenameCRU;
	}

	public void setFilenameCRU(String filenameCRU) {
		FilenameCRU = filenameCRU;
	}

	public String getFilenameOBS() {
		return FilenameOBS;
	}

	public void setFilenameOBS(String filenameOBS) {
		FilenameOBS = filenameOBS;
	}

	public String getDirCRU() {
		return dirCRU;
	}

	public void setDirCRU(String dirCRU) {
		this.dirCRU = dirCRU;
	}

	public String getDirOBS() {
		return dirOBS;
	}

	public void setDirOBS(String dirOBS) {
		this.dirOBS = dirOBS;
	}

	
	public String getDirLeituraArquivos() {
		return dirLeituraArquivos;
	}

	public void setDirLeituraArquivos(String dirLeituraArquivos) {
		this.dirLeituraArquivos = dirLeituraArquivos;
	}

	public String getFilenameTemplateXls() {
		return FilenameTemplateXls;
	}

	public void setFilenameTemplateXls(String filenameTemplateXls) {
		FilenameTemplateXls = filenameTemplateXls;
	}

	public String getDirTemplateXls() {
		return dirTemplateXls;
	}

	public void setDirTemplateXls(String dirTemplateXls) {
		this.dirTemplateXls = dirTemplateXls;
	}

	public boolean isSelecionarDataArq() {
		return selecionarDataArq;
	}

	public void setSelecionarDataArq(boolean selecionarDataArq) {
		this.selecionarDataArq = selecionarDataArq;
	}

	public String getDirTodosTemplateXls() {
		return dirTodosTemplateXls;
	}

	public void setDirTodosTemplateXls(String dirTodosTemplateXls) {
		this.dirTodosTemplateXls = dirTodosTemplateXls;
	}

	
	public boolean isApenasInventario() {
		return apenasInventario;
	}

	public void setApenasInventario(boolean apenasInventario) {
		this.apenasInventario = apenasInventario;
	}

	public static ArrayList<VariavelHidrologica> getVariaveisHidrFiltrada() {
		return VariaveisHidrFiltrada;
	}

	public static void setVariaveisHidrFiltrada(ArrayList<VariavelHidrologica> variaveisHidrFiltrada) {
		VariaveisHidrFiltrada = variaveisHidrFiltrada;
	}

	public boolean isDesenharShapesSecundarios() {
		return desenharShapesSecundarios;
	}

	public void setDesenharShapesSecundarios(boolean desenharShapesSecundarios) {
		this.desenharShapesSecundarios = desenharShapesSecundarios;
	}

	public boolean isDesenharShapesPrincipais() {
		return desenharShapesPrincipais;
	}

	public void setDesenharShapesPrincipais(boolean desenharShapesPrincipais) {
		this.desenharShapesPrincipais = desenharShapesPrincipais;
	}

	public String getOperacaoSelecionada() {
		return operacaoSelecionada;
	}

	public void setOperacaoSelecionada(String operacaoSelecionada) {
		this.operacaoSelecionada = operacaoSelecionada;
	}

	public ArrayList<VariavelHidrologica> getVariaveisHidrologicasComVies() {
		return variaveisHidrologicasComVies;
	}

	public void setVariaveisHidrologicasComVies(
			ArrayList<VariavelHidrologica> variaveisHidrologicasComVies) {
		this.variaveisHidrologicasComVies = variaveisHidrologicasComVies;
	}

	public ArrayList<VariavelHidrologica> getVariaveisHidrologicasSemVies() {
		return variaveisHidrologicasSemVies;
	}

	public void setVariaveisHidrologicasSemVies(
			ArrayList<VariavelHidrologica> variaveisHidrologicasSemVies) {
		this.variaveisHidrologicasSemVies = variaveisHidrologicasSemVies;
	}

	public ArrayList<VariavelHidrologica> getVariaveisHidrologicasViesCorrigido() {
		return variaveisHidrologicasViesCorrigido;
	}

	public void setVariaveisHidrologicasViesCorrigido(
			ArrayList<VariavelHidrologica> variaveisHidrologicasViesCorrigido) {
		this.variaveisHidrologicasViesCorrigido = variaveisHidrologicasViesCorrigido;
	}

	
	public ArrayList<VariavelHidrologica> getVariaveisHidrologicasComViesFiltrado() {
		return variaveisHidrologicasComViesFiltrado;
	}

	public void setVariaveisHidrologicasComViesFiltrado(
			ArrayList<VariavelHidrologica> variaveisHidrologicasComViesFiltrado) {
		this.variaveisHidrologicasComViesFiltrado = variaveisHidrologicasComViesFiltrado;
	}

	public ArrayList<VariavelHidrologica> getVariaveisHidrologicasSemViesFiltrado() {
		return variaveisHidrologicasSemViesFiltrado;
	}

	public void setVariaveisHidrologicasSemViesFiltrado(
			ArrayList<VariavelHidrologica> variaveisHidrologicasSemViesFiltrado) {
		this.variaveisHidrologicasSemViesFiltrado = variaveisHidrologicasSemViesFiltrado;
	}

	public String getIndiceCodigoInterpolacao() {
		return indiceCodigoInterpolacao;
	}

	public void setIndiceCodigoInterpolacao(String indiceCodigoInterpolacao) {
		this.indiceCodigoInterpolacao = indiceCodigoInterpolacao;
	}

	public ArrayList<VariavelHidrologica> getVariaveisHidrologicasSemViesPreenchido() {
		return variaveisHidrologicasSemViesPreenchido;
	}

	public void setVariaveisHidrologicasSemViesPreenchido(
			ArrayList<VariavelHidrologica> variaveisHidrologicasSemViesPreenchido) {
		this.variaveisHidrologicasSemViesPreenchido = variaveisHidrologicasSemViesPreenchido;
	}

	public Map<String,ArrayList<Double>> getDadosOttobaciasBD() {
		return dadosOttobaciasBD;
	}

	public void setDadosOttobaciasBD(Map<String,ArrayList<Double>> dadosOttobaciasBD) {
		this.dadosOttobaciasBD = dadosOttobaciasBD;
	}

	public Map<String,ArrayList<Double>> getIndiceOttobaciasBD() {
		return indiceOttobaciasBD;
	}

	public void setIndiceOttobaciasBD(Map<String,ArrayList<Double>> indiceOttobaciasBD) {
		this.indiceOttobaciasBD = indiceOttobaciasBD;
	}

	public ArrayList<String> getNomeCampoIndice1() {
		return nomeCampoIndice1;
	}

	public void setNomeCampoIndice1(ArrayList<String> nomeCampoIndice1) {
		this.nomeCampoIndice1 = nomeCampoIndice1;
	}

	public boolean isSetadocheckboxFiltroTamanhoMinimo() {
		return SetadocheckboxFiltroTamanhoMinimo;
	}

	public void setSetadocheckboxFiltroTamanhoMinimo(
			boolean setadocheckboxFiltroTamanhoMinimo) {
		SetadocheckboxFiltroTamanhoMinimo = setadocheckboxFiltroTamanhoMinimo;
	}

	public boolean isSetadocheckboxFiltroPeriodo() {
		return SetadocheckboxFiltroPeriodo;
	}

	public void setSetadocheckboxFiltroPeriodo(boolean setadocheckboxFiltroPeriodo) {
		SetadocheckboxFiltroPeriodo = setadocheckboxFiltroPeriodo;
	}

	public boolean isSetadocheckboxFiltroTamanhoMinimoApenasRestricao() {
		return SetadocheckboxFiltroTamanhoMinimoApenasRestricao;
	}

	public void setSetadocheckboxFiltroTamanhoMinimoApenasRestricao(
			boolean setadocheckboxFiltroTamanhoMinimoApenasRestricao) {
		SetadocheckboxFiltroTamanhoMinimoApenasRestricao = setadocheckboxFiltroTamanhoMinimoApenasRestricao;
	}

	public boolean isSetadocheckboxFiltroPeriodoApenasRestricao() {
		return SetadocheckboxFiltroPeriodoApenasRestricao;
	}

	public void setSetadocheckboxFiltroPeriodoApenasRestricao(
			boolean setadocheckboxFiltroPeriodoApenasRestricao) {
		SetadocheckboxFiltroPeriodoApenasRestricao = setadocheckboxFiltroPeriodoApenasRestricao;
	}

	public int getTipoSerieFalhaTamanhoMinimoHIDROPG() {
		return TipoSerieFalhaTamanhoMinimoHIDROPG;
	}

	public void setTipoSerieFalhaTamanhoMinimoHIDROPG(
			int tipoSerieFalhaTamanhoMinimoHIDROPG) {
		TipoSerieFalhaTamanhoMinimoHIDROPG = tipoSerieFalhaTamanhoMinimoHIDROPG;
	}

	public int getTipoSerieFalhaPeriodoHIDROPG() {
		return TipoSerieFalhaPeriodoHIDROPG;
	}

	public void setTipoSerieFalhaPeriodoHIDROPG(int tipoSerieFalhaPeriodoHIDROPG) {
		TipoSerieFalhaPeriodoHIDROPG = tipoSerieFalhaPeriodoHIDROPG;
	}

	public Double getTolFalhaMaxPeriodoHIDROPG() {
		return tolFalhaMaxPeriodoHIDROPG;
	}

	public void setTolFalhaMaxPeriodoHIDROPG(Double tolFalhaMaxPeriodoHIDROPG) {
		this.tolFalhaMaxPeriodoHIDROPG = tolFalhaMaxPeriodoHIDROPG;
	}

	public Double getTolFalhaMaxTamanhoMinimoHIDROPG() {
		return tolFalhaMaxTamanhoMinimoHIDROPG;
	}

	public void setTolFalhaMaxTamanhoMinimoHIDROPG(
			Double tolFalhaMaxTamanhoMinimoHIDROPG) {
		this.tolFalhaMaxTamanhoMinimoHIDROPG = tolFalhaMaxTamanhoMinimoHIDROPG;
	}

	public ArrayList<String> getPeriodosSeremConsideradasHIDROPG() {
		return periodosSeremConsideradasHIDROPG;
	}

	public void setPeriodosSeremConsideradasHIDROPG(
			ArrayList<String> periodosSeremConsideradasHIDROPG) {
		this.periodosSeremConsideradasHIDROPG = periodosSeremConsideradasHIDROPG;
	}

	public Map<String,ArrayList<String>> getPeriodosSeremConsideradas() {
		return periodosSeremConsideradas;
	}

	public void setPeriodosSeremConsideradas(
			Map<String,ArrayList<String>> periodosSeremConsideradas) {
		this.periodosSeremConsideradas = periodosSeremConsideradas;
	}

	public int getTamMinSerietotEstacionaridadeHIDROPG() {
		return tamMinSerietotEstacionaridadeHIDROPG;
	}

	public void setTamMinSerietotEstacionaridadeHIDROPG(
			int tamMinSerietotEstacionaridadeHIDROPG) {
		this.tamMinSerietotEstacionaridadeHIDROPG = tamMinSerietotEstacionaridadeHIDROPG;
	}

	public int getMesIniHIDROPG() {
		return mesIniHIDROPG;
	}

	public void setMesIniHIDROPG(int mesIniHIDROPG) {
		this.mesIniHIDROPG = mesIniHIDROPG;
	}

	public int getMesFimHIDROPG() {
		return mesFimHIDROPG;
	}

	public void setMesFimHIDROPG(int mesFimHIDROPG) {
		this.mesFimHIDROPG = mesFimHIDROPG;
	}

	
	public double getBufferHIDROPG() {
		return bufferHIDROPG;
	}

	public void setBufferHIDROPG(double bufferHIDROPG) {
		this.bufferHIDROPG = bufferHIDROPG;
	}

	public boolean isSetadoCheckBoxAnosContinuos() {
		return setadoCheckBoxAnosContinuos;
	}

	public void setSetadoCheckBoxAnosContinuos(boolean setadoCheckBoxAnosContinuos) {
		this.setadoCheckBoxAnosContinuos = setadoCheckBoxAnosContinuos;
	}

	public Map<Integer,Integer> getAnoEstacao() {
		return anoEstacao;
	}

	public void setAnoEstacao(Map<Integer,Integer> anoEstacao) {
		this.anoEstacao = anoEstacao;
	}

	/*public Map <String, ResultOutliers> getResultOutlierMapa() {
		return resultOutlierMapa;
	}

	public void setResultOutlierMapa(Map <String, ResultOutliers> resultOutlierMapa) {
		this.resultOutlierMapa = resultOutlierMapa;
	}*/

	/*public static ArrayList<Map<String,VariavelHidrologica>> getVariaveisHidrIndicesExtremos() {
		return VariaveisHidrIndicesExtremos;
	}

	public static void setVariaveisHidrIndicesExtremos(
			ArrayList<Map<String,VariavelHidrologica>> variaveisHidrIndicesExtremos) {
		VariaveisHidrIndicesExtremos = variaveisHidrIndicesExtremos;
	}*/
	 public static Map<String, ArrayList<VariavelHidrologica>> getVariaveisHidrIndicesExtremos() {
			return VariaveisHidrIndicesExtremos;
		}

		public static void setVariaveisHidrIndicesExtremos(
				Map<String, ArrayList<VariavelHidrologica>> variaveisHidrIndicesExtremos) {
			VariaveisHidrIndicesExtremos = variaveisHidrIndicesExtremos;
		}

		public boolean isConsiderarAutoCorrelacao() {
			return considerarAutoCorrelacao;
		}

		public void setConsiderarAutoCorrelacao(boolean considerarAutoCorrelacao) {
			this.considerarAutoCorrelacao = considerarAutoCorrelacao;
		}

		public boolean isFazerFDR() {
			return fazerFDR;
		}

		public void setFazerFDR(boolean fazerFDR) {
			this.fazerFDR = fazerFDR;
		}

		public boolean isFazerFDRClassico() {
			return fazerFDRClassico;
		}

		public void setFazerFDRClassico(boolean fazerFDRClassico) {
			this.fazerFDRClassico = fazerFDRClassico;
		}

    public String getTipoCota() {
      return tipoCota;
    }

    public void setTipoCota(String tipoCota) {
      this.tipoCota = tipoCota;
    }

    public String getTipoVarHidro() {
      return TipoVarHidro;
    }

    public void setTipoVarHidro(String tipoVarHidro) {
      TipoVarHidro = tipoVarHidro;
    }

    public String getNivelConsistencia() {
      return NivelConsistencia;
    }

    public void setNivelConsistencia(String nivelConsistencia) {
      NivelConsistencia = nivelConsistencia;
    }

    public String getNomeNivelConsistencia() {
      return nomeNivelConsistencia;
    }

    public void setNomeNivelConsistencia(String nomeNivelConsistencia) {
      this.nomeNivelConsistencia = nomeNivelConsistencia;
    }

    public boolean isConsultaLatLonEstacao() {
      return consultaLatLonEstacao;
    }

    public void setConsultaLatLonEstacao(boolean consultaLatLonEstacao) {
      this.consultaLatLonEstacao = consultaLatLonEstacao;
    }

	public Map <String, ResultOutliers> getResultOutlierMapa() {
		return resultOutlierMapa;
	}

	public void setResultOutlierMapa(Map <String, ResultOutliers> resultOutlierMapa) {
		this.resultOutlierMapa = resultOutlierMapa;
	}

	public boolean isFazerMTFPW() {
		return fazerMTFPW;
	}

	public void setFazerMTFPW(boolean fazerMTFPW) {
		this.fazerMTFPW = fazerMTFPW;
	}

	public boolean isFazerVC() {
		return fazerVC;
	}

	public void setFazerVC(boolean fazerVC) {
		this.fazerVC = fazerVC;
	}

	public boolean isFazerVCPW() {
		return fazerVCPW;
	}

	public void setFazerVCPW(boolean fazerVCPW) {
		this.fazerVCPW = fazerVCPW;
	}

    /*public ConfiguracaoGUILatLon getConfigLatLon() {
      return configLatLon;
    }

    public void setConfigLatLon(ConfiguracaoGUILatLon configLatLon) {
      this.configLatLon = configLatLon;
    }	*/
}
