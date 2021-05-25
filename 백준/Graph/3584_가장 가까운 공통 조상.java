import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Graph_4 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			int N = Integer.parseInt(br.readLine());
			
			parent = new int[N+1];
			String[] s;
			
			for(int i=0; i<N-1; i++) {
				s = br.readLine().split(" ");
				parent[Integer.parseInt(s[1])] = Integer.parseInt(s[0]);
			}
			
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			
			int aDepth = depth(a);
			int bDepth = depth(b);
			
			System.out.println(find(a,b,aDepth,bDepth));
			
		}
		
	}
	
	static int depth(int node) {
		int dep = 0;
		int cur = node;
		
		while(cur != 0) {
			dep++;
			cur = parent[cur];
		}
		
		return dep-1;
	}
	
	static int find(int a, int b, int aDepth, int bDepth) {
		if(aDepth > bDepth) {
			while(aDepth != bDepth) {
				aDepth--;
				a = parent[a];
			}
		} else if(bDepth > aDepth) {
			while(bDepth != aDepth) {
				bDepth--;
				b = parent[b];
			}
		}
		
		while(a!=b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}
}
