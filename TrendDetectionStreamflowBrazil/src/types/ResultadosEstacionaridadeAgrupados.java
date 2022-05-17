package types;

import java.util.ArrayList;

public class ResultadosEstacionaridadeAgrupados {
	private double nestacTotal;
	private String nomeTeste;
	private String codigo;
	private String nomeIndice;
	
	
	private double NS;
	private double NSperc;
	
	private double S;
	private double Sperc;
	
	private double C;
	private double Cperc;
	
	private double D;
	private double Dperc;
	
	private double NSC;
	private double NSCperc;
	
	private double NSD;
	private double NSDperc;
	
	private double SC;
	private double SCperc;
	
	private double SD;
	private double SDperc;
	
	
	private double SIC;
	private double SICperc;
	
	private double SID;
	private double SIDperc;
	
	
	
	public ArrayList<Double> pegarResultadosLinhasTabela(){
		
		ArrayList<Double> resLinha=new ArrayList<Double>();
		resLinha.add(this.NSperc);
		resLinha.add(this.NS);
		resLinha.add(this.Sperc);
		resLinha.add(this.S);
		resLinha.add(this.Cperc);
		resLinha.add(this.C);
		resLinha.add(this.Dperc);
		resLinha.add(this.D);
		resLinha.add(this.NSCperc);
		resLinha.add(this.NSC);
		resLinha.add(this.NSDperc);
		resLinha.add(this.NSD);
		resLinha.add(this.SCperc);
		resLinha.add(this.SC);
		resLinha.add(this.SDperc);
		resLinha.add(this.SD);
		resLinha.add(this.SICperc);
		resLinha.add(this.SIC);
		resLinha.add(this.SIDperc);
		resLinha.add(this.SID);
		
		
		
		return resLinha;
		
		
		
		
	}
	
	
	
	public double getNestacTotal() {
		return nestacTotal;
	}
	public void setNestacTotal(double nestacTotal) {
		this.nestacTotal = nestacTotal;
	}
	public String getNomeTeste() {
		return nomeTeste;
	}
	public void setNomeTeste(String nomeTeste) {
		this.nomeTeste = nomeTeste;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
	public double getNS() {
		return NS;
	}
	public void setNS(double nS) {
		NS = nS;
	}
	public double getNSperc() {
		return NSperc;
	}
	public void setNSperc(double nSperc) {
		NSperc = nSperc;
	}
	public double getS() {
		return S;
	}
	public void setS(double s) {
		S = s;
	}
	public double getSperc() {
		return Sperc;
	}
	public void setSperc(double sperc) {
		Sperc = sperc;
	}
	public double getC() {
		return C;
	}
	public void setC(double c) {
		C = c;
	}
	public double getCperc() {
		return Cperc;
	}
	public void setCperc(double cperc) {
		Cperc = cperc;
	}
	public double getD() {
		return D;
	}
	public void setD(double d) {
		D = d;
	}
	public double getDperc() {
		return Dperc;
	}
	public void setDperc(double dperc) {
		Dperc = dperc;
	}
	public double getNSC() {
		return NSC;
	}
	public void setNSC(double nSC) {
		NSC = nSC;
	}
	public double getNSCperc() {
		return NSCperc;
	}
	public void setNSCperc(double nSCperc) {
		NSCperc = nSCperc;
	}
	public double getNSD() {
		return NSD;
	}
	public void setNSD(double nSD) {
		NSD = nSD;
	}
	public double getNSDperc() {
		return NSDperc;
	}
	public void setNSDperc(double nSDperc) {
		NSDperc = nSDperc;
	}
	public double getSC() {
		return SC;
	}
	public void setSC(double sC) {
		SC = sC;
	}
	public double getSCperc() {
		return SCperc;
	}
	public void setSCperc(double sCperc) {
		SCperc = sCperc;
	}
	public double getSD() {
		return SD;
	}
	public void setSD(double sD) {
		SD = sD;
	}
	public double getSDperc() {
		return SDperc;
	}
	public void setSDperc(double sDperc) {
		SDperc = sDperc;
	}
	public double getSIC() {
		return SIC;
	}
	public void setSIC(double sIC) {
		SIC = sIC;
	}
	public double getSICperc() {
		return SICperc;
	}
	public void setSICperc(double sICperc) {
		SICperc = sICperc;
	}
	public double getSID() {
		return SID;
	}
	public void setSID(double sID) {
		SID = sID;
	}
	public double getSIDperc() {
		return SIDperc;
	}
	public void setSIDperc(double sIDperc) {
		SIDperc = sIDperc;
	}
	public String getNomeIndice() {
		return nomeIndice;
	}
	public void setNomeIndice(String nomeIndice) {
		this.nomeIndice = nomeIndice;
	}
}
