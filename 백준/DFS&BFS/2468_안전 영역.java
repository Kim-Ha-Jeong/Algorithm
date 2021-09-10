import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2468 {
	static int[][] map;
	static boolean[][] v;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		int min = 101, max = -1;
		map = new int[n][n];
		v = new boolean[n][n];
		
		boolean flag = true;
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
				if(map[0][0] != map[i][j]) {
					flag = false;
				}
			}
		}
		
		if(flag) {
			System.out.println(1);
			return;
		}
		
		int ret = -1;
		int cnt = 0;
		
		for(int k=min; k<=max; k++) {
			v = new boolean[n][n];
			cnt = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(!v[i][j] && map[i][j] > k) {
						dfs(k, i, j);
						cnt++;
					}
				}
			}
			
			ret = Math.max(cnt, ret);
		}
		
		bw.write(ret+"");
		bw.flush();
		bw.close();
	}
	
	static void dfs(int rain, int x, int y) {
		v[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			
			if(!v[nx][ny] && map[nx][ny] > rain) {
				dfs(rain, nx, ny);
			}
		}
	}

}
