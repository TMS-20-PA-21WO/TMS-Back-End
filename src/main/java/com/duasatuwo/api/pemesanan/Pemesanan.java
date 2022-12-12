package com.duasatuwo.api.pemesanan;

import java.io.Serializable;

import com.duasatuwo.api.paket.Paket;
import com.duasatuwo.api.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "pemesanan")
public class Pemesanan implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @Column(length = 10)
    // @NotEmpty(message = "User id is required")
    // private int id_user;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    // @Column(length = 10)
    // @NotEmpty(message = "Package id is required")
    // private int id_package;

    @OneToOne
    @JoinColumn(name = "id_package")
    private Paket paket;

    @Column(columnDefinition = "DATE")
    @NotEmpty(message = "Date is required")
    private String date;

    @Column(length = 20)
    @NotEmpty(message = "Phone number is required")
    private String phone_number;

    public Pemesanan() {
    }

    public Pemesanan(int id, User user, Paket paket, @NotEmpty(message = "Date is required") String date,
            @NotEmpty(message = "Phone number is required") String phone_number) {
        this.id = id;
        this.user = user;
        this.paket = paket;
        this.date = date;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Paket getPaket() {
        return paket;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


}
