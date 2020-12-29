package study;

import java.util.*;

public class Eureka {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int order = 0;
		int eureka[] = new int[45];
		int result[] = new int[n];

		while (order < n) {
			int flag = 0;
			int num = sc.nextInt();

			for (int i = 1; i < 45; i++)
				eureka[i] = i * (i + 1) / 2;

			for (int i = 1; i < 45; i++) {
				if (flag == 1)
					break;
				for (int j = 1; j < 45; j++) {
					for (int k = 1; k < 45; k++) {
						if (num == eureka[i] + eureka[j] + eureka[k]) {
							flag = 1;
							break;
						}
					}
				}
			}

			if (flag == 1)
				result[order] = 1;
			else
				result[order] = 0;

			order++;
		}

		for (int i = 0; i < n; i++)
			System.out.println(result[i]);
	}
}
