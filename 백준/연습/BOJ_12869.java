import java.util.*;
import java.io.*;

public class BOJ_12869 {
    static int n;
    static int[] scv;
    static boolean[][][] v;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        v = new boolean[61][61][61];
        scv = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = n; i < 3; i++) {
            scv[i] = 0;
        }

        solve(0, scv[0], scv[1], scv[2]);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void solve(int depth, int hp1, int hp2, int hp3) {
        hp1 = Math.max(0, hp1);
        hp2 = Math.max(0, hp2);
        hp3 = Math.max(0, hp3);

        int max = Math.max(hp1, Math.max(hp2, hp3));
        int min = Math.min(hp1, Math.min(hp2, hp3));

        int mid = hp1 + hp2 + hp3 - max - min;

        hp1 = max;
        hp2 = mid;
        hp3 = min;

        if (hp1 <= 0) {
            ans = Math.min(ans, depth);
            return;
        }

        if (v[hp1][hp2][hp3]) {
            return;
        } else {
            v[hp1][hp2][hp3] = true;
        }

        if (ans < depth)
            return;

        int next = depth + 1;
        solve(next, hp1 - 9, hp2 - 3, hp3 - 1);
        solve(next, hp1 - 9, hp2 - 1, hp3 - 3);
        solve(next, hp1 - 3, hp2 - 9, hp3 - 1);
        solve(next, hp1 - 3, hp2 - 1, hp3 - 9);
        solve(next, hp1 - 1, hp2 - 9, hp3 - 3);
        solve(next, hp1 - 1, hp2 - 3, hp3 - 9);
    }

}
