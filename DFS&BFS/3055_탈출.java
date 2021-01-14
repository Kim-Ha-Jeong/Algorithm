import java.util.*;
import java.io.*;

public class BOJ_3055 {
	static int r, c;
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };
	static char map[][];
	static int result = -1;
	static Queue<Escape> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st[] = br.readLine().split(" ");

		r = Integer.parseInt(st[0]);
		c = Integer.parseInt(st[1]);
		map = new char[r][c];

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '*')
					q.add(new Escape(i, j, 0));
			}
		}
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if (map[i][j] == 'S')
					q.add(new Escape(i, j, 1));
			}
		}

		bfs();
		
		if(result == -1)
			System.out.println("KAKTUS");
		else 
			System.out.println(result);
		
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Escape d = q.poll();
			
			if (d.cnt == 0) {
				for (int i = 0; i < 4; i++) {
					int nx = d.x + dx[i];
					int ny = d.y + dy[i];

					if (nx < 0 || nx >= r || ny < 0 || ny >= c)
						continue;
											
						
						if(map[nx][ny] == '.') {
							map[nx][ny] = '*';
							q.add(new Escape(nx, ny, 0));
						
						}
					}
				
			} else {
				for (int i = 0; i < 4; i++) {
					int nx = d.x + dx[i];
					int ny = d.y + dy[i];

					if (nx < 0 || nx >= r || ny < 0 || ny >= c)
						continue;

					if (map[nx][ny] == 'D') {
						result = d.cnt;
						return;
					}
					
					if(map[nx][ny] == '.') {
						map[nx][ny] = 'S';
						q.add(new Escape(nx, ny, d.cnt + 1));
					}
					
				}
			}

		}

	}
	
	static boolean check() {
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[r][c] == 'D')
					return true;
			}
		}
		
		return false;
	}

}


class Escape {
	int x;
	int y;
	int cnt;

	Escape(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}
