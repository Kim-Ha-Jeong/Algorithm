import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_14503 {
    static int n, m;
    static int[][] arr;
    static boolean[][] visited;
    static int ans = 1;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        arr = new int[n][m];
        visited = new boolean[n][m];

        s = br.readLine().split(" ");
        Node now = new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));

        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        bfs(now);

        bw.write(ans + "");
        bw.flush();
        bw.close();
    }

    static void bfs(Node no) {
        Queue<Node> q = new LinkedList<>();
        visited[no.x][no.y] = true;
        q.add(no);

        while (!q.isEmpty()) {
            Node now = q.poll();
            int nd = now.d;

            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                nd = nd == 0 ? 3 : nd - 1;
                int nx = now.x + dx[nd];
                int ny = now.y + dy[nd];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || arr[nx][ny] == 1)
                    continue;

                visited[nx][ny] = true;
                q.add(new Node(nx, ny, nd));
                ans++;
                flag = true;
                break;
            }

            if (!flag) {
                int nx = now.x + dx[(now.d + 2) % 4];
                int ny = now.y + dy[(now.d + 2) % 4];

                if (arr[nx][ny] == 1 || nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    break;
                } else
                    q.add(new Node(nx, ny, now.d));
            }

        }
    }

    static class Node {
        int x;
        int y;
        int d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
