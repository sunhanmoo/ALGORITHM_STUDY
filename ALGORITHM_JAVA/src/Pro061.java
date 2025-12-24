import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Pro061 {
    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        int N, M, K;
        int[][] W;
        PriorityQueue<Integer>[] distQueue;
        PriorityQueue<Node2> pq;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시 개수
        M = Integer.parseInt(st.nextToken()); // 도로 개수
        K = Integer.parseInt(st.nextToken()); // 몇 번째 최단 경로인지
        W = new int[1001][1001]; // 그래프 정보 저장 인접 행렬
        distQueue = new PriorityQueue[N + 1]; // 최단 거리 큐 배열

        Comparator<Integer> cp = new Comparator<Integer>() { // 오름차순 정렬 기준 설정
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : -1;
            }
        };

        for (int i = 0; i < N + 1; i++) {
            // 최단 거리는 K번째까지만 필요
            distQueue[i] = new PriorityQueue<Integer>(K, cp); // 최단 거리 큐 배열 초기화
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 인접 행렬에 그래프 데이터 저장
            int a = Integer.parseInt(st.nextToken()); // 출발 노드
            int b = Integer.parseInt(st.nextToken()); // 도착 노드
            int c = Integer.parseInt(st.nextToken()); // 가중치
            W[a][b] = c;
        }
        pq = new PriorityQueue<>();
        pq.add(new Node2(1, 0)); // 문제에서 시작 노드 1로 지정
        distQueue[1].add(0); // 출발 노드의 거리 배열은 0으로 초기화
        while (!pq.isEmpty()) {
            Node2 u = pq.poll();
            for (int adjNode = 1; adjNode <= N; adjNode++) {
                // 연결된 모든 노드 검색(시간 복잡도에서 불리하다)
                if (W[u.node][adjNode] != 0) { // 해당 노드와 현재 노드가 연결돼 있는 경우
                    // 저장된 경로가 K개 미만이라면 경로에 추가
                    if (distQueue[adjNode].size() < K) {
                        distQueue[adjNode].add(u.cost + W[u.node][adjNode]);
                        pq.add(new Node2(adjNode, u.cost + W[u.node][adjNode]));
                    }
                    // 저장된 경로가 K개라면, K개 중 가장 큰 값보다 작을 때만 경로에 추가
                    else if (distQueue[adjNode].peek() > u.cost + W[u.node][adjNode]) {
                        distQueue[adjNode].poll();
                        distQueue[adjNode].add(u.cost + W[u.node][adjNode]);
                        pq.add(new Node2(adjNode, u.cost + W[u.node][adjNode]));
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (distQueue[i].size() == K) {
                bw.write(distQueue[i].peek() + "\n");
            }
            else {
                bw.write(-1 + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

class Node2 implements Comparable<Node2> {
    int node; // 가리키는 노드
    int cost; // 에지의 가중치

    Node2(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node2 o) {
        return this.cost < o.cost ? -1 : 1;
    }
}