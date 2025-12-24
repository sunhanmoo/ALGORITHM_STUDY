import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Pro052 {
    // 6가지 이동 케이스 표현 배열 (0 = A, 1 = B, 2 = C)
    static int[] Sender = {0, 0, 1, 1, 2, 2};
    static int[] Receiver = {1, 2, 0, 2, 0, 1};
    static boolean visited[][]; // A, B의 무게만 있으면 C의 무게가 고정되므로 2개만 체크
    static boolean answer[]; // A가 0일 때 가능한 C의 양을 표시하는 배열
    static int now[]; // A, B, C의 값을 저장하는 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        now = new int[3]; // A, B, C 물의 양을 저장하는 배열
        now[0] = sc.nextInt(); // A
        now[1] = sc.nextInt(); // B
        now[2] = sc.nextInt(); // C
        visited = new boolean[201][201];
        answer = new boolean[201];
        BFS();
        for (int i = 0; i < answer.length; i++) {
            if (answer[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void BFS() {
        Queue<AB> queue = new LinkedList<>();
        // 초기화 부분
        queue.add(new AB(0, 0)); // 초기에는 무조건 비어있기 때문에 0
        visited[0][0] = true; // visited 배열에 현재 노드 방문 기록하기
        answer[now[2]] = true; // 초기 C = now[2] 이므로 기록

        while (!queue.isEmpty()) {
            AB p = queue.poll(); // 큐에서 상태 하나를 꺼내기

            // 현재의 A, B, C 양 구하기
            int A = p.A;
            int B = p.B;
            int C = now[2] - A - B; // C는 전체 물의 양에서 A와 B를 뺀 것

            // 그 상태에서 가능한 6가지 물 붓기 동작(A→B, A→C, B→A, B→C, C→A, C→B)을 모두 검사
            for (int k = 0; k < 6; k++) {
                int[] next = {A, B, C}; // next 배열에 현재 상태(A,B,C)를 복사
                next[Receiver[k]] += next[Sender[k]]; // 먼저 보내는 쪽의 모든 물을 받는 쪽에 더해놓고
                next[Sender[k]] = 0; // 보낸 쪽을 0으로 초기화 (일단 모두 옮긴다는 가정)
                if (next[Receiver[k]] > now[Receiver[k]]) { // 만약 받는 쪽이 용량을 초과
                    // 초과된 양은 다시 보낸 쪽에 남겨둡니다
                    next[Sender[k]] = next[Receiver[k]] - now[Receiver[k]];
                    next[Receiver[k]] = now[Receiver[k]];
                }
                if (!visited[next[0]][next[1]]) { // 아직 방문하지 않은 상태면
                    visited[next[0]][next[1]] = true; // 방문 처리
                    queue.add(new AB(next[0], next[1])); // 큐에 넣기
                    if (next[0] == 0) { // A의 용량이 0이 되면
                        answer[next[2]] = true; // 그때의 C의 용량을 저장
                    }
                }
            }
        }
    }
}

class AB {
    int A; // A의 현재 물의 양
    int B; // B의 현재 물의 양

    public AB(int A, int B) {
        this.A = A;
        this.B = B;
    }
}
