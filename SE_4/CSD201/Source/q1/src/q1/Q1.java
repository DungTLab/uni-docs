/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q1 {
    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
                    int T;
                    ArrayList<String> lineList = new ArrayList<>();
                    StringBuilder result = new StringBuilder();
                    ArrayList<Book> books = new ArrayList<>();
                    ArrayList<BookCategory> bookCategorys = new ArrayList<>();

	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
    String fi, fo;
    
    /**
     * Set input and output file for automatic rating
     * @param args path of input file and path of output file
     */
    public void setFile (String[] args){
        fi = args.length>=2? args[0]: inputFile;
        fo = args.length>=2? args[1]: outputFile;
    }
    
    /**
     * Reads data from input file
     */
    public void read(){
        try (Scanner sc  = new Scanner(new File(fi))){
    //--END FIXED PART----------------------------

            //INPUT - @STUDENT: ADD YOUR CODE FOR INPUT HERE:
                    T = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < T; i++){
                        lineList.add(sc.nextLine());
                    }

	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
            sc.close();
        }catch(FileNotFoundException ex){
            System.out.println("Input Exception # " + ex);
        }
    }
    //--END FIXED PART----------------------------
    
    //ALGORITHM - @STUDENT: ADD YOUROWN METHODS HERE (IF NEED):
    

    
	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
    /**
     * Main algorithm
     */
    public void solve(){
    //--END FIXED PART----------------------------

        //ALGORITHM - @STUDENT: ADD YOUR CODE HERE:
        
                    for (String s : lineList){
                        String arr[] = s.trim().split(",");
                        if (arr[0].equalsIgnoreCase("AddBookCategory")){
                            BookCategory newBookCategory = new BookCategory(arr[1], arr[2]);
                            bookCategorys.add(newBookCategory);
                            result.append(newBookCategory.entry()).append("\n");
                        } else if (arr[0].equalsIgnoreCase("PrintBookCategory")){
                            result.append("---PrintBookCategory---\n");
                            if (bookCategorys.isEmpty()){
                                    result.append("Empty\n");
                             } else {
                                for (BookCategory bc : bookCategorys){
                                    result.append(bc.print()).append("\n");
                                }
                            }
                            result.append("---\n");
                        }  else if (arr[0].equalsIgnoreCase("AddBook")){
                            BookCategory bc = new BookCategory();
                            for (BookCategory bcs : bookCategorys){
                                if (bcs.getId().equals(arr[4])){
                                    bc = bcs;
                                }
                            }
                            
                            Book newBook = new Book(arr[1], arr[2], Integer.parseInt(arr[3]), bc);
                            books.add(newBook);
                            result.append(newBook.entry()).append("\n");
                        } else if (arr[0].equalsIgnoreCase("PrintBook")){
                            result.append("--PrintBook---\n");
                            if (books.isEmpty()){
                                result.append("Empty\n");
                            } else {
                                for (Book b : books){
                                    result.append(b.print()).append("\n");
                                }
                            }
                            result.append("---\n");
                        } else if (arr[0].equalsIgnoreCase("SearchBook")){
                            int found = 0;
                            result.append("---Search Result---\n");
                            for (Book b : books){
                                if (b.getTitle().equals(arr[1])){
                                    result.append(b.print()).append("\n");
                                    found = 1;
                                    break;
                                }
                            }
                            if (found == 0){
                                result.append("Not Found\n");
                            }
                            result.append("---\n");
                        }  else if (arr[0].equalsIgnoreCase("ClearBook")){
                            books.clear();
                            result.append("* Remove all book");
                        }
                    }
                    System.out.println(result.toString());
	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART-------------------------- 
    }
    
    /**
     * Write result into output file
     */
    public void printResult(){
	    try{
            FileWriter fw = new FileWriter(fo);
	//--END FIXED PART----------------------------
                
        	//OUTPUT - @STUDENT: ADD YOUR CODE FOR OUTPUT HERE:
          
                fw.write(result.toString());


	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART-------------------------- 
            fw.flush();
            fw.close();
        }catch (IOException ex){
            System.out.println("Output Exception # " + ex);
        }
    }

    /**
     * @param args the command line arguments
    */
    public static void main(String[] args) {
        // TODO code application logic here
        Q1 q = new Q1();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
	//--END FIXED PART----------------------------    
}