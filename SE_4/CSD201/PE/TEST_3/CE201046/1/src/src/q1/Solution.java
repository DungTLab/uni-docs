package q1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Solution {

    // ==== STUDENT VARIABLES - NOI KHAI BAO BIEN CUA BAN ==== KHAI BAO PUBLIC
    public int n;
    public Integer[] list;
    // Chuoi chua ket qua cuoi cung dung de ghi ra file output.txt
    public String resultText = "";

    // ==== STUDENT INPUT - DOC DU LIEU DAU VAO CUA BAN ====
    public void read(Scanner sc) {
        // Kiem tra xem file co phan tu tiep theo hay khong
        if (sc.hasNextInt()) {
            // Doc so luong phan tu n va m
            n = sc.nextInt();
            // Khoi tao ma tran
            list = new Integer[n];
            for (int i = 0; i < n; i++) {
                list[i] = sc.nextInt();
            }

        }
    }

    // ==== STUDENT ALGORITHM - THUAT TOAN CHINH CUA BAN ====
    public void solve() {

        // BUOC 2: Dinh dang chuoi dau ra dung StringBuilder de toi uu hieu nang
        StringBuilder sb = new StringBuilder();
        int isIncreasing = 1;
        for (int i = 1; i < n; i++) {
            if (list[i] < list[i - 1]) {
                isIncreasing = -1;
                break;
            }
        }
        int isDecreasing = 1;
        if (isIncreasing == -1) {

            for (int i = 1; i < n; i++) {
                if (list[i] > list[i - 1]) {
                    isDecreasing = -1;
                    break;
                }
            }
        }
        if (isIncreasing == 1) {
            sb.append("Increasing Sequence");
        } else if (isDecreasing == 1) {
            sb.append("Decreasing Sequence");
        } else {
            sb.append("Normal Sequence");
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
