
package org.cgiar.ccafs.marlo.ocs.ws.client;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01
 * Generated source version: 2.2
 */
@WebService(name = "WSMarlo", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/")
@XmlSeeAlso({ObjectFactory.class})
public interface WSMarlo {


  /**
   * @param agreementId
   * @return
   *         returns java.util.List<org.cgiar.ccafs.marlo.ocs.ws.client.TWsMarloAgreeCountry>
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "getMarloAgreeCountry", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloAgreeCountry")
  @ResponseWrapper(localName = "getMarloAgreeCountryResponse",
    targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloAgreeCountryResponse")
  public List<TWsMarloAgreeCountry>
    getMarloAgreeCountry(@WebParam(name = "agreementId", targetNamespace = "") String agreementId);

  /**
   * @param agreementId
   * @return
   *         returns java.util.List<org.cgiar.ccafs.marlo.ocs.ws.client.TWsMarloAgreeCrp>
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "getMarloAgreeCrp", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloAgreeCrp")
  @ResponseWrapper(localName = "getMarloAgreeCrpResponse", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloAgreeCrpResponse")
  public List<TWsMarloAgreeCrp>
    getMarloAgreeCrp(@WebParam(name = "agreementId", targetNamespace = "") String agreementId);

  /**
   * @param agreementId
   * @return
   *         returns java.util.List<org.cgiar.ccafs.marlo.ocs.ws.client.TWsMarloAgree>
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "getMarloAgreements", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloAgreements")
  @ResponseWrapper(localName = "getMarloAgreementsResponse",
    targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloAgreementsResponse")
  public List<TWsMarloAgree>
    getMarloAgreements(@WebParam(name = "agreementId", targetNamespace = "") String agreementId);

  /**
   * @param agreementId
   * @return
   *         returns java.util.List<org.cgiar.ccafs.marlo.ocs.ws.client.TWsMarloPla>
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "getMarloPla", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloPla")
  @ResponseWrapper(localName = "getMarloPlaResponse", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloPlaResponse")
  public List<TWsMarloPla> getMarloPla(@WebParam(name = "agreementId", targetNamespace = "") String agreementId);


  /**
   * @param plaId
   * @return
   *         returns java.util.List<org.cgiar.ccafs.marlo.ocs.ws.client.TWsMarloPlaCountry>
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "getMarloPlaCountry", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloPlaCountry")
  @ResponseWrapper(localName = "getMarloPlaCountryResponse",
    targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ccafs.marlo.ocs.ws.client.GetMarloPlaCountryResponse")
  public List<TWsMarloPlaCountry> getMarloPlaCountry(@WebParam(name = "plaId", targetNamespace = "") String plaId);

  /**
   * @param resourceId
   * @return
   *         returns java.util.List<org.cgiar.ciat.abw.control.logic.TWsMarloResourceInfo>
   * @throws Exception_Exception
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "getMarloResourceInformation",
    targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ciat.abw.control.logic.GetMarloResourceInformation")
  @ResponseWrapper(localName = "getMarloResourceInformationResponse",
    targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ciat.abw.control.logic.GetMarloResourceInformationResponse")
  public List<TWsMarloResourceInfo>
    getMarloResourceInformation(@WebParam(name = "resourceId", targetNamespace = "") String resourceId);

  /**
   * @param resourceId
   * @return
   *         returns java.util.List<org.cgiar.ciat.abw.control.logic.TWsMarloResStudies>
   * @throws Exception_Exception
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "getMarloResStudies", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ciat.abw.control.logic.GetMarloResStudies")
  @ResponseWrapper(localName = "getMarloResStudiesResponse",
    targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
    className = "org.cgiar.ciat.abw.control.logic.GetMarloResStudiesResponse")
  public List<TWsMarloResStudies>
    getMarloResStudies(@WebParam(name = "resourceId", targetNamespace = "") String resourceId);

}
