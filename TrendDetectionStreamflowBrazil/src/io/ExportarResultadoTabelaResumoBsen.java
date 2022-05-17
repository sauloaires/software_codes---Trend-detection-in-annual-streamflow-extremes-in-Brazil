package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
//import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import gui.PanelTrendDetectionStreamflowBrazil;
import types.InventarioHidrologico;
import types.ResultEstacionaridade;
import types.SimulationDataExtremos;
import util.Messages;
/*import org.snirh.extremos_unb.deteccao.gui.PanelTestesEstatisticos;
import org.snirh.extremos_unb.deteccao.io.ExportarResultadoTabelaResumoBsenTamanho;
import org.snirh.extremos_unb.deteccao.testes.ResultEstacionaridade;
import org.snirh.extremos_unb.tipos.InventarioHidrologico;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.extremos_unb.util.Messages;
import org.snirh.mar.lumped.dailymodel.filtrosHIDROPG.FiltroEstacoesTotalHIDROPG;
import org.snirh.util.ExtensionFileFilter;*/

public class ExportarResultadoTabelaResumoBsen {
	private JFileChooser chooser_xls;
	private ExtensionFileFilter filter_xls;
	private SimulationDataExtremos simulationData;
   private PanelTrendDetectionStreamflowBrazil pnt; 
	
	public ExportarResultadoTabelaResumoBsen(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt){
		this.simulationData=simulationData;
		this.pnt=pnt;
		this.createFileChooser();
		
	}
	
	
	
	private void createFileChooser(){
		
		this.chooser_xls = new JFileChooser(new File("."));
	    this.filter_xls = new ExtensionFileFilter("xls", "Arquivos EXCEL97 (*.xls)");
		this.chooser_xls.setFileFilter(this.filter_xls);
		
		
	}
	
	
	public void selecionarArquivoTemplateXls(){
		int returnVal = this.chooser_xls.showOpenDialog(this.pnt);
		String dir="";
		String filename ="";
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		dir = this.chooser_xls.getCurrentDirectory().getAbsolutePath() + "\\";
		filename = this.chooser_xls.getSelectedFile().getName();
		}
		
		this.simulationData.setDirTemplateXls(dir);
		this.simulationData.setFilenameTemplateXls(filename);

	}
	
	
	public void selecionarDiretorioTemplateXlx(){
		
		JFileChooser fc = new JFileChooser();  
 	     // restringe a amostra a diretorios apenas  
       fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
        int res = fc.showOpenDialog(null);  
       if(res == JFileChooser.APPROVE_OPTION){ 
   	 File diretorio = fc.getSelectedFile();  
        //JOptionPane.showMessageDialog(null, "Voce escolheu o diretório: " + diretorio.getName()); 
        //txtDiretorioArquivoTodosTemplate.setText(diretorio.getAbsolutePath()+ "\\");
        simulationData.setDirTodosTemplateXls(diretorio.getAbsolutePath()+ "\\");
      }  
     else  
           JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");
	}
	
	
	public String [] pegarNomeTeste(){
		
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
							
		return nometesteExtenso;
	}
	
	
public String [] pegarSiglaTeste(){
		
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
		return nometeste;
	}
	

//C:\OpenJump150\PROJETO E IMPLEMENTACOES\EXTREMOS_UNB\resultados\templates

    public void executar(){
    	
    	this.selecionarArquivoTemplateXls();
   	    String dirTemplate=simulationData.getDirTemplateXls();
        String nomearq=simulationData.getFilenameTemplateXls();
        String nomearqOriginal=simulationData.getFilenameBD();
        if(nomearq.contains(".xls") == false){
        nomearq=nomearq+".xls";	
        }
    	
          String [] nometesteTotal=this.pegarSiglaTeste();
           ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
   	       resultestacionaridade = this.simulationData.getVariaveisHidr().get(0).getResultestacionaridade();
   	       String [] nometeste=new String [resultestacionaridade.size()];
		   	 for(int j=0;j<resultestacionaridade.size();j++){
		   		nometeste[j]=resultestacionaridade.get(j).getNometeste();
		   	 }
   	    
		   	// Map<String,Double> estacAlt=FiltroEstacoesTotalHIDROPG.pegarAltitude(); 
		   	Map<String,Double> estacAlt=new HashMap<String,Double>();
        Map<String,Map<String,ResultEstacionaridade>> resultEstacTesteEstacao =new HashMap<String,Map<String,ResultEstacionaridade>>();
        Map<String,InventarioHidrologico> inventario=new HashMap<String,InventarioHidrologico>();
        String nomeIndice="";
        for(int j=0;j<nometeste.length;j++){
        
		        for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
			   	    
		        	if(this.simulationData.getVariaveisHidr().get(i).isSelecionada() && this.simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin()){
		        		
					   	   	String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
					   	   	if(j == 0){
					   	    inventario.put(codigo, this.simulationData.getVariaveisHidr().get(i).getInvhidro());
					   	    nomeIndice=this.simulationData.getVariaveisHidr().get(i).getInvhidro().getDescricaoOrigemSerie();
					   	   	}
					   	   	if(resultEstacTesteEstacao.containsKey(nometeste[j])){
					   	   	resultEstacTesteEstacao.get(nometeste[j]).put(codigo, this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j));
					   	   	}else{
					   	   	Map<String,ResultEstacionaridade> resestac=new HashMap<String,ResultEstacionaridade>();	
					   	    resultEstacTesteEstacao.put(nometeste[j], resestac);
					   	    resultEstacTesteEstacao.get(nometeste[j]).put(codigo, this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j));
					   	   	}
			   	     }
			   	    
		        }
        
        }
        
        String dirOutput="C:\\OpenJump150\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\";
        String nomePlan="TabelaIndice";
        for(int j=0;j<nometeste.length;j++){
        	        	
        	String nmteste=nometeste[j];
        	
        	if(nmteste.equals("MK")){
        	//HSSFWorkbook wb;
			try {
				HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(dirTemplate+nomearq));
				HSSFSheet sh = wb.getSheet(nomePlan);
				//Row row = sh.getRow(0);
				//Cell cellNome = row.createCell(1);
				//cellNome.setCellValue(nomearqOriginal);
				 FileOutputStream stream = new FileOutputStream(dirOutput+nmteste+"_"+nomeIndice+".xls");
				 Set<String> chaves = resultEstacTesteEstacao.get(nmteste).keySet();
				 int nlinhasIniserie=3;
				 int i1=0;
				 DescriptiveStatistics dscBsen =new  DescriptiveStatistics();
				 DescriptiveStatistics dscBsenRel = new  DescriptiveStatistics();
				 DescriptiveStatistics dscAlt = new  DescriptiveStatistics();
				 //double ns=5.0;
				  	for (String codigo : chaves){
				  	
				  		if(i1 == 0){
				  			Row row = sh.getRow(0);
							Cell cellNome = row.createCell(1);
							cellNome.setCellValue(String.valueOf(inventario.get(codigo).getDescricaoOrigemSerie()));
							//ns=resultEstacTesteEstacao.get(nmteste).get(codigo).g
				  		}
				  		
				  		
				  		Row rowLinhaEst = sh.createRow(nlinhasIniserie+i1);
						Cell cell = rowLinhaEst.createCell(0);
						cell.setCellValue(String.valueOf(codigo));
				  		cell = rowLinhaEst.createCell(1);
						cell.setCellValue((Double) inventario.get(codigo).getLatitude());
						cell = rowLinhaEst.createCell(2);
						cell.setCellValue((Double) inventario.get(codigo).getLongitude());
						cell = rowLinhaEst.createCell(3);
						
						if (estacAlt.containsKey(codigo)){
						double altitude=estacAlt.get(codigo);
						if(!(altitude < 0)){
						cell.setCellValue((Double) estacAlt.get(codigo));
					    dscAlt.addValue((Double) estacAlt.get(codigo));
						}
						}else{
							cell.setCellValue(0.0);
							dscAlt.addValue(0.0);
						}
						cell = rowLinhaEst.createCell(4);
						cell.setCellValue((Double) inventario.get(codigo).getAreaDrenagem());
						cell = rowLinhaEst.createCell(5);
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getPvalue());
						cell = rowLinhaEst.createCell(6);
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getBsen());
						cell = rowLinhaEst.createCell(7);
						dscBsen.addValue(resultEstacTesteEstacao.get(nmteste).get(codigo).getBsen());
						
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getBsenRel());
						cell = rowLinhaEst.createCell(8);
						dscBsenRel.addValue(resultEstacTesteEstacao.get(nmteste).get(codigo).getBsenRel());
												
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getNanos());
						cell = rowLinhaEst.createCell(9);
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getNanosPeriodo());
						cell = rowLinhaEst.createCell(10);
						cell.setCellValue(String.valueOf(resultEstacTesteEstacao.get(nmteste).get(codigo).getSentidoTendencia()));
				  		
						i1++;	
				  	}
				
				  	double ns=5.0;
				  	int nlinha=nlinhasIniserie;
				  	for(int k=0;k<2;k++){
				  	
				  	Row row = sh.getRow(nlinha);
					Cell cellNome = row.createCell(15);
					cellNome.setCellValue((Double) ns*-1.0);
					cellNome = row.createCell(16);
					cellNome.setCellValue((Double) dscBsen.getMin());
					cellNome = row.createCell(17);
					cellNome.setCellValue((Double) dscBsenRel.getMin());
					cellNome = row.createCell(18);
					cellNome.setCellValue((Double) dscAlt.getMin());
					
					row = sh.getRow(nlinha+1);
					cellNome = row.createCell(15);
					cellNome.setCellValue((Double) ns*-1.0);
					cellNome = row.createCell(16);
					cellNome.setCellValue((Double) dscBsen.getMax());
					cellNome = row.createCell(17);
					cellNome.setCellValue((Double) dscBsenRel.getMax());
					cellNome = row.createCell(18);
					cellNome.setCellValue((Double) dscAlt.getMax());
					
					
					row = sh.getRow(nlinha);
					cellNome = row.createCell(20);
					cellNome.setCellValue((Double) ns*1.0);
					cellNome = row.createCell(21);
					cellNome.setCellValue((Double) dscBsen.getMin());
					cellNome = row.createCell(22);
					cellNome.setCellValue((Double) dscBsenRel.getMin());
					cellNome = row.createCell(23);
					cellNome.setCellValue((Double) dscAlt.getMin());
					
					row = sh.getRow(nlinha+1);
					cellNome = row.createCell(20);
					cellNome.setCellValue((Double) ns*1.0);
					cellNome = row.createCell(21);
					cellNome.setCellValue((Double) dscBsen.getMax());
					cellNome = row.createCell(22);
					cellNome.setCellValue((Double) dscBsenRel.getMax());
					cellNome = row.createCell(23);
					cellNome.setCellValue((Double) dscAlt.getMax());
					ns=ns+5.0;
					nlinha=nlinha+2;
				  	}
					
					
					
					
				  	wb.write(stream);
					stream.close();
					
					
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
        	
			
        	}
        	
        	
        	
        	
        }
        
        
        Messages.informMsg("Executado com sucesso");
        System.out.println("");
	}
	
	
    
    
    
public void executar(String dirTemplate,String nomearq,String dirOutput){
    	
    	//this.selecionarArquivoTemplateXls();
   	    //String dirTemplate=simulationData.getDirTemplateXls();
       // String nomearq=simulationData.getFilenameTemplateXls();
        String nomearqOriginal=simulationData.getFilenameBD();
        if(nomearq.contains(".xls") == false){
        nomearq=nomearq+".xls";	
        }
    	
          String [] nometesteTotal=this.pegarSiglaTeste();
           ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
           int k1=0;
           while(this.simulationData.getVariaveisHidr().get(k1).getResultestacionaridade().size() <= 0){
        	   k1++;
           }
          
           
   	       resultestacionaridade = this.simulationData.getVariaveisHidr().get(k1).getResultestacionaridade();
   	       String [] nometeste=new String [resultestacionaridade.size()];
		   	 for(int j=0;j<resultestacionaridade.size();j++){
		   		nometeste[j]=resultestacionaridade.get(j).getNometeste();
		   	 }
		 	Map<String,Double> estacAlt=new HashMap<String,Double>();
		   	// Map<String,Double> estacAlt=FiltroEstacoesTotalHIDROPG.pegarAltitude(); 
        Map<String,Map<String,ResultEstacionaridade>> resultEstacTesteEstacao =new HashMap<String,Map<String,ResultEstacionaridade>>();
        Map<String,InventarioHidrologico> inventario=new HashMap<String,InventarioHidrologico>();
        String nomeIndice="";
        for(int j=0;j<nometeste.length;j++){
        
		        for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
			   	    
		        	if(this.simulationData.getVariaveisHidr().get(i).isSelecionada() && this.simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin()){
		        		
					   	   	String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
					   	   	if(j == 0){
					   	    inventario.put(codigo, this.simulationData.getVariaveisHidr().get(i).getInvhidro());
					   	    nomeIndice=this.simulationData.getVariaveisHidr().get(i).getInvhidro().getDescricaoOrigemSerie();
					   	   	}
					   	   	if(resultEstacTesteEstacao.containsKey(nometeste[j])){
					   	   	resultEstacTesteEstacao.get(nometeste[j]).put(codigo, this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j));
					   	   	}else{
					   	   	Map<String,ResultEstacionaridade> resestac=new HashMap<String,ResultEstacionaridade>();	
					   	    resultEstacTesteEstacao.put(nometeste[j], resestac);
					   	    resultEstacTesteEstacao.get(nometeste[j]).put(codigo, this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j));
					   	   	}
			   	     }
			   	    
		        }
        
        }
        
        //String dirOutput="C:\\OpenJump150\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\";
        String nomePlan="TabelaIndice";
        for(int j=0;j<nometeste.length;j++){
        	        	
        	String nmteste=nometeste[j];
        	
        	if(nmteste.equals("MK")){
        	//HSSFWorkbook wb;
			try {
				HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(dirTemplate+nomearq));
				HSSFSheet sh = wb.getSheet(nomePlan);
				//Row row = sh.getRow(0);
				//Cell cellNome = row.createCell(1);
				//cellNome.setCellValue(nomearqOriginal);
				 FileOutputStream stream = new FileOutputStream(dirOutput+nmteste+"_"+nomeIndice+".xls");
				 Set<String> chaves = resultEstacTesteEstacao.get(nmteste).keySet();
				 
				
				 
				 int nlinhasIniserie=3;
				 int i1=0;
				 DescriptiveStatistics dscBsen = new  DescriptiveStatistics();
				 DescriptiveStatistics dscBsenRel = new  DescriptiveStatistics();
				 DescriptiveStatistics dscAlt = new  DescriptiveStatistics();
				 //double ns=5.0;
				  	for (String codigo : chaves){
				  	
				  		if(i1 == 0){
				  			Row row = sh.getRow(0);
							Cell cellNome = row.createCell(1);
							cellNome.setCellValue(String.valueOf(inventario.get(codigo).getDescricaoOrigemSerie()));
							//ns=resultEstacTesteEstacao.get(nmteste).get(codigo).g
				  		}
				  		
				  		
				  		Row rowLinhaEst = sh.createRow(nlinhasIniserie+i1);
						Cell cell = rowLinhaEst.createCell(0);
						cell.setCellValue(String.valueOf(codigo));
				  		cell = rowLinhaEst.createCell(1);
						cell.setCellValue((Double) inventario.get(codigo).getLatitude());
						cell = rowLinhaEst.createCell(2);
						cell.setCellValue((Double) inventario.get(codigo).getLongitude());
						cell = rowLinhaEst.createCell(3);
						
						if (estacAlt.containsKey(codigo)){
						double altitude=estacAlt.get(codigo);
						if(!(altitude < 0)){
						cell.setCellValue((Double) estacAlt.get(codigo));
					    dscAlt.addValue((Double) estacAlt.get(codigo));
						}
						}else{
							cell.setCellValue(0.0);
							dscAlt.addValue(0.0);
						}
						cell = rowLinhaEst.createCell(4);
						cell.setCellValue((Double) inventario.get(codigo).getAreaDrenagem());
						cell = rowLinhaEst.createCell(5);
            cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getEstatteste());
						cell = rowLinhaEst.createCell(6);
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getPvalue());
						cell = rowLinhaEst.createCell(7);
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getBsen());
						cell = rowLinhaEst.createCell(8);
						dscBsen.addValue(resultEstacTesteEstacao.get(nmteste).get(codigo).getBsen());
						
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getBsenRel());
						cell = rowLinhaEst.createCell(9);
						dscBsenRel.addValue(resultEstacTesteEstacao.get(nmteste).get(codigo).getBsenRel());
												
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getNanos());
						cell = rowLinhaEst.createCell(10);
						cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getNanosPeriodo());
						cell = rowLinhaEst.createCell(11);
						cell.setCellValue(String.valueOf(resultEstacTesteEstacao.get(nmteste).get(codigo).getSentidoTendencia()));
				  		
						i1++;	
				  	}
				
				  	double ns=5.0;
				  	int nlinha=nlinhasIniserie;
				  	for(int k=0;k<2;k++){
				  	
				  	Row row = sh.getRow(nlinha);
					Cell cellNome = row.createCell(15);
					cellNome.setCellValue((Double) ns*-1.0);
					cellNome = row.createCell(16);
					cellNome.setCellValue((Double) dscBsen.getMin());
					cellNome = row.createCell(17);
					cellNome.setCellValue((Double) dscBsenRel.getMin());
					cellNome = row.createCell(18);
					cellNome.setCellValue((Double) dscAlt.getMin());
					
					row = sh.getRow(nlinha+1);
					cellNome = row.createCell(15);
					cellNome.setCellValue((Double) ns*-1.0);
					cellNome = row.createCell(16);
					cellNome.setCellValue((Double) dscBsen.getMax());
					cellNome = row.createCell(17);
					cellNome.setCellValue((Double) dscBsenRel.getMax());
					cellNome = row.createCell(18);
					cellNome.setCellValue((Double) dscAlt.getMax());
					
					
					row = sh.getRow(nlinha);
					cellNome = row.createCell(20);
					cellNome.setCellValue((Double) ns*1.0);
					cellNome = row.createCell(21);
					cellNome.setCellValue((Double) dscBsen.getMin());
					cellNome = row.createCell(22);
					cellNome.setCellValue((Double) dscBsenRel.getMin());
					cellNome = row.createCell(23);
					cellNome.setCellValue((Double) dscAlt.getMin());
					
					row = sh.getRow(nlinha+1);
					cellNome = row.createCell(20);
					cellNome.setCellValue((Double) ns*1.0);
					cellNome = row.createCell(21);
					cellNome.setCellValue((Double) dscBsen.getMax());
					cellNome = row.createCell(22);
					cellNome.setCellValue((Double) dscBsenRel.getMax());
					cellNome = row.createCell(23);
					cellNome.setCellValue((Double) dscAlt.getMax());
					ns=ns+5.0;
					nlinha=nlinha+2;
				  	}
					
					
					
					
				  	wb.write(stream);
					stream.close();
					
					
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
        	
			
        	}
        	
        	
        	
        	
        }
        
        
        Messages.informMsg("Executado com sucesso");
        System.out.println("");
	}
    
    
    
    
public static void executarPlanilhaSimulacao(String dirTemplate,String nomearq,String dirOutput,
    Map<String, Map<String,ResultEstacionaridade>> resultEstacionaridadeTipo2,String nomeIndice,
    Map<String,InventarioHidrologico> inventario,SimulationDataExtremos simulationData){
  
  //this.selecionarArquivoTemplateXls();
    //String dirTemplate=simulationData.getDirTemplateXls();
   // String nomearq=simulationData.getFilenameTemplateXls();
    String nomearqOriginal=nomearq;
    if(nomearq.contains(".xls") == false){
    nomearq=nomearq+".xls"; 
    }
  
      String [] nometesteTotal=pegarSiglaTesteSimulacao();
       //ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
      // int k1=0;
       //while(this.simulationData.getVariaveisHidr().get(k1).getResultestacionaridade().size() <= 0){
      //  k1++;
       //}
      String [] nometeste=null;
       Set<String> chavesEstacoes =  resultEstacionaridadeTipo2.keySet();
       //resultestacionaridade = this.simulationData.getVariaveisHidr().get(k1).getResultestacionaridade();
       //String [] nometeste=new String [resultestacionaridade.size()];
       for (String estac : chavesEstacoes){
     //for(int j=0;j<resultestacionaridade.size();j++){
      //nometeste[j]=resultestacionaridade.get(j).getNometeste();
         Set<String> chavesTeste=resultEstacionaridadeTipo2.get(estac).keySet();
         nometeste=new String [chavesTeste.size()];
         int j=0;
         for (String teste : chavesTeste){
           nometeste[j]=teste;
           j++;
         }
        break; 
     }
    
    // Map<String,Double> estacAlt=FiltroEstacoesTotalHIDROPG.pegarAltitude(); 
    Map<String,Map<String,ResultEstacionaridade>> resultEstacTesteEstacao =new HashMap<String,Map<String,ResultEstacionaridade>>();
   // Map<String,InventarioHidrologico> inventario=new HashMap<String,InventarioHidrologico>();
    //String nomeIndice="";
    for(int j=0;j<nometeste.length;j++){
      
       for (String estac : chavesEstacoes){
       // for(int i=0;i<this.simulationData.getVariaveisHidr().size();i++){
          
          //if(this.simulationData.getVariaveisHidr().get(i).isSelecionada() && this.simulationData.getVariaveisHidr().get(i).isAtendeRestricaoTamMin()){
            
              //String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(i).getInvhidro().getEstacao_codigo());
              String codigo=estac;
              if(j == 0){
              //inventario.put(codigo, this.simulationData.getVariaveisHidr().get(i).getInvhidro());
              //nomeIndice=this.simulationData.getVariaveisHidr().get(i).getInvhidro().getDescricaoOrigemSerie();
              }
              if(resultEstacTesteEstacao.containsKey(nometeste[j])){
              //resultEstacTesteEstacao.get(nometeste[j]).put(codigo, this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j));
                resultEstacTesteEstacao.get(nometeste[j]).put(codigo, resultEstacionaridadeTipo2.get(estac).get(nometeste[j]));
              }else{
              Map<String,ResultEstacionaridade> resestac=new HashMap<String,ResultEstacionaridade>(); 
              resultEstacTesteEstacao.put(nometeste[j], resestac);
              //resultEstacTesteEstacao.get(nometeste[j]).put(codigo, this.simulationData.getVariaveisHidr().get(i).getResultestacionaridade().get(j));
              resultEstacTesteEstacao.get(nometeste[j]).put(codigo, resultEstacionaridadeTipo2.get(estac).get(nometeste[j]));
              }
          // }
          
        }
    
    }
    
    //String dirOutput="C:\\OpenJump150\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\";
    String nomePlan="TabelaIndice";
    for(int j=0;j<nometeste.length;j++){
                
      String nmteste=nometeste[j];
      
      if(nmteste.equals("MK")){
      //HSSFWorkbook wb;
  try {
    HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(dirTemplate+nomearq));
    HSSFSheet sh = wb.getSheet(nomePlan);
    //Row row = sh.getRow(0);
    //Cell cellNome = row.createCell(1);
    //cellNome.setCellValue(nomearqOriginal);
     FileOutputStream stream = new FileOutputStream(dirOutput+nmteste+"_"+nomeIndice+".xls");
     Set<String> chaves = resultEstacTesteEstacao.get(nmteste).keySet();
     
    
     
     int nlinhasIniserie=3;
     int i1=0;
     DescriptiveStatistics dscBsen = new  DescriptiveStatistics();
     DescriptiveStatistics dscBsenRel = new  DescriptiveStatistics();
     DescriptiveStatistics dscAlt = new  DescriptiveStatistics();
     //double ns=5.0;
        for (String codigo : chaves){
        
          if(i1 == 0){
            Row row = sh.getRow(0);
          Cell cellNome = row.createCell(1);
          cellNome.setCellValue(String.valueOf(nomeIndice));
          //ns=resultEstacTesteEstacao.get(nmteste).get(codigo).g
          }
          
          
          Row rowLinhaEst = sh.createRow(nlinhasIniserie+i1);
        Cell cell = rowLinhaEst.createCell(0);
        cell.setCellValue(String.valueOf(codigo));
          cell = rowLinhaEst.createCell(1);
        cell.setCellValue((Double) inventario.get(codigo).getLatitude());
        cell = rowLinhaEst.createCell(2);
        cell.setCellValue((Double) inventario.get(codigo).getLongitude());
        cell = rowLinhaEst.createCell(3);
        
       // if (estacAlt.containsKey(codigo)){
        //double altitude=estacAlt.get(codigo);
        //if(!(altitude < 0)){
       // cell.setCellValue((Double) estacAlt.get(codigo));
        //  dscAlt.addValue((Double) estacAlt.get(codigo));
        //}
        //}else{
          cell.setCellValue(0.0);
          dscAlt.addValue(0.0);
       // }
        cell = rowLinhaEst.createCell(4);
        cell.setCellValue((Double) inventario.get(codigo).getAreaDrenagem());
        cell = rowLinhaEst.createCell(5);
        cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getEstatteste());
        cell = rowLinhaEst.createCell(6);
        cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getPvalue());
        cell = rowLinhaEst.createCell(7);
        cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getBsen());
        cell = rowLinhaEst.createCell(8);
        dscBsen.addValue(resultEstacTesteEstacao.get(nmteste).get(codigo).getBsen());
        
        cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getBsenRel());
        cell = rowLinhaEst.createCell(9);
        dscBsenRel.addValue(resultEstacTesteEstacao.get(nmteste).get(codigo).getBsenRel());
                    
        cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getNanos());
        cell = rowLinhaEst.createCell(10);
        cell.setCellValue((Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getNanosPeriodo());
        cell = rowLinhaEst.createCell(11);
        cell.setCellValue(String.valueOf(resultEstacTesteEstacao.get(nmteste).get(codigo).getSentidoTendencia()));
          
        i1++; 
        }
    
        double ns=5.0;
        int nlinha=nlinhasIniserie;
        for(int k=0;k<2;k++){
        
        Row row = sh.getRow(nlinha);
      Cell cellNome = row.createCell(15);
      cellNome.setCellValue((Double) ns*-1.0);
      cellNome = row.createCell(16);
      cellNome.setCellValue((Double) dscBsen.getMin());
      cellNome = row.createCell(17);
      cellNome.setCellValue((Double) dscBsenRel.getMin());
      cellNome = row.createCell(18);
      cellNome.setCellValue((Double) dscAlt.getMin());
      
      row = sh.getRow(nlinha+1);
      cellNome = row.createCell(15);
      cellNome.setCellValue((Double) ns*-1.0);
      cellNome = row.createCell(16);
      cellNome.setCellValue((Double) dscBsen.getMax());
      cellNome = row.createCell(17);
      cellNome.setCellValue((Double) dscBsenRel.getMax());
      cellNome = row.createCell(18);
      cellNome.setCellValue((Double) dscAlt.getMax());
      
      
      row = sh.getRow(nlinha);
      cellNome = row.createCell(20);
      cellNome.setCellValue((Double) ns*1.0);
      cellNome = row.createCell(21);
      cellNome.setCellValue((Double) dscBsen.getMin());
      cellNome = row.createCell(22);
      cellNome.setCellValue((Double) dscBsenRel.getMin());
      cellNome = row.createCell(23);
      cellNome.setCellValue((Double) dscAlt.getMin());
      
      row = sh.getRow(nlinha+1);
      cellNome = row.createCell(20);
      cellNome.setCellValue((Double) ns*1.0);
      cellNome = row.createCell(21);
      cellNome.setCellValue((Double) dscBsen.getMax());
      cellNome = row.createCell(22);
      cellNome.setCellValue((Double) dscBsenRel.getMax());
      cellNome = row.createCell(23);
      cellNome.setCellValue((Double) dscAlt.getMax());
      ns=ns+5.0;
      nlinha=nlinha+2;
        }
      
        boolean utilizouFDR=simulationData.isFazerFDR();
        ExportarResultadoTabelaResumoBsenTamanho.preencherPlanilhaTemplate(wb, nmteste, nomeIndice, inventario, resultEstacTesteEstacao,utilizouFDR);
      
        wb.write(stream);
      stream.close();
      
      
  } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
    
      
  
      }
      
      
      
      
    }
    
    
  //  Messages.informMsg("Executado com sucesso");
    System.out.println("");
}
    
    
    
public static String [] pegarSiglaTesteSimulacao(){
  
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
  return nometeste;
}
}
