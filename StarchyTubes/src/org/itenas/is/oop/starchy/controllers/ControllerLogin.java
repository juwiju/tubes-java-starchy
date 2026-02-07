/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.itenas.is.oop.starchy.utils.ConnectorManager;

/**
 *
 * @author faradilla maudy
 */

public class ControllerLogin {
    
    ConnectorManager conMan = new ConnectorManager();
    Connection con = conMan.connectDb();
    
    public int User(String username, String password){
        int stat = 0;
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM user where username ='"
                    + username + "' and password ='" + password + "'");
            
            while (rs.next()){
                if (username.equals(rs.getString("username")) && 
                        password.equals(rs.getString("password"))){
                    stat = 1;
                }else {
                    stat = 0;
                }
            }
            
            return stat;
        } catch (SQLException ex) {
            return stat;
        }
    }
}