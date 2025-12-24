import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Pro048 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()); // 1번째 수
        int b = Integer.parseInt(st.nextToken()); // 2번째 수
        int c = Integer.parseInt(st.nextToken()); // 3번째 수
        long gcd = gcd(a, b); // 최대 공약수

        if (c % gcd != 0) { // c가 a와 b의 최대 공약수의 배수가 아니라면
            System.out.println(-1); // 해는 없기 때문에 -1 출력 후 종료
        }
        else { // c가 a와 b의 최대 공약수의 배수라면
            int mok = (int) (c / gcd); // c를 x와 y의 최대 공약수로 나눈 값
            long[] ret = Excute(a, b); // 최종으로 계산된 x와 y의 값
            System.out.println(ret[0] * mok + " " + ret[1] * mok);
        }
    }

    // 확장된 유클리드 호제법 함수
    private static long[] Excute(long a, long b) {
        long[] ret = new long[2]; // 최종 x, y
        if (b == 0) { // 나머지가 0이 된 순간
            // x = 1, y = 0으로 설정 후 반환
            ret[0] = 1;
            ret[1] = 0;
            return ret;
        }
        long q = a / b; // 몫
        long[] v = Excute(b, a % b); // 재귀 형태로 확장된 유클리드 호제법 수행
        ret[0] = v[1]; // x = y'
        ret[1] = v[0] - v[1] * q; // y = x' - y' * 몫
        return ret;
    }

    // 최대 공약수 구하는 함수
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return Math.abs(a);
    }
}