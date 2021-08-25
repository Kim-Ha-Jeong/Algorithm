import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_1744 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int zero = -1;
		Integer[] arr = new Integer[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] == 0) zero = 1;
		}
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		ArrayList<Integer> plus = new ArrayList<>();
		ArrayList<Integer> minus = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			if(arr[i] < 0) {
				minus.add(arr[i]);
				arr[i] = 0;
			}
			
			plus.add(arr[i]);
		}
		
		plus.removeAll(Arrays.asList(Integer.valueOf(0)));
		
		Collections.sort(minus);
		
		int pSize = plus.size();
		int mSize = minus.size();
		int sum = 0;
		
		for(int i=0; i<pSize/2; i++) {
			int a = plus.remove(0);
			int b = plus.remove(0);
			if(a == 1 || b == 1) {
				sum += (a+b);
			} else {
				sum += (a*b);
			}
		}
		
		if(pSize % 2 != 0) {
			sum += plus.remove(0);
		}
		
		for(int i=0; i<mSize/2; i++) {
			int a = minus.remove(0);
			int b = minus.remove(0);
			sum += (a*b);
		}
		
		if(zero == -1 && mSize % 2 != 0) {
			sum += minus.remove(0);
		}
		
		System.out.println(sum);
	}

}
