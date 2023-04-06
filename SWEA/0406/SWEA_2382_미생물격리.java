// 시뮬레이션, 구현

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA_2382_미생물격리 {
     
    static int T;
    static int N, M, K;
     
    static Micro[][] arr;
    static int[][] microSum;
    static Point[][] microMax;
    static Queue<Micro> micro;
    static int[] dx = {-1, 0, 1, 0};    // 0: 상 1: 좌 2: 하 3: 우
    static int[] dy = {0, -1, 0, 1};
     
     
    static class Micro{
        int x, y, num, dir;
        Micro(int x, int y, int num, int dir){
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }
    }
     
    // 남아있는 미생물의 총 합 구하는 함수
    public static int totalSum() {
        int sum = 0;
        while(!micro.isEmpty()) {
            Micro p = micro.poll();
            sum += p.num;
        }
        return sum;
    }
     
    // 전에 정보 담았던 배열 탐색하면서 미생물 정보 다시 담아
    public static void getMicroInfo() {
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(microSum[i][j]!=0) {
        			// 미생물의 수는 microSum에 있는 총합을 담고
        			int nnum = microSum[i][j];
            		// 방향은 microMax에 있는 방향을 담아
        			int ndir = microMax[i][j].y;
        			
        			micro.add(new Micro(i, j, nnum, ndir));
        		}
        	}
        }
    }
     
    // 모든 좌표에 있는 미생물 다 한번 이동시키고
    public static void moveBFS() {
    	
        // 이동 시 미생물 수의 총 합을 구해놓는 배열
    	microSum = new int[N][N];
    	// 이동 시 한 칸의 미생물의 최댓값과 방향정보를 구해놓는 배열
    	microMax = new Point[N][N];
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			microMax[i][j] = new Point();
    		}
    	}
        
    	// 한 칸씩 이동시켜
        while(!micro.isEmpty()) {
            Micro m = micro.poll();
            // 현재 위치에 있는 미생물의 다음 좌표 계산
            int nx = m.x + dx[m.dir];
            int ny = m.y + dy[m.dir];
            
            // 1) 약품 셀에 닿으면 미생물 수는 1/2, 방향은 반대로 만들어
            if(nx==0 || ny==0 || nx==N-1 || ny==N-1) {
                int nnum = m.num / 2;
                int ndir = (m.dir + 2) % 4;
                // 미생물의 수가 2 이상일때만 
//                if(nnum >= 2) {
                	// 해당 칸에 미생물 수와 방향 정보 표시
                    microSum[nx][ny] = nnum;
                    microMax[nx][ny] = new Point(nnum, ndir);
//                }
            }
            else {
                // 2) 군집이 이동했을 때 약품이 없는 셀이면,
            	// 미생물 수는 각 군집 미생물 수의 총 합이고
                microSum[nx][ny] += m.num;
                // 해당 셀에 위치한 군집들 중 미생물의 최댓값을 가지는 군집의 미생물의 수와 방향을 가짐
                microMax[nx][ny] = microMax[nx][ny].x > m.num ? new Point(microMax[nx][ny].x, microMax[nx][ny].y) : new Point(m.num, m.dir);
            }
        }
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());       // 셀의 개수
            M = Integer.parseInt(st.nextToken());       // 격리 시간
            K = Integer.parseInt(st.nextToken());       // 군집의 개수
             
            micro = new LinkedList<>();
            for(int k=0; k<K; k++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
 
                // 각 좌표에 미생물 수랑 이동 방향 저장
                if(dir == 1) dir = 0;
                else if(dir == 2) dir = 2;
                else if(dir == 3) dir = 1;
                else if(dir == 4) dir = 3;
 
                // 미생물 좌표 따로 큐에 저장
                micro.add(new Micro(row, col, num, dir));
            }
             
            while(M --> 0) {
            	// M시간 동안 이동해!
                moveBFS();
                // 미생물 정보 다시 담기
                getMicroInfo();
            }
             
            // M시간 후 남아있는 미생물의 총합
            int ans = totalSum();
             
            sb.append("#" + (t+1) + " " + ans + "\n");
        }
        System.out.println(sb);
    }
}
