import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int maxHigh = Integer.MIN_VALUE;
	static int maxCnt = Integer.MIN_VALUE;
	
	static int[][] arr;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny] || map[nx][ny]==1) continue;
				q.add(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
		return 1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxHigh = Math.max(maxHigh, arr[i][j]);			// 영역의 최대 높이
			}
		}
		for(int h=0; h<maxHigh; h++) {		// 비의 양
			map = new int[N][N];			// 맵 초기화
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j]<=h) {
						map[i][j] = 1;		// 잠기는 부분을 1로 표시
					}
				}
			}
			visited = new boolean[N][N];
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j]==0) {	// 방문하지 않고 잠기지 않은 부분
						cnt += bfs(i, j);
					}
				}
			}
			maxCnt = Math.max(maxCnt, cnt);		// 매 높이의 비의 양마다 안전영역 개수 갱신
		}
		System.out.println(maxCnt);
	}
}
