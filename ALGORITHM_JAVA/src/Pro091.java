import java.util.Scanner;

public class Pro091 {
    static long mod = 1000000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 자릿수
        long D[][] = new long[N + 1][11]; // 길이가 N일 때 높이 H로 끝나는 계단 수의 모든 경우의 수
        for (int i = 1; i <= 9; i++) { // D[1][0] = 0(0으로 시작할 수 없기 때문)
            D[1][i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            D[i][0] = D[i - 1][1];
            D[i][9] = D[i - 1][8];
            for (int j = 1; j <= 8; j++) {
                D[i][j] = (D[i - 1][j - 1] + D[i - 1][j + 1]) % mod;
            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + D[N][i]) % mod;
        }
        System.out.println(sum);
    }
}
