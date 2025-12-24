import java.util.Scanner;

public class Pro082 {
    static long[][] D;
    static int T, N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        D = new long[31][31];

        // 초기화
        for (int i = 0; i <= 30; i++) {
            D[i][1] = i;
            D[i][0] = 1;
            D[i][i] = 1;
        }

        for (int i = 2; i <= 30; i++) {
            for (int j = 1; j < i; j++) {
                D[i][j] = D[i - 1][j] + D[i - 1][j - 1]; // 점화식
            }
        }

        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            N = sc.nextInt();
            M = sc.nextInt();
            System.out.println(D[M][N]);
        }
    }
}
