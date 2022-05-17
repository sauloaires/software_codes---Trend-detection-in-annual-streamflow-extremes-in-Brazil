/**
 * 
 */
package util.fileloader;



import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFactorySpi;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureWriter;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.referencing.CRS;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JProgressWindow;
import org.geotools.swing.action.SafeAction;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureVisitor;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.FeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.util.ProgressListener;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;

/**
 * @author planner
 *
 */
public class GeometryType {
	 public static void main(String[] args) throws Exception {
		 
		 /*GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory( null );
		 //Point
		 WKTReader reader = new WKTReader(geometryFactory);
		 Point pointwkt =(Point)reader.read("POINT (1 1)");
		
		 Coordinate coord = new Coordinate( 1, 1 );
		 Point point = geometryFactory.createPoint(coord );
		 // Line
		 WKTReader readerLine = new WKTReader(geometryFactory );
		 LineString line =(LineString) readerLine.read("LINESTRING(0 2, 2 0, 8 6)");
		 Coordinate[] coords  =new Coordinate[] {new Coordinate(0, 2), new Coordinate(2, 0), new Coordinate(8, 6) };
		 LineString lineCoords = geometryFactory.createLineString(coords);
		 // Polygon
		 WKTReader readerPolygon = new WKTReader( geometryFactory );
		 Polygon polygon =(Polygon)readerPolygon.read("POLYGON((20 10, 30 0, 40 10, 30 20, 20 10))");
		 
		 System.out.println("print point:"+point);*/

}
}