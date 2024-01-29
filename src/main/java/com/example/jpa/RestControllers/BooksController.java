package com.example.jpa.RestControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jpa.entities.Book;
import com.example.jpa.service.GetMyBooks;

@RestController
public class BooksController {

	@Autowired
	GetMyBooks books;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {

		List<Book> list = books.getAllBooks();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.ok(list);
		}
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getMyBook(@PathVariable("id") int id) {
		Optional<Book> myBookById = books.getMyBookById(id);
		if (myBookById == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(myBookById);
	}

//	@RequestMapping(name = "/books", method=RequestMethod.POST)
	@PostMapping("/books")
	public ResponseEntity<Book> addANewBook(@RequestBody Book b) {
		Book book = books.addBook(b);
		return ResponseEntity.of(Optional.of(book));
	}

	@DeleteMapping("/books")
	public ResponseEntity<Void> deleteAllBooks() {
		this.books.deleteAllBooks();
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<Boolean> deleteBookById(@PathVariable("id") int id) {
		 boolean deleteBookById = books.deleteBookById(id);
		if (!deleteBookById) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(deleteBookById);
	}

	@PutMapping("/books")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		Book updateBook = books.updateBook(book);
		if (updateBook != null) {
			return ResponseEntity.of(Optional.of(book));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
