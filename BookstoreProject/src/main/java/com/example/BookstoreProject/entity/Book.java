package com.example.BookstoreProject.entity;

import javax.persistence.*;

@Entity
@Table (name="Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long book_id;

    @Column(name="book_name")
    private String book_name;

    @Column(name="price")
    private double book_price;

    @Column(name="in_stock")
    private int books_in_stock;

    @Column(name="author")
    private String author;


    public Book(String book_name, double book_price, int books_in_stock, String author) {
        this.book_name = book_name;
        this.book_price = book_price;
        this.books_in_stock = books_in_stock;
        this.author = author;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    public int getBooks_in_stock() {
        return books_in_stock;
    }

    public void setBooks_in_stock(int books_in_stock) {
        this.books_in_stock = books_in_stock;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
    private Purchases purchases;

    public Book() {
    }



}//
