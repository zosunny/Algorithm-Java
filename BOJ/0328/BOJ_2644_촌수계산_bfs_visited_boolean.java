package codingTest;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2644_촌수계산_bfs_visited_boolean {
	
	static int n;
	static int p1, p2;
	static int m;
	static int cnt;
	static int flag;
	
	static List<Integer>[] graph;
	static boolean[] visited;
	
	public static int bfs(int x) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, 1));
		visited[x] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<graph[p.x].size(); i++) {
				if(!visited[graph[p.x].get(i)]) {
					q.add(new Point(graph[p.x].get(i), p.y+1));
					visited[graph[p.x].get(i)] = true;
					if(graph[p.x].get(i) == p2) {
						flag = 1;
						return p.y;
					}
				}
			}
		}
		return -1;
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
		int ans = bfs(p1);
		
		System.out.println(ans);
	}
}
