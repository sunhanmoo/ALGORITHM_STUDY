import java.util.Scanner;
import java.util.Stack;

public class Pro011 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수열 개수
        int A[] = new int[N]; // 수열 배열

        for(int i = 0; i < N; i++){
            A[i] = sc.nextInt(); // 수열 입력받기
        }

        Stack<Integer> stack = new Stack<>();
        int num = 1;
        StringBuffer bf = new StringBuffer();
        boolean result = true;

        for(int i = 0; i < A.length; i++){
            int su = A[i]; // 현재 수열의 수
            if(su >= num){
                while(su >= num){
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop(); // 같아지면 pop
                bf.append("-\n");
            }
            else{
                int n = stack.pop();
                if(n > su){
                    System.out.println("NO");
                    result = false;
                    break;
                }
                else{
                    bf.append("-\n");
                }
            }
        }
        if(result == true){
            System.out.println(bf.toString());
        }
    }
}
