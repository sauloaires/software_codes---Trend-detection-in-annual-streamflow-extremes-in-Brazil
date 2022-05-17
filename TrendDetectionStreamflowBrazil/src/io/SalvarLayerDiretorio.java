package io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.vividsolutions.jump.feature.FeatureCollection;
import com.vividsolutions.jump.io.datasource.DataSource;
import com.vividsolutions.jump.io.datasource.DataSourceQuery;
import com.vividsolutions.jump.task.DummyTaskMonitor;
import com.vividsolutions.jump.util.FileUtil;
import com.vividsolutions.jump.workbench.model.Layer;

public class SalvarLayerDiretorio {
public static void save(Layer layer, File dir, DataSource dataSource, String ext,String nomeShape) throws Exception {
        
		ext = "shp";
        dataSource = new com.vividsolutions.jump.io.datasource.StandardReaderWriterFileDataSource.Shapefile();
        dataSource.getProperties();
       // FileUtil.
		//String name = FileUtil.getFileNameFromLayerName(layer.getName());
        String name = nomeShape;
        // remove extension if any (ex. for layer image.png, will remove png
        int dotPos = name.indexOf(".");
        if (dotPos > 0) name = name.substring(0, dotPos);
        File fileName = FileUtil.addExtensionIfNone(new File(name), ext);
        String path = new File(dir, fileName.getName()).getAbsolutePath();
        
        //DriverProperties dp = new DriverProperties();
        //dp.set("File", path);
        Map<String,Object> properties =new HashMap<String,Object>();
        properties.put("File", path);
        dataSource.setProperties(properties);
                
        DataSourceQuery dsq = new DataSourceQuery(dataSource, path, path);
        layer.setDataSourceQuery(dsq).setFeatureCollectionModified(false);
                
        dataSource.getConnection().executeUpdate("", layer.getFeatureCollectionWrapper(), new DummyTaskMonitor());
    }



public static void save(Layer layer, File dir,String nomeShape) throws Exception {
    
	String ext = "shp";
	DataSource dataSource = new com.vividsolutions.jump.io.datasource.StandardReaderWriterFileDataSource.Shapefile();
	
	
	//String name = FileUtil.getFileNameFromLayerName(layer.getName());
    String name = nomeShape;
    // remove extension if any (ex. for layer image.png, will remove png
    int dotPos = name.indexOf(".");
    if (dotPos > 0) name = name.substring(0, dotPos);
    File fileName = FileUtil.addExtensionIfNone(new File(name), ext);
    String path = new File(dir, fileName.getName()).getAbsolutePath();
    
  //DriverProperties dp = new DriverProperties();
    //dp.set("File", path);
    Map<String,Object> properties =new HashMap<String,Object>();
    properties.put("File", path);
    dataSource.setProperties(properties);
    /**
     * Saulo 29/03/2017
     */
   // dataSource.setProperties(dp);
            
    DataSourceQuery dsq = new DataSourceQuery(dataSource, path, path);
    layer.setDataSourceQuery(dsq).setFeatureCollectionModified(false);
            
    dataSource.getConnection().executeUpdate("", layer.getFeatureCollectionWrapper(), new DummyTaskMonitor());
}


public static void save(FeatureCollection fc,String nome, File dir,String nomeShape) throws Exception {
  
  String ext = "shp";
  DataSource dataSource = new com.vividsolutions.jump.io.datasource.StandardReaderWriterFileDataSource.Shapefile();
  
  
  //String name = FileUtil.getFileNameFromLayerName(layer.getName());
//String name = FileUtil.getFileNameFromLayerName(layer.getName());
  String name = nomeShape;
    int dotPos = name.indexOf(".");
    if (dotPos > 0) name = name.substring(0, dotPos);
    File fileName = FileUtil.addExtensionIfNone(new File(name), ext);
    String path = new File(dir, fileName.getName()).getAbsolutePath();
    
  //DriverProperties dp = new DriverProperties();
    //dp.set("File", path);
    Map<String,Object> properties =new HashMap<String,Object>();
    properties.put("File", path);
    dataSource.setProperties(properties);
    /**
     * Saulo 29/03/2017
     */
   // dataSource.setProperties(dp);
            
    //DataSourceQuery dsq = new DataSourceQuery(dataSource, path, path);
    //layer.setDataSourceQuery(dsq).setFeatureCollectionModified(false);
    //fc.se       
    dataSource.getConnection().executeUpdate("", fc, new DummyTaskMonitor());
}
}
