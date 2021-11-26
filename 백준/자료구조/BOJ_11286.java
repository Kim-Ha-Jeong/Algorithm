import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class BOJ_11286 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Abs> pq = new PriorityQueue<>();
		
		while(n-->0) {
			int tmp = Integer.parseInt(br.readLine());
			
			if(tmp != 0) {
				pq.add(new Abs(Math.abs(tmp), tmp));
			} else {
				if(pq.isEmpty()) {
					sb.append("0");
				} else {
					sb.append(Integer.toString(pq.poll().origin));
				}
				sb.append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static class Abs implements Comparable<Abs>{
		int abs;
		int origin;
		Abs(int abs, int origin){
			this.abs = abs;
			this.origin = origin;
		}
		
		@Override
		public int compareTo(Abs o) {
			if(this.abs == o.abs)
				return this.origin - o.origin;
			return this.abs - o.abs;
		}
		
	}

}
