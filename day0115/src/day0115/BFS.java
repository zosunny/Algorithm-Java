package day0115;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
	
	public static int[][] graph;
	public static boolean[][] visited;
	
	public static void bfs(int x, int y, int N, int M) {
		visited[x][y] = true;
		
		// 형을 미리 강제함
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[]{x, y});
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		while(!que.isEmpty()) {
			int[] q = que.poll();
			for(int i=0; i<4; i++) {
				int nx = q[0] + dx[i];
				int ny = q[1] + dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M) {
					continue;
				}
				if(graph[nx][ny] == 1 && visited[nx][ny]== false) {
					visited[nx][ny] = true;
					graph[nx][ny] = graph[q[0]][q[1]] +1;
					que.offer(new int[] {nx, ny});
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		sc.nextLine();
		
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String ice = sc.nextLine();
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(ice.charAt(j)+"");	// +"" : string 으로 자동 변환
			}
		}
		
		bfs(0, 0, N, M);
		System.out.println(graph[N - 1][M - 1]);
	}
}
