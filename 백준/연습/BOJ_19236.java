import java.util.*;
import java.io.*;

public class BOJ_19236 {
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        HashMap<Integer, Node> fishes = new HashMap<>();
        Node shark;
        int[][] map = new int[4][4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int idx = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = idx;
                fishes.put(idx, new Node(i, j, d));
            }
        }

        int idx = map[0][0];
        Node fish = fishes.get(idx);
        map[0][0] = -1;
        shark = new Node(0, 0, fish.d);
        shark.sum += idx;
        fishes.remove(idx);

        dfs(map, fishes, shark);

        bw.write(sb.append(max).toString());
        bw.flush();
        bw.close();
    }

    static void moveFish(int[][] map, HashMap<Integer, Node> fishes) {
        for (int i = 1; i <= 16; i++) {
            Node now = fishes.get(i);

            if (now == null || map[now.x][now.y] == -1)
                continue;

            int d = now.d;

            boolean f = true;
            int nx = -1, ny = -1;
            for (int j = 0; j < 8; j++) {
                nx = now.x + dx[(d + j) % 8];
                ny = now.y + dy[(d + j) % 8];

                if (!outOfBound(nx, ny) && map[nx][ny] != -1) {
                    d = (d + j) % 8;
                    f = false;
                    break;
                }
            }

            if (f)
                continue;

            map[now.x][now.y] = 0;

            if (map[nx][ny] != 0) {
                int idx = map[nx][ny];
                map[now.x][now.y] = idx;
                Node next = fishes.get(idx);
                fishes.put(idx, new Node(now.x, now.y, next.d));
            }
            fishes.put(i, new Node(nx, ny, d));
            map[nx][ny] = i;
        }
    }

    static void dfs(int[][] map, HashMap<Integer, Node> fishes, Node shark) {
        max = Math.max(max, shark.sum);
        moveFish(map, fishes);

        for (int depth = 1; depth < 4; depth++) {
            int nx = shark.x + dx[shark.d] * depth;
            int ny = shark.y + dy[shark.d] * depth;

            if (outOfBound(nx, ny) || map[nx][ny] <= 0)
                continue;

            int[][] copyMap = copy(map);
            HashMap<Integer, Node> copyFishes = copyMap(fishes);

            copyMap[shark.x][shark.y] = 0;
            Node fish = copyFishes.get(map[nx][ny]);
            Node newShark = new Node(nx, ny, fish.d);
            newShark.sum = shark.sum + map[nx][ny];

            copyFishes.remove(map[nx][ny]);
            copyMap[nx][ny] = -1;

            dfs(copyMap, copyFishes, newShark);
        }

    }

    static int[][] copy(int[][] arr) {
        int[][] ret = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ret[i][j] = arr[i][j];
            }
        }

        return ret;
    }

    static HashMap<Integer, Node> copyMap(HashMap<Integer, Node> fishes) {
        HashMap<Integer, Node> tmp = new HashMap<>();

        for (int key : fishes.keySet()) {
            tmp.put(key, fishes.get(key));
        }

        return tmp;
    }

    static boolean outOfBound(int x, int y) {
        if (x < 0 || x >= 4 || y < 0 || y >= 4)
            return true;
        return false;
    }

    static class Node {
        int x;
        int y;
        int d;
        int sum = 0;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

}
