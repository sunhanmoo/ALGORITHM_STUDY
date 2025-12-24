import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Pro046 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long a = sc.nextLong(); // 1번째 수 a의 길이
        long b = sc.nextLong(); // 2번째 수 b의 길이
        long result = gcd(a, b); // a, b 숫자 길이의 최대 공약수 = a, b 숫자 최대 공약수의 길이
        while (result > 0) {
            bw.write("1");
            result--; // 길이만큼 1 출력
        }
        bw.flush();
        bw.close();
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b); // goc(작은 수, 큰 수 % 작은 수)
        }
    }
}
