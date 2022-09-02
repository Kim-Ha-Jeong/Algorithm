import java.util.*;
import java.io.*;

public class BOJ_17837 {
    static int n, k;
    static int[][] map;
    static ArrayList<Integer>[][] hMap;
    static ArrayList<Horse> list;
    static int WHITE = 0, RED = 1, BLUE = 2;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean flag = true;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        hMap = new ArrayList[n][n];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                hMap[i][j] = new ArrayList<>();
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            list.add(new Horse(i, x, y, d));
            hMap[x][y].add(i);
        }

        int turn = 0;
        while (true) {
            if (turn > 1000)
                break;
            if (!flag)
                break;
            for (int i = 0; i < k; i++) {
                Horse now = list.get(i);
                moveHorse(now);
            }
            turn++;
        }

        turn = flag ? -1 : turn;
        bw.write(sb.append(turn).toString());
        bw.flush();
        bw.close();
    }

    static void moveHorse(Horse h) {
        int d = h.d;
        int hx = h.x;
        int hy = h.y;
        int nx = hx + dx[d];
        int ny = hy + dy[d];
        int idx = hMap[hx][hy].indexOf(h.num);
        int realSize = hMap[hx][hy].size();
        int size = realSize - idx;

        if (outOfBound(nx, ny) || map[nx][ny] == BLUE) {
            d = changeDir(h.d);
            h.d = d;
            nx = hx + dx[d];
            ny = hy + dy[d];
        }

        if (outOfBound(nx, ny) || map[nx][ny] == BLUE) {
            return;
        } else {
            if (hMap[nx][ny].size() + size >= 4) {
                flag = false;
                return;
            }

            if (map[nx][ny] == RED) {
                for (int i = realSize - 1; i >= idx; i--) {
                    int num = hMap[hx][hy].remove(hMap[hx][hy].size() - 1);
                    Horse horse = list.get(num);
                    horse.x = nx;
                    horse.y = ny;
                    hMap[nx][ny].add(num);
                }
            } else if (map[nx][ny] == WHITE) {
                for (int i = idx; i < realSize; i++) {
                    int num = hMap[hx][hy].remove(idx);
                    Horse horse = list.get(num);
                    horse.x = nx;
                    horse.y = ny;
                    hMap[nx][ny].add(num);
                }
            }
        }
    }

    static int changeDir(int d) {
        if (d % 2 == 0)
            return d + 1;
        return d - 1;
    }

    static boolean outOfBound(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= n)
            return true;
        return false;
    }

    static class Horse {
        int num;
        int x;
        int y;
        int d;

        Horse(int num, int x, int y, int d) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

}
