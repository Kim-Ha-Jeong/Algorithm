import java.util.*;

public class BOJ_3009 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int arr[][] = new int[3][2];
		int flagX = -1, flagY = -1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++)
				arr[i][j] = sc.nextInt();
		}

		for (int i = 1; i < 3; i++) {
			if (arr[0][0] == arr[i][0])
				flagX = 3 - i;

			if (arr[0][1] == arr[i][1])
				flagY = 3 - i;
		}

		if (flagX == -1)
			System.out.print(arr[0][0] + " ");
		else
			System.out.print(arr[flagX][0] + " ");

		if (flagY == -1)
			System.out.print(arr[0][1] + " ");
		else
			System.out.print(arr[flagY][1] + " ");

		sc.close();

	}

}
