import java.util.*;

public class BOJ_1436 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int count = 1;
		int result = 666;
		String s;
		
		while(true) {
			s = result+"";

			if(count == n && s.contains("666"))
				break;
			
			if(s.contains("666")) 
				count++;
			
			result++;
		}
		
		System.out.println(result);
	}

}
