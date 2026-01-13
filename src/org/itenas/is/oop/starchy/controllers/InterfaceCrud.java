/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.controllers;

import java.util.List;

/**
 *
 * @author rumah
 */
public interface InterfaceCrud<T> {
    boolean create(T data);
    boolean update(String id,T data);
    List<T> show();
    boolean delete(String id);
}
