package com.ainamaani.librarymanagementsystem.services.impl;

import com.ainamaani.librarymanagementsystem.models.Book;
import com.ainamaani.librarymanagementsystem.repositories.BookRepository;
import com.ainamaani.librarymanagementsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(()-> new RuntimeException("Book not found with that ID " + id));
    }

    @Override
    public Book deleteBookById(Long id){
        Optional<Book> bookToBeDeleted = bookRepository.findById(id);

        if(bookToBeDeleted.isPresent()){
            bookRepository.deleteById(id);
            return bookToBeDeleted.get();
        }else{
            throw new RuntimeException("Book to be deleted not found");
        }
    }

    @Override
    public Book updateBook(Long id,Book updatedBook){
        //check if book with that id exists
        Optional<Book> existingBook = bookRepository.findById(id);

        if(existingBook.isPresent()){
            Book bookToUpdate = existingBook.get();

            //update the fields
            bookToUpdate.setTitle(updatedBook.getTitle());
            bookToUpdate.setBookDescription(updatedBook.getBookDescription());
            bookToUpdate.setAuthor(updatedBook.getAuthor());
            bookToUpdate.setCategory(updatedBook.getCategory());
            bookToUpdate.setPublicationDate(updatedBook.getPublicationDate());
            bookToUpdate.setPublisher(updatedBook.getPublisher());
            bookToUpdate.setEdition(updatedBook.getEdition());
            bookToUpdate.setLanguage(updatedBook.getLanguage());
            bookToUpdate.setNumberOfPages(updatedBook.getNumberOfPages());
            bookToUpdate.setStatus(updatedBook.getStatus());

            return bookRepository.save(bookToUpdate);
        }else{
            throw new RuntimeException("Book with that id "+ id + " is not available");
        }
    }
}
