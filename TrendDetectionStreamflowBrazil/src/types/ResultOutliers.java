package types;


public class ResultOutliers {
	private DadoTemporal outlier;
	private String codigo;
	private String metodo;
	private double linf;
	private double lsup;
	private String tipo;
    private String datastr;
	private boolean excluirSerie;
	
	
	
	public DadoTemporal getOutlier() {
		return outlier;
	}
	public void setOutlier(DadoTemporal outlier) {
		this.outlier = outlier;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public double getLinf() {
		return linf;
	}
	public void setLinf(double linf) {
		this.linf = linf;
	}
	public double getLsup() {
		return lsup;
	}
	public void setLsup(double lsup) {
		this.lsup = lsup;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDatastr() {
		return datastr;
	}
	public void setDatastr(String datastr) {
		this.datastr = datastr;
	}
	public boolean isExcluirSerie() {
		return excluirSerie;
	}
	public void setExcluirSerie(boolean excluirSerie) {
		this.excluirSerie = excluirSerie;
	}
	
}
