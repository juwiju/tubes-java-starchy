/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.models;

/**
 *
 * @author rumah
 */
public abstract class Menu {
    private String menu_id;
    private String menu_nama;
    private int menu_harga;

    public Menu() {
    }

    
    public Menu(String menu_id, String menu_nama, int menu_harga) {
        this.menu_id = menu_id;
        this.menu_nama = menu_nama;
        this.menu_harga = menu_harga;
    }


    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public void setMenu_nama(String menu_nama) {
        this.menu_nama = menu_nama;
    }

    public void setMenu_harga(int menu_harga) {
        this.menu_harga = menu_harga;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public String getMenu_nama() {
        return menu_nama;
    }

    public int getMenu_harga() {
        return menu_harga;
    }
    public abstract boolean isPaket();
}
