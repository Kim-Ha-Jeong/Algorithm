import java.io.*;
import java.util.*;

public class BOJ_16940 {
    static int n;
    static ArrayList<Integer>[] list;
    static int[] result;
    static int[] visited;
    static int ans = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        result = new int[n];
        list = new ArrayList[n + 1];
        visited = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            result[i] = Integer.parseInt(st.nextToken());
        }

        if (result[0] != 1) {
            ans = 0;
        } else {
            bfs();
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = 1;

        int idx = 1;
        while (!q.isEmpty()) {
            int now = q.poll();

            int child = 0;
            for (int next : list[now]) {
                if (visited[next] != 0)
                    continue;
                visited[next] = now;
                child++;
            }

            for (int i = idx; i < idx + child; i++) {
                if (visited[result[i]] != now) {
                    ans = 0;
                    return;
                }
                q.add(result[i]);
            }

            idx += child;
        }
    }
}
