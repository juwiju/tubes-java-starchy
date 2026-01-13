/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
<<<<<<< HEAD
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
=======
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
>>>>>>> 8c03bb72143c29b6cf3632c7f4f7c3791da026b5
 */
package org.itenas.is.oop.starchy.models;

/**
 *
<<<<<<< HEAD
 * @author faradilla maudy
 */

=======
 * @author Anis Sarah
 */
>>>>>>> 8c03bb72143c29b6cf3632c7f4f7c3791da026b5
public class User {
    private int user_id;
    private String username;
    private String password;

    public User() {
    }

    public User(int user_id) {
        this.user_id = user_id;
    }
    
    public static User userLogin = new User(14111, "Lala", "123");
    
    public User(int user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
