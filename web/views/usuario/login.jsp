<%-- 
    Document   : login
    Created on : 20/05/2025, 10:53:11 p. m.
    Author     : yesid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesión</title>
    </head>
    <body>
        <h1>Iniciar Sesión</h1>
        <form action="../../LoginServlet" method="post">
            <p>
                <label for="identificador">Nombre de Usuario o Email:</label><br>
                <input type="text" id="identificador" name="identificador" required>
            </p>
            <p>
                <label for="contrasena">Contraseña:</label><br>
                <input type="password" id="contrasena" name="contrasena" required>
            </p>
            <p>
                <button type="submit">Entrar</button>
            </p>
        </form>
        <%
            String loginError = (String) request.getAttribute("loginError");
            if (loginError != null) {
        %>
        <p style="color: red; font-weight: bold;"><%= loginError%></p>
        <%
            }
        %>
        <br>
        <a href="crear.jsp">¿No tienes cuenta? Regístrate aquí</a>
    </body>
</html>
