import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1922_네트워크연결 {
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int N;
	static int M;
	static int result;	// 최소비용 저장
	static int cnt;		// 간선 개수 체크
	
	static int[] parents;
	static Edge[] edges;
	
	public static void makeSet() {
		parents = new int[N+1];
		for(int i=1; i<N+1; i++) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;				// b는 현재 자기자신이 대빵, 하지만 이제 a의 대빵 밑으로 들어감 
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 컴퓨터의 수
		M = Integer.parseInt(br.readLine());	// 간선의 수
		
		edges = new Edge[M];
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			int tmp3 = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(tmp1, tmp2, tmp3);
		}
		
		Arrays.sort(edges);
		
		makeSet();
		
		for(Edge e : edges) {
			if(union(e.from, e.to)) {
				result += e.weight;
				if(++cnt == N-1) break;
			}
		}
		System.out.println(result);
	}
}
