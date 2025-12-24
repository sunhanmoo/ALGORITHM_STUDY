import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Pro037 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수열 개수
        // 1보다 큰 수, 1, 0. 음수 총 4가지 유형으로 분류
        PriorityQueue<Integer> plusPq = new PriorityQueue<>(Collections.reverseOrder()); // 양수는 내림차순 정렬
        PriorityQueue<Integer> minusPq = new PriorityQueue<>(); // 음수
        int one = 0; // 1
        int zero = 0; // 0

        for (int i = 0; i < N; i++) {
            int data = sc.nextInt();
            if (data > 1) {
                plusPq.add(data);
            }
            else if (data == 1) {
                one++;
            }
            else if (data == 0) {
                zero++;
            }
            else {
                minusPq.add(data);
            }
        }
        int sum = 0;

        // 양수 처리
        while (plusPq.size() > 1) {
            int first = plusPq.remove();
            int second = plusPq.remove();
            sum = sum + first * second;
        }
        if (!plusPq.isEmpty()) {
            sum = sum + plusPq.remove();
        }

        // 음수 처리
        while (minusPq.size() > 1) {
            int first = minusPq.remove();
            int second = minusPq.remove();
            sum = sum + first * second;
        }
        if (!minusPq.isEmpty()) {
            if (zero == 0) {
                sum = sum + minusPq.remove();
            }
        }

        // 1 처리
        sum = sum + one;
        System.out.println(sum);
    }
}
