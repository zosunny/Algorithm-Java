import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
	
	static int T;
	static int N, K;
	static int row;
	
	static char[][] arr;
	static Deque<String> dq;
	static HashSet<Integer> set;
	
	
	public static void checkNum() {
		int dqSize = dq.size();
		while(true) {
			String sb = "";
			for(int i=0; i<row; i++) {			// 행의 수만큼
				String tmp = dq.pollFirst();	// 데크 앞에서 빼
				dqSize--;						// 처음에 데크에 들어있던 문자열 수만큼만 진행
				sb += tmp;
				dq.add(tmp);					// 다시 데크에 넣어
			}
			set.add(Integer.parseInt(sb, 16));	// 생성가능한 수 만들어주고
			if(dqSize==0) break;			// 들어있던 수로 다 만들어 줬으면 탈출
		}
	}
	
	public static void rotate() {
		for(int r=0; r<row; r++) {
			String tmp = dq.pollLast();		// 뒤에서 뻬서
			dq.addFirst(tmp);				// 앞으로 넣어
			checkNum();						// 1회전마다 생성 가능한 수 체크
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 숫자 개수
			K = Integer.parseInt(st.nextToken());	// 원하는 크기 순서
			
			row = N/4;
			arr = new char[4][row];
			
			dq = new ArrayDeque<>();
			String s = br.readLine();
			for(int i=0; i<N; i++) {
				dq.add(String.valueOf(s.charAt(i)));
			}
			
			set = new HashSet<>();
			
			rotate();	// 회전
			
			ArrayList<Integer> list = new ArrayList<>(set);
			
			Collections.sort(list);	// 생성가능한 수 나열
			
			System.out.println("#" + (t+1) + " " + list.get(list.size()-K));
		}
	}
}
