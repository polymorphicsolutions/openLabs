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
public class DSchedule {
    
    private ArrayList<DScheduleDay> days;
    //0 -> mon
    //1 -> tues
    //6 -> sun
    
    public DSchedule(){
        days = new ArrayList<DScheduleDay>();
        
        for(int i=0; i < 7; i++){
            days.add(new DScheduleDay());            
        }
        
    }
    
    public void setDaySchedule(DScheduleDay pDay, int dayNum){
        days.add(dayNum, pDay);
    }
    
    public DScheduleDay getDay(int dayNum){
        return days.get(dayNum);
    }
    
}
