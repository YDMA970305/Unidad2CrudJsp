/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.modelo.servicios;

/**
 *
 * @author yesid
 */
// se importan las librerias necesarias.

import com.proyecto.modelo.entidades.Usuario;
import com.proyecto.modelo.repositorios.UsuarioRepository;
import com.proyecto.modelo.repositorios.UsuarioRepositoryImpl;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt; 

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private static final Logger logger = LogManager.getLogger(UsuarioService.class);

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
    }

    public Usuario login(String identificador, String contrasena) {
        logger.info("Intentando login para el identificador: {}", identificador);
        Usuario usuario = null;

        // Intenta buscar por nombre de usuario (que es el ID primario)
        usuario = usuarioRepository.findById(identificador);

        // Si no se encuentra por nombre de usuario y el identificador contiene '@' (potencialmente un email)
        if (usuario == null && identificador.contains("@")) {
            usuario = usuarioRepository.findByEmail(identificador);
        }

        if (usuario != null) {
            // Verifica la contraseña hasheada usando BCrypt
            if (BCrypt.checkpw(contrasena, usuario.getContrasena())) {
                logger.info("Login exitoso para el usuario: {}", identificador);
                return usuario;
            } else {
                logger.warn("Fallo de login para {}: Contraseña incorrecta.", identificador);
            }
        } else {
            logger.warn("Fallo de login para {}: Usuario no encontrado.", identificador);
        }
        return null; // Retorna null si el login falla
    }

    public Usuario crearUsuario(Usuario usuario) {
        // Validaciones antes de guardar
        if (usuarioRepository.findById(usuario.getNombreUsuario()) != null) {
            throw new IllegalArgumentException("El nombre de usuario '" + usuario.getNombreUsuario() + "' ya está en uso.");
        }
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("El email '" + usuario.getEmail() + "' ya está registrado.");
        }

        // A continuación se hashea la contraseña antes de guardarla en la base de datos
        String hashedPassword = BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt());
        usuario.setContrasena(hashedPassword);
        logger.info("Creando nuevo usuario con nombre de usuario: {}", usuario.getNombreUsuario());
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findById(nombreUsuario);
    }

    public List<Usuario> listarTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public void actualizarUsuario(Usuario usuario) {
        Usuario existingUser = usuarioRepository.findById(usuario.getNombreUsuario());
        if (existingUser == null) {
            throw new RuntimeException("Usuario no encontrado para actualizar.");
        }

        // Actualiza los campos que se pueden modificar desde el formulario
        existingUser.setNombre(usuario.getNombre());
        existingUser.setEmail(usuario.getEmail());
        existingUser.setTipo(usuario.getTipo());

        // Solo actualiza la contraseña si se proporciona una nueva (no está nula o vacía)
        if (usuario.getContrasena() != null && !usuario.getContrasena().trim().isEmpty()) {
            String hashedPassword = BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt());
            existingUser.setContrasena(hashedPassword);
        }
        // Si la contraseña en el objeto 'usuario' es nula o vacía, la contraseña hasheada existente
        // en 'existingUser' (la que se cargó de la DB) se mantendrá.

        logger.info("Actualizando usuario con nombre de usuario: {}", existingUser.getNombreUsuario());
        usuarioRepository.update(existingUser);
    }

    public void eliminarUsuario(String nombreUsuario) {
        logger.info("Eliminando usuario con nombre de usuario: {}", nombreUsuario);
        usuarioRepository.deleteById(nombreUsuario);
    }
}