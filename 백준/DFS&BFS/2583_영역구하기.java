import java.util.*;
import java.io.*;

public class BOJ_2583 {
	static int m,n,k;
	static int paper[][];
	static boolean visited[][];
	static int dx[] = {0,-1,0,1};
	static int dy[] = {-1,0,1,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String st[] = br.readLine().split(" ");
		m = Integer.parseInt(st[0]);
		n = Integer.parseInt(st[1]);
		k = Integer.parseInt(st[2]);
		
		paper = new int[m][n];
		visited = new boolean[m][n];
		int flag = 0, result[] = new int[n*m];
		
		for(int i=0; i<k; i++) {
			String s[] = br.readLine().split(" ");
			int x1 = Integer.parseInt(s[0]);
			int y1 = m-Integer.parseInt(s[1]);
			int x2 = Integer.parseInt(s[2]);
			int y2 = m-Integer.parseInt(s[3]);
			
			
			for(int j=y2; j<y1; j++) {
				for(int k=x1; k<x2; k++) {
					paper[j][k] = -1;
				}
			}
		}
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(paper[i][j] != -1 && !visited[i][j]) {
					result[flag] = bfs(i,j);
					flag++;
				}
			}
		}
		
		Arrays.sort(result);
		
		System.out.println(flag);
		for(int i=result.length-flag; i<result.length; i++)
			System.out.print(result[i]+" ");
		
	}
	
	static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x,y));
		visited[x][y] = true;
		
		int cnt = 1;
		while(!q.isEmpty()) {
			Node d = q.poll();
			
			
			for(int i=0; i<4; i++) {
				int nx = d.x+dx[i];
				int ny = d.y+dy[i];
				
				if(nx<0 || nx>=m || ny<0 || ny>=n) continue;
				
				if(!visited[nx][ny] && paper[nx][ny] != -1) {
					visited[nx][ny] = true;
					q.add(new Node(nx,ny));
					cnt++;
				}
			}
		}
		
		return cnt;
	}

}
