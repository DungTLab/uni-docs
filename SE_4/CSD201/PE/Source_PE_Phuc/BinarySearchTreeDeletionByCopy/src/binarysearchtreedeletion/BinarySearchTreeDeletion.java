/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtreedeletion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchTreeDeletion {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    int n, m;
    int[] arr;
    int[] deleteArr;
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
                deleteArr = new int[m];
                for (int i = 0; i < m; i++) {
                    deleteArr[i] = sc.nextInt();
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

    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        result.append(p.info).append(" ");
        inOrder(p.right);
    }

    void deleteByCopyMaxLeft(int x) {
        Node p = root;
        Node f = null;

        if (p == null) {
            return;
        }

        // tìm node cần xóa
        while (p != null && p.info != x) {
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        // Case 1: Node có 2 con
        if (p.left != null && p.right != null) {
            Node q = p.left; // nhảy sang nhánh trái
            Node fq = p;
            // tìm node lớn nhất của nhánh trái ( đi từ trái qua phải )
            while (q.right != null) {
                fq = q;
                q = q.right;
            }
            // copy giá trị
            p.info = q.info;
            // chuyển giá trị xuống node q
            p = q;
            f = fq;
        }

        // Case 2: Node có 1 con hoặc không có con nào
        Node child = (p.left != null) ? p.left : p.right;
        if (f == null) {
            root = child; // xóa ngay root
        } else if (f.left == p) {
            f.left = child;
        } else {
            f.right = child;
        }

    }

    void deleteByCopyMinRight(int x) {
        Node p = root;
        Node f = null;

        if (p == null) {
            return;
        }

        // tìm node cần xóa
        while (p != null && p.info != x) {
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        // Case 1: Node có 2 con
        if (p.left != null && p.right != null) {
            Node q = p.right; // Nhảy sang nhánh phải
            Node fq = p;      // Node cha của q

            // Tìm node nhỏ nhất của nhánh phải (đi mãi sang trái)
            while (q.left != null) {
                fq = q;
                q = q.left;
            }

            // Copy giá trị từ node q (Min nhánh phải) lên node p (Node cần xóa)
            p.info = q.info;

            // Chuyển mục tiêu xóa xuống node q
            p = q;
            f = fq;
        }

        // Case 2: Node có 1 con hoặc không có con nào
        Node child = (p.left != null) ? p.left : p.right;
        if (f == null) {
            root = child;
        } else if (f.left == p) {
            f.left = child;
        } else {
            f.right = child;
        }
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

        // Build cây
        for (int i = 0; i < n; i++) {
            insert(arr[i]);
        }

        // Xóa lần lượt và lưu kết quả Inorder
        for (int i = 0; i < m; i++) {
            deleteByCopyMaxLeft(deleteArr[i]); // tùy đề bài yêu câu lấy max left hay min right
            result.append("After deleting ").append(deleteArr[i]).append(":\r\n");
            inOrder(root);
            result.append("\r\n\r\n"); // Xuống dòng chuẩn Windows sau mỗi Inorder
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
        BinarySearchTreeDeletion q = new BinarySearchTreeDeletion();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
