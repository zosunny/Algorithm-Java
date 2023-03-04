import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16935_배열돌리기3 {
	
	static int N, M, R;
	
	static int[][] arr;
	
	public static void one() {
		int[][] make = deepCopy(arr);			// make배열에 현재 들고있는 원본배열 복사
		for(int i=0; i<make.length; i++) {
			for(int j=0; j<make[0].length; j++) {
				arr[i][j] = make[make.length-1-i][j];	// 원본배열에 덮어씌워!
			}
		}
	}
	
	public static void two() {
		int[][] make = deepCopy(arr);
		for(int i=0; i<make.length; i++) {
			for(int j=0; j<make[0].length; j++) {
				arr[i][j] = make[i][make[0].length-1-j];
			}
		}
	}

	public static void three() {
		int[][] make = deepCopy(arr);
		arr = new int[make[0].length][make.length];
		for(int i=0; i<make[0].length; i++) {
			for(int j=0; j<make.length; j++) {
				arr[i][j] = make[make.length-1-j][i];
			}
		}
	}

	public static void four() {
		int[][] make = deepCopy(arr);
		arr = new int[make[0].length][make.length];
		for(int i=0; i<make[0].length; i++) {
			for(int j=0; j<make.length; j++) {
				arr[i][j] = make[j][make[0].length-1-i];
			}
		}
	}

	public static void five() {
		int[][] make = deepCopy(arr);
		for(int i=0; i<make.length/2; i++) {
			for(int j=0; j<make[0].length/2; j++) {
				arr[i][j] = make[i+make.length/2][j];
			}
		}
		for(int i=0; i<make.length/2; i++) {
			for(int j=make[0].length/2; j<make[0].length; j++) {
				arr[i][j] = make[i][j-make[0].length/2];
			}
		}
		for(int i=make.length/2; i<make.length; i++) {
			for(int j=0; j<make[0].length/2; j++) {
				arr[i][j] = make[i][j+make[0].length/2];
			}
		}
		for(int i=make.length/2; i<make.length; i++) {
			for(int j=make[0].length/2; j<make[0].length; j++) {
				arr[i][j] = make[i-make.length/2][j];
			}
		}
	}

	public static void six() {
		int[][] make = deepCopy(arr);
		for(int i=0; i<make.length/2; i++) {
			for(int j=0; j<make[0].length/2; j++) {
				arr[i][j] = make[i][j+make[0].length/2];
			}
		}
		for(int i=0; i<make.length/2; i++) {
			for(int j=make[0].length/2; j<make[0].length; j++) {
				arr[i][j] = make[i+make.length/2][j];
			}
		}
		for(int i=make.length/2; i<make.length; i++) {
			for(int j=0; j<make[0].length/2; j++) {
				arr[i][j] = make[i-make.length/2][j];
			}
		}
		for(int i=make.length/2; i<make.length; i++) {
			for(int j=make[0].length/2; j<make[0].length; j++) {
				arr[i][j] = make[i][j-make[0].length/2];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 행 
		M = Integer.parseInt(st.nextToken());	// 열 
		R = Integer.parseInt(st.nextToken());	// 연산의 수
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp == 1) {
				one();
			}else if(tmp == 2) {
				two();
			}else if(tmp == 3) {
				three();
			}else if(tmp == 4) {
				four();
			}else if(tmp == 5) {
				five();
			}else if(tmp == 6){
				six();
			}
		}
		// 출력부
		for(int[] r : arr) {
			for(int c : r) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
	// 딥카피
	public static int[][] deepCopy(int[][] arr) {
		int[][] copy = new int[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}
}
