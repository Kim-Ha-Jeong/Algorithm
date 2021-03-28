package study;

import java.util.*;
import java.io.*;

public class Virus {
	static ArrayList<Integer> list[];
	static boolean visited[];
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		visited = new boolean[n+1];
		
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++)
			list[i] = new ArrayList<Integer>();
		
		for(int i=0; i<m; i++) {
			String[] str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			
			list[x].add(y);
			list[y].add(x);
		}
		
		for(int i=1; i<n+1; i++)
			Collections.sort(list[i]);
		
		dfs(1);
		
		System.out.println(count-1);
	}
	
	public static void dfs(int v) {
		visited[v] = true;
		count++;
		
		for(int i : list[v]) {
			if(!visited[i])
				dfs(i);
		}
	}

}
