import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

public class Bruteforce_6 {
	static ArrayList<Integer>[] relation;
	static boolean[] visited;
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		visited = new boolean[n+1];
		relation = new ArrayList[n+1];
		
		for(int i=1; i<n+1; i++)
			relation[i] = new ArrayList<>();
		
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			int v = Integer.parseInt(s[0]);
			int w = Integer.parseInt(s[1]);
			relation[v].add(w);
			relation[w].add(v);
		}
		
		int min = Integer.MAX_VALUE;
		int flag = -1;
		for(int i=1; i<n+1; i++) {
			int ret = bfs(i);
			if(min > ret) {
				min = ret;
				flag = i;
			}
			Arrays.fill(visited, false);
		}
		
		System.out.println(flag);
	}
	
	static int bfs(int start) {
		Queue<Person> q = new LinkedList<>();
		q.add(new Person(start,0));
		visited[start] = true;
		int sum = 0;
		
		while(!q.isEmpty()) {
			Person now = q.poll();
			
			for(int connect : relation[now.vertex]) {
				if(!visited[connect]) {
					sum+=(now.cnt+1);
					q.add(new Person(connect,now.cnt+1));
					visited[connect] = true;
				}
			}
			
		}
		
		return sum;
	}
	
	static class Person{
		int vertex;
		int cnt;
		Person(int vertex, int cnt){
			this.vertex = vertex;
			this.cnt = cnt;
		}
	}
}
