import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
public class BOJ_1016 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		
		long min = Long.parseLong(s[0]);
		long max = Long.parseLong(s[1]);
		boolean[] powNum = new boolean[(int)(max-min+1)];
		
		for(long i=2; i*i <= max; i++) {
			long pow = i*i;
			long start = min % pow == 0 ? (min/pow) : (min/pow)+1;
			
			for(long j= start; j*pow<=max; j++) {
				powNum[(int)((j*pow)-min)] = true;
			}
		}
		
		int cnt = 0;
		
		for(int i=0; i<max-min+1; i++) {
			if(!powNum[i]) cnt++;
		}
		
		bw.write(cnt+"");
		bw.flush();
		bw.close();
	}

}
