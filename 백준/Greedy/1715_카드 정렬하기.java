import java.io.*;
import java.util.*;
public class Greedy_16 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for(int i=0; i<n; i++)
			pq.add(Integer.parseInt(br.readLine()));
		
		int sum = 0;
		while(pq.size() > 1){
			int a = pq.poll();
			int b = pq.poll();
			
			sum += (a+b);
			pq.add(a+b);
		}
		
		System.out.println(sum);
	}

}
