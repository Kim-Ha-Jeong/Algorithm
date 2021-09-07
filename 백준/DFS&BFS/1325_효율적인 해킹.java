import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
public class BOJ_1325 {
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		
		arr = new int[n+1];
		ArrayList<Integer> list[] = new ArrayList[n+1]; 
		
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			list[a].add(b);
		}
		
		int max = -1;
		for(int i=1; i<n+1; i++) {
			boolean[] visited = new boolean[n+1];
			dfs(i, visited, list);
		}
		
		for(int i=1; i<n+1; i++) {
			max = Math.max(arr[i], max);
		}
		
		for(int i=1; i<n+1; i++) {
			if(max == arr[i]) {
				bw.write(Integer.toString(i));
				bw.write(" ");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static void dfs(int x, boolean[] visited, ArrayList<Integer>[] list) {
		visited[x] = true;
		
		for(int tmp : list[x]) {
			if(!visited[tmp]) {
				arr[tmp]++;
				dfs(tmp, visited, list);
			}
		}
	}

}
