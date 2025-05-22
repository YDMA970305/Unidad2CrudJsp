/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto.controlador;



/**
 *
 * @author yesid
 */


import com.proyecto.modelo.entidades.Articulo;
import com.proyecto.modelo.servicios.ArticuloService;
import com.proyecto.modelo.entidades.Usuario; // Necesario para obtener el usuario logueado
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "ArticuloServlet", urlPatterns = {"/ArticuloServlet"})
public class ArticuloServlet extends HttpServlet {

    private final ArticuloService articuloService = new ArticuloService();
    private static final Logger logger = LogManager.getLogger(ArticuloServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try {
            if (accion == null) {
                accion = "listar";
            }

            switch (accion) {
                case "listar":
                    listarArticulos(request, response);
                    break;
                case "crear":
                    mostrarFormularioCrear(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarArticulo(request, response);
                    break;
                default:
                    listarArticulos(request, response);
                    break;
            }
        } catch (Exception e) {
            logger.error("Error inesperado en ArticuloServlet (GET, acción: {}): {}", accion, e.getMessage(), e);
            request.setAttribute("generalError", "Ocurrió un error inesperado con los artículos. Por favor, inténtelo de nuevo.");
            request.getRequestDispatcher("views/menu.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try {
            switch (accion) {
                case "guardar":
                    guardarArticulo(request, response);
                    break;
                case "actualizar":
                    actualizarArticulo(request, response);
                    break;
                default:
                    listarArticulos(request, response);
                    break;
            }
        } catch (Exception e) {
            logger.error("Error inesperado en ArticuloServlet (POST, acción: {}): {}", accion, e.getMessage(), e);
            request.setAttribute("generalError", e.getMessage());
            if ("guardar".equals(accion)) {
                request.getRequestDispatcher("views/articulo/crear.jsp").forward(request, response);
            } else if ("actualizar".equals(accion)) {
                request.getRequestDispatcher("views/articulo/editar.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("views/articulo/listar.jsp").forward(request, response);
            }
        }
    }

    private void listarArticulos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Articulo> articulos = articuloService.listarTodosLosArticulos();
        request.setAttribute("articulos", articulos);
        request.getRequestDispatcher("views/articulo/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioCrear(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/articulo/crear.jsp").forward(request, response);
    }

    private void guardarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Validar que el usuario esté logueado para crear artículos
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            throw new RuntimeException("Debe iniciar sesión para crear artículos.");
        }

        // Obtener los parámetros del formulario de creación de artículo
        String marca = request.getParameter("marca");
        // Asegúrate de que los tipos de datos en la JSP y aquí sean compatibles (ej. double para precio)
        double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
        double precioCompra = Double.parseDouble(request.getParameter("precioCompra"));
        double iva = Double.parseDouble(request.getParameter("iva"));
        String modelo = request.getParameter("modelo");
        String proveedor = request.getParameter("proveedor");
        String tienda = request.getParameter("tienda");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String categoria = request.getParameter("categoria");
        String descripcion = request.getParameter("descripcion");

        // Crear una nueva instancia de Articulo. El idArticulo se pasa como 0 porque es auto-incremental.
        // Se asigna el nombre de usuario del usuario logueado como creador del artículo.
        Articulo nuevoArticulo = new Articulo(0, marca, precioVenta, precioCompra, iva, modelo, proveedor, tienda, cantidad, categoria, descripcion, usuarioLogueado.getNombreUsuario());
        articuloService.crearArticulo(nuevoArticulo);
        response.sendRedirect("ArticuloServlet?accion=listar"); // Redirige a la lista de artículos
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idArticulo = Integer.parseInt(request.getParameter("idArticulo")); // Obtiene el ID del artículo a editar
        Articulo articulo = articuloService.obtenerArticuloPorId(idArticulo);
        request.setAttribute("articulo", articulo); // Pone el artículo en el ámbito de la solicitud
        request.getRequestDispatcher("views/articulo/editar.jsp").forward(request, response); // Muestra el formulario de edición
    }

    private void actualizarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idArticulo = Integer.parseInt(request.getParameter("idArticulo")); // Obtiene el ID del artículo que se está actualizando
        String marca = request.getParameter("marca");
        double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
        double precioCompra = Double.parseDouble(request.getParameter("precioCompra"));
        double iva = Double.parseDouble(request.getParameter("iva"));
        String modelo = request.getParameter("modelo");
        String proveedor = request.getParameter("proveedor");
        String tienda = request.getParameter("tienda");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String categoria = request.getParameter("categoria");
        String descripcion = request.getParameter("descripcion");

        // Validar que el usuario esté logueado
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            throw new RuntimeException("Debe iniciar sesión para actualizar artículos.");
        }

        // Cargar el artículo existente de la base de datos para no perder su 'nombreUsuario'
        Articulo articuloExistente = articuloService.obtenerArticuloPorId(idArticulo);
        if (articuloExistente == null) {
            throw new RuntimeException("Artículo no encontrado para actualizar.");
        }

        // Actualiza solo los campos que vienen del formulario.
        // El campo 'nombreUsuario' (el creador) se mantiene del objeto 'articuloExistente'.
        articuloExistente.setMarca(marca);
        articuloExistente.setPrecioVenta(precioVenta);
        articuloExistente.setPrecioCompra(precioCompra);
        articuloExistente.setIva(iva);
        articuloExistente.setModelo(modelo);
        articuloExistente.setProveedor(proveedor);
        articuloExistente.setTienda(tienda);
        articuloExistente.setCantidad(cantidad);
        articuloExistente.setCategoria(categoria);
        articuloExistente.setDescripcion(descripcion);

        articuloService.actualizarArticulo(articuloExistente);
        response.sendRedirect("ArticuloServlet?accion=listar");
    }

    private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
        articuloService.eliminarArticulo(idArticulo);
        response.sendRedirect("ArticuloServlet?accion=listar");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para la gestión de artículos";
    }
}