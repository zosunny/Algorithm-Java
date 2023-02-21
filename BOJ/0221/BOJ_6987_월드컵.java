import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵 {
	
	static int t;
	static boolean flag;
	
	static int[][] pair;
	static int[][] game;
	
	public static void backtracking(int cnt) {
		
		if(cnt == 15) {
			flag = true;
			return;
		}
		
		int team1 = pair[cnt][0];
		int team2 = pair[cnt][1];

		// 승 : 패
		if(game[0][team1] > 0 && game[2][team2] > 0) {
			game[0][team1]--;
			game[2][team2]--;
			backtracking(cnt+1);
			game[0][team1]++;
			game[2][team2]++;
		}
		
		// 무 : 무
		if(game[1][team1] > 0 && game[1][team2] > 0) {
			game[1][team1]--;
			game[1][team2]--;
			backtracking(cnt+1);
			game[1][team1]++;
			game[1][team2]++;
		}
		
		// 패 : 승
		if(game[2][team1] > 0 && game[0][team2] > 0) {
			game[2][team1]--;
			game[0][team2]--;
			backtracking(cnt+1);
			game[2][team1]++;
			game[0][team2]++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		pair = new int[15][2];	// 2쌍 15게임
		
		int idx = 0;
		for(int i=0; i<5; i++) {
			for(int j=i+1; j<6; j++) {
				pair[idx][0] = i;
				pair[idx][1] = j;
				idx++;
			}
		}
		
		t = 4;
		while(t --> 0) {
			st = new StringTokenizer(br.readLine());
			
			boolean possible = true;
			flag = false;
			
			game = new int[3][6];	// 승,무,패 횟수를 가지는 6팀
			
			for(int i=0; i<6; i++) {
				 int tmp1 = Integer.parseInt(st.nextToken());	// 승
				 game[0][i] = tmp1;
				 int tmp2 = Integer.parseInt(st.nextToken());	// 무
				 game[1][i] = tmp2;
				 int tmp3 = Integer.parseInt(st.nextToken());	// 패
				 game[2][i] = tmp3;

				 if((tmp1+tmp2+tmp3)!=5) {	// 한 팀의 총 경기수가 5번이 아니면
					 possible = false;
					 break;
				 }
			}
			
			if(!possible) {
				sb.append("0" + " ");
			}else {
				backtracking(0);
				if(flag) {
					sb.append("1" + " ");
				}else {
					sb.append("0" + " ");
				}
			}
		}
		System.out.println(sb.toString());
	}
}
