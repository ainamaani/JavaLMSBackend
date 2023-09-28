package com.ainamaani.librarymanagementsystem.services.impl;

import com.ainamaani.librarymanagementsystem.models.BookedBook;
import com.ainamaani.librarymanagementsystem.repositories.BookedBookRepository;
import com.ainamaani.librarymanagementsystem.services.BookedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedBookServiceImpl implements BookedBookService {

    @Autowired
    private BookedBookRepository bookedBookRepository;

    @Override
    public BookedBook addBookedBook(BookedBook bookedBook){
        return bookedBookRepository.save(bookedBook);
    }

    @Override
    public List<BookedBook> fetchBookedBooks(){
        return bookedBookRepository.findAll();
    }
}
