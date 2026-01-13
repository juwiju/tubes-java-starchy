/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.models;

/**
 *
 * @author rumah
 */
public class DetailPaket {
    private String paket_id;
    private String menu_id;
    private int qty;

    public DetailPaket(String paket_id, String menu_id, int qty) {
        this.paket_id = paket_id;
        this.menu_id = menu_id;
        this.qty = qty;
    }

    public void setPaket_id(String paket_id) {
        this.paket_id = paket_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPaket_id() {
        return paket_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public int getQty() {
        return qty;
    }
    
}
