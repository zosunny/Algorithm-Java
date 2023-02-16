import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
	
	static int N;	// 재료수
	static int L;	// 제한 칼로리
	
	static int[] taste;
	static int[] calorie;
	static boolean[] select;
	
	static ArrayList<Integer> taste_list;
	
	public static void subset(int cnt) {
		
		if(cnt == N) {
			int total_taste = 0;
			int total_calorie = 0;
			for(int i=0; i<N; i++) {
				if(select[i]==true) {
					total_taste += taste[i];
					total_calorie += calorie[i];
				}
			}
			if(total_calorie <= L) {
				taste_list.add(total_taste);
			}
			return;
		}
		
		select[cnt] = true;
		subset(cnt+1);
		select[cnt] = false;
		subset(cnt+1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t=0; t<T; t++) {
			N = sc.nextInt();
			L = sc.nextInt();
			taste = new int[N];
			calorie = new int[N];
			select = new boolean[N];
			taste_list = new ArrayList<>();
			for(int i=0; i<N; i++) {
				taste[i] = sc.nextInt();
				calorie[i] = sc.nextInt();
			}
			subset(0);
			
			Collections.sort(taste_list);
			System.out.println("#" + (t+1) + " " + taste_list.get(taste_list.size()-1));
		}
	}
}
