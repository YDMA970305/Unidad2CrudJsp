<%-- 
    Document   : crear
    Created on : 20/05/2025, 10:53:37 p. m.
    Author     : yesid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Nuevo Usuario</title>
    </head>
    <body>
        <h1>Crear Nuevo Usuario</h1>
        <form action="../UsuarioServlet?accion=guardar" method="post">
            <p>
                <label for="nombreUsuario">Nombre de Usuario:</label><br>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required>
            </p>
            <p>
                <label for="nombre">Nombre:</label><br>
                <input type="text" id="nombre" name="nombre" required>
            </p>
            <p>
                <label for="email">Email:</label><br>
                <input type="email" id="email" name="email" required>
            </p>
            <p>
                <label for="contrasena">Contraseña:</label><br>
                <input type="password" id="contrasena" name="contrasena" required>
            </p>
            <p>
                <label for="tipo">Tipo de Usuario:</label><br>
                <select id="tipo" name="tipo" required>
                    <option value="admin">Administrador</option>
                    <option value="cliente">Cliente</option>
                </select>
            </p>
            <p>
                <button type="submit">Guardar Usuario</button>
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