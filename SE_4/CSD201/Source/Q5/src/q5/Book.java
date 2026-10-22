/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q5;

import java.util.Locale;

/**
 *
 * @author LENOVO
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private int year;
    private long price;

    public Book(String isbn, String title, String author, int year, long price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public long getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        //NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        //String formattedString = formatter.format(number);
        String formatted = String.format(Locale.US, "%,d", price);
        return String.format("%s;%s;%s;%d;%s", isbn, title,author,year, formatted);
    }    
}
