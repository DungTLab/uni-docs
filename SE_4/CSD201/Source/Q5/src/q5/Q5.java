/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q5{
    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
	
                    int T;
                    StringBuilder result = new StringBuilder();
                    ArrayList<String> lineList = new ArrayList<>();
                    Library library = new Library();

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
                   while (sc.hasNextLine()){
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
    
    private static long parsePrice(String newPrice){
        String formatted;
        formatted = newPrice.replace(",", "");
        return Long.parseLong(formatted);
    }
    
	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
    /**
     * Main algorithm
     */
    public void solve(){
    //--END FIXED PART----------------------------

        //ALGORITHM - @STUDENT: ADD YOUR CODE HERE:
            int command = 0;
            int idx = 0;
                    while (command < T){
                        String s = lineList.get(idx);
                        idx++;
                        if (s.equalsIgnoreCase("AddBook")){
                            String isbn = lineList.get(idx); idx++;
                            String title = lineList.get(idx); idx++;
                            String author = lineList.get(idx); idx++;
                            int year = Integer.parseInt(lineList.get(idx)); idx++;
                            long price = parsePrice(lineList.get(idx)); idx++;
                            Book newBook = new Book(isbn, title, author, year, price);
                            library.add(newBook);
                            result.append("Add a book\n");
                        } else if (s.equalsIgnoreCase("UpdateBook")){
                            String isbn = lineList.get(idx); idx++;
                            String title = lineList.get(idx); idx++;
                            String author = lineList.get(idx); idx++;
                            int year = Integer.parseInt(lineList.get(idx)); idx++;
                            long price = parsePrice(lineList.get(idx)); idx++;
                            library.update(isbn, title, author, year, price);
                            result.append("Update a book\n");
                        } else if (s.equalsIgnoreCase("RemoveBook")){
                            String isbn = lineList.get(idx); idx++;
                            library.remove(isbn);
                            result.append("Remove a book.\n");
                        } else if (s.equalsIgnoreCase("PrintAll")){
                            List<Book> b = library.listAll();
                            result.append("List of book:\n");
                            for (Book book : b){
                                result.append(book.toString()).append("\n");
                            }
                            result.append("-------------------\n");
                        }
                        command++;
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
        Q5 q = new Q5();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
	//--END FIXED PART----------------------------    
}