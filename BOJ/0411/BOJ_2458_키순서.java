import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int cnt;
	
	static boolean[] visited;
	static ArrayList<Integer>[] graphOut;
	static ArrayList<Integer>[] graphIn;
	
	public static void dfsIn(int x) {
		visited[x] = true;
		for(int i=0; i<graphIn[x].size(); i++) {
			int nx = graphIn[x].get(i);
			if(!visited[nx]) dfsIn(nx);
		}
	}
	
	public static void dfsOut(int x) {
		visited[x] = true;
		for(int i=0; i<graphOut[x].size(); i++) {
			int nx = graphOut[x].get(i);
			if(!visited[nx]) dfsOut(nx);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graphOut = new ArrayList[N+1];
		graphIn = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			graphOut[i] = new ArrayList<>();
			graphIn[i] = new ArrayList<>();
		} // init end
		
		
		for(int i=0; i<M; i++) {
			 st = new StringTokenizer(br.readLine());
			 int a = Integer.parseInt(st.nextToken());
			 int b = Integer.parseInt(st.nextToken());
			 graphOut[a].add(b);
			 graphIn[b].add(a);
		} // input end
		
		// 각 정점에서 dfs 시작
		for(int i=1; i<N+1; i++) {
			visited = new boolean[N+1];
			dfsOut(i);
			dfsIn(i);
			int check = 0;
			for(int v=1; v<N+1; v++) {
				if(visited[v]) check++;
			}
			if(check==N) cnt++;
		}
		System.out.println(cnt);
	}
}
