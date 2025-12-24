import java.util.PriorityQueue;
import java.util.Scanner;

public class Pro067 {
    static int[] parent;
    static PriorityQueue<pEdge> queue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 노드 수
        int M = sc.nextInt(); // 에지 수
        queue = new PriorityQueue<>(); // 에지 정보 저장(자동 정렬을 위해 우선순위 큐 자료구조 사용)
        parent = new int [N + 1]; // 대표 노드 저장 배열

        for (int i = 0; i < N; i++) {
            parent[i] = i; // 인덱스 값으로 초기화
        }
        // 에지 정보를 우선 순위 큐에 저장
        for (int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int v = sc.nextInt();
            queue.add(new pEdge(s, e, v));
        }

        int useEdge = 0; // 사용된 에지의 수
        int result = 0; // 에지 가중치의 합
        while (useEdge < N - 1) {
            pEdge now = queue.poll();
            if (find(now.s) != find(now.e)) { // 사이클이 생성되지 않는 경우
                union(now.s, now.e); // union 연산 후
                result = result + now.v; // 가중치 합 갱신
                useEdge++; // 사용된 에지의 개수 증가
            }
        }
        System.out.println(result);
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
}

class pEdge implements Comparable<pEdge> {
    int s;
    int e;
    int v;

    pEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    @Override
    public int compareTo(pEdge o) {
         // 가중치를 기준으로 오름차순 정렬하기 위해 compareTo() 함수 재정의
        return this.v - o.v;
    }
}