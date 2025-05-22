<%-- 
    Document   : editar
    Created on : 20/05/2025, 10:53:46 p. m.
    Author     : yesid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.proyecto.modelo.entidades.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Usuario</title>
    </head>
    <body>
        <h1>Editar Usuario</h1>
        <% // Recupera el objeto Usuario del ámbito de la solicitud
           Usuario usuario = (Usuario) request.getAttribute("usuario");
          
           if (usuario == null) {
               response.sendRedirect("../UsuarioServlet?accion=listar&error=usuarioNotFound");
               return;
           }
        %>
        <form action="../UsuarioServlet?accion=actualizar" method="post">
            <%-- Campo oculto para enviar el ID original del usuario --%>
            <input type="hidden" name="nombreUsuarioOriginal" value="<%= usuario.getNombreUsuario()%>">
            <p>
                <label for="nombreUsuario">Nombre de Usuario:</label><br>
                <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%= usuario.getNombreUsuario()%>" readonly> <%-- El nombre de usuario (ID) no debe ser editable --%>
            </p>
            <p>
                <label for="nombre">Nombre:</label><br>
                <input type="text" id="nombre" name="nombre" value="<%= usuario.getNombre()%>" required>
            </p>
            <p>
                <label for="email">Email:</label><br>
                <input type="email" id="email" name="email" value="<%= usuario.getEmail()%>" required>
            </p>
            <p>
                <label for="contrasena">Nueva Contraseña (dejar en blanco para no cambiar):</label><br>
                <input type="password" id="contrasena" name="contrasena"> 
            </p>
            <p>
                <label for="tipo">Tipo de Usuario:</label><br>
                <select id="tipo" name="tipo" required>
                    <option value="admin" <%= "admin".equals(usuario.getTipo()) ? "selected" : ""%>>Administrador</option>
                    <option value="cliente" <%= "cliente".equals(usuario.getTipo()) ? "selected" : ""%>>Cliente</option>
                </select>
            </p>
            <p>
                <button type="submit">Actualizar Usuario</button>
            </p>
        </form>
        <br>
        <a href="../UsuarioServlet?accion=listar">Volver a la lista de Usuarios</a>
        <%
            String generalError = (String) request.getAttribute("generalError");
            if (generalError != null) {
        %>
        <p style="color: red; font-weight: bold;"><%= generalError%></p>
        <%
            }
        %>
    </body>
</html>
