package study;

import java.io.*;
import java.util.*;

public class Maze {
	static int n, m;
	static int[][] maze;
	static boolean[][] visited;
	static int min;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");

		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		maze = new int[n + 1][m + 1];
		visited = new boolean[n+1][m+1];
		min = n * m;
		int count = 0;

		for (int i = 1; i < n + 1; i++) {
			String[] s = br.readLine().split("");
			for (int j = 1; j < m + 1; j++)
				maze[i][j] = Integer.parseInt(s[j-1]);
		}
		
		bfs();
		
		System.out.println(maze[n][m]);
	}

	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		int nx, ny;
		q.add(new Node(1,1));
		visited[1][1] = true;

		while (!q.isEmpty()) {
			Node a = q.poll();
			
			for(int i=0; i<4; i++) {
				nx = a.x+dx[i];
				ny = a.y+dy[i];
				
				if(nx<0 || ny<0 || nx>=n+1 || ny>=m+1) 
					continue;
				
				if(maze[nx][ny] == 0 || visited[nx][ny])
					continue;
				
				maze[nx][ny] = maze[a.x][a.y] + 1;
				visited[nx][ny] = true;
				q.add(new Node(nx, ny));
			
			}

		}
	}

}
