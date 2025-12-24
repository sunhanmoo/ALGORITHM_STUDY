import java.util.*;

public class Pro049 {
    static int visited[]; // 방문 거리 저장 배열
    static ArrayList<Integer>[] A; // 그래프 데이터 저장 인접 리스트
    static int N, M, K, X;
    static List<Integer> answer; // 정답 리스트

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 노드(도시) 개수
        M = sc.nextInt(); // 에지(도로) 개수
        K = sc.nextInt(); // 목표 최단 거리
        X = sc.nextInt(); // 출발 노드(도시)
        A = new ArrayList[N + 1];
        answer = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Integer>(); // A의 인접 리스트의 각 ArrayList 초기화
        }

        for (int i = 0; i< M; i++) {
            int S = sc.nextInt(); // 출발 노드
            int E = sc.nextInt(); // 도착 노드
            A[S].add(E); // S에서 E로 가는 도로 추가
        }

        visited = new int[N + 1]; // 방문 배열 초기화
        for (int i = 0; i <= N; i++) {
            visited[i] = -1;
        }

        BFS(X); // BFS 탐색 수행
        for (int i = 0; i <= N; i++) {
            if (visited[i] == K) { // 탐색 후 방문 거리가 목표 K와 같다면
                answer.add(i); // 정답 리스트에 데이터 추가
            }
        }
        if (answer.isEmpty()) { // 정답 리스트가 비었다면
            System.out.println("-1"); // -1 출력
        }
        else { // 정답 리스트가 존재한다면
            Collections.sort(answer); // 오름차순 정렬
            for (int temp : answer) {
                System.out.println(temp); // 오름차순 정렬된 정답 리스트 출력
            }
        }
    }

    private static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node); // 큐 자료구조에 출발 노드 더하기
        visited[Node]++; // 현재 노드 방문 기록
        while (!queue.isEmpty()) { // 큐가 빌 때까지
            int now_Node = queue.poll(); // 큐에서 노드 데이터 가져오기
            for (int i : A[now_Node]) {
                if (visited[i] == -1) { // 방문하지 않은 노드라면
                    visited[i] = visited[now_Node] + 1; // 이전 노드의 방문 노드 거리 + 1
                    queue.add(i); // 큐에 데이터 삽입
                }
            }
        }
    }
}