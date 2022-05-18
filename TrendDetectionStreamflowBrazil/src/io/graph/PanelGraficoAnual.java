package io.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
/*import org.snirh.extremos_unb.deteccao.testes.Outliers;
import org.snirh.extremos_unb.tipos.DadoTemporal;
import org.snirh.extremos_unb.tipos.SimulationDataExtremos;
import org.snirh.extremos_unb.tipos.VariavelHidrologica;*/

import flanagan.analysis.Regression;
import types.DadoTemporal;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;
import util.Outliers;

public class PanelGraficoAnual extends JPanel{

	private SimulationDataExtremos simulationData;
    private static final long serialVersionUID = 1L;
	private VariavelHidrologica vhid;
	private JDesktopPane panelButtons;
	private JTable tblDailyData;
	private JButton btnChart;
	private JButton btnClose;
	private JFileChooser chooser;
	//ArrayList<ResultDistrib> resultmodelosdistrib;
	//ResultDistrib rdposplot;
	private String codigo;
	private ArrayList<Double> serieX;
	private ArrayList<Double> serieY;
	private ArrayList<Double> serieY1;
	private ArrayList<Double> serieY2;
	
	private Map<String,DadoTemporal> serieYMapa;
	private Map<String,DadoTemporal> serieY1Mapa;
	private Map<String,DadoTemporal> serieY2Mapa;
		
	private int anodiv;
		
	
public PanelGraficoAnual(SimulationDataExtremos simulationData,VariavelHidrologica vhid,String codigo,Map<String,DadoTemporal> serieY,int anodiv){
		
		this.vhid=vhid;
		this.codigo=codigo;
		this.serieYMapa=serieY;
		this.anodiv=anodiv;
		this.simulationData=simulationData;
		this.serieY=new ArrayList<Double>();
		this.serieX=new ArrayList<Double>();
		Set<String> chaves =  this.serieYMapa.keySet();
	  	for (String data : chaves){
	  	DadoTemporal dado = this.serieYMapa.get(data);
	  	this.serieY.add(dado.getValor());
	  	String [] datastr=data.split("/");
	  	Double dataAno=Double.parseDouble(datastr[2]);
	  	this.serieX.add(dataAno);
	  	}
		
		this.criarMostrarGrafico(); 
		
	}

	public PanelGraficoAnual(VariavelHidrologica vhid,String codigo,Map<String,DadoTemporal> serieY,int anodiv){
		
		this.vhid=vhid;
		this.codigo=codigo;
		this.serieYMapa=serieY;
		this.anodiv=anodiv;
		
		this.serieY=new ArrayList<Double>();
		this.serieX=new ArrayList<Double>();
		Set<String> chaves =  this.serieYMapa.keySet();
	  	for (String data : chaves){
	  	DadoTemporal dado = this.serieYMapa.get(data);
	  	this.serieY.add(dado.getValor());
	  	String [] datastr=data.split("/");
	  	Double dataAno=Double.parseDouble(datastr[2]);
	  	this.serieX.add(dataAno);
	  	}
		
		this.criarMostrarGrafico(); 
		
	}
	
	
	
	private void criarMostrarGrafico() {
		this.setBounds(100, 200, 800, 600);
		this.setPreferredSize(new Dimension(800, 600));
		this.setBackground(Color.LIGHT_GRAY);
	  	XYDataset xydataset = this.criarSerieDadosMaximo();
		 JFreeChart jfreechart = this.criarGrafico(xydataset, this.codigo);
		 ChartPanel chartpanel = new ChartPanel(jfreechart);
		 chartpanel.setPreferredSize(new Dimension(800, 600));
		 this.add(chartpanel);
	}
	
	
	
	private  XYDataset criarSerieDadosMaximo(){
	  	XYSeriesCollection xyseriescollection = new XYSeriesCollection();
	  	//String nomeseries="Maximo Anual";  
	  	String nomeEstat=this.simulationData.getTipoEstatisticaSelecionadaEstacionaridade();
	  	String especificidadeEstatistica="";
	  	String unidadeVariavel="m³/s";
	  	if(nomeEstat.equals("PERMANENCIA") ){
			  especificidadeEstatistica=String.valueOf(this.simulationData.getPercentil()+" %");
		  }else if(nomeEstat.equals("MMmin")){
			  especificidadeEstatistica=String.valueOf(this.simulationData.getTamanhoMediaMovelMin()+ " dias");
		  }else if(nomeEstat.equals("NDlim")){
			  especificidadeEstatistica=String.valueOf(this.simulationData.getTamanhoMediaMovelMin()+" "+unidadeVariavel);
		  }
		  
		  
		 String nomeseries= nomeEstat+" "+especificidadeEstatistica;
	  	//String nomeseries=nomeEstat;
	  	XYSeries xyseries = new XYSeries(nomeseries);  
	  	ArrayList <Integer> anoSerie=new ArrayList <Integer>();
	  	
	  	
	  	for (int i = 0; i < this.serieY.size(); i++){
	  	    double ano=this.serieX.get(i);
	  		int anoInt=(int) ano;
	  		anoSerie.add(anoInt);
	  		xyseries.add(this.serieX.get(i), this.serieY.get(i));
	  	}
	  	Collections.sort(anoSerie);
	  	DescriptiveStatistics dsctot = new DescriptiveStatistics();
		for(int j=0;j<this.serieY.size();j++){
		dsctot.addValue((Double) xyseries.getY(j));
		}
		
		   double [] xdata=new double [serieX.size()];
		   double [] xdata2=new double [serieX.size()];
		   double [] ydata=new double [serieX.size()];
		   for (int j = 0; j < serieX.size(); j++) {
			   xdata[j] =serieX.get(j);
			   xdata2[j] =j+1;
			   ydata[j] =serieY.get(j);
		   }
		   

		
		int anoIniSerie1=anoSerie.get(0);
		int anoFimSerie1=this.anodiv-1;
		
		int anoFimSerie2=anoSerie.get(anoSerie.size()-1);
		
		double anodivnovo=(anoIniSerie1+anoFimSerie2)/2.0;
		if((anoIniSerie1+anoFimSerie2)%2 != 0){
			//anodivnovo++;	
		}
		
		double anoIniSerie2=anodivnovo;
		
		
		double mediatot=dsctot.getMean();
		double mediaMaisDP= mediatot+dsctot.getStandardDeviation();
		double mediaMenosDP= mediatot-dsctot.getStandardDeviation();
			
		Regression reg = new Regression(xdata, ydata);
		reg.linear();
		Double b=reg.getBestEstimates()[1];
		Double a=reg.getBestEstimates()[0];
		Double b_graus=Math.toDegrees(Math.atan(b));
		
		
		Regression reg2 = new Regression(xdata2, ydata);
		reg2.linear();
		Double b2=reg2.getBestEstimates()[1];
		Double b_graus2=Math.toDegrees(Math.atan(b2));
		System.out.println(b_graus+"   "+b_graus2);
		
		Double y1=a+(b*anoIniSerie1);
		Double y2=a+(b*anoFimSerie2);
		Double anguloInclinacaoteste=Math.toDegrees(Math.atan(Math.pow(3, 0.5)));
		anguloInclinacaoteste=Math.toDegrees(Math.atan(-1));
		System.out.println(anguloInclinacaoteste);
			
		XYSeries xyseries1 = new XYSeries("Total Mean");
		xyseries1.add(anoIniSerie1, mediatot);
		xyseries1.add(anoFimSerie2, mediatot);
								
		XYSeries xyseries4 = new XYSeries("Mean+SD");
		xyseries4.add(anoIniSerie1, mediaMaisDP);
		xyseries4.add(anoFimSerie2, mediaMaisDP);
		
		XYSeries xyseries5 = new XYSeries("Mean-SD");
		xyseries5.add(anoIniSerie1, mediaMenosDP);
		xyseries5.add(anoFimSerie2, mediaMenosDP);
		
		XYSeries xyseries6 = new XYSeries("Trend");
		xyseries6.add(anoIniSerie1,y1);
		xyseries6.add(anoFimSerie2, y2);
		
		XYSeries xyseries7 = new XYSeries("Split Half");
		xyseries7.add(anoIniSerie2,0.0);
		xyseries7.add(anoIniSerie2, dsctot.getMax());
		  	
		xyseriescollection.addSeries(xyseries);
	  	xyseriescollection.addSeries(xyseries1);
	  	xyseriescollection.addSeries(xyseries4);
	  	xyseriescollection.addSeries(xyseries5);
	  	xyseriescollection.addSeries(xyseries6);
	  	xyseriescollection.addSeries(xyseries7);
	  	
	  	
	  	
	  	
	  	Outliers ols = new Outliers(this.serieY);
		ols.testeGrubbsBeck();
		double xinfGB=ols.getLiminf();
		double xsupGB=ols.getLimsup();
		System.out.println("liminf = "+xinfGB+"  limsup = "+xsupGB);
		
		Outliers olsBP = new Outliers(this.serieY);							
		olsBP.testeBoxPlot();
		double xinfBP=olsBP.getLiminf();
		double xsupBP=olsBP.getLimsup();
		System.out.println("liminf = "+xinfBP+"  limsup = "+xsupBP);
	  	
	  	
		XYSeries xyseries8 = new XYSeries("lower limit-GB");
		xyseries8.add(anoIniSerie1,xinfGB);
		xyseries8.add(anoFimSerie2, xinfGB);
		
		
		XYSeries xyseries9 = new XYSeries("upper limit-GB");
		xyseries9.add(anoIniSerie1,xsupGB);
		xyseries9.add(anoFimSerie2, xsupGB);
	  	  	
		
		XYSeries xyseries10 = new XYSeries("lower limit-BP");
		xyseries10.add(anoIniSerie1,xinfBP);
		xyseries10.add(anoFimSerie2, xinfBP);
		
		
		XYSeries xyseries11 = new XYSeries("upper limit-BP");
		xyseries11.add(anoIniSerie1,xsupBP);
		xyseries11.add(anoFimSerie2, xsupBP);
		
		xyseriescollection.addSeries(xyseries8);
	  	xyseriescollection.addSeries(xyseries9);
	  	xyseriescollection.addSeries(xyseries10);
	  	xyseriescollection.addSeries(xyseries11);
	  	
	  	
	  	
	    return xyseriescollection;
	    
	    
	  }
	
	
	
	private JFreeChart criarGrafico(XYDataset xydataset,String nomeseries) {
		  String nomevar="";
		  if(this.vhid.getInvhidro().getTipodeDado().equals("Chuvas")){
			  //nomevar="Chuvas (mm)";  
		  }else{
			  //nomevar="Vazões (m³/s)";
		  }
		  
		  String nomeEstat=this.simulationData.getTipoEstatisticaSelecionadaEstacionaridade();
		  String unidadeVariavel=this.vhid.getInvhidro().getUnidadeDaVariavel();
		  String nomeDaVariavel=this.vhid.getInvhidro().getTipodeDado();
		  unidadeVariavel="m³/s";
		  String especificidadeEstatistica="";
		  
		  if(nomeEstat.equals("PERMANENCIA") ){
			  especificidadeEstatistica=String.valueOf(this.simulationData.getPercentil()+" %");
		  }else if(nomeEstat.equals("MMmin")){
			  especificidadeEstatistica=String.valueOf(this.simulationData.getTamanhoMediaMovelMin()+ " dias");
		  }else if(nomeEstat.equals("NDlim")){
			  especificidadeEstatistica=String.valueOf(this.simulationData.getTamanhoMediaMovelMin()+" "+unidadeVariavel);
		  }
		  
		  
		  //String tituloEixoY= nomeEstat+" "+especificidadeEstatistica+" da "+nomeDaVariavel+"("+unidadeVariavel+")";
		  String tituloEixoY= nomeEstat;
	      JFreeChart jfreechart = ChartFactory.createXYLineChart(nomeseries, "Ano", tituloEixoY, xydataset, PlotOrientation.VERTICAL, true, true, false);
	      jfreechart.setBackgroundPaint(Color.white);
	      XYPlot xyplot = jfreechart.getXYPlot();
	      xyplot.setBackgroundPaint(Color.lightGray);
	      xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
	      xyplot.setDomainGridlinePaint(Color.white);
	      xyplot.setRangeGridlinePaint(Color.white);
	      XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer();
	      
	      xyplot.setRenderer(xylineandshaperenderer);
	      XYItemRenderer xyitemrenderer = xyplot.getRenderer();
	      xyitemrenderer.setSeriesPaint(0,Color.blue);
	      xyitemrenderer.setSeriesPaint(1,Color.BLACK);
	      xyitemrenderer.setSeriesPaint(2,Color.YELLOW);
	      xyitemrenderer.setSeriesPaint(3,Color.YELLOW);
	      xyitemrenderer.setSeriesPaint(4,Color.MAGENTA);
	      xyitemrenderer.setSeriesPaint(5,Color.BLACK);
	      
	      
	      xyitemrenderer.setSeriesPaint(6,Color.RED);
	      xylineandshaperenderer.setSeriesShapesVisible(6, false);
    	  xylineandshaperenderer.setSeriesStroke(6,new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,  
                  0.0f, new float[] { 10.0f, 6.0f }, 0.0f));
	      
    	  xyitemrenderer.setSeriesPaint(7,Color.RED);
	      xylineandshaperenderer.setSeriesShapesVisible(7, false);
    	  xylineandshaperenderer.setSeriesStroke(7,new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,  
                  0.0f, new float[] { 10.0f, 6.0f }, 0.0f));
	      
    	  
    	  xyitemrenderer.setSeriesPaint(8,Color.ORANGE);
	      xylineandshaperenderer.setSeriesShapesVisible(8, false);
    	  xylineandshaperenderer.setSeriesStroke(8,new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,  
                  0.0f, new float[] { 10.0f, 6.0f }, 0.0f));
    	  
    	  
    	  xyitemrenderer.setSeriesPaint(9,Color.ORANGE);
	      xylineandshaperenderer.setSeriesShapesVisible(9, false);
    	  xylineandshaperenderer.setSeriesStroke(9,new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,  
                  0.0f, new float[] { 10.0f, 6.0f }, 0.0f));
    	  
	      
	      NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
	      numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	      return jfreechart;
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public PanelGraficoAnual(VariavelHidrologica vhid,String codigo,ArrayList<Double> serieX,ArrayList<Double> serieY){
		
		this.vhid=vhid;
		this.codigo=codigo;
		this.serieX=serieX;
		this.serieY=serieY;
		this.createAndShowGUI(); 
		
		 
	
	}
	
public PanelGraficoAnual(VariavelHidrologica vhid,String codigo,ArrayList<Double> serieX,ArrayList<Double> serieY,ArrayList<Double> serieY1,ArrayList<Double> serieY2,int anodiv){
		
		this.vhid=vhid;
		this.codigo=codigo;
		this.serieX=serieX;
		this.serieY=serieY;
		this.serieY1=serieY1;
		this.serieY2=serieY2;
		this.anodiv=anodiv;
		
		this.createAndShowGUI(); 
		
		 
	
	}	
public PanelGraficoAnual(VariavelHidrologica vhid,String codigo,Map<String,DadoTemporal> serieY,Map<String,DadoTemporal>serieY1,Map<String,DadoTemporal> serieY2,int anodiv){
	
	this.vhid=vhid;
	this.codigo=codigo;
	this.serieYMapa=serieY;
	this.serieY1Mapa=serieY1;
	this.serieY2Mapa=serieY2;
	this.anodiv=anodiv;
	
	this.serieY=new ArrayList<Double>();
	this.serieY1=new ArrayList<Double>();
	this.serieY2=new ArrayList<Double>();
	this.serieX=new ArrayList<Double>();
	Set<String> chaves =  this.serieYMapa.keySet();
  	for (String data : chaves){
  	DadoTemporal dado = this.serieYMapa.get(data);
  	this.serieY.add(dado.getValor());
  	String [] datastr=data.split("/");
  	Double dataAno=Double.parseDouble(datastr[2]);
  	this.serieX.add(dataAno);
  	}
	
	
  	Set<String> chaves1 =  this.serieY1Mapa.keySet();
  	for (String data : chaves1){
  	DadoTemporal dado = this.serieY1Mapa.get(data);
  	this.serieY1.add(dado.getValor());
  	}
  	
  	Set<String> chaves2 =  this.serieY2Mapa.keySet();
  	for (String data : chaves2){
  	DadoTemporal dado = this.serieY2Mapa.get(data);
  	this.serieY2.add(dado.getValor());
  	}
  	
	this.createAndShowGUI(); 
	
	 

}
	
	private void createAndShowGUI() {
		this.setBounds(100, 200, 800, 600);
		this.setPreferredSize(new Dimension(800, 600));
		this.setBackground(Color.LIGHT_GRAY);
	  	XYDataset xydataset = this.criarSerieDados();
		 JFreeChart jfreechart = this.createChart(xydataset, codigo);
		 ChartPanel chartpanel = new ChartPanel(jfreechart);
		 chartpanel.setPreferredSize(new Dimension(800, 600));
		 this.add(chartpanel);
	}
	
	
	private  XYDataset criarSerieDados(){
  	XYSeriesCollection xyseriescollection = new XYSeriesCollection();
  	String nomeseries="MediaAnual";    
  	XYSeries xyseries = new XYSeries(nomeseries);  
  	
  	ArrayList <Integer> anoSerie=new ArrayList <Integer>();
  	
  	int ndadostot=this.serieY1.size()+this.serieY2.size();
  	
  	for (int i = 0; i < this.serieY.size(); i++){
  	    double ano=this.serieX.get(i);
  		int anoInt=(int) ano;
  		anoSerie.add(anoInt);
  		xyseries.add(this.serieX.get(i), this.serieY.get(i));
  	}
  	Collections.sort(anoSerie);
  	//anoSerie.  	
  	
  	DescriptiveStatistics dsctot = new DescriptiveStatistics();
	for(int j=0;j<this.serieY.size();j++){
	
    //dsctot.addValue(this.serieY.get(j));
	dsctot.addValue((Double) xyseries.getY(j));
	
	}
	
	
	DescriptiveStatistics dsc1 = new DescriptiveStatistics();
	for(int j=0;j<this.serieY1.size();j++){
	dsc1.addValue(this.serieY1.get(j));
	}
	
	
	DescriptiveStatistics dsc2 = new DescriptiveStatistics();
	for(int j=0;j<this.serieY2.size();j++){
	dsc2.addValue(this.serieY2.get(j));
	}
  	
	
	   double [] xdata=new double [serieX.size()];
	   double [] xdata2=new double [serieX.size()];
	   double [] ydata=new double [serieX.size()];
	   for (int j = 0; j < serieX.size(); j++) {
		   xdata[j] =serieX.get(j);
		   xdata2[j] =j+1;
		   ydata[j] =serieY.get(j);
	   }
	   

	
	int anoIniSerie1=anoSerie.get(0);
	int anoFimSerie1=this.anodiv-1;
	int anoIniSerie2=this.anodiv;
	int anoFimSerie2=anoSerie.get(anoSerie.size()-1);
	
	double mediatot=dsctot.getMean();
	double media1=dsc1.getMean();
	double media2=dsc2.getMean();
	double mediaMaisDP= mediatot+dsctot.getStandardDeviation();
	double mediaMenosDP= mediatot-dsctot.getStandardDeviation();
		
	Regression reg = new Regression(xdata, ydata);
	reg.linear();
	Double b=reg.getBestEstimates()[1];
	Double a=reg.getBestEstimates()[0];
	Double b_graus=Math.toDegrees(Math.atan(b));
	
	
	Regression reg2 = new Regression(xdata2, ydata);
	reg2.linear();
	Double b2=reg2.getBestEstimates()[1];
	Double b_graus2=Math.toDegrees(Math.atan(b2));
	System.out.println(b_graus+"   "+b_graus2);
	
	Double y1=a+(b*anoIniSerie1);
	Double y2=a+(b*anoFimSerie2);
	Double anguloInclinacaoteste=Math.toDegrees(Math.atan(Math.pow(3, 0.5)));
	anguloInclinacaoteste=Math.toDegrees(Math.atan(-1));
	System.out.println(anguloInclinacaoteste);
		
	XYSeries xyseries1 = new XYSeries("Media Total");
	xyseries1.add(anoIniSerie1, mediatot);
	xyseries1.add(anoFimSerie2, mediatot);
	
	XYSeries xyseries2 = new XYSeries("Media Serie1");
	xyseries2.add(anoIniSerie1, media1);
	xyseries2.add(anoFimSerie1, media1);
	
	XYSeries xyseries3 = new XYSeries("Media Serie2");
	xyseries3.add(anoIniSerie2, media2);
	xyseries3.add(anoFimSerie2, media2);
			
	XYSeries xyseries4 = new XYSeries("Media+DP");
	xyseries4.add(anoIniSerie1, mediaMaisDP);
	xyseries4.add(anoFimSerie2, mediaMaisDP);
	
	XYSeries xyseries5 = new XYSeries("Media-DP");
	xyseries5.add(anoIniSerie1, mediaMenosDP);
	xyseries5.add(anoFimSerie2, mediaMenosDP);
	
	//XYSeries xyseries6 = new XYSeries("Inc="+b_graus+"º");
	XYSeries xyseries6 = new XYSeries("Inclinação");
	xyseries6.add(anoIniSerie1,y1);
	xyseries6.add(anoFimSerie2, y2);
	
	XYSeries xyseries7 = new XYSeries("Divisão");
	xyseries7.add(anoIniSerie2,0.0);
	xyseries7.add(anoIniSerie2, dsctot.getMax());
	  	
	xyseriescollection.addSeries(xyseries);
  	xyseriescollection.addSeries(xyseries1);
  	xyseriescollection.addSeries(xyseries2);
  	xyseriescollection.addSeries(xyseries3);
  	xyseriescollection.addSeries(xyseries4);
  	xyseriescollection.addSeries(xyseries5);
  	xyseriescollection.addSeries(xyseries6);
  	xyseriescollection.addSeries(xyseries7);
  	
    return xyseriescollection;
    
    
  }

  private JFreeChart createChart(XYDataset xydataset,String nomeseries) {
	  String nomevar="varHidro";
	  if(this.vhid.getInvhidro().getTipodeDado().equals("Chuvas")){
		  nomevar="Chuvas (mm)";  
	  }else{
		  nomevar="Vazões (m³/s)";
	  }
	  
      JFreeChart jfreechart = ChartFactory.createXYLineChart(nomeseries, "Ano", nomevar, xydataset, PlotOrientation.VERTICAL, true, true, false);
      jfreechart.setBackgroundPaint(Color.white);
      XYPlot xyplot = jfreechart.getXYPlot();
      xyplot.setBackgroundPaint(Color.lightGray);
      xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
      xyplot.setDomainGridlinePaint(Color.white);
      xyplot.setRangeGridlinePaint(Color.white);
      XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer();
      
      
      //xyplot.set
      //CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
      //categoryplot.setBackgroundPaint(Color.lightGray);
      //categoryplot.setRangeGridlinePaint(Color.white);
    /* CategoryMarker categorymarker = new CategoryMarker(this.anodiv, Color.blue, new BasicStroke(1.0F));
      categorymarker.setDrawAsLine(true);
      categorymarker.setLabel("Ano Separação");
      categorymarker.setLabelFont(new Font("Dialog", 0, 11));
      categorymarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
      categorymarker.setLabelOffset(new RectangleInsets(2D, 5D, 2D, 5D));
      xyplot.addDomainMarker(categorymarker, Layer.BACKGROUND);*/
    
      
      
      
      //xylineandshaperenderer.setSeriesLinesVisible(0, false);
     // xylineandshaperenderer.setSeriesShapesVisible(0, false);
      //xylineandshaperenderer.setSeriesShapesVisible(2, false);
      xyplot.setRenderer(xylineandshaperenderer);
      XYItemRenderer xyitemrenderer = xyplot.getRenderer();
      xyitemrenderer.setSeriesPaint(0,Color.blue);
      xyitemrenderer.setSeriesPaint(1,Color.BLACK);
      xyitemrenderer.setSeriesPaint(2,Color.YELLOW);
      xyitemrenderer.setSeriesPaint(3,Color.YELLOW);
      xyitemrenderer.setSeriesPaint(4,Color.RED);
      xyitemrenderer.setSeriesPaint(5,Color.RED);
      //xyitemrenderer.setSeriesShape(6, arg1);
      //xylineandshaperenderer.se
     // xylineandshaperenderer.setSeriesOutlineStroke(6, new BasicStroke(2));
      xyitemrenderer.setSeriesPaint(6,Color.MAGENTA);
      
      xyitemrenderer.setSeriesPaint(7,Color.BLACK);
      
      //xyitemrenderer.sets
      
      //xyitemrenderer.setSeriesPaint(1,Color.PINK);
      //xyitemrenderer.setSeriesPaint(2,Color.MAGENTA);
      NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
      numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
      return jfreechart;
  }
    

}
