import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int K;
	static int idx;
	
	static LinkedList<Integer> al;
	static LinkedList<Integer> ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // N명
		K = Integer.parseInt(st.nextToken()); // K번째 사람 제거

		al = new LinkedList<>();
		ans = new LinkedList<>();

		for (int i = 0; i < N; i++) { // 1 ~ N 사람 입력
			al.add(i + 1);
		}

		for(int i=0; i<N; i++) {
			idx += K-1;
			if(idx >= al.size()) {
				idx %= al.size();
			}
			ans.add(al.remove(idx));
		}
		
		System.out.print("<");
		for(int i=0; i<ans.size()-1; i++) {
			System.out.print(ans.get(i) + ", ");
		}
		System.out.print(ans.get(ans.size()-1) + ">");
	}
}
