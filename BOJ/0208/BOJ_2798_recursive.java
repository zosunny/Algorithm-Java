package assignment;

//boj_2798_블랙잭_재귀

import java.util.Scanner;

public class BOJ_2798_recur {
	
	static int[] arr;	// 주어진 카드 배열
	static int[] store;	// 조합 카드 저장 배열
	static int N;
	static int M;
	static int max = 0;
	
	public static void combi(int idx, int start) {
		if(idx == 3) {
			int tmp = 0;
			for(int x : store) {
//				System.out.print(x + " ");
				tmp += x;					// 각 조합의 합을 저장해 두고
			}
//			System.out.println("tmp: " + tmp);
			if(tmp <= M) {						// 그 합이  M을 넘지 않을 때
				max = Math.max(tmp, max);		// 최댓값을 갱신
			}
			return;
		}
		for(int i=start; i<N; i++) {
			store[idx] = arr[i];
			combi(idx+1, i+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();		// 카드의 개수
		M = sc.nextInt();		// 목표 값
		arr = new int[N];
		store = new int[3];
		for(int i=0; i<N; i++) {	// 카드에 수 대응
			arr[i] = sc.nextInt();
		}
		combi(0, 0);	// (뽑은 카드 idx값, 카드 시작 idx값)
		
		System.out.println(max);
	}
}
