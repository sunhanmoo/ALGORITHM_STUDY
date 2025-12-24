import java.util.Scanner;

public class Pro072 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 집합 Sㅡ이 문자열 개수
        int M = sc.nextInt(); // 검사할 문자열 개수
        tNode root = new tNode(); // 루트 노드
        while (N > 0) { // N만큼 반복
            String text = sc.next(); // 집합 S의 문자열
            tNode now = root; // 현재 노드를 루트 노드로 설정
            for (int i = 0; i < text.length(); i++) { // i를 text 문자열 길이만큼 반복
                char c = text.charAt(i); // text 문자열의 i번째 문자 c
                // 26개 알파벳의 위치를 배열 index로 나타내기 위해 - 'a' 수행
                if (now.next[c - 'a'] == null) { // c 변수에 해당하는 다음 노드가 null인 경우
                    now.next[c - 'a'] = new tNode(); // 새로운 노드 생성
                }
                now = now.next[c - 'a']; // 현재 노드를 c 변수 노드로 변경
                if (i == text.length() - 1) { // i가 문자열의 마지막인 경우
                    now.isEnd = true; // isEnd 변수를 true로 변경
                }
            }
            N--;
        }
        int count = 0; // 정답 변수
        while (M > 0) { // M만큼 반복
            String text = sc.next(); // 검색 문자열
            tNode now = root; // 현재 노드를 루트 노드로 설정
            for (int i = 0; i < text.length(); i++) { // i를 text 문자열 길이만큼 반복
                char c = text.charAt(i); // text 문자열의 i번째 문자 c
                if (now.next[c - 'a'] == null) { // c 변수에 해당하는 다음 노드가 null인 경우
                    break; // 검색 중단
                }
                now = now.next[c - 'a']; // 현재 노드를 c 변수 노드로 변경
                if (i == text.length() - 1 && now.isEnd) { // i가 문자열의 마지막이고 현재 노드의 isEnd 값이 true인 경우
                    count++; // 증가
                }
            }
            M--;
        }
        System.out.println(count);
    }
}

class tNode { // 트라이 자료구조를 위한 클래스
    tNode[] next = new tNode[26]; // 알파벳은 총 26자
    boolean isEnd; // 문자열의 마지막 여부 표시
}