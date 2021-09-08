import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_13023 {
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int ret = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		
		list = new ArrayList[n];
		visited = new boolean[n];
		
		for(int i=0; i<n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i=0; i<n; i++) {
			if(ret == 0) {
				dfs(i, 0);
			}
		}
		
		sb.append(ret);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dfs(int x, int depth) {
		if(depth == 4) {
			ret = 1;
			return;
		}
		
		visited[x] = true;
		for(int i : list[x]) {
			if(!visited[i]) {
				dfs(i, depth+1);
			}
		}
		visited[x] = false;
	}

}
