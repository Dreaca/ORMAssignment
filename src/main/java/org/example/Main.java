package org.example;

import org.example.config.FactoryConfiguration;
import org.example.entity.Author;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.Year;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Book book = new Book();
            Author author = new Author();
            author.setName("Arthur Conan Doyle");

            book.setTitle("Sherlock Holmes");
            book.setPublicationYear(Year.parse("1996"));
            book.setPrice(400.00);

            ArrayList<Book> books = new ArrayList<>();
            books.add(book);
            author.setBook(books);
            book.setAuthor(author);

            session.save(author);
            session.save(book);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}