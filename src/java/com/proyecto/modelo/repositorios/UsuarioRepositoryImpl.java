/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.modelo.repositorios;

/**
 *
 * @author yesid
 */


import com.proyecto.modelo.entidades.Usuario;
import com.proyecto.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query; // Importa org.hibernate.query.Query
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsuarioRepositoryImpl extends BaseRepository<Usuario, String> implements UsuarioRepository {

    private static final Logger logger = LogManager.getLogger(UsuarioRepositoryImpl.class);

    public UsuarioRepositoryImpl() {
        super(Usuario.class); // Pasa la clase de la entidad al constructor del BaseRepository
    }

    @Override
    public Usuario findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Crea una consulta HQL para buscar un usuario por su email
            Query<Usuario> query = session.createQuery("FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email); // Establece el parámetro 'email' en la consulta
            logger.info("Buscando usuario por email: {}", email);
            return query.uniqueResult(); // Retorna un único resultado o null si no se encuentra
        } catch (Exception e) {
            logger.error("Error al buscar usuario por email {}: {}", email, e.getMessage(), e);
            throw new RuntimeException("Error al buscar usuario por email: " + email, e);
        }
    }
}
