/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polymorphic.openlabs.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stephenwright
 */
public class dbConnection {

    {
        try {
            String url = "jdbc:mysql://localhost/team02";
            Connection conn = DriverManager.getConnection(url, "team02", "password1#");
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
