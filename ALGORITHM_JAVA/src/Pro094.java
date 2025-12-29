import java.util.Scanner;

public class Pro094 {
    public static void main(String[] args) {
        // D[i][j]는 i, j의 위치를 오른쪽 아래 꼭짓점으로 정하고
        // 해당 자리에서 그릴 수 있는 가장 큰 정사각형의 변의 길이를 의미
        long[][] D = new long[1001][1001];

        Scanner sc = new Scanner(System.in);
        // 배열의 크기는 n x m
        int n = sc.nextInt();
        int m = sc.nextInt();
        long max = 0; // 정사각형 한 변의 최댓값

        for (int i = 0; i < n; i++) {
            String mline = sc.next();
            for (int j = 0; j < m; j++) {
                D[i][j] = Long.parseLong(String.valueOf(mline.charAt(j)));
                if (D[i][j] == 1 && j > 0 && i > 0) {
                    D[i][j] = Math.min(D[i - 1][j - 1], Math.min(D[i - 1][j], D[i][j - 1])) + D[i][j];
                }
                if (max < D[i][j]) {
                    max = D[i][j];
                }
            }
        }
        System.out.println(max * max);
    }
}
