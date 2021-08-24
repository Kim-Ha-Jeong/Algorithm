import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1946 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[n][2];
			
			for(int i=0; i<n; i++) {
				String[] s = br.readLine().split(" ");
				arr[i][0] = Integer.parseInt(s[0]);
				arr[i][1] = Integer.parseInt(s[1]);
			}
			
			Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			
			int cnt = 1;
			int min = arr[0][1];
			for(int i=1; i<n; i++) {
				if(min > arr[i][1]) {
					cnt++;
					min = arr[i][1];
				}
			}
			
			bw.write(cnt+"");
			bw.write("\n");
		}
		
		bw.flush();
		
	}

}
