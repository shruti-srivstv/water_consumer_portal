package it.polimi.awt.waterconsumer.repository;

import java.util.List;

public interface GenericRepository<E, K>{
	public void persist(E entity);
	public List<E> findAll();
}