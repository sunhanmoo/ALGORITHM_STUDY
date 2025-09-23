import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pro016 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        mData[] A = new mData[N]; // 클래스를 데이터로 담는 배열
        for(int i = 0; i < N; i++){
            //  N개의 줄에서 정수를 하나씩 읽어 mData(int value, int index)로 만들어 배열에 넣음
            A[i] = new mData(Integer.parseInt(br.readLine()), i); // index로 i를 함께 저장
        }
        Arrays.sort(A); // A 배열 정렬

        int max = 0;
        for(int i = 0; i < N; i++){
            // A[i].index는 정렬 전 index, i는 정렬 후 index
            if(max < A[i].index - i){ // 정렬 전 index - 정렬 후 index 최댓값 구하기
                max = A[i].index - i;
            }
        }
        System.out.println(max + 1); // 마지막으로 교환이 없었던 확인 라운드까지 포함
    }
}

class mData implements Comparable<mData> {
    int value; // 값
    int index; // 인덱스

    public mData(int value, int index) {
        super(); // 자식 클래스에서 부모 생성자를 호출할 경우
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(mData o) { // 값 기준으로 오름차순 비교
        return this.value - o.value; // 음수면 앞, 양수면 뒤
    }
}