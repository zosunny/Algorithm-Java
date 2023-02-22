import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int T;
	static int N;
	
	static int minSumDist;
	
	public static void combi(int idx, boolean[] selected, Point company, Point[] customers, Point[] newCustomers, Point home) {
		if(idx == N) {
			int sumDist = 0;
			sumDist += Math.abs(company.x-newCustomers[0].x) + Math.abs(company.y-newCustomers[0].y);	// 회사와 첫번째 고객 사이 거리
			sumDist += Math.abs(home.x-newCustomers[N-1].x) + Math.abs(home.y-newCustomers[N-1].y);		// 집과 마지막 고객 사이 거리
			for(int i=1; i<N; i++) {
				sumDist += Math.abs(newCustomers[i-1].x-newCustomers[i].x) + Math.abs(newCustomers[i-1].y-newCustomers[i].y);
			}
			minSumDist = Math.min(minSumDist, sumDist);
			return;
		}
		for(int i=0; i<N; i++) {
			if(selected[i]) continue;
			newCustomers[idx] = new Point(customers[i].x, customers[i].y);
			selected[i] = true;
			combi(idx+1, selected, company, customers, newCustomers, home);
			selected[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			minSumDist = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());	// 고객 수
			Point[] customers = new Point[N];			// 고객 좌표 담을 포인트 배열
			Point[] newCustomers = new Point[N];		// 새로운 고객 좌표 담을 포인트 배열
			
			boolean[] selected = new boolean[N];		// 고객 좌표 담을 포인트 배열
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Point company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 회사 좌표
			Point home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));		// 집 좌표
			
			for(int i=0; i<N; i++) {
				customers[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 고객 좌표
			}
			combi(0, selected, company, customers, newCustomers, home);
			
			System.out.println("#" + (t+1) + " " + minSumDist);
		}
	}
}
