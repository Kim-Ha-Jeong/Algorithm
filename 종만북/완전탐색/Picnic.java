import java.util.*;

public class Picnic {
	static int c,n,m;
	static boolean friends[][] = new boolean[10][10];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		c = sc.nextInt();
		int result[] = new int[c];
		
		for(int i=0; i<c; i++) {
			n = sc.nextInt();
			m = sc.nextInt();
			
			boolean taken[] = new boolean[n];
			
			for(int j=0; j<m; j++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				friends[x][y] = true;
				friends[y][x] = true;
			}
			result[i] = count(taken);
		}
		
		for(int i=0; i<c; i++)
			System.out.println(result[i]);
	
		sc.close();
	}
	
	public static int count(boolean taken[]) {
		int pairOne = -1;
		
		for(int i=0; i<n; i++) {
			if(!taken[i]) {
				pairOne = i;
				break;
			}
		}
		
		if(pairOne == -1)
			return 1;
		
		int ret = 0;
		
		for(int pairTwo=pairOne+1; pairTwo<n; pairTwo++) {
			if(!taken[pairTwo] && friends[pairOne][pairTwo] == true) {
				taken[pairTwo] = true;
				taken[pairOne] = true;
				ret += count(taken);
				taken[pairTwo] = false;
				taken[pairOne] = false;
			}
		}
		return ret;
	}

}
