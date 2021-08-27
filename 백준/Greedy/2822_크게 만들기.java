import java.util.*;
import java.io.*;

public class BOJ_2822 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		String num = br.readLine().trim();
		
		Stack<Integer> stack = new Stack<>();
		
		int cnt = 0;
		for(int i=0; i<num.length(); i++) {
			int c = num.charAt(i) - '0';
			
			while(cnt<k && !stack.isEmpty() && stack.peek() < c) {
				stack.pop();
				cnt++;
			}
			
			stack.push(c);
		}
		
		for(int i=0; i<n-k; i++) {
			System.out.print(stack.elementAt(i));
		}
		
	}

}
