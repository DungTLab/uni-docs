/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binarytreeanalyzer;

/**
 *
 * @author LENOVO
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTreeAnalyzer {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    int n;
    int[] arr;
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
                arr = new int[n];
                leftArr = new int[n];
                rightArr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = sc.nextInt();
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
        java.util.HashMap<Integer, Node> map = new java.util.HashMap<>();

        for (int i = 0; i < n; i++) {
            // Khởi tạo Node hiện tại nếu chưa có trong map
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new Node(arr[i]));
            }
            Node p = map.get(arr[i]);

            // Node đầu tiên trong dữ liệu vào luôn là Gốc
            if (i == 0) {
                root = p;
            }

            // Xử lý nhánh trái
            if (leftArr[i] != -1) {
                if (!map.containsKey(leftArr[i])) {
                    map.put(leftArr[i], new Node(leftArr[i]));
                }
                p.left = map.get(leftArr[i]);
            }

            // Xử lý nhánh phải
            if (rightArr[i] != -1) {
                if (!map.containsKey(rightArr[i])) {
                    map.put(rightArr[i], new Node(rightArr[i]));
                }
                p.right = map.get(rightArr[i]);
            }
        }
    }

    public int getHeight(Node p) {
        if (p == null) {
            return -1;
        }
        return 1 + Math.max(getHeight(p.left), getHeight(p.right));
    }

    public int getSize(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + getSize(p.left) + getSize(p.right);
    }

    public int countLeaf(Node p) {
        if (p == null) {
            return 0;
        } else if (p.left == null && p.right == null) {
            return 1;
        } else {
            return countLeaf(p.left) + countLeaf(p.right);
        }
    }

    public int getMaxNode(Node p) {
        if (p == null) {
            return Integer.MIN_VALUE;
        }
        int maxLeft = getMaxNode(p.left);
        int maxRight = getMaxNode(p.right);

        return Math.max(p.info, Math.max(maxLeft, maxRight));
    }

    public int getMinNode(Node p) {
        if (p == null) {
            return Integer.MAX_VALUE;
        }

        int minLeft = getMinNode(p.left);
        int minRight = getMinNode(p.right);

        return Math.min(p.info, Math.min(minLeft, minRight));
    }

    public void levelOrder(Node p) {
        if (p == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(p);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            result.append(temp.info).append(" ");

            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
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

        buildTree();
        result.append("Height:\r\n").append(getHeight(root)).append("\r\n\r\n");
        result.append("Size:\r\n").append(getSize(root)).append("\r\n\r\n");
        result.append("Leaves:\r\n").append(countLeaf(root)).append("\r\n\r\n");
        result.append("Maximum:\r\n").append(getMaxNode(root)).append("\r\n\r\n");
        result.append("Minimum:\r\n").append(getMinNode(root)).append("\r\n\r\n");

        result.append("Level-order:\r\n");
        levelOrder(root);

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
        BinaryTreeAnalyzer q = new BinaryTreeAnalyzer();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
