import java.io.*;
import java.util.*;
public class BOJ_2304 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Wood[] arr = new Wood[N];
		ArrayList<Wood> left = new ArrayList<>();
		ArrayList<Wood> right = new ArrayList<>();
		
		for(int i=0; i< N; i++) {
			String[] s = br.readLine().split(" ");
			arr[i] = new Wood(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		}
		
		Arrays.sort(arr);
		
		int max = -1;
		int sum = 0;
		for(int i=0; i<N; i++) {
			if(max <= arr[i].y) {
				max = arr[i].y;
				left.add(arr[i]);
			}
		}
		
		Wood tmp = left.get(0);
		for(int i=1; i<left.size(); i++) {
			Wood cur = left.get(i);
			sum += Math.abs(cur.x - tmp.x)*tmp.y;
			tmp = cur;
		}
		
		if(!left.contains(arr[arr.length-1])) {
			max = -1;
			for(int i=N-1; i>=0; i--) {
				if(max <= arr[i].y) {
					max = arr[i].y;
					if(!left.contains(arr[i])) {	
						right.add(arr[i]);
					}
				}
			}
			
			tmp = right.get(0);
			sum += Math.abs(left.get(left.size()-1).x-right.get(right.size()-1).x)*right.get(right.size()-1).y;
			for(int i=1; i<right.size(); i++) {
				Wood cur = right.get(i);
				sum += Math.abs(cur.x - tmp.x)*tmp.y;
				tmp = cur;
			}
		}
		
		sum += left.get(left.size()-1).y;
		
		System.out.println(sum);
	}

	static class Wood implements Comparable<Wood>{
		int x;
		int y;
		
		Wood(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Wood w) {
			return this.x - w.x;
		};
		
	}
}
