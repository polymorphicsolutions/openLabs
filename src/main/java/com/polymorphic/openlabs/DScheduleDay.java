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
public class DScheduleDay {
    
    private ArrayList<ArrayList<String>> slots;
    
    public DScheduleDay(){
        slots = new ArrayList<ArrayList<String>>();
        
        ArrayList<String> slot1 = new ArrayList<String>();
        slot1.add("1000");
        slot1.add("2000");
        
        ArrayList<String> slot2 = new ArrayList<String>();
        slot2.add("4000");
        slot2.add("5000");
        
        ArrayList<String> slot3 = new ArrayList<String>();
        slot3.add("7000");
        slot3.add("8000");
        
        slots.add(slot1);
        slots.add(slot2);
        slots.add(slot3);
    }
    
    public int getSlotCount(){
        return slots.size();
    }
    
    public ArrayList<ArrayList<String>> getSlots(){
        return slots;
    }
    
    public ArrayList<String> getSlot(int i){
        return slots.get(i);
    }
    
    public void setNewSlot(String pStart, String pEnd){
        ArrayList<String> nSlot = new ArrayList<String>();
        nSlot.add(pStart);
        nSlot.add(pEnd);
        slots.add(nSlot);
    }
    
}
