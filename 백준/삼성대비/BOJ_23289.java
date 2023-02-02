import java.util.*;
import java.io.*;

public class BOJ_23289 {
    static ArrayList<Node> search = new ArrayList<>();
    static ArrayList<Node> hitter = new ArrayList<>();
    static int[][] map;
    static int[][] temper;
    static boolean[][][] wall;
    static int r, c, k;
    static int[] dx = { 0, 0, 0, -1, 1 };
    static int[] dy = { 0, 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        temper = new int[r][c];
        wall = new boolean[r][c][5];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 5) {
                    search.add(new Node(i, j));
                } else if (map[i][j] != 0) {
                    Node hit = new Node(i, j);
                    hit.d = map[i][j];
                    hitter.add(hit);
                }
            }
        }

        int tmp = Integer.parseInt(br.readLine());
        for (int i = 0; i < tmp; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int flag = Integer.parseInt(st.nextToken());

            if (flag == 0) {
                if (x - 1 < 0)
                    continue;
                wall[x][y][3] = true;
                wall[x - 1][y][4] = true;
            } else {
                if (y + 1 >= c)
                    continue;
                wall[x][y][1] = true;
                wall[x][y + 1][2] = true;
            }
        }

        int chocolate = 0;
        boolean flag = false;
        while (true) {
            for (Node hit : hitter) {
                upTemper(hit);
            }
            control();
            downTemper();
            chocolate++;
            if (chocolate > 100) {
                flag = true;
                break;
            }
            if (check())
                break;
        }

        int ans = 0;
        if (flag)
            ans = 101;
        else
            ans = chocolate;

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void downTemper() {
        for (int i = 1; i < r - 1; i++) {
            if (temper[i][0] > 0)
                temper[i][0] -= 1;
            if (temper[i][c - 1] > 0)
                temper[i][c - 1] -= 1;
        }

        for (int i = 1; i < c - 1; i++) {
            if (temper[0][i] > 0)
                temper[0][i] -= 1;
            if (temper[r - 1][i] > 0)
                temper[r - 1][i] -= 1;
        }

        if (temper[0][0] > 0)
            temper[0][0] -= 1;
        if (temper[0][c - 1] > 0)
            temper[0][c - 1] -= 1;
        if (temper[r - 1][0] > 0)
            temper[r - 1][0] -= 1;
        if (temper[r - 1][c - 1] > 0)
            temper[r - 1][c - 1] -= 1;
    }

    static boolean check() {
        for (Node s : search) {
            if (temper[s.x][s.y] < k)
                return false;
        }

        return true;
    }

    static void control() {
        int[][] newT = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (temper[i][j] == 0)
                    continue;

                for (int d = 1; d < 5; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (isOutBound(nx, ny))
                        continue;
                    if (wall[i][j][d])
                        continue;
                    if (temper[i][j] > temper[nx][ny]) {
                        int diff = (temper[i][j] - temper[nx][ny]) / 4;
                        newT[nx][ny] += diff;
                        newT[i][j] -= diff;
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temper[i][j] += newT[i][j];
            }
        }
    }

    static void upTemper(Node hit) {
        if (wall[hit.x][hit.y][hit.d])
            return;
        boolean[][] v = new boolean[r][c];
        int[] arr = { -1, 0, 1 };
        int hot = 5;
        int startX = hit.x + dx[hit.d];
        int startY = hit.y + dy[hit.d];

        Queue<Node> q = new LinkedList<>();

        q.add(new Node(startX, startY));
        v[startX][startY] = true;
        temper[startX][startY] += hot;
        hot--;

        while (!q.isEmpty()) {
            if (hot == 0)
                break;
            int size = q.size();
            for (int round = 0; round < size; round++) {
                Node now = q.poll();

                for (int i = 0; i < 3; i++) {
                    int[] tmp = getNext(now, arr[i], hit.d);
                    int nx = tmp[0];
                    int ny = tmp[1];

                    if (isOutBound(nx, ny))
                        continue;
                    if (v[nx][ny] || !isPossible(now.x, now.y, hit.d, i))
                        continue;
                    q.add(new Node(nx, ny));
                    v[nx][ny] = true;
                    temper[nx][ny] += hot;
                }
            }
            hot--;
        }
    }

    static boolean isPossible(int x, int y, int d, int i) { // 4개의 케이스 벽 살펴보는 경우 따져줌
        if (d == 1) { // 동
            if (i == 0) {
                if (!isOutBound(x - 1, y) && !wall[x - 1][y][1] && !wall[x - 1][y][4])
                    return true;
            } else if (i == 1) {
                if (!wall[x][y][1])
                    return true;
            } else if (i == 2) {
                if (!isOutBound(x + 1, y) && !wall[x + 1][y][3] && !wall[x + 1][y][1])
                    return true;
            }
        } else if (d == 2) { // 서
            if (i == 0) {
                if (!isOutBound(x - 1, y) && !wall[x - 1][y][2] && !wall[x - 1][y][4])
                    return true;
            } else if (i == 1) {
                if (!wall[x][y][2])
                    return true;
            } else if (i == 2) {
                if (!isOutBound(x + 1, y) && !wall[x + 1][y][3] && !wall[x + 1][y][2])
                    return true;
            }
        } else if (d == 3) { // 북
            if (i == 0) {
                if (!isOutBound(x, y - 1) && !wall[x][y - 1][1] && !wall[x][y - 1][3])
                    return true;
            } else if (i == 1) {
                if (!wall[x][y][3])
                    return true;
            } else if (i == 2) {
                if (!isOutBound(x, y + 1) && !wall[x][y + 1][2] && !wall[x][y + 1][3])
                    return true;
            }
        } else if (d == 4) { // 남
            if (i == 0) {
                if (!isOutBound(x, y - 1) && !wall[x][y - 1][1] && !wall[x][y - 1][4])
                    return true;
            } else if (i == 1) {
                if (!wall[x][y][4])
                    return true;
            } else if (i == 2) {
                if (!isOutBound(x, y + 1) && !wall[x][y + 1][2] && !wall[x][y + 1][4])
                    return true;
            }
        }
        return false;
    }

    static int[] getNext(Node now, int part, int d) {
        int nx = -1, ny = -1;
        if (d == 1) {
            nx = now.x + part;
            ny = now.y + 1;
        } else if (d == 2) {
            nx = now.x + part;
            ny = now.y - 1;
        } else if (d == 3) {
            nx = now.x - 1;
            ny = now.y + part;
        } else if (d == 4) {
            nx = now.x + 1;
            ny = now.y + part;
        }

        int[] ret = { nx, ny };
        return ret;
    }

    static boolean isOutBound(int x, int y) {
        if (x < 0 || x >= r || y < 0 || y >= c)
            return true;
        return false;
    }

    static class Node {
        int x;
        int y;
        int d = -1;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
