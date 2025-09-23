import java.util.Scanner;

public class Pro018 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 사람 수
        int[] A = new int[N]; // 인출하는 데 걸리는 시간
        int[] S = new int[N]; // 인출하는 데 걸리는 시간 합 배열

        for(int i = 0; i < N; ++i){
            A[i] = sc.nextInt(); // 시간 입력 받기
        }

        for(int i = 1; i < N; ++i){
            int insert_point = i; // insert_value를 넣어야 할 위치
            int insert_value = A[i]; // 지금 삽입할 값을 임시로 저장

            for(int j = i - 1; j >= 0; j--){
                // A[j]는 이미 정렬된 구간 안의 어떤 값
                // A[i]는 새로 삽입하려는 어떤 값
                if(A[j] < A[i]){ // 새로 삽입하려는 값이 원래 있던 값보다 크다
                    insert_point = j + 1; // 큰 값이 뒤에 와야하기 때문에 j+1에 삽입
                    break;
                }
                if(j == 0){ // 만약 반복 끝까지 A[j] < A[i]가 한 번도 참이 되지 않으면 맨 앞에 삽입
                    insert_point = 0;
                }
            }

            for(int j = i; j > insert_point; j--){ // insert_point까지 오른쪽으로 한 칸씩 밀기
                A[j] = A[j-1];
            }
            A[insert_point] = insert_value; // 비어 있는 A[insert_point]에 insert_value를 넣음
        }

        S[0] = A[0];
        for(int i = 1; i < N; ++i){
            S[i] = S[i - 1] + A[i]; // i번째 사람이 기다린 시간은 앞사람들 모두의 시간 + 자기 시간
        }

        int sum =0;
        for(int i = 0; i < N; i++){
            sum += S[i]; // 모든 사람의 누적 대기 시간 S[i]를 합산
        }
        System.out.println(sum);
    }
}
