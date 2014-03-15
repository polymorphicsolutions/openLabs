package com.polymorphic.openlabs;

import javax.xml.soap.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class LabSOAPHandler {

        //jsfdklsdjl
    
	private ArrayList<HashMap<String,String>> labsData;
	
	public LabSOAPHandler(){}
	
	public ArrayList<HashMap<String,String>> getData() throws Exception{
		labsData = new ArrayList<HashMap<String,String>>();
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		//String url = "http://labstat.radford.edu/labstats/WebServices/Statistics.asmx?WSDL";
		String url = "https://labstat.radford.edu/labstats/WebServices/Statistics.asmx";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
		try{
			Iterator<SOAPElement> children1;
			children1 = soapResponse.getSOAPBody().getChildElements();
			SOAPElement child2 = children1.next(); //"GetGroupedCurrentStatsResponse: null"
			
			System.out.println("children1");
			System.out.println(children1.toString());
			System.out.println("child2");
			System.out.println(child2.toString());
			
			Iterator<SOAPElement> children2;
			children2 = child2.getChildElements();
			SOAPElement child3 = children2.next();
			
			System.out.println("child3");
			System.out.println(child3.toString());
			
			Iterator<SOAPElement> children3;
			children3 = child3.getChildElements(); //presumably getGroupedCurrent Stats
			
			ArrayList<SOAPElement> labs = new ArrayList<SOAPElement>();
			while(children3.hasNext()){
				labs.add(children3.next());
			}
			
			for( int i = 0; i < labs.size(); i++){
				SOAPElement currentLab = labs.get(i);
				Iterator<SOAPElement> currentLabContents = currentLab.getChildElements();
				HashMap<String,String> currentLabToAdd = new HashMap<String,String>();
				
				while(currentLabContents.hasNext()){
					SOAPElement toAdd = currentLabContents.next();
					currentLabToAdd.put(toAdd.getTagName(),toAdd.getValue());
				}
				labsData.add(currentLabToAdd);
				//System.out.println("");
			}
			//System.out.println(labs.size());
			
		} catch (SOAPException e) {
			//System.out.println("WHOOPS");
		}		
		
        soapConnection.close();		
		
		return labsData;
		
	}
	
    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        //String serverURI = "http://ws.cdyne.com/";
        String serverURI = "http://tempuri.org/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tem", serverURI);

        // SOAP Body
		SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("GetGroupedCurrentStats", "tem");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        //headers.addHeader("SOAPAction", serverURI  + "GetGroupedCurrentStats");
        headers.addHeader("SOAPAction", serverURI + "GetGroupedCurrentStats");
		
        soapMessage.saveChanges();

        /* Print the request message */
		/*
        System.out.print("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
		*/

        return soapMessage;
    }
    
    public void addFavorites(ArrayList<String> favorited,
                             ArrayList<HashMap<String,String>> labData){
        for(int i = 0; i < favorited.size(); i++){
            for(int j = 0; j < labData.size(); j++){
                if(favorited.get(i).equals(
                   labData.get(j).get("groupId"))){
                    labData.get(j).put("favorite","true");
                }
            }
        }
    }

}


/*package com.polymorphic.openlabs;

import javax.xml.soap.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class LabSOAPHandler {

        //jsfdklsdjl
    
	private ArrayList<HashMap<String,String>> labsData;
	
	public LabSOAPHandler(){}
	
	public ArrayList<HashMap<String,String>> getData() throws Exception{
		labsData = new ArrayList<HashMap<String,String>>();
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		//String url = "http://labstat.radford.edu/labstats/WebServices/Statistics.asmx?WSDL";
		String url = "https://labstat.radford.edu/labstats/WebServices/Statistics.asmx";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
		try{
			Iterator<SOAPElement> children1;
			children1 = soapResponse.getSOAPBody().getChildElements();
			SOAPElement child2 = children1.next(); //"GetGroupedCurrentStatsResponse: null"
			
			System.out.println("children1");
			System.out.println(children1.toString());
			System.out.println("child2");
			System.out.println(child2.toString());
			
			Iterator<SOAPElement> children2;
			children2 = child2.getChildElements();
			SOAPElement child3 = children2.next();
			
			//System.out.println("child3");
			//System.out.println(child3.toString());
			
			Iterator<SOAPElement> children3;
			children3 = child3.getChildElements(); //presumably getGroupedCurrent Stats
			
			ArrayList<SOAPElement> labs = new ArrayList<SOAPElement>();
			while(children3.hasNext()){
				labs.add(children3.next());
			}
			
			for( int i = 0; i < labs.size(); i++){
				SOAPElement currentLab = labs.get(i);
				Iterator<SOAPElement> currentLabContents = currentLab.getChildElements();
				HashMap<String,String> currentLabToAdd = new HashMap<String,String>();
				
				while(currentLabContents.hasNext()){
					SOAPElement toAdd = currentLabContents.next();
					currentLabToAdd.put(toAdd.getTagName(),toAdd.getValue());
				}
				labsData.add(currentLabToAdd);
				//System.out.println("");
			}
			//System.out.println(labs.size());
			
		} catch (SOAPException e) {
			//System.out.println("WHOOPS");
		}		
		
        soapConnection.close();		
		
		return labsData;
		
	}
	
    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        //String serverURI = "http://ws.cdyne.com/";
        String serverURI = "http://tempuri.org/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tem", serverURI);

        // SOAP Body
		SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("GetGroupedCurrentStats", "tem");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        //headers.addHeader("SOAPAction", serverURI  + "GetGroupedCurrentStats");
        headers.addHeader("SOAPAction", serverURI + "GetGroupedCurrentStats");
		
        soapMessage.saveChanges();

        /* Print the request message */
		/*
        System.out.print("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
		*/


/*

        return soapMessage;
    }

}

*/