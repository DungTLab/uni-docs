/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binarysearchtreecountsearchstep;

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

public class BinarySearchTreeCountSearchStep {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    int n, m;
    int[] arr;
    int[] searchArr;
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
                arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = sc.nextInt();
                }

                m = sc.nextInt();
                searchArr = new int[m];
                for (int i = 0; i < m; i++) {
                    searchArr[i] = sc.nextInt();
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
        Node left;
        Node right;

        public Node() {
        }

        public Node(int x) {
            info = x;
            left = right = null;
        }
    }

    void insert(int x) {
        if (root == null) {
            root = new Node(x);
            return;
        }
        Node p = root;
        Node f = null;
        while (p != null) {
            if (p.info == x) {
                return; // ignore duplicates
            }
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x < f.info) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }
    }

    void searchAndCount(int x) {
        int steps = 0;
        Node p = root;
        boolean found = false;

        while (p != null) {
            steps++; // Tăng số bước mỗi khi thăm một node
            if (p.info == x) {
                found = true;
                break;
            }
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        result.append("Search ").append(x).append(":\r\n");
        if (found) {
            result.append("Found\r\n");
        } else {
            result.append("Not Found\r\n");
        }
        result.append("Steps: ").append(steps).append("\r\n");
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

        // Build cây BST
        for (int i = 0; i < n; i++) {
            insert(arr[i]);
        }

        // Thực hiện tìm kiếm và ghi kết quả
        for (int i = 0; i < m; i++) {
            searchAndCount(searchArr[i]);
            if (i < m - 1) {
                result.append("\r\n"); // Thêm một dòng trống giữa các kết quả tìm kiếm (theo chuẩn format mẫu)
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
        BinarySearchTreeCountSearchStep q = new BinarySearchTreeCountSearchStep();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
