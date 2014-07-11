/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.clients.swing.gis.imagegenerator;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.SchemaException;
import org.geotools.geometry.jts.Geometries;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.extended.layer.ExtendedFeatureLayer;
import org.geotools.map.extended.layer.ExtendedLayerGraphics;
import org.geotools.swing.extended.Map;
import org.geotools.swing.extended.exception.InitializeLayerException;
import org.geotools.swing.extended.exception.InitializeMapException;
import org.geotools.swing.extended.util.MapImageGenerator;
import org.geotools.swing.extended.util.ScalebarGenerator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;
import org.sola.clients.swing.gis.data.PojoDataAccess;
import org.sola.clients.swing.gis.layer.PojoLayer;
import org.sola.webservices.search.ConfigMapLayerMetadataTO;
import org.sola.webservices.search.CrsTO;
import org.sola.webservices.search.ConfigMapLayerTO;
import org.sola.webservices.search.SpatialResult;

/**
 *
 * @author Elton Manoku
 */
public class MapImageGeneratorForSelectedParcel {

    private static final int DPI = 72;
    private static final String IMAGE_FORMAT = "png";
    private static final String LAYER_NAME = "target_plan_parcel";
    private static final String LAYER_STYLE_RESOURCE = "parcel_title_plan.xml";
    private static final String LAYER_STYLE_RESOURCE_FOR_SKETCH = "parcel_title_plan_sketch.xml";
    private static final String LAYER_FEATURE_LABEL_FIELD_NAME = "label";
    private static final String LAYER_FEATURE_TARGET_FIELD_NAME = "target";
    private static String extraSldResources = "/org/sola/clients/swing/gis/layer/resources/";
    private static final String IN_PLAN_PRODUCTION = "in-plan-production";
    private static final String IN_PLAN_SKETCH_PRODUCTION = "in-plan-sketch-production";
    private List<ExtendedLayerGraphics> layers = new ArrayList<ExtendedLayerGraphics>();
    private MapImageGenerator mapImageGenerator = null;
    private MapImageGenerator mapImageSketchGenerator = null;
    private ScalebarGenerator scalebarGenerator = null;
    //private Map map;
    private int imageWidth;
    private int imageHeight;
    private int imageMargin = 55;
    private int sketchWidth;
    private int sketchHeight;
    private int scalebarWidth;
    private int coordinateLineLength = 6;
    private double[] scaleRange = new double[]{500, 1000, 2000, 2500, 5000, 10000, 20000, 25000, 50000};
    private CoordinateReferenceSystem mapCrs;
    int gridCutSrid;

    /**
     * Constructor of the class that generates map image and sketch map image
     * information.
     *
     * @param imageWidth The map image width
     * @param imageHeight The map image height
     * @param sketchWidth The sketch map image width
     * @param sketchHeight The sketch map image height
     * @param generateAlsoScaleBar If True the scalebar will also generated. 
     * If False the next two parameters are ignored.
     * @param scalebarWidth The scalebar width. The returned width of the
     * scalebar can vary to fit a good scale.
     * @param scalebarHeight The scalebar height
     * @throws InitializeLayerException
     * @throws InitializeMapException
     * @throws SchemaException
     */
    public MapImageGeneratorForSelectedParcel(
            int imageWidth, int imageHeight, int sketchWidth, int sketchHeight,
            boolean generateAlsoScaleBar, int scalebarWidth, int scalebarHeight)
            throws InitializeLayerException, InitializeMapException, SchemaException {

        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.sketchWidth = sketchWidth;
        this.sketchHeight = sketchHeight;
        org.geotools.swing.extended.util.Messaging.getInstance().setMessaging(
                new org.sola.clients.swing.gis.Messaging());
        ExtendedFeatureLayer.setExtraSldResources(extraSldResources);
        
        //Get grid cut SRID
        gridCutSrid = 3333;
        
        // Initialize the map generator of the map image
        CrsTO crs = PojoDataAccess.getInstance().getMapDefinition().getCrsList().get(0);
        Map map = new Map(crs.getSrid(), crs.getWkt());
        this.addLayers(map, IN_PLAN_PRODUCTION, LAYER_STYLE_RESOURCE);
        mapCrs = map.getMapContent().getCoordinateReferenceSystem();
        mapImageGenerator = new MapImageGenerator(map.getMapContent());
        mapImageGenerator.setDrawCoordinatesInTheSides(false);
        mapImageGenerator.setTextInTheMapCenter(null);
        
        // Initialize the map generator of the map sketch image
        map = new Map(crs.getSrid(), crs.getWkt());
        this.addLayers(map, IN_PLAN_SKETCH_PRODUCTION, LAYER_STYLE_RESOURCE_FOR_SKETCH);
        mapImageSketchGenerator = new MapImageGenerator(map.getMapContent());
        mapImageSketchGenerator.setDrawCoordinatesInTheSides(false);
        mapImageSketchGenerator.setTextInTheMapCenter(null);

        this.setScaleRange();
        if (generateAlsoScaleBar) {
            this.scalebarWidth = scalebarWidth;
            scalebarGenerator = new ScalebarGenerator();
            scalebarGenerator.setHeight(scalebarHeight);
        }
    }

    public int getImageMargin() {
        return imageMargin;
    }

    public void setImageMargin(int imageMargin) {
        this.imageMargin = imageMargin;
    }

    private void addLayers(Map map, String MapUsage, String cadastreObjectStyleResource) 
            throws InitializeLayerException, SchemaException {
        for (ConfigMapLayerTO configMapLayer
                : PojoDataAccess.getInstance().getMapDefinition().getLayers()) {
            for (ConfigMapLayerMetadataTO metadata : configMapLayer.getMetadataList()) {
                if (metadata.getName().equals(MapUsage)
                        && metadata.getValue().equals("true")) {
                    this.addLayerConfig(map, configMapLayer);
                }
            }
        }
        
         ExtendedLayerGraphics layer = new ExtendedLayerGraphics(
                LAYER_NAME, Geometries.POLYGON, cadastreObjectStyleResource,
                LAYER_FEATURE_LABEL_FIELD_NAME + ":String," + LAYER_FEATURE_TARGET_FIELD_NAME + ":String");
        map.addLayer(layer);
        this.layers.add(layer);
    }

    /**
     * It adds a layer in the map using map definition as retrieved by server
     *
     * @param configMapLayer The map layer definition
     * @throws InitializeLayerException
     * @throws SchemaException
     */
    private void addLayerConfig(Map map, ConfigMapLayerTO configMapLayer)
            throws InitializeLayerException, SchemaException {
        if (configMapLayer.getTypeCode().equals("wms")) {
            String wmsServerURL = configMapLayer.getUrl();
            ArrayList<String> wmsLayerNames = new ArrayList<String>();
            String[] layerNameList = configMapLayer.getWmsLayers().split(";");
            String wmsVersion = configMapLayer.getWmsVersion();
            String format = configMapLayer.getWmsFormat();
            java.util.Collections.addAll(wmsLayerNames, layerNameList);
            map.addLayerWms(
                    configMapLayer.getId(), configMapLayer.getTitle(), wmsServerURL, wmsLayerNames,
                    true, wmsVersion, format);
        } else if (configMapLayer.getTypeCode().equals("shape")) {
            map.addLayerShapefile(
                    configMapLayer.getId(),
                    configMapLayer.getTitle(),
                    configMapLayer.getShapeLocation(),
                    configMapLayer.getStyle(),
                    true);
        } else if (configMapLayer.getTypeCode().equals("pojo")) {
            map.addLayer(new PojoLayer(configMapLayer.getId(), PojoDataAccess.getInstance(), true));
        }
    }

    /**
     * Gets the map image and scalebar information that is used to generate the
     * title plan.
     *
     * @param cadastreObjectID The ID of the target cadastre object
     * @return
     * @throws IOException
     * @throws FactoryException
     * @throws TransformException
     * @throws ParseException
     */
    public MapImageInformation getInformation(String cadastreObjectID)
            throws IOException, FactoryException, TransformException, ParseException {

        List<SpatialResult> cadastreObjectList =
                PojoDataAccess.getInstance().getSearchService().getPlanCadastreObjects(cadastreObjectID);
        if (cadastreObjectList.isEmpty()) {
            throw new RuntimeException("CADASTRE_OBJECT_NOT_FOUND.ID:" + cadastreObjectID);
        }

        for (ExtendedLayerGraphics layer:this.layers){
            layer.removeFeatures(false);
        }
        MapImageInformation info = new MapImageInformation();
        SpatialResult targetObject = null;
        for (SpatialResult cadastreObject : cadastreObjectList) {
            if (cadastreObject.getId().equals(cadastreObjectID)) {
                targetObject = cadastreObject;
                continue;
            }
            addFeature(cadastreObject);
        }
        SimpleFeature targetFeature = addFeature(targetObject);
        ReferencedEnvelope extent = getProperExtent();
        double scale = getProperScale(extent);
        String mapImageLocation = this.getMapImageAsFileLocation(
                extent, scale, String.format("map-%s", cadastreObjectID));
        String mapSketchImageLocation = this.getMapSketchImageAsFileLocation(
                extent, scale*2, String.format("map-%s-sketch", cadastreObjectID));
        info.setMapImageLocation(mapImageLocation);
        info.setSketchMapImageLocation(mapSketchImageLocation);
        info.setArea(((Geometry) targetFeature.getDefaultGeometry()).getArea());
        info.setSrid(this.gridCutSrid);
        info.setScale(scale);
        if (scalebarGenerator != null){
        info.setScalebarImageLocation(this.scalebarGenerator.getImageAsFileLocation(
                scale, this.scalebarWidth, DPI, String.format("scalebar-%s", cadastreObjectID)));            
        }
        return info;
    }

    private String getMapImageAsFileLocation(
            ReferencedEnvelope extent, double scale, String fileName) throws IOException {
        String pathToResult = mapImageGenerator.getFullpathOfMapImage(fileName, IMAGE_FORMAT);
        File outputFile = new File(pathToResult);
        BufferedImage mapOnlyImage = mapImageGenerator.getImage(
                extent, getMapOnlyWidth(), getMapOnlyHeight(), scale, DPI);

        BufferedImage fullImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = fullImage.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.drawImage(mapOnlyImage, null, 0, 0);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, getMapOnlyWidth() - 1, getMapOnlyHeight() - 1);

        //Print the coordinates in the bottom right corner
        int xCoordinate = ((int) extent.getMaxX() / 10) * 10;
        int yCoordinate = (((int) extent.getMinY() / 10) * 10) + 10;
        double pixelPerMeter = getMapOnlyWidth() / extent.getWidth();
        int xCoordinateLocation = getMapOnlyWidth() - 1
                - (int) ((extent.getMaxX() - xCoordinate) * pixelPerMeter);
        int yCoordinateLocation = getMapOnlyHeight() - 1
                - (int) ((yCoordinate - extent.getMinY()) * pixelPerMeter);
        graphics.drawLine(
                getMapOnlyWidth() - 1 - coordinateLineLength, yCoordinateLocation,
                getMapOnlyWidth() - 1, yCoordinateLocation);
        graphics.drawLine(
                xCoordinateLocation - coordinateLineLength / 2, yCoordinateLocation,
                xCoordinateLocation + coordinateLineLength / 2, yCoordinateLocation);
        graphics.drawLine(
                xCoordinateLocation, getMapOnlyHeight() - 1,
                xCoordinateLocation, getMapOnlyHeight() - 1 - coordinateLineLength);
        graphics.drawLine(
                xCoordinateLocation, yCoordinateLocation + coordinateLineLength / 2,
                xCoordinateLocation, yCoordinateLocation - coordinateLineLength / 2);

        graphics.drawString(String.format("%s", yCoordinate), getMapOnlyWidth() + 1, yCoordinateLocation);

        graphics.rotate(Math.toRadians(-90), xCoordinateLocation, imageHeight);
        graphics.drawString(String.format("%s", xCoordinate), xCoordinateLocation, imageHeight);

        ImageIO.write(fullImage, IMAGE_FORMAT, outputFile);
        return pathToResult;
    }

    private String getMapSketchImageAsFileLocation(
            ReferencedEnvelope extent, double scale, String fileName) throws IOException {
        String pathToResult = mapImageGenerator.getFullpathOfMapImage(fileName, IMAGE_FORMAT);
        File outputFile = new File(pathToResult);
        BufferedImage mapOnlyImage = mapImageSketchGenerator.getImage(
                extent, this.sketchWidth, this.sketchHeight, scale, DPI);
        
        ImageIO.write(mapOnlyImage, IMAGE_FORMAT, outputFile);
        return pathToResult;
    }

    private SimpleFeature addFeature(SpatialResult cadastreObject) throws ParseException {
        java.util.HashMap<String, Object> fieldValues = new java.util.HashMap<String, Object>();
        fieldValues.put(LAYER_FEATURE_LABEL_FIELD_NAME, cadastreObject.getLabel());
        fieldValues.put(LAYER_FEATURE_TARGET_FIELD_NAME, cadastreObject.getFilterCategory());
        SimpleFeature feature = null;
        for(ExtendedLayerGraphics layer:this.layers){
            feature = layer.addFeature(cadastreObject.getId(), cadastreObject.getTheGeom(), fieldValues, false);
        }
        return feature;
    }

    private void setScaleRange() {
        String setting = PojoDataAccess.getInstance().getWSManager().getAdminService().getSetting(
                "scale-range", "");
        if (!(setting.isEmpty())) {
            String[] scaleRangeAsString = setting.split(",");
//            scaleRange = new double[scaleRangeAsString.length+1];
//            scaleRange[0] = 0;
            scaleRange = new double[scaleRangeAsString.length];
            
//            for (int i = 1; i <= scaleRangeAsString.length; i++) {
            for (int i = 1; i < scaleRangeAsString.length; i++) {
                scaleRange[i] = Double.parseDouble(scaleRangeAsString[i]);
            }
        }
    }

    private double[] getScaleRange() {
        return scaleRange;
    }

    private ReferencedEnvelope getProperExtent() {

        // Make a list of all extents in the order of parcels. Target parcel is the last one.
        SimpleFeatureIterator featureIterator =
                (SimpleFeatureIterator) this.layers.get(0).getFeatureCollection().features();
        List<ReferencedEnvelope> extents = new ArrayList<ReferencedEnvelope>();
        while (featureIterator.hasNext()) {
            extents.add(0, JTS.toEnvelope((Geometry) featureIterator.next().getDefaultGeometry()));
        }
        featureIterator.close();

        //Max total parcels that can be used in calculation
        int totalParcels = extents.size() - 1;
        //Calculate the best extent for all parcels
        ReferencedEnvelope bestExtent = this.layers.get(0).getFeatureCollection().getBounds();
        //Calculate the best scale for all parcels
        double minScale = getProperScale(bestExtent);
        //Start a cycle that calculates the best extent for the number of parcels starting
        //with the total of all parcels minus 1 (because the total of all parcels is already 
        //calculated. The cycle stops when the total number of parcels is 3.
        for (int mainIndex = totalParcels; mainIndex > 2; mainIndex--) {
            //Initialize the extent with the extent of the first parcel which is the target parcel.
            ReferencedEnvelope extentToCheck = new ReferencedEnvelope(extents.get(0));
            //Extend the extent in order to include all extents of the other parcels
            //that has to be included for calculation.
            for (int i = 1; i < mainIndex; i++) {
                extentToCheck.expandToInclude(extents.get(i));
            }
            //Find the best scale for the calculated extent.
            double scale = getProperScale(extentToCheck);
            //If the scale is less(larger) than the minimum allowed scale, stop.
            if (scale < this.getScaleRange()[0]) {
                break;
            }
            //If the scale is less(larger) than the best scale already found,
            // make this as the best scale and this as the best extent.
            if (scale < minScale) {
                minScale = scale;
                bestExtent = extentToCheck;
            }
        }

        //Return the found extent with the CRS.
        return new ReferencedEnvelope(bestExtent, this.mapCrs);
    }

    private double getProperScale(ReferencedEnvelope extent) {
        double dotPerCm = DPI / 2.54;
        double scale = (extent.getWidth() / getMapOnlyWidth()) * dotPerCm * 100;
        double scaleToFitHeight = (extent.getHeight() / getMapOnlyHeight()) * dotPerCm * 100;
        if (scaleToFitHeight > scale) {
            scale = scaleToFitHeight;
        }
        double[] range = this.getScaleRange();
        if (range[0] >= scale) {
            scale = range[0];
        } else {
            int scaleRangeIndex = 0;

            while (!(range[scaleRangeIndex] < scale
                    && scale <= range[scaleRangeIndex + 1])) {
                scaleRangeIndex += 1;
                if (scaleRangeIndex >= range.length - 1) {
                    break;
                }
            }
            scale = range[scaleRangeIndex + 1];

        }
        return scale;

    }

    private int getMapOnlyWidth() {
        return imageWidth - imageMargin;
    }

    private int getMapOnlyHeight() {
        return imageHeight - imageMargin;
    }
}
