import java.util.*;
import java.io.*;

public class BOJ_14889 {
    static int n, ans = Integer.MAX_VALUE;
    static int[][] S;
    static boolean[] visited;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        S = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int idx) {
        if (depth == n / 2) {
            ans = Math.min(ans, cal());
            return;
        }

        for (int i = idx + 1; i < n + 1; i++) {
            visited[i] = true;
            combination(depth + 1, i);
            visited[i] = false;
        }
    }

    static int cal() {
        int start = 0, link = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j)
                    continue;
                if (visited[i] && visited[j]) {
                    start += S[i][j];
                }
                if (!visited[i] && !visited[j]) {
                    link += S[i][j];
                }
            }
        }

        return Math.abs(link - start);
    }

}
