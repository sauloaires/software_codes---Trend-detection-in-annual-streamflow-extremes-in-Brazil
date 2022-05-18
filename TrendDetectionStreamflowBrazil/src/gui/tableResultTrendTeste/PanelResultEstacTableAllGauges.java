package gui.tableResultTrendTeste;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

import types.ResultEstacionaridade;
import types.SimulationDataExtremos;
import util.ExcelAdapter;

public class PanelResultEstacTableAllGauges extends JPanel{

	private SimulationDataExtremos simulationData;
   private static final long serialVersionUID = 1L;
	
	private JDesktopPane panelButtons;
	private JTable tblDailyData;
	private JButton btnChart;
	private JButton btnClose;
	private JFileChooser chooser;
	//ArrayList<ResultEstacionaridade> resultestacionaridade;
	
	
	public PanelResultEstacTableAllGauges(SimulationDataExtremos simulationData){
		this.simulationData = simulationData;
	//	this.resultestacionaridade=resultestacionaridade;
		   this.createAndShowGUI(); 
	}
	
	private void createAndShowGUI() {
		this.setBounds(100, 200, 900, 270);
		this.setBackground(Color.LIGHT_GRAY);
	  	this.setLayout(null);
		this.insertDataIntoTable();
    	
		//this.pack(); 
    	
    	
    	
	}
	
	private void insertDataIntoTable() {
				
		
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
	
		
		
		Object[][] data = this.setDailyData();
		
		this.tblDailyData = new JTable(data, columnNames);
		ExcelAdapter exetbl=new ExcelAdapter(this.tblDailyData);
       for(int i = 0; i < data.length; i++){
			for(int j = 0; j < data[i].length; j++ ){
			    	if(data[i][j] != null){
					String valor = data[i][j].toString();
					//int colunaCerta = j - 1;
					int colunaCerta = j;
					//String coluna = Integer.toString(colunaCerta);
					if(j == 4){
						if(valor != null ){
							String coluna ="Result";
							RenderizaCelulaTableResultEstac celula = new RenderizaCelulaTableResultEstac(coluna);
							this.tblDailyData.getColumn(this.tblDailyData.getColumnName(j)).setCellRenderer(celula);
						}
					}else {
						String coluna =columnNames[j];
						RenderizaCelulaTableResultEstac celula = new RenderizaCelulaTableResultEstac(coluna);
						this.tblDailyData.getColumn(this.tblDailyData.getColumnName(j)).setCellRenderer(celula);
					}

				}
			}
			
		}
		
		
       this.tblDailyData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn colYear = this.tblDailyData.getColumnModel().getColumn(0);
		colYear.setPreferredWidth(180);
		for (int i =1; i < 11 ; i++){
			
			TableColumn col = this.tblDailyData.getColumnModel().getColumn(i);
			if(i == 2 ||i==3 || i==5){
				col.setPreferredWidth(60);
			}else{
				col.setPreferredWidth(100);	
			}
			
		}
		this.tblDailyData.setPreferredScrollableViewportSize(new Dimension(900, 270));
		JScrollPane scrollPane = new JScrollPane(this.tblDailyData);
		scrollPane.setBounds(20, 20, 900, 270);
		scrollPane.setPreferredSize(new Dimension(900, 270));
		Border border = BorderFactory.createBevelBorder(2);
		scrollPane.setBorder(border);
		this.add(scrollPane);
  		
	}

	private Object[][] setDailyData() {
		//int ntestes=14;
		int ntestes=1;
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
		
		ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
		ArrayList<String> codigos =new ArrayList<String>();
		for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
	   		if(this.simulationData.getVariaveisHidr().get(i).isSelecionada() && this.simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin())	{		
	   	   	    String codigo=this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo();
	   	   	    codigos.add(codigo);
	   	   	    resultestacionaridade.add(this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(0));
	   		}
		}
		
		
		
		
		Object[][] result = new Object[resultestacionaridade.size()][11];
		
		for (int i = 0; i < resultestacionaridade.size(); i++){
			//result[i][0]="gradual change(trend)";
			result[i][0]=codigos.get(i);
			/*if(i<3){
			result[i][0]="Mudança gradual (Tendência)";
			}else if(i>=3 && i<7){
			result[i][0]="Mudança brusca (Média)";
			}else if(i==7){
			result[i][0]="Mudança brusca (Mediana)";
			}else if(i==8){
			result[i][0]="Mudança brusca (Variância)";	
			}else{
			result[i][0]="Teste de Independência";	
			}*/
			
			DecimalFormatSymbols dc = new DecimalFormatSymbols();
			dc.setDecimalSeparator('.');
			String strange = "0.00";
			DecimalFormat myFormatter = new DecimalFormat(strange, dc);		
			//	for(int j=0;j<this.resultestacionaridade.size();j++){
					result[i][1]=nometesteExtenso[0];
					//if(this.resultestacionaridade.get(j).getNometeste().equals(nometeste[i])){
						result[i][2]=myFormatter.format(resultestacionaridade.get(i).getEstatteste());
						result[i][3]=myFormatter.format(resultestacionaridade.get(i).getPvalue());
						result[i][4]=resultestacionaridade.get(i).getMetodoObterValCritico();
						result[i][5]=myFormatter.format(resultestacionaridade.get(i).getValorcriticoteste());
						result[i][6]=resultestacionaridade.get(i).getResultadoteste();
						result[i][7]=resultestacionaridade.get(i).getResultadoDescritivoTeste();
						
						//if(i<3){
							result[i][8]=resultestacionaridade.get(i).getSentidoTendencia();
							result[i][9]="";
							result[i][10]="";
							//}else if(i>=3 && i<9){
							//result[i][8]="";
							//result[i][9]=String.valueOf(this.resultestacionaridade.get(j).getAnoMudanca());
						//	result[i][10]=this.resultestacionaridade.get(j).getSentidoMediaRecente();
						//	}else {
						//	result[i][8]="";
						//	result[i][9]="";
						//	result[i][10]="";
						//	}
						
					//}
						
				//}
				
			
			
			
			
			
		}
		
			
		return result;
	}


}
