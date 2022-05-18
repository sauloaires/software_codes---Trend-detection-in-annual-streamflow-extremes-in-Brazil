package io.graph;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Log;
import org.jfree.util.LogContext;

import gui.PanelTrendDetectionStreamflowBrazil;
import types.DadoTemporal;
import types.SimulationDataExtremos;
/*import org.snirh.extremos_unb.deteccao.gui.GraficoBoxPlotPanel;
import org.snirh.extremos_unb.deteccao.gui.PanelImportarDados;
import org.snirh.extremos_unb.deteccao.gui.GraficoBoxPlotPanel.executarGraficoBoxPlot;
import org.snirh.extremos_unb.tipos.DadoTemporal;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.testes.BoxAndWhiskerDemo;*/
import util.PegarSerieEstatisticaPadrao;

public class GraficoBoxPlotPanel extends  JFrame{
	 /** Access to logging facilities. */
   // private static final LogContext LOGGER = Log.createContext(BoxAndWhiskerDemo.class);
    SimulationDataExtremos simulationData;
	private PanelTrendDetectionStreamflowBrazil pid;
    
    
    
	public GraficoBoxPlotPanel(final String title,SimulationDataExtremos simulationData){
		
		super(title);
		this.simulationData=simulationData;
		final BoxAndWhiskerCategoryDataset dataset = criarSerieMaximosBox();
		
		String nomeEstat=this.simulationData.getTipoEstatisticaSelecionadaEstacionaridade();
		String unidadeVariavel=this.simulationData.getVariaveisHidr().get(0).getInvhidro().getUnidadeDaVariavel();
		String nomeDaVariavel=this.simulationData.getVariaveisHidr().get(0).getInvhidro().getTipodeDado();
		unidadeVariavel="m³/s";
		  
		  String tituloEixoY= nomeEstat+" da "+nomeDaVariavel+"("+unidadeVariavel+")";

        final CategoryAxis xAxis = new CategoryAxis("Código das Estações");
        //final NumberAxis yAxis = new NumberAxis("Vazões Máximas (m³/s)");
        final NumberAxis yAxis = new NumberAxis(tituloEixoY);
        yAxis.setAutoRangeIncludesZero(false);
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
       // renderer.setItemLabelsVisible(true);
      //  renderer.setSeriesVisibleInLegend(true);
       // renderer.setMeanVisible(false);
        //renderer.set
        renderer.setSeriesFillPaint(0, Color.green);
        
        final JFreeChart chart = new JFreeChart(
            "Box Plot da +"+tituloEixoY,
            new Font("SansSerif", Font.BOLD, 14),
            plot,
            true
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        setContentPane(chartPanel);
       

    }

	
	
private BoxAndWhiskerCategoryDataset criarSerieMaximosBox() {
        
        final int seriesCount = 1;
        
        final DefaultBoxAndWhiskerCategoryDataset dataset 
            = new DefaultBoxAndWhiskerCategoryDataset();
        
    	for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
       	    if(this.simulationData.getVariaveisHidr().get(i).isSelecionada())	{
            	
       	    	for (int j = 0; j < seriesCount; j++) {
                    
       	    		
       	    		if(j == 0){
                               
		                String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
		           	    int tipoSerie=this.simulationData.getTipoSerieFalhaEstacionaridade();
		           	   String tipoSeriestr=simulationData.getTipoSerieHidroMaximos();
		           	 PegarSerieEstatisticaPadrao pid=new PegarSerieEstatisticaPadrao();
		        		Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
		        		int anodiv=this.simulationData.getVariaveisHidr().get(i).getSerietemporal().pegarAnoMetadeSerie();
		        		//boolean normalizar=false;
		        		//int iflagAnodiv=0;
		        		//serieMapa=pid.pegarSerieMaximos(this.simulationData.getVariaveisHidr().get(i), this.simulationData, anodiv, iflagAnodiv, normalizar);
		        		int anoIni=this.simulationData.getAnoIniSerie1Estacionaridade();
		    			int anoFim=this.simulationData.getAnoIniSerie1Estacionaridade();
		    			serieMapa=pid.pegarSerieEstatistica(this.simulationData.getVariaveisHidr().get(i), this.simulationData, anoIni,anoFim);
		        		
		        		
		        		ArrayList<Double> serieNew=new ArrayList<Double>();
		        		
		        		Set<String> chaves =  serieMapa.keySet();
		        	  	for (String data : chaves){
		        	  	DadoTemporal dado = serieMapa.get(data);
		        	  	serieNew.add(dado.getValor());
		        	   	}
		        	
		        		int tamanhoMinimoSerie=this.simulationData.getTamMinSerietotEstacionaridade();
		        		//int anodiv=this.simulationData.getVariaveisHidr().get(i).getSerietemporal().pegarAnoMetadeSerie();
		        		if(serieNew.size() >= tamanhoMinimoSerie){
		        			dataset.add(serieNew, tipoSeriestr, codigo);  
		           	   
		        		}
              
                    }
                
         
                //LOGGER.debug("Adicionando series " + i);
                //LOGGER.debug(list.toString());
                
                                
            }
         
       	    
       	    }
       	    
       	    
        }

        return dataset;
    }
	
	
	
public GraficoBoxPlotPanel(final String title,SimulationDataExtremos simulationData, PanelTrendDetectionStreamflowBrazil pid){
	super(title);
	
	this.simulationData=simulationData;
    this.pid=pid;
    executarGraficoBoxPlot exegraf= new executarGraficoBoxPlot(this.simulationData,this.pid,this);
   	exegraf.addPropertyChangeListener(this.pid);
   	exegraf.execute();
   

}




	
class executarGraficoBoxPlot extends SwingWorker<Void, String> {

		private SimulationDataExtremos simulationData;
		private PanelTrendDetectionStreamflowBrazil pid;
		private GraficoBoxPlotPanel graficoBoxPlotPanel;
		private int iestfim;
		
		public executarGraficoBoxPlot(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pid,GraficoBoxPlotPanel graficoBoxPlotPanel){
			
			this.simulationData=simulationData;
			this.pid=pid;
			this.graficoBoxPlotPanel=graficoBoxPlotPanel;
			
		}
		
		
		protected Void doInBackground() throws Exception {
		
			String nomeEstat=this.simulationData.getTipoEstatisticaSelecionadaEstacionaridade();
		  	String especificidadeEstatistica="";
		  	String unidadeVariavel="m³/s";
		  	if(nomeEstat.equals("PERMANENCIA") ){
				  especificidadeEstatistica=String.valueOf(this.simulationData.getPercentil()+" %");
			  }else if(nomeEstat.equals("MMmin")){
				  especificidadeEstatistica=String.valueOf(this.simulationData.getTamanhoMediaMovelMin()+ " dias");
			  }else if(nomeEstat.equals("NDlim")){
				  especificidadeEstatistica=String.valueOf(this.simulationData.getTamanhoMediaMovelMin()+" "+unidadeVariavel);
			  }
			  
			  
			 String nomeseries= nomeEstat+" "+especificidadeEstatistica;
		
	
		//this.simulationData=simulationData;
		final BoxAndWhiskerCategoryDataset dataset = criarSerieMaximosBox();
	    final CategoryAxis xAxis = new CategoryAxis("Gauge Code");
	   // final NumberAxis yAxis = new NumberAxis("Vazões Máximas (m³/s)");
	    final NumberAxis yAxis = new NumberAxis(nomeseries);
	    yAxis.setAutoRangeIncludesZero(false);
	    final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
	    renderer.setFillBox(true);
	    renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
	    final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
	     renderer.setSeriesFillPaint(0, Color.green);
	    
	    final JFreeChart chart = new JFreeChart(
	    		"Graph "+nomeseries,
	        new Font("SansSerif", Font.BOLD, 14),
	        plot,
	        true
	    );
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(700, 500));
	    setContentPane(chartPanel);	
			
	
	    
	    return null;
		
			
		}
		
		
		
		private BoxAndWhiskerCategoryDataset criarSerieMaximosBox() {
	        
	        final int seriesCount = 1;
	        
	        final DefaultBoxAndWhiskerCategoryDataset dataset 
	            = new DefaultBoxAndWhiskerCategoryDataset();
	        
	        
	        
	        setProgress(0);
			String textointerface= "Iniciando desenho dos gráficos box plot  ";
		    publish(textointerface);
			int progress = 0;
	        setProgress(0);
			int nestac=this.simulationData.getVariaveisHidr().size();
			int iestfim=0;
	      	        
			
	    	for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
	    		
	       	    if(this.simulationData.getVariaveisHidr().get(i).isSelecionada())	{
	            	
	       	    	for (int j = 0; j < seriesCount; j++) {
	                    
	       	    		
	       	    		if(j == 0){
	                               
	       	    			String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
			           	    int tipoSerie=this.simulationData.getTipoSerieFalhaEstacionaridade();
			           	   String tipoSeriestr=simulationData.getTipoSerieHidroMaximos();
			           	 PegarSerieEstatisticaPadrao pid=new PegarSerieEstatisticaPadrao();
			        		Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
			        		int anodiv=this.simulationData.getVariaveisHidr().get(i).getSerietemporal().pegarAnoMetadeSerie();
			        		//boolean normalizar=false;
			        		//int iflagAnodiv=0;
			        		//serieMapa=pid.pegarSerieMaximos(this.simulationData.getVariaveisHidr().get(i), this.simulationData, anodiv, iflagAnodiv, normalizar);
			        		int anoIni=this.simulationData.getAnoIniSubConjunto();
			  				int anoFim=this.simulationData.getAnoFimSubConjunto();
			    			serieMapa=pid.pegarSerieEstatistica(this.simulationData.getVariaveisHidr().get(i), this.simulationData, anoIni,anoFim);
			        		
			        		
			        		ArrayList<Double> serieNew=new ArrayList<Double>();
			        		
			        		Set<String> chaves =  serieMapa.keySet();
			        	  	for (String data : chaves){
			        	  	DadoTemporal dado = serieMapa.get(data);
			        	  	serieNew.add(dado.getValor());
			        	   	}
			        	
			        		int tamanhoMinimoSerie=this.simulationData.getTamMinSerietotEstacionaridade();
			        		//int anodiv=this.simulationData.getVariaveisHidr().get(i).getSerietemporal().pegarAnoMetadeSerie();
			        		if(serieNew.size() >= tamanhoMinimoSerie){
			        			dataset.add(serieNew, tipoSeriestr, codigo);  
			        			iestfim++;
			        		}
	              
	                    }
	                
	         
	               // LOGGER.debug("Adicionando series " + i);
	                //LOGGER.debug(list.toString());
	                
	                                
	            }
	         
	       	    
	       	    }
	       	    
	       	    
	       	  double progress2 = ((i+1)*1.0/(this.simulationData.getVariaveisHidr().size()*1.0))*100;
		   	  progress=(int) progress2;
		      setProgress(Math.min(progress, 100));
		      textointerface= "Aguarde..desenhando  o(s) gráfico(s) da estação  "+(i+1)+"/"+nestac+"";
		      publish(textointerface);
			  System.out.println(textointerface); 
	       	    
	       	    
	       	    
	        }
	    	
	    	this.iestfim=iestfim;
	    	
	        return dataset;
	    }
		
		
		
		
		
		
		protected void process(List<String> text) {
	    
    	 //this.pid.lblAguardeThread.setText(text.get(0));
    
        }
    
     protected void done() {
    	     
    	this.graficoBoxPlotPanel.setTitle(this.iestfim+" Gauges Annual Time Series");
       	this.graficoBoxPlotPanel.pack();
       	//this.pid.lblAguardeThread.setText("Gráficos desenhados com sucesso");
 	    RefineryUtilities.centerFrameOnScreen(this.graficoBoxPlotPanel);
 	    this.graficoBoxPlotPanel.setVisible(true);
 	 	this.graficoBoxPlotPanel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	 
    	 
    	 
     } 
		
		
  }

	
	
}
