package com.ainamaani.librarymanagementsystem.models;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "booked_books")
public class BookedBook extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "booked_book")
    private Book bookedBook;
    @Column(name = "borrower")
    private String borrower;

    public Book getBookedBook(){
        return bookedBook;
    }

    public void setBookedBook(Book bookedBook) {
        this.bookedBook = bookedBook;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
}
