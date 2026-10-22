/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binarysearchtreemanager;

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

public class BinarySearchTreeManager {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    int n;
    String[] s;
    Node root;
    StringBuilder result;
    StringBuilder preResult;
    StringBuilder inResult;
    StringBuilder postResult;
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
                sc.nextLine();
                s = new String[n];
                for (int i = 0; i < n; i++) {
                    s[i] = sc.nextLine().trim();
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

        String info;
        Node left, right;

        Node(String x) {
            info = x;
            left = right = null;
        }
    }

    private Node insert(Node p, String x) {
        if (p == null) {
            return new Node(x);
        }
        int cmp = x.compareTo(p.info);
        if (cmp < 0) {
            p.left = insert(p.left, x);
        } else if (cmp > 0) {
            p.right = insert(p.right, x);
        }
        return p;
    }

    private Node search(Node p, String x) {
        if (p == null || p.info.equals(x)) {
            return p;
        }
        if (x.compareTo(p.info) < 0) {
            return search(p.left, x);
        }
        return search(p.right, x);
    }

    // Hàm Delete (Delete by Copy Max Left) dùng String
    private Node delete(Node p, String key) {
        if (p == null) {
            return p;
        }

        int cmp = key.compareTo(p.info);
        if (cmp < 0) {
            p.left = delete(p.left, key);
        } else if (cmp > 0) {
            p.right = delete(p.right, key);
        } else {
            if (p.left == null || p.right == null) {
                Node temp = (p.left != null) ? p.left : p.right;
                if (temp == null) {
                    p = null;
                } else {
                    p = temp;
                }
            } else {
                Node temp = p.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                p.info = temp.info;
                p.left = delete(p.left, temp.info);
            }
        }
        return p;
    }

    public int getHeight(Node p) {
        if (p == null) {
            return -1;
        }
        return 1 + Math.max(getHeight(p.left), getHeight(p.right));
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
        inOrder(p.left);
        inResult.append(p.info).append(" ");
        inOrder(p.right);
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
        result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String cmd = s[i];

            if (cmd.startsWith("I ")) {
                String x = cmd.substring(2).trim();
                root = insert(root, x);
            } else if (cmd.startsWith("D ")) {
                String x = cmd.substring(2).trim();
                root = delete(root, x);
                result.append("After deleting ").append(x).append(":\r\n");
                preResult = new StringBuilder();
                preOrder(root);
                result.append(preResult.toString().trim()).append("\r\n\r\n");
            } else if (cmd.startsWith("S ")) {
                String x = cmd.substring(2).trim();
                Node found = search(root, x);
                result.append("Search ").append(x).append(":\r\n");
                if (found != null) {
                    result.append("Found\r\n\r\n");
                } else {
                    result.append("Not Found\r\n\r\n");
                }
            } else if (cmd.equals("H")) {
                result.append("Height:\r\n").append(getHeight(root)).append("\r\n\r\n");
            } else if (cmd.equals("IN")) {
                inResult = new StringBuilder();
                inOrder(root);
                result.append("Inorder:\r\n").append(inResult.toString().trim()).append("\r\n\r\n");
            } else if (cmd.equals("PRE")) {
                preResult = new StringBuilder();
                preOrder(root);
                result.append("Preorder:\r\n").append(preResult.toString().trim()).append("\r\n\r\n");
            } else if (cmd.equals("POST")) {
                postResult = new StringBuilder();
                postOrder(root);
                result.append("Postorder:\r\n").append(postResult.toString().trim()).append("\r\n\r\n");
            }
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
        BinarySearchTreeManager q = new BinarySearchTreeManager();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
