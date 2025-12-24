import java.util.PriorityQueue;
import java.util.Scanner;

public class Pro036 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 카드 묶음 개수
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선순위 큐
        for (int i = 0; i < N; i++) {
            int data = sc.nextInt(); // 우선순위 큐에 데이터 저장
            pq.add(data);
        }
        int data1 = 0;
        int data2 = 0;
        int sum = 0;
        while (pq.size() != 1) { // 우선순위 큐 크기가 1이 될 때까지(1묶음이 될 때까지)
            // 2개 카드 묶음을 큐에서 뽑음
            data1 = pq.remove();
            data2 = pq.remove();
            sum += data1 + data2; // 묶음의 합
            pq.add(data1 + data2); // 2개 카드 묶음의 합을 우선순위 큐에 다시 넣음
        }
        System.out.println(sum);
    }
}
