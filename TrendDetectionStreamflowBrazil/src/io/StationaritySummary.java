package io;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;



import gui.PanelTrendDetectionStreamflowBrazil;

import types.ResultEstacionaridade;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;

/*import org.snirh.extremos_unb.deteccao.gui.PanelTestesEstatisticos;
import org.snirh.extremos_unb.deteccao.testes.ResultEstacionaridade;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.extremos_unb.tipos.VariavelHidrologica;
import org.snirh.extremos_unb.util.ExtensionFileFilter;*/

public class StationaritySummary {
	private SimulationDataExtremos simulationData;
	//private GuiResulEstac guiResultestac;
	private PanelTrendDetectionStreamflowBrazil pnt;
	
	public StationaritySummary(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt) {
		this.simulationData = simulationData;
		this.pnt=pnt;
		//this.resumoResultadosProgress();
	}
	
	
	public void resumoResultadosProgress(){
		executarResumoEstacionaridade boot= new executarResumoEstacionaridade(this.simulationData,this.pnt);
	    boot.addPropertyChangeListener(this.pnt);
		boot.execute();
		
	}
	
	class executarResumoEstacionaridade extends SwingWorker<Void, String> {

		private SimulationDataExtremos simulationData;
	    private PanelTrendDetectionStreamflowBrazil pnt;    
	    private ProgressMonitor progressMonitor;
	    private JTextArea taskOutput;
	    private JFileChooser chooser;
		private ExtensionFileFilter filter;
		public final static String newline = "\n";
		
		public executarResumoEstacionaridade (SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt){
			
			 this.simulationData=simulationData;
			 this.pnt=pnt;
			 
		}
		
		
		
		
		@Override
		protected Void doInBackground() throws Exception {
			
			int ntestes=14;
			String [] nometeste2=new String [ntestes];
			
			nometeste2[0]="MK";
			nometeste2[1]="SR";
			nometeste2[2]="LR";
			nometeste2[3]="TT";
			nometeste2[4]="DC";
			nometeste2[5]="CD";
			nometeste2[6]="WR";
			nometeste2[7]="MW";
			nometeste2[8]="TF";
			nometeste2[9]="MC";
			nometeste2[10]="TP";
			nometeste2[11]="RD";
			nometeste2[12]="AC";
			nometeste2[13]="WW";
			
			String [] nometesteExtenso=new String [ntestes];
			nometesteExtenso[0]="Mann-Kendall:              ";
			nometesteExtenso[1]="Spearman’s Rho:            ";
			nometesteExtenso[2]="Linear Regression:         ";
			nometesteExtenso[3]="Teste T:                   ";
			nometesteExtenso[4]="Distribution CUSUM:        ";
			nometesteExtenso[5]="Cumulative Deviation:      ";
			nometesteExtenso[6]="Worsley Lik. Ratio:        ";
			nometesteExtenso[7]="Rank-Sum (Mann-Whitney):   ";
			nometesteExtenso[8]="Teste F:                   ";
			nometesteExtenso[9]="Median Crossing:           ";
			nometesteExtenso[10]="Turning Points:           ";
			nometesteExtenso[11]="Rank Difference:          ";
			nometesteExtenso[12]="Autocorrelation:          ";
			nometesteExtenso[13]="Wald-Wolfowitz:           ";
			
			
			
			String [] nomeTipoEstacio=new String [4];
			
			nomeTipoEstacio[0]="TENDENCIA";
			nomeTipoEstacio[1]="SALTO";
			nomeTipoEstacio[2]="VARIANCIA";
			nomeTipoEstacio[3]="INDEPENDENCIA";
			
			
			String [] nomeSentidoTend=new String [2];
			nomeSentidoTend[0]="Increase";
			nomeSentidoTend[1]="Decrease";
			
			
			String [] nomeSentidoMedia=new String [2];
			nomeSentidoMedia[0]="Maior";
			nomeSentidoMedia[1]="Menor";
			
			int ndecadas=9;
			int [] decada=new int [ndecadas];
			
			String [] nometesteAnoMud=new String [3];
			nometesteAnoMud[0]="DC";
			nometesteAnoMud[1]="CD";
			nometesteAnoMud[2]="WR";
				
			
			//int decadaIni=1930;
			
			decada[0]=1929;
			int passo=10;
			for(int i=1;i<ndecadas;i++){
			decada[i]=decada[i-1]+passo;
			}
			
			double [] resDecadaMudanca= new double [ndecadas];
			double [] resDecadaMudanca_S= new double [ndecadas];
			double [] resDecadaMudanca_NS= new double [ndecadas];
					
			double [][] resDecadaMudancaTeste= new double [3] [ndecadas];		
			double [][] resDecadaMudancaTeste_S= new double [3] [ndecadas];
			double [][] resDecadaMudancaTeste_NS= new double [3] [ndecadas];
					
			double [] resTipoEstacio_NS= new double [4];
			double [] resTipoTeste_NS= new double [14];
			
			double [] resTipoEstacio_S= new double [4];
			double [] resTipoTeste_S= new double [14];
					
			double [] resSentidoTend= new double [2];
			double [] resSentidoTend_NS= new double [2];
			double [] resSentidoTend_S= new double [2];
			
			double [][] resSentidoTendTeste_S= new double [3][2];
			double [][] resSentidoTendTeste_NS= new double [3][2];
			
			
			
			
			double [] resSentidoMedia= new double [2];
			double [] resSentidoMedia_NS= new double [2];
			double [] resSentidoMedia_S= new double [2];
			double [][] resSentidoMediaTeste_S= new double [5][2];
			double [][] resSentidoMediaTeste_NS= new double [5][2];
			
			/**
			 * percentuais
			 */
			double [] percResTipoEstacio_NS= new double [4];
			double [] percResTipoEstacio_S= new double [4];
			
			
			double [] percResTipoTeste_S= new double [14];
			double [] percResTipoTeste_NS= new double [14];
			
			double [] percResSentidoTend_S=new double [2];
			double [][] percResSentidoTendTeste_S= new double [3][2];
			double [] percResSentidoTend_NS=new double [2];
			double [][] percResSentidoTendTeste_NS= new double [3][2];
			
			double [] percResSentidoTend= new double [2];
			
			
			double [] percResSentidoMedia= new double [2];
			double [] percResSentidoMedia_NS= new double [2];
			double [] percResSentidoMedia_S= new double [2];
			double [][] percResSentidoMediaTeste_S= new double [5][2];
			double [][] percResSentidoMediaTeste_NS= new double [5][2];
			
			double [] percResDecadaMudanca= new double [ndecadas];
			double [] percResDecadaMudanca_S= new double [ndecadas];
			double [] percResDecadaMudanca_NS= new double [ndecadas];
					
			double [][] percResDecadaMudancaTeste= new double [3] [ndecadas];		
			double [][] percResDecadaMudancaTeste_S= new double [3] [ndecadas];
			double [][] percResDecadaMudancaTeste_NS= new double [3] [ndecadas];
			
			setProgress(0);
			String textointerface= "Iniciando o Calculo do Resumo dos Resultados das Estações  ";
		    publish(textointerface);
			int progress = 0;
	        setProgress(0);
	        
			int ngauges=this.simulationData.getVariaveisHidr().size();
			int iestac=0;
			int iestfim=0;
			for(int igauges=0;igauges<this.simulationData.getVariaveisHidr().size();igauges++){
				
		   	    if(this.simulationData.getVariaveisHidr().get(igauges).isSelecionada() && this.simulationData.getVariaveisHidr().get(igauges).isAtendeRestricaoTamMin())	{		
		   	    String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(igauges).getInvhidro().getEstacao_codigo());
		   	    ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
		   	    resultestacionaridade = this.simulationData.getVariaveisHidr().get(igauges).getResultestacionaridade();
		   	   			
		   	    VariavelHidrologica vhid =this.simulationData.getVariaveisHidr().get(igauges);
		   	    
		   	    boolean optTipoEstacionaridade=true;
		   	    
		   	    
		   	    if(optTipoEstacionaridade){
		   	    	
		   	    	for(int i=0;i<resultestacionaridade.size();i++){
		   	    		
		   	    		int ktipo=0;
		   	    		while(resultestacionaridade.get(i).getTipoTeste() != nomeTipoEstacio[ktipo]){
		   	    			ktipo++;	
		   	    		}	
		   	    			if(resultestacionaridade.get(i).getResultadoteste() == "NS"){
		   	    				resTipoEstacio_NS[ktipo]++;	
		   	    			}else{
		   	    				resTipoEstacio_S[ktipo]++;
		   	    			}
		   	    			
		   	    		
		   	    	}
		   	    	
		   	    	for(int i=0;i<resTipoEstacio_NS.length;i++){
		   	    		double soma=resTipoEstacio_NS[i]+resTipoEstacio_S[i];
		   	    		percResTipoEstacio_S[i]=(resTipoEstacio_S[i]/soma)*100.0;
		   	    		percResTipoEstacio_NS[i]=(resTipoEstacio_NS[i]/soma)*100.0;
		   	       	}
		   	    
		   	    	
		   	    	
		   	  }
		   	    		
		   	    
		   	 boolean optTipoTesteEstac=true; 
		   	 if(optTipoTesteEstac){
		   	    		   	    	
		   	    	   	for(int i=0;i<resultestacionaridade.size();i++){
		   	    		int kteste=0;
		   	    		while(resultestacionaridade.get(i).getNometeste() != nometeste2[kteste]){
		   	    		kteste++;		   	    			
		   	    		}
		   	    	    		if(resultestacionaridade.get(i).getResultadoteste() == "NS"){
				   	    			resTipoTeste_NS[kteste]++;	
				   	    		}else{
				   	    	    	resTipoTeste_S[kteste]++;
				   	    		}
		   	    	   	}
		   	    	   	
		   	    	   	
		   	    	   for(int i=0;i<resTipoTeste_NS.length;i++){
			   	    		double soma=resTipoTeste_NS[i]+resTipoTeste_S[i];
			   	    		percResTipoTeste_S[i]=(resTipoTeste_S[i]/soma)*100.0;
			   	    		percResTipoTeste_NS[i]=(resTipoTeste_NS[i]/soma)*100.0;
			   	       	}   	
		   	    	   	
		   	    	   	
		   	
		   	 }
		   	    
		   	   
		   	 
		   	 
		   	boolean optSentidoTend=true;
		   	 
		    if(optSentidoTend){
		    	
		    		for(int i=0;i<resultestacionaridade.size();i++){
	  	    		
		    		if(resultestacionaridade.get(i).getTipoTeste() == nomeTipoEstacio[0])	{
		    		
				    		int kteste=0;
			  	    		while(resultestacionaridade.get(i).getSentidoTendencia() != nomeSentidoTend[kteste]){
			  	    		kteste++;		   	    			
			  	    		}
			  	    		resSentidoTend[kteste]++;
			  	    	    		if(resultestacionaridade.get(i).getResultadoteste() == "NS"){
			  	    	    			resSentidoTend_NS[kteste]++;	
					   	    		}else{
					   	    			resSentidoTend_S[kteste]++;
					   	    		}
			  	    	    		
			  	    	    		
			  	    	    		
			  	    	    		if(resultestacionaridade.get(i).getNometeste() == nometeste2[0]){
			  	    	    			if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
			  	    	    			resSentidoTendTeste_S[0][kteste]++;
			  	    	    			}else{
			  	    	    			resSentidoTendTeste_NS[0][kteste]++;	
			  	    	    			}
			  	    	    		}
			  	    	    		
			  	    	    		if(resultestacionaridade.get(i).getNometeste() == nometeste2[1]){
			  	    	    			if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
			  	    	    			resSentidoTendTeste_S[1][kteste]++;
			  	    	    			}else{
				  	    	    		resSentidoTendTeste_NS[1][kteste]++;	
				  	    	    		}
			  	    	    		}
			  	    	    		
			  	    	    		if(resultestacionaridade.get(i).getNometeste() == nometeste2[2]){
			  	    	    			if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
			  	    	    			resSentidoTendTeste_S[2][kteste]++;
			  	    	    			}else{
				  	    	    		resSentidoTendTeste_NS[2][kteste]++;	
				  	    	    		}
			  	    	    		}
			  	    	    		
			  	    	    		
		    		}
		    		
		    		
		    		
	  	    	   	}
		    		
		    		
		    		
		    		
		    		//for(int i=0;i<resSentidoTend.length;i++){
		   	    		double somaTot=resSentidoTend[0]+resSentidoTend[1];
		   	    		percResSentidoTend[0]=(resSentidoTend[0]/somaTot)*100.0;
		   	    		percResSentidoTend[1]=(resSentidoTend[1]/somaTot)*100.0;
		   	       	//}	
		   	    		
		   	    		
		   	    		for(int i=0;i<resSentidoTend.length;i++){
		   	    			double soma=resSentidoTend_NS[i]+resSentidoTend_S[i];
		   	    			percResSentidoTend_NS[i]=(resSentidoTend_NS[i]/soma)*100.0;
		   	    			percResSentidoTend_S[i]=(resSentidoTend_S[i]/soma)*100.0;
		   	    			
		   	    		}
		    		
		    		
		   	    		for(int i=0;i<resSentidoTend.length;i++){
		   	    			
		   	    			for(int j=0;j<3;j++){
		   	    				double soma=resSentidoTendTeste_S[j][i]+resSentidoTendTeste_NS[j][i];
		   	    				percResSentidoTendTeste_S[j][i]=(resSentidoTendTeste_S[j][i]/soma)*100.0;
		   	    				percResSentidoTendTeste_NS[j][i]=(resSentidoTendTeste_NS[j][i]/soma)*100.0;
		   	    			}
		   	    			
		   	    		}
		    		
		    
		    }
		   	 
		    
			boolean optSentidoMedia=true;
		   	 
		    if(optSentidoMedia){
		    	
		    		for(int i=0;i<resultestacionaridade.size();i++){
		    			if(resultestacionaridade.get(i).getTipoTeste() == nomeTipoEstacio[1])	{
				    		int kteste=0;
			  	    		while(resultestacionaridade.get(i).getSentidoMediaRecente() != nomeSentidoMedia[kteste]){
			  	    		kteste++;		   	    			
			  	    		}
			  	    	
			  	    		resSentidoMedia[kteste]++;
			  	    	    		if(resultestacionaridade.get(i).getResultadoteste() == "NS"){
			  	    	    			resSentidoMedia_NS[kteste]++;	
					   	    		}else{
					   	    			resSentidoMedia_S[kteste]++;
					   	    		}
			  	    	    		
			  	    	    		
				  	    	    		if(resultestacionaridade.get(i).getNometeste() == nometeste2[3]){
				  	    	    			if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
				  	    	    			resSentidoMediaTeste_S[0][kteste]++;
				  	    	    			}else{
				  	    	    			resSentidoMediaTeste_NS[0][kteste]++;	
				  	    	    			}
				  	    	    		}		
				  	    	    		
				  	    	    		if(resultestacionaridade.get(i).getNometeste() == nometeste2[4]){
				  	    	    			
				  	    	    			if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
				  	    	    			resSentidoMediaTeste_S[1][kteste]++;
				  	    	    			}else{
					  	    	    		resSentidoMediaTeste_NS[1][kteste]++;	
					  	    	    		}
				  	    	    			
				  	    	    		}
				  	    	    		
				  	    	    		if(resultestacionaridade.get(i).getNometeste() == nometeste2[5]){
				  	    	    			if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
				  	    	    			resSentidoMediaTeste_S[2][kteste]++;
				  	    	    			}else{
					  	    	    		resSentidoMediaTeste_NS[2][kteste]++;	
					  	    	    		}
				  	    	    		}
				  	    	    		
				  	    	    		if(resultestacionaridade.get(i).getNometeste() == nometeste2[6]){
				  	    	    			if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
				  	    	    			resSentidoMediaTeste_S[3][kteste]++;
				  	    	    			}else{
					  	    	    		resSentidoMediaTeste_NS[3][kteste]++;	
					  	    	    		}
				  	    	    		}
				  	    	    		
				  	    	    		if(resultestacionaridade.get(i).getNometeste() == nometeste2[7]){
				  	    	    			if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
				  	    	    			resSentidoMediaTeste_S[4][kteste]++;
				  	    	    			}else{
					  	    	    		resSentidoMediaTeste_NS[4][kteste]++;	
					  	    	    		}
				  	    	    		}
			  	    	    		
			  	    	    		
		    			}
	  	    	   	}
		    
		    		
		    		double somaTot=resSentidoMedia[0]+resSentidoMedia[1];
	   	    		percResSentidoMedia[0]=(resSentidoMedia[0]/somaTot)*100.0;
	   	    		percResSentidoMedia[1]=(resSentidoMedia[1]/somaTot)*100.0;
	   	       	//}	
	   	    		
	   	    		
	   	    		for(int i=0;i<resSentidoMedia.length;i++){
	   	    			double soma=resSentidoMedia_NS[i]+resSentidoMedia_S[i];
	   	    			percResSentidoMedia_NS[i]=(resSentidoMedia_NS[i]/soma)*100.0;
	   	    			percResSentidoMedia_S[i]=(resSentidoMedia_S[i]/soma)*100.0;
	   	    			
	   	    		}
	    		
	    		
	   	    		for(int i=0;i<resSentidoMedia.length;i++){
	   	    			
	   	    			for(int j=0;j<5;j++){
	   	    				double soma=resSentidoMediaTeste_S[j][i]+resSentidoMediaTeste_NS[j][i];
	   	    				percResSentidoMediaTeste_S[j][i]=(resSentidoMediaTeste_S[j][i]/soma)*100.0;
	   	    				percResSentidoMediaTeste_NS[j][i]=(resSentidoMediaTeste_NS[j][i]/soma)*100.0;
	   	    			}
	   	    			
	   	    		}
		    		
		    		
		    		
		    		
		    		
		    }
		   	    
		    
		    boolean optDecadaMudanca=true;
		   	 
		    if(optDecadaMudanca){
		    	
	           for(int i=0;i<resultestacionaridade.size();i++){
	  	    			    		
	  	    		if(resultestacionaridade.get(i).getNometeste() == nometesteAnoMud[0]){
	  	    			  	    		  
	  	    			int kano=0;
	  	    			int ano=resultestacionaridade.get(i).getAnoMudanca();
	  	   	    		while((kano < decada.length-1) && resultestacionaridade.get(i).getAnoMudanca() > decada[kano]){
	  	   	    		kano++;		   	    			
	  	   	    		}
	  	   	    	    resDecadaMudanca[kano]++;
	  	   	    	    String tp=resultestacionaridade.get(i).getResultadoteste();
					   	   	    	if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
					   	   	    	resDecadaMudanca_S[kano]++;
					   	   	    	resDecadaMudancaTeste_S[0][kano]++;	
					   	    		}
	  	   	    }else if(resultestacionaridade.get(i).getNometeste() == nometesteAnoMud[1]){
	  	   	    	
	  	    			int kano=0;
	  	    			int ano=resultestacionaridade.get(i).getAnoMudanca();
	  	   	    		while((kano < decada.length-1) && resultestacionaridade.get(i).getAnoMudanca() > decada[kano]){
	  	   	    		kano++;		   	    			
	  	   	    		}
	  	   	    	    resDecadaMudanca[kano]++;
	  	   	    	    String tp=resultestacionaridade.get(i).getResultadoteste();
				   	   	    		if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
					   	   	    	resDecadaMudanca_S[kano]++;
					   	   	    	resDecadaMudancaTeste_S[1][kano]++;	
					   	    		}
	  	    			
	  	   	     }else if(resultestacionaridade.get(i).getNometeste() == nometesteAnoMud[2]){
	  	   	   	    	
	  	   	    	    int kano=0;
	  	   	    	    int ano=resultestacionaridade.get(i).getAnoMudanca();
		   	    		while((kano < decada.length-1) && resultestacionaridade.get(i).getAnoMudanca() > decada[kano]){
		   	    		kano++;		   	    			
		   	    		}
		   	    	    resDecadaMudanca[kano]++;
		   	    	    String tp=resultestacionaridade.get(i).getResultadoteste();
		   	    	    			if(resultestacionaridade.get(i).getResultadoteste() != "NS"){
					   	   	    	resDecadaMudanca_S[kano]++;
					   	   	    	resDecadaMudancaTeste_S[2][kano]++;	
					   	    		}
	  	   	   	  }
	  	    		
	  	    	
	  	    	}
		    	
		    }
		    
		    
		    
		    iestfim++;   
		    iestac++;
		   	    }
		   	 System.out.println("estacao = "+igauges+"/"+ngauges+"  estacao = "+iestac); 
		   	 
		   	 
		   	  double progress2 = ((igauges+1)*1.0/(this.simulationData.getVariaveisHidr().size()*1.0))*100;
		      progress=(int) progress2;
		      setProgress(Math.min(progress, 100));
		      textointerface= "Aguarde..executando  o calculo do resumo da estação  "+(igauges+1)+"/"+ngauges+"";
		      publish(textointerface);
			  System.out.println(textointerface);
		   	 
		   	 
			}
			
			
			
			
			this.pnt.setarFrameResumoResultados();
			
			DecimalFormatSymbols dc = new DecimalFormatSymbols();
			dc.setDecimalSeparator(',');
			String strange = "0.00";
			DecimalFormat myFormatter = new DecimalFormat(strange, dc);
			String strange2 = "0.0";
			DecimalFormat myf = new DecimalFormat(strange2, dc);
			this.pnt.textAreaResumo.append("SUMMARY OF RESULTS:   "+ pnt.newline);
			this.pnt.textAreaResumo.append("Percentage of non-significant (NS) and significant (S) results according to the type of change analyzed:"+ pnt.newline);
			this.pnt.textAreaResumo.append("                          NS                     S"+ pnt.newline);
			this.pnt.textAreaResumo.append("Trend:       "+myFormatter.format(percResTipoEstacio_NS[0])+"("+myf.format(resTipoEstacio_NS[0])+")          "+myFormatter.format(percResTipoEstacio_S[0])+"("+myf.format(resTipoEstacio_S[0])+")"+ pnt.newline);
			this.pnt.textAreaResumo.append("Jump    :       "+myFormatter.format(percResTipoEstacio_NS[1])+"("+myf.format(resTipoEstacio_NS[1])+")          "+myFormatter.format(percResTipoEstacio_S[1])+"("+myf.format(resTipoEstacio_S[1])+")"+ pnt.newline);
			this.pnt.textAreaResumo.append("Variance:       "+myFormatter.format(percResTipoEstacio_NS[2])+"("+myf.format(resTipoEstacio_NS[2])+")          "+myFormatter.format(percResTipoEstacio_S[2])+"("+myf.format(resTipoEstacio_S[2])+")"+ pnt.newline);
			this.pnt.textAreaResumo.append("Independence:   "+myFormatter.format(percResTipoEstacio_NS[3])+"("+myf.format(resTipoEstacio_NS[3])+")          "+myFormatter.format(percResTipoEstacio_S[3])+"("+myf.format(resTipoEstacio_S[3])+")"+ pnt.newline);
			
			for(int i=0;i<3;i++){
				this.pnt.textAreaResumo.append(" "+ pnt.newline);
			}
					
			this.pnt.textAreaResumo.append("Percentage of non-significant (NS) and significant (S) results according to the type of test analyzed:"+ pnt.newline);
			this.pnt.textAreaResumo.append("                                           NS                    S"+ pnt.newline);
			for(int i=0;i<ntestes;i++){
				this.pnt.textAreaResumo.append(nometesteExtenso[i]+myFormatter.format(percResTipoTeste_NS[i])+"("+myf.format(resTipoTeste_NS[i])+")          "+myFormatter.format(percResTipoTeste_S[i])+"("+myf.format(resTipoTeste_S[i])+")"+ pnt.newline);	
			}
			
			
			
			for(int i=0;i<3;i++){
				this.pnt.textAreaResumo.append(" "+ newline);
			}
			
			this.pnt.textAreaResumo.append("Percentage of results in which the trend is Increase (C) and Decrease (D) considering statistical significance:"+ newline);
			this.pnt.textAreaResumo.append("                                    Increase     |      Decrease"+ newline);
			this.pnt.textAreaResumo.append("Non-significant:       "+myFormatter.format(percResSentidoTend_NS[0])+"("+myf.format(resSentidoTend_NS[0])+")          "+myFormatter.format(percResSentidoTend_NS[1])+"("+myf.format(resSentidoTend_NS[1])+")"+ newline);
			this.pnt.textAreaResumo.append("Significant:           "+myFormatter.format(percResSentidoTend_S[0])+"("+myf.format(resSentidoTend_S[0])+")          "+myFormatter.format(percResSentidoTend_S[1])+"("+myf.format(resSentidoTend_S[1])+")"+ newline);
			this.pnt.textAreaResumo.append("Total:                  "+myFormatter.format(percResSentidoTend[0])+"("+myf.format(resSentidoTend[0])+")          "+myFormatter.format(percResSentidoTend[1])+"("+myf.format(resSentidoTend[1])+")"+ newline);
			
			for(int i=0;i<2;i++){
				this.pnt.textAreaResumo.append(" "+ newline);
			}
			this.pnt.textAreaResumo.append("Percentage of results in which the trend is Increase (C) and Decrease (D) by Test and Significance:"+ newline);
			
			this.pnt.textAreaResumo.append("                                                      Increase       |      Decrease"+ newline);
			for(int i=0;i<3;i++){
				this.pnt.textAreaResumo.append(nometesteExtenso[i]+"| (NS)   |    "+myFormatter.format(percResSentidoTendTeste_NS[i][0])+"("+myf.format(resSentidoTendTeste_NS[i][0])+")      |      "+myFormatter.format(percResSentidoTendTeste_NS[i][1])+"("+myf.format(resSentidoTendTeste_NS[i][1])+")"+ newline);
				this.pnt.textAreaResumo.append(nometesteExtenso[i]+"| (S)    |    "+myFormatter.format(percResSentidoTendTeste_S[i][0])+"("+myf.format(resSentidoTendTeste_S[i][0])+")      |       "+myFormatter.format(percResSentidoTendTeste_S[i][1])+"("+myf.format(resSentidoTendTeste_S[i][1])+")"+ newline);
				this.pnt.textAreaResumo.append("--------------------------------------------------------------------------------------------- "+ newline);
			}
			
			
			for(int i=0;i<3;i++){
				this.pnt.textAreaResumo.append(" "+ newline);
			}
				
			this.pnt.textAreaResumo.append("Percentage of results in which the most recent Mean was Higher or Lower considering statistical significance:"+ newline);
			this.pnt.textAreaResumo.append("                                 Higher       |      Lower"+ newline);
			this.pnt.textAreaResumo.append("Non-significant:       "+myFormatter.format(percResSentidoMedia_NS[0])+"("+myf.format(resSentidoMedia_NS[0])+")          "+myFormatter.format(percResSentidoMedia_NS[1])+"("+myf.format(resSentidoMedia_NS[1])+")"+ newline);
			this.pnt.textAreaResumo.append("Significant:           "+myFormatter.format(percResSentidoMedia_S[0])+"("+myf.format(resSentidoMedia_S[0])+")          "+myFormatter.format(percResSentidoMedia_S[1])+"("+myf.format(resSentidoMedia_S[1])+")"+ newline);
			this.pnt.textAreaResumo.append("Total:                  "+myFormatter.format(percResSentidoMedia[0])+"("+myf.format(resSentidoMedia[0])+")          "+myFormatter.format(percResSentidoMedia[1])+"("+myf.format(resSentidoMedia[1])+")"+ newline);
				
				for(int i=0;i<2;i++){
					this.pnt.textAreaResumo.append(" "+ newline);
				}
				this.pnt.textAreaResumo.append("Percentage of results in which the most recent Mean was Major or Minor by Test and Significance:"+ newline);
				
				this.pnt.textAreaResumo.append("                                               Higher             |           Lower"+ newline);
				for(int i=0;i<5;i++){
					this.pnt.textAreaResumo.append(nometesteExtenso[i+3]+"| (NS)   |    "+myFormatter.format(percResSentidoMediaTeste_NS[i][0])+"("+myf.format(resSentidoMediaTeste_NS[i][0])+")      |      "+myFormatter.format(percResSentidoMediaTeste_NS[i][1])+"("+myf.format(resSentidoMediaTeste_NS[i][1])+")"+ newline);
					this.pnt.textAreaResumo.append(nometesteExtenso[i+3]+"| (S)    |    "+myFormatter.format(percResSentidoMediaTeste_S[i][0])+"("+myf.format(resSentidoMediaTeste_S[i][0])+")      |       "+myFormatter.format(percResSentidoMediaTeste_S[i][1])+"("+myf.format(resSentidoMediaTeste_S[i][1])+")"+ newline);
					this.pnt.textAreaResumo.append("--------------------------------------------------------------------------------------------- "+ newline);
				}
			
			
				this.pnt.frameResumo.pack();
				this.pnt.frameResumo.setVisible(true);
			
			System.out.println("finalizou");
			textointerface= "resumo dos resultados da(s) "+iestfim+" estação(s) efetuado com sucesso";	
			publish(textointerface); 
			textointerface= "resumo dos resultados da(s) "+iestfim+" estação(s) efetuado com sucesso";	
			publish(textointerface);
			textointerface= "resumo dos resultados da(s) "+iestfim+" estação(s) efetuado com sucesso";	
			publish(textointerface);
			
			return null;
			
		}
	
	
		protected void process(List<String> text) {
		    
	    	// this.pnt.lblAguardeThread.setText(text.get(0));
	    
	     }
	    
	     protected void done() {
	     
	    	// Messages.informMsg("Arquivo excel construido com sucesso");
	     
	     } 
		
		
		
		
	}
}
