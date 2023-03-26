import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int T;
	static int N;
	static int cnt;
	
	static List<ap> aps;
	static int tmp;
	
	static class ap implements Comparable<ap> {
		int s1;
		int s2;
		int idx;
		
		public ap(int s1, int s2, int idx) {
			super();
			this.s1 = s1;
			this.s2 = s2;
			this.idx = idx;
		}

		@Override
		public int compareTo(ap o) {
			return this.s1 - o.s1;		// 오름차순 정렬
		}		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());	// 지원자 수
			aps = new ArrayList<>();
			cnt = N;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int score1 = Integer.parseInt(st.nextToken());
				int score2 = Integer.parseInt(st.nextToken());
				aps.add(new ap(score1, score2, i));
			}
			Collections.sort(aps);
			
			tmp = aps.get(0).s2;		// 필기 최하등수의 면접 점
			
			// 필기 비교
			for(int i=0; i<N; i++) {
				if(aps.get(i).s2 < tmp) tmp = aps.get(i).s2;
				if(aps.get(i).s2 - tmp > 0) {
					cnt--;
				}
			}
			System.out.println(cnt);
		}
	}
}

