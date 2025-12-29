import java.util.Scanner;

public class Pro095 {
    static long mod = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 빌딩의 개수
        int L = sc.nextInt(); // 가장 왼쪽에서 보이는 빌딩의 개수
        int R = sc.nextInt(); // 가장 오른쪽에서 보이는 빌딩의 개수
        // D[N][L][R]: N개의 빌딩이 있고 왼쪽에서 L개, 오른쪽에서 R개가 보일 때 가능한 경우의 수
        long D[][][] = new long[101][101][101];
        D[1][1][1] = 1; // 건물이 1개면 경우의 수는 1

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                for (int k = 1; k <= R; k++) {
                    D[i][j][k] = (D[i - 1][j][k] * (i - 2) + (D[i - 1][j][k - 1] + D[i - 1][j - 1][k])) % mod;
                }
            }
        }
        System.out.println(D[N][L][R]);
    }
}
