package org.example.entity;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.persistence.*;

import java.time.Year;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private Year publicationYear;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "auth_id",referencedColumnName = "author_id")
    private Author author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(int id, String title, Year publicationYear, Double price, Author author) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.price = price;
        this.author = author;
    }
}

