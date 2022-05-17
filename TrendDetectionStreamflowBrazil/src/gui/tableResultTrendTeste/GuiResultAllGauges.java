package gui.tableResultTrendTeste;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;

import org.jfree.ui.RefineryUtilities;

import gui.PanelTrendDetectionStreamflowBrazil;
import gui.tableResultTrendTeste.GuiResulEstac.executarTabelaResultados;
import types.ResultEstacionaridade;
import types.SimulationDataExtremos;

public class GuiResultAllGauges extends JPanel {

	private static final long serialVersionUID = 1L;
	
   private JTabbedPane tabResults;
   
   private PanelResultEstacTableAllGauges panelResultEstacTable;
   private SimulationDataExtremos simulationData;
   private FrameResultadoEstacionaridadeAllGauges frm;
   private PanelTrendDetectionStreamflowBrazil pnt;
   
   
   public  GuiResultAllGauges(SimulationDataExtremos simulationData){
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
   	//this.tabResults.setLayout(new BorderLayout());
   	//this.panelEstatistics = new PanelEstatistics(this.simulationData);
   //	this.tabResults.addTab("Estatística", this.panelEstatistics);
   	
   	
   	for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
   	    if(this.simulationData.getVariaveisHidr().get(i).isSelecionada())	{		
   	    String codigo=this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo();
   	    ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
   	    resultestacionaridade = this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade();
   	    this.panelResultEstacTable = new PanelResultEstacTableAllGauges(this.simulationData);
   	    this.tabResults.addTab(codigo, this.panelResultEstacTable);
   		}
   		
   	}
      	
   	this.add(this.tabResults, BorderLayout.CENTER);
	}
	
	
	
	
	
	public  GuiResultAllGauges(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt,FrameResultadoEstacionaridadeAllGauges frm){
		   this.simulationData = simulationData;
		   this.pnt=pnt;
		   this.frm=frm;
		   this.createAndShowGUI(this.pnt,this.frm); 
	   }
	   
	   
	   
	   private void createAndShowGUI(PanelTrendDetectionStreamflowBrazil pnt,FrameResultadoEstacionaridadeAllGauges frm) {		
		this.setBackground(Color.LIGHT_GRAY);
	  	this.setLayout(new BorderLayout());
		this.formatPanels(pnt,frm);
		
		}
	   
	   
		
		private void formatPanels(PanelTrendDetectionStreamflowBrazil pnt,FrameResultadoEstacionaridadeAllGauges frm){
	   	this.tabResults = new JTabbedPane();
	   	this.tabResults.setBackground(this.getBackground());
	   	this.tabResults.setBorder(new EtchedBorder());
	   	this.tabResults.setBounds(0, 0, 550, 300);
	   	
	   	executarTabelaResultadosAllGauges exeModProb= new executarTabelaResultadosAllGauges(this.simulationData,this.pnt,this,this.tabResults,this.panelResultEstacTable, frm);
		 exeModProb.addPropertyChangeListener(this.pnt);
		 exeModProb.execute();
	   	
	   
		}
	
	
	
	
	
	
	
		class executarTabelaResultadosAllGauges extends SwingWorker<Void, String> {

			private SimulationDataExtremos simulationData;
		    private PanelTrendDetectionStreamflowBrazil pnt;    
		    private ProgressMonitor progressMonitor;
		    private JTextArea taskOutput;
			private PanelResultEstacTableAllGauges panelResultEstacTable;
			private JTabbedPane tabResults;
			private GuiResultAllGauges guiresgraf;
			private FrameResultadoEstacionaridadeAllGauges frm;
			private int iestfim;
			
			public executarTabelaResultadosAllGauges (SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt,GuiResultAllGauges guiresgraf,JTabbedPane tabResults,PanelResultEstacTableAllGauges panelResultEstacTable,FrameResultadoEstacionaridadeAllGauges frm){
				
				 this.simulationData=simulationData;
				 this.pnt=pnt;
				 this.guiresgraf=guiresgraf;
				 this.tabResults=tabResults;
				 this.panelResultEstacTable=panelResultEstacTable;
				 this.frm=frm;
			}
			
			
			
			
			@Override
			protected Void doInBackground() throws Exception {
				
				
				setProgress(0);
				String textointerface= "Iniciando preenchimento da tabela de resultados de estacionaridade das estações  ";
			    publish(textointerface);
				int progress = 0;
		        setProgress(0);
				int nestac=this.simulationData.getVariaveisHidr().size();
			   	int iestfim=0;
			   	
			   	this.panelResultEstacTable = new PanelResultEstacTableAllGauges(this.simulationData);
	   	   	    this.tabResults.addTab("Mann-Kendall", this.panelResultEstacTable.getComponent(0));
			   	
			   /*	for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
			   		if(this.simulationData.getVariaveisHidr().get(i).isSelecionada() && this.simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin())	{		
			   	   	    String codigo=this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo();
			   	   	    ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
			   	   	    resultestacionaridade = this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade();
			   	   	    this.panelResultEstacTable = new PanelResultEstacTable(this.simulationData,resultestacionaridade);
			   	   	    this.tabResults.addTab(codigo, this.panelResultEstacTable.getComponent(0));
			   	   	     iestfim++;
			   	   		}
			   		
			   	  double progress2 = ((i+1)*1.0/(this.simulationData.getVariaveisHidr().size()*1.0))*100;
			   	  progress=(int) progress2;
			      setProgress(Math.min(progress, 100));
			      textointerface= "Aguarde..preenchendo  a tabela de resultados da estação  "+(i+1)+"/"+nestac+"";
			      
			      publish(textointerface);
				  System.out.println(textointerface);
			   	  if(i == 88) {
			   		  System.out.println();
			   	  }
			   	    
			   	}*/
			      	
			   	
			   	
			   	
			   	//textointerface= "preenchido a tabela de resultados da estação  ";
			    //publish(textointerface);
			   	this.guiresgraf.add(this.tabResults, BorderLayout.CENTER);
				this.iestfim=iestfim;			   	
			   	/*textointerface= "tabela de resultados da(s) "+iestfim;
			   	String textointerface2=" estação(s) efetuado com sucesso";
			   	publish(textointerface,textointerface2);
			   	textointerface= "tabela de resultados da(s) "+iestfim+" estação(s) efetuado com sucesso";
			   	publish(textointerface);
			   	textointerface= "tabela de resultados da(s) "+iestfim+" estação(s) efetuado com sucesso";
			   	publish(textointerface);*/
			   	
				return null;
				
			}
		
		
			protected void process(List<String> text) {
			    
		    	// this.pnt.lblAguardeThread.setText(text.get(0));
		    
		     }
		    
		     protected void done() {
		    	 String textointerface= "tabela de resultados da(s) "+this.iestfim+" estação(s) efetuado com sucesso";
		    	// this.pnt.lblAguardeThread.setText(textointerface);
				 publish(textointerface);
		    	this.frm.setContentPane(this.guiresgraf); 
		    	this.frm.setVisible(true);
		    	this.frm.pack();
		 		RefineryUtilities.centerFrameOnScreen(this.frm);
		     
		    	 //Messages.informMsg("Definição dos Gráficos com os Resultados do Ajuste das Distribuições de Probabilidade efetuado com sucesso");
		     
		     } 
			
			
			
			
		}	

}
