import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2668 {
	static int n;
	static boolean[] visited;
	static int[] arr;
	static ArrayList<Integer> ret;
	static int origin;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		ret = new ArrayList<>();
		visited = new boolean[n+1];
		arr = new int[n+1];
		
		for(int i=1; i<n+1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=1; i<n+1; i++) {
			visited[i] = true;
			origin = i;
			dfs(origin);
			visited[i] = false;
		}
		
		Collections.sort(ret);
		
		sb.append(ret.size()+"\n");
		
		for(int i : ret) {
			sb.append(Integer.toString(i));
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int idx) {
		if(arr[idx] == origin) {
			ret.add(origin);
		}
		
		if(!visited[arr[idx]]) {
			visited[arr[idx]] = true;
			dfs(arr[idx]);
			visited[arr[idx]] = false;
		}
	}
}
