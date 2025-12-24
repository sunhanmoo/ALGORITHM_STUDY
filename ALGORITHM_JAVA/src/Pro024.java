import java.util.Scanner;

public class Pro024 {
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 자릿수

        // 일의 자리 소수는 2, 3, 5, 7만 존재
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    // DFS 함수
    static void DFS(int number, int jarisu) { // 숫자, 자릿수
        if(jarisu == N) {
            if(isPrime(number)) {
                System.out.println(number);
            }
            return;
        }
        for(int i = 1; i < 10; i++) {
            // 마지막 자리가 짝수이면 그 수는 2로 나누어져 소수가 될 수 없으므로 탐색에서 제외
            if(i % 2 == 0) {
                continue;
            }
            if(isPrime(number * 10 + i)) { // 소수라면 재귀 함수로 자릿수를 늘림
                DFS(number * 10 + i, jarisu + 1);
            }
        }
    }

    // 소수 구하기 함수
    static boolean isPrime(int num) {
        // 1은 계산 필요 없음, 어떤 수가 나누어 떨어지려면 최대 절반보다 작은 수로 나눠져야 하기 때문에 num / 2
        for(int i = 2; i <= num / 2; i++) {
            if(num % i == 0) { // 나누어 떨어지면
                return false; // 소수가 아니므로 false
            }
        }
        return true;
    }
}
