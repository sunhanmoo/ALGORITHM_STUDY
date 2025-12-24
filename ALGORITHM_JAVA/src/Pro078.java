import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Pro078 {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int kmax;
    static int[][] parent;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 노드 개수
        tree = new ArrayList[N + 1]; // 트리 인접 리스트

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>(); // tree 인접 리스트의 각 ArrayList 초기화
        }

        // 인접 리스트에 그래프 데이터 저장
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }

        depth = new int[N + 1]; // 노드 깊이 배열
        visited = new boolean[N + 1]; // 방문 여부 저장 배열
        int temp = 1;
        kmax = 0; // 최대 가능 깊이

        // 2^k >= N을 만족하는 최소 k
        while (temp <= N) {
            temp <<= 1;
            kmax++;
        }

        parent = new int[kmax + 1][N + 1];

        BFS(1);

        for (int k = 1; k <= kmax; k++) { // kmax만큼 반복
            for (int n = 1; n <= N; n++) { // 노드 개수만큼 반복
                parent[k][n] = parent[k - 1][parent[k - 1][n]]; // 점화식 사용
            }
        }

        int M = Integer.parseInt(br.readLine()); // 질의 수

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int LCA = excuteLCA(a, b);
            System.out.println(LCA);
        }
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node); // 큐에 출발 노드 더하기
        visited[node] = true;
        int level = 1;
        int now_size = 1;
        int count = 0;
        while (!queue.isEmpty()) {
            int now_node = queue.poll();
            for (int next : tree[now_node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    parent[0][next] = now_node; // 부모 노드 저장
                    depth[next] = level; // 노드 depth 저장
                }
            }
            count++;
            if (count == now_size) { // 이번 깊이에 해당하는 모든 노드를 방문했을 때
                count = 0;
                now_size = queue.size();
                level++; // 현재 배열의 depth를 1 증가
            }
        }
    }

    public static int excuteLCA(int a, int b) {
        if (depth[a] > depth[b]) { // 더 깊은 depth가 b가 되도록 변경
            int temp = a;
            a = b;
            b = temp;
        }
        // 깊이 맞추기
        for (int k = kmax; k >= 0; k--) {
            if (Math.pow(2, k) <= depth[b] - depth[a]) { // 2^k <= 높이 차이
                if (depth[a] <= depth[parent[k][b]]) {
                    b = parent[k][b]; // b를 2^k 위 조상으로 이동
                }
            }
        }
        // 공통 조상 찾기
        for (int k = kmax; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) { // 다른 조상일 때
                // 동시에 2^k만큼 위로 이동
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        int LCA = a;
        if (a != b) { // a와 b가 다른 노드라면
            LCA = parent[0][LCA]; // 바로 위의 부모가 LCA
        }
        return LCA;
    }
}