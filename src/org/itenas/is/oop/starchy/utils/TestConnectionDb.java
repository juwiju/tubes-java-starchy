/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.utils;

import java.sql.Connection;

/**
 *
 * @author rumah
 */
public class TestConnectionDb {
    public static void main(String[] args) {
        // Create DatabaseConnection instance
        ConnectorManager connMan = new ConnectorManager();

        // Connect to the database
        Connection conn = connMan.connectDb();

        // Disconnect from the database
        connMan.disconnectDb(conn);
    }
}

