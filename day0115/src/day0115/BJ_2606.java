package day0115;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ_2606 {
	static int N;
	static int L;
	static boolean[] visited;
	static int cnt;
	
	public static void dfs(int v, ArrayList<Integer>[] arr) {
		// 방문처리
		visited[v] = true;
		// 연결노드 재귀 탐색
		for(int i=0; i<arr[v].size(); i++) {
			if(visited[arr[v].get(i)]==false) {
				cnt += 1;
				System.out.println("cnt: " + cnt + " " + "v: " + arr[v].get(i));
				dfs(arr[v].get(i), arr);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		
		ArrayList<Integer>[] graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		// graph 만들기
		for(int i=0; i<L; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph[a].add(b);
			graph[b].add(a);
		}
		
		// 방문처리할 배열
		visited = new boolean[N+1];
		
		// dfs 탐색
		dfs(1, graph);
		
		System.out.println(cnt);
	}
}
