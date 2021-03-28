import java.util.*;
import java.io.*;

public class BOJ_2146 {
	static int n, map[][], result = Integer.MAX_VALUE;
	static boolean visited[][];
	static int dx[] = {0,-1,0,1};
	static int dy[] = {-1,0,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		
		for(int i=0; i<n; i++) {
			String s[] = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 1) {
					visited = new boolean[n][n];
					numberIsland(i,j);
					connect(i,j);
				}
			}
		}
		
		System.out.println(result);
	}
	
	static void numberIsland(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x,y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Node d = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = d.x+dx[i];
				int ny = d.y+dy[i];
				
				if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
				
				if(map[nx][ny] == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Node(nx,ny));
				}
			}
		}
	}
	
	static void connect(int x, int y) {
		Queue<Island> q = new LinkedList<>();
		q.add(new Island(x,y,0));
		
		while(!q.isEmpty()){
			Island d = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = d.x+dx[i];
				int ny = d.y +dy[i];
				
				if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
				
				if(map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Island(nx,ny,d.cnt+1));
				} 
				if(map[nx][ny] == 1 && !visited[nx][ny] && d.cnt>0) {
					result = Math.min(result, d.cnt);
					//System.out.println(result);
				}
			}
		}
	}

}

class Island{
	int x;
	int y;
	int cnt;
	Island(int x, int y, int cnt){
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}
