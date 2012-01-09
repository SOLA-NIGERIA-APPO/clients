/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO).
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,this list
 *       of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice,this list
 *       of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *    3. Neither the name of FAO nor the names of its contributors may be used to endorse or
 *       promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.clients.swing.gis;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKBWriter;
import com.vividsolutions.jts.io.WKTReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.sola.clients.swing.gis.data.PojoDataAccess;
import org.sola.webservices.search.QueryForSelect;
import org.sola.webservices.spatial.ResultForNavigationInfo;
import org.sola.webservices.search.ResultForSelectionInfo;
import org.sola.webservices.spatial.MapDefinitionTO;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
//    public AppTest(String testName) {
//        super(testName);
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite() {
//        return new TestSuite(AppTest.class);
//    }
//
    private Boolean skipTests = null;

    /**
     * Checks the SOLA_OPTS environment variable to determine if the Integration Tests such as
     * those using Embedded Glassfish should be skipped or not. 
     * <p> To set this environment variable in Ubuntu, add the following export to the 
     * ~/.gnomerc file: {@code export SOLA_OPTS=SkipIntTests} You may need to create
     * the ~/.gnomerc file if it doesn't exist.</p><p>
     * This variable is used by Bamboo to avoid running Integration tests during the automated build process. 
     * @return 
     */
    private boolean skipIntegrationTest() {
        if (skipTests == null) {
            skipTests = false;
            String solaBuildOptions = System.getenv("SOLA_OPTS");
            if (solaBuildOptions != null && solaBuildOptions.matches(".*SkipIntTests.*")) {
                // Skip running the tests
                skipTests = true;
            }
        }
        return skipTests;
    }

    /**
     * Testing the getMapDefinition
     */
    @Test
    public void testGetMapDefinition() {
        if (skipIntegrationTest()) {
            return;
        }
        System.out.println("Test getMapDefinition");

        MapDefinitionTO result = PojoDataAccess.getInstance().getMapDefinition();
        System.out.println("result (srid): " + result.getSrid());
        System.out.println("result (nr of layers): " + result.getLayers().size());
    }

    /**
     * Testing the web service
     */
    //@Ignore
    @Test
    public void testWebService() throws Exception{
        if (skipIntegrationTest()) {
            return;
        }
        System.out.println("Test web service");

        System.out.println("Initializing web service");
        String layerName = "parcels";

        Date startTime = Calendar.getInstance().getTime();
        System.out.println("Start time:" + startTime.toString());
//        double west = 393650, south = 8465340, east = 400800, north = 8470075;
        double east = 1795771, west = 1776400, north = 5932259, south = 5919888;

        int srid = 2193;
        int pixelResolution = 20;

        System.out.println("Query web service");
//        QueryForNavigation spatialQuery = new QueryForNavigation();
//        spatialQuery.setQueryName(layerName);
//        spatialQuery.setWest(west);
//        spatialQuery.setEast(east);
//        spatialQuery.setSouth(south);
//        spatialQuery.setNorth(north);
//        spatialQuery.setSrid(srid);
        ResultForNavigationInfo result =
                PojoDataAccess.getInstance().GetQueryData(
                "Parcels", west, south, east, north, srid, pixelResolution);
        Date endTime = Calendar.getInstance().getTime();
        System.out.println("Number of results: " + result.getToAdd().size());
        if (result.getToAdd().size() > 0) {
            System.out.println("First id in the list:" + result.getToAdd().get(0).getId());
        }
        System.out.println("Finish time:" + endTime.toString());
        long diffTime = endTime.getTime() - startTime.getTime();
        System.out.println("Total required time (milliseconds) for the query:" + diffTime);

        Object[] params = new Object[2];
        byte[] filteringGeometry = this.getGeometry("POINT(400000 8470000)");
        params[0] = filteringGeometry; //x
        params[1] = 2193; //srid
        QueryForSelect queryInfo = new QueryForSelect();
        queryInfo.setId("parcels");
        queryInfo.setQueryName("dynamic.informationtool.get_parcel");
        queryInfo.setSrid(2193);
        queryInfo.setFilteringGeometry(filteringGeometry);
        List<QueryForSelect> queries = new ArrayList<QueryForSelect>();
        queries.add(queryInfo);
        List<ResultForSelectionInfo> resultInfoList = PojoDataAccess.getInstance().Select(queries);
        System.out.println("Results returned. Total:" + resultInfoList.size());
        if (resultInfoList.get(0).getResult().getValues().size() > 0) {
            System.out.println("First result is:\n" 
                    + resultInfoList.get(0).getResult().getValues().get(0));
        }
    }
    private byte[] getGeometry(String wktGeometry) throws Exception {
        WKTReader wktReader = new WKTReader();
        Geometry geom = wktReader.read(wktGeometry);
        WKBWriter wkbWriter = new WKBWriter();
        return wkbWriter.write(geom);
    }

    @Test
    public void testMessaging() throws Exception{
        if (skipIntegrationTest()) {
            return;
        }
        System.out.println("Test messaging");
        //Messaging messaging = new Messaging();
        System.out.println("Message add_feature_error: " + Messaging.getInstance().getMessageText(
                Messaging.Ids.ADDING_FEATURE_ERROR.toString()));
    }
}
