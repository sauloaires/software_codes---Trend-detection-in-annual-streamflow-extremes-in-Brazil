package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import types.DadoTemporal;
import types.InventarioHidrologico;
import types.SerieTemporal;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;

/*import org.snirh.extremos_unb.deteccao.gui.PanelImportarDados;
import org.snirh.extremos_unb.deteccao.io.LeituraDATEstacionaridade;
import org.snirh.extremos_unb.tipos.DadoTemporal;
import org.snirh.extremos_unb.tipos.InventarioHidrologico;
import org.snirh.extremos_unb.tipos.SerieTemporal;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.extremos_unb.tipos.VariavelHidrologica;
import org.snirh.extremos_unb.util.Messages;*/

public class MAR_ImportDataDAO {
	private ProgressMonitor progressMonitor;
    private JButton startButton;
    private JTextArea taskOutput;
    private ArrayList<VariavelHidrologica> vhidExistente;
    private boolean adicionar_estac;
    private int nseries;
    private String tabulacao;
    private int nestac;
    
    

    
    
    
//public ArrayList<VariavelHidrologica> leituraDATVarHidSemBarraProgresso(final SimulationDataExtremos simulationData,PanelImportarDados pnd) {
    public ArrayList<VariavelHidrologica> leituraDATVarHidSemBarraProgresso(String dir,String nomearq) {
		
		ArrayList<VariavelHidrologica> variaveishidrologicasSheet=new ArrayList<VariavelHidrologica>();
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	   	 //String dir=simulationData.getDataDirBD();
	   	 //String nomearq=simulationData.getFilenameBD();
	   	 if(nomearq.contains(".dat") == false){
	   	    	nomearq=nomearq+".dat";	
	   	    }
	   	    
	   	String modelDir=dir+nomearq;
	   	BufferedReader file = null;
		
		int nLinhasArquivo = 0;
		try{
			file = new BufferedReader(new FileReader(modelDir));
			File arquivo = new File(modelDir);
			LineNumberReader lineRead = new LineNumberReader(new FileReader(arquivo));
			lineRead.skip(arquivo.length());
			nLinhasArquivo = lineRead.getLineNumber();
			
			StringTokenizer tok2 = null;
			int nseries=0;
		
			ArrayList<String> strInvHidDAT=new ArrayList<String>();
			int nLinhasArquivoInventario=15;
			
			for(int i=0;i<nLinhasArquivoInventario;i++){
			strInvHidDAT.add(file.readLine());	
			}
			
			ArrayList<InventarioHidrologico> invhidTot=this.SetarInventarioVariavelHidrologica(strInvHidDAT);
			
			
			for(int i=0;i<this.nseries;i++){
				SerieTemporal serietemp = new SerieTemporal ();
				ArrayList<DadoTemporal> dados = new ArrayList<DadoTemporal>();
				VariavelHidrologica vhid=new VariavelHidrologica();
				 vhid.setInvhidro(invhidTot.get(i));
				 vhid.setSerietemporal(serietemp);
				 vhid.getSerietemporal().setDados(dados);
				 variaveishidrologicasSheet.add(vhid);	
				
			}
			
			//simulationData.setVariaveisHidr(variaveishidrologicasSheet);
			
			int nlinhasVazio=6;
			for(int i=0;i<nlinhasVazio;i++){
			file.readLine();	
			}
			
			//setProgress(0);
			String textointerface= "Iniciando leitura  a estação  ";
		   // publish(textointerface);
			int nLinhasSeries=nLinhasArquivo-(nLinhasArquivoInventario+nlinhasVazio);
			int tot=this.nseries;
			int progress = 0;
	        //setProgress(0);
			int totlinhas=nLinhasSeries;
			for(int i=0;i<nLinhasSeries;i++){
			    	
				
				    if(i==nLinhasSeries-1){
				    System.out.println();
				    }
				    if(i==920){
					    System.out.println();
					 }
				    
			    	String linhaStr=file.readLine();
			    	String[] str = linhaStr.split(this.tabulacao);
			    	int i1=0;
					for(int j=0;j<this.nseries;j++){
						  double progress2 = ((i+1)*1.0/(totlinhas*1.0))*100;
					      progress=(int) progress2;
					     // setProgress(Math.min(progress, 100));
					      textointerface= "Aguarde..lendo  o registro  "+(i+1)+"/"+totlinhas+"  da  estação  "+(j+1)+"/"+tot+"";
					    //  publish(textointerface);
						  System.out.println(textointerface);
						
						  if(i == 1735 && j == 29){
							  System.out.println();
						  }
						  
						String vdata=str[i1];
						String vval=str[i1+1];
						//varData.add(str[i1]);
						//varValor.add(str[i1+1]);
						DadoTemporal dado=new DadoTemporal();
						if(vdata.equals("-99999")){
							System.out.println(vdata);
						}
						if(vdata.equals("")){
							System.out.println(vdata);
						}
						    if(!vdata.equals("null") && !vdata.equals("") && !vdata.equals("-99999")){
						    Date utilDate = new Date();
						    String dataStrdia= vdata;	  
					  		try {
							utilDate = formatter.parse(dataStrdia);
							} catch (ParseException e) {
							e.printStackTrace();
							}
					  		//dado.setData(utilDate);
					  		/**
					  		 * Saulo - 19/11/2015
					  		 */
					  		dado.setData(vdata);
					  		if(!(vval.equals("null") || vval.equals(""))){
					  		dado.setValor(Double.parseDouble((vval)));	
					  		}else{
					  		dado.setValor(-99999.0);
					  		}
					  		
					  		variaveishidrologicasSheet.get(j).getSerietemporal().getDados().add(dado);
						    
						    }
				
						        
						    //dados.add(dado);
											
						i1=i1+2;
					}
			    	
			    	
			    }
			
			    //textointerface= "Aguarde..lendo  o registro  "+totlinhas+"/"+totlinhas+"  da  estação  "+tot+"/"+tot+"";
		        //publish(textointerface);
		        this.nestac=tot;
			
			for(int i=0;i<variaveishidrologicasSheet.size();i++){
				 int ndados=variaveishidrologicasSheet.get(i).getSerietemporal().getDados().size();
				 Collections.sort(variaveishidrologicasSheet.get(i).getSerietemporal().getDados());
				 Calendar dataInicialSerie= Calendar.getInstance();
				 Calendar dataFinalSerie= Calendar.getInstance();
				
				 dataInicialSerie.setTime(variaveishidrologicasSheet.get(i).getSerietemporal().getDados().get(0).getData());
				 dataFinalSerie.setTime(variaveishidrologicasSheet.get(i).getSerietemporal().getDados().get(ndados-1).getData());
				 
				 Date dini=dataInicialSerie.getTime();
				 String dataInistr=formatter.format(dini);
				 variaveishidrologicasSheet.get(i).getInvhidro().setDataInicialstr(dataInistr);
				 
				 Date dfim=dataFinalSerie.getTime();
				 String dataFimstr=formatter.format(dfim);
				 variaveishidrologicasSheet.get(i).getInvhidro().setDataFinalstr(dataFimstr);
				 
				 
				 variaveishidrologicasSheet.get(i).getSerietemporal().setDataInicialSerie(dataInicialSerie);
				 variaveishidrologicasSheet.get(i).getSerietemporal().setDataFinalSerie(dataFinalSerie);
				 variaveishidrologicasSheet.get(i).getSerietemporal().setaMapaStrDadoTemporal();
				
			}
			
			
		//	this.simulationData.setVariaveisHidr(variaveishidrologicasSheet);
		
		}catch(IOException e){
			e.printStackTrace();
		}
		try {
		file.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		
		return variaveishidrologicasSheet;
		
		
		
	}	
	


private ArrayList<InventarioHidrologico> SetarInventarioVariavelHidrologica(ArrayList<String> strInvHidDAT){
	ArrayList<InventarioHidrologico> inventarioDAT=new ArrayList<InventarioHidrologico>();
	
	this.tabulacao="	";
	if(strInvHidDAT.get(0).contains(";")){
	this.tabulacao=";";	
	}
	
	String[] str2 =strInvHidDAT.get(0).split(tabulacao);
	int nVariaveis=strInvHidDAT.size();
	this.nseries=str2.length/2;
	Object [][] varInventario=new Object[this.nseries][nVariaveis];
	
	for (int k = 0; k < nVariaveis; k++){
		String[] str =strInvHidDAT.get(k).split(tabulacao);
		int i1=0;
		for(int i=0;i<this.nseries;i++){
		varInventario[i][k]=str[i1+1];
		i1=i1+2;
		}
	}
	
	 int tot=this.nseries;
	 int progress = 0;
    // setProgress(0);
	for(int j=0;j<this.nseries;j++){
		InventarioHidrologico invhid = new InventarioHidrologico();
		for (int k = 0; k < nVariaveis; k++){
			 
			  double progress2 = ((j+1)*1.0/(tot*1.0))*100;
		      progress=(int) progress2;
		     // setProgress(Math.min(progress, 100));
		      String textointerface= "Aguarde.. verificando  a estação  "+(j+1)+"/"+tot+"";
		     // publish(textointerface);	
	    	 
		      
		      if(k == 0){
        		     invhid.setBaciaCodigo(Integer.parseInt((String) varInventario[j][k]));
        		    
       		  } else if(k == 1){
       			  invhid.setSubBaciaCodigo(Integer.parseInt((String)varInventario[j][k]));
       			
       		  }else if(k == 2){
       			 invhid.setEstacao_codigo((String)varInventario[j][k]);
       			
       		  } else if(k == 3){
       			  invhid.setLatitude(Double.parseDouble((String)varInventario[j][k]));
       			
       		  }else if(k == 4){
       			 invhid.setLongitude(Double.parseDouble((String)varInventario[j][k]));
       			
       		  } else if(k == 5){
       			 invhid.setAltitude(Double.parseDouble((String)varInventario[j][k]));
       			
       		 }else if(k == 6){
       			invhid.setAreaDrenagem(Double.parseDouble((String)varInventario[j][k]));
       			
       		 } else if(k == 7){
       			 invhid.setOrigemSerie(Integer.parseInt((String)varInventario[j][k]));
       			
       		 }else if(k == 8){
       			 invhid.setDescricaoOrigemSerie((String) varInventario[j][k]);
       			
       		 } else if(k == 9){
       			 invhid.setDiscretizaçãoTemporária((String) varInventario[j][k]);
       			
       		 }else if(k == 10){
       			 invhid.setTipodeDado((String) varInventario[j][k]);
       			
       		 } else if(k ==11){
       			 invhid.setMesAnoHidro(Integer.parseInt((String)varInventario[j][k]));
       			
       		 }   else if(k ==12){
       			invhid.setNomedaEstacao(((String)varInventario[j][k]));
      			
       		 }  else if(k ==13){
       			invhid.setNomedoRio(((String)varInventario[j][k]));
       			
       		 }else if(k ==14){
       			invhid.setMunicipio(((String)varInventario[j][k]));
       			
       		 }  
			
		}
		inventarioDAT.add(invhid);
	}
	return inventarioDAT;
	
	

	
	
}






/*public static void leituraDATVarHid(final SimulationDataExtremos simulationData,PanelImportarDados pnd) {
	
	LeituraDATEstacionaridade mbt= new LeituraDATEstacionaridade(simulationData,pnd);
    mbt.addPropertyChangeListener(pnd);
	mbt.execute();
	// Messages.informMsg("Leitura efetuada com sucesso");
 }


public static void leituraDATVarHid(final SimulationDataExtremos simulationData,PanelImportarDados pnd,ArrayList<VariavelHidrologica> vhid) {
	
	LeituraDATEstacionaridade mbt= new LeituraDATEstacionaridade(simulationData,pnd,vhid);
    mbt.addPropertyChangeListener(pnd);
	mbt.execute();
	// Messages.informMsg("Leitura efetuada com sucesso");
 }*/






}














