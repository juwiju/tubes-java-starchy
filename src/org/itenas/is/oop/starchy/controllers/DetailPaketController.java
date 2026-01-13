/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.itenas.is.oop.starchy.models.DetailPaket;
import org.itenas.is.oop.starchy.utils.ConnectorManager;
/**
 *
 * @author rumah
 */
public class DetailPaketController {

    private final Connection con;

    public DetailPaketController() {
        ConnectorManager conMan = new ConnectorManager();
        this.con = conMan.connectDb();
    }

    public boolean create(DetailPaket detail) {
        String sql = "INSERT INTO detail_paket (paket_id, menu_id, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, detail.getPaket_id());
            ps.setString(2, detail.getMenu_id());
            ps.setInt(3, detail.getQty());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteByPaket(String paketId) {
        String sql = "DELETE FROM detail_paket WHERE paket_id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, paketId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<DetailPaket> showByPaket(String paketId) {
        List<DetailPaket> list = new ArrayList<>();

        String sql = "SELECT * FROM detail_paket WHERE paket_id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, paketId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new DetailPaket(
                    rs.getString("paket_id"),
                    rs.getString("menu_id"),
                    rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
