// 순조부 + BFS/DFS

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1941_소문난칠공주 {
	
	static char[][] arr;
	static boolean[] select;
	static boolean[][] visited;
	static int ans;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void bfs(int x, int y, int[][] seatArr) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx<0 || ny<0 || nx>=5 || ny>=5 || visited[nx][ny] || seatArr[nx][ny]==0) continue;
				cnt++;
				q.add(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
		if(cnt == 6) {
			ans++;
		}
	}
	

	// 전체 좌석에서 7공주 자리만 1로 만들어 주는 함수
	public static void makeSeat(Point[] seat) {
		int[][] seatArr = new int[5][5];
		for(int i=0; i<7; i++) {
			int x = seat[i].x;
			int y = seat[i].y;
			seatArr[x][y] = 1;
		}
		// DFS로 좌석 붙어 있는지 확인
		visited = new boolean[5][5];
		
		// 아무 좌석 점에서부터 시작
		bfs(seat[0].x, seat[0].y, seatArr);
	}
	
	// 25명 중 7공주 조합을 뽑는 함수. 단, S가 4명이상 포함되어야 함
	public static void combi(int cnt, int start, boolean[] select) {
		if(cnt == 7) {
			Point[] seat = new Point[7];
			int idx = 0;
			int snum = 0;
			for(int i=0; i<25; i++) {
				if(select[i]) {
					int row = i / 5;
					int col = i % 5;
					// 선택된 S의 개수 카운트
					if(arr[row][col]=='S') snum++;
					// 좌석 위치 정보 담아
					seat[idx++] = new Point(row, col);
				}
			}
			// S가 4명인 경우에만 통과
			if(snum >= 4) {
				// 해당 좌표 가지고 5X5 배열 만들어
				makeSeat(seat);
			}
			return;
		}
		for(int i=start; i<25; i++) {
			if(select[i]) continue;
			select[i] = true;
			combi(cnt+1, i+1, select);
			select[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[5][5];
		for(int i=0; i<5; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		select = new boolean[25];
		combi(0, 0, select);
		
		System.out.println(ans);
	}
}
