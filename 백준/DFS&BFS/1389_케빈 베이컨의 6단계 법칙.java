import java.util.*;
import java.io.*;

public class BOJ_1389 {
	static int n, m;
	static ArrayList<Integer> list[];
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st[] = br.readLine().split(" ");
		
		n = Integer.parseInt(st[0]);
		m = Integer.parseInt(st[1]);
		int flag = -1;
		
		list = new ArrayList[n+1];
		
		for(int i=1; i<n+1; i++)
			list[i] = new ArrayList<Integer>();
		
		for(int i=0; i<m; i++) {
			String s[] = br.readLine().split(" ");
			int v = Integer.parseInt(s[0]);
			int w = Integer.parseInt(s[1]);
			
			list[v].add(w);
			list[w].add(v);
		}
		
		for(int i=1; i<=n; i++) {
			int result = bfs(i);
			if(min > result) {
				min = result;
				flag = i;
			}
		}
		
		System.out.println(flag);
	}
	
	static int bfs(int num) {
		boolean visited[] = new boolean[n+1];
		int sum = 0;
		Queue<Person> q = new LinkedList<>();
		q.add(new Person(num,0));
		visited[num] = true;
		
		while(!q.isEmpty()) {
			Person now = q.poll();
			for(int i : list[now.n]) {
				if(!visited[i]) {
					visited[i] = true;
					sum += now.cnt;
					q.add(new Person(i, now.cnt+1));
				}
			}
		}
		
		return sum;
	}

}

class Person{
	int n;
	int cnt;
	Person(int n,int cnt){
		this.n = n;
		this.cnt = cnt;
	}
}
