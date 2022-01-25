import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ_11266_2 {
    static int v, e, order = 1;
    static ArrayList<Integer>[] list;
    static boolean[] cutVertex;
    static int[] visited;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v + 1];
        cutVertex = new boolean[v + 1];
        visited = new int[v + 1];

        for (int i = 1; i < v + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list[start].add(end);
            list[end].add(start);
        }

        for (int i = 1; i < v + 1; i++) {
            if (visited[i] == 0)
                dfs(i, true);
        }

        int cnt = 0;
        for (int i = 1; i < v + 1; i++) {
            if (cutVertex[i])
                cnt++;
        }

        sb.append(cnt).append("\n");

        for (int i = 1; i < v + 1; i++) {
            if (cutVertex[i])
                sb.append(i).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int dfs(int now, boolean root) {
        visited[now] = order++;
        int ret = visited[now];
        int child = 0;

        for (int next : list[now]) {
            if (visited[next] == 0) {
                child++;

                int low = dfs(next, false);

                if (!root && low >= visited[now]) {
                    cutVertex[now] = true;
                }

                ret = Math.min(ret, low);
            } else {
                ret = Math.min(ret, visited[next]);
            }
        }

        if (root && child >= 2)
            cutVertex[now] = true;

        return ret;
    }
}
