/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.modelo.repositorios;

/**
 *
 * @author yesid
 */


import com.proyecto.modelo.entidades.Articulo;

public class ArticuloRepositoryImpl extends BaseRepository<Articulo, Integer> implements ArticuloRepository {

    public ArticuloRepositoryImpl() {
        super(Articulo.class); // Pasa la clase de la entidad al constructor del BaseRepository
    }
    // Los métodos CRUD básicos de Articulo ya están implementados en BaseRepository
    }