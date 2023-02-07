import java.util.Scanner;

public class BOJ_15649 {
	
	static int[] arr;
	static boolean[] visited;
	static int[] ans;
	static int N;
	static int M;
	
	public static void permutation(int idx) {
		if(idx == M) {
			for(int x : ans) {
				System.out.print(x + " ");
			}
			System.out.println();
			return;					// 종료 필수
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				ans[idx] = arr[i];
				visited[i] = true;
				permutation(idx+1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N];
		visited = new boolean[N];
		ans = new int[M];
		
		// 1 ~ N 수 리스트 생성
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		permutation(0);
	}
}
