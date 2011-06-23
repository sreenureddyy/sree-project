package com.sree.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://YSReddi.com", name = "MyService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface MyService {

    String sayHello(@WebParam(name = "name") String name);

    //Person getPerson(@WebParam(name = "id") Integer id);

    String xmlData(@WebParam(name = "data") String data);

    //void sendPerson(@WebParam(name = "sendPerson") Person person);
    
    /** Returns a purchase order in xml format. */
    String getPurchaseOrder();
}
