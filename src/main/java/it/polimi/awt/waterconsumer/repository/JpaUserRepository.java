package it.polimi.awt.waterconsumer.repository;

import it.polimi.awt.waterconsumer.domain.NeutralUser;
import it.polimi.awt.waterconsumer.domain.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepository implements UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);		
		return query.getResultList();
	}
	
	
	public User findUserById(Integer id) {
		return em.find(User.class, id);
	}	
	
	public User findUserbyUsername(String username, String password){
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username and u.password = :password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		List<User> resultList = query.getResultList();
		
		User user = null;
		if (!resultList.isEmpty()){
			user = resultList.get(0);
		}
		return user;
	}
	
	public NeutralUser findNeutralUserbyId(Integer id){
		TypedQuery<NeutralUser> query = em.createQuery("SELECT n FROM NeutralUser n WHERE n.userOid = :oid", NeutralUser.class);
		query.setParameter("oid", id);
		List<NeutralUser> resultList = query.getResultList();
		
		NeutralUser neutralUser = null;
		if (!resultList.isEmpty()){
			neutralUser = resultList.get(0);
		}
		return neutralUser;
	}
	
	public User findUserbyHouseholdId(Integer id){
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.neutralUser.household.oid = :householdId", User.class);
		query.setParameter("householdId", id);
		List<User> resultList = query.getResultList();
		
		User user = null;
		if (!resultList.isEmpty()){
			user = resultList.get(0);
		}
		return user;
	}
	
	public User findUserbyBuildingId(Integer id){
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.neutralUser.household.oid = :householdId", User.class);
		query.setParameter("householdId", id);
		List<User> resultList = query.getResultList();
		
		User user = null;
		if (!resultList.isEmpty()){
			user = resultList.get(0);
		}
		return user;
	}
	public User findUserbySmartMeterId(Integer id){
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.neutralUser.household.oid = :householdId", User.class);
		query.setParameter("householdId", id);
		List<User> resultList = query.getResultList();
		
		User user = null;
		if (!resultList.isEmpty()){
			user = resultList.get(0);
		}
		return user;
	}
	public User findUserbyZipcode(String id){
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.neutralUser.household.oid = :householdId", User.class);
		query.setParameter("householdId", id);
		List<User> resultList = query.getResultList();
		
		User user = null;
		if (!resultList.isEmpty()){
			user = resultList.get(0);
		}
		return user;
	}
}
