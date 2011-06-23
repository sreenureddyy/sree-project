/**
 * 
 */
package com.sree.sms;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import org.apache.log4j.Logger;

/**
 * @author YSReddi
 * 
 */
public class Way2SmsUtil {

	private static Logger log = Logger.getLogger(Way2SmsUtil.class);
	
	public static void main(String[] args) {
		try {
			sendSMS("9886919190", "trinay", "9886919190", "Test...", "Sree");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String sendSMS(String username, String password,
			String mobile, String message, String name) throws IOException {
		HttpConnection httpConn = null;
		String url = "http://demo.samplephpcodes.com/sms/way2sms.php?uid="
				+ username + "&pwd=" + password;
		InputStream is = null;
		String out = "";
		url = url + "&phone=" + mobile + "&msg=" + urlEncode(message);
		try {
			httpConn = (HttpConnection) Connector.open(url);

			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("User-Agent",
					"Profile/MIDP-1.0 Confirguration/CLDC-1.0");

			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				StringBuffer sb = new StringBuffer();
				is = httpConn.openDataInputStream();
				int chr;
				while ((chr = is.read()) != -1) {
					sb.append((char) chr);
				}
				out = sb.toString();
				log.info(out+ " to " + name+" ["+ mobile+"]");
			} else {
				log.error("Error in opening HTTP Connection. Error#"
						+ respCode);
			}
		} finally {
			if (is != null)
				is.close();
			if (httpConn != null)
				httpConn.close();
		}
		return out;
	}

	public static String urlEncode(String s) {
		try {
			if (s == null) {
				return s;
			}
			StringBuffer sb = new StringBuffer(100);

			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);

				if (((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z'))
						|| ((c >= 'a') && (c <= 'z'))) {
					sb.append(c);
				} else if (c > '\017')
					sb.append("%" + Integer.toHexString(c));
				else {
					sb.append("%0" + Integer.toHexString(c));
				}
			}

			return sb.toString();
		} catch (Exception ex) {
			System.out.println("Exception, URLencode string is " + s);
		}
		return null;
	}
}
