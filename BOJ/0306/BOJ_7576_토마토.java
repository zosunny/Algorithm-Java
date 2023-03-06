import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_토마토 {
	
	static int M, N;	// 열, 행
	static int unripen;
	static int cnt;
	static int flag;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] arr;
	static boolean[][] visited;
	static ArrayList<Point> tomato = new ArrayList<>();
	
	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		for(int i=0; i<tomato.size(); i++) {
			q.add(tomato.get(i));
			visited[tomato.get(i).x][tomato.get(i).y] = true;
		}
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int k=0; k<qSize; k++) {
				Point p = q.poll();
				for(int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if(nx<0 || ny<0 || nx>=N || ny>=M || arr[nx][ny]!=0 || visited[nx][ny]) continue;
					if(arr[nx][ny]==0 && !visited[nx][ny]) {
						unripen--;
						if(unripen==0) {
							return;
						}
						arr[nx][ny] = 1;
						q.add(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
			cnt++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 열
		N = Integer.parseInt(st.nextToken());	// 행
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==1) tomato.add(new Point(i, j));	// 익은 토마토 좌표값
				if(arr[i][j]==0) unripen++;
			}
		}
		
		// 모든 토마토가 익어있으면 0 출력하고 
		if(unripen == 0) {
			System.out.println(0);
			return;
		}
		
		
		// 익은 토마토 좌표값을 시작점으로 bfs 탐
		bfs();
		
		if(unripen == 0) {
			System.out.println(cnt+1);			
		}else {
			System.out.println(-1);
		}
	}
}
