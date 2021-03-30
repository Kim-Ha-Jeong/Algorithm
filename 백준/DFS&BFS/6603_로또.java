import java.util.*;

public class BOJ_6603 {
	static int count = 0;
	static int k;
	static int arr[]; 
	static boolean result[];
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			k = sc.nextInt();
			
			if(k == 0)
				break;
			
			arr = new int[k];
			result = new boolean[k];
			
			for(int i=0; i<k; i++)
				arr[i] = sc.nextInt();
			
			dfs(0, 0);
			System.out.println();
		}
		
	}

	public static void dfs(int depth, int start) {
		if(depth == 6) {
			for(int i=0; i<k; i++) {
				if(result[i])
					System.out.print(arr[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=start; i<k; i++) {
			result[i] = true;
			dfs(depth+1, i+1);
			result[i] = false;
		}
		
	}
}
