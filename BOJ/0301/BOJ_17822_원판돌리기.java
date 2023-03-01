import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N;
	static int M;
	static int T;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] arr;
	
	// 원판에 남은 수들의 
	public static int calcSum() {
		int allSum = 0;
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<M; j++) {
				allSum += arr[i][j];
			}
		}
		return allSum;
	}
	
	public static void calcAvg() {
		// 원판에 적힌 수의 평균을 구하자
		int sum = 0;
		int cnt = 0;
		float avg = 0;
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<M; j++) {
				sum += arr[i][j];
				if(arr[i][j]!=0) {
					cnt++;
				}
			}
		}
		avg = (float)sum / cnt;	// 원판에 적힌 수의 평균
		
		// 평균보다 큰 수는 1을 빼고, 평균보다 작은 수는 1을 더홰!
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] > avg && arr[i][j]!=0) arr[i][j] -= 1;
				else if(arr[i][j] < avg && arr[i][j]!=0) arr[i][j] += 1;
			}
		}
	}
	
	public static void goDelete(int x, int y) {
		boolean[][] visited = new boolean[N+1][M];
		boolean[][] check = new boolean[N+1][M];
		
		// 행 탐색
		for(int i=1; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == arr[i+1][j]) {
					check[i][j] = true;
					check[i+1][j] = true;
				}
			}
		}
		// 열 탐색
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<M; j++) {
				if(j==M-1) {
					if(arr[i][M-1] == arr[i][0]) {
						check[i][M-1] = true;
						check[i][0] = true;
					}
				}
				else if(arr[i][j] == arr[i][j+1]) {
					check[i][j] = true;
					check[i][j+1] = true;
				}
			}
		}
		
		// 인접하고 동일한 수로 체크해 둔 부분 지워! 0으로
		int flag = 0;
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<M; j++) {
				if(check[i][j] && arr[i][j]!=0) {
					flag = 1;
					arr[i][j] = 0;
				}
			}
		}
		if(flag==0) {	// 만약 인접한 수가 없었으면 수행하는 계산 함수
			calcAvg();
		}
	}
	
	// 시계 반시계 돌리는 함수
	public static void isClockWise(int[] arr, int dir, int can) {
		if(dir == 0) {					// 시계방향으로 돌릴거?
			for(int i=0; i<can; i++) {
				int tmp1 = arr[M-1];
				for(int j=M-1; j>0; j--) {
					arr[j] = arr[j-1];
				}
				arr[0] = tmp1;
			}
		}else if(dir == 1){				// 반시계방향으로 돌릴거야?
			for(int i=0; i<can; i++) {
				int tmp2 = arr[0];
				for(int j=0; j<M-1; j++) {
					arr[j] = arr[j+1];
				}
				arr[M-1] = tmp2;
			}
		}
	}
	
	// 입력 받은 정보로 원판 돌리는 함수
	public static void goRotate(int[] rotateInfo) {
		int num = rotateInfo[0];	// 번호가 num의 배수인 원판
		int dir = rotateInfo[1];	// 회전방향(0: 시계, 1: 반시계)
		int can = rotateInfo[2];	// 몇칸 회전 할래?
		
		for(int i=1; i<N+1; i++) {
			if(i % num == 0) {		// 해당 배수 번호 원판이야?
				isClockWise(arr[i], dir, can);
			}
		}
		goDelete(1, 0);		// 인접한 수 지우러 가
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 원판 반지름
		M = Integer.parseInt(st.nextToken());	// 원판에 적힌 M개의 정수
		T = Integer.parseInt(st.nextToken());	// T번 회전
		
		arr = new int[N+1][M];
		
		// 원판에 적힌 수 입력
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
					
		// T번 어떻게 회전시킬건데?
		for(int i=0; i<T; i++) {
			int[] rotateInfo = new int[3];
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				rotateInfo[j] = Integer.parseInt(st.nextToken());
			}
			goRotate(rotateInfo);	// 회전정보 입력 받을 때마다 함수로 돌려!
			
		}
		// T번 회전시킨 후 원판에 적힌 수의 합 구하자!
		System.out.println(calcSum());
	}
}
