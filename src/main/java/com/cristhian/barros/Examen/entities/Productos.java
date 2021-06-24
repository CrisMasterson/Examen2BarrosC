package com.cristhian.barros.Examen.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String marca,modelo;
    private double price;

    public Productos(int id, String marca, String modelo, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.price = price;
    }

    public Productos(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productos productos = (Productos) o;
        return id == productos.id && Double.compare(productos.price, price) == 0 && Objects.equals(marca, productos.marca) && Objects.equals(modelo, productos.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, modelo, price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
