package it.polimi.awt.waterconsumer.repository;

import it.polimi.awt.waterconsumer.domain.Building;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class JpaBuildingRepository implements BuildingRepository{
	@PersistenceContext
	private EntityManager em;
	
	public Building findBuildingById(Integer id){
		TypedQuery<Building> query= em.createQuery("SELECT b FROM Building b WHERE b.oid = :id", Building.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}
}