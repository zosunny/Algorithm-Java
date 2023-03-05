import java.util.Scanner;

public class BOJ_2023_신기한소수_prof {
	
	static int N;
	
	public static boolean isPrime(int tmp) {
		for(int i=2; i<=Math.sqrt(tmp); i++) {
			if(tmp % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void permu(int cnt, int tmp) {
		
		if(!isPrime(tmp)) return;		// 현재까지 만들어진 수 소수 아니면 현재 값 종료
		
		if(cnt==N) {
			System.out.println(tmp);
			return;
		}
		
		for(int i=1; i<10; i+=2) {		// 짝수는 무조건 불가능
			permu(cnt+1, tmp*10+i);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// N자리 수에서 소수 찾아볼
		
		// 초기값 있는 상태에서 순열 돌리
		permu(1, 2);
		permu(1, 3);
		permu(1, 5);
		permu(1, 7);
	}
}
