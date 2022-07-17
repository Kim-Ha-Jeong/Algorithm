import java.util.*;
import java.io.*;

public class BOJ_16928 {
    static int[] map = new int[101];
    static int[] dist = new int[101];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a] = b;
        }

        Arrays.fill(dist, -1);
        bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        dist[1] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 1; i <= 6; i++) {
                int next = now + i;

                if (next > 100)
                    continue;

                if (next == 100) {
                    ans = Math.min(ans, dist[now] + 1);
                    continue; // break도 가능
                }

                while (map[next] != 0) {
                    next = map[next];
                }

                if (dist[next] == -1 || dist[next] > dist[now] + 1) {
                    dist[next] = dist[now] + 1;
                    q.add(next);
                }
            }
        }
    }

}
