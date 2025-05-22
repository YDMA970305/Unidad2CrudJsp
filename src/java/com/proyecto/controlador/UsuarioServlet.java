/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto.controlador;


/**
 *
 * @author yesid
 */


import com.proyecto.modelo.entidades.Usuario;
import com.proyecto.modelo.servicios.UsuarioService;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();
    private static final Logger logger = LogManager.getLogger(UsuarioServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try {
            if (accion == null) {
                accion = "listar"; // Acción por defecto si no se especifica
            }
            switch (accion) {
                case "listar":
                    listarUsuarios(request, response);
                    break;
                case "crear":
                    mostrarFormularioCrear(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarUsuario(request, response);
                    break;
                default:
                    listarUsuarios(request, response);
                    break;
            }
        } catch (Exception e) {
            logger.error("Error inesperado en UsuarioServlet (GET, acción: {}): {}", accion, e.getMessage(), e);
            request.setAttribute("generalError", "Ocurrió un error inesperado al gestionar usuarios. Por favor, inténtelo de nuevo.");
            request.getRequestDispatcher("views/menu.jsp").forward(request, response); // Redirige a una página de error o al menú
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try {
            switch (accion) {
                case "guardar":
                    guardarUsuario(request, response);
                    break;
                case "actualizar":
                    actualizarUsuario(request, response);
                    break;
                default:
                    listarUsuarios(request, response);
                    break;
            }
        } catch (Exception e) {
            logger.error("Error inesperado en UsuarioServlet (POST, acción: {}): {}", accion, e.getMessage(), e);
            request.setAttribute("generalError", e.getMessage()); // Pasa el mensaje de error a la JSP

            // Redirige al formulario correspondiente en caso de error en el POST
            if ("guardar".equals(accion)) {
                request.getRequestDispatcher("views/usuario/crear.jsp").forward(request, response);
            } else if ("actualizar".equals(accion)) {
                request.getRequestDispatcher("views/usuario/editar.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("views/usuario/listar.jsp").forward(request, response);
            }
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> usuarios = usuarioService.listarTodosLosUsuarios();
        request.setAttribute("usuarios", usuarios); // Establece la lista de usuarios para la JSP
        request.getRequestDispatcher("views/usuario/listar.jsp").forward(request, response); // Envía a la JSP de listado
    }

    private void mostrarFormularioCrear(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/usuario/crear.jsp").forward(request, response); // Muestra el formulario de creación
    }

    private void guardarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");
        String tipo = request.getParameter("tipo");

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, email, nombre, tipo);
        usuarioService.crearUsuario(nuevoUsuario); // Llama al servicio para crear el usuario
        response.sendRedirect("UsuarioServlet?accion=listar"); // Redirige a la lista después de guardar
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario"); // Obtiene el ID del usuario a editar
        Usuario usuario = usuarioService.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        request.setAttribute("usuario", usuario); // Pone el usuario en el ámbito de la solicitud
        request.getRequestDispatcher("views/usuario/editar.jsp").forward(request, response); // Muestra el formulario de edición
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 'nombreUsuarioOriginal' se usa para identificar el usuario en la DB
        String nombreUsuarioOriginal = request.getParameter("nombreUsuarioOriginal");
        // Otros parámetros para actualizar
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena"); // Puede estar vacía si no se desea cambiar
        String tipo = request.getParameter("tipo");

        Usuario usuarioExistente = usuarioService.obtenerUsuarioPorNombreUsuario(nombreUsuarioOriginal);
        if (usuarioExistente == null) {
            throw new RuntimeException("Usuario no encontrado para actualizar.");
        }

        // Actualiza las propiedades del usuario existente con los nuevos valores del formulario
        usuarioExistente.setNombre(nombre);
        usuarioExistente.setEmail(email);
        usuarioExistente.setTipo(tipo);
        // Solo actualiza la contraseña si se proporcionó una nueva en el formulario
        if (contrasena != null && !contrasena.trim().isEmpty()) {
            usuarioExistente.setContrasena(contrasena); // El servicio se encargará de hashearla
        }

        usuarioService.actualizarUsuario(usuarioExistente); // Llama al servicio para actualizar
        response.sendRedirect("UsuarioServlet?accion=listar"); // Redirige a la lista
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        usuarioService.eliminarUsuario(nombreUsuario); // Llama al servicio para eliminar
        response.sendRedirect("UsuarioServlet?accion=listar"); // Redirige a la lista
    }

    @Override
    public String getServletInfo() {
        return "Servlet para la gestión de usuarios";
    }
}