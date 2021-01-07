import java.io.*;
import java.util.*;
public class BOJ_11724 {
	static int n,m;
	static ArrayList<Integer> list[];
	static boolean visited[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String st[] = br.readLine().split(" ");
		
		n = Integer.parseInt(st[0]);
		m = Integer.parseInt(st[1]);
		list = new ArrayList[n+1];
		visited = new boolean[n+1];
		int cnt = 0;
		
		for(int i=1; i<n+1; i++)
			list[i] = new ArrayList<Integer>();
		
		for(int i=0; i<m; i++) {
			String s[] = br.readLine().split(" ");
			int v = Integer.parseInt(s[0]);
			int u = Integer.parseInt(s[1]);
			
			list[v].add(u);
			list[u].add(v);
		}
		
		for(int i=1; i<n+1; i++) {
			if(!visited[i]) {
				cnt++;
				visited[i] = true;
				connect(i);
			}
		}
		
		System.out.println(cnt);
	}
	
	static void connect(int v) {
		for(int i:list[v]) {
			if(!visited[i]) {
				visited[i] = true;
				connect(i);
			}
		}
	}

}
