package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int authorId;
    private String name;
    @OneToMany(mappedBy = "author" )
    private List<Book> book;


    @Override
    public String toString() {
        return "Author{" +
                "id='" + authorId + '\'' +
                ", name='" + name + '\'' +
                ", book=" + book +
                '}';
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public Author(int id, String name, List<Book> book) {
        this.authorId = id;
        this.name = name;
        this.book = book;
    }

    public Author() {
    }
}
