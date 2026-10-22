/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package expressiontree;

/**
 *
 * @author LENOVO
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ExpressionTree {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    String s;
    Node root;
    StringBuilder preResult;
    StringBuilder inResult;
    StringBuilder postResult;
    StringBuilder result;
    //--FIXED PART - DO NOT EDIT ANY THINGS HERE--
    //--START FIXED PART--------------------------    
    String fi, fo;

    /**
     * Set input and output file for automatic rating
     *
     * @param args path of input file and path of output file
     */
    public void setFile(String[] args) {
        fi = args.length >= 2 ? args[0] : inputFile;
        fo = args.length >= 2 ? args[1] : outputFile;
    }

    /**
     * Reads data from input file
     */
    public void read() {
        try (Scanner sc = new Scanner(new File(fi))) {
            //--END FIXED PART----------------------------

            //INPUT - @STUDENT: ADD YOUR CODE FOR INPUT HERE:
            if (sc.hasNext()) {
                s = sc.next().trim();
            }
            //--FIXED PART - DO NOT EDIT ANY THINGS HERE--
            //--START FIXED PART--------------------------    
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Input Exception # " + ex);
        }
    }
    //--END FIXED PART----------------------------

    //ALGORITHM - @STUDENT: ADD YOUROWN METHODS HERE (IF NEED):
    class Node {

        char info;
        Node left, right;

        Node(char c) {
            info = c;
            left = right = null;
        }
    }

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public void buildTree() {
        Stack<Node> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!isOperator(c)) {
                stack.push(new Node(c));
            } else {
                Node t = new Node(c);
                t.right = stack.pop();
                t.left = stack.pop();
                stack.push(t);
            }
        }
        root = stack.pop();
    }

    public void preOrder(Node p) {
        if (p == null) {
            return;
        }

        preResult.append(p.info).append(" ");
        preOrder(p.left);
        preOrder(p.right);
    }

    public void inOrder(Node p) {
        if (p == null) {
            return;
        }
        if (isOperator(p.info)) {
            inResult.append("(");
        }

        inOrder(p.left);
        inResult.append(p.info).append(" ");
        inOrder(p.right);

        if (isOperator(p.info)) {
            inResult.append(")");
        }
    }

    public void postOrder(Node p) {
        if (p == null) {
            return;
        }

        postOrder(p.left);
        postOrder(p.right);
        postResult.append(p.info).append(" ");
    }

    //--FIXED PART - DO NOT EDIT ANY THINGS HERE--
    //--START FIXED PART--------------------------    
    /**
     * Main algorithm
     */
    public void solve() {
        //--END FIXED PART----------------------------

        //ALGORITHM - @STUDENT: ADD YOUR CODE HERE:
        root = null;
        preResult = new StringBuilder();
        inResult = new StringBuilder();
        postResult = new StringBuilder();
        result = new StringBuilder();

        buildTree();

        preOrder(root);
        inOrder(root);
        postOrder(root);

        result.append("Prefix:\r\n").append(preResult.toString().trim()).append("\r\n\r\n");
        result.append("Infix:\r\n").append(inResult.toString().trim()).append("\r\n\r\n");
        result.append("Postfix:\r\n").append(postResult.toString().trim());
        //--FIXED PART - DO NOT EDIT ANY THINGS HERE--
        //--START FIXED PART-------------------------- 
    }

    /**
     * Write result into output file
     */
    public void printResult() {
        try {
            FileWriter fw = new FileWriter(fo);
            //--END FIXED PART----------------------------

            //OUTPUT - @STUDENT: ADD YOUR CODE FOR OUTPUT HERE:
            fw.write(result.toString().trim() + "\r\n");

            //--FIXED PART - DO NOT EDIT ANY THINGS HERE--
            //--START FIXED PART-------------------------- 
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            System.out.println("Output Exception # " + ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ExpressionTree q = new ExpressionTree();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
