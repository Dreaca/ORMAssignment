package org.example;

import org.example.config.FactoryConfiguration;
import org.example.entity.Author;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

//        saveData(session,transaction);
//        get2010(session,transaction);
//        increaseby10(session,transaction);
//        deleteAuthor(session,transaction);
//        findAverage(session,transaction);
//        getAllAuthorBooks(session,transaction);
        countryBooks(session,transaction);
        session.close();
    }
    public static void saveData(Session session,Transaction transaction){

            transaction = session.beginTransaction();
            Book book = new Book();
            Author author = new Author();
            author.setName("Arthur Conan Doyle");
            author.setCountry("UK");

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
    }
    public static void get2010(Session session, Transaction transaction){
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Book where publicationYear > ?1");
        query.setParameter(1,Year.parse("2010"));
        List <Book>list = query.list();
        for(Book book : list){
            System.out.println(book.getId()+"   |   "+book.getTitle()+"   |   "+book.getAuthor().getName()+"    |   "+book.getPrice()+"    |   "+book.getPublicationYear()+"  |    " );
        }
        transaction.commit();
    }
    public static void increaseby10(Session session, Transaction transaction){

         transaction = session.beginTransaction();

        Query query = session.createQuery("update Book set price=(price+(price/100)*10) where author.id=?1");
        System.out.print("Enter Author id ; ");
        query.setParameter(1,new Scanner(System.in).next());
        int i = query.executeUpdate();
        System.out.println("Updated Rows : "+i);

        query = session.createQuery("from Book ");
        List<Book> list = query.list();
        for(Book book:list){
            System.out.println(book.getId()+"   |   "+book.getTitle()+"   |   "+book.getAuthor().getName()+"    |   "+book.getPrice()+"    |   "+book.getPublicationYear()+"  |    " );
        }
        transaction.commit();
    }
    public static void deleteAuthor(Session session,Transaction transaction){
        transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Author where authorId =?1");
        System.out.print("Auther Id : ");
        query.setParameter(1,new Scanner(System.in).next());
        int i = query.executeUpdate();
        System.out.println("Authors deleted :"+i);
        transaction.commit();
        displayAuthors(session,transaction);


    }
    public static void displayAuthors(Session session, Transaction transaction){
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Author ");
        List<Author> list = query.list();
        for(Author author:list){
            System.out.println(author.getAuthorId()+"   |   "+author.getName());
        }
        transaction.commit();
    }

    public static void findAverage(Session session, Transaction transaction){
        transaction = session.beginTransaction();
        Query query = session.createQuery("select AVG(price) from Book");
        List list = query.list();
        System.out.println(list);
        transaction.commit();
    }

    public static void getAllAuthorBooks(Session session, Transaction transaction){

        transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT a.name, COUNT(b.id) FROM Author a LEFT JOIN Book b on a.authorId = b.author.authorId GROUP BY a");
        List<Object[]> results = query.list();
        for (Object[] result : results) {
            String name = (String) result[0];
            Long bookCount = (Long) result[1];
            System.out.println(name+":  Count  "+bookCount );
        }

    }
    public static void countryBooks(Session session, Transaction transaction){

        transaction = session.beginTransaction();
        Query query = session.createQuery("from Book b where author.country = ?1");
        System.out.print("Enter a country : ");
        query.setParameter(1,new Scanner(System.in).next());
        List<Book> list = query.list();
        for (Book book : list){
            System.out.println("| "+book.getId()+"| "+book.getTitle()+ "| "+book.getPublicationYear()+"| "+book.getPrice());
        }

    }
}