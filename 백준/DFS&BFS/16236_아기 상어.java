import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class Bruteforce_11 {
	static int[][] map;
	static int[][] dist;
	static int[] dx = {0,-1,1,0};
	static int[] dy = {-1,0,0,1};
	static int n, minX, minY, minDist, babyShark = 2, eatCount = 0, time = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		int x=0, y=0;
		
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				
				if(map[i][j] == 9) {
					x = i;
					y = j;
					map[x][y] = 0;
				}
			}
		}
		
		while(true) {
			dist = new int[n][n];
			minX = Integer.MAX_VALUE;
			minY = Integer.MAX_VALUE;
			minDist = Integer.MAX_VALUE;
			
			bfs(x,y);
			
			if(minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
				eatCount++;
				x = minX;
				y = minY;
				map[x][y] = 0;
				time += dist[x][y];
				
				if(eatCount == babyShark) {
					babyShark++;
					eatCount = 0;
				} 
			} else
				break;
		}
		
		System.out.println(time);
	}
	
	static void bfs(int a, int b) {
		Queue<Node> q = new LinkedList<>();
		
		q.add(new Node(a,b));
		
		while(!q.isEmpty()) {			
			Node cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				
				if(dist[nx][ny] != 0 || map[nx][ny] > babyShark)
					continue;

				dist[nx][ny] = dist[x][y]+1;
				
				if(map[nx][ny] != 0 && map[nx][ny] < babyShark) {
					if(minDist > dist[nx][ny]) {
						minDist = dist[nx][ny];
						minX = nx;
						minY = ny;
					} else if(minDist == dist[nx][ny]) {
						if(minX == nx) {
							if(minY > ny) {
								minX = nx;
								minY = ny;
							}
						} else if(minX > nx) {
							minX = nx;
							minY = ny;
						}
					}
					
				}
				q.add(new Node(nx,ny));
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
