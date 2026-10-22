package heap;

public class MaxHeap {
    int[] a;
    int size;
    int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.a = new int[capacity];
    }

    // ==========================================
    // 1. HAM TINH INDEX (PARENT, LEFT, RIGHT)
    // ==========================================
    
    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    // ==========================================
    // 2. KIEM TRA MANG CO PHAI LA HEAP KHONG
    // ==========================================

    // Kiem tra mang co phai Max Heap khong
    public static boolean isMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            // Neu con trai lon hon cha -> False
            if (2 * i + 1 < n && arr[2 * i + 1] > arr[i]) {
                return false;
            }
            // Neu con phai lon hon cha -> False
            if (2 * i + 2 < n && arr[2 * i + 2] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    // Kiem tra mang co phai Min Heap khong
    public static boolean isMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            // Neu con trai nho hon cha -> False
            if (2 * i + 1 < n && arr[2 * i + 1] < arr[i]) {
                return false;
            }
            // Neu con phai nho hon cha -> False
            if (2 * i + 2 < n && arr[2 * i + 2] < arr[i]) {
                return false;
            }
        }
        return true;
    }

    // ==========================================
    // 3. THAO TAC TREN MAX HEAP (INSERT & REMOVE)
    // ==========================================

    // Ham doi cho
    private void swap(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Upheap (Bubble-up) dung sau khi Insert
    private void upHeap(int i) {
        while (i > 0 && a[parent(i)] < a[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Downheap (Bubble-down) dung sau khi RemoveMax hoac dung de Build Heap
    private void downHeap(int i, int n) {
        int largest = i;
        int l = left(i);
        int r = right(i);

        // Neu con trai lon hon cha
        if (l < n && a[l] > a[largest]) {
            largest = l;
        }

        // Neu con phai lon hon the hien tai cua largest
        if (r < n && a[r] > a[largest]) {
            largest = r;
        }

        // Neu largest khong phai la cha
        if (largest != i) {
            swap(i, largest);
            downHeap(largest, n); // Tiep tuc downheap
        }
    }

    // Upheap (Bubble-up) dung sau khi Insert vao Min Heap
    private void upHeapMin(int i) {
        while (i > 0 && a[parent(i)] > a[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Downheap (Bubble-down) dung cho Min Heap
    private void downHeapMin(int i, int n) {
        int smallest = i;
        int l = left(i);
        int r = right(i);

        // Neu con trai nho hon cha
        if (l < n && a[l] < a[smallest]) {
            smallest = l;
        }

        // Neu con phai nho hon the hien tai cua smallest
        if (r < n && a[r] < a[smallest]) {
            smallest = r;
        }

        // Neu smallest khong phai la cha
        if (smallest != i) {
            swap(i, smallest);
            downHeapMin(smallest, n); // Tiep tuc downheap
        }
    }

    // Chen phan tu vao Max Heap
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }
        // Dua vao cuoi mang
        a[size] = value;
        // Upheap
        upHeap(size);
        size++;
    }

    // Xoa phan tu Root (Gia tri Max)
    public int removeMax() {
        if (size <= 0) {
            return Integer.MIN_VALUE; // Mang rong
        }
        if (size == 1) {
            size--;
            return a[0];
        }

        // Luu lai gia tri root (gia tri Max)
        int root = a[0];
        // Dua phan tu cuoi len lam root
        a[0] = a[size - 1];
        size--;
        // Downheap tu root
        downHeap(0, size);

        return root;
    }

    // Chen phan tu vao Min Heap
    public void insertMin(int value) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }
        // Dua vao cuoi mang
        a[size] = value;
        // Upheap
        upHeapMin(size);
        size++;
    }

    // Lay phan tu nho nhat (khong xoa)
    // Neu la Min Heap, phan tu nho nhat la root: a[0] (O(1))
    // Neu la Max Heap, phan tu nho nhat nam o cac la (O(N))
    public int getMin() {
        if (size <= 0) {
            System.out.println("Heap is empty");
            return Integer.MIN_VALUE;
        }
        
        // Kiem tra xem mang co dang thoa man tinh chat Min Heap khong
        if (isCurrentMinHeap()) {
            return a[0];
        }
        
        // Neu khong phai Min Heap (dang la Max Heap), tim phan tu nho nhat trong cac leaf nodes
        int startLeaf = size / 2;
        int minVal = a[startLeaf];
        for (int i = startLeaf + 1; i < size; i++) {
            if (a[i] < minVal) {
                minVal = a[i];
            }
        }
        return minVal;
    }

    // Xoa phan tu Root cua Min Heap (Gia tri Min)
    public int removeMin() {
        if (size <= 0) {
            System.out.println("Heap is empty");
            return Integer.MIN_VALUE; // Mang rong
        }
        if (size == 1) {
            size--;
            return a[0];
        }

        // Luu lai gia tri root (gia tri Min)
        int root = a[0];
        // Dua phan tu cuoi len lam root
        a[0] = a[size - 1];
        size--;
        // Downheap tu root
        downHeapMin(0, size);

        return root;
    }

    // Ham phu tro kiem tra phan tu hien tai trong heap co phai minheap khong
    private boolean isCurrentMinHeap() {
        for (int i = 0; i <= (size - 2) / 2; i++) {
            if (2 * i + 1 < size && a[2 * i + 1] < a[i]) {
                return false;
            }
            if (2 * i + 2 < size && a[2 * i + 2] < a[i]) {
                return false;
            }
        }
        return true;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    // ==========================================
    // 4. XAY DUNG HEAP TREN MANG CHO SAN (BOTTOM-UP)
    // ==========================================

    // Ham dung max heap tu mang bat ky voi do phuc tap O(N)
    public void buildHeapBottomUp(int[] arr) {
        this.size = arr.length;
        if (this.capacity < size) {
            this.a = new int[size];
            this.capacity = size;
        }
        for (int i = 0; i < size; i++) {
            this.a[i] = arr[i];
        }

        // Bat dau downheap tu node cha cuoi cung ve root
        int startIdx = (size / 2) - 1;
        for (int i = startIdx; i >= 0; i--) {
            downHeap(i, size);
        }
    }

    // Ham dung min heap tu mang bat ky voi do phuc tap O(N)
    public void buildMinHeapBottomUp(int[] arr) {
        this.size = arr.length;
        if (this.capacity < size) {
            this.a = new int[size];
            this.capacity = size;
        }
        for (int i = 0; i < size; i++) {
            this.a[i] = arr[i];
        }

        // Bat dau downheap tu node cha cuoi cung ve root theo kieu Min Heap
        int startIdx = (size / 2) - 1;
        for (int i = startIdx; i >= 0; i--) {
            downHeapMin(i, size);
        }
    }

    // ==========================================
    // 5. HEAP SORT (SAP XEP TANG DAN)
    // ==========================================

    // Thuat toan Heap Sort: Sap xep mang tang dan bang Max Heap
    public static void heapSort(int[] arr) {
        int n = arr.length;
        MaxHeap mh = new MaxHeap(n);
        
        // 1. Xay dung Max Heap
        mh.buildHeapBottomUp(arr);

        // 2. Chuyen dan phan tu Max xuong cuoi mang
        for (int i = n - 1; i > 0; i--) {
            // Doi cho root (Max hien tai) xuong vi tri cuoi i
            mh.swap(0, i);
            
            // Goi downheap cho root voi kich thuoc mang da giam i
            mh.downHeap(0, i);
        }

        // Copy nguoc vao mang goc
        for (int i = 0; i < n; i++) {
            arr[i] = mh.a[i];
        }
    }

    // ==========================================
    // 6. TRA VE STRING KHI KHONG IN RA MAN HINH
    // (GUARANTEED TO ONLY RETURN STRING, NO PRINTING)
    // ==========================================

    public String heapToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(a[i]).append(" ");
        }
        return sb.toString().trim();
    }
}
