import java.util.*;
import java.io.*;

public class BOJ_16926 {
    static int n, m, k;
    static int[][] map;
    static boolean flag = true;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            int fx = 0, fy = 0, lx = n - 1, ly = m - 1;
            flag = true;
            while (flag) {
                func(fx, fy, lx, ly);
                fx++;
                fy++;
                lx--;
                ly--;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void func(int fx, int fy, int lx, int ly) {
        if (fx >= lx || fy >= ly) {
            flag = false;
            return;
        }

        int tmp = map[fx][fy];

        for (int i = fy; i < ly; i++) {
            map[fx][i] = map[fx][i + 1];
        }

        for (int i = fx; i < lx; i++) {
            map[i][ly] = map[i + 1][ly];
        }

        for (int i = ly - 1; i >= fy; i--) {
            map[lx][i + 1] = map[lx][i];
        }

        for (int i = lx - 1; i >= fx + 1; i--) {
            map[i + 1][fy] = map[i][fy];
        }

        map[fx + 1][fy] = tmp;
    }

}
