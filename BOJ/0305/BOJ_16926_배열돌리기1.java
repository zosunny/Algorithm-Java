import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926_배열돌리기1 {
	
	static int N, M, R;
	
	static int[][] arr;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void rotate(int x, int y, int n, int m, int[][] copyArr) {
		if(n<x || m<y) return;
		int start = x;
		int end = y;
		int d = 0;
		while(d < 4) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx<start || ny<end || nx>=n || ny>=m) {
				d++;
				continue;
			}
			arr[nx][ny] = copyArr[x][y];
			x = nx;
			y = ny;	
		}
		rotate(x + 1, y + 1, n-1, m-1, copyArr);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 행
		M = Integer.parseInt(st.nextToken());	// 열
		R = Integer.parseInt(st.nextToken());	// 회전 수
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<R; i++) {
			int[][] copyArr = deepCopy(arr);
			rotate(0, 0, N, M, copyArr);			
		}
		
		for(int[] r : arr) {
			for(int c : r) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
	// 딥카피
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
