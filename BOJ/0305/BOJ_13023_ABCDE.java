import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023_ABCDE {
	
	static int N, M;
	static int flag;
	
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	
	public static void dfs(int x, int cnt) {
		if(cnt >= 4) {
			flag = 1;
			return;
		}
		visited[x] = true;
		for(int i=0; i<graph[x].size(); i++) {
			if(!visited[graph[x].get(i)]) {
				dfs(graph[x].get(i), cnt+1);
			}
		}
		visited[x] = false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 사람의 수
		M = Integer.parseInt(st.nextToken());	// 친구 관계 수
		
		graph = new ArrayList[N];	// 사람 수 만큼 생성
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		for(int i=0; i<N; i++) {
			dfs(i, 0);
			if(flag==1) break;
		}
		
		if(flag == 1) {
			System.out.println(1);			
		}else {
			System.out.println(0);
		}
	}
}
