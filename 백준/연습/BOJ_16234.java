import java.util.*;
import java.io.*;

public class BOJ_16234 {
    static int n, l, r;
    static int[][] map;
    static boolean[][] visited;
    static int ans = 0;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            int cnt = 0;
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j])
                        continue;
                    cnt += bfs(i, j);
                }
            }

            if (cnt == 0)
                break;
            ans++;
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static int bfs(int x, int y) {
        int sum = 0;

        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> list = new ArrayList<>();

        q.add(new Node(x, y));
        visited[x][y] = true;
        list.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node now = q.poll();
            sum += map[now.x][now.y];

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (visited[nx][ny])
                    continue;

                int diff = Math.abs(map[nx][ny] - map[now.x][now.y]);

                if (diff < l || diff > r)
                    continue;
                Node next = new Node(nx, ny);
                q.add(next);
                list.add(next);
                visited[nx][ny] = true;
            }
        }

        if (list.size() == 1)
            return 0;

        int avg = sum / list.size();

        for (Node n : list) {
            map[n.x][n.y] = avg;
        }

        return list.size();
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
