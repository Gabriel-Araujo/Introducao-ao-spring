package com.example.biblioteca.ClientBooks;

import com.example.biblioteca.Book.Book;
import com.example.biblioteca.Client.Client;
import jakarta.persistence.*;

import java.util.Date;

@Entity @IdClass(ClientBooksId.class) @Table(name = "client_books")
public class ClientBooks {
    @Id @ManyToOne @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @Id @OneToOne @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    private Date startDate;
    private Date endDate;
}
