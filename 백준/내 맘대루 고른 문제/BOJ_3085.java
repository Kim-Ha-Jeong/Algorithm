import java.io.*;

public class BOJ_3085 {
    static int[][] map;
    static int[][] tmp;
    static int n;
    static int[] dx = { 1, 0 };
    static int[] dy = { 0, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        tmp = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (c[j] == 'C')
                    map[i][j] = 1;
                else if (c[j] == 'P')
                    map[i][j] = 2;
                else if (c[j] == 'Z')
                    map[i][j] = 3;
                else if (c[j] == 'Y')
                    map[i][j] = 4;
            }
        }

        int max = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                for (int k = 0; k < 2; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == map[i][j])
                        continue;

                    swap(i, j, nx, ny);

                    max = Math.max(max, find());

                    swap(i, j, nx, ny);
                }

            }
        }

        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void swap(int i, int j, int nx, int ny) {
        int tmp = map[i][j];
        map[i][j] = map[nx][ny];
        map[nx][ny] = tmp;
    }

    static int find() {
        int max = -1;
        for (int i = 0; i < n; i++) {
            int x = map[i][0];
            int y = map[0][i];
            int cnt = 0;
            int cnt2 = 0;
            for (int j = 0; j < n; j++) {
                if (x == map[i][j]) {
                    cnt++;
                } else {
                    x = map[i][j];
                    max = Math.max(max, cnt);
                    cnt = 1;
                }

                if (y == map[j][i]) {
                    cnt2++;
                } else {
                    y = map[j][i];
                    max = Math.max(max, cnt2);
                    cnt2 = 1;
                }
            }

            max = Math.max(max, Math.max(cnt, cnt2));
        }

        return max;
    }
}
