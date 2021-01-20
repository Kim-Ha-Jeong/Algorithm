import java.util.Scanner;

public class BOJ_1002 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int x[] = new int[2];
		int y[] = new int[2];
		int r[] = new int[2];
		int result[] = new int[t];
		int count = 0;

		while (true) {
			if(count >= t)
				break; 
			
			for (int i = 0; i < 2; i++) {
				x[i] = sc.nextInt();
				y[i] = sc.nextInt();
				r[i] = sc.nextInt();
			}
			
			int d = (int)(Math.pow(x[1] - x[0],2)+Math.pow(y[1]-y[0], 2));
			int sum = r[0] + r[1];
			int sub = r[0] - r[1];
			
			if(x[0] == x[1] && y[0] == y[1] && r[0] == r[1])
				result[count] = -1;
			else if(d > Math.pow(sum,2) || d < Math.pow(sub,2))
				result[count] = 0;
			else if(d == Math.pow(sum, 2) || d == Math.pow(sub,2))
				result[count] = 1;
			else if(d< Math.pow(sum, 2) && d> Math.pow(sub,2))
				result[count] = 2;
			
			count++;
		}
		
		for(int i=0; i<t; i++) 
			System.out.println(result[i]);
	}

}
