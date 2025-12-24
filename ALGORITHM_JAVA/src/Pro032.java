import java.util.Arrays;
import java.util.Scanner;

public class Pro032 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            boolean find = false;
            int target = sc.nextInt();
            int start = 0;
            int end = A.length - 1;
            while (start <= end) {
                int midI = (start + end) / 2; // 중간 인덱스
                int midV = A[midI]; // 중간 인덱스 값
                if (midV > target) {
                    end = midI - 1;
                }
                else if (midV < target) {
                    start = midI + 1;
                }
                else {
                    find = true;
                    break;
                }
            }
            if (find) {
                System.out.println(1);
            }
            else {
                System.out.println(0);
            }
        }
    }
}