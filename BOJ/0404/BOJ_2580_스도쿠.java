// 재귀

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_2112_보호필름2 {
	
	static int T;
	static int D, W, K;		// 행, 열, 합격기준
	static int minAns = Integer.MAX_VALUE;
	
	static int[][] arr;
	
	public static boolean check(int[][] film) {
		// 모든 열에 대해
		for(int i=0; i<W; i++) {
			// 모든 행의 값을 비교
			int cnt = 0;
			int maxCnt = Integer.MIN_VALUE;
			for(int j=0; j<D-1; j++) {
//				System.out.println(j+"/"+D+"/"+i);
				// 위아래가 서로 같으면
				if(film[j][i]==film[j+1][i]) {
					cnt++;
				// 위아래가 서로 다르면
				}else {
					cnt = 0;
				}
				// 하나의 열에 대해 최대 연속 개수 갱신
				maxCnt = Math.max(maxCnt, cnt);
			}
			// 하나의 열의 모든 행의 탐색이 끝나고 연속 개수가 합격기준보다 작으면
			if(maxCnt < K-1) return false;
		}
		// 모든 열이 끝날 때까지 합격기준을 모두 넘기면
		return true;
	}
	
	
	public static void subset(int cnt, int[][] copy, int count) {
		if(cnt == D) {
			if(check(copy)) minAns = Math.min(minAns, count);
			return;
		}
		int[][] copyA = deepCopy(copy);
		// 선택한 행을 A로 채우는 경우
		for(int i=0; i<W; i++) {
			copyA[cnt][i] = 0;
		}
		subset(cnt+1, copyA, count+1);
		
		int[][] copyB = deepCopy(copy);
		// 선택한 행을 B로 채우는 경우
		for(int i=0; i<W; i++) {
			copyB[cnt][i] = 1;
		}
		subset(cnt+1, copyB, count+1);
		
		// 선택한 행을 바꾸지 않는 경우
		int[][] copyC = deepCopy(copy);
		subset(cnt+1, copyC, count);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());	// 보호 필름 두께
			W = Integer.parseInt(st.nextToken());	// 가로 크기
			K = Integer.parseInt(st.nextToken());	// 합격 기준
			
			arr = new int[D][W];
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			minAns = Integer.MAX_VALUE;
			
			int[][] copy = deepCopy(arr);
			subset(0, copy, 0);
			
			sb.append("#" + (t+1) + " " + minAns + "\n");
		}
		System.out.println(sb);
	}
	
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
