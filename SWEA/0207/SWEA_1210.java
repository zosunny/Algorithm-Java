import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_1210 {

	static int[][] arr;
	static boolean[][] visited;
	static int sy; // 도착해야하는 지점이자 추적 시작 지점의 y좌표(열)
	static int[] dx = { 0, 0, -1 }; // 좌, 우, 상
	static int[] dy = { -1, 1, 0 }; // 좌, 우, 상
	static int ans; // 2로 도착하는 시작지점의 행 갱신 저장해주는 변수

	public static void bfs(int x, int y, int t) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y)); // 좌표 넣기
		visited[x][y] = true;
		while (!q.isEmpty()) { // 큐에 값이 있는 동안 반복
			Point p = q.poll(); // 큐에서 pop
			int cnt = 0; // 좌, 우 탐색 여부 카운트 변수
			for (int i = 0; i < 2; i++) { // 일단 좌, 우만 우선 탐색
				int nx = p.x + dx[i]; // 다음 x 좌표
				int ny = p.y + dy[i]; // 다음 y 좌표

				if (nx < 0 || ny < 0 || nx >= 100 || ny >= 100 || visited[nx][ny] == true) { // 범위 넘어가거나 방문한 곳이면 넘어감
					cnt++;
					if (cnt == 2) { // 좌, 우 양쪽 모두 이동 못하면
						nx = p.x + dx[2]; // 위로 이동
						ny = p.y + dy[2]; // 위로 이동
					} else {
						continue;
					}
				}
				if (arr[nx][ny] == 0) { // 좌, 우를 이동 못할 때 카운트
					cnt++;
					if (cnt == 2) { // 좌, 우 양쪽 모두 이동 못하면
						nx = p.x + dx[2]; // 위로 이동
						ny = p.y + dy[2]; // 위로 이동
					}
				}
				if (arr[nx][ny] == 1 && visited[nx][ny] == false) {
					visited[nx][ny] = true;
					ans = ny;
//					System.out.println("이동 x좌표: " + nx + " 이동 y좌표: " + ans);
					q.add(new Point(nx, ny));
					if (nx == 0) {
						System.out.println("#" + t + " " + ans);
						return;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 0; t < 10; t++) {
			int T = sc.nextInt(); // 테케번호
			arr = new int[100][100];
			visited = new boolean[100][100];

			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					arr[i][j] = sc.nextInt(); // 사다리 배열 입력
					if (arr[i][j] == 2) {
						sy = j; // 도착해야하는 지점이자 추적 시작 점
//						System.out.println("시작점 x좌표: " + i + " y좌표: " + sy);
					}
				}
			}
			bfs(99, sy, T);
		}
	}

}
