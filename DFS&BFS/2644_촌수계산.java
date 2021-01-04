package study;

import java.util.*;
import java.io.*;

public class Family {
	static int n, m, a, b;
	static ArrayList<Integer> list[];
	static boolean visited[];
	static int arr[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		String s[] = br.readLine().split(" ");
		a = Integer.parseInt(s[0]);
		b = Integer.parseInt(s[1]);
		
		m = Integer.parseInt(br.readLine());
		
		visited = new boolean[n+1];
		arr = new int[n+1];
		
		list = new ArrayList[n+1];
		
		for(int i=1; i<n+1; i++)
			list[i] = new ArrayList<Integer>();
		
		for(int i=0; i<m; i++) {
			String str[] = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			
			list[x].add(y);
			list[y].add(x);
		}
		
		bfs(a);
		
		if(arr[b] == 0)
			System.out.println(-1);
		else
			System.out.println(arr[b]);
	}
	
	public static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(v);
		visited[v] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			if(x == b)
				break;
			
			for(int i:list[x]) {
				if(!visited[i]) {
					arr[i] = arr[x]+1;
					visited[i] = true;
					q.add(i);
				}
			}
		}
	}

}
