import java.util.*;
import java.io.*;

public class BOJ_17089 {
    static int n, m, ans = Integer.MAX_VALUE;
    static ArrayList<Integer>[] friend;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        friend = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            friend[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friend[a].add(b);
            friend[b].add(a);
        }

        int[] tmp = new int[3];
        combination(0, 1, tmp);

        ans = ans == Integer.MAX_VALUE ? -1 : ans;

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int idx, int[] choice) {
        if (depth == 3) {
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                int tmp = choice[i];
                sum += friend[tmp].size();
            }
            ans = Math.min(ans, sum - 6);
            return;
        }

        for (int i = idx; i < n + 1; i++) {
            if (depth != 0) {
                boolean flag = true;
                for (int j = 0; j < depth; j++) {
                    if (!friend[i].contains(choice[j])) {
                        flag = false;
                        break;
                    }
                }
                if (!flag)
                    continue;
            }
            choice[depth] = i;
            combination(depth + 1, i + 1, choice);
        }
    }
}
