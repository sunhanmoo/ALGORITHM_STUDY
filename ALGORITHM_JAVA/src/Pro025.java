import java.util.ArrayList;
import java.util.Scanner;

public class Pro025 {
    static boolean visited[]; // 방문 기록 저장 배열
    static ArrayList<Integer>[] A; // 그래프 데이터 저장 인접 리스트
    static boolean arrive; // 길이 5 경로 발견 여부

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 노드 개수
        int M = sc.nextInt(); // 에지 개수
        arrive = false;
        A = new ArrayList[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<Integer>(); // 인접 리스트 초기화
        }

        // 친구 관계
        for(int i = 0; i < M; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            // 양방향 저장
            A[S].add(E);
            A[E].add(S);
        }

        for(int i = 0; i < N; i++) {
            DFS(i, 1); // depth 1부터 시작
            if(arrive){
                break;
            }
        }
        if(arrive){
            System.out.println("1");
        }
        else{
            System.out.println("0");
        }
    }

    public static void DFS(int now, int depth) {
        if(depth == 5 || arrive) { // 이미 5개의 노드를 모았거나 이미 찾았으면 종료
            arrive = true;
            return;
        }
        visited[now] = true; // 현재 노드를 경로에 포함
        for(int i : A[now]) { // 현재 노드의 모든 이웃을 확인
            if(!visited[i]) {
                DFS(i, depth + 1); // 재귀 호출 될 때마다 depth 1씩 증가
            }
        }
        visited[now] = false; // 백트래킹: 이 경로 탐색이 끝나면 현재 노드 방문 해제
    }
}