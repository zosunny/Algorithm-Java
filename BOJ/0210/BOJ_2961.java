import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arrS; // 신맛 배열
	static int[] arrB; // 쓴맛 배열
	static boolean[] visited;
	static int subMin = Integer.MAX_VALUE;

	public static void getSubSet(int idx, int falseCnt) {
		if (idx == N) {
			if(falseCnt == N) return;
			int calcS = 1;
			int calcB = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					calcS *= arrS[i];
					calcB += arrB[i];
				}
			}
			subMin = Math.min(subMin, Math.abs(calcS - calcB)); // 쓴맛-신맛 절댓값 최솟값 갱신
			return;
		}

		visited[idx] = true;
		getSubSet(idx + 1, falseCnt);
		visited[idx] = false;
		getSubSet(idx + 1, falseCnt + 1);

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 재료의 개수 N

		arrS = new int[N];
		arrB = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arrS[i] = Integer.parseInt(st.nextToken()); // 신맛 배열에 추가
			arrB[i] = Integer.parseInt(st.nextToken()); // 쓴맛 배열에 추가
		}
		getSubSet(0, 0);
		
		System.out.println(subMin);
	}
}
