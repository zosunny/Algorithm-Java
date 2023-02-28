import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4012_요리사 {
	
	static int T;
	static int N;
	static int r;
	static int minResult = Integer.MAX_VALUE;
	
	static int[][] arr;
	static boolean[] select;
	static boolean[] tmp;
	
	public static void calc(boolean[] tmp) {
		int calcA = 0;
		int calcB = 0;
		for(int i=1; i<N; i++) {
			for(int j=i+1; j<N+1; j++) {
				if(tmp[i] && tmp[j]) {
					calcA += arr[i][j] + arr[j][i];
				}
				if(!tmp[i] && !tmp[j]) {
					calcB += arr[i][j] + arr[j][i];
				}
			}
		}
		int result = Math.abs(calcA - calcB);
		minResult = Math.min(minResult, result);
	}
	
	public static void combi(int cnt, int start) {
		if(cnt == r) {
			tmp = new boolean[N+1];
			for(int i=1; i<N+1; i++) {
				if(select[i]) {
					tmp[i] = true;
				}
			}
			calc(tmp);
			return;
		}
		for(int i=start; i<N+1; i++) {
			if(select[i]) continue;
			select[i] = true;
			combi(cnt+1, i+1);
			select[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());	// 테케
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());	// 식재료 수
			r = N / 2;								// 뽑아야하는 식재료 수
			
			arr = new int[N+1][N+1];	// 시너지 값 배열
			select = new boolean[N+1];
			
			for(int i=1; i<N+1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1; j<N+1; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			combi(0, 1);
			
			System.out.println("#" + (t+1) + " " + minResult);
		}
	}
}
