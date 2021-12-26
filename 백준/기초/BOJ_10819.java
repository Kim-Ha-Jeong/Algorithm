import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class BOJ_10819 {
    static int n;
    static boolean[] visited;
    static int[] arr;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];
        String[] s = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        int[] order = new int[n];
        permutation(0, order);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void permutation(int depth, int[] order) {
        if (depth == n) {
            ans = Math.max(sum(order), ans);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = arr[i];
                permutation(depth + 1, order);
                visited[i] = false;
            }
        }
    }

    static int sum(int[] order) {
        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            cnt += Math.abs(order[i] - order[i + 1]);
        }

        return cnt;
    }
}
