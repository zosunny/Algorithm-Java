package day0111;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_2304 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        int[] arr = new int[1002];

        int N = sc.nextInt();
        int arr_length = 0;
        for(int i=0; i<N; i++) {
            int L = sc.nextInt();
            int H = sc.nextInt();
            // 값을 가지는 최대 길이 찾기
            if(arr_length < L) {
            	arr_length = L;
            }
            // 배열 인덱스에 높이를 값으로 저장
            for(int j=0; j<arr_length+1; j++) {
                arr[L] = H;
            }
        }
        
        // 높이의 최댓값을 가지는 인덱스 찾기
        int max_idx = 0;
        int max_H = 0;
        for(int x=0; x<arr_length+1; x++) {
        	if(arr[x] > max_H) {
        		max_H = arr[x];
        		max_idx = x;
        	}
        }
        
        int sum = 0;
        
        // 최댓값 인덱스 기준으로 좌측 최소면적 구하기
        for(int i=1; i<=max_idx; i++) {
        	if(arr[i-1] > arr[i]) {
        		arr[i] = arr[i-1];
        	}
        	sum += arr[i-1];
        }

        
        // 최댓값 인덱스 기준으로 우측 최소면적 구하기
        for(int j=arr_length; j>max_idx; j--) {
        	if(arr[j] < arr[j+1]) {
        		arr[j] = arr[j+1];
        	}
        	sum += arr[j];
        }
        sum += arr[max_idx];

        
        System.out.println(sum);
    }
}