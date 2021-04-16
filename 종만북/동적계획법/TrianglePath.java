import java.util.Scanner;

public class DP_EX_3 {
	static int n, triangle[][] = new int[100][100];
	static int cache[][] = new int[100][100];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		while(tc > 0) {
			n = sc.nextInt();
			
			int size = 0;
			for(int i=0; i<n; i++) {
				size++;
				for(int j=0; j<size; j++) {
					triangle[i][j] = sc.nextInt();
					cache[i][j] = -1;
				}
			}
			
			System.out.println(path(0,0));
			tc--;
		}
	}
	
	static int path(int y, int x) {
		if(y == n-1) return triangle[y][x];
		
		int ret = cache[y][x];
		if(ret != -1) return ret;
		return ret = Math.max(path(y+1,x), path(y+1, x+1)) + triangle[y][x];
	}

}
