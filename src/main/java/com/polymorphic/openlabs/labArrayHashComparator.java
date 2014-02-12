/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polymorphic.openlabs;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author tim
 * Barely adapted from: http://stackoverflow.com/questions/5369573/how-sort-an-arraylist-of-hashmaps-holding-several-key-value-pairs-each
 */
class labArrayHashComparator implements Comparator<Map<String, String>>
{
    private final String key;

    public labArrayHashComparator(String key)
    {
        this.key = key;
    }

    @Override
    public int compare(Map<String, String> first,
                       Map<String, String> second){
        String firstValue = first.get(key).replaceAll("\\s+","");
        String secondValue = second.get(key).replaceAll("\\s+","");
        
        try {
            double fv = Double.parseDouble(firstValue);
            double sv = Double.parseDouble(secondValue);
            if(fv > sv){
                return 1;
            } else if(fv < sv){
                return -1;
            }
            return 0;
        } catch (Exception e){
            if( (firstValue != null) &&  (secondValue != null)){
                return firstValue.compareTo(secondValue);
            }
        }
        return 1; //todo: does this maintain or reverse the order?
    }
}