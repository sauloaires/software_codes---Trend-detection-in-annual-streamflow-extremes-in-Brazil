package io;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/*import org.snirh.extremos_unb.deteccao.gui.PanelTestesEstatisticos;
import org.snirh.extremos_unb.deteccao.testes.ResultEstacionaridade;
import org.snirh.extremos_unb.deteccao.testes.ResultadoEstacMagnitudeTamanho;
import org.snirh.extremos_unb.deteccao.util.PegarLayerEstilo;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.extremos_unb.tipos.VariavelHidrologica;
import org.snirh.extremos_unb.util.SNIRHPlugInSettings;
import org.snirh.extremos_unb.util.SalvarLayerDiretorio;*/

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jump.feature.AttributeType;
import com.vividsolutions.jump.feature.BasicFeature;
import com.vividsolutions.jump.feature.Feature;
import com.vividsolutions.jump.feature.FeatureCollection;
import com.vividsolutions.jump.feature.FeatureDataset;
import com.vividsolutions.jump.feature.FeatureSchema;
import com.vividsolutions.jump.workbench.model.Category;
import com.vividsolutions.jump.workbench.model.Layer;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;

import gui.PanelTrendDetectionStreamflowBrazil;
import types.ResultEstacionaridade;
import types.ResultadoEstacMagnitudeTamanho;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;

public class DesenharShapesResultadoDetalhadoFDRTamanhoMagnitude {

	 private SimulationDataExtremos simulationData;
	  private PanelTrendDetectionStreamflowBrazil pnt; 
	  protected PlugInContext context = null;
	protected Category category = null;

	private ResultadoEstacMagnitudeTamanho resEstacMagnitudeTamanho;

	public DesenharShapesResultadoDetalhadoFDRTamanhoMagnitude(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt){
	  this.simulationData=simulationData;
	  this.pnt=pnt;
	  
	}





	public void executar(String testeEscolhido,String dirOutput,String nomeShapeGrupo,
	    Map<String, Map<String,ResultEstacionaridade>> resultEstacionaridadeTipo2, String nomeIndice){
	  
	  
	 /* this.context= this.pnt.getEstacionaridadePlugIn().getContext();
	  this.category = context.getLayerManager().getCategory(SNIRHPlugInSettings.resultLayerCategory());
	  
	  if (this.category == null) {
	    this.context.getLayerManager().addCategory(
	        SNIRHPlugInSettings.resultLayerCategory(), 0);
	    this.category = this.context.getLayerManager().getCategory(
	        SNIRHPlugInSettings.resultLayerCategory());
	  }*/
	  
	  int ncolum=8;
	  String[] columnNames = new String[ncolum];    
	  
	  columnNames[0] = "TTE";
	  columnNames[1] = "TES";
	  columnNames[2] = "ETE";
	  columnNames[3] = "VCT";
	  columnNames[4] = "RES";
	  columnNames[5] = "VCB";
	  columnNames[6] = "REB";
	  columnNames[7] = "DIR"; //DIRECAO DA MUDANCA
	  
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
	    
	  int ntipotestes=4;
	  String [] nometipoteste=new String [ntipotestes];
	  nometipoteste[0]="Mudança gradual (Tendência)";
	  nometipoteste[1]="Mudança brusca (Média e Mediana)";
	  nometipoteste[2]="Mudança brusca (Variância)";
	  nometipoteste[3]="Teste de Independência";
	  FeatureSchema fs = new FeatureSchema();
	  fs.addAttribute("Geometry", AttributeType.GEOMETRY);
	  fs.addAttribute("ID", AttributeType.STRING);
	  fs.addAttribute("CODIGO", AttributeType.STRING);
	  fs.addAttribute("LONG", AttributeType.DOUBLE);
	  fs.addAttribute("LAT", AttributeType.DOUBLE);
	  fs.addAttribute("AREA", AttributeType.DOUBLE);
	  
	  
	  fs.addAttribute("nanos", AttributeType.DOUBLE);
	  fs.addAttribute("pvalue", AttributeType.DOUBLE);
	  fs.addAttribute("bsenrel", AttributeType.DOUBLE);
	  
	  for (int i = 0; i < ntestes; i++) {
	    for (int k = 0; k < ncolum; k++) {
	      if(k <=1 || k==4 || k==6 || k==7){
	      fs.addAttribute(nometeste[i]+"_"+columnNames[k], AttributeType.STRING); 
	      }else{
	      fs.addAttribute(nometeste[i]+"_"+columnNames[k], AttributeType.DOUBLE); 
	      }
	    }
	  }
	  
	     // String nomearq=this.simulationData.getFilenameBD();
	  String nomearq=nomeShapeGrupo+"_"+nomeIndice;
	  if(nomearq.contains(".dat")){
	    nomearq=nomearq.replace(".dat","");
	  }
	  
	  FeatureDataset featureDataset_MK_NS = new FeatureDataset(fs);
	  //Layer layer_MK_NS = context.addLayer(category.getName(),nomearq+"_"+testeEscolhido+"_NS", featureDataset_MK_NS);
	  //FeatureCollection fd_MK_NS = layer_MK_NS.getFeatureCollectionWrapper();
	  FeatureSchema fs2_MK_NS = fs;
	  ArrayList<Feature> featuresMK_NS = new ArrayList<Feature>();
	      
	  FeatureDataset featureDataset_MK_S = new FeatureDataset(fs);
	  //Layer layer_MK_S = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_S", featureDataset_MK_S);
	  //FeatureCollection fd_MK_S = layer_MK_S.getFeatureCollectionWrapper();
	  FeatureSchema fs2_MK_S = fs;
	  ArrayList<Feature> featuresMK_S = new ArrayList<Feature>();
	  
	  
	  ArrayList<Feature> featuresMK_CS = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS = new ArrayList<Feature>();
	  //ArrayList<Feature> featuresMK_CS_S = new ArrayList<Feature>();
	  //ArrayList<Feature> featuresMK_DS_S = new ArrayList<Feature>();
	  
	  ArrayList<Feature> featuresMK_CS_S_FDP = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS_S_FDP = new ArrayList<Feature>();
	  
	  
	  
	  /**
	   * features por tamanho e magnitude
	   */
	  
	  ArrayList<Feature> featuresMK_CS_S_tam1_mag1 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_CS_S_tam1_mag2 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_CS_S_tam1_mag3 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_CS_S_tam2_mag1 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_CS_S_tam2_mag2 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_CS_S_tam2_mag3 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_CS_S_tam3_mag1 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_CS_S_tam3_mag2 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_CS_S_tam3_mag3 = new ArrayList<Feature>();
	  
	  
	  ArrayList<Feature> featuresMK_DS_S_tam1_mag1 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS_S_tam1_mag2 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS_S_tam1_mag3 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS_S_tam2_mag1 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS_S_tam2_mag2 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS_S_tam2_mag3 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS_S_tam3_mag1 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS_S_tam3_mag2 = new ArrayList<Feature>();
	  ArrayList<Feature> featuresMK_DS_S_tam3_mag3 = new ArrayList<Feature>();
	  
	  double tam1=45.0;
	  double tam2=60.0;
	  
	  double mag1=5.0;
	  double mag2=10.0;
	  
	  
	  
	  
	  Set<String> chaves =  resultEstacionaridadeTipo2.keySet();
	  int igauges=0;
	    for (String codigo : chaves){
	      
	    Map<String,ResultEstacionaridade> resultestacionaridadeTipo2=resultEstacionaridadeTipo2.get(codigo);
	      
	    VariavelHidrologica vhid =this.simulationData.pegarVariaveisHidrDoCodigo(codigo);
	    GeometryFactory geomFact = new GeometryFactory();
	  //Coordinate coord = new Coordinate(vhid.getInvhidro().getPosition().getX(), vhid.getInvhidro().getPosition().getY()); 
	  Coordinate coord = new Coordinate(vhid.getInvhidro().getLongitude(), vhid.getInvhidro().getLatitude()); 
	  
	  Point p = geomFact.createPoint(coord);
	  BasicFeature featMK = new BasicFeature(fs2_MK_NS);
	  
	        
	  featMK.setGeometry(p);
	  featMK.setAttribute("ID", igauges);
	  featMK.setAttribute("LONG", vhid.getInvhidro().getLongitude());
	  featMK.setAttribute("LAT", vhid.getInvhidro().getLatitude());
	  featMK.setAttribute("CODIGO", vhid.getInvhidro().getEstacao_codigo());
	  featMK.setAttribute("AREA", vhid.getInvhidro().getAreaDrenagem());
	  
	  
	  double nanos=(Double) resultestacionaridadeTipo2.get(testeEscolhido).getNanos();
	  double bsenrel=(Double) resultestacionaridadeTipo2.get(testeEscolhido).getBsenRel();
	  double pvalue=resultestacionaridadeTipo2.get(testeEscolhido).getPvalue();
	  
	  featMK.setAttribute("nanos", nanos);
	  featMK.setAttribute("pvalue", pvalue);
	  featMK.setAttribute("bsenrel", bsenrel);
	  
	    
	  DecimalFormatSymbols dc = new DecimalFormatSymbols();
	  dc.setDecimalSeparator('.');
	  String strange = "0.00";
	  DecimalFormat myFormatter = new DecimalFormat(strange, dc); 
	  boolean eFalsoPositivoFDP=false;
	  boolean utilizouFDR=this.simulationData.isFazerFDR();
	  for (int i = 0; i < ntestes; i++) {
	    System.out.println();
	            if(nometeste[i] == testeEscolhido){
	              featMK.setAttribute(nometeste[i]+"_"+columnNames[0], nometipoteste[0]); 
	            }
	          
	            
	                Set<String> chavesTeste =  resultestacionaridadeTipo2.keySet();
	                for (String teste : chavesTeste){
	               // for(int j=0;j<resultestacionaridade.size();j++){
	                  String nmTeste=teste;
	                  //if(resultestacionaridade.get(j).getNometeste().equals(nometeste[i])){
	                       if(nmTeste.equals(nometeste[i])){
	                                          
	                        if(nometeste[i] == testeEscolhido){
	                          if(utilizouFDR){
	                          eFalsoPositivoFDP=resultestacionaridadeTipo2.get(testeEscolhido).getResultadoFDR().isFalsoPositivo();
	                          }
	                          
	                        featMK.setAttribute(nometeste[i]+"_"+columnNames[2], resultestacionaridadeTipo2.get(testeEscolhido).getEstatteste());
	                        featMK.setAttribute(nometeste[i]+"_"+columnNames[3], resultestacionaridadeTipo2.get(testeEscolhido).getValorcriticoteste());
	                          featMK.setAttribute(nometeste[i]+"_"+columnNames[4], resultestacionaridadeTipo2.get(testeEscolhido).getResultadoteste()); 
	                          
	                          if(resultestacionaridadeTipo2.get(testeEscolhido).getSentidoTendencia() == null){
	                            featMK.setAttribute(nometeste[i]+"_"+columnNames[7],resultestacionaridadeTipo2.get(testeEscolhido).getSentidoMediaRecente());
	                          }else{
	                            featMK.setAttribute(nometeste[i]+"_"+columnNames[7],resultestacionaridadeTipo2.get(testeEscolhido).getSentidoTendencia());  
	                          }
	                          
	                          
	                      }
	                                          
	                        
	                  }
	                            
	              }
	        
	            
	            }
	  
	     String resMK=(String) featMK.getAttribute(testeEscolhido+"_"+columnNames[4]);
	    String resMKDIR=(String) featMK.getAttribute(testeEscolhido+"_"+columnNames[7]);
	    //String resMK=(String) featMK.getAttribute("MK"+"_"+columnNames[4]);
	    //String resMKDIR=(String) featMK.getAttribute("MK"+"_"+columnNames[7]);
	    
	    if(resMK == null){
	      System.out.println(igauges);
	    }
	    
	    if(!resMK.equals("NS")){
	      System.out.println(igauges);
	    }
	    
	    if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)") || resMK.equals("S")){
	      featuresMK_S.add(featMK); 
	    }else{
	      featuresMK_NS.add(featMK);
	    }
	    
	    //Saulo 11/08/2016
	    if(resMKDIR == null){
	      System.out.println();
	    }else{
	    
	    if(resMKDIR.equals("Crescente") || resMKDIR.equals("Maior")){
	      featuresMK_CS.add(featMK);
	    }else{
	      featuresMK_DS.add(featMK);
	    }
	    
	    }
	    
	    
	    if(resMKDIR == null){
	      System.out.println();
	    }else{
	      if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)") && (resMKDIR.equals("Crescente") || resMKDIR.equals("Maior"))){
	        //featuresMK_CS_S.add(featMK);
	      }else if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)") && (resMKDIR.equals("Decrescente") || resMKDIR.equals("Menor"))){
	        //featuresMK_DS_S.add(featMK);
	      }
	              
	    }
	    
	    
	    if(resMKDIR == null){
	      System.out.println();
	    }else{
	      if(eFalsoPositivoFDP && (resMKDIR.equals("Crescente") || resMKDIR.equals("Maior"))){
	        featuresMK_CS_S_FDP.add(featMK);
	      }else if(eFalsoPositivoFDP && (resMKDIR.equals("Decrescente") || resMKDIR.equals("Menor"))){
	        featuresMK_DS_S_FDP.add(featMK);
	      }
	              
	    }
	    
	    
	    if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)") && (resMKDIR.equals("Crescente") || resMKDIR.equals("Maior"))){
	      double bsenrelabs=Math.abs(bsenrel);
	      
	      if(nanos <= tam1 && bsenrelabs <= mag1){
	        featuresMK_CS_S_tam1_mag1.add(featMK);
	      }else if(nanos <= tam1 && bsenrelabs > mag1 && bsenrelabs <= mag2){
	        featuresMK_CS_S_tam1_mag2.add(featMK);
	      }else if(nanos <= tam1 && bsenrelabs > mag2){
	        featuresMK_CS_S_tam1_mag3.add(featMK);
	      }else if(nanos > tam1 && nanos <= tam2 && bsenrelabs <= mag1){
	        featuresMK_CS_S_tam2_mag1.add(featMK);
	      }else if(nanos > tam1 && nanos <= tam2 && bsenrelabs > mag1 && bsenrelabs <= mag2){
	        featuresMK_CS_S_tam2_mag2.add(featMK);
	      }else if(nanos > tam1 && nanos <= tam2 && bsenrelabs > mag2){
	        featuresMK_CS_S_tam2_mag3.add(featMK);
	      }else if(nanos > tam2  && bsenrelabs <= mag1){
	        featuresMK_CS_S_tam3_mag1.add(featMK);
	      }else if(nanos > tam2  && bsenrelabs > mag1 && bsenrelabs <= mag2){
	        featuresMK_CS_S_tam3_mag2.add(featMK);
	      }else if(nanos > tam2 && bsenrelabs > mag2){
	        featuresMK_CS_S_tam3_mag3.add(featMK);
	      }
	    
	    }
	    
	    
	    if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)") && (resMKDIR.equals("Decrescente") || resMKDIR.equals("Menor"))){
	      double bsenrelabs=Math.abs(bsenrel);
	      
	      if(nanos <= tam1 && bsenrelabs <= mag1){
	        featuresMK_DS_S_tam1_mag1.add(featMK);
	      }else if(nanos <= tam1 && bsenrelabs > mag1 && bsenrelabs <= mag2){
	        featuresMK_DS_S_tam1_mag2.add(featMK);
	      }else if(nanos <= tam1 && bsenrelabs > mag2){
	        featuresMK_DS_S_tam1_mag3.add(featMK);
	      }else if(nanos > tam1 && nanos <= tam2 && bsenrelabs <= mag1){
	        featuresMK_DS_S_tam2_mag1.add(featMK);
	      }else if(nanos > tam1 && nanos <= tam2 && bsenrelabs > mag1 && bsenrelabs <= mag2){
	        featuresMK_DS_S_tam2_mag2.add(featMK);
	      }else if(nanos > tam1 && nanos <= tam2 && bsenrelabs > mag2){
	        featuresMK_DS_S_tam2_mag3.add(featMK);
	      }else if(nanos > tam2  && bsenrelabs <= mag1){
	        featuresMK_DS_S_tam3_mag1.add(featMK);
	      }else if(nanos > tam2  && bsenrelabs > mag1 && bsenrelabs <= mag2){
	        featuresMK_DS_S_tam3_mag2.add(featMK);
	      }else if(nanos > tam2 && bsenrelabs > mag2){
	        featuresMK_DS_S_tam3_mag3.add(featMK);
	      }
	    
	    }
	    
	    
	    
	  igauges++;
	    }
	    
	    
	    /**
	     * Saulo - 26/12/2018
	     */
	    
	    this.resEstacMagnitudeTamanho=new ResultadoEstacMagnitudeTamanho();
	    boolean guardarResultMagTam=true;
	    if(guardarResultMagTam){
	      this.resEstacMagnitudeTamanho.setCS_S_tam1_mag1(featuresMK_CS_S_tam1_mag1.size());
	      this.resEstacMagnitudeTamanho.setCS_S_tam1_mag2(featuresMK_CS_S_tam1_mag2.size());
	      this.resEstacMagnitudeTamanho.setCS_S_tam1_mag3(featuresMK_CS_S_tam1_mag3.size());
	      this.resEstacMagnitudeTamanho.setCS_S_tam2_mag1(featuresMK_CS_S_tam2_mag1.size());
	      this.resEstacMagnitudeTamanho.setCS_S_tam2_mag2(featuresMK_CS_S_tam2_mag2.size());
	      this.resEstacMagnitudeTamanho.setCS_S_tam2_mag3(featuresMK_CS_S_tam2_mag3.size());
	      this.resEstacMagnitudeTamanho.setCS_S_tam3_mag1(featuresMK_CS_S_tam3_mag1.size());
	      this.resEstacMagnitudeTamanho.setCS_S_tam3_mag2(featuresMK_CS_S_tam3_mag2.size());
	      this.resEstacMagnitudeTamanho.setCS_S_tam3_mag3(featuresMK_CS_S_tam3_mag3.size());
	      
	      this.resEstacMagnitudeTamanho.setDS_S_tam1_mag1(featuresMK_DS_S_tam1_mag1.size());
	      this.resEstacMagnitudeTamanho.setDS_S_tam1_mag2(featuresMK_DS_S_tam1_mag2.size());
	      this.resEstacMagnitudeTamanho.setDS_S_tam1_mag3(featuresMK_DS_S_tam1_mag3.size());
	      this.resEstacMagnitudeTamanho.setDS_S_tam2_mag1(featuresMK_DS_S_tam2_mag1.size());
	      this.resEstacMagnitudeTamanho.setDS_S_tam2_mag2(featuresMK_DS_S_tam2_mag2.size());
	      this.resEstacMagnitudeTamanho.setDS_S_tam2_mag3(featuresMK_DS_S_tam2_mag3.size());
	      this.resEstacMagnitudeTamanho.setDS_S_tam3_mag1(featuresMK_DS_S_tam3_mag1.size());
	      this.resEstacMagnitudeTamanho.setDS_S_tam3_mag2(featuresMK_DS_S_tam3_mag2.size());
	      this.resEstacMagnitudeTamanho.setDS_S_tam3_mag3(featuresMK_DS_S_tam3_mag3.size());
	    }
	    
	    
	    
	    Layer layer_MK_NS=null;
	    boolean desenhar_MK_NS=false;
	    if(desenhar_MK_NS){
	    
	    if(featuresMK_NS.size() != 0){
	      
	      layer_MK_NS = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS", featureDataset_MK_NS);
	    FeatureCollection fd_MK_NS = layer_MK_NS.getFeatureCollectionWrapper();
	    fs2_MK_NS = fd_MK_NS.getFeatureSchema();
	    layer_MK_NS.setEditable(true);
	    fd_MK_NS.clear();
	    fd_MK_NS.addAll(featuresMK_NS);
	      layer_MK_NS.setEditable(false);
	    
	    String nomelayerTemplate="MK_NS";
	    //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	    //layer_MK_NS.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	    //}
	    }
	    }
	    Layer layer_MK_S=null;
	    boolean desenhar_MK_S=false;
	    if(desenhar_MK_S){
	    
	  if(featuresMK_S.size() != 0){
	    layer_MK_S = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS", featureDataset_MK_S);
	    FeatureCollection fd_MK_S = layer_MK_S.getFeatureCollectionWrapper();
	    fs2_MK_S = fd_MK_S.getFeatureSchema();
	    layer_MK_S.setEditable(true);
	    fd_MK_S.clear();
	    fd_MK_S.addAll(featuresMK_NS);
	      layer_MK_S.setEditable(false);
	          
	    String nomelayerTemplate="MK_S";
	    //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	    //  layer_MK_S.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	    //}
	  }
	  
	    }
	  
	  Layer layer_MK_CS = null;
	  if(featuresMK_CS.size() != 0){
	    FeatureDataset featureDataset_MK_CS = new FeatureDataset(fs);
	    layer_MK_CS = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS", featureDataset_MK_CS);
	    FeatureCollection fd_MK_CS = layer_MK_CS.getFeatureCollectionWrapper();
	    FeatureSchema fs2_MK_CS = fd_MK_CS.getFeatureSchema();
	    layer_MK_CS.setEditable(true);
	    fd_MK_CS.clear();
	    fd_MK_CS.addAll(featuresMK_CS);
	    layer_MK_CS.setEditable(false);
	    
	    String nomelayerTemplate="MK_CS";
	    //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	     // layer_MK_CS.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	    //}
	    
	  }
	  Layer layer_MK_DS=null;
	  if(featuresMK_DS.size() != 0){
	    FeatureDataset featureDataset_MK_DS = new FeatureDataset(fs);
	    layer_MK_DS = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS", featureDataset_MK_DS);
	    FeatureCollection fd_MK_DS = layer_MK_DS.getFeatureCollectionWrapper();
	    FeatureSchema fs2_MK_DS = fd_MK_DS.getFeatureSchema();
	    
	    layer_MK_DS.setEditable(true);
	    fd_MK_DS.clear();
	    fd_MK_DS.addAll(featuresMK_DS);
	    layer_MK_DS.setEditable(false);
	    
	    String nomelayerTemplate="MK_DS";
	    //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	     // layer_MK_DS.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	   // }
	  }
	  
	  
	  
	  Layer layer_MK_CS_S_FDP =null;
	  if(featuresMK_CS_S_FDP.size() != 0){
	    FeatureDataset featureDataset_MK_CS_S_FDP = new FeatureDataset(fs);
	    layer_MK_CS_S_FDP = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_FDP", featureDataset_MK_CS_S_FDP);
	    FeatureCollection fd_MK_CS_S_FDP = layer_MK_CS_S_FDP.getFeatureCollectionWrapper();
	        
	    layer_MK_CS_S_FDP.setEditable(true);
	    fd_MK_CS_S_FDP.clear();
	    fd_MK_CS_S_FDP.addAll(featuresMK_CS_S_FDP);
	    layer_MK_CS_S_FDP.setEditable(false);
	    
	    String nomelayerTemplate="MK_CS_S_FDP";
	   // if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	   //   layer_MK_CS_S_FDP.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	    //}
	  }
	  
	  Layer layer_MK_DS_S_FDP =null;
	  if(featuresMK_DS_S_FDP.size() != 0){
	    FeatureDataset featureDataset_MK_DS_S_FDP = new FeatureDataset(fs);
	    layer_MK_DS_S_FDP = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_FDP", featureDataset_MK_DS_S_FDP);
	    FeatureCollection fd_MK_DS_S_FDP = layer_MK_DS_S_FDP.getFeatureCollectionWrapper();
	    //FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	    layer_MK_DS_S_FDP.setEditable(true);
	    fd_MK_DS_S_FDP.clear();
	    fd_MK_DS_S_FDP.addAll(featuresMK_DS_S_FDP);
	    layer_MK_DS_S_FDP.setEditable(false);
	    
	    String nomelayerTemplate="MK_DS_S_FDP";
	    //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	    //  layer_MK_DS_S_FDP.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	    //}
	  }
	  
	    
	  Layer layer_MK_CS_S_tam1_mag1 =null;
	  if(featuresMK_CS_S_tam1_mag1.size() != 0){
	    FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
	    layer_MK_CS_S_tam1_mag1 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_tam1_mag1", featureDataset_MK_CS_S);
	    FeatureCollection fd_MK_CS_S = layer_MK_CS_S_tam1_mag1.getFeatureCollectionWrapper();
	    FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
	    
	    layer_MK_CS_S_tam1_mag1.setEditable(true);
	    fd_MK_CS_S.clear();
	    fd_MK_CS_S.addAll(featuresMK_CS_S_tam1_mag1);
	    layer_MK_CS_S_tam1_mag1.setEditable(false);
	    
	    String nomelayerTemplate="MK_CS_S_tam1_mag1";
	    //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	    //  layer_MK_CS_S_tam1_mag1.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	    //}
	  }
	  
	  
	  
	  Layer layer_MK_CS_S_tam1_mag2 =null;
	  if(featuresMK_CS_S_tam1_mag2.size() != 0){
	    FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
	    layer_MK_CS_S_tam1_mag2 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_tam1_mag2", featureDataset_MK_CS_S);
	    FeatureCollection fd_MK_CS_S = layer_MK_CS_S_tam1_mag2.getFeatureCollectionWrapper();
	    FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
	    
	    layer_MK_CS_S_tam1_mag2.setEditable(true);
	    fd_MK_CS_S.clear();
	    fd_MK_CS_S.addAll(featuresMK_CS_S_tam1_mag2);
	    layer_MK_CS_S_tam1_mag2.setEditable(false);
	    
	    String nomelayerTemplate="MK_CS_S_tam1_mag2";
	    //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	    //  layer_MK_CS_S_tam1_mag2.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	    //}
	  }
	  
	  
	  
	  
	  Layer layer_MK_CS_S_tam1_mag3 =null;
	  if(featuresMK_CS_S_tam1_mag3.size() != 0){
	    FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
	    layer_MK_CS_S_tam1_mag3 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_tam1_mag3", featureDataset_MK_CS_S);
	    FeatureCollection fd_MK_CS_S = layer_MK_CS_S_tam1_mag3.getFeatureCollectionWrapper();
	    FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
	    
	    layer_MK_CS_S_tam1_mag3.setEditable(true);
	    fd_MK_CS_S.clear();
	    fd_MK_CS_S.addAll(featuresMK_CS_S_tam1_mag3);
	    layer_MK_CS_S_tam1_mag3.setEditable(false);
	    
	    String nomelayerTemplate="MK_CS_S_tam1_mag3";
	    //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	    //  layer_MK_CS_S_tam1_mag3.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	   // }
	  }
	  
	  
	    Layer layer_MK_CS_S_tam2_mag1 =null;
	    if(featuresMK_CS_S_tam2_mag1.size() != 0){
	      FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
	      layer_MK_CS_S_tam2_mag1 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_tam2_mag1", featureDataset_MK_CS_S);
	      FeatureCollection fd_MK_CS_S = layer_MK_CS_S_tam2_mag1.getFeatureCollectionWrapper();
	      FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
	      
	      layer_MK_CS_S_tam2_mag1.setEditable(true);
	      fd_MK_CS_S.clear();
	      fd_MK_CS_S.addAll(featuresMK_CS_S_tam2_mag1);
	      layer_MK_CS_S_tam2_mag1.setEditable(false);
	      
	      String nomelayerTemplate="MK_CS_S_tam2_mag1";
	      //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	      //  layer_MK_CS_S_tam2_mag1.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	      //}
	    }
	  
	  
	  
	    Layer layer_MK_CS_S_tam2_mag2 =null;
	    if(featuresMK_CS_S_tam2_mag2.size() != 0){
	      FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
	      layer_MK_CS_S_tam2_mag2 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_tam2_mag2", featureDataset_MK_CS_S);
	      FeatureCollection fd_MK_CS_S = layer_MK_CS_S_tam2_mag2.getFeatureCollectionWrapper();
	      FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
	      
	      layer_MK_CS_S_tam2_mag2.setEditable(true);
	      fd_MK_CS_S.clear();
	      fd_MK_CS_S.addAll(featuresMK_CS_S_tam2_mag2);
	      layer_MK_CS_S_tam2_mag2.setEditable(false);
	      
	      String nomelayerTemplate="MK_CS_S_tam2_mag2";
	      //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	      //  layer_MK_CS_S_tam2_mag2.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	     // }
	    }
	  
	  
	    Layer layer_MK_CS_S_tam2_mag3 =null;
	    if(featuresMK_CS_S_tam2_mag3.size() != 0){
	      FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
	      layer_MK_CS_S_tam2_mag3 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_tam2_mag3", featureDataset_MK_CS_S);
	      FeatureCollection fd_MK_CS_S = layer_MK_CS_S_tam2_mag3.getFeatureCollectionWrapper();
	      FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
	      
	      layer_MK_CS_S_tam2_mag3.setEditable(true);
	      fd_MK_CS_S.clear();
	      fd_MK_CS_S.addAll(featuresMK_CS_S_tam2_mag3);
	      layer_MK_CS_S_tam2_mag3.setEditable(false);
	      
	      String nomelayerTemplate="MK_CS_S_tam2_mag3";
	      //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	      //  layer_MK_CS_S_tam2_mag3.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	      //}
	    }
	  
	    Layer layer_MK_CS_S_tam3_mag1 =null;
	    if(featuresMK_CS_S_tam3_mag1.size() != 0){
	      FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
	      layer_MK_CS_S_tam3_mag1 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_tam3_mag1", featureDataset_MK_CS_S);
	      FeatureCollection fd_MK_CS_S = layer_MK_CS_S_tam3_mag1.getFeatureCollectionWrapper();
	      FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
	      
	      layer_MK_CS_S_tam3_mag1.setEditable(true);
	      fd_MK_CS_S.clear();
	      fd_MK_CS_S.addAll(featuresMK_CS_S_tam3_mag1);
	      layer_MK_CS_S_tam3_mag1.setEditable(false);
	      
	      String nomelayerTemplate="MK_CS_S_tam3_mag1";
	      //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	      //  layer_MK_CS_S_tam3_mag1.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	      //}
	    }
	  
	  
	  
	    Layer layer_MK_CS_S_tam3_mag2 =null;
	    if(featuresMK_CS_S_tam3_mag2.size() != 0){
	      FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
	      layer_MK_CS_S_tam3_mag2 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_tam3_mag2", featureDataset_MK_CS_S);
	      FeatureCollection fd_MK_CS_S = layer_MK_CS_S_tam3_mag2.getFeatureCollectionWrapper();
	      FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
	      
	      layer_MK_CS_S_tam3_mag2.setEditable(true);
	      fd_MK_CS_S.clear();
	      fd_MK_CS_S.addAll(featuresMK_CS_S_tam3_mag2);
	      layer_MK_CS_S_tam3_mag2.setEditable(false);
	      
	      String nomelayerTemplate="MK_CS_S_tam3_mag2";
	      //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	      //  layer_MK_CS_S_tam3_mag2.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	      //}
	    }
	  
	  
	    Layer layer_MK_CS_S_tam3_mag3 =null;
	    if(featuresMK_CS_S_tam3_mag3.size() != 0){
	      FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
	      layer_MK_CS_S_tam3_mag3 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S_tam3_mag3", featureDataset_MK_CS_S);
	      FeatureCollection fd_MK_CS_S = layer_MK_CS_S_tam3_mag3.getFeatureCollectionWrapper();
	      FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
	      
	      layer_MK_CS_S_tam3_mag3.setEditable(true);
	      fd_MK_CS_S.clear();
	      fd_MK_CS_S.addAll(featuresMK_CS_S_tam3_mag3);
	      layer_MK_CS_S_tam3_mag3.setEditable(false);
	      
	      String nomelayerTemplate="MK_CS_S_tam3_mag3";
	      //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	      //  layer_MK_CS_S_tam3_mag3.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	     // }
	    }
	    
	    
	    
	    
	    /**
	     * Decrescente
	     */
	    
	    
	    Layer layer_MK_DS_S_tam1_mag1 =null;
	    if(featuresMK_DS_S_tam1_mag1.size() != 0){
	      FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
	      layer_MK_DS_S_tam1_mag1 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_tam1_mag1", featureDataset_MK_DS_S);
	      FeatureCollection fd_MK_DS_S = layer_MK_DS_S_tam1_mag1.getFeatureCollectionWrapper();
	      FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	      
	      layer_MK_DS_S_tam1_mag1.setEditable(true);
	      fd_MK_DS_S.clear();
	      fd_MK_DS_S.addAll(featuresMK_DS_S_tam1_mag1);
	      layer_MK_DS_S_tam1_mag1.setEditable(false);
	      
	      String nomelayerTemplate="MK_DS_S_tam1_mag1";
	      //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	      //  layer_MK_DS_S_tam1_mag1.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	      //}
	    }
	    
	    
	    
	    Layer layer_MK_DS_S_tam1_mag2 =null;
	    if(featuresMK_DS_S_tam1_mag2.size() != 0){
	      FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
	      layer_MK_DS_S_tam1_mag2 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_tam1_mag2", featureDataset_MK_DS_S);
	      FeatureCollection fd_MK_DS_S = layer_MK_DS_S_tam1_mag2.getFeatureCollectionWrapper();
	      FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	      
	      layer_MK_DS_S_tam1_mag2.setEditable(true);
	      fd_MK_DS_S.clear();
	      fd_MK_DS_S.addAll(featuresMK_DS_S_tam1_mag2);
	      layer_MK_DS_S_tam1_mag2.setEditable(false);
	      
	      String nomelayerTemplate="MK_DS_S_tam1_mag2";
	      ////if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	       // layer_MK_DS_S_tam1_mag2.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	      //}
	    }
	    
	    
	    
	    
	    Layer layer_MK_DS_S_tam1_mag3 =null;
	    if(featuresMK_DS_S_tam1_mag3.size() != 0){
	      FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
	      layer_MK_DS_S_tam1_mag3 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_tam1_mag3", featureDataset_MK_DS_S);
	      FeatureCollection fd_MK_DS_S = layer_MK_DS_S_tam1_mag3.getFeatureCollectionWrapper();
	      FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	      
	      layer_MK_DS_S_tam1_mag3.setEditable(true);
	      fd_MK_DS_S.clear();
	      fd_MK_DS_S.addAll(featuresMK_DS_S_tam1_mag3);
	      layer_MK_DS_S_tam1_mag3.setEditable(false);
	      
	      String nomelayerTemplate="MK_DS_S_tam1_mag3";
	     // if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	      //  layer_MK_DS_S_tam1_mag3.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	      //}
	    }
	    
	    
	      Layer layer_MK_DS_S_tam2_mag1 =null;
	      if(featuresMK_DS_S_tam2_mag1.size() != 0){
	        FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
	        layer_MK_DS_S_tam2_mag1 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_tam2_mag1", featureDataset_MK_DS_S);
	        FeatureCollection fd_MK_DS_S = layer_MK_DS_S_tam2_mag1.getFeatureCollectionWrapper();
	        FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	        
	        layer_MK_DS_S_tam2_mag1.setEditable(true);
	        fd_MK_DS_S.clear();
	        fd_MK_DS_S.addAll(featuresMK_DS_S_tam2_mag1);
	        layer_MK_DS_S_tam2_mag1.setEditable(false);
	        
	        String nomelayerTemplate="MK_DS_S_tam2_mag1";
	        //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	       //   layer_MK_DS_S_tam2_mag1.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	       // }
	      }
	    
	    
	    
	      Layer layer_MK_DS_S_tam2_mag2 =null;
	      if(featuresMK_DS_S_tam2_mag2.size() != 0){
	        FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
	        layer_MK_DS_S_tam2_mag2 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_tam2_mag2", featureDataset_MK_DS_S);
	        FeatureCollection fd_MK_DS_S = layer_MK_DS_S_tam2_mag2.getFeatureCollectionWrapper();
	        FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	        
	        layer_MK_DS_S_tam2_mag2.setEditable(true);
	        fd_MK_DS_S.clear();
	        fd_MK_DS_S.addAll(featuresMK_DS_S_tam2_mag2);
	        layer_MK_DS_S_tam2_mag2.setEditable(false);
	        
	        String nomelayerTemplate="MK_DS_S_tam2_mag2";
	        //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	       //   layer_MK_DS_S_tam2_mag2.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	       // }
	      }//
	    
	    
	      Layer layer_MK_DS_S_tam2_mag3 =null;
	      if(featuresMK_DS_S_tam2_mag3.size() != 0){
	        FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
	        layer_MK_DS_S_tam2_mag3 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_tam2_mag3", featureDataset_MK_DS_S);
	        FeatureCollection fd_MK_DS_S = layer_MK_DS_S_tam2_mag3.getFeatureCollectionWrapper();
	        FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	        
	        layer_MK_DS_S_tam2_mag3.setEditable(true);
	        fd_MK_DS_S.clear();
	        fd_MK_DS_S.addAll(featuresMK_DS_S_tam2_mag3);
	        layer_MK_DS_S_tam2_mag3.setEditable(false);
	        
	        String nomelayerTemplate="MK_DS_S_tam2_mag3";
	       // //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	      //   layer_MK_DS_S_tam2_mag3.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	       // }
	      }
	    
	      Layer layer_MK_DS_S_tam3_mag1 =null;
	      if(featuresMK_DS_S_tam3_mag1.size() != 0){
	        FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
	        layer_MK_DS_S_tam3_mag1 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_tam3_mag1", featureDataset_MK_DS_S);
	        FeatureCollection fd_MK_DS_S = layer_MK_DS_S_tam3_mag1.getFeatureCollectionWrapper();
	        FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	        
	        layer_MK_DS_S_tam3_mag1.setEditable(true);
	        fd_MK_DS_S.clear();
	        fd_MK_DS_S.addAll(featuresMK_DS_S_tam3_mag1);
	        layer_MK_DS_S_tam3_mag1.setEditable(false);
	        
	        String nomelayerTemplate="MK_DS_S_tam3_mag1";
	        //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	         // layer_MK_DS_S_tam3_mag1.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	        //}
	      }
	    
	    
	    
	      Layer layer_MK_DS_S_tam3_mag2 =null;
	      if(featuresMK_DS_S_tam3_mag2.size() != 0){
	        FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
	        layer_MK_DS_S_tam3_mag2 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_tam3_mag2", featureDataset_MK_DS_S);
	        FeatureCollection fd_MK_DS_S = layer_MK_DS_S_tam3_mag2.getFeatureCollectionWrapper();
	        FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	        
	        layer_MK_DS_S_tam3_mag2.setEditable(true);
	        fd_MK_DS_S.clear();
	        fd_MK_DS_S.addAll(featuresMK_DS_S_tam3_mag2);
	        layer_MK_DS_S_tam3_mag2.setEditable(false);
	        
	        String nomelayerTemplate="MK_DS_S_tam3_mag2";
	        //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	        //  layer_MK_DS_S_tam3_mag2.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	        //}
	      }
	    
	    
	      Layer layer_MK_DS_S_tam3_mag3 =null;
	      if(featuresMK_DS_S_tam3_mag3.size() != 0){
	        FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
	        layer_MK_DS_S_tam3_mag3 = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S_tam3_mag3", featureDataset_MK_DS_S);
	        FeatureCollection fd_MK_DS_S = layer_MK_DS_S_tam3_mag3.getFeatureCollectionWrapper();
	        FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
	        
	        layer_MK_DS_S_tam3_mag3.setEditable(true);
	        fd_MK_DS_S.clear();
	        fd_MK_DS_S.addAll(featuresMK_DS_S_tam3_mag3);
	        layer_MK_DS_S_tam3_mag3.setEditable(false);
	        
	        String nomelayerTemplate="MK_DS_S_tam3_mag3";
	        //if(PegarLayerEstilo.existeTemplate(this.pnt.getMgeralinterface(), nomelayerTemplate)){
	       //   layer_MK_DS_S_tam3_mag3.setStyles(PegarLayerEstilo.executar(this.pnt.getMgeralinterface(), nomelayerTemplate));
	       // }
	      }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	  
	  boolean salvarArquivo=true;
	  if(salvarArquivo){
	  File fdir=  new File(dirOutput);  
	  try {
	    /*if(layer_MK_NS != null){
	    SalvarLayerDiretorio.save(layer_MK_NS, fdir);
	    }
	    if(layer_MK_S != null){
	    SalvarLayerDiretorio.save(layer_MK_S, fdir);
	    }
	    if(layer_MK_CS != null){
	    SalvarLayerDiretorio.save(layer_MK_CS, fdir);
	    }
	    if(layer_MK_DS != null){
	    SalvarLayerDiretorio.save(layer_MK_DS, fdir);
	    }
	    if(layer_MK_CS_S != null){
	    SalvarLayerDiretorio.save(layer_MK_CS_S, fdir);
	    }
	    if(layer_MK_DS_S != null){
	    SalvarLayerDiretorio.save(layer_MK_DS_S, fdir);
	    }
	    if(layer_MK_CS_S_FDP != null){
	    SalvarLayerDiretorio.save(layer_MK_CS_S_FDP, fdir);
	    }
	    if(layer_MK_DS_S_FDP != null){
	    SalvarLayerDiretorio.save(layer_MK_DS_S_FDP, fdir);
	    }*/
	    String nomeShape=nomearq+"_"+testeEscolhido+"_CS_S_tam1_mag1";
	    if(layer_MK_CS_S_tam1_mag1 != null){
	      SalvarLayerDiretorio.save(layer_MK_CS_S_tam1_mag1, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_CS_S_tam1_mag2";
	    if(layer_MK_CS_S_tam1_mag2 != null){
	      SalvarLayerDiretorio.save(layer_MK_CS_S_tam1_mag2, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_CS_S_tam1_mag3";
	    if(layer_MK_CS_S_tam1_mag3 != null){
	      SalvarLayerDiretorio.save(layer_MK_CS_S_tam1_mag3, fdir,nomeShape);
	      }
	    
	    nomeShape=nomearq+"_"+testeEscolhido+"_CS_S_tam2_mag1";
	    if(layer_MK_CS_S_tam2_mag1 != null){
	      SalvarLayerDiretorio.save(layer_MK_CS_S_tam2_mag1, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_CS_S_tam2_mag2"; 
	    if(layer_MK_CS_S_tam2_mag2 != null){
	      SalvarLayerDiretorio.save(layer_MK_CS_S_tam2_mag2, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_CS_S_tam2_mag3";
	    if(layer_MK_CS_S_tam2_mag3 != null){
	      SalvarLayerDiretorio.save(layer_MK_CS_S_tam2_mag3, fdir,nomeShape);
	      }
	    
	    nomeShape=nomearq+"_"+testeEscolhido+"_CS_S_tam3_mag1";
	    if(layer_MK_CS_S_tam3_mag1 != null){
	      SalvarLayerDiretorio.save(layer_MK_CS_S_tam3_mag1, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_CS_S_tam3_mag2";
	    if(layer_MK_CS_S_tam3_mag2 != null){
	      SalvarLayerDiretorio.save(layer_MK_CS_S_tam3_mag2, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_CS_S_tam3_mag3";
	    if(layer_MK_CS_S_tam3_mag3 != null){
	      SalvarLayerDiretorio.save(layer_MK_CS_S_tam3_mag3, fdir,nomeShape);
	      }
	    
	    
	    
	    nomeShape=nomearq+"_"+testeEscolhido+"_DS_S_tam1_mag1";
	    if(layer_MK_DS_S_tam1_mag1 != null){
	      SalvarLayerDiretorio.save(layer_MK_DS_S_tam1_mag1, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_DS_S_tam1_mag2";
	    if(layer_MK_DS_S_tam1_mag2 != null){
	      SalvarLayerDiretorio.save(layer_MK_DS_S_tam1_mag2, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_DS_S_tam1_mag3";
	    if(layer_MK_DS_S_tam1_mag3 != null){
	      SalvarLayerDiretorio.save(layer_MK_DS_S_tam1_mag3, fdir,nomeShape);
	      }
	    
	    nomeShape=nomearq+"_"+testeEscolhido+"_DS_S_tam2_mag1";
	    if(layer_MK_DS_S_tam2_mag1 != null){
	      SalvarLayerDiretorio.save(layer_MK_DS_S_tam2_mag1, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_DS_S_tam2_mag2";
	    if(layer_MK_DS_S_tam2_mag2 != null){
	      SalvarLayerDiretorio.save(layer_MK_DS_S_tam2_mag2, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_DS_S_tam2_mag3";
	    if(layer_MK_DS_S_tam2_mag3 != null){
	      SalvarLayerDiretorio.save(layer_MK_DS_S_tam2_mag3, fdir,nomeShape);
	      }
	    
	    nomeShape=nomearq+"_"+testeEscolhido+"_DS_S_tam3_mag1";
	    if(layer_MK_DS_S_tam3_mag1 != null){
	      SalvarLayerDiretorio.save(layer_MK_DS_S_tam3_mag1, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_DS_S_tam3_mag2";
	    if(layer_MK_DS_S_tam3_mag2 != null){
	      SalvarLayerDiretorio.save(layer_MK_DS_S_tam3_mag2, fdir,nomeShape);
	      }
	    nomeShape=nomearq+"_"+testeEscolhido+"_DS_S_tam3_mag3";
	    if(layer_MK_DS_S_tam3_mag3 != null){
	      SalvarLayerDiretorio.save(layer_MK_DS_S_tam3_mag3, fdir,nomeShape);
	      }
	    
	  } catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	  }
	  
	  }
	  
	  
	  
	  
	  
	   //Messages.informMsg("Layer (arquivo .shp) criado com sucesso");
	    
	    
	    
	    
	    
	  
	  
	}





	public ResultadoEstacMagnitudeTamanho getResEstacMagnitudeTamanho() {
	  return resEstacMagnitudeTamanho;
	}





	public void setResEstacMagnitudeTamanho(ResultadoEstacMagnitudeTamanho resEstacMagnitudeTamanho) {
	  this.resEstacMagnitudeTamanho = resEstacMagnitudeTamanho;
	}
	
	
}
