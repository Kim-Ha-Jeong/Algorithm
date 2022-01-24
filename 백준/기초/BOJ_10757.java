import java.util.*;
import java.math.*;

public class BOJ_10757 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String AA = sc.next();
		String BB = sc.next();
		BigInteger A = new BigInteger(AA);
		BigInteger B = new BigInteger(BB);

		System.out.println(A.add(B));

		sc.close();
	}

}
