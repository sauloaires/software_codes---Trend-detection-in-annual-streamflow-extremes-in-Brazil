package io;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import gui.PanelTrendDetectionStreamflowBrazil;
import types.ResultEstacionaridade;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;
import util.Messages;
/*import org.snirh.extremos_unb.deteccao.gui.PanelTestesEstatisticos;
import org.snirh.extremos_unb.deteccao.gui.PanelEscolherArquivoExcelExportarResultado.executarSalvarExcel;
import org.snirh.extremos_unb.deteccao.io.ExportarResultadoTabelaResumoBsen;
import org.snirh.extremos_unb.deteccao.testes.ResultEstacionaridade;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.extremos_unb.tipos.VariavelHidrologica;
import org.snirh.extremos_unb.util.ExtensionFileFilter;
import org.snirh.extremos_unb.util.Messages;*/

public class PanelEscolherArquivoExcelExportarResultado extends JFrame{

	
	
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
	private JRadioButton button4;
	private JRadioButton button5;
	private JRadioButton button6;
	
	private SimulationDataExtremos simulationData;
	private PanelTrendDetectionStreamflowBrazil pnt;
	private ExtensionFileFilter filter;
	
	private JFileChooser chooser_xlsx;
	private ExtensionFileFilter filter_xlsx;
	
	
	public PanelEscolherArquivoExcelExportarResultado(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt){
		super("Indique o tipo de Arquivo a ser exportado");
		this.simulationData = simulationData;
		this.pnt=pnt;
		this.createAndShowGUI();
		this.createPane();
		this.pack();
		
	}
	
	
	
	private void createPane() {
    	this.chooser = new JFileChooser(new File("."));
    	this.filter = new ExtensionFileFilter("dat", "Arquivos de dados hidrológicos (*.dat)");
		this.chooser.setFileFilter(this.filter);
		
		
		this.chooser_xlsx = new JFileChooser(new File("."));
    	this.filter_xlsx = new ExtensionFileFilter("xlsx", "Arquivos de dados hidrológicos (*.xlsx)");
		this.chooser_xlsx.setFileFilter(this.filter_xlsx);
		
	}
	
	
	private void createAndShowGUI() {
		this.setBounds(20, 20, 440, 320);
		this.setPreferredSize(new Dimension(440, 320));
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
    	this.panelData.setBounds(10, 10, 290, 280);
    	this.panelData.setLayout(null);
		this.add(this.panelData);
		
		this.formatButtonGroup();
		//this.formatLabels();
	}

	private void formatButtonGroup() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 265, 260);
		JLabel label = new JLabel("Choose one of the options:");
		panel.add(label);	
		panel.setLayout(new GridLayout(0, 1));
		this.cboxButtonGroup = new ButtonGroup();
		//button1 = new JRadioButton("DAT - Máximos");
		String nomeEstat=this.simulationData.getTipoEstatisticaSelecionadaEstacionaridade();
		button1 = new JRadioButton("Trend Result");
		button1.setSelected(true);
		//button2 = new JRadioButton("Excel - Máximos");
		button2 = new JRadioButton("bsen and pvalue Table");
		
		//button3 = new JRadioButton("DAT - Original");
		//button4 = new JRadioButton("Excel - Original");
		//button5 = new JRadioButton("DAT - Original - Filtro");
		//button6 = new JRadioButton("DAT - Original- Total - Filtro");
		
		this.cboxButtonGroup.add(button1);
		this.cboxButtonGroup.add(button2);
		//this.cboxButtonGroup.add(button3);
		//this.cboxButtonGroup.add(button4);
		//this.cboxButtonGroup.add(button5);
		//this.cboxButtonGroup.add(button6);
		
		panel.add(button1);
		panel.add(button2);
		//panel.add(button3);
		//panel.add(button4);
		//panel.add(button5);
		//panel.add(button6);
		
		this.panelData.add(panel);

	}


	private void formatPanelButtons() {
		this.panelButtons = new JPanel();
    	this.panelButtons.setBorder(new EtchedBorder());
    	this.panelButtons.setBounds(310, 10, 110, 280);
    	this.panelButtons.setLayout(null);
		this.add(this.panelButtons);
		    	
    	this.btnExecute = new JButton("Export");
    	String nomeEstat=this.simulationData.getTipoEstatisticaSelecionadaEstacionaridade();
    	this.btnExecute.setToolTipText("Open a selected file that will be used to obtain series of "+ nomeEstat);
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
		
		this.exportarDados();
		
		
		}else if (jb.equals(this.btnCancel)){
		//this.AdicionarDados();
		//this.setVisible(false);
		
		}
		
		this.setVisible(false);
	}
	
	
	
	

	
public void exportarDados()  {
	
	
	String dir = null;
	String filename = null;
	
	
		
		if(this.button1.isSelected()){
		
			executeSaveXLSX_MK boot= new executeSaveXLSX_MK(this.simulationData,this.pnt);
		    boot.addPropertyChangeListener(this.pnt);
			boot.execute();
			
		}else if(this.button2.isSelected()){
						
			ExportarResultadoTabelaResumoBsen exportar=new ExportarResultadoTabelaResumoBsen(this.simulationData,this.pnt);
			exportar.executar();
		
		}
		
	
	
			
}











private void SolicitarArquivosAoUsuário(JFileChooser chooser)  {
    
    String filename=null;
	String dir =null;
	int returnVal = chooser.showOpenDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	dir = chooser.getCurrentDirectory().getAbsolutePath() + "\\";
	filename = chooser.getSelectedFile().getName();
	}
	
	this.simulationData.setDataDirBD(dir);
	this.simulationData.setFilenameBD(filename);


}





class executeSaveXLSX_MK extends SwingWorker<Void, String> {

	private SimulationDataExtremos simulationData;
    private PanelTrendDetectionStreamflowBrazil pnt;    
    private ProgressMonitor progressMonitor;
    private JTextArea taskOutput;
    private JFileChooser chooser;
	private ExtensionFileFilter filter;
	
	
	public executeSaveXLSX_MK (SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt){
		
		 this.simulationData=simulationData;
		 this.pnt=pnt;
		 
	}
	
	
	
	
	@Override
	protected Void doInBackground() throws Exception {
		
		String dir = null;
		String filename = null;
		//if(dir == null){
			Messages.informMsg("Indique o nome do arquivo de resultados .xlsx");
			int returnVal = this.pnt.getChooser().showOpenDialog(this.pnt);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			dir = this.pnt.getChooser().getCurrentDirectory().getAbsolutePath() + "\\";
			filename = this.pnt.getChooser().getSelectedFile().getName();
			}
			
			//this.simulationData.setFilenameBD(filename);
			this.simulationData.setDataDirBD(dir);
			
			String[] columnNames = new String[11];		
			columnNames[0] = "Gauge Code";
			columnNames[1] = "Trend Test";
			columnNames[2] = "Test Statistics";
			columnNames[3] = "Pvalue (%)";
			columnNames[4] = "Critical Value Method";
			columnNames[5] = "Critical Value";
			columnNames[6] = "Result";
			columnNames[7] = "Description Result";
			columnNames[8] = "Trend";
			columnNames[9] = "Year Shift";
			columnNames[10] = "Mean";
						
			int ntestes=1;
						
			String [] nometesteExtenso=new String [ntestes];
			nometesteExtenso[0]="Mann-Kendall";
			/*nometesteExtenso[1]="Spearman’s Rho";
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
			nometesteExtenso[13]="Wald-Wolfowitz";*/
						
			String [] nometeste=new String [ntestes];
			nometeste[0]="MK";
			/*nometeste[1]="SR";
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
			nometeste[13]="WW";*/
			
			
    	    String nomearq=dir+filename+".xlsx";
    	    Workbook wb = new SXSSFWorkbook(100);
    	   
    	    
    	    setProgress(0);
			String textointerface= "Iniciando o Calculo do Resumo dos Resultados das Estações  ";
		    publish(textointerface);
			int progress = 0;
	        setProgress(0);
	        int iestfim=0;
	        Sheet sh = wb.createSheet("MK Trend Result");
	        Row rowHEAD = sh.createRow(0);
	        for(int i1=0;i1<columnNames.length;i1++) {
	        	Cell cell = rowHEAD.createCell(i1);
	        	cell.setCellValue(columnNames[i1]);
	        }
	        
	        int igauge=1;
    	    for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
    	   	    if(this.simulationData.getVariaveisHidr().get(i).isSelecionada() && this.simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin())	{		
    	   	    	
    	   	   	    String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
    		   	    ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
    		   	    resultestacionaridade = this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade();
    		   	    VariavelHidrologica vhid =this.simulationData.getVariaveisHidr().get(i);
    	   	   	    
    	   	   	    	   	   	    
    	   	   	    
    	   	    int nlinhastr=ntestes+1;
    	   	    int ncolstr=columnNames.length;
    			
    			
    			Object[][] result =this.pnt.setDadosResEstac_MK(resultestacionaridade);
    			result[0][0]=codigo;
    			//for(int rownum = 0; rownum <nlinhastr; rownum++){
    	            Row row = sh.createRow(igauge);
    	            for(int cellnum = 0; cellnum < ncolstr; cellnum++){
    	                Cell cell = row.createCell(cellnum);
    	                String address = new CellReference(cell).formatAsString();
    	                
    	                if(igauge !=0){
    	                      
    	                	if(cellnum == 2 || cellnum == 3 || cellnum == 5 || cellnum == 9){
    	                		String teste=String.valueOf(result[0][cellnum]);
    	                		if(teste != ""){
    	                			double val=Double.parseDouble(teste);
        	                		cell.setCellValue(val);
    	                		}else{
    	                			
    	                		}
    	                		
    	                	}else{
    	                		cell.setCellValue(String.valueOf(result[0][cellnum]));
    	                	}
    	                
	    	                
    	                }else{
    	                	cell.setCellValue(columnNames[cellnum]);	
    	                }
    	                
    	            }

    	       // }
    			
    	         igauge++;
    			iestfim++;
    	   	    }
    	    
    	    
    	   	 double progress2 = ((i+1)*1.0/(this.simulationData.getVariaveisHidr().size()*1.0))*100;
		     int ngauges= this.simulationData.getVariaveisHidr().size();
    	   	 progress=(int) progress2;
		      setProgress(Math.min(progress, 100));
		      textointerface= "Aguarde..executando  o calculo da estação  "+(i+1)+"/"+ngauges+"";
		      publish(textointerface);
			  System.out.println(textointerface);
    	   	    
    	   	    
    	   	    
    	    }
    	    
    	    FileOutputStream out = null;
    		try {
    			out = new FileOutputStream(nomearq);
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            try {
    			wb.write(out);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            try {
    			out.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	    
            textointerface= "arquivo excel dos resultados da(s) "+iestfim+" estação(ões) efetuado com sucesso";
            publish(textointerface);
    	    // keep 100 rows in memory, exceeding rows will be flushed to disk
	        
            //Messages.informMsg("Arquivo excel criado com sucesso");
			
		    
		return null;
		
	}


	protected void process(List<String> text) {
	    
    	 //this.pnt.lblAguardeThread.setText(text.get(0));
    
     }
    
     protected void done() {
     
    	 Messages.informMsg("XLSX file exported successfully");
     
     } 
}

















class executarSalvarExcel extends SwingWorker<Void, String> {

	private SimulationDataExtremos simulationData;
    private PanelTrendDetectionStreamflowBrazil pnt;    
    private ProgressMonitor progressMonitor;
    private JTextArea taskOutput;
    private JFileChooser chooser;
	private ExtensionFileFilter filter;
	
	
	public executarSalvarExcel (SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt){
		
		 this.simulationData=simulationData;
		 this.pnt=pnt;
		 
	}
	
	
	
	
	@Override
	protected Void doInBackground() throws Exception {
		
		String dir = null;
		String filename = null;
		//if(dir == null){
			Messages.informMsg("Indique o nome do arquivo de resultados .xlsx");
			int returnVal = this.pnt.getChooser().showOpenDialog(this.pnt);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			dir = this.pnt.getChooser().getCurrentDirectory().getAbsolutePath() + "\\";
			filename = this.pnt.getChooser().getSelectedFile().getName();
			}
			
			this.simulationData.setFilenameBD(filename);
			this.simulationData.setDataDirBD(dir);
			
			String[] columnNames = new String[11];		
			columnNames[0] = "Tipo de Teste";
			columnNames[1] = "Teste Estatístico";
			columnNames[2] = "Estatística do Teste";
			columnNames[3] = "Pvalue (%)";
			columnNames[4] = "Método Valor Critico";
			columnNames[5] = "Valor Critico";
			columnNames[6] = "Resultado";
			columnNames[7] = "Descrição Resultado";
			columnNames[8] = "Tendencia";
			columnNames[9] = "Ano Divisão";
			columnNames[10] = "Média Pós-AnoDivisão";
						
			int ntestes=14;
						
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
			
			
    	    String nomearq=dir+filename+".xlsx";
    	    Workbook wb = new SXSSFWorkbook(100);
    	    
    	    
    	    setProgress(0);
			String textointerface= "Iniciando o Calculo do Resumo dos Resultados das Estações  ";
		    publish(textointerface);
			int progress = 0;
	        setProgress(0);
	        int iestfim=0;
    	    for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
    	   	    if(this.simulationData.getVariaveisHidr().get(i).isSelecionada() && this.simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin())	{		
    	   	    	
    	   	   	    String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
    		   	    ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
    		   	    resultestacionaridade = this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade();
    		   	    VariavelHidrologica vhid =this.simulationData.getVariaveisHidr().get(i);
    	   	   	    
    	   	   	    	   	   	    
    	   	   	    
    	   	    int nlinhastr=ntestes+1;
    	   	    int ncolstr=columnNames.length;
    			Sheet sh = wb.createSheet(codigo);
    			
    			Object[][] result =this.pnt.setDadosResEstac(resultestacionaridade);
    			
    			for(int rownum = 0; rownum <nlinhastr; rownum++){
    	            Row row = sh.createRow(rownum);
    	            for(int cellnum = 0; cellnum < ncolstr; cellnum++){
    	                Cell cell = row.createCell(cellnum);
    	                String address = new CellReference(cell).formatAsString();
    	                
    	                if(rownum !=0){
    	                      
    	                	if(cellnum == 2 || cellnum == 3 || cellnum == 5 || cellnum == 9){
    	                		String teste=String.valueOf(result[rownum-1][cellnum]);
    	                		if(teste != ""){
    	                			double val=Double.parseDouble(teste);
        	                		cell.setCellValue(val);
    	                		}else{
    	                			
    	                		}
    	                		
    	                	}else{
    	                		cell.setCellValue(String.valueOf(result[rownum-1][cellnum]));
    	                	}
    	                		
    	                	    	                	
	    	               /* if(cellnum == 0){
	    	                cell.setCellValue(String.valueOf(result[rownum-1][0]));	
	    	                }else if (cellnum == 1){
	    	                	cell.setCellValue(String.valueOf(result[rownum-1][0]));	
	    	                }else if (cellnum == 2){
	    	                	cell.setCellValue(result[rownum-1][2]);
	    	                }else if (cellnum == 3){
	    	                	cell.setCellValue(result[rownum-1][3]);
	    	                }else if (cellnum == 4){
	    	                	cell.setCellValue(result[rownum-1][3]);
	    	                }*/
	    	             
	    	                
    	                }else{
    	                	cell.setCellValue(columnNames[cellnum]);	
    	                }
    	                
    	            }

    	        }
    			
    			
    			iestfim++;
    	   	    }
    	    
    	    
    	   	 double progress2 = ((i+1)*1.0/(this.simulationData.getVariaveisHidr().size()*1.0))*100;
		     int ngauges= this.simulationData.getVariaveisHidr().size();
    	   	 progress=(int) progress2;
		      setProgress(Math.min(progress, 100));
		      textointerface= "Aguarde..executando  o calculo da estação  "+(i+1)+"/"+ngauges+"";
		      publish(textointerface);
			  System.out.println(textointerface);
    	   	    
    	   	    
    	   	    
    	    }
    	    
    	    FileOutputStream out = null;
    		try {
    			out = new FileOutputStream(nomearq);
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            try {
    			wb.write(out);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            try {
    			out.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	    
            textointerface= "arquivo excel dos resultados da(s) "+iestfim+" estação(ões) efetuado com sucesso";
            publish(textointerface);
    	    // keep 100 rows in memory, exceeding rows will be flushed to disk
	        
            //Messages.informMsg("Arquivo excel criado com sucesso");
			
		    
		return null;
		
	}


	protected void process(List<String> text) {
	    
    	 //this.pnt.lblAguardeThread.setText(text.get(0));
    
     }
    
     protected void done() {
     
    	 Messages.informMsg("Arquivo excel construido com sucesso");
     
     } 
}

}
