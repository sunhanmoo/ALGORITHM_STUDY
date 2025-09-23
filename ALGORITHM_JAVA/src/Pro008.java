import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Pro008 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long A[] = new long[N];
        int count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(A);

        for(int k  = 0; k < N; k++){
            long find = A[k];
            int i = 0;
            int j = N -1;

            while(i < j){
                if(A[i] + A[j] < find){
                    i++;
                }
                else if(A[i] + A[j] > find){
                    j--;
                }
                else{
                    if(i != k && j != k){
                        count++;
                        break;
                    }
                    else if(i == k){
                        i++;
                    }
                    else if(j == k){
                        j--;
                    }
                }
            }
        }
        System.out.print(count);
        br.close();
    }
}
