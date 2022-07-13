import java.util.*;
import java.io.*;

public class BOJ_1062 {
    static int n, k;
    static String[] words;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];
        visited = new boolean[26];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            words[i] = str.substring(4, str.length() - 4);
        }

        if (k <= 4) {
            sb.append(0);
        } else if (k == 26) {
            sb.append(n);
        } else {
            visited['a' - 'a'] = true;
            visited['n' - 'a'] = true;
            visited['t' - 'a'] = true;
            visited['i' - 'a'] = true;
            visited['c' - 'a'] = true;

            if (k == 5) {
                ans = Math.max(ans, cal());
            } else {
                dfs(0, 0);
            }
            sb.append(ans);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int idx) {
        if (depth == k - 5) {
            ans = Math.max(ans, cal());
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            dfs(depth + 1, i + 1);
            visited[i] = false;
        }
    }

    static int cal() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!visited[c - 'a']) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                sum++;
        }
        return sum;
    }

}
