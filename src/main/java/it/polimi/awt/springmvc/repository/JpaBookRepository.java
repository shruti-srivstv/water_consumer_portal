package it.polimi.awt.springmvc.repository;

import it.polimi.awt.springmvc.domain.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBookRepository implements BookRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Book> findAll() {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}
	
	@Override
	public Book findBookById(Integer id) {
		return em.find(Book.class, id);
	}
	
	@Override
	public Book findBookByIsbn(String isbn) {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class);
		query.setParameter("isbn",isbn);
		
		List<Book> resultList = query.getResultList();
		
		Book book = null;		
		if (!resultList.isEmpty()){
        	book = resultList.get(0);
        }
		return book;
	}
	
	@Override
	public void persistBook(Book book) {
		em.persist(book);
	}
	
	@Override
	public void saveOrUpdateBook(Book book) {
		em.merge(book);
	}
	
	@Override
	public void removeBook(Book book) {
		em.remove(book);
	}
	
}
