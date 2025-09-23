import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Pro012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N =Integer.parseInt(br.readLine());
        int[] A = new int[N]; // 수열 배열
        int[] answer = new int[N]; // 출력할 정답 배열
        String[] str = br.readLine().split(" "); // 공백으로 구분해 출력

        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(str[i]);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0); // 처음에는 항상 스택이 비어 있기 때문에 최초 값을 push해 초기화

        for(int i = 1; i < N; i++){
            while(!stack.isEmpty() && A[stack.peek()] < A[i]){
                answer[stack.pop()] = A[i];
            }
            stack.push(i);
        }

        while(!stack.empty()){
            answer[stack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < N; i++){
            bw.write(answer[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
