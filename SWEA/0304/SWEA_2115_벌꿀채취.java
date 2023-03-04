import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취 {
	
	static int T;
	static int N, M, C;
	static int profitSum;
	static int maxProfit;
	static int flag;
	static int ans;
	
	static int[] row;
	static int[][] arr;
	static int[][] putBeehive;
	
	public static void subset(int cnt, int[] putBeehiveOne, boolean[] selected) {
		if(cnt == M) {
			int sum = 0;
			int profit = 0;
			for(int i=0; i<M; i++) {
				if(selected[i]) {
					sum += putBeehiveOne[i];
					if(sum > C) break;
					profit += (int)Math.pow(putBeehiveOne[i], 2);
				}
			}
			maxProfit = Math.max(maxProfit, profit);
			return;
		}
		selected[cnt] = true;
		subset(cnt+1, putBeehiveOne, selected);
		selected[cnt] = false;
		subset(cnt+1, putBeehiveOne, selected);
	}
	
	// 넘겨받은 M개의 벌통을 가지로 꿀을 채취해보자
	public static void getHoney(int[][] putBeehive) {
		profitSum = 0;
		for(int i=0; i<2; i++) {
			maxProfit = Integer.MIN_VALUE;
			boolean[] selected = new boolean[M];
			subset(0, putBeehive[i], selected);
			profitSum += maxProfit;
		}
	}
	
	// M개만큼 벌통 선택 (행을 2로 조합)
	public static void beehive(int cnt, int start, boolean[] select) {
		if(cnt == 2) {
			row = new int[2];		// 조합으로 택한 2명의 일꾼 행 담아
			int index = 0;
			for(int i=0; i<N; i++) {
				if(select[i]) {
					row[index++] = i;
				}
			}
			putBeehive = new int[2][M];
			for(int i=0; i<N-M+1; i++) {		// 첫번째 일꾼 꺼
				int idx1 = 0;
				for(int j=i; j<i+M; j++) {
					putBeehive[0][idx1++] = arr[row[0]][j];
				}
				for(int m=0; m<N-M+1; m++) {		// 두번째 일꾼 꺼
					int idx2 = 0;
					for(int n=m; n<m+M; n++) {
						putBeehive[1][idx2++] = arr[row[1]][n];
					}
					getHoney(putBeehive);	// 꿀 채취로 넘겨!
					ans = Math.max(ans, profitSum);
				}
			}
			return;
		}
		for(int i=start; i<N; i++) {
			if(select[i]) continue;
			select[i] = true;
			beehive(cnt+1, i, select);
			select[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());	// 테케
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 벌통들의 크기
			M = Integer.parseInt(st.nextToken());	// 선택 가능한 벌통의 개수
			C = Integer.parseInt(st.nextToken());	// 꿀을 채취할 수 있는 최대 양
			
			ans = Integer.MIN_VALUE;
			
			arr = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			boolean[] select = new boolean[N];
			beehive(0, 0, select);
			
			System.out.println("#" + (t+1) + " " + ans);
		}
	}
}
