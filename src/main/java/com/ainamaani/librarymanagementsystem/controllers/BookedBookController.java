package com.ainamaani.librarymanagementsystem.controllers;

import com.ainamaani.librarymanagementsystem.models.BookedBook;
import com.ainamaani.librarymanagementsystem.services.BookedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookedBookController {

    @Autowired
    private BookedBookService bookedBookService;

    @PostMapping("/addbookedbook")
    public ResponseEntity<String> addBookedBook(@RequestBody BookedBook bookedBook){
        if(bookedBook.getBookedBook() == null){
            return ResponseEntity.status(400).body("The booked book can't be empty");
        }
        if(bookedBook.getBorrower() == null){
            return ResponseEntity.status(400).body("The borrower can't be missing");
        }
        try{
            BookedBook bookBooked = bookedBookService.addBookedBook(bookedBook);
            return ResponseEntity.status(200).body("Saved successfully" + bookBooked);
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/bookedbooks")
    public ResponseEntity<?> getBookedBooks(){
        try{
            List<BookedBook> bookedBooks = bookedBookService.fetchBookedBooks();
            if(bookedBooks != null){
                return ResponseEntity.status(200).body(bookedBooks);
            }else{
                return ResponseEntity.status(400).body("Could not fetch the list of booked books");
            }
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
}
