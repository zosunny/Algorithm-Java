import java.util.Scanner;

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
	
	public static void kmp(String str, String pattern) {
		int[] table = makeTable(pattern);
		
		int sLen = str.length();
		int pLen = pattern.length();
		
		int idx = 0;
		for(int i=0; i<sLen; i++) {
			while(idx>0 && str.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			if(str.charAt(i)==pattern.charAt(idx)) {
				if(idx==pLen-1) {
					System.out.println(1);
					return;
				}
				else {
					idx++;
				}
			}
		}
		System.out.println(0);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.nextLine();
		String P = sc.nextLine();
		kmp(S, P);
	}
}
