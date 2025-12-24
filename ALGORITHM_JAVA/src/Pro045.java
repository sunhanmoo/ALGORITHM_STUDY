import java.util.Scanner;

public class Pro045 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // 테스트 케이스
        for (int i = 0; i < t; i++) {
            int a = sc.nextInt(); // 1번째 수
            int b = sc.nextInt(); // 2번째 수
            int result = a * b / gcd(a, b); // 결괏값
            System.out.println(result);
        }
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }
}
