package gui.tableResultTrendTeste;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import gui.PanelTrendDetectionStreamflowBrazil;
import types.SimulationDataExtremos;

public class FrameResultadoEstacionaridadeAllGauges extends JFrame {

	private SimulationDataExtremos simulationData;
	private GuiResultAllGauges guiResultestac;
	private PanelTrendDetectionStreamflowBrazil pnt;
	
	public FrameResultadoEstacionaridadeAllGauges(SimulationDataExtremos simulationData){
		super("Resultados Estacionaridade");
		this.simulationData = simulationData;
		//this.simultipo=1;
		this.createMainFrame();
		this.createTabbedPane();
		//this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	

	public FrameResultadoEstacionaridadeAllGauges(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt){
		super("Resultados Estacionaridade");
		this.simulationData = simulationData;
		this.pnt=pnt;
		this.createMainFrame();
		this.createTabbedPane(this.pnt);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	

	private void createMainFrame() {
		this.setBounds(50, 50, 1000, 400);
		this.setLayout(new BorderLayout());
		this.setResizable(true);
		
	}
	
     private void createTabbedPane(PanelTrendDetectionStreamflowBrazil pnt) {
		this.guiResultestac = new GuiResultAllGauges (this.simulationData,pnt,this);
		//this.add(this.guiResultestac);
	 }
	
     private void createTabbedPane() {
		this.guiResultestac = new GuiResultAllGauges (this.simulationData);
		this.add(this.guiResultestac);
	 }

}
