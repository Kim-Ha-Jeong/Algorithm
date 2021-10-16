import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_13549 {
	static int start, end;
	static int ans = Integer.MAX_VALUE;
	static int max = 100000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		start = Integer.parseInt(s[0]);
		end = Integer.parseInt(s[1]);
		
		bfs();
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] v = new boolean[100001];
		
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int num = cur.num;
			
			v[num] = true;
			
			if(num == end) ans = Math.min(ans,cur.val); 
			
			if(num*2 <= max && !v[num*2]) pq.add(new Node(num*2, cur.val));
			if(num+1 <= max && !v[num+1]) pq.add(new Node(num+1, cur.val+1));
			if(num-1 >= 0 && !v[num-1]) pq.add(new Node(num-1, cur.val+1));
		}
	}
	
	static class Node implements Comparable<Node>{
		int num;
		int val;
		Node(int num, int val){
			this.num = num;
			this.val = val;
		}
		
		@Override
        public int compareTo(Node o) {
            return val - o.val;
        }
	}

}
