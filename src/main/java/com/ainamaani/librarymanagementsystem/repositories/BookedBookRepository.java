package com.ainamaani.librarymanagementsystem.repositories;

import com.ainamaani.librarymanagementsystem.models.BookedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedBookRepository extends JpaRepository<BookedBook,Long> {
}
