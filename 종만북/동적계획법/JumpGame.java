import java.util.*;

public class DP_EX_1 {
	static int n;
	static int board[][] = new int[100][100];
	static int cache[][] = new int[100][100];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		while(tc > 0) {
			n = sc.nextInt();
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					board[i][j] = sc.nextInt();
					cache[i][j] = -1;
				}
			}
			
			if(jump(0,0) == 1)
				System.out.println("YES");
			else
				System.out.println("NO");
			
			tc--;
		}
	}
	
	static int jump(int y, int x) {
		if(y>=n || x>=n) return 0;
		if(y == n-1 && x == n-1) return 1;
		
		if(cache[y][x] != -1)
			return cache[y][x];
		
		int jumpSize = board[y][x];
		
		return cache[y][x] = jump(y+jumpSize, x) | jump(y, x+jumpSize);
	}

}
