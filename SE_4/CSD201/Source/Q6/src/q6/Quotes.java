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
public class Quotes implements IDocument{
    private String Content;
    private int NumberOfWord;
    private Literature objLiterature;

    public Quotes(String Content, int NumberOfWord, Literature objLiterature) {
        this.Content = Content;
        this.NumberOfWord = NumberOfWord;
        this.objLiterature = objLiterature;
    }

    public String getContent() {
        return Content;
    }

    public int getNumberOfWord() {
        return NumberOfWord;
    }

    public Literature getObjLiterature() {
        return objLiterature;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void setNumberOfWord(int NumberOfWord) {
        this.NumberOfWord = NumberOfWord;
    }
    
    public void setObjLiterature(Literature objLiterature) {
        this.objLiterature = objLiterature;
    }
    
    @Override
    public String inputData() {
        return String.format("Add Quotes(%s,%d,[%s])", parseContent(), NumberOfWord, objLiterature.printData());
    }

    public String parseContent(){
        String arr[] = Content.trim().split("-");
        int len = arr.length;
        String review = "";
        if (len == 1){
            review = String.format("%s ...", arr[0]);
        } else if (len == 2){
            review = String.format("%s %s ...", arr[0], arr[1]);
        } else {
            review = String.format("%s %s %s ...", arr[0], arr[1], arr[2]);
        }
        return review;
    }
    
    @Override
    public String printData() {
        return String.format("Quotes(%s,%d,[%s])", parseContent(), NumberOfWord, objLiterature.printData());
    }
}
