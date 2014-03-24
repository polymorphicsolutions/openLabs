/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymorphic.openlabs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stephenwright
 */
public class databaseHandler {
    
    private jdbcConnection jdbc;
    private Connection connect;
    private ResultSet results;
    private Statement statement;
    
    public databaseHandler() {
        
        jdbc = new jdbcConnection();
        connect = jdbc.getMyConnection();
        
    }
    
    public ResultSet getResults(String lab) throws SQLException{
        statement  = connect.createStatement();
        results  = statement.executeQuery("SELECT * FROM Installations WHERE lab = '"+lab+"'");
        return results;
    }
    
    public ArrayList<String> getSoftware(String lab) {
        //Instantiating the software data type
        DSoftware ds = new DSoftware();
        try {
            //Creating the statement and executing the query
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT s.software FROM Software s WHERE lab = '" + lab + "'");
            //Looping through the result set and filling the software container
            while (results.next()) {
                ds.addSoftware(results.getString("software"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds.getSoftware();
    }
    
    /*      This is commented out for compiling's sake
	    As of right now I cannot recall grabbing a whole schedule
    
    public ArrayList<String> getSchedule(String lab){
        DSchedule ds = new DSchedule();
        String queryResult = "";
        try {
            //Creating the statement and executing the query
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT * FROM LabSchedule WHERE lab = '" + lab + "'");
            //Looping through the result set and filling the software container
            while (results.next()) {
                queryResult = "";
                queryResult = results.getString("day")+": "+results.getString("start_time")+
                        "-"+results.getString("end_time");
                ds.addDays(queryResult);
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds.getDays();
    }
    
    */
    
    public ArrayList<ArrayList<String>> getScheduleByDay(String lab, String day){
        ArrayList<ArrayList<String>> daySchedule = new ArrayList<ArrayList<String>>();
		
        String queryResult = "";
        try {
            //Creating the statement and executing the query
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT * FROM LabSchedule WHERE lab = '" + lab + "' AND day = '" + day +"'");
            //Looping through the result set and filling the software container
            while (results.next()) {
				ArrayList<String> curSlot = new ArrayList<String>();
                curSlot.add(results.getString("start_time"));
				curSlot.add(results.getString("end_time"));
				
				daySchedule.add(curSlot);
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return daySchedule;
    }
	
	/* old
    public ArrayList<String> getScheduleByDay(String lab, String day){
        DSchedule ds = new DSchedule();
        String queryResult = "";
        try {
            //Creating the statement and executing the query
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT * FROM LabSchedule WHERE lab = '" + lab + "' AND day = '" + day +"'");
            //Looping through the result set and filling the software container
            while (results.next()) {
                queryResult = "";
                queryResult = results.getString("day")+": "+results.getString("start_time")+
                        "-"+results.getString("end_time");
                ds.addDays(queryResult);
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds.getDays();
    }
	*/
}
