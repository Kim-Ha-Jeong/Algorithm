import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class BOJ_9375 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0) {
			HashMap<String, Integer> map = new HashMap<>();
			int n = Integer.parseInt(br.readLine());
			int ret = 1;
			
			for(int i=0; i<n; i++) {
				String[] s = br.readLine().split(" ");
				
				if(map.containsKey(s[1])) {
					map.put(s[1], map.get(s[1])+1);
				} else {
					map.put(s[1], 2);
				}
			}
			
			for(String key :map.keySet()) {
				ret *= map.get(key);
			}
			
			sb.append(Integer.toString(ret-1));
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
