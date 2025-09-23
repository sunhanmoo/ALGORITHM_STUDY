import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro009 {
    static int checkArr[];
    static int myArr[];
    static int checkSecret;

    public static void main(String[] args) throws NumberFormatException ,IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // 문자열 크기
        int P = Integer.parseInt(st.nextToken()); // 부분 문자열의 크기
        int count = 0; // 조건 만족할 때 카운트
        char A[] = new char[S]; // 문자열 데이터
        checkArr = new int[4]; // 비밀번호 체크 배열
        myArr = new int[4]; // 현재 상태 배열
        checkSecret = 0; // 몇 개의 문자가 개수 조건을 충족했는지 나타내는 변수

        A = br.readLine().toCharArray(); // 문자열 입력받기
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            checkArr[i] = Integer.parseInt(st.nextToken());
            if(checkArr[i] == 0){ // 비밀번호 최소 개수 조건이 0일 경우
                checkSecret++; // 바로 비밀번호 조건 만족
            }
        }
        for(int i = 0; i < P; i++){
            Add(A[i]);
        }
        if(checkSecret == 4){
            count++;
        }
        for(int i = P; i < S; i++){
            int j = i - P;
            Add(A[i]);
            Remove(A[j]);
            if(checkSecret == 4){
                count++;
            }
        }
        System.out.println(count);
        br.close();
    }

    private static void Add(char c){
        switch(c){
            case 'A':
                myArr[0]++;
                if(myArr[0] == checkArr[0]) {
                    checkSecret++;
                }
                break;
            case 'C':
                myArr[1]++;
                if(myArr[1] == checkArr[1]) {
                    checkSecret++;
                }
                break;
            case 'G':
                myArr[2]++;
                if(myArr[2] == checkArr[2]) {
                    checkSecret++;
                }
                break;
            case 'T':
                myArr[3]++;
                if(myArr[3] == checkArr[3]) {
                    checkSecret++;
                }
                break;
        }
    }

    private static void Remove(char c){
        switch(c){
            case 'A':
                if(myArr[0] == checkArr[0]) {
                    checkSecret--;
                }
                myArr[0]--;
                break;
            case 'C':
                if(myArr[1] == checkArr[1]) {
                    checkSecret--;
                }
                myArr[1]--;
                break;
            case 'G':
                if(myArr[2] == checkArr[2]) {
                    checkSecret--;
                }
                myArr[2]--;
                break;
            case 'T':
                if(myArr[3] == checkArr[3]) {
                    checkSecret--;
                }
                myArr[3]--;
                break;
        }
    }
}
