import java.util.*;
import java.io.*;

public class BOJ_2096 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        int[][] max = new int[n][3];
        int[][] min = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    max[i][j] = min[i][j] = arr[i][j];
                } else {
                    if (j == 0) {
                        max[i][j] = Math.max(max[i - 1][j], max[i - 1][j + 1]) + arr[i][j];
                        min[i][j] = Math.min(min[i - 1][j], min[i - 1][j + 1]) + arr[i][j];
                    } else if (j == 2) {
                        max[i][j] = Math.max(max[i - 1][j], max[i - 1][j - 1]) + arr[i][j];
                        min[i][j] = Math.min(min[i - 1][j], min[i - 1][j - 1]) + arr[i][j];
                    } else {
                        max[i][j] = Math.max(max[i - 1][j], Math.max(max[i - 1][j + 1], max[i - 1][j - 1])) + arr[i][j];
                        min[i][j] = Math.min(min[i - 1][j], Math.min(min[i - 1][j + 1], min[i - 1][j - 1])) + arr[i][j];
                    }
                }
            }
        }

        int MAX = -1;
        int MIN = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            MAX = Math.max(MAX, max[n - 1][i]);
            MIN = Math.min(MIN, min[n - 1][i]);
        }

        sb.append(MAX).append(" ").append(MIN);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
