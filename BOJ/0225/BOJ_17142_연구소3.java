import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142_연구소 {

	static int N;
	static int M;
	static int virusNum;
	static int minTime = Integer.MAX_VALUE;
	static int empty;
	static int flag;	// 빈칸의 유무
	static int ans;

	static String[][] origin; // 원본 input 배열
	static String[][] copy; // 활성 바이러스를 놓은 딥카피 배열
	static boolean[] select;

	static ArrayList<Point> virus; // 바이러스 좌표
	static Queue<Point> acVirus;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void bfs(Queue<Point> acVirus, String[][] copy, int emptyNum) {

		// 일단 현재 활성 바이러스 다 큐에 담아
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			Point ac = acVirus.poll();
			q.add(ac);
			visited[ac.x][ac.y] = true;
		}
		// 탐색 시작
		int time = 0;
		int maxTime = Integer.MIN_VALUE;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || copy[nx][ny].equals("|") || visited[nx][ny])
					continue;
				if(!copy[nx][ny].equals("-")) {			// 빈칸인 경우
					if (copy[p.x][p.y].equals("*")) {
						copy[nx][ny] = "1";
						time = Integer.parseInt(copy[nx][ny]);
					} else {
						copy[nx][ny] = Integer.toString(Integer.parseInt(copy[p.x][p.y]) + 1);
						time = Integer.parseInt(copy[nx][ny]);
					}
					emptyNum--;
					maxTime = Math.max(maxTime, time);
				}else {									// 비활성 바이러스 인경우
					if (copy[p.x][p.y].equals("*")) {
						copy[nx][ny] = "1";
					} else {
						copy[nx][ny] = Integer.toString(Integer.parseInt(copy[p.x][p.y]) + 1);
					}
				}
				q.add(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
		if(emptyNum == 0) {		// 모든 칸으로 바이러스 퍼진 경우의 수가 한번이라도 존재하면
			flag = 1;
			minTime = Math.min(minTime, maxTime);
		}
	}

	public static void activeVirusCombi(int cnt, int start, boolean[] select) {
		if (cnt == M) {
			// 활성 바이러스를 놓은 딥카피 배열 재생성
			int emptyNum = deepCopy();
			acVirus = new LinkedList<>(); // 활성 바이러스 담을 배열
			for (int i = 0; i < virusNum; i++) {
				if (select[i]) {
					acVirus.add(new Point(virus.get(i)));
					copy[virus.get(i).x][virus.get(i).y] = "*";
				}
			}

			bfs(acVirus, copy, emptyNum);
			return;
		}
		for (int i = start; i < virusNum; i++) {
			if (select[i])
				continue;
			select[i] = true;
			activeVirusCombi(cnt + 1, i + 1, select);
			select[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 연구소 크기
		M = Integer.parseInt(st.nextToken()); // 활성 바이러스 개수

		origin = new String[N][N];
		virus = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				origin[i][j] = st.nextToken();
				if (origin[i][j].equals("1")) {
					origin[i][j] = "|";
				}
				if (origin[i][j].equals("2")) {
					virus.add(new Point(i, j)); // 바이러스 좌표 모음집
					origin[i][j] = "-";
					continue;
				}
			}
		}
		// 활성 바이러스 조합 부분
		virusNum = virus.size();
		select = new boolean[virusNum];
		activeVirusCombi(0, 0, select); // 활성 바이러스 조합

		// 출력
		if(flag == 0) {
			System.out.println(-1);
		}else if(minTime == Integer.MIN_VALUE){
			System.out.println("0");
		}else {
			System.out.println(minTime);			
		}

	} // main end

	// 활성 바이러스를 놓은 원본 딥카피 배열 생성
	public static int deepCopy() {
		copy = new String[N][N];
		empty = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = origin[i][j];
				if (copy[i][j].equals("0")) {
					empty++;
				}
			}
		}
		return empty;
	}
}
