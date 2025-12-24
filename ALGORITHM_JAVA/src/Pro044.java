import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pro044 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine()); // 소인수 표현
        long result = n; // 결괏값
        for (long p = 2; p <= Math.sqrt(n); p++) { // 제곱근까지만 탐색
            if (n % p == 0) { // 현재 값(p)이 소인수라면
                result = result - result / p; // 결괏값 업데이트
                while (n % p ==0) { // n에서 소인수 내역 제거
                    // 2의 7제곱 * 11 * 13인 경우
                    // 현재 소인수가 2라면
                    // 2의 7제곱을 제거하고 11 * 13만 남긴다
                    n /= p;
                }
            }
        }
        // 반복문 종료 후 n이 1보다 큰 경우
        // n이 마지막 소인수라는 뜻
        if (n > 1) {
            result = result - result / n; // result 값을 마지막으로 업데이트
        }
        System.out.println(result);
    }
}