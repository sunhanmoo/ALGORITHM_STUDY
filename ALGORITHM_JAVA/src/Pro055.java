import java.util.ArrayList;
import java.util.Scanner;

public class Pro055 {
    static int[] parent; // 대표 노드 저장 배열
    static int[] trueP; // 진실을 아는 사람 데이터
    static ArrayList<Integer>[] party; // 파티 데이터
    static int result; // 과장할 수 있는 파티 개수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 사람 수
        int M = sc.nextInt(); // 파티 개수
        int T = sc.nextInt(); // 진실을 아는 사람 수
        result = 0; // 과장할 수 있는 파티 개수 초기화
        trueP = new int[T]; // 진실을 아는 사람
        for (int i = 0; i < T; i++) {
            trueP[i] = sc.nextInt(); // 진실을 아는 사람 저장
        }
        party = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<Integer>(); // party 인접 리스트의 각 ArrayList 초기화
            int partySize = sc.nextInt();
            for (int j = 0; j < partySize; j++) {
                party[i].add(sc.nextInt());
            }
        }
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i; // 대표 노드를 자기 자신으로 초기화
        }
        // get은 Java의 List 인터페이스에 정의된 메서드
        // .get(0)은 이 리스트에서 인덱스 0에 해당하는 요소를 가져온다
        for (int i = 0; i < M; i++) { // 각 파티에 참여한 사람들을 1개의 그룹으로 만들기
            int firstPeople = party[i].get(0); // i번째 파티의 1번째 사람
            for (int j = 1; j < party[i].size(); j++) {
                union(firstPeople, party[i].get(j));
            }
        }
        for (int i = 0; i < M; i++) {
            boolean isPossible = true;
            int cur = party[i].get(0); // i번째 파티의 1번째 사람
            for (int j = 0; j < trueP.length; j++) {
                if (find(cur) == find(trueP[j])) { // 각 파티의 대표 노드와 진실을 아는 사람들의 대표 노드가 같다면
                    isPossible = false; // 과장된 얘기를 할 수 없음
                    break; // 탐색 중단 후 다음 파티(인덱스)로 넘어감
                }
            }
            if (isPossible) { // 노드가 다르다면
                result++; // 과장된 얘기 할 수 있기 때문에 증가시키기
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