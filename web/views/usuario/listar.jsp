<%-- 
    Document   : listar
    Created on : 20/05/2025, 10:53:29 p. m.
    Author     : yesid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.proyecto.modelo.entidades.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Usuarios</title>
    </head>
    <body>
        <h1>Lista de Usuarios</h1>
        <a href="../UsuarioServlet?accion=crear">Crear Nuevo Usuario</a>
        <br><br>
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre de Usuario</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Tipo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Recupera la lista de usuarios del ámbito de la solicitud
                    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                    if (usuarios != null && !usuarios.isEmpty()) {
                        for (Usuario usuario : usuarios) {
                %>
                <tr>
                    <td><%= usuario.getNombreUsuario()%></td>
                    <td><%= usuario.getNombre()%></td>
                    <td><%= usuario.getEmail()%></td>
                    <td><%= usuario.getTipo()%></td>
                    <td>
                        <a href="../UsuarioServlet?accion=editar&nombreUsuario=<%= usuario.getNombreUsuario()%>">Editar</a> |
                        <a href="../UsuarioServlet?accion=eliminar&nombreUsuario=<%= usuario.getNombreUsuario()%>" onclick="return confirm('¿Estás seguro de que quieres eliminar este usuario?');">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No hay usuarios registrados.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <br>
        <a href="../menu.jsp">Volver al Menú Principal</a>
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
