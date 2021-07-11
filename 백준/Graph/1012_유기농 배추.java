import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
public class Graph_9 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {-1,1,0,0};
	static int m, n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0) {
			String[] s = br.readLine().split(" ");
			m = Integer.parseInt(s[0]);
			n = Integer.parseInt(s[1]);
			int k = Integer.parseInt(s[2]);
			int count = 0;
			
			map = new int[m][n];
			visited = new boolean[m][n];
			
			for(int i=0; i<k; i++) {
				s= br.readLine().split(" ");
				map[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = 1;
			}
			
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						bfs(i,j);
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x,y));
		
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx>= m || ny>=n)
					continue;
				
				if(!visited[nx][ny] && map[nx][ny] == 1) {
					q.add(new Node(nx,ny));
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	static class Node{
		int x;
		int y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
