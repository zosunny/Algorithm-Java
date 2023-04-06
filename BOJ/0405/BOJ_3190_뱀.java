// 구현 + 자료구조(Deque)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {
    
    static int N;
    static int K;    // 사과
    static int L;    // 뱀의 방향 변환 횟수
    
    static Info[] info;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};    // 오, 아래, 왼, 위
    static int[] dy = {1, 0, -1, 0};
    
    static class Info{
        int time;
        char dir;
        Info(int time, char dir){
            this.time = time;
            this.dir = dir;
        }
    }
    
    static class Snake{
        int x, y, sec;
        Snake(int x, int y, int sec){
            this.x = x;
            this.y = y;
            this.sec = sec;
        }
    }
    
    public static int move(int x, int y) {
        Deque<Snake> dq = new ArrayDeque<>();
        // 처음 뱀의 몸이 있는 칸 
        dq.add(new Snake(x, y, 0));
        visited[x][y] = true;
        // 처음 이동 오른쪽부터 시작
        int d = 0;
        // 방향 전환 정보 인덱스
        int idx = 0;
        
        while(!dq.isEmpty()) {
        	// 마지막에 들어있는 머리 좌표 확인
            Snake s = dq.peekLast();
            
            // 현재 시간이 방향 전환 시간이 되면 방향 전환해서 이동할거야
            if(idx < L) {
            	if(s.sec == info[idx].time) {
                    char tmpD = info[idx].dir;
                    // 왼쪽 이동은 d를 감소시키면 됨
                    if(tmpD == 'L') d = (d + 3) % 4;
                    // 오른쪽 이동은 d를 증가시키면 됨
                    else if(tmpD == 'D') d = (d + 1) % 4;
                    
                    idx++;
                }
            }
            
            int nx = s.x + dx[d];
            int ny = s.y + dy[d];
            int nsec = s.sec + 1;
            
            // 기저조건: 벽 또는 자기자신의 몸 즉, 방문처리된 칸에 도달하면 게임 끝
            if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny]) {
            	return s.sec + 1;
            }
            
            // 다음 칸에 사과가 있으면
            if(arr[nx][ny]==5) {
                // 그 칸 사과 없어지고
                arr[nx][ny] = 0;
                // 꼬리는 그대로 방문처리 해두고 머리가 온 칸도 방문처리
                visited[nx][ny] = true;
            // 다음 칸에 사과가 없으면
            }else {
                // 꼬리 위치한 칸 비워 -> 방문처리 해제
            	Snake tail = dq.poll();
                visited[tail.x][tail.y] = false;
                // 머리 온 칸은 방문 처리
                visited[nx][ny] = true;
            }
            dq.add(new Snake(nx, ny, nsec));
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());    // 보드 크기
        K = Integer.parseInt(br.readLine());    // 사과 개수
        
        arr = new int[N][N];
        // 사과의 위치
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x-1][y-1] = 5;
        }
        
        L = Integer.parseInt(br.readLine());    // 뱀의 방향 변환 횟수
        
        // 시간과 방향 변환 정보
        info = new Info[L];
        for(int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            info[i] = new Info(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }
        
        // (1, 1)부터 이동
        visited = new boolean[N][N];
        System.out.println(move(0, 0));
    }
}
