import java.util.Scanner;

public class Pro081 {
    static int T, N, K;
    static int[][] D;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        D = new int[15][15];

        // 초기화
        for (int i = 0; i < 15; i++) {
            D[i][1] = 1;
            D[0][i] = i;
        }

        for (int i = 1; i < 15; i++) {
            for (int j = 2; j < 15; j++) {
                D[i][j] = D[i][j - 1] + D[i - 1][j]; // 새로 도출된 점화식
            }
        }

        T = sc.nextInt(); // 테스트 케이스 개수
        for (int i = 0; i < T; i++) {
            K = sc.nextInt();
            N = sc.nextInt();
            System.out.println(D[K][N]);
        }
    }
}