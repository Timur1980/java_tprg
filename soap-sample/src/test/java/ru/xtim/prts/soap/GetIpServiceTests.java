package ru.xtim.prts.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by timur.khisamutdinov on 07.07.2017.
 */
public class GetIpServiceTests {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("94.180.113.11");
        assertEquals(geoIP.getCountryCode(),"RUS");


    }
}
