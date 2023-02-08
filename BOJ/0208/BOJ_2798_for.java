package assignment;

// boj_2798_블랙잭_반복문

import java.util.Scanner;

public class BOJ_2798_for {
	
	static int[] arr;	// 주어진 카드 배열
	static int N;
	static int M;
	static int max = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();		// 카드의 개수
		M = sc.nextInt();		// 목표 값
		arr = new int[N];
		
		for(int i=0; i<N; i++) {	// 카드에 수 대응
			arr[i] = sc.nextInt();
		}
		for(int i=0; i<N-2; i++) {
			for(int j=i+1; j<N-1; j++) {
				for(int k=j+1; k<N; k++) {
					int tmp = arr[i] + arr[j] + arr[k];
					if(tmp<=M) {
						max = Math.max(tmp, max);						
					}
				}
			}
		}
		System.out.println(max);
	}
}
