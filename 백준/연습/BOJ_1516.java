import java.util.*;
import java.io.*;

public class BOJ_1516 {
    static int n;
    static ArrayList<Integer>[] list;
    static int[] time;
    static int[] arrow;
    static int[] sum;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        arrow = new int[n + 1];
        time = new int[n + 1];
        sum = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());

                if (x == -1)
                    break;
                list[x].add(i);
                arrow[i]++;
            }
        }

        solve();

        for (int i = 1; i < n + 1; i++) {
            sb.append(sum[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < n + 1; i++) {
            if (arrow[i] == 0) {
                sum[i] = time[i];
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : list[now]) {
                sum[next] = Math.max(sum[next], sum[now] + time[next]);
                arrow[next]--;
                if (arrow[next] == 0) {
                    q.add(next);
                }
            }
        }
    }

}
