import java.util.Scanner;

public class BOJ_14888_연산자끼워넣기 {
	
	static int N;
	static int maxAns = Integer.MIN_VALUE;
	static int minAns = Integer.MAX_VALUE;
	
	static int[] arr;
	static int[] op;
	
	public static void calc(int[] input) {
		int tmpAns = 0;
		if(input[0]==0) {
			tmpAns += arr[0] + arr[1];
		}else if(input[0]==1) {
			tmpAns += arr[0] - arr[1];
		}else if(input[0]==2) {
			tmpAns += arr[0] * arr[1];
		}else {
			tmpAns += arr[0] / arr[1];
		}
		for(int i=1; i<N-1; i++) {
			if(input[i]==0) {
				tmpAns += arr[i+1];
			}else if(input[i]==1) {
				tmpAns -= arr[i+1];
			}else if(input[i]==2) {
				tmpAns *= arr[i+1];
			}else {
				tmpAns /= arr[i+1];
			}
		}
		maxAns = Math.max(maxAns, tmpAns);		// 최대
		minAns = Math.min(minAns, tmpAns);		// 최소
	}
	
	public static void permu(int cnt, int[] input, boolean[] select) {
		if(cnt == N-1) {
			calc(input);
			return;
		}
		for(int i=0; i<N-1; i++) {
			if(select[i]) continue;
			input[cnt] = op[i];
			select[i] = true;
			permu(cnt+1, input, select);
			select[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		arr = new int[N];			// N개의 수
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		op = new int[N-1];			// 주어진 연산자 +, -, *, /
		int idx = 0;
		for(int i=0; i<4; i++) {
			int tmp = sc.nextInt();
			if(tmp != 0) {
				for(int j=0; j<tmp; j++) {
					op[idx++] = i;					
				}
			}
		}
		int[] input = new int[N-1];
		boolean[] select = new boolean[N-1];
		permu(0, input, select);
		
		System.out.println(maxAns);
		System.out.println(minAns);
	}
}
