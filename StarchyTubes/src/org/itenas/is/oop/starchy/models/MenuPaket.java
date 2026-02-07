/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.models;

/**
 *
 * @author rumah
 */
public class MenuPaket extends Menu{

    public MenuPaket(String menu_id, String menu_nama, int menu_harga) {
        super(menu_id, menu_nama, menu_harga);
    }

    @Override
    public boolean isPaket() {
        return true;
    }
    
}
