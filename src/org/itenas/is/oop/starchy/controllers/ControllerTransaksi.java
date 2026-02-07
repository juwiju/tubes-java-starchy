/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.controllers;

/*import com.sun.jdi.connect.spi.Connection;
import utils.ConnectionManager;
import java.sql.*;
import java.util.List;
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.itenas.is.oop.starchy.models.Transaksi;
import org.itenas.is.oop.starchy.models.TransaksiDetail;
import org.itenas.is.oop.starchy.models.User;

/**
 *
 * @author Anis Sarah
 */
public class ControllerTransaksi {
    private Connection con;

    public ControllerTransaksi(Connection con) {
        this.con = con;
    }
    
    public boolean create(Transaksi tr, List<TransaksiDetail> daftarDetail, User user) {
        String queryTr = "INSERT INTO transaksi (user_id, transaksi_date, transaksi_total, transaksi_metode) VALUES (?, ?, ?, ?)";

        try {
            // ID transaksi auto
            PreparedStatement psTr = con.prepareStatement(queryTr, Statement.RETURN_GENERATED_KEYS);

            psTr.setInt(1, user.getUser_id()); 
            psTr.setTimestamp(2, new java.sql.Timestamp(tr.getTransaksi_date().getTime()));
            psTr.setInt(3, tr.getTransaksi_total()); 
            psTr.setString(4, tr.getTransaksi_metode().name());
            psTr.executeUpdate();

            // Ambil ID yang barusan dibuat
            ResultSet rs = psTr.getGeneratedKeys();
            if (rs.next()) {
                int lastId = rs.getInt(1);
                ControllerTransaksiDetail ctrlDetail = new ControllerTransaksiDetail();

                for (TransaksiDetail detail : daftarDetail) {
                    if (detail.getTransaksi() == null) {
                        detail.setTransaksi(new Transaksi());
                    }
                    detail.getTransaksi().setTransaksi_id(lastId);

                    // Menyimpan detail ke database
                    ctrlDetail.create(detail);

                    // Kurangi stok
                    detail.kurangistok(con, detail.getJumlah());
                }

                // Menghitung total belanja
                this.hitungTotalTransaksi(lastId);
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Error Transaksi: " + ex.getMessage());
            return false;
        }
    }
    
    public void hitungTotalTransaksi(int idTr) {
        String sql = "UPDATE transaksi SET transaksi_total = (SELECT SUM(subtotal) " +
                     "FROM transaksi_detail WHERE transaksi_id = ?) WHERE transaksi_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTr);
            ps.setInt(2, idTr);
            ps.executeUpdate();
            System.out.println("Total Transaksi ID " + idTr + " berhasil diupdate!");
        } catch (SQLException e) {
            System.out.println("Gagal Update Total: " + e.getMessage());
        }
    }
    
    
}
