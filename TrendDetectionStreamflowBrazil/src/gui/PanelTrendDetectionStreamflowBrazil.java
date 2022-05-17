package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.jfree.ui.RefineryUtilities;


import com.vividsolutions.jump.workbench.model.Category;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;

import gui.tableResultTrendTeste.FrameResultadoEstacionaridade;
import gui.tableResultTrendTeste.FrameResultadoEstacionaridadeAllGauges;
import io.DesenharShapesResultadoDetalhado;
import io.ExportarResultadoTabelaResumoBsen;
import io.ExtensionFileFilter;
import io.MAR_ImportDataDAO;
import io.PanelEscolherArquivoExcelExportarResultado;
import io.StationaritySummary;
import tests.ExecutarTestesEstacionaridadeMapaResultsAllCorrelTemporal;
import types.InventarioHidrologico;
import types.ResultEstacionaridade;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;
import util.Messages;



public class PanelTrendDetectionStreamflowBrazil extends JFrame implements  
PropertyChangeListener{

private static final long serialVersionUID = 1L;
	
	private JFileChooser chooser;
	
	public JTextField getTxtDiretorioArquivo() {
		return txtDiretorioArquivo;
	}



	public JTextField getTxtDiretorioArquivoOutput() {
		return txtDiretorioArquivoOutput;
	}



	private JPanel panelData;
	private JPanel panelButtons;
	
	private JButton btnExecute;
	private JButton btnExecuteSIM2;
	private JButton btnCancel;
	
	private ButtonGroup cboxButtonGroup;
	private JRadioButton button1;
	private JRadioButton button2;
	private JRadioButton button3;
	
	
	private JCheckBox ckbox01;
	private JCheckBox ckbox02;
	
	private SimulationDataExtremos simulationData;
	//private PanelTestesEstatisticos pnd;
	private ExtensionFileFilter filter;
	//private PanelImportarDados panelImportarDados;
	
	private JPanel panelSetarDiretorios;
	public JTextField txtDiretorioArquivo;
	private JButton btnDiretorioArquivo;
	
	private JPanel panelSetarDiretoriosOutput;
	public JTextField txtDiretorioArquivoOutput;
	private JButton btnDiretorioArquivoOutput;
	
	protected PlugInContext context = null;
	protected Category category = null;
	
	
	private JButton btnExecuteSIM3;
	private JButton btnExecuteSIM5;
	private JButton btnExecuteSIM6;
	private JButton btnExecuteSIM7;
	
	private JButton btnExecuteSIM8;
	
	//public PanelShapeComAtributoSimplificado panelShapeComAtributoSimplificado;
	
	private JPanel panelAgregarShapes;
	
	private JButton btnAgregarShapesTipo1;
	private JButton btnAgregarShapesTipo2;
	private JButton btnBaciasONS;
	private JButton btnExportarVhidPorShape;
	
	private JButton btnDefinirCaracteristicaSeriesHidro;
	
	private JButton btnDefinirDesenharPermanenciaIntervaloConfiancaBoostrap;
	
	private JButton btnDefinirCorrelEspacial;
	
	
	private JButton btnExecuteSIM9;
	private JButton btnExecuteSIM10;
	private JButton btnSimulaTipo1TemperaturaNexgddp;
	
	private JButton btnResultadoBudykoSMAPGabriela;
	private JButton btnResultadosFelipeSBRH;
	private JButton btnResultadosDecisaoTp1;
	private JButton btnResultadosDecisaoTp2;

	private JPanel panelImportData;
	private PanelTabelaIDadosImportados panelDadosTable;
	private JCheckBox checkSelecionarTodas;
	private JPanel formatPanelData;
	 private JButton btnexecutarTestes;
	 private JButton btnImportData;
	 
	 private JPanel panelEstacionaridadeTendencia;
	 private JCheckBox[] checkEstacionaridadeTendencia = new JCheckBox[50]; 
	 static private String []  nomeTesteIndep =new String[12];
	 static private String []  nomeTesteTenden =new String[12];
	 static private String []  nomeTesteMedia =new String[12];
	 static private String []  nomeTesteVariancia =new String[12];
	 static private String []  nomeTesteOutlier=new String[12];
	 static private String []  nomeTesteHomogeneidade=new String[12];
	 static private String []  nomeTesteAnaReg=new String[12];
		
		static private String []  nomeTesteTendenAutoCorrelacao =new String[12];
	 
		private JPanel panelFDR;
		private ButtonGroup cboxButtonGroupFDR;
		private JRadioButton button1FDR;
		private JRadioButton button2FDR;
		
		private JCheckBox checkSelFDR;	
		private JPanel panelTipoHipotese;
		
		
		/*private ButtonGroup cboxButtonGroup;
		private JRadioButton button1;
		private JRadioButton button2;
		private JRadioButton button3;*/
		
		private JPanel panelNivelSignificanciaTeorico;
		private JLabel lblNivelSignificanciaTeorico;
		private JComboBox cboNivelSignificanciaTeorico;	
		private JCheckBox[] checkEstacionaridadeTendenciaAutoCorrelacao = new JCheckBox[50];
		private JPanel panelEstacionaridadeTendenciaAutoCorrelacao;
		private ButtonGroup cboxButtonGroupAutoCorrel;
		private JRadioButton button1AutoCorrel;
		private JRadioButton button2AutoCorrel;
		private JRadioButton button3AutoCorrel;
		private JRadioButton button4AutoCorrel;
		private JRadioButton button5AutoCorrel;
		
		
		private JCheckBox checkSelAutoCorrel;
		
		 private JButton btnResultTable;
		
		 public JTextArea textAreaResumo;
		 public final static String newline = "\n";
		 public JFrame frameResumo;
		 public JPanel panelResumo;
		 
		 private JButton btnSummaryResult;
		 private JButton btnSaveXLSXProgress;
		 private JButton btnSaveSHP;
		 
	public PanelTabelaIDadosImportados getPanelDadosTable() {
		return panelDadosTable;
	}



	public void setPanelDadosTable(PanelTabelaIDadosImportados panelDadosTable) {
		this.panelDadosTable = panelDadosTable;
	}



	public PanelTrendDetectionStreamflowBrazil(SimulationDataExtremos simulationData) {
		super("Trend Detection Streamflow Brazil");
		this.simulationData = simulationData;
		this.createAndShowGUI();
		this.createPane();
		this.pack();
	}
	
	
	
	private void createPane() {
    	this.chooser = new JFileChooser(new File("."));
    	this.filter = new ExtensionFileFilter("dat", "Lista de Arquivos (*.dat)");
		this.chooser.setFileFilter(this.filter);
	}
	
	private void formatPanelData() {
		this.formatPanelData = new JPanel();
    	this.formatPanelData.setBorder(new EtchedBorder());
    	this.formatPanelData.setBounds(0, 0, 850, 500);
    	this.formatPanelData.setLayout(null);
    	this.formatPanelData.setBackground(Color.LIGHT_GRAY);
		this.add(this.formatPanelData);
		
    	
	}
	private void createAndShowGUI() {
		this.setBounds(20, 20, 800, 500);
		this.setPreferredSize(new Dimension(850, 500));
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		this.formatPanelData();
		this.setarnomescheckbox();
		//this.formatPanelData();
		this.formatPanelButton();
		this.formatPanelSetarDirTemplate();
		this.formatPanelSetarDirOutput();
		this.formatPanelTableDadosFluviometricas();
		this.formatPanelEstacionaridadeTendencia();
		this.formatpanelTipoHipotese();
		this.formatpanelNivelSignificancia();
		this.formatPanelEstacionaridadeTendenciaAutoCorrelacao();
		this.formatPanelFDR();
	
	} 
	
	 public void setarFrameResumoResultados(){
			
		   this.frameResumo = new JFrame("Resultados");
		   this.frameResumo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		   this.frameResumo.setBounds(100, 60, 500, 200);
		   //this.frameResumo.set
         //Add contents to the window.
		   
		   this.frameResumo.add(this.setarPanelResumo());
          //Display the window.
	       this.frameResumo.pack();
	       this.frameResumo.setVisible(true);
	       		  		   
	   }
	
	 
	 private JPanel setarPanelResumo(){
			
		   this.panelResumo=new JPanel();
		   this.panelResumo.setLayout(new GridBagLayout());
		   this.textAreaResumo = new JTextArea(40, 110);
		   this.textAreaResumo.setEditable(false);
	       JScrollPane scrollPane = new JScrollPane(this.textAreaResumo);
	       //Add Components to this panel.
	       GridBagConstraints c = new GridBagConstraints();
	       c.gridwidth = GridBagConstraints.REMAINDER;
          c.fill = GridBagConstraints.HORIZONTAL;
	       //add(textField, c);
	       c.fill = GridBagConstraints.BOTH;
	       c.weightx = 1.0;
	       c.weighty = 1.0;
	       this.panelResumo.add(scrollPane, c);
		  
	       return this.panelResumo;
		 
	 }
private void formatPanelSetarDirOutput() {
		
		
		this.panelSetarDiretoriosOutput = new JPanel();
		this.panelSetarDiretoriosOutput.setBackground(Color.LIGHT_GRAY);
    	this.panelSetarDiretoriosOutput.setBorder(new EtchedBorder());
    	this.panelSetarDiretoriosOutput.setBorder(BorderFactory.createTitledBorder("Directory Output"));
    	
    	this.panelSetarDiretoriosOutput.setBounds(5, 410, 835,50);
    	this.panelSetarDiretoriosOutput.setLayout(null);
    	this.formatPanelData.add(this.panelSetarDiretoriosOutput);	
	
    	
    	this.txtDiretorioArquivoOutput = new JTextField();
		this.txtDiretorioArquivoOutput.setBounds(10, 20, 700, 25);
		this.txtDiretorioArquivoOutput.setEnabled(true);
		this.panelSetarDiretoriosOutput.add(this.txtDiretorioArquivoOutput);
		
		this.btnDiretorioArquivoOutput = new JButton("Directory");
    	this.btnDiretorioArquivoOutput.setToolTipText("Selecionar diretorio onde os arquivos criados serão salvos");
    	this.btnDiretorioArquivoOutput.setBounds(720, 20, 90, 25);
    	//String dirOutput="E:\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\resultados\\RESULTADOS_SET_2017\\";
    	//String dirOutput="C:\\Users\\saulo.souza\\Desktop\\Resultados - CAPES-ANA - SET_17\\RESULTADOS\\30 ANOS_COHID_01\\ANUAL\\FLU\\FDR-PW\\";
    	//String dirOutput="C:\\AnaliseTendenciaExtremosBrasil\\resultados\\";
    	//String dirOutput="C:\\Artigos 2018\\Artigo2 - Analise de Sensibilidade\\Resultados2018\\";
    	//String dirOutput="C:\\Artigos 2018\\Artigo1 - Analise de Tendencia\\resultados\\testes\\";
    	//String dirOutput="C:\\Users\\saulo.souza\\Desktop\\Capitulos_Livro_Dirceu\\CorrelacaoEspacialDirceu\\resultados\\";
    	String dirOutput="C:\\Users\\saulo.souza\\eclipse-workspace\\TrendDetectionStreamflowBrazil\\test\\";
    	
    	this.txtDiretorioArquivoOutput.setText(dirOutput);
    	this.simulationData.setDirOutputExtremosUNB(dirOutput);
    	this.btnDiretorioArquivoOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();  
          	     // restringe a amostra a diretorios apenas  
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
                 int res = fc.showOpenDialog(null);  
                if(res == JFileChooser.APPROVE_OPTION){ 
            	 File diretorio = fc.getSelectedFile();  
                 //JOptionPane.showMessageDialog(null, "Voce escolheu o diretório: " + diretorio.getName()); 
            	 txtDiretorioArquivoOutput.setText(diretorio.getAbsolutePath()+ "\\");
                 //simulationData.setDirSalvarArquivos(diretorio.getAbsolutePath()+ "\\");
                 simulationData.setDirOutputExtremosUNB(diretorio.getAbsolutePath()+ "\\");
               }  
              else  
                    JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");  
           
			}
		});
    	this.panelSetarDiretoriosOutput.add(this.btnDiretorioArquivoOutput, JLayeredPane.DEFAULT_LAYER);
    	
}


	private void formatPanelSetarDirTemplate() {
		
		
		this.panelSetarDiretorios = new JPanel();
		this.panelSetarDiretorios.setBackground(Color.LIGHT_GRAY);
    	this.panelSetarDiretorios.setBorder(new EtchedBorder());
    	this.panelSetarDiretorios.setBorder(BorderFactory.createTitledBorder("Directory Templates"));
    	
    	this.panelSetarDiretorios.setBounds(5, 360, 835,50);
    	this.panelSetarDiretorios.setLayout(null);
    	this.formatPanelData.add(this.panelSetarDiretorios);	
	
    	
    	this.txtDiretorioArquivo = new JTextField();
		this.txtDiretorioArquivo.setBounds(10, 20, 700, 25);
		this.txtDiretorioArquivo.setEnabled(true);
		this.panelSetarDiretorios.add(this.txtDiretorioArquivo);
		
		this.btnDiretorioArquivo = new JButton("Directory");
    	this.btnDiretorioArquivo.setToolTipText("Selecionar diretorio onde os arquivos criados serão salvos");
    	this.btnDiretorioArquivo.setBounds(720, 20, 90, 25);
    	//String dirTemplate="E:\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\resultados\\templates\\";
    	//String dirTemplate="C:\\AnaliseTendenciaExtremosBrasil\\templates\\";
    	//String dirTemplate="C:\\Users\\saulo.souza\\eclipse-workspace\\OpenJump1141\\TemplatesXLSOficial\\";
    	String dirTemplate="C:\\Users\\saulo.souza\\eclipse-workspace\\TrendDetectionStreamflowBrazil\\TemplatesXLSOficial\\";
    	
    	this.txtDiretorioArquivo.setText(dirTemplate);
    	this.simulationData.setDirTemplateExtremosUNB(dirTemplate);
    	this.btnDiretorioArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();  
          	     // restringe a amostra a diretorios apenas  
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
                 int res = fc.showOpenDialog(null);  
                if(res == JFileChooser.APPROVE_OPTION){ 
            	 File diretorio = fc.getSelectedFile();  
                 //JOptionPane.showMessageDialog(null, "Voce escolheu o diretório: " + diretorio.getName()); 
                 txtDiretorioArquivo.setText(diretorio.getAbsolutePath()+ "\\");
                 //simulationData.setDirSalvarArquivos(diretorio.getAbsolutePath()+ "\\");
                 simulationData.setDirTemplateExtremosUNB(diretorio.getAbsolutePath()+ "\\");
               }  
              else  
                    JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");  
           
			}
		});
    	this.panelSetarDiretorios.add(this.btnDiretorioArquivo, JLayeredPane.DEFAULT_LAYER);
    	
}
	
	 public void formatPanelTableDadosFluviometricas(){
	      
	      
	      this.panelImportData= new JPanel();
	      this.panelImportData.setBounds(10, 10, 250, 340);
	      this.panelImportData.setLayout(null);
	      this.formatPanelData.add(this.panelImportData);
	      this.panelImportData.setBorder(BorderFactory.createTitledBorder("Import Data"));
	      this.panelImportData.setBackground(Color.LIGHT_GRAY);
	      //this.panelButtons
	        int posx=10;
	        int posy=20;
	        int tamx=230;
	        int tamy=290;
	        
	        ArrayList<VariavelHidrologica> variaveishidrologicas=new ArrayList<VariavelHidrologica>();
	        this.simulationData.setVariaveisHidr(variaveishidrologicas);
	        this.panelDadosTable=new PanelTabelaIDadosImportados(this.simulationData.getVariaveisHidr(),tamx,tamy);
	        this.panelDadosTable.setBounds(posx, posy, tamx,tamy);
	        this.panelDadosTable .setBackground(Color.LIGHT_GRAY);
	        this.panelDadosTable .setBorder(new EtchedBorder());
	        this.panelDadosTable.setBorder(BorderFactory.createTitledBorder("Gauges"));
	        this.panelDadosTable.setLayout(null);
	        //this.add(this.panelDadosTable);
	        this.panelImportData.add(this.panelDadosTable);
	        this.formateCheckSelTodas();
	        
	        
	 }
	
	 private void formateCheckSelTodas(){
	      this.checkSelecionarTodas =new JCheckBox("Select All Gauges");
	      this.checkSelecionarTodas.setHorizontalTextPosition(SwingConstants.RIGHT);
	      this.checkSelecionarTodas.setVerticalTextPosition(SwingConstants.CENTER);
	      this.checkSelecionarTodas.setSelected(true);
	      this.checkSelecionarTodas.setBackground(Color.LIGHT_GRAY);
	      this.panelImportData.add(this.checkSelecionarTodas);
	      //this.panelDadosTable.add(this.checkSelecionarTodas);
	      this.checkSelecionarTodas.setBounds(10, 310, 200, 25);
	    
	      
	      this.checkSelecionarTodas.addItemListener(new ItemListener(){  
	        public void itemStateChanged(ItemEvent evt){
	          int nrows =5000;
	          if(simulationData.getVariaveisHidr() != null){
	            nrows = simulationData.getVariaveisHidr().size();
	          }
	        if(checkSelecionarTodas.isSelected() == true){
	            for (int r = 0; r < nrows; r++){    
	          panelDadosTable.getTabela().setValueAt(true, r, 0);   
	              }
	         }else{
	            for (int r = 0; r < nrows; r++){    
	          panelDadosTable.getTabela().setValueAt(false, r, 0);
	          }   
	         }
	        
	        panelDadosTable.getTabela().repaint();
	        }
	      });
	     }
	 
	 
	 
	 
	 
	 private void formatPanelEstacionaridadeTendencia() {
			this.panelEstacionaridadeTendencia = new JPanel();
			this.panelEstacionaridadeTendencia.setBackground(Color.LIGHT_GRAY);
	    	this.panelEstacionaridadeTendencia.setBorder(BorderFactory.createTitledBorder("Trend Tests"));
	    	//this.panelEstacionaridadeTendencia.setBounds(230, 210, 215, 190);
	    	this.panelEstacionaridadeTendencia.setBounds(260, 10, 160, 120);
	    	this.panelEstacionaridadeTendencia.setLayout(null);
	    	this.formatPanelData.add(this.panelEstacionaridadeTendencia);
	    	this.formatCheckEstacionaridadeTendencia();
	    	//this.panelEstacionaridadeTendencia.setEnabled(false);
			
		}
	 
	 
	 private void formatCheckEstacionaridadeTendencia() {
			int ntestindep=3;
			for (int i=0;i<ntestindep;i++){
				this.checkEstacionaridadeTendencia[i] = new JCheckBox(PanelTrendDetectionStreamflowBrazil.nomeTesteTenden[i]);
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
	 
	 
	 private void setarnomescheckbox() {
			
		 PanelTrendDetectionStreamflowBrazil.nomeTesteIndep[0]="Median Crossing";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteIndep[1]="Turning Points";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteIndep[2]="Rank Difference";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteIndep[3]="Autocorrelation";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteIndep[4]="Wald-Wolfowitz";
			
		 PanelTrendDetectionStreamflowBrazil.nomeTesteTenden[0]="Mann-Kendall        ";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteTenden[1]="Spearman’s Rho   ";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteTenden[2]="Linear Regression";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteTenden[3]="Autocorrelation";
		
					
		 PanelTrendDetectionStreamflowBrazil.nomeTesteHomogeneidade[0]="Mann-Whitney";
			
		 PanelTrendDetectionStreamflowBrazil.nomeTesteMedia[0]="Teste T";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteMedia[1]="Distribution CUSUM";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteMedia[2]="Cumul. Deviation";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteMedia[3]="Worsley Lik. Ratio";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteMedia[4]="Rank-Sum";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteMedia[5]="Mann-Whitney";
			
		 PanelTrendDetectionStreamflowBrazil.nomeTesteVariancia[0]="Teste F";
							
		 PanelTrendDetectionStreamflowBrazil.nomeTesteAnaReg[0]="Medida de discordância";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteAnaReg[1]="Heterogeneidade - Identificação de Regiões Homogêneas";
					
		 PanelTrendDetectionStreamflowBrazil.nomeTesteOutlier[0]="Chauvenet’s method";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteOutlier[1]="Dixon–Thompson";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteOutlier[2]="Rosner";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteOutlier[3]="Grubbs and Beck";
			
		 //PanelTrendDetectionStreamflowBrazil.nomeTesteAderencia[0]="Teste Qui-Quadrado";
		 //PanelTrendDetectionStreamflowBrazil.nomeTesteAderencia[1]="Kolmogorov-Smirnov";
		 //PanelTrendDetectionStreamflowBrazil.nomeTesteAderencia[2]="Zdist(Hosking,1991)";
			
			
		 PanelTrendDetectionStreamflowBrazil.nomeTesteTendenAutoCorrelacao[0]="Mann-Kendall - PW";
		 PanelTrendDetectionStreamflowBrazil.nomeTesteTendenAutoCorrelacao[1]="Mann-Kendall - TFPW";
			
		}
	 
	 
	 
	 public void formatpanelTipoHipotese (){
			
			this.panelTipoHipotese=new JPanel();
			this.panelTipoHipotese.setBackground(Color.LIGHT_GRAY);
			this.panelTipoHipotese.setBorder(BorderFactory.createTitledBorder("Hypothesis"));
	    	this.panelTipoHipotese.setBounds(420, 10, 120, 120);
			this.panelTipoHipotese.setLayout(new GridLayout(0, 1));
	    		
			this.cboxButtonGroup = new ButtonGroup();
			button1 = new JRadioButton("Two-Tailed");
			button1.setSelected(true);
			button2 = new JRadioButton("Left-Tailed");
			button3 = new JRadioButton("Right-Tailed");
			button1.setBackground(Color.LIGHT_GRAY);
			button2.setBackground(Color.LIGHT_GRAY);
			button3.setBackground(Color.LIGHT_GRAY);
			this.cboxButtonGroup.add(button1);
			this.cboxButtonGroup.add(button2);
			this.cboxButtonGroup.add(button3);
			this.panelTipoHipotese.add(button1);
			this.panelTipoHipotese.add(button2);
			this.panelTipoHipotese.add(button3);
			this.formatPanelData.add(this.panelTipoHipotese);
			
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
			this.panelNivelSignificanciaTeorico.setBorder(BorderFactory.createTitledBorder("Significance Level"));
	    	this.panelNivelSignificanciaTeorico.setBounds(540, 10, 120, 60);
			//this.panelNivelSignificanciaTeorico.setLayout(new GridLayout(0, 1));
			this.panelNivelSignificanciaTeorico.setLayout(null);
			this.formatPanelData.add(this.panelNivelSignificanciaTeorico);
			
			String[] items = {"5 %", "10 %", "1 %"};
			this.cboNivelSignificanciaTeorico = new JComboBox(items);
	    	this.cboNivelSignificanciaTeorico.setBounds(50, 35, 60, 20);
	    	this.panelNivelSignificanciaTeorico.add(this.cboNivelSignificanciaTeorico);
	    	
	    	
	    	this.lblNivelSignificanciaTeorico = new JLabel("Alpha:");
	    	this.lblNivelSignificanciaTeorico.setBounds(10, 40, 40, 15);
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
	 
	 
	 
	 
	 private void formatPanelFDR() {
			
			this.panelFDR = new JPanel();
			this.panelFDR.setBackground(Color.LIGHT_GRAY);
	    	this.panelFDR.setBorder(BorderFactory.createTitledBorder("Multiplicity Test"));
	    	this.panelFDR.setBounds(420, 130, 120, 100);
	    	this.panelFDR.setLayout(null);
	    	//this.panelEstacionaridadeTendenciaAutoCorrelacao.setLayout(new GridLayout(0, 1));
	    	this.formatPanelData.add(this.panelFDR);
	    	
	    	
	    	JPanel panel = new JPanel();
			panel.setBounds(10, 40, 100, 50);
			panel.setLayout(new GridLayout(0, 1));
			
			this.cboxButtonGroupFDR = new ButtonGroup();
			int ntestindep=2;
			this.button1FDR = new JRadioButton("FDR");
			this.button1FDR.setSelected(true);
			this.button2FDR = new JRadioButton("FWER");
			this.cboxButtonGroupFDR.add(this.button1FDR);
			this.cboxButtonGroupAutoCorrel.add(this.button2FDR);
			panel.add(this.button1FDR);
			panel.add(this.button2FDR);
			this.panelFDR.add(panel);
			//this.panelEstacionaridadeTendenciaAutoCorrelacao.add(panel);
			
			this.button1FDR.setBackground(Color.LIGHT_GRAY);
			this.button2FDR.setBackground(Color.LIGHT_GRAY);
			
			this.button1FDR.setEnabled(false);
			this.button2FDR.setEnabled(false);
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
			
			this.button2FDR.addItemListener(new ItemListener(){  
				public void itemStateChanged(ItemEvent evt){  
				 if(button2FDR.isSelected() == true){
					 //simulationData.setFazerPW(button1AutoCorrel.isSelected());
					 //simulationData.setFazerTFPW(button2FDR.isSelected());
				 }
					  }
			});
			
			this.checkSelFDR =new JCheckBox("Select");
			this.checkSelFDR.setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkSelFDR.setVerticalTextPosition(SwingConstants.CENTER);
			this.checkSelFDR.setSelected(false);
			this.checkSelFDR.setBackground(Color.LIGHT_GRAY);
			this.checkSelFDR.setBounds(45, 15, 80, 25);
			this.panelFDR.add(this.checkSelFDR);
			
			
			this.checkSelFDR.addItemListener(new ItemListener(){  
				public void itemStateChanged(ItemEvent evt){  
				if(checkSelFDR.isSelected() == true){
					button1FDR.setEnabled(true);
					button2FDR.setEnabled(true);
					simulationData.setFazerFDR(true);				
				 }else{
					 button1FDR.setEnabled(false);
					 button2FDR.setEnabled(false);
					 simulationData.setFazerFDR(false);
				 }
				}
			});
	    	
	    
			
		}
		
		
		private void formatPanelEstacionaridadeTendenciaAutoCorrelacao() {
			this.panelEstacionaridadeTendenciaAutoCorrelacao = new JPanel();
			this.panelEstacionaridadeTendenciaAutoCorrelacao.setBackground(Color.LIGHT_GRAY);
	    	this.panelEstacionaridadeTendenciaAutoCorrelacao.setBorder(BorderFactory.createTitledBorder("Serial Correlation"));
	    	//this.panelEstacionaridadeTendencia.setBounds(230, 210, 215, 190);
	    	this.panelEstacionaridadeTendenciaAutoCorrelacao.setBounds(260, 130, 160, 220);
	    	this.panelEstacionaridadeTendenciaAutoCorrelacao.setLayout(null);
	    	//this.panelEstacionaridadeTendenciaAutoCorrelacao.setLayout(new GridLayout(0, 1));
	    	this.formatPanelData.add(this.panelEstacionaridadeTendenciaAutoCorrelacao);
	  
	    	//260, 10, 160, 120
	    	this.formatCheckEstacionaridadeTendenciaAutoCorrelacao();
	    	//this.panelEstacionaridadeTendencia.setEnabled(false);
	    
		}
		
				
		
		private void formatCheckEstacionaridadeTendenciaAutoCorrelacao() {
			JPanel panel = new JPanel();
			panel.setBounds(10, 40, 100, 130);
			panel.setLayout(new GridLayout(0, 1));
			
			this.cboxButtonGroupAutoCorrel = new ButtonGroup();
			int ntestindep=2;
			this.button1AutoCorrel = new JRadioButton("PW");
			this.button1AutoCorrel.setSelected(true);
			this.button2AutoCorrel = new JRadioButton("TFPW");
			this.button3AutoCorrel = new JRadioButton("MTFPW");
			this.button4AutoCorrel = new JRadioButton("VCPW");
			this.button5AutoCorrel = new JRadioButton("VC");
			this.cboxButtonGroupAutoCorrel.add(this.button1AutoCorrel);
			this.cboxButtonGroupAutoCorrel.add(this.button2AutoCorrel);
			this.cboxButtonGroupAutoCorrel.add(this.button3AutoCorrel);
			this.cboxButtonGroupAutoCorrel.add(this.button4AutoCorrel);
			this.cboxButtonGroupAutoCorrel.add(this.button5AutoCorrel);
			panel.add(this.button1AutoCorrel);
			panel.add(this.button2AutoCorrel);
			panel.add(this.button3AutoCorrel);
			panel.add(this.button4AutoCorrel);
			panel.add(this.button5AutoCorrel);
			
			this.panelEstacionaridadeTendenciaAutoCorrelacao.add(panel);
			this.panelEstacionaridadeTendenciaAutoCorrelacao.add(panel);
			
			this.button1AutoCorrel.setBackground(Color.LIGHT_GRAY);
			this.button2AutoCorrel.setBackground(Color.LIGHT_GRAY);
			this.button3AutoCorrel.setBackground(Color.LIGHT_GRAY);
			this.button4AutoCorrel.setBackground(Color.LIGHT_GRAY);
			this.button5AutoCorrel.setBackground(Color.LIGHT_GRAY);
			
			this.button1AutoCorrel.setEnabled(false);
			this.button2AutoCorrel.setEnabled(false);
			this.button3AutoCorrel.setEnabled(false);
			this.button4AutoCorrel.setEnabled(false);
			this.button5AutoCorrel.setEnabled(false);
			this.simulationData.setConsiderarAutoCorrelacao(false);
			this.simulationData.setFazerPW(true);
			
			
			this.button1AutoCorrel.addItemListener(new ItemListener(){  
				public void itemStateChanged(ItemEvent evt){  
				 if(button1AutoCorrel.isSelected() == true){
					 simulationData.setFazerPW(button1AutoCorrel.isSelected());
					 simulationData.setFazerTFPW(button2AutoCorrel.isSelected());
					 simulationData.setFazerMTFPW(button3AutoCorrel.isSelected());
					 simulationData.setFazerVCPW(button4AutoCorrel.isSelected());
					 simulationData.setFazerVC(button5AutoCorrel.isSelected());
					// simulationData.setTipoHipoteseEstacionaridade(0);
				 }
					  }
			});
			
			
			
			this.button2AutoCorrel.addItemListener(new ItemListener(){  
				public void itemStateChanged(ItemEvent evt){  
				 if(button2AutoCorrel.isSelected() == true){
					 simulationData.setFazerPW(button1AutoCorrel.isSelected());
					 simulationData.setFazerTFPW(button2AutoCorrel.isSelected());
					 simulationData.setFazerMTFPW(button3AutoCorrel.isSelected());
					 simulationData.setFazerVCPW(button4AutoCorrel.isSelected());
					 simulationData.setFazerVC(button5AutoCorrel.isSelected());
				 }
					  }
			});
			
			
			this.button3AutoCorrel.addItemListener(new ItemListener(){  
				public void itemStateChanged(ItemEvent evt){  
				 if(button2AutoCorrel.isSelected() == true){
					 simulationData.setFazerPW(button1AutoCorrel.isSelected());
					 simulationData.setFazerTFPW(button2AutoCorrel.isSelected());
					 simulationData.setFazerMTFPW(button3AutoCorrel.isSelected());
					 simulationData.setFazerVCPW(button4AutoCorrel.isSelected());
					 simulationData.setFazerVC(button5AutoCorrel.isSelected());
				 }
					  }
			});
			
			this.button4AutoCorrel.addItemListener(new ItemListener(){  
				public void itemStateChanged(ItemEvent evt){  
				 if(button2AutoCorrel.isSelected() == true){
					 simulationData.setFazerPW(button1AutoCorrel.isSelected());
					 simulationData.setFazerTFPW(button2AutoCorrel.isSelected());
					 simulationData.setFazerMTFPW(button3AutoCorrel.isSelected());
					 simulationData.setFazerVCPW(button4AutoCorrel.isSelected());
					 simulationData.setFazerVC(button5AutoCorrel.isSelected());
				 }
					  }
			});
			
			
			this.button5AutoCorrel.addItemListener(new ItemListener(){  
				public void itemStateChanged(ItemEvent evt){  
				 if(button2AutoCorrel.isSelected() == true){
					 simulationData.setFazerPW(button1AutoCorrel.isSelected());
					 simulationData.setFazerTFPW(button2AutoCorrel.isSelected());
					 simulationData.setFazerMTFPW(button3AutoCorrel.isSelected());
					 simulationData.setFazerVCPW(button4AutoCorrel.isSelected());
					 simulationData.setFazerVC(button5AutoCorrel.isSelected());
				 }
					  }
			});
						
			this.checkSelAutoCorrel =new JCheckBox("Select");
			this.checkSelAutoCorrel.setHorizontalTextPosition(SwingConstants.LEFT);
			this.checkSelAutoCorrel.setVerticalTextPosition(SwingConstants.CENTER);
			this.checkSelAutoCorrel.setSelected(false);
			this.checkSelAutoCorrel.setBackground(Color.LIGHT_GRAY);
			this.checkSelAutoCorrel.setBounds(90, 15, 70, 25);
			this.panelEstacionaridadeTendenciaAutoCorrelacao.add(this.checkSelAutoCorrel);
			
			
			this.checkSelAutoCorrel.addItemListener(new ItemListener(){  
				public void itemStateChanged(ItemEvent evt){  
				if(checkSelAutoCorrel.isSelected() == true){
					button1AutoCorrel.setEnabled(true);
					button2AutoCorrel.setEnabled(true);
					button3AutoCorrel.setEnabled(true);
					button4AutoCorrel.setEnabled(true);
					button5AutoCorrel.setEnabled(true);
					simulationData.setConsiderarAutoCorrelacao(true);				
				 }else{
					 button1AutoCorrel.setEnabled(false);
					 button2AutoCorrel.setEnabled(false);
					 button3AutoCorrel.setEnabled(false);
					 button4AutoCorrel.setEnabled(false);
					button5AutoCorrel.setEnabled(false);
					 simulationData.setConsiderarAutoCorrelacao(false);
				 }
				}
			});
			
						
		
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 private void formatPanelButton() {
			
			this.panelButtons = new JPanel();
			this.panelButtons.setBackground(Color.LIGHT_GRAY);
	    	this.panelButtons.setBorder(new EtchedBorder());
	    	this.panelButtons.setBounds(720, 10, 110, 350);
	    	this.panelButtons.setLayout(null);
			this.formatPanelData.add(this.panelButtons);
			
	    	this.btnImportData = new JButton("Import");
	    	this.btnImportData.setToolTipText("Abrir lista de postos fluviométricos");
	    	this.btnImportData.setBounds(10, 10, 90, 25);
	    	this.btnImportData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonAction(btnImportData);
				}
			});
	    	this.panelButtons.add(this.btnImportData, JLayeredPane.DEFAULT_LAYER);
	    	
	    	
	    	this.btnExecute = new JButton("Execute");
	    	this.btnExecute.setToolTipText("Execute trend tests");
	    	this.btnExecute.setBounds(10, 40, 90, 25);
	    	this.btnExecute.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonAction(btnExecute);
				}
			});
	    	this.panelButtons.add(this.btnExecute, JLayeredPane.DEFAULT_LAYER);
	    	
	    	this.btnResultTable = new JButton("Restbl");
	    	this.btnResultTable.setToolTipText("Execute trend tests");
	    	this.btnResultTable.setBounds(10, 70, 90, 25);
	    	this.btnResultTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonAction(btnResultTable);
				}
			});
	    	this.panelButtons.add(this.btnResultTable, JLayeredPane.DEFAULT_LAYER);
	    	
	    	this.btnSummaryResult = new JButton("Summary");
	    	this.btnSummaryResult.setToolTipText("Execute trend tests");
	    	this.btnSummaryResult.setBounds(10, 100, 90, 25);
	    	this.btnSummaryResult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonAction(btnSummaryResult);
				}
			});
	    	this.panelButtons.add(this.btnSummaryResult, JLayeredPane.DEFAULT_LAYER);
	    	
	    	this.btnSaveXLSXProgress = new JButton("XLSX");
	    	this.btnSaveXLSXProgress.setToolTipText("Execute trend tests");
	    	this.btnSaveXLSXProgress.setBounds(10, 130, 90, 25);
	    	this.btnSaveXLSXProgress.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonAction(btnSaveXLSXProgress);
				}
			});
	    	this.panelButtons.add(this.btnSaveXLSXProgress, JLayeredPane.DEFAULT_LAYER);
	    	
	    	this.btnSaveSHP = new JButton("SHP");
	    	this.btnSaveSHP.setToolTipText("Execute trend tests");
	    	this.btnSaveSHP.setBounds(10, 160, 90, 25);
	    	this.btnSaveSHP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonAction(btnSaveSHP);
				}
			});
	    	this.panelButtons.add(this.btnSaveSHP, JLayeredPane.DEFAULT_LAYER);
	    	
	    	
	    	
	 }
	 
	 
	
	
	
	
	
	private void buttonAction(JButton jb){
		//this.context= this.simulationData.getContext();        
	    //this.category = this.context.getLayerManager().getCategory(SNIRHPlugInSettings.resultLayerCategory());
		if (jb.equals(this.btnImportData)){
		
		this.importDataGauges();
				
		}else if(jb.equals(this.btnExecute)){
			
			this.executeTrendTest();
		}else if(jb.equals(this.btnResultTable)){
			
			this.executeResultTable();
		}else if(jb.equals(this.btnSummaryResult)){
			
			this.summaryResult();
		}else if(jb.equals(this.btnSaveXLSXProgress)){
			
			this.saveXLSXProgress();
		}else if(jb.equals(this.btnSaveSHP)){
			
			this.saveSHP();
		}
	}
	
	
	
	private void saveSHP() {
		// TODO Auto-generated method stub
		DesenharShapesResultadoDetalhado desenharShapesResultadoDetalhado=new DesenharShapesResultadoDetalhado(this.simulationData,this);
		String dirShape="C:\\Users\\saulo.souza\\eclipse-workspace\\TrendDetectionStreamflowBrazil\\test";
		desenharShapesResultadoDetalhado.execute("MK", dirShape);
	}



	private void saveXLSXProgress(){
		/*executarSalvarExcel boot= new executarSalvarExcel(this.simulationData,this);
	    boot.addPropertyChangeListener(this);
		boot.execute();*/
		
		//ExportarResultadoTabelaResumoBsen exportar=new ExportarResultadoTabelaResumoBsen(this.simulationData,this);
		//exportar.executar();
		
		final PanelEscolherArquivoExcelExportarResultado panelEscolherArq = new PanelEscolherArquivoExcelExportarResultado(this.simulationData,this);
		panelEscolherArq.setVisible(true);
		panelEscolherArq.pack();
		RefineryUtilities.centerFrameOnScreen(panelEscolherArq);
		
	}

	private void summaryResult() {
		// TODO Auto-generated method stub
		StationaritySummary stationaritySummary=new StationaritySummary(this.simulationData,this);
		stationaritySummary.resumoResultadosProgress();
	}



	private void importDataGauges() {

	    //ArrayList<ConfigAnaliseFrequenciaMinimos> config=this.panelDadosTableAnaliseFrequenciaMinimos.pegarConfiguracoesColocadasNaTabela();
	    
	    final PanelEscolherArquivo panelEscolherArq = new PanelEscolherArquivo(this.simulationData,this);
	    panelEscolherArq.setVisible(true);
	    panelEscolherArq.pack();
	    RefineryUtilities.centerFrameOnScreen(panelEscolherArq);
	    
	    
	  }
	
	
	public void executeTrendTest()  {
		
		this.simulationData.setDirTemplateExtremosUNB(this.txtDiretorioArquivo.getText());
		this.simulationData.setDirOutputExtremosUNB(this.txtDiretorioArquivoOutput.getText());
		String dirTemplate=this.simulationData.getDirTemplateExtremosUNB();
		
		ExecutarTestesEstacionaridadeMapaResultsAllCorrelTemporal executarTestes = new ExecutarTestesEstacionaridadeMapaResultsAllCorrelTemporal(this.simulationData);
		Map<String, Map<String,ResultEstacionaridade>> resultEstacionaridadeTipo2=executarTestes.executarTestes();
		
		Messages.informMsg("Trend test run successfully");
		System.out.println("finished");
		
	}
	
	
	
	
	private void executeResultTable() {
		// TODO Auto-generated method stub
		//final FrameResultadoEstacionaridade frameresultest = new FrameResultadoEstacionaridade(this.simulationData,this);
		
		final FrameResultadoEstacionaridadeAllGauges frameresultest = new FrameResultadoEstacionaridadeAllGauges(this.simulationData,this);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void simularTipo1()  {
		
		//String dirTemplate="C:\\OpenJump150\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\resultados\\templates\\";
		
		this.simulationData.setDirTemplateExtremosUNB(this.txtDiretorioArquivo.getText());
		this.simulationData.setDirOutputExtremosUNB(this.txtDiretorioArquivoOutput.getText());
			
		String dirTemplate=this.simulationData.getDirTemplateExtremosUNB();
		String nomearqTemplate="template_xls_TabelaResultadosIndices_tp3.xls";
		//String dirOutput="C:\\OpenJump150\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\resultados\\";
		String dirOutput=this.simulationData.getDirOutputExtremosUNB();
		
		//boolean pegarDadosShape=this.panelShapeComAtributoSimplificado.getCheckboxSelecionado().isSelected();
		boolean fazerFiltro=false;
		//String tipoFiltro="areaDrenagem";
		String tipoFiltro="impactoReservacao";
		String filtroCod="0";
		//String filtroCod="1";
		/**
		 * Leitura dos varios arquivos de diferentes indices
		 */
		
		String dir = null;
		String filename = null;
		File [] files = null;
		String [] filenames = null;
		ArrayList<String> nomeArquivo=new ArrayList<String>();
		//Messages.informMsg("Indique o(s) arquivo(s) .dat");
		this.chooser.setMultiSelectionEnabled(true);
		int returnVal = this.chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		dir = this.chooser.getCurrentDirectory().getAbsolutePath() + "\\";
		filename = this.chooser.getSelectedFile().getName();
		files=this.chooser.getSelectedFiles();
		filenames = new String [files.length];
		   for(int i=0;i<files.length;i++){
			filenames[i]=files[i].getName();  
			nomeArquivo.add(files[i].getName());
		   }
		}
		
		
		MAR_ImportDataDAO leiturarArquivos = new MAR_ImportDataDAO();
		Map<String,ArrayList<VariavelHidrologica>> vhidPorIndice=new HashMap<String,ArrayList<VariavelHidrologica>>();
		ArrayList<String>nomeIndicePorOrdemUsuario=new ArrayList<String>();
		Map<String,InventarioHidrologico> inventario=new HashMap<String,InventarioHidrologico>();
		Map<String,Map<String,Double>> valoresCamposGeoMapaPorIndice=new HashMap<String,Map<String,Double>>();
		
		Map<String, Map<String, Map<String, Map<String,ResultEstacionaridade>>>> resultEstacionaridadeShape=
				new HashMap<String, Map<String, Map<String, Map<String,ResultEstacionaridade>>>> ();
		
		for(int i=0;i<nomeArquivo.size();i++){
		  		  
		  ArrayList<VariavelHidrologica> vhidOriginal=leiturarArquivos.leituraDATVarHidSemBarraProgresso(dir, nomeArquivo.get(i));
		  ArrayList<VariavelHidrologica> variaveishidrologicasSheet=new ArrayList<VariavelHidrologica>();
		  variaveishidrologicasSheet=vhidOriginal;
		  
				   for(int k=0;k<variaveishidrologicasSheet.size();k++){
						  String cod=variaveishidrologicasSheet.get(k).getInvhidro().getEstacao_codigo();
						  if(!inventario.containsKey(cod)){
						  inventario.put(cod, variaveishidrologicasSheet.get(k).getInvhidro());
						  }
					}
			//formatos antigos dos arquivos o nome do indice ficavam em descricaoOrigem serie
			String nomeIndice=variaveishidrologicasSheet.get(0).getInvhidro().getDescricaoOrigemSerie();
			//String nomeIndice=variaveishidrologicasSheet.get(0).getInvhidro().getTipodeDado();
			vhidPorIndice.put(nomeIndice, variaveishidrologicasSheet);
			nomeIndicePorOrdemUsuario.add(nomeIndice);
			
			this.simulationData.setVariaveisHidr(null);
			//String nomeIndice=nomeIndicePorOrdemUsuario.get(j);
			this.simulationData.setVariaveisHidr(vhidPorIndice.get(nomeIndice));
			
			ExecutarTestesEstacionaridadeMapaResultsAllCorrelTemporal executarTestes = new ExecutarTestesEstacionaridadeMapaResultsAllCorrelTemporal(this.simulationData);
			Map<String, Map<String,ResultEstacionaridade>> resultEstacionaridadeTipo2=executarTestes.executarTestes();
			
			boolean escrecerPlanilhaTp1=false;
	        if(escrecerPlanilhaTp1){
	          //String nomearqTemplateTP1="template_xls_bsenPvalue_comS_tp3.xls";
	          String nomearqTemplateTP1="template_xls_bsenPvalue_comS_tp3_tamanho_magnitude";
					ExportarResultadoTabelaResumoBsen.
					executarPlanilhaSimulacao(dirTemplate,nomearqTemplateTP1,dirOutput,resultEstacionaridadeTipo2,nomeIndice+"_"+nomeIndice,inventario, this.simulationData);
	        }
						
			System.out.println("arquivo - "+nomeArquivo.get(i));
		}
	
	
	
	
	}

	public Object[][] setDadosResEstac(ArrayList<ResultEstacionaridade> resultestacionaridade) {
		int ntestes=14;
		String [] nometeste=new String [ntestes];
		
		
		nometeste[0]="MK";
		nometeste[1]="SR";
		nometeste[2]="LR";
		nometeste[3]="TT";
		nometeste[4]="DC";
		nometeste[5]="CD";
		nometeste[6]="WR";
		nometeste[7]="MW";
		nometeste[8]="TF";
		nometeste[9]="MC";
		nometeste[10]="TP";
		nometeste[11]="RD";
		nometeste[12]="AC";
		nometeste[13]="WW";
		
		String [] nometesteExtenso=new String [ntestes];
		nometesteExtenso[0]="Mann-Kendall";
		nometesteExtenso[1]="Spearman’s Rho";
		nometesteExtenso[2]="Linear Regression";
		nometesteExtenso[3]="Teste T";
		nometesteExtenso[4]="Distribution CUSUM";
		nometesteExtenso[5]="Cumulative Deviation";
		nometesteExtenso[6]="Worsley Lik. Ratio";
		nometesteExtenso[7]="Rank-Sum (Mann-Whitney)";
		nometesteExtenso[8]="Teste F";
		nometesteExtenso[9]="Median Crossing";
		nometesteExtenso[10]="Turning Points";
		nometesteExtenso[11]="Rank Difference";
		nometesteExtenso[12]="Autocorrelation";
		nometesteExtenso[13]="Wald-Wolfowitz";
		
		Object[][] result = new Object[ntestes][11];
		
		for (int i = 0; i < ntestes; i++){
			if(i<3){
			result[i][0]="Mudança gradual (Tendência)";
			}else if(i>=3 && i<7){
			result[i][0]="Mudança brusca (Média)";
			}else if(i==7){
			result[i][0]="Mudança brusca (Mediana)";
			}else if(i==8){
			result[i][0]="Mudança brusca (Variância)";	
			}else{
			result[i][0]="Teste de Independência";	
			}
			
			DecimalFormatSymbols dc = new DecimalFormatSymbols();
			dc.setDecimalSeparator('.');
			String strange = "0.00";
			DecimalFormat myFormatter = new DecimalFormat(strange, dc);		
				for(int j=0;j<resultestacionaridade.size();j++){
					result[i][1]=nometesteExtenso[i];
					if(resultestacionaridade.get(j).getNometeste().equals(nometeste[i])){
						
						System.out.println(resultestacionaridade.get(j).getEstatteste());
						Double valteste=resultestacionaridade.get(j).getEstatteste();
						
						result[i][2]=myFormatter.format(resultestacionaridade.get(j).getEstatteste());
						result[i][3]=myFormatter.format(resultestacionaridade.get(j).getPvalue());
						result[i][5]=myFormatter.format(resultestacionaridade.get(j).getValorcriticoteste());
						
						result[i][4]=resultestacionaridade.get(j).getMetodoObterValCritico();
						result[i][6]=resultestacionaridade.get(j).getResultadoteste();
						result[i][7]=resultestacionaridade.get(j).getResultadoDescritivoTeste();
						String campo2="";
						if(valteste.isNaN()){
							campo2="-99999.0";
						}else{
						campo2=myFormatter.format(resultestacionaridade.get(j).getEstatteste());
						}
						
						
					
						
						String campo3=myFormatter.format(resultestacionaridade.get(j).getPvalue());
						String campo5=myFormatter.format(resultestacionaridade.get(j).getValorcriticoteste());
						
						result[i][2]=Double.parseDouble(campo2);
						result[i][3]=Double.parseDouble(campo3);
						result[i][5]=Double.parseDouble(campo5);
						
						
						if(i<3){
							result[i][8]=resultestacionaridade.get(j).getSentidoTendencia();
							result[i][9]=-99999.0;
							result[i][10]="-99999.0";
							
							}else if(i>=3 && i<9){
							
								result[i][8]="-99999.0";
								String campo9=String.valueOf(resultestacionaridade.get(j).getAnoMudanca());
							result[i][9]=Double.parseDouble(campo9);
							result[i][10]=resultestacionaridade.get(j).getSentidoMediaRecente();
							}else {
								result[i][8]="-99999.0";
								result[i][9]=-99999.0;
								result[i][10]="-99999.0";
							
							}
					
						break;
					}else{
						
						result[i][2]=-99999.0;
						result[i][3]=-99999.0;
						result[i][5]=-99999.0;
						
						result[i][4]="-99999.0";
						result[i][6]="-99999.0";
						result[i][7]="-99999.0";
						
						if(i<3){
							result[i][8]="-99999.0";
							result[i][9]=-99999.0;
							result[i][10]="-99999.0";
							
							}else if(i>=3 && i<9){
							
							result[i][8]="-99999.0";
							result[i][9]=-99999.0;
							result[i][10]="-99999.0";
							}else {
								result[i][8]="-99999.0";
								result[i][9]=-99999.0;
								result[i][10]="-99999.0";
							
							}
						
					}
						
				}
				
			
			
			
			//2,3,5,9
			
		}
		
			
		return result;
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}



	public JFileChooser getChooser() {
		return chooser;
	}



	public void setChooser(JFileChooser chooser) {
		this.chooser = chooser;
	}
	
	
}
