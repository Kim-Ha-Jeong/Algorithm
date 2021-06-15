import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Bruteforce_7 {
	static final int trash = 1;
	static int n,m,k, count = 0;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		k = Integer.parseInt(s[2]);
		
		map = new int[n+1][m+1];
		visited = new boolean[n+1][m+1];
		
		for(int i=0; i<k; i++) {
			s = br.readLine().split(" ");
			map[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = trash;
		}
		
		int max = 0;
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<m+1; j++) {
				if(map[i][j] == trash) {
					visited[i][j] = true;
					dfs(i,j);
					max = Math.max(count, max);
					count = 0;
				}
			}
		}
		
		System.out.println(max);
		
	}
	
	public static void dfs(int x, int y) {
		count++;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 1 || ny < 1 || nx>=n+1 || ny>=m+1)
				continue;
			
			if(!visited[nx][ny] && map[nx][ny] == trash) {
				visited[nx][ny] = true;
				dfs(nx,ny);
			}
		}
	}

}
