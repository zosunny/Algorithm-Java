package day0403;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class SWEA_4193_수영대회결승전 {
	
	static int T;
	static int N;
	static int time;
	static int startX, startY;
	static int endX, endY;
	
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static int bfs() {
		time = 0;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startX, startY));
		visited[startX][startY] = true;
		while(!q.isEmpty()) {
			int qSize = q.size();
			while(qSize --> 0) {
				Point p = q.poll();
				if(p.x == endX && p.y == endY) return time;
				for(int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny] || arr[nx][ny]==1) continue;
					// 2초, 5초, .. 가 아닌, 소용돌이가 치고 있을 땐 다음 좌표가 아닌, 현재 좌표를 다시 큐에 넣어!
					if(time%3!=2 && arr[nx][ny]==2) {
						q.add(new Point(p.x, p.y));
					}else {
						q.add(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
			time++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][N];
			sb.append("#" + (t+1) + " " + bfs() + "\n");
		}
		System.out.println(sb);
	}
}
