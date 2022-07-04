import java.util.*;
import java.io.*;

public class BOJ_16935 {
    static int[][] map;
    static int n, m, r;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < r; i++) {
            int x = Integer.parseInt(s[i]);

            if (x == 1)
                upDown();
            else if (x == 2)
                leftRight();
            else if (x == 3 || x == 4)
                rotate(x);
            else if (x == 5 || x == 6)
                half(x);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void upDown() {
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n / 2; i++) {
                int a = i;
                int b = n - 1 - i;

                int tmp = map[a][j];
                map[a][j] = map[b][j];
                map[b][j] = tmp;
            }
        }
    }

    static void leftRight() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                int a = j;
                int b = m - 1 - j;

                int tmp = map[i][a];
                map[i][a] = map[i][b];
                map[i][b] = tmp;
            }
        }
    }

    static void rotate(int command) {
        int[][] tmp = new int[map.length][map[0].length];

        tmp = copy(map, tmp, n, m);

        map = new int[m][n];

        if (command == 3)
            right(tmp);
        else if (command == 4)
            left(tmp);

        int temp = n;
        n = m;
        m = temp;
    }

    static void right(int[][] tmp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[j][n - 1 - i] = tmp[i][j];
            }
        }
    }

    static void left(int[][] tmp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[m - j - 1][i] = tmp[i][j];
            }
        }
    }

    static void half(int command) {
        int[][] tmp = new int[n / 2][m / 2];

        tmp = copy(map, tmp, n / 2, m / 2);

        if (command == 5)
            five(tmp);
        else if (command == 6)
            six(tmp);
    }

    static void five(int[][] tmp) {
        // 4 -> 1
        for (int i = n / 2; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                map[i - n / 2][j] = map[i][j];
            }
        }

        // 3 -> 4
        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                map[i][j - m / 2] = map[i][j];
            }
        }

        // 2 -> 3
        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2; j < m; j++) {
                map[i + n / 2][j] = map[i][j];
            }
        }

        // 1 -> 2
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                map[i][j + m / 2] = tmp[i][j];
            }
        }
    }

    static void six(int[][] tmp) {
        // 2 -> 1
        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2; j < m; j++) {
                map[i][j - m / 2] = map[i][j];
            }
        }

        // 3 -> 2
        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                map[i - n / 2][j] = map[i][j];
            }
        }

        // 4 -> 3
        for (int i = n / 2; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                map[i][j + m / 2] = map[i][j];
            }
        }

        // 1 -> 4
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                map[i + n / 2][j] = tmp[i][j];
            }
        }
    }

    static int[][] copy(int[][] src, int[][] dest, int limitX, int limitY) {
        for (int i = 0; i < limitX; i++) {
            for (int j = 0; j < limitY; j++) {
                dest[i][j] = map[i][j];
            }
        }

        return dest;
    }

}
