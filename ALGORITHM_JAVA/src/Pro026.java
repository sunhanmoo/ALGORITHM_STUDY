import java.util.Scanner;

public class Pro026 {
    static int N, M;
    static boolean[] V; // 숫자 사용 여부 저장
    static int[] S; // 수열 정보 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        S = new int[N];
        V = new boolean[N];

        backtracking(0);
    }

    private static void backtracking(int length) {
        if (length == M) { // 정답인지 확인한 후 반환
            printArray();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!V[i]) { // 사용된 수가 아니라면
                V[i] = true; // 수 사용 저장하기
                S[length] = i; // 수열에 수 사용하기
                backtracking(length + 1);
                V[i] = false; // 수 반납 저장하기
            }
        }
    }

    private static void printArray() {
        for (int i = 0; i < M; i++) {
            System.out.print(S[i] + 1 + " "); // i = 0부터이기 때문에 1씩 더해줘야 맞는 수
        }
        System.out.println();
    }
}
