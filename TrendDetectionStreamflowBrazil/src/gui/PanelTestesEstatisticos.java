package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

/*import org.snirh.extremos_unb.deteccao.gui.PanelImportarDados;
import org.snirh.extremos_unb.deteccao.gui.PanelTestesEstatisticos;
import org.snirh.extremos_unb.deteccao.interfacemdl.ExtremosUNB_Deteccao_Interface;
import org.snirh.extremos_unb.deteccao.plugin.ExtremosUNB_Deteccao_PlugIn;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.extremos_unb.util.ExtensionFileFilter;*/

import com.vividsolutions.jump.feature.FeatureCollection;
import com.vividsolutions.jump.workbench.model.Category;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;
import com.vividsolutions.jump.workbench.ui.AnimatedClockPanel;

import io.ExtensionFileFilter;
import types.SimulationDataExtremos;

public class PanelTestesEstatisticos extends JFrame{
//	private static final long serialVersionUID = 1L;
private static final long serialVersionUID = 1L;
	

	private JButton btnResultados;
	private JButton btnexecutarTestes;
	private JButton btnViewData;
	private JButton btnDesenharShape;
	private JButton btnDesenharShapeV2;
	private JButton btnBUD;
	private JButton btnExport;
	private JButton btnInserirBud;
	private JButton btnSalvarExcel;
	private JButton btnResumoResult;
	
	private JFileChooser chooser;
	public JFileChooser getChooser() {
		return chooser;
	}


	public ExtensionFileFilter getFilter() {
		return filter;
	}




	private ExtensionFileFilter filter;
	
	private JFileChooser chooser_dat;
	private ExtensionFileFilter filter_dat;
	
	private SimulationDataExtremos simulationData;

	private JLabel lblDowloadingPostCode;

	private DefaultTableModel tmodel;
	
	//private ExtremosUNB_Deteccao_PlugIn estacionaridadePlugIn;
//	private MaximasLumpedDailyPlugIn marLumpedDailyPlugIn;
	
	private AnimatedClockPanel clock;

	//public ExtremosUNB_Deteccao_PlugIn getEstacionaridadePlugIn() {
	//	return estacionaridadePlugIn;
	//}




	private JFileChooser exportchooser;

	private ExtensionFileFilter filterLumped;
	
	private FeatureCollection fcbase;
	protected PlugInContext context = null;
	protected Category category = null;
	
	
	private int dirxlabelsparam;
	private int dirylabelsparam;
	static private String []  nomedistrib =new String[12];
	static private String []  nomeTesteAderencia =new String[12];
	static private String []  nomeRegional =new String[2];
	
	static private String []  nomeTesteIndep =new String[12];
	static private String []  nomeTesteTenden =new String[12];
	static private String []  nomeTesteMedia =new String[12];
	static private String []  nomeTesteVariancia =new String[12];
	static private String []  nomeTesteOutlier=new String[12];
	static private String []  nomeTesteHomogeneidade=new String[12];
	static private String []  nomeTesteAnaReg=new String[12];
	
	static private String []  nomeTesteTendenAutoCorrelacao =new String[12];
	
	
	private JCheckBox[] checkIndependencia = new JCheckBox[50];
	private JCheckBox[] checkEstacionaridadeMedia = new JCheckBox[50];
	private JCheckBox[] checkEstacionaridadeVariancia = new JCheckBox[50];
	private JCheckBox[] checkEstacionaridadeTendencia = new JCheckBox[50];
	private JCheckBox[] checkOutliers = new JCheckBox[50];
	private JCheckBox[] checkAnaliseRegional = new JCheckBox[50];
	private JCheckBox[] checkTesteAderencia = new JCheckBox[10];
	private JCheckBox[] checkRegional = new JCheckBox[4];
	private JCheckBox[] checkEstacionaridadeTesteAderencia = new JCheckBox[50];
	
	private JCheckBox checktodos;
	private JPanel panelIndependencia;
	private JPanel panelTodos;
	private JPanel panelButtons;
	private JPanel panelEstacionaridadeMedia;
	private JPanel panelEstacionaridadeVariancia;
	private JPanel panelEstacionaridadeTendencia;
	private JPanel panelPosPlotagem;
	private JPanel panelOutliers;
	private JPanel panelAnaliseRegional;
	private JPanel panelEstacionaridadeTesteAderencia;
	private ArrayList<Double> m2Menorm1;
	private ArrayList<Double> m2Maiorm1;
	
	private ArrayList<Double> tendDec;
	private ArrayList<Double> tendCre;
	private Double [] estResM2menorM1;
	private Double [] estResM2maiorM1;
	
	
	
	
	private JPanel panelTipoHipotese;
	
	
	
	
	
	private ButtonGroup cboxButtonGroup;
	private JRadioButton button1;
	private JRadioButton button2;
	private JRadioButton button3;
	
	private JPanel panelNivelSignificanciaTeorico;
	private JLabel lblNivelSignificanciaTeorico;
	private JComboBox cboNivelSignificanciaTeorico;
	
	 private JPanel panelBootstrap;
	 private JTextField txtNSigBootstrap;
	 public JTextField getTxtNSigBootstrap() {
		return txtNSigBootstrap;
	}


	public JTextField getTxtNAmostrasBootstrap() {
		return txtNAmostrasBootstrap;
	}




	private JLabel lblNSigBootstrap;
	 private JCheckBox checkSelBootstrap;
	 private JTextField txtNAmostrasBootstrap;
	 private JLabel lblNAmostrasBootstrap;
	 
	 private JPanel panelAnodiv;
	 private JTextField txtAnodiv;
	 private JLabel lblAnodiv;
	 
	 public JProgressBar progressBar;
	 public JLabel lblAguardeThread;
	 
	 
	 private JTextArea textAreaResumo;
	 private final static String newline = "\n";
	 private JFrame frameResumo;
	 private JPanel panelResumo;
	 
	 
	    private JPanel panelPeriodosDisjuntos;
	    private JPanel panelAnoUsuario;
		private JTextField txtAnoIniSerie1;
		private JTextField txtAnoFimSerie1;
		private JTextField txtAnoIniSerie2;
		private JTextField txtAnoFimSerie2;
		private JTextField txtAnoSeparacao;
		
		private JPanel panelTamanhoMinimoPeriodos;
		private JTextField txtTamMinSerieTotal;
		private JTextField txtTamMinSerie1;
		private JTextField txtTamMinSerie2;
	 
		private ButtonGroup cboxButtonGroupAnoDiv;
		private JRadioButton buttonAnoDiv;
		private JRadioButton buttonDisjunto;
		
		
		public JCheckBox getCheckSelBootstrap() {
			return checkSelBootstrap;
		}


		public JCheckBox[] getCheckIndependencia() {
			return checkIndependencia;
		}


		public JCheckBox[] getCheckEstacionaridadeMedia() {
			return checkEstacionaridadeMedia;
		}


		public JCheckBox[] getCheckEstacionaridadeVariancia() {
			return checkEstacionaridadeVariancia;
		}


		public JCheckBox[] getCheckEstacionaridadeTendencia() {
			return checkEstacionaridadeTendencia;
		}


		public JRadioButton getButtonAnoDiv() {
			return buttonAnoDiv;
		}


		public JRadioButton getButtonDisjunto() {
			return buttonDisjunto;
		}


		public JCheckBox[] getCheckEstacionaridadeTendenciaAutoCorrelacao() {
			return checkEstacionaridadeTendenciaAutoCorrelacao;
		}


		public JRadioButton getButtonMetade() {
			return buttonMetade;
		}




		private JRadioButton buttonMetade;
	 
		
		
		private JCheckBox[] checkEstacionaridadeTendenciaAutoCorrelacao = new JCheckBox[50];
		private JPanel panelEstacionaridadeTendenciaAutoCorrelacao;
		
		
		private ButtonGroup cboxButtonGroupAutoCorrel;
		private JRadioButton button1AutoCorrel;
		private JRadioButton button2AutoCorrel;
		
		private JCheckBox checkSelAutoCorrel;
		
		
		private JButton btnSimulaExtremosUNB;
		private JButton btnSimulaExtremosUNB_Tipo2;
		
		//private PanelImportarDados panelImportarDados;
		//private ExtremosUNB_Deteccao_Interface mgeralinterface;
		
		private JPanel panelFDR;
		private ButtonGroup cboxButtonGroupFDR;
		private JRadioButton button1FDR;
		private JCheckBox checkSelFDR;
		
	   public void formatLabelsThread() {
			this.lblAguardeThread = new JLabel("");
			this.lblAguardeThread.setBounds(7, 700, 400, 20);
			this.add(this.lblAguardeThread);
			
			this.progressBar = new JProgressBar(0, 100);
			this.progressBar.setValue(0);
			this.progressBar.setStringPainted(true);
			this.add(this.progressBar);
			this.progressBar.setBounds(5, 715, 850, 20);
		}
	 
	 
	 
		/*public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			
			if ("progress" == evt.getPropertyName()) {
		        int progress = (Integer) evt.getNewValue();
		        progressBar.setValue(progress);
		        
		    } 
			
			
		}	*/
	 
	 
	public PanelTestesEstatisticos(SimulationDataExtremos simulationData){
		this.simulationData = simulationData;
		//this.estacionaridadePlugIn = estacionaridadePlugIn;
		this.setarnomescheckbox();
		this.createFileChooser();
		this.createPanel();
		
	}
	
	/*public PanelTestesEstatisticos(SimulationDataExtremos simulationData, ExtremosUNB_Deteccao_PlugIn estacionaridadePlugIn,PanelImportarDados panelImportarDados,ExtremosUNB_Deteccao_Interface mgeralinterface){
		this.simulationData = simulationData;
		this.estacionaridadePlugIn = estacionaridadePlugIn;
		this.panelImportarDados=panelImportarDados;
		this.mgeralinterface=mgeralinterface;
		this.setarnomescheckbox();
		this.createFileChooser();
		this.createPanel();
		
	}*/
	
	
	//public ExtremosUNB_Deteccao_Interface getMgeralinterface() {
	//	return mgeralinterface;
	//}


	private void createPanel() {
				
		
		//this.setBackground(Color.LIGHT_GRAY);
		this.setBounds(20, 20, 1000, 600);
		this.setPreferredSize(new Dimension(1000, 600));
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		this.formatPanelIndependencia();
		this.formatPanelEstacionaridadeTendencia();
		this.formatPanelEstacionaridadeMedia();
		this.formatPanelEstacionaridadeVariancia();
		this.formatpanelTipoHipotese();
		this.formatpanelNivelSignificancia();
		this.formatpanelNSigBootstrap();
		this.formatpanelAnodiv();
		//this.formatPanelEstacionaridadeTesteAderencia();
		this.formatpanelAnaliseRegional();
		//this.formatPanelOutliers();
		this.formatPanelButton();
		this.formatCheckTodos();
		
		this.formatPanelEstacionaridadeTendenciaAutoCorrelacao();
		this.formatPanelFDR();
		
		this.createFileChooser();
		this.formatLabelsThread();
		this.setLayout(new BorderLayout());
				
		
	}
	private void createFileChooser(){
		this.chooser = new JFileChooser(new File("."));
		//this.filter = new ExtensionFileFilter("flu", "Lista de postos fluviométricos (*.flu)");
		this.filter = new ExtensionFileFilter("xlsx", "Arquivo de saída dos resultados da análise de estacionaridade (*.xlsx)");
		this.chooser.setFileFilter(this.filter);
		
		
		this.chooser_dat = new JFileChooser(new File("."));
		//this.filter = new ExtensionFileFilter("flu", "Lista de postos fluviométricos (*.flu)");
		this.filter_dat = new ExtensionFileFilter("dat", "Arquivo de saída do resumo dos resultados  (*.dat)");
		this.chooser_dat.setFileFilter(this.filter);
		
		
	}
	
	public void formatpanelTipoHipotese (){
		
		this.panelTipoHipotese=new JPanel();
		this.panelTipoHipotese.setBackground(Color.LIGHT_GRAY);
		this.panelTipoHipotese.setBorder(BorderFactory.createTitledBorder("Hipóteses"));
    	this.panelTipoHipotese.setBounds(10, 230, 200, 100);
		this.panelTipoHipotese.setLayout(new GridLayout(0, 1));
    		
		this.cboxButtonGroup = new ButtonGroup();
		button1 = new JRadioButton("Bilateral");
		button1.setSelected(true);
		button2 = new JRadioButton("Unilateral a esquerda");
		button3 = new JRadioButton("Unilateral a direita");
		button1.setBackground(Color.LIGHT_GRAY);
		button2.setBackground(Color.LIGHT_GRAY);
		button3.setBackground(Color.LIGHT_GRAY);
		this.cboxButtonGroup.add(button1);
		this.cboxButtonGroup.add(button2);
		this.cboxButtonGroup.add(button3);
		this.panelTipoHipotese.add(button1);
		this.panelTipoHipotese.add(button2);
		this.panelTipoHipotese.add(button3);
		this.add(this.panelTipoHipotese);
		
		this.simulationData.setTipoHipoteseEstacionaridade(0);
		this.simulationData.setBootstrap(false);
		
		button1.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(button1.isSelected() == true){
				 simulationData.setTipoHipoteseEstacionaridade(0);
			 }
				  }
		});
		
		
		button2.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(button2.isSelected() == true){
				 simulationData.setTipoHipoteseEstacionaridade(2);
			 }
				  }
		});
		
		
		button3.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(button3.isSelected() == true){
				 simulationData.setTipoHipoteseEstacionaridade(1);
			 }
				  }
		});
		
		
		
		
		
	}
	
	
	
	
	
	
	public void formatpanelNivelSignificancia (){
		
		this.panelNivelSignificanciaTeorico=new JPanel();
		this.panelNivelSignificanciaTeorico.setBackground(Color.LIGHT_GRAY);
		this.panelNivelSignificanciaTeorico.setBorder(BorderFactory.createTitledBorder("Nível Significância Teórico"));
    	this.panelNivelSignificanciaTeorico.setBounds(225, 230, 200, 100);
		//this.panelNivelSignificanciaTeorico.setLayout(new GridLayout(0, 1));
		this.panelNivelSignificanciaTeorico.setLayout(null);
		this.add(this.panelNivelSignificanciaTeorico);
		
		String[] items = {"10 %", "5 %", "1 %"};
		this.cboNivelSignificanciaTeorico = new JComboBox(items);
    	this.cboNivelSignificanciaTeorico.setBounds(130, 40, 60, 20);
    	this.panelNivelSignificanciaTeorico.add(this.cboNivelSignificanciaTeorico);
    	
    	
    	this.lblNivelSignificanciaTeorico = new JLabel("Nivel de Significância:");
    	this.lblNivelSignificanciaTeorico.setBounds(10, 40, 130, 15);
    	this.panelNivelSignificanciaTeorico.add(this.lblNivelSignificanciaTeorico);
    	
    	
    	this.simulationData.setNivelSignificancia(5);
    	this.simulationData.setBootstrap(false);
    	
    	this.cboNivelSignificanciaTeorico.addFocusListener(new FocusListener(){   //need these to handle tab key; will also handle click in/out
            public void focusGained(FocusEvent e){}
            public void focusLost(FocusEvent e){
            	int ns = cboNivelSignificanciaTeorico.getSelectedIndex();
            	
            	if(ns == 0){
            		simulationData.setNivelSignificancia(10);	
            	}else if(ns == 2){
            		simulationData.setNivelSignificancia(1);
            	}else{
            		simulationData.setNivelSignificancia(5);
            	}
            	
            	//int mesIni=indice+1;
            	//simulationData.setMesIniEstacionaridade(mesIni);
            }
           
        });
		
		
	}
	
	public void formatpanelNSigBootstrap (){
		
		
		this.panelBootstrap=new JPanel();
		this.panelBootstrap.setBackground(Color.LIGHT_GRAY);
		this.panelBootstrap.setBorder(BorderFactory.createTitledBorder("Bootstrap"));
    	this.panelBootstrap.setBounds(440, 230, 200, 100);
		this.panelBootstrap.setLayout(null);
		this.add(this.panelBootstrap);
		
		this.txtNSigBootstrap = new JTextField();
		this.txtNSigBootstrap.setBounds(120, 40, 50, 25);
		this.txtNSigBootstrap.setEnabled(false);
		this.panelBootstrap.add(this.txtNSigBootstrap);
				
		this.lblNSigBootstrap = new JLabel("Nivel de Significância:");
    	this.lblNSigBootstrap.setBounds(10, 45, 130, 15);
    	this.panelBootstrap.add(this.lblNSigBootstrap);
    	    	
    	JLabel lblperc = new JLabel("%");
    	lblperc.setBounds(175, 45, 15, 15);
    	this.panelBootstrap.add(lblperc);
    	   
    	
    	this.txtNAmostrasBootstrap = new JTextField();
		this.txtNAmostrasBootstrap.setBounds(120, 70, 50, 25);
		this.txtNAmostrasBootstrap.setEnabled(false);
		this.panelBootstrap.add(this.txtNAmostrasBootstrap);
				
		this.lblNAmostrasBootstrap = new JLabel("Amostras:");
    	this.lblNAmostrasBootstrap.setBounds(10, 75, 130, 15);
    	this.panelBootstrap.add(this.lblNAmostrasBootstrap);
    	    	
    	
    	this.checkSelBootstrap =new JCheckBox("Selecionar");
		this.checkSelBootstrap.setHorizontalTextPosition(SwingConstants.LEFT);
		this.checkSelBootstrap.setVerticalTextPosition(SwingConstants.CENTER);
		this.checkSelBootstrap.setSelected(false);
		this.checkSelBootstrap.setBackground(Color.LIGHT_GRAY);
		this.checkSelBootstrap.setBounds(110, 10, 80, 25);
		this.panelBootstrap.add(this.checkSelBootstrap);
		
		
		this.checkSelBootstrap.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			if(checkSelBootstrap.isSelected() == true){
				cboNivelSignificanciaTeorico.setEnabled(false); 
				txtNSigBootstrap.setEnabled(true);
				txtNSigBootstrap.setText("5");
				txtNAmostrasBootstrap.setEnabled(true); 
				txtNAmostrasBootstrap.setText("1000");
				simulationData.setBootstrap(true);
								
			 }else{
				 cboNivelSignificanciaTeorico.setEnabled(true); 
				 txtNSigBootstrap.setEnabled(false); 
				 txtNSigBootstrap.setText("");
				 txtNAmostrasBootstrap.setEnabled(false); 
				 txtNAmostrasBootstrap.setText("");
				 simulationData.setBootstrap(false);
			 }
			}
		});
		
		
	}
	
	
public void formatpanelAnodiv (){
		
		
		this.panelAnodiv=new JPanel();
		this.panelAnodiv.setBackground(Color.LIGHT_GRAY);
		this.panelAnodiv.setBorder(BorderFactory.createTitledBorder("Ano Divisão- Rank Sum,teste t e F"));
    	this.panelAnodiv.setBounds(655, 230, 200, 300);
		this.panelAnodiv.setLayout(null);
		this.add(this.panelAnodiv);
		
		
		//private ButtonGroup cboxButtonGroupAnoDiv;
		//private JRadioButton buttonAnoDiv;
		//private JRadioButton buttonDisjunto;
		
		
		this.cboxButtonGroupAnoDiv = new ButtonGroup();
		this.buttonMetade=new JRadioButton();
		JLabel lbl9 = new JLabel("Dividir Series em Periodos Iguais");
		lbl9.setBounds(35, 25, 140, 25);
		this.panelAnodiv.add(lbl9);
		buttonMetade.setSelected(true);
		buttonAnoDiv = new JRadioButton();
		buttonAnoDiv.setSelected(false);
		buttonDisjunto = new JRadioButton();
		buttonAnoDiv.setBackground(Color.LIGHT_GRAY);
		buttonDisjunto.setBackground(Color.LIGHT_GRAY);
		buttonMetade.setBackground(Color.LIGHT_GRAY);
		
		this.cboxButtonGroupAnoDiv.add(buttonAnoDiv);
		this.cboxButtonGroupAnoDiv.add(buttonDisjunto);
		this.cboxButtonGroupAnoDiv.add(buttonMetade);
		//this.cboxButtonGroupAnoDiv.
		buttonAnoDiv.setLayout(null);
		buttonAnoDiv.setBounds(5, 90, 15, 15);
		buttonDisjunto.setLayout(null);
		buttonDisjunto.setBounds(5, 190, 15, 15);
		buttonMetade.setLayout(null);
		buttonMetade.setBounds(5, 30, 15, 15);
		
		this.panelAnodiv.add(buttonAnoDiv);
		this.panelAnodiv.add(buttonDisjunto);
		this.panelAnodiv.add(buttonMetade);
		
		
		this.panelAnoUsuario=new JPanel();
		this.panelAnoUsuario.setBackground(Color.LIGHT_GRAY);
		this.panelAnoUsuario.setBorder(BorderFactory.createTitledBorder("Ano de Corte"));
    	this.panelAnoUsuario.setBounds(25, 60, 170, 60);
		this.panelAnoUsuario.setLayout(null);
		//this.panelAnoUsuario.set
		this.panelAnodiv.add(this.panelAnoUsuario);
		
		JLabel lbl1 = new JLabel("Ano  :");
		lbl1.setBounds(15, 20, 70, 25);
		this.panelAnoUsuario.add(lbl1);
		this.txtAnoSeparacao = new JTextField();
		this.txtAnoSeparacao.setBounds(60, 20, 60, 25);
		this.txtAnoSeparacao.setEnabled(false);
		this.panelAnoUsuario.add(this.txtAnoSeparacao);
		
				
		
		
		this.panelPeriodosDisjuntos=new JPanel();
		this.panelPeriodosDisjuntos.setBackground(Color.LIGHT_GRAY);
		this.panelPeriodosDisjuntos.setBorder(BorderFactory.createTitledBorder("Disjuntos"));
    	this.panelPeriodosDisjuntos.setBounds(25, 130, 170, 120);
		this.panelPeriodosDisjuntos.setLayout(null);
		this.panelAnodiv.add(this.panelPeriodosDisjuntos);
		
		
		
		
		
		int x1=25;
		int x2=30;
		int x3=15;
		JLabel lbl2 = new JLabel("S1:");
		lbl2.setBounds(30-x3, 70-x2, 70, 25);
		JLabel lbl5 = new JLabel("Ano Inicial");
		lbl5.setBounds(65-x1, 50-x2, 70, 25);
		JLabel lbl6 = new JLabel("Ano Final");
		lbl6.setBounds(140-x1, 50-x2, 70, 25);
		
		this.panelPeriodosDisjuntos.add(lbl2);
		this.panelPeriodosDisjuntos.add(lbl5);
		this.panelPeriodosDisjuntos.add(lbl6);
		this.txtAnoIniSerie1 = new JTextField();
		this.txtAnoIniSerie1.setBounds(60-x1,70-x2, 60, 25);
		this.txtAnoIniSerie1.setEnabled(false);
		
		this.txtAnoFimSerie1 = new JTextField();
		this.txtAnoFimSerie1.setBounds(130-x1, 70-x2, 60, 25);
		this.txtAnoFimSerie1.setEnabled(false);
		this.panelPeriodosDisjuntos.add(this.txtAnoFimSerie1);
		this.panelPeriodosDisjuntos.add(this.txtAnoIniSerie1);
		
		
		JLabel lbl3 = new JLabel("S2:");
		lbl3.setBounds(30-x3,115-x2, 70, 25);
		JLabel lbl7 = new JLabel("Ano Inicial");
		lbl7.setBounds(65-x1, 95-x2, 70, 25);
		JLabel lbl8 = new JLabel("Ano Final");
		lbl8.setBounds(140-x1, 95-x2, 70, 25);
		this.panelPeriodosDisjuntos.add(lbl3);
		this.panelPeriodosDisjuntos.add(lbl7);
		this.panelPeriodosDisjuntos.add(lbl8);	
		this.txtAnoIniSerie2 = new JTextField();
		this.txtAnoIniSerie2.setBounds(60-x1, 115-x2, 60, 25);
		this.txtAnoIniSerie2.setEnabled(false);
		this.panelPeriodosDisjuntos.add(this.txtAnoIniSerie2);
		this.txtAnoFimSerie2 = new JTextField();
		this.txtAnoFimSerie2.setBounds(130-x1, 115-x2, 60, 25);
		this.txtAnoFimSerie2.setEnabled(false);
		this.panelPeriodosDisjuntos.add(this.txtAnoFimSerie2);
		
		this.simulationData.setAnoDeSeparacaoPeriodo(-99999);
		this.simulationData.setAnoIniSerie1Estacionaridade(-99999);
		this.simulationData.setAnoFimSerie1Estacionaridade(-99999);
		this.simulationData.setAnoIniSerie2Estacionaridade(-99999);
		this.simulationData.setAnoFimSerie2Estacionaridade(-99999);
		
		
		
		
		
		buttonMetade.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(buttonMetade.isSelected() == true){
				 txtAnoSeparacao.setEnabled(false);
				 txtAnoIniSerie1.setEnabled(false);
				 txtAnoIniSerie2.setEnabled(false);
				 txtAnoFimSerie1.setEnabled(false);
				 txtAnoFimSerie2.setEnabled(false);
			 }else{
				 
			 }
				  }
		});
		
		
		
		buttonAnoDiv.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(buttonAnoDiv.isSelected() == true && (checkEstacionaridadeMedia[0].isSelected() || checkEstacionaridadeMedia[4].isSelected() || checkEstacionaridadeVariancia[0].isSelected())){
				 txtAnoSeparacao.setEnabled(true);
			 }else{
				 txtAnoSeparacao.setEnabled(false);
			 }
				  }
		});
		
		
		buttonDisjunto.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(buttonDisjunto.isSelected() == true && (checkEstacionaridadeMedia[0].isSelected() || checkEstacionaridadeMedia[4].isSelected() || checkEstacionaridadeVariancia[0].isSelected())){
				 txtAnoIniSerie1.setEnabled(true);
				 txtAnoIniSerie2.setEnabled(true);
				 txtAnoFimSerie1.setEnabled(true);
				 txtAnoFimSerie2.setEnabled(true);
				 
			 }else{
				 txtAnoIniSerie1.setEnabled(false);
				 txtAnoIniSerie2.setEnabled(false);
				 txtAnoFimSerie1.setEnabled(false);
				 txtAnoFimSerie2.setEnabled(false);
			 }
			 
			 
			 
				  }
		});
		
		
		this.txtAnoSeparacao.addFocusListener(new FocusListener(){   //need these to handle tab key; will also handle click in/out
            public void focusGained(FocusEvent e){}
            public void focusLost(FocusEvent e){
            	
            	if(txtAnoSeparacao.getText() == null || txtAnoSeparacao.getText().equals("")){
            		simulationData.setAnoDeSeparacaoPeriodo(-99999);
            		simulationData.setPeriodoTotalSerie(false);
            	}else{
            		Integer anoSepara = Integer.parseInt(txtAnoSeparacao.getText());
            		simulationData.setAnoDeSeparacaoPeriodo(anoSepara);	
            		simulationData.setPeriodoTotalSerie(true);
            		
            	}
            	
            }
           
        });
		
		
		this.txtAnoIniSerie1.addFocusListener(new FocusListener(){   //need these to handle tab key; will also handle click in/out
            public void focusGained(FocusEvent e){}
            public void focusLost(FocusEvent e){
            	
            	
            	if(txtAnoIniSerie1.getText() == null || txtAnoIniSerie1.getText().equals("")){
            		
                	simulationData.setAnoIniSerie1Estacionaridade(-99999);
                	simulationData.setPeriodoTotalSerie(false);
            	}else{
            		int anoInis1 = Integer.parseInt(txtAnoIniSerie1.getText());
                	simulationData.setAnoIniSerie1Estacionaridade(anoInis1);
                	simulationData.setPeriodoTotalSerie(false);
            	}
            	
            }
           
        });
		
		this.txtAnoFimSerie1.addFocusListener(new FocusListener(){   //need these to handle tab key; will also handle click in/out
            public void focusGained(FocusEvent e){}
            public void focusLost(FocusEvent e){
            	if(txtAnoFimSerie1.getText() == null || txtAnoFimSerie1.getText().equals("")){
            		
                	simulationData.setAnoFimSerie1Estacionaridade(-99999);
                	simulationData.setPeriodoTotalSerie(false);
            	}else{
            		int anoFims1 = Integer.parseInt(txtAnoFimSerie1.getText());
                	simulationData.setAnoFimSerie1Estacionaridade(anoFims1);
                	simulationData.setPeriodoTotalSerie(false);
            	}
            	
            	
            }
           
        });
		
		this.txtAnoIniSerie2.addFocusListener(new FocusListener(){   //need these to handle tab key; will also handle click in/out
            public void focusGained(FocusEvent e){}
            public void focusLost(FocusEvent e){
            	
            	if(txtAnoIniSerie2.getText() == null || txtAnoIniSerie2.getText().equals("")){
            		
                	simulationData.setAnoIniSerie2Estacionaridade(-99999);
                	simulationData.setPeriodoTotalSerie(false);
            	}else{
            		
            		int anoInis2 = Integer.parseInt(txtAnoIniSerie2.getText());
                	simulationData.setAnoIniSerie2Estacionaridade(anoInis2);
                	simulationData.setPeriodoTotalSerie(false);
            	}
            	
            }
           
        });
		
		this.txtAnoFimSerie2.addFocusListener(new FocusListener(){   //need these to handle tab key; will also handle click in/out
            public void focusGained(FocusEvent e){}
            public void focusLost(FocusEvent e){
            	
            	if(txtAnoFimSerie2.getText() == null || txtAnoFimSerie2.getText().equals("")){
            		
                	simulationData.setAnoFimSerie2Estacionaridade(-99999);
                	simulationData.setPeriodoTotalSerie(false);
            	}else{
            		int anoFims2 = Integer.parseInt(txtAnoFimSerie2.getText());
                	simulationData.setAnoFimSerie2Estacionaridade(anoFims2);
                	simulationData.setPeriodoTotalSerie(false);
            	}
            	
            }
           
        });
		
		
		
		
		
		
		/*this.txtAnodiv = new JTextField();
		this.txtAnodiv.setBounds(120, 40, 50, 25);
		this.txtAnodiv.setEnabled(false);
		this.panelAnodiv.add(this.txtAnodiv);
				
		this.lblAnodiv = new JLabel("Ano Divisão:");
    	this.lblAnodiv.setBounds(10, 45, 130, 15);
    	this.panelAnodiv.add(this.lblAnodiv);
    	this.simulationData.setAnoDeSeparacaoPeriodo(-99999);
		this.simulationData.setPeriodoTotalSerie(false);
    	
    	this.txtAnodiv.addFocusListener(new FocusListener(){   //need these to handle tab key; will also handle click in/out
            public void focusGained(FocusEvent e){}
            public void focusLost(FocusEvent e){
            	
            	if(txtAnodiv.getText() == null || txtAnodiv.getText().equals("")){
            		simulationData.setAnoDeSeparacaoPeriodo(-99999);
            		simulationData.setPeriodoTotalSerie(false);
            	}else{
            		Integer anoSepara = Integer.parseInt(txtAnodiv.getText());
            		simulationData.setAnoDeSeparacaoPeriodo(anoSepara);	
            		simulationData.setPeriodoTotalSerie(true);
            		
            	}
            	
            }
           
        });*/
    	
    	/*JLabel lblperc = new JLabel("%");
    	lblperc.setBounds(175, 45, 15, 15);
    	this.panelBootstrap.add(lblperc);*/
    	    	
    	
    	
		
		
	}
	
	
	public static String [] getNomedistrib() {
		return nomedistrib;
	}

	public static void setNomedistrib(String [] nomedistrib) {
		PanelTestesEstatisticos.nomedistrib = nomedistrib;
	}

	
	
	
	private void setarnomescheckbox() {
		
		PanelTestesEstatisticos.nomeTesteIndep[0]="Median Crossing";
		PanelTestesEstatisticos.nomeTesteIndep[1]="Turning Points";
		PanelTestesEstatisticos.nomeTesteIndep[2]="Rank Difference";
		PanelTestesEstatisticos.nomeTesteIndep[3]="Autocorrelation";
		PanelTestesEstatisticos.nomeTesteIndep[4]="Wald-Wolfowitz";
		
		PanelTestesEstatisticos.nomeTesteTenden[0]="Mann-Kendall";
		PanelTestesEstatisticos.nomeTesteTenden[1]="Spearman’s Rho";
		PanelTestesEstatisticos.nomeTesteTenden[2]="Linear Regression";
		PanelTestesEstatisticos.nomeTesteTenden[3]="Autocorrelation";
	
				
		PanelTestesEstatisticos.nomeTesteHomogeneidade[0]="Mann-Whitney";
		
		PanelTestesEstatisticos.nomeTesteMedia[0]="Teste T";
		PanelTestesEstatisticos.nomeTesteMedia[1]="Distribution CUSUM";
		PanelTestesEstatisticos.nomeTesteMedia[2]="Cumul. Deviation";
		PanelTestesEstatisticos.nomeTesteMedia[3]="Worsley Lik. Ratio";
		PanelTestesEstatisticos.nomeTesteMedia[4]="Rank-Sum";
		PanelTestesEstatisticos.nomeTesteMedia[5]="Mann-Whitney";
		
		PanelTestesEstatisticos.nomeTesteVariancia[0]="Teste F";
						
		PanelTestesEstatisticos.nomeTesteAnaReg[0]="Medida de discordância";
		PanelTestesEstatisticos.nomeTesteAnaReg[1]="Heterogeneidade - Identificação de Regiões Homogêneas";
				
		PanelTestesEstatisticos.nomeTesteOutlier[0]="Chauvenet’s method";
		PanelTestesEstatisticos.nomeTesteOutlier[1]="Dixon–Thompson";
		PanelTestesEstatisticos.nomeTesteOutlier[2]="Rosner";
		PanelTestesEstatisticos.nomeTesteOutlier[3]="Grubbs and Beck";
		
		PanelTestesEstatisticos.nomeTesteAderencia[0]="Teste Qui-Quadrado";
		PanelTestesEstatisticos.nomeTesteAderencia[1]="Kolmogorov-Smirnov";
		PanelTestesEstatisticos.nomeTesteAderencia[2]="Zdist(Hosking,1991)";
		
		
		PanelTestesEstatisticos.nomeTesteTendenAutoCorrelacao[0]="Mann-Kendall - PW";
		PanelTestesEstatisticos.nomeTesteTendenAutoCorrelacao[1]="Mann-Kendall - TFPW";
		
	}

	

	private void formatCheckTodos() {
		
		/*this.panelTodos = new JPanel();
		this.panelTodos.setBackground(Color.LIGHT_GRAY);
    	this.panelTodos.setBorder(BorderFactory.createTitledBorder("Selecionar todos os testes"));
    	this.panelTodos.setBounds(10, 400, 215, 60);
    	this.add(this.panelTodos);*/
    	
		this.checktodos =new JCheckBox("Selecionar todos os testes");
		this.checktodos.setHorizontalTextPosition(SwingConstants.RIGHT);
		this.checktodos.setVerticalTextPosition(SwingConstants.CENTER);
		this.checktodos.setSelected(false);
		this.checktodos.setBackground(Color.LIGHT_GRAY);
		this.add(this.checktodos);
		
		this.checktodos.setBounds(10, 205, 200, 25);
		//this.panelTodos.add(this.checktodos);
		
		this.checktodos.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			if(checktodos.isSelected() == true){
				 
					int ntestindep=5;
					for (int i=0;i<ntestindep;i++){
					checkIndependencia[i].setSelected(true);	
					}
					for (int i=0;i<ntestindep;i++){
					checkEstacionaridadeMedia[i].setSelected(true);	
					}		
					ntestindep=3;
					for (int i=0;i<ntestindep;i++){
					checkEstacionaridadeTendencia[i].setSelected(true);
					}
					ntestindep=1;
					for (int i=0;i<ntestindep;i++){
						checkEstacionaridadeVariancia[i].setSelected(true);
					}
			
			 }else{
				    int ntestindep=5;
					for (int i=0;i<ntestindep;i++){
					checkIndependencia[i].setSelected(false);	
					}
					for (int i=0;i<ntestindep;i++){
					checkEstacionaridadeMedia[i].setSelected(false);	
					}		
					ntestindep=3;
					for (int i=0;i<ntestindep;i++){
					checkEstacionaridadeTendencia[i].setSelected(false);
					}
					ntestindep=1;
					for (int i=0;i<ntestindep;i++){
						checkEstacionaridadeVariancia[i].setSelected(false);
					}
			 }
			}
		});
		
		
		
		
	}

	private void formatPanelEstacionaridadeTendencia() {
		this.panelEstacionaridadeTendencia = new JPanel();
		this.panelEstacionaridadeTendencia.setBackground(Color.LIGHT_GRAY);
    	this.panelEstacionaridadeTendencia.setBorder(BorderFactory.createTitledBorder("Teste de Estacionaridade - Tendencia"));
    	//this.panelEstacionaridadeTendencia.setBounds(230, 210, 215, 190);
    	this.panelEstacionaridadeTendencia.setBounds(10, 10, 200, 190);
    	this.panelEstacionaridadeTendencia.setLayout(null);
    	this.add(this.panelEstacionaridadeTendencia);
    	this.formatCheckEstacionaridadeTendencia();
    	//this.panelEstacionaridadeTendencia.setEnabled(false);
		
	}
	
	private void formatPanelEstacionaridadeMedia() {
		this.panelEstacionaridadeMedia = new JPanel();
		this.panelEstacionaridadeMedia.setBackground(Color.LIGHT_GRAY);
    	this.panelEstacionaridadeMedia.setBorder(BorderFactory.createTitledBorder("Teste de Estacionaridade - Média"));
    	this.panelEstacionaridadeMedia.setBounds(225, 10, 200, 190);
    	this.panelEstacionaridadeMedia.setLayout(null);
    	this.add(this.panelEstacionaridadeMedia);
    	this.formatCheckEstacionaridadeMedia();
    	//this.panelEstacionaridadeMedia.setEnabled(false);
		
	}
	
	private void formatPanelEstacionaridadeVariancia() {
		this.panelEstacionaridadeVariancia = new JPanel();
		this.panelEstacionaridadeVariancia.setBackground(Color.LIGHT_GRAY);
    	this.panelEstacionaridadeVariancia.setBorder(BorderFactory.createTitledBorder("Teste de Estacionaridade - Variancia"));
    	this.panelEstacionaridadeVariancia.setBounds(440, 10, 200, 190);
    	this.panelEstacionaridadeVariancia.setLayout(null);
    	this.add(this.panelEstacionaridadeVariancia);
    	this.formatCheckEstacionaridadeVariancia();
    	//this.panelEstacionaridadeVariancia.setEnabled(false);
    	//this.panelEstacionaridadeVariancia.setFocusable(false);
	}
	
	private void formatPanelIndependencia() {
		this.panelIndependencia = new JPanel();
		this.panelIndependencia.setBackground(Color.LIGHT_GRAY);
    	this.panelIndependencia.setBorder(BorderFactory.createTitledBorder("Testes de Indepedencia"));
    	this.panelIndependencia.setBounds(655, 10, 200, 190);
    	this.panelIndependencia.setLayout(null);
    	this.add(this.panelIndependencia);
    	this.formatCheckIndependencia();
		
	}

	private void formatCheckIndependencia() {
		int ntestindep=5;
		for (int i=0;i<ntestindep;i++){
			this.checkIndependencia[i] = new JCheckBox(PanelTestesEstatisticos.nomeTesteIndep[i]+" (B)");
			this.checkIndependencia[i].setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkIndependencia[i].setVerticalTextPosition(SwingConstants.CENTER);
			this.checkIndependencia[i].setSelected(false);
			this.checkIndependencia[i].setBackground(Color.LIGHT_GRAY);
			this.panelIndependencia.add(this.checkIndependencia[i]);
			this.checkIndependencia[i].setEnabled(true);
		}
		
		this.checkIndependencia[0].setBounds(10, 20, 140, 25);
		this.checkIndependencia[1].setBounds(18, 50, 140, 25);
		this.checkIndependencia[2].setBounds(8, 80, 140, 25);
		this.checkIndependencia[3].setBounds(10, 110, 140, 25);
		this.checkIndependencia[4].setBounds(10, 140, 140, 25);
		
		
		
		
	}

	
		
	private void formatCheckEstacionaridadeMedia() {
		int ntestindep=5;
		for (int i=0;i<ntestindep;i++){
			
			if(i == 0 || i == 4){
				this.checkEstacionaridadeMedia[i] = new JCheckBox(PanelTestesEstatisticos.nomeTesteMedia[i]+" (B-U)");	
			}else{
				this.checkEstacionaridadeMedia[i] = new JCheckBox(PanelTestesEstatisticos.nomeTesteMedia[i]+" (B)");
			}
			
			this.checkEstacionaridadeMedia[i].setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkEstacionaridadeMedia[i].setVerticalTextPosition(SwingConstants.CENTER);
			this.checkEstacionaridadeMedia[i].setSelected(false);
			this.checkEstacionaridadeMedia[i].setBackground(Color.LIGHT_GRAY);
			this.panelEstacionaridadeMedia.add(this.checkEstacionaridadeMedia[i]);
			this.checkEstacionaridadeMedia[i].setEnabled(true);
		}
		
		this.checkEstacionaridadeMedia[0].setBounds(10, 20, 140, 25);
		this.checkEstacionaridadeMedia[1].setBounds(10, 45, 140, 25);
		this.checkEstacionaridadeMedia[2].setBounds(10, 70, 140, 25);
		this.checkEstacionaridadeMedia[3].setBounds(10, 95, 140, 25);
		this.checkEstacionaridadeMedia[4].setBounds(10, 120, 160, 25);
		
		
		
		
		   this.checkEstacionaridadeMedia[0].addItemListener(new ItemListener(){  
					public void itemStateChanged(ItemEvent evt){  
					if(checkEstacionaridadeMedia[0].isSelected() == true){
						
						
						/*if(buttonAnoDiv.isSelected()){
							txtAnoSeparacao.setEnabled(true);
						}else if(buttonDisjunto.isSelected()){
							txtAnoSeparacao.setEnabled(false);	
						}
						
						if(buttonDisjunto.isSelected()){
							 txtAnoIniSerie1.setEnabled(true);
							 txtAnoIniSerie2.setEnabled(true);
							 txtAnoFimSerie1.setEnabled(true);
							 txtAnoFimSerie2.setEnabled(true);
						 }else{
							 txtAnoIniSerie1.setEnabled(false);
							 txtAnoIniSerie2.setEnabled(false);
							 txtAnoFimSerie1.setEnabled(false);
							 txtAnoFimSerie2.setEnabled(false);
						 }*/
										
					 }else if(checkEstacionaridadeMedia[4].isSelected() == true){
						 
						 /*if(buttonDisjunto.isSelected()){
							 txtAnoIniSerie1.setEnabled(true);
							 txtAnoIniSerie2.setEnabled(true);
							 txtAnoFimSerie1.setEnabled(true);
							 txtAnoFimSerie2.setEnabled(true);
						 }else{
							 txtAnoIniSerie1.setEnabled(false);
							 txtAnoIniSerie2.setEnabled(false);
							 txtAnoFimSerie1.setEnabled(false);
							 txtAnoFimSerie2.setEnabled(false);
						 }*/
						  
							 
					 }else{
						 buttonMetade.setSelected(true);
						 repaint();
					 }
					
					
					}
				});
		
		
		   this.checkEstacionaridadeMedia[4].addItemListener(new ItemListener(){  
				public void itemStateChanged(ItemEvent evt){  
				if(checkEstacionaridadeMedia[4].isSelected() == true){
					//txtAnodiv.setEnabled(true); 
									
				 }else if(checkEstacionaridadeMedia[0].isSelected() == true){
					// txtAnodiv.setEnabled(true); 
				}else{
					buttonMetade.setSelected(true); 
					repaint();
				 }
				}
			});
		   
		   
		//this.checkEstacionaridadeMedia[5].setBounds(10, 145,140, 25);
	
			
	
	}
	
	
	

	private void formatCheckEstacionaridadeTendencia() {
		int ntestindep=3;
		for (int i=0;i<ntestindep;i++){
			this.checkEstacionaridadeTendencia[i] = new JCheckBox(PanelTestesEstatisticos.nomeTesteTenden[i]+" (B-U)");
			this.checkEstacionaridadeTendencia[i].setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkEstacionaridadeTendencia[i].setVerticalTextPosition(SwingConstants.CENTER);
			this.checkEstacionaridadeTendencia[i].setSelected(false);
			this.checkEstacionaridadeTendencia[i].setBackground(Color.LIGHT_GRAY);
			this.panelEstacionaridadeTendencia.add(this.checkEstacionaridadeTendencia[i]);
			this.checkEstacionaridadeTendencia[i].setEnabled(true);
		}
		
		this.checkEstacionaridadeTendencia[0].setBounds(10, 20, 140, 25);
		this.checkEstacionaridadeTendencia[1].setBounds(10, 50, 140, 25);
		this.checkEstacionaridadeTendencia[2].setBounds(10, 80, 140, 25);
		//this.checkEstacionaridadeTendencia[3].setBounds(10, 110, 140, 25);
		
	
	}
	
	private void formatPanelFDR() {
		
		
		
		this.panelFDR = new JPanel();
		this.panelFDR.setBackground(Color.LIGHT_GRAY);
    	this.panelFDR.setBorder(BorderFactory.createTitledBorder("FDR - False Discovery Rate"));
    	this.panelFDR.setBounds(225, 340, 200, 100);
    	this.panelFDR.setLayout(null);
    	//this.panelEstacionaridadeTendenciaAutoCorrelacao.setLayout(new GridLayout(0, 1));
    	this.add(this.panelFDR);
    	
    	
    	JPanel panel = new JPanel();
		panel.setBounds(10, 40, 150, 50);
		panel.setLayout(new GridLayout(0, 1));
		
		this.cboxButtonGroupFDR = new ButtonGroup();
		int ntestindep=2;
		this.button1FDR = new JRadioButton("FDR - CLASSICO");
		this.button1FDR.setSelected(true);
		//this.button2FDR = new JRadioButton("FDR");
		this.cboxButtonGroupFDR.add(this.button1FDR);
		//this.cboxButtonGroupAutoCorrel.add(this.button2FDR);
		panel.add(this.button1FDR);
		//panel.add(this.button2FDR);
		this.panelFDR.add(panel);
		//this.panelEstacionaridadeTendenciaAutoCorrelacao.add(panel);
		
		this.button1FDR.setBackground(Color.LIGHT_GRAY);
		//this.button2FDR.setBackground(Color.LIGHT_GRAY);
		
		this.button1FDR.setEnabled(false);
		//this.button2FDR.setEnabled(false);
		this.simulationData.setFazerFDR(false);
		this.simulationData.setFazerFDRClassico(true);
		this.button1FDR.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(button1FDR.isSelected() == true){
				 simulationData.setFazerFDRClassico(button1FDR.isSelected());
				 //simulationData.setFazerTFPW(button2AutoCorrel.isSelected());
				// simulationData.setTipoHipoteseEstacionaridade(0);
			 }
				  }
		});
		
		/*this.button2FDR.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(button2FDR.isSelected() == true){
				 simulationData.setFazerPW(button1AutoCorrel.isSelected());
				 simulationData.setFazerTFPW(button2FDR.isSelected());
			 }
				  }
		});*/
		
		this.checkSelFDR =new JCheckBox("Selecionar");
		this.checkSelFDR.setHorizontalTextPosition(SwingConstants.LEFT);
		this.checkSelFDR.setVerticalTextPosition(SwingConstants.CENTER);
		this.checkSelFDR.setSelected(false);
		this.checkSelFDR.setBackground(Color.LIGHT_GRAY);
		this.checkSelFDR.setBounds(110, 15, 80, 25);
		this.panelFDR.add(this.checkSelFDR);
		
		
		this.checkSelFDR.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			if(checkSelFDR.isSelected() == true){
				button1FDR.setEnabled(true);
				//button2FDR.setEnabled(true);
				simulationData.setFazerFDR(true);				
			 }else{
				 button1FDR.setEnabled(false);
				 //button2FDR.setEnabled(false);
				 simulationData.setFazerFDR(false);
			 }
			}
		});
    	
    	
    	
    	//this.formatCheckEstacionaridadeTendenciaAutoCorrelacao();
    	//this.panelEstacionaridadeTendencia.setEnabled(false);
    	
    	
    	
    	
		
	}
	
	
	private void formatPanelEstacionaridadeTendenciaAutoCorrelacao() {
		this.panelEstacionaridadeTendenciaAutoCorrelacao = new JPanel();
		this.panelEstacionaridadeTendenciaAutoCorrelacao.setBackground(Color.LIGHT_GRAY);
    	this.panelEstacionaridadeTendenciaAutoCorrelacao.setBorder(BorderFactory.createTitledBorder("Tendencia - Autocorrelação"));
    	//this.panelEstacionaridadeTendencia.setBounds(230, 210, 215, 190);
    	this.panelEstacionaridadeTendenciaAutoCorrelacao.setBounds(10, 340, 200, 190);
    	this.panelEstacionaridadeTendenciaAutoCorrelacao.setLayout(null);
    	//this.panelEstacionaridadeTendenciaAutoCorrelacao.setLayout(new GridLayout(0, 1));
    	this.add(this.panelEstacionaridadeTendenciaAutoCorrelacao);
    	
    	
    	this.formatCheckEstacionaridadeTendenciaAutoCorrelacao();
    	//this.panelEstacionaridadeTendencia.setEnabled(false);
    	
    	
    	
    	
		
	}
	
	
	
	
	private void formatCheckEstacionaridadeTendenciaAutoCorrelacao() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 40, 150, 130);
		panel.setLayout(new GridLayout(0, 1));
		
		this.cboxButtonGroupAutoCorrel = new ButtonGroup();
		int ntestindep=2;
		this.button1AutoCorrel = new JRadioButton("PW");
		this.button1AutoCorrel.setSelected(true);
		this.button2AutoCorrel = new JRadioButton("TFPW");
		this.cboxButtonGroupAutoCorrel.add(this.button1AutoCorrel);
		this.cboxButtonGroupAutoCorrel.add(this.button2AutoCorrel);
		panel.add(this.button1AutoCorrel);
		panel.add(this.button2AutoCorrel);
		this.panelEstacionaridadeTendenciaAutoCorrelacao.add(panel);
		this.panelEstacionaridadeTendenciaAutoCorrelacao.add(panel);
		
		this.button1AutoCorrel.setBackground(Color.LIGHT_GRAY);
		this.button2AutoCorrel.setBackground(Color.LIGHT_GRAY);
		
		this.button1AutoCorrel.setEnabled(false);
		this.button2AutoCorrel.setEnabled(false);
		this.simulationData.setConsiderarAutoCorrelacao(false);
		this.simulationData.setFazerPW(true);
		
		
		this.button1AutoCorrel.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(button1AutoCorrel.isSelected() == true){
				 simulationData.setFazerPW(button1AutoCorrel.isSelected());
				 simulationData.setFazerTFPW(button2AutoCorrel.isSelected());
				// simulationData.setTipoHipoteseEstacionaridade(0);
			 }
				  }
		});
		
		
		
		this.button2AutoCorrel.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			 if(button2AutoCorrel.isSelected() == true){
				 simulationData.setFazerPW(button1AutoCorrel.isSelected());
				 simulationData.setFazerTFPW(button2AutoCorrel.isSelected());
			 }
				  }
		});
		
		
		
		this.checkSelAutoCorrel =new JCheckBox("Selecionar");
		this.checkSelAutoCorrel.setHorizontalTextPosition(SwingConstants.LEFT);
		this.checkSelAutoCorrel.setVerticalTextPosition(SwingConstants.CENTER);
		this.checkSelAutoCorrel.setSelected(false);
		this.checkSelAutoCorrel.setBackground(Color.LIGHT_GRAY);
		this.checkSelAutoCorrel.setBounds(110, 15, 80, 25);
		this.panelEstacionaridadeTendenciaAutoCorrelacao.add(this.checkSelAutoCorrel);
		
		
		this.checkSelAutoCorrel.addItemListener(new ItemListener(){  
			public void itemStateChanged(ItemEvent evt){  
			if(checkSelAutoCorrel.isSelected() == true){
				button1AutoCorrel.setEnabled(true);
				button2AutoCorrel.setEnabled(true);
				simulationData.setConsiderarAutoCorrelacao(true);				
			 }else{
				 button1AutoCorrel.setEnabled(false);
				 button2AutoCorrel.setEnabled(false);
				 simulationData.setConsiderarAutoCorrelacao(false);
			 }
			}
		});
		
		
		
		/*for (int i=0;i<ntestindep;i++){
			this.checkEstacionaridadeTendenciaAutoCorrelacao[i] = new JCheckBox(PanelTestesEstatisticos.nomeTesteTendenAutoCorrelacao[i]);
			this.checkEstacionaridadeTendenciaAutoCorrelacao[i].setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkEstacionaridadeTendenciaAutoCorrelacao[i].setVerticalTextPosition(SwingConstants.CENTER);
			this.checkEstacionaridadeTendenciaAutoCorrelacao[i].setSelected(false);
			this.checkEstacionaridadeTendenciaAutoCorrelacao[i].setBackground(Color.LIGHT_GRAY);
			this.panelEstacionaridadeTendenciaAutoCorrelacao.add(this.checkEstacionaridadeTendenciaAutoCorrelacao[i]);
			this.checkEstacionaridadeTendenciaAutoCorrelacao[i].setEnabled(true);
		}*/
		
		//this.checkEstacionaridadeTendenciaAutoCorrelacao[0].setBounds(10, 20, 140, 25);
		//this.checkEstacionaridadeTendenciaAutoCorrelacao[1].setBounds(10, 50, 140, 25);
		//this.checkEstacionaridadeTendencia[2].setBounds(10, 80, 140, 25);
		//this.checkEstacionaridadeTendencia[3].setBounds(10, 110, 140, 25);
		
		
		
		
	
	}
	
	
	
	
	
	
	
	
	
	private void formatPanelEstacionaridadeTesteAderencia() {
		this.panelEstacionaridadeTesteAderencia = new JPanel();
		this.panelEstacionaridadeTesteAderencia.setBackground(Color.LIGHT_GRAY);
    	this.panelEstacionaridadeTesteAderencia.setBorder(BorderFactory.createTitledBorder("Testes de Aderencia"));
    	this.panelEstacionaridadeTesteAderencia.setBounds(450, 10, 215, 190);
    	this.panelEstacionaridadeTesteAderencia.setLayout(null);
    	this.add(this.panelEstacionaridadeTesteAderencia);
    	this.formatCheckEstacionaridadeTesteAderencia();
    	//this.panelEstacionaridadeTesteAderencia.setEnabled(false);
	}
	
	
	private void formatCheckEstacionaridadeTesteAderencia() {
		int ntestindep=3;
		for (int i=0;i<ntestindep;i++){
			this.checkEstacionaridadeTesteAderencia[i] = new JCheckBox(PanelTestesEstatisticos.nomeTesteAderencia[i]);
			this.checkEstacionaridadeTesteAderencia[i].setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkEstacionaridadeTesteAderencia[i].setVerticalTextPosition(SwingConstants.CENTER);
			this.checkEstacionaridadeTesteAderencia[i].setSelected(false);
			this.checkEstacionaridadeTesteAderencia[i].setBackground(Color.LIGHT_GRAY);
			this.panelEstacionaridadeTesteAderencia.add(this.checkEstacionaridadeTesteAderencia[i]);
			this.checkEstacionaridadeTesteAderencia[i].setEnabled(false);
		}
		
		this.checkEstacionaridadeTesteAderencia[0].setBounds(10, 20, 140, 25);
		this.checkEstacionaridadeTesteAderencia[1].setBounds(10, 50, 140, 25);
		this.checkEstacionaridadeTesteAderencia[2].setBounds(10, 80, 140, 25);
			
	}
	
	private void formatCheckEstacionaridadeVariancia() {
		int ntestindep=1;
		for (int i=0;i<ntestindep;i++){
			this.checkEstacionaridadeVariancia[i] = new JCheckBox(PanelTestesEstatisticos.nomeTesteVariancia[i]+" (B-U)");
			this.checkEstacionaridadeVariancia[i].setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkEstacionaridadeVariancia[i].setVerticalTextPosition(SwingConstants.CENTER);
			this.checkEstacionaridadeVariancia[i].setSelected(false);
			this.checkEstacionaridadeVariancia[i].setBackground(Color.LIGHT_GRAY);
			this.panelEstacionaridadeVariancia.add(this.checkEstacionaridadeVariancia[i]);
			this.checkEstacionaridadeVariancia[i].setEnabled(true);
		}
		
		this.checkEstacionaridadeVariancia[0].setBounds(10, 20, 140, 25);
		
		
		
		
			
	}
	
	
	
	private void formatpanelAnaliseRegional() {
		this.panelAnaliseRegional = new JPanel();
		this.panelAnaliseRegional.setBackground(Color.LIGHT_GRAY);
    	this.panelAnaliseRegional.setBorder(BorderFactory.createTitledBorder("Testes para Análise Regional - Hosking e Wallis (1991)"));
    	this.panelAnaliseRegional.setBounds(480, 410, 450, 190);
    	this.panelAnaliseRegional.setLayout(null);
    	//this.add(this.panelAnaliseRegional);
    	//this.formatCheckAnaliseRegional();
	}
	
	private void formatCheckAnaliseRegional() {
		int ntestindep=2;
		for (int i=0;i<ntestindep;i++){
			this.checkAnaliseRegional[i] = new JCheckBox(PanelTestesEstatisticos.nomeTesteAnaReg[i]);
			this.checkAnaliseRegional[i].setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkAnaliseRegional[i].setVerticalTextPosition(SwingConstants.CENTER);
			this.checkAnaliseRegional[i].setSelected(false);
			this.checkAnaliseRegional[i].setBackground(Color.LIGHT_GRAY);
			this.panelAnaliseRegional.add(this.checkAnaliseRegional[i]);
			this.checkAnaliseRegional[i].setEnabled(false);
		}
		
		this.checkAnaliseRegional[0].setBounds(10, 20, 300, 25);
		this.checkAnaliseRegional[1].setBounds(10, 50, 350, 25);	
	}
	
	
	private void formatPanelOutliers() {
		this.panelOutliers = new JPanel();
		this.panelOutliers.setBackground(Color.LIGHT_GRAY);
    	this.panelOutliers.setBorder(BorderFactory.createTitledBorder("Testes de Outliers"));
    	//this.panelOutliers.setBounds(450, 210, 215, 190);
    	this.panelOutliers.setBounds(450, 10, 215, 190);
    	this.panelOutliers.setLayout(null);
    	this.add(this.panelOutliers);
    	this.formatCheckOutliers();
	}
	
	private void formatCheckOutliers() {
		int ntestindep=4;
		for (int i=0;i<ntestindep;i++){
			this.checkOutliers[i] = new JCheckBox(PanelTestesEstatisticos.nomeTesteOutlier[i]);
			this.checkOutliers[i].setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkOutliers[i].setVerticalTextPosition(SwingConstants.CENTER);
			this.checkOutliers[i].setSelected(false);
			this.checkOutliers[i].setBackground(Color.LIGHT_GRAY);
			this.panelOutliers.add(this.checkOutliers[i]);	
			this.checkOutliers[i].setEnabled(false);
		}
		
		this.checkOutliers[0].setBounds(10, 20, 140, 25);
		this.checkOutliers[1].setBounds(10, 50, 140, 25);
		this.checkOutliers[2].setBounds(10, 80, 140, 25);
		this.checkOutliers[3].setBounds(10, 110, 140, 25);
	}
	
	
	
	private void formatCheckRegional() {
		this.checkRegional[0] = new JCheckBox(PanelTestesEstatisticos.nomeRegional[0]);
		this.checkRegional[0].setHorizontalTextPosition(SwingConstants.LEFT);
		this.checkRegional[0].setVerticalTextPosition(SwingConstants.CENTER);
		this.checkRegional[0].setBounds(10, 40, 220, 25);	
		this.checkRegional[0].setSelected(false);
		this.checkRegional[0].setBackground(Color.LIGHT_GRAY);
		this.checkRegional[0].setEnabled(false);
		//this.panelAnaliseRegional.add(this.checkRegional[0]);
	}

	
	private void formatCheckTesteAderencia() {
		this.checkTesteAderencia[0] = new JCheckBox(PanelTestesEstatisticos.nomeTesteAderencia[0]);
		this.checkTesteAderencia[0].setHorizontalTextPosition(SwingConstants.LEFT);
		this.checkTesteAderencia[0].setVerticalTextPosition(SwingConstants.CENTER);
		this.checkTesteAderencia[0].setBounds(10, 15, 220, 25);	
		this.checkTesteAderencia[0].setSelected(false);
		this.checkTesteAderencia[0].setBackground(Color.LIGHT_GRAY);
		
		
		this.checkTesteAderencia[1] = new JCheckBox(PanelTestesEstatisticos.nomeTesteAderencia[1]);
		this.checkTesteAderencia[1].setHorizontalTextPosition(SwingConstants.LEFT);
		this.checkTesteAderencia[1].setVerticalTextPosition(SwingConstants.CENTER);
		this.checkTesteAderencia[1].setBounds(10, 38, 220, 25);	
		this.checkTesteAderencia[1].setSelected(false);
		this.checkTesteAderencia[1].setBackground(Color.LIGHT_GRAY);
		
		
		
		this.checkTesteAderencia[2] = new JCheckBox(PanelTestesEstatisticos.nomeTesteAderencia[2]);
		this.checkTesteAderencia[2].setHorizontalTextPosition(SwingConstants.LEFT);
		this.checkTesteAderencia[2].setVerticalTextPosition(SwingConstants.CENTER);
		this.checkTesteAderencia[2].setBounds(12, 60, 200, 25);	
		this.checkTesteAderencia[2].setSelected(false);
		this.checkTesteAderencia[2].setBackground(Color.LIGHT_GRAY);
	
		
	}

	
	private void formatPanelPosPlotagem() {
		this.panelPosPlotagem = new JPanel();
		this.panelPosPlotagem.setBackground(Color.LIGHT_GRAY);
    	this.panelPosPlotagem.setBorder(BorderFactory.createTitledBorder("Posição de Plotagem"));
    	this.panelPosPlotagem.setBounds(590, 220, 300, 200);
    	this.panelPosPlotagem.setLayout(null);
    	
    	
    	this.add(this.panelPosPlotagem);
    	//this.formatCheckBoxPplot();
    //	this.formatTextFieldPosPlotger();
		
	}

	
private void formatPanelButton() {
		
		this.panelButtons = new JPanel();
		this.panelButtons.setBackground(Color.LIGHT_GRAY);
    	this.panelButtons.setBorder(new EtchedBorder());
    	this.panelButtons.setBounds(870, 10, 110, 390);
    	this.panelButtons.setLayout(null);
		this.add(this.panelButtons);
		
    	this.btnexecutarTestes = new JButton("Executar");
    	this.btnexecutarTestes.setToolTipText("Abrir lista de postos fluviométricos");
    	this.btnexecutarTestes.setBounds(10, 10, 90, 25);
    	this.btnexecutarTestes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnexecutarTestes);
			}
		});
    	this.panelButtons.add(this.btnexecutarTestes, JLayeredPane.DEFAULT_LAYER);
    	
    	this.btnResultados = new JButton("Resultados");
    	this.btnResultados.setToolTipText("Executar testes estatísticos de estacionaridade");
    	this.btnResultados.setBounds(10, 40, 90, 25);
    	this.btnResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnResultados);
			}
		});
    	
    	this.panelButtons.add(this.btnResultados, JLayeredPane.DEFAULT_LAYER);
    	
    	
    	this.btnDesenharShape = new JButton("Desenhar");
    	this.btnDesenharShape.setToolTipText("Desenhar Shape da estações selecionadas os resultados dos testes de estacionaridade");
    	this.btnDesenharShape.setBounds(10, 70, 90, 25);
    	this.btnDesenharShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnDesenharShape);
			}
		});
    	this.panelButtons.add(this.btnDesenharShape, JLayeredPane.DEFAULT_LAYER);
    	
    	this.btnSalvarExcel = new JButton("Excel");
    	this.btnSalvarExcel.setToolTipText("Salvar todos os resultados em arquivo .xls do Excel da ferramenta");
    	this.btnSalvarExcel.setBounds(10, 100, 90, 25);
    	//this.btnSalvarExcel.setEnabled(false);
    	this.btnSalvarExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnSalvarExcel);
			}
		});
    	 
    	
    	this.panelButtons.add(this.btnSalvarExcel, JLayeredPane.DEFAULT_LAYER);
    	
    	
    	
    	
    	
    	
    	this.btnResumoResult = new JButton("Resumo");
    	this.btnResumoResult.setToolTipText("resumo dos resultados de alguns testes estatísticos");
    	this.btnResumoResult.setBounds(10, 130, 90, 25);
    	this.btnResumoResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnResumoResult);
			}
		});
    	this.panelButtons.add(this.btnResumoResult, JLayeredPane.DEFAULT_LAYER);
    	
    	
    	
    	this.btnDesenharShapeV2 = new JButton("Shp_Res");
    	this.btnDesenharShapeV2.setToolTipText("Desenhar Shape da estações selecionadas os resultados dos testes de estacionaridade subdivididos por tipo de teste");
    	this.btnDesenharShapeV2.setBounds(10, 160, 90, 25);
    	this.btnDesenharShapeV2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnDesenharShapeV2);
			}
		});
    	this.panelButtons.add(this.btnDesenharShapeV2, JLayeredPane.DEFAULT_LAYER);
    	   	
    	
    	this.btnSimulaExtremosUNB = new JButton("SIM_UNB");
    	this.btnSimulaExtremosUNB.setToolTipText("Diversas simulações voltadas exclusivamente ao estudo EXTREMOS UNB");
    	this.btnSimulaExtremosUNB.setBounds(10, 190, 90, 25);
    	this.btnSimulaExtremosUNB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnSimulaExtremosUNB);
			}
		});
    	this.panelButtons.add(this.btnSimulaExtremosUNB, JLayeredPane.DEFAULT_LAYER);
    	
    	
    	this.btnSimulaExtremosUNB_Tipo2 = new JButton("SIM_UNB_2");
    	this.btnSimulaExtremosUNB_Tipo2.setToolTipText("Diversas simulações voltadas exclusivamente ao estudo EXTREMOS UNB");
    	this.btnSimulaExtremosUNB_Tipo2.setBounds(10, 220, 90, 25);
    	this.btnSimulaExtremosUNB_Tipo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnSimulaExtremosUNB_Tipo2);
			}
		});
    	this.panelButtons.add(this.btnSimulaExtremosUNB_Tipo2, JLayeredPane.DEFAULT_LAYER);
    	
    	/*this.btnInserirBud = new JButton("BUD");
    	this.btnInserirBud.setToolTipText("Inserir todos os resultados no BUD da ferramenta");
    	this.btnInserirBud.setBounds(10, 160, 90, 25);
    	this.btnInserirBud.setEnabled(false);
    	this.btnInserirBud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonAction(btnInserirBud);
			}
		});
    	this.panelButtons.add(this.btnInserirBud, JLayeredPane.DEFAULT_LAYER);*/
    	
    	
    	
    	
	}
	

	private void buttonAction(JButton jb){
		if (jb.equals(this.btnexecutarTestes)){
			
				//this.executarBootstrap();	
			
		}
		
	}
	
	
}
