/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.models;

import java.sql.Date;
/**
 *
 * @author user
 */

public class TransaksiHistori {
    private int transaksiId;
    private int userId;
    private Date transaksiDate;
    private int transaksiTotal;
    private int transaksiMetode;

    // Getter & Setter
    public int getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(int transaksiId) {
        this.transaksiId = transaksiId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTransaksiDate() {
        return transaksiDate;
    }

    public void setTransaksiDate(Date transaksiDate) {
        this.transaksiDate = transaksiDate;
    }

    public int getTransaksiTotal() {
        return transaksiTotal;
    }

    public void setTransaksiTotal(int transaksiTotal) {
        this.transaksiTotal = transaksiTotal;
    }

    public int getTransaksiMetode() {
        return transaksiMetode;
    }

    public void setTransaksiMetode(int transaksiMetode) {
        this.transaksiMetode = transaksiMetode;
    }
}

