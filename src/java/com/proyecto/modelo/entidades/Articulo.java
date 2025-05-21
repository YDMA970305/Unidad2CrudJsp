/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.modelo.entidades;

/**
 *
 * @author yesid
 */
public class Articulo {
 private int idarticulo;
 private String marca;
 private double PrecioVenta;
 private double PrecioCompra;
 private double Iva;
 private String Modelo;
 private String proovedor;
 private String Tienda;
 private int Cantidad;
 private String Categoria;
 private String Descripcion;
 private String nombreUsuario;

    public Articulo(int idarticulo, String marca, double PrecioVenta, double PrecioCompra, double Iva, String Modelo, String proovedor, String Tienda, int Cantidad, String Categoria, String Descripcion, String nombreUsuario) {
        this.idarticulo = idarticulo;
        this.marca = marca;
        this.PrecioVenta = PrecioVenta;
        this.PrecioCompra = PrecioCompra;
        this.Iva = Iva;
        this.Modelo = Modelo;
        this.proovedor = proovedor;
        this.Tienda = Tienda;
        this.Cantidad = Cantidad;
        this.Categoria = Categoria;
        this.Descripcion = Descripcion;
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(double PrecioVenta) {
        this.PrecioVenta = PrecioVenta;
    }

    public double getPrecioCompra() {
        return PrecioCompra;
    }

    public void setPrecioCompra(double PrecioCompra) {
        this.PrecioCompra = PrecioCompra;
    }

    public double getIva() {
        return Iva;
    }

    public void setIva(double Iva) {
        this.Iva = Iva;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getProovedor() {
        return proovedor;
    }

    public void setProovedor(String proovedor) {
        this.proovedor = proovedor;
    }

    public String getTienda() {
        return Tienda;
    }

    public void setTienda(String Tienda) {
        this.Tienda = Tienda;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
 
    
}
