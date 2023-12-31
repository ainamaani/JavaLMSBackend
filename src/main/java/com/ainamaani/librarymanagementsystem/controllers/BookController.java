package com.ainamaani.librarymanagementsystem.controllers;

import com.ainamaani.librarymanagementsystem.models.Book;
import com.ainamaani.librarymanagementsystem.models.enums.BookStatus;
import com.ainamaani.librarymanagementsystem.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addbook")
    public ResponseEntity<String> add(@RequestBody Book book) {

        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().body("Title is required");
        }

        if (book.getBookDescription() == null || book.getBookDescription().isEmpty()) {
            return ResponseEntity.badRequest().body("Book description is required");
        }

        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            return ResponseEntity.badRequest().body("Author is required");
        }

        if (book.getCategory() == null || book.getCategory().isEmpty()) {
            return ResponseEntity.badRequest().body("Category is required");
        }
        if(book.getPublicationDate() == null ){
            return ResponseEntity.badRequest().body("Publication date is required");
        }
        if(book.getPublisher() == null || book.getPublisher().isEmpty()){
            return ResponseEntity.badRequest().body("Publisher is required");
        }
        if(book.getEdition() == null){
            return ResponseEntity.badRequest().body("Edition is required");
        }
        if(book.getNumberOfPages() == null){
            return ResponseEntity.badRequest().body("Number of pages is required");
        }
        if(book.getLanguage() == null || book.getLanguage().isEmpty()){
            return ResponseEntity.badRequest().body("Language is required");
        }
        if (!isValidStatus(book.getStatus())) {
            return ResponseEntity.badRequest().body("Invalid status value. Allowed values are: BOOKED, AVAILABLE, TAKEN");
        }

        try {
            Book addedBook = bookService.saveBook(book);
            if (addedBook != null) {
                return ResponseEntity.ok("Book saved successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book save failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    private boolean isValidStatus(BookStatus status) {
        return EnumSet.of(BookStatus.AVAILABLE, BookStatus.BOOKED, BookStatus.TAKEN).contains(status);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    //return responses of different data types
    public ResponseEntity<?> getBookOfId(@PathVariable Long id){
        try{
            Book book = bookService.getBookById(id);
            if(book != null){
                return ResponseEntity.status(200).body(book);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        try{
            Book deletedBook = bookService.deleteBookById(id);
            if(deletedBook != null){
                return ResponseEntity.status(200).body(deletedBook);
            }else{
                return ResponseEntity.status(400).body("Failed to delete the book");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
        try{
            Book newBookVersion = bookService.updateBook(id,updatedBook);
            if(newBookVersion != null){
                return ResponseEntity.status(200).body(updatedBook);
            }else{
                return ResponseEntity.status(400).body("Failed to update book");
            }
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
