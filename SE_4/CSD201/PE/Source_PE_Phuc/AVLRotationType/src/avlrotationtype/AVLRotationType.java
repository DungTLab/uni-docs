/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package avlrotationtype;

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
import javax.swing.Spring;

public class AVLRotationType {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    int n;
    int[] arr;
    Node root;
    StringBuilder result;
    String rotation;
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
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = sc.nextInt();
                }
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

        int info, height;
        Node left, right;

        Node(int x) {
            info = x;
            height = 1; // Node mới luôn có chiều cao là 1
            left = right = null;
        }
    }

    public int height(Node p) {
        if (p == null) {
            return 0;
        }
        return p.height;
    }

    public int getBalance(Node p) {
        if (p == null) {
            return 0;
        }
        return height(p.left) - height(p.right);
    }

    public Node leftRotation(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public Node rightRotation(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    public Node insert(Node p, int key) {
        if (p == null) {
            return new Node(key);
        }

        if (key < p.info) {
            p.left = insert(p.left, key);
        } else if (key > p.info) {
            p.right = insert(p.right, key);
        } else {
            return p;
        }

        p.height = 1 + Math.max(height(p.left), height(p.right));

        int balance = getBalance(p);

        if (balance > 1 && key < p.left.info) {
            if (rotation.isEmpty()) {
                rotation = "Right";
            }
            return rightRotation(p);
        } else if (balance > 1 && key > p.left.info) {
            if (rotation.isEmpty()) {
                rotation = "Left-Right";
            }
            p.left = leftRotation(p.left);
            return rightRotation(p);
        } else if (balance < -1 && key > p.right.info) {
            if (rotation.isEmpty()) {
                rotation = "Left";
            }
            return leftRotation(p);
        } else if (balance < -1 && key < p.right.info) {
            if (rotation.isEmpty()) {
                rotation = "Right-Left";
            }
            p.right = rightRotation(p.right);
            return leftRotation(p);
        }
        return p;
    }

    public void preOrder(Node p) {
        if (p == null) {
            return;
        }

        result.append(p.info).append(" ");
        preOrder(p.left);
        preOrder(p.right);
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
        result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            rotation = "";
            root = insert(root, arr[i]);

            if (!rotation.isEmpty()) {
                result.append("Rotation:\r\n").append(rotation).append("\r\n\r\n");
            }
            result.append("After inserting ").append(arr[i]).append(":\r\n");
            preOrder(root);
            result.append("\r\n\r\n");

        }

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
        AVLRotationType q = new AVLRotationType();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
