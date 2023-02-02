import java.io.*;
import java.util.*;

public class BOJ_14890 {
    static int n, l, ans = 0;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            if (solve(map[i]))
                ans++;
        }

        for (int i = 0; i < n; i++) {
            int[] tmp = new int[n];
            for (int j = 0; j < n; j++) {
                tmp[j] = map[j][i];
            }
            if (solve(tmp))
                ans++;
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static boolean solve(int[] arr) {
        boolean flag = true;
        boolean[] v = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                if (Math.abs(arr[i] - arr[i + 1]) != 1) {
                    return false;
                }
                if (arr[i] < arr[i + 1]) {
                    int num = arr[i];
                    if (i - l < -1)
                        return false;

                    for (int k = i; k > i - l; k--) {
                        if (num != arr[k] || v[k]) {
                            return false;
                        } else {
                            v[k] = true;
                        }
                    }
                } else {
                    int num = arr[i + 1];
                    if (i + l + 1 >= n + 1)
                        return false;
                    for (int k = i + 1; k < i + l + 1; k++) {
                        if (num != arr[k] || v[k]) {
                            return false;
                        } else {
                            v[k] = true;
                        }
                    }
                }
            }
        }

        return flag;
    }
}
