import java.util.Scanner;

public class Pro033 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 레슨 수
        int M = sc.nextInt(); // 블루레이 개수
        int[] A = new int[N]; // 기타 레슨 길이 데이터 저장 배열
        int start = 0; // 이진탐색 시작 인덱스
        int end = 0; // 이진탐색 종료 인덱스

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt(); // 기타 레슨 길이 입력
            if (start < A[i]) { // 레슨 길이 최댓값을 시작 인덱스로 저장
                start = A[i];
            }
            end = end + A[i]; // 레슨 길이 총합을 종료 인덱스로 저장
        }

        while (start <= end) {
            int middle = (start + end) / 2; // 중앙값
            int sum = 0; // 레슨 합
            int count = 0; // 현재 사용한 블루레이 개수
            for (int i = 0; i < N; i++) {
                if (sum + A[i] > middle) { // 누적 레슨 시간 + 현재 레슨 시간 > 중앙값
                    count++; // 블루레이 개수 증가
                    sum = 0; // 누적 레슨 시간 0으로 초기화
                }
                // 현재 블루레이이 시간 초과로 저장할 수 없어 새로운 블루레이로 교체한다는 의미
                sum = sum + A[i]; // 누적 레슨 시간에 현재 레슨 시간 더하기
            }
            if (sum != 0) {
                count++;
            }
            if (count > M) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }
        System.out.println(start);
    }
}