import java.util.Scanner;

public class BOJ_2798 {
	static int N,M, arr[];
	static boolean result[];
	static int ret = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N];
		result = new boolean[N];

		for(int i=0; i<N; i++)
			arr[i] = sc.nextInt();
		
		dfs(0,0);
		
		System.out.println(ret);
	}
	
	public static void dfs(int depth, int start) {
		if(depth == 3) {
			ret = Math.max(ret, check());
			return;
		}
		
		for(int i=start; i<N; i++) {
			result[i] = true;
			dfs(depth+1, i+1);
			result[i] = false;
		}
	}
	
	public static int check() {
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			if(result[i])
				sum+=arr[i];
		}
		
		if(sum > M)
			return -1;
		else
			return sum;
	}
}
