import java.util.Scanner;

public class Pro040 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt(); // 시작 수
        int N = sc.nextInt(); // 종료 수
        int[] A = new int[N + 1]; // 소수 배열
        for (int i = 2; i <= N; i++) { // 0과 1은 소수가 아니기 때문에 제외
            A[i] = i; // 인덱스 값을 배열 요소로 저장
        }
        for (int i = 2; i <= Math.sqrt(N); i++) { // N의 제곱근까지만 탐색
            if (A[i] == 0) { // 소수가 아닌 경우
                continue; // 넘어가기
            }
            // i의 배수를 찾기 위한 범위
            // i 다음으로 가장 작은 i의 배수는 i + i(또는 2 * i)
            // i의 배수를 찾는 것이기 때문에 범위를 1씩 증가를 의미하는 i++로 두면 안 됨
            // i의 배수는 i씩 차이나기 때문에 j + i로 둬야 함
            for (int j = i + i; j <= N; j = j + i) {
                A[j] = 0; // 배수인 경우 0으로 변경
            }
        }
        for (int i = M; i <= N; i++) {
            if (A[i] != 0) { // 배수로 판별된 값 제외
                System.out.println(A[i]); // 소수 출력
            }
        }
    }
}