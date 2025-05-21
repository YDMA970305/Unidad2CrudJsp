<%-- 
    Document   : listar
    Created on : 20/05/2025, 10:54:18 p. m.
    Author     : yesid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.proyecto.modelo.entidades.Articulo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Artículos</title>
    </head>
    <body>
        <h1>Lista de Artículos</h1>
        <a href="../ArticuloServlet?accion=crear">Crear Nuevo Artículo</a>
        <br><br>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Precio Venta</th>
                    <th>Cantidad</th>
                    <th>Categoría</th>
                    <th>Usuario Creador</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Articulo> articulos = (List<Articulo>) request.getAttribute("articulos");
                    if (articulos != null && !articulos.isEmpty()) {
                        for (Articulo articulo : articulos) {
                %>
                <tr>
                    <td><%= articulo.getIdArticulo()%></td>
                    <td><%= articulo.getMarca()%></td>
                    <td><%= articulo.getModelo()%></td>
                    <td><%= articulo.getPrecioVenta()%></td>
                    <td><%= articulo.getCantidad()%></td>
                    <td><%= articulo.getCategoria()%></td>
                    <td><%= articulo.getNombreUsuario()%></td>
                    <td>
                        <a href="../ArticuloServlet?accion=editar&idArticulo=<%= articulo.getIdArticulo()%>">Editar</a> |
                        <a href="../ArticuloServlet?accion=eliminar&idArticulo=<%= articulo.getIdArticulo()%>" onclick="return confirm('¿Estás seguro de que quieres eliminar este artículo?');">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="8">No hay artículos registrados.</td>
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
