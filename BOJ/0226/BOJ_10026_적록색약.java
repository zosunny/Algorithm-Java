import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int N;
	static char[][] arr;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static boolean[][] visited1;
	static boolean[][] visited2;
	
	public static int bfs(int x, int y, char[][] arr, boolean[][] visited) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny]) continue;
				if(arr[p.x][p.y] == arr[nx][ny] && !visited[nx][ny]) {
					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
		return 1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new char[N][N];
		
		for(int i=0; i<N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		// 적록색약 아닌 사람
		visited1 = new boolean[N][N];
		int cnt1 = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited1[i][j]) {
					cnt1 += bfs(i, j, arr, visited1);					
				}
			}
		}
		
		// 적록색약인 사람
		char[][] arr2 = new char[N][N];
		arr2 = alterArr(arr);
		
		visited2 = new boolean[N][N];
		int cnt2 = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited2[i][j]) {
					cnt2 += bfs(i, j, arr2, visited2);					
				}
			}
		}
		
		System.out.println(cnt1 + " " + cnt2);
	}
	
	public static char[][] alterArr(char[][] arr) {
		char[][] newArr = new char[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] == 'G') {
					newArr[i][j] = 'R';
				}else {
					newArr[i][j] = arr[i][j];					
				}
			}
		}
		return newArr;
	}
}
