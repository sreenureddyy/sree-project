package com.sree.test;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public final class Client {

	public static void main(String[] args) throws Exception {
		String xml = null;
		try {
			// Make a HTTP connection to the server
			URL u = new URL("http://localhost:8080/orderapp_rest/orders");
			HttpURLConnection httpCon = (HttpURLConnection) u.openConnection();
			// Set the request method as POST
			httpCon.setRequestMethod("POST"); 
			httpCon.setDoOutput(true);

			OutputStream os = httpCon.getOutputStream();
			// XML are encoded in UTF-8 format
			OutputStreamWriter wout = new OutputStreamWriter(os, "UTF-8"); 
			wout.write("<?xml version=\"1.0\"?>\r\n");  
			// Add customer name as XML fragment
			wout.write("<order xmlns=\"http://demo.order\"><name>Rajeev</name></order>r\n"); 
			wout.flush();

			// Make implicit connection to the server
			httpCon.getContentLength(); 
			httpCon.disconnect();
   
			os.close();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}
