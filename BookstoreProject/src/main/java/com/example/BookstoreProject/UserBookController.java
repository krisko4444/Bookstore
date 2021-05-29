package com.example.BookstoreProject;

import com.example.BookstoreProject.entity.Book;
import com.example.BookstoreProject.entity.Purchases;
import com.example.BookstoreProject.entity.User;
import com.example.BookstoreProject.repository.BookRepository;
import com.example.BookstoreProject.repository.PurchasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserBookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PurchasesRepository purchasesRepository;

    @GetMapping("/")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/{book_id}")
    public Book findById(@PathVariable long Id){
        return bookRepository.findById(Id).orElseThrow();
    }

    @PutMapping("/{book_id}")
    public Book sellBook(@RequestBody Book book, @RequestBody User user , @PathVariable Long Id) throws Exception {
        Optional<Book> found = bookRepository.findById(Id);
        int BooksNow;
        if (found.isEmpty()){
            throw new Exception("Error!");
        } else {
            Book bookNew = found.get();
            bookNew.setBook_name(found.get().getBook_name());
            bookNew.setBook_price(found.get().getBook_price());
            bookNew.setAuthor(found.get().getAuthor());
            int inStock = found.get().getBooks_in_stock();
            if (book.getBooks_in_stock() > inStock && book.getBooks_in_stock() < 1) {
                throw new Exception("Error!");
            } else {
                BooksNow = inStock - book.getBooks_in_stock();
                bookNew.setBooks_in_stock(BooksNow);
            }
            bookRepository.save(bookNew);
            purchasesRepository.save(new Purchases(found.get(), user, BooksNow));
            return bookNew;
        }
    }

}//
