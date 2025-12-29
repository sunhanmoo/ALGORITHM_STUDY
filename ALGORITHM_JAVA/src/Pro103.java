import java.util.Scanner;

public class Pro103 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 점의 개수
        long[] x = new long[N + 1]; // x 좌표
        long[] y = new long[N + 1]; // y 좌표
        for (int i = 0; i < N; i++) {
            x[i] = sc.nextLong();
            y[i] = sc.nextLong();
        }

        // 배열의 마지막에 처음 첨 다시 넣기
        x[N] = x[0];
        y[N] = y[0];

        double res = 0;
        for (int i = 0; i < N; i++) {
            // 원점, i, i+1 사이의 간편화된 CCW 공식
            res += (x[i] * y[i + 1]) - (x[i + 1] * y[i]);
        }
        String ans = String.format("%.1f", Math.abs(res) / 2.0);
        System.out.println(ans);
    }
}
