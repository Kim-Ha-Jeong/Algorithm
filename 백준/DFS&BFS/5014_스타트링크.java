import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_5014 {
	static int f,s,g,u,d;
	static int[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		
		f = Integer.parseInt(str[0]);
		s = Integer.parseInt(str[1]);
		g = Integer.parseInt(str[2]);
		u = Integer.parseInt(str[3]);
		d = Integer.parseInt(str[4])*(-1);
		v = new int[f+1];
		
		if(s == g) {
			bw.write("0");
			bw.flush();
			bw.close();
			return;
		}
		
		bfs();
		
		if(v[g] != 0) {
			sb.append(v[g]-1);
		} else {
			sb.append("use the stairs");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(s);
		v[s] = 1;
		int[] upDown = {u,d};
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == g)
				return;
			
			for(int i=0; i<2; i++) {
				int next = cur + upDown[i];
				
				if(next < 1 || next >= f+1) continue;
				
				if(v[next] == 0) {
					q.add(next);
					v[next] = v[cur] + 1;
				}
			}
		}
	}
}
