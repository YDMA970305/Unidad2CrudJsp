/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto.controlador;


import com.proyecto.modelo.entidades.Usuario;
import com.proyecto.modelo.servicios.UsuarioService;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; // Para la anotación @WebServlet
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author yesid
 */



@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"}) // Define la URL de acceso para este Servlet
public class LoginServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService(); // Instancia del servicio de usuarios
//    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    // Método principal para procesar solicitudes GET y POST (se llama desde doGet/doPost)
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // Establece el tipo de contenido de la respuesta
        String identificador = request.getParameter("identificador"); // Obtiene el nombre de usuario o email del formulario
        String contrasena = request.getParameter("contrasena");     // Obtiene la contraseña del formulario

  //      logger.info("Intento de login para identificador: {}", identificador);

        // Llama al servicio para intentar el login
        Usuario usuario = usuarioService.login(identificador, contrasena);

        if (usuario != null) {
            HttpSession session = request.getSession(); // Obtiene la sesión HTTP actual
            session.setAttribute("usuarioLogueado", usuario); // Guarda el objeto Usuario en la sesión
    //        logger.info("Usuario {} logueado exitosamente.", usuario.getNombreUsuario());
            // Redirige al menú principal después del login exitoso
            response.sendRedirect("views/menu.jsp");
        } else {
      //      logger.warn("Fallo de login para identificador: {}", identificador);
            request.setAttribute("loginError", "Usuario o contraseña incorrectos."); // Guarda un mensaje de error
            // Vuelve a la página de login, mostrando el mensaje de error
            request.getRequestDispatcher("views/usuario/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion"); // Obtiene el parámetro 'accion' de la URL
        if ("logout".equals(accion)) {
            HttpSession session = request.getSession(false); // Obtiene la sesión existente, si la hay
            if (session != null) {
      //      logger.info("Usuario {} cerrando sesión.", (session.getAttribute("usuarioLogueado") != null ? ((Usuario) session.getAttribute("usuarioLogueado")).getNombreUsuario() : "Desconocido"));
                session.invalidate(); // Invalida (cierra) la sesión actual
            }
            response.sendRedirect("views/usuario/login.jsp"); // Redirige al formulario de login
        } else {
            processRequest(request, response); // Para otras acciones GET, procesa como un intento de login
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Procesa las solicitudes POST (envío del formulario de login)
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejo de Login y Logout de Usuarios";
    }
}