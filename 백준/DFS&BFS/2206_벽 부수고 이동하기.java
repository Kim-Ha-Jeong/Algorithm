import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
public class Bruteforce_12 {
	static int[][] map;
	static int[][][] dist;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		map = new int[n][m];
		dist = new int[n][m][2];
		
		for(int i=0; i<n; i++) {
			s = br.readLine().split("");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		System.out.println(bfs());
		
	}
	
	static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0,0,0));
		dist[0][0][0] = 0;
		int ret = -1;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(cur.x == n-1 && cur.y == m-1) {
				ret = cur.cnt+1;
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx>=n || ny>= m)
					continue;
				
				if(cur.wallBreak < 1) {
					if(map[nx][ny] == 1 && dist[nx][ny][cur.wallBreak+1] == 0) {
						dist[nx][ny][cur.wallBreak+1] = cur.cnt+1;
						q.add(new Node(nx,ny,cur.cnt+1, cur.wallBreak+1));
					}
				}
				
				if(map[nx][ny] == 0 && dist[nx][ny][cur.wallBreak] == 0) {
					dist[nx][ny][cur.wallBreak] = cur.cnt+1;
					q.add(new Node(nx, ny, cur.cnt+1, cur.wallBreak));
				}
			}
		}
		
		return ret;
	}

	static class Node {
		int x;
		int y;
		int cnt;
		int wallBreak;
		Node(int x, int y, int cnt, int wallBreak){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.wallBreak = wallBreak;
		}
	}
}
