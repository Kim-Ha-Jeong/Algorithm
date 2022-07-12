import java.io.*;

class BOJ_9663 {
    static int n, ans = 0;
    static int[] arr;
    static boolean[] col;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        col = new boolean[n];

        dfs(0);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth) {
        if (depth == n) {
            ans++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!check(depth, i))
                continue;
            col[i] = true;
            arr[depth] = i;
            dfs(depth + 1);
            col[i] = false;
        }
    }

    static boolean check(int r, int c) {
        if (col[c])
            return false;
        for (int i = 0; i < r; i++) {
            if (Math.abs(i - r) == Math.abs(arr[i] - c))
                return false;
        }

        return true;
    }
}