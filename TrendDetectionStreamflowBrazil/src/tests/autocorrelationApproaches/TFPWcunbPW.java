package tests.autocorrelationApproaches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import tests.MannKendallTest;
import types.DadoTemporal;


public class TFPWcunbPW {

	/**
	 * Aqui só tira a tendencia pra estimar o Ro depois faz o PW na amostra original
	 * @param amostra
	 * @return
	 */
	
	
public Map<String,DadoTemporal> executar (Map<String,DadoTemporal> serieMapa) {
		
		
		ArrayList<DadoTemporal> dadostot = new ArrayList<DadoTemporal>();
  		Set<String> chavesMax =  serieMapa.keySet();
	  	for (String data : chavesMax){
	  	DadoTemporal dado = serieMapa.get(data);
	  	dadostot.add(dado);
	   	}
	  	//
    	Collections.sort(dadostot);
    	
    	FuncaoAutoCorrelacaoAnual facOriginal =new FuncaoAutoCorrelacaoAnual(dadostot);
    	Map<Integer,DadoTemporal> mapaAnoDadosOriginal=facOriginal.getMapaAnoDados();
   	    Map<Integer,Integer>mapaAnoOrdem=PegarOrdemCronologicaAnoSerieTemporal.pegarMapaAnoOrdem(dadostot);  	
   	 
   	 Map<String,DadoTemporal> Yt=new HashMap<String,DadoTemporal>(); //Serie com tendencia (original) removida
   	 Map<Integer,Double> Ylt=new HashMap<Integer,Double>(); // Serie com autocorrelação removida
   	 Map<Integer,Double> Y=new HashMap<Integer,Double>(); //Serie com adicao da tendencia mas sem correlacao
   	 Map<String,DadoTemporal> serieMapaY=new HashMap<String,DadoTemporal>(); //Serie MAPA com adicao da tendencia mas sem correlacao
   	/**
   	 * Calcula o b de sen (slope ou declividade)
   	 */
   	 double bsemCronologia=MagnitudeTendencia.bSen(dadostot);
   	 double b=MagnitudeTendencia.bSenRespeitandoCronologia(dadostot);
     	Set<Integer> chaves = mapaAnoDadosOriginal.keySet();
     	int ival=0;
	  	for (Integer ano : chaves){	
   		DadoTemporal dado=new DadoTemporal();
   		//double t=ano; //Importante para considerar a distancia entre dois anos que entre eles exista falha
   		double t=mapaAnoOrdem.get(ano);
   		double xt=mapaAnoDadosOriginal.get(ano).getValor();
   		double yt=xt-b*t;
   			    		
   		
   		dado.setData(mapaAnoDadosOriginal.get(ano).getData());
   		dado.setValor(yt);
   	    Yt.put(mapaAnoDadosOriginal.get(ano).getDataStr(), dado);
   	 ival++;
   	}
   	
   	int klag=1;
   	FuncaoAutoCorrelacaoAnual fac =new FuncaoAutoCorrelacaoAnual(Yt);
		double r1Original=fac.correlLagMapa(1);
		//int n=facOriginal.getSeries1().length+1;
		int n=ival;
   	 //int n=dadostot.size();
   	   double r1AmostralCorrigido=(n*r1Original+2)/(n-4);
   	   String resultado=TesteCorrelacaoLag1Tradicional.executar(r1AmostralCorrigido, n);
	   if(resultado.equals("NS")) {
			return serieMapa;
		}
		
		
		ArrayList<Integer>ano=facOriginal.getAno();
		Map<Integer,DadoTemporal> mapaAnoDados=facOriginal.getMapaAnoDados();
     for (int i=0;i<ano.size();i++){
  	   	Integer anolag=ano.get(i)-klag;
	  		if(mapaAnoDadosOriginal.containsKey(anolag)){
	  			if(mapaAnoDadosOriginal.get(anolag).getValor() != -99999. || mapaAnoDadosOriginal.get(anolag).getValor() > 0){
	  				double yt=mapaAnoDadosOriginal.get(ano.get(i)).getValor();
	  				double yt1=mapaAnoDadosOriginal.get(anolag).getValor();
	  				double ylt=yt-r1AmostralCorrigido*yt1;
	  				Ylt.put(ano.get(i), ylt);
	  			}
	  		}
   	}
     
     
     
     Set<Integer> chavesAno = Ylt.keySet();
	  	for (Integer anoFinal : chavesAno){
	  		double t=mapaAnoOrdem.get(anoFinal);
	  		double ylt=Ylt.get(anoFinal);
	  		//double y=ylt+b*t;
	  		Y.put(anoFinal, ylt);
	  		DadoTemporal dado=new DadoTemporal();
	  		String datastr="01/01/"+anoFinal;
	  		dado.setData(datastr);
	  		dado.setValor(ylt);
	  		serieMapaY.put(datastr, dado);
	  	}
		return serieMapaY;
     
}
	
	
public ArrayList<Double> executar(ArrayList<Double> amostra){
		
		ArrayList<Double> amostraDetrend=new ArrayList<Double>();
		double bsen=MagnitudeTendencia.bSenDouble(amostra);
		
		for(int i=0;i<amostra.size();i++) {
			int t=i+1;
			double xt=amostra.get(i);
			double xtmod=xt-bsen*t;
			amostraDetrend.add(xtmod);
		}
			
		
		PegarRoLag1 estimarRo1=new PegarRoLag1();
		estimarRo1.executar(amostraDetrend);
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
			amostraPW.add(ytmod);
			
		}
		
		return amostraPW;
		
	}
	
public ArrayList<Double> executar_V2(ArrayList<Double> amostra){
	
	/*ArrayList<Double> amostraDetrend=new ArrayList<Double>();
	double bsen=MagnitudeTendencia.bSenDouble(amostra);
	
	for(int i=0;i<amostra.size();i++) {
		int t=i+1;
		double xt=amostra.get(i);
		double xtmod=xt-bsen*t;
		amostraDetrend.add(xtmod);
	}*/
		
	
	boolean foiSignificativo=false;
	MannKendallTest mkteste=new MannKendallTest(amostra);
    mkteste.teste1();
    double pvalueferahmk=mkteste.getPvalue();
    double alfa=0.05;
     if(pvalueferahmk < alfa){
      foiSignificativo=true;
     }
     ArrayList<Double> amostraDetrend=new ArrayList<Double>();
    DescriptiveStatistics amostraXtmod=new DescriptiveStatistics();
	//DescriptiveStatistics amostraYtmod=new DescriptiveStatistics();
	double bsen=MagnitudeTendencia.bSenDouble(amostra);
   	if(foiSignificativo) {
   		
			for(int i=0;i<amostra.size();i++) {
				int t=i+1;
				double xt=amostra.get(i);
				double xtmod=xt-bsen*t;
				amostraXtmod.addValue(xtmod);
				amostraDetrend.add(xtmod);
			}
		
   	}else{
   		double bsennulo=0.0;
		
		for(int i=0;i<amostra.size();i++) {
			int t=i+1;
			double xt=amostra.get(i);
			double xtmod=xt-bsennulo*t;
			amostraXtmod.addValue(xtmod);
			amostraDetrend.add(xtmod);
		}
   		
   	}
	
	PegarRoLag1 estimarRo1=new PegarRoLag1();
	estimarRo1.executar(amostraDetrend);
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
		amostraPW.add(ytmod);
		
	}
	
	return amostraPW;
	
}




public ArrayList<Double> executar_V3(ArrayList<Double> amostra){
	
	ArrayList<Double> amostraDetrend=new ArrayList<Double>();
	double bsen=MagnitudeTendencia.bSenDouble(amostra);
	
	for(int i=0;i<amostra.size();i++) {
		int t=i+1;
		double xt=amostra.get(i);
		double xtmod=xt-bsen*t;
		amostraDetrend.add(xtmod);
	}
		
	
	PegarRoLag1 estimarRo1=new PegarRoLag1();
	estimarRo1.executar(amostraDetrend);
	double r1=estimarRo1.getR1amostral();
	//double r1corrigido=estimarRo1.getR1AmostralCorrigido();
	int n=amostra.size();
	String resultado=TesteCorrelacaoLag1Tradicional.executar(r1, n);
	
	if(resultado.equals("NS")) {
		return amostra;
	}
	
	ArrayList<Double> amostraPW=new ArrayList<Double>();
	for(int i=1;i<amostra.size();i++) {
		//int t=i+1;
		double xtmodmenos1=amostra.get(i-1);
		double xtmod=amostra.get(i);
		double ytmod=xtmod-r1*xtmodmenos1;
		amostraPW.add(ytmod);
		
	}
	
	return amostraPW;
	
}


}
