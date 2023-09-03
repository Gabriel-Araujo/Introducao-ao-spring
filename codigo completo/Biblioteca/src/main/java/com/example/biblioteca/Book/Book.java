package com.example.biblioteca.Book;

import com.example.biblioteca.ClientBooks.ClientBooks;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @OneToOne(mappedBy = "book", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private ClientBooks client;
}
