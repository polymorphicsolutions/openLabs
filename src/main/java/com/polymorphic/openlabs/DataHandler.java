package com.polymorphic.openlabs;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Timothy
 */

//TODO incorporate databaseHandler


public class DataHandler {
    //private LabSOAPHandler lsh;
    //private dbHandler dbh;

    private ArrayList<HashMap<String,String>> labsData;

    public DataHandler(){}

    public ArrayList<HashMap<String,String>> getData() throws Exception{	
        //dbHandler dbh = new dbHandler();
        LabSOAPHandler lsh = new LabSOAPHandler();
        
        /*if(dbHandler.getTime() < max_time){
            //do stuff
        } else {*/
            labsData = lsh.getData();
        /*  dbHandler.update(labsData);
        }*/        
        
        return labsData;

    }
    
    public void addFavorites(ArrayList<String> favorited,
                             ArrayList<HashMap<String,String>> labData){
        for(int i=0; i < labData.size(); i++){
            labData.get(i).put("favorite","B");
        }
        
        for(int i = 0; i < favorited.size(); i++){
            for(int j = 0; j < labData.size(); j++){
                if(favorited.get(i).equals(labData.get(j).get("groupId"))){
                        labData.get(j).put("favorite","A");
                }
            }
        }
    }
    
    public DSchedule getSchedule(String LabID){
        //dbHandler.getHours
        databaseHandler dbH = new databaseHandler();
        
        ArrayList<ArrayList<String>> mon = dbH.getScheduleByDay(LabID, "Monday");
        ArrayList<ArrayList<String>> tue = dbH.getScheduleByDay(LabID, "Tuesday");
        ArrayList<ArrayList<String>> wed = dbH.getScheduleByDay(LabID, "Wednesday");
        ArrayList<ArrayList<String>> thu = dbH.getScheduleByDay(LabID, "Thursday");
        ArrayList<ArrayList<String>> fri = dbH.getScheduleByDay(LabID, "Friday");
        ArrayList<ArrayList<String>> sat = dbH.getScheduleByDay(LabID, "Saturday");
        ArrayList<ArrayList<String>> sun = dbH.getScheduleByDay(LabID, "Sunday");

        DSchedule ds = new DSchedule();
        ds.setDay(mon,0);
        ds.setDay(tue,1);
        ds.setDay(wed,2);
        ds.setDay(thu,3);
        ds.setDay(fri,4);
        ds.setDay(sat,5);
        ds.setDay(sun,6);

        return ds;
    }
    
    public ArrayList<String> getSoftware(String LabID){
        databaseHandler dbH = new databaseHandler();
        return dbH.getSoftware(LabID);
        
        /*
        ArrayList<String> software = new ArrayList<String>();
        software.add("Office");
        software.add("Photoshop");
        software.add("Blender");
        
        return software;*/
    }
    
    public ArrayList<String> checkNewSoftware(String LabID, HttpServletRequest req){
        ArrayList<String> software = new ArrayList<String>();
        
        String nValue = req.getParameter("newSoftware0");
        if(nValue != null && !nValue.equals("")){
            software.add(nValue);
        }
        nValue = req.getParameter("newSoftware1");
        if(nValue != null && !nValue.equals("")){
            software.add(nValue);
        }
        nValue = req.getParameter("newSoftware2");
        if(nValue != null && !nValue.equals("")){
            software.add(nValue);
        }
        
        //check 3 slots
        return software;
    }
    
    public ArrayList<String> checkRemoveSoftware(String LabID, HttpServletRequest req, int count){
        ArrayList<String> toRemove = new ArrayList<String>();
        
        for(int i = 0; i < count; i++){
            String check = req.getParameter("toRemove" + i);
            if(check != null && !check.equals("")){
                toRemove.add(check);
            }
        }
        
        
        
        //check removeCheckBoxes
        return toRemove;
    }
    
    public ArrayList<ArrayList<String>> checkLabDetailChange(String LabID, HttpServletRequest req){
        System.out.println("A");
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        System.out.println("B");
        data.add(cldcDay(LabID,"Monday",req));
        System.out.println("Size" + data.size() + " " + data.get(0).size());
        data.add(cldcDay(LabID,"Tuesday",req));
        System.out.println("Size" + data.size() + " " + data.get(1).size());
        data.add(cldcDay(LabID,"Wednesday",req));
        System.out.println("Size" + data.size() + " " + data.get(2).size());
        data.add(cldcDay(LabID,"Thursday",req));
        System.out.println("Size" + data.size() + " " + data.get(3).size());
        data.add(cldcDay(LabID,"Friday",req));
        System.out.println("Size" + data.size() + " " + data.get(4).size());
        data.add(cldcDay(LabID,"Saturday",req));
        System.out.println("Size" + data.size() + " " + data.get(5).size());
        data.add(cldcDay(LabID,"Sunday",req));
        System.out.println("Size" + data.size() + " " + data.get(6).size());
        System.out.println("C");
        //todo: there has to be some sort of bad data catch here
        
        return data;
    }
    
    public ArrayList<String> cldcDay(String LabId, String DayName, HttpServletRequest req){
        /* stub data
        ArrayList<String> ass = new ArrayList<String>();
        ass.add("1000");
        ass.add("2000");
        ass.add("3000");
        ass.add("5000");
        return ass;
        */
        
        int num = 0;
        boolean found = true;
        boolean badData = false;
        
        if(LabId == null || LabId.equals("")){
            badData = true;
        }
        
        ArrayList<String> nDay = new ArrayList<String>();
        
        
        System.out.println("1");
        
        while(found == true && badData == false){
            if(req.getParameter(DayName + "Time" + num) != null){
                String nValue = req.getParameter(DayName + "Time" + num);
                
                System.out.println("2");
                
                //1500-2000
                nValue = nValue.replaceAll("\\s","");
               
                if(nValue.charAt(4) != '-'){
                    badData = true;
                    found = false;
                }
                else {
                    nDay.add(nValue.substring(0,4));
                    nDay.add(nValue.substring(5,9));
                }
                                        
                num = num + 1;
            } else {
                badData = true;
                found = false;
            }
        }
        
        System.out.println("1Done");
        
        if(badData){
            //todo: Something should indicate this is proabably bad data
            return nDay;
        }
        return nDay;        
        
    }
    
    

}