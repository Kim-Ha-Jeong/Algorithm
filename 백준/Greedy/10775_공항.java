import java.io.*;

public class BOJ_10775 {
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		parent = new int[G+1];
		
		for(int i=1; i<G+1; i++) {
			parent[i] = i;
		}
		
		int cnt = 0;
		
		for(int i=0; i<P; i++) {
			int g = Integer.parseInt(br.readLine());
			
			int emptyGate = find(g);
			
			if(emptyGate == 0) break;
			
			cnt++;
			union(emptyGate, emptyGate - 1);
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y) parent[x] = y;
	}
}
