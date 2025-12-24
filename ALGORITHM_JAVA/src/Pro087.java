import java.io.IOException;
import java.util.Scanner;

public class Pro087 {
    static int N; // 1로 만들고 싶은 수
    static int[] D; // 1로 만드는 데 걸리는 최소 연산 횟수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        D = new int[N + 1];
        D[1] = 0; // 1은 이미 1이기 때문에 연산이 필요 없다

        for (int i = 2; i <= N; i++) {
            D[i] = D[i - 1] + 1; // -1 연산 점화식(항상 가능하다)
            if (i % 2 == 0) {
                D[i] = Math.min(D[i], D[i / 2] + 1); // 나누기 2 연산 점화식
            }
            if (i % 3 == 0) { // else if를 사용하면 2와 3의 공배수는 제외하기 때문에 틀린다
                D[i] = Math.min(D[i], D[i / 3] + 1); // 나누기 3 연산 점화식
            }
        }
        System.out.println(D[N]);
    }
}