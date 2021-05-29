package com.example.BookstoreProject.entity;
import javax.persistence.*;

@Entity
@Table (name="Purchases")
public class Purchases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchases_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "books_sold")
    private int books_sold;

    //TODO add column for date here

    public Purchases(){}

    public Purchases(Book book, User user, int books_sold) {
        this.book = book;
        this.user = user;
        this.books_sold = books_sold;
    }

}//
