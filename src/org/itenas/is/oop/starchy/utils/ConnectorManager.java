/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rumah
 */
public class ConnectorManager {
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/starchy";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String username = "root";
    private String password = "basdat2025";
    
    // Constructor untuk memuat driver JDBC
    public ConnectorManager() {
        try {
            Class.forName(driver);
            System.out.println("Driver berhasil dimuat.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error saat memuat driver: " + e.getMessage());
        }
    }
    
    // Metode untuk membuka koneksi ke database
    public Connection connectDb() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Error saat membuka koneksi: " + e.getMessage());
            return null;
        }
    }

    // Metode untuk menutup koneksi ke database
    public void disconnectDb(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Koneksi berhasil ditutup.");
            } catch (SQLException e) {
                System.err.println("Error saat menutup koneksi: " + e.getMessage());
            }
        }
    }
}

