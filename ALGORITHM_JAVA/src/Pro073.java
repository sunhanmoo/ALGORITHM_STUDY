import java.util.Scanner;

public class Pro073 {
    static int[][] tree;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 노드 개수
        sc.nextLine();
        tree = new int[26][2]; // 데이터 저장 2차원 배열
        for (int i = 0; i < N; i++) {
            String[] temp = sc.nextLine().split(" "); // 공백으로 노드, 왼쪽 자식 노드, 오른쪽 자식 노드 구분
            int node = temp[0].charAt(0) - 'A'; // index로 변환하기 위해 A 문자 빼기
            char left = temp[1].charAt(0);
            char right = temp[2].charAt(0);

            if (left == '.') { // 자식 노드가 없을 때 -1 저장
                tree[node][0] = -1;
            }
            else { // 왼쪽 자식 노드 인덱스 저장
                tree[node][0] = left - 'A';
            }
            if (right == '.') { // 자식 노드가 없을 때 -1 저장
                tree[node][1] = -1;
            }
            else { // 오른쪽 자식 노드 인덱스 저장
                tree[node][1] = right - 'A';
            }
        }
        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
        System.out.println();
    }

    public static void preOrder(int now) {
        if (now == -1) {
            return;
        }
        System.out.print((char)(now + 'A')); // 1. 현재 노드 출력
        preOrder(tree[now][0]); // 2. 왼쪽 자식 노드 탐색
        preOrder(tree[now][1]); // 3. 오른쪽 자식 노드 탐색
    }

    public static void inOrder(int now) {
        if (now == -1) {
            return;
        }
        inOrder(tree[now][0]); // 1. 왼쪽 자식 노드 탐색
        System.out.print((char)(now + 'A')); // 2. 현재 노드 출력
        inOrder(tree[now][1]); // 3. 오른쪽 자식 노드 탐색
    }

    public static void postOrder(int now) {
        if (now == -1) {
            return;
        }
        postOrder(tree[now][0]); // 1. 왼쪽 자식 노드 탐색
        postOrder(tree[now][1]); // 2. 오른쪽 자식 노드 탐색
        System.out.print((char)(now + 'A')); // 3. 현재 노드 출력
    }
}
