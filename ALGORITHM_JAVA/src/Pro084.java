import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro084 {
    public static void main(String[] args) throws IOException {
        int N, Q; // 순열의 길이, 문제의 종류
        long F[] = new long[21]; // 각 자리별로 만들 수 있는 경우의 수 저장 배열(팩토리얼)
        int S[] = new int[21]; // 순열을 담는 배열
        boolean visited[] = new boolean[21]; // 숫자 사용 여부 저장 배열

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(st.nextToken());
        F[0] = 1; // 0! = 1로 초기화

        // 팩토리얼 초기화(각 자릿수에서 만들 수 있는 경우의 수)
        for (int i = 1; i <= N; i++) {
            F[i] = F[i - 1] * i;
        }

        if (Q == 1) { // K가 주어지면 K번째 순열을 구한다
            long K = Long.parseLong(st.nextToken());
            for (int i = 1; i <= N; i++) {
                for (int j = 1, cnt = 1; j <= N; j++) {
                    if (visited[j]) { // 이미 사용한 숫자인 경우
                        continue; // 계산하지 않음
                    }
                    if (K <= cnt * F[N - i]) { // K가 커버할 수 있는 경우의 수보다 안쪽에 있는 경우
                        K -= (F[N - i] * (cnt - 1)); // K를 K - (경우의 수 * (cnt - 1))로 업데이트
                        S[i] = j;
                        visited[j] = true;
                        break;
                    }
                    cnt++;
                }
            }
            for (int i = 1; i <= N; i++) {
                System.out.print(S[i] + " ");
            }
        }
        else { // 임의의 순열이 주어지면 이 순열이 몇 번째 순열인지 구한다
            long K = 1; // 최소의 값으로 초기화
            for (int i = 1; i <= N; i++) {
                S[i] = Integer.parseInt(st.nextToken());
                long cnt = 0;
                for (int j = 1; j < S[i]; j++) {
                    if (visited[j] == false) { // 사용하지 않은 숫자의 개수만큼
                        cnt++; // 증간(사용하지 않은 숫자의 개수)
                    }
                }
                K += cnt * F[N - i]; // 해당 순서 * (현재 자리 - 1에서 만들 수 있는 순열의 개수)를 K에 더한다
                visited[S[i]] = true;
            }
            System.out.println(K);
        }
    }
}
