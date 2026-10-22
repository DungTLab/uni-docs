/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q6;

/**
 *
 * @author LENOVO
 */
public class Literature implements IDocument{
    private String Name;
    private String TypeOfLit;
    private String Author;

    public Literature(String Name, String TypeOfLit, String Author) {
        this.Name = Name;
        this.TypeOfLit = TypeOfLit;
        this.Author = Author;
    }

    public String getName() {
        return Name;
    }

    public String getTypeOfLit() {
        return TypeOfLit;
    }

    public String getAuthor() {
        return Author;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setTypeOfLit(String TypeOfLit) {
        this.TypeOfLit = TypeOfLit;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }
    
    @Override
    public String inputData(){
        return String.format("Add literature (%s,%s,%s)", Name, TypeOfLit, Author);
    }
    
    @Override
    public String printData(){
        return String.format("Literature (%s,%s,%s)", Name, TypeOfLit, Author);
    }
}
