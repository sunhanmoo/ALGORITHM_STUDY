import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro083 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M, K, T;
        int D[] = new int[51]; // 색상별 조약돌 개수 저장 배열
        double prob[] = new double[51]; // 색상별 확률 저장 배열
        double answer;

        T = 0; // 전체 조약돌 개수
        M = Integer.parseInt(st.nextToken()); // 색상의 개수

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            D[i] = Integer.parseInt(st.nextToken()); // 각 조약돌 개수 저장
            T += D[i]; // 조약돌 개수 더하기
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 뽑을 조약돌의 개수
        answer = 0.0;

        for (int i = 0; i < M; i++) {
            prob[i] = 0.0; // 초기화
            // 현재 색의 조약돌의 개수가 뽑아야 하는 개수보다 작다면 모두 같은 색으로 뽑을 확률은 0이다
            if (D[i] >= K) { // 현재 색의 조약돌의 개수가 뽑아야 하는 개수보다 크거나 같은 경우
                prob[i] = 1.0;
                for (int k = 0; k < K; k++) {
                    prob[i] *= (double) (D[i] - k) / (T - k);
                }
            }
            answer += prob[i];
        }
        System.out.println(answer);
    }
}
