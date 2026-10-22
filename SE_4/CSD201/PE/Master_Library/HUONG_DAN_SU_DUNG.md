# HƯỚNG DẪN SỬ DỤNG MASTER LIBRARY CSD201

Chào mừng bạn đến với **Master Library** cho môn học CSD201 (Cấu trúc dữ liệu và giải thuật). File này hướng dẫn chi tiết về cấu trúc và cách sử dụng các tài nguyên trong thư viện này.

Thư mục này tổng hợp toàn bộ các cấu trúc dữ liệu, các thuật toán mẫu (templates) và các phương thức nâng cao thường xuyên xuất hiện trong các đề thi thực hành (PE) của môn CSD201. Thay vì phải tự viết lại từ đầu, bạn có thể tham khảo và tái sử dụng mã nguồn tại đây.

---

## 📂 Cấu trúc thư mục

Thư viện được chia thành các thư mục tương ứng với từng loại cấu trúc dữ liệu và thuật toán:

### 1. `Advanced_Generic_Templates`
- **Mô tả:** Chứa các cấu trúc dữ liệu được viết dưới dạng **Generic** (`<T>`), cho phép lưu trữ và xử lý đa dạng các kiểu đối tượng thay vì bị gò bó vào một class cố định.
- **Thành phần:** `BSTree` (Cây), `Graph` (Đồ thị), `MyList` (Danh sách liên kết), `MyQueue` (Hàng đợi), `MyStack` (Ngăn xếp), v.v.

### 2. `tree`
- **Mô tả:** Triển khai **Cây nhị phân tìm kiếm (Binary Search Tree - BSTree)** cơ bản.
- **Thành phần:** Các thao tác cơ bản như thêm node (insert), xóa node (remove by copy/merge), duyệt cây (preOrder, inOrder, postOrder, BFS), tìm kiếm, tính chiều cao cây, xoay cây (rotate right/left), v.v.

### 3. `graph`
- **Mô tả:** Triển khai **Đồ thị (Graph)** sử dụng ma trận kề (adjacency matrix).
- **Thành phần:** Các thuật toán duyệt đồ thị theo chiều rộng (BFS), chiều sâu (DFS), và thuật toán tìm đường đi ngắn nhất Dijkstra.

### 4. `list`
- **Mô tả:** Triển khai **Danh sách liên kết đơn (Singly Linked List)**.
- **Thành phần:** Các thao tác chèn (addFirst, addLast, insertAt), xóa (removeFirst, removeNode), tìm kiếm, và sắp xếp các phần tử trong danh sách liên kết.

### 5. `stack_queue`
- **Mô tả:** Triển khai **Ngăn xếp (Stack)** và **Hàng đợi (Queue)** cơ bản.
- **Thành phần:** Sử dụng danh sách liên kết để triển khai các thao tác cơ bản như `push()`, `pop()`, `enqueue()`, `dequeue()`.

### 6. `heap`
- **Mô tả:** Triển khai cấu trúc **Max Heap**.
- **Thành phần:** Hữu ích cho các bài toán yêu cầu cấu trúc hàng đợi ưu tiên (Priority Queue).

### 7. `sort`
- **Mô tả:** Chứa file `BasicSortTemplates.java` tổng hợp các **thuật toán sắp xếp** phổ biến.
- **Thành phần:** Bubble Sort, Selection Sort, Insertion Sort, Quick Sort, Merge Sort, v.v.

### 8. `Q1.java` & `Q1_CSD.txt`
- **Mô tả:** Mẫu code chuẩn bị sẵn cho dạng bài xử lý chuỗi và đọc/ghi file (thường rơi vào **Câu 1** của đề thi).
- **Thành phần:** Cách đọc cấu trúc dữ liệu từ file `input.txt`, phân tích lệnh (Command parser) qua câu lệnh `switch-case`, và xuất kết quả ra `output.txt`.

---

## 🛠️ Hướng dẫn sử dụng trong bài thi (PE)

1. **Xác định yêu cầu đề bài:** Đọc kỹ đề để xem bài toán yêu cầu sử dụng cấu trúc dữ liệu nào (Linked List, BST, Graph...).
2. **Tìm kiếm mã nguồn:** Mở thư mục tương ứng trong `Master_Library`. Nếu đề bài yêu cầu lưu trữ nhiều loại đối tượng, hãy ưu tiên dùng mã nguồn trong `Advanced_Generic_Templates`.
3. **Sao chép và tùy chỉnh:**
   - Copy các phương thức (methods) hoặc lớp (classes) cần thiết vào Project làm bài của bạn.
   - **Lưu ý quan trọng:** Đổi tên lớp đối tượng (ví dụ từ `Item`, `Node` thành `Student`, `Car` hoặc `Book`) sao cho khớp tuyệt đối với yêu cầu của đề thi.
   - Chỉnh sửa lại các điều kiện so sánh (nếu có) tùy theo logic của bài (ví dụ so sánh theo `ID` hay so sánh theo `Price`).
4. **Kiểm thử (Testing):** Sử dụng các file `main()` hoặc file mẫu có sẵn trong đề thi để chạy thử và đối chiếu `output` với đề bài.

---

## 📚 Tổng hợp Đề thi cũ (old_exams)

Ngoài thư viện mẫu, dự án còn cung cấp thư mục `old_exams` chứa các đề thi thực hành từ các kỳ trước, giúp bạn ôn luyện và làm quen với cấu trúc đề.

### 1. Các đề có sẵn mã nguồn (Source code)
- **Exam 1:** Gồm 3 phần riêng biệt: `material_0` (Linked List), `material_1` (BSTree), `material_2` (Graph).
- **Exam 2:** Gộp chung 2 cấu trúc dữ liệu vào 1 project (`material_0`), kiểm tra kỹ năng thao tác trên Circular Linked List và BST.
- **Exam 3:** Tương tự Exam 1, có `Material_1` (Linked List), `Material_2` (BSTree), `Material_3` (Graph).
- **Exam 5:** Đề thi gộp chứa `CompletionQueue` (Hàng đợi) và `TaskBST` (Cây nhị phân tìm kiếm phân loại theo mức độ ưu tiên).
- **Exam 7:** Đề thi gộp sử dụng `ActionLogStack` (Ngăn xếp) và `OrderBST` (Cây tìm kiếm dùng chuỗi ID để so sánh).

### 2. Các đề chỉ có hình ảnh đề bài
- **Exam 4, Exam 6, Exam 8:** Các thư mục này không cung cấp mã nguồn khởi tạo mà chỉ chứa file ảnh (`.webp`, `.png`) chụp lại yêu cầu của đề thi. Bạn có thể sử dụng các class trong `Master_Library` để tự giải quyết các yêu cầu trong hình.

### 3. Thư mục `Exercises`
- Chứa các file bài tập dạng `.docx` (Word) hướng dẫn chi tiết từng dạng (List, Tree, Graph, HeapSort...).
- Cung cấp sẵn các bộ **TestCases** (`in.txt`, `out.txt`) để bạn tự kiểm tra tính đúng đắn của code do mình viết ra cho các câu hỏi Q1, Q2.

---

> **💡 Mẹo nhỏ:** Hãy sử dụng tính năng tìm kiếm (Ctrl + F hoặc Ctrl + Shift + F) trong thư mục `Master_Library` để nhanh chóng tìm thấy tên thuật toán mà đề bài yêu cầu (VD: *Dijkstra*, *Euler*, *Rotate*).
