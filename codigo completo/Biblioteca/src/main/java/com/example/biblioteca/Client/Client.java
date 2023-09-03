package com.example.biblioteca.Client;

import com.example.biblioteca.ClientBooks.ClientBooks;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    List<ClientBooks> books;
}
