import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Pro062 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static long distance[];
    static Edge3 edges[];
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 버스 개수
        M = Integer.parseInt(st.nextToken()); // 노선 개수
        distance = new long[N + 1]; // 최단 거리 배열
        edges = new Edge3[M + 1]; // 에지 리스트 배열
        Arrays.fill(distance, Integer.MAX_VALUE); // 거리 배열을 무한대 값으로 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 출발 노드
            int end = Integer.parseInt(st.nextToken()); // 도착 노드
            int time = Integer.parseInt(st.nextToken()); // 걸리는 시간
            edges[i] = new Edge3(start, end, time); // 에지 리스트 배열에 주어진 에지 정보 저장
        }

        // 벨만-포드 알고리즘
        distance[1] = 0; // 출발 노드의 거리 배열은 0으로 초기화
        for (int i = 1; i < N; i++) { // N-1만큼 반복
            for (int j = 0; j < M; j++) {
                Edge3 edge = edges[j]; // 현재 에지 데이터 가져오기
                // 출발 노드 거리 배열의 값이 무한대가 아니고
                // 종료 노드 값 > 출발 노드 값 + 에지 가중치
                if (distance[edge.start] != Integer.MAX_VALUE
                        && distance[edge.end] > distance[edge.start] + edge.time) {
                    distance[edge.end] = distance[edge.start] + edge.time; // 더 작은 최단 거리로 업데이트
                }
            }
        }
        boolean mCycle = false; // 음수 사이클 여부
        for (int i = 0; i < M; i++) {
            Edge3 edge = edges[i]; // 현제 에지 데이터 가져오기
            // 다시 한 번 더 최단 거리로 업데이트 된다면
            if (distance[edge.start] != Integer.MAX_VALUE
                    && distance[edge.end] > distance[edge.start] + edge.time) {
                mCycle = true; // 에지의 가중치에 음수 값이 포함 된다 판단
            }
        }
        if (!mCycle) { // 음수 사이클이 없는 경우
            for (int i = 2; i <= N; i++) {
                if (distance[i] == Integer.MAX_VALUE) { // 최단 거리 배열이 무한대 값이면
                    System.out.println("-1"); // 최단 거리가 없으므로 -1 출력
                }
                else {
                    System.out.println(distance[i]); // 최단 거리 배열 값 출력
                }
            }
        }
        else { // 음수 사이클이 있는 경우
            System.out.println("-1"); // 최단 거리 자체가 존재할 수 없으므로 -1 출력 후 종료
        }
    }
}

class Edge3 {
    int start;
    int end;
    int time;

    public Edge3(int start, int end, int time) {
        this.start = start; // 출발 노드
        this.end = end; // 도착 노드
        this.time = time; // 걸리는 시간
    }
}