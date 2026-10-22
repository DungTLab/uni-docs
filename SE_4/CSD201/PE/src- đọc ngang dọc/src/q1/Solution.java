package q1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Solution {

    // ==== STUDENT VARIABLES - NOI KHAI BAO BIEN CUA BAN ==== KHAI BAO PUBLIC
    public int n, m;
    Integer[][] matrix;
    // Chuoi chua ket qua cuoi cung dung de ghi ra file output.txt
    public String resultText = "";

    // ==== STUDENT INPUT - DOC DU LIEU DAU VAO CUA BAN ====
    public void read(Scanner sc) {
        // Kiem tra xem file co phan tu tiep theo hay khong
        if (sc.hasNextInt()) {
            // Doc so luong phan tu n va m
            n = sc.nextInt();
            m = sc.nextInt();
            // Khoi tao ma tran
            matrix = new Integer[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
            // Doc cac dinh va cap nhat vao ma tran
            while (sc.hasNextInt()) {
                int x=sc.nextInt()-1;
                int y = sc.nextInt()-1;
                matrix[x][y]++;
                matrix[y][x]++;
            }
        }
    }

    // ==== STUDENT ALGORITHM - THUAT TOAN CHINH CUA BAN ====
    public void solve() {

        // BUOC 2: Dinh dang chuoi dau ra dung StringBuilder de toi uu hieu nang
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(matrix[i][j]);
                if (j < n - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
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
