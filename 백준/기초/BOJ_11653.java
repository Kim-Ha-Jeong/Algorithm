import java.util.*;

public class BOJ_11653 {
	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		for (int i = 2; i <= n; i++) {
			prime(i);
		}

		sc.close();

	}

	public static void prime(int i) {
		while (true) {
			if (n % i != 0)
				break;

			if (n % i == 0) {
				n /= i;
				System.out.println(i);
			}
		}
	}

}
