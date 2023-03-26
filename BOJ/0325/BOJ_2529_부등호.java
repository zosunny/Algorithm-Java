import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int k;
	static long maxInt = Long.MIN_VALUE;
	static long minInt = Long.MAX_VALUE;
	
	static String[] op;
	static int[] input;
	static boolean[] select;
	
	public static void calc(int[] input) {
		String sb = "";
		for(int i=0; i<k+1; i++) {
			sb += input[i];
		}
		long tmp = Long.parseLong(sb);
		maxInt = (long) Math.max(maxInt, tmp);
		minInt = (long) Math.min(minInt, tmp);
	}
	
	public static void permu(int cnt, boolean[] select) {
		if(cnt == k+1) {
			calc(input);
			return;
		}
		for(int i=0; i<10; i++) {
			if(select[i]) continue;
			if(cnt==0) {
				input[cnt] = i;
				select[i] = true;
				permu(cnt+1, select);
				select[i] = false;
			}else {
				if(op[cnt-1].equals("<")) {
					if(select[i] || input[cnt-1]>i) continue;
					input[cnt] = i;
					select[i] = true;
					permu(cnt+1, select);
					select[i] = false;
				}else if(op[cnt-1].equals(">")) {
					if(select[i] || input[cnt-1]<i) continue;
					input[cnt] = i;
					select[i] = true;
					permu(cnt+1, select);
					select[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		k = Integer.parseInt(br.readLine());
		
		// 부등호
		op = new String[k];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			op[i] = st.nextToken();
		}
		
		input = new int[k+1];
		select = new boolean[10];
		
		permu(0, select);
		
		System.out.println(maxInt);
		if(Long.toString(minInt).length() != k+1) {
			System.out.println(0+Long.toString(minInt));
		}else {
			System.out.println(minInt);			
		}
	}
}
