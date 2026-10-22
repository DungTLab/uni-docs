package q3;
// ==================================================================================================================================
//  Graph.java — Do thi bieu dien bang MA TRAN KE (Adjacency Matrix) cho CSD201
//  => I/O pattern: Scanner doc file, StringBuilder + FileWriter ghi file (giong Q1 template).
//  => Ma tran ke a[i][j]: gia tri 0 = khong canh; gia tri khac 0 = trong so canh (i, j).
//  => Dinh duoc danh so tu 0 den n-1; ten dinh la v[i] = 'A', 'B', 'C', ...
//  => INF = 99: gia tri vo cuc dung trong cac thuat toan duong ngan nhat.
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
    static final int INF = 99; // Gia tri vo cuc dung trong de thi

    String fi = "input.txt"; // Ten file dau vao (thay theo de thi)
    String fo = "output.txt"; // Ten file dau ra (thay theo de thi)
    StringBuilder outputBuffer = new StringBuilder();

    // setOutputBuffer - Cho phep dung chung outputBuffer voi Q1.java de de dang tich hop
    void setOutputBuffer(StringBuilder sb) {
        this.outputBuffer = sb;
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
    void print(Object o) { outputBuffer.append(o); }
    void println(Object o) { outputBuffer.append(o).append("\n"); }
    void println() { outputBuffer.append("\n"); }

    // setFile - doi ten file theo tham so truyen vao (format de thi)
    void setFile(String[] args) {
        if (args.length >= 2) { fi = args[0]; fo = args[1]; }
    }

    // printResult - Ghi outputBuffer ra file dau ra
    void printResult() {
        try {
            FileWriter fw = new FileWriter(fo);
            fw.write(outputBuffer.toString().trim());
            fw.flush();
            fw.close();
        } catch (IOException e) {}
    }

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================
    Graph() {
        v = "ABCDEFGHIJKLMNOP".toCharArray();
        deg = new int[20];
        a = new int[20][20];
        n = 0;
    }

    // ========================================================================
    // I. I/O VA HIEN THI
    // ========================================================================

    // 1. loadData - Doc ma tran ke tu file fi
    //    k = so dong can bo qua truoc khi doc
    //    Format: dong dau la n, sau do n dong ma tran trong so
    void loadData(int k) {
        try (Scanner sc = new Scanner(new File(fi))) {
            for (int i = 0; i < k; i++) if (sc.hasNextLine()) sc.nextLine();
            loadData(sc);
        } catch (FileNotFoundException e) {}
    }

    // 1b. loadEdgeList - Doc do thi tu danh sach canh
    //     k = so dong bo qua; oneIndexed = true neu dinh danh so tu 1
    //     Format: dong dau la "n m", sau do m dong "u v" hoac "u v w"
    void loadEdgeList(int k, boolean oneIndexed) {
        try (Scanner sc = new Scanner(new File(fi))) {
            for (int i = 0; i < k; i++) if (sc.hasNextLine()) sc.nextLine();
            loadEdgeList(sc, oneIndexed);
        } catch (FileNotFoundException e) {}
    }

    // 1c. loadData - Doc ma tran ke truc tiep tu doi tuong Scanner dang hoat dong
    //     Format: gia tri dau tien doc duoc la n, sau do doc ma tran ke n x n
    void loadData(Scanner sc) {
        a = new int[20][20];
        if (sc.hasNextInt()) {
            n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (sc.hasNextInt()) {
                        a[i][j] = sc.nextInt();
                    }
                }
            }
        }
    }

    // 1d. loadEdgeList - Doc danh sach canh truc tiep tu doi tuong Scanner dang hoat dong
    //     Format: hai gia tri dau tien la n va m, sau do la m dong chua u v (hoac u v w)
    void loadEdgeList(Scanner sc, boolean oneIndexed) {
        a = new int[20][20];
        if (sc.hasNextInt()) {
            n = sc.nextInt();
            int m = sc.nextInt();
            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int vv = sc.nextInt();
                if (oneIndexed) { u--; vv--; }
                int w = 1;
                if (sc.hasNextInt()) w = sc.nextInt();
                a[u][vv] = w;
                a[vv][u] = w; // Bo dong nay neu do thi co huong
            }
        }
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
            if (i < n - 1) sb.append("\n");
        }
        return sb.toString();
    }

    // 3. fDisplayAdj - Ghi ma tran ke ra file (fdispAdj trong de thi)
    void fdispAdj() {
        int i, j;
        println("n = " + n);
        for (i = 0; i < n; i++) {
            println();
            for (j = 0; j < n; j++) print("  " + a[i][j]);
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
    void fvisit(int i) { print("  " + v[i]); }

    // 5. fvisitDeg - Ghi dinh kem bac cua no ra outputBuffer
    void fvisitDeg(int i) { print("  " + v[i] + "(" + deg[i] + ")"); }

    // ========================================================================
    // II. BFS - DUYET THEO CHIEU RONG
    // ========================================================================

    // 6. BFS tra ve String - duyet tu dinh start
    String BFS(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] en = new boolean[20];
        for (int i = 0; i < n; i++) en[i] = false;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            sb.append("  ").append(v[r]);
            for (int j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0 && a[r][j] < INF) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
        return sb.toString();
    }

    // 7. BFS voi mang visited tu ben ngoai - tra ve String
    String BFS(boolean[] en, int start) {
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            sb.append("  ").append(v[r]);
            for (int j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0 && a[r][j] < INF) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
        return sb.toString();
    }

    // 8. fBFS - BFS ghi vao outputBuffer (breadth trong de thi)
    void fBFS(int start) {
        boolean[] en = new boolean[20];
        for (int i = 0; i < n; i++) en[i] = false;
        fBFS(en, start);
    }

    // 9. fBFS voi mang visited tu ben ngoai - ghi vao outputBuffer
    void fBFS(boolean[] en, int start) {
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            fvisit(r);
            for (int j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0 && a[r][j] < INF) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
    }

    // 10. BFS_full - BFS duyet het tat ca thanh phan lien thong
    String BFS_full(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] en = new boolean[20];
        for (int i = 0; i < n; i++) en[i] = false;
        sb.append(BFS(en, start));
        for (int i = 0; i < n; i++) {
            if (!en[i]) sb.append(BFS(en, i));
        }
        return sb.toString();
    }

    // 11. fBFS_full - BFS duyet het ghi vao outputBuffer (breadth(int k, f) trong de thi)
    void fBFS_full(int start) {
        boolean[] en = new boolean[20];
        for (int i = 0; i < n; i++) en[i] = false;
        fBFS(en, start);
        for (int i = 0; i < n; i++) {
            if (!en[i]) fBFS(en, i);
        }
    }

    // 12. fBFS_conditional - BFS chi hien thi maxVertices dinh bat dau tu vi tri startFrom
    //     Vi du: Exam 1 f1 - hien thi 5 dinh bat dau tu dinh thu 3
    //     startFromVertex: 1-indexed (dinh thu may trong ket qua BFS)
    void fBFS_conditional(int start, int maxVertices, int startFromVertex) {
        boolean[] en = new boolean[20];
        for (int i = 0; i < n; i++) en[i] = false;
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
                if (!en[j] && a[r][j] > 0 && a[r][j] < INF) {
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
    //      Goi: boolean[] visited = new boolean[20]; boolean[] enqueued = new boolean[20];
    //           graph.fBFS_dual(visited, enqueued, 0);
    void fBFS_dual(boolean[] visited, boolean[] enqueued, int start) {
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
                if (!visited[j] && !enqueued[j] && a[r][j] > 0 && a[r][j] < INF) {
                    q.addLast(j);
                    enqueued[j] = true;
                }
            }
        }
    }

    // 12c. fBFS_dual_full - BFS dual duyet TAT CA thanh phan lien thong
    void fBFS_dual_full(int start) {
        boolean[] visited  = new boolean[20];
        boolean[] enqueued = new boolean[20];
        for (int i = 0; i < n; i++) { visited[i] = false; enqueued[i] = false; }
        fBFS_dual(visited, enqueued, start);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) fBFS_dual(visited, enqueued, i);
        }
    }

    // BFS_conditional tra ve String (phien ban khong ghi file)
    String BFS_conditional(int start, int maxVertices, int startFromVertex) {
        StringBuilder sb = new StringBuilder();
        boolean[] en = new boolean[20];
        for (int i = 0; i < n; i++) en[i] = false;
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
                if (!en[j] && a[r][j] > 0 && a[r][j] < INF) {
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
        sb.append(" ").append(k);
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0 && a[k][i] < INF) {
                dfsRecursive(visited, i, sb);
            }
        }
    }

    // 13. DFS de quy tra ve String
    String DFS(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        dfsRecursive(visited, start, sb);
        return sb.toString();
    }

    // 14. DFS voi mang visited tu ben ngoai - tra ve String
    String DFS(boolean[] visited, int start) {
        StringBuilder sb = new StringBuilder();
        dfsRecursive(visited, start, sb);
        return sb.toString();
    }

    // 15. fDFS - DFS de quy ghi vao outputBuffer (depth trong de thi)
    void fDFS(int start) {
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        fDFS(visited, start);
    }

    // 16. fDFS voi mang visited tu ben ngoai - ghi vao outputBuffer
    void fDFS(boolean[] visited, int start) {
        fvisit(start);
        visited[start] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[start][i] > 0 && a[start][i] < INF) {
                fDFS(visited, i);
            }
        }
    }

    // 17. DFS_full - DFS duyet het tat ca thanh phan lien thong
    String DFS_full(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        dfsRecursive(visited, start, sb);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) dfsRecursive(visited, i, sb);
        }
        return sb.toString();
    }

    // 18. fDFS_full - DFS duyet het ghi vao outputBuffer (depth(int k, f) trong de thi)
    void fDFS_full(int start) {
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        fDFS(visited, start);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) fDFS(visited, i);
        }
    }

    // 19. DFS_iterative - DFS dung stack (khong de quy)
    String DFS_iterative(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        stack.addLast(start);
        while (!stack.isEmpty()) {
            int k = stack.removeLast();
            if (!visited[k]) {
                visited[k] = true;
                sb.append("  ").append(v[k]);
                // Day cac dinh ke vao stack theo thu tu nguoc de dam bao duyet dung thu tu
                for (int i = n - 1; i >= 0; i--) {
                    if (!visited[i] && a[k][i] > 0 && a[k][i] < INF) {
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
    //     Voi do thi co trong so: dem so canh ke (a[vertex][j] > 0 va != INF)
    int calcDegree(int vertex) {
        int degree = 0;
        for (int j = 0; j < n; j++) {
            if (a[vertex][j] > 0 && a[vertex][j] < INF) {
                degree++;
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
            if (i < n - 1) sb.append("  ");
        }
        return sb.toString();
    }

    // ========================================================================
    // V. DIJKSTRA - DUONG DI NGAN NHAT
    // ========================================================================

    // 23. dijkstra(src, dest) - Tim duong di ngan nhat tu src den dest
    //     Tra ve String gom: duong di va chi phi
    //     Ma tran trong so: a[i][j] = 0 => khong co canh (hoac chinh no)
    //                        a[i][j] = INF (99) => khong co canh
    //                        a[i][j] > 0 && < INF => trong so canh
    String dijkstra(int src, int dest) {
        int[] dist = new int[20];       // Khoang cach ngan nhat tu src
        boolean[] inS = new boolean[20]; // Tap S - da xac dinh duong ngan nhat
        int[] prev = new int[20];        // Dinh truoc do tren duong di ngan nhat
        List<Integer> sOrder = new ArrayList<Integer>(); // Thu tu cac dinh duoc chon vao S

        // Khoi tao
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;

        for (int count = 0; count < n; count++) {
            // Tim dinh u chua trong S co dist nho nhat
            int u = -1;
            int minDist = INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) break; // Khong con dinh nao co the den duoc
            inS[u] = true;
            sOrder.add(u);

            // Cap nhat khoang cach cac dinh ke cua u
            for (int w = 0; w < n; w++) {
                if (!inS[w] && a[u][w] > 0 && a[u][w] < INF) {
                    int newDist = dist[u] + a[u][w];
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

        // Dong 1: Duong di ngan nhat
        sb.append("Shortest path from ").append(v[src]).append(" to ").append(v[dest]).append(": ");
        if (dist[dest] >= INF || path.get(0) != src) {
            sb.append("No path found");
        } else {
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) sb.append(" ");
                sb.append(v[path.get(i)]);
            }
            sb.append("\n");

            // Dong 2: Chi phi duong di
            sb.append("Cost: ").append(dist[dest]).append("\n");

            // Dong 3: 4 dinh cuoi cung duoc chon vao tap S (thuong hoi trong de thi)
            sb.append("Last 4 vertices selected into S: ");
            int start = Math.max(0, sOrder.size() - 4);
            for (int i = start; i < sOrder.size(); i++) {
                if (i > start) sb.append(" ");
                sb.append(v[sOrder.get(i)]);
            }
            sb.append("\n");

            // Dong 4: Thu tu cac dinh duoc chon vao S voi khoang cach
            sb.append("S selection order: ");
            for (int i = 0; i < sOrder.size(); i++) {
                if (i > 0) sb.append(" ");
                sb.append(v[sOrder.get(i)]).append("|").append(dist[sOrder.get(i)]);
            }
        }
        return sb.toString();
    }

    // 24. dijkstra(src) - Dijkstra tu src den TAT CA cac dinh, tra ve mang dist
    int[] dijkstra(int src) {
        int[] dist = new int[n];
        boolean[] inS = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            inS[i] = false;
        }
        dist[src] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) break;
            inS[u] = true;
            for (int w = 0; w < n; w++) {
                if (!inS[w] && a[u][w] > 0 && a[u][w] < INF) {
                    int newDist = dist[u] + a[u][w];
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
        int[] dist = new int[20];
        boolean[] inS = new boolean[20];
        int[] prev = new int[20];
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) break;
            inS[u] = true;
            for (int w = 0; w < n; w++) {
                if (!inS[w] && a[u][w] > 0 && a[u][w] < INF) {
                    int newDist = dist[u] + a[u][w];
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
        int[] dist = new int[20];
        boolean[] inS = new boolean[20];
        int[] prev = new int[20];
        List<Integer> sOrder = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;

        // Header: bang theo doi qua trinh Dijkstra
        println("Dijkstra from " + v[src] + " to " + v[dest]);

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) break;
            inS[u] = true;
            sOrder.add(u);

            // Ghi buoc hien tai
            print("  Select " + v[u] + " (dist=" + dist[u] + "), S={");
            for (int i = 0; i < sOrder.size(); i++) {
                if (i > 0) print(",");
                print("" + v[sOrder.get(i)]);
            }
            println("}");

            for (int w = 0; w < n; w++) {
                if (!inS[w] && a[u][w] > 0 && a[u][w] < INF) {
                    int newDist = dist[u] + a[u][w];
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
        if (dist[dest] >= INF || path.get(0) != src) {
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
        int[] dist = new int[20];
        boolean[] inS = new boolean[20];
        int[] prev = new int[20];
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;
        int step = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) break;
            inS[u] = true;
            step++;

            // Cap nhat khoang cach
            for (int w = 0; w < n; w++) {
                if (!inS[w] && a[u][w] > 0 && a[u][w] < INF) {
                    int nd = dist[u] + a[u][w];
                    if (nd < dist[w]) { dist[w] = nd; prev[w] = u; }
                }
            }

            // In trang thai sau buoc nay
            print("Step " + step + ": Select " + v[u] + ". d[]= [");
            for (int i = 0; i < n; i++) {
                print(dist[i] == INF ? "99" : "" + dist[i]);
                if (i < n - 1) print(" ");
            }
            println("]");
        }
    }

    // 26c. fDijkstraDistances - In KHOANG CACH TICH LUY tren duong di ngan nhat
    //      Format output: 0 1 9 11 13
    //      Goi: graph.fDijkstraDistances(0, 5);
    void fDijkstraDistances(int src, int dest) {
        int[] dist = new int[20];
        boolean[] inS = new boolean[20];
        int[] prev = new int[20];
        for (int i = 0; i < n; i++) {
            dist[i] = INF; inS[i] = false; prev[i] = -1;
        }
        dist[src] = 0;
        for (int count = 0; count < n; count++) {
            int u = -1, minDist = INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) { minDist = dist[i]; u = i; }
            }
            if (u == -1) break;
            inS[u] = true;
            for (int w = 0; w < n; w++) {
                if (!inS[w] && a[u][w] > 0 && a[u][w] < INF) {
                    int nd = dist[u] + a[u][w];
                    if (nd < dist[w]) { dist[w] = nd; prev[w] = u; }
                }
            }
        }
        if (dist[dest] >= INF) { println("No path"); return; }
        // Xay dung duong di
        List<Integer> path = new ArrayList<Integer>();
        int cur = dest;
        while (cur != -1) { path.add(0, cur); cur = prev[cur]; }
        // In khoang cach tich luy
        for (int i = 0; i < path.size(); i++) {
            print("" + dist[path.get(i)]);
            if (i < path.size() - 1) print(" ");
        }
        println();
    }

    // 26d. fDijkstraFormatted - In format (khoangcach)Dinh -> (khoangcach)Dinh
    //      Format output: (0)A -> (1)D -> (2)E -> (4)F
    //      Goi: graph.fDijkstraFormatted(0, 5);
    void fDijkstraFormatted(int src, int dest) {
        int[] dist = new int[20];
        boolean[] inS = new boolean[20];
        int[] prev = new int[20];
        for (int i = 0; i < n; i++) {
            dist[i] = INF; inS[i] = false; prev[i] = -1;
        }
        dist[src] = 0;
        for (int count = 0; count < n; count++) {
            int u = -1, minDist = INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) { minDist = dist[i]; u = i; }
            }
            if (u == -1) break;
            inS[u] = true;
            for (int w = 0; w < n; w++) {
                if (!inS[w] && a[u][w] > 0 && a[u][w] < INF) {
                    int nd = dist[u] + a[u][w];
                    if (nd < dist[w]) { dist[w] = nd; prev[w] = u; }
                }
            }
        }
        if (dist[dest] >= INF) { println("No path"); return; }
        List<Integer> path = new ArrayList<Integer>();
        int cur = dest;
        while (cur != -1) { path.add(0, cur); cur = prev[cur]; }
        // In dang (chi_phi)Dinh -> ...
        for (int i = 0; i < path.size(); i++) {
            int p = path.get(i);
            print("(" + dist[p] + ")" + v[p]);
            if (i < path.size() - 1) print(" -> ");
        }
        println();
    }

    // ========================================================================
    // VI. KIEM TRA LIEN THONG (CONNECTIVITY)
    // ========================================================================

    // 27. isConnected - Kiem tra do thi co lien thong hay khong
    //     Su dung BFS tu dinh 0, neu tat ca cac dinh deu duoc tham thi lien thong
    boolean isConnected() {
        if (n == 0) return true;
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        // BFS tu dinh 0
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(0);
        visited[0] = true;
        int countVisited = 1;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            for (int j = 0; j < n; j++) {
                if (!visited[j] && a[r][j] > 0 && a[r][j] < INF) {
                    visited[j] = true;
                    q.addLast(j);
                    countVisited++;
                }
            }
        }
        return countVisited == n;
    }

    // 28. countConnectedComponents - Dem so thanh phan lien thong
    int countConnectedComponents() {
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
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
                        if (!visited[j] && a[r][j] > 0 && a[r][j] < INF) {
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
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;

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
                        if (!visited[j] && a[r][j] > 0 && a[r][j] < INF) {
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
        boolean[] pushed = new boolean[20];
        for (int i = 0; i < n; i++) pushed[i] = false;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int k = 0;
        int i, r;
        while (true) {
            // Tim dinh chua duoc duyet
            i = 0;
            while (i < n && pushed[i]) i++;
            if (i == n) break;
            // BFS/DFS tu dinh i
            stack.clear();
            stack.addLast(i);
            pushed[i] = true;
            k++;
            while (!stack.isEmpty()) {
                r = stack.removeLast(); // DFS: removeLast; BFS: removeFirst
                for (int j = 0; j < n; j++) {
                    if (j == r) continue;
                    if (!pushed[j] && a[r][j] > 0 && a[r][j] < INF) {
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
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        int comp = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                comp++;
                print("Component " + comp + ": ");
                // BFS trong thanh phan nay
                LinkedList<Integer> q = new LinkedList<Integer>();
                q.addLast(i); visited[i] = true;
                while (!q.isEmpty()) {
                    int r = q.removeFirst();
                    fvisit(r);
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && a[r][j] > 0 && a[r][j] < INF) {
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
        StringBuilder sb = new StringBuilder();
        boolean[] inMST = new boolean[20];
        int[] key = new int[20];     // Trong so nho nhat de ket noi voi MST
        int[] parent = new int[20];  // Dinh cha trong MST
        int totalWeight = 0;

        for (int i = 0; i < n; i++) {
            inMST[i] = false;
            key[i] = INF;
            parent[i] = -1;
        }
        key[start] = 0;

        sb.append("Prim's MST (starting from ").append(v[start]).append("):\n");

        for (int count = 0; count < n; count++) {
            // Tim dinh u chua trong MST co key nho nhat
            int u = -1;
            int minKey = INF;
            for (int i = 0; i < n; i++) {
                if (!inMST[i] && key[i] < minKey) {
                    minKey = key[i];
                    u = i;
                }
            }
            if (u == -1) break; // Khong con dinh nao co the ket noi
            inMST[u] = true;

            if (parent[u] != -1) {
                sb.append("  Edge ").append(v[parent[u]]).append("-").append(v[u]);
                sb.append(" (weight=").append(a[parent[u]][u]).append(")\n");
                totalWeight += a[parent[u]][u];
            }

            // Cap nhat key cac dinh ke
            for (int w = 0; w < n; w++) {
                if (!inMST[w] && a[u][w] > 0 && a[u][w] < INF && a[u][w] < key[w]) {
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
        StringBuilder sb = new StringBuilder();
        sb.append("Kruskal's MST:\n");

        // Thu thap tat ca cac canh
        // Moi canh: {weight, u, v}
        List<int[]> edges = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i][j] > 0 && a[i][j] < INF) {
                    edges.add(new int[]{a[i][j], i, j});
                }
            }
        }

        // Sap xep canh theo trong so tang dan
        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                return e1[0] - e2[0];
            }
        });

        // Union-Find
        int[] parentUF = new int[20];
        int[] rank = new int[20];
        for (int i = 0; i < n; i++) {
            parentUF[i] = i;
            rank[i] = 0;
        }

        int totalWeight = 0;
        int edgeCount = 0;

        for (int[] edge : edges) {
            if (edgeCount >= n - 1) break;
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
        if (!isConnectedNonIsolated()) return false;
        calcAllDegrees();
        int oddCount = 0;
        for (int i = 0; i < n; i++) {
            if (deg[i] % 2 != 0) oddCount++;
        }
        return (oddCount == 0 || oddCount == 2);
    }

    // 33. hasEulerCircuit - Kiem tra do thi co chu trinh Euler hay khong
    //     Tat ca cac dinh co bac > 0 deu co bac chan, va do thi lien thong
    boolean hasEulerCircuit() {
        if (!isConnectedNonIsolated()) return false;
        calcAllDegrees();
        for (int i = 0; i < n; i++) {
            if (deg[i] > 0 && deg[i] % 2 != 0) return false;
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
        boolean conn  = isConnected();
        boolean even  = isEvenDegree();
        // In tung dieu kien
        println(undir ? "The graph is undirected."
                      : "The graph is directed.");
        println(conn  ? "The graph is connected."
                      : "The graph is not connected.");
        println(even  ? "All vertices have even degree."
                      : "The graph has a vertex with odd degree.");
        if (undir && conn && even)
            println("Conditions for Euler's cycle are satisfied.");
        else
            println("Conditions for Euler's cycle are not satisfied.");
    }

    // 33c. hasIsolated - Kiem tra co dinh CO LAP (bac = 0) hay khong
    //      Dung trong dieu kien Euler Path/Circuit
    //      Goi: boolean b = graph.hasIsolated();
    boolean hasIsolated() {
        for (int i = 0; i < n; i++) {
            if (calcDegree(i) == 0) return true;
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
        if (startVertex == -1) return true; // Khong co canh nao

        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(startVertex);
        visited[startVertex] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            for (int j = 0; j < n; j++) {
                if (!visited[j] && a[r][j] > 0 && a[r][j] < INF) {
                    visited[j] = true;
                    q.addLast(j);
                }
            }
        }
        // Kiem tra tat ca dinh co bac > 0 deu da duoc tham
        for (int i = 0; i < n; i++) {
            if (deg[i] > 0 && !visited[i]) return false;
        }
        return true;
    }

    // 34. findEulerPath - Tim duong di Euler (thuat toan Hierholzer)
    //     Su dung ban sao ma tran ke de xoa canh khi di qua
    String findEulerPath(int start) {
        if (!hasEulerPath()) return "No Euler path exists";

        // Tao ban sao ma tran ke (chi giu 0/1 cho bai toan Euler)
        int[][] tempA = new int[20][20];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] > 0 && a[i][j] < INF) {
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
                    tempA[j][curr]--; // Do thi vo huong
                    found = true;
                    break;
                }
            }
            if (!found) {
                path.add(stack.removeLast());
            }
        }

        // Dao nguoc path
        StringBuilder sb = new StringBuilder();
        sb.append("Euler Path: ");
        for (int i = path.size() - 1; i >= 0; i--) {
            if (i < path.size() - 1) sb.append(" ");
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
        int[] inDeg = new int[20];
        for (int i = 0; i < n; i++) inDeg[i] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] > 0 && a[i][j] < INF) {
                    inDeg[j]++;
                }
            }
        }

        LinkedList<Integer> q = new LinkedList<Integer>();
        // Dua cac dinh co in-degree = 0 vao queue
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) q.addLast(i);
        }

        List<Integer> result = new ArrayList<Integer>();
        while (!q.isEmpty()) {
            int u = q.removeFirst();
            result.add(u);
            for (int w = 0; w < n; w++) {
                if (a[u][w] > 0 && a[u][w] < INF) {
                    inDeg[w]--;
                    if (inDeg[w] == 0) q.addLast(w);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (result.size() != n) {
            sb.append("Graph has cycle - topological sort not possible");
        } else {
            sb.append("Topological order: ");
            for (int i = 0; i < result.size(); i++) {
                if (i > 0) sb.append(" ");
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
        int[] inDeg = new int[20];
        for (int i = 0; i < n; i++) inDeg[i] = 0;

        boolean isDirected = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] > 0 && a[i][j] < INF) {
                    // Kiem tra do thi co huong hay vo huong
                    if (a[i][j] != a[j][i]) isDirected = true;
                    inDeg[j]++;
                }
            }
        }

        if (isDirected) {
            // Do thi co huong: dung Kahn's algorithm
            LinkedList<Integer> q = new LinkedList<Integer>();
            for (int i = 0; i < n; i++) {
                if (inDeg[i] == 0) q.addLast(i);
            }
            int count = 0;
            while (!q.isEmpty()) {
                int u = q.removeFirst();
                count++;
                for (int w = 0; w < n; w++) {
                    if (a[u][w] > 0 && a[u][w] < INF) {
                        inDeg[w]--;
                        if (inDeg[w] == 0) q.addLast(w);
                    }
                }
            }
            return count != n;
        } else {
            // Do thi vo huong: dung Union-Find
            int[] parentUF = new int[20];
            for (int i = 0; i < n; i++) parentUF[i] = i;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (a[i][j] > 0 && a[i][j] < INF) {
                        int rootI = find(parentUF, i);
                        int rootJ = find(parentUF, j);
                        if (rootI == rootJ) return true; // Co chu trinh
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
        boolean[] visited = new boolean[20];
        int[] prev = new int[20];
        int[] distBFS = new int[20];
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
            if (r == dest) break;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && a[r][j] > 0 && a[r][j] < INF) {
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
            if (i > 0) sb.append(" ");
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
                    dist[i][j] = 0;
                } else if (a[i][j] > 0 && a[i][j] < INF) {
                    dist[i][j] = a[i][j];
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        // Thuat toan Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
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
                if (dist[i][j] >= INF) {
                    sb.append(" INF");
                } else {
                    sb.append(String.format("%4d", dist[i][j]));
                }
            }
            if (i < n - 1) sb.append("\n");
        }
        return sb.toString();
    }

    // ========================================================================
    // XII. CAC HAM HO TRO THEM (EXAM UTILITY)
    // ========================================================================

    // --- Xuat BFS voi bac cua dinh (fvisitDeg) ---
    void fBFS_withDeg(int start) {
        calcAllDegrees();
        boolean[] en = new boolean[20];
        for (int i = 0; i < n; i++) en[i] = false;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(start);
        en[start] = true;
        while (!q.isEmpty()) {
            int r = q.removeFirst();
            fvisitDeg(r);
            for (int j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0 && a[r][j] < INF) {
                    q.addLast(j);
                    en[j] = true;
                }
            }
        }
    }

    // --- Xuat DFS voi bac cua dinh (fvisitDeg) ---
    void fDFS_withDeg(int start) {
        calcAllDegrees();
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        fDFS_withDeg_recursive(visited, start);
    }

    private void fDFS_withDeg_recursive(boolean[] visited, int k) {
        fvisitDeg(k);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0 && a[k][i] < INF) {
                fDFS_withDeg_recursive(visited, i);
            }
        }
    }

    // --- DFS conditional (tuong tu BFS_conditional) ---
    void DFS_conditional(int start, int maxVertices, int startFromVertex) {
        boolean[] visited = new boolean[20];
        for (int i = 0; i < n; i++) visited[i] = false;
        int[] counter = {0};   // Dem tong so dinh da duyet
        int[] displayed = {0}; // Dem so dinh da hien thi
        dfsConditionalHelper(visited, start, maxVertices, startFromVertex, counter, displayed);
    }

    private void dfsConditionalHelper(boolean[] visited, int k, int max, int startFrom,
                                       int[] counter, int[] displayed) {
        if (displayed[0] >= max) return;
        visited[k] = true;
        counter[0]++;
        if (counter[0] >= startFrom) {
            fvisit(k);
            displayed[0]++;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0 && a[k][i] < INF && displayed[0] < max) {
                dfsConditionalHelper(visited, i, max, startFrom, counter, displayed);
            }
        }
    }

    // --- Prim ghi vao outputBuffer ---
    void fPrim(int start) {
        boolean[] inMST = new boolean[20];
        int[] key = new int[20];
        int[] parent = new int[20];
        int totalWeight = 0;

        for (int i = 0; i < n; i++) {
            inMST[i] = false;
            key[i] = INF;
            parent[i] = -1;
        }
        key[start] = 0;

        println("Prim's MST (start=" + v[start] + "):");

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minKey = INF;
            for (int i = 0; i < n; i++) {
                if (!inMST[i] && key[i] < minKey) {
                    minKey = key[i];
                    u = i;
                }
            }
            if (u == -1) break;
            inMST[u] = true;

            if (parent[u] != -1) {
                println("  " + v[parent[u]] + "-" + v[u] + " w=" + a[parent[u]][u]);
                totalWeight += a[parent[u]][u];
            }

            for (int w = 0; w < n; w++) {
                if (!inMST[w] && a[u][w] > 0 && a[u][w] < INF && a[u][w] < key[w]) {
                    key[w] = a[u][w];
                    parent[w] = u;
                }
            }
        }
        println("Total: " + totalWeight);
    }

    // --- Kruskal ghi vao outputBuffer ---
    void fKruskal() {
        println("Kruskal's MST:");

        List<int[]> edges = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i][j] > 0 && a[i][j] < INF) {
                    edges.add(new int[]{a[i][j], i, j});
                }
            }
        }

        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                return e1[0] - e2[0];
            }
        });

        int[] parentUF = new int[20];
        int[] rank = new int[20];
        for (int i = 0; i < n; i++) {
            parentUF[i] = i;
            rank[i] = 0;
        }

        int totalWeight = 0;
        int edgeCount = 0;

        for (int[] edge : edges) {
            if (edgeCount >= n - 1) break;
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
        int[] dist = new int[20];
        boolean[] inS = new boolean[20];
        int[] prev = new int[20];
        List<Integer> sOrder = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            inS[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            int minDist = INF;
            for (int i = 0; i < n; i++) {
                if (!inS[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) break;
            inS[u] = true;
            sOrder.add(u);

            for (int w = 0; w < n; w++) {
                if (!inS[w] && a[u][w] > 0 && a[u][w] < INF) {
                    int newDist = dist[u] + a[u][w];
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
        if (dist[dest] >= INF || path.isEmpty() || path.get(0) != src) {
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
        println("--- Shortest path " + v[src1] + " -> " + v[dest1] + " ---");
        fDijkstra(src1, dest1);
        println();
        println("--- Shortest path " + v[src2] + " -> " + v[dest2] + " ---");
        fDijkstra(src2, dest2);
    }

    // --- Kiem tra do thi co huong hay vo huong ---
    boolean isDirected() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != a[j][i]) return true;
            }
        }
        return false;
    }

    // --- isUndirected - Alias tuong minh cho !isDirected() (thuong xuat hien trong de thi) ---
    //     Goi: if (graph.isUndirected()) ...
    boolean isUndirected() { return !isDirected(); }

    // --- isEvenDegree - Kiem tra TAT CA DINH deu co BAC CHAN (dieu kien Euler circuit) ---
    //     Goi: if (graph.isEvenDegree()) ...
    boolean isEvenDegree() {
        for (int i = 0; i < n; i++) {
            if (calcDegree(i) % 2 != 0) return false;
        }
        return true;
    }

    // --- Dem so canh ---
    int countEdges() {
        int count = 0;
        if (isDirected()) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] > 0 && a[i][j] < INF) count++;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (a[i][j] > 0 && a[i][j] < INF) count++;
                }
            }
        }
        return count;
    }

    // --- Kiem tra 2 dinh co ke nhau khong ---
    boolean isAdjacent(int u, int w) {
        return a[u][w] > 0 && a[u][w] < INF;
    }

    // --- Lay trong so canh ---
    int getEdgeWeight(int u, int w) {
        if (a[u][w] > 0 && a[u][w] < INF) return a[u][w];
        return -1; // Khong co canh
    }

    // --- Chuyen index sang nhan dinh ---
    char getLabel(int index) {
        return v[index];
    }

    // --- Chuyen nhan dinh sang index ---
    int getIndex(char label) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] == label) return i;
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
        n = numVertices;
        a = new int[20][20];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = matrix[i][j];
            }
        }
    }

    // Them canh (do thi vo huong)
    void addEdge(int u, int w, int weight) {
        a[u][w] = weight;
        a[w][u] = weight;
    }

    // Them canh (do thi co huong)
    void addDirectedEdge(int u, int w, int weight) {
        a[u][w] = weight;
    }

    // Xoa canh (vo huong)
    void removeEdge(int u, int w) {
        a[u][w] = 0;
        a[w][u] = 0;
    }

    // Xoa canh (co huong)
    void removeDirectedEdge(int u, int w) {
        a[u][w] = 0;
    }

    // Khoi tao do thi rong voi n dinh
    void initEmpty(int numVertices) {
        n = numVertices;
        a = new int[20][20];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = 0;
            }
        }
    }
}
