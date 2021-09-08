import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1926 {
	static int n,m;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int width = 1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		int cnt = 0, max = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1) {
					width = 1;
					map[i][j] = -1;
					dfs(i, j);
					cnt++;
					max = Math.max(max, width);
				}
			}
		}
		
		sb.append(cnt);
		sb.append("\n");
		sb.append(max);
		sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dfs(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			
			if(nx >= n || nx < 0 || ny >= m || ny < 0)
				continue;
			
			if(map[nx][ny] == 1) {
				map[nx][ny] = -1;
				width++;
				dfs(nx, ny);
			}
		}
	}

}
