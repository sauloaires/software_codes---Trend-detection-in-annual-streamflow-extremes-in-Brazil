package types;



public class ResultEstacionaridade {
	private double  pvalue;
	private Double  estatteste;
	private double  valorcriticoteste;
	private String  resultadoteste;
	private String nometeste;
	private String tiposerie;
	private String Descriçãoteste;
	
	private String sentidoTendencia;
	private String sentidoMediaRecente;
	private String sentidoMediaAntiga;
	private int anoMudanca;
	private String metodoObterValCritico;
	private String resultadoDescritivoTeste;
	private String tipoTeste;
	
	
	
	private double bsen;
	private double media;
	private double nanos;
	private double bsenRel;
	private double nanosPeriodo;
	
	private ResultadoBsen resbsen;
	
	private ResultadoFDR resultadoFDR;
	
	
	public void setResbsen(ResultadoBsen resbsen) {
		this.resbsen = resbsen;
		this.setBsen(this.resbsen.getBsen());
		this.setMedia(this.resbsen.getMedia());
		this.setBsenRel(this.resbsen.getBsenRel());
		this.setNanos(this.resbsen.getNanos());
		this.setNanosPeriodo(this.resbsen.getNanosPeriodo());
		
	}
	
	public double getBsen() {
		return bsen;
	}

	public void setBsen(double bsen) {
		this.bsen = bsen;
	
		
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double getNanos() {
		return nanos;
	}

	public void setNanos(double nanos) {
		this.nanos = nanos;
	}

	public double getBsenRel() {
		return bsenRel;
	}

	public void setBsenRel(double bsenRel) {
		this.bsenRel = bsenRel;
	}

	public double getNanosPeriodo() {
		return nanosPeriodo;
	}

	public void setNanosPeriodo(double nanosPeriodo) {
		this.nanosPeriodo = nanosPeriodo;
	}

	public ResultEstacionaridade(String nometeste,String tiposerie){
		
		this.nometeste=nometeste;
		this.tiposerie=tiposerie;
		
	}
	
     public ResultEstacionaridade(String nometeste){
		
		this.nometeste=nometeste;
		this.tiposerie="Não Informada";
		
	}

	public double getPvalue() {
		return pvalue;
	}

	public void setPvalue(double pvalue) {
		this.pvalue = pvalue;
	}

	public double getEstatteste() {
		return estatteste;
	}

	public void setEstatteste(Double estatteste) {
		this.estatteste = estatteste;
	}

	public double getValorcriticoteste() {
		return valorcriticoteste;
	}

	public void setValorcriticoteste(double valorcriticoteste) {
		this.valorcriticoteste = valorcriticoteste;
	}

	public String getResultadoteste() {
		return resultadoteste;
	}

	public void setResultadoteste(String resultadoteste) {
		this.resultadoteste = resultadoteste;
	}

	public String getNometeste() {
		return nometeste;
	}

	public void setNometeste(String nometeste) {
		this.nometeste = nometeste;
	}

	public String getTiposerie() {
		return tiposerie;
	}

	public void setTiposerie(String tiposerie) {
		this.tiposerie = tiposerie;
	}

	public String getDescriçãoteste() {
		return Descriçãoteste;
	}

	public void setDescriçãoteste(String descriçãoteste) {
		Descriçãoteste = descriçãoteste;
	}

	public String getSentidoTendencia() {
		return sentidoTendencia;
	}

	public void setSentidoTendencia(String sentidoTendencia) {
		this.sentidoTendencia = sentidoTendencia;
	}

	public String getSentidoMediaRecente() {
		return sentidoMediaRecente;
	}

	public void setSentidoMediaRecente(String sentidoMediaRecente) {
		this.sentidoMediaRecente = sentidoMediaRecente;
	}

	public int getAnoMudanca() {
		return anoMudanca;
	}

	public void setAnoMudanca(int anoMudanca) {
		this.anoMudanca = anoMudanca;
	}

	public String getMetodoObterValCritico() {
		return metodoObterValCritico;
	}

	public void setMetodoObterValCritico(String metodoObterValCritico) {
		this.metodoObterValCritico = metodoObterValCritico;
	}

	public String getResultadoDescritivoTeste() {
		return resultadoDescritivoTeste;
	}

	public void setResultadoDescritivoTeste(String resultadoDescritivoTeste) {
		this.resultadoDescritivoTeste = resultadoDescritivoTeste;
	}

	public String getSentidoMediaAntiga() {
		return sentidoMediaAntiga;
	}

	public void setSentidoMediaAntiga(String sentidoMediaAntiga) {
		this.sentidoMediaAntiga = sentidoMediaAntiga;
	}

	public String getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(String tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	public ResultadoBsen getResbsen() {
		return resbsen;
	}

	public ResultadoFDR getResultadoFDR() {
		return resultadoFDR;
	}

	public void setResultadoFDR(ResultadoFDR resultadoFDR) {
		this.resultadoFDR = resultadoFDR;
	}

	
	
}

