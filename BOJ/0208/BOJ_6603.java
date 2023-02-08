import java.util.Scanner;

public class BOJ_6603 {
	
	static int k;
	static int[] arr;
	static int[] ans;
	
	public static void combination(int idx, int start) {
		StringBuilder sb = new StringBuilder();
		if(idx == 6) {
			for(int x : ans) {
				sb.append(x);
				sb.append(" ");
//				System.out.print(x + " ");
			}
			System.out.println(sb);
			return;
		}
		for(int i=start; i<k; i++) {
			ans[idx] = arr[i];
			combination(idx+1, i+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			k = sc.nextInt();
			
			if(k==0) return;			// 0 입력 시 종료
			
			arr = new int[k];
			ans = new int[6];			// 조합해 넣을 배열
			
			for(int i=0; i<k; i++) {	// 집합 S 입력
				arr[i] = sc.nextInt();
			}
			combination(0, 0);
			System.out.println();
		}
	}
}
