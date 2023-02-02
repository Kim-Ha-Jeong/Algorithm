import java.util.*;
import java.io.*;

public class BOJ_17837 {
    static int n, k;
    static ArrayList<Integer>[][] hMap;
    static int[][] map;
    static ArrayList<Horse> horse;
    static int WHITE = 0, RED = 1, BLUE = 2;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        hMap = new ArrayList[n][n];
        horse = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                hMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            Horse h = new Horse(i, x, y, d);

            horse.add(h);
            hMap[x][y].add(i);
        }

        boolean flag = true;
        int cnt = 0;
        while (true) {
            if (cnt > 1000)
                break;
            if (!flag)
                break;
            for (Horse now : horse) {
                flag = moveHorse(now);
                if (!flag)
                    break;
            }
            cnt++;
        }

        cnt = cnt > 1000 ? -1 : cnt;
        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();

    }

    static boolean moveHorse(Horse h) {
        int hx = h.x;
        int hy = h.y;
        int nx = hx + dx[h.d];
        int ny = hy + dy[h.d];
        int idx = hMap[hx][hy].indexOf(h.num);
        int realSize = hMap[hx][hy].size();
        int size = realSize - idx;

        if (isOutofBound(nx, ny) || map[nx][ny] == BLUE) {
            h.d = h.d % 2 == 0 ? h.d + 1 : h.d - 1;
            nx = hx + dx[h.d];
            ny = hy + dy[h.d];
        }

        if (isOutofBound(nx, ny) || map[nx][ny] == BLUE)
            return true;
        else {
            if (hMap[nx][ny].size() + size >= 4)
                return false;

            if (map[nx][ny] == WHITE) {
                for (int i = idx; i < realSize; i++) {
                    int next = hMap[hx][hy].remove(idx);
                    Horse hNext = horse.get(next);
                    hNext.x = nx;
                    hNext.y = ny;
                    hMap[nx][ny].add(next);
                }
            } else if (map[nx][ny] == RED) {
                for (int i = realSize - 1; i >= idx; i--) {
                    int next = hMap[hx][hy].remove(hMap[hx][hy].size() - 1);
                    Horse hNext = horse.get(next);
                    hNext.x = nx;
                    hNext.y = ny;
                    hMap[nx][ny].add(next);
                }
            }
        }

        return true;

    }

    static boolean isOutofBound(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
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
