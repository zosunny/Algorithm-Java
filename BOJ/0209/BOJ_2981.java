import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2981 {

	static int N;
	static int[] arr;
	static int[] subArr;
	static int minNum;

	public static int euclidGCD(int a, int b) {
		int tmp;
		if (a < b) {
			tmp = b;
			b = a;
			a = tmp;
		}

		if (b == 0) {
			return a;
		}
		return euclidGCD(b, a % b);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine()); // 수 N개

		arr = new int[N];
		subArr = new int[N - 1];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine()); // 수 N개 arr입력
		}
		Arrays.sort(arr); // N들 정렬시킴

		for (int i = 0; i < N - 1; i++) {
			subArr[i] = arr[i + 1] - arr[i]; // 두 수의 차를 집어넣는 배열
		}
		Arrays.sort(subArr); // 차들 정렬시킴

		// subArr의 최대공약수와 그 약수를 구하면 된다!
		minNum = subArr[0];

		for (int i = 0; i < N - 3; i++) {
			if (N <= 3) {
				minNum = euclidGCD(subArr[0], subArr[1]);
				break;
			} else {
				minNum = Math.min(minNum, euclidGCD(minNum, subArr[i + 1]));
			}
		}
		// 최대공약수의 약수들 append
		for (int i = 2; i <= minNum; i++) {
			if (minNum % i == 0) {
				sb.append(i + " ");
			}
		}
		System.out.println(sb);
	}
}
