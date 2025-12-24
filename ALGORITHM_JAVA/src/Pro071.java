import java.util.ArrayList;
import java.util.Scanner;

public class Pro071 {
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int N;
    static int answer = 0;
    static int deleteNode = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 노드 개수
        tree = new ArrayList[N]; // 트리 데이터 저장 인접 리스트
        visited = new boolean[N]; // 방문 기록 저장 배열
        int root = 0;

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<Integer>(); // tree 인접 리스트의 각 ArrayList 초기화
        }

        // 각 노드의 부모
        for (int i = 0; i < N; i++) {
            int p = sc.nextInt();
            if (p != -1) { // 루트 노드가 아니라면
                tree[i].add(p);
                tree[p].add(i);
            }
            else {
                root = i;
            }
        }

        deleteNode = sc.nextInt(); // 삭제 노드값 저장
        if (deleteNode == root) { // 삭제 노드가 루트 노드라면
            System.out.println(0); // 모두 삭제되므로 DFS 실행하지 않고 종료
        }
        else {
            DFS(root);
            System.out.println(answer);
        }
    }

    public static void DFS(int number) {
        visited[number] = true; // visited 배열에 현재 노드 방문 기록
        int cNode = 0; // 자식 노드
        for (int i : tree[number]) { // 현재 노드의 연결 노드 중
            if (visited[i] == false && i != deleteNode) { // 방문하지 않은 노드이고 삭제 노드가 아닐 때
                cNode++; // 자식 노드 증가
                DFS(i);
            }
        }
        if (cNode == 0) { // 자식 노드 개수가 0이라면
            answer++; // 리프 노드 개수 증가
        }
    }
}
