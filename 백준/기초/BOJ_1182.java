import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BOJ_1182 {
    static boolean[] visited;
    static int[] arr;
    static int ans = 0;
    static int n, sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] s = br.readLine().split(" ");

        n = Integer.parseInt(s[0]);
        sum = Integer.parseInt(s[1]);

        arr = new int[n];
        visited = new boolean[n];

        s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        dfs(0, 0);

        if (sum == 0)
            sb.append(ans - 1);
        else
            sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int total) {
        if (depth == n) {
            if (sum == total)
                ans++;
            return;
        }

        dfs(depth + 1, total + arr[depth]);
        dfs(depth + 1, total);
    }
}
