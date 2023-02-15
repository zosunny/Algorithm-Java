import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_1228 {

	static LinkedList<Integer> al;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 0; t < 10; t++) { // 테케 10개
			al = new LinkedList<>();
			int N = Integer.parseInt(br.readLine()); // 원본 암호문 길이
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				al.add(Integer.parseInt(st.nextToken())); // 원본 암호문
			}

			int order = Integer.parseInt(br.readLine()); // 명령어의 개수

			st = new StringTokenizer(br.readLine());

			for (int o = 0; o < order; o++) {
				String p = st.nextToken(); // "I"
				int idx = Integer.parseInt(st.nextToken()); // insert할 위치
				int num = Integer.parseInt(st.nextToken()); // insert할 암호 개수

				for (int n = 0; n < num; n++) {
					al.add(idx++, Integer.parseInt(st.nextToken()));
				}
			}
			System.out.print("#" + (t + 1) + " ");
			for (int x = 0; x < 10; x++) {
				System.out.print(al.get(x) + " ");
			}
			System.out.println();
		}
	}
}
