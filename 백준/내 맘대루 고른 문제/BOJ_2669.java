import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2669 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[100][100];
		
		int tc = 4;
		int sum = 0;
		
		while(tc-->0) {
			String[] s = br.readLine().split(" ");
			
			int lx = Integer.parseInt(s[0]);
			int ly = Integer.parseInt(s[1]);
			int rx = Integer.parseInt(s[2]);
			int ry = Integer.parseInt(s[3]);
			
			for(int i = lx; i<rx; i++) {
				for(int j=ly; j<ry; j++) {
					if(map[i][j] == 0) {
						sum++;
						map[i][j] = 1;
					}
				}
			}
		}
		
		System.out.println(sum);
	}

}
