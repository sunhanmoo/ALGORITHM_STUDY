import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro028 {
    static int[][] M = new int[10][10]; // 종이 상태 저장 배열
    static int[]S = {0, 5, 5, 5, 5, 5}; // 남은 색종이 수 저장 배열
    static int result = Integer.MAX_VALUE; // 최소로 사용한 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                M[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0); // 좌표, 몇 개의 색종이 사용했는지
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1); // 불가능한 경우 -1 출력
        }
        else {
            System.out.println(result);
        }
    }

    static void backtracking(int xy, int useCnt) { // 좌표, 몇 개의 색종이 사용했는지
        // 색종이로 1이 적힌 모든 칸을 붙였을 때 탐색 종료
        if (xy == 100) {
            result = Math.min(result, useCnt);
            return;
        }

        // x = 9, y = 8이면 xy = 89
        int x = xy % 10; // 일의 자리
        int y = xy / 10; // 십의 자리

        // 이전에 최소로 사용한 색종이 수보다 더 많으면 탐색 중단
        if (result <= useCnt) {
            return;
        }
        if (M[y][x] == 1) {
            for (int i = 5; i > 0; i--) { // 큰 색종이부터 붙여보기
                if (S[i] > 0 && check(x, y, i)) { // 색종이가 남아있고, 붙일 수 있는 위치여야 한다
                    S[i]--; // 색종이 사용
                    fill(x, y, i, 0); // 종이 붙이기(붙이면 1을 0으로 변경)
                    backtracking(xy + 1, useCnt + 1); // 한 칸 이동
                    S[i]++; // 사용한 색종이 다시 채우기
                    fill(x, y, i, 1); // 종이 떼어 내기(기존에 덮인 부분 0을 1로 변경)
                }
            }
        }
        else {
            backtracking(xy + 1, useCnt); // 현재 좌표 값이 0이면 다음 칸으로 이동
        }
    }

    static void fill(int x, int y, int size, int num) { // 색종이 크기, 채우는 값(0 또는 1)
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                M[i][j] = num;
            }
        }
    }

    static boolean check(int x, int y, int size) { // 색종이 붙일 수 있는지 확인
        if (x + size > 10 || y + size > 10) { // 범위 밖이면 붙일 수 없음
            return false;
        }
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (M[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
