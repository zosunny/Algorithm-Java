package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2644_촌수계산_dfs {
	
	static int n;
	static int p1, p2;
	static int m;
	static int cnt;
	static int flag;
	
	static List<Integer>[] graph;
	static boolean[] visited;
	
	public static void dfs(int x, int cnt) {
		visited[x] = true;
		for(int i=0; i<graph[x].size(); i++) {
			int next = graph[x].get(i);
			if(!visited[next]) {
				cnt++;
				if(next == p2) {
					flag = 1;
					System.out.println(cnt);
					return;
				}
				dfs(next, cnt);
				cnt--;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());	// 사람 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());	// 부모 자식 관계 수
		
		graph = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		visited = new boolean[n+1];
		
		dfs(p1, 0);
		
		if(flag != 1) {
			System.out.println(-1);
		}
	}
}
