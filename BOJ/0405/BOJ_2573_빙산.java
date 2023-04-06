// BFS

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
	
	static int N, M;
	static int year;
	
	static int[][] arr;
	static boolean[][] visited;
	static Queue<Point> ice;
	static Queue<Info> info;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static class Info{
		int x, y, cnt;
		Info(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	// 빙산 덩어리 카운트하는 함수
	// 카운트 하면서 빙산 좌표 다시 큐에 다 넣어주자
	public static int bfsCount(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		// 카운트 하면서 빙산 좌표 다시 큐에 다 넣어주자
		ice.add(new Point(x, y));
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny] || arr[nx][ny]==0) continue;
				q.add(new Point(nx, ny));
				visited[nx][ny] = true;
				ice.add(new Point(nx, ny));
			}
		}
		return 1;
	}
	
	// 앞에서 담아온 정보를 바탕으로 바다 개수 만큼 높이 줄여주는 함수
	public static void iceRemove() {
		while(!info.isEmpty()) {
			Info i = info.poll();
			// 4방탐색 끝나고 바다 개수 만큼 빙산 높이 줄이기
			arr[i.x][i.y] -= i.cnt;
			// 높이가 0 이하로 내려갈 수는 없음.. 0이하로 내려가면 무한루프 돈다!!
			if(arr[i.x][i.y] < 0) arr[i.x][i.y] = 0;
		}
	}
	
	// 모든 빙산 사방탐색해 빙산별 바다 개수 정보 담아주는 함수
	public static void bfsRemoveInfo(int x, int y) {
		// 한 빙산 좌표 꺼냈을 때 사방 확인해 바다 개수 카운트 할 것
		int cnt = 0;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || ny<0 || nx>=N || ny>=M || arr[nx][ny] != 0) continue;
			// 빙산의 위치에서 사방탐색해 0이 있으면 그 수 카운트
			if(arr[nx][ny] == 0) cnt++;
		}
		// 어떤 빙산 얼마나 줄여야 하는지 정보 담기 
		info.add(new Info(x, y, cnt));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 빙산 정보
        arr = new int[N][M];
        ice = new LinkedList<>();
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		// 빙산 위치 큐에 다 담아!
        		if(arr[i][j] != 0) ice.add(new Point(i, j));
        	}
        }
        while(true) {
        	// 빙산의 높이를 얼마나 줄여야 하는지 정보를 담아두자. 바로 줄이면 다음 bfs돌 때 다른 빙산 줄일때 영향이 감
    		info = new LinkedList<>();
        	for(int i=0; i<N; i++) {
            	for(int j=0; j<M; j++) {
            		if(arr[i][j]!=0) {
            			bfsRemoveInfo(i, j);
            		}
            	}
            }
        	// 쫙 처리해
        	iceRemove();
        	year++;
        	
        	// 빙산 덩어리 계산
            int group = 0;
            visited = new boolean[N][M];
            for(int i=0; i<N; i++) {
            	for(int j=0; j<M; j++) {
            		if(arr[i][j]!=0 && !visited[i][j]) {
            			group += bfsCount(i, j);
            		}
            	}
            }
            // 두 덩어리 이상으로 분리되면 그때 년수 출력
            if(group >= 2) {
            	System.out.println(year);
            	return;
            }
            // 빙산이 다 녹을 때까지 분리되지 않는지 체크
            int flag = 0;
            for(int[] r : arr) {
            	for(int c : r) {
            		if(c != 0) flag = 1;
            	}
            }
            if(flag == 0 && group < 2) {
            	System.out.println(0);
            	return;
            }
        }
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
