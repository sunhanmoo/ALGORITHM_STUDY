import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pro068 {
    // 네 방향 탐색을 위한 상수(상, 우, 하, 좌)
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int[] parent; // 대표 노드 저장 배열
    static int[][] map; // 지도 정보 저장 배열
    static int N, M; // 행렬의 크기
    static int sNum; // 다음에 붙일 섬 번호
    static boolean[][] visited; // BFS 수행할 때 방문 여부 저장 배열
    static ArrayList<ArrayList<int[]>> sumlist; // 모든 섬의 좌표 목록(섬 하나당 ArrayList<int[]>)
    static ArrayList<int[]> mlist; // BFS로 찾은 현재 섬의 좌표 목록 (섬 1개)
    static PriorityQueue<bEdge> queue; // (섬A, 섬B, 길이) 형태의 다리 후보들을 길이 오름차순으로 정렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 가로 크기
        M = Integer.parseInt(st.nextToken()); // 세로 크기
        map = new int[N][M]; // 지도 크기
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 지도 정보 저장
            }
        }

        // 1단계 : BFS로 섬을 구분하여 번호 붙이기
        sNum = 1; // 섬 번호는 1번부터 시작
        sumlist = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && visited[i][j] != true) { // 바다가 아니고 방문하지 않은 땅이라면
                    BFS(i, j); // BFS로 이 섬의 모든 좌표 찾음
                    sumlist.add(mlist); // 섬 리스트에 저장
                    sNum++; // 섬 번호 증가시키기
                }
            }
        }

        // 2단계 : 각 섬의 좌표에서 다른 섬으로 갈 수 있는 직선 다리 후보 찾기
        queue = new PriorityQueue<>();
        for (int i = 0; i < sumlist.size(); i++) {
            ArrayList<int[]> now = sumlist.get(i);

            // 섬의 모든 칸에서 4방향 직선 탐색
            for (int j = 0; j < now.size(); j++) {
                int r = now.get(j)[0]; // 행
                int c = now.get(j)[1]; // 열
                int now_S = map[r][c]; // 현재 섬 번호

                for (int d = 0; d < 4; d++) {
                    int tempR = dr[d]; // r에서 한 방향으로 얼마나 이동했는지(행 이동량)
                    int tempC = dc[d]; // c에서 한 방향으로 얼마나 이동했는지(열 이동량)
                    int blength = 0; // 다리 길이

                    // 행 이동량이 0보다 크고 N보다 작고, 열 이동량이 0보다 크고 M보다 작을 때
                    while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                        if (map[r + tempR][c + tempC] == now_S) { // 지도 좌표 위치가 현재 섬 번호인 경우
                            break; // 탐색 중단
                        }
                        else if (map[r + tempR][c + tempC] != 0) { // 다른 섬 번호인 경우
                            if (blength > 1) { // 다리 길이가 1보다 크다면
                                // 다리 후보 추가
                                queue.add(new bEdge(now_S, map[r + tempR][c + tempC], blength));
                            }
                            break;
                        }
                        else { // 바다인 경우
                            blength++; // 다리 길이 증가(한 칸 더 직진 가능)
                        }

                        if (tempR < 0) {
                            tempR--;
                        }
                        else if (tempR > 0) {
                            tempR++;
                        }
                        else if (tempC < 0) {
                            tempC--;
                        }
                        else if (tempC > 0) {
                            tempC++;
                        }
                    }
                }
            }
        }

        // 3단계 : 크루스칼 알고리즘으로 최소 신장 트리 만들기
        parent = new int[sNum]; // 총 sNum-1개의 섬 생성됨
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i; // 인덱스 번호로 초기화
        }

        int useEdge = 0; // 선택된 다리 수
        int result = 0; // 총 다리 길이

        while (!queue.isEmpty()) {
            bEdge now = queue.poll();
            if (find(now.s) != find(now.e)) { // 두 섬이 연결되어 있지 않으면 선택
                union(now.s, now.e);
                result = result + now.v; // 총 다리 길이 갱신
                useEdge++; // 다리 수 증가
            }
        }

        // 4단계 : 모든 섬이 연결되었는지 확인
        // 섬 개수 = sNum - 1
        // 필요한 다리 수 = 섬 개수 - 1 = sNum - 2
        if (useEdge == sNum - 2) {
            System.out.println(result);
        }
        else {
            System.out.println(-1); // 연결할 수 없는 경우 -1 출력
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        else {
            return parent[a] = find(parent[a]);
        }
    }

    public static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        mlist = new ArrayList<>();

        int[] start = {i, j};
        queue.add(start);
        mlist.add(start);
        visited[i][j] = true;

        map[i][j] = sNum; // 현재 땅을 현재 섬 번호로 지정

        while (!queue.isEmpty()) {
            int now[] = queue.poll();
            int r = now[0];
            int c = now[1];

            for (int d = 0; d < 4; d++) {
                int tempR = dr[d];
                int tempC = dc[d];

                while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                    // 방문하지 않은 땅이라면
                    if (visited[r + tempR][c + tempC] == false && map[r + tempR][c + tempC] != 0) {
                        addNode(r + tempR, c + tempC, queue); // 섬에 추가
                    }
                    else {
                        break;
                    }

                    if (tempR < 0) {
                        tempR--;
                    }
                    else if (tempR > 0) {
                        tempR++;
                    }
                    else if (tempC < 0) {
                        tempC--;
                    }
                    else if (tempC > 0) {
                        tempC++;
                    }
                }
            }
        }
    }

    // BFS에서 새로운 땅을 만났을 때 호출되는 함수
    static void addNode(int i, int j, Queue<int[]> queue) {
        map[i][j] = sNum; // 섬 번호로 변경
        visited[i][j] = true; // 방문 체크
        int[] temp = {i, j};
        mlist.add(temp); // 섬 좌표 저장
        queue.add(temp); // BFS 큐에 넣기
    }
}

class bEdge implements Comparable<bEdge> {
    // s:섬1, e:섬2, v:다리 길이
    int s;
    int e;
    int v;

    bEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    @Override
    public int compareTo(bEdge o) {
        return this.v - o.v;
    }
}