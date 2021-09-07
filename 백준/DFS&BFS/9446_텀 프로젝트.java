import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class BOJ_9446 {

	static boolean[] visited, done;
	static int[] arr;
	static int cnt, n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0) {
			cnt = 0;
			n = Integer.parseInt(br.readLine());
			
			arr = new int[n+1];
			visited = new boolean[n+1];
			done = new boolean[n+1];
			
			String[] s = br.readLine().split(" ");
			
			for(int i=1; i<n+1; i++) {
				arr[i] = Integer.parseInt(s[i-1]);
			}
			
			for(int i=1; i<n+1; i++) {
				if(!done[i]) {
					dfs(i);
				}
			}
			
			int ret = n-cnt;
			bw.write(Integer.toString(ret)+"\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static void dfs(int v) {
		if(visited[v]) {
			done[v] = true;
			cnt++;
		} else {
			visited[v] = true;
		}
		
		int tmp = arr[v];
		
		if(!done[tmp]) dfs(tmp);
		
		visited[v] = false;
		done[v] = true;
	}

}
