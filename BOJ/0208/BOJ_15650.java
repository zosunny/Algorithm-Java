import java.util.Scanner;

public class BOJ_15650 {
	
	static int N;
	static int M;
	static int[] arr;
	static int[] store;
	
	public static void combination(int idx, int start) {
		if(idx == M) {
			for(int x : store) {
				System.out.print(x + " ");
			}
			System.out.println();
			return;
		}
		for(int i=start; i<N; i++) {
			store[idx] = arr[i];
			combination(idx+1, i+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N];
		store = new int[M];
		
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		combination(0, 0);
	}
}
