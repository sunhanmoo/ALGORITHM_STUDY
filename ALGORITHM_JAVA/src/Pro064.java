import java.io.*;
import java.util.StringTokenizer;

public class Pro064 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int distance[][];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 도시 개수
        M = Integer.parseInt(br.readLine()); // 노선 개수
        distance = new int[N + 1][N + 1];

        // 인접 행렬 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) { // 자기 자신으로 가는 노선인 경우
                    distance[i][j] = 0; // 0으로 초기화
                }
                else { // 이외의 노선인 경우
                    distance[i][j] = 10000001; // 무한대 값으로 초기화
                }
            }
        }

        // 버스의 정보
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 도시
            int b = Integer.parseInt(st.nextToken()); // 도착 도시
            int c = Integer.parseInt(st.nextToken()); // 한 번 타는 데 필요한 비용
            if (distance[a][b] > c) { // 더 작은 비용이 들어왔을 경우
                distance[a][b] = c; // 더 작은 비용으로 업데이트
            }
        }

        // 플로이드-워셜
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i<= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (distance[i][j] == 10000001) { // 갈 수 없는 경로인 경우
                    bw.write("0 ");
                }
                else {
                    bw.write(distance[i][j] + " ");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
