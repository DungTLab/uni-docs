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
public class BookCategory implements IBook{
    private String id;
    private String name;

    public BookCategory() {
    }

    public BookCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String entry() {
        return String.format("(%s,%s) is added", id, name);
    }

    @Override
    public String print() {
        return String.format("(%s,%s)", id, name);
    }
}
