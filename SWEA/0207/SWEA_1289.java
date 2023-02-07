import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int cnt = 0;
			int tmp = 0;
			ArrayList<Integer> arr = new ArrayList<>();
			String l = br.readLine();
			for(int i=0; i<l.length(); i++) {
				arr.add(l.charAt(i) - '0');
			}
			for(int i=0; i<arr.size(); i++) {
				if(arr.get(i)!=tmp) {
					cnt++;
					if (tmp==0) tmp=1;
					else if (tmp==1) tmp=0;
				}
			}
			System.out.println("#" + t + " " + cnt);
		}
	}
}
