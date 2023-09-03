CREATE TABLE client (
    "id" SERIAL PRIMARY KEY,
    "name" TEXT NOT NULL
);


CREATE TABLE book (
    "id" SERIAL PRIMARY KEY,
    "title" TEXT NOT NULL
);


CREATE TABLE client_books (
    "client_id" INTEGER NOT NULL,
    "book_id" INTEGER NOT NULL,
    "start_date" TIMESTAMP DEFAULT NOW(),
    "end_date" TIMESTAMP DEFAULT NOW() - INTERVAL '-30 DAY',
    PRIMARY KEY (client_id, book_id),
    FOREIGN KEY (client_id) REFERENCES client("id"),
    FOREIGN KEY (book_id) REFERENCES book("id")
);