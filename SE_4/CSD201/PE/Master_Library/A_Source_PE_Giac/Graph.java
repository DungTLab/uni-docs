// ==================================================================================================================================
//  Graph.java — Do thi bieu dien bang MA TRAN KE (Adjacency Matrix) cho CSD201
//  => I/O pattern: Scanner doc file, StringBuilder + FileWriter ghi file (giong Q1 template).
//  => Ma tran ke a[i][j]: gia tri 0 = khong canh; gia tri khac 0 = trong so canh (i, j).
//  => Dinh duoc danh so tu 0 den n-1; ten dinh la v[i] = 'A', 'B', 'C', ...
//  => NO_EDGE = 99: ky hieu khong co canh trong input; DIST_INF moi la vo cuc khoang cach.
// ==================================================================================================================================
//
//  [CACH SU DUNG]
//    Graph g = new Graph();
//    g.loadData(0);                        // Doc ma tran ke tu file input.txt
//    g.loadEdgeList(0, true);              // Doc danh sach canh (n m + m dong canh)
//    g.fBFS_full(0);                       // BFS ghi vao outputBuffer
//    g.fDijkstraSteps(0);                  // In bang Dijkstra tung buoc
//    g.checkEulerCycle();                  // In dieu kien Euler chi tiet
//
//  [MUC LUC - CTRL+F tim nhanh theo so]
//
//    I.   I/O VA HIEN THI
//          1.  loadData()         - Doc ma tran ke tu file dau vao fi
//          1b. loadEdgeList()     - Doc danh sach canh tu file dau vao fi
//          1c. loadData(Scanner)  - Doc truc tiep tu doi tuong Scanner [MOI]
//          1d. loadEdgeList(Sc)   - Doc danh sach canh truc tiep tu Scanner [MOI]
//          2.  displayAdj()       - Hien thi ma tran ke (tra ve String)
//          3.  fdispAdj()         - Ghi ma tran ke ra file (co "n = ...")
//          3b. fdispAdjRaw()      - Ghi ma tran ke thuan tuy (khong tieu de) [MOI]
//          4.  fvisit()           - Ghi ten dinh ra file
//          5.  fvisitDeg()        - Ghi ten dinh kem bac ra file
//
//    II.  BFS - DUYET THEO CHIEU RONG (Queue)
//          6.  BFS()              - BFS tu 1 dinh, tra ve String
//          7.  BFS() overload     - BFS voi visited[] tu ngoai truyen vao
//          8.  fBFS()             - BFS ghi ra file
//          9.  fBFS() overload    - BFS voi visited[] tu ngoai, ghi ra file
//          10. BFS_full()         - BFS duyet tat ca thanh phan lien thong
//          11. fBFS_full()        - BFS full ghi ra file
//          12. fBFS_conditional   - BFS co dieu kien (gioi han so dinh, bat dau tu vi tri)
//          12b.fBFS_dual()        - BFS voi 2 mang visited[]+enqueued[] (format Co Vy) [MOI]
//          12c.fBFS_dual_full()   - BFS dual duyet tat ca TPLT [MOI]
//
//    III. DFS - DUYET THEO CHIEU SAU (Stack / De quy)
//          13. DFS()              - DFS de quy tu 1 dinh, tra ve String
//          14. DFS() overload     - DFS voi visited[] tu ngoai
//          15. fDFS()             - DFS de quy ghi ra file
//          16. fDFS() overload    - DFS voi visited[], ghi ra file
//          17. DFS_full()         - DFS duyet tat ca thanh phan lien thong
//          18. fDFS_full()        - DFS full ghi ra file
//          19. DFS_iterative()    - DFS dung Stack (khong de quy)
//
//    IV.  BAC CUA DINH (DEGREE)
//          20. calcDegree()       - Bac cua 1 dinh (vo huong)
//          21. calcAllDegrees()   - Tinh bac tat ca cac dinh, luu vao deg[]
//          22. getDegreesString() - In bac tat ca cac dinh ra String
//
//    V.   DIJKSTRA - DUONG DI NGAN NHAT
//          23. dijkstra(src,dest) - Tim duong ngan nhat, tra ve String day du
//          24. dijkstra(src)      - Dijkstra tu src den tat ca cac dinh (mang dist)
//          25. dijkstraPath()     - Lay List cac dinh tren duong di ngan nhat
//          26. dijkstraFullInfo() - Ghi day du thong tin Dijkstra (bang chon S) ra file
//          26b.fDijkstraSteps()   - In BANG TRANG THAI d[] sau moi buoc [MOI - PHO BIEN NHAT]
//          26c.fDijkstraDistances() - In khoang cach tich luy: "0 9 11 13" [MOI]
//          26d.fDijkstraFormatted() - In format "(0)A -> (9)C -> (11)F" [MOI]
//
//    VI.  LIEN THONG (CONNECTIVITY)
//          27. isConnected()               - Kiem tra do thi lien thong
//          27b. isTree()                   - Kiem tra do thi co phai la cay khong
//          27c. bellmanFord(src)           - Tim duong di ngan nhat co the co trong so am
//          28. countConnectedComponents()  - Dem TPLT bang BFS
//          29. getConnectedComponents()    - Lay List cac TPLT
//          29b.countConnectedComponentsDFS() - Dem TPLT bang Stack/DFS [MOI]
//          29c.fPrintComponents()          - In cac TPLT kem nhan dinh ra file [MOI]
//
//    VII. CAY KHUNG NHO NHAT (MST)
//          30. Prim()    - Thuat toan Prim (tra ve String)
//          31. Kruskal() - Thuat toan Kruskal (Union-Find, tra ve String)
//
//    VIII.EULER PATH / CIRCUIT
//          32. hasEulerPath()    - Kiem tra co duong di Euler
//          33. hasEulerCircuit() - Kiem tra co chu trinh Euler
//          33b.checkEulerCycle() - In TUNG DIEU KIEN Euler ra file [MOI - PHO BIEN]
//          33c.hasIsolated()     - Kiem tra co dinh co lap [MOI]
//          34. findEulerPath()   - Tim duong di Euler (Hierholzer)
//
//    IX.  TOPOLOGICAL SORT (DAG)
//          35. topologicalSort() - Sap xep topo (thuat toan Kahn/BFS)
//
//    X.   PHAT HIEN CHU TRINH
//          36. hasCycle() - Kiem tra co chu trinh (Union-Find / Kahn)
//
//    XI.  DUONG DI NGAN NHAT BO SUNG
//          37. shortestPathBFS()    - BFS dem so canh (khong trong so)
//          38. floydWarshall()      - Ma tran khoang cach all-pairs
//              floydWarshallString() - Phien ban in ra String
//
//    XII. TIEN ICH (EXAM UTILITY)
//          - fBFS_withDeg(), fDFS_withDeg() - Duyet in kem bac dinh
//          - DFS_conditional()              - DFS co dieu kien
//          - fPrim(), fKruskal()            - MST ghi ra file
//          - fDijkstra(), fDijkstra_exam()  - Dijkstra ghi ra file (format de thi)
//          - isDirected()                   - Kiem tra co huong
//          - isUndirected()                 - Kiem tra vo huong [MOI]
//          - isEvenDegree()                 - Tat ca dinh bac chan [MOI]
//          - countEdges(), isAdjacent()     - Dem canh, kiem tra ke nhau
//          - getLabel(), getIndex()         - Chuyen doi nhan-index
//          - getGraphInfo()                 - Tong hop thong tin do thi
//
//    XIII.f1, f2 THEO FORMAT DE THI
//          - Ham mau: f1() BFS/DFS + xu ly them, f2() Dijkstra/MST
//
//    XIV. THIET LAP DO THI THU CONG
//          - setGraph(), addEdge(), addDirectedEdge(), removeEdge(), initEmpty()
//
// ========================================================================================================================================

import java.io.*;
import java.util.*;

public class Graph {

    int[][] a;      // Ma tran ke (co trong so)
    int n;          // So dinh
    char[] v;       // Nhan cac dinh: A, B, C, D, ...
    int[] deg;      // Bac cua moi dinh
    boolean directed; // Luu ro loai do thi; khong suy luan lai tu ma tran o moi lan goi

    // 99 la ky hieu "khong co canh" cua de thi, KHONG phai vo cuc khoang cach.
    static final int NO_EDGE = 99;
    static final int DIST_INF = Integer.MAX_VALUE;
    @Deprecated
    static final int INF = NO_EDGE; // Giu ten cu de source code ben ngoai van bien dich

    String fi = "input.txt"; // Ten file dau vao (thay theo de thi)
    String fo = "output.txt"; // Ten file dau ra (thay theo de thi)
    StringBuilder outputBuffer = new StringBuilder();

    // setOutputBuffer - Cho phep dung chung outputBuffer voi Q1.java de de dang tich hop
    void setOutputBuffer(StringBuilder sb) {
        this.outputBuffer = Objects.requireNonNull(sb, "output buffer");
    }

    // getOutput - Lay toan bo noi dung tu outputBuffer duoi dang String
    String getOutput() {
        return outputBuffer.toString();
    }

    // clearOutput - Xoa sach noi dung cua outputBuffer
    void clearOutput() {
        outputBuffer.setLength(0);
    }

    // Helper output - dung nhu System.out nhung ghi vao outputBuffer
    void print(Object o) {
        outputBuffer.append(o);
    }

    void println(Object o) {
        outputBuffer.append(o).append("\n");
    }

    void println() {
        outputBuffer.append("\n");
    }

    // setFile - doi ten file theo tham so truyen vao (format de thi)
    void setFile(String[] args) {
        if (args != null && args.length >= 2) {
            fi = args[0];
            fo = args[1];
        }
    }

    // printResult - Ghi outputBuffer ra file dau ra
    void printResult() {
        try (FileWriter fw = new FileWriter(fo)) {
            // Khong trim: de thi co the cham ca dong trong va khoang trang cuoi dong.
            fw.write(outputBuffer.toString());
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot write output file: " + fo, e);
        }
    }

    private static char[] defaultLabels(int size) {
        char[] labels = new char[size];
        for (int i = 0; i < size; i++) {
            labels[i] = (char) ('A' + i);
        }
        return labels;
    }

    private void initStorage(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Number of vertices must be >= 0: " + size);
        }
        n = size;
        a = new int[n][n];
        deg = new int[n];
        v = defaultLabels(n);
    }

    private static void validateMatrix(int[][] matrix, int size) {
        if (matrix == null) {
            throw new IllegalArgumentException("Adjacency matrix must not be null");
        }
        if (size < 0 || matrix.length < size) {
            throw new IllegalArgumentException("Matrix does not contain " + size + " rows");
        }
        for (int i = 0; i < size; i++) {
            if (matrix[i] == null || matrix[i].length < size) {
                throw new IllegalArgumentException("Matrix row " + i + " has fewer than " + size + " columns");
            }
        }
    }

    private boolean inferDirectedFromMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i][j] != a[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkVertex(int vertex) {
        if (vertex < 0 || vertex >= n) {
            throw new IllegalArgumentException(
                    "Invalid vertex " + vertex + "; expected 0.." + (n - 1));
        }
    }

    private void checkVisitedArray(boolean[] visited, String name) {
        if (visited == null || visited.length < n) {
            throw new IllegalArgumentException(name + " must have at least n elements");
        }
    }

    private boolean hasEdge(int from, int to) {
        int weight = a[from][to];
        return weight != 0 && weight != NO_EDGE;
    }

    private boolean hasUndirectedConnection(int x, int y) {
        return hasEdge(x, y) || hasEdge(y, x);
    }

    private void checkEdgeWeight(int weight) {
        if (weight == 0 || weight == NO_EDGE) {
            throw new IllegalArgumentException(
                    "Edge weight must differ from 0 and NO_EDGE (99): " + weight);
        }
    }

    private void requireNonNegativeEdges(String algorithm) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hasEdge(i, j) && a[i][j] < 0) {
                    throw new IllegalArgumentException(
                            algorithm + " does not support negative edge " + i + "->" + j);
                }
            }
        }
    }

    private static int safeDistance(long value) {
        if (value >= DIST_INF) {
            return DIST_INF;
        }
        if (value <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }

    private static int nextRequiredInt(Scanner sc, String what) {
        if (!sc.hasNextInt()) {
            throw new IllegalArgumentException("Missing integer for " + what);
        }
        return sc.nextInt();
    }

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================
    Graph() {
        initStorage(0);
        directed = false;
    }

    // Constructor truyen truc tiep (Dap ung yeu cau de thi moi)
    Graph(int[][] a, int n, char[] v) {
        this(a, n, v, false);
        this.directed = inferDirectedFromMatrix();
        calcAllDegrees();
    }

    // Overload tuong minh khi ma tran doi xung nhung van bieu dien do thi co huong.
    Graph(int[][] a, int n, char[] v, boolean directed) {
        validateMatrix(a, n);
        initStorage(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.a[i][j] = a[i][j];
            }
        }
        if (v != null) {
            for (int i = 0; i < n && i < v.length; i++) {
                this.v[i] = v[i];
            }
        }
        this.directed = directed;
        calcAllDegrees();
    }

    // ========================================================================
    // I. I/O VA HIEN THI
    // ========================================================================
    // 1. loadData - Doc ma tran ke tu file fi
    //    k = so dong can bo qua truoc khi doc
    //    Format: dong dau la n, sau do n dong ma tran trong so
    void loadData(int k) {
        try (Scanner sc = new Scanner(new File(fi))) {
            for (int i = 0; i < k; i++) {
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }
            }
            loadData(sc);
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException("Cannot read input file: " + fi, e);
        }
    }

    // 1b. loadEdgeList - Doc do thi tu danh sach canh
    //     k = so dong bo qua; oneIndexed = true neu dinh danh so tu 1
    //     Format: dong dau la "n m", sau do m dong "u v" hoac "u v w"
    void loadEdgeList(int k, boolean oneIndexed) {
        try (Scanner sc = new Scanner(new File(fi))) {
            for (int i = 0; i < k; i++) {
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }
            }
            loadEdgeList(sc, oneIndexed);
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException("Cannot read input file: " + fi, e);
        }
    }

    // weighted/directed duoc truyen ro rang de khong doc nham dinh cua dong sau lam trong so.
    void loadEdgeList(int k, boolean oneIndexed, boolean weighted, boolean directed) {
        try (Scanner sc = new Scanner(new File(fi))) {
            for (int i = 0; i < k; i++) {
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }
            }
            loadEdgeList(sc, oneIndexed, weighted, directed);
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException("Cannot read input file: " + fi, e);
        }
    }

    // 1c. loadData - Doc ma tran ke truc tiep tu doi tuong Scanner dang hoat dong
    //     Format: gia tri dau tien doc duoc la n, sau do doc ma tran ke n x n
    void loadData(Scanner sc) {
        Objects.requireNonNull(sc, "scanner");
        int size = nextRequiredInt(sc, "number of vertices");
        initStorage(size);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = nextRequiredInt(sc, "matrix[" + i + "][" + j + "]");
            }
        }
        directed = inferDirectedFromMatrix();
        calcAllDegrees();
    }

    // 1d. loadEdgeList - Docn danh sach canh truc tiep tu doi tuong Scanner dang hoat dong
    //     Format: hai gia tri dau tien la n va m, sau do la m dong chua u v (hoac u v w)
    void loadEdgeList(Scanner sc, boolean oneIndexed) {
        Objects.requireNonNull(sc, "scanner");
        int size = nextRequiredInt(sc, "number of vertices");
        int m = nextRequiredInt(sc, "number of edges");
        if (m < 0) {
            throw new IllegalArgumentException("Number of edges must be >= 0: " + m);
        }
        initStorage(size);
        directed = false; // Overload cu luon tao do thi vo huong.

        // Overload tuong thich: moi canh phai nam tren mot dong va co 2 hoac 3 so.
        // Doc theo dong de khong bao gio lay u cua canh ke tiep lam weight.
        if (sc.hasNextLine()) {
            sc.nextLine(); // Phan con lai cua dong header "n m".
        }
        for (int i = 0; i < m; i++) {
            String line = null;
            while (sc.hasNextLine() && (line == null || line.trim().isEmpty())) {
                line = sc.nextLine();
            }
            if (line == null || line.trim().isEmpty()) {
                throw new IllegalArgumentException("Missing edge line " + i);
            }
            try (Scanner edgeScanner = new Scanner(line)) {
                int u = nextRequiredInt(edgeScanner, "edge " + i + " start");
                int vv = nextRequiredInt(edgeScanner, "edge " + i + " end");
                int w = edgeScanner.hasNextInt() ? edgeScanner.nextInt() : 1;
                if (edgeScanner.hasNext()) {
                    throw new IllegalArgumentException(
                            "Edge line " + i + " must contain exactly 2 or 3 integers");
                }
                if (oneIndexed) {
                    u--;
                    vv--;
                }
                checkVertex(u);
                checkVertex(vv);
                checkEdgeWeight(w);
                a[u][vv] = w;
                a[vv][u] = w;
            }
        }
        calcAllDegrees();
    }

    void loadEdgeList(Scanner sc, boolean oneIndexed, boolean weighted, boolean directed) {
        Objects.requireNonNull(sc, "scanner");
        int size = nextRequiredInt(sc, "number of vertices");
        int m = nextRequiredInt(sc, "number of edges");
        if (m < 0) {
            throw new IllegalArgumentException("Number of edges must be >= 0: " + m);
        }
        initStorage(size);
        this.directed = directed;
        for (int i = 0; i < m; i++) {
            int u = nextRequiredInt(sc, "edge " + i + " start");
            int vv = nextRequiredInt(sc, "edge " + i + " end");
            if (oneIndexed) {
                u--;
                vv--;
            }
            checkVertex(u);
            checkVertex(vv);
            int w = weighted ? nextRequiredInt(sc, "edge " + i + " weight") : 1;
            checkEdgeWeight(w);
            a[u][vv] = w;
            if (!directed) {
                a[vv][u] = w;
            }
        }
        calcAllDegrees();
    }

    // 2. displayAdj - Tra ve ma tran ke duoi dang String
    String displayAdj() {
        StringBuilder sb = new StringBuilder();
        sb.append("n = ").append(n).append("\n");
        // Header row - nhan cac dinh
        sb.append("    ");
        for (int j = 0; j < n; j++) {
            sb.append(String.format("%4c", v[j]));
        }
        sb.append("\n");
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%2c: ", v[i]));
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%4d", a[i][j]));
            }
            if (i < n - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    // 3. fDisplayAdj - Ghi ma tran ke ra file (fdispAdj trong de thi)
    void fdispAdj() {
        int i, j;
        println("n = " + n);
        for (i = 0; i < n; i++) {
            println();
            for (j = 0; j < n; j++) {
                print("  " + a[i][j]);
            }
        }
        println();
    }

    // 3b. fdispAdjRaw - Ghi ma tran ke THUAN TUY ra file (khong co dong "n = ...")
    //     Dung khi de thi chi yeu cau in bang so, khong co tieu de
    //     Vi du output:
    //       0 1 1 0
    //       1 0 0 1
    //       ...
    void fdispAdjRaw() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                print(a[i][j] + " ");
            }
            println();
        }
    }

    // 4. fvisit - Ghi nhan dinh ra outputBuffer
    void fvisit(int i) {
        checkVertex(i);
        print("  " + v[i]);
    }

    // 5. fvisitDeg - Ghi dinh kem bac cua no ra outputBuffer
    void fvisitDeg(int i) {
        checkVertex(i);
        print("  " + v[i] + "(" + deg[i] + ")");
    }

    // ========================================================================
    // II. BFS - DUYET THEO CHIEU RONG
    // ========================================================================
    // 6. BFS tra ve String - duyet tu dinh start
    String BFS(int start) {
        checkVertex(start);
        StringBuilder sb = new StringBuilder();
        boolean[] en = new boolean[n];
        for (int i = 0; i < n; i++) {
            en[i] = false;
        }
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            sb.append("  ").append(v[r]);
            for (int j = 0; j < n; j++) {
                if (!en[j] && hasEdge(r, j)) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
        return sb.toString();
    }

    // 7. BFS voi mang visited tu ben ngoai - tra ve String
    String BFS(boolean[] en, int start) {
        checkVertex(start);
        checkVisitedArray(en, "visited");
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            sb.append("  ").append(v[r]);
            for (int j = 0; j < n; j++) {
                if (!en[j] && hasEdge(r, j)) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
        return sb.toString();
    }

    // 8. fBFS - BFS ghi vao outputBuffer (breadth trong de thi)
    void fBFS(int start) {
        checkVertex(start);
        boolean[] en = new boolean[n];
        for (int i = 0; i < n; i++) {
            en[i] = false;
        }
        fBFS(en, start);
    }

    // 9. fBFS voi mang visited tu ben ngoai - ghi vao outputBuffer
    void fBFS(boolean[] en, int start) {
        checkVertex(start);
        checkVisitedArray(en, "visited");
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            fvisit(r);
            for (int j = 0; j < n; j++) {
                if (!en[j] && hasEdge(r, j)) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
    }

    // 10. BFS_full - BFS duyet het tat ca thanh phan lien thong
    String BFS_full(int start) {
        checkVertex(start);
        StringBuilder sb = new StringBuilder();
        boolean[] en = new boolean[n];
        for (int i = 0; i < n; i++) {
            en[i] = false;
        }
        sb.append(BFS(en, start));
        for (int i = 0; i < n; i++) {
            if (!en[i]) {
                sb.append(BFS(en, i));
            }
        }
        return sb.toString();
    }

    // 11. fBFS_full - BFS duyet het ghi vao outputBuffer (breadth(int k, f) trong de thi)
    void fBFS_full(int start) {
        checkVertex(start);
        boolean[] en = new boolean[n];
        for (int i = 0; i < n; i++) {
            en[i] = false;
        }
        fBFS(en, start);
        for (int i = 0; i < n; i++) {
            if (!en[i]) {
                fBFS(en, i);
            }
        }
    }

    // 12. fBFS_conditional - BFS chi hien thi maxVertices dinh bat dau tu vi tri startFrom
    //     Vi du: Exam 1 f1 - hien thi 5 dinh bat dau tu dinh thu 3
    //     startFromVertex: 1-indexed (dinh thu may trong ket qua BFS)
    void fBFS_conditional(int start, int maxVertices, int startFromVertex) {
        checkVertex(start);
        if (maxVertices < 0 || startFromVertex < 1) {
            throw new IllegalArgumentException("Invalid BFS display limits");
        }
        boolean[] en = new boolean[n];
        for (int i = 0; i < n; i++) {
            en[i] = false;
        }
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        int count = 0;     // dem so dinh da duyet
        int displayed = 0; // dem so dinh da hien thi
        while (!q.isEmpty() && displayed < maxVertices) {
            int r = q.removeFirst();
            count++;
            // Chi hien thi tu dinh thu startFromVertex tro di
            if (count >= startFromVertex) {
                fvisit(r);
                displayed++;
            }
            for (int j = 0; j < n; j++) {
                if (!en[j] && hasEdge(r, j)) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
    }

    // 12b. BFS_dual - BFS dung HAI MANG RIENG BIET: visited[] va enqueued[]
    //      (Cach dung cua Co Vy / de thi FPT)
    //      Khac voi BFS thuong o cho: visited danh dau sau khi dequeue, enqueued danh dau khi enqueue
    //      => Dam bao moi dinh chi duoc enqueue 1 lan va chi duoc visit 1 lan
    //      Goi: boolean[] visited = new boolean[n]; boolean[] enqueued = new boolean[n];
    //           graph.fBFS_dual(visited, enqueued, 0);
    void fBFS_dual(boolean[] visited, boolean[] enqueued, int start) {
        checkVertex(start);
        checkVisitedArray(visited, "visited");
        checkVisitedArray(enqueued, "enqueued");
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        enqueued[start] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            if (!visited[r]) {
                fvisit(r);
                visited[r] = true;
            }
            for (int j = 0; j < n; j++) {
                if (!visited[j] && !enqueued[j] && hasEdge(r, j)) {
                    q.addLast(j);
                    enqueued[j] = true;
                }
            }
        }
    }

    // 12c. fBFS_dual_full - BFS dual duyet TAT CA thanh phan lien thong
    void fBFS_dual_full(int start) {
        checkVertex(start);
        boolean[] visited = new boolean[n];
        boolean[] enqueued = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            enqueued[i] = false;
        }
        fBFS_dual(visited, enqueued, start);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                fBFS_dual(visited, enqueued, i);
            }
        }
    }

    // BFS_conditional tra ve String (phien ban khong ghi file)
    String BFS_conditional(int start, int maxVertices, int startFromVertex) {
        checkVertex(start);
        if (maxVertices < 0 || startFromVertex < 1) {
            throw new IllegalArgumentException("Invalid BFS display limits");
        }
        StringBuilder sb = new StringBuilder();
        boolean[] en = new boolean[n];
        for (int i = 0; i < n; i++) {
            en[i] = false;
        }
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        int count = 0;
        int displayed = 0;
        while (!q.isEmpty() && displayed < maxVertices) {
            int r = q.removeFirst();
            count++;
            if (count >= startFromVertex) {
                sb.append("  ").append(v[r]);
                displayed++;
            }
            for (int j = 0; j < n; j++) {
                if (!en[j] && hasEdge(r, j)) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
        return sb.toString();
    }

    // ========================================================================
    // III. DFS - DUYET THEO CHIEU SAU
    // ========================================================================
    // --- Helper de quy noi bo cho DFS tra ve String ---
    private void dfsRecursive(boolean[] visited, int k, StringBuilder sb) {
        visited[k] = true;
        sb.append("  ").append(v[k]);
        for (int i = 0; i < n; i++) {
            if (!visited[i] && hasEdge(k, i)) {
                dfsRecursive(visited, i, sb);
            }
        }
    }

    // 13. DFS de quy tra ve String
    String DFS(int start) {
        checkVertex(start);
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        dfsRecursive(visited, start, sb);
        return sb.toString();
    }

    // 14. DFS voi mang visited tu ben ngoai - tra ve String
    String DFS(boolean[] visited, int start) {
        checkVertex(start);
        checkVisitedArray(visited, "visited");
        StringBuilder sb = new StringBuilder();
        dfsRecursive(visited, start, sb);
        return sb.toString();
    }

    // 15. fDFS - DFS de quy ghi vao outputBuffer (depth trong de thi)
    void fDFS(int start) {
        checkVertex(start);
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        fDFS(visited, start);
    }

    // 16. fDFS voi mang visited tu ben ngoai - ghi vao outputBuffer
    void fDFS(boolean[] visited, int start) {
        checkVertex(start);
        checkVisitedArray(visited, "visited");
        fvisit(start);
        visited[start] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && hasEdge(start, i)) {
                fDFS(visited, i);
            }
        }
    }

    // 17. DFS_full - DFS duyet het tat ca thanh phan lien thong
    String DFS_full(int start) {
        checkVertex(start);
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        dfsRecursive(visited, start, sb);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsRecursive(visited, i, sb);
            }
        }
        return sb.toString();
    }

    // 18. fDFS_full - DFS duyet het ghi vao outputBuffer (depth(int k, f) trong de thi)
    void fDFS_full(int start) {
        checkVertex(start);
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        fDFS(visited, start);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                fDFS(visited, i);
            }
        }
    }

    // 19. DFS_iterative - DFS dung stack (khong de quy)
    String DFS_iterative(int start) {
        checkVertex(start);
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        LinkedList<Integer> stack = new LinkedList<Integer>();
        stack.addLast(start);
        while (!stack.isEmpty()) {
            int k = stack.removeLast();
            if (!visited[k]) {
                visited[k] = true;
                sb.append("  ").append(v[k]);
                // Day cac dinh ke vao stack theo thu tu nguoc de dam bao duyet dung thu tu
                for (int i = n - 1; i >= 0; i--) {
                    if (!visited[i] && hasEdge(k, i)) {
                        stack.addLast(i);
                    }
                }
            }
        }
        return sb.toString();
    }

    // ========================================================================
    // IV. BAC CUA DINH (DEGREE)
    // ========================================================================
    // 20. calcDegree - Tinh bac cua mot dinh (do thi vo huong)
    //     Voi do thi co trong so: moi gia tri khac 0 va NO_EDGE deu la mot canh.
    int calcDegree(int vertex) {
        checkVertex(vertex);
        int degree = 0;
        for (int j = 0; j < n; j++) {
            if (hasEdge(vertex, j)) {
                degree += (!directed && vertex == j) ? 2 : 1;
            }
        }
        return degree;
    }

    // 21. calcAllDegrees - Tinh bac cho tat ca cac dinh, luu vao deg[]
    void calcAllDegrees() {
        for (int i = 0; i < n; i++) {
            deg[i] = calcDegree(i);
        }
    }

    // 22. getDegreesString - Tra ve bac cua tat ca cac dinh duoi dang String
    String getDegreesString() {
        calcAllDegrees();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(v[i]).append("(").append(deg[i]).append(")");
            if (i < n - 1) {
                sb.append("  ");
            }
        }
        return sb.toString();
    }

    // ========================================================================
    // V. DIJKSTRA - DUONG DI NGAN NHAT
    // ========================================================================
    // 23. dijkstra(src, dest) - Tim duong di ngan nhat tu src den dest
    //     Tra ve String gom: duong di va chi phi
    //     Ma tran trong so: a[i][j] = 0 => khong co canh (hoac chinh no)
    //                        a[i][j] = NO_EDGE (99) => khong co canh
    //                        gia tri khac 0/99 => trong so canh; Dijkstra se tu choi canh am
    String dijkstra(int src, int dest) {
        checkVertex(src);
        checkVertex(dest);
        requireNonNegativeEdges("Dijkstra");
        int[] dist = new int[n];       // Khoang cach ngan nhat tu src
        boolean[] inS = new boolean[n]; // Tap S - da xac dinh duong ngan nhat
        int[] prev = new int[n];        // Dinh truoc do tren duong di ngan nhat
        List<Integer> sOrder = new ArrayList<Integer>(); // Thu tu cac dinh duoc chon vao S

        // Khoi tao
        for (int i = 0; i < n; i++) {
            dist[i] = DIST_INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;

        for (int count = 0; count < n; count++) {
            // Tim dinh u chua trong S co dist nho nhat
            int u = -1;
            int minDist = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break; // Khong con dinh nao co the den duoc
            }
            inS[u] = true;
            sOrder.add(u);

            // Cap nhat khoang cach cac dinh ke cua u
            for (int w = 0; w < n; w++) {
                if (!inS[w] && hasEdge(u, w)) {
                    int newDist = safeDistance((long) dist[u] + a[u][w]);
                    if (newDist < dist[w]) {
                        dist[w] = newDist;
                        prev[w] = u;
                    }
                }
            }
        }

        // Xay dung duong di tu src den dest
        List<Integer> path = new ArrayList<Integer>();
        int current = dest;
        while (current != -1) {
            path.add(0, current);
            current = prev[current];
        }

        StringBuilder sb = new StringBuilder();

        if (dist[dest] == DIST_INF || path.get(0) != src) {
            sb.append("No path");
        } else {
            int count = 0;

            sb.append("\n");

            // Dong 2: Chi phi duong di
            sb.append(dist[dest]).append("\n");
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) {
                    temp.append(",");
                }
                temp.append(path.get(i));
                count++;
            }
            sb.append(count).append("\n");
            sb.append(temp);
        }
        return sb.toString();
    }

    // 24. dijkstra(src) - Dijkstra tu src den TAT CA cac dinh, tra ve mang dist
    int[] dijkstra(int src) {
        checkVertex(src);
        requireNonNegativeEdges("Dijkstra");
        int[] dist = new int[n];
        boolean[] inS = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = DIST_INF;
            inS[i] = false;
        }
        dist[src] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            inS[u] = true;
            for (int w = 0; w < n; w++) {
                if (!inS[w] && hasEdge(u, w)) {
                    int newDist = safeDistance((long) dist[u] + a[u][w]);
                    if (newDist < dist[w]) {
                        dist[w] = newDist;
                    }
                }
            }
        }
        return dist;
    }

    // 25. dijkstraPath - Tra ve danh sach cac dinh tren duong di ngan nhat
    List<Integer> dijkstraPath(int src, int dest) {
        checkVertex(src);
        checkVertex(dest);
        requireNonNegativeEdges("Dijkstra");
        int[] dist = new int[n];
        boolean[] inS = new boolean[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = DIST_INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            inS[u] = true;
            for (int w = 0; w < n; w++) {
                if (!inS[w] && hasEdge(u, w)) {
                    int newDist = safeDistance((long) dist[u] + a[u][w]);
                    if (newDist < dist[w]) {
                        dist[w] = newDist;
                        prev[w] = u;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<Integer>();
        int current = dest;
        while (current != -1) {
            path.add(0, current);
            current = prev[current];
        }
        // Kiem tra co duong di hay khong
        if (path.isEmpty() || path.get(0) != src) {
            return new ArrayList<Integer>(); // Khong co duong di
        }
        return path;
    }

    // 26. dijkstraFullInfo - Ghi day du thong tin Dijkstra vao outputBuffer
    //     Bao gom: duong di, chi phi, tap S, bang khoang cach
    void dijkstraFullInfo(int src, int dest) {
        checkVertex(src);
        checkVertex(dest);
        requireNonNegativeEdges("Dijkstra");
        int[] dist = new int[n];
        boolean[] inS = new boolean[n];
        int[] prev = new int[n];
        List<Integer> sOrder = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            dist[i] = DIST_INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;

        // Header: bang theo doi qua trinh Dijkstra
        println("Dijkstra from " + v[src] + " to " + v[dest]);

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            inS[u] = true;
            sOrder.add(u);

            // Ghi buoc hien tai
            print("  Select " + v[u] + " (dist=" + dist[u] + "), S={");
            for (int i = 0; i < sOrder.size(); i++) {
                if (i > 0) {
                    print(",");
                }
                print("" + v[sOrder.get(i)]);
            }
            println("}");

            for (int w = 0; w < n; w++) {
                if (!inS[w] && hasEdge(u, w)) {
                    int newDist = safeDistance((long) dist[u] + a[u][w]);
                    if (newDist < dist[w]) {
                        dist[w] = newDist;
                        prev[w] = u;
                    }
                }
            }
        }

        // Xay dung va ghi duong di
        List<Integer> path = new ArrayList<Integer>();
        int current = dest;
        while (current != -1) {
            path.add(0, current);
            current = prev[current];
        }

        println();
        // Duong di
        print("Path: ");
        if (dist[dest] == DIST_INF || path.get(0) != src) {
            println("No path found");
        } else {
            for (int i = 0; i < path.size(); i++) {
                fvisit(path.get(i));
            }
            println();
            println("Cost: " + dist[dest]);
        }

        // 4 dinh cuoi duoc chon vao S
        print("Last 4 in S: ");
        int start = Math.max(0, sOrder.size() - 4);
        for (int i = start; i < sOrder.size(); i++) {
            fvisit(sOrder.get(i));
        }
        println();

        // Bang khoang cach cuoi cung
        print("Final distances from " + v[src] + ":");
        for (int i = 0; i < n; i++) {
            print("  " + v[i] + "=" + dist[i]);
        }
        println();
    }

    // 26b. fDijkstraSteps - In BANG TRANG THAI DIJKSTRA sau moi buoc chon dinh vao S
    //      Day la dang bai PHO BIEN NHAT trong de thi: "In mang d[] sau moi buoc"
    //      Format moi buoc:
    //        Step 1: Select A. d[]= [0 2 5 1 99 99]
    //        Step 2: Select D. d[]= [0 2 4 1 2 99]
    //        ...
    void fDijkstraSteps(int src) {
        checkVertex(src);
        requireNonNegativeEdges("Dijkstra");
        int[] dist = new int[n];
        boolean[] inS = new boolean[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = DIST_INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;
        int step = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            inS[u] = true;
            step++;

            // Cap nhat khoang cach
            for (int w = 0; w < n; w++) {
                if (!inS[w] && hasEdge(u, w)) {
                    int nd = safeDistance((long) dist[u] + a[u][w]);
                    if (nd < dist[w]) {
                        dist[w] = nd;
                        prev[w] = u;
                    }
                }
            }

            // In trang thai sau buoc nay
            print("Step " + step + ": Select " + v[u] + ". d[]= [");
            for (int i = 0; i < n; i++) {
                print(dist[i] == DIST_INF ? "99" : "" + dist[i]);
                if (i < n - 1) {
                    print(" ");
                }
            }
            println("]");
        }
    }

    // 26c. fDijkstraDistances - In KHOANG CACH TICH LUY tren duong di ngan nhat
    //      Format output: 0 1 9 11 13
    //      Goi: graph.fDijkstraDistances(0, 5);
    void fDijkstraDistances(int src, int dest) {
        checkVertex(src);
        checkVertex(dest);
        requireNonNegativeEdges("Dijkstra");
        int[] dist = new int[n];
        boolean[] inS = new boolean[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = DIST_INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;
        for (int count = 0; count < n; count++) {
            int u = -1, minDist = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            inS[u] = true;
            for (int w = 0; w < n; w++) {
                if (!inS[w] && hasEdge(u, w)) {
                    int nd = safeDistance((long) dist[u] + a[u][w]);
                    if (nd < dist[w]) {
                        dist[w] = nd;
                        prev[w] = u;
                    }
                }
            }
        }
        if (dist[dest] == DIST_INF) {
            println("No path");
            return;
        }
        // Xay dung duong di
        List<Integer> path = new ArrayList<Integer>();
        int cur = dest;
        while (cur != -1) {
            path.add(0, cur);
            cur = prev[cur];
        }
        // In khoang cach tich luy
        for (int i = 0; i < path.size(); i++) {
            print("" + dist[path.get(i)]);
            if (i < path.size() - 1) {
                print(" ");
            }
        }
        println();
    }

    // 26d. fDijkstraFormatted - In format (khoangcach)Dinh -> (khoangcach)Dinh
    //      Format output: (0)A -> (1)D -> (2)E -> (4)F
    //      Goi: graph.fDijkstraFormatted(0, 5);
    void fDijkstraFormatted(int src, int dest) {
        checkVertex(src);
        checkVertex(dest);
        requireNonNegativeEdges("Dijkstra");
        int[] dist = new int[n];
        boolean[] inS = new boolean[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = DIST_INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;
        for (int count = 0; count < n; count++) {
            int u = -1, minDist = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            inS[u] = true;
            for (int w = 0; w < n; w++) {
                if (!inS[w] && hasEdge(u, w)) {
                    int nd = safeDistance((long) dist[u] + a[u][w]);
                    if (nd < dist[w]) {
                        dist[w] = nd;
                        prev[w] = u;
                    }
                }
            }
        }
        if (dist[dest] == DIST_INF) {
            println("No path");
            return;
        }
        List<Integer> path = new ArrayList<Integer>();
        int cur = dest;
        while (cur != -1) {
            path.add(0, cur);
            cur = prev[cur];
        }
        // In dang (chi_phi)Dinh -> ...
        for (int i = 0; i < path.size(); i++) {
            int p = path.get(i);
            print("(" + dist[p] + ")" + v[p]);
            if (i < path.size() - 1) {
                print(" -> ");
            }
        }
        println();
    }

    // ========================================================================
    // VI. KIEM TRA LIEN THONG (CONNECTIVITY)
    // ========================================================================
    // 27. isConnected - Kiem tra do thi co lien thong hay khong
    //     Do thi co huong duoc xet theo weak connectivity (bo huong cua canh).
    boolean isConnected() {
        if (n == 0) {
            return true;
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        // BFS tu dinh 0
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(0);
        visited[0] = true;
        int countVisited = 1;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            for (int j = 0; j < n; j++) {
                if (!visited[j] && hasUndirectedConnection(r, j)) {
                    visited[j] = true;
                    q.addLast(j);
                    countVisited++;
                }
            }
        }
        return countVisited == n;
    }

    // 27b. isTree - Kiem tra do thi co phai la cay khong (lien thong va n-1 canh)
    boolean isTree() {
        if (n == 0 || directed || !isConnected()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (hasEdge(i, i)) {
                return false;
            }
        }
        return countEdges() == n - 1;
    }

    // 27c. bellmanFord - Tim duong di ngan nhat co the co trong so am
    void bellmanFord(int src) {
        checkVertex(src);
        int[] dist = new int[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = DIST_INF;
            parent[i] = -1;
        }
        dist[src] = 0;

        for (int k = 1; k < n; k++) {
            boolean changed = false;
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (hasEdge(u, v)) {
                        int candidate = dist[u] == DIST_INF ? DIST_INF
                                : safeDistance((long) dist[u] + a[u][v]);
                        if (candidate < dist[v]) {
                            dist[v] = candidate;
                            parent[v] = u;
                            changed = true;
                        }
                    }
                }
            }
            if (!changed) {
                break;
            }
        }

        // Kiem tra chu trinh am
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (hasEdge(u, v)) {
                    int candidate = dist[u] == DIST_INF ? DIST_INF
                            : safeDistance((long) dist[u] + a[u][v]);
                    if (candidate < dist[v]) {
                        println("Do thi chua chu trinh am");
                        return;
                    }
                }
            }
        }

        println("Khoang cach ngan nhat tu " + v[src] + " theo Bellman-Ford:");
        for (int i = 0; i < n; i++) {
            if (dist[i] == DIST_INF) {
                println(v[src] + " -> " + v[i] + " : INF");
            } else {
                println(v[src] + " -> " + v[i] + " : " + dist[i]);
            }
        }
    }

    // 28. countConnectedComponents - Dem so TPLT bang BFS
    int countConnectedComponents() {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                // BFS tu dinh i de danh dau tat ca cac dinh trong thanh phan nay
                LinkedList<Integer> q = new LinkedList<Integer>();
                q.addLast(i);
                visited[i] = true;
                while (!q.isEmpty()) {
                    int r = q.removeFirst();
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && hasUndirectedConnection(r, j)) {
                            visited[j] = true;
                            q.addLast(j);
                        }
                    }
                }
            }
        }
        return count;
    }

    // 29. getConnectedComponents - Lay danh sach cac thanh phan lien thong
    List<List<Integer>> getConnectedComponents() {
        List<List<Integer>> components = new ArrayList<List<Integer>>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<Integer>();
                LinkedList<Integer> q = new LinkedList<Integer>();
                q.addLast(i);
                visited[i] = true;
                while (!q.isEmpty()) {
                    int r = q.removeFirst();
                    component.add(r);
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && hasUndirectedConnection(r, j)) {
                            visited[j] = true;
                            q.addLast(j);
                        }
                    }
                }
                components.add(component);
            }
        }
        return components;
    }

    // 29b. countConnectedComponentsDFS - Dem TPLT dung STACK (phong cach Co Vy / de thi)
    //      Cung ket qua voi countConnectedComponents() nhung thu tu duyet co the khac
    //      Dung khi de thi yeu cau DFS-style connectivity counting
    //      Goi: int k = graph.countConnectedComponentsDFS();
    int countConnectedComponentsDFS() {
        boolean[] pushed = new boolean[n];
        for (int i = 0; i < n; i++) {
            pushed[i] = false;
        }
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int k = 0;
        int i, r;
        while (true) {
            // Tim dinh chua duoc duyet
            i = 0;
            while (i < n && pushed[i]) {
                i++;
            }
            if (i == n) {
                break;
            }
            // BFS/DFS tu dinh i
            stack.clear();
            stack.addLast(i);
            pushed[i] = true;
            k++;
            while (!stack.isEmpty()) {
                r = stack.removeLast(); // DFS: removeLast; BFS: removeFirst
                for (int j = 0; j < n; j++) {
                    if (j == r) {
                        continue;
                    }
                    if (!pushed[j] && hasUndirectedConnection(r, j)) {
                        stack.addLast(j);
                        pushed[j] = true;
                    }
                }
            }
        }
        return k;
    }

    // 29c. fPrintComponents - In ra cac thanh phan lien thong kem nhan dinh (pho bien trong de thi)
    //      Format output:
    //        Component 1:   A  B  C
    //        Component 2:   D  E
    //      Goi: graph.fPrintComponents();
    void fPrintComponents() {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        int comp = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                comp++;
                print("Component " + comp + ": ");
                // BFS trong thanh phan nay
                LinkedList<Integer> q = new LinkedList<Integer>();
                q.addLast(i);
                visited[i] = true;
                while (!q.isEmpty()) {
                    int r = q.removeFirst();
                    fvisit(r);
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && hasUndirectedConnection(r, j)) {
                            visited[j] = true;
                            q.addLast(j);
                        }
                    }
                }
                println();
            }
        }
    }

    // ========================================================================
    // VII. CAY KHUNG NHO NHAT (MINIMUM SPANNING TREE)
    // ========================================================================
    // 30. Prim - Thuat toan Prim, tra ve cac canh MST va tong trong so
    String prim(int start) {
        if (directed) {
            return "No MST: graph must be undirected";
        }
        if (n == 0) {
            return "No MST: graph is empty";
        }
        checkVertex(start);
        if (!isConnected()) {
            return "No MST: graph is disconnected";
        }
        StringBuilder sb = new StringBuilder();
        boolean[] inMST = new boolean[n];
        int[] key = new int[n];     // Trong so nho nhat de ket noi voi MST
        int[] parent = new int[n];  // Dinh cha trong MST
        long totalWeight = 0;

        for (int i = 0; i < n; i++) {
            inMST[i] = false;
            key[i] = DIST_INF;
            parent[i] = -1;
        }
        key[start] = 0;

        sb.append("Prim's MST (starting from ").append(v[start]).append("):\n");

        for (int count = 0; count < n; count++) {
            // Tim dinh u chua trong MST co key nho nhat
            int u = -1;
            int minKey = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inMST[i] && key[i] < minKey) {
                    minKey = key[i];
                    u = i;
                }
            }
            if (u == -1) {
                break; // Khong con dinh nao co the ket noi
            }
            inMST[u] = true;

            if (parent[u] != -1) {
                sb.append("  Edge ").append(v[parent[u]]).append("-").append(v[u]);
                sb.append(" (weight=").append(a[parent[u]][u]).append(")\n");
                totalWeight += a[parent[u]][u];
            }

            // Cap nhat key cac dinh ke
            for (int w = 0; w < n; w++) {
                if (!inMST[w] && hasEdge(u, w) && a[u][w] < key[w]) {
                    key[w] = a[u][w];
                    parent[w] = u;
                }
            }
        }
        sb.append("Total MST weight: ").append(totalWeight);
        return sb.toString();
    }

    // 31. Kruskal - Thuat toan Kruskal dung Union-Find
    String kruskal() {
        if (directed) {
            return "No MST: graph must be undirected";
        }
        if (n == 0) {
            return "No MST: graph is empty";
        }
        if (!isConnected()) {
            return "No MST: graph is disconnected";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Kruskal's MST:\n");

        // Thu thap tat ca cac canh
        // Moi canh: {weight, u, v}
        List<int[]> edges = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (hasEdge(i, j)) {
                    edges.add(new int[]{a[i][j], i, j});
                }
            }
        }

        // Sap xep canh theo trong so tang dan
        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                return Integer.compare(e1[0], e2[0]);
            }
        });

        // Union-Find
        int[] parentUF = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            parentUF[i] = i;
            rank[i] = 0;
        }

        long totalWeight = 0;
        int edgeCount = 0;

        for (int[] edge : edges) {
            if (edgeCount >= n - 1) {
                break;
            }
            int w = edge[0];
            int u = edge[1];
            int vv = edge[2];

            int rootU = find(parentUF, u);
            int rootV = find(parentUF, vv);

            if (rootU != rootV) {
                union(parentUF, rank, rootU, rootV);
                sb.append("  Edge ").append(v[u]).append("-").append(v[vv]);
                sb.append(" (weight=").append(w).append(")\n");
                totalWeight += w;
                edgeCount++;
            }
        }
        sb.append("Total MST weight: ").append(totalWeight);
        return sb.toString();
    }

    // Helper cho Kruskal: Find voi path compression
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    // Helper cho Kruskal: Union by rank
    private void union(int[] parent, int[] rank, int x, int y) {
        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }

    // ========================================================================
    // VIII. EULER PATH / CIRCUIT
    // ========================================================================
    // 32. hasEulerPath - Kiem tra do thi co duong di Euler hay khong
    //     Do thi vo huong co duong di Euler khi va chi khi:
    //     - Lien thong (chi xet cac dinh co bac > 0)
    //     - Co dung 0 hoac 2 dinh bac le
    boolean hasEulerPath() {
        if (directed || !isConnectedNonIsolated()) {
            return false;
        }
        calcAllDegrees();
        int oddCount = 0;
        for (int i = 0; i < n; i++) {
            if (deg[i] % 2 != 0) {
                oddCount++;
            }
        }
        return (oddCount == 0 || oddCount == 2);
    }

    // 33. hasEulerCircuit - Kiem tra do thi co chu trinh Euler hay khong
    //     Tat ca cac dinh co bac > 0 deu co bac chan, va do thi lien thong
    boolean hasEulerCircuit() {
        if (directed || !isConnectedNonIsolated()) {
            return false;
        }
        calcAllDegrees();
        for (int i = 0; i < n; i++) {
            if (deg[i] > 0 && deg[i] % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    // 33b. checkEulerCycle - Kiem tra TUNG DIEU KIEN Euler va in chi tiet vao outputBuffer
    //      Phuong phap thuong duoc yeu cau trong de thi:
    //      Vi du output:
    //        The graph is undirected.
    //        The graph is connected.
    //        All vertices have even degree.
    //        Conditions for Euler's cycle are satisfied.
    //      Goi: graph.checkEulerCycle();
    void checkEulerCycle() {
        boolean undir = isUndirected();
        boolean conn = undir && isConnectedNonIsolated();
        boolean even = isEvenDegree();
        // In tung dieu kien
        println(undir ? "The graph is undirected."
                : "The graph is directed.");
        println(conn ? "The graph is connected."
                : "The graph is not connected.");
        println(even ? "All vertices have even degree."
                : "The graph has a vertex with odd degree.");
        if (undir && conn && even) {
            println("Conditions for Euler's cycle are satisfied.");
        } else {
            println("Conditions for Euler's cycle are not satisfied.");
        }
    }

    // 33c. hasIsolated - Kiem tra co dinh CO LAP (bac = 0) hay khong
    //      Dung trong dieu kien Euler Path/Circuit
    //      Goi: boolean b = graph.hasIsolated();
    boolean hasIsolated() {
        for (int i = 0; i < n; i++) {
            if (calcDegree(i) == 0) {
                return true;
            }
        }
        return false;
    }

    // Helper: kiem tra lien thong chi xet cac dinh co bac > 0
    private boolean isConnectedNonIsolated() {
        calcAllDegrees();
        // Tim dinh dau tien co bac > 0
        int startVertex = -1;
        for (int i = 0; i < n; i++) {
            if (deg[i] > 0) {
                startVertex = i;
                break;
            }
        }
        if (startVertex == -1) {
            return true; // Khong co canh nao
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(startVertex);
        visited[startVertex] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            for (int j = 0; j < n; j++) {
                if (!visited[j] && hasEdge(r, j)) {
                    visited[j] = true;
                    q.addLast(j);
                }
            }
        }
        // Kiem tra tat ca dinh co bac > 0 deu da duoc tham
        for (int i = 0; i < n; i++) {
            if (deg[i] > 0 && !visited[i]) {
                return false;
            }
        }
        return true;
    }

    // 34. findEulerPath - Tim duong di Euler (thuat toan Hierholzer)
    //     Su dung ban sao ma tran ke de xoa canh khi di qua
    String findEulerPath(int start) {
        checkVertex(start);
        if (!hasEulerPath()) {
            return "No Euler path exists";
        }
        calcAllDegrees();
        int oddCount = 0;
        for (int i = 0; i < n; i++) {
            if ((deg[i] & 1) != 0) {
                oddCount++;
            }
        }
        if ((oddCount == 2 && (deg[start] & 1) == 0)
                || (countEdges() > 0 && deg[start] == 0)) {
            return "Invalid Euler start";
        }
        int edgeCount = countEdges();

        // Tao ban sao ma tran ke (chi giu 0/1 cho bai toan Euler)
        int[][] tempA = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hasEdge(i, j)) {
                    tempA[i][j] = 1;
                } else {
                    tempA[i][j] = 0;
                }
            }
        }

        // Thuat toan Hierholzer
        LinkedList<Integer> stack = new LinkedList<Integer>();
        List<Integer> path = new ArrayList<Integer>();
        stack.addLast(start);

        while (!stack.isEmpty()) {
            int curr = stack.getLast();
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (tempA[curr][j] > 0) {
                    stack.addLast(j);
                    tempA[curr][j]--;
                    if (curr != j) {
                        tempA[j][curr]--; // Do thi vo huong; self-loop chi giam mot lan
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                path.add(stack.removeLast());
            }
        }

        // Dao nguoc path
        if (path.size() != edgeCount + 1) {
            return "No Euler path exists";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Euler Path: ");
        for (int i = path.size() - 1; i >= 0; i--) {
            if (i < path.size() - 1) {
                sb.append(" ");
            }
            sb.append(v[path.get(i)]);
        }
        return sb.toString();
    }

    // ========================================================================
    // IX. TOPOLOGICAL SORT (cho do thi co huong DAG)
    // ========================================================================
    // 35. topologicalSort - Sap xep topo (thuat toan Kahn - BFS)
    //     Su dung in-degree
    String topologicalSort() {
        // Tinh in-degree cho moi dinh (do thi co huong)
        int[] inDeg = new int[n];
        for (int i = 0; i < n; i++) {
            inDeg[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hasEdge(i, j)) {
                    inDeg[j]++;
                }
            }
        }

        LinkedList<Integer> q = new LinkedList<Integer>();
        // Dua cac dinh co in-degree = 0 vao queue
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                q.addLast(i);
            }
        }

        List<Integer> result = new ArrayList<Integer>();
        while (!q.isEmpty()) {
            int u = q.removeFirst();
            result.add(u);
            for (int w = 0; w < n; w++) {
                if (hasEdge(u, w)) {
                    inDeg[w]--;
                    if (inDeg[w] == 0) {
                        q.addLast(w);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (result.size() != n) {
            sb.append("Graph has cycle - topological sort not possible");
        } else {
            sb.append("Topological order: ");
            for (int i = 0; i < result.size(); i++) {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(v[result.get(i)]);
            }
        }
        return sb.toString();
    }

    // ========================================================================
    // X. PHAT HIEN CHU TRINH (CYCLE DETECTION)
    // ========================================================================
    // 36. hasCycle - Kiem tra do thi co chu trinh hay khong
    //     Dung cho CA hai loai: co huong va vo huong
    //     Phien ban nay dung DFS voi 3 mau (WHITE, GRAY, BLACK) cho do thi co huong
    //     Va union-find cho do thi vo huong
    boolean hasCycle() {
        // Kiem tra bang cach dung topological sort (Kahn's algorithm)
        // Neu khong sap xep duoc het n dinh => co chu trinh
        int[] inDeg = new int[n];
        for (int i = 0; i < n; i++) {
            inDeg[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hasEdge(i, j)) {
                    inDeg[j]++;
                }
            }
        }

        if (directed) {
            // Do thi co huong: dung Kahn's algorithm
            LinkedList<Integer> q = new LinkedList<Integer>();
            for (int i = 0; i < n; i++) {
                if (inDeg[i] == 0) {
                    q.addLast(i);
                }
            }
            int count = 0;
            while (!q.isEmpty()) {
                int u = q.removeFirst();
                count++;
                for (int w = 0; w < n; w++) {
                    if (hasEdge(u, w)) {
                        inDeg[w]--;
                        if (inDeg[w] == 0) {
                            q.addLast(w);
                        }
                    }
                }
            }
            return count != n;
        } else {
            // Do thi vo huong: dung Union-Find
            for (int i = 0; i < n; i++) {
                if (hasEdge(i, i)) {
                    return true;
                }
            }
            int[] parentUF = new int[n];
            for (int i = 0; i < n; i++) {
                parentUF[i] = i;
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (hasEdge(i, j)) {
                        int rootI = find(parentUF, i);
                        int rootJ = find(parentUF, j);
                        if (rootI == rootJ) {
                            return true; // Co chu trinh
                        }
                        parentUF[rootI] = rootJ;
                    }
                }
            }
            return false;
        }
    }

    // ========================================================================
    // XI. DUONG DI NGAN NHAT BO SUNG
    // ========================================================================
    // 37. shortestPathBFS - Duong di ngan nhat khong trong so bang BFS
    //     Chi dem so canh (khong phai trong so)
    String shortestPathBFS(int src, int dest) {
        checkVertex(src);
        checkVertex(dest);
        boolean[] visited = new boolean[n];
        int[] prev = new int[n];
        int[] distBFS = new int[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            prev[i] = -1;
            distBFS[i] = -1;
        }

        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(src);
        visited[src] = true;
        distBFS[src] = 0;

        while (!q.isEmpty()) {
            int r = q.removeFirst();
            if (r == dest) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (!visited[j] && hasEdge(r, j)) {
                    visited[j] = true;
                    prev[j] = r;
                    distBFS[j] = distBFS[r] + 1;
                    q.addLast(j);
                }
            }
        }

        // Xay dung duong di
        if (!visited[dest]) {
            return "No path from " + v[src] + " to " + v[dest];
        }

        List<Integer> path = new ArrayList<Integer>();
        int current = dest;
        while (current != -1) {
            path.add(0, current);
            current = prev[current];
        }

        StringBuilder sb = new StringBuilder();
        sb.append("BFS shortest path from ").append(v[src]).append(" to ").append(v[dest]).append(": ");
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(v[path.get(i)]);
        }
        sb.append(" (").append(distBFS[dest]).append(" edges)");
        return sb.toString();
    }

    // 38. floydWarshall - Duong di ngan nhat moi cap (all-pairs)
    //     Tra ve ma tran khoang cach
    int[][] floydWarshall() {
        int[][] dist = new int[n][n];

        // Khoi tao
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = hasEdge(i, i) ? Math.min(0, a[i][i]) : 0;
                } else if (hasEdge(i, j)) {
                    dist[i][j] = a[i][j];
                } else {
                    dist[i][j] = DIST_INF;
                }
            }
        }

        // Thuat toan Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != DIST_INF && dist[k][j] != DIST_INF) {
                        int candidate = safeDistance((long) dist[i][k] + dist[k][j]);
                        if (candidate < dist[i][j]) {
                            dist[i][j] = candidate;
                        }
                    }
                }
            }
        }
        return dist;
    }

    // floydWarshallString - Phien ban tra ve String de de doc
    String floydWarshallString() {
        int[][] dist = floydWarshall();
        StringBuilder sb = new StringBuilder();
        sb.append("Floyd-Warshall all-pairs shortest paths:\n");
        // Header
        sb.append("    ");
        for (int j = 0; j < n; j++) {
            sb.append(String.format("%4c", v[j]));
        }
        sb.append("\n");
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%2c: ", v[i]));
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == DIST_INF) {
                    sb.append(" INF");
                } else {
                    sb.append(String.format("%4d", dist[i][j]));
                }
            }
            if (i < n - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    // ========================================================================
    // XII. CAC HAM HO TRO THEM (EXAM UTILITY)
    // ========================================================================
    // --- Xuat BFS voi bac cua dinh (fvisitDeg) ---
    void fBFS_withDeg(int start) {
        checkVertex(start);
        calcAllDegrees();
        boolean[] en = new boolean[n];
        for (int i = 0; i < n; i++) {
            en[i] = false;
        }
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            fvisitDeg(r);
            for (int j = 0; j < n; j++) {
                if (!en[j] && hasEdge(r, j)) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
    }

    // --- Xuat DFS voi bac cua dinh (fvisitDeg) ---
    void fDFS_withDeg(int start) {
        checkVertex(start);
        calcAllDegrees();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        fDFS_withDeg_recursive(visited, start);
    }

    private void fDFS_withDeg_recursive(boolean[] visited, int k) {
        fvisitDeg(k);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && hasEdge(k, i)) {
                fDFS_withDeg_recursive(visited, i);
            }
        }
    }

    // --- DFS conditional (tuong tu BFS_conditional) ---
    void DFS_conditional(int start, int maxVertices, int startFromVertex) {
        checkVertex(start);
        if (maxVertices < 0 || startFromVertex < 1) {
            throw new IllegalArgumentException("Invalid DFS display limits");
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        int[] counter = {0};   // Dem tong so dinh da duyet
        int[] displayed = {0}; // Dem so dinh da hien thi
        dfsConditionalHelper(visited, start, maxVertices, startFromVertex, counter, displayed);
    }

    private void dfsConditionalHelper(boolean[] visited, int k, int max, int startFrom,
            int[] counter, int[] displayed) {
        if (displayed[0] >= max) {
            return;
        }
        visited[k] = true;
        counter[0]++;
        if (counter[0] >= startFrom) {
            fvisit(k);
            displayed[0]++;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && hasEdge(k, i) && displayed[0] < max) {
                dfsConditionalHelper(visited, i, max, startFrom, counter, displayed);
            }
        }
    }

    // --- Prim ghi vao outputBuffer ---
    void fPrim(int start) {
        if (n == 0) {
            println("No MST: graph is empty");
            return;
        }
        checkVertex(start);
        if (directed) {
            println("No MST: graph must be undirected");
            return;
        }
        if (!isConnected()) {
            println("No MST: graph is disconnected");
            return;
        }
        boolean[] inMST = new boolean[n];
        int[] key = new int[n];
        int[] parent = new int[n];
        long totalWeight = 0;

        for (int i = 0; i < n; i++) {
            inMST[i] = false;
            key[i] = DIST_INF;
            parent[i] = -1;
        }
        key[start] = 0;

        println("Prim's MST (start=" + v[start] + "):");

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minKey = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inMST[i] && key[i] < minKey) {
                    minKey = key[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            inMST[u] = true;

            if (parent[u] != -1) {
                println("  " + v[parent[u]] + "-" + v[u] + " w=" + a[parent[u]][u]);
                totalWeight += a[parent[u]][u];
            }

            for (int w = 0; w < n; w++) {
                if (!inMST[w] && hasEdge(u, w) && a[u][w] < key[w]) {
                    key[w] = a[u][w];
                    parent[w] = u;
                }
            }
        }
        println("Total: " + totalWeight);
    }

    // --- Kruskal ghi vao outputBuffer ---
    void fKruskal() {
        if (directed) {
            println("No MST: graph must be undirected");
            return;
        }
        if (n == 0) {
            println("No MST: graph is empty");
            return;
        }
        if (!isConnected()) {
            println("No MST: graph is disconnected");
            return;
        }
        println("Kruskal's MST:");

        List<int[]> edges = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (hasEdge(i, j)) {
                    edges.add(new int[]{a[i][j], i, j});
                }
            }
        }

        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                return Integer.compare(e1[0], e2[0]);
            }
        });

        int[] parentUF = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            parentUF[i] = i;
            rank[i] = 0;
        }

        long totalWeight = 0;
        int edgeCount = 0;

        for (int[] edge : edges) {
            if (edgeCount >= n - 1) {
                break;
            }
            int w = edge[0];
            int u = edge[1];
            int vv = edge[2];
            int rootU = find(parentUF, u);
            int rootV = find(parentUF, vv);
            if (rootU != rootV) {
                union(parentUF, rank, rootU, rootV);
                println("  " + v[u] + "-" + v[vv] + " w=" + w);
                totalWeight += w;
                edgeCount++;
            }
        }
        println("Total: " + totalWeight);
    }

    // --- Dijkstra ghi vao outputBuffer (chi duong di va chi phi, format de thi) ---
    void fDijkstra(int src, int dest) {
        checkVertex(src);
        checkVertex(dest);
        requireNonNegativeEdges("Dijkstra");
        int[] dist = new int[n];
        boolean[] inS = new boolean[n];
        int[] prev = new int[n];
        List<Integer> sOrder = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            dist[i] = DIST_INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            inS[u] = true;
            sOrder.add(u);

            for (int w = 0; w < n; w++) {
                if (!inS[w] && hasEdge(u, w)) {
                    int newDist = safeDistance((long) dist[u] + a[u][w]);
                    if (newDist < dist[w]) {
                        dist[w] = newDist;
                        prev[w] = u;
                    }
                }
            }
        }

        // Xay dung duong di
        List<Integer> path = new ArrayList<Integer>();
        int current = dest;
        while (current != -1) {
            path.add(0, current);
            current = prev[current];
        }

        // Dong 1: Cac dinh tren duong di ngan nhat
        if (dist[dest] == DIST_INF || path.isEmpty() || path.get(0) != src) {
            println("No path from " + v[src] + " to " + v[dest]);
        } else {
            for (int i = 0; i < path.size(); i++) {
                fvisit(path.get(i));
            }
            println();

            // Dong 2: 4 dinh cuoi cung duoc chon vao S kem nhan
            int startIdx = Math.max(0, sOrder.size() - 4);
            print("  " + v[src] + "|" + dist[dest]);
            for (int i = startIdx; i < sOrder.size(); i++) {
                print("  " + v[sOrder.get(i)]);
            }
            println();

            // Dong 3: Chi phi
            println("  Cost = " + dist[dest]);
        }
    }

    // --- Dijkstra hai diem, format cua de thi (2 truy van lien tiep) ---
    //     Vi du: Dijkstra(0->6) roi Dijkstra(1->5)
    void fDijkstra_exam(int src1, int dest1, int src2, int dest2) {
        checkVertex(src1);
        checkVertex(dest1);
        checkVertex(src2);
        checkVertex(dest2);
        println("--- Shortest path " + v[src1] + " -> " + v[dest1] + " ---");
        fDijkstra(src1, dest1);
        println();
        println("--- Shortest path " + v[src2] + " -> " + v[dest2] + " ---");
        fDijkstra(src2, dest2);
    }

    // --- Kiem tra do thi co huong hay vo huong ---
    boolean isDirected() {
        return directed;
    }

    // --- isUndirected - Alias tuong minh cho !isDirected() (thuong xuat hien trong de thi) ---
    //     Goi: if (graph.isUndirected()) ...
    boolean isUndirected() {
        return !isDirected();
    }

    // --- isEvenDegree - Kiem tra TAT CA DINH deu co BAC CHAN (dieu kien Euler circuit) ---
    //     Goi: if (graph.isEvenDegree()) ...
    boolean isEvenDegree() {
        for (int i = 0; i < n; i++) {
            if (calcDegree(i) % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    // --- Dem so canh ---
    int countEdges() {
        int count = 0;
        if (isDirected()) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (hasEdge(i, j)) {
                        count++;
                    }
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (hasEdge(i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // --- Kiem tra 2 dinh co ke nhau khong ---
    boolean isAdjacent(int u, int w) {
        checkVertex(u);
        checkVertex(w);
        return hasEdge(u, w);
    }

    // --- Lay trong so canh ---
    int getEdgeWeight(int u, int w) {
        checkVertex(u);
        checkVertex(w);
        if (hasEdge(u, w)) {
            return a[u][w];
        }
        return -1; // Khong co canh
    }

    // --- Chuyen index sang nhan dinh ---
    char getLabel(int index) {
        checkVertex(index);
        return v[index];
    }

    // --- Chuyen nhan dinh sang index ---
    int getIndex(char label) {
        for (int i = 0; i < n; i++) {
            if (v[i] == label) {
                return i;
            }
        }
        return -1;
    }

    // --- Tra ve chuoi tom tat thong tin do thi ---
    String getGraphInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph Info:\n");
        sb.append("  Vertices: ").append(n).append("\n");
        sb.append("  Edges: ").append(countEdges()).append("\n");
        sb.append("  Directed: ").append(isDirected()).append("\n");
        sb.append("  Connected: ").append(isConnected()).append("\n");
        sb.append("  Components: ").append(countConnectedComponents()).append("\n");
        sb.append("  Has cycle: ").append(hasCycle()).append("\n");
        sb.append("  Has Euler path: ").append(hasEulerPath()).append("\n");
        sb.append("  Has Euler circuit: ").append(hasEulerCircuit());
        return sb.toString();
    }

    // ========================================================================
    // XIII. CAC HAM f1, f2 THEO DUNG FORMAT DE THI
    // ========================================================================
    // Template f1 - BFS/DFS + xu ly them
    void f1() {
        loadData(1); // Doc du lieu (bo qua dong dau)
        // === BFS/DFS tu day ===
        fBFS_full(0);
        println();
        // === Chen code cua ban o day ===

        // ===========================
        printResult();
    }

    // Template f2 - Dijkstra hoac thuat toan khac
    void f2() {
        loadData(1);
        // === Chen code cua ban o day ===

        // ===========================
        printResult();
    }

    // ========================================================================
    // XIV. THIET LAP DO THI THU CONG (MANUAL GRAPH SETUP)
    // ========================================================================
    // Tao do thi tu ma tran cho san (de test khong can file)
    void setGraph(int[][] matrix, int numVertices) {
        setGraph(matrix, numVertices, false);
        directed = inferDirectedFromMatrix();
        calcAllDegrees();
    }

    void setGraph(int[][] matrix, int numVertices, boolean directed) {
        validateMatrix(matrix, numVertices);
        initStorage(numVertices);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = matrix[i][j];
            }
        }
        this.directed = directed;
        calcAllDegrees();
    }

    // Them canh (do thi vo huong)
    void addEdge(int u, int w, int weight) {
        checkVertex(u);
        checkVertex(w);
        checkEdgeWeight(weight);
        a[u][w] = weight;
        a[w][u] = weight;
        calcAllDegrees();
    }

    // Them canh (do thi co huong)
    void addDirectedEdge(int u, int w, int weight) {
        checkVertex(u);
        checkVertex(w);
        checkEdgeWeight(weight);
        a[u][w] = weight;
        directed = true;
        calcAllDegrees();
    }

    // Xoa canh (vo huong)
    void removeEdge(int u, int w) {
        checkVertex(u);
        checkVertex(w);
        a[u][w] = 0;
        a[w][u] = 0;
        calcAllDegrees();
    }

    // Xoa canh (co huong)
    void removeDirectedEdge(int u, int w) {
        checkVertex(u);
        checkVertex(w);
        a[u][w] = 0;
        calcAllDegrees();
    }

    // Khoi tao do thi rong voi n dinh
    void initEmpty(int numVertices) {
        initEmpty(numVertices, false);
    }

    void initEmpty(int numVertices, boolean directed) {
        initStorage(numVertices);
        this.directed = directed;
    }

    void setDirected(boolean directed) {
        this.directed = directed;
    }

    // ========================================================================
    // XV. IN KẾT QUẢ DẠNG SỐ (NUMERIC OUTPUT) & DẤU PHẨY (FORMAT ĐỀ THI MỚI)
    // ========================================================================
    // In mot dinh dang so
    void fvisitNumeric(int i, boolean isLast) {
        print(i);
        if (!isLast) {
            print(", ");
        }
    }

    // BFS xuat ra Dang So kem Dau Phay
    void fBFS_Numeric(int start) {
        checkVertex(start);
        boolean[] en = new boolean[n];
        for (int i = 0; i < n; i++) {
            en[i] = false;
        }

        LinkedList<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        q.addLast(start);
        en[start] = true;

        while (!q.isEmpty()) {
            int r = q.removeFirst();
            result.add(r);
            for (int j = 0; j < n; j++) {
                if (!en[j] && hasEdge(r, j)) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }

        // In ra ngan cach boi dau phay, khong co dau phay o phan tu cuoi
        for (int i = 0; i < result.size(); i++) {
            fvisitNumeric(result.get(i), i == result.size() - 1);
        }
    }

    // DFS xuat ra Dang So kem Dau Phay
    void fDFS_Numeric(int start) {
        checkVertex(start);
        boolean[] en = new boolean[n];
        for (int i = 0; i < n; i++) {
            en[i] = false;
        }
        ArrayList<Integer> result = new ArrayList<>();
        fDFS_Numeric_Rec(start, en, result);

        for (int i = 0; i < result.size(); i++) {
            fvisitNumeric(result.get(i), i == result.size() - 1);
        }
    }

    void fDFS_Numeric_Rec(int u, boolean[] en, ArrayList<Integer> result) {
        en[u] = true;
        result.add(u);
        for (int i = 0; i < n; i++) {
            if (!en[i] && hasEdge(u, i)) {
                fDFS_Numeric_Rec(i, en, result);
            }
        }
    }

    // ========================================================================
    // XVI. THUẬT TOÁN NÂNG CAO (TOPOLOGICAL SORT, BIPARTITE)
    // ========================================================================
    // Topological Sort (Sap xep Topo) dung mang ban bac vao (Kahn's Algorithm)
    void topologicalSortNumeric() {
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hasEdge(i, j)) {
                    inDegree[j]++;
                }
            }
        }

        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.addLast(i);
            }
        }

        ArrayList<Integer> topoOrder = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.removeFirst();
            topoOrder.add(u);

            for (int v = 0; v < n; v++) {
                if (hasEdge(u, v)) {
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        q.addLast(v);
                    }
                }
            }
        }

        if (topoOrder.size() != n) {
            println("Graph contains a cycle! Topological sort not possible.");
        } else {
            for (int i = 0; i < topoOrder.size(); i++) {
                fvisitNumeric(topoOrder.get(i), i == topoOrder.size() - 1);
            }
        }
    }

    // Kiem tra do thi hai phia (Bipartite Graph)
    boolean isBipartite(int start) {
        checkVertex(start);
        int[] color = new int[n];
        Arrays.fill(color, -1);

        // Kiem tra component chua start truoc, sau do kiem tra tat ca component con lai.
        for (int pass = -1; pass < n; pass++) {
            int source = pass < 0 ? start : pass;
            if (color[source] != -1) {
                continue;
            }
            LinkedList<Integer> q = new LinkedList<Integer>();
            q.addLast(source);
            color[source] = 1;

            while (!q.isEmpty()) {
                int u = q.removeFirst();
                if (hasEdge(u, u)) {
                    return false;
                }
                for (int vv = 0; vv < n; vv++) {
                    if (hasUndirectedConnection(u, vv)) {
                        if (color[vv] == -1) {
                            color[vv] = 1 - color[u];
                            q.addLast(vv);
                        } else if (color[vv] == color[u]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }



    // ========================================================================
    // XVIII. CÁC THUẬT TOÁN ĐỈNH CAO (ADVANCED EXAM 40% EXPANSION)
    // ========================================================================

    // 1. Tarjan's Strongly Connected Components (SCC)
    int time = 0;
    void tarjanSCC() {
        int[] disc = new int[n];
        int[] low = new int[n];
        boolean[] stackMember = new boolean[n];
        LinkedList<Integer> st = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            disc[i] = -1;
            low[i] = -1;
            stackMember[i] = false;
        }
        time = 0;
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                tarjanSCCUtil(i, disc, low, st, stackMember);
            }
        }
    }

    void tarjanSCCUtil(int u, int[] disc, int[] low, LinkedList<Integer> st, boolean[] stackMember) {
        disc[u] = time;
        low[u] = time;
        time++;
        stackMember[u] = true;
        st.addLast(u);
        
        for (int v = 0; v < n; v++) {
            if (hasEdge(u, v)) {
                if (disc[v] == -1) {
                    tarjanSCCUtil(v, disc, low, st, stackMember);
                    low[u] = Math.min(low[u], low[v]);
                } else if (stackMember[v]) {
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }
        
        int w = -1;
        if (low[u] == disc[u]) {
            while (w != u) {
                w = st.removeLast();
                print(v[w] + " ");
                stackMember[w] = false;
            }
            println();
        }
    }

    // 2. Hamiltonian Path Check (Quy hoach dong / Quay lui)
    boolean hasHamiltonianPath() {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = true;
            if (hamiltonianPathUtil(i, visited, 1)) return true;
            visited[i] = false;
        }
        return false;
    }

    boolean hamiltonianPathUtil(int u, boolean[] visited, int count) {
        if (count == n) return true;
        for (int v = 0; v < n; v++) {
            if (hasEdge(u, v) && !visited[v]) {
                visited[v] = true;
                if (hamiltonianPathUtil(v, visited, count + 1)) return true;
                visited[v] = false;
            }
        }
        return false;
    }

    // 3. Articulation Points (Dinh khop)
    void findArticulationPoints() {
        if (directed) {
            throw new IllegalStateException("Articulation points require an undirected graph");
        }
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        boolean[] ap = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            disc[i] = -1;
            parent[i] = -1;
        }
        time = 0;
        
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                apUtil(i, disc, low, parent, ap);
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (ap[i]) print(v[i] + " ");
        }
        println();
    }

    void apUtil(int u, int[] disc, int[] low, int[] parent, boolean[] ap) {
        int children = 0;
        disc[u] = low[u] = ++time;
        for (int v = 0; v < n; v++) {
            if (hasEdge(u, v)) {
                if (disc[v] == -1) {
                    children++;
                    parent[v] = u;
                    apUtil(v, disc, low, parent, ap);
                    low[u] = Math.min(low[u], low[v]);
                    if (parent[u] == -1 && children > 1) ap[u] = true;
                    if (parent[u] != -1 && low[v] >= disc[u]) ap[u] = true;
                } else if (v != parent[u]) {
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }
    }

    // ============================================================================================================================
    //  [U] TÍNH NĂNG TÍCH HỢP TỪ PE_PHUC (OUTPUT FORMATTING CHUYÊN BIỆT)
    // ============================================================================================================================

    /**
     * [U1] BFS / DFS Format Không phẩy (Space-separated)
     */
    void fBFS_PEPhuc(int start) {
        checkVertex(start);
        boolean[] en = new boolean[n];
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(start);
        en[start] = true;
        
        println("BFS Traversal:");
        boolean first = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            print((first ? "" : " ") + r);
            first = false;
            for (int i = 0; i < n; i++) {
                if (!en[i] && hasEdge(r, i)) {
                    q.addLast(i);
                    en[i] = true;
                }
            }
        }
        println();
    }

    void fDFS_PEPhuc(int start) {
        checkVertex(start);
        boolean[] en = new boolean[n];
        println("DFS Traversal:");
        fDFS_PEPhuc_Rec_Formatted(start, en, new boolean[]{true});
        println();
    }

    private void fDFS_PEPhuc_Rec_Formatted(int u, boolean[] en, boolean[] first) {
        en[u] = true;
        print((first[0] ? "" : " ") + u);
        first[0] = false;
        for (int i = 0; i < n; i++) {
            if (!en[i] && hasEdge(u, i)) {
                fDFS_PEPhuc_Rec_Formatted(i, en, first);
            }
        }
    }

    void fDFS_PEPhuc_Rec(int u, boolean[] en) {
        en[u] = true;
        print(u + " ");
        for (int i = 0; i < n; i++) {
            if (!en[i] && hasEdge(u, i)) {
                fDFS_PEPhuc_Rec(i, en);
            }
        }
    }

    /**
     * [U2] Dijkstra PE Format - In theo dải dọc chuẩn PE_Phuc
     */
    void dijkstra_PEPhuc(int src) {
        checkVertex(src);
        requireNonNegativeEdges("Dijkstra");
        int[] d = new int[n];
        boolean[] s = new boolean[n];
        for (int i = 0; i < n; i++) {
            d[i] = DIST_INF;
            s[i] = false;
        }
        d[src] = 0;
        
        for (int i = 0; i < n; i++) {
            int u = -1;
            int minD = DIST_INF;
            for (int j = 0; j < n; j++) {
                if (!s[j] && d[j] < minD) {
                    minD = d[j];
                    u = j;
                }
            }
            if (u == -1) break;
            s[u] = true;
            for (int v = 0; v < n; v++) {
                int candidate = safeDistance((long) d[u] + a[u][v]);
                if (!s[v] && hasEdge(u, v) && candidate < d[v]) {
                    d[v] = candidate;
                }
            }
        }
        
        println("Shorted Distance:");
        for (int i = 0; i < n; i++) {
            println(i + " " + (d[i] == DIST_INF ? "INF" : d[i]));
        }
    }

    /**
     * [U3] Prim MST PE Format - In theo form chi tiết chuẩn PE_Phuc
     */
    void prim_PEPhuc(int start) {
        if (n == 0) {
            println("No MST: graph is empty");
            return;
        }
        checkVertex(start);
        if (directed) {
            println("No MST: graph must be undirected");
            return;
        }
        if (!isConnected()) {
            println("No MST: graph is disconnected");
            return;
        }
        int[] key = new int[n];
        int[] parent = new int[n];
        boolean[] mstSet = new boolean[n];
        
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            key[i] = DIST_INF;
            mstSet[i] = false;
        }
        key[start] = 0;
        parent[start] = -1;
        
        for (int count = 0; count < n - 1; count++) {
            int u = -1;
            int minKey = DIST_INF;
            for (int i = 0; i < n; i++) {
                if (!mstSet[i] && key[i] < minKey) {
                    minKey = key[i];
                    u = i;
                }
            }
            if (u == -1) break;
            mstSet[u] = true;
            
            for (int v = 0; v < n; v++) {
                if (hasEdge(u, v) && !mstSet[v] && a[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = a[u][v];
                }
            }
        }
        
        println("Prim's MST (starting from " + start + "):");
        long totalWeight = 0;
        for (int i = 0; i < n; i++) {
            if (i != start && parent[i] != -1) {
                println("  Edge " + parent[i] + "-" + i + " (weight=" + a[i][parent[i]] + ")");
                totalWeight += a[i][parent[i]];
            }
        }
        println("Total MST weight: " + totalWeight);
    }

    /**
     * [U4] Connected Components PE Format
     */
    void countConnectedComponents_PEPhuc() {
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                fDFS_PEPhuc_Rec_Silent(i, visited);
                count++;
            }
        }
        println("Number of Connected Components: " + count);
    }

    void fDFS_PEPhuc_Rec_Silent(int u, boolean[] en) {
        en[u] = true;
        for (int i = 0; i < n; i++) {
            if (!en[i] && hasUndirectedConnection(u, i)) {
                fDFS_PEPhuc_Rec_Silent(i, en);
            }
        }
    }
}
