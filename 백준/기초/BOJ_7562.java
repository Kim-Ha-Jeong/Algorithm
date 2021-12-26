import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_7562 {
    static int n;
    static Node start, end;
    static int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int[] dy = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            n = Integer.parseInt(br.readLine());

            visited = new boolean[n][n];
            String[] s = br.readLine().split(" ");
            start = new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1]), 0);
            s = br.readLine().split(" ");
            end = new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1]), 0);

            sb.append(bfs()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;
        int ans = -1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == end.x && now.y == end.y) {
                ans = now.cnt;
                break;
            }

            for (int i = 0; i < 8; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                if (!visited[nx][ny]) {
                    q.add(new Node(nx, ny, now.cnt + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        return ans;
    }

    static class Node {
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
