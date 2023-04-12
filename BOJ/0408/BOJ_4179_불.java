import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static final int MAX = Integer.MAX_VALUE;
	static Point J;
	static Queue<Fire> fire;
	
	static char[][] arr;
	static int[][] fireVisited;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Fire{
		int x, y, time;
		Fire(int x, int y, int time){
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		if(x==0 || y==0 || x==R-1 || y==C-1) {
			System.out.println(1);
			return;
		}
		int time = 1;
		while(!q.isEmpty()) {
			int qSize = q.size();
			while(qSize --> 0) {
				Point j = q.poll();
				for(int i=0; i<4; i++) {
					int nx = j.x + dx[i];
					int ny = j.y + dy[i];
					// 지훈이는 벽도 못가고 현재 시간에서 불이 난 곳도 못간다.
					if(nx<0 || ny<0 || nx>=R || ny>=C || fireVisited[nx][ny]<=time || visited[nx][ny] || arr[nx][ny]=='#') continue;
					
					if(nx==0 || ny==0 || nx==R-1 || ny==C-1) {
						visited[nx][ny] = true;
						System.out.println(time+1);
						return;
					}
					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
			time++;
		}
		System.out.println("IMPOSSIBLE");
	}

	
	public static void fireBfs() {
		Queue<Fire> q = new LinkedList<>();
		
		while(!fire.isEmpty()) {
			Fire p = fire.poll();
			q.add(new Fire(p.x, p.y, p.time));
			fireVisited[p.x][p.y] = 0;
		}
		while(!q.isEmpty()) {
			int qSize = q.size();
			while(qSize --> 0) {
				Fire f = q.poll();
				for(int i=0; i<4; i++) {
					int nx = f.x + dx[i];
					int ny = f.y + dy[i];
					int ntime = f.time + 1;
					// 불은 벽을 못간다.
					if(nx<0 || ny<0 || nx>=R || ny>=C || fireVisited[nx][ny]!=MAX || arr[nx][ny]=='#') continue;
					q.add(new Fire(nx, ny, ntime));
					fireVisited[nx][ny] = Math.min(fireVisited[nx][ny], ntime);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		for(int i=0; i<R; i++) {
			arr[i] = br.readLine().toCharArray();
			
		}
		fire = new LinkedList<>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 지훈이 위치
				if(arr[i][j]=='J') J = new Point(i, j);
				// 불 위치
				else if(arr[i][j]=='F') fire.add(new Fire(i, j, 0));
			}
		}
		fireVisited = new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				fireVisited[i][j] = Integer.MAX_VALUE;
			}
		}
		fireBfs();
		
		visited = new boolean[R][C];
		bfs(J.x, J.y);
	}
	
	// 디버깅용
	public static void print(int[][] arr) {
		for(int[] r : arr) {
			for(int c : r) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
	
	public static void printB(boolean[][] arr) {
		for(boolean[] r : arr) {
			for(boolean c : r) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
}
