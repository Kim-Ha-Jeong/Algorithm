import java.util.*;
import java.io.*;

class BOJ_17822 {
    static int n, m, tc;
    static int[][] map;
    static boolean flag;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        tc = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < m + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < tc; t++) {
            flag = false;
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = (Integer.parseInt(st.nextToken())) % m;

            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 1; i < n + 1; i++) {
                if (i % x == 0)
                    list.add(i);
            }

            for (int i : list) {
                for (int j = 0; j < k; j++)
                    rotate(i, d);
            }

            removeSame();

            if (!flag) {
                int sum = 0;
                int cnt = 0;
                for (int i = 1; i < n + 1; i++) {
                    for (int j = 1; j < m + 1; j++) {
                        if (map[i][j] != -1) {
                            sum += map[i][j];
                            cnt++;
                        }
                    }
                }

                double avg = sum / ((double) cnt);

                for (int i = 1; i < n + 1; i++) {
                    for (int j = 1; j < m + 1; j++) {
                        if (map[i][j] == -1)
                            continue;
                        if (map[i][j] < avg) {
                            map[i][j]++;
                        } else if (map[i][j] > avg) {
                            map[i][j]--;
                        }
                    }
                }
            }

        }

        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (map[i][j] == -1)
                    continue;
                ans += map[i][j];
            }
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();

    }

    static void removeSame() {

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (map[i][j] == -1)
                    continue;
                col(i, j);
            }
        }
    }

    static void col(int i, int j) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(i, j));
        int num = map[i][j];

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = now.x + dx[k];
                int ny = now.y + dy[k];

                if (nx < 1 || nx >= n + 1)
                    continue;
                if (ny >= m + 1)
                    ny = 1;
                if (ny < 1)
                    ny = m;

                if (map[nx][ny] == -1)
                    continue;

                if (map[nx][ny] != num)
                    continue;

                q.add(new Node(nx, ny));
                map[now.x][now.y] = map[nx][ny] = -1;
                flag = true;
            }
        }
    }

    static void rotate(int x, int d) {
        int tmp;
        if (d == 1) {
            tmp = map[x][1];
            for (int i = 1; i < m; i++) {
                map[x][i] = map[x][i + 1];
            }
            map[x][m] = tmp;
        } else {
            tmp = map[x][m];
            for (int i = m - 1; i >= 1; i--) {
                map[x][i + 1] = map[x][i];
            }
            map[x][1] = tmp;
        }
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