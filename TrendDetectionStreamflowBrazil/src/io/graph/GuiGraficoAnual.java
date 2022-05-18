package io.graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;

import org.jfree.ui.RefineryUtilities;
/*import org.snirh.extremos_unb.deteccao.gui.FrameGraficosAnual;
import org.snirh.extremos_unb.deteccao.gui.GuiGraficoAnual;
import org.snirh.extremos_unb.deteccao.gui.PanelGraficoAnual;
import org.snirh.extremos_unb.deteccao.gui.PanelImportarDados;
import org.snirh.extremos_unb.deteccao.gui.GuiGraficoAnual.executarGraficoLinhaAnual;
import org.snirh.extremos_unb.tipos.DadoTemporal;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;*/

import gui.PanelTrendDetectionStreamflowBrazil;
import types.DadoTemporal;
import types.SimulationDataExtremos;
import util.PegarSerieEstatisticaPadrao;

public class GuiGraficoAnual extends JPanel {

	private static final long serialVersionUID = 1L;
	
   private JTabbedPane tabResults;
   
   private PanelGraficoAnual panelResultGraficoDistrib;
   private SimulationDataExtremos simulationData;
   private PanelTrendDetectionStreamflowBrazil pid;
   private FrameGraficosAnual frm;
   
   
   
   public  GuiGraficoAnual(SimulationDataExtremos simulationData){
	   this.simulationData = simulationData;
	   this.createAndShowGUI(); 
   }
   
   private void createAndShowGUI() {		
	this.setBackground(Color.LIGHT_GRAY);
  	this.setLayout(new BorderLayout());
	this.formatPanels();
	
	}
	
	private void formatPanels(){
   	this.tabResults = new JTabbedPane();
   	this.tabResults.setBackground(this.getBackground());
   	this.tabResults.setBorder(new EtchedBorder());
   	this.tabResults.setBounds(0, 0, 550, 300);
   
   	
   	int nseries=0;
   	for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
   	    if(this.simulationData.getVariaveisHidr().get(i).isSelecionada())	{	
   	    String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
   	    
   	    int tipoSerie=this.simulationData.getTipoSerieFalhaEstacionaridade();
   	// PanelTrendDetectionStreamflowBrazil pid=new PanelTrendDetectionStreamflowBrazil();
   	    PegarSerieEstatisticaPadrao pid=new PegarSerieEstatisticaPadrao();
		Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
		
		int anodiv=this.simulationData.getVariaveisHidr().get(i).getSerietemporal().pegarAnoMetadeSerie();
		//boolean normalizar=false;
		//int iflagAnodiv=0;
		//serieMapa=pid.pegarSerieEstatistica(this.simulationData.getVariaveisHidr().get(i), this.simulationData, anodiv, iflagAnodiv, normalizar);
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
		
		
		
		//int nseries=0;
		if(serieNew.size() >= tamanhoMinimoSerie){
		    //this.panelResultGraficoDistrib = new PanelGraficoAnual(this.simulationData.getVariaveisHidr().get(i),codigo,serieMapa,anodiv);
		    this.panelResultGraficoDistrib = new PanelGraficoAnual(this.simulationData,this.simulationData.getVariaveisHidr().get(i),codigo,serieMapa,anodiv);
			this.tabResults.addTab(codigo, this.panelResultGraficoDistrib.getComponent(0));
			nseries++;
		}
		
		
   	    
   	    }
   		
   	}
      this.simulationData.setnSeriesSelecionadas(nseries);	
   	this.add(this.tabResults, BorderLayout.CENTER);
	}
	
	
	
	public  GuiGraficoAnual(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pid, FrameGraficosAnual frm){
		  
		   this.pid=pid;
		   this.frm=frm;
		   this.simulationData = simulationData;
		   this.createAndShowGUI(this.pid,this.frm); 
		   
	   }
	
	
	
	 private void createAndShowGUI(PanelTrendDetectionStreamflowBrazil pid, FrameGraficosAnual frm) {		
			this.setBackground(Color.LIGHT_GRAY);
		  	this.setLayout(new BorderLayout());
			this.formatPanels(pid,frm);
			
			}
	
	
	
	      private void formatPanels(PanelTrendDetectionStreamflowBrazil pid, FrameGraficosAnual frm){
		   	this.tabResults = new JTabbedPane();
		   	this.tabResults.setBackground(this.getBackground());
		   	this.tabResults.setBorder(new EtchedBorder());
		   	this.tabResults.setBounds(0, 0, 550, 300);
		   
		   	executarGraficoLinhaAnual exegraf= new executarGraficoLinhaAnual(this.simulationData,pid,this,this.tabResults,this.panelResultGraficoDistrib, frm);
		   	exegraf.addPropertyChangeListener(this.pid);
		   	exegraf.execute();
		   	
		   
			}
	
	
	
	      class executarGraficoLinhaAnual extends SwingWorker<Void, String> {

	  		private SimulationDataExtremos simulationData;
	  		private PanelTrendDetectionStreamflowBrazil pid;
	  		private GuiGraficoAnual guiresgraf;
	  		private JTabbedPane tabResults;
	  		private PanelGraficoAnual panelResultGrafico;
	  		private FrameGraficosAnual frm;
	  		
	  		private int iestfim;
	  		
	  		public executarGraficoLinhaAnual(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pid,GuiGraficoAnual guiresgraf,JTabbedPane tabResults,PanelGraficoAnual panelResultGrafico,FrameGraficosAnual frm){
	  			
	  			
	  			this.simulationData=simulationData;
	  			this.pid=pid;
	  			this.guiresgraf=guiresgraf;
	  			this.tabResults=tabResults;
	  			this.panelResultGrafico=panelResultGrafico;
	  			this.frm=frm;
	  			
	  			
	  		}
	  		
	  		
	  		protected Void doInBackground() throws Exception {
				
	  			
	  			setProgress(0);
				String textointerface= "Iniciando desenho dos gráficos  ";
			    publish(textointerface);
				int progress = 0;
		        setProgress(0);
				int nestac=this.simulationData.getVariaveisHidr().size();
				int iestfim=0;
	  			for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
	  		   	    if(this.simulationData.getVariaveisHidr().get(i).isSelecionada())	{	
	  		   	   String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
	  		   	    
	  		   	    int tipoSerie=this.simulationData.getTipoSerieFalhaEstacionaridade();
	  		   // PanelTrendDetectionStreamflowBrazil pid=new PanelTrendDetectionStreamflowBrazil();
	  		   	    PegarSerieEstatisticaPadrao pid=new PegarSerieEstatisticaPadrao();
	  				Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
	  				
	  				int anodiv=this.simulationData.getVariaveisHidr().get(i).getSerietemporal().pegarAnoMetadeSerie();
	  				//boolean normalizar=false;
	  				//int iflagAnodiv=0;
	  				//serieMapa=pid.pegarSerieEstatistica(this.simulationData.getVariaveisHidr().get(i), this.simulationData, anodiv, iflagAnodiv, normalizar);
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
	  					//this.panelResultGraficoDistrib = new PanelGraficoAnual(this.simulationData,this.simulationData.getVariaveisHidr().get(i),codigo,serieMapa,anodiv);
	  				    this.panelResultGrafico = new PanelGraficoAnual(this.simulationData,this.simulationData.getVariaveisHidr().get(i),codigo,serieMapa,anodiv);
	  					this.tabResults.addTab(codigo, this.panelResultGrafico.getComponent(0));
	  					iestfim++;
	  				}
	  				
	  				
	  		   	    
	  		   	    }
	  		   	    
	  		   	  double progress2 = ((i+1)*1.0/(this.simulationData.getVariaveisHidr().size()*1.0))*100;
			   	  progress=(int) progress2;
			      setProgress(Math.min(progress, 100));
			      textointerface= "Aguarde..desenhando  o(s) gráfico(s) da estação  "+(i+1)+"/"+nestac+"";
			      publish(textointerface);
				  System.out.println(textointerface);
	  		   		
	  		   	}
	  		      	
	  			this.guiresgraf.add(this.tabResults, BorderLayout.CENTER);
	  		    this.iestfim=iestfim;
	  			
	  			
	  			return null;
	  		
	  			
	  		}
	  		
	  		
	  		protected void process(List<String> text) {
			    
		    	 //this.pid.lblAguardeThread.setText(text.get(0));
		    
		     }
		    
		     protected void done() {
		    	 String textointerface="Gráficos da Séries Selecionadas - "+this.iestfim+" Séries Selecionadas";
		    	this.frm.setTitle(this.iestfim+" Gauge Annual Time Series");
		    	//this.pid.lblAguardeThread.setText(textointerface);
		    	this.frm.setContentPane(this.guiresgraf); 
		    	this.frm.setVisible(true);
		    	this.frm.pack();
		 		RefineryUtilities.centerFrameOnScreen(this.frm);
		     
		    	 //Messages.informMsg("Definição dos Gráficos com os Resultados do Ajuste das Distribuições de Probabilidade efetuado com sucesso");
		     
		     } 
	  		
	  		
	      }
	
	
	
	
	
	

}
