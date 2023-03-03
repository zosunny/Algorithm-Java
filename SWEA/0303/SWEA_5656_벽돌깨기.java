import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {
	
	static int T;
	static int N;
	static int W;
	static int H;
	static int minCntN;
	
	static int[] dx = {0, 1, 0, -1};	// 왼, 아래, 오른
	static int[] dy = {-1, 0, 1, 0};
	static int[][] arr;
	static int[][] copyArr;
	
	// 벽돌 개수 카운트하는 함수
	public static void cntNum() {
		int cntN = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(copyArr[i][j]!=0) cntN++;
			}
		}
		
//		System.out.println("cntN: " + cntN);
		
		minCntN = Math.min(minCntN, cntN);

//		System.out.println("minCntN: " + minCntN);
	}
	
	// 아래로 내려주는 함수 (스택에 한번 담아서 내렸엉 최고!!)
	public static void goDown() {
		for(int i=0; i<W; i++) {
			Stack<Integer> s = new Stack<>();
			for(int j=0; j<H; j++) {
				if(copyArr[j][i]!=0) {
					s.add(copyArr[j][i]);
				}
			}
			for(int j=H-1; j>=0; j--) {
				if(!s.isEmpty()) {
					copyArr[j][i] = s.pop();					
				}else {
					copyArr[j][i] = 0;
				}
			}
		}
	}
	
	// N개의 벽돌은 하나씩 bfs 돌려
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		q.add(new Point(x, y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			// 디버깅
//			System.out.println("p.x: " + p.x + ", p.y: " + p.y + "값: " + copyArr[p.x][p.y]);
			if(copyArr[p.x][p.y] > 1) {
				for(int k=0; k<copyArr[p.x][p.y]-1; k++) {	// 벽돌번호-1만큼 3방탐색하며 지워
					for(int i=0; i<4; i++) {
						int nx = p.x + dx[i]*(k+1);
						int ny = p.y + dy[i]*(k+1);
						if(nx<0 || ny<0 || nx>=H || ny>=W || visited[nx][ny]) continue;
						if(!visited[nx][ny]) {
							q.add(new Point(nx, ny));
							visited[nx][ny] = true;
						}
					}
				}
			}
			copyArr[p.x][p.y] = 0;
		}
		
//		// 디버깅
//				for(int[] r : copyArr) {
//					for(int c : r) {
//						System.out.print(c + " ");
//					}
//					System.out.println();
//				}
		
		goDown();
	}
	
	// 고른 N개의 열의 가장 위칸에 있는 벽돌의 좌표를 한번씩 bfs로 넘겨줄게요
	public static void peekTop(int tmp, int[] input) {
		for(int i=tmp; i<N; i++) {
			for(int j=0; j<H; j++) {
				if(copyArr[j][input[i]] != 0) {
					// 디버깅
//					System.out.println(j + ", " + input[i]);
					
					bfs(j, input[i]);
					tmp = i+1;
					break;
				}
			}
		}
	}
	
	// 모든 열 중 중복해서 N개를 골라볼게요
	public static void permu(int cnt, int[] input) {
		if(cnt == N) {
			copyArr = deepCopy();
			
//			// 디버깅
//			for(int x : input) {
//				System.out.print(x + " ");
//			}
//			System.out.println();
			
			peekTop(0, input);
			cntNum();	// 조합별 남아있는 벽돌 수 세기
			return;
		}
		for(int i=0; i<W; i++) {
			input[cnt] = i;		// 열의 값
			permu(cnt+1, input);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());	// 테케
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());		// N개의 벽돌 떨어트릴거야
			W = Integer.parseInt(st.nextToken());		// 열
			H = Integer.parseInt(st.nextToken());		// 행
			
			minCntN = Integer.MAX_VALUE;
			
			arr = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] input = new int[N];
			permu(0, input);
			
			// 출력부분
			System.out.println("#" + (t+1) + " " + minCntN);
		}
	}
	
	// 2차원 배열 딥카피
	public static int[][] deepCopy() {
		int[][] copy = new int[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}
}
