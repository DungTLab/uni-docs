package list;

import java.io.RandomAccessFile;
import java.util.Stack;

public class MyList {
    Node head, tail;

    public MyList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void clear() {
        head = tail = null;
    }

    // ==========================================
    // 1. CAC HAM INSERT (CHEN)
    // ==========================================

    // Chen vao dau danh sach
    public void addFirst(Item x) {
        Node p = new Node(x);
        if (isEmpty()) {
            head = tail = p;
        } else {
            p.next = head;
            head = p;
        }
    }

    // Chen vao cuoi danh sach
    public void addLast(Item x) {
        Node p = new Node(x);
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    // Chen x sau node p
    public void insertAfter(Node p, Item x) {
        if (p == null) return;
        Node q = new Node(x);
        q.next = p.next;
        p.next = q;
        // Neu p la tail thi cap nhat tail
        if (tail == p) {
            tail = q;
        }
    }

    // Chen vao vi tri index (index bat dau tu 0)
    public void insertAtIndex(int index, Item x) {
        if (index < 0) return;
        if (index == 0) {
            addFirst(x);
            return;
        }
        int count = 0;
        Node p = head;
        while (p != null && count < index - 1) {
            p = p.next;
            count++;
        }
        if (p != null) {
            insertAfter(p, x);
        }
    }

    // ==========================================
    // 2. CAC HAM DELETE (XOA)
    // ==========================================

    public void deleteFirst() {
        if (isEmpty()) return;
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }

    public void deleteLast() {
        if (isEmpty()) return;
        if (head == tail) {
            head = tail = null;
            return;
        }
        Node p = head;
        while (p.next != tail) {
            p = p.next;
        }
        p.next = null;
        tail = p;
    }

    // Xoa 1 node p bat ky
    public void deleteNode(Node p) {
        if (p == null || isEmpty()) return;
        if (head == p) {
            deleteFirst();
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q != null) { // Tim thay p
            q.next = p.next;
            if (p == tail) {
                tail = q;
            }
        }
    }

    // Bien the hay thi: Xoa node THU K thoa man dieu kien (vd: price > 10)
    public void deleteConditionK(int k) {
        int count = 0;
        Node p = head;
        while (p != null) {
            if (p.info.price > 10) { // DIEU KIEN DE BAI
                count++;
                if (count == k) {
                    deleteNode(p);
                    break;
                }
            }
            p = p.next;
        }
    }

    // Xoa node tai vi tri index (index tu 0)
    public void deleteAtIndex(int index) {
        if (index < 0 || isEmpty()) return;
        if (index == 0) {
            deleteFirst();
            return;
        }
        int count = 0;
        Node p = head;
        while (p != null && count < index) {
            p = p.next;
            count++;
        }
        if (p != null) {
            deleteNode(p);
        }
    }

    // ==========================================
    // 3. CAC HAM TIM KIEM / LAY GIA TRI (SEARCH & FIND)
    // ==========================================

    // Tim node dau tien co name trung voi xName
    public Node search(String xName) {
        Node p = head;
        while (p != null) {
            if (p.info.name.equals(xName)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    // Tim node co price max (neu nhieu node cung max, tra ve node DAU TIEN)
    public Node findMax() {
        if (isEmpty()) return null;
        Node p = head;
        Node maxNode = head;
        while (p != null) {
            if (p.info.price > maxNode.info.price) {
                maxNode = p;
            }
            p = p.next;
        }
        return maxNode;
    }

    // Tim node co price min
    public Node findMin() {
        if (isEmpty()) return null;
        Node p = head;
        Node minNode = head;
        while (p != null) {
            if (p.info.price < minNode.info.price) {
                minNode = p;
            }
            p = p.next;
        }
        return minNode;
    }

    // ==========================================
    // 4. CAC HAM SAP XEP (SORT) VA DAO NGUOC (REVERSE)
    // ==========================================

    // Ham lay Node tai vi tri index
    public Node getNode(int index) {
        int count = 0;
        Node p = head;
        while (p != null && count < index) {
            p = p.next;
            count++;
        }
        return p;
    }

    // Ham lay kich thuoc list
    public int size() {
        int count = 0;
        Node p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    // Sap xep tang dan theo price tu index thu start den index thu end (inclusive)
    // Su dung Selection Sort doi cho info chu khong doi pointer
    public void sort(int startIndex, int endIndex) {
        int n = size();
        if (startIndex < 0) startIndex = 0;
        if (endIndex >= n) endIndex = n - 1;
        if (startIndex >= endIndex) return;

        for (int i = startIndex; i < endIndex; i++) {
            for (int j = i + 1; j <= endIndex; j++) {
                Node pi = getNode(i);
                Node pj = getNode(j);
                if (pi.info.price > pj.info.price) { // Tang dan thi > , Giam dan thi <
                    // Swap data
                    Item temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
            }
        }
    }

    // Dao nguoc thu tu danh sach tu vi tri startIndex den endIndex
    // Dung Stack de doi cho data an toan nhat, tranh dut con tro
    public void reverse(int startIndex, int endIndex) {
        int n = size();
        if (startIndex < 0) startIndex = 0;
        if (endIndex >= n) endIndex = n - 1;
        if (startIndex >= endIndex) return;

        Stack<Item> stack = new Stack<>();
        Node p = head;
        int count = 0;
        while (p != null) {
            if (count >= startIndex && count <= endIndex) {
                stack.push(p.info);
            }
            p = p.next;
            count++;
        }

        p = head;
        count = 0;
        while (p != null) {
            if (count >= startIndex && count <= endIndex) {
                p.info = stack.pop();
            }
            p = p.next;
            count++;
        }
    }
    

    // ==========================================
    // 5. BO TRO: HAM GHI FILE KHI DI THI
    // ==========================================
    
    // Ghi thong tin 1 node vao file (Ham nay thuong cho san)
    public void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) f.writeBytes(p.info.toString() + " ");
    }

    // Duyet list va ghi vao file (Ham nay thuong cho san)
    public void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f);
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    // ==========================================
    // 6. CAC HAM THUONG GAP TRONG PE (PE EXAM REQUIREMENTS)
    // ==========================================

    // Chen x vao truoc node p
    public void insertBefore(Node p, Item x) {
        if (p == null || isEmpty()) return;
        if (head == p) {
            addFirst(x);
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q != null) {
            Node newNode = new Node(x);
            newNode.next = p;
            q.next = newNode;
        }
    }

    // Xoa node ngay sau node co gia tri max
    public void deleteNodeAfterMax() {
        if (isEmpty() || head == tail) return;
        Node maxNode = findMax();
        if (maxNode != null && maxNode.next != null) {
            deleteNode(maxNode.next);
        }
    }

    // Tim node co price lon thu 2
    public Node findSecondMax() {
        if (isEmpty() || head.next == null) return null;
        Node max1 = head;
        Node max2 = null;
        Node p = head.next;
        while (p != null) {
            if (p.info.price > max1.info.price) {
                max2 = max1;
                max1 = p;
            } else if (p.info.price < max1.info.price && (max2 == null || p.info.price > max2.info.price)) {
                max2 = p;
            }
            p = p.next;
        }
        return max2;
    }

    // Tim index cua node co price max (neu nhieu max thi lay node dau tien)
    public int findMaxIndex() {
        if (isEmpty()) return -1;
        Node p = head;
        int maxPrice = head.info.price;
        int index = 0;
        int maxIndex = 0;
        while (p != null) {
            if (p.info.price > maxPrice) {
                maxPrice = p.info.price;
                maxIndex = index;
            }
            p = p.next;
            index++;
        }
        return maxIndex;
    }

    // Tim index cua node co price min (neu nhieu min thi lay node dau tien)
    public int findMinIndex() {
        if (isEmpty()) return -1;
        Node p = head;
        int minPrice = head.info.price;
        int index = 0;
        int minIndex = 0;
        while (p != null) {
            if (p.info.price < minPrice) {
                minPrice = p.info.price;
                minIndex = index;
            }
            p = p.next;
            index++;
        }
        return minIndex;
    }

    // Thay doi thong tin node (Vd: Node dau tien co price > 10 thi name = "XX")
    public void replaceCondition() {
        Node p = head;
        while (p != null) {
            if (p.info.price > 10) {
                p.info.name = "XX";
                break; // Chi thay the node dau tien
            }
            p = p.next;
        }
    }

    // Xoa cac phan tu trung lap (giu lai phan tu dau tien)
    public void removeDuplicates() {
        if (isEmpty()) return;
        Node p = head;
        while (p != null) {
            Node q = p;
            while (q.next != null) {
                if (q.next.info.price == p.info.price) { // Thay dieu kien trung lap o day (vd: price bang nhau)
                    q.next = q.next.next;
                    if (q.next == null) tail = q;
                } else {
                    q = q.next;
                }
            }
            p = p.next;
        }
    }

    // Duyet va tra ve String khong in ra man hinh
    public String traverseToString() {
        StringBuilder sb = new StringBuilder();
        Node p = head;
        while (p != null) {
            sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
            p = p.next;
        }
        return sb.toString().trim();
    }

    // Duyet va tra ve String cac phan tu thoa man dieu kien (VD: x.price > threshold)
    public String traverseConditionToString(java.util.function.Predicate<Item> condition) {
        StringBuilder sb = new StringBuilder();
        Node p = head;
        while (p != null) {
            if (condition.test(p.info)) {
                sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
            }
            p = p.next;
        }
        return sb.toString().trim();
    }

    // ==========================================
    // 7. THUAT TOAN NANG CAO (ADVANCED ALGORITHMS)
    // ==========================================

    // Tim diem giua cua danh sach lien ket (Fast/Slow Pointers)
    public Node findMiddle() {
        if (isEmpty()) return null;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Phat hien chu trinh trong danh sach (Floyd's Cycle-Finding Algorithm)
    public boolean hasCycle() {
        if (isEmpty()) return false;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
