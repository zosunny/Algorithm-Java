package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2644_촌수계산_bfs_visited_int {
	
	static int n;
	static int p1, p2;
	static int m;
	
	static List<Integer>[] graph;
	static int[] visited;
	
	public static int bfs(int x) {
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		visited[x] = 1;
		while(!q.isEmpty()) {
			int p = q.poll();
			for(int i=0; i<graph[p].size(); i++) {
				int next = graph[p].get(i);		// 다음 노드
				if(visited[next] == 0) {		// 방문을 안했으면
					q.add(next);
					visited[next] = visited[p] + 1;
					if(next == p2) {
						return visited[p];
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
		visited = new int[n+1];
		
		System.out.println(bfs(p1));
	}
}
