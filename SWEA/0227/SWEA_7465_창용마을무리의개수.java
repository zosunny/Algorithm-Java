import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_7465_창용마을무리의개수 {
	
	static int T;
	static int N;
	static int M;
	static int cnt;
	
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	
	public static int dfs(int v) {
		visited[v] = true;
		for(int i=0; i<graph[v].size(); i++) {
			if(!visited[graph[v].get(i)]) {
				dfs(graph[v].get(i));
			}
		}
		
		return 1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();	// 테케
		
		for(int t=0; t<T; t++) {
			N = sc.nextInt();	// 사람의 수
			M = sc.nextInt();	// 관계 수
			
			cnt = 0;
			
			graph = new ArrayList[N+1];
			visited = new boolean[N+1];
			
			for(int i=1; i<N+1; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				graph[x].add(y);
				graph[y].add(x);
			}
			
			for(int i=1; i<N+1; i++) {
				if(!visited[i])
					cnt += dfs(i);
			}
			
			System.out.println("#" + (t+1) + " " + cnt);
		}
	}
}
