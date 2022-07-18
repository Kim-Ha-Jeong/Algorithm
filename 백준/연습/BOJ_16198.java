import java.io.*;
import java.util.*;

public class BOJ_16198 {
    static int n;
    static int[] arr;
    static int ans = 0;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        v = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int sum) {
        if (depth == n - 2) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            if (v[i])
                continue;
            v[i] = true;
            int tmp = find(i - 1, i + 1);
            dfs(depth + 1, sum + tmp);
            v[i] = false;
        }
    }

    static int find(int prev, int next) {
        while (true) {
            if (!v[prev])
                break;
            prev--;
        }

        while (true) {
            if (!v[next])
                break;
            next++;
        }

        return arr[prev] * arr[next];
    }

}
