import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_19236 {
    static int max = 0;
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] map = new int[4][4];
        ArrayList<Fish> fishes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(s[j * 2]);
                int dir = Integer.parseInt(s[j * 2 + 1]) - 1;

                map[i][j] = num;
                fishes.add(new Fish(num, i, j, dir, true));
            }
        }

        Collections.sort(fishes);
        Fish f = fishes.get(map[0][0] - 1);
        Shark shark = new Shark(0, 0, f.dir, f.num);
        f.isAlive = false;
        map[0][0] = -1;

        dfs(map, shark, fishes);
        bw.write(max + "");
        bw.flush();
        bw.close();
    }

    static void dfs(int[][] map, Shark shark, ArrayList<Fish> fishes) {
        max = Math.max(max, shark.sum);

        for (Fish f : fishes) {
            moveFish(f, map, fishes);
        }

        for (int depth = 1; depth < 4; depth++) {
            int nx = shark.x + dx[shark.dir] * depth;
            int ny = shark.y + dy[shark.dir] * depth;

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] <= 0)
                continue;

            int[][] mapCopy = copy(map);
            ArrayList<Fish> fishCopy = copyFish(fishes);

            mapCopy[shark.x][shark.y] = 0;
            Fish f = fishCopy.get(map[nx][ny] - 1);
            Shark newShark = new Shark(f.x, f.y, f.dir, shark.sum + f.num);
            f.isAlive = false;
            mapCopy[f.x][f.y] = -1;

            dfs(mapCopy, newShark, fishCopy);
        }
    }

    static ArrayList<Fish> copyFish(ArrayList<Fish> fishes) {
        ArrayList<Fish> tmp = new ArrayList<>();

        for (Fish f : fishes) {
            tmp.add(new Fish(f.num, f.x, f.y, f.dir, f.isAlive));
        }

        return tmp;
    }

    static int[][] copy(int[][] map) {
        int[][] tmp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        return tmp;
    }

    static void moveFish(Fish fish, int[][] map, ArrayList<Fish> fishes) {
        if (!fish.isAlive)
            return;

        int dir = fish.dir;
        int nx, ny;
        while (true) {
            nx = fish.x + dx[dir];
            ny = fish.y + dy[dir];

            if (nx >= 4 || nx < 0 || ny >= 4 || ny < 0 || map[nx][ny] == -1) {
                dir = (dir + 1) % 8;
            } else {
                map[fish.x][fish.y] = 0;

                if (map[nx][ny] == 0) {
                    fish.x = nx;
                    fish.y = ny;
                } else {
                    Fish tmp = fishes.get(map[nx][ny] - 1);
                    tmp.x = fish.x;
                    tmp.y = fish.y;
                    map[fish.x][fish.y] = tmp.num;
                    fish.x = nx;
                    fish.y = ny;
                }

                map[nx][ny] = fish.num;
                fish.dir = dir;
                break;
            }
        }

    }

    static class Fish implements Comparable<Fish> {
        int num;
        int dir;
        int x;
        int y;
        boolean isAlive;

        Fish(int num, int x, int y, int dir, boolean isAlive) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.isAlive = isAlive;
        }

        @Override
        public int compareTo(Fish o) {
            return this.num - o.num;
        }
    }

    static class Shark {
        int x;
        int y;
        int dir;
        int sum;

        Shark(int x, int y, int dir, int sum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.sum = sum;
        }
    }

}
