import java.io.IOException;
import java.util.Scanner;

public class Pro097 {
    static int N;
    static Matrix[] M;
    static int[][] D;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 행렬 개수 입력
        M = new Matrix[N + 1]; // 행렬 저장 클래스 배열
        D = new int[N + 1][N + 1]; // D[i][j]: i~j번째 행렬까지 최소 연산 횟수를 저장하는 배열

        for (int i = 0; i < D.length; i++) { // 행의 개수
            for (int j = 0; j < D[i].length; j++) { // i번째 행의 열의 개수
                D[i][j] = -1; // D 배열 초기화
            }
        }

        for (int i = 1; i <= N; i++) {
            // 행렬의 크기 (y x x 행렬)
            int y = sc.nextInt();
            int x = sc.nextInt();
            M[i] = new Matrix(y, x);
        }

        System.out.println(excute(1, N));
    }

    // 톱-다운 방식으로 점화식 함수 구현
    static int excute(int s, int e) { // s: 시작 인덱스, e: 종료 인덱스
        int result = Integer.MAX_VALUE;
        if (D[s][e] != -1) { // 게산했던 부분이면 다시 계산하지 않는다(메모이제이션)
            return D[s][e];
        }
        if (s == e) { // 행렬 1개의 곱셈 연산의 수
            return 0;
        }
        if (s + 1 == e) { // 행렬 2개의 곱셈 연산의 수
            return M[s].y * M[s].x * M[e].x;
        }
        for (int i = s; i < e; i++) { // 행렬이 3개 이상일 때 곱셈 연산의 수
            result = Math.min(result, M[s].y * M[i].x * M[e].x + excute(s, i) + excute(i + 1, e));
        }
        return D[s][e] = result;
    }

    // 행렬 정보 저장 클래스
    static class Matrix {
        private int y;
        private int x;

        Matrix(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
