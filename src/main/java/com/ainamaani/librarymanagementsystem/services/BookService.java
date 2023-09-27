package com.ainamaani.librarymanagementsystem.services;

import com.ainamaani.librarymanagementsystem.models.Book;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book deleteBookById(Long id);
    Book updateBook(Long id, Book updatedBook);

}
