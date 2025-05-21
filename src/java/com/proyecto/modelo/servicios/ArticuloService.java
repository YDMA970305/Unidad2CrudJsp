/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.modelo.servicios;

/**
 *
 * @author yesid
 */


import com.proyecto.modelo.entidades.Articulo;
import com.proyecto.modelo.repositorios.ArticuloRepository;
import com.proyecto.modelo.repositorios.ArticuloRepositoryImpl;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArticuloService {

    private final ArticuloRepository articuloRepository;
    private static final Logger logger = LogManager.getLogger(ArticuloService.class);

    public ArticuloService() {
        this.articuloRepository = new ArticuloRepositoryImpl();
    }

    public Articulo crearArticulo(Articulo articulo) {
        logger.info("Creando nuevo artículo: {}", articulo.getMarca());
        return articuloRepository.save(articulo);
    }

    public Articulo obtenerArticuloPorId(int idArticulo) {
        return articuloRepository.findById(idArticulo);
    }

    public List<Articulo> listarTodosLosArticulos() {
        return articuloRepository.findAll();
    }

    public void actualizarArticulo(Articulo articulo) {
        logger.info("Actualizando artículo con ID: {}", articulo.getIdArticulo());
        // El método 'update' del repositorio base se encarga de la lógica de persistencia
        articuloRepository.update(articulo);
    }

    public void eliminarArticulo(int idArticulo) {
        logger.info("Eliminando artículo con ID: {}", idArticulo);
        articuloRepository.deleteById(idArticulo);
    }
}
