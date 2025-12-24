import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Pro059 {
    static int V, E, K;
    static int distance[];
    static boolean visited[];
    static ArrayList<Edge2> list[];
    static PriorityQueue<Edge2> q = new PriorityQueue<Edge2>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 노드 개수
        E = Integer.parseInt(st.nextToken()); // 에지 개수
        K = Integer.parseInt(br.readLine()); // 출발 노드

        distance = new int[V + 1]; // 거리 배열
        visited = new boolean[V + 1]; // 방문 여부 배열
        list = new ArrayList[V + 1]; // 그래프 정보 저장

        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<Edge2>(); // 그래프 정보를 저장하는 인접 리스트 초기화
        }
        for (int i = 0; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE; // 거리 배열 무한대로 초기화(출발 노드는 나중에 설정)
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            // 각 에지의 정보
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Edge2(v, w));
        }
        q.add(new Edge2(K, 0)); // K를 시작점으로 설정
        distance[K] = 0; // 출발 노드의 거리 배열은 0으로 초기화

        while (!q.isEmpty()) {
            Edge2 current = q.poll();
            int cVertex = current.vertex; // 현재 노드
            if (visited[cVertex]) { // 이미 방문한 적이 있는 노드라면
                continue; // 다시 큐에 넣지 않는다
            }
            visited[cVertex] = true; // 현재 노드를 방문 노드로 업데이트
            for (int i = 0; i < list[cVertex].size(); i++) { // 현재 선택 노드의 에지 개수
                Edge2 tmp = list[cVertex].get(i);
                int next = tmp.vertex;
                int value = tmp.value;
                // 목표 노드 최단 거리 > 현재 선택 노드 최단 거리 + 가중치
                if (distance[next] > distance[cVertex] + value) {
                    distance[next] = distance[cVertex] + value; // 최단 거리 업데이트
                    q.add(new Edge2(next, distance[next])); // 우선순위 큐에 목표 노드 추가
                }
            }
        }
        for (int i = 1; i <= V; i++) {
            if (visited[i]) {
                System.out.println(distance[i]);
            }
            else {
                System.out.println("INF");
            }
        }
    }
}

class Edge2 implements Comparable<Edge2> {
    int vertex;
    int value;

    Edge2(int vertex, int value) {
        this.vertex = vertex;
        this.value = value;
    }

    public int compareTo(Edge2 e) {
        if (this.value > e.value) {
            return 1;
        }
        else {
            return -1;
        }
    }
}