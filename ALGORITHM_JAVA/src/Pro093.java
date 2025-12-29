import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Pro093 {
    static long[][] DP;
    static ArrayList<Character> Path;
    static char[] A;
    static char[] B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray(); // 1번째 문자열
        B = br.readLine().toCharArray(); // 2번째 문자열
        // DP[i][j]에 저장된 값은
        // A의 i번재 문자까지, B의 j번째 문자까지 봤을 때
        // 만들 수 있는 최장 공통 부분 수열의 길이
        DP = new long[A.length + 1][B.length + 1];
        Path = new ArrayList<Character>(); // LCS 저장 리스트

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                // A의 i번째 문자 == B의 j번째 문자 (즉, 공통으로 쓸 수 있는 문자 하나 발견)
                // 이 문자는 그 이전까지 마나든 공통 부분 수열의 뒤에 사용 가능
                if (A[i - 1] == B[j - 1]) {
                    DP[i][j] = DP[i - 1][j - 1] + 1; // 사용 가능한 문자 하나를 찾았기 때문에 수열의 길이는 1 증가
                }
                // A의 i번째 문자 != B의 j번째 문자 (즉, 공통으로 사용 불가)
                // 선택 1. A의 현재 문자를 버린다: (i - 1, j)
                // 선택 2. B의 현재 문자를 버린다: (i, j - 1);
                else {
                    // 두 경우 중 이미 더 긴 공통 수열을 만들 수 있었던 쪽을 선택 max(위, 왼쪽)
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
                }
            }
        }
        System.out.println(DP[A.length][B.length]);
        getText(A.length, B.length);
        for (int i = Path.size() - 1; i >= 0; i--) { // 마지막부터 탐색 시작
            System.out.print(Path.get(i));
        }
        System.out.println();
    }

    public static void getText(int r, int c) { // LCS 출력 함수
        if (r == 0 || c == 0) {
            return;
        }
        if (A[r - 1] == B[c - 1]) { // 문자가 동일한 경우
            Path.add(A[r - 1]); // LCS 리스트에 추가
            getText(r - 1, c - 1); // 왼쪽 위로 이동
        }
        else { // 문자가 다른 경우
            if (DP[r - 1][c] > DP[r][c - 1]) { // 왼쪽과 위 중 큰 수로 이동
                getText(r - 1, c);
            }
            else {
                getText(r, c - 1);
            }
        }
    }
}