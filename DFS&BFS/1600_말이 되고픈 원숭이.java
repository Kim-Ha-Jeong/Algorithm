import java.io.*;
import java.util.*;

public class BOJ_1600 {
	static int k, w, h;
	static int board[][], dist[][][];
	static int dx1[] = { -1,-2,-1,-2,1,2,1,2 };
	static int dy1[] = { -2,-1,2,1,-2,-1,2,1};
	static int dx2[] = { -1, 0, 1, 0 };
	static int dy2[] = { 0, 1, 0, -1 };
	static int flag = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		k = Integer.parseInt(br.readLine());

		String str[] = br.readLine().split(" ");

		w = Integer.parseInt(str[0]);
		h = Integer.parseInt(str[1]);

		board = new int[h][w];
		dist = new int[h][w][k+1];
		
		for (int i = 0; i < h; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 0; j < w; j++) {
				board[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		
		bfs();
		
	}

	public static void bfs() {
		int res = -1;
		Queue<Nodeh> q = new LinkedList<>();

		q.add(new Nodeh(0, 0, 0, 0));
		dist[0][0][0] = 0;

		while (!q.isEmpty()) {
			Nodeh now = q.poll();
			
			if(now.x == h-1 && now.y == w-1) {
				res = dist[now.x][now.y][now.k];
				break;
			}

			if (now.k < k) {
				for (int i = 0; i < 8; i++) {
					int x = now.x + dx1[i];
					int y = now.y + dy1[i];

					if (x < 0 || y < 0 || x >= h || y >= w)
						continue;

					if (board[x][y] != 1 && dist[x][y][now.k+1] == 0) {
						dist[x][y][now.k+1] = now.cnt+1;
						q.add(new Nodeh(x, y, now.k+1, now.cnt+1));
					}
				}
			} 
				for (int i = 0; i < 4; i++) {
					int x = now.x + dx2[i];
					int y = now.y + dy2[i];

					if (x < 0 || y < 0 || x >= h || y >= w)
						continue;

					if (board[x][y] != 1 && dist[x][y][now.k] == 0) {
						dist[x][y][now.k] = now.cnt+1;
						q.add(new Nodeh(x, y, now.k, now.cnt+1));
					}
				}
			
		}
		
		System.out.println(res);
	}

}

class Nodeh{
	int x;
	int y;
	int k;
	int cnt;
	
	Nodeh(int x, int y, int k, int cnt){
		this.x = x;
		this.y = y;
		this.k = k;
		this.cnt = cnt;
	}
}