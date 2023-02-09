import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {
	static int N;
	static int M;
	static int[][] arr;
	static int[][] accSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());	// 배열 크기
		M = Integer.parseInt(st.nextToken());	// 합을 구해야 하는 횟수
		
		arr = new int[N][N];
		accSum = new int[N+1][N+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());			
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(j==0) {
					accSum[i+1][1] = arr[i][0];
					continue;
				}
				accSum[i+1][j+1] += accSum[i+1][j] + arr[i][j];		// 행 누적
			}
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int ans = 0;
			for(int j=x2; j>=x1; j--) {
				ans += accSum[j][y2] - accSum[j][y1-1];
			}
			sb.append(ans + "\n");
		}
		System.out.print(sb);
	}
}
