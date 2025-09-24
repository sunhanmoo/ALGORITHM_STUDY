import java.io.*;

public class Pro020 {
    public static int[] A, tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 정렬할 수 개수
        // 1번 인덱스부터 사용하기 때문에 N + 1 사용
        A = new int[N + 1];
        tmp = new int[N + 1]; // 임시 정렬 배열
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine()); // N개의 수 입력
        }
        // 병합 정렬 수행
        merge_sort(1, N); // 1부터 N까지 구간을 병합 정렬
        for(int i = 1; i  <= N; i++) {
            bw.write(A[i] + "\n"); // 정렬된 결과를 한 줄씩 출력
        }
        bw.flush();
        bw.close();
    }

    private static void merge_sort(int s, int e) { // start(시작점), end(종료점)
        if(e - s < 1) { // 구간 길이가 1 이하라면
            return; // 더 이상 나눌 필요가 없으므로 종료
        }
        // middle(중간점)
        int m = s + (e - s) / 2; // 시작점에서 길이의 절반만큼 이동
        // 재귀함수 형태로 구현
        merge_sort(s, m); // 왼쪽 구간
        merge_sort(m + 1, e); // 오른쪽 구간
        for(int i = s; i <= e; i++) {
            tmp[i] = A[i];
        }
        int k = s; // A에 결과를 쓸 위치
        int index1 = s; // 왼쪽 그룹 읽는 포인터
        int index2 = m + 1; // 오른쪽 그룹 읽는 포인터
        // 두 그룹을 병합하는 로직
        while(index1 <= m && index2 <= e) { // 양쪽에 아직 남아있는 값이 있을 때만 비교하고 채운다
            // 양쪽 그룹의 index가 가리키는 값을 비교해 더 작을 수를 선택해 배열에 저장하고
            // 선택된 데이터의 index 값을 오른쪽으로 한 칸 이동하기
            if(tmp[index1] > tmp[index2]) {
                A[k] = tmp[index2];
                k++;
                index2++;
            }
            else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
        }
        // 위 while문이 끝나면 한쪽은 이미 모두 병합되었고 다른 쪽에 남아있는 원소들을 순서대로 복사한다
        while(index1 <= m) { // 한쪽 그룹이 모두 선택된 후 남아있는 값 정리하기
            A[k] = tmp[index1];
            k++;
            index1++;
        }
        while(index2 <= e) {
            A[k] = tmp[index2];
            k++;
            index2++;
        }
    }
}