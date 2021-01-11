import java.io.*;
import java.util.*;

public class BOJ_7576 {
	static int n,m, tomato[][];
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	static Queue<Node> q = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String st[] = br.readLine().split(" ");
		n = Integer.parseInt(st[0]);
		m = Integer.parseInt(st[1]);
		tomato = new int[m][n];
		int flag = 1, max = -1;
		
		for(int i=0; i<m; i++) {
			String s[] = br.readLine().split(" ");
			for(int j=0; j<n; j++)
				tomato[i][j] = Integer.parseInt(s[j]);
		}
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(tomato[i][j] == 1) {
					q.add(new Node(i,j));
					tomato[i][j] = 1;
				}
			}
		}
		
		bfs();
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(tomato[i][j] == 0) {
					flag = 0;
					break;
				}
				max = Math.max(max, tomato[i][j]);
			}
		}
		
		if(flag == 0)
			System.out.println(-1);
		else
			System.out.println(max-1);
	}
	
	public static void bfs() {
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				
				if(nx<0 || ny<0 || nx>=m || ny>=n )
					continue;
				
				if(tomato[nx][ny] == 0) {
					tomato[nx][ny] = tomato[now.x][now.y] + 1;
					q.add(new Node(nx,ny));
				}
				
			}
		}
	}

}
