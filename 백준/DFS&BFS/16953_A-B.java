import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Bruteforce_8 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		long A = Long.parseLong(s[0]);
		long B = Long.parseLong(s[1]);
		
		System.out.println(bfs(A,B));
	}

	static int bfs(long A, long B) {
		Queue<Cal> q = new LinkedList<>();
		q.add(new Cal(A,0));
		
		while(!q.isEmpty()) {
			Cal cur = q.poll();
			
			if(cur.num == B) 
				return cur.cnt+1;
			
			if(cur.num*2 <= B) {
				q.add(new Cal(cur.num*2, cur.cnt+1));
			}
			if(cur.num*10+1 <= B) {
				q.add(new Cal(cur.num*10+1, cur.cnt+1));
			}
		}
		
		return -1;
	}
	
	static class Cal{
		long num;
		int cnt;
		Cal(long num, int cnt){
			this.num = num;
			this.cnt = cnt;
		}
	}
}
