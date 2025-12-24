import java.util.Scanner;

public class Pro079 {
    static int N, K;
    static int[][] D;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        D = new int[N + 1][N + 1];

        // 초기화
        for (int i = 0; i <= N; i++) {
            D[i][1] = i;
            D[i][0] = 1;
            D[i][i] = 1;
        }

        // 점화식을 통해 배열 값 채우기
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) { // 고르는 수의 개수가 전체 개수를 넘을 수 없기 때문에 i만큼 반복
                D[i][j] = D[i - 1][j] + D[i - 1][j - 1]; // 조합 기본 점화식
            }
        }

        System.out.println(D[N][K]);
    }
}
