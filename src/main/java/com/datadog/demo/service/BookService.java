package com.datadog.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datadog.demo.entities.Book;
import com.datadog.demo.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book add(Book book) {
        return bookRepository.save(book);
    }

    public void remove(Integer id) {
        bookRepository.deleteById(id);
    }

    public Book getById(Integer id) {
        return bookRepository.findById(id).get();
    }
}
