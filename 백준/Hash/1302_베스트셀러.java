import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
public class Hash_7 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		HashMap<String, Integer> map = new HashMap<>();

		for(int i=0; i<n;i ++) {
			String str = br.readLine();
			if(map.containsKey(str))
				map.put(str, map.get(str)+1);
			else
				map.put(str, 1);
		}
		
		int max = 0;
		for(String s : map.keySet()) 
			max = Math.max(max, map.get(s));
		
		ArrayList<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list);
		
		for(String s : list) {
			if(map.get(s) == max) {
				System.out.println(s);
				break;
			}
		}
 	}

}
