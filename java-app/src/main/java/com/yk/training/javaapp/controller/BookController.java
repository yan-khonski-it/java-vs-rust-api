package com.yk.training.javaapp.controller;

import com.yk.training.javaapp.model.Book;
import com.yk.training.javaapp.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books") // Base path for book-related endpoints
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public Book getBook(@PathVariable("id") int id) {
    return bookService.getBook(id);
  }

  @GetMapping
  public List<Book> getBooks(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    return bookService.getBooks(page, pageSize);
  }
}