import java.util.Scanner;

public class BOJ_2023_신기한소수 {
	
	static int N;
	
	static int[] nums = {1, 2, 3, 5, 7, 9};
	
	public static boolean isPrime(int tmp) {
		for(int i=2; i<=Math.sqrt(tmp); i++) {
			if(tmp % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void permu(int cnt, int tmp) {
		if(cnt == N) {
			if(isPrime(tmp)) {
				System.out.println(tmp);
			}
			return;
		}
		for(int i=0; i<6; i++) {
			if((cnt==0 && i==0) || (cnt==0 && i==5) || (cnt>0 && i==1)) continue;
			tmp *= 10;
			tmp += nums[i];
			if(isPrime(tmp)) {
				permu(cnt+1, tmp);
			}
			tmp -= nums[i];
			tmp /= 10;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// N자리 수에서 소수 찾아볼
		
		permu(0, 0);
	}
}
