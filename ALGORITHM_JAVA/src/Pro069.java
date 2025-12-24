import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Pro069 {
    static int[] parent;
    static int N, sum;
    static PriorityQueue<lEdge> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 컴퓨터 개수
        queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] tempc = st.nextToken().toCharArray(); // 랜선 길이 저장
            for (int j = 0; j < N; j++) {
                int temp = 0;
                if (tempc[j] >= 'a' && tempc[j] <= 'z') {
                    temp = tempc[j] - 'a' + 1;
                }
                else if (tempc[j] >= 'A' && tempc[j] <= 'Z') {
                    temp = tempc[j] - 'A' + 27;
                }
                sum = sum + temp; // 총 랜선 길이 저장
                if (i != j && temp != 0) {
                    queue.add(new lEdge(i, j, temp));
                }
            }
        }
        parent = new int[N];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i; // 인덱스로 초기화
        }
        int useEdge = 0; // 사용된 에지 개수
        int result = 0; // 다솜이가 기부할 수 있는 랜선 길이
        while (!queue.isEmpty()) {
            lEdge now = queue.poll();
            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                result = result + now.v;
                useEdge++;
            }
        }
        if (useEdge == N - 1) {
            System.out.println(sum - result);
        }
        else {
            System.out.println(-1);
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
}

class lEdge implements Comparable<lEdge> {
    int s;
    int e;
    int v;

    public lEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    @Override
    public int compareTo(lEdge o) {
        return this.v - o.v;
    }
}