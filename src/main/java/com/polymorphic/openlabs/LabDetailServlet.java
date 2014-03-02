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
            /* TODO output your page here. You may use following sample code. */
            selectedLabName = "";
            admin = false;
            
            selectedLabName = request.getParameter("name");
            admin = checkAdmin(request);
                       
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
    
    public boolean checkAdmin(HttpServletRequest request){
        boolean admin = false;
        try{
            String adminLoggedIn = request.getSession().getAttribute("admin").toString();
            if(adminLoggedIn.equals("true")){
                admin = true;
                //out.println("<a href=\"logout\">Logout</a>");
            }
        }
        catch(Exception e){
            //out.println("<a href=\"loginPage\">Login</a>");
        }
        return admin;
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
            out.println("    <link rel=\"stylesheet\" href=\"themes/openLabsTheme.min.css\" /> ");
            out.println("    <link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.3.2/jquery.mobile.structure-1.3.2.min.css\" /> ");
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
            out.println("        <button onclick=\"location.reload(true)\" data-icon=\"refresh\" class=\"ui-btn-left\">Refresh</button>");
            out.println("        <!--<h1>Radford University</h1>-->");
            out.println("        <h1>Open Labs</h1>");
            out.println("        <a href=\"settings.html\" data-icon=\"gear\" class=\"ui-btn-right\">Settings</a>");
            out.println("      </div><!-- /header -->");
            out.println("");
            out.println("      <!--<div data-role=\"fieldcontain\">");
            out.println("      <!--<label for=\"search-2\">Search Labs:</label>-->");
            out.println("      <input type=\"search\" name=\"search-2\" id=\"search-2\" value=\"Search\" class=\"searchbar\" />");
            out.println("      <!--</div>-->");
            out.println("");
            
            if(admin == false){
                printLabs(out,response);
            } else {
                printLabsAdmin(out,response);
            }
            out.println("");
            out.println("      <div data-role=\"footer\" data-theme=\"a\" class=\"foot\" data-position=\"fixed\">");
            //out.println("        <h4>Radford University</h4>");
            out.print("        <h4>Radford University ");
            
            //loginPortion(request, out,response);
            if(admin == false){
                out.println("<a href=\"loginPage\">Login</a>");
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
    
    private void printLabs(PrintWriter out, HttpServletResponse response){
        
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
                out.println("<table>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      M");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      T");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      W");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      TR");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      F");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      Sa");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      Su");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("</table>");
                
                out.println("<br><br>");
                
                out.println("<table>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("Available Sofware");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("</table>");
            } else {
                out.println("No lab was specified. Or data could not be retrieved");
            }
        } catch (Exception ex) {
            //Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        out.println("<button class=\"ui-btn\">" +
                    selectedLabName +
                    "</button>");*/

    }

    private void printLabsAdmin(PrintWriter out, HttpServletResponse response){
        
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
                out.println("<table>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      M");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      T");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      W");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      TR");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      F");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      Sa");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      6:00 - 12:00 (bad data)");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("      Su");
                out.println("    </td>");
                out.println("    <td>");
                out.println("      <input type=\"text\" value=\"6:00 - 12:00 (bad data)\"/>");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("</table>");
                
                out.println("<br><br>");
                
                out.println("<table>");
                out.println("  <tr>");
                out.println("    <td>");
                out.println("Available Sofware");
                out.println("    </td>");
                out.println("  </tr>");
                out.println("</table>");
            } else {
                out.println("No lab was specified. Or data could not be retrieved");
            }
        } catch (Exception ex) {
            //Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
    
    
    
}