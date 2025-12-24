import java.util.Scanner;

public class Pro027 {
    static int[] A; // 퀸 배치 정보 저장하기
    static int N; // 체스판 크기
    static int cnt = 0; // 퀸을 배치하는 경우의 수 저장하기

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = new int[N];
        backtracking(0);
        System.out.println(cnt);
    }

    private static void backtracking(int row) {
        if (row == N) { // 퀸 N개를 모두 배치한 경우
            cnt++;
            return;
        }
        for (int i = 0; i < N; i++) {
            A[row] = i;
            if(check(row)) { // 배치한 퀸이 이전 퀸들과 서로 공격할 수 없는지 확인
                backtracking(row + 1);
            }
        }
    }

    private static boolean check(int row) {
        for (int i = 0; i < row; i++) { // 일직선 배치
            if(A[i] == A[row]) {
                return false;
            }
            if(Math.abs(A[i] - A[row]) == Math.abs(row - i)) { // 대각선 배치
                return false;
            }
        }

        return true;
    }
}
