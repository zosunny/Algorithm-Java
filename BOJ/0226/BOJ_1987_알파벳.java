import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R;
	static int C;
	static int cnt;
	static int maxCnt = Integer.MIN_VALUE;
	
	static int[][] arr;
	static boolean[] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void dfs(int x, int y) {
		visited[arr[x][y]] = true;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || ny<0 || nx>= R || ny>=C || visited[arr[nx][ny]]) continue;
			if(!visited[arr[nx][ny]]) {
				cnt++;
				maxCnt = Math.max(maxCnt, cnt);		
				dfs(nx, ny);
				visited[arr[nx][ny]] = false;
				cnt--;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[R][C];
		visited = new boolean[30];
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				arr[i][j] = s.charAt(j) - '0' - 17;
			}
		}
		
		dfs(0, 0);
		
		if(maxCnt == Integer.MIN_VALUE) {
			System.out.println(1);
		}else {
			System.out.println(maxCnt+1);			
		}
	}
}
