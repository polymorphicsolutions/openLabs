/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polymorphic.openlabs;

import java.util.ArrayList;

/**
 *
 * @author Timothy
 */
public class DSoftware {
    
    private ArrayList<String> software;
    
    public DSoftware(){
        software = new ArrayList<String>();
        software.add("Office");
        software.add("Eclipse");        
    }
    
    public void addSoftware(String pProgram){
        if(!software.contains(pProgram)){
            software.add(pProgram);
        }
    }

    public ArrayList<String> getSoftware(){
        return software;        
    }
}
