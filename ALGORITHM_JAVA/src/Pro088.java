import java.util.Scanner;

public class Pro088 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 퇴사일
        int[] D = new int[N + 2]; // 오늘부터 퇴사일까지 벌 수 있는 최대 수입
        int[] T = new int[N + 1]; // 상담에 필요한 일 수
        int[] P = new int[N + 1]; // 상담을 완료했을 때 받는 수입

        // 일 수와 수입 입력
        for (int i = 1; i <= N; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        for (int i = N; i > 0; i--) {
            if (i + T[i] > N + 1) { // i번째 상담을 퇴사일까지 끝낼 수 없을 때
                D[i] = D[i + 1];
            }
            else { // i번째 상담을 퇴사일까지 끝낼 수 있을 때
                D[i] = Math.max(D[i + 1], P[i] + D[i + T[i]]);
            }
        }

        System.out.println(D[1]); // 1일부터 퇴사일까지 벌 수 있는 최대 수입
    }
}