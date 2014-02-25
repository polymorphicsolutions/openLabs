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

/**
 *
 * @author Timothy
 */
public class Da201 extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Da201</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Da201 at " + request.getContextPath() + "</h1>");
            
            LabSOAPHandler lsh = new LabSOAPHandler();
            ArrayList<HashMap<String,String>> data = null;
            try {
                data = lsh.getData();
            } catch (Exception ex) {
                Logger.getLogger(Da201.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(int i = 0; i < data.size(); i++){
                if(data.get(i).get("groupDescription").equals("Davis 201")){
                    HashMap<String,String> labInfo = data.get(i);
                    out.println("groupId : " + labInfo.get("groupId") + "</br>");
                    out.println("groupName : " + labInfo.get("groupName") + "</br>");
                    out.println("offCount : " + labInfo.get("offCount") + "</br>");
                    out.println("availableCount : " + labInfo.get("availableCount") + "</br>");
                    out.println("unavailableCount : " + labInfo.get("unavailableCount") + "</br>");
                    out.println("inUseCount : " + labInfo.get("inUseCount") + "</br>");
                    out.println("hasPublishedSchedule : " + labInfo.get("hasPublishedSchedule") + "</br>");
                    out.println("isAvailable : " + labInfo.get("isAvailable") + "</br>");
                    out.println("totalCount : " + labInfo.get("totalCount") + "</br>");
                    out.println("groupDescription : " + labInfo.get("groupDescription") + "</br>");
                    out.println("reservable : " + labInfo.get("reservable") + "</br>");
                    out.println("childGroupCount : " + labInfo.get("childGroupCount") + "</br>");
                    out.println("percentInUse : " + labInfo.get("percentInUse") + "</br>");
                }
            }
            
            
            
            out.println("</body>");
            out.println("</html>");
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

}
