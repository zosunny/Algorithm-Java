// 순조부(완탐)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1486_장훈이의높은선반 {
	
	static int T;
	static int N, B;
	static int minAns = Integer.MAX_VALUE;
	
	static int[] arr;
	static boolean[] select;
	
	public static void subset(int cnt) {
		if(cnt == N) {
			int sum = 0;
			for(int i=0; i<N; i++) {
				if(select[i]) {
					sum += arr[i];
				}
			}
			if(sum >= B) {
				minAns = Math.min(minAns, sum-B);
			}
			return;
		}
		select[cnt] = true;
		subset(cnt+1);
		select[cnt] = false;
		subset(cnt+1);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 점원 수
			B = Integer.parseInt(st.nextToken());	// 선반 높이
			
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			minAns = Integer.MAX_VALUE;
			select = new boolean[N];
			subset(0);
			sb.append("#" + (t+1) + " " + minAns + "\n");
		}
		System.out.println(sb);
	}
}
