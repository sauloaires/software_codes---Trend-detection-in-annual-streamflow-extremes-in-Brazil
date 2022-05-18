package io.graph;

import javax.swing.JFrame;

import gui.PanelTrendDetectionStreamflowBrazil;
import types.SimulationDataExtremos;

/*import org.snirh.extremos_unb.deteccao.gui.GuiGraficoAnual;
import org.snirh.extremos_unb.deteccao.gui.PanelImportarDados;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;*/

public class FrameGraficosAnual extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimulationDataExtremos simulationData;
	private GuiGraficoAnual guiResultgraficodistrib;
	private PanelTrendDetectionStreamflowBrazil pid;
	
	public FrameGraficosAnual(SimulationDataExtremos simulationData){
		super("Graphs of Selected Series");
		this.simulationData = simulationData;
		this.createMainFrame();
		this.createTabbedPane();
		
		this.setTitle("Graphs of Selected Series - "+this.simulationData.getnSeriesSelecionadas()+" Selected Series");
		
		
	}

	

	private void createMainFrame() {
		this.setBounds(100, 100, 1000, 700);
	
	}
	
     private void createTabbedPane() {
		this.guiResultgraficodistrib = new GuiGraficoAnual (this.simulationData);
		
		this.setContentPane(this.guiResultgraficodistrib);
	
	 }
	
     public FrameGraficosAnual(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pid){
  		super("Graphs of Gauge Series");
  		this.pid=pid;
  		this.simulationData = simulationData;
  		this.createMainFrame();
  		this.createTabbedPane(this.pid);
  		
  	}
      
      
      private void createTabbedPane(PanelTrendDetectionStreamflowBrazil pid) {
  		this.guiResultgraficodistrib = new GuiGraficoAnual (this.simulationData,this.pid,this);
  		
  		//this.setContentPane(this.guiResultgraficodistrib);
  	 } 

}
