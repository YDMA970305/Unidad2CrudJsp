/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.modelo.repositorios;

/**
 *
 * @author yesid
 */


import com.proyecto.modelo.entidades.Usuario;

public interface UsuarioRepository extends GenericRepository<Usuario, String> {
    Usuario findByEmail(String email); // Método específico para buscar un usuario por su email
}