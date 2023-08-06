package com.bmo.processbmo.view.Model;

import com.bmo.processbmo.model.Address;
import com.bmo.processbmo.model.Supplier;

public class ProductRequest {
    private String name;
    private Integer quantity;
    private Double value;
    private String observation;
    private Supplier supplier;
    //#endregion

    //#region Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    //#endregion
}
