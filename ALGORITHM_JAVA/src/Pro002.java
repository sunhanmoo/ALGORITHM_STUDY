import java.util.Scanner;

public class Pro002 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        int score[] = new int[N];
        for(int i = 0; i < N; i++){
            score[i] = scan.nextInt();
        }

        int sum = 0;
        int M = score[0];
        double average = 0;
        for(int i = 0; i < N; i++){
            if(score[i] > M){
                M = score[i];
            }
            sum += score[i];
        }
        average = sum * 100.0 / M / N;

        System.out.print(average);
    }
}
