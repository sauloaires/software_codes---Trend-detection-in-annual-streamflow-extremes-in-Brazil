package types;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jump.feature.AttributeType;
import com.vividsolutions.jump.feature.BasicFeature;
import com.vividsolutions.jump.feature.Feature;
import com.vividsolutions.jump.feature.FeatureSchema;




public class InventarioHidrologico {
	private int id;
	private int BaciaCodigo;
	private int SubBaciaCodigo;
	//private int Estacao_codigo;
	private String Estacao_codigo;
	private int MesAnoHidro;
	private double Latitude;
	private double Longitude;
	private double Altitude;
	private double AreaDrenagem;
	private int OrigemSerie;
	private String DescricaoOrigemSerie;
	private String DiscretizaçãoTemporária;
	private String TipodeDado;
	private String dataInicialstr;
	private String dataFinalstr;
	private String NomedaEstacao;
	protected Point2D position;
	private double ThiessenArea;
	private double ThiessenCoef;
	private String dataInicial;
	private String dataFinal;
	
	private Date dataIni;
	private Date dataFim;
	
	private int nAnosSF;
	
	private int [] nAnosSFSazonal;
	private String nomedoRio;
	private String municipio;
	private String codmunicipio;
	private String codrio;
	
	private Double resolLatyKm;
	private Double resolLaty;
	private Double resolLonxKm;
	private Double resolLonx;
	private Double areaKm2;
	
	private String nomeRegiaoHidrografica;
	
	private ArrayList<String> nomesRHsCompartilhadas;
	//private ArrayList<Geometry> gradePoligonoCompartilhadas;
	private Map<String,Geometry> gradePoligonoCompartilhadas;
	private Geometry gradePoligono;
	private Point gradeCentroide;
	
	
	private double gradeEW;
	private double gradeNS;
	
	private double gradeKmEW;
	private double gradeKmNS;
	
	private double areaDaGradeKm2;
	
	
	private boolean selecionadaApoio;
	private boolean selecionadaPrincipal;
	
	
	//informacao hidro
	private String codHidroNivelConsistencia;
	private String nomeTabelaBDHidro;
	private String nomeCampoTipoVarHidro;
    private String nivelconsistenciaNome;
		
	
    private boolean existeInventarioBD;
    private String nomeTabelaBD;
    
    private String cobacia;
	
    
    private String codigoResponsavel;
    private String codigoOperadora;
    private String codigoAdicional;
    
    
    private String siglaResponsavel;
    private String siglaOperadora;
    private String emoperacao;
    
    private String ultimaAtualizacao;
    
    
    /*public Coordenadas pegarCoordenadas(){
    	Coordenadas c=new Coordenadas();
    	c.setLat(Latitude);
    	c.setLon(Longitude);
		return c;
    	
    }
    
    
    public Point pegarPontoFeature(){
    	Point ponto = CriarPontoGeografico.coordenadas(Latitude, Longitude);
		return ponto;
    	
    }*/
    
    
    public Geometry pegarGrade(double gradeEW,double gradeNS){
      	   	      
      double latV1=Latitude+(gradeNS/2.0);
      double lonV1=Longitude+(gradeEW/2.0);
      
      double latV2=Latitude+(gradeNS/2.0);
      double lonV2=Longitude-(gradeEW/2.0);
      
      double latV3=Latitude-(gradeNS/2.0);
      double lonV3=Longitude-(gradeEW/2.0);
      
      double latV4=Latitude-(gradeNS/2.0);
      double lonV4=Longitude+(gradeEW/2.0);
      
      
      GeometryFactory geomFact = new GeometryFactory();
      Coordinate[] coords  =
             new Coordinate[] {new Coordinate(lonV1, latV1), new Coordinate(lonV2, latV2),
                               new Coordinate(lonV3, latV3), new Coordinate(lonV4, latV4), new Coordinate(lonV1, latV1) };

      LinearRing ring = geomFact.createLinearRing(coords);
      LinearRing holes[] = null; // use LinearRing[] to represent holes
      Geometry gradePoligono=geomFact.createPolygon(ring, holes);
      
    return gradePoligono;
     
      
    }
    
public Feature getFeatureWebservice(Geometry geom) {
        
        //Point ponto = CriarPontoGeografico.coordenadas(lat, lon);
        FeatureSchema fsnew = new FeatureSchema();    
        fsnew.addAttribute("Geometry", AttributeType.GEOMETRY);
        fsnew.addAttribute("lat", AttributeType.DOUBLE);
        fsnew.addAttribute("lon", AttributeType.DOUBLE);
        fsnew.addAttribute("alt", AttributeType.DOUBLE);
        fsnew.addAttribute("codigo", AttributeType.STRING);
        
        fsnew.addAttribute("nome", AttributeType.STRING);
        fsnew.addAttribute("baciacod", AttributeType.INTEGER);
        fsnew.addAttribute("subbaccod", AttributeType.INTEGER);
        fsnew.addAttribute("rio", AttributeType.STRING);
        fsnew.addAttribute("municipio", AttributeType.STRING);
        fsnew.addAttribute("resp", AttributeType.STRING);
        fsnew.addAttribute("operador", AttributeType.STRING);
        fsnew.addAttribute("adkm2", AttributeType.DOUBLE);
        fsnew.addAttribute("emoperacao", AttributeType.STRING);
        fsnew.addAttribute("ultimatualiza", AttributeType.STRING);
        
        Feature feature = new BasicFeature(fsnew);
        //feature.setGeometry(this.pegarPontoFeature());
        feature.setGeometry(geom);
        feature.setAttribute("lat", this.Latitude);
        feature.setAttribute("lon", this.Longitude);
        feature.setAttribute("alt", this.Altitude);
        feature.setAttribute("codigo", this.Estacao_codigo);
        
        feature.setAttribute("nome", this.NomedaEstacao);
        feature.setAttribute("baciacod", this.BaciaCodigo);
        feature.setAttribute("subbaccod", this.SubBaciaCodigo);
        feature.setAttribute("rio",this.nomedoRio);
        feature.setAttribute("municipio", this.municipio);
        feature.setAttribute("resp", this.siglaResponsavel);
        feature.setAttribute("operador", this.siglaOperadora);
        feature.setAttribute("adkm2", this.AreaDrenagem);
        feature.setAttribute("emoperacao",this.emoperacao);
        feature.setAttribute("ultimatualiza",this.ultimaAtualizacao);
        
        
                 
        return feature;
      } 
 /*   public Feature getFeatureWebservice(Geometry geom) {
        
        //Point ponto = CriarPontoGeografico.coordenadas(lat, lon);
        FeatureSchema fsnew = new FeatureSchema();    
        fsnew.addAttribute("Geometry", AttributeType.GEOMETRY);
        fsnew.addAttribute("lat", AttributeType.DOUBLE);
        fsnew.addAttribute("lon", AttributeType.DOUBLE);
        fsnew.addAttribute("alt", AttributeType.DOUBLE);
        fsnew.addAttribute("codigo", AttributeType.STRING);
        
        fsnew.addAttribute("nome", AttributeType.STRING);
        fsnew.addAttribute("baciacod", AttributeType.INTEGER);
        fsnew.addAttribute("subbaccod", AttributeType.INTEGER);
        fsnew.addAttribute("rio", AttributeType.STRING);
        fsnew.addAttribute("municipio", AttributeType.STRING);
        fsnew.addAttribute("resp", AttributeType.STRING);
        fsnew.addAttribute("operador", AttributeType.STRING);
        fsnew.addAttribute("adkm2", AttributeType.DOUBLE);
        fsnew.addAttribute("emoperacao", AttributeType.STRING);
        fsnew.addAttribute("ultimatualiza", AttributeType.STRING);
        
        Feature feature = new BasicFeature(fsnew);
        //feature.setGeometry(this.pegarPontoFeature());
        feature.setGeometry(geom);
        feature.setAttribute("lat", this.Latitude);
        feature.setAttribute("lon", this.Longitude);
        feature.setAttribute("alt", this.Altitude);
        feature.setAttribute("codigo", this.Estacao_codigo);
        
        feature.setAttribute("nome", this.NomedaEstacao);
        feature.setAttribute("baciacod", this.BaciaCodigo);
        feature.setAttribute("subbaccod", this.SubBaciaCodigo);
        feature.setAttribute("rio",this.nomedoRio);
        feature.setAttribute("municipio", this.municipio);
        feature.setAttribute("resp", this.siglaResponsavel);
        feature.setAttribute("operador", this.siglaOperadora);
        feature.setAttribute("adkm2", this.AreaDrenagem);
        feature.setAttribute("emoperacao",this.emoperacao);
        feature.setAttribute("ultimatualiza",this.ultimaAtualizacao);
        
        
                 
        return feature;
      } 
    
  public Feature getFeatureWebservice() {
    
    //Point ponto = CriarPontoGeografico.coordenadas(lat, lon);
    FeatureSchema fsnew = new FeatureSchema();    
    fsnew.addAttribute("Geometry", AttributeType.GEOMETRY);
    fsnew.addAttribute("lat", AttributeType.DOUBLE);
    fsnew.addAttribute("lon", AttributeType.DOUBLE);
    fsnew.addAttribute("alt", AttributeType.DOUBLE);
    fsnew.addAttribute("codigo", AttributeType.STRING);
    
    fsnew.addAttribute("nome", AttributeType.STRING);
    fsnew.addAttribute("baciacod", AttributeType.INTEGER);
    fsnew.addAttribute("subbaccod", AttributeType.INTEGER);
    fsnew.addAttribute("rio", AttributeType.STRING);
    fsnew.addAttribute("municipio", AttributeType.STRING);
    fsnew.addAttribute("resp", AttributeType.STRING);
    fsnew.addAttribute("operador", AttributeType.STRING);
    fsnew.addAttribute("adkm2", AttributeType.DOUBLE);
    fsnew.addAttribute("emoperacao", AttributeType.STRING);
    fsnew.addAttribute("ultimatualiza", AttributeType.STRING);
    
    Feature feature = new BasicFeature(fsnew);
    feature.setGeometry(this.pegarPontoFeature());
    feature.setAttribute("lat", this.Latitude);
    feature.setAttribute("lon", this.Longitude);
    feature.setAttribute("alt", this.Altitude);
    feature.setAttribute("codigo", this.Estacao_codigo);
    
    feature.setAttribute("nome", this.NomedaEstacao);
    feature.setAttribute("baciacod", this.BaciaCodigo);
    feature.setAttribute("subbaccod", this.SubBaciaCodigo);
    feature.setAttribute("rio",this.nomedoRio);
    feature.setAttribute("municipio", this.municipio);
    feature.setAttribute("resp", this.siglaResponsavel);
    feature.setAttribute("operador", this.siglaOperadora);
    feature.setAttribute("adkm2", this.AreaDrenagem);
    feature.setAttribute("emoperacao",this.emoperacao);
    feature.setAttribute("ultimatualiza",this.ultimaAtualizacao);
    
    
             
    return feature;
  }
    
    
    public Feature getFeature() {
      
      //Point ponto = CriarPontoGeografico.coordenadas(lat, lon);
      FeatureSchema fsnew = new FeatureSchema();    
      fsnew.addAttribute("Geometry", AttributeType.GEOMETRY);
      fsnew.addAttribute("Codigo", AttributeType.STRING);
      //fsnew.addAttribute("j", AttributeType.INTEGER);
      
       
      Feature feature = new BasicFeature(fsnew);
      feature.setGeometry(this.pegarPontoFeature());
      feature.setAttribute("Codigo", this.Estacao_codigo);
      //feature.setAttribute("j",this.j);
               
      return feature;
    }  
    
    
    
    public Feature getFeatureWebserviceCaracResMon(CaracteristicaReservacaoMontante caracResMon) {
        
        //Point ponto = CriarPontoGeografico.coordenadas(lat, lon);
        FeatureSchema fsnew = new FeatureSchema();    
        fsnew.addAttribute("Geometry", AttributeType.GEOMETRY);
        fsnew.addAttribute("lat", AttributeType.DOUBLE);
        fsnew.addAttribute("lon", AttributeType.DOUBLE);
        fsnew.addAttribute("alt", AttributeType.DOUBLE);
        fsnew.addAttribute("codigo", AttributeType.STRING);
        
        fsnew.addAttribute("nome", AttributeType.STRING);
        fsnew.addAttribute("baciacod", AttributeType.INTEGER);
        fsnew.addAttribute("subbaccod", AttributeType.INTEGER);
        fsnew.addAttribute("rio", AttributeType.STRING);
        fsnew.addAttribute("municipio", AttributeType.STRING);
        fsnew.addAttribute("resp", AttributeType.STRING);
        fsnew.addAttribute("operador", AttributeType.STRING);
        fsnew.addAttribute("adkm2", AttributeType.DOUBLE);
        fsnew.addAttribute("emoperacao", AttributeType.STRING);
        fsnew.addAttribute("ultimatualiza", AttributeType.STRING);
        
        fsnew.addAttribute("cobacia", AttributeType.STRING);
        fsnew.addAttribute("cocursodag", AttributeType.STRING);
        fsnew.addAttribute("ne", AttributeType.DOUBLE);
        fsnew.addAttribute("sumae", AttributeType.DOUBLE);
        fsnew.addAttribute("volhm3", AttributeType.DOUBLE);
        fsnew.addAttribute("perae", AttributeType.DOUBLE);
        fsnew.addAttribute("rvolqmlt", AttributeType.DOUBLE);
      
        
        
        Feature feature = new BasicFeature(fsnew);
        feature.setGeometry(this.pegarPontoFeature());
        feature.setAttribute("lat", this.Latitude);
        feature.setAttribute("lon", this.Longitude);
        feature.setAttribute("alt", this.Altitude);
        feature.setAttribute("codigo", this.Estacao_codigo);
        
        feature.setAttribute("nome", this.NomedaEstacao);
        feature.setAttribute("baciacod", this.BaciaCodigo);
        feature.setAttribute("subbaccod", this.SubBaciaCodigo);
        feature.setAttribute("rio",this.nomedoRio);
        feature.setAttribute("municipio", this.municipio);
        feature.setAttribute("resp", this.siglaResponsavel);
        feature.setAttribute("operador", this.siglaOperadora);
        feature.setAttribute("adkm2", this.AreaDrenagem);
        feature.setAttribute("emoperacao",this.emoperacao);
        feature.setAttribute("ultimatualiza",this.ultimaAtualizacao);
        
        feature.setAttribute("cobacia", caracResMon.getCobacia());
        feature.setAttribute("cocursodag", caracResMon.getCocursodag());
        feature.setAttribute("ne", caracResMon.getNumeroEspelhoDagua());
        feature.setAttribute("sumae", caracResMon.getSomaAreaEspelhos());
        feature.setAttribute("volhm3", caracResMon.getVolumeTotalEstimado());
        feature.setAttribute("perae", caracResMon.getPercentualAreaEspelhos());
        feature.setAttribute("rvolqmlt", caracResMon.getRelVolQmedAnual());
                 
        return feature;
      }*/
    
    
    
    
    
    
	public double getGradeEW() {
		return gradeEW;
	}
	public void setGradeEW(double gradeEW) {
		this.gradeEW = gradeEW;
	}
	public double getGradeNS() {
		return gradeNS;
	}
	public void setGradeNS(double gradeNS) {
		this.gradeNS = gradeNS;
	}
	public double getGradeKmEW() {
		return gradeKmEW;
	}
	public void setGradeKmEW(double gradeKmEW) {
		this.gradeKmEW = gradeKmEW;
	}
	public double getGradeKmNS() {
		return gradeKmNS;
	}
	public void setGradeKmNS(double gradeKmNS) {
		this.gradeKmNS = gradeKmNS;
	}
	private String unidadeDaVariavel;
	
	public Point2D getPosition() {
		return position;
	}
	public void setPosition(Point2D position) {
		this.position = position;
	}
	public int getBaciaCodigo() {
		return BaciaCodigo;
	}
	public void setBaciaCodigo(int baciaCodigo) {
		BaciaCodigo = baciaCodigo;
	}
	public int getSubBaciaCodigo() {
		return SubBaciaCodigo;
	}
	public void setSubBaciaCodigo(int subBaciaCodigo) {
		SubBaciaCodigo = subBaciaCodigo;
	}
	public String getEstacao_codigo() {
		return Estacao_codigo;
	}
	public void setEstacao_codigo(String estacao_codigo) {
		Estacao_codigo = estacao_codigo;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	public double getAltitude() {
		return Altitude;
	}
	public void setAltitude(double altitude) {
		Altitude = altitude;
	}
	public double getAreaDrenagem() {
		return AreaDrenagem;
	}
	public void setAreaDrenagem(double areaDrenagem) {
		AreaDrenagem = areaDrenagem;
	}
	public int getOrigemSerie() {
		return OrigemSerie;
	}
	public void setOrigemSerie(int origemSerie) {
		OrigemSerie = origemSerie;
	}
	public String getDescricaoOrigemSerie() {
		return DescricaoOrigemSerie;
	}
	public void setDescricaoOrigemSerie(String descricaoOrigemSerie) {
		DescricaoOrigemSerie = descricaoOrigemSerie;
	}
	public String getDiscretizaçãoTemporária() {
		return DiscretizaçãoTemporária;
	}
	public void setDiscretizaçãoTemporária(String discretizaçãoTemporária) {
		DiscretizaçãoTemporária = discretizaçãoTemporária;
	}
	public String getTipodeDado() {
		return TipodeDado;
	}
	public void setTipodeDado(String tipodeDado) {
		TipodeDado = tipodeDado;
	}
	public int getMesAnoHidro() {
		return MesAnoHidro;
	}
	public void setMesAnoHidro(int mesAnoHidro) {
		MesAnoHidro = mesAnoHidro;
	}
	public String getDataInicialstr() {
		return dataInicialstr;
		
	}
	public void setDataInicialstr(String dataInicialstr) {
		this.dataInicialstr = dataInicialstr;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.setDataIni(formatter.parse(dataInicialstr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getDataFinalstr() {
		return dataFinalstr;
	}
	public void setDataFinalstr(String dataFinalstr) {
		this.dataFinalstr = dataFinalstr;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.setDataFim(formatter.parse(dataFinalstr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getNomedaEstacao() {
		return NomedaEstacao;
	}
	public void setNomedaEstacao(String nomedaEstacao) {
		NomedaEstacao = nomedaEstacao;
	}
	public double getThiessenArea() {
		return ThiessenArea;
	}
	public void setThiessenArea(double thiessenArea) {
		ThiessenArea = thiessenArea;
	}
	public double getThiessenCoef() {
		return ThiessenCoef;
	}
	public void setThiessenCoef(double thiessenCoef) {
		ThiessenCoef = thiessenCoef;
	}
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.setDataIni(formatter.parse(dataInicial));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.setDataFim(formatter.parse(dataFinal));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public int getnAnosSF() {
		return nAnosSF;
	}
	public void setnAnosSF(int nAnosSF) {
		this.nAnosSF = nAnosSF;
	}
	public String getNomedoRio() {
		return nomedoRio;
	}
	public void setNomedoRio(String nomedoRio) {
		this.nomedoRio = nomedoRio;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getCodmunicipio() {
		return codmunicipio;
	}
	public void setCodmunicipio(String codmunicipio) {
		this.codmunicipio = codmunicipio;
	}
	public String getCodrio() {
		return codrio;
	}
	public void setCodrio(String codrio) {
		this.codrio = codrio;
	}
	public String getUnidadeDaVariavel() {
		return unidadeDaVariavel;
	}
	public void setUnidadeDaVariavel(String unidadeDaVariavel) {
		this.unidadeDaVariavel = unidadeDaVariavel;
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
	public String getNomeRegiaoHidrografica() {
		return nomeRegiaoHidrografica;
	}
	public void setNomeRegiaoHidrografica(String nomeRegiaoHidrografica) {
		this.nomeRegiaoHidrografica = nomeRegiaoHidrografica;
	}
	public Geometry getGradePoligono() {
		return gradePoligono;
	}
	public void setGradePoligono(Geometry gradePoligono) {
		this.gradePoligono = gradePoligono;
	}
	public Point getGradeCentroide() {
		return gradeCentroide;
	}
	public void setGradeCentroide(Point gradeCentroide) {
		this.gradeCentroide = gradeCentroide;
	}
	public Double getAreaKm2() {
		return areaKm2;
	}
	public void setAreaKm2(Double areaKm2) {
		this.areaKm2 = areaKm2;
	}
	public ArrayList<String> getNomesRHsCompartilhadas() {
		return nomesRHsCompartilhadas;
	}
	public void setNomesRHsCompartilhadas(ArrayList<String> nomesRHsCompartilhadas) {
		this.nomesRHsCompartilhadas = nomesRHsCompartilhadas;
	}
	public Map<String,Geometry> getGradePoligonoCompartilhadas() {
		return gradePoligonoCompartilhadas;
	}
	public void setGradePoligonoCompartilhadas(
			Map<String,Geometry> gradePoligonoCompartilhadas) {
		this.gradePoligonoCompartilhadas = gradePoligonoCompartilhadas;
	}
	public double getAreaDaGradeKm2() {
		return areaDaGradeKm2;
	}
	public void setAreaDaGradeKm2(double areaDaGradeKm2) {
		this.areaDaGradeKm2 = areaDaGradeKm2;
	}
	public boolean isSelecionadaApoio() {
		return selecionadaApoio;
	}
	public void setSelecionadaApoio(boolean selecionadaApoio) {
		this.selecionadaApoio = selecionadaApoio;
	}
	public boolean isSelecionadaPrincipal() {
		return selecionadaPrincipal;
	}
	public void setSelecionadaPrincipal(boolean selecionadaPrincipal) {
		this.selecionadaPrincipal = selecionadaPrincipal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataIni() {
		return dataIni;
	}
	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public String getCodHidroNivelConsistencia() {
		return codHidroNivelConsistencia;
	}
	public void setCodHidroNivelConsistencia(String codHidroNivelConsistencia) {
		this.codHidroNivelConsistencia = codHidroNivelConsistencia;
	}
	public String getNomeTabelaBDHidro() {
		return nomeTabelaBDHidro;
	}
	public void setNomeTabelaBDHidro(String nomeTabelaBDHidro) {
		this.nomeTabelaBDHidro = nomeTabelaBDHidro;
	}
	public String getNomeCampoTipoVarHidro() {
		return nomeCampoTipoVarHidro;
	}
	public void setNomeCampoTipoVarHidro(String nomeCampoTipoVarHidro) {
		this.nomeCampoTipoVarHidro = nomeCampoTipoVarHidro;
	}
	public String getNivelconsistenciaNome() {
		return nivelconsistenciaNome;
	}
	public void setNivelconsistenciaNome(String nivelconsistenciaNome) {
		this.nivelconsistenciaNome = nivelconsistenciaNome;
	}
	public boolean isExisteInventarioBD() {
		return existeInventarioBD;
	}
	public void setExisteInventarioBD(boolean existeInventarioBD) {
		this.existeInventarioBD = existeInventarioBD;
	}
	public String getNomeTabelaBD() {
		return nomeTabelaBD;
	}
	public void setNomeTabelaBD(String nomeTabelaBD) {
		this.nomeTabelaBD = nomeTabelaBD;
	}
	public String getCobacia() {
		return cobacia;
	}
	public void setCobacia(String cobacia) {
		this.cobacia = cobacia;
	}




	public int [] getnAnosSFSazonal() {
		return nAnosSFSazonal;
	}




	public void setnAnosSFSazonal(int [] nAnosSFSazonal) {
		this.nAnosSFSazonal = nAnosSFSazonal;
	}


public String getCodigoResponsavel() {
  return codigoResponsavel;
}


public void setCodigoResponsavel(String codigoResponsavel) {
  this.codigoResponsavel = codigoResponsavel;
}


public String getCodigoOperadora() {
  return codigoOperadora;
}


public void setCodigoOperadora(String codigoOperadora) {
  this.codigoOperadora = codigoOperadora;
}


public String getCodigoAdicional() {
  return codigoAdicional;
}


public void setCodigoAdicional(String codigoAdicional) {
  this.codigoAdicional = codigoAdicional;
}


public String getSiglaResponsavel() {
  return siglaResponsavel;
}


public void setSiglaResponsavel(String siglaResponsavel) {
  this.siglaResponsavel = siglaResponsavel;
}


public String getSiglaOperadora() {
  return siglaOperadora;
}


public void setSiglaOperadora(String siglaOperadora) {
  this.siglaOperadora = siglaOperadora;
}


public String getEmoperacao() {
  return emoperacao;
}


public void setEmoperacao(String emoperacao) {
  this.emoperacao = emoperacao;
}


public String getUltimaAtualizacao() {
  return ultimaAtualizacao;
}


public void setUltimaAtualizacao(String ultimaAtualizacao) {
  this.ultimaAtualizacao = ultimaAtualizacao;
}
}
