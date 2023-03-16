import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10819_차이를최대로 {
	
	static int N;
	static int maxSum = Integer.MIN_VALUE;
	
	static int[] arr;
	static boolean[] select;
	
	public static void calc(int[] input) {
		int sum = 0;
		for(int i=1; i<N; i++) {
			sum += Math.abs(input[i]-input[i-1]);
		}
		maxSum = Math.max(maxSum, sum);
	}
	
	public static void permu(int cnt, int[] input) {
		if(cnt == N) {
			calc(input);
			return;
		}
		for(int i=0; i<arr.length; i++) {
			if(select[i]) continue;
			input[cnt] = arr[i];
			select[i] = true;
			permu(cnt+1, input);
			select[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		select = new boolean[N];
		int[] input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		permu(0, input);
		
		System.out.println(maxSum);
	}
}
