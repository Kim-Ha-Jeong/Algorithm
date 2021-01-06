import java.util.*;
import java.io.*;

public class BOJ_9205 {
	static Location a[];
	static int n;
	static boolean visited[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int j = 0; j < t; j++) {
			n = Integer.parseInt(br.readLine());

			a = new Location[n+2];
			visited = new boolean[n+2];
			
			for (int i = 0; i < n + 2; i++) {
				String s[] = br.readLine().split(" ");
				a[i] = new Location(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
			}

			if (!bfs())
				System.out.println("sad");
			else
				System.out.println("happy");
		}

	}

	public static boolean bfs() {
		Queue<Location> q = new LinkedList<>();
		q.add(a[0]);
		
		while(!q.isEmpty()) {
			Location start = q.poll();
			
			if(start == a[n+1])
				return true;
			
			for(int i=0; i<a.length; i++) {
				Location end = a[i];
				int diff = Math.abs(start.x-end.x)+Math.abs(start.y-end.y);
				
				if(diff <= 1000 && !visited[i]) {
					q.add(end);
					visited[i] = true;
				}
			}
		}
		return false;
	}
}

class Location {
	int x;
	int y;

	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
