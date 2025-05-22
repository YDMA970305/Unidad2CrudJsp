

package com.proyecto.modelo.entidades;

/**
 *
 * @author yesid
 */


import jakarta.persistence.*; 
import java.io.Serializable; 

@Entity // Declara esta clase como una entidad JPA
@Table(name = "usuario") // Mapea esta entidad a la tabla 'usuario'
public class Usuario implements Serializable { // Implementa Serializable

    @Id // Marca 'nombreUsuario' como la clave primaria
    @Column(name = "nombre_usuario") // Mapea el campo 'nombreUsuario' de Java a la columna 'nombre_usuario' en la DB
    private String nombreUsuario; // Este será el ID

    @Column(name = "contrasena") 
    private String contrasena; 

    private String email; 
    private String nombre;
    private String tipo;   

    public Usuario() {
        // Constructor vacío es necesario para Hibernate
    }

    // Constructor para crear nuevos usuarios ()
    public Usuario(String nombreUsuario, String contrasena, String email, String nombre, String tipo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.email = email;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "nombreUsuario='" + nombreUsuario + '\'' +
               ", email='" + email + '\'' +
               ", nombre='" + nombre + '\'' +
               ", tipo='" + tipo + '\'' +
               '}';
    }
}