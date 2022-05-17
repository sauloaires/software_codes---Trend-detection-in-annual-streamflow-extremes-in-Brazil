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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import gui.PanelTrendDetectionStreamflowBrazil;
import types.ResultadosEstacionaridadeAgrupados;
import types.SimulationDataExtremos;
/*import org.snirh.extremos_unb.deteccao.gui.PanelTestesEstatisticos;
import org.snirh.extremos_unb.deteccao.util.ResultadosEstacionaridadeAgrupados;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.util.ExtensionFileFilter;*/

public class ExportarResultadoTabelaResumoIndicesPorRegiaoVazao {
	  private JFileChooser chooser_xls;
	  private ExtensionFileFilter filter_xls;
	  private SimulationDataExtremos simulationData;
	    private PanelTrendDetectionStreamflowBrazil pnt; 
	  
	  public ExportarResultadoTabelaResumoIndicesPorRegiaoVazao(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt){
	    this.simulationData=simulationData;
	    this.pnt=pnt;
	    this.createFileChooser();
	    
	  }
	  
	  
	  
	  
	public void executar(String dirTemplate,String nomearq,String dirOutput,String nomeShapeGrupo,
	    Map<String, Map<String, ResultadosEstacionaridadeAgrupados>> resultGrupoIndices){
	      
	      //this.selecionarArquivoTemplateXls();
	        //String dirTemplate=simulationData.getDirTemplateXls();
	       // String nomearq=simulationData.getFilenameTemplateXls();
	        String nomearqOriginal=simulationData.getFilenameBD();
	        if(nomearq.contains(".xls") == false){
	        nomearq=nomearq+".xls"; 
	        }
	        
	        
	       
	    try {
	    HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(dirTemplate+nomearq));
	    FileOutputStream stream = new FileOutputStream(dirOutput+nomeShapeGrupo+".xls");
	        Set<String> chavesIndice =  resultGrupoIndices.keySet();
	        HSSFSheet sh = wb.getSheet("RESUMO");
	            for (String nomeIndice : chavesIndice){
	        //HSSFSheet sh = wb.getSheet(nomeIndice);
	        
	        //int nlinhasIniserie=76;
	        
	        String [] nometeste=this.pegarSiglaTeste();
	        //int ilinha=0
	          for(int i=0;i<nometeste.length;i++){
	            String nmteste=nometeste[i];
	            int nlinhasIniserie=pegarLinhaArquivoExcelTemplateTp3(nmteste,nomeIndice);
	            ResultadosEstacionaridadeAgrupados resTesteIndice=resultGrupoIndices.get(nomeIndice).get(nmteste);
	            Row rowLinha = sh.getRow(nlinhasIniserie);
	            ArrayList<Double> resLinha=resTesteIndice.pegarResultadosLinhasTabela();
	            for(int j=0;j<resLinha.size();j++){
	            //Cell cell = rowLinha.createCell(j+1);
	              Cell cell = rowLinha.getCell(j+1);
	            cell.setCellValue((Double) resLinha.get(j));
	            }
	            
	          }
	          
	          
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
	        
	    // Messages.informMsg("Executado com sucesso");
	        System.out.println("Executado com sucesso");
	        
	}
	  
	  
	  
	  
	  
	  private int pegarLinhaArquivoExcelTemplateTp3(String nmteste,String nomeIndice){
	    
	    int linhaIndiceTeste=0;
	    
	    
	    
	    Map<String, Integer>ordemIndice=new HashMap<String, Integer>();
	    ArrayList<String> nomesIndice=this.pegarIndice();
	    for(int i=0;i<nomesIndice.size();i++){
	    ordemIndice.put(nomesIndice.get(i), i);
	    }
	    
	    Map<String, Integer>ordemTesteLinhaIni=new HashMap<String, Integer>();
	    ArrayList<String> nomesTeste=this.pegarTeste();
	    int linhaIniTeste=4;
	    int incremento=17;
	    int linhaInicialTeste=linhaIniTeste;
	    for(int i=0;i<nomesTeste.size();i++){
	      ordemTesteLinhaIni.put(nomesTeste.get(i), linhaInicialTeste);
	      linhaInicialTeste=linhaInicialTeste+incremento;
	      
	    }
	    
	    linhaIndiceTeste=ordemIndice.get(nomeIndice)+ordemTesteLinhaIni.get(nmteste);
	    
	    
	    return linhaIndiceTeste;
	    
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
	  
	  
	public ArrayList<String> pegarTeste(){
	  
	  int ntestes=14;
	                
	  ArrayList<String> nomeTeste=new ArrayList<String>();
	  
	  nomeTeste.add("MK");
	  nomeTeste.add("MW");
	  nomeTeste.add("SR");
	  nomeTeste.add("LR");
	  nomeTeste.add("TT");
	  nomeTeste.add("DC");
	  nomeTeste.add("CD");
	  nomeTeste.add("WR");
	  
	  nomeTeste.add("TF");
	  
	  nomeTeste.add("MC");
	  nomeTeste.add("TP");
	  nomeTeste.add("RD");
	  nomeTeste.add("AC");
	  nomeTeste.add("WW");
	    
	  return nomeTeste;
	}


	  
	public ArrayList<String> pegarIndice(){
	  
	  int ntestes=14;
	                
	  ArrayList<String> nomeIndice=new ArrayList<String>();
	  nomeIndice.add("QX1day");
	  nomeIndice.add("QMed");
	  nomeIndice.add("Qmin7day");
	  nomeIndice.add("Qmin30day");
	  nomeIndice.add("QX5day");
	  nomeIndice.add("QX30day");
	  nomeIndice.add("Qmin7dayUmidoSemestre");
	  nomeIndice.add("Qmin7dayUmidoTrimestre");
	  
	  return nomeIndice;
	}
	  
}
