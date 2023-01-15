package day0115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DFS {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] graph = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				String ice = sc.nextLine();
				graph[i][j] = Integer.parseInt(ice.charAt(j)+"");	// +"" : string 으로 자동 변환
			}
			
			
//			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//			String ice = bf.readLine();
//			StringTokenizer st = new StringTokenizer(ice);
//			Integer.parseInt(ice);
		}
		
	}
}
