import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class Bruteforce_5 {
	static int[] dx1 = {-1,1,-2,2,-2,2,-1,1};
	static int[] dx2 = {0,0,-1,1};
	static int[] dy1 = {2,2,1,1,-1,-1,-2,-2};
	static int[] dy2 = {-1,1,0,0};
	static int[][] board;
	static boolean[][][] visited; 
	static int k,w,h;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		
		String[] s = br.readLine().split(" ");
		w = Integer.parseInt(s[0]);
		h = Integer.parseInt(s[1]);
		
		board = new int[h][w];
		visited = new boolean[h][w][k+1];
		
		for(int i=0; i<h; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<w; j++) {
				board[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		bfs();
	}
	
	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		int res = -1;
		q.add(new Node(0,0,0,0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(now.x == h-1 && now.y == w-1) {
				res = now.cnt;
				break;
			}
			
			if(now.k < k) {
				for(int i=0; i<8; i++) {
					int nx = now.x + dx1[i];
					int ny = now.y + dy1[i];
					
					if(nx >= h || ny >= w || nx<0 || ny<0)
						continue;
					
					if(board[nx][ny] != 1 && visited[nx][ny][now.k+1] == false) {
						q.add(new Node(nx, ny, now.cnt+1, now.k+1));
						visited[nx][ny][now.k+1] = true;
					}
				}
			}
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx2[i];
				int ny = now.y + dy2[i];
				
				if(nx >= h || ny >= w || nx < 0 || ny < 0)
					continue;
				
				if(board[nx][ny] != 1 && visited[nx][ny][now.k] == false) {
					q.add(new Node(nx, ny, now.cnt+1, now.k));
					visited[nx][ny][now.k] = true;
				}
			}
		}
		
		System.out.println(res);
	}
	
	static class Node{
		int x;
		int y;
		int cnt;
		int k;
		Node(int x, int y, int cnt, int k){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}
	}

}
