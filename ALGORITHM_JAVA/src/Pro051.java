import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Pro051 {
    static ArrayList<Integer>[] A; // 그래프 데이터 저장 인접 리스트
    static int[] check; // 이분 그래프 체크 배열
    static boolean visited[]; // 방문 기록 저장 배열
    static boolean isEven; // 이분 그래프 판별 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        for (int t = 0; t < N; t++) {
            String[] s = br.readLine().split(" ");
            int V = Integer.parseInt(s[0]); // 노드 개수
            int E = Integer.parseInt(s[1]); // 에지 개수
            A = new ArrayList[V + 1];
            visited = new boolean[V + 1];
            check = new int[V + 1]; // 따로 초기화하지 않으면 모두 0으로 초기화 됨
            isEven = true;

            for (int i = 1; i <= V; i++) {
                A[i] = new ArrayList<Integer>(); // A 인접 리스트의 각 ArrayList 초기화
            }

            for (int i = 0; i < E; i++) { // 인접 리스트로 그래프 저장
                s = br.readLine().split(" "); // 공백을 기준으로 출발 및 도착 노드 구분
                int start = Integer.parseInt(s[0]); // 출발 노드
                int end = Integer.parseInt(s[1]); // 도착 노드
                A[start].add(end);
                A[end].add(start);
            }

            // 주어진 그래프가 1개로 연결돼 있다는 보장이 없기 때문에 모든 노드에서 수행
            for (int i = 1; i <= V; i++) {
                if (isEven) {
                    DFS(i);
                }
                else {
                    break; // 이분 그래프가 아니라면 바로 중단
                }
            }

            if (isEven) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }

    public static void DFS(int node) {
        visited[node] = true;
        for (int i : A[node]) {
            if (!visited[i]) { // 방문하지 않은 노드라면
                // 인접한 노드는 같은 집합이 아니므로 이전 노드와 다른 집합으로 처리
                check[i] = (check[node] + 1) % 2; // 0과 1로 구분
                DFS(i);
            }
            else if (check[node] == check[i]) { // 이미 방문한 노드가 현재 내 노드와 같은 집합이면
                isEven = false; // 이분 노드가 아니다
            }
        }
    }
}
