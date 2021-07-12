import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class Graph_10 {
	static int[] colors;
	static boolean flag = true;
	static ArrayList<Integer>[] edge;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0) {
			String[] s = br.readLine().split(" ");
			int v = Integer.parseInt(s[0]);
			int e = Integer.parseInt(s[1]);
			edge = new ArrayList[v+1];
			colors = new int[v+1];
			flag = true;
			
			for(int i=1; i<v+1; i++) {
				edge[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<e; i++) {
				s = br.readLine().split(" ");
				int x = Integer.parseInt(s[0]);
				int y = Integer.parseInt(s[1]);
				
				edge[x].add(y);
				edge[y].add(x);
			}
			
			for(int i=1; i<v+1; i++) {
				if(!flag)
					break;
				
				if(colors[i] == 0) {
					bfs(i, 1);
				}
			}
			
			System.out.println(flag == true ? "YES" : "NO");
		}
	}
	
	static void bfs(int x, int color) {
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		
		colors[x] = color;
		
		while(!q.isEmpty() && flag) {
			int v = q.poll();
			
			for(int adjV : edge[v]) {
				if(colors[adjV] == 0) {
					colors[adjV] = colors[v]*(-1);
					q.add(adjV);
				} else if(colors[v] + colors[adjV] != 0) {
					flag = false;
					return;
				}
			}
		}
		
	}
}
