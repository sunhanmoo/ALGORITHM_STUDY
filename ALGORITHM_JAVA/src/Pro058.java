import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Pro058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 도시 수
        int M = Integer.parseInt(br.readLine()); // 도로 수
        ArrayList<ArrayList<dNode>> A = new ArrayList<>(); // 도시 인접 리스트
        ArrayList<ArrayList<dNode>> reverseA = new ArrayList<>(); // 역방향 도시 인접 리스트
        for (int i = 0; i <= N; i++) {
            // 도시 수만큼 인접리스트 초기화
            A.add(new ArrayList<>());
            reverseA.add(new ArrayList<>());
        }
        int[] indegree = new int[N + 1]; // 진입 차수 배열
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()); // 출발 도시
            int E = Integer.parseInt(st.nextToken()); // 도착 도시
            int V = Integer.parseInt(st.nextToken()); // 걸리는 시간
            A.get(S).add(new dNode(E, V));
            reverseA.get(E).add(new dNode(S, V)); // 역방향 에지 정보 저장
            indegree[E]++; // 진입 차수 배열 초기 데이터 저장
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startDosi = Integer.parseInt(st.nextToken()); // 지도를 그리는 사람들이 출발하는 도시
        int endDosi = Integer.parseInt(st.nextToken()); // 지도를 그리는 사람들이 도착하는 도시
        // 위상 정렬
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startDosi);
        int[] result = new int[N + 1]; // 각 도시의 최대 걸리는 시간 저장
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (dNode next : A.get(now)) { // 현재 노드에서 갈 수 있는 노드의 개수
                indegree[next.targetNode]--; // 목표 노드 진입 차수 배열 --
                // 목표 노드의 현재 경로값과 현재 노드의 경로값 + 도로 시간값 중 큰 값으로 저장
                result[next.targetNode] = Math.max(result[next.targetNode], result[now] + next.value);
                if (indegree[next.targetNode] == 0) { // 목표 노드의 진입 차수가 0이면
                    queue.offer(next.targetNode); // 큐에 목표 노드 추가
                }
            }
        }
        // 위상 정렬 reverse
        int resultCount = 0; // 1분도 쉬지 않고 달려야 하는 도로의 수
        boolean visited[] = new boolean[N + 1]; // 각 도시의 방문 여부 저장
        queue = new LinkedList<>();
        queue.offer(endDosi);
        visited[endDosi] = true; // 도착 도시를 방문 도시로 표시
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (dNode next : reverseA.get(now)) { // 현재 노드에서 갈 수 있는 노드의 개수
                // 1분도 쉬지 않는 도로 확인
                // 목표 노드의 result값 + 도로를 지나는 데 걸리는 시간 = 현재 노드의 result값
                if (result[next.targetNode] + next.value == result[now]) {
                    resultCount++; // 1분도 쉬지 않는 도로인 것으로 판단
                    // 중복 카운트 방지를 위해 이미 방문한 적이 있는 노드 제외
                    if (visited[next.targetNode] == false) { // 아직 방문하지 않은 도시라면
                        visited[next.targetNode] = true; // visited 배열에 방문 도시 표시
                        queue.offer(next.targetNode); // 큐에 목표 노드추가
                    }
                }
            }
        }
        System.out.println(result[endDosi]); // 만나는 시간
        System.out.println(resultCount); // 1분도 쉬지 않고 달려야 하는 도로의 수
    }
}

class dNode {
    int targetNode;
    int value;

    dNode (int targetNode, int value) {
        this.targetNode = targetNode;
        this.value = value;
    }
}