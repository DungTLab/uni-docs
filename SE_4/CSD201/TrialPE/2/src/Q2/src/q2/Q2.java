package q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Q2 {
    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    StringBuilder outputBuffer = new StringBuilder();
    
    // For INT Array type input
    int n;
    int[] arr;
    
    // For String COMMAND type input
    ArrayList<String> commands = new ArrayList<>();
    BSTree bstree = new BSTree();


    // Output helper methods
    public void print(Object o) {
        outputBuffer.append(o);
    }

    public void println(Object o) {
        outputBuffer.append(o).append("\n");
    }

    public void println() {
        outputBuffer.append("\n");
    }

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
            
            // --- TEMPLATE 1: READ INTEGER ARRAY ---
            
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                arr = new int[n];
                for (int i = 0; i < n; i++) {
                    if (sc.hasNextInt()) {
                        bstree.insert(sc.nextInt());
                    }
                }
            }
            
            
            // --- TEMPLATE 2: READ STRING COMMANDS ---
            /*
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    commands.add(line);
                }
            }
            */

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
        
        // Example for COMMAND processing:
        /*
        for (String cmd : commands) {
            String[] parts = cmd.split(" ");
            String key = parts[0];
            switch (key) {
                case "C":
                    // Do something
                    break;
            }
        }
        */
        
        outputBuffer.append(bstree.postOrder(bstree.root) + "\n");
        bstree.countLeaves();
        outputBuffer.append(bstree.leaf);

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
            fw.write(outputBuffer.toString().trim());

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
        Q2 q = new Q2();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
	//--END FIXED PART----------------------------    
}
