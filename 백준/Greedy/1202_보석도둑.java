import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.io.InputStreamReader;

public class BOJ_1202 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		
		int[][] jewelry = new int[n][2];
		int[] backpack = new int[k];
		
		for(int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			jewelry[i][0] = Integer.parseInt(s[0]);
			jewelry[i][1] = Integer.parseInt(s[1]);
		}
		
		for(int i=0; i<k; i++) {
			backpack[i] = Integer.parseInt(br.readLine().trim());
		}
		
		Arrays.sort(jewelry, new Comparator<int[]>() {
			@Override
			public int compare(int o1[], int o2[]) {
				if(o1[0] == o2[0]) return o2[1] - o1[1];
				return o1[0] - o2[0];
			}
		});
		
		Arrays.sort(backpack);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		long cnt = 0;
		int idx = 0;
		
		for(int i=0; i<k; i++) {
			while(idx < n && jewelry[idx][0] <= backpack[i]) {
				pq.add((-1)*jewelry[idx][1]);
				idx++;
			}
			
			if(!pq.isEmpty()) {
				cnt += Math.abs(pq.poll());
			}
		}
		
		System.out.println(cnt);
	}

}
