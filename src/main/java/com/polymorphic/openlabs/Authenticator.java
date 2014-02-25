/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polymorphic.openlabs;

/**
 *
 * @author Timothy
 */
public class Authenticator {
    
    /**
     * Checks if a username & password correspond to real user
     * @param UName User name
     * @param Pword Password
     * @return 
     */
    public boolean valid(String UName, String Pword){
        boolean valid = false;
        if(UName.equals("admin")){
            if(Pword.equals("pass")){
                valid = true;
            }
        }
        return valid;
    }
    
}
