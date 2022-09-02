import java.util.*;
import java.io.*;

public class BOJ_16964 {
    static int n, idx = 1;
    static ArrayList<Integer> list[];
    static int[] ans;
    static boolean[] v;
    static boolean flag = true;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        ans = new int[n];
        v = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ans[i] = Integer.parseInt(st.nextToken());
        }

        if (ans[0] != 1) {
            flag = false;
        } else {
            dfs(ans[0]);
        }

        System.out.println(flag ? 1 : 0);
    }

    static void dfs(int now) {
        if (v[now])
            return;

        v[now] = true;

        ArrayList<Integer> tmp = new ArrayList<>();
        for (int next : list[now]) {
            if (v[next])
                continue;
            tmp.add(next);
        }

        if (tmp.size() == 0)
            return;

        if (tmp.contains(ans[idx])) {
            dfs(ans[idx++]);
        } else {
            flag = false;
        }

    }

}
