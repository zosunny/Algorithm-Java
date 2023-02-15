import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int ans;

	static int[][] arr;
	static boolean[] select;

	static ArrayList<Point> house;
	static ArrayList<Point> chicken;
	static ArrayList<Point> chicken_combi;
	static ArrayList<Integer> distance;

	public static void combination(int idx, int start) {
		if (idx == M) { // chicken_combi 에 치킨집 M개의 조합 담겨있음

			int dis_min_sum = 0;
			for (int i = 0; i < house.size(); i++) {
				int dis_min = Integer.MAX_VALUE;
				for (int j = 0; j < chicken_combi.size(); j++) {
					Point p1 = house.get(i);
					Point p2 = chicken_combi.get(j);
					int dis_tmp = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
					if (dis_tmp < dis_min)
						dis_min = dis_tmp; // 각 집마다 M개의 치킨집 대해 구한 거리 중 최솟값을 dis_min에 저장
				}
				dis_min_sum += dis_min; // 모든 집이 M개의 치킨집과 비교해 구한 최솟값을 누적해 저장
			}
			distance.add(dis_min_sum);
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			chicken_combi.add(new Point(chicken.get(i)));
			combination(idx + 1, i + 1);
			chicken_combi.remove(new Point(chicken.get(i)));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시
		M = Integer.parseInt(st.nextToken()); // 최대 치킨집

		arr = new int[N][N];

		house = new ArrayList<>();
		chicken = new ArrayList<>();
		chicken_combi = new ArrayList<>();
		distance = new ArrayList<>();

		select = new boolean[chicken.size()];

		// 도시 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					house.add(new Point(i, j)); // 집의 좌표 저장
				}
				if (arr[i][j] == 2) {
					chicken.add(new Point(i, j)); // 치킨집의 좌표 저장
				}
			}
		}
		combination(0, 0);

		Collections.sort(distance);
		System.out.println(distance.get(0));
	}
}
