import java.util.*;
import java.io.*;

public class BOJ_18500 {
    static int r, c;
    static char[][] map;
    static int[][] v;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = r - 1; i >= 0; i--) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                map[i][j] = ch[j];
            }
        }

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken()) - 1;

            boolean flag;
            if (i % 2 == 0) {
                flag = left(height);
            } else {
                flag = right(height);
            }

            if (flag)
                cal();
        }

        for (int i = r - 1; i >= 0; i--) {
            for (int j = 0; j < c; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void cal() {
        int cnt = 0;

        v = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'x' && v[i][j] == 0) {
                    cnt++;
                    boolean flag = bfs(i, j, cnt);
                    if (!flag) {
                        changeMineral(cnt);
                        return;
                    }
                }
            }
        }
    }

    static void changeMineral(int cnt) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (v[i][j] == cnt) {
                    if (map[i - 1][j] == '.') {
                        min = Math.min(min, find(i, j, cnt));
                    }
                }
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (v[i][j] == cnt) {
                    if (i - min < 0)
                        continue;
                    map[i][j] = '.';
                    map[i - min][j] = 'x';
                }
            }
        }
    }

    static int find(int x, int y, int cnt) {
        int ret = x;
        for (int i = x - 1; i >= 0; i--) {
            if (map[i][y] == 'x' && v[i][y] != cnt) {
                ret = x - (i + 1);
                break;
            }
        }
        return ret;
    }

    static boolean bfs(int x, int y, int cnt) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        v[x][y] = cnt;
        boolean flag = false;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == 0 && !flag) {
                flag = true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c)
                    continue;

                if (map[nx][ny] == '.')
                    continue;
                if (v[nx][ny] != 0)
                    continue;

                q.add(new Node(nx, ny));
                v[nx][ny] = cnt;
            }
        }

        return flag;
    }

    static boolean left(int x) {
        for (int i = 0; i < c; i++) {
            if (map[x][i] == 'x') {
                map[x][i] = '.';
                return true;
            }
        }

        return false;
    }

    static boolean right(int x) {
        for (int i = c - 1; i >= 0; i--) {
            if (map[x][i] == 'x') {
                map[x][i] = '.';
                return true;
            }
        }
        return false;
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
