import java.util.*;

public class Baseball {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		int guessCount = 0;

		String num[] = new String[count];
		int SB[][] = new int[count][2];

		for (int i = 0; i < count; i++) {
			int nn = sc.nextInt();
			num[i] = Integer.toString(nn);
			SB[i][0] = sc.nextInt();
			SB[i][1] = sc.nextInt();
		}

		for (int i = 123; i <= 987; i++) {
			String guess = Integer.toString(i);
			int a = i / 100;
			int b = (i % 100) / 10;
			int c = (i % 10);
			int flag = 0;

			if (a == b || a == c || b == c)
				continue;
			if (a == 0 || b == 0 || c == 0)
				continue;

			for (int j = 0; j < count; j++) {
				if (StrikeBall(guess, num[j], SB[j]))
					flag++;
				else
					break;
			}

			if (flag == count)
				guessCount++;
		}

		System.out.println(guessCount);

	}

	public static boolean StrikeBall(String guess, String num, int SB[]) {
		int strike = 0, ball = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (guess.charAt(i) == num.charAt(j)) {
					if (i == j)
						strike++;
					else
						ball++;
				}
			}
		}

		if (SB[0] == strike && SB[1] == ball)
			return true;
		else
			return false;
	}
}
