import java.io.*;
import java.util.*;

public class BOJ_2529 {
    static int k;
    static char[] inequality;
    static int[] arr;
    static boolean[] v;
    static long max = Long.MIN_VALUE, min = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        k = Integer.parseInt(br.readLine());
        inequality = new char[k];
        arr = new int[k + 1];
        v = new boolean[10];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            inequality[i] = st.nextToken().charAt(0);
        }

        dfs(0, "");

        String Max = String.valueOf(max);
        String Min = String.valueOf(min);

        if (Max.length() != Min.length()) {
            Min = "0" + Min;
        }

        bw.write(sb.append(Max).append("\n").append(Min).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, String str) {
        if (depth == k + 1) {
            long result = Long.parseLong(str);
            max = Math.max(result, max);
            min = Math.min(result, min);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (v[i])
                continue;
            if (depth == 0 || (inequality[depth - 1] == '<' && arr[depth - 1] < i)
                    || (inequality[depth - 1] == '>' && arr[depth - 1] > i)) {
                v[i] = true;
                arr[depth] = i;
                dfs(depth + 1, str + i);
                v[i] = false;
            }
        }
    }

}
