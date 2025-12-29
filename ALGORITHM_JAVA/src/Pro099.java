import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro099 {
    static int N; // 수열 개수
    static int maxLength; // 최대 길이 저장 변수
    static int B[] = new int[1000001]; // 현재 가장 유리한 증가 수열 저장
    static int A[] = new int[1000001]; // 수열 데이터 저장
    static int D[] = new int[1000001]; // 0 ~ i까지 i를 포함하는 최장 증가 수열의 길이 저장
    static int ans[] = new int[1000001]; // 정답 수열 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int index;
        // 수열 원소의 개수가 1개일 때는 D[1] = 1이다
        B[++maxLength] = A[1]; // B[1] = A[1]와 동일
        D[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (B[maxLength] < A[i]) { // 가장 마지막 수열보다 현재 수열이 클 때
                B[++maxLength] = A[i]; // B 배열의 끝에 A[i] 값 추가
                D[i] = maxLength; // D[i]에 최대 길이 저장
            }
            else { // 가장 마지막 수열이 현재 수열보다 작을 때
                // B 배열에서 A[i]보다 처음으로 크거나 같아지는 원소의 index 찾기
                index = binarysearch(1, maxLength, A[i]);
                B[index] = A[i];
                D[i] = index;
            }
        }
        System.out.println(maxLength); // 가장 길고, 증가하는 부분의 수열 길이 출력
        index = maxLength;
        int x = B[maxLength] + 1; // 상한선 + 1
        for (int i = N; i >= 1; i--) {
            if (D[i] == index && A[i] < x) {
                ans[index] = A[i];
                x = A[i];
                index--;
            }
        }
        for (int i = 1; i <= maxLength; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    // 현재 수열이 들어갈 수 있는 위치를 찾는 함수
    static int binarysearch(int l, int r, int now) {
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (B[mid] < now) { // B[mid]가 now보다 작으면
                l = mid + 1; // l 값을 mid + 1로 변경
            }
            else { // B[mid]가 now보다 크거나 같으면
                r = mid; // r 값을 mid 값으로 변경
            }
        }
        return l;
    }
}
