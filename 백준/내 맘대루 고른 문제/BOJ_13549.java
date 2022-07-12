import java.io.*;
import java.util.*;

public class BOJ_13549 {
    static int n, k, ans = -1;
    static int[] second;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        second = new int[100001];

        Arrays.fill(second, -1);

        if (n == k) {
            sb.append(0);
        } else if (n > k) {
            sb.append(n - k);
        } else {
            bfs();
            sb.append(ans);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        second[n] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == k) {
                ans = second[now];
                return;
            }

            int[] arr = { now * 2, now - 1, now + 1 };
            for (int i = 0; i < 3; i++) {
                if (arr[i] < 0 || arr[i] > 100000 || second[arr[i]] != -1)
                    continue;
                q.add(arr[i]);
                if (i >= 1)
                    second[arr[i]] = second[now] + 1;
                else
                    second[arr[i]] = second[now];
            }
        }

    }

}
