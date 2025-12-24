import java.util.Scanner;

public class Pro086 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 학생의 수
        long mod = 1000000000; // 나머지 연산할 수
        long D[] = new long[1000001]; // N명일 때 선물을 교환할 수 있는 모든 경우의 수
        D[1] = 0; // 1명일 때 선물을 교환할 수 있는 경우의 수는 없다
        D[2] = 1; // 2명일 때 선물을 교환할 수 있는 경우의 수는 1이다(서로 교환만 가능)

        for (int i = 3; i <= N; i++) {
            D[i] = (i - 1) * (D[i - 1] + D[i - 2]) % mod; // 완전 순열 점화식
        }

        System.out.println(D[N]);
    }
}
