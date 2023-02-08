import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11659 {
	
	static int N;
	static int M;
	static int[] arr;
	static int[] accSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 수의 개수
		M = Integer.parseInt(st.nextToken());	// 합을 구해야 하는 횟수
		
		arr = new int[N];
		accSum = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());		// N개의 수 배열 입력
			if(i==0) {
				accSum[1] = arr[0];
				continue;
			}
			accSum[i+1] = accSum[i] + arr[i];
			
//			for(int j=0; j<=i; j++) {
//				accSum[i+1] += arr[j];						// 누적 합 배열 입력
//			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			System.out.println(accSum[end]-accSum[start-1]);
		}
	}
}
