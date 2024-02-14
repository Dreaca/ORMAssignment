package org.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int authorId;
    private String name;
    private String country;

    @OneToMany(mappedBy = "author" ,orphanRemoval = true,cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Book> book;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", book=" + book +
                '}';
    }

    public Author(int authorId, String name, String country, List<Book> book) {
        this.authorId = authorId;
        this.name = name;
        this.country = country;
        this.book = book;
    }

    public Author() {
    }
}
