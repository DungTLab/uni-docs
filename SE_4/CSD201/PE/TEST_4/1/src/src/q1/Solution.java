package q1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

    // ==== STUDENT VARIABLES - NOI KHAI BAO BIEN CUA BAN ==== KHAI BAO PUBLIC
        public int n;
    public Stack<Integer> stack = new Stack<>();
    public String line;
    public String resultText = "";
    public ArrayList<String> commands = new ArrayList<>();

    // ==== STUDENT INPUT - DOC DU LIEU DAU VAO CUA BAN ====
    public void read(Scanner sc) {
        // Kiem tra xem file co phan tu tiep theo hay khong
        if (sc.hasNextInt()) {
            n = Integer.parseInt(sc.nextLine().trim());
            for (int i = 0; i < n && sc.hasNextLine(); i++) {
                line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    commands.add(line);
                }
            }

        }
    }

    // ==== STUDENT ALGORITHM - THUAT TOAN CHINH CUA BAN ====
    public void solve() {
        for (String cmdLine : commands) {
            String[] parts = cmdLine.split(" ");
            String cmd = parts[0];
            switch (cmd) {
                case "PUSH":
                    int number = Integer.parseInt(parts[1]);
                    stack.push(number);
                    break;
                case "POP":
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    break;
                case "PEEK":
                    // PEEK does not print, just processes if needed.
                    break;
            }
        }
        
        // Build the comma-separated string of the stack elements from top to bottom
        StringBuilder sb = new StringBuilder();
        for (int i = stack.size() - 1; i >= 0; i--) {
            sb.append(stack.get(i));
            if (i > 0) {
                sb.append(",");
            }
        }
        // Luu ket qua dinh dang duoc vao bien resultText
        resultText = sb.toString();
    }

    // ==== STUDENT OUTPUT - GHI KET QUA RA FILE ====
    public void printResult(FileWriter fw) throws IOException {
        // Ghi truc tiep chuoi resultText ra file thong qua FileWriter
        fw.write(resultText);
    }

    // ==== STUDENT METHODS - BAN CO THE TU VIET THEM CAC HAM BO TRO DUOI NAY ====
    // Vi du: public void myHelperMethod() { ... }
}
