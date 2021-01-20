import java.util.Scanner;

public class BOJ_10872 {
	static int result = 1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		System.out.println(factorial(n));

	}
	
	static int factorial(int n) {
		if(n == 1 || n == 0)
			return 1;
		return n*factorial(n-1);
	}

}
