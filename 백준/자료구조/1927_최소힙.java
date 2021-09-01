import java.util.*;
import java.io.*;
public class BOJ_1927 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		ArrayList<Integer> list = new ArrayList<>();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			int tmp = Integer.parseInt(br.readLine());
			
			if(tmp == 0) {
				if(pq.isEmpty()) {
					list.add(0);
				} else {
					list.add(pq.poll());
				}
			} else {
				pq.add(tmp);
			}
		}
		
		for(int i : list) {
			System.out.println(i);
		}
	}

}
