import java.util.*;
import java.io.*;

public class BOJ_12886 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if (bfs(a, b, c))
            sb.append(1);
        else
            sb.append(0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean bfs(int a, int b, int c) {
        if ((a + b + c) % 3 != 0)
            return false;

        Queue<Stone> q = new LinkedList<>();
        boolean[][] visited = new boolean[1501][1501];
        q.add(new Stone(a, b, c));

        while (!q.isEmpty()) {
            Stone stone = q.poll();

            a = stone.a;
            b = stone.b;
            c = stone.c;

            if (a == b && b == c)
                return true;

            if (a != b) {
                int na = a > b ? a - b : 2 * a;
                int nb = a > b ? b * 2 : b - a;

                if (!visited[na][nb]) {
                    q.add(new Stone(na, nb, c));
                    visited[na][nb] = true;
                }
            }

            if (b != c) {
                int nb = b > c ? b - c : b * 2;
                int nc = b > c ? c * 2 : c - b;
                if (!visited[nb][nc]) {
                    q.add(new Stone(a, nb, nc));
                    visited[nb][nc] = true;
                }
            }

            if (a != c) {
                int na = a > c ? a - c : 2 * a;
                int nc = a > c ? c * 2 : c - a;
                if (!visited[na][nc]) {
                    q.add(new Stone(na, b, nc));
                    visited[na][nc] = true;
                }
            }
        }

        return false;
    }

    static class Stone {
        int a;
        int b;
        int c;

        Stone(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

}
