import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int cnt;
	
	static int[][] arr;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Point{
		int x, y, w;
		Point(int x, int y, int w){
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
	
	public static int bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0));
		visited[0][0][0] = true;
		while(!q.isEmpty()) {
			int qSize = q.size();
			while(qSize --> 0) {
				Point p = q.poll();
				// 도착지점에 도착했다면
				if(p.x == N-1 && p.y == M-1) {
					return cnt+1;
				}
				// 아직 벽을 부순적이 없다면
				if(p.w < 1) {
					for(int i=0; i<4; i++) {
						int nx = p.x + dx[i];
						int ny = p.y + dy[i];
						int nw = p.w + 1;
						// 이때는 벽이 있어도 지나갈 수 있음
						if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny][nw]) continue;
						// 벽 부쉈어?
						if(arr[nx][ny]==1) {
							q.add(new Point(nx, ny, nw));
							visited[nx][ny][nw] = true;
						}
					}
				}
				// 벽 부쉈든 말든 이동 가능한 경우
				for(int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					// 이때는 벽을 못지나가
					if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny][p.w] || arr[nx][ny]==1) continue;
					q.add(new Point(nx, ny, p.w));
					visited[nx][ny][p.w] = true;
				}
			}
			cnt++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		// 1번까지 벽 부수고 이동 가능
		visited = new boolean[N][M][2];
		System.out.println(bfs());
	}
}
