import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro074 {
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간 합을 구하는 횟수
        int treeHeight = 0;
        int length = N;

        // 2^treeHeight ≥ N 을 만족하는 최소 treeHeight
        // 즉, treeHeight = k
        while ( length != 0) {
            length /= 2; // length = N
            treeHeight++;
        }

        int treeSize = (int) Math.pow(2, treeHeight + 1); // 2^k * 2 = 2^(k+1)
        // 리프 노드는 leftNodeStartIndex + 1부터 시작
        int leftNodeStartIndex = treeSize / 2 - 1; // leftNodeStartIndex = 2^k - 1
        tree = new long[treeSize + 1];

        // 데이터를 리프 노드에 입력
        for (int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        setTree(treeSize - 1); // tree 만들기

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken()); // 질의 유형
            int s = Integer.parseInt(st.nextToken()); // 시작 인덱스
            long e = Long.parseLong(st.nextToken()); // 변경값 또는 종료 인덱스

            if (a == 1) { // a가 1인 경우
                changeVal(leftNodeStartIndex + s, e); // 데이터 변경
            }
            else if (a == 2) { // a가 2인 경우
                s = s + leftNodeStartIndex; // 시작 인덱스
                e = e + leftNodeStartIndex; // 종료 인덱스
                System.out.println(getSum(s, (int) e)); // 구간 합 함수 호출
            }
            else {
                return;
            }
        }
        br.close();
    }

    public static void setTree(int i) { // 초기 트리를 구성하는 함수
        while (i != 1) { // 인덱스가 루트가 이닐 때까지 반복
            tree[i / 2] += tree[i]; // 트리 인덱스 / 2 부분(부모 노드)에 현재 인덱스의 트리값 더하기
            i--; // 인덱스 감소
        }
    }

    // 값을 변경하는 함수
    public static void changeVal(int index, long val) { // 시작 인덱스, 변경값
        long diff = val - tree[index]; // 현재 노드의 값과 변경된 값의 차이
        while (index > 0) { // 시작 인덱스가 0보다 클 때
            tree[index] = tree[index] + diff; // 시작 인덱스의 트리값에 diff값을 더한다
            index = index / 2; // 시작 인덱스 = 시작 인덱스 / 2
        }
    }

    // 구간 합을 구하는 함수
    public static long getSum(int s, int e) { // 시작 인덱스, 종료 인덱스
        long partSum = 0; // 부분 합
        while (s <= e) { // 시작 인덱스와 종료 인덱스가 교차될 때까지
            if (s % 2 == 1) { // 시작 인덱스의 나머지가 1인 경우
                partSum = partSum + tree[s]; // 해당 노드의 값을 구간 합에 추가
                s++; // 시작 인덱스 증가
            }
            if (e % 2 == 0) { // 종료 인덱스의 나머지가 0인 경우
                partSum = partSum + tree[e]; // 해당 노드의 값을 구간 합에 추가
                e--; // 종료 인덱스 감소
            }
            s = s / 2;
            e = e / 2;
        }
        return partSum; // 구간 합 결과 반환
    }
}