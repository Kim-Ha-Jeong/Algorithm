import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ_9935 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		char[] str = br.readLine().toCharArray();
		String bomb = br.readLine();
		Stack<Character> s = new Stack<Character>();
		int len = bomb.length();
		
		for(int i=0; i<str.length; i++) {
			s.push(str[i]);
			
			if(s.size() >= len) {
				boolean flag =true;
				
				int tmp = s.size()-len;
				
				for(int j=0; j<len; j++) {
					if(s.get(tmp+j) != bomb.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					for(int j=0; j<len; j++) {
						s.pop();
					}
				}
			}
		}
		
		for(char c : s) {
			sb.append(c);
		}
		
		bw.write(sb.length() > 0 ? sb.toString() : "FRULA");
		bw.flush();
		bw.close();
		
	}

}
