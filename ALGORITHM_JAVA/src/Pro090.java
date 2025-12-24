import java.util.Scanner;

public class Pro090 {
    static long mod = 10007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 길이 N
        long[] D = new long[1001]; // 길이 N으로 만들 수 있는 타일의 경우의 수

        D[1] = 1; // 길이가 2x1일 때 타일의 경우의 수
        D[2] = 2; // 길이가 2x2일 때 타일의 경우의 수

        for (int i = 3; i <= N; i++) {
            D[i] = (D[i - 1] + D[i - 2]) % mod;
        }

        System.out.println(D[N]);
    }
}