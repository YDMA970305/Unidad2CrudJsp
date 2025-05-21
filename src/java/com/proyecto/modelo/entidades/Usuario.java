

package com.proyecto.modelo.entidades;

/**
 *
 * @author yesid
 */


import javax.persistence.*; // Importar las anotaciones de JPA
import java.io.Serializable; // Importar Serializable

@Entity // Declara esta clase como una entidad JPA
@Table(name = "usuario") // Mapea esta entidad a la tabla 'usuarios'
public class Usuario implements Serializable { // Implementa Serializable

    @Id // Marca 'nombreUsuario' como la clave primaria
    @Column(name = "nombre_usuario") // Mapea el campo 'nombreUsuario' de Java a la columna 'nombre_usuario' en la DB
    private String nombreUsuario; // Este será tu ID

    @Column(name = "contrasena") // Mapea contrasena a la columna 'contrasena'
    private String contrasena; // Esto es password en tu descripción original, mantengo 'contrasena'

    private String email; // Asumiendo que el nombre de columna en DB es 'email'
    private String nombre; // Asumiendo que el nombre de columna en DB es 'nombre'
    private String tipo;   // Asumiendo que el nombre de columna en DB es 'tipo'

    public Usuario() {
        // Constructor vacío es necesario para Hibernate
    }

    // Constructor para crear nuevos usuarios (sin necesidad de pasar el ID ya que es nombreUsuario)
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