import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Pro023 {
    static ArrayList<Integer>[] A;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드 개수
        int M = Integer.parseInt(st.nextToken()); // 에지 개수
        A = new ArrayList[N + 1]; // 인덱스 1번부터 사용
        visited = new boolean[N + 1]; // 인덱스 1번부터 사용

        for(int i = 1; i < N + 1; i++) { // 인접 리스트 초기화
            A[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++) { // 간선의 개수만큼 반복
            st = new StringTokenizer(br.readLine());
            // s와 e는 간선의 양끝 노드 번호(예시로 1과 4면 간선의 양끝 노드 번호가 1, 4라는 뜻)
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            // 무방향 그래프이기 때문에 양방향 모두 고려
            A[s].add(e);
            A[e].add(s);
        }
        int count = 0;
        for(int i = 1; i < N + 1; i++) {
            if(!visited[i]) {
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }

    static void DFS(int v) {
        if(visited[v]){
            return; // 이미 방문한 노드는 바로 반환
        }
        visited[v] = true;
        for(int i : A[v]) {
            if(visited[i] == false) {
                DFS(i);
            }
        }
    }
}