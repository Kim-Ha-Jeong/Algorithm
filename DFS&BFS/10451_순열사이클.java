import java.io.*;
import java.util.*;

public class BOJ_10451 {
	static int arr[];
	static boolean visited[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			int count = 0;
			int n = Integer.parseInt(br.readLine());
			arr = new int[n+1];
			visited = new boolean[n+1];
			
			String s[] = br.readLine().split(" ");
			for(int j=1; j<n+1; j++)
				arr[j] = Integer.parseInt(s[j-1]);
				
			for(int j=1; j<n+1; j++) {
				if(!visited[j]) {
					visited[j] = true;
					count++;
					bfs(arr[j]);
				}
			}
			
			System.out.println(count);
		}
		
	}
	
	public static void bfs(int x) {
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		visited[x] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(!visited[arr[now]]) {
				visited[arr[now]] = true;
				q.add(arr[now]);
			}
		}
	}

}
