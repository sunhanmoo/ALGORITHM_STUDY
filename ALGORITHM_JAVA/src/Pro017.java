import java.util.Scanner;

public class Pro017 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String sNum = scan.next();
        int[] A = new int[sNum.length()];
        for(int i = 0; i < sNum.length(); i++){
            A[i] = Integer.parseInt(sNum.substring(i, i + 1)); // startIndex(포함)부터 endIndex(불포함)까지 반환
        }

        for(int i = 0; i < sNum.length(); i++){
            int max = i; // 최댓값이 저장돼 있는 인덱스
            for(int j = i + 1; j < sNum.length(); j++){
                if(A[j] > A[max]) {
                    max = j;
                }
            }
            if(A[i] < A[max]){
                int temp = A[i];
                A[i] = A[max];
                A[max] = temp;
            }
        }
        for(int i = 0; i < sNum.length(); i++){
            System.out.print(A[i]);
        }
    }
}
