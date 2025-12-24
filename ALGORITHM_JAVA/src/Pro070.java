import java.util.ArrayList;
import java.util.Scanner;

public class Pro070 {
    static int N;
    static boolean[] visited;
    static ArrayList<Integer> tree[];
    static int[] answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 노드 개수
        visited = new boolean[N + 1]; // 방문 기록 배열
        tree = new ArrayList[N + 1]; // 트리 데이터 저장 인접 리스트
        answer = new int[N + 1]; // 정답 배열

        // 트리 인접 리스트의 각 ArrayList 초기화
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        // tree 인접 리스트에 트리 데이터 저장
        for (int i = 1; i < N; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            // 양방향 에지
            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        DFS(1); // 부모 노드부터 DFS 시작
        for (int i = 2; i <= N; i++) {
            System.out.println(answer[i]); // index 2부터 정답 배열 출력
        }
    }

    static void DFS(int number) {
        visited[number] = true; // visited 배열에 현재 노드 방문 기록
        for (int i : tree[number]) { // 현재 노드의 연결 노드 중
            if (!visited[i]) { // 방문하지 않은 노드
                answer[i] = number; // 부모 노드 저장
                DFS(i);
            }
        }
    }
}
