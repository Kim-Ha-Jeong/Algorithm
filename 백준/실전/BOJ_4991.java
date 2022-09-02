import java.util.*;
import java.io.*;

public class BOJ_4991 {
    static int h, w, ans;
    static char[][] map;
    static int[][] dist;
    static ArrayList<Node> dust;
    static Node start;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;
            flag = true;

            if (w == 0 && h == 0)
                break;
            map = new char[h][w];
            dust = new ArrayList<>();

            for (int i = 0; i < h; i++) {
                char[] ch = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    map[i][j] = ch[j];
                    if (ch[j] == 'o') {
                        dust.add(0, new Node(i, j));
                    } else if (ch[j] == '*') {
                        dust.add(new Node(i, j));
                    }
                }
            }

            int size = dust.size();
            dist = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    int ret = bfs(dust.get(i), dust.get(j));
                    dist[i][j] = dist[j][i] = ret;
                    if (i == 0 && ret == -1) {
                        flag = false;
                        break;
                    }
                }
                if (!flag)
                    break;
            }

            if (flag) {
                boolean[] v = new boolean[dust.size() + 1];
                v[0] = true;
                int[] arr = new int[dust.size() - 1];
                dfs(0, arr, v);
            }

            ans = flag ? ans : -1;
            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int[] order, boolean[] v) {
        if (depth == dust.size() - 1) {
            cal(order);
            return;
        }

        for (int i = 1; i < dust.size(); i++) {
            if (v[i])
                continue;
            v[i] = true;
            order[depth] = i;
            dfs(depth + 1, order, v);
            v[i] = false;
        }
    }

    static void cal(int[] order) {
        int sum = dist[0][order[0]];

        for (int i = 0; i < order.length - 1; i++) {
            int a = order[i];
            int b = order[i + 1];
            if (dist[a][b] == -1) {
                flag = false;
                return;
            } else {
                sum += dist[a][b];
            }
        }

        ans = Math.min(sum, ans);
    }

    static int bfs(Node start, Node end) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] v = new boolean[h][w];
        start.cnt = 0;
        q.add(start);
        v[start.x][start.y] = true;

        int ret = -1;
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == end.x && now.y == end.y) {
                ret = now.cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                    continue;
                if (map[nx][ny] == 'x' || v[nx][ny])
                    continue;

                v[nx][ny] = true;
                Node next = new Node(nx, ny);
                next.cnt = now.cnt + 1;
                q.add(next);
            }
        }

        return ret;
    }

    static class Node {
        int x;
        int y;
        int cnt = 0;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
