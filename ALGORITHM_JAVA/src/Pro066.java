import java.io.*;
import java.util.StringTokenizer;

public class Pro066 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int distance[][];
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 유저의 수
        M = Integer.parseInt(st.nextToken()); // 친구 관계의 수
        distance = new int[N + 1][N + 1];

        // 인접 행렬 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) { // 자기 자신인 경우
                    distance[i][j] = 0; // 0으로 초기화
                }
                else { // 이외의 경우
                    distance[i][j] = 10000001; // 무한대 값으로 초기화
                }
            }
        }

        // 친구 관계 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            distance[s][e] = 1;
            distance[e][s] = 1;
        }

        // 플로이드-워셜
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int answer = -1;
        for (int i = 1; i <= N; i++) {
            int temp = 0;
            for (int j = 1; j <= N; j++) {
                temp = temp + distance[i][j]; // 각 행의 인접 행렬 값을 모두 더한 것이 케빈 베이컨 수
            }
            if (min > temp) { // 가장 값이 작은 행을 answer에 저장
                min = temp;
                answer = i;
            }
        }
       System.out.println(answer);
    }
}
