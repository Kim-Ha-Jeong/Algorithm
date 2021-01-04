package study;

import java.util.*;
import java.io.*;

public class Apart {
	static int apart[][];
	static int dx[] = { 0, -1, 0, 1 };
	static int dy[] = { -1, 0, 1, 0 };
	static int n;
	static boolean visited[][];
	static int arr[] = new int[1000];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		apart = new int[n][n];
		visited = new boolean[n][n];
		int count = 0, index = 0;

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < n; j++)
				apart[i][j] = Integer.parseInt(str[j]);
		}

		int k = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && apart[i][j] == 1) {
					count++;
					arr[k] = bfs(i, j);
					k++;
				}
			}
		}

		System.out.println(count);
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == 0) {
				index = i;
				break;
			}	
		}
		
		Arrays.sort(arr,0,index);
		
		for(int i=0; i<index; i++)
			System.out.println(arr[i]);
	}

	public static int bfs(int x, int y) {
		int sum = 0;
		Queue<Node> q = new LinkedList<>();

		q.add(new Node(x, y));
		visited[x][y] = true;
		sum++;

		while (!q.isEmpty()) {
			Node d = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if (!visited[nx][ny] && apart[nx][ny] == 1) {
						visited[nx][ny] = true;
						q.add(new Node(nx, ny));
						sum++;
					}
				}

			}
		}
		return sum;
	}

}
