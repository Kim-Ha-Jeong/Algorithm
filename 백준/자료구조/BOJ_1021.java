import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class BOJ_1021 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		
		int[] target = new int[m];
		
		s = br.readLine().split(" ");
		for(int i=0; i<m; i++) {
			target[i] = Integer.parseInt(s[i]);
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i=1; i<=n; i++) {
			list.add(i);
		}
		
		int sum = 0;
		int idx = 0;
		while(list.size() != 0) {
			if(idx >= m) break;
			if(list.get(0) == target[idx]) {
				list.remove(0);
				idx++;
			} else {
				int len = list.size();
				int index = list.indexOf(target[idx]);
				if(index > len/2) {
					for(int i=len-1; i>=index; i--) {
						int tmp = list.remove(list.size()-1);
						list.add(0, tmp);
						sum++;
					}
				} else {
					for(int i=0; i<index; i++) {
						int tmp = list.remove(0);
						list.add(len-1, tmp);
						sum++;
					}
				}
			}
		}
		
		System.out.println(sum);
		
	}

}
