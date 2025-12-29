import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro098 {
    static final int INF = 1000000 * 16 + 1;
    static int N;
    static int[][] W;
    static int[][] D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        // trim(): 문자열 앞과 뒤에 있는 공백 문자들을 제거
        N = Integer.parseInt(br.readLine().trim()); // 도시 개수
        W = new int[16][16]; // W[i][j]: i 도시에서 j 도시로 가는 데 드는 비용 저장 배열
        // D[c][v]: 현재 도시가 c, 현재까지 방문한 도시 리스트가 v일 때 앞으로 남은 모든 도시를 경유하는 데 필요한 최소 비용
        D = new int[16][1 << 16];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(tsp(0, 1));
    }

    // c: 현재 있는 도시, v: 방문한 도시 리스트
    static int tsp(int c, int v) {
        if (v == (1 << N) - 1) { // 모든 도시를 방문하는 경우
            // 도시 i에서 도시 j로 갈 수 없는 경우 W[i][j] = 0이라 가정
            // 현재 도시 c에서 출발 도시 0으로 돌아갈 수 없는 경우 W[c][0] = 0
            // 돌아갈 수 없다면 INF
            // 돌아갈 수 있다면 W[c][0]
            return W[c][0] == 0 ? INF : W[c][0];
        }
        if (D[c][v] != 0) { // 이미 방문한 도시인 경우
            return D[c][v]; // 반환(메모이제이션)
        }
        int min_val = INF;
        for (int i = 0; i < N; i++) {
            if ((v & (1 << i)) == 0 && W[c][i] != 0) { // 방문한 적이 없고 갈 수 있는 도시인 경우
                min_val = Math.min(min_val, tsp(i, (v | (1 << i))) + W[c][i]);
            }
        }
        D[c][v] = min_val;
        return D[c][v];
    }
}
