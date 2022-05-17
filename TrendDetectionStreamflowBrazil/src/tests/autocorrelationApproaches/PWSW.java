package tests.autocorrelationApproaches;

import java.util.ArrayList;

public class PWSW {

	public ArrayList<Double> executar(ArrayList<Double> amostra){
		
		
		PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostra);
		//double r1=estimarRo1.getR1amostral();
		double r1corrigido=estimarRo1.getR1AmostralCorrigido();
		int n=amostra.size();
		String resultado=TesteCorrelacaoLag1Tradicional.executar(r1corrigido, n);
		
		if(resultado.equals("NS")) {
			return amostra;
		}

		ArrayList<Double> amostraPW=new ArrayList<Double>();
		for(int i=1;i<amostra.size();i++) {
			//int t=i+1;
			double xtmodmenos1=amostra.get(i-1);
			double xtmod=amostra.get(i);
			double ytmod=xtmod-r1corrigido*xtmodmenos1;
			double ytmodcorr=(ytmod)/(1-r1corrigido);
			amostraPW.add(ytmodcorr);
			
		}
		
	
	return amostraPW;
	
}
	
	
}
