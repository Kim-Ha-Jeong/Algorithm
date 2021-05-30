import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Hash_1 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		HashMap<Integer, ArrayList<Integer>> xMap = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> yMap = new HashMap<>();
		
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			
			ArrayList<Integer> ylist = new ArrayList<>();
			ArrayList<Integer> xlist = new ArrayList<>();
			
			if(xMap.containsKey(x)) {
				ylist = xMap.get(x);
			}
			ylist.add(y);
			xMap.put(x, ylist);
			
			if(yMap.containsKey(y)) {
				xlist = yMap.get(y);
			}
			xlist.add(x);
			yMap.put(y, xlist);
		}
		
		int count = 0;
		for(int key : xMap.keySet()) {
			if(xMap.get(key).size() >= 2)
				count++;
		}
		
		for(int key : yMap.keySet()) {
			if(yMap.get(key).size() >= 2)
				count++;
		}
		
		System.out.println(count);
	}

}
