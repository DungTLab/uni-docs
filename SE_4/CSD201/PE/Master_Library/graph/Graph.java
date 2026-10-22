package graph;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Collections;
import java.util.List;

public class Graph {
    int[][] a;
    int n;
    String[] vertexNames;

    public Graph() {
        a = new int[20][20];
        n = 0;
        vertexNames = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
    }

    public void clear() {
        n = 0;
    }

    public void fvisit(int i, RandomAccessFile f) throws Exception {
        f.writeBytes(vertexNames[i] + " ");
    }

    // ==========================================
    // 0. CAC HAM CO BAN (IN MA TRAN, KHOI TAO)
    // ==========================================

    // In ma tran ke ra console
    public void dispAdj() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
            System.out.println();
        }
    }

    // In ma tran ke ra file
    public void fdispAdj(RandomAccessFile f) throws Exception {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                f.writeBytes(a[i][j] + " ");
            }
            f.writeBytes("\r\n");
        }
        f.writeBytes("\r\n");
    }

    // Them canh (truong hop do thi vo huong)
    public void addEdge(int i, int j, int weight) {
        a[i][j] = weight;
        a[j][i] = weight; // Bo dong nay neu la do thi co huong
    }

    // ==========================================
    // 1. DUYET DO THI (TRAVERSALS)
    // ==========================================

    // Duyet theo chieu rong (Breadth First Search)
    public void breadthFirstSearch(int start, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            fvisit(u, f);
            for (int v = 0; v < n; v++) {
                if (!visited[v] && a[u][v] > 0) { // Co canh noi tu u den v
                    q.add(v);
                    visited[v] = true;
                }
            }
        }
        f.writeBytes("\r\n");
    }

    // Bien the PE: Tim va in ra dinh thu K duoc duyet boi BFS
    public void bfsWithCondition(int start, int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        int count = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            count++;
            if (count == k) {
                fvisit(u, f);
                break; // Dung lai khi da in ra dinh thu k
            }
            for (int v = 0; v < n; v++) {
                if (!visited[v] && a[u][v] > 0) {
                    q.add(v);
                    visited[v] = true;
                }
            }
        }
        f.writeBytes("\r\n");
    }

    // Duyet theo chieu sau (Depth First Search)
    public void depthFirstSearch(int start, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[n];
        dfsRecursive(start, visited, f);
        f.writeBytes("\r\n");
    }

    private void dfsRecursive(int u, boolean[] visited, RandomAccessFile f) throws Exception {
        fvisit(u, f);
        visited[u] = true;
        for (int v = 0; v < n; v++) {
            if (!visited[v] && a[u][v] > 0) {
                dfsRecursive(v, visited, f);
            }
        }
    }

    // ==========================================
    // 2. DUONG DI NGAN NHAT (DIJKSTRA)
    // ==========================================

    // Tim duong di ngan nhat tu start den end
    public void dijkstra(int start, int end, RandomAccessFile f) throws Exception {
        int[] dist = new int[n];
        int[] path = new int[n];
        boolean[] selected = new boolean[n];

        // Khoi tao
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
            selected[i] = false;
        }
        dist[start] = 0;

        // Lap n lan de tim dinh
        for (int i = 0; i < n; i++) {
            // Tim dinh u co dist nho nhat trong so cac dinh chua duoc chon
            int u = -1;
            int minD = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!selected[j] && dist[j] < minD) {
                    minD = dist[j];
                    u = j;
                }
            }

            // Neu do thi khong lien thong
            if (u == -1) break;

            selected[u] = true;
            if (u == end) break; // Da tim thay dich

            // Update lai dist cua cac dinh lien ke voi u
            for (int v = 0; v < n; v++) {
                if (!selected[v] && a[u][v] > 0 && dist[u] != Integer.MAX_VALUE) {
                    if (dist[u] + a[u][v] < dist[v]) {
                        dist[v] = dist[u] + a[u][v];
                        path[v] = u;
                    }
                }
            }
        }

        // In duong di ra file
        printShortestPath(path, start, end, f);
    }

    // Ham bo tro in duong di nguoc tu end ve start dung Stack
    public void printShortestPath(int[] path, int start, int end, RandomAccessFile f) throws Exception {
        if (path[end] == -1 && start != end) {
            f.writeBytes("Khong co duong di tu " + vertexNames[start] + " den " + vertexNames[end] + "\r\n");
            return;
        }

        Stack<Integer> stack = new Stack<>();
        int current = end;
        while (current != -1) {
            stack.push(current);
            current = path[current];
        }

        // In ra format A -> B -> C
        while (!stack.isEmpty()) {
            int v = stack.pop();
            f.writeBytes(vertexNames[v]);
            if (!stack.isEmpty()) {
                f.writeBytes(" -> ");
            }
        }
        f.writeBytes("\r\n");
    }

    // Bien the PE: Luu cac buoc chay cua dijkstra
    // Thong thuong de PE se yeu cau luu 3 dong cuoi cung hoac 3 dong dau tien cua bang chay tay
    public void dijkstraIntermediateSteps(int start, int end, RandomAccessFile f) throws Exception {
        int[] dist = new int[n];
        int[] path = new int[n];
        boolean[] selected = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
            selected[i] = false;
        }
        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            int minD = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!selected[j] && dist[j] < minD) {
                    minD = dist[j];
                    u = j;
                }
            }
            if (u == -1) break;
            selected[u] = true;

            // Update
            for (int v = 0; v < n; v++) {
                if (!selected[v] && a[u][v] > 0 && dist[u] != Integer.MAX_VALUE) {
                    if (dist[u] + a[u][v] < dist[v]) {
                        dist[v] = dist[u] + a[u][v];
                        path[v] = u;
                    }
                }
            }
            
            // In trang thai cua dist sau moi vong lap i
            // (Tuy vao de ma ban viet f.writeBytes o day)
            // Vi du in cac dist ra:
            // for(int v = 0; v < n; v++) { f.writeBytes(dist[v] + " "); }
            // f.writeBytes("\n");
            
            if (u == end) break;
        }
    }

    // ==========================================
    // MINIMUM SPANNING TREE (KRUSKAL)
    // ==========================================
    public String kruskalToString() {
        StringBuilder sb = new StringBuilder();
        int INF = 9999;
        class Edge implements Comparable<Edge> {
            int u, v, weight;
            public Edge(int u, int v, int weight) {
                this.u = u; this.v = v; this.weight = weight;
            }
            public int compareTo(Edge other) {
                return this.weight - other.weight;
            }
        }
        
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i][j] > 0 && a[i][j] < INF) {
                    edges.add(new Edge(i, j, a[i][j]));
                }
            }
        }
        Collections.sort(edges);
        
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        int totalWeight = 0;
        int count = 0;
        for (Edge e : edges) {
            if (count == n - 1) break;
            
            // Find
            int rootU = e.u;
            while (rootU != parent[rootU]) rootU = parent[rootU];
            int rootV = e.v;
            while (rootV != parent[rootV]) rootV = parent[rootV];
            
            // Union
            if (rootU != rootV) {
                parent[rootU] = rootV;
                sb.append("(").append(vertexNames[e.u]).append(",").append(vertexNames[e.v]).append(") ");
                totalWeight += e.weight;
                count++;
            }
        }
        sb.append("\nTotal Weight: ").append(totalWeight);
        return sb.toString().trim();
    }

    // ==========================================
    // ALL-PAIRS SHORTEST PATHS (FLOYD-WARSHALL)
    // ==========================================
    public String floydWarshallToString() {
        int INF = 9999;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) dist[i][j] = 0;
                else if (a[i][j] > 0) dist[i][j] = a[i][j];
                else dist[i][j] = INF;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] >= INF) sb.append("INF ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    // ==========================================
    // 3. EULER CYCLE / EULER PATH
    // ==========================================

    // Tinh bac cua 1 dinh
    public int degree(int v) {
        int deg = 0;
        for (int i = 0; i < n; i++) {
            if (a[v][i] > 0) deg++;
            if (a[i][v] > 0) deg++; // Neu do thi co huong, day la in-degree
        }
        return deg;
    }

    // Kiem tra do thi co chu trinh Euler khong (Vo huong: tat ca cac dinh deu co bac chan)
    public boolean isEulerGraph() {
        for (int i = 0; i < n; i++) {
            if (degree(i) % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    // Tim va in chu trinh Euler tu dinh start
    public void findEulerCycle(int start, RandomAccessFile f) throws Exception {
        if (!isEulerGraph()) {
            f.writeBytes("Do thi khong phai la do thi Euler\r\n");
            return;
        }

        // Clone ma tran ke vi thuat toan se pha huy ma tran
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) temp[i][j] = a[i][j];
        }

        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int u = stack.peek(); // Chi xem, chua pop
            boolean hasEdge = false;
            
            for (int v = 0; v < n; v++) {
                if (temp[u][v] > 0) {
                    stack.push(v);
                    // Xoa canh
                    temp[u][v]--;
                    temp[v][u]--;
                    hasEdge = true;
                    break;
                }
            }
            if (!hasEdge) { // Neu khong con duong di
                int v = stack.pop();
                result.add(0, v); // Add vao dau array list
            }
        }

        // In ket qua
        for (int i = 0; i < result.size(); i++) {
            f.writeBytes(vertexNames[result.get(i)] + " ");
        }
        f.writeBytes("\r\n");
    }

    // ==========================================
    // 4. HAM BO TRO (UTILS)
    // ==========================================

    public int countIsolatedVertices() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (degree(i) == 0) count++;
        }
        return count;
    }

    // ==========================================
    // 5. CAC HAM THUONG GAP TRONG PE (PE EXAM REQUIREMENTS)
    // ==========================================

    // Thuat toan Prim tim Cay Khung Nho Nhat (Minimum Spanning Tree)
    public void prim(int start, RandomAccessFile f) throws Exception {
        boolean[] selected = new boolean[n];
        int[] parent = new int[n];
        int[] key = new int[n];

        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        key[start] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = -1;
            int minKey = Integer.MAX_VALUE;
            for (int v = 0; v < n; v++) {
                if (!selected[v] && key[v] < minKey) {
                    minKey = key[v];
                    u = v;
                }
            }

            if (u == -1) break; // Do thi khong lien thong
            selected[u] = true;

            for (int v = 0; v < n; v++) {
                if (a[u][v] > 0 && !selected[v] && a[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = a[u][v];
                }
            }
        }

        // In ket qua ra file
        int mstWeight = 0;
        for (int i = 0; i < n; i++) {
            if (i != start && parent[i] != -1) {
                f.writeBytes(vertexNames[parent[i]] + " - " + vertexNames[i] + " : " + a[i][parent[i]] + "\r\n");
                mstWeight += a[i][parent[i]];
            }
        }
        f.writeBytes("Total Weight: " + mstWeight + "\r\n");
    }

    // Dem so thanh phan lien thong
    public int countConnectedComponents() {
        boolean[] visited = new boolean[n];
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                // Dung BFS hoac DFS de danh dau tat ca cac dinh cung thanh phan lien thong
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i] = true;
                while (!q.isEmpty()) {
                    int u = q.poll();
                    for (int v = 0; v < n; v++) {
                        if (a[u][v] > 0 && !visited[v]) {
                            visited[v] = true;
                            q.add(v);
                        }
                    }
                }
            }
        }
        return components;
    }

    // ==========================================
    // 6. DUYET TRA VE STRING KHI KHONG IN RA MAN HINH
    // (GUARANTEED TO ONLY RETURN STRING, NO PRINTING)
    // ==========================================

    public String bfsToString(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            sb.append(vertexNames[u]).append(" ");
            for (int v = 0; v < n; v++) {
                if (!visited[v] && a[u][v] > 0) {
                    q.add(v);
                    visited[v] = true;
                }
            }
        }
        return sb.toString().trim();
    }

    private void dfsToStringRec(int u, boolean[] visited, StringBuilder sb) {
        sb.append(vertexNames[u]).append(" ");
        visited[u] = true;
        for (int v = 0; v < n; v++) {
            if (!visited[v] && a[u][v] > 0) {
                dfsToStringRec(v, visited, sb);
            }
        }
    }

    public String dfsToString(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[n];
        dfsToStringRec(start, visited, sb);
        return sb.toString().trim();
    }

    public String dijkstraPathToString(int start, int end) {
        int[] dist = new int[n];
        int[] path = new int[n];
        boolean[] selected = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
            selected[i] = false;
        }
        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            int minD = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!selected[j] && dist[j] < minD) {
                    minD = dist[j];
                    u = j;
                }
            }
            if (u == -1) break;
            selected[u] = true;
            if (u == end) break;

            for (int v = 0; v < n; v++) {
                if (!selected[v] && a[u][v] > 0 && dist[u] != Integer.MAX_VALUE) {
                    if (dist[u] + a[u][v] < dist[v]) {
                        dist[v] = dist[u] + a[u][v];
                        path[v] = u;
                    }
                }
            }
        }

        if (path[end] == -1 && start != end) {
            return "No path from " + vertexNames[start] + " to " + vertexNames[end];
        }

        Stack<Integer> stack = new Stack<>();
        int current = end;
        while (current != -1) {
            stack.push(current);
            current = path[current];
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(vertexNames[stack.pop()]);
            if (!stack.isEmpty()) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }
}
