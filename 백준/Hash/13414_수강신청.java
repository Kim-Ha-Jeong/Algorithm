import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashSet;

public class Hash_9 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		int k = Integer.parseInt(s[0]);
		int l = Integer.parseInt(s[1]);
		
		LinkedHashSet<String> set = new LinkedHashSet<>();
		for(int i=0; i<l; i++) {
			String num = br.readLine();
			if(set.contains(num)) set.remove(num);
			set.add(num);
		}
		
		for(String ret : set) {
			if(k <= 0)
				break;
			bw.write(ret);
			bw.write("\n");
			k--;
		}
		
		bw.flush();
		bw.close();
	}
}
