// 바이러스
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
public class Bruteforce_10 {
	static List<Integer>[] computer;
	static boolean[] visited;
	static int count = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		computer = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		for(int i=1; i<n+1; i++)
			computer[i] = new ArrayList<>(); 
		
		for(int i=0; i<m; i++) {
			String[] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			computer[a].add(b);
			computer[b].add(a);
		}
		
		visited[1] = true;
		dfs(1);
		
		System.out.println(count);
		
	}
	
	static void dfs(int virus) {
		for(int target : computer[virus]) {
			if(!visited[target]) {
				visited[target] = true;
				count++;
				dfs(target);
			}
		}
	}

}
