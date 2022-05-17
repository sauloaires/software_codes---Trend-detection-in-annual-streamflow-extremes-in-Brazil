package tests.autocorrelationApproaches;

import java.util.ArrayList;





public class BS {

	/**
	 * Bootstrap
	 */
	
	public double [] executar_bloco2(ArrayList<Double> amostra) {
		ArrayList<double []> valoresCriticos = new ArrayList<double []>();
	    ArrayList<ArrayList<Double>> distribTotTestes = new ArrayList<ArrayList<Double>>();
	    double nivelsignificancia=5.0;
	    int tipoHipotese=0;
	    
	    PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostra);
		//double r1=estimarRo1.getR1amostral();
		double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1corrigido, n);
		
		if(resultado.equals("NS")) {
			double [] vcritico=new double [2];
			vcritico[0]=-99999.;
			vcritico[1]=-99999.;
			return vcritico;
		}
	    	   
		Bootstrap bts = new Bootstrap(amostra);
		
		boolean bootBloco=false;
		int namostras=2000;
		int l=2;
			bts.obterSerieBootstrapBlocoCronologicoAno(namostras,l);
			//ArrayList<Boolean> opTestes=this.listaTesteSelecionados();
			valoresCriticos=bts.obterDistribValorCritico(tipoHipotese,nivelsignificancia);
			distribTotTestes=bts.getResultTotTestes();
			return valoresCriticos.get(0);
		
	}
	
	
	public double [] executar_bloco3(ArrayList<Double> amostra) {
		ArrayList<double []> valoresCriticos = new ArrayList<double []>();
	    ArrayList<ArrayList<Double>> distribTotTestes = new ArrayList<ArrayList<Double>>();
	    double nivelsignificancia=5.0;
	    int tipoHipotese=0;
	    
		Bootstrap bts = new Bootstrap(amostra);
		
		boolean bootBloco=false;
		int namostras=2000;
		int l=3;
			bts.obterSerieBootstrapBlocoCronologicoAno(namostras,l);
			//ArrayList<Boolean> opTestes=this.listaTesteSelecionados();
			valoresCriticos=bts.obterDistribValorCritico(tipoHipotese,nivelsignificancia);
			distribTotTestes=bts.getResultTotTestes();
			return valoresCriticos.get(0);
		
	}
	
	
	public double [] executar_bloco4(ArrayList<Double> amostra) {
		ArrayList<double []> valoresCriticos = new ArrayList<double []>();
	    ArrayList<ArrayList<Double>> distribTotTestes = new ArrayList<ArrayList<Double>>();
	    double nivelsignificancia=5.0;
	    int tipoHipotese=0;
	    
		Bootstrap bts = new Bootstrap(amostra);
		
		boolean bootBloco=false;
		int namostras=2000;
		int l=4;
			bts.obterSerieBootstrapBlocoCronologicoAno(namostras,l);
			//ArrayList<Boolean> opTestes=this.listaTesteSelecionados();
			valoresCriticos=bts.obterDistribValorCritico(tipoHipotese,nivelsignificancia);
			distribTotTestes=bts.getResultTotTestes();
			return valoresCriticos.get(0);
		
	}
}
