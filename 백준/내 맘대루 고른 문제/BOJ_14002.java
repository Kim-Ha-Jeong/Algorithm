import java.util.*;
import java.io.*;

public class BOJ_14002 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] parent = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
        }

        int max = -1;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (max < dp[i]) {
                max = dp[i];
                idx = i;
            }
        }

        sb.append(max).append("\n");

        Stack<Integer> stack = new Stack<>();
        while (idx != -1) {
            stack.add(idx);
            idx = parent[idx];
        }

        while (!stack.isEmpty()) {
            sb.append(arr[stack.pop()]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
