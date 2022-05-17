package tests.autocorrelationApproaches;

import java.util.ArrayList;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class PegarRoLag1 {

	private double r1amostral;
	private double r1AmostralCorrigido;
	
	public void executar(ArrayList<Double> amostra) {
		
		double [] serie1=new double[amostra.size()-1];
    	double [] serie2=new double[amostra.size()-1];
    	for (int k5=0;k5<amostra.size()-1;k5++){
    		serie1[k5]=amostra.get(k5);
    		serie2[k5]=amostra.get(k5+1);
    	}
    	
    	PearsonsCorrelation pcor=new PearsonsCorrelation();
	   	this.r1amostral=pcor.correlation(serie1, serie2);
    	int n=amostra.size();
    	this.r1AmostralCorrigido=(n*this.r1amostral+2)/(n-4);
	}

	public double getR1amostral() {
		return r1amostral;
	}

	public void setR1amostral(double r1amostral) {
		this.r1amostral = r1amostral;
	}

	public double getR1AmostralCorrigido() {
		return r1AmostralCorrigido;
	}

	public void setR1AmostralCorrigido(double r1AmostralCorrigido) {
		this.r1AmostralCorrigido = r1AmostralCorrigido;
	}
	
	
}
