import java.util.*;
import java.io.*;

public class BOJ_16946 {
    static int n, m;
    static int[][] map;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean[][] v;
    static int[][] group;
    static ArrayList<Integer> size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        group = new int[n][m];
        size = new ArrayList<>();
        size.add(0);

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = c[j] - '0';
            }
        }

        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (group[i][j] != 0)
                    continue;
                if (map[i][j] == 0) {
                    bfs(i, j, num);
                    num++;
                }
            }
        }

        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    sb.append(0);
                    continue;
                }
                HashSet<Integer> set = new HashSet<>();
                ans[i][j] = 1;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                        continue;
                    if (map[nx][ny] == 1)
                        continue;

                    set.add(group[nx][ny]);
                }

                for (int idx : set) {
                    ans[i][j] += size.get(idx);
                }
                sb.append(ans[i][j] % 10);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void bfs(int x, int y, int num) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        group[x][y] = num;

        int cnt = 1;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (group[nx][ny] != 0)
                    continue;
                if (map[nx][ny] == 0) {
                    q.add(new Node(nx, ny));
                    group[nx][ny] = num;
                    cnt++;
                }
            }
        }

        size.add(cnt);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Section {
        int num;
        int cnt;

        Section(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

}
