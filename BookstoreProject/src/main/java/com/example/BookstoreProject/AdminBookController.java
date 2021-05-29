package com.example.BookstoreProject;

import com.example.BookstoreProject.entity.Book;
import com.example.BookstoreProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminBookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/{book_id}")
    public Book findById(@PathVariable long Id){
        return bookRepository.findById(Id).orElseThrow();
    }

    @PostMapping("/")
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/{book_id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long Id) throws Exception {
        Optional<Book> found = bookRepository.findById(Id);
        if (found.isEmpty()) {
            throw new Exception("Error!");
        } else {
            Book bookNew = found.get();
            if (book.getBook_name() != null) {
                bookNew.setBook_name(book.getBook_name());
            } else {
                bookNew.setBook_name(found.get().getBook_name());
            }
            if (book.getBook_price() > -1) {
                bookNew.setBook_price(book.getBook_price());
            } else {
                bookNew.setBook_price(found.get().getBook_price());
            }
            if (book.getBooks_in_stock() > -1) {
                bookNew.setBooks_in_stock(book.getBooks_in_stock());
            } else {
                bookNew.setBooks_in_stock(found.get().getBooks_in_stock());
            }
            if (book.getAuthor() != null){
                bookNew.setAuthor(book.getAuthor());
            } else {
                bookNew.setAuthor(found.get().getAuthor());
            }
            bookRepository.save(bookNew);
            return bookNew;
        }
    }

    @DeleteMapping("/{book_id}")
    public void deleteBook(@PathVariable Long Id){
        bookRepository.deleteById(Id);
    }

//    @GetMapping("/top10")
//    public Book[] findMostSoldBooks() {
//        int[] topBooks = {0,0,0,0,0,0,0,0,0,0};
//        List<Book> books = bookRepository.findAll();
//
//    }

}//
