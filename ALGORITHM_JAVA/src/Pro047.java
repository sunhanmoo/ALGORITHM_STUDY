import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Pro047 {
    static ArrayList<cNode>[] A; // 인접 리스트(각 재료의 비율)
    static long lcm; // 최소 공배수
    static boolean visited[]; // 탐색 여부 저장 배열
    static long D[]; // 각 노드값 저장 배열

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = sc.nextInt(); // 필요한 재료 개수
        A = new ArrayList[N];
        visited = new boolean[N];
        D = new long[N];
        lcm = 1;

        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<cNode>();
        }

        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt(); // a번째 재료의 질량
            int b = sc.nextInt(); // b번째 재료의 질량
            // a / b = p / q
            int p = sc.nextInt();
            int q = sc.nextInt();

            A[a].add(new cNode(b, p, q));
            A[b].add(new cNode(a, q, p));
            lcm *= (p * q / gcd(p, q)); // 최소 공배수 = 두 수의 곱 / 최대 공약수
        }

        D[0] = lcm; // 0번 노드(임의의 노드)에 최소 공배수 저장
        DFS(0); // 0번 노드에서 DFS 탐색 수행
        long mgcd = D[0];
        for (int i = 1; i < N; i++) {
            mgcd = gcd(mgcd, D[i]); // D 배열 값들의 최대 공약수 계산
        }
        for (int i = 0; i < N; i++) {
            System.out.print(D[i] / mgcd + " "); // D 배열 값들을 최대 공약수로 나눠 정답 출력
        }
    }

    // 최대 공약수 구하는 함수
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }

    // DFS 함수
    public static void DFS(int Node) {
        visited[Node] = true; // 방문했다면 true
        for (cNode i : A[Node]) {
            int next = i.getB();
            if (!visited[next]) { // 방문하지 않은 노드라면
                D[next] = D[Node] * i.getQ() / i.getP();
                DFS(next);
            }
        }
    }
}

class cNode {
    int b; // 다음 노드
    int p; // 비율 1
    int q; // 비율 2

    public cNode(int b, int p, int q) {
        super();
        this.b = b;
        this.p = p;
        this.q = q;
    }

    public int getB() {
        return b;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }
}
