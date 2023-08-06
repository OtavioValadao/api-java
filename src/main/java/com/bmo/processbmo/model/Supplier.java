package com.bmo.processbmo.model;

import jakarta.persistence.Entity;

@Entity
public class Supplier {
    //#region Atributs
    private String name;
    private String telephone;
    private Address address;
    //#endregion

    //#region Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    //#endregion

}
