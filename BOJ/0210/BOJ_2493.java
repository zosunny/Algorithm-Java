import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static class TopInfo {
		private int idx;
		private int value;

		TopInfo(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}

		public int getIdx() {
			return idx;
		}

		public int getValue() {
			return value;
		}
	}

	static int N;
	static int cnt;

	static Stack<TopInfo> stack;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		stack = new Stack<>();

		StringTokenizer st = new StringTokenizer(br.readLine());

		stack.push(new TopInfo(1, Integer.parseInt(st.nextToken()))); // 첫번째 값은 그냥 넣기 (idx, value)
		sb.append(0 + " ");

		for (int i = 1; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken()); 	// value
			if (tmp < stack.peek().getValue()) {			// 다음 탑의 높이가 이전 탑의 높이보다 작으면
				sb.append(stack.peek().getIdx() + " ");		// 이전 탑의 idx 출력
				stack.push(new TopInfo(i + 1, tmp));		// 현재 탑 스택에 저장
			} else {										// 다음 탑의 높이가 이전 탑의 높이보다 크면
				while (!stack.isEmpty()) {
					if (stack.peek().getValue() >= tmp) {	// 현재 탑보다 큰 탑이 나올때까지
						break;
					}
					stack.pop();							// 작은 탑들은 pop
				}
				if (stack.isEmpty()) {						// 이전 탑들이 다 현재 탑보다 작으면 (스택이 비었으면)
					sb.append(0 + " ");						// 0 출력
					stack.push(new TopInfo(i + 1, tmp));	// 현재 탑은 스택에 저장
				}else {										// 현재 탑보다 큰 탑이 있으면
					sb.append(stack.peek().getIdx() + " ");	// 그 탑의 idx 출력
					stack.push(new TopInfo(i + 1, tmp));	// 현재 탑은 스택에 저장
				}
			}
		}
		System.out.println(sb);
	}
}
