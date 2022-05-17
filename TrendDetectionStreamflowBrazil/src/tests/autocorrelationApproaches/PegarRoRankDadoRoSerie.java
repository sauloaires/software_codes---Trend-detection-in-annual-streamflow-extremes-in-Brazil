package tests.autocorrelationApproaches;

public class PegarRoRankDadoRoSerie {

	/**
	 * Retirado do artigo abaixo
	 * Wenpeng Wang, Yuanfang Chen, Stefan Becker 2 and Bo Liu. 
	 * Linear Trend Detection in Serially Dependent Hydrometeorological Data Based on a Variance Correction Spearman Rho Method. 
	 * Water 2015, 7, 7045–7065; doi:10.3390/w7126673
	 * @param r1
	 * @return
	 */
	public static double executar(double r1) {
		
		double r1Rank=0.0;
		double termo1=6.0/Math.PI;
		double termo2 = Math.asin(r1/2.0);
		r1Rank=termo1*termo2;
				
		return r1Rank;
	}
	
}
