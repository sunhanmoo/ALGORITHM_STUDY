import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Pro038 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 회의 개수
        int[][] A = new int[N][2]; // 각 회의 시간
        for (int i = 0; i < N; i++) {
            A[i][0] = sc.nextInt(); // 회의 시작 시간
            A[i][1] = sc.nextInt(); // 회의 종료 시간
        }
        Arrays.sort(A, new Comparator<int[]>() {
            @Override
            public int compare(int[] S, int[] E) {
                if (S[1] == E[1]) { // 회의 종료 시간이 같은 경우
                    return S[0] - E[0]; // 회의 시작 시간이 빠른 순으로 정렬
                }
                return S[1] - E[1]; // 회의 종료 시간이 빠른 순으로 정렬
            }
        });
        int count = 0; // 총 진행 가능한 회의 수
        int end = -1;
        for (int i = 0; i < N; i++) {
            if (A[i][0] >= end) { // 겹치지 않는 다음 회의가 나온 경우
                end = A[i][1]; // 종료 시간 업데이트
                count++;
            }
        }
        System.out.println(count);
    }
}