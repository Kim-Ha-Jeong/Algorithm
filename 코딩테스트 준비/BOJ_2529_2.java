import java.util.*;
import java.io.*;

public class BOJ_2529_2 {
    static char[] arr;
    static int n;
    static boolean[] visited;
    static boolean flag = false;
    static StringBuilder min, max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new char[n + 1];

        for (int i = 1; i < n + 1; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        int[] order = new int[n + 1];
        visited = new boolean[10];
        dfs(0, -1, order);

        max.append("\n");
        min.append("\n");

        sb.append(max).append(min);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void dfs(int depth, int num, int[] order) {
        if (depth == n + 1) {
            if (!flag) {
                min = new StringBuilder();
                for (int i = 0; i < n + 1; i++) {
                    min.append(order[i]);
                }
                flag = true;
            } else {
                max = new StringBuilder();
                for (int i = 0; i < n + 1; i++) {
                    max.append(order[i]);
                }
            }

            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (((arr[depth] == '<' && num < i) || (arr[depth] == '>' && num > i) || num == -1) && !visited[i]) {
                order[depth] = i;
                visited[i] = true;
                dfs(depth + 1, i, order);
                visited[i] = false;
            }
        }
    }

}
