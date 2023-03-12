import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		
		Map<Character, Node> childNode = new HashMap<>();
		
		boolean isEnd;
	}
	
	static class Trie {
		
		Node root = new Node();
		
		void insert(String str) {
			Node node = this.root;
			int size = str.length();
			for(int i=0; i<size; i++) {
				node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
			}
			node.isEnd = true;
		}
		
		boolean search(String str) {
			Node node = this.root;
			int size = str.length();
			for(int i=0; i<size; i++) {
				node = node.childNode.getOrDefault(str.charAt(i), null);
				if(node == null) {
					return false;
				}
			}
			return node.isEnd;
		}
	}
	
	static int N, M;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 문자열 개수
		M = Integer.parseInt(st.nextToken());	// 검사해야하는 문자열
		Trie trie = new Trie();
		for(int i=0; i<N; i++) {
			trie.insert(br.readLine());
		}
		for(int i=0; i<M; i++) {
			boolean tmp = trie.search(br.readLine());
			if(tmp==true) cnt++;
		}
		System.out.println(cnt);
	}
}
