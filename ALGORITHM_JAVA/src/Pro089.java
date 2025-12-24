import java.util.Scanner;

public class Pro089 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 자릿수
        // D[i][0]: 길이 i에서 끝이 0으로 끝나는 이친수의 개수
        // D[i][1]: 길이 i에서 끝이 1로 끝나는 이친수의 개수
        long[][] D = new long[N + 1][2];
        D[1][1] = 1; // 1자리 이친수는 1 한 가지
        D[1][0] = 0; // 0으로 시작할 수 없음

        for (int i = 2; i <= N; i++) {
            D[i][0] = D[i - 1][0] + D[i - 1][1];
            D[i][1] = D[i - 1][0];
        }

        System.out.println(D[N][0] + D[N][1]);
    }
}
