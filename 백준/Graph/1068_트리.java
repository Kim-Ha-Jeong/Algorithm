import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Graph_12 {
	static int n, target;
	static int start;
	static int cnt = 0;
	static ArrayList<Integer>[] list = new ArrayList[51];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<51; i++) {
			list[i] = new ArrayList<>(); 
		}
		
		String[] s = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			int val = Integer.parseInt(s[i]);
			if(val != -1) {
				list[val].add(i);
			} else {
				start = i;
			}
		}
		
		target = Integer.parseInt(br.readLine());
		delete();
		
		if(target != start) {
			search(start);
			System.out.println(cnt);
		} else {
			System.out.println(0);
		}
	}
	
	static void delete() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<list[i].size(); j++) {
				if(list[i].get(j) == target)
					list[i].remove(j);
			}
		}
	}
	
	static void search(int index) {
		if(list[index].size() == 0) {
			cnt++;
			return;
		}
		
		for(int i=0; i<list[index].size(); i++) {
			int next = list[index].get(i);
			if(list[index].size() == 1 && next == target) {
				cnt++;
				return;
			}
			
			if(next < n && next != target) {
				search(next);
			}
		}
	}

}
