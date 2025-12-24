import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Pro077 {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[] parent;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 노드 개수
        tree = new ArrayList[N + 1]; // 트리 인접 리스트

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>(); // tree 인접 리스트의 각 ArrayList 초기화
        }

        // 인접 리스트에 그래프 데이터 저장
        for (int i = 0; i < N - 1; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            tree[s].add(e);
            tree[e].add(s);
        }

        depth = new int[N + 1]; // 노드 깊이 배열
        parent = new int[N + 1]; // 노드 조상 배열
        visited = new boolean[N + 1]; // 방문 여부 저장 배열

        BFS(1);

        int M = sc.nextInt(); // 질의 수

        for (int i = 0; i < M; i++) {
            // 공통 조상을 구할 두 노드
            int a = sc.nextInt();
            int b = sc.nextInt();
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
                    parent[next] = now_node; // 부모 노드 저장
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
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (depth[a] != depth[b]) { // 두 노드의 depth 맞추기
            a = parent[a];
        }
        while (a != b) { // 같은 조상이 나올 때까지 한 칸씩 올리기
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
}
