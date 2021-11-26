import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class BOJ_3036 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		
		int firstRing = Integer.parseInt(s[0]);
		int[] ring = new int[n-1];
		
		for(int i=1; i<n; i++) {
			ring[i-1] = Integer.parseInt(s[i]);
		}
		
		for(int i=0; i<n-1; i++) {
			int a = firstRing;
			int b = ring[i];
			
			while(a % b != 0) {
				int tmp = a%b;
				a = b;
				b = tmp;
			}
			
			sb.append(firstRing/b+"/"+ring[i]/b+"\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
