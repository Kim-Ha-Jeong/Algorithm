import java.util.*;
import java.io.*;

class BOJ_23290 {
    static int m, s, ans = 0;
    static ArrayList<Fish> originFish, fish;
    static ArrayList<Fish>[][] map;
    static int[][] smell;
    static int[] fdx = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] fdy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
    static Fish shark;
    static int max = 0;
    static int[] dir;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        originFish = new ArrayList<>();
        fish = new ArrayList<>();

        map = new ArrayList[4][4];
        smell = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            originFish.add(new Fish(x, y, d));
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        shark = new Fish(x, y, -1);

        while (s-- > 0) {
            fish = new ArrayList<>();

            for (Fish f : originFish) {
                fish.add(moveFish(f));
            }

            setMap();

            int[] order = new int[3];
            dir = new int[3];
            max = -1;
            searchRoute(shark, 0, 0, order);
            moveShark();
            removeSmell();
            copyFish();
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void setMap() {
        for (Fish f : fish) {
            map[f.x][f.y].add(f);
        }
    }

    static void removeSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smell[i][j] > 0) {
                    smell[i][j]--;
                }
            }
        }
    }

    static void copyFish() {
        for (Fish f : originFish) {
            map[f.x][f.y].add(f);
        }

        int cnt = 0;
        originFish = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < map[i][j].size(); k++) {
                    cnt++;
                    originFish.add(map[i][j].get(k));
                }
                map[i][j].clear();
            }
        }

        ans = cnt;
    }

    static void moveShark() {
        int nx = shark.x, ny = shark.y;
        for (int i = 0; i < 3; i++) {
            int d = dir[i];
            nx += dx[d];
            ny += dy[d];

            if (map[nx][ny].size() > 0) {
                smell[nx][ny] = 3;
                map[nx][ny].clear();
            }
        }

        shark = new Fish(nx, ny, -1);
    }

    static void searchRoute(Fish shark, int depth, int sum, int[] order) {
        if (depth == 3) {
            if (sum > max) {
                max = sum;
                System.arraycopy(order, 0, dir, 0, order.length);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = shark.x + dx[i];
            int ny = shark.y + dy[i];

            if (isOutBound(nx, ny))
                continue;

            Fish newShark = new Fish(nx, ny, -1);
            ArrayList<Fish> tmp = map[nx][ny];
            map[nx][ny] = new ArrayList<>();
            order[depth] = i;
            searchRoute(newShark, depth + 1, sum + tmp.size(), order);
            map[nx][ny] = tmp;
        }
    }

    static Fish moveFish(Fish now) {
        int nx = now.x, ny = now.y, d = now.d;
        boolean flag = false;

        for (int i = 0; i < 8; i++) {
            d = now.d - i < 0 ? 8 + (now.d - i) : now.d - i;
            nx = now.x + fdx[d];
            ny = now.y + fdy[d];

            if (isOutBound(nx, ny))
                continue;
            if (smell[nx][ny] != 0 || (nx == shark.x && ny == shark.y))
                continue;

            flag = true;
            break;
        }

        if (!flag) {
            return now;
        } else {
            return new Fish(nx, ny, d);
        }
    }

    static boolean isOutBound(int x, int y) {
        return x < 0 || y < 0 || x >= 4 || y >= 4;
    }

    static class Fish {
        int x;
        int y;
        int d;

        Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}