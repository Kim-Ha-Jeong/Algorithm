import java.io.*;
import java.util.*;

public class BOJ_10819 {
    static int[] arr;
    static boolean[] visited;
    static int[] result;
    static int n;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        visited = new boolean[n];
        result = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth) {
        if (depth == n) {
            max = Math.max(max, cal());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    static int cal() {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(result[i] - result[i + 1]);
        }
        return sum;
    }
}
