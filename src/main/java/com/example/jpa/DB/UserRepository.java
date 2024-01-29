package com.example.jpa.DB;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa.entities.Book;

public interface UserRepository extends CrudRepository<Book,Integer> {

}
