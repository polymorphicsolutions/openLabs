package com.polymorphic.openlabs;

//import javax.xml.soap.*;
//import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

//import javax.xml.transform.*;
//import javax.xml.transform.stream.*;

public class testDriver{

	public static void main(String args[]){
		LabSOAPHandler lsh = new LabSOAPHandler();
		try{
			//lsh.getData();
			ArrayList<HashMap<String,String>> labData = lsh.getData();
			System.out.println("~~~");
			System.out.println(labData.size());
			System.out.println(labData.get(1).get((String)"groupId"));
			System.out.println("~~~");
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}

}