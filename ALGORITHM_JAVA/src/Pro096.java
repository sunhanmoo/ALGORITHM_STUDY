import java.util.Scanner;

public class Pro096 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // dp[N][L][R]: N개 수열을 수행했고, 왼쪽이 L 오른쪽이 R에 있을 때 최소 누적 힘
        int dp[][][] = new int[100001][5][5];

        // mp[i][j]: 발이 i에 있을 때 j로 이동하는 데 드는 힘
        int mp[][] = {
                {0, 2, 2, 2, 2},
                {2, 1, 3, 4, 3},
                {2, 3, 1, 3, 4},
                {2, 4, 3, 1, 3},
                {2, 3, 4, 3, 1}
        };

        int n = 0; // 발판 번호
        int s = 1; // 처리한 지시 개수

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 100001; k++) {
                    dp[k][i][j] = 100001 * 4; // 충분히 큰 수로 초기회
                }
            }
        }
        dp[0][0][0] = 0; // 원점은 움직이지 않은 상태이기 때문에 0

        while (true) {
            n = sc.nextInt(); // 현재 입력으로 들어온 발판 번호

            // 오른발로 n을 밟는 경우
            if (n == 0) { // 0을 입력하면
                break; // 수열의 마지막을 의미
            }
            for (int i = 0; i < 5; i++) { // i는 왼발 위치
                if (n == i) { // 오른발과 왼발의 위치가 동일
                    continue; // 불가능한 조건이기 때문에 계산하지 않는다
                }
                for (int j = 0; j < 5; j++) {
                    // 오른발을 옮겨 현재 모습이 됐을 때 최소의 힘 저장
                    dp[s][i][n] = Math.min(dp[s - 1][i][j] + mp[j][n], dp[s][i][n]);
                }
            }

            // 왼발로 n을 밟는 경우
            for (int j = 0; j < 5; j++) { // j는 오른발 위치
                if (n == j) { // 왼발과 오른발의 위치가 동일
                    continue; // 불가능한 조건이기 때문에 계산하지 않는다
                }
                for (int i = 0; i < 5; i++) {
                    // 왼발을 옮겨 현재 모습이 됐을 때 최소의 힘 저장
                    dp[s][n][j] = Math.min(dp[s - 1][i][j] + mp[i][n], dp[s][n][j]);
                }
            }
            s++;
        }
        s--; // s가 1에서 시작하기 때문에 처리한 지시의 개수보다 1크개 설정돼 있는 것을 상쇄

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                min = Math.min(min, dp[s][i][j]); // 최솟값 찾기
            }
        }
        System.out.println(min);
    }
}
