import java.util.*;

public class Pro029 {
    static boolean visited[]; // 방문 기록 저장 배열
    static ArrayList<Integer>[] A; // 인접 리스트

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(); // 노드 개수
        int M = scan.nextInt(); // 에지 개수
        int start = scan.nextInt(); // 시작점
        A = new ArrayList[N + 1]; // 반복문 1부터 시작하기 때문에 +1 필요

        for(int i = 1; i <= N; i++) { // 초기화
            A[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++) { // 데이터 저장
            int S = scan.nextInt();
            int E = scan.nextInt();
            // 양방향
            A[S].add(E);
            A[E].add(S);
        }

        // 오름차순 정렬 (작은 것부터 방문)
        for(int i = 1; i <= N; i++) {
            Collections.sort(A[i]);
        }

        visited = new boolean[N + 1]; // 방문 배열 초기화
        DFS(start);
        System.out.println();

        visited = new boolean[N + 1]; // 방문 배열 초기화
        BFS(start);
        System.out.println();
    }

    public static void DFS(int Node) {
        System.out.print(Node + " ");
        visited[Node] = true;
        for(int i : A[Node]) {
            if(!visited[i]) {
                DFS(i);
            }
        }
    }

    public static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);
        visited[Node] = true;

        while(!queue.isEmpty()) {
            int now_Node = queue.poll();
            System.out.print(now_Node + " ");
            for(int i : A[now_Node]) {
                if(!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}