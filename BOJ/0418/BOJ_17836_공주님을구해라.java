import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_17836_공주님을구해라 {
	
	static int N, M, T;
	static int time;
	
	static int[][] arr;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Point{
		int x, y, time, flag;
		Point(int x, int y, int time, int flag){
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.flag = flag;
		}
	}
	
	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;
		while(!q.isEmpty()){
			int qSize = q.size();
			while(qSize-->0) {
				Point p = q.poll();
				for(int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					int ntime = p.time + 1;
					int nflag = p.flag;
					// 영역 밖 무시
					if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny][nflag]) continue;
					// 제한시간 내 도착
					if(nx==N-1 && ny==M-1 && ntime<=T) {
						System.out.println(ntime);
						return;
					}
					// 제한 시간이 지나면 끝
					if(ntime > T) {
						System.out.println("Fail");
						return;
					}
					// 그람 찾음
					if(arr[nx][ny]==2) nflag = 1;
					// 그람 찾기 전 -> 벽 못 부숨
					if(nflag==0 && arr[nx][ny]==1) continue;
						
					q.add(new Point(nx, ny, ntime, nflag));
					visited[nx][ny][nflag] = true;
				}
			}
		}
		System.out.println("Fail");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][M][2];
		bfs();
	}
}
