package binarysearchtreeheightandcountnode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchTreeHeightAndCountNode {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    int n;
    int[] arr;
    Node root;
    StringBuilder result;
    java.util.ArrayList<Integer> bfsList;

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

        int info;
        Node left, right;

        Node(int x) {
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

    public int getHeight(Node p) {
        if (p == null) {
            return -1;
        }
        return 1 + Math.max(getHeight(p.left), getHeight(p.right));
    }

    public int countLeaf(Node p) { // or leaf
        if (p == null) {
            return 0;
        } else if (p.left == null && p.right == null) {
            return 1;
        } else {
            return countLeaf(p.left) + countLeaf(p.right);
        }
    }

    public int countInternalNode(Node p) {
        if (p == null) {
            return 0;
        } else if (p.left == null && p.right == null) {
            return 1;
        } else {
            return 1 + countInternalNode(p.left) + countInternalNode(p.right);
        }
    }

    public int countFullNode(Node p) {
        if (p == null) {
            return 0;
        }

        int count = (p.left != null && p.right != null) ? 1 : 0;
        return count + countFullNode(p.left) + countFullNode(p.right);
    }

    public int countSingleChildNode(Node p) {
        if (p == null) {
            return 0;
        }

        int count = (((p.left != null && p.right != null) || p.left == null && p.right == null)) ? 1 : 0;
        return count + countSingleChildNode(p.left) + countSingleChildNode(p.right);
    }

    public void bfs() {
        if (root == null) {
            return;
        }
        java.util.Queue<Node> q = new java.util.LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node p = q.poll();
            bfsList.add(p.info);
            if (p.left != null) {
                q.add(p.left);
            }
            if (p.right != null) {
                q.add(p.right);
            }
        }
    }

    public boolean dfs(Node p, int target) {
        if (p == null) {
            return false;
        } else if (p.info == target) {
            return true;
        }
        return dfs(p.left, target) || dfs(p.right, target);
    }

    private int getLevel(Node p, int target, int currentLevel) {
        if (p == null) {
            return 0;
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

    // Hàm thực hiện tạo cấu trúc cây gương
    public void mirror(Node p) {
        if (p == null) {
            return;
        }
        Node temp = p.left;
        p.left = p.right;
        p.right = temp;
        mirror(p.left);
        mirror(p.right);
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
        bfsList = new java.util.ArrayList<>();

        // Xây dựng cây
        for (int i = 0; i < n; i++) {
            insert(arr[i]);
        }

        // Gắn các thông số vào chuỗi kết quả
        result.append("Height:\r\n").append(getHeight(root)).append("\r\n");
        result.append("Leaf Nodes:\r\n").append(countLeaf(root)).append("\r\n");
        result.append("Internal Nodes:\r\n").append(countInternalNode(root)).append("\r\n");
        result.append("Full Nodes:\r\n").append(countFullNode(root)).append("\r\n");
        result.append("Single-child Nodes:\r\n").append(countSingleChildNode(root)).append("\r\n");

        // Xử lý Before Mirror
        bfs();
        result.append("Before Mirror:\r\n");
        for (int i = 0; i < bfsList.size(); i++) {
            result.append(bfsList.get(i)).append(i < bfsList.size() - 1 ? " " : "");
        }
        result.append("\r\n");

        // Gọi hàm đảo cây thực sự
        mirror(root);

        // Xử lý After Mirror (In mảng BFS theo chiều ngược)
        result.append("After Mirror:\r\n");
        for (int i = bfsList.size() - 1; i >= 0; i--) {
            result.append(bfsList.get(i)).append(i > 0 ? " " : "");
        }

        // Gọi hàm DFS
        boolean isFound = dfs(root, 15);

        // Gọi hàm lấy Level (Gốc bắt đầu từ mức 1)
        int levelOfNode = getLevel(root, 15, 1);
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
        BinarySearchTreeHeightAndCountNode q = new BinarySearchTreeHeightAndCountNode();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
