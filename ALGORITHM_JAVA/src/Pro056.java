import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Pro056 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 학생 수
        int M = sc.nextInt(); // 비교 횟수
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(); // 데이터 저장 인접 리스트
        for (int i = 0; i <= N; i++) {
            A.add(new ArrayList<>()); // 학생 수만큼 인접 리스트 초기화
        }
        int[] indegree = new int[N + 1]; // 진입 차수 배열
        for (int i = 0; i < M; i++) {
            // 인접 리스트 데이터 저장
            int S = sc.nextInt(); // 출발 노드
            int E = sc.nextInt(); // 도착 노드
            A.get(S).add(E);
            indegree[E]++; // 진입 차수 배열 초기 데이터 저장
        }
        Queue<Integer> queue = new LinkedList<>(); // 위상 정렬 수행
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i); // offer: 큐에 새로운 요소를 넣으려고 시도하는 함수
            }
        }
        while (!queue.isEmpty()) { // 큐가 빌 때까지 반복
            int now = queue.poll(); // 데이터 빼기
            System.out.print(now + " "); // 현재 노드값 출력
            for (int next : A.get(now)) { // 현재 노드에서 갈 수 있는 노드의 개수
                indegree[next]--; // 목표 노드 진입 차수 배열 감소
                if (indegree[next] == 0) { // 목표 노드의 진입 차수가 0이면
                    queue.offer(next); // 큐에 목표 노드 추가
                }
            }
        }
    }
}
