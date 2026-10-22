/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q1;

/**
 *
 * @author LENOVO
 */
public class Book  implements IBook{
    private String title;
    private String author;
    private int yop;
    private BookCategory bookCategory;

    public Book() {
    }

    public Book(String title, String author, int yop, BookCategory bookCategory) {
        this.title = title;
        this.author = author;
        this.yop = yop;
        this.bookCategory = bookCategory;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYop() {
        return yop;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYop(int yop) {
        this.yop = yop;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
    

    @Override
    public String entry() {
        return String.format("(%s) is added", title);
    }

    @Override
    public String print() {
        return String.format("(%s,%s,%d):[%s]", title, author, yop, bookCategory.getName());
    }
}
