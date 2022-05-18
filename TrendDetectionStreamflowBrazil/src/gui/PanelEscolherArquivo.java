package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

import io.ExtensionFileFilter;
import io.MAR_ImportDataDAO;
import io.MAR_ImportDataDAO_TrendBrazil;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;



public class PanelEscolherArquivo extends JFrame implements  
PropertyChangeListener{

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
  
  private SimulationDataExtremos simulationData;
  private PanelTrendDetectionStreamflowBrazil pnd;
  private ExtensionFileFilter filter;
  
  private JFileChooser chooser_xlsx;
  private ExtensionFileFilter filter_xlsx;

  //BIFURCAÇÃO pnd/pne para criar a regionalização diária (pne)
  
  // Bloco pnd
  public PanelEscolherArquivo(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnd){
    super("Choosing the File to Import");
    this.simulationData = simulationData;
    this.pnd=pnd;
    this.createAndShowGUI();
    this.createPane();
    this.pack();
    
  }
  
   // Bloco pne
 
  
  
  private void createPane() {
      this.chooser = new JFileChooser(new File("."));
      this.filter = new ExtensionFileFilter("dat", "StreamFlow data files (*.dat)");
    this.chooser.setFileFilter(this.filter);
    
    
    this.chooser_xlsx = new JFileChooser(new File("."));
      this.filter_xlsx = new ExtensionFileFilter("xlsx", "StreamFlow data files (*.xlsx)");
    this.chooser_xlsx.setFileFilter(this.filter_xlsx);
    
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
    JLabel label = new JLabel("Escolha uma da opções:");
    panel.add(label); 
    panel.setLayout(new GridLayout(0, 1));
    this.cboxButtonGroup = new ButtonGroup();
    button1 = new JRadioButton("DAT");
    button1.setSelected(true);
    button2 = new JRadioButton("XLSX");
    //button3 = new JRadioButton("Importar dados de média ja calculados.");
    this.cboxButtonGroup.add(button1);
    this.cboxButtonGroup.add(button2);
    //this.cboxButtonGroup.add(button3);
    panel.add(button1);
    panel.add(button2);
    //panel.add(button3);
    this.panelData.add(panel);

  }


  private void formatPanelButtons() {
    this.panelButtons = new JPanel();
      this.panelButtons.setBorder(new EtchedBorder());
      this.panelButtons.setBounds(310, 10, 110, 255);
      this.panelButtons.setLayout(null);
    this.add(this.panelButtons);
          
      this.btnExecute = new JButton("Open");
      this.btnExecute.setToolTipText("Open a selected file that will be used to trend analysis");
      this.btnExecute.setBounds(10, 10, 90, 25);
      this.btnExecute.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        buttonAction(btnExecute);
      }
    });
      this.panelButtons.add(this.btnExecute, JLayeredPane.DEFAULT_LAYER);
      
      this.btnCancel = new JButton("Add");
      this.btnCancel.setToolTipText("Add the series from the selected file to the study");
      this.btnCancel.setBounds(10, 40, 90, 25);
      this.btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        buttonAction(btnCancel);
      }
    });
      this.panelButtons.add(this.btnCancel, JLayeredPane.DEFAULT_LAYER);
    }
  
  private void buttonAction(JButton jb){
    if (jb.equals(this.btnExecute)){
    this.AbrirNovosDados();
    
    
    }else if (jb.equals(this.btnCancel)){
    this.AdicionarDados();
    //this.setVisible(false);
    
    }
    
    this.setVisible(false);
  }
  
  
  
  
public void AdicionarDados()  {
    
  if(this.button1.isSelected()){  
  String dir = null;
  String filename = null;
  
    //Messages.informMsg("Indique o arquivo .dat");
    int returnVal = this.chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
    dir = this.chooser.getCurrentDirectory().getAbsolutePath() + "\\";
    filename = this.chooser.getSelectedFile().getName();
    }
    
    
    this.simulationData.setFilenameBD(filename);
    this.simulationData.setDataDirBD(dir);  
    
     ArrayList<VariavelHidrologica> variaveishidrologicasExistentes=new ArrayList<VariavelHidrologica>();
    if(!(this.simulationData.getVariaveisHidr() == null)){
          
      for (int k = 0; k < this.simulationData.getVariaveisHidr().size(); k++){
        
        if(this.simulationData.getVariaveisHidr().get(k).isSelecionada()) {
        variaveishidrologicasExistentes.add(this.simulationData.getVariaveisHidr().get(k));
        }
        
        
        }
     }
      
    if(!(this.pnd == null)){
    //MARDaily_FACADE mardaily = MARDaily_FACADE.getInstance();
    //mardaily.leituraDATVarHid(this.simulationData, this.pnd,variaveishidrologicasExistentes);
    	MAR_ImportDataDAO_TrendBrazil.leituraDATVarHid(simulationData, pnd,variaveishidrologicasExistentes);
    }
    
    
  }else if(this.button2.isSelected()){
    String dir = null;
    String filename = null;
    
      //Messages.informMsg("Indique o arquivo .dat");
    int returnVal = this.chooser_xlsx.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
    dir = this.chooser_xlsx.getCurrentDirectory().getAbsolutePath() + "\\";
    filename = this.chooser_xlsx.getSelectedFile().getName();
    }
      
      
      this.simulationData.setFilenameBD(filename);
      this.simulationData.setDataDirBD(dir);  
      
       ArrayList<VariavelHidrologica> variaveishidrologicasExistentes=new ArrayList<VariavelHidrologica>();
      if(!(this.simulationData.getVariaveisHidr() == null)){
            
        for (int k = 0; k < this.simulationData.getVariaveisHidr().size(); k++){    
          if(this.simulationData.getVariaveisHidr().get(k).isSelecionada()) {
          variaveishidrologicasExistentes.add(this.simulationData.getVariaveisHidr().get(k));
          }
        }
       }
          
     // MARDaily_FACADE mardaily = MARDaily_FACADE.getInstance();
      if(!(this.pnd == null)){
      //mardaily.leituraXLSVarHid(this.simulationData, this.pnd,variaveishidrologicasExistentes);
      }
      
      
  }
  
  
      
  }
  
public void AbrirNovosDados()  {
  
  
  String dir = null;
  String filename = null;
  
  
    //Messages.informMsg("Indique o arquivo .dat");
    
        
    
    if(this.button1.isSelected()){
      
      int returnVal = this.chooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
      dir = this.chooser.getCurrentDirectory().getAbsolutePath() + "\\";
      filename = this.chooser.getSelectedFile().getName();
      }
      
      
      this.simulationData.setFilenameBD(filename);
      this.simulationData.setDataDirBD(dir);  
      
      
      if(!(this.simulationData.getVariaveisHidr() == null)){
      this.simulationData.setVariaveisHidr(null); 
      }
      
     // MARDaily_FACADE mardaily = MARDaily_FACADE.getInstance();
      
      if(!(this.pnd == null)){
      //mardaily.leituraDATVarHid(simulationData, this.pnd);
    	  MAR_ImportDataDAO_TrendBrazil.leituraDATVarHid(simulationData, this.pnd);
      }
      
      
      
    }else if(this.button2.isSelected()){
      
      int returnVal = this.chooser_xlsx.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
      dir = this.chooser_xlsx.getCurrentDirectory().getAbsolutePath() + "\\";
      filename = this.chooser_xlsx.getSelectedFile().getName();
      }
      
      
      this.simulationData.setFilenameBD(filename);
      this.simulationData.setDataDirBD(dir);  
      
      
      if(!(this.simulationData.getVariaveisHidr() == null)){
      this.simulationData.setVariaveisHidr(null); 
      }
      
      
      //MARDaily_FACADE mardaily = MARDaily_FACADE.getInstance();
      //mardaily.leituraXLSVarHid(simulationData, this.pnd);
    }
    
    
  
  
      
}

@Override
public void propertyChange(PropertyChangeEvent arg0) {
	// TODO Auto-generated method stub
	
}

}
