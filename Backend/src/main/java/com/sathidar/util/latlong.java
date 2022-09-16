package com.sathidar.util;

import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;


public class latlong {
	public static void main(String[] args) throws Exception {
		

			 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			    String postcode="pune";
				try {
					String latLongs[];
//					postcode = reader.readLine();
					latLongs = getLatLongPositions(postcode);
					 System.out.println("Latitude: "+latLongs[0]+" and Longitude: "+latLongs[1]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			  
			  public static String[] getLatLongPositions(String address) throws Exception
			  {
//				  String api_key="AIzaSyD2G3P4LRQt_QnqqRO4wr9p6wmwqh4PSyA";  // durga api key
//				  String api_key="AIzaSyDb3v_GRU_2FKBh00p-wH7fCOTbhP1O528";  // vikas api key
				  String api_key="AIzaSyC-NPkY9u8EgOBKbbj5nXv0Rh0xiSjg6rI";  // new key durga	
				int responseCode = 0;
			    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true&Key="+api_key+"";
			    URL url = new URL(api);
			    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
			    httpConnection.connect();
			    responseCode = httpConnection.getResponseCode();
			    if(responseCode == 200)
			    {
			      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
			      Document document = builder.parse(httpConnection.getInputStream());
			      XPathFactory xPathfactory = XPathFactory.newInstance();
			      XPath xpath = xPathfactory.newXPath();
			      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
			      String status = (String)expr.evaluate(document, XPathConstants.STRING);
			      if(status.equals("OK"))
			      {
			         expr = xpath.compile("//geometry/location/lat");
			         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
			         expr = xpath.compile("//geometry/location/lng");
			         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
			         return new String[] {latitude, longitude};
			      }
			      else
			      {
			         throw new Exception("Error from the API - response status: "+status);
			      }
			    }
			    return null;
			  }
		
}
