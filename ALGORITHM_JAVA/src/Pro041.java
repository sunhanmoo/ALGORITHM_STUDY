import java.util.Scanner;

public class Pro041 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long Min = sc.nextLong(); // 시작 수
        long Max = sc.nextLong(); // 종료 수
        long[] A = new long[10000001]; // 10의 14제곱의 제곱근인 10의 7제곱 범위까지
        for (int i = 2; i < A.length; i++) { // 0과 1은 소수가 아니기 때문에 제외
            A[i] = i; // 인덱스 값을 요소로 저장
        }
        for (int i = 2; i <= Math.sqrt(A.length); i++) { // 제곱근까지만 탐색
            if (A[i] == 0) { // 소수가 아닌 경우
                continue; // 넘어가기
            }
            for (int j = i + i; j < A.length; j = j + i) {
                A[j] = 0; // 배수인 경우 0으로 변경
            }
        }
        int count = 0; // 거의 소수의 개수
        for (int i = 2; i < 10000001; i++) {
            if (A[i] != 0) { // 소수일 때
                long temp = A[i]; // 현재 소수 값 임시 저장
                // 2의 X제곱이 후보가 될 수 있기 때문에 double 형 변환 필요
                // temp 값이 2048인 경우(Min은 5324)
                // A[i] >= Min / temp ->  2 >= 5324 / 2048 -> 2 >= 2
                // 성립이 되는 것처럼 인식(실제로는 성립하면 안 되는 경우(4096으로 Min 값보다 작기 때문))
                // 따라서 double을 붙여 형 변환이 필요
                // 2 >= 5324.0 /2048.0 ->  2 >= 2.XX
                // 성립이 안 되는 것 확인 가능
                while ((double)A[i] <= (double)Max / (double)temp) {
                    if((double)A[i] >= (double)Min / (double)temp) {
                        count++;
                    }
                    temp = temp * A[i];
                }
            }
        }
        System.out.println(count);
    }
}
