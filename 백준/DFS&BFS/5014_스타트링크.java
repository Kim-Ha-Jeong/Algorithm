import java.io.*;
import java.util.*;

public class BOJ_5014 {
	static int f,s,g,u,d;
	static int arr[] = new int[1000001];
	static int stoi(String ss) {
		return Integer.parseInt(ss);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		f = stoi(str[0]);
		s = stoi(str[1]);
		g = stoi(str[2]);
		u = stoi(str[3]);
		d = stoi(str[4]);
		
		if(s == g) {
			System.out.println(0);
			return;
		}
		bfs();
		
		if(arr[g] == 0)
			System.out.println("use the stairs");
		else
			System.out.println(arr[g]-1);
		
	}
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();

		arr[s] = 1;
		q.add(s);
		
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			if(tmp == g) 
				break;
			
			for(int i=0; i<2; i++) {
				int next;
				if(i == 0) 
					next = tmp + u;
				else 
					next = tmp - d;
				
				if(next<= f && next >= 1 && arr[next]== 0) {
					arr[next] = arr[tmp] + 1;
					q.add(next);
				}
				
			}
		}
		
	}

}
