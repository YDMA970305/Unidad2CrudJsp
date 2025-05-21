package com.proyecto.modelo.repositorios;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author yesid
 */


import java.util.List;

// T: Tipo de la entidad ( Usuario, Articulo)
// ID: Tipo de la clave primaria de la entidad 
public interface GenericRepository<T, ID> {
    T save(T entity);         
    T findById(ID id);        
    List<T> findAll();        
    T update(T entity);       
    void deleteById(ID id);   
}
