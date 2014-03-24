/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polymorphic.openlabs;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tim
 */
public class LabDetailServlet extends HttpServlet {
    
    StringBuilder DebugStr;
    
    String selectedLabName;

    boolean admin;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            DebugStr = new StringBuilder("");
            
            admin = SessionHandler.checkAdmin(request.getSession(),request);
            
            //SessionHandler.checkNewFavorite(response,request);
            
            selectedLabName = "";
            selectedLabName = request.getParameter("name");
            
            
            DataHandler dh = new DataHandler();
            //todo: handle this refresh required 
            //todo: handle selectedLabName == null
            
            try{
                if(request.getParameter("MondayTime0").equals(null)){
                    //nothing
                } else {

                    System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHh");
                    ArrayList<ArrayList<String>> schedChanges = dh.checkLabDetailChange(selectedLabName,request);
                    System.out.println("FUCKKKKKKKK");
                    //out.println(schedChanges.size() + "*/*/");

                    if((schedChanges.size() > 6)){
                        System.out.println("SSSSSSFFFFFFFFFFDDDDDDDD");
                        for(int i=0; i< schedChanges.size(); i++){
                            ArrayList<String> curDay = schedChanges.get(i);
                            if(curDay.size() > 0){
                                System.out.println("QWERT");
                                for(int j=0; j<curDay.size(); j=j+2){
                                    System.out.println(curDay.get(j));
                                    System.out.println(curDay.get(j+1));
                                    DebugStr.append("/n" + curDay.get(j) + " " + curDay.get(j+1) + "<br>");
                                    System.out.println("DEBUG:" + DebugStr);
                                }
                            }
                            out.println("\n");
                        }
                    }
                    System.out.println("DDDDDDDDDDDDDDSSSSSSFFFFFFFFFFDDDDDDDD");
                
                }                
                
            }catch(Exception e){
                //TODO: something
                //out.println(e.toString());
            }
            
            System.out.println("AA");
            
            DebugStr.append("<hr>");
            
            System.out.println("AA");
            System.out.println("AA");
            ArrayList<String> soft = dh.checkNewSoftware(selectedLabName, request);
            System.out.println("BB");
            System.out.println("BB");
            DebugStr.append("New software:<br>");
            for(int i=0; i<soft.size(); i++){
                //print software? add it to debugStr?
                DebugStr.append(soft.get(i));
                DebugStr.append("<br>");
            }
            
            
            
            ArrayList<String> curSW = dh.getSoftware(selectedLabName);
            ArrayList<String> softRemove = dh.checkRemoveSoftware(selectedLabName, request, curSW.size());
            DebugStr.append("Removed Software: <br>");
            for(int i = 0; i < softRemove.size(); i++){
                DebugStr.append(softRemove.get(i));
                DebugStr.append("<br>");
            }
            
            
            
                       
            out.println("<!DOCTYPE html> ");
            out.println("<html> ");
            out.println(" ");
            
            printHeader(out, response);
            printBody(request, out, response);                
            
            out.println("</html> ");
        } finally {
            out.close();
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void printHeader(PrintWriter out, HttpServletResponse response)
        throws ServletException, IOException {
        try {

            ///* old print
            out.println("  <head> ");
            out.println("    <meta charset=\"utf-8\"> ");
            out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\"/> ");
            //out.println("    <link rel=\"stylesheet\" href=\"themes/openLabsTheme.min.css\" /> ");
            //out.println("    <link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.3.2/jquery.mobile.structure-1.3.2.min.css\" /> ");
            out.println("    <title>Open Labs</title>  ");
            out.println("    <link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.2.1/jquery.mobile-1.2.1.min.css\" /> ");
            out.println("    <script src=\"http://code.jquery.com/jquery-1.8.3.min.js\"></script> ");
            out.println("    <script src=\"http://code.jquery.com/mobile/1.2.1/jquery.mobile-1.2.1.min.js\"></script> ");
            out.println("    <script> ");
            out.println("      var incoming = window.location.search; ");
            out.println("      if (incoming.substring(0, 1) === '?') ");
            out.println("      { ");
            out.println("        incoming = incoming.substring(1); ");
            out.println("      } ");
            out.println(" ");
            out.println("      function setCookie(c_name, value, exdays) ");
            out.println("      { ");
            out.println("        var exdate = new Date(); ");
            out.println("        exdate.setDate(exdate.getDate() + exdays); ");
            out.println("        var c_value = escape(value) + ((exdays == null) ? \"\" : \"; expires=\" + exdate.toUTCString()); ");
            out.println("        document.cookie = c_name + \"=\" + c_value; ");
            out.println("      } ");
            out.println("    </script> ");
            out.println("    <style> ");
            out.println("      .page ");
            out.println("      { ");
            out.println("        margin: 0 auto; ");
            out.println("        overflow-x: hidden; ");
            out.println("        background:#001f44; ");
            out.println("        color: white;");
            out.println("      } ");
            out.println("      h1, h4 ");
            out.println("      { ");
            out.println("        margin: 0 auto; ");
            out.println("        overflow-x: hidden; ");
            out.println("      } ");
            out.println("      .head, .foot ");
            out.println("      { ");
            out.println("        margin: 0 auto; ");
            out.println("        overflow-x: hidden; ");
            out.println("        background:#c2011b; ");
            out.println("      } ");
            out.println("      .buttons ");
            out.println("      { ");
            out.println("        margin: 0 auto; ");
            out.println("        width: 300px; ");
            out.println("        padding-top: 10px; ");
            out.println("        padding-bottom: 10px; ");
            out.println("        overflow-x: hidden; ");
            out.println("        background:#001f44; ");
            out.println("        color:blue; ");
            out.println("      } ");
            out.println("      .searchbar ");
            out.println("      { ");
            out.println("        width: 350px; ");
            out.println("        background:#001f44; ");
            out.println("      } ");
            out.println("      h2, h3, td { ");
            out.println("        color: white; ");
            out.println("      } ");
            out.println("    </style> ");
            out.println("  </head>  ");
            //*/
        } finally {
            //out.close();
        }
    }
	
    private void printBody(HttpServletRequest request, PrintWriter out, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println(" ");
            out.println("  <body> ");
            
            out.println("");
            out.println("    <div data-role=\"page\" class=\"page\">");
            out.println("");
            out.println("      <div data-role=\"header\" data-theme=\"a\" class=\"head\" data-position=\"fixed\">");
            out.println("        <button onclick=\"location.reload(true)\" data-icon=\"refresh\" class=\"ui-btn-left\" data-inline=\"true\">Refresh</button>");
            out.println("        <!--<h1>Radford University</h1>-->");
            out.println("        <h1>Open Labs</h1>");
            out.println("        <a href=\"settings.html\" data-icon=\"gear\" class=\"ui-btn-right\">Settings</a>");
            out.println("      </div><!-- /header -->");
            out.println("");
            out.println("      <!--<div data-role=\"fieldcontain\">-->");
            out.println("      <!--<label for=\"search-2\">Search Labs:</label>-->");
            out.println("      <input type=\"search\" name=\"search-2\" id=\"search-2\" value=\"Search\" class=\"searchbar\" />");
            
            out.println("");
            
            if(admin == false){
                printLabs(out,response,request);
            } else {
                printLabsAdmin(out,response, request);
            }
            
            
            if(admin == true){
                out.println("<h1>");
                out.println(DebugStr.toString());
                out.println("</h1>");
            }
            
            
            
            
            out.println("");
            out.println("      <div data-role=\"footer\" data-theme=\"a\" class=\"foot\" data-position=\"fixed\">");
            //out.println("        <h4>Radford University</h4>");
            out.print("        <h4>Radford University ");
            
            //loginPortion(request, out,response);
            if(admin == false){
                
                out.println("<a href=\"loginPage"
                        + "?name="
                        + selectedLabName
                        + "\">Login</a>");
            } else {
                out.println("<a href=\"Logout\">Log out</a>");
            }
            
            out.println("</h4>");
            
            
            out.println("      </div><!-- /footer -->");
            out.println("");
            out.println("    </div><!-- /page -->");
            out.println("");
            out.println("  </body>");

        } finally {
            //out.close();
        }
    }
    
    private void printLabs(PrintWriter out, HttpServletResponse response, HttpServletRequest req){
        LabSOAPHandler lsh = new LabSOAPHandler();
        
        try {
            ArrayList<HashMap<String,String>> data = lsh.getData();
            HashMap<String,String> labData = new HashMap<String,String>();
            
            for(int i = 0; i < data.size(); i++){               
                if(data.get(i).get("groupId").equals(selectedLabName)){
                    labData = data.get(i);  
                }
            }
            
            if(labData.size() > 0){
                out.println("<h2>" + labData.get("groupDescription") + "</h2>");
                out.println("<h3>" + labData.get("totalCount") + " computers total </h3>");
                out.println("<h3>" + labData.get("availableCount") + " computers unocupied </h3>");

                //TODO: PRINT LABHOURS
                //printLabAdminForm(out,response,req);
                //printHours(out,response,req,false);
                printSoftware(out,response,req,false);
                //printLabAdminFormClose(out,response,req);
                
                
            } else {
                out.println("No lab was specified. Or data could not be retrieved");
            }
        } catch (Exception ex) {
            //Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }       

    }

    private void printLabsAdmin(PrintWriter out, HttpServletResponse response, HttpServletRequest req){
        
        LabSOAPHandler lsh = new LabSOAPHandler();
        
        try {
            ArrayList<HashMap<String,String>> data = lsh.getData();
            HashMap<String,String> labData = new HashMap<String,String>();
            
            for(int i = 0; i < data.size(); i++){               
                if(data.get(i).get("groupId").equals(selectedLabName)){
                    labData = data.get(i);  
                }
            }
            
            if(labData.size() > 0){
                out.println("<h2>" + labData.get("groupDescription") + "</h1>");
                out.println("<h3>" + labData.get("totalCount") + " computers total </h3>");
                out.println("<h3>" + labData.get("availableCount") + " computers unocupied </h3>");
                out.println("<hr>");

                //TODO: PRINT LABHOURS
                printLabAdminForm(out,response,req);
               // printHours(out,response,req,true);
                printSoftware(out,response,req,true);
                printLabAdminFormClose(out,response,req);
                
            } else {
                out.println("No lab was specified. Or data could not be retrieved");
            }
        } catch (Exception ex) {
            //Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void printLabAdminForm(PrintWriter out, HttpServletResponse reqponse, HttpServletRequest req){
        out.println("<form name=\"edits\" action=\"LabDetailServlet"
                + "?name="
                + selectedLabName
                + "\" method=\"post\">");
        
    }
    
    private void printLabAdminFormClose(PrintWriter out, HttpServletResponse reqponse, HttpServletRequest req){
        out.println("<input type=\"submit\" value=\"submit\">");
        out.println("</form>");
    }
    
    private void printHours(PrintWriter out, HttpServletResponse response, HttpServletRequest req, boolean admin){
        DataHandler dh = new DataHandler();
        DSchedule schedule = dh.getSchedule(selectedLabName);
        
        out.println("<h3>Schedule:</h3>");
        out.println("<table>");
        printScheduleDay("Monday", schedule.getDay(0), out, admin);
        printScheduleDay("Tuesday", schedule.getDay(1), out, admin);
        printScheduleDay("Wednesday", schedule.getDay(2), out, admin);
        printScheduleDay("Thursday", schedule.getDay(3), out, admin);
        printScheduleDay("Friday", schedule.getDay(4), out, admin);
        printScheduleDay("Saturday", schedule.getDay(5), out, admin);
        printScheduleDay("Sunday", schedule.getDay(6), out, admin);
        out.println("</table>");
        out.println("<hr>");
        
        out.println("");
    }
    
    private void printSoftware(PrintWriter out, HttpServletResponse response, HttpServletRequest req, boolean admin) throws IOException{
        PrintWriter out2 = response.getWriter();
        DataHandler dh = new DataHandler();
        ArrayList<String> software = dh.getSoftware(selectedLabName);
        
        
        out2.println("<h3>Software:</h3>");
        out2.println("<table>");
        
        for(int i=0; i<software.size();i++){
            if(admin == true){
                out2.println("  <tr>");
                out2.println("    <td>");
                out2.println("<input type=\"checkbox\" name=\""
                        + "toRemove" + Integer.toString(i)
                        + "\" value=\""
                        + software.get(i)
                        + "\" />");
                out2.println("    </td>");
                out2.println("    <td>");
                out2.println(software.get(i));
                out2.println("    </td>");
                out2.println("  </tr>");
                
            } else {
                out2.println("<tr>");
                out2.println("  <td></td>");
                out2.println("  <td>");
                out2.println(software.get(i));
                out2.println("  </td>");
                out2.println("</tr>");
            }
        }
        out2.println("</table>");
        
        
        if(admin){
            out2.println("<table>");
            out2.println("  <tr>");
            out2.println("    <td>");
            out2.println("      New Software: ");
            out2.println("    </td>");
            out2.println("    <td>");
            out2.println("      <input type=\"text\" name=\"newSoftware0\" />");      
            out2.println("    </td>");
            out2.println("  </tr>");
            out2.println("  <tr>");
            out2.println("    <td>");
            out2.println("    </td>");
            out2.println("    <td>");
            out2.println("      <input type=\"text\" name=\"newSoftware1\" />");
            out2.println("    </td>");
            out2.println( "  </tr>");
            out2.println("  <tr>");
            out2.println("    <td>");
            out2.println("    </td>");
            out2.println("    <td>");
            out2.println("      <input type=\"text\" name=\"newSoftware2\" />");
            out2.println("    </td>");
            out2.println("  </tr>");
            out2.println("</table>");
        }
        
    }
    
    private void printScheduleDay(String day, DScheduleDay pDay,
            PrintWriter out, boolean admin){
        out.println("  <tr>");
        out.println("    <td>");
        out.println("       " + day);
        out.println("    </td>");
        
        for(int i = 0; i < pDay.getSlotCount(); i++){
            
            ArrayList<String> slot = pDay.getSlot(i);
            
            if (i != 0){
                out.println("  <tr>");
                out.println("    <td>");
                out.println("    </td>");
            }

            out.println("    <td>");
            if(admin){
            out.println("      <input type=\"text\" " +
                        "\" name=\"" + day + "Time" + i + "\" " +
                        "value=\"" + slot.get(0) + "-" + slot.get(1) + "\" >" );
            } else {
                out.println("      " + slot.get(0) + "-" + slot.get(1));
            }

            out.println("    </td>");
            out.println("  </tr>");
                    

        }
        if(admin){
            out.println("  <tr>");
            out.println("    <td>");
            out.println("    </td>");
            out.println("    <td>");
            out.println("      " + "<input type=\"text\" name=\"lname\" >");
            out.println("    </td>");
            out.println("  </tr>");
        }
    }
    
    
    
}