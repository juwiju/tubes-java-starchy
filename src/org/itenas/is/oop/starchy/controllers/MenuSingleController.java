/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.itenas.is.oop.starchy.utils.ConnectorManager;
import org.itenas.is.oop.starchy.models.DetailPaket;
import org.itenas.is.oop.starchy.models.Menu;
import org.itenas.is.oop.starchy.models.MenuPaket;
import org.itenas.is.oop.starchy.models.MenuSingle;
/**
 *
 * @author rumah
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MenuSingleController implements InterfaceCrud<MenuSingle> {

    private final Connection con;

    public MenuSingleController() {
        ConnectorManager conMan = new ConnectorManager();
        this.con = conMan.connectDb();
    }

    @Override
    public boolean create(MenuSingle menu) {
        String sql = "INSERT INTO menu_single (menu_id, menu_nama, menu_harga, menu_stok) VALUES (?, ?, ?, ?)";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, menu.getMenu_id());
        ps.setString(2, menu.getMenu_nama());
        ps.setInt(3, menu.getMenu_harga());
        ps.setInt(4, menu.getStok());
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
    }

    @Override
    public boolean update(String id, MenuSingle menu) {
           String sql = "UPDATE menu_single SET menu_harga=?, menu_stok=? WHERE menu_id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, menu.getMenu_harga());
            ps.setInt(2, menu.getStok());
            ps.setString(3, id); 
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
       public List<MenuSingle> show() {
        List<MenuSingle> listMenu = new ArrayList<>();

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM menu_single");

            while (rs.next()) {
                MenuSingle menu = new MenuSingle(
                    rs.getInt("menu_stok"),
                    rs.getString("menu_id"),
                    rs.getString("menu_nama"),
                    rs.getInt("menu_harga")
                );
                listMenu.add(menu);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return listMenu;
    }

    @Override
    public boolean delete(String id) {
         String sql = "DELETE FROM menu_single WHERE menu_id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);  // 🔥 ini juga
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
   
    }
  }