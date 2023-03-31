import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K;
	static int W, H;
	static int cnt;
	
	static int[][] arr;
	static boolean[][][] visited;
	static int[] dx = { -1, +1, 0, 0, +2, +1, -2, -1, +2, +1, -2, -1 };
	static int[] dy = { 0, 0, -1, +1, -1, -2, -1, -2, +1, +2, +1, +2 };

	static class Point {
		int x;
		int y;
		int check;

		public Point(int x, int y, int check) {
			super();
			this.x = x;
			this.y = y;
			this.check = check;
		}
	}

	public static int bfs(int x, int y, int check) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y, check));
		visited[x][y][check] = true;
		while (!q.isEmpty()) {
			int qSize = q.size();
			for(int s=0; s<qSize; s++) {
				Point p = q.poll();
				if (p.x == H - 1 && p.y == W - 1) {
					return cnt;
				}
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (nx < 0 || ny < 0 || nx >= H || ny >= W || visited[nx][ny][p.check] || arr[nx][ny] == 1)
						continue;
					q.add(new Point(nx, ny, p.check));
					visited[nx][ny][p.check] = true;
				}
				if (p.check < K) {
					for (int i = 4; i < 12; i++) {
						int nx = p.x + dx[i];
						int ny = p.y + dy[i];
						if (nx < 0 || ny < 0 || nx >= H || ny >= W || visited[nx][ny][p.check+1] || arr[nx][ny] == 1)	// ***
							continue;
						q.add(new Point(nx, ny, p.check + 1));
						visited[nx][ny][p.check+1] = true;		// ***
					}
				}
			}
			cnt++;
		}
		return -1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // 가로길이 (열)
		H = Integer.parseInt(st.nextToken()); // 세로길이 (행)

		arr = new int[H][W];
		visited = new boolean[H][W][K+1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs(0, 0, 0));
	}
}
