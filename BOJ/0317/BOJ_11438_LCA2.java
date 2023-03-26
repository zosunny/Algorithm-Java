import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;

	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] depth;
	static int[][] parent;

	static void dfs(int v, int d) {
		visited[v] = true;
		depth[v] = d;
		for(int node : graph[v]) {
			if(!visited[node]) {
				parent[node][0] = v;
				dfs(node, d+1);
			}
		}
	}
	
	static void calc() {
		for(int j=1; j<=17; j++) {
			for(int i=1; i<=N; i++) {
				parent[i][j] = parent[parent[i][j-1]][j-1];
			}
		}
	}
	
	static int lca(int u, int v) {
		// 두 노드의 깊이 차 계산
		if(depth[u] < depth[v]) {
			u = u ^ v;
			v = u ^ v;
			u = u ^ v;
		}
		int diff = depth[u] - depth[v];
		
		for(int i=0; 0<diff; i++) {
			if((diff & 1) > 0) u = parent[u][i];
			diff >>= 1;
		}
		if(u == v) return u;
		
		for(int i=17; i>=0; i--) {
			if(parent[u][i] != parent[v][i]) {
				u = parent[u][i];
				v = parent[v][i];
			}
		}
		return parent[u][0];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 그래프 생성
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		depth = new int[N+1];
		parent = new int[N+1][18];
		
		for(int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		// 각 노드의 첫번째 조상 노드 배열 생성
		// dfs(현재 루트 노드 1, 높이 0)
		dfs(1, 0);
		
		// dp(tabulation)를 활용해 2의 배수 조상배열 구축
		calc();

		// 알고싶은 쌍
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			// 최소 공통조상의 탐색
			System.out.println(lca(u, v));
		}
	}
}
