/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SinglyLinkedlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SinglyLinkedList {

    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Các con trỏ quản lý danh sách
    private Node head = null;
    private Node tail = null;
    private String resultStr = "";

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
            if (sc.hasNextLine()) {
                String[] elements = sc.nextLine().trim().split("\\s+");
                for (String e : elements) {
                    if (!e.isEmpty()) {
                        addLast(Integer.parseInt(e)); // Gọi trực tiếp hàm addLast
                    }
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
    public boolean isEmpty() {
        return head == null;
    }

    public Integer first() {
        return isEmpty() ? null : head.data;
    }

    public Integer last() {
        return isEmpty() ? null : tail.data;
    }

    public void addFirst(int e) {
        Node newNode = new Node(e);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void addLast(int e) {
        Node newNode = new Node(e);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public Integer removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int res = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return res;
    }

    // Đếm số node (tương đương size, không dùng biến đếm ngoài)
    public int countNodes() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // ==========================================
    // CÁC HÀM NÂNG CAO
    // ==========================================
    // In danh sách liên kết
    public String printList() {
        if (isEmpty()) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            sb.append(temp.data).append(" -> ");
            temp = temp.next;
        }
        sb.append("null");
        return sb.toString();
    }

    // Tìm phần tử lớn nhất
    public Integer findMax() {
        if (isEmpty()) {
            return null;
        }
        int max = head.data;
        Node temp = head.next;
        while (temp != null) {
            if (temp.data > max) {
                max = temp.data;
            }
            temp = temp.next;
        }
        return max;
    }

    // Tìm node giữa list (Dùng slow pointer & fast pointer)
    public Integer findMiddle() {
        if (isEmpty()) {
            return null;
        }
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Chạy 1 bước
            fast = fast.next.next;     // Chạy 2 bước
        }
        return slow.data;
    }

    // Đảo ngược linked list (Không tạo list mới)
    public void reverse() {
        Node prev = null, current = head, next = null;
        tail = head;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    // Xóa tất cả các node có giá trị x
    public void remove(int x) {
        while (head != null && head.data == x) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        }
        Node current = head;
        while (current != null && current.next != null) {
            if (current.next.data == x) {
                current.next = current.next.next;
                if (current.next == null) {
                    tail = current;
                }
            } else {
                current = current.next;
            }
        }
    }

    // Xóa tất cả node trùng lặp
    public void removeAllDuplicates() {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                    if (runner.next == null) {
                        tail = runner;
                    }
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    // Kiểm tra palindrome (Dùng find middle + reverse nửa sau)
    public boolean isPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node prev = null, current = slow, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        Node left = head;
        Node right = prev;
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    // Phát hiện cycle (Floyd Cycle Detection)
    public boolean hasCycle() {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    //--FIXED PART - DO NOT EDIT ANY THINGS HERE--
    //--START FIXED PART--------------------------    
    /**
     * Main algorithm
     */
    public void solve() {
        //--END FIXED PART----------------------------

        //ALGORITHM - @STUDENT: ADD YOUR CODE HERE:
        //ALGORITHM - @STUDENT: ADD YOUR CODE HERE:
        
        // Ví dụ: Đề yêu cầu xóa các phần tử trùng lặp rồi in ra
        // removeAllDuplicates();
        // finalOutput = printList();
        
        // Ví dụ: Tìm Max
        // finalOutput = String.valueOf(findMax());
        
        // Ví dụ: Kiểm tra Palindrome
        // boolean check = isPalindrome();
        // finalOutput = check ? "true" : "false";

        // Mặc định in ra để test thử:
        resultStr = printList();
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
            fw.write(resultStr);

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
        SinglyLinkedList q = new SinglyLinkedList();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
    //--END FIXED PART----------------------------    
}
