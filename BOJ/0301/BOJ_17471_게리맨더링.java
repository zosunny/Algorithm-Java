import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링 {
	
	static int N;
	static int r;
	static int flag;
	static int minResult = Integer.MAX_VALUE;
	
	static int[] popul;
	static boolean[] select;
	static boolean[] visited;
	static boolean[] tmpA;
	static boolean[] tmpB;
	
	static ArrayList<Integer>[] graph;
	
	public static void calcPopul(boolean[] tmpA) {
		int populA = 0;
		int populB = 0;
		int result = 0;
		for(int i=1; i<N+1; i++) {
			if(tmpA[i]) {				// 선거구 A 인구 계산
				populA += popul[i];
			}else {						// 선거구 B 인구 계산
				populB += popul[i];				
			}
		}
		result = Math.abs(populA - populB);
		
		minResult = Math.min(minResult, result);
	}
	
	public static boolean checkVisit(boolean[] visited) {
		for(int i=1; i<N+1; i++) {
			if(!visited[i]) return false;	// 선거구A, 선거구B에서 탐색 안한 구역이 있으면 false
		}
		return true;
	}
	
	public static void dfs(int x, boolean[] tmpArr, boolean[] visited) {
		visited[x] = true;
		for(int i=0; i<graph[x].size(); i++) {
			if(!visited[graph[x].get(i)] && tmpArr[graph[x].get(i)]) {	// 방문안했고 나눈 구역에 해당하면 탐색
				dfs(graph[x].get(i), tmpArr, visited);
			}
		}
	}
	
	public static void subset(int cnt) {
		if(cnt == N) {
			int idx = 1;
			int check = 0;
			tmpA = new boolean[N+1];
			tmpB = new boolean[N+1];
			for(int i=1; i<N+1; i++) {
				if(select[i-1]) {
					tmpA[idx++] = true;		// 선거구A 구역선택
					check++;
				}else {
					tmpB[idx++] = true;		// 선거구B 구역선택
				}
			}
			if(check != 0 && check != N) {		// 한 선거구에 1개 이상의 구역이 존재하는 지 확인				
				visited = new boolean[N+1];		// dfs에서 구역 방문 체크

				int pointA = 0;
				for(int i=1; i<N+1; i++) {
					if(tmpA[i]) {
						pointA = i;
						break;
					}
				}
				dfs(pointA, tmpA, visited);

				int pointB = 0;
				for(int i=1; i<N+1; i++) {
					if(tmpB[i]) {
						pointB = i;
						break;
					}
				}
				dfs(pointB, tmpB, visited);
				
				if(checkVisit(visited)) {	// 모든 구역이 dfs 돌았으면
					calcPopul(tmpA);		// 인구계산하자
				}
			}
			return;
		}
		select[cnt] = true;
		subset(cnt+1);
		select[cnt] = false;
		subset(cnt+1);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());	// 구역의 개수
		
		select = new boolean[N+1];		// subset에서 구역 선택 체크
		
		graph = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {		// 1부터 사용할 것
			graph[i] = new ArrayList<>();
		}
		
		// 각 구역의 인구수 입력
		popul = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			popul[i] = Integer.parseInt(st.nextToken());	// 1인덱스에 1구역
		}
		
		// 인접한 구역 입력받기
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j=0; j<num; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		subset(0);
		
		// 출력 부분
		if(minResult == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(minResult);
		}
	}
}
