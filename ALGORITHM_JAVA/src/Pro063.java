import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Pro063 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, sCity, eCity;
    static long distance[], cityMoney[];
    static Edge4 edges[];

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시 개수
        sCity = Integer.parseInt(st.nextToken()); // 출발 도시
        eCity = Integer.parseInt(st.nextToken()); // 도착 도시
        M = Integer.parseInt(st.nextToken()); // 교통 수단의 개수
        edges = new Edge4[M]; // 에지 리스트 배열
        distance = new long[N]; // 최단 거리 배열
        cityMoney = new long[N]; // 각 도시에서 최대로 벌 수 있는 돈
        Arrays.fill(distance, Long.MIN_VALUE); // 최단 거리 배열 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 출발 노드
            int end = Integer.parseInt(st.nextToken()); // 도착 노드
            int price = Integer.parseInt(st.nextToken()); // 가중치
            edges[i] = new Edge4(start, end, price); // 에지 리스트에 저장
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cityMoney[i] = Long.parseLong(st.nextToken()); // 벌 수 있는 돈 저장
        }
        distance[sCity] = cityMoney[sCity]; // 거리 배열의 출발 노드를 cityMoney[출발 노드]로 초기화 (돈으로 바꿈)
        // 양수 사이클이 전파되도록 충분히 큰 수로 반복(N-1개가 아님)
        for (int i = 0; i <= N + 100; i++) {
            for (int j = 0; j < M; j++) {
                int start = edges[j].start;
                int end = edges[j].end;
                int price = edges[j].price;
                if (distance[start] == Long.MIN_VALUE) { // 출발 노드가 방문하지 않은 노드라면
                    continue; // 넘어가기
                }
                else if (distance[start] == Long.MAX_VALUE) { // 출발 노드가 양수 사이클에 연결된 노드라면
                    distance[end] = Long.MAX_VALUE; // 종료 노드도 연결 노드로 업데이트
                }
                // 종료 노드 값 < 출발 노드 값 + 도착 도시에서의 수입 - 에지의 가중치
                else if (distance[end] < distance[start] + cityMoney[end] - price) {
                    distance[end] = distance[start] + cityMoney[end] - price; // 더 많은 돈을 벌 수 있게 업데이트
                    if (i >= N - 1) { // N-1번 이후 업데이트 되는 종료 노드는
                        distance[end] = Long.MAX_VALUE; // 양수 사이클 연결 노드로 변경
                    }
                }
            }
        }
        if (distance[eCity] == Long.MIN_VALUE) { // 버는 돈보다 쓰는 돈이 더 많은 경우
            System.out.println("gg"); // 도착 불가능
        }
        else if (distance[eCity] == Long.MAX_VALUE) { // 무한대로 돈을 벌 수 있는 경우
            System.out.println("Gee");
        }
        else {
            System.out.println(distance[eCity]); // 최단 거리 배열 출력
        }
    }
}

class Edge4 {
    int start; // 시작점
    int end; // 도착점
    int price; // 에지 비용

    public Edge4(int start, int end, int price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }
}