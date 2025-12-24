import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Pro060 {
    static int N, M;
    static ArrayList<Node>[] list;
    static int[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 도시 개수
        M = Integer.parseInt(br.readLine()); // 버스 개수
        list = new ArrayList[N + 1]; // 인접 리스트로 그래프 표현
        distance = new int[N + 1]; // 최단 거리 배열
        visited = new boolean[N + 1]; // 사용 노드 확인 배열
        Arrays.fill(distance, Integer.MAX_VALUE); // 최단 거리 배열을 무한대로 초기화
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<Node>(); // 그래프 정보 저장하는 인접 리스트 초기화
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 그래프의 에지를 인접 리스트 자료구조에 넣기
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, weight));
        }
        st = new StringTokenizer(br.readLine());
        int startIndex = Integer.parseInt(st.nextToken()); // 구하고자 하는 출발 도시의 번호
        int endIndex = Integer.parseInt(st.nextToken()); // 구하고자 하는 도착 도시의 번호
        bw.write(dijkstra(startIndex, endIndex) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0)); // 출발 노드의 거리 배열은 0으로 초기화
        distance[start] = 0;
        while (!q.isEmpty()) {
            Node nowNode = q.poll();
            int now = nowNode.targetNode;
            if (!visited[now]) { // 방문하지 않은 노드라면
                visited[now] = true; // 방문한 것으로 변경
                for (Node n : list[now]) { // 현재 노드의 에지 개수
                    // 목표 노드 방문 전이면서
                    // 목표 노드 최단 거리 > 현재 선택 노드 최단 거리 + 가중치
                    if (!visited[n.targetNode] && distance[n.targetNode] > distance[now] + n.value) {
                        distance[n.targetNode] = distance[now] + n.value; // 최단 거리 업데이트
                        q.add(new Node(n.targetNode, distance[n.targetNode])); // 우선순위 큐에 목표 노드 추가
                    }
                }
            }
        }
        return distance[end];
    }
}

class Node implements Comparable<Node>{
    int targetNode; // 가리키는 노드
    int value; // 에지의 가중치

    Node(int targetNode, int value) {
        this.targetNode = targetNode;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return value - o.value;
    }
}