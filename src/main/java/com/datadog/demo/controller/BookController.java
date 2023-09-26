package com.datadog.demo.controller;

import com.datadog.demo.entities.Book;
import com.datadog.demo.service.BookService;
import com.timgroup.statsd.StatsDClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("rest")
public class BookController {

    @Autowired
    BookService bookService;

    List<Book> books = new ArrayList<>();

    private final StatsDClient statsd;

    public BookController(StatsDClient statsd) {
        this.statsd = statsd;
    }


    @GetMapping("/healthcheck")
    public String healthCheck() throws InterruptedException {
        statsd.count("healthcheck.count", 1);
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        long seconds = (timeTaken / 1000) % 60;
        statsd.histogram("healthcheck.histogram.duration", seconds);
        statsd.gauge("healthcheck.gauge.active-threads", Thread.activeCount());
        return "You are successfully reached the REST endpoint";
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        statsd.count("add", 1);

        return bookService.add(book);
    }

    @GetMapping("/remove/{id}")
    public String removeBook(@PathVariable Integer id) {
        statsd.count("remove", 1);

        bookService.remove(id);
        return "Book Removed";
    }

    @GetMapping("/getAll")
    public List<Book> getAllBook() {
        statsd.count("get-all", 1);
        return bookService.findAll();
    }

    @GetMapping("{id}")
    public Book getById(@PathVariable Integer id) {
        statsd.count("get-by-id", 1);
        return bookService.getById(id);
    }
}
