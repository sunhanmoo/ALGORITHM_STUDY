import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 숫자 개수
        int K = Integer.parseInt(st.nextToken()); // K번째 수
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N]; // 숫자 데이터 저장 배열
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken()); // 숫자 입력 받기
        }

        quickSort(A, 0, N - 1, K - 1);
        System.out.println(A[K - 1]);
    }

    // 완전히 정렬하지 않아도 K번째 수가 위치한 구간만 재귀적으로 탐색해서 답을 찾는 것이 목표
    // S는 시작 인덱스, E는 끝 인덱스, K는 K번째 수의 인덱스
    public static void quickSort(int[] A, int S, int E, int K){
        if(S < E){
            int pivot = partition(A, S, E);
            if(pivot == K){ // K번째 수를 찾은 경우
                return; // 종료
            }
            else if(K < pivot){ // 왼쪽 구간만 탐색
                quickSort(A, S, pivot - 1, K);
            }
            else{ // 오른쪽 구간만 탐색
                quickSort(A, pivot + 1, E, K);
            }
        }
    }

    public static int partition(int[] A, int S, int E){
        if(S + 1 == E){ // 두 원소만 있는 경우
            if(A[S] > A[E]){
                swap(A, S, E);
            }
            return E;
        }

        int M = (S + E) / 2; // 구간 중앙 값(M)을 pivot으로 선택
        swap(A, S, M); // 맨 앞(S)과 swap
        int pivot = A[S];
        int i = S + 1, j = E;
        while(i <= j){
            while(j >= S + 1 && pivot < A[j]){
                j--; // j는 pivot보다 작은 값을 찾기 위해 왼쪽으로 이동
            }
            while(i <= E && pivot > A[i]){
                i++; // i는 pivot보다 큰 값을 찾기 위해 오른쪽으로 이동
            }
            if(i <= j){ // 만나는 경우
                swap(A, i++, j--); // swap
            }
        }
        A[S] = A[j];
        A[j] = pivot; // pivot을 정렬된 위치로 이동, j가 pivot의 최종 위

        return j;
    }

    public static void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
