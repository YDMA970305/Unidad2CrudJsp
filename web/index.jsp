<%-- 
    Document   : index
    Created on : 21/05/2025, 11:53:53 a. m.
    Author     : yesid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redirigiendo...</title>
        <%
            // Redirigir directamente a la página de login
            response.sendRedirect("views/usuario/login.jsp");
        %>
    </head>
    <body>
        <p>Redirigiendo a la página de inicio de sesión...</p>
    </body>
</html>