/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @Juwita
 */
public class TransaksiDetail implements IPembelian{
    private int detail_id;
    private Transaksi transaksi; 
    private Menu menu;
    private int jumlah;
    private int subtotal;

    public TransaksiDetail() {
    }

    public TransaksiDetail(int detail_id, Transaksi transaksi, Menu menu, int jumlah, int subtotal) {
        this.detail_id = detail_id;
        this.transaksi = transaksi;
        this.menu = menu;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public Menu getMenu() { 
        return menu; 
    }
    
    public void setMenu(Menu menu) { 
        this.menu = menu; 
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public void kurangistok(Connection con, int qty) {
        String idItem = this.menu.getMenu_id(); // Ini isinya ID Paket (misal: P001)

        try {
            if (idItem.startsWith("P")) { //bila menu paket
                String sqlAmbilDetail = "SELECT menu_id, quantity FROM detail_paket WHERE paket_id = ?";
                PreparedStatement psDetail = con.prepareStatement(sqlAmbilDetail);
                psDetail.setString(1, idItem); 
                ResultSet rs = psDetail.executeQuery();

                while (rs.next()) {
                    String idSingle = rs.getString("menu_id"); // Ini ID menu satuannya
                    int qtyDalamPaket = rs.getInt("quantity");
                    int totalPotong = qtyDalamPaket * qty;

                    // Baru deh kita potong stoknya di menu_single
                    String sqlUpdate = "UPDATE menu_single SET menu_stok = menu_stok - ? WHERE menu_id = ?";
                    PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
                    psUpdate.setInt(1, totalPotong);
                    psUpdate.setString(2, idSingle);
                    psUpdate.executeUpdate();
                }
            } else {
                // Logika buat menu single biasa
                String sqlSingle = "UPDATE menu_single SET menu_stok = menu_stok - ? WHERE menu_id = ?";
                PreparedStatement ps = con.prepareStatement(sqlSingle);
                ps.setInt(1, qty);
                ps.setString(2, idItem);
                ps.executeUpdate();
            }
            System.out.println("Stok Berhasil Terupdate!");
        } catch (SQLException e) {
            System.out.println("Gagal Update Stok: " + e.getMessage());
        }
    }
    
    
}
