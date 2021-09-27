import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class BOJ_1057 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		
		int kim = Integer.parseInt(s[1]);
		int lim = Integer.parseInt(s[2]);
		
		int cnt = 1;
		while(true) {
			if(Math.abs(kim-lim) == 1 && Math.max(kim, lim) % 2 == 0)
				break;
			
			kim = kim % 2 == 1 ? kim/2+1 : kim/2;
			lim = lim % 2 == 1 ? lim/2+1 : lim/2;
			cnt++;
		}
		
		bw.write(cnt+"");
		bw.flush();
		bw.close();
	}
	

}
