import java.util.*;
import java.io.*;

public class BOJ_17143 {
    static int r, c, m;
    static int[] dr = { 0, -1, 1, 0, 0 };
    static int[] dc = { 0, 0, 0, 1, -1 };
    static int[][] map;
    static HashMap<Integer, Shark> shark = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[r + 1][c + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int ra = Integer.parseInt(st.nextToken());
            int ca = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            if (d < 3) {
                s = s % ((r - 1) * 2);
            } else {
                s = s % ((c - 1) * 2);
            }

            shark.put(z, new Shark(ra, ca, s, d));
            map[ra][ca] = z;
        }

        int idx = 1;
        int sum = 0;

        while (idx < c + 1) {
            for (int i = 1; i < r + 1; i++) {
                if (map[i][idx] != 0) {
                    shark.remove(map[i][idx]);
                    sum += map[i][idx];
                    map[i][idx] = 0;
                    break;
                }
            }

            int[][] visited = new int[r + 1][c + 1];
            ArrayList<Integer> del = new ArrayList<>();
            for (int size : shark.keySet()) {
                Shark tmp = shark.get(size);
                map[tmp.r][tmp.c] = 0;

                int nr = tmp.r;
                int nc = tmp.c;
                for (int i = 0; i < tmp.vel; i++) {
                    if (nr + dr[tmp.dir] <= 0 || nr + dr[tmp.dir] >= r + 1 || nc + dc[tmp.dir] <= 0
                            || nc + dc[tmp.dir] >= c + 1) {
                        if (tmp.dir % 2 == 0)
                            tmp.dir--;
                        else
                            tmp.dir++;
                    }

                    nr += dr[tmp.dir];
                    nc += dc[tmp.dir];
                }

                if (visited[nr][nc] != 0) {
                    if (size > visited[nr][nc]) {
                        del.add(visited[nr][nc]);
                        visited[nr][nc] = size;
                        map[nr][nc] = size;
                        tmp.r = nr;
                        tmp.c = nc;
                    } else if (size < visited[nr][nc]) {
                        del.add(size);
                    }
                } else {
                    tmp.r = nr;
                    tmp.c = nc;
                    map[nr][nc] = size;
                    visited[nr][nc] = size;
                }
            }

            for (int x : del) {
                shark.remove(x);
            }
            idx++;

        }

        bw.write(sb.append(sum).toString());
        bw.flush();
        bw.close();

    }

    static class Shark {
        int r;
        int c;
        int vel;
        int dir;

        Shark(int r, int c, int vel, int dir) {
            this.r = r;
            this.c = c;
            this.vel = vel;
            this.dir = dir;
        }
    }

}
