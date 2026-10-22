import java.io.*;

// ==================================================================================================================================
//  MyQueue.java — Queue (Hang doi) dung Singly Linked List cho CSD201
//  => Dung Object: hoat dong voi moi kieu du lieu.
//  => KHONG dung System.out — tat ca hien thi deu RETURN String.
//  => front: dau hang (lay ra); rear: cuoi hang (them vao).
// ==================================================================================================================================
//
//  [MUC LUC & SEARCH KEYS]
//    ||____PEEK_____   -> [B] front(), rear(), peek()
//    ||____ADD______   -> [B] enqueue(), enqueueMany()
//    ||___DELETE____   -> [B] dequeue()
//    ||___SEARCH____   -> [D] contains(), remove(), countOccurrences()
//    ||___DISPLAY___   -> [C] traverse(), ftraverse()
//    ||___REVERSE___   -> [D] reverse()
//
//    [A] Khoi tao & Co ban  : isEmpty(), size(), length(), clear()
//    [B] Them / Lay phan tu : enqueue(), enqueueMany(), dequeue(), front(), rear(), peek()
//    [C] Duyet / Ghi file   : traverse(), ftraverse()
//    [D] Tim kiem & Tien ich : contains(), remove(), countOccurrences(), toArray(), reverse()
//
//  CAU TRUC NHO:
//    Enqueue: them vao CUOI (rear)   — O(1)
//    Dequeue: lay tu DAU (front)     — O(1)
//    FIFO: First In, First Out
//
// ==================================================================================================================================

public class MyQueue {
    private Node front, rear; // front = dau hang, rear = cuoi hang
    private int length;
    private StringBuilder outputBuffer = new StringBuilder();

    // setOutputBuffer - Cho phhep dung chung outputBuffer voi Q1.java de de dang tich hop
    public void setOutputBuffer(StringBuilder sb) {
        this.outputBuffer = sb;
    }

    // Helper output
    private void print(Object o) { outputBuffer.append(o); }
    private void println(Object o) { outputBuffer.append(o).append("\n"); }

    // ==================================================================
    //  [A] KHOI TAO & CO BAN
    // ==================================================================

    /** Tao Queue rong. */
    public MyQueue() {
        front = rear = null;
        length = 0;
    }

    /** Tra ve true neu Queue rong. */
    public boolean isEmpty() {
        return front == null;
    }

    /** Tra ve so phan tu trong Queue. */
    public int size() { return length; }

    /** [A] Alias cho size(). */
    public int length() { return length; }

    /** Xoa toan bo Queue. */
    public void clear() {
        front = rear = null;
        length = 0;
    }

    // ==================================================================
    //  [B] THEM / LAY PHAN TU
    // ==================================================================

    /**
     * [B1] Enqueue — Them phan tu x vao CUOI hang doi.
     * Goi: queue.enqueue(new Tiger(5, "Sora"))
     */
    public void enqueue(Object x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode; // Node cuoi cu tro toi node moi
            rear = newNode;      // rear = node moi
        }
        length++;
    }

    /** [B1b] EnqueueMany — Them nhieu phan tu vao Queue. */
    public void enqueueMany(Object[] arr) {
        if (arr == null) return;
        for (Object x : arr) enqueue(x);
    }

    /**
     * [B2] Dequeue — Xoa va tra ve phan tu o DAU hang.
     * Tra ve null neu Queue rong.
     * Goi: Object o = queue.dequeue();
     */
    public Object dequeue() {
        if (isEmpty()) return null;
        Object data = front.info;
        if (front == rear) {
            front = rear = null; // Chi con 1 phan tu
        } else {
            front = front.next;  // front dich len 1 buoc
        }
        length--;
        return data;
    }

    /**
     * [B3] Front — Xem phan tu o DAU hang ma KHONG xoa.
     * Tra ve null neu Queue rong.
     * Goi: Object o = queue.front();
     */
    public Object front() {
        if (isEmpty()) return null;
        return front.info;
    }

    /** [B4] Rear — Xem phan tu o CUOI hang ma KHONG xoa. */
    public Object rear() {
        if (isEmpty()) return null;
        return rear.info;
    }

    /** [B3] Alias cho front(). */
    public Object peek() { return front(); }

    // ==================================================================
    //  [C] DUYET / GHI FILE
    // ==================================================================

    /**
     * [C1] Duyet Queue tu DAU den CUOI, tra ve String.
     * Moi phan tu tren 1 dong.
     * Goi: String s = queue.traverse();
     */
    public String traverse() {
        StringBuilder sb = new StringBuilder();
        Node p = front;
        while (p != null) {
            sb.append(p.info.toString());
            if (p.next != null) sb.append("\n");
            p = p.next;
        }
        return sb.toString();
    }

    /**
     * [C2] Ghi toan bo Queue vao RandomAccessFile (tu front den rear).
     * Goi: queue.ftraverse(raf);
     */
    public void ftraverse(RandomAccessFile f) throws IOException {
        Node p = front;
        while (p != null) {
            f.writeBytes(p.info.toString() + "\r\n");
            p = p.next;
        }
    }

    /** [C3] Ghi toan bo Queue vao outputBuffer. */
    public void ftraverse() {
        Node p = front;
        while (p != null) {
            println(p.info.toString());
            p = p.next;
        }
    }

    // ==================================================================
    //  [D] TIM KIEM & TIEN ICH
    // ==================================================================

    /**
     * [D1] Kiem tra Queue co chua phan tu bang x hay khong (dung equals()).
     * Goi: queue.contains(myObject)
     */
    public boolean contains(Object x) {
        Node p = front;
        while (p != null) {
            if (p.info.equals(x)) return true;
            p = p.next;
        }
        return false;
    }

    /** [D1b] Kiem tra Queue co chua phan tu co toString() chua id. */
    public boolean contains(String id) {
        Node p = front;
        while (p != null) {
            if (p.info.toString().contains(id)) return true;
            p = p.next;
        }
        return false;
    }

    /** [D4] Xoa phan tu dau tien co toString() chua id. */
    public boolean remove(String id) {
        if (isEmpty()) return false;
        if (front.info.toString().contains(id)) {
            dequeue();
            return true;
        }
        Node p = front;
        while (p.next != null) {
            if (p.next.info.toString().contains(id)) {
                p.next = p.next.next;
                if (p.next == null) rear = p; // Cap nhat rear neu xoa phan tu cuoi
                length--;
                return true;
            }
            p = p.next;
        }
        return false;
    }

    /** [D5] Dem so lan xuat hien cua x (equals). */
    public int countOccurrences(Object x) {
        int count = 0;
        Node p = front;
        while (p != null) {
            if (p.info.equals(x)) count++;
            p = p.next;
        }
        return count;
    }

    /**
     * [D2] Chuyen Queue thanh mang Object[] (front o index 0).
     * Goi: Object[] arr = queue.toArray();
     */
    public Object[] toArray() {
        Object[] arr = new Object[length];
        Node p = front;
        for (int i = 0; i < length; i++) {
            arr[i] = p.info;
            p = p.next;
        }
        return arr;
    }

    /**
     * [D3] Dao nguoc Queue tai cho (front cu tro thanh rear, rear tro thanh front).
     * Goi: queue.reverse();
     */
    public void reverse() {
        if (isEmpty() || front.next == null) return;
        rear = front;     // Front cu se tro thanh rear moi
        Node prev = null, curr = front;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        front = prev;
    }

    // ==================================================================
    //  [E] TÍNH TOÁN & THỐNG KÊ (MATH & STATS) - Mặc định kiểu Integer
    // ==================================================================
    
    /** Tinh tong tat ca cac phan tu kieu Integer trong Queue. */
    public int sumInteger() {
        int sum = 0;
        Node p = front;
        while (p != null) {
            if (p.info instanceof Integer) {
                sum += (Integer) p.info;
            }
            p = p.next;
        }
        return sum;
    }

    /** Tinh trung binh cong cac phan tu kieu Integer. */
    public double averageInteger() {
        int count = 0;
        int sum = 0;
        Node p = front;
        while (p != null) {
            if (p.info instanceof Integer) {
                sum += (Integer) p.info;
                count++;
            }
            p = p.next;
        }
        return count == 0 ? 0 : (double) sum / count;
    }

    // ==================================================================
    //  [F] TÌM KIẾM NÂNG CAO (ADVANCED SEARCHING)
    // ==================================================================

    /** Tim Node co gia tri Integer max (Khong xoa). */
    public Node findMaxInteger() {
        if (isEmpty()) return null;
        Node maxNode = null;
        Node p = front;
        while (p != null) {
            if (p.info instanceof Integer) {
                if (maxNode == null || (Integer) p.info > (Integer) maxNode.info) {
                    maxNode = p;
                }
            }
            p = p.next;
        }
        return maxNode;
    }

    /** Tim Node co gia tri Integer min (Khong xoa). */
    public Node findMinInteger() {
        if (isEmpty()) return null;
        Node minNode = null;
        Node p = front;
        while (p != null) {
            if (p.info instanceof Integer) {
                if (minNode == null || (Integer) p.info < (Integer) minNode.info) {
                    minNode = p;
                }
            }
            p = p.next;
        }
        return minNode;
    }

    // ==================================================================
    //  [G] XÓA/SỬA CÓ ĐIỀU KIỆN & THAO TÁC NÂNG CAO (PE SPECIFIC)
    // ==================================================================

    /** Dao nguoc K phan tu dau tien cua Queue. (Dung 1 mang hoac stack phu) */
    public void reverseFirstK(int k) {
        if (isEmpty() || k <= 1 || k > length) return;
        Object[] temp = new Object[k];
        for (int i = 0; i < k; i++) {
            temp[i] = dequeue();
        }
        // Dua vao lai tu cuoi mang (tuong duong Stack LIFO)
        for (int i = k - 1; i >= 0; i--) {
            enqueue(temp[i]);
        }
        // Xoay K vong doi voi cac phan tu con lai
        int remaining = length - k;
        for (int i = 0; i < remaining; i++) {
            enqueue(dequeue());
        }
    }

    /** Xoa TAT CA cac phan tu co gia tri lon hon threshold (Giu nguyen thu tu cac phan tu con lai). */
    public void removeAllCondition(int thresholdInteger) {
        int n = length;
        for (int i = 0; i < n; i++) {
            Object curr = dequeue();
            if (curr instanceof Integer) {
                if ((Integer) curr <= thresholdInteger) {
                    enqueue(curr);
                }
            } else {
                enqueue(curr); // Khong phai Integer thi giu nguyen
            }
        }
    }

    /** Lay ra (Dequeue) phan tu thu K (k tinh tu 1 den length) ma giu nguyen cac phan tu khac. */
    public Object dequeueAt(int k) {
        if (k <= 0 || k > length) return null;
        int n = length;
        Object target = null;
        for (int i = 1; i <= n; i++) {
            Object curr = dequeue();
            if (i == k) {
                target = curr;
            } else {
                enqueue(curr);
            }
        }
        return target;
    }

    /** Sap xep Queue theo gia tri Integer tang dan (Chi dung mang tam thoi). */
    public void sortAscendingInteger() {
        if (isEmpty() || front.next == null) return;
        Object[] arr = toArray();
        java.util.Arrays.sort(arr, new java.util.Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    return Integer.compare((Integer) o1, (Integer) o2);
                }
                return 0; // Bo qua neu khong phai Integer
            }
        });
        clear();
        enqueueMany(arr);
    }
}
