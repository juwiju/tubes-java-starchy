/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.itenas.is.oop.starchy.utils.ConnectorManager; 
import java.sql.*;
import org.itenas.is.oop.starchy.models.Transaksi;
import org.itenas.is.oop.starchy.models.TransaksiDetail;
import org.itenas.is.oop.starchy.models.User;
/**
 *
 * @Juwita
 */
public class ControllerTransaksiDetail {
    ConnectorManager conMan = new ConnectorManager();
    Connection con = conMan.connectDb();

    public boolean create(TransaksiDetail detail) {
        String sql = "INSERT INTO transaksi_detail (transaksi_id, menu_id, jumlah, subtotal) VALUES (?, ?, ?, ?)";

        try {
            // Ambil harga 
            int harga = getMenu_Harga(detail.getMenu().getMenu_id());
            // Menghitung subtotal per menu
            int hitungSubtotal = harga * detail.getJumlah();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, detail.getTransaksi().getTransaksi_id());
            ps.setString(2, detail.getMenu().getMenu_id()); 
            ps.setInt(3, detail.getJumlah());
            ps.setInt(4, hitungSubtotal); 

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal Detail: " + e.getMessage());
            return false;
        }
    }
    
    // Method Update baris transaksi
    public void update(List<TransaksiDetail> daftar, int index, String idMenuBaru, int qtyBaru, String metodeBaru) {
        TransaksiDetail detail = daftar.get(index);

        int hargaBaru = getMenu_Harga(idMenuBaru); 

        // Menggunakan Superclass Menu supaya bisa Single/Bundling
        org.itenas.is.oop.starchy.models.MenuSingle m = new org.itenas.is.oop.starchy.models.MenuSingle(0, idMenuBaru, "", hargaBaru); 
        m.setMenu_id(idMenuBaru);
        m.setMenu_harga(hargaBaru);

        // Set menu baru ke dalam detail
        detail.setMenu(m);

        // Mengupdate Jumlah & Hitung Subtotal
        detail.setJumlah(qtyBaru);
        detail.setSubtotal(qtyBaru * hargaBaru);
    }

    public void delete(List<TransaksiDetail> daftar, int index) {
        // Hapus barang dari baris yang dipilih di tabel
        daftar.remove(index);
    }
    
    public int getMenu_Harga(String idMenu) {
        int harga = 0;

        String sql = "SELECT menu_harga FROM menu_single WHERE menu_id = ? " +
                     "UNION " +
                     "SELECT paket_harga FROM menu_bundling WHERE paket_id = ?"; 

        try (Connection con = conMan.connectDb(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idMenu);
            ps.setString(2, idMenu);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Karena UNION menyatukan kolom, kita ambil index 1 saja biar aman
                harga = rs.getInt(1); 
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil harga menu: " + e.getMessage());
        }
        return harga;
    }
    
    public int getMenu_Stok(String idMenu) {
        int stok = 0;
        String sql = "SELECT menu_stok FROM menu_single WHERE menu_id = ?"; 

        try (Connection con = conMan.connectDb(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idMenu);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                stok = rs.getInt("menu_stok"); 
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil stok menu: " + e.getMessage());
        }
        return stok;
    }
    
    public boolean cekStokPaket(String idPaket, String namaPaket, int qtyBeli) {
        String sql = "SELECT menu_nama, menu_stok FROM menu_single WHERE ? LIKE CONCAT('%', menu_nama, '%')";

        try (Connection con = conMan.connectDb();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, namaPaket);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int stokAsli = rs.getInt("menu_stok");
                
                if (stokAsli < qtyBeli) {
                    System.out.println("Menu " + rs.getString("menu_nama") + " habis!");
                    return false; 
                }
            }
        } catch (SQLException e) {
            System.err.println("Gagal cek stok paket: " + e.getMessage());
        }
        return true; // jika stoknya cukup
    }
    
    public String getNamaMenuByID(String id) {
        String nama = "";
        String sql = "SELECT menu_nama FROM menu_single WHERE menu_id = ? " +
                     "UNION SELECT paket_nama FROM menu_bundling WHERE paket_id = ?";
        try (Connection con = conMan.connectDb(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) nama = rs.getString(1);
        } catch (SQLException e) { }
        return nama;
    }
}
