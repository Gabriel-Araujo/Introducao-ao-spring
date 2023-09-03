package com.example.biblioteca.ClientBooks;

import com.example.biblioteca.Book.Book;
import com.example.biblioteca.Client.Client;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClientBooksId implements Serializable {
    private Client client;
    private Book book;

    ClientBooksId(Client client, Book book) {
        this.client = client;
        this.book = book;
    }

}
