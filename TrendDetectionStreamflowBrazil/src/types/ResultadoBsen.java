package types;

public class ResultadoBsen {

	
	private double bsen;
	private double media;
	private double nanos;
	private double bsenRel;
	private double nanosPeriodo;
	
	private double mediaPeriodo1;
	private double mediaPeriodo2;
	
	private Double bsenRelAnual;
	private Double Cv;
	private Integer tamanhon;
	
	
	
	
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
	
	public double getMediaPeriodo1() {
		return mediaPeriodo1;
	}
	public void setMediaPeriodo1(double mediaPeriodo1) {
		this.mediaPeriodo1 = mediaPeriodo1;
	}
	public double getMediaPeriodo2() {
		return mediaPeriodo2;
	}
	public void setMediaPeriodo2(double mediaPeriodo2) {
		this.mediaPeriodo2 = mediaPeriodo2;
	}
	public Double getBsenRelAnual() {
		return bsenRelAnual;
	}
	public void setBsenRelAnual(Double bsenRelAnual) {
		this.bsenRelAnual = bsenRelAnual;
	}
	public Integer getTamanhon() {
		return tamanhon;
	}
	public void setTamanhon(Integer tamanhon) {
		this.tamanhon = tamanhon;
	}
	public void setCv(Double cv) {
		Cv = cv;
	}
	public Double getCv() {
		return Cv;
	}
	
	
	
}
