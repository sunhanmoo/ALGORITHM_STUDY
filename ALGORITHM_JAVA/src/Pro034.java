import java.util.Scanner;

public class Pro034 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 배열의 크기
        int K = sc.nextInt(); // 구하려는 인덱스
        // N의 범위가 크기 때문에 int 대신 long 사용
        long start = 1, end = K; // 배열의 인덱스는 1부터 시작
        long ans = 0; // 정답

        while (start <= end) {
            long middle = (start + end) / 2; // 중간 인덱스
            long cnt = 0; // 중앙값보다 작은 수의 개수

            // 중앙값보다 작은 수는 몇 개인지 계산
            for (int i = 1; i <= N; i++) {
                // 각 행에서 중앙값보다 작거나 같은 수의 개수는 중앙값을 각 행의 첫 번째 값으로 나눈 값
                // 단, 나눈 값이 N보다 크면 N으로 설정
                cnt += Math.min(middle / i, N);
            }
            if (cnt < K) {
                start = middle + 1;
            }
            else {
                ans = middle; // 현재 단계의 중앙값을 정답 변수에 저장
                end = middle - 1;
            }
        }
        System.out.println(ans);
    }
}
