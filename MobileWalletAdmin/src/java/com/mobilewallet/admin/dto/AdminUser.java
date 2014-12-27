/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.admin.dto;

/**
 *
 * @author Gopi
 */
public class AdminUser {
    private Integer adminId;
    private String email;
    private String role;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
