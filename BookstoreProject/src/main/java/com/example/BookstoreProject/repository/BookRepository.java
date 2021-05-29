package com.example.BookstoreProject.repository;

import com.example.BookstoreProject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BookstoreProject.entity.Purchases;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
