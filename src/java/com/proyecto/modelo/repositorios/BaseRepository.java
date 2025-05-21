/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yesid
 */
package com.proyecto.modelo.repositorios;

import com.proyecto.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class BaseRepository<T, ID> implements GenericRepository<T, ID> {

    private final Class<T> entityClass; // Almacena la clase de la entidad concreta 
    private static final Logger logger = LogManager.getLogger(BaseRepository.class);

    public BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Inicia una transacción
            session.save(entity); // Persiste la entidad en la base de datos
            transaction.commit(); // Confirma la transacción (guarda los cambios)
            logger.info("Entidad {} guardada exitosamente.", entityClass.getSimpleName());
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Si hay un error, revierte la transacción
            }
            logger.error("Error al guardar la entidad {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al guardar la entidad " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public T findById(ID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            logger.info("Buscando entidad {} por ID: {}", entityClass.getSimpleName(), id);
            return session.get(entityClass, id); // Obtiene una entidad por su ID
        } catch (Exception e) {
            logger.error("Error al buscar entidad {} por ID {}: {}", entityClass.getSimpleName(), id, e.getMessage(), e);
            throw new RuntimeException("Error al buscar entidad " + entityClass.getSimpleName() + " por ID " + id, e);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            logger.info("Listando todas las entidades {}.", entityClass.getSimpleName());
            // Crea una consulta HQL (Hibernate Query Language) para seleccionar todas las entidades
            return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).list();
        } catch (Exception e) {
            logger.error("Error al listar todas las entidades {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al listar todas las entidades " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public T update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity); // Actualiza la entidad en la base de datos
            transaction.commit();
            logger.info("Entidad {} actualizada exitosamente.", entityClass.getSimpleName());
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error al actualizar la entidad {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al actualizar la entidad " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public void deleteById(ID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T entity = session.get(entityClass, id); // Primero, carga la entidad que quieres eliminar
            if (entity != null) {
                session.delete(entity); // Si la encuentra, la elimina
                transaction.commit();
                logger.info("Entidad {} con ID {} eliminada exitosamente.", entityClass.getSimpleName(), id);
            } else {
                logger.warn("No se encontró la entidad {} con ID {} para eliminar. No se realizó ninguna operación.", entityClass.getSimpleName(), id);
                if (transaction != null && transaction.isActive()) { // Solo rollback si la transacción aún está activa
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error al eliminar la entidad {} con ID {}: {}", entityClass.getSimpleName(), id, e.getMessage(), e);
            throw new RuntimeException("Error al eliminar la entidad " + entityClass.getSimpleName() + " con ID " + id, e);
        }
    }
}