import java.io.IOException;
import java.util.Scanner;

public class Pro085 {
    static int N, M, K;
    static int[][] D;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // a의 개수
        M = sc.nextInt(); // z의 개수
        K = sc.nextInt(); // 몇 번째 문자열인지
        D = new int[202][202];

        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) { // 초기화
                    D[i][j] = 1;
                }
                else {
                    // i = 남은 문자 총 개수, j = 남은 z 개수
                    D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
                    if (D[i][j] > 1000000000) { // D[i][j] 값이 K의 범위를 벗어나는 경우
                        D[i][j] = 1000000001; // K 범위의 최댓값으로 저장
                    }
                }
            }
        }
        if (D[N + M][M] < K) { // 주어진 자릿수로 만들 수 없는 문자열이라면
            System.out.println(-1); // -1 출력
        }
        else {
            while (!(N == 0 && M == 0)) { // 모든 문자를 사용할 때까지
                if (D[N - 1 + M][M] >= K) { // a를 선택했을 때 남은 문자로 만들 수 있는 모든 경우의 수가 K보다 큰 경우
                    System.out.print("a"); // a 출력
                    N--; // a문자 개수 감소
                }
                else { // a를 선택했을 때 남은 문자로 만들 수 있는 모든 경우의 수가 K보다 작은 경우
                    System.out.print("z"); // z 출력
                    // K를 업데이트
                    // K - 현재 자릿수에서 a를 선택했을 때 남아 있는 문자들로 만들 수 있는 모든 경우의 수
                    K = K - D[N - 1 + M][M];
                    M--; // z문자 개수 감소
                }
            }
        }
    }
}
