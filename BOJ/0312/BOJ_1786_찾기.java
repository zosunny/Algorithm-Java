import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	public static int[] makeTable(String pattern) {
		int pLen = pattern.length();
		int[] table = new int[pLen];
		
		int idx = 0;
		for(int i=1; i<pLen; i++) {
			while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				table[i] = ++idx;
			}
		}
		return table;
	}
	
	public static void kmp(String text, String pattern) {
		int[] table = makeTable(pattern);
		
		int tLen = text.length();
		int pLen = pattern.length();
		
		int idx = 0;
		for(int i=0; i<tLen; i++) {
			while(idx>0 && text.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			if(text.charAt(i) == pattern.charAt(idx)) {
				if(idx == pLen-1) {
					cnt++;
					sb.append(i-idx+1 + " ");
					idx = table[idx];
				}
				else {
					idx++;
				}
			}
		}
	}
	
	static int cnt;
	static StringBuilder sb = new StringBuilder();;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T  = br.readLine();
		String P  = br.readLine();

		kmp(T, P);
		
		System.out.println(cnt);
		System.out.println(sb);
	}
}
