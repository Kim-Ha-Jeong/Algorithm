import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_3055 {
    static int r, c;
    static char[][] map;
    static int[] dr = { 0, 0, 1, -1 };
    static int[] dc = { 1, -1, 0, 0 };
    static Queue<Node> q = new LinkedList<Node>();
    static int ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        String[] s = br.readLine().split(" ");
        r = Integer.parseInt(s[0]);
        c = Integer.parseInt(s[1]);

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '*')
                    q.add(new Node(i, j, 0));
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'S') {
                    q.add(new Node(i, j, 1));
                }
            }
        }

        bfs();

        if (ans != -1)
            sb.append(ans);
        else
            sb.append("KAKTUS");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.cnt == 0) {
                for (int i = 0; i < 4; i++) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];

                    if (nr < 0 || nr >= r || nc < 0 || nc >= c)
                        continue;

                    if (map[nr][nc] == '*' || map[nr][nc] == 'D' || map[nr][nc] == 'X')
                        continue;

                    map[nr][nc] = '*';
                    q.add(new Node(nr, nc, 0));
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];

                    if (nr < 0 || nr >= r || nc < 0 || nc >= c)
                        continue;

                    if (map[nr][nc] == 'D') {
                        ans = now.cnt;
                        return;
                    }

                    if (map[nr][nc] == '.') {
                        map[nr][nc] = 'S';
                        q.add(new Node(nr, nc, now.cnt + 1));
                    }

                }

            }

        }
    }

    static class Node {
        int r;
        int c;
        int cnt;

        Node(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}
