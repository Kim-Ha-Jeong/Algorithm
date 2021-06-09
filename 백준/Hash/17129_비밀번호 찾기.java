import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class Hash_8 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] s = br.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		
		HashMap<String, String> map = new HashMap<>();
		
		for(int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			map.put(s[0],s[1]);
		}
		
		for(int i=0; i<m; i++) {
			String site = br.readLine();
			if(map.containsKey(site))
				bw.write(map.get(site));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}

}
