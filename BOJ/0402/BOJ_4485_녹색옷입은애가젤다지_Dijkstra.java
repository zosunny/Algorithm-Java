import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int tc;
	
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Edge implements Comparable<Edge>{
		int x, y, weight;
		Edge(int x, int y, int weight){
			super();
			this.x = x;;
			this.y = y;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	public static int dijk(int startX, int startY, int endX, int endY) {
		// 우선순위 큐에 넣어 (가중치를 기준으로 정렬)
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(startX, startY, arr[startX][startY]));
		while(!pq.isEmpty()) {
			
			Edge e = pq.poll();
			
			// 이미 방문한(이미 최소가중치가 들어있는)좌표는 pass
			if(visited[e.x][e.y]) continue;
			
			// 도착점에 오면 현재 좌표에서의 가중치(최솟값) 출력
			if(e.x == endX && e.y == endY) return e.weight;
			
			// 이제 방문처리 해줘
			visited[e.x][e.y] = true;
			
			// 아직방문하지 않은 좌표 우선순위큐에 넣어
			for(int i=0; i<4; i++) {
				int nx = e.x + dx[i];
				int ny = e.y + dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				int nweight = e.weight + arr[nx][ny];
				
				pq.add(new Edge(nx, ny, nweight));
			}
		}
		// 마지막 도달 못하면.. 이 문제에선 그럴 수 없음
		return -1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			N = Integer.parseInt(br.readLine());
			// 0들어오면 종료
			if(N==0) break;
			
			arr = new int[N][N];
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			tc++;
			// (0, 0) 부터 (N-1, N-1)까지 가중치를 고려한 최단거리를 찾겠다!
			sb.append("Problem " + tc + ": " + dijk(0, 0, N-1, N-1) + "\n");
		}
		System.out.println(sb);
	}
}
