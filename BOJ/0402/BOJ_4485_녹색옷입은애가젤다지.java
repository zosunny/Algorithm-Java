import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T;
	static int N;
	static int tc;
	static int ans;

	static int[][] arr;
	static int[][] result;
	static int[] dx = { 0, 1, -1, 0 };
	static int[] dy = { 1, 0, 0, -1 };

	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		result[x][y] = arr[x][y];
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
//				if (result[nx][ny] == 0) {
//					result[nx][ny] = result[p.x][p.y] + arr[nx][ny];
//					q.add(new Point(nx, ny));
//				} else {
					// 다음 갈 곳이 지금 경로에서 갈 값보다 작으면(이미 다른 경로에서 큐에 들어가 있고 현재 경로는 그만 춰야하면 큐에 넣지마)
					if (result[nx][ny] > result[p.x][p.y] + arr[nx][ny]) {
						result[nx][ny] = result[p.x][p.y] + arr[nx][ny];
						q.add(new Point(nx, ny));
					}
//				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			// 테케마다 초기화
			N = Integer.parseInt(br.readLine());
			// 0이면 탈출
			if (N == 0)
				break;
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 전체 최댓값으로 채우기
			result = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(result[i], Integer.MAX_VALUE);
			}
			bfs(0, 0);
			ans = result[N-1][N-1];
			sb.append("Problem " + (tc + 1) + ": " + ans + "\n");
			// 테케 증가
			tc++;
		}
		System.out.println(sb);
	}
}
