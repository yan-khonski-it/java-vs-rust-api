package com.yk.training.javaapp.service;

import static java.lang.String.format;

import com.yk.training.javaapp.model.Book;
import com.yk.training.javaapp.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class BookService {

  private static final Logger LOGGER = LogManager.getLogger(BookService.class);

  private static final String[] AUTHORS = {"Josh", "Alex", "Bob", "Richard", "Kevin"};
  private static final int MAX_PAGE_SIZE = 100;
  private static final AtomicInteger COUNTER = new AtomicInteger(0);
  private final List<Book> books = initBooks();

  private static List<Book> initBooksWithTimer() {
    return TimeUtils.measureTime("initBooks", BookService::initBooks);
  }

  private static Book generateBook() {
    int counterValue = COUNTER.incrementAndGet();
    String title = "Title-" + (System.currentTimeMillis() % 100) + "-" + counterValue;
    String isbn = "ISBN-" + (System.currentTimeMillis() % 1000) + "-" + counterValue;
    int authorIndex = counterValue % AUTHORS.length;
    String author = AUTHORS[authorIndex] + "-" + counterValue;
    return new Book(author, title, isbn, counterValue);
  }

  private static List<Book> initBooks() {
    int nBooks = 100000;
    List<Book> books = new ArrayList<>(nBooks);
    for (int i = 0; i < nBooks; i++) {
      Book book = generateBook();
      books.add(book);
    }
    return books;
  }

  public List<Book> getBooks(int page, int pageSize) {
    if (page < 0) {
      throw new IllegalArgumentException(format("Page: %s must be greater than or equal to 0.", page));
    }

    if (pageSize > MAX_PAGE_SIZE) {
      throw new IllegalArgumentException(format("pageSize: %s must not be greater than %s", pageSize, MAX_PAGE_SIZE));
    }

    int start = (page - 1) * pageSize;
    if (start >= books.size()) {
      return new ArrayList<>();
    }

    int end = start + pageSize;
    if (end > books.size()) {
      end = books.size();
    }

    List<Book> res = books.subList(start, end);
    LOGGER.info("getBooks(start: {}, end: {}, size: {})", start, end, res.size());
    return res;
  }

  public Book getBook(int id) {
    if (id < 0 || id >= books.size()) {
      throw new IllegalArgumentException(format("ID: %s must be greater than or equal to 0.", id));
    }
    LOGGER.info("getBook({})", id);
    return books.get(id);
  }
}
