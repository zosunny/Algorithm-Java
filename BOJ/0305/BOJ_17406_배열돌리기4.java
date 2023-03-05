import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17406_배열돌리기4 {
	
	static class Point{
		int r;
		int c;
		int s;
		
		Point(int r, int c, int s){
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	
	static int N, M, K;
	static int minValue = Integer.MAX_VALUE;
	
	static int[][] arr;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void calc(int[][] arr) {
		// 각 행별 최솟값 계산
		for(int i=0; i<arr.length; i++) {
			int tmp = 0;
			for(int j=0; j<arr[0].length; j++) {
				tmp += arr[i][j];
			}
			minValue = Math.min(minValue, tmp);
		}
	}
	
	public static void rotate(int x, int y, int n, int m, int[][] copyArr) {
		if(n<x || m<y) return;
		int d = 0;
		int start = x;
		int end = y;
		while(d < 4) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx<start || ny<end || nx>=n || ny>=m) {
				d++;
				continue;
			}
			map[nx][ny] = copyArr[x][y];
			x = nx;
			y = ny;
		}
		rotate(x+1, y+1, n-1, m-1, copyArr);
	}
	
	public static void permu(int cnt, Point[] info, Point[] input, boolean[] select) {
		if(cnt == K) {
			map = deepCopy(arr);
			
			for(int i=0; i<K; i++) {
				int x1 = input[i].r - input[i].s - 1;	// 시작점 x좌표
				int y1 = input[i].c - input[i].s - 1;	// 시작점 y좌표
				int x2 = input[i].r + input[i].s;	// 끝점 x좌표
				int y2 = input[i].c + input[i].s;	// 끝점 x좌표
				
				int[][] copyArr = deepCopy(map);
				rotate(x1, y1, x2, y2, copyArr);
			}
			calc(map);
			return;
		}
		for(int i=0; i<K; i++) {
			if(select[i]) continue;
			input[cnt] = new Point(info[i].r, info[i].c, info[i].s);
			select[i] = true;
			permu(cnt+1, info, input, select);
			select[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());	// 회전 연산의 개수
		
		// 배열 정보 입력
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// K번의 회전 연산 정보 입력
		Point[] info = new Point[K];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());	// 회전 연산의 개수
			
			info[i] = new Point(R, C, S);

		}
		Point[] input = new Point[K];
		boolean[] select = new boolean[K];
		permu(0, info, input, select);
		
		System.out.println(minValue);
	}
	
	public static int[][] deepCopy(int[][] arr) {
		int[][] copy = new int[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}
}
