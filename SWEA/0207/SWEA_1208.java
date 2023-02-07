import java.util.Arrays;
import java.util.Scanner;

public class SWEA_1208 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int t=1; t<=10; t++) {
			int ans = 0;				// 최대 -최소 저장할 변수
//			int cnt = 0;
			int dump = sc.nextInt();	// 덤프 횟수
			int[] arr = new int[100];	// 가로 길이는 항상 100 
			for(int i=0; i<100; i++) {	// 모든 위치 상자의 높이 입력
				arr[i] = sc.nextInt();
			}
//			for(int x : arr) {
//				System.out.print(x + " ");
//			}
//			System.out.println();
//			Arrays.sort(arr);
//			System.out.println("정렬 후");
//			for(int x : arr) {
//				System.out.print(x + " ");
//			}
//			System.out.println();
			
			for(int i=0; i<dump; i++) {
				Arrays.sort(arr);		// 전체 높이 정렬 -> min:arr[0], max:arr[arr.length-1]
				arr[0] += 1;
				arr[99] -= 1;
//				cnt++;
//				System.out.println("arr[arr.length-1]: " + arr[arr.length-1] + ", arr[0]: " + arr[0] + " cnt: " + cnt);
			}
			Arrays.sort(arr);
			ans = arr[99] - arr[0];
			System.out.println("#" + t + " " + ans);
		}
	}
}
