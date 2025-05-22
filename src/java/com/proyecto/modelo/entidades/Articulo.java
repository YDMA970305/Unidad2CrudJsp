package com.proyecto.modelo.entidades;

import jakarta.persistence.*; // ¡Importante para las anotaciones JPA!
import java.io.Serializable; // Necesario para la serialización de entidades

/**
 *
 * @author yesid
 */
@Entity // Declara esta clase como una entidad JPA
@Table(name = "articulo") // Mapea esta entidad a la tabla 'articulos' en tu base de datos
public class Articulo implements Serializable { // Implementa Serializable

    @Id // Marca 'idArticulo' como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el ID es auto-incremental en la DB
    @Column(name = "id_articulo") // Mapea el campo 'idArticulo' de Java a la columna 'id_articulo' en la DB
    private int idArticulo;

    @Column(name = "marca") 
    private String marca;

    @Column(name = "precio_venta") 
    private double precioVenta;

    @Column(name = "precio_compra") 
    private double precioCompra;

    @Column(name = "iva") 
    private double iva;

    @Column(name = "modelo") 
    private String modelo;

    @Column(name = "proveedor") 
    private String proveedor;

    @Column(name = "tienda") 
    private String tienda;

    @Column(name = "cantidad") 
    private int cantidad;

    @Column(name = "categoria") 
    private String categoria;

    @Column(name = "descripcion") 
    private String descripcion;

    @Column(name = "nombre_usuario") 
    private String nombreUsuario; 

    public Articulo() {
        // El constructor vacío es necesario para que Hibernate pueda instanciar la entidad
    }

    public Articulo(int idArticulo, String marca, double precioVenta, double precioCompra, double iva, String modelo, String proveedor, String tienda, int cantidad, String categoria, String descripcion, String nombreUsuario) {
        this.idArticulo = idArticulo;
        this.marca = marca;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.iva = iva;
        this.modelo = modelo;
        this.proveedor = proveedor;
        this.tienda = tienda;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.nombreUsuario = nombreUsuario;
    }

    // Getters y Setters
    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return "Articulo{" +
               "idArticulo=" + idArticulo +
               ", marca='" + marca + '\'' +
               ", precioVenta=" + precioVenta +
               ", precioCompra=" + precioCompra +
               ", iva=" + iva +
               ", modelo='" + modelo + '\'' +
               ", proveedor='" + proveedor + '\'' +
               ", tienda='" + tienda + '\'' +
               ", cantidad=" + cantidad +
               ", categoria='" + categoria + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", nombreUsuario='" + nombreUsuario + '\'' +
               '}';
    }
}
