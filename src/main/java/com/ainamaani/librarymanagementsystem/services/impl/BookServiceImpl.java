package com.ainamaani.librarymanagementsystem.services.impl;

import com.ainamaani.librarymanagementsystem.models.Book;
import com.ainamaani.librarymanagementsystem.repositories.BookRepository;
import com.ainamaani.librarymanagementsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
