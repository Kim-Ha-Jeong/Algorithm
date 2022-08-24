import java.util.*;
import java.io.*;

public class BOJ_4574 {
    static int n;
    static int[][] map;
    static boolean[][] col;
    static boolean[][] row;
    static boolean[][][] square;
    static boolean[][] domino;
    static int[][] dir = { { 0, 1 }, { 1, 0 } };
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0)
                break;

            map = new int[9][9];
            row = new boolean[9][10];
            col = new boolean[9][10];
            square = new boolean[3][3][10];
            domino = new boolean[10][10];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                char[] lu = st.nextToken().toCharArray();
                int v = Integer.parseInt(st.nextToken());
                char[] lv = st.nextToken().toCharArray();

                int x1 = lu[0] - 'A';
                int y1 = lu[1] - '0' - 1;
                int x2 = lv[0] - 'A';
                int y2 = lv[1] - '0' - 1;

                map[x1][y1] = u;
                map[x2][y2] = v;

                row[x1][u] = true;
                col[y1][u] = true;
                row[x2][v] = true;
                col[y2][v] = true;

                square[x1 / 3][y1 / 3][u] = true;
                square[x2 / 3][y2 / 3][v] = true;

                domino[u][v] = true;
                domino[v][u] = true;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 9; i++) {
                char[] c = st.nextToken().toCharArray();
                int x = c[0] - 'A';
                int y = c[1] - '0' - 1;

                row[x][i] = true;
                col[y][i] = true;

                square[x / 3][y / 3][i] = true;
                map[x][y] = i;
            }

            sb.append("Puzzle ").append(tc++).append("\n");
            dfs(0);

        }

        bw.write(sb.deleteCharAt(sb.length() - 1).toString());
        bw.flush();
        bw.close();

    }

    static boolean dfs(int depth) {
        if (depth == 81) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

            return true;
        }

        int x = depth / 9;
        int y = depth % 9;

        if (map[x][y] != 0) {
            if (dfs(depth + 1))
                return true;
        } else {
            for (int d = 0; d < 2; d++) {
                int nx = x + dir[d][0];
                int ny = y + dir[d][1];

                if (nx >= 9 || ny >= 9 || map[nx][ny] != 0)
                    continue;

                for (int i = 1; i < 9; i++) {
                    for (int j = i + 1; j < 10; j++) {
                        if (domino[i][j] || domino[j][i])
                            continue;
                        domino[i][j] = domino[j][i] = true;

                        if (isPossible(x, y, i) && isPossible(nx, ny, j)) {
                            set(x, y, i, nx, ny, j);

                            if (dfs(depth + 1))
                                return true;

                            release(x, y, i, nx, ny, j);
                        }

                        if (isPossible(x, y, j) && isPossible(nx, ny, i)) {
                            set(x, y, j, nx, ny, i);

                            if (dfs(depth + 1))
                                return true;

                            release(x, y, j, nx, ny, i);
                        }

                        domino[i][j] = domino[j][i] = false;
                    }
                }
            }
        }

        return false;
    }

    static void set(int x, int y, int i, int nx, int ny, int j) {
        row[x][i] = col[y][i] = square[x / 3][y / 3][i] = true;
        row[nx][j] = col[ny][j] = square[nx / 3][ny / 3][j] = true;
        map[x][y] = i;
        map[nx][ny] = j;
    }

    static void release(int x, int y, int i, int nx, int ny, int j) {
        row[x][i] = col[y][i] = square[x / 3][y / 3][i] = false;
        row[nx][j] = col[ny][j] = square[nx / 3][ny / 3][j] = false;
        map[x][y] = map[nx][ny] = 0;
    }

    static boolean isPossible(int x, int y, int num) {
        if (!row[x][num] && !col[y][num] && !square[x / 3][y / 3][num])
            return true;
        return false;
    }

}