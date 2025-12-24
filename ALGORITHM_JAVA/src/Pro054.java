import java.util.Scanner;

public class Pro054 {
    static int[] parent; // 대표 노드 저장 배열
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 도시의 수
        int M = sc.nextInt(); // 여행 계획에 속한 도시의 수
        int[][] dosi = new int[N + 1][N + 1]; // 도시 연결 데이터 배열
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dosi[i][j] = sc.nextInt(); // dosi 데이터 저장하기
            }
        }
        int[] route = new int[M + 1]; // 여행 계획 도시 저장 배열
        for (int i = 1; i <= M; i++) {
            route[i] = sc.nextInt(); // route 데이터 저장하기
        }
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i; // 대표 노드를 자기 자신으로 초기화
        }
        for (int i = 1; i<= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dosi[i][j] == 1) { // 인접 행렬에서 도시가 연결돼 있으면 union 실행
                    union(i, j);
                }
            }
        }
        int index = find(route[1]);
        // route에 포함되는 노드들의 대표 노드가 모두 동일한지 확인한 후 결괏값 출력
        for (int i = 2; i < route.length; i++) {
            if (index != find(route[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static void union(int a, int b) { // union 연산
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int find(int a) { // find 연산
        if (a == parent[a]) {
            return a;
        }
        else {
            return parent[a] = find(parent[a]); // 경로 압축
        }
    }
}