import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int minVal = Integer.MAX_VALUE;
	
	static int[][] arr;
	static boolean[] select;
	
	public static void calc(boolean[] team) {
		int start = 0;
		int link = 0;
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(i!=j && team[i] && team[j]) {
					start += arr[i][j];
				}else if(i!=j && !team[i] && !team[j]) {
					link += arr[i][j];
				}
			}
		}
		int val = Math.abs(start - link);
		minVal = Math.min(minVal, val);
	}
	
	public static void combi(int cnt, int start, boolean[] select) {
		if(cnt==N/2) {
			calc(select);
			return;
		}
		for(int i=start; i<N+1; i++) {
			if(select[i]) continue;
			select[i] = true;
			combi(cnt+1, i+1, select);
			select[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		select = new boolean[N+1];
		combi(0, 1, select);
		System.out.println(minVal);
	}
}
