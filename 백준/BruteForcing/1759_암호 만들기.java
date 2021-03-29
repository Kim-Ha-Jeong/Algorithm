import java.io.*;
import java.util.*;

public class BOJ_1759 {
	static int L, C, result[];
	static char alpha[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s[] = br.readLine().split(" ");
		
		L = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		
		alpha = new char[C];
		result = new int[C];
		
		s = br.readLine().split(" ");
		
		for(int i=0; i<C; i++)
			alpha[i] = s[i].charAt(0);
		
		Arrays.sort(alpha);
		
		dfs("",0);
			
	}
	
	public static void dfs(String pwd, int i) {
		if(pwd.length() == C && check(pwd)) {
			System.out.println(pwd);
			return;
		}
		if(alpha.length <= i)
			return;
		
		dfs(pwd+alpha[i],i+1);
		dfs(pwd,i+1);
		
	}

	public static boolean check(String pwd) {
		int cons = 0;
		int vowel = 0;
		
		for(char c : pwd.toCharArray()) {
			if(c == 'a' || c == 'e' || c =='i' || c =='o' || c=='u')
				vowel++;
			else
				cons++;
		}
		
		if(cons >=2 && vowel >=1)
			return true;
		else
			return false;
	}
}
