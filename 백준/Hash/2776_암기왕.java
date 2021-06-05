import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class Hash_4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0){
			HashMap<Integer,Boolean> memo = new HashMap<>();
			
			int n = Integer.parseInt(br.readLine());
			String[] s = br.readLine().split(" ");
			
			for(int i=0; i<n; i++) {
				int num = Integer.parseInt(s[i]);
				memo.put(num, true);
			}
			
			int m = Integer.parseInt(br.readLine());
			s = br.readLine().split(" ");
						
			for(int i=0; i<m; i++) {
				int num = Integer.parseInt(s[i]);
				if(memo.containsKey(num))
					bw.write("1\n");
				else
					bw.write("0\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	

}
