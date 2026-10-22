/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binarysearchtreeanalyzenode;

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

public class BinarySearchTreeAnalyzeNode {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    int n;
    int[] arr;
    Node root;
    StringBuilder result;
    int m;
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
                return;
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

    public Node findNode(Node p, int x) {
        if (p == null || p.info == x) {
            return p;
        }
        if (x < p.info) {
            return findNode(p.left, x);
        }
        return findNode(p.right, x);
    }

    // 2. Tìm Node cha của giá trị x
    public Node findParent(Node root, int x) {
        if (root == null || root.info == x) {
            return null;
        }
        Node parent = null;
        Node curr = root;
        while (curr != null) {
            if (x < curr.info) {
                if (curr.left != null && curr.left.info == x) {
                    parent = curr;
                    break;
                }
                curr = curr.left;
            } else {
                if (curr.right != null && curr.right.info == x) {
                    parent = curr;
                    break;
                }
                curr = curr.right;
            }
        }
        return parent;
    }

    // 3. Tính Level (Độ sâu) của Node x kể từ Gốc (Gốc có level = 0)
    public int getLevel(Node p, int target, int currentLevel) {
        if (p == null) {
            return -1;
        }
        if (p.info == target) {
            return currentLevel;
        }
        if (target < p.info) {
            return getLevel(p.left, target, currentLevel + 1);
        } else {
            return getLevel(p.right, target, currentLevel + 1);
        }
    }

    // 4. Tính Chiều cao của một Node cụ thể (Node lá có chiều cao = 0)
    public int getNodeHeight(Node p) {
        if (p == null) {
            return -1;
        }
        if (p.left == null && p.right == null) {
            return 0;
        }
        return 1 + Math.max(getNodeHeight(p.left), getNodeHeight(p.right));
    }

    // 5. Tính kích thước cây con (Subtree size) gốc p
    public int getSubtreeSize(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + getSubtreeSize(p.left) + getSubtreeSize(p.right);
    }

    // 6. Xác định Node Type
    public String getNodeType(Node root, Node target) {
        if (target == root) {
            return "Root";
        }
        if (target.left == null && target.right == null) {
            return "Leaf Node";
        }
        return "Internal Node";
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
            insert(arr[i]);
        }

        Node target = findNode(root, m);

        if (target == null) {
            result.append("Node not found\r\n");
        } else {
            result.append("Node:\r\n").append(target.info).append("\r\n\r\n");

            Node parent = findParent(root, m);
            result.append("Parent:\r\n").append(parent == null ? "NULL" : parent.info).append("\r\n\r\n");

            result.append("Left Child:\r\n").append(target.left == null ? "NULL" : target.left.info).append("\r\n\r\n");
            result.append("Right Child:\r\n").append(target.right == null ? "NULL" : target.right.info).append("\r\n\r\n");

            result.append("Level:\r\n").append(getLevel(root, m, 0)).append("\r\n\r\n");
            result.append("Height:\r\n").append(getNodeHeight(target)).append("\r\n\r\n");
            result.append("Subtree Size:\r\n").append(getSubtreeSize(target)).append("\r\n\r\n");
            result.append("Node Type:\r\n").append(getNodeType(root, target));
        }
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
        BinarySearchTreeAnalyzeNode q = new BinarySearchTreeAnalyzeNode();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
