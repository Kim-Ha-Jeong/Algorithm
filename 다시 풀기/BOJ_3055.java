import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_3055 {
    static char[][] map;
    static int r, c;
    static int[] dr = { 0, 0, 1, -1 };
    static int[] dc = { 1, -1, 0, 0 };
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        Node start = new Node(0, 0, 1, 0);

        for (int i = 0; i < r; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                map[i][j] = s[j].charAt(0);
                if (map[i][j] == '*')
                    q.add(new Node(i, j, 0, 0));
                else if (map[i][j] == 'S') {
                    start = new Node(i, j, 1, 0);
                    start.cnt = 0;
                }
            }
        }

        q.add(start);

        int ret = bfs();

        if (ret == -1)
            sb.append("KAKTUS");
        else
            sb.append(ret);
        sb.append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c)
                    continue;

                if (map[nr][nc] == 'X')
                    continue;

                if (now.type == 0) {
                    if (map[nr][nc] == 'D' || map[nr][nc] == '*')
                        continue;

                    map[nr][nc] = '*';
                    q.add(new Node(nr, nc, 0, 0));
                } else {
                    if (map[nr][nc] == '*' || map[nr][nc] == 'S')
                        continue;

                    if (map[nr][nc] == 'D') {
                        return now.cnt + 1;
                    }

                    map[nr][nc] = 'S';
                    q.add(new Node(nr, nc, 1, now.cnt + 1));
                }
            }
        }

        return -1;
    }

    static class Node {
        int r;
        int c;
        int type;
        int cnt;

        Node(int r, int c, int type, int cnt) {
            this.r = r;
            this.c = c;
            this.type = type;
            this.cnt = cnt;
        }
    }
}
