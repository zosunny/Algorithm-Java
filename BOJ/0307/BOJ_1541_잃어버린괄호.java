import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1541_잃어버린괄호 {
	
	static char[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = br.readLine().toCharArray();
		
		boolean flag = false;
		int ans = 0;
		String tmp = "";
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i]=='-' || arr[i]=='+') {
				if(!flag && arr[i]=='-') {
					ans += Integer.parseInt(tmp);
					tmp = "";
					flag = true;
				}
				else if(flag) {
					ans -= Integer.parseInt(tmp);
					tmp = "";
				}else if(!flag && arr[i]=='+') {
					ans += Integer.parseInt(tmp);
					tmp = "";
				}
			}else {
				tmp += arr[i];				
			}
		}
		if(flag) ans -= Integer.parseInt(tmp);
		else ans += Integer.parseInt(tmp);
		
		System.out.println(ans);
	}
}
