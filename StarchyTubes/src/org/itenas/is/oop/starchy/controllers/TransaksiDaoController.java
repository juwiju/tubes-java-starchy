/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.itenas.is.oop.starchy.utils.ConnectorManager;



/**
 *
 * @author user
 */

public class TransaksiDaoController {

    private Connection conn;

    public TransaksiDaoController() {
        ConnectorManager conMan = new ConnectorManager();
        this.conn = conMan.connectDb();
    }

    public List<Map<String, Object>> laporanPerHari() {
        List<Map<String, Object>> list = new ArrayList<>();

        String sql =
            "SELECT transaksi_date AS tanggal, " +
            "SUM(transaksi_total) AS total " +
            "FROM transaksi " +
            "GROUP BY transaksi_date";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("tanggal", rs.getDate("tanggal"));
                data.put("total", rs.getInt("total"));
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Map<String, Object>> laporanPerMinggu() {
        List<Map<String, Object>> list = new ArrayList<>();

        String sql =
            "SELECT YEAR(transaksi_date) AS tahun, " +
            "WEEK(transaksi_date) AS minggu, " +
            "SUM(transaksi_total) AS total " +
            "FROM transaksi " +
            "GROUP BY tahun, minggu";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("tahun", rs.getInt("tahun"));
                data.put("minggu", rs.getInt("minggu"));
                data.put("total", rs.getInt("total"));
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Map<String, Object>> laporanPerBulan() {
        List<Map<String, Object>> list = new ArrayList<>();

        String sql =
            "SELECT YEAR(transaksi_date) AS tahun, " +
            "MONTH(transaksi_date) AS bulan, " +
            "SUM(transaksi_total) AS total " +
            "FROM transaksi " +
            "GROUP BY tahun, bulan";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("tahun", rs.getInt("tahun"));
                data.put("bulan", rs.getInt("bulan"));
                data.put("total", rs.getInt("total"));
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Map<String, Object>> laporanPerTahun() {
        List<Map<String, Object>> list = new ArrayList<>();

        String sql =
            "SELECT YEAR(transaksi_date) AS tahun, " +
            "SUM(transaksi_total) AS total " +
            "FROM transaksi " +
            "GROUP BY tahun";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("tahun", rs.getInt("tahun"));
                data.put("total", rs.getInt("total"));
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
