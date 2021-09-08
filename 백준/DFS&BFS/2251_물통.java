import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2251 {
	static int A,B,C;
	static boolean[][] visited;
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] s = br.readLine().split(" ");
		A = Integer.parseInt(s[0]);
		B = Integer.parseInt(s[1]);
		C = Integer.parseInt(s[2]);
		
		visited = new boolean[A+1][B+1];
		dfs(0,0,C);
		
		Collections.sort(list);
		
		for(int i: list) {
			sb.append(i+" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dfs(int a, int b, int c) {
		if(visited[a][b]) return;
		
		if(a == 0) list.add(c);
		
		visited[a][b] = true;
		
		if(a+b > B) {
			dfs(a+b-B, B, c);
		} else {
			dfs(0, a+b, c);
		}
		
		if(a+b > A) {
			dfs(A, a+b-A, c);
		} else {
			dfs(a+b, 0, c);
		}
		
		if(a+c > A) {
			dfs(A, b, a+c-A);
		} else {
			dfs(a+c, b, 0);
		}
		
		if(b+c > B) {
			dfs(a, B, b+c-B);
		} else {
			dfs(a, b+c, 0);
		}
		
		dfs(0,b,a+c);
		dfs(a,0,b+c);
	}

}
