package io;

import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
//import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import types.InventarioHidrologico;
import types.ResultEstacionaridade;
//import org.snirh.extremos_unb.deteccao.testes.ResultEstacionaridade;
//import org.snirh.extremos_unb.tipos.InventarioHidrologico;

public class ExportarResultadoTabelaResumoBsenTamanho {
	 public static void preencherPlanilhaTemplate(HSSFWorkbook wb,String nmteste,String nomeIndice,
		      Map<String,InventarioHidrologico> inventario, Map<String,Map<String,ResultEstacionaridade>> resultEstacTesteEstacao,
		      boolean utilizouFDR){
		    
		    String nomePlan="VALORES_TAMANHO_MAGNITUDE";
		    HSSFSheet sh = wb.getSheet(nomePlan);
		    
		    Set<String> chaves = resultEstacTesteEstacao.get(nmteste).keySet();
		    
		    
		    
		    int nlinhasIniserie=3;
		    int i1=0;
		    DescriptiveStatistics dscBsen = new DescriptiveStatistics();
		    DescriptiveStatistics dscBsenRel = new DescriptiveStatistics();
		    DescriptiveStatistics dscAlt = new DescriptiveStatistics();
		    //double ns=5.0;
		    
		    int itam1=0;
		    double valTam1=45;
		    int coliniTam1=26;
		    
		    int itam2=0;
		    double valTam2=60;
		    int coliniTam2=coliniTam1+5; 
		    
		    int itam3=0;
		    int coliniTam3=coliniTam2+5;
		    
		    int itamFDR=0;
		    int coliniFDR=coliniTam3+5;
		    
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
		         
		       double nanos=(Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getNanos();
		       double bsenrel=(Double) resultEstacTesteEstacao.get(nmteste).get(codigo).getBsenRel();
		       double pvalue=resultEstacTesteEstacao.get(nmteste).get(codigo).getPvalue();
		       boolean eFalsoPositivoFDP=false;
		       //boolean utilizouFDR=this.simulationData.isFazerFDR();
		       if(utilizouFDR){
		         eFalsoPositivoFDP=resultEstacTesteEstacao.get(nmteste).get(codigo).getResultadoFDR().isFalsoPositivo();
		         }
		       if(nanos <= valTam1 && !eFalsoPositivoFDP){
		         Row rowtam1 = sh.getRow(nlinhasIniserie+itam1);
		         Cell celltam1 = rowtam1.createCell(coliniTam1+0);
		         celltam1.setCellValue(String.valueOf(codigo));
		         celltam1 = rowtam1.createCell(coliniTam1+1);
		         celltam1.setCellValue((Double)pvalue);
		         celltam1 = rowtam1.createCell(coliniTam1+2);
		         celltam1.setCellValue((Double)bsenrel);
		         celltam1 = rowtam1.createCell(coliniTam1+3);
		         celltam1.setCellValue((Double)nanos);
		         itam1++;
		       }else if(nanos > valTam1 && nanos <= valTam2 && !eFalsoPositivoFDP){
		         Row rowtam1 = sh.getRow(nlinhasIniserie+itam2);
		         Cell celltam1 = rowtam1.createCell(coliniTam2+0);
		         celltam1.setCellValue(String.valueOf(codigo));
		         celltam1 = rowtam1.createCell(coliniTam2+1);
		         celltam1.setCellValue((Double)pvalue);
		         celltam1 = rowtam1.createCell(coliniTam2+2);
		         celltam1.setCellValue((Double)bsenrel);
		         celltam1 = rowtam1.createCell(coliniTam2+3);
		         celltam1.setCellValue((Double)nanos);
		         itam2++;
		       }else if(nanos > valTam2 && !eFalsoPositivoFDP){
		         Row rowtam1 = sh.getRow(nlinhasIniserie+itam3);
		         Cell celltam1 = rowtam1.createCell(coliniTam3+0);
		         celltam1.setCellValue(String.valueOf(codigo));
		         celltam1 = rowtam1.createCell(coliniTam3+1);
		         celltam1.setCellValue((Double)pvalue);
		         celltam1 = rowtam1.createCell(coliniTam3+2);
		         celltam1.setCellValue((Double)bsenrel);
		         celltam1 = rowtam1.createCell(coliniTam3+3);
		         celltam1.setCellValue((Double)nanos);
		         itam3++;
		         
		       }else if(eFalsoPositivoFDP){
		         
		         Row rowtam1 = sh.getRow(nlinhasIniserie+itamFDR);
		         Cell celltam1 = rowtam1.createCell(coliniFDR+0);
		         celltam1.setCellValue(String.valueOf(codigo));
		         celltam1 = rowtam1.createCell(coliniFDR+1);
		         celltam1.setCellValue((Double)pvalue);
		         celltam1 = rowtam1.createCell(coliniFDR+2);
		         celltam1.setCellValue((Double)bsenrel);
		         celltam1 = rowtam1.createCell(coliniFDR+3);
		         celltam1.setCellValue((Double)nanos);
		         itamFDR++;
		       }
		       
		       
		       
		       
		       
		       
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
		    
		    
		  }
}
