package com.app.springmvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


public class AbstractDAO<T, ID extends Serializable> {

    @PersistenceContext
    EntityManager entityManager;

//        protected SimpleJpaRepository<T,ID> proxyRepo;//SimpleJpaRepository will act as default actual repository handler  proxy


    private final Class<T> persistentClass;

////	@SuppressWarnings("unchecked")
//	public AbstractDAO(){
//            super(persistentClass, entityManager);
//            this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//                
//	}
    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> domainClass) {
        super();
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public T findById(ID id) {
        return (T) getOne(id);
    }

    public T getOne(ID id) {
        return null;
    }

    public void save(T obj) {
        entityManager.persist(obj);
    }
    
    public void delete(T obj) {
        entityManager.remove(obj);
    }
    

    public T getByKey(ID key) {
        return (T) entityManager.find(persistentClass, key);

    }

    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {

        entityManager.merge(entity);
    }

//	public void delete(T entity) {
//		entityManager.remove(entity);
//	}
}
