package io.graph;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

import gui.PanelTrendDetectionStreamflowBrazil;
import io.ExtensionFileFilter;
import types.SimulationDataExtremos;

/*import org.snirh.extremos_unb.deteccao.gui.FrameGraficosAnual;
import org.snirh.extremos_unb.deteccao.gui.GraficoBoxPlotPanel;
import org.snirh.extremos_unb.deteccao.gui.PanelImportarDados;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.extremos_unb.util.ExtensionFileFilter;*/

public class PanelEscolherGrafico extends JFrame{

private static final long serialVersionUID = 1L;
	
	private JFileChooser chooser;
	
	private JPanel panelData;
	private JPanel panelButtons;
	
	private JButton btnExecute;
	private JButton btnCancel;
	
	private ButtonGroup cboxButtonGroup;
	private JRadioButton button1;
	private JRadioButton button2;
	private JRadioButton button3;
	
	
	private JCheckBox ckbox01;
	private JCheckBox ckbox02;
	
	private SimulationDataExtremos simulationData;
	private PanelTrendDetectionStreamflowBrazil pnd;
	private ExtensionFileFilter filter;
	
	
	public PanelEscolherGrafico(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnd){
		super("Choice of Graphics to be Drawn");
		this.simulationData = simulationData;
		this.pnd=pnd;
		this.createAndShowGUI();
		this.createPane();
		this.pack();
	}
	
	
	
	private void createPane() {
    	this.chooser = new JFileChooser(new File("."));
    	this.filter = new ExtensionFileFilter("dat", "List of gauge stations (*.dat)");
		this.chooser.setFileFilter(this.filter);
	}
	
	
	private void createAndShowGUI() {
		this.setBounds(20, 20, 440, 300);
		this.setPreferredSize(new Dimension(440, 300));
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		this.formatPanelData();
		this.formatPanelButtons();
	} 
	
	private void formatPanelData(){
		this.panelData = new JPanel();
    	this.panelData.setBorder(new EtchedBorder());
    	this.panelData.setBounds(10, 10, 290, 255);
    	this.panelData.setLayout(null);
		this.add(this.panelData);
		
		this.formatButtonGroup();
		//this.formatLabels();
	}

	private void formatButtonGroup() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 265, 235);
		JLabel label = new JLabel("Choose one of the options:");
		panel.add(label);	
		panel.setLayout(new GridLayout(0, 1));
		//this.cboxButtonGroup = new ButtonGroup();
		this.ckbox01=new JCheckBox( " Line Chart" ); ;
		this.ckbox02=new JCheckBox( " Box Plot" );
		//this.cboxButtonGroup.add(this.ckbox01);
		//this.cboxButtonGroup.add(this.ckbox02);
		panel.add(this.ckbox01);
		panel.add(this.ckbox02);
		this.panelData.add(panel);

	}


	private void formatPanelButtons() {
		this.panelButtons = new JPanel();
    	this.panelButtons.setBorder(new EtchedBorder());
    	this.panelButtons.setBounds(310, 10, 110, 255);
    	this.panelButtons.setLayout(null);
		this.add(this.panelButtons);
		    	
    	this.btnExecute = new JButton("Graph");
    	this.btnExecute.setToolTipText("Draw selected graphs of the annual flow series");
    	this.btnExecute.setBounds(10, 10, 90, 25);
    	this.btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnExecute);
			}
		});
    	this.panelButtons.add(this.btnExecute, JLayeredPane.DEFAULT_LAYER);
    	
    	/*this.btnCancel = new JButton("Adicionar");
    	this.btnCancel.setToolTipText("Adicionar ao estudo as séries do arquivo selecionado");
    	this.btnCancel.setBounds(10, 40, 90, 25);
    	this.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnCancel);
			}
		});
    	this.panelButtons.add(this.btnCancel, JLayeredPane.DEFAULT_LAYER);*/
    }
	
	private void buttonAction(JButton jb){
		
		if (jb.equals(this.btnExecute)){
		
		this.desenharGrafico();
				
		}else if (jb.equals(this.btnCancel)){
		
		//this.AdicionarDados();
		//this.setVisible(false);
		
		}
		
		this.setVisible(false);
	}
	
	
	
	
public void desenharGrafico()  {
		
	
	
	if(this.ckbox01.isSelected()){
		final FrameGraficosAnual frameresulgraf = new FrameGraficosAnual(this.simulationData,this.pnd);
		/*frameresulgraf.setVisible(true);
		frameresulgraf.pack();
		RefineryUtilities.centerFrameOnScreen(frameresulgraf);*/
	}
		
	if(this.ckbox02.isSelected()){
		final GraficoBoxPlotPanel demo = new GraficoBoxPlotPanel("Annual Series",this.simulationData,this.pnd);
	   
		
		
		/* demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);
	    demo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);*/
	}
	
	
	System.out.println("Finalizou com Sucesso!!!");
			
}
	
public void AbrirNovosDados()  {
	
	
	/*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	String tiposerie=this.simulationData.getTipoSerieHidroMaximos();
	ArrayList<VariavelHidrologica> variaveishidrologicasSheet=new ArrayList<VariavelHidrologica>();
	
	for (int i = 0; i < this.simulationData.getVariaveisHidr().size(); i++) {
					
		
		if(this.simulationData.getVariaveisHidr().get(i).isSelecionada())	{
			
			int tipoSerie=this.simulationData.getTipoSerieFalhaEstacionaridade();
			//PanelImportarDados pid=new PanelImportarDados();
			Map<String,DadoTemporal> serieMapa=new HashMap<String,DadoTemporal>();
			serieMapa=this.pnd.pegarSerieMaximos(this.simulationData.getVariaveisHidr().get(i), this.simulationData);
							
			ArrayList<Double> serieNew=new ArrayList<Double>();
			
			Set<String> chaves =  serieMapa.keySet();
		  	for (String data : chaves){
		  	DadoTemporal dado = serieMapa.get(data);
		  	serieNew.add(dado.getValor());
		   	}
						  	
		  	int tamanhoMinimoSerie=this.simulationData.getTamMinSerietotEstacionaridade();
		  	String tipoSeriestr=simulationData.getTipoSerieHidroMaximos();
				  	
				   if(serieNew.size() >= tamanhoMinimoSerie){
					   
					   
				   }
		}
		
		
		
	}*/
		
		
}



}
