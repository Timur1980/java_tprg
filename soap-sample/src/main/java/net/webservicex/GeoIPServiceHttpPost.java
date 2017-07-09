package net.webservicex;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.12
 * 2017-07-07T23:27:46.170+07:00
 * Generated source version: 3.1.12
 * 
 */
@WebService(targetNamespace = "http://www.webservicex.net/", name = "GeoIPServiceHttpPost")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface GeoIPServiceHttpPost {

    /**
     * GeoIPService - GetGeoIP enables you to easily look up countries by IP addresses
     */
    @WebMethod(operationName = "GetGeoIP")
    @WebResult(name = "GeoIP", targetNamespace = "http://www.webservicex.net/", partName = "Body")
    public GeoIP getGeoIP(
        @WebParam(partName = "IPAddress", name = "IPAddress", targetNamespace = "")
        java.lang.String ipAddress
    );

    /**
     * GeoIPService - GetGeoIPContext enables you to easily look up countries by Context
     */
    @WebMethod(operationName = "GetGeoIPContext")
    @WebResult(name = "GeoIP", targetNamespace = "http://www.webservicex.net/", partName = "Body")
    public GeoIP getGeoIPContext();
}
