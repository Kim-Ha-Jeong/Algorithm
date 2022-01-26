import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ_11400 {
    static int v, e, order = 1;
    static int[] visited;
    static ArrayList<Integer>[] list;
    static PriorityQueue<Edge> ans;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v + 1];
        visited = new int[v + 1];

        for (int i = 1; i < v + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        ans = new PriorityQueue<>();
        for (int i = 1; i < v + 1; i++) {
            if (visited[i] == 0)
                dfs(i, 0);
        }

        sb.append(ans.size()).append("\n");

        while (!ans.isEmpty()) {
            Edge now = ans.poll();
            sb.append(now.x).append(" ").append(now.y).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int dfs(int now, int parent) {
        visited[now] = order++;
        int ret = visited[now];

        for (int next : list[now]) {
            if (next == parent)
                continue;

            if (visited[next] == 0) {
                int low = dfs(next, now);

                if (visited[now] < low) {
                    if (next > now)
                        ans.add(new Edge(now, next));
                    else
                        ans.add(new Edge(next, now));
                }

                ret = Math.min(low, ret);
            } else {
                ret = Math.min(ret, visited[next]);
            }

        }

        return ret;
    }

    static class Edge implements Comparable<Edge> {
        int x;
        int y;

        Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Edge e) {
            if (this.x == e.x)
                return this.y - e.y;
            return this.x - e.x;
        }

    }

}
