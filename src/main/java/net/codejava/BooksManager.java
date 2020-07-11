package net.codejava;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BooksManager {
	
	static EntityManagerFactory factory;
	static EntityManager entityManager;

	public static void main(String[] args) {
		
		begin();
		//create();
		//update();
		//find();
		//query();
		remove();
		end();
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("BookUnit");
	    entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	private static void create() {
		Book newBook = new Book();
		newBook.setTitle("Thinking in Java");
		newBook.setAuthor("Aurora Cassano");
		newBook.setPrice(45.50f);
		entityManager.persist(newBook);
	}
	
	private static void update() {
		Book existingBook = new Book();
		existingBook.setBookId(1);
		existingBook.setTitle("Effective Java 2");
		existingBook.setAuthor("Aurora Cassano");
		existingBook.setPrice(145.50f);
		entityManager.merge(existingBook);
	}
	
	private static void find() {
		Integer primaryKey = 2;
		Book book = entityManager.find(Book.class, primaryKey);
		System.out.println(book);
	}
	
	private static void query() {
		String jpql = "Select b From Book b Where b.price < 100";
		Query query = entityManager.createQuery(jpql);
		List<Book> books = query.getResultList();
		for(Book book : books) System.out.println(book);
	}
	
	private static void remove() {
		Integer primaryKey = 3;
		Book book = entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(book);
	}

}
