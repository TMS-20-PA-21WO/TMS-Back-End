package com.duasatuwo.api.dto;

public class Validation {
    
    private int id_user;
    private int id_package;
    private String date;

    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public int getId_package() {
        return id_package;
    }
    public void setId_package(int id_package) {
        this.id_package = id_package;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
