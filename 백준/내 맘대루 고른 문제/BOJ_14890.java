import java.util.*;
import java.io.*;

public class BOJ_14890 {
    static int[][] map;
    static int L, n, cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            int[] tmp = new int[n];
            int[] tmp2 = new int[n];
            for (int j = 0; j < n; j++) {
                tmp[j] = map[i][j];
                tmp2[j] = map[j][i];
            }
            solve(tmp);
            solve(tmp2);
        }

        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();
    }

    static void solve(int[] arr) {
        int[] dp = new int[n];
        boolean[] visited = new boolean[n];

        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1]) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }

        boolean flag = true;
        for (int i = 1; i < n; i++) {
            if (dp[i] != 1)
                continue;

            if (Math.abs(arr[i] - arr[i - 1]) != 1) {
                flag = false;
                break;
            }

            if (arr[i] > arr[i - 1]) {
                for (int j = i - 1; j >= i - L; j--) {
                    if (i - L < 0 || visited[j] || arr[i - 1] != arr[j]) {
                        flag = false;
                        break;
                    }
                    visited[j] = true;
                }
                if (!flag)
                    break;
            } else if (arr[i] < arr[i - 1]) {
                for (int j = i; j < i + L; j++) {
                    if (i + L - 1 >= n || visited[j] || arr[i] != arr[j]) {
                        flag = false;
                        break;
                    }
                    visited[j] = true;
                }
                if (!flag)
                    break;
            }
        }

        if (flag)
            cnt++;
    }

}
