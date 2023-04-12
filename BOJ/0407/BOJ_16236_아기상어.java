import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, L, R;
	static int day;
	static int flag;
	static int popuSum;
	static int gcnt;
	
	static int[][] arr;
	static boolean[][] visited;
	static int[][] copy;
	static Queue<Point> group;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void calc() {
		// 한 그룹에 들어있는 애 모두 꺼내서 인구수 조정해줘
		int popu = popuSum / gcnt;
		
		while(!group.isEmpty()) {
			Point g = group.poll();
			copy[g.x][g.y] = popu;
		}
		
//		print(copy);
		
	}
	
	public static void bfs(int x, int y, int idx) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		group.add(new Point(x, y));
		visited[x][y] = true;
		popuSum = arr[x][y];
		gcnt++;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				// 범위 밖 이거나 이미 방문했으면 무시
				if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny]) continue;
				// 두 칸의 차이가 L명 이상 R명 이하가 아니라면 못 가
				if(Math.abs(arr[nx][ny]-arr[p.x][p.y]) < L || Math.abs(arr[nx][ny]-arr[p.x][p.y]) > R) continue;
				// 그 외는 갈 수 있는 곳 idx 값으로 같은 그룹임을 표시
				q.add(new Point(nx, ny));
				group.add(new Point(nx, ny));
				visited[nx][ny] = true;
				popuSum += arr[nx][ny];
				gcnt++;
				// 갈 수 있는 칸이 있었음을 표시
				flag = 1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			// 모든 칸에 대해 사방 탐색해 열리는지 확인
			visited = new boolean[N][N];
			flag = 0;
			int idx = 1;
			copy = deepCopy(arr);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						// 그룹이 되는 좌표를 담는 큐
						group = new LinkedList<Point>();
						// 그룹이 되는 칸의 인구수의 합
						popuSum = 0;
						// 그룹이 되는 칸의 수
						gcnt = 0;
						// 현재 칸에서 갈 수 있는 모든 칸을 탐색
						bfs(i, j, idx++);
						// 인구수합이 현재칸보다 크면 확장된 칸이 있다는 뜻
						if(popuSum!=arr[i][j]) {
//							System.out.println("---------------");
//							System.out.println(i + " " + j + "칸에서 확장");
							calc();
//							System.out.println("---------------");
						}
					}
				}
			}
			// 한번도 다른 칸으로 확장한 적이 없으면 함수 종료
			if(flag==0) break;
			// 모든 칸에 대해 하루간 다 탐색했다.
			arr = deepCopy(copy);
			day++;
		}
		System.out.println(day);
	}
	
	// 딥카피
	public static int[][] deepCopy(int[][] arr) {
		int[][] newArr = new int[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				newArr[i][j] = arr[i][j];
			}
		}
		return newArr;
	}
	
	// 디버깅용
	public static void print(int[][] arr) {
		for(int[] r : arr) {
			for(int c : r) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
}
