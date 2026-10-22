/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;

public class Q6 {
    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
	
                    int T;
                    StringBuilder result = new StringBuilder();
                    ArrayList<String> lineList = new ArrayList<>();
                    Map<String, Literature> literatures = new LinkedHashMap<>();
                    ArrayList<Quotes> quotes = new ArrayList<>();
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
                    for (int i = 0; i < T;  i++){
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
                    for (String line : lineList){
                        String arr[] = line.trim().split(" ");
                        if (arr[0].equalsIgnoreCase("Literature")){
                            Literature newLiterature = new Literature(arr[1], arr[2], arr[3]);
                            literatures.put(arr[3], newLiterature);
                            result.append(newLiterature.inputData()).append("\n");
                        } else if (arr[0].equalsIgnoreCase("Quotes")){
                            String sub[] = arr[1].trim().split("-");
                            int number = sub.length;
                            Literature li = literatures.get(arr[2]);
                            Quotes newQuotes = new Quotes(arr[1], number, li);
                            quotes.add(newQuotes);
                            result.append(newQuotes.printData()).append("\n");
                        } else if (arr[0].equalsIgnoreCase("PrintLiterature")){
                            result.append("---Literature---\n");
                            if (literatures.isEmpty()){
                                result.append("Empty\n");
                            } else {
                                for (Literature value : literatures.values()){
                                    result.append(value.printData()).append("\n");
                                }
                            }
                            result.append("------\n");
                        } else if (arr[0].equalsIgnoreCase("PrintQuotes")){
                            result.append("---Quotes---");
                            if (quotes.isEmpty()){
                                result.append("Empty\n");
                            } else {
                                quotes.forEach(value -> {
                                    result.append(value.printData()).append("\n");
                                 });
                            }
                            result.append("------\n");
                        } else if (arr[0].equalsIgnoreCase("SearchFirst")){
                            result.append("---SearchFirst---\n");
                            quotes.forEach(value -> {
                                int idx = 1;
                                int found = -1;
                                String sub[] = value.getContent().trim().split("-");
                                for (String s : sub){
                                    if (s.equals(arr[1])){
                                        found = idx;
                                        break;
                                    }
                                    idx++;
                                }
                                 String review = value.parseContent();
                                if (found != -1){
                                    String re = String.format("Quotes (%s, %s, %d )", review, arr[1], found);
                                    result.append(re).append("\n");
                                } else {
                                    String re = String.format("Quotes (%s, %s, Not Found! )", review, arr[1]);
                                    result.append(re).append("\n");
                                }
                            });
                            result.append("------\n");
                        } else if (arr[0].equalsIgnoreCase("SearchSecond")){
                            result.append("---SearchSecond---\n");
                            quotes.forEach(value -> {
                                int found = -1, solan = 0;
                                String sub[] = value.getContent().trim().split("-");
                                int idx = sub.length;
                                for (String s : sub){
                                    if (s.equals(arr[1])){
                                        if (solan == 1){
                                            found = idx;
                                            break;
                                        }
                                        solan++;
                                    }
                                    idx++;
                                }
                                 String review = value.parseContent();
                                if (found != -1){
                                    String re = String.format("Quotes (%s, %s, %d )", review, arr[1], found);
                                    result.append(re).append("\n");
                                } else {
                                    String re = String.format("Quotes (%s, %s, Not Found! )", review, arr[1]);
                                    result.append(re).append("\n");
                                }
                            });
                            result.append("------\n");
                        } else if (arr[0].equalsIgnoreCase("SearchLast")){
                            result.append("---SearchLast---\n");
                            quotes.forEach(value -> {
                                int idx = 1;
                                int found = -1;
                                String sub[] = value.getContent().trim().split("-");
                                for (int i = sub.length-1; i >= 0; i--){
                                    String s = sub[i];
                                    if (s.equals(arr[1])){
                                            found = idx;
                                            break;
                                    }
                                    idx++;
                                }
                                 String review = value.parseContent();
                                if (found != -1){
                                    String re = String.format("Quotes (%s, %s, %d )", review, arr[1], found);
                                    result.append(re).append("\n");
                                } else {
                                    String re = String.format("Quotes (%s, %s, Not Found! )", review, arr[1]);
                                    result.append(re).append("\n");
                                }
                            });
                            result.append("------\n");
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
        Q6 q = new Q6();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
	//--END FIXED PART----------------------------    
}