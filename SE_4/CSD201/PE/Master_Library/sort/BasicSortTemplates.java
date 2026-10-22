package q1;

import java.util.Arrays;

/**
 * Các template thuật toán sắp xếp (Mảng số nguyên tăng dần).
 * Có hỗ trợ in từng bước để phục vụ việc làm bài thi (PE CSD201).
 */
public class BasicSortTemplates {

    private static StringBuilder outputBuffer = new StringBuilder();

    public static String getOutput() {
        return outputBuffer.toString();
    }

    public static void clearOutput() {
        outputBuffer.setLength(0);
    }

    /**
     * Hàm hỗ trợ lưu mảng ở một bước cụ thể vào buffer.
     * @param a mảng cần lưu
     * @param currentStep bước hiện tại của thuật toán
     * @param targetStep bước muốn lưu (truyền -1 nếu muốn lưu TẤT CẢ các bước)
     * @param message thông báo đi kèm
     */
    public static void printStep(int[] a, int currentStep, int targetStep, String message) {
        if (targetStep == -1 || targetStep == currentStep) {
            outputBuffer.append(message).append("Partition ").append(currentStep).append(":");
            for (int x : a) {
                outputBuffer.append(x).append(" ");
            }
            outputBuffer.append("\n");
            outputBuffer.append("\n");
        }
    }

    // =========================================================
    // 1. SELECTION SORT (Sắp xếp chọn)
    // =========================================================
    public static void selectionSort(int[] a, int targetStep) {
        int n = a.length;
        int step = 1;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            // Tìm phần tử nhỏ nhất trong phần chưa sắp xếp
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            // Hoán vị
            int temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;

            // --- ĐIỂM IN BƯỚC ---
            printStep(a, step++, targetStep, "Selection Sort - Sau vong " + (i + 1));
        }
    }

    // =========================================================
    // 2. BUBBLE SORT (Sắp xếp nổi bọt)
    // =========================================================
    public static void bubbleSort(int[] a, int targetStep) {
        int n = a.length;
        int step = 1;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
            // --- ĐIỂM IN BƯỚC ---
            printStep(a, step++, targetStep, "Bubble Sort - Sau vong " + (i + 1));
            
            // Tối ưu: Nếu vòng lặp không có sự đổi chỗ nào => mảng đã sắp xếp xong
            if (!swapped) break; 
        }
    }

    // =========================================================
    // 3. INSERTION SORT (Sắp xếp chèn)
    // =========================================================
    public static void insertionSort(int[] a, int targetStep) {
        int n = a.length;
        int step = 1;
        for (int i = 1; i < n; ++i) {
            int key = a[i];
            int j = i - 1;

            // Di chuyển các phần tử lớn hơn key về sau 1 vị trí
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[j + 1] = key;

            // --- ĐIỂM IN BƯỚC ---
            printStep(a, step++, targetStep, "Insertion Sort - Sau khi chen phan tu o vi tri " + i);
        }
    }

    // =========================================================
    // 4. QUICK SORT (Sắp xếp nhanh)
    // =========================================================
    public static void quickSort(int[] a, int targetStep) {
        int[] stepCount = {1}; // Dùng mảng 1 phần tử để pass by reference qua đệ quy
        quickSortRec(a, 0, a.length - 1, targetStep, stepCount);
    }

    private static void quickSortRec(int[] a, int low, int high, int targetStep, int[] stepCount) {
        if (low < high) {
            int pi = partition(a, low, high);
            
            // --- ĐIỂM IN BƯỚC ---
            printStep(a, stepCount[0]++, targetStep, "");
            
            quickSortRec(a, low, pi - 1, targetStep, stepCount);
            quickSortRec(a, pi + 1, high, targetStep, stepCount);
        }
    }

    // Phân hoạch Lomuto (Chọn phần tử CUỐI CÙNG làm pivot)
    private static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = (low - 1); // Index của phần tử nhỏ hơn
        for (int j = low; j < high; j++) {
            if (a[j] < pivot) {
                i++;
                // Swap a[i] và a[j]
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        // Swap a[i+1] và pivot (a[high])
        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1;
    }

    // =========================================================
    // 5. MERGE SORT (Sắp xếp trộn)
    // =========================================================
    public static void mergeSort(int[] a, int targetStep) {
        int[] stepCount = {1};
        mergeSortRec(a, 0, a.length - 1, targetStep, stepCount);
    }

    private static void mergeSortRec(int[] a, int left, int right, int targetStep, int[] stepCount) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortRec(a, left, mid, targetStep, stepCount);
            mergeSortRec(a, mid + 1, right, targetStep, stepCount);
            
            merge(a, left, mid, right);
            
            // --- ĐIỂM IN BƯỚC ---
            printStep(a, stepCount[0]++, targetStep, "Merge Sort - Sau khi tron [left=" + left + ", mid=" + mid + ", right=" + right + "]");
        }
    }

    private static void merge(int[] a, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = a[left + i];
        for (int j = 0; j < n2; ++j) R[j] = a[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }

    // =========================================================
    // 6. HEAP SORT (Sắp xếp vun đống)
    // =========================================================
    public static void heapSort(int[] a, int targetStep) {
        int n = a.length;
        int step = 1;

        // Xây dựng Max-Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(a, n, i);
        }
        printStep(a, step++, targetStep, "Heap Sort - Sau khi build Max-Heap lan dau");

        // Rút từng phần tử
        for (int i = n - 1; i > 0; i--) {
            // Đưa root (lớn nhất) về cuối mảng
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            // Vun đống lại mảng phần còn lại
            heapify(a, i, 0);

            // --- ĐIỂM IN BƯỚC ---
            printStep(a, step++, targetStep, "Heap Sort - Sau khi rut phan tu Max va heapify lai");
        }
    }

    private static void heapify(int[] a, int n, int i) {
        int largest = i;
        int l = 2 * i + 1; // Left child
        int r = 2 * i + 2; // Right child

        if (l < n && a[l] > a[largest]) largest = l;
        if (r < n && a[r] > a[largest]) largest = r;

        if (largest != i) {
            int swap = a[i];
            a[i] = a[largest];
            a[largest] = swap;
            heapify(a, n, largest);
        }
    }

    // =========================================================
    // 7. RADIX SORT (Sắp xếp cơ số - hệ cơ số 10)
    // =========================================================
    public static void radixSort(int[] a, int targetStep) {
        if (a.length == 0) return;
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) max = a[i];
        }

        int step = 1;
        // Thực hiện counting sort cho từng chữ số
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(a, a.length, exp);
            
            // --- ĐIỂM IN BƯỚC ---
            printStep(a, step++, targetStep, "Radix Sort - Sau khi sap xep theo chu so " + exp);
        }
    }

    private static void countSort(int[] a, int n, int exp) {
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) count[(a[i] / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        
        for (int i = n - 1; i >= 0; i--) {
            output[count[(a[i] / exp) % 10] - 1] = a[i];
            count[(a[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            a[i] = output[i];
        }
    }

}
