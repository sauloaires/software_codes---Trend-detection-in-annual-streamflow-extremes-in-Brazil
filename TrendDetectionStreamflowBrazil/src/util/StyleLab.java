package util;

import java.awt.Color;
import java.io.File;
import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.LineSymbolizer;
import org.geotools.styling.Mark;
import org.geotools.styling.PointSymbolizer;
import org.geotools.styling.PolygonSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.geotools.swing.dialog.JExceptionReporter;
import org.geotools.swing.styling.JSimpleStyleDialog;
import org.geotools.xml.styling.SLDParser;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory;



public class StyleLab  {
	static StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory();
    static FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory();

    public static void main(String[] args) throws Exception {
        StyleLab me = new StyleLab();
        me.displayShapefile();
    }
	
	/**
     * Prompts the user for a shapefile (unless a filename is provided on the command line; then
     * creates a simple Style and displays the shapefile on screen
     */
    private void displayShapefile() throws Exception {
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        FeatureSource featureSource = store.getFeatureSource();

        // Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("StyleLab");

        // Create a basic Style to render the features
        Style style = createStyle(file, featureSource);

        // Add the features and the associated Style object to
        // the MapContent as a new Layer
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);

        //saulo
        /*File file2 = JFileDataStoreChooser.showOpenFile("shp", null);
        FileDataStore store2 = FileDataStoreFinder.getDataStore(file2);
        FeatureSource featureSource2 = store2.getFeatureSource();
        Style style2 = createStyle(file2, featureSource2);
        Layer layer2 = new FeatureLayer(featureSource2, style2);
        map.addLayer(layer2);*/
        // Now display the map
        JMapFrame.showMap(map);
    }
    
    
    /**
     * Create a Style to display the features. If an SLD file is in the same directory as the
     * shapefile then we will create the Style by processing this. Otherwise we display a
     * JSimpleStyleDialog to prompt the user for preferences.
     */
    private Style createStyle(File file, FeatureSource featureSource) {
        File sld = toSLDFile(file);
        if (sld != null) {
            return createFromSLD(sld);
        }

        SimpleFeatureType schema = (SimpleFeatureType) featureSource.getSchema();
        return JSimpleStyleDialog.showDialog(null, schema);
    }
    
    
    public File toSLDFile(File file) {
        String path = file.getAbsolutePath();
        String base = path.substring(0, path.length() - 4);
        String newPath = base + ".sld";
        File sld = new File(newPath);
        if (sld.exists()) {
            return sld;
        }
        newPath = base + ".SLD";
        sld = new File(newPath);
        if (sld.exists()) {
            return sld;
        }
        return null;
    }

    /** Create a Style object from a definition in a SLD document */
    private Style createFromSLD(File sld) {
        try {
            SLDParser stylereader = new SLDParser(styleFactory, sld.toURI().toURL());
            Style[] style = stylereader.readXML();
            return style[0];

        } catch (Exception e) {
            JExceptionReporter.showDialog(e, "Problem creating style");
        }
        return null;
    }
    
    /**
     * Here is a programmatic alternative to using JSimpleStyleDialog to get a Style. This methods
     * works out what sort of feature geometry we have in the shapefile and then delegates to an
     * appropriate style creating method.
     */
  /*  private Style createStyle2(FeatureSource featureSource) {
        SimpleFeatureType schema = (SimpleFeatureType) featureSource.getSchema();
        Class geomType = schema.getGeometryDescriptor().getType().getBinding();

        if (Polygon.class.isAssignableFrom(geomType)
                || MultiPolygon.class.isAssignableFrom(geomType)) {
            return createPolygonStyle();

        } else if (LineString.class.isAssignableFrom(geomType)
                || MultiLineString.class.isAssignableFrom(geomType)) {
            return createLineStyle();

        } else {
            return createPointStyle();
        }
    }*/
}
