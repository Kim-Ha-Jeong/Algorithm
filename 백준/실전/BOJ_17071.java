import java.util.*;
import java.io.*;

public class BOJ_17071 {
    static int n, k, ans = -1;
    static int max = 500000;
    static int[][] v = new int[2][max + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Arrays.fill(v[0], -1);
        Arrays.fill(v[1], -1);

        if (n == k)
            ans = 0;
        else
            bfs();

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        int time = 0;
        q.add(n);
        v[0][n] = 0;

        while (!q.isEmpty()) {
            int len = q.size();
            time++;
            int mod = time % 2;

            for (int i = 0; i < len; i++) {
                int now = q.poll();

                int[] arr = { now - 1, now + 1, now * 2 };
                for (int j = 0; j < 3; j++) {
                    if (arr[j] < 0 || arr[j] > max)
                        continue;
                    if (v[mod][arr[j]] != -1)
                        continue;
                    q.add(arr[j]);
                    v[mod][arr[j]] = time;
                }
            }

            int young = k + (time * (time + 1) / 2);
            if (young > max)
                break;
            if (v[mod][young] != -1) {
                ans = time;
                return;
            }
        }
    }
}
