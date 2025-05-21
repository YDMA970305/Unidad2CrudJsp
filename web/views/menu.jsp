<%-- 
    Document   : menu
    Created on : 20/05/2025, 10:55:03 p. m.
    Author     : yesid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.proyecto.modelo.entidades.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú Principal</title>
    </head>
    <body>
        <%
            // Recupera el objeto de usuario de la sesión. Si no existe, redirige a la página de login.
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
            if (usuarioLogueado == null) {
                response.sendRedirect("usuario/login.jsp"); // Asegúrate de que esta ruta sea correcta
                return; // Detiene la ejecución del resto de la JSP
            }
        %>
        <h1>Bienvenido, <%= usuarioLogueado.getNombre()%>!</h1>
        <p>Tu tipo de usuario es: <%= usuarioLogueado.getTipo()%></p>

        <h2>Gestión de Usuarios</h2>
        <ul>
            <li><a href="../UsuarioServlet?accion=listar">Listar Usuarios</a></li>
            <li><a href="../UsuarioServlet?accion=crear">Crear Nuevo Usuario</a></li>
        </ul>

        <h2>Gestión de Artículos</h2>
        <ul>
            <li><a href="../ArticuloServlet?accion=listar">Listar Artículos</a></li>
            <li><a href="../ArticuloServlet?accion=crear">Crear Nuevo Artículo</a></li>
        </ul>

        <br>
        <a href="../LoginServlet?accion=logout">Cerrar Sesión</a>
    </body>
</html>