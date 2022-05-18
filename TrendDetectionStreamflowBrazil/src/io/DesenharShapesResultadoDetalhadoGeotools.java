package io;

import gui.PanelTrendDetectionStreamflowBrazil;
import types.ResultEstacionaridade;
import types.SimulationDataExtremos;
import types.VariavelHidrologica;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;




public class DesenharShapesResultadoDetalhadoGeotools {

	
private SimulationDataExtremos simulationData;
	
	private PanelTrendDetectionStreamflowBrazil pnt;
	//protected PlugInContext context = null;
	//protected Category category = null;
	public DesenharShapesResultadoDetalhadoGeotools(SimulationDataExtremos simulationData,PanelTrendDetectionStreamflowBrazil pnt) {
		this.simulationData = simulationData;
		this.pnt=pnt;
	}
	
	public void execute(String testeEscolhido,String dir) throws SchemaException, IOException{
		
		    SimpleFeatureType TYPE=this.createFeatureTypeResultTrend();
		    
		    List<SimpleFeature> featuresMK_NS = new ArrayList<>();
		    List<SimpleFeature> featuresMK_S = new ArrayList<>();
		    List<SimpleFeature> featuresMK_CS = new ArrayList<>();
		    List<SimpleFeature> featuresMK_DS = new ArrayList<>();
		    List<SimpleFeature> featuresMK_CS_S = new ArrayList<>();
		    List<SimpleFeature> featuresMK_DS_S = new ArrayList<>();
		    
		    String filename=this.simulationData.getFilenameBD();
		    String filenameFinal="";
		    if(filename.contains(".dat")) {
		    	filenameFinal=filename.split(".dat")[0];
		    }else {
		    	filenameFinal=filename;
		    }
		    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		    
		    int idgauges=1;
			for(int igauges=0;igauges<this.simulationData.getVariaveisHidr().size();igauges++){
	   	   
			        boolean selGauges=this.simulationData.getVariaveisHidr().get(igauges).isSelecionada();
			        boolean isRecordLenght=this.simulationData.getVariaveisHidr().get(igauges).isAtendeRestricaoTamMin();
					if(selGauges && isRecordLenght){		
						SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
			   	     String codigo=String.valueOf(this.simulationData.getVariaveisHidr().get(igauges).getInvhidro().getEstacao_codigo());
			   	     ArrayList<ResultEstacionaridade> resultestacionaridade =new ArrayList<ResultEstacionaridade>();
			   	     resultestacionaridade = this.simulationData.getVariaveisHidr().get(igauges).getResultestacionaridade();
			  
			   	     VariavelHidrologica vhid =this.simulationData.getVariaveisHidr().get(igauges);
			   	    
				   	 double latitude = vhid.getInvhidro().getLatitude();
	                 double longitude = vhid.getInvhidro().getLongitude();
	                 String gauge_code = vhid.getInvhidro().getEstacao_codigo();
	                 int number = idgauges;
	
	                 /* Longitude (= x coord) first ! */
	                 Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
	
	                 featureBuilder.add(point);
	                 featureBuilder.add(idgauges);
	                 featureBuilder.add(gauge_code);
	                 featureBuilder.add(latitude);
	                 featureBuilder.add(longitude);
	                 featureBuilder.add(vhid.getInvhidro().getAreaDrenagem());
	                 featureBuilder.add("MannKendall");
	                 featureBuilder.add(resultestacionaridade.get(0).getPvalue());
	                 
	                 String resMK=resultestacionaridade.get(0).getResultadoteste();
	                 String direction=resultestacionaridade.get(0).getSentidoTendencia();
	                 featureBuilder.add(resMK);
	                 featureBuilder.add(direction);
	                 SimpleFeature feature = featureBuilder.buildFeature(null);
	                 
	                 
	                if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)")|| resMK.equals("S(10.0)")){
	     				featuresMK_S.add(feature);	
	     			}else{
	     				featuresMK_NS.add(feature);
	     			}
	                 
	                if(direction == null){
	    					System.out.println();
	    			}else{
	    			
			    			if(direction.equals("Increase") || direction.equals("Maior")){
			    				featuresMK_CS.add(feature);
			    			}else{
			    				featuresMK_DS.add(feature);
			    			}
	    			
	    			}
	                 
	                if(direction == null){
	    				System.out.println();
	    			}else{
	    			if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)") && (direction.equals("Increase") || direction.equals("Maior"))){
	    				featuresMK_CS_S.add(feature);
	    			}else if(resMK.equals("S(1.0)") || resMK.equals("S(5.0)") && (direction.equals("Decrease") || direction.equals("Menor"))){
	    				featuresMK_DS_S.add(feature);
	    			}
	    								
	    			}	
	                 
	                // features.add(feature);
	                 idgauges++;
			   	    
					}
		
		
			}
			
			int nTypeShp=6;
			String nameSHP=filenameFinal+"_MK_NS.shp";
			File newFile_1=	new File(dir+nameSHP);	//Aqui tem que vim ja com o nome do arquivo shape
			if(featuresMK_NS.size() > 0) {
			this.createExportShapefile(TYPE, newFile_1, featuresMK_NS);
			}
			nameSHP=filenameFinal+"_MK_S.shp";
			if(featuresMK_S.size() > 0) {
			File newFile_2=	new File(dir+nameSHP);	
			this.createExportShapefile(TYPE, newFile_2, featuresMK_S);
			}
			nameSHP=filenameFinal+"_MK_IC.shp";//increase
			if(featuresMK_CS.size() > 0) {
			File newFile_3=	new File(dir+nameSHP);	
			this.createExportShapefile(TYPE, newFile_3, featuresMK_CS);
			}
			nameSHP=filenameFinal+"_MK_DC.shp";//decrease
			if(featuresMK_DS.size() > 0) {
			File newFile_4=	new File(dir+nameSHP);	
			this.createExportShapefile(TYPE, newFile_4, featuresMK_DS);
			}
			nameSHP=filenameFinal+"_MK_IC_S.shp";//increase
			if(featuresMK_CS_S.size() > 0) {
			File newFile_5=	new File(dir+nameSHP);	
			this.createExportShapefile(TYPE, newFile_5, featuresMK_CS_S);
			}
			nameSHP=filenameFinal+"_MK_DC_S.shp";//decrease
			if(featuresMK_DS_S.size() > 0) {
			File newFile_6=	new File(dir+nameSHP);	
			this.createExportShapefile(TYPE, newFile_6, featuresMK_DS_S);
			}
			
			System.out.println("Final shape create");
	}
	
	
	public void createExportShapefile(SimpleFeatureType TYPE,File newFile,List<SimpleFeature> features) {
		
		ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
        Map<String, Serializable> params = new HashMap<>();
        try {
			params.put("url", newFile.toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        params.put("create spatial index", Boolean.TRUE);

        ShapefileDataStore newDataStore = null;
		try {
			newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        /*
         * TYPE is used as a template to describe the file contents
         */
        try {
			newDataStore.createSchema(TYPE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
     // docs break transaction
        /*
         * Write the features to the shapefile
         */
        Transaction transaction = new DefaultTransaction("create");
        String typeName = null;
		try {
			typeName = newDataStore.getTypeNames()[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SimpleFeatureSource featureSource = null;
		try {
			featureSource = newDataStore.getFeatureSource(typeName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();
        /*
         * The Shapefile format has a couple limitations:
         * - "the_geom" is always first, and used for the geometry attribute name
         * - "the_geom" must be of type Point, MultiPoint, MuiltiLineString, MultiPolygon
         * - Attribute names are limited in length
         * - Not all data types are supported (example Timestamp represented as Date)
         *
         * Each data store has different limitations so check the resulting SimpleFeatureType.
         */
        System.out.println("SHAPE:" + SHAPE_TYPE);

        if (featureSource instanceof SimpleFeatureStore) {
            SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
            /*
             * SimpleFeatureStore has a method to add features from a
             * SimpleFeatureCollection object, so we use the ListFeatureCollection
             * class to wrap our list of features.
             */
            SimpleFeatureCollection collection = new ListFeatureCollection(TYPE, features);
            featureStore.setTransaction(transaction);
            try {
                featureStore.addFeatures(collection);
                transaction.commit();
            } catch (Exception problem) {
                problem.printStackTrace();
                try {
					transaction.rollback();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } finally {
                try {
					transaction.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            //System.exit(0); // success!
        } else {
            System.out.println(typeName + " does not support read/write access");
           // System.exit(1);
        }
		
	}
	
	
	public SimpleFeatureType pegarTYPEGeotoolsSimpleFeature() throws SchemaException {
		
		
		final SimpleFeatureType TYPE =
                DataUtilities.createType(
                        "Location",
                        "the_geom:Point:srid=4326,"
                                + // <- the geometry attribute: Point type
                                "name:String,"
                                + // <- a String attribute
                                "number:Integer" // a number attribute
                        );
        System.out.println("TYPE:" + TYPE);
		return TYPE;
		
		
		
	}
	
	
	private static SimpleFeatureType createFeatureTypeResultTrend() {

        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("Location");
        builder.setCRS(DefaultGeographicCRS.WGS84); // <- Coordinate reference system

        // add attributes in order
        builder.add("the_geom", Point.class);
        builder.add("id", Integer.class);
        builder.length(15).add("gauge_code", String.class); // <- 15 chars width for name field
        builder.add("lat", Double.class);
        builder.add("lon", Double.class);
        builder.add("basin_area", Double.class);
        builder.length(15).add("test", String.class);
        builder.add("pvalue", Double.class);
        builder.length(15).add("result", String.class);
        builder.length(15).add("direction", String.class);
        //builder.add("number", Integer.class);

        // build the type
        final SimpleFeatureType LOCATION = builder.buildFeatureType();

        return LOCATION;
    }
	
	
	 /**
     * Here is how you can use a SimpleFeatureType builder to create the schema for your shapefile
     * dynamically.
     *
     * <p>This method is an improvement on the code used in the main method above (where we used
     * DataUtilities.createFeatureType) because we can set a Coordinate Reference System for the
     * FeatureType and a a maximum field length for the 'name' field dddd
     */
    private static SimpleFeatureType createFeatureType() {

        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("Location");
        builder.setCRS(DefaultGeographicCRS.WGS84); // <- Coordinate reference system

        // add attributes in order
        builder.add("the_geom", Point.class);
        builder.length(15).add("Name", String.class); // <- 15 chars width for name field
        builder.add("number", Integer.class);

        // build the type
        final SimpleFeatureType LOCATION = builder.buildFeatureType();

        return LOCATION;
    }
    
    
	private static File getNewShapeFile(File csvFile) {
        String path = csvFile.getAbsolutePath();
        String newPath = path.substring(0, path.length() - 4) + ".shp";

        JFileDataStoreChooser chooser = new JFileDataStoreChooser("shp");
        chooser.setDialogTitle("Save shapefile");
        chooser.setSelectedFile(new File(newPath));

        int returnVal = chooser.showSaveDialog(null);

        if (returnVal != JFileDataStoreChooser.APPROVE_OPTION) {
            // the user cancelled the dialog
            System.exit(0);
        }

        File newFile = chooser.getSelectedFile();
        if (newFile.equals(csvFile)) {
            System.out.println("Error: cannot replace " + csvFile);
            System.exit(0);
        }

        return newFile;
    }
	
	
}
