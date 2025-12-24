import java.util.*;

public class Pro031 {
    static boolean visited[]; // 방문 기록 저장 배열
    static int[] distance; // 거리 저장 배열
    static ArrayList<Edge>[] A; // 그래프 데이터 저장 인접 리스트

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 노드 개수
        A = new ArrayList[N + 1]; // 인덱스 1부터 시작하기 때문에 1개 더 필요
        for (int i = 1; i <= N; i++) { // 초기화
            A[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < N; i++) {
            int S = sc.nextInt();
            while(true) {
                int E = sc.nextInt();
                if (E == -1) { // 더이상 노드가 없으므로 종료
                    break;
                }
                int V = sc.nextInt();
                A[S].add(new Edge(E, V));
            }
        }
        distance = new int[N + 1]; // 초기화
        visited = new boolean[N + 1]; // 초기화
        BFS(1);
        int max = 1;
        for (int i = 2; i <= N; i++) {
            if (distance[max] < distance[i])
                max = i;
        }
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        BFS(max);
        Arrays.sort(distance);
        System.out.println(distance[N]);
    }

    private static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        visited[index] = true;
        while (!queue.isEmpty()) {
            int now_node = queue.poll();
            for (Edge i : A[now_node]) {
                int e = i.e;
                int v = i.value;
                if (!visited[e]) {
                    visited[e] = true;
                    queue.add(e);
                    distance[e] = distance[now_node] + v;
                }
            }
        }
    }
}

class Edge { // 에지 클래스
    int e; // 목적지 노드
    int value; // 에지의 가중치
    public Edge(int e, int value) {
        this.e = e;
        this.value = value;
    }
}
