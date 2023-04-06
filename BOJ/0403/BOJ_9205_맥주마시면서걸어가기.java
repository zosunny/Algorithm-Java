// 다익스트라 or BFS or 플로이드-워샬

package homework;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_9205_맥주마시면서걸어가기 {
	
	static int t;
	static int n;
	static Point[] arr;
	static int[][] adjArr;
	static boolean[] visited;
	
	static class Edge implements Comparable<Edge>{
		int idx, dis;
		Edge(int idx, int dis){
			super();
			this.idx = idx;
			this.dis = dis;
		}
		// 거리가 최소여야 함
		@Override
		public int compareTo(Edge o) {
			return this.dis - o.dis;
		}
	}
	
	public static void dijk(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 시작좌표 0, 거리 0
		pq.add(new Edge(0, 0));
		visited[start] = true;
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			visited[e.idx] = true;

			// 최단거리가 1000보다 크면 쫑난거임..
			if(e.dis > 1000) {
				System.out.println("sad");
				return;
			}
			// 끝까지 도착했으면
			if(e.idx == end) {
				System.out.println("happy");
				return;
			}
			for(int i=0; i<n+2; i++) {
				// 아직 방문하지 않은 인접행렬이면 다 방문.
				if(adjArr[e.idx][i]>0 && !visited[i]) {
					// 0보다 크면(같은 점이아닌 정점) 현재 정점과의 거리 우선순위 큐에 넣어
					pq.add(new Edge(i, adjArr[e.idx][i]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		t = Integer.parseInt(br.readLine());
		for(int tc=0; tc<t; tc++) {
			n = Integer.parseInt(br.readLine());	// 편의점 개수
			
			// 집, 가게 n개, 페스티벌 좌표
			arr = new Point[n+2];
			for(int i=0; i<n+2; i++) {
				st = new StringTokenizer(br.readLine());
				int tmp1 = Integer.parseInt(st.nextToken());
				int tmp2 = Integer.parseInt(st.nextToken());
				arr[i] = new Point(tmp1, tmp2);
			}
			
			// 인접행렬 구하기
			adjArr = new int[n+2][n+2];
			for(int i=0; i<n+2; i++) {
				for(int j=0; j<n+2; j++) {
					adjArr[i][j] = Math.abs(arr[i].x-arr[j].x) + Math.abs(arr[i].y-arr[j].y);
				}
			}
			visited = new boolean[n+2];
			dijk(0, n+1);
		}
	}
}
