import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890_경사로 {
	
	static int N, L;
	static int rowFlag;
	static int colFlag;
	static int cnt;
	static int ans;
	
	static int[][] arr;
	static boolean[][] rowVisited;
	static boolean[][] colVisited;
	
	public static boolean checkCol(int x, int y) {
		
		// colFlag가 0이면 아래로, 1이면 위로 경사로를 놓을 수 없는 경우를 확인
		if(colFlag == 0) {
			for(int i=0; i<L; i++) {
				// 1. 경사로를 놓다가 범위를 벗어나는 경우
				if(x+i >= N) return false;
				
				// 2. 경사로를 놓은 곳에 또 경사로를 놓는 경우
				if(colVisited[x+i][y]) return false;
			}
			// 3. 낮은 칸의 L개가 연속되지 않는 경우
			for(int i=1; i<L; i++) {
				if(arr[x][y] != arr[x+i][y]) return false;
			}
		}else {
			for(int i=0; i<L; i++) {
				// 1. 경사로를 놓다가 범위를 벗어나는 경우
				if(x-i < 0) return false;
				
				// 2. 경사로를 놓은 곳에 또 경사로를 놓는 경우
				if(colVisited[x-i][y]) return false;
			}
			// 3. 낮은 칸의 L개가 연속되지 않는 경우
			for(int i=1; i<L; i++) {
				if(arr[x][y] != arr[x-i][y]) return false;
			}
		}
		// 다 잘 통과했으면
		if(colFlag == 0) {
			for(int i=0; i<L; i++) {
				colVisited[x+i][y] = true;
			}
		}else {
			for(int i=0; i<L; i++) {
				colVisited[x-i][y] = true;
			}
		}
		
		return true;
	}
	
	public static boolean checkRow(int x, int y) {
		// rowFlag가 0이면 오른쪽으로, 1이면 왼쪽으로 경사로를 놓을 수 없는 경우를 확인
		if(rowFlag == 0) {
			
			
			for(int i=0; i<L; i++) {
				// 1. 경사로를 놓다가 범위를 벗어나는 경우
				if(y+i >= N) return false;
				
				// 2. 경사로를 놓은 곳에 또 경사로를 놓는 경우
				if(rowVisited[x][y+i]) return false;
			}
			// 3. 낮은 칸의 L개가 연속되지 않는 경우
			for(int i=1; i<L; i++) {
				if(arr[x][y] != arr[x][y+i]) return false;
			}
			
		}else {
			for(int i=0; i<L; i++) {
				// 1. 경사로를 놓다가 범위를 벗어나는 경우
				if(y-i < 0) return false;
				
				// 2. 경사로를 놓은 곳에 또 경사로를 놓는 경우
				if(rowVisited[x][y-i]) return false;
			}
			// 3. 낮은 칸의 L개가 연속되지 않는 경우
			for(int i=1; i<L; i++) {
				if(arr[x][y] != arr[x][y-i]) return false;
			}
			
		}
		// 다 잘 통과했으면
		if(rowFlag == 0) {
			for(int i=0; i<L; i++) {
				rowVisited[x][y+i] = true;
			}
		}else {
			for(int i=0; i<L; i++) {
				rowVisited[x][y-i] = true;
			}
		}
		
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        arr = new int[N][N];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        // 행 탐색
        // 앞 뒤 탐색하다 다음 칸이 1 낮아지면 다음 칸을, 1 높아지면 현재칸을 넘겨 경사로를 둘 수 있는지 확인
        for(int i=0; i<N; i++) {
        	// 경사로의 유무를 확인할 방문처리 배열
    		rowVisited = new boolean[N][N];
    		boolean okay = true;
        	for(int j=0; j<N-1; j++) {
        		rowFlag = 0;
        		if(arr[i][j] - arr[i][j+1] == 1) {
        			if(!checkRow(i, j+1)) okay = false;
        		}else if(arr[i][j] - arr[i][j+1] == -1){
        			rowFlag = 1;
        			if(!checkRow(i, j)) okay = false;
        		}else if(Math.abs(arr[i][j] - arr[i][j+1]) > 1){
        			okay = false;
        		}
        	}
        	if(okay) {
        		ans++;
        	}
        }
        
        // 열 탐색
        // 위 아래 탐색하다 아래 칸이 1 낮아지면 아래 칸을, 1 높아지면 현재칸을 넘겨 경사로를 둘 수 있는지 확인
        for(int j=0; j<N; j++) {
        	// 경사로의 유무를 확인할 방문처리 배열
        	colVisited = new boolean[N][N];
        	boolean okay = true;
        	for(int i=0; i<N-1; i++) {
        		colFlag = 0;
        		if(arr[i][j] - arr[i+1][j] == 1) {
        			if(!checkCol(i+1, j)) okay = false;
        		}else if(arr[i][j] - arr[i+1][j] == -1){
        			colFlag = 1;
        			if(!checkCol(i, j)) okay = false;
        		}else if(Math.abs(arr[i][j] - arr[i+1][j]) > 1){
        			okay = false;
        		}
        	}
        	if(okay) {
        		ans++;
        	}
        }
        System.out.println(ans);
	}
	
  // 디버깅용
	public static void print(boolean[][] arr) {
		for(boolean[] r : arr) {
			for(boolean c : r) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
		System.out.println("----------------");
	}
}
