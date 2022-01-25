import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ_11266 {
    static int v, e, order = 1;
    static int[] visited;
    static boolean[] cutVertex;
    static ArrayList<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        adj = new ArrayList[v + 1];

        for (int i = 1; i < v + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        visited = new int[v + 1];
        cutVertex = new boolean[v + 1];

        for (int i = 1; i < v + 1; i++) {
            if (visited[i] == 0) // 탐색한 적이 없으면 DFS를 실행
                dfs(i, true);
        }

        int cnt = 0;
        for (int i = 1; i < v + 1; i++) {
            if (cutVertex[i]) {
                cnt++;
            }
        }

        sb.append(cnt).append("\n");

        for (int i = 1; i < v + 1; i++) {
            if (cutVertex[i]) {
                sb.append(i).append(" ");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int dfs(int now, boolean isRoot) {
        visited[now] = order++;
        int ret = visited[now]; // 지금 정점 이후에 도달할 수 있는 모든 정점의 탐색순서(order) 중 가장 작은값
        int child = 0;

        for (int next : adj[now]) {
            if (visited[next] == 0) { // 탐색하지 않은 경우
                child++;

                int low = dfs(next, false);

                if (!isRoot && low >= visited[now]) {
                    cutVertex[now] = true;
                }

                ret = Math.min(ret, low);
            } else {
                ret = Math.min(ret, visited[next]);
            }
        }

        if (isRoot && child >= 2)
            cutVertex[now] = true;
        return ret;
    }
}
