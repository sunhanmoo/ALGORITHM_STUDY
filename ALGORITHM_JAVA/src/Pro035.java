import java.util.Scanner;

public class Pro035 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 동전 종류 개수
        int K = sc.nextInt(); // 목표 금액
        int[] A = new int[N]; // 동전 데이터 배열

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        int count = 0; // K원을 만드는 데 필요한 동전 개수
        for (int i = N - 1; i >= 0; i-- ) {
            if (A[i] <= K) { // K보다 작거나 같은 동전을 만날 때까지
                count += (K / A[i]); // 몫은 동전 개수로
                K = K % A[i]; // 나머지는 K로 갱신
            }
        }

        System.out.println(count);
    }
}
