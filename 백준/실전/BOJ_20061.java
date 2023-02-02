import java.util.*;
import java.io.*;

public class BOJ_20061 {
    static int nx, ny;
    static int[][] map;
    static int score = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        map = new int[10][10];

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                moveOne(b, c);
                afterGreenOne();
                afterBlueOne();
            } else if (a == 2) {
                moveTwo(b, c);
                afterGreenOne();
                afterBlueTwo();
            } else {
                moveThree(b, c);
                afterGreenTwo();
                afterBlueOne();
            }
        }

        int cnt = 0;
        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == 1)
                    cnt++;
                if (map[j][i] == 1)
                    cnt++;
            }
        }

        sb.append(score).append("\n").append(cnt);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void afterGreenTwo() {
        int s = 0, idx = 0;
        for (int i = nx - 1; i <= nx; i++) {
            boolean flag = true;
            for (int j = 0; j < 4; j++) {
                if (map[i][j] != 1) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                s++;
                idx = i;
            }
        }

        if (s != 0) {
            for (int i = idx - s; i >= 4; i--) {
                for (int j = 0; j < 4; j++) {
                    map[i + s][j] = map[i][j];
                    map[i][j] = 0;
                }
            }
        }

        score += s;
        if ((nx == 5 && s <= 1) || (nx == 6 && s == 0)) {
            int tmp = 1;
            if (nx == 5)
                tmp += 1;

            for (int i = 9; i >= 6; i--) {
                for (int j = 0; j < 4; j++) {
                    map[i][j] = map[i - tmp][j];
                    map[i - tmp][j] = 0;
                }
            }
        }
    }

    static void moveThree(int x, int y) {
        nx = ny = 9;
        for (int i = 6; i < 10; i++) {
            if (map[i][y] == 1) {
                nx = i - 1;
                map[i - 1][y] = map[i - 2][y] = 1;
                break;
            }

            if (i == 9) {
                map[i][y] = map[i - 1][y] = 1;
            }
        }

        for (int i = 6; i < 10; i++) {
            if (map[x][i] == 1 || map[x + 1][i] == 1) {
                map[x + 1][i - 1] = map[x][i - 1] = 1;
                ny = i - 1;
                break;
            }

            if (i == 9) {
                map[x][i] = map[x + 1][i] = 1;
            }
        }
    }

    static void afterBlueTwo() {
        int s = 0;
        int idx = -1;

        for (int i = ny - 1; i <= ny; i++) {
            boolean flag = true;
            for (int j = 0; j < 4; j++) {
                if (map[j][i] != 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                s++;
                idx = i;
            }
        }

        if (s != 0) {
            for (int i = idx - s; i >= 4; i--) {
                for (int j = 0; j < 4; j++) {
                    map[j][i + s] = map[j][i];
                    map[j][i] = 0;
                }
            }
        }

        score += s;
        if ((ny == 5 && s <= 1) || (ny == 6 && s == 0)) {
            int tmp = 1;
            if (ny == 5)
                tmp += 1;

            for (int i = 9; i >= 6; i--) {
                for (int j = 0; j < 4; j++) {
                    map[j][i] = map[j][i - tmp];
                    map[j][i - tmp] = 0;
                }
            }
        }
    }

    static void moveTwo(int x, int y) {
        nx = ny = 9;

        for (int i = 6; i < 10; i++) {
            if (map[i][y] == 1 || map[i][y + 1] == 1) {
                map[i - 1][y] = map[i - 1][y + 1] = 1;
                nx = i - 1;
                break;
            }

            if (i == 9) {
                map[i][y] = map[i][y + 1] = 1;
            }
        }

        for (int i = 6; i < 10; i++) {
            if (map[x][i] == 1) {
                map[x][i - 1] = map[x][i - 2] = 1;
                ny = i - 1;
                break;
            }

            if (i == 9) {
                map[x][i] = map[x][i - 1] = 1;
            }
        }
    }

    static void afterBlueOne() {
        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            if (map[i][ny] == 1) {
                flag = false;
                break;
            }
        }

        if (flag) {
            score++;
            for (int i = ny - 1; i >= 5; i--) {
                for (int j = 0; j < 4; j++) {
                    map[j][i + 1] = map[j][i];
                }
            }

            for (int i = 0; i < 4; i++) {
                map[i][5] = 0;
            }
        }

        if (!flag && ny == 5) {
            for (int i = 9; i >= 6; i--) {
                for (int j = 0; j < 4; j++) {
                    map[j][i] = map[j][i - 1];
                }
            }

            for (int i = 0; i < 4; i++) {
                map[i][5] = 0;
            }
        }
    }

    static void afterGreenOne() {
        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            if (map[nx][i] != 1) {
                flag = false;
                break;
            }
        }

        if (flag) {
            score++;
            for (int i = nx - 1; i >= 5; i--) {
                map[i + 1] = map[i];
            }
            Arrays.fill(map[5], 0);
        }

        if (!flag && nx == 5) {
            for (int i = 9; i >= 6; i--) {
                map[i] = map[i - 1];
            }
            Arrays.fill(map[5], 0);
        }
    }

    static void moveOne(int x, int y) {
        nx = ny = 9;
        for (int i = 5; i < 10; i++) {
            if (map[i][y] == 1) {
                map[i - 1][y] = 1;
                nx = i - 1;
                break;
            }

            if (i == 9)
                map[i][y] = 1;
        }

        for (int i = 5; i < 10; i++) {
            if (map[x][i] == 1) {
                map[x][i - 1] = 1;
                ny = i - 1;
                break;
            }

            if (i == 9)
                map[x][i] = 1;
        }
    }

}
