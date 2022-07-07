import java.util.*;
import java.io.*;

public class BOJ_6603 {
    static int n;
    static int[] arr;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        StringTokenizer st;

        while (true) {
            String s = br.readLine();
            if (s.equals("0"))
                break;

            st = new StringTokenizer(s);

            n = Integer.parseInt(st.nextToken());
            arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            dfs(0, "", 0);
            sb.append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, String str, int idx) {
        if (depth == 6) {
            sb.append(str).append("\n");
            return;
        }

        for (int i = idx; i < n; i++) {
            dfs(depth + 1, str + arr[i] + " ", i + 1);
        }

    }

}
