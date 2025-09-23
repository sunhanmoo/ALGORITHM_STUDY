import java.util.Scanner;

public class Pro006 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int sum = 1;
        int count = 1;
        int start_index = 1;
        int end_index = 1;

        while(end_index != N){
            if(sum < N){
                end_index++;
                sum += end_index;
            }
            else if(sum > N){
                sum -= start_index;
                start_index++;
            }
            else{
                end_index++;
                sum += end_index;
                count++;
            }
        }
        System.out.print(count);
    }
}
