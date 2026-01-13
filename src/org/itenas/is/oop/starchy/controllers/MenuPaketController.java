/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.controllers;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.itenas.is.oop.starchy.utils.ConnectorManager;
import org.itenas.is.oop.starchy.models.MenuPaket;

/**
 *
 * @author rumah
 */
public class MenuPaketController implements InterfaceCrud<MenuPaket> {

    private final Connection con;

    public MenuPaketController() {
        ConnectorManager conMan = new ConnectorManager();
        this.con = conMan.connectDb();
    }

    @Override
    public boolean create(MenuPaket paket) {
        String sql = "INSERT INTO menu_paket (menu_id, menu_nama, menu_harga) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, paket.getMenu_id());
            ps.setString(2, paket.getMenu_nama());
            ps.setInt(3, paket.getMenu_harga());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
    }
    }
    @Override
    public boolean update(String id, MenuPaket paket) {
        String sql = "UPDATE menu_paket SET menu_nama=?, menu_harga=? WHERE menu_id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, paket.getMenu_nama());
            ps.setInt(2, paket.getMenu_harga());
            ps.setString(3, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<MenuPaket> show() {
       List<MenuPaket> list = new ArrayList<>();

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM menu_paket");

            while (rs.next()) {
                list.add(new MenuPaket(
                    rs.getString("menu_id"),
                    rs.getString("menu_nama"),
                    rs.getInt("menu_harga")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public boolean delete(String id) {
      String sql = "DELETE FROM menu_paket WHERE menu_id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
