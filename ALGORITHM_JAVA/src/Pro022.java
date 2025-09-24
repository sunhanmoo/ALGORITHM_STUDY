import java.io.*;

public class Pro022 {
    public static int[] A;
    public static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        radix_sort(A, 5); // 최대 5자리 수까지 정렬

        for(int i = 0; i < N; i++) {
            bw.write(A[i] + "\n"); // 정렬된 결과 출력
        }
        bw.flush();
        bw.close();
    }

    public static void radix_sort(int[] A, int max_size) {
        int[] output = new int[A.length]; // // 정렬 결과를 임시 저장할 배열
        int jarisu = 1; // 현재 보고 있는 자릿수
        int count = 0; // 몇 번 반복했는지 (max_size번 반복하면 종료)

        while(count != max_size) { // 최대 자릿수만큼 반복하기
            int[] bucket = new int[10]; // // 0~9까지의 자릿수 카운트 배열

            for(int i = 0; i < A.length; i++) {
                bucket[(A[i] / jarisu) % 10]++; // 일의 자리부터 시작하기
            }
            // 단순한 카운트 배열을 누적합 배열로 바꿈
            // 이걸 쓰면 정렬된 배열에서 이 digit이 마지막으로 들어갈 위치를 알 수 있음
            for(int i = 1; i < 10;  i++) { // 합 배열을 이용해 index 계산하기
                bucket[i] += bucket[i - 1]; // bucket[i] = "현재 자릿수가 i 이하인 원소의 개수"
            }
            // 뒤에서부터 넣는다 → 안정 정렬(Stable Sort)을 보장
            for(int i = A.length - 1; i >= 0; i--) { // 현재 자릿수를 기준으로 정렬하기
                output[bucket[(A[i] / jarisu) % 10] - 1] = A[i];
                bucket[(A[i] / jarisu) % 10]--;
            }
            for(int i = 0; i < A.length; i++) {
                // 다음 자릿수로 이동하기 위해 현재 자릿수 기준 정렬 데이터 저장하기
                A[i] = output[i];
            }
            jarisu *= 10; // 자릿수 증가시키기
            count++;
        }
    }
}
