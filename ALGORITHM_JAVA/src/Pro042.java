import java.util.Scanner;

public class Pro042 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long Min = sc.nextLong(); // 최솟값
        long Max = sc.nextLong(); // 최댓값
        // 제곱수 판별 배열
        boolean[] Check = new boolean[(int) (Max - Min + 1)]; // 최댓값과 최솟값 차이만큼 크기 설정
        // 2의 제곱수인 4부터 Max보다 작거나 같은 값까지 반복
        for (long i = 2; i * i <= Max; i++) { // 제곱수만 탐색
            long pow = i * i; // 제곱수
            // 우리는 pow(4, 9, 16, ...)의 배수들 중
            //Min 이상, Max 이하인 값들을 찾아서 Check 배열에 표시하는 것이 목표
            //즉, 구간 안에서 다음 조건을 만족하는 j를 찾아야 한다
            // j * pow >= Min
            // j * pow <= Max
            long start_index = Min / pow; // 시작 인덱스 = 최솟값 / 제곱수
            // 나누어떨어지지 않으면(나머지가 0이 아니면) j * pow가 Min보다 작기 때문에 j를 1 증가시켜야 한다
            if (Min % pow != 0) {
                start_index++; // 나머지가 있으면 1을 더해야 Min보다 큰 제곱수에서 시작됨
            }
            for (long j = start_index; pow * j <= Max; j++) {
                Check[(int) ((j * pow) - Min)] = true; // 제곱수를 true로 변경
            }
        }
        int count = 0; // 제곱이 아닌 수
        for (int i = 0; i <= Max - Min; i++) {
            if (!Check[i]) {
                count++;
            }
        }
        System.out.println(count);
    }
}