package gui;

import org.jfree.ui.RefineryUtilities;

import types.SimulationDataExtremos;


public class MainTrendDetectionStreamflowBrazil_Workbench {

	
	
	private void panelExecuteGuiMain() {
		SimulationDataExtremos simulationData=new SimulationDataExtremos();
		
		simulationData.setTipoSerieHidroMaximos("SF");
		simulationData.setTipoSerieFalhaEstacionaridade(1);
		simulationData.setTolFalhaMax(0.0);
		simulationData.setMesIniEstacionaridade(1);
		simulationData.setMesFimEstacionaridade(12);
		simulationData.setTamMinSerietotEstacionaridade(30);
		simulationData.setCodEstatisticaSelecionadaEstacionaridade(1);
		simulationData.setAnoIniSubConjunto(-99999);
		simulationData.setAnoFimSubConjunto(-99999);
		simulationData.setConsiderarAutoCorrelacao(true);
		simulationData.setFazerFDR(true);
		simulationData.setFazerFDRClassico(true);
		
		final PanelTrendDetectionStreamflowBrazil panelSimulaExtremosUNB = new PanelTrendDetectionStreamflowBrazil(simulationData);
		panelSimulaExtremosUNB.setVisible(true);
		panelSimulaExtremosUNB.pack();
		RefineryUtilities.centerFrameOnScreen(panelSimulaExtremosUNB);
		
		
		/*final PanelTestesEstatisticos panelTestesEstatisticos = new PanelTestesEstatisticos(simulationData);
		panelTestesEstatisticos.setVisible(true);
		panelTestesEstatisticos.pack();
		RefineryUtilities.centerFrameOnScreen(panelTestesEstatisticos);*/
		
		
	}
	
	public static void main(String[] args) {
		
		MainTrendDetectionStreamflowBrazil_Workbench mainTrend=new MainTrendDetectionStreamflowBrazil_Workbench();
		mainTrend.panelExecuteGuiMain();
	}
	
	
	
}
