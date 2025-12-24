//import java.io.*;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class Pro050 {
//    static int N, M; // 노드, 에지 개수
//    static boolean visited[]; // 방문 기록 저장 배열
//    static int answer[]; // 정답 배열
//    static ArrayList<Integer> A[]; // 그래프 데이터 저장 인접 리스트
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken()); // 노드 개수
//        M = Integer.parseInt(st.nextToken()); // 에지 개수
//        A = new ArrayList[N + 1];
//        answer = new int[N + 1];
//
//        for (int i = 1; i <= N; i++) {
//            A[i] = new ArrayList<>(); // A의 인접 리스트의 각 ArrayList 초기화
//        }
//        for (int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine());
//            int S = Integer.parseInt(st.nextToken()); // 출발 노드
//            int E = Integer.parseInt(st.nextToken()); // 도착 노드
//            A[S].add(E); // S에서 E로 가는 데이터 추가
//        }
//        for (int i = 1; i <= N; i++) { // 모든 노드로 BFS 실행
//            visited = new boolean[N + 1];
//            BFS(i);
//        }
//
//        int maxVal = 0; // 신뢰 최대치
//        for (int i = 1; i <= N; i++) {
//            maxVal = Math.max(maxVal, answer[i]); // maxVal과 answer[i]를 비교해 최대치 갱신
//        }
//        for (int i = 1; i <= N; i++) {
//            if (answer[i] == maxVal) { // 가장 신뢰하는 컴퓨터라면
//                System.out.print(i + " "); // 공백을 두고 출력
//            }
//        }
//    }
//
//    public static void BFS(int index) {
//        Queue<Integer> queue = new LinkedList<Integer>();
//        queue.add(index); // 큐 자료구조에 출발 노드 추가
//        visited[index] = true; // 방문했다면 true
//        while (!queue.isEmpty()) { // 큐가 빌 때까지
//            int now_Node = queue.poll(); // 큐에서 노드 데이터 가져오기
//            for (int i : A[now_Node]) {
//                if (visited[i] == false) { // 방문하지 않은 노드라면
//                    visited[i] = true; // 방문으로 변경
//                    answer[i]++; // 가중치 증가
//                    queue.add(i); // 큐에 데이터 추가
//                }
//            }
//        }
//    }
//}

import java.util.*;
import java.io.*;

public class Pro050 {
    static int N,M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static ArrayList<Integer>[] arr;
    static boolean [] check;
    static int[] check_v;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            arr[i] = new ArrayList<>();
        }
        check_v = new int[N+1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
        }
        for(int i=1; i<=N; i++){
            check = new boolean[N+1];
            bfs(i);
        }
        int max = Integer.MIN_VALUE;
        for (int i : check_v) {
            if(i > max){
                max = i;
            }
        }
        for(int i=1; i<=N; i++){
            if(check_v[i] == max){
                System.out.print(i + " ");
            }
        }
        bw.flush();
        bw.close();
    }

    static void bfs(int n){
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        check[n] = true;
        while(!q.isEmpty()){
            int now = q.poll();
            for (Integer i : arr[now]) {
                if(!check[i]){
                    check_v[i] ++;
                    check[i] = true;
                    q.add(i);
                }
            }
        }
    }
}