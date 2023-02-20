import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	static int r;
	static int c;
	static int d;
	
	static int[][] arr;
	static boolean[][] clean;
	
	static int[] dx = {-1, 0, 1, 0};	// 북, 동, 남, 서
	static int[] dy = {0, 1, 0, -1};
	
	public static void robot(int x, int y, int idx) {
		while(true) {
			clean[x][y] = true;
			int flag = 0;
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(arr[nx][ny]==0 && clean[nx][ny]==false) {	// 청소할 곳 있으면
					flag = 1;
				}
			}
			if(flag == 0) {		// 청소할 곳 없으면
				if(arr[x-dx[idx]][y-dy[idx]]==0 && clean[x-dx[idx]][y-dy[idx]]==true) {
					x -= dx[idx];
					y -= dy[idx];
				}else{
					break;
				}
			}else {				// 청소할 곳 있으면
				idx = (idx + 3) % 4;
				if(arr[x+dx[idx]][y+dy[idx]]==0 && clean[x+dx[idx]][y+dy[idx]]==false) {
					x += dx[idx];
					y += dy[idx];
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 방 크기
		M = Integer.parseInt(st.nextToken());	// 방 크기
		
		arr = new int[N][M];
		clean = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());	// 로봇 청소기 처음 위치 행
		c = Integer.parseInt(st.nextToken());	// 로봇 청소기 처음 위치 열
		d = Integer.parseInt(st.nextToken());	// 로봇 청소기가 바라보는 방향
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		robot(r, c, d);
		
		int cnt = 0;
		for(boolean[] r : clean) {
			for(boolean c : r) {
				if(c == true) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
