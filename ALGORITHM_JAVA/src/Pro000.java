import java.util.Arrays;
import java.util.Scanner;

public class Pro000 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수의 개수
        int[] D = new int[N]; // 숫자 저장 배열

        for (int i = 0; i < N; i++) {
            D[i] = sc.nextInt();
        }

        Arrays.sort(D); // 오름차순 정렬

        for (int i = 0; i < N; i++) {
            System.out.println(D[i]);
        }

    }
}