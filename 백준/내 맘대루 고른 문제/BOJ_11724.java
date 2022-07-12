import java.util.*;
import java.io.*;

public class BOJ_11724 {
    static int n, m, cnt = 0;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 1; i < n + 1; i++) {
            if (visited[i])
                continue;
            bfs(i);
            cnt++;
        }

        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();

    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : list[now]) {
                if (visited[next])
                    continue;
                q.add(next);
                visited[next] = true;
            }
        }
    }

}
