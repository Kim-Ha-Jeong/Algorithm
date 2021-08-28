import java.io.*;

public class BOJ_3109 {
	static char[][] map;
	static int r, c, cnt = 0;
	static int[] dr = {-1,0,1};
	static boolean connect;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		
		r = Integer.parseInt(s[0]);
		c = Integer.parseInt(s[1]);
		map = new char[r][c];
		
		for(int i=0; i<r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<r; i++) {
			connect = false;
			dfs(i, 0);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void dfs(int row, int col) {
		if(col == c-1) {
			connect = true;
			cnt++;
			return;
		}
		
		for(int i=0; i<3; i++) {
			int nc = col + 1;
			int nr = row + dr[i];
			
			if(nr < 0 || nr >= r || nc < 0 || nc >= c)
				continue;
			
			if(map[nr][nc] == '.') {
				map[row][col] = 'x';
				dfs(nr, nc);
				if(connect) {
					return;
				}
			}
		}
	}

}
