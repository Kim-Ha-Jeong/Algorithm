package study;

import java.util.*;
import java.io.*;

public class Tomato {
	static int m, n, h;
	static int arr[][][];
	static boolean visited[][][];
	static int[] dx = { 0, 0, 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 0, 0, 1, -1 };
	static int[] dh = { 1, -1, 0, 0, 0, 0 };
	static Queue<Dod> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");

		m = Integer.parseInt(s[0]);
		n = Integer.parseInt(s[1]);
		h = Integer.parseInt(s[2]);

		arr = new int[h][n][m];
		visited = new boolean[h][n][m];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				String str[] = br.readLine().split(" ");
				for (int k = 0; k < m; k++)
					arr[i][j][k] = Integer.parseInt(str[k]);
			}
		}
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if(!visited[i][j][k] && arr[i][j][k] == 1) {
						q.add(new Dod(i,j,k));
						visited[i][j][k] = true;
					}
				}
			}
		}
		
		System.out.println(bfs());

	}

	public static int bfs() {

		while (!q.isEmpty()) {
			Dod d = q.poll();
			
			for(int i=0; i<6; i++) {
				int nh = d.h+dh[i];
				int nx = d.x+dx[i];
				int ny = d.y+dy[i];
				
				if(nh >= 0 && nh < h && nx>=0 && nx<n && ny>=0 && ny<m) {
					if(!visited[nh][nx][ny] && arr[nh][nx][ny] == 0) {
						arr[nh][nx][ny] = arr[d.h][d.x][d.y]+1;
						visited[nh][nx][ny] = true;
						q.add(new Dod(nh,nx,ny));
					}
				}

			}
		}
		
		int result = Integer.MIN_VALUE;
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if(arr[i][j][k] == 0) return -1;
					result = Math.max(result, arr[i][j][k]);
				}
			}
		}
		
		if(result == 1) return 0;
		else return (result-1);
	}

}

class Dod {
	int h;
	int x;
	int y;

	Dod(int h, int x, int y) {
		this.h = h;
		this.x = x;
		this.y = y;
	}
}
