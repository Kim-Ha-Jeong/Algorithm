import java.io.*;

public class BOJ_1913 {
    static int n, findNum;
    static int[][] map;
    static int x, y, ansX = -1, ansY = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        findNum = Integer.parseInt(br.readLine());

        map = new int[n][n];

        x = y = n / 2;

        for (int num = 1; num < n; num += 2) {
            map[x][y] = num * num;
            rotate(x - 1, y, num + 1, num * num + 1);
            x -= 1;
            y -= 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == findNum && ansX == -1) {
                    ansX = i + 1;
                    ansY = j + 1;
                }
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        sb.append(ansX).append(" ").append(ansY);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void rotate(int x, int y, int cnt, int next) {
        if (cnt == 0)
            return;

        int realX = x;
        int realY = y;

        for (int i = y; i < y + cnt; i++) {
            map[x][i] = next++;
        }

        y += (cnt - 1);

        for (int i = x + 1; i < x + 1 + cnt; i++) {
            map[i][y] = next++;
        }

        x += cnt;

        for (int i = y - 1; i >= realY - 1; i--) {
            map[x][i] = next++;
        }

        for (int i = x - 1; i >= realX; i--) {
            map[i][realY - 1] = next++;
        }
    }
}
