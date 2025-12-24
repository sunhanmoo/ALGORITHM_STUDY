import java.util.Scanner;

public class Pro039 {
    static int answer = 0; // 정답 변수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String example = sc.nextLine(); // 주어진 식
        String[] str = example.split("-"); // 주어진 식을 "-"를 기준으로 나누기
        for (int i = 0; i < str.length; i++) {
            int temp = mySum(str[i]);
            if (i == 0) { // 가장 앞 데이터인 경우
                answer = answer + temp; // answer에 결괏값 더하기
            }
            else { // 가장 앞 데이터가 아닌 경우
                answer = answer - temp; // answer에 결괏값 빼기
            }
        }
        System.out.println(answer);
    }

    static int mySum(String a) { // "-"를 기준으로 나눠진 식 더하기 연산 수행 함수
        int sum = 0;
        String temp[] = a.split("[+]"); // 이스케이프 처리
        for (int i = 0; i < temp.length; i++) {
            sum += Integer.parseInt(temp[i]);
        }
        return sum;
    }
}
