import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] arr;
	static int[] dp;
	static ArrayList<Integer> listForSize;
	
	public static int binarySearch(int start, int end, int target) {
		while(start < end) {
			int mid = (start + end) / 2;
			if(target > listForSize.get(mid)) {
				start = mid + 1;
			}else {			// target <= listForSize.get(mid)
				end = mid;
			}
		}
		return start;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		dp = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		listForSize = new ArrayList<>();
		listForSize.add(Integer.MIN_VALUE);
		
		for(int i=0; i<N; i++) {
			if(listForSize.get(listForSize.size()-1) < arr[i]) {
				listForSize.add(arr[i]);
				dp[i] = listForSize.size() - 1;
			}else {
				int idx = binarySearch(1, listForSize.size()-1, arr[i]);
				listForSize.set(idx, arr[i]);
				dp[i] = idx;
			}
		}
		int LISLength = listForSize.size() - 1;
		System.out.println(LISLength);
		
		// 수열 출력
		Stack<Integer> s = new Stack<>();
		int index = LISLength;
		for(int i=N-1; i>=0; i--) {
			if(dp[i] == index) {
				s.push(arr[i]);
				index--;
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!s.isEmpty()) {
			sb.append(s.pop() + " ");
		}
		System.out.println(sb);
	}
}
