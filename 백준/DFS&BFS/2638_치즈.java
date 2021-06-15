import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
public class Bruteforce_3 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n, m;
	static Queue<Node> entireQ = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int time = 0;
		
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		while(true) {

			if(check())
				break;
			
			OutsideAir();
			meltCheese();
			init();
			time++;
		}
		
		System.out.println(time);
		
	}

	static void OutsideAir() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0));
		visited[0][0] = true;
		map[0][0] = 9;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || ny<0 || nx>=n || ny>=m)
					continue;
				if(map[nx][ny] == 0 && visited[nx][ny] == false) {
					map[nx][ny] = 9;
					visited[nx][ny] = true;
					q.add(new Node(nx,ny));
				}
			}
		}
		
	}
	
	static void meltCheese() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				int count = 0;
				if(map[i][j] == 1) {
					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if(nx < 0 || ny < 0 || nx>=n || ny>=m)
							continue;
						
						if(map[nx][ny] == 9)
							count++;
					}
					
					if(count >= 2) map[i][j] = 0;
				}
			}
		}
	}
	
	static boolean check() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1)
					return false;
			}
		}
		return true;
	}
	
	static void init() {
		map[0][0] = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 9)
					map[i][j] = 0;
				visited[i][j] = false;
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
