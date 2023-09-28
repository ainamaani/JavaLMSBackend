package com.ainamaani.librarymanagementsystem.services;

import com.ainamaani.librarymanagementsystem.models.BookedBook;

import java.util.List;

public interface BookedBookService {
    BookedBook addBookedBook(BookedBook bookedBook);

    List<BookedBook> fetchBookedBooks();
}
