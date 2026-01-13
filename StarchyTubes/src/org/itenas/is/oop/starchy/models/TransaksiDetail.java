/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String idItem = this.menu.getMenu_id(); 
        String namaItem = this.menu.getMenu_nama(); 

        try {
            // P untuk menu paket
            if (idItem.startsWith("P")) {
                // Jika paket, maka akan memotong menu satuan yang ada di paket
                String sqlPaket = "UPDATE menu_single SET menu_stok = menu_stok - ? WHERE ? LIKE CONCAT('%', menu_nama, '%')";
                PreparedStatement ps = con.prepareStatement(sqlPaket);
                ps.setInt(1, qty);
                ps.setString(2, namaItem); 
                ps.executeUpdate();

            } else {
                // bukan paket
                String sqlSingle = "UPDATE menu_single SET menu_stok = menu_stok - ? WHERE menu_id = ?";
                PreparedStatement ps = con.prepareStatement(sqlSingle);
                ps.setInt(1, qty);
                ps.setString(2, idItem);
                ps.executeUpdate();
            }
            System.out.println("Stok Berhasil Terupdate!");
        } catch (SQLException e) {
            System.out.println("Gagal: " + e.getMessage());
        }
    }

    
    
}
