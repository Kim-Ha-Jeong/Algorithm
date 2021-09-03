import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Collections;

public class BOJ_1655 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> s = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> b = new PriorityQueue<>();
		
		for(int i=0; i<n; i++) {
			if(s.size() == b.size()) {
				s.add(Integer.parseInt(br.readLine()));
			} else {
				b.add(Integer.parseInt(br.readLine()));
			}
			
			if(!s.isEmpty() && !b.isEmpty()) {
				if(b.peek() < s.peek()) {
					int tmp = b.poll();
					b.add(s.poll());
					s.add(tmp);
				}
			}
			
			sb.append(Integer.toString(s.peek()));
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
