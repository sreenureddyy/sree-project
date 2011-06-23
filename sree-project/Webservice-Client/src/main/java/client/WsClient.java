package client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceClient;

import wsclient.MyService;
import wsclient.MyServiceImplService;
import wsclient.Person;

/**
 * Servlet implementation class WsClient
 */
public class WsClient {

	public static void main(String[] args) {
		String name = "Sreenivasa Reddy";
		Integer id = 765;
		try {
			// Get a handle to web service client interface
			WebServiceClient ann = MyServiceImplService.class
					.getAnnotation(WebServiceClient.class);
			MyServiceImplService service = new MyServiceImplService(new URL(
					"http://localhost:8080/sree-1.0/myService"), new QName(ann
					.targetNamespace(), ann.name()));
			MyService myService = service.getMyServiceImplPort();

			// Invoke methods
			if (name != null) {
				String hello = myService.sayHello(name);
				System.out.println("nameResult :: " + hello);
			}
			if (id != null) {
				Person person = myService.getPerson(id);
				String idResult = "Person [id: " + person.getId() + ", name: "
						+ person.getName() + "]";
				System.out.println("idResult :: " + idResult);
			}

			// now try to send XML String
			String xml = "<name>Padma</name>";
			String returnXml = myService.xmlData(xml);
			System.out.println(returnXml);

			// Now receive a big XML from service
			String po = myService.getPurchaseOrder();
			System.out.println("PURCHASE ORDER\n" + po);

			// Sending Object to webservices
			Person person = new Person();
			person.setName("Ganesh");
			myService.sendPerson(person);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
