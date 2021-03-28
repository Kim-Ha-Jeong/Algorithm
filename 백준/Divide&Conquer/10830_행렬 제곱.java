import java.io.*;

public class BOJ_10830 {
	static int arr[][];
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String st[] = br.readLine().split(" ");
		n = Integer.parseInt(st[0]);
		long b = Long.parseLong(st[1]);
		arr = new int[n][n];
		int result[][] = new int[n][n];

		for (int i = 0; i < n; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 0; j < n; j++)
				arr[i][j] = Integer.parseInt(s[j]);
		}

		result = solve(b);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++)
				bw.write(result[i][j]%1000+" "); //arr 1¹ø °öÇÒ ¶§ 
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();

	}

	static int[][] mulMatrix(int x[][], int y[][]) {
		int tmp[][] = new int[n][n];
		

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int sum = 0;
				for (int k = 0; k < n; k++)
					sum += x[i][k] * y[k][j];
				tmp[i][j] = sum%1000;
			}
		}

		return tmp;
	}

	static int[][] solve(long b) {
		int temp[][] = new int[n][n];

		if (b == 1)
			return arr;
		else if (b % 2 == 0) {
			temp = solve(b / 2);
			return mulMatrix(temp, temp);
		} else {
			temp = solve(b - 1);
			return mulMatrix(temp, arr);
		}

	}

}
