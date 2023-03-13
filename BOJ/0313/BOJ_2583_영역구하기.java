package codingTest;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583_영역구하기 {
	
	static int M, N, K;
	static int cnt;

	static int[][] arr;
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static ArrayList<Integer> list;
	
	public static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		int sum = 1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny] || arr[nx][ny]==0) continue;
				if(arr[nx][ny]==1 && !visited[nx][ny]) {
					sum += arr[nx][ny];
					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
		}
		list.add(sum);
		return 1;
	}
	
	// 직사각형 영역은 으로 만들어 줌
	public static void draw(int x1, int y1, int x2, int y2) {
		for(int i=x1; i<x2; i++) {
			for(int j=y1; j<y2; j++) {
				arr[i][j] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		list = new ArrayList<>();
		
		// 1로 초기화
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = 1;
			}
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			draw(x1, y1, x2, y2);
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && arr[i][j]!=0) {
					cnt += bfs(i, j);
				}
			}
		}

		System.out.println(cnt);
		
		Collections.sort(list);
		for(int x : list) {
			System.out.print(x + " ");
		}
	}
}
