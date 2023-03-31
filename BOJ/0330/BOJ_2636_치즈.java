import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int H, W;
	static int cnt;
	static int time;
	static int beforeCnt;

	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= H || ny >= W || visited[nx][ny])
					continue;
				if (!visited[nx][ny] && arr[nx][ny] == 1) {
					cnt--;
					arr[nx][ny] = 0;
					visited[nx][ny] = true;
				} else if (!visited[nx][ny] && arr[nx][ny] == 0) {
					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1)
					cnt++; // 치즈 개수
			}
		}
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				visited = new boolean[H][W];
				if (!visited[i][j] && arr[i][j] == 0) {
					beforeCnt = cnt;
					time += bfs(i, j);
					if (cnt == 0) {
						System.out.println(time);
						System.out.println(beforeCnt);
						return;
					}
				}
			}
		}
	}
}
