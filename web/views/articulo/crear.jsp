<%-- 
    Document   : crear
    Created on : 20/05/2025, 10:54:26 p. m.
    Author     : yesid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Nuevo Artículo</title>
    </head>
    <body>
        <h1>Crear Nuevo Artículo</h1>
        <form action="../ArticuloServlet?accion=guardar" method="post">
            <p>
                <label for="marca">Marca:</label><br>
                <input type="text" id="marca" name="marca" required>
            </p>
            <p>
                <label for="modelo">Modelo:</label><br>
                <input type="text" id="modelo" name="modelo">
            </p>
            <p>
                <label for="precioVenta">Precio Venta:</label><br>
                <input type="number" step="0.01" id="precioVenta" name="precioVenta" required>
            </p>
            <p>
                <label for="precioCompra">Precio Compra:</label><br>
                <input type="number" step="0.01" id="precioCompra" name="precioCompra" required>
            </p>
            <p>
                <label for="iva">IVA (%):</label><br>
                <input type="number" step="0.01" id="iva" name="iva" required>
            </p>
            <p>
                <label for="cantidad">Cantidad:</label><br>
                <input type="number" id="cantidad" name="cantidad" required>
            </p>
            <p>
                <label for="categoria">Categoría:</label><br>
                <input type="text" id="categoria" name="categoria">
            </p>
            <p>
                <label for="proveedor">Proveedor:</label><br>
                <input type="text" id="proveedor" name="proveedor">
            </p>
            <p>
                <label for="tienda">Tienda:</label><br>
                <input type="text" id="tienda" name="tienda">
            </p>
            <p>
                <label for="descripcion">Descripción:</label><br>
                <textarea id="descripcion" name="descripcion" rows="4" cols="50"></textarea>
            </p>
            <p>
                <button type="submit">Guardar Artículo</button>
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