import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ_1766 {
	static int[] indegree;
	static int n, m;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		indegree = new int[n+1];
		list = new ArrayList[n+1];
		
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			list[a].add(b);
			indegree[b]++;
		}
		
		String str = sort();
		bw.write(str);
		bw.flush();
		bw.close();
	}
	
	static String sort() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<n+1; i++) {
			if(indegree[i] == 0)
				pq.add(i);
		}
		
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			
			for(int con : list[cur]) {
				indegree[con]--;
				
				if(indegree[con] == 0) {
					pq.add(con);
				}
			}
			
			sb.append(Integer.toString(cur));
			sb.append(" ");
		}
		
		return sb.toString();
	}
}
