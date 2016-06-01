package it.polimi.awt.waterconsumer.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import it.polimi.awt.waterconsumer.repository.GenericRepository;

import javax.persistence.*;

public abstract class JpaGenericRepository<E, K> implements GenericRepository<E, K>{
	
	@PersistenceContext
	protected EntityManager em;
	
	protected Class<E> entityClass;
	
	@SuppressWarnings("unchecked")
	public JpaGenericRepository() {
		this.entityClass = (Class<E>) ( (ParameterizedType) getClass().getGenericSuperclass() ).getActualTypeArguments()[0];
	}

	public void persist(E entity){
		em.persist(entity);
	}
		
	public E findById(K id) {
		return em.find(this.entityClass, id);
	}
		
	public List<E> findAll() {
		StringBuilder sb = new StringBuilder()
				.append("SELECT e FROM ")
				.append(this.entityClass.getName())
				.append(" e ");
		
		TypedQuery<E> query = em.createQuery(sb.toString(), this.entityClass);
		return query.getResultList();
	}
	
}