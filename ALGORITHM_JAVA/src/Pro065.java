import java.io.*;
import java.util.StringTokenizer;

public class Pro065 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int distance[][];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 도시 개수
        distance = new int[N][N]; // 인접 행렬

        // 인접 행렬 데이터 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());
                distance[i][j] = v;
            }
        }

        // 플로이드-워셜
        for (int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if (distance[i][k] == 1 && distance[k][j] == 1) {
                        distance[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(distance[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
