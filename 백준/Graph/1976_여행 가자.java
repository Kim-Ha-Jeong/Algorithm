import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Graph_13 {
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		parent = new int[n+1];
		for(int i=1; i<n+1; i++) {
			parent[i] = i;
		}
		
		for(int i=1; i<n+1; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=1; j<n+1; j++) {
				int tmp = Integer.parseInt(s[j-1]);
				
				if(tmp == 1) {
					union(i, j);
				}
			}
		}
		
		String[] s = br.readLine().split(" ");
		int start = find(Integer.parseInt(s[0]));
		
		for(int i=1; i<m; i++) {
			int now = Integer.parseInt(s[i]);
			
			if(start != find(now)) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
	}
	
	static int find(int a) {
		if(a == parent[a])
			return a;
		
		return parent[a] = find(parent[a]);
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			if(a < b) {
				parent[b] = a;
			} else {
				parent[a] = b;
			}
		}
	}
}
