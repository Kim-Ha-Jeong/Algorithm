import java.util.*;
import java.io.*;

public class BOJ_19236_2 {
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        HashMap<Integer, Fish> fish = new HashMap<>();
        int[][] map = new int[4][4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;
                fish.put(num, new Fish(i, j, d));
                map[i][j] = num;
            }
        }

        int num = map[0][0];
        Fish shark = new Fish(0, 0, fish.get(num).d);
        shark.sum += num;
        map[0][0] = -1;
        fish.remove(num);

        solve(shark, fish, map);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void solve(Fish shark, HashMap<Integer, Fish> fish, int[][] map) {
        ans = Math.max(ans, shark.sum);

        for (int i = 1; i < 17; i++) {
            Fish now = fish.get(i);

            if (now == null || map[now.x][now.y] == -1)
                continue;
            moveFish(now, i, map, fish);
        }

        for (int i = 1; i < 4; i++) {
            int nx = shark.x + dx[shark.d] * i;
            int ny = shark.y + dy[shark.d] * i;

            if (isOutBound(nx, ny))
                continue;
            if (map[nx][ny] <= 0)
                continue;

            HashMap<Integer, Fish> newFish = copyMap(fish);
            int[][] newMap = copyArray(map);

            newMap[shark.x][shark.y] = 0;
            Fish eat = newFish.get(map[nx][ny]);
            Fish newShark = new Fish(nx, ny, eat.d);
            newShark.sum = shark.sum + map[nx][ny];

            newFish.remove(map[nx][ny]);
            newMap[nx][ny] = -1;

            solve(newShark, newFish, newMap);
        }
    }

    static int[][] copyArray(int[][] map) {
        int[][] tmp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        return tmp;
    }

    static HashMap<Integer, Fish> copyMap(HashMap<Integer, Fish> fish) {
        HashMap<Integer, Fish> tmp = new HashMap<>();

        for (int key : fish.keySet()) {
            tmp.put(key, fish.get(key));
        }

        return tmp;
    }

    static void moveFish(Fish f, int num, int[][] map, HashMap<Integer, Fish> fish) {
        int d = f.d;

        for (int i = 0; i < 8; i++) {
            int nx = f.x + dx[(d + i) % 8];
            int ny = f.y + dy[(d + i) % 8];

            if (isOutBound(nx, ny))
                continue;
            if (map[nx][ny] == -1)
                continue;

            d = (d + i) % 8;
            map[f.x][f.y] = 0;

            if (map[nx][ny] != 0) {
                int idx = map[nx][ny];
                map[f.x][f.y] = idx;
                Fish next = fish.get(idx);
                fish.put(idx, new Fish(f.x, f.y, next.d));
            }

            fish.put(num, new Fish(nx, ny, d));
            map[nx][ny] = num;
            break;
        }
    }

    static boolean isOutBound(int x, int y) {
        if (x < 0 || x >= 4 || y < 0 || y >= 4)
            return true;
        return false;
    }

    static class Fish {
        int x;
        int y;
        int d;
        int sum = 0;

        Fish(int x, int y, int d) {
            this.d = d;
            this.x = x;
            this.y = y;
        }
    }
}
