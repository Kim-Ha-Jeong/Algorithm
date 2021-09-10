import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_2178 {
	static int[][] map;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n, m;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			s = br.readLine().split("");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		bfs(0, 0);
	}

	static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(x,y,0));
		map[x][y] = 0;
		
		while(!q.isEmpty()) {

			Node cur = q.poll();
			
			if(cur.x == n-1 && cur.y == m-1) {
				System.out.println(cur.cnt+1);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || nx >= n || ny <0 || ny >= m)
					continue;
				
				if(map[nx][ny] == 1) {
					map[nx][ny] = 0;
					q.add(new Node(nx, ny, cur.cnt+1));
				}
			}
		}
		
	}
	
	static class Node{
		int x;
		int y;
		int cnt;
		Node(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
