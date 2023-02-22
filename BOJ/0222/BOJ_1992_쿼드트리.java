import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static char[][] arr;
	
	static StringBuilder sb;
	
	public static void cut(int x, int y, int len) {
		if(len == 1) {
			sb.append((arr[x][y] - '0') + "");
			return;
		}
		
		int sum = 0;
		for(int i=x; i<x+len; i++) {
			for(int j=y; j<y+len; j++) {
				sum += arr[i][j] - '0';
			}
		}
		

		if(0 < sum && sum < len*len) {
			len /= 2;
			sb.append("(");
			cut(x, y, len);
			cut(x, y+len, len);
			cut(x+len, y, len);
			cut(x+len, y+len, len);
		}
		else {
			if(sum == 0) {
				sb.append("0");
			}else {
				sb.append("1");
			}
			return;
		}
		sb.append(")");
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		arr = new char[N][N];
		
		for(int i=0; i<N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		cut(0, 0, N);
		
		System.out.println(sb.toString());
	}
}
