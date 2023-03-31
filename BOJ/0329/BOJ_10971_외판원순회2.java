import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_10971_외판원순회 {
	
	static int N;
	static int minCost = Integer.MAX_VALUE;
	
	static int[][] arr;
	static int[] input;
	static boolean[] select;
	
	public static void calc(int[] input) {
		int cost = 0;
		for(int i=0; i<N-1; i++) {
			int a = input[i];
			int b = input[i+1];
			if(arr[a][b]!=0) cost += arr[a][b];
			else return;
		}
		// 되돌아올 때 비용
		int back = arr[input[N-1]][input[0]];
		if(back!=0) cost += back;
		else return;
		minCost = Math.min(minCost, cost);
	}
	
	public static void permu(int cnt, int[] input, boolean[] select) {
		if(cnt==N) {
			calc(input);
			return;
		}
		for(int i=0; i<N; i++) {
			if(select[i]) continue;
			input[cnt] = i;
			select[i] = true;
			permu(cnt+1, input, select);
			select[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		input = new int[N];
		select = new boolean[N];
		permu(0, input, select);
		System.out.println(minCost);
	}
}
