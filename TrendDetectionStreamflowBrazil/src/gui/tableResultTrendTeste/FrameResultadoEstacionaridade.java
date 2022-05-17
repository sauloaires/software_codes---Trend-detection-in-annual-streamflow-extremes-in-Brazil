package gui.tableResultTrendTeste;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import gui.PanelTrendDetectionStreamflowBrazil;
import types.SimulationDataExtremos;



public class FrameResultadoEstacionaridade extends JFrame {

	private SimulationDataExtremos simulationData;
	private GuiResulEstac guiResultestac;
	private PanelTrendDetectionStreamflowBrazil pnt;
	
	public FrameResultadoEstacionaridade(SimulationDataExtremos simulationData){
		super("Resultados Estacionaridade");
		this.simulationData = simulationData;
		//this.simultipo=1;
		this.createMainFrame();
		this.createTabbedPane();
		//this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	

	public FrameResultadoEstacionaridade(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt){
		super("Resultados Estacionaridade");
		this.simulationData = simulationData;
		this.pnt=pnt;
		this.createMainFrame();
		this.createTabbedPane(this.pnt);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	

	private void createMainFrame() {
		this.setBounds(100, 100, 1000, 400);
		this.setLayout(new BorderLayout());
		this.setResizable(true);
		
	}
	
     private void createTabbedPane(PanelTrendDetectionStreamflowBrazil pnt) {
		this.guiResultestac = new GuiResulEstac (this.simulationData,pnt,this);
		//this.add(this.guiResultestac);
	 }
	
     private void createTabbedPane() {
		this.guiResultestac = new GuiResulEstac (this.simulationData);
		this.add(this.guiResultestac);
	 }

}
