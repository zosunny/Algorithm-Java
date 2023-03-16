import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static int L;
    static int W;
    static int maxLen = Integer.MIN_VALUE;
    
    static char[][] arr;
    static int[][] tmp;
    static boolean[][] visited;
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void bfs(int x, int y, boolean[][] visited, int[][] tmp) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
        while(!q.isEmpty()) {
            Point p = q.poll();
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx<0 || ny<0 || nx>=L || ny>=W || visited[nx][ny] || arr[nx][ny]=='W') continue;
                if(arr[nx][ny]=='L' && !visited[nx][ny]) {
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                    tmp[nx][ny] = tmp[p.x][p.y] + 1;
                    x = nx;
                    y = ny;
                }
            }
        }
        maxLen = Math.max(maxLen, tmp[x][y]);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());    // 세로
        W = Integer.parseInt(st.nextToken());    // 가로
        
        arr = new char[L][W];
        tmp = new int[L][W];
        visited = new boolean[L][W];
        
        // 지도
        for(int i=0; i<L; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        
        for(int i=0; i<L; i++) {
            for(int j=0; j<W; j++) {
                if(arr[i][j]=='L') {
                	tmp = new int[L][W];
                    visited = new boolean[L][W];
                    bfs(i, j, visited, tmp);
                }
            }
        }
        System.out.println(maxLen);
    }
}
