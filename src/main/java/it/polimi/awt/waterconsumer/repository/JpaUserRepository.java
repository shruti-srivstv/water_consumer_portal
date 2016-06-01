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
	
	public Integer findUserforLogin(String username, String password){
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		List<User> resultList = query.getResultList();
		Integer userid = null;
		if (!resultList.isEmpty()){
			userid = resultList.get(0).getOid();
		}
		return userid;
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
	
	//	@Override
//	public User findBookByIsbn(String isbn) {
//		TypedQuery<Book> query = em.createQuery("SELECT b FROM User b WHERE b.isbn = :isbn", Book.class);
//		query.setParameter("isbn",isbn);
//		
//		List<Book> resultList = query.getResultList();
//		
//		Book book = null;		
//		if (!resultList.isEmpty()){
//        	book = resultList.get(0);
//        }
//		return book;
//	}
//	
//	@Override
//	public void persistBook(Book book) {
//		em.persist(book);
//	}
//	
//	@Override
//	public void saveOrUpdateBook(Book book) {
//		em.merge(book);
//	}
//	
//	@Override
//	public void removeBook(Book book) {
//		em.remove(book);
//	}
	
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
