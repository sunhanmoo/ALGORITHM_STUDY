import java.util.Scanner;

public class Pro053 {
    static int[] parent; // 대표 노드 저장 배열
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 원소 개수
        int M = sc.nextInt(); // 질의 개수
        parent = new int[N + 1]; // 대표 노드 저장 배열

        for (int i = 0; i <= N; i++) {
            parent[i] = i; // 대표 노드를 자기 자신으로 초기화
        }

        for (int i = 0; i < M; i++) {
            // question이 0이라면 a가 포함돼 있는 집합과 b가 포함돼 있는 집합을 합친다는 의미 (union)
            // question이 1이라면 a와 b가 같은 집합에 포함돼 있는지 확인한다는 의미 (find)
            int question = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (question == 0) {
                union(a, b);
            }
            else {
                if (checkSame(a, b)) { // 같은 집합의 원소인지 확인
                    System.out.println("YES");
                }
                else {
                    System.out.println("NO");
                }
            }
        }
    }

    public static void union(int a, int b) { // union 연산 (대표 노드끼리 연결)
        a = find(a);
        b = find(b);
        if (a != b) { // a와 b가 다르다면
            parent[b] = a; // 합쳐주기
        }
    }

    public static int find(int a) { // find 연산 (재귀 함수 형태)
        if (a == parent[a]) { // 대표 노드와 값이 같은 경우
            return a; // 그래도 a 반환
        }
        else { // 대표 노드와 값이 다른 경우
            return parent[a] = find(parent[a]); // 값이 parent[a]와 같은 대표 노드 찾기
        }
    }

    public static boolean checkSame(int a, int b) { // 두 원소가 같은 집합인지 확인
        a = find(a);
        b = find(b);
        if (a == b) {
            return true;
        }
        return false;
    }
}
