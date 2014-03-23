/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polymorphic.openlabs;

import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Timothy
 */
public class SessionHandler {
    //shot this won't work
    //there needs to be a 
    public static void setFavorite(HttpSession sess ,int ID){
        sess.setAttribute(Integer.toString(ID),"F");
    }
    
    public static void unsetFavorite(HttpSession sess, int ID){
        sess.setAttribute(Integer.toString(ID),"U");
    }
    
    public static boolean checkFavorite(HttpSession sess, int ID){
        if(sess.getAttribute(Integer.toString(ID)).equals("F")){
            return true;
        }
        return false;
    }
    
    public static boolean checkAdmin(HttpSession sess, HttpServletRequest req){
        boolean admin = false;
        String sessCheck = "";
        boolean sessionHasAdminField = true;
               
        try{
            sessCheck = sess.getAttribute("admin").toString();
        }
        catch(NullPointerException e){
            sessionHasAdminField = false;
        }
                
        
            if(sessionHasAdminField == true && 
               sessCheck.equals("true")){
                admin = true;
            }
            else{
                String userName = req.getParameter("uname");
                String password = req.getParameter("pword");

                Authenticator auth = new Authenticator();
                boolean validity = auth.valid(userName,password);

                if(validity == true){
                    admin = true;
                    sess.setAttribute("admin", "true");
                }
            }

        return admin;        
    }
    
    public static void logout(HttpSession sess){
        sess.invalidate();
    }
    
    
    
    public static boolean favoriteCheck(HttpServletRequest req, String labId){
        ArrayList<String> favorites = SessionHandler.favoriteCheck(req);
        for(int i = 0; i < favorites.size(); i++){
            if(favorites.get(i).equals(labId)){
                return true;
            }
        }
        return false;
    }
    
    //todo: this hasn't been tested at all
    public static ArrayList<String> favoriteCheck(HttpServletRequest req){
        Cookie[] requestCookies = req.getCookies();
        ArrayList<String> favorites = new ArrayList<String>();
        int numFavorites = 0;
        
        try{
            for(int i = 0; i < requestCookies.length; i++){
                if(requestCookies[i].getValue().equals("favorite")){
                    favorites.add(requestCookies[i].getName());
                    numFavorites++;
                }
            } 
            //System.out.println("#fav: " + numFavorites);
        }
        catch(NullPointerException e){
            //System.out.println("favoriteCheck***");
            System.out.println(e.toString());
            //System.out.println("favoriteCheck***");
        }
        return favorites;
        //ref:http://www.journaldev.com/1956/servlet-cookie-example-tutorial
    }
   
    /*
    //todo: untested
    public static void setFavorite(String labID, HttpServletResponse response){
        
    }*/
    
    //untested
    //return of 1 means a new favorite was added
    public static String checkNewFavorite(HttpServletResponse response,
                                 HttpServletRequest req){
        
        String newFavorite = req.getParameter("favorite");
        if(newFavorite != null){
            Cookie[] requstCookies = req.getCookies();
            boolean alreadyFavorited = false;
            for(int i = 0; i < requstCookies.length; i++){
                if(requstCookies[i].getName().equals(newFavorite)){
                    alreadyFavorited = true;
                }
            }
            if (alreadyFavorited == false){
                Cookie favCookie = new Cookie(newFavorite, "favorite");
                //todo: work this path stuff out
                favCookie.setPath("/");
                response.addCookie(favCookie);
                return newFavorite;
            }         
        }
        //a href=\"TestServlet?sortBy=groupDescription\        
        
        //String toSortBy = request.getParameter("sortBy");
            
            //if(toSortBy == null){ toSortBy = "groupDescription"; }
        return null;
    }
    
    public static String checkUnFavorite(HttpServletResponse response,
                                      HttpServletRequest req){
        String unFavorite = req.getParameter("unFavorite");
        if(unFavorite != null){
            Cookie[] requestCookies = req.getCookies();
            for(int i = 0; i < requestCookies.length; i++){
                if(unFavorite.equals(requestCookies[i].getName())){
                    requestCookies[i].setMaxAge(0);
                    requestCookies[i].setPath("/");
                    response.addCookie(requestCookies[i]);
                    return unFavorite;
                }
            }
        }
        return null;
    }
    
    public static void handleAdminEdits(HttpSession sess, HttpServletRequest req){
        //TODO make this     
    }
    
}
