import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1197_최소스패닝트리 {
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight){
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int V;
	static int E;
	static int result;
	static int cnt;
	
	static Edge[] edges;
	static int[] parents;
	
	static void makeSet() {
		parents = new int[V+1];
		for(int i=1; i<V+1; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(x == parents[x]) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		
		if(rootA == rootB) return false;
		
		parents[rootB] = rootA;
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();	// 정점의 개수
		E = sc.nextInt();	// 간선의 개수
		
		edges = new Edge[E];
		
		for(int i=0; i<E; i++) {
			edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		
		makeSet();
		
		Arrays.sort(edges);

		for(Edge e : edges) {
			if(union(e.from, e.to)) {
				result += e.weight;
				if(++cnt == V-1) break;
			}
		}
		System.out.println(result);
	}
}
