/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q5;

/**
 *
 * @author LENOVO
 */
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;

public class Library {
    Map<String, Book> books = new LinkedHashMap<>();
    
    public boolean add(Book b){
        if (books.containsKey(b.getIsbn())){
            return false;
        } else {
            books.put(b.getIsbn(), b);
            return true;
        }
    }
    
    public boolean remove(String isbn){
        if (books.containsKey(isbn)){
            books.remove(isbn);
            return true;
        } else {
            return false;
        }
    }
    
    public Book get(String isbn){
        return books.get(isbn);
    }
    
    public List<Book> listAll(){
        ArrayList<Book> b = new ArrayList<>(books.values());
        return b;
    }
    
    public boolean update(String isbn, String newTitle, String newAuthor, int newYear, long newPrice){
        if (books.containsKey(isbn)){
            Book bookUpadte = books.get(isbn);
            bookUpadte.setTitle(newTitle);
            bookUpadte.setAuthor(newAuthor);
            bookUpadte.setYear(newYear);
            bookUpadte.setPrice(newPrice);
            return true;
        } else return false;
    }
}
