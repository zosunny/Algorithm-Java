package day0114;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_1463 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N + 1];
		
		// �迭�� �ʱ�ȭ
		for(int i=2; i<arr.length; i++) {
			
			int tmp1 = Integer.MAX_VALUE;
			int tmp2 = Integer.MAX_VALUE;
			int tmp3 = Integer.MAX_VALUE;
			
			if (i % 3 == 0) {
				tmp1 = arr[i/3] + 1;
			}
			if (i % 2 == 0) {
				tmp2 = arr[i/2] + 1;
			}
			tmp3 = arr[i-1] + 1;
			
			arr[i] = Math.min(tmp3, Math.min(tmp2, tmp1));
		}

		System.out.println(arr[N]);
	}
}


// 2. �ʼ��� ����ؾ��ϴ� -1 ���� ���ִ� ��� => �ʱ�ȭ ���ص� ��