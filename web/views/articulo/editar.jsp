<%-- 
    Document   : editar
    Created on : 20/05/2025, 10:54:38 p. m.
    Author     : yesid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.proyecto.modelo.entidades.Articulo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Artículo</title>
    </head>
    <body>
        <h1>Editar Artículo</h1>
        <%
           Articulo articulo = (Articulo) request.getAttribute("articulo");
           if (articulo == null) {
               response.sendRedirect("../ArticuloServlet?accion=listar&error=articuloNotFound");
               return;
           }
        %>
        <form action="../ArticuloServlet?accion=actualizar" method="post">
            <input type="hidden" name="idArticulo" value="<%= articulo.getIdArticulo()%>">
            <p>
                <label for="marca">Marca:</label><br>
                <input type="text" id="marca" name="marca" value="<%= articulo.getMarca()%>" required>
            </p>
            <p>
                <label for="modelo">Modelo:</label><br>
                <input type="text" id="modelo" name="modelo" value="<%= articulo.getModelo()%>">
            </p>
            <p>
                <label for="precioVenta">Precio Venta:</label><br>
                <input type="number" step="0.01" id="precioVenta" name="precioVenta" value="<%= articulo.getPrecioVenta()%>" required>
            </p>
            <p>
                <label for="precioCompra">Precio Compra:</label><br>
                <input type="number" step="0.01" id="precioCompra" name="precioCompra" value="<%= articulo.getPrecioCompra()%>" required>
            </p>
            <p>
                <label for="iva">IVA (%):</label><br>
                <input type="number" step="0.01" id="iva" name="iva" value="<%= articulo.getIva()%>" required>
            </p>
            <p>
                <label for="cantidad">Cantidad:</label><br>
                <input type="number" id="cantidad" name="cantidad" value="<%= articulo.getCantidad()%>" required>
            </p>
            <p>
                <label for="categoria">Categoría:</label><br>
                <input type="text" id="categoria" name="categoria" value="<%= articulo.getCategoria()%>">
            </p>
            <p>
                <label for="proveedor">Proveedor:</label><br>
                <input type="text" id="proveedor" name="proveedor" value="<%= articulo.getProveedor()%>">
            </p>
            <p>
                <label for="tienda">Tienda:</label><br>
                <input type="text" id="tienda" name="tienda" value="<%= articulo.getTienda()%>">
            </p>
            <p>
                <label for="descripcion">Descripción:</label><br>
                <textarea id="descripcion" name="descripcion" rows="4" cols="50"><%= articulo.getDescripcion()%></textarea>
            </p>
            <p>
                <button type="submit">Actualizar Artículo</button>
            </p>
        </form>
        <br>
        <a href="../ArticuloServlet?accion=listar">Volver a la lista de Artículos</a>
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
