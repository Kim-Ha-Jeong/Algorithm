import java.util.*;
import java.io.*;

public class BOJ_4991 {
    static int n, m;
    static char[][] map;
    static int[][] dist;
    static Node start;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int ans = -1;
    static ArrayList<Node> list;
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        while (true) {
            ans = -1;
            flag = true;
            st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            dist = new int[n][m];
            list = new ArrayList<>();

            if (m == 0 && n == 0) {
                break;
            }

            map = new char[n][m];

            for (int i = 0; i < n; i++) {
                char[] c = br.readLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    map[i][j] = c[j];
                    if (map[i][j] == '*') {
                        list.add(new Node(i, j));
                    }
                    if (map[i][j] == 'o') {
                        list.add(0, new Node(i, j));
                    }
                }
            }

            solve();
            if (!flag) {
                ans = -1;
            } else {
                boolean[] visited = new boolean[list.size() + 1];
                int[] arr = new int[list.size() + 1];
                dfs(0, visited, arr);
                ans -= 3;
            }
            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void solve() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int ret = bfs(list.get(i), list.get(j));
                if (ret == -1) {
                    flag = false;
                    return;
                } else {
                    dist[i][j] = dist[j][i] = ret;
                }
            }
        }
    }

    static int bfs(Node start, Node end) {
        Queue<Node> q = new LinkedList<>();
        int[][] visited = new int[n][m];
        q.add(start);
        for (int i = 0; i < n; i++)
            Arrays.fill(visited[i], -1);

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == end.x && now.y == end.y) {
                return visited[now.x][now.y];
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (map[nx][ny] == 'x')
                    continue;
                if (visited[nx][ny] != -1 || map[nx][ny] == 'x')
                    continue;

                q.add(new Node(nx, ny));
                visited[nx][ny] = visited[now.x][now.y] + 1;
            }
        }

        return -1;
    }

    static void dfs(int depth, boolean[] visited, int[] arr) {
        if (depth == list.size() - 1) {
            cal(arr);
            return;
        }

        for (int i = 1; i < list.size(); i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            arr[depth] = i;
            dfs(depth + 1, visited, arr);
            arr[depth] = 0;
            visited[i] = false;
        }
    }

    static void cal(int[] arr) {
        int index = 0;
        int sum = 0;

        for (int i = 1; i < arr.length; i++) {
            sum += dist[index][i];
            index = arr[i];
        }

        ans = Math.max(ans, sum);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
