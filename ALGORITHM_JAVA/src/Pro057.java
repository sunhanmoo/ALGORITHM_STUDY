import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Pro057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 건물 종류의 수
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(); // 데이터 저장 인접 리스트
        for (int i = 0; i <= N; i++) {
            A.add(new ArrayList<>()); // 건물의 개수만큼 인접 리스트 초기화
        }
        int[] indegree = new int[N + 1]; // 진입 차수 배열
        int[] selfBuild = new int[N + 1]; // 자기 자신을 짓는 데 걸리는 시간
        for (int i = 1; i<= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            selfBuild[i] = Integer.parseInt(st.nextToken()); // 자신의 생산 시간 저장
            while (true) { // 인접 리스트 데이터 저장
                int preTemp = Integer.parseInt(st.nextToken()); // 먼저 지어져야 하는 건물의 번호
                if (preTemp == -1) { // -1을 만난다면 줄의 끝을 의미
                    break;
                }
                A.get(preTemp).add(i);
                indegree[i]++; // 진입 차수 배열 데이터 저장
            }
        }
        // 위상 정렬
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i); // 진입 차수 배열의 값이 0인 건물(노드)을 큐에 삽입
            }
        }
        int[] result = new int[N + 1]; // 정답 배열
        while (!queue.isEmpty()) {
            int now = queue.poll(); // 현재 노드
            for (int next : A.get(now)) {
                indegree[next]--; // 목표 노드 진입 차수 배열 --
                // 시간 업데이트
                result[next] = Math.max(result[next], result[now] + selfBuild[now]); // 현재 저장된 값, 현재 출발 노드 + 비용
                if (indegree[next] == 0) { // 목표 노드의 진입 차수가 0이라면
                    queue.offer(next); // 우선순위 큐에 목표 노드 추가
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            System.out.println(result[i] + selfBuild[i]);
        }
    }
}
