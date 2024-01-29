package com.example.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.core.ApplicationContext;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.example.jpa.BootRestApiApplication;
import com.example.jpa.DB.UserRepository;
import com.example.jpa.entities.Book;

@Service
public class GetMyBooks {
	@Autowired
	UserRepository context;

	private List<Book> books = new ArrayList<>();
	int flag = 0;

	public GetMyBooks() {
//		books.add(new Book("Java The complete reference", "herbert schilts"));
//		books.add(new Book("C++ The complete reference", "herbert schilts"));
//		books.add(new Book("Programming in ANSI", "BalGuru swamy"));
//		books.add(new Book("Data Structure and algorithms", "Vishal Bala"));
//		books.add(new Book("Gaddari Korbe", "Bihar guys"));

//		context = BootRestApiApplication.run;
//		UserRepository repo = context.getBean(UserRepository.class);
//		this.context.save(new Book("Java The complete reference", "herbert schilts"));

	}

	public List<Book> getAllBooks() {
		return (List<Book>) context.findAll();
	}

	public Optional<Book> getMyBookById(int id) {

		Optional<Book> byId = context.findById(id);
		return byId;
		// return new Book(id, "No Book Found With This Name", "No Author Found");
	}

	public Book addBook(Book book) {
		Book b = context.save(book);
		return b;
	}

	public void deleteAllBooks() {

		context.deleteAll();
	}

	public boolean deleteBookById(int id) {
		try {

			context.deleteById(id);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Book updateBook(Book book) {
		Optional<Book> b = context.findById(book.getId());
		if (b.isEmpty()) {
			return null;
		}
		Book save = context.save(b.get());

		return save;
	}
}
