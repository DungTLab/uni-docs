/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkavltree;

/**
 *
 * @author LENOVO
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CheckAVLTree {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    int n;
    int[] arr;
    int[] valArr;
    int[] leftArr;
    int[] rightArr;
    Node root;
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
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                valArr = new int[n];
                leftArr = new int[n];
                rightArr = new int[n];
                for (int i = 0; i < n; i++) {
                    valArr[i] = sc.nextInt();
                    leftArr[i] = sc.nextInt();
                    rightArr[i] = sc.nextInt();
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

        int info;
        Node left, right;

        Node(int x) {
            info = x;
            left = right = null;
        }
    }

    public void buildTree() {
        java.util.HashMap<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(valArr[i])) {
                map.put(valArr[i], new Node(valArr[i]));
            }
            Node p = map.get(valArr[i]);

            if (i == 0) {
                root = p;
            }

            if (leftArr[i] != -1) {
                if (!map.containsKey(leftArr[i])) {
                    map.put(leftArr[i], new Node(leftArr[i]));
                }
                p.left = map.get(leftArr[i]);
            }

            if (rightArr[i] != -1) {
                if (!map.containsKey(rightArr[i])) {
                    map.put(rightArr[i], new Node(rightArr[i]));
                }
                p.right = map.get(rightArr[i]);
            }
        }
    }

    public boolean isBST(Node p, int min, int max) {
        if (p == null) {
            return true;
        }

        if (p.info <= min || p.info >= max) {
            return false;
        }
        return isBST(p.left, min, p.info) && isBST(p.right, p.info, max);

    }

    public int getHeight(Node p) {
        if (p == null) {
            return 0;
        }

        return 1 + Math.max(getHeight(p.left), getHeight(p.right));
    }

    public boolean isBalance(Node p) {
        if (p == null) {
            return true;
        }

        int leftHeight = getHeight(p.left);
        int rightHeight = getHeight(p.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        return isBalance(p.left) && isBalance(p.right);
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

        buildTree();
        boolean check = isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        boolean checkAVL = check && isBalance(root);

        result.append("AVL Tree:\r\n");
        if (checkAVL) {
            result.append("Yes");
        } else {
            result.append("No");
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
        CheckAVLTree q = new CheckAVLTree();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
