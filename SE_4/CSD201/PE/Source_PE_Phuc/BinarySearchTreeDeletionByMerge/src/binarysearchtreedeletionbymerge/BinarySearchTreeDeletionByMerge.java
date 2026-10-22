/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binarysearchtreedeletionbymerge;

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

public class BinarySearchTreeDeletionByMerge {

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

    public void insert(int x) {
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

    public void preOrder(Node p) {
        if (p == null) {
            return;
        }
        result.append(p.info).append(" ");
        preOrder(p.left);
        preOrder(p.right);
    }

    public void deleteByMerge(int x) {
        Node p = root;
        Node f = null;

        if (p == null) {
            return;
        }

        // 1. CHỈ TÌM NODE CẦN XÓA
        while (p != null && p.info != x) {
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        } // <--- CHÚ Ý: ĐÓNG NGOẶC VÒNG LẶP WHILE Ở ĐÂY!

        // Nếu đi hết cây mà vẫn không tìm thấy thì thoát
        if (p == null) {
            return;
        }

        // 2. BẮT ĐẦU TIẾN HÀNH XÓA NODE P
        // Trường hợp p có đủ 2 con
        if (p.left != null && p.right != null) {

            // Đi tìm node lớn nhất của nhánh trái
            Node rightMost = p.left;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }

            // Gắn toàn bộ nhánh phải của p vào node rightMost vừa tìm được
            rightMost.right = p.right;

            // Kéo nhánh trái của p lên thế chỗ cho p
            if (f == null) {
                root = p.left; // Nếu xóa Root
            } else if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
            return; // Đã xử lý xong, thoát hàm
        }

        // Trường hợp p có 1 con hoặc không có con nào
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
        for (int i = 0; i < m; i++) {
            deleteByMerge(deleteArr[i]);
            result.append("After deleting ").append(deleteArr[i]).append(":\r\n");
            // GỌI PREORDER THAY VÌ INORDER
            preOrder(root);
            result.append("\r\n\r\n"); // Thêm dòng trống giữa các lần xóa
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
        BinarySearchTreeDeletionByMerge q = new BinarySearchTreeDeletionByMerge();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
