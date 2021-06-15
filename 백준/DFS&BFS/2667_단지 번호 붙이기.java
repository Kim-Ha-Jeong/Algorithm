import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class Bruteforce_4 {
	static int n;
	static boolean[][] visited;
	static int[][] map;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int group = 0;
		map = new int[n][n];
		visited = new boolean[n][n];
		List<Integer> ret = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split("");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 1 && visited[i][j] == false) {
					ret.add(bfs(i,j));
					group++;
				}
			}
		}
		
		Collections.sort(ret);
		
		System.out.println(group);
		for(int num : ret) {
			System.out.println(num);
		}
	}
	
	static int bfs(int x, int y) {
		int apart = 1;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x,y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				
				if(map[nx][ny] == 1 && visited[nx][ny] == false) {
					visited[nx][ny] = true;
					q.add(new Node(nx, ny));
					apart++;
				}
			}
		}
		
		return apart;
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
