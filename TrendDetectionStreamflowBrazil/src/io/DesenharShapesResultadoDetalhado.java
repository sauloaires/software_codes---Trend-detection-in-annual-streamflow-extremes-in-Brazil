package io;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

/*import org.snirh.extremos_unb.deteccao.testes.ResultEstacionaridade;
import org.snirh.extremos_unb.tipos.VariavelHidrologica;
import org.snirh.extremos_unb.util.Messages;
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
import gui.tableResultTrendTeste.GuiResultAllGauges;
import types.ResultEstacionaridade;
import types.SNIRHPlugInSettings;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;
import util.Messages;

public class DesenharShapesResultadoDetalhado {
	private SimulationDataExtremos simulationData;
	
	private PanelTrendDetectionStreamflowBrazil pnt;
	//protected PlugInContext context = null;
	//protected Category category = null;
	public DesenharShapesResultadoDetalhado(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt) {
		this.simulationData = simulationData;
		this.pnt=pnt;
	}
	
	public void execute(String testeEscolhido,String dir){
		
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
		
		//this.context= new 
		/*this.category = context.getLayerManager().getCategory(SNIRHPlugInSettings.resultLayerCategory());
		
		if (this.category == null) {
			this.context.getLayerManager().addCategory(
					SNIRHPlugInSettings.resultLayerCategory(), 0);
			this.category = this.context.getLayerManager().getCategory(
					SNIRHPlugInSettings.resultLayerCategory());
		}*/
		
		FeatureSchema fs = new FeatureSchema();
		fs.addAttribute("Geometry", AttributeType.GEOMETRY);
		fs.addAttribute("ID", AttributeType.STRING);
		fs.addAttribute("CODIGO", AttributeType.STRING);
		fs.addAttribute("LONG", AttributeType.DOUBLE);
		fs.addAttribute("LAT", AttributeType.DOUBLE);
		fs.addAttribute("AREA", AttributeType.DOUBLE);
		
		for (int i = 0; i < ntestes; i++) {
			for (int k = 0; k < ncolum; k++) {
				if(k <=1 || k==4 || k==6 || k==7){
				fs.addAttribute(nometeste[i]+"_"+columnNames[k], AttributeType.STRING);	
				}else{
				fs.addAttribute(nometeste[i]+"_"+columnNames[k], AttributeType.DOUBLE);	
				}
			}
		}
		
		//FeatureDataset featureDataset = new FeatureDataset(fs);
		//Layer layer = context.addLayer(category.getName(), "Res_Estac_Total", featureDataset);
		//FeatureCollection fd = layer.getFeatureCollectionWrapper();
		//FeatureSchema fs2 = fd.getFeatureSchema();
		//ArrayList<Feature> features = new ArrayList<Feature>();
		
		String nomearq=this.simulationData.getFilenameBD();
		
		if(nomearq.contains(".dat")){
			nomearq=nomearq.replace(".dat","");
		}
		
		//String dir=this.simulationData.getDataDirBD();
		
		
		
		
		
		
		//String dir="C:\\OpenJump150\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\resultados\\RH_SFR\\CLASSICO\\shapes\\";
		//String dir="C:\\OpenJump150\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\resultados\\RH_SFR\\PW\\shapes\\";
		//String dir="C:\\OpenJump150\\PROJETO E IMPLEMENTACOES\\EXTREMOS_UNB\\resultados\\RH_SFR\\TFPW\\shapes\\";
		
		
		
		
		//this.simulationData.setFilenameBD(filename);
		//this.simulationData.setDataDirBD(dir);	
		
		FeatureDataset featureDataset_MK_NS = new FeatureDataset(fs);
		//Layer layer_MK_NS = context.addLayer(category.getName(),nomearq+"_"+testeEscolhido+"_NS", featureDataset_MK_NS);
		Layer layer_MK_NS =new Layer();
		layer_MK_NS.setFeatureCollection(featureDataset_MK_NS);
		//FeatureCollection fd_MK_NS = new FeatureCollection();
		//layer_MK_NS.set
		FeatureCollection fd_MK_NS = layer_MK_NS.getFeatureCollectionWrapper();
		FeatureSchema fs2_MK_NS = fd_MK_NS.getFeatureSchema();
		ArrayList<Feature> featuresMK_NS = new ArrayList<Feature>();
				
		FeatureDataset featureDataset_MK_S = new FeatureDataset(fs);
		//Layer layer_MK_S = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_S", featureDataset_MK_S);
		Layer layer_MK_S=new Layer();
		layer_MK_S.setFeatureCollection(featureDataset_MK_S);
		FeatureCollection fd_MK_S = layer_MK_S.getFeatureCollectionWrapper();
		FeatureSchema fs2_MK_S = fd_MK_S.getFeatureSchema();
		ArrayList<Feature> featuresMK_S = new ArrayList<Feature>();
		
		
		ArrayList<Feature> featuresMK_CS = new ArrayList<Feature>();
		ArrayList<Feature> featuresMK_DS = new ArrayList<Feature>();
		ArrayList<Feature> featuresMK_CS_S = new ArrayList<Feature>();
		ArrayList<Feature> featuresMK_DS_S = new ArrayList<Feature>();
		
		
		
		for(int igauges=0;igauges<this.simulationData.getVariaveisHidr().size();igauges++){
	   	   
			
			if(this.simulationData.getVariaveisHidr().get(igauges).isSelecionada() && this.simulationData.getVariaveisHidr().get(igauges).getResultestacionaridade().size() > 0)	{		
	   	   
	   	    String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(igauges).getInvhidro().getEstacao_codigo());
	   	    ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
	   	    resultestacionaridade = this.simulationData.getVariaveisHidr().get(igauges).getResultestacionaridade();
	   	    	   	   
	   	    VariavelHidrologica vhid =this.simulationData.getVariaveisHidr().get(igauges);
	   	    
	   	    
	   	  //  if (layer==null || vhid ==null) return;
			
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
						
				
			
			//featMK.
			
			DecimalFormatSymbols dc = new DecimalFormatSymbols();
			dc.setDecimalSeparator('.');
			String strange = "0.00";
			DecimalFormat myFormatter = new DecimalFormat(strange, dc);		
			for (int i = 0; i < ntestes; i++) {
				
				
					
					if(nometeste[i] == testeEscolhido){
						featMK.setAttribute(nometeste[i]+"_"+columnNames[0], nometipoteste[0]);	
					}
				
			    for(int j=0;j<resultestacionaridade.size();j++){
			    	if(resultestacionaridade.get(j).getNometeste().equals(nometeste[i])){
			    				    				    	
						    	if(nometeste[i] == testeEscolhido){
									featMK.setAttribute(nometeste[i]+"_"+columnNames[2], resultestacionaridade.get(j).getEstatteste());
									featMK.setAttribute(nometeste[i]+"_"+columnNames[3], resultestacionaridade.get(j).getValorcriticoteste());
							    	featMK.setAttribute(nometeste[i]+"_"+columnNames[4], resultestacionaridade.get(j).getResultadoteste());	
							    	
							    	if(resultestacionaridade.get(j).getSentidoTendencia() == null){
							    		featMK.setAttribute(nometeste[i]+"_"+columnNames[7],resultestacionaridade.get(j).getSentidoMediaRecente());
							    	}else{
							    		featMK.setAttribute(nometeste[i]+"_"+columnNames[7],resultestacionaridade.get(j).getSentidoTendencia());	
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
			
			if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)")){
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
				featuresMK_CS_S.add(featMK);
			}else if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)") && (resMKDIR.equals("Decrescente") || resMKDIR.equals("Menor"))){
				featuresMK_DS_S.add(featMK);
			}
								
			}		
	   	  }
		
		
		
	   	 
		}
		
		
		
		if(featuresMK_NS.size() != 0){
			layer_MK_NS.setEditable(true);
			fd_MK_NS.clear();
			fd_MK_NS.addAll(featuresMK_NS);
			layer_MK_NS.setEditable(false);
			
		}
		
		if(featuresMK_S.size() != 0){
			layer_MK_S.setEditable(true);
			fd_MK_S.clear();
			fd_MK_S.addAll(featuresMK_S);
			layer_MK_S.setEditable(false);
		}
		
		Layer layer_MK_CS = new Layer();
		if(featuresMK_CS.size() != 0){
			FeatureDataset featureDataset_MK_CS = new FeatureDataset(fs);
			//layer_MK_CS = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS", featureDataset_MK_CS);
			layer_MK_CS.setFeatureCollection(featureDataset_MK_CS);
			FeatureCollection fd_MK_CS = layer_MK_CS.getFeatureCollectionWrapper();
			FeatureSchema fs2_MK_CS = fd_MK_CS.getFeatureSchema();
			layer_MK_CS.setEditable(true);
			fd_MK_CS.clear();
			fd_MK_CS.addAll(featuresMK_CS);
			layer_MK_CS.setEditable(false);
		}
		Layer layer_MK_DS=new Layer();
		if(featuresMK_DS.size() != 0){
			FeatureDataset featureDataset_MK_DS = new FeatureDataset(fs);
			//layer_MK_DS = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS", featureDataset_MK_DS);
			layer_MK_DS.setFeatureCollection(featureDataset_MK_DS);
			FeatureCollection fd_MK_DS = layer_MK_DS.getFeatureCollectionWrapper();
			FeatureSchema fs2_MK_DS = fd_MK_DS.getFeatureSchema();
			
			layer_MK_DS.setEditable(true);
			fd_MK_DS.clear();
			fd_MK_DS.addAll(featuresMK_DS);
			layer_MK_DS.setEditable(false);
		}
		Layer layer_MK_CS_S =new Layer();
		if(featuresMK_CS_S.size() != 0){
			FeatureDataset featureDataset_MK_CS_S = new FeatureDataset(fs);
			//layer_MK_CS_S = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_CS_S", featureDataset_MK_CS_S);
			layer_MK_CS_S.setFeatureCollection(featureDataset_MK_CS_S);
			FeatureCollection fd_MK_CS_S = layer_MK_CS_S.getFeatureCollectionWrapper();
			FeatureSchema fs2_MK_CS_S = fd_MK_CS_S.getFeatureSchema();
			
			layer_MK_CS_S.setEditable(true);
			fd_MK_CS_S.clear();
			fd_MK_CS_S.addAll(featuresMK_CS_S);
			layer_MK_CS_S.setEditable(false);
		}
		
		Layer layer_MK_DS_S =new Layer();
		if(featuresMK_DS_S.size() != 0){
			FeatureDataset featureDataset_MK_DS_S = new FeatureDataset(fs);
			//layer_MK_DS_S = context.addLayer(category.getName(), nomearq+"_"+testeEscolhido+"_DS_S", featureDataset_MK_DS_S);
			layer_MK_DS_S.setFeatureCollection(featureDataset_MK_DS_S);
			FeatureCollection fd_MK_DS_S = layer_MK_DS_S.getFeatureCollectionWrapper();
			FeatureSchema fs2_MK_DS_S = fd_MK_DS_S.getFeatureSchema();
			layer_MK_DS_S.setEditable(true);
			fd_MK_DS_S.clear();
			fd_MK_DS_S.addAll(featuresMK_DS_S);
			layer_MK_DS_S.setEditable(false);
		}
		
		boolean salvarArquivo=true;
		if(salvarArquivo){
		File fdir=	new File(dir);	
		String nomeShape="layer_MK_NS";
		try {
		  if(layer_MK_NS != null){
		    SalvarLayerDiretorio.save(layer_MK_NS, fdir,nomeShape);
		  }
		  nomeShape="layer_MK_S";
		  if(layer_MK_S != null){
			SalvarLayerDiretorio.save(layer_MK_S, fdir,nomeShape);
		  }
		  nomeShape="layer_MK_CS";
		  if(layer_MK_CS != null){
			SalvarLayerDiretorio.save(layer_MK_CS, fdir,nomeShape);
		  }
		  nomeShape="layer_MK_DS";
		  if(layer_MK_DS != null){
			SalvarLayerDiretorio.save(layer_MK_DS, fdir,nomeShape);
		  }
		  nomeShape="layer_MK_CS_S";
		  if(layer_MK_CS_S != null){
			SalvarLayerDiretorio.save(layer_MK_CS_S, fdir,nomeShape);
		  }
		  nomeShape="layer_MK_DS_S";
		  if(layer_MK_DS_S != null){
			SalvarLayerDiretorio.save(layer_MK_DS_S, fdir,nomeShape);
		  }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		
		
		
		
		 Messages.informMsg("Layer (arquivo .shp) criado com sucesso");
	}	
	
	
	
}
