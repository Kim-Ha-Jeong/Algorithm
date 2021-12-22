import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class BOJ_2529 {
    static int n;
    static int[] order;
    static boolean[] visited;
    static String[] sign;
    static long max = -1;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        sign = br.readLine().split(" ");
        order = new int[n + 1];
        visited = new boolean[10];

        dfs(0, 0);

        sb.append(max).append("\n");
        if ((int) (min / Math.pow(10, n)) == 0) {
            sb.append(0);
        }
        sb.append(min);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int idx) {
        if (depth == n + 1) {
            long sum = 0;
            for (int i = 0; i < n + 1; i++) {
                sum += order[i] * Math.pow(10, n - i);
            }

            min = Math.min(sum, min);
            max = Math.max(sum, max);

            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {
                if (depth != 0) {
                    if ((order[depth - 1] < i && sign[idx].equals("<"))
                            || (order[depth - 1] > i && sign[idx].equals(">"))) {
                        order[depth] = i;
                        visited[i] = true;
                        dfs(depth + 1, idx + 1);
                        visited[i] = false;
                    }
                } else {
                    order[depth] = i;
                    visited[i] = true;
                    dfs(depth + 1, idx);
                    visited[i] = false;
                }
            }
        }
    }
}
