import java.util.*;
import java.io.*;

public class BOJ_2458 {
    static int n, m;
    static boolean[][] connect;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        connect = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            connect[a][b] = true;
        }

        for (int mid = 1; mid < n + 1; mid++) {
            for (int start = 1; start < n + 1; start++) {
                for (int end = 1; end < n + 1; end++) {
                    if (connect[start][mid] && connect[mid][end]) {
                        connect[start][end] = true;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            int cnt = 0;
            for (int j = 1; j < n + 1; j++) {
                if (connect[i][j] || connect[j][i])
                    cnt++;
            }

            if (cnt == n - 1)
                ans++;
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }
}
