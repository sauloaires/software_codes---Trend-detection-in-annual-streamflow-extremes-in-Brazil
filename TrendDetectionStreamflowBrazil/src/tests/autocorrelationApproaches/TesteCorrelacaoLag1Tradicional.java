package tests.autocorrelationApproaches;

public class TesteCorrelacaoLag1Tradicional {
	public static String executar(double r1amostral, double n) {
		
		String resultado="NS";
		double limsup=(-1+(1.96*Math.sqrt(n-2)))/(n-1);
		double liminf=(-1-(1.96*Math.sqrt(n-2)))/(n-1);
		if(r1amostral > limsup) {
			resultado="S";
		}else if(r1amostral < liminf) {
			resultado="S";
		}
		return resultado;
	
}
}
