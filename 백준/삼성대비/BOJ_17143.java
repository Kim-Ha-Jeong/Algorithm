import java.util.*;
import java.io.*;

public class BOJ_17143 {
    static int n, m, k, ans = 0;
    static int[][] map;
    static HashMap<Integer, Shark> shark;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        shark = new HashMap<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            shark.put(i + 1, new Shark(i + 1, x, y, s, d, z));
            map[x][y] = i + 1;
        }

        for (int y = 0; y < m; y++) {
            fishing(y);
            if (shark.size() == 0)
                break;
            move();
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void move() {
        ArrayList<Shark>[][] v = new ArrayList[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                v[i][j] = new ArrayList<>();
            }
        }
        for (int num = 1; num < k + 1; num++) {
            if (!shark.containsKey(num))
                continue;

            Shark sh = shark.get(num);
            map[sh.x][sh.y] = 0;
            int s = sh.s;
            int d = sh.d;
            int nx = sh.x + s * dx[d], ny = sh.y + s * dy[d];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                int[] arr = new int[2];
                if (d < 2) {
                    arr = moveDetail(nx, d, n);
                    nx = arr[0];
                } else {
                    arr = moveDetail(ny, d, m);
                    ny = arr[0];
                }
                d = arr[1];
            }

            v[nx][ny].add(sh);
            sh.x = nx;
            sh.y = ny;
            sh.d = d;
            map[nx][ny] = num;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int size = v[i][j].size();
                if (size == 0)
                    continue;
                if (size > 1) {
                    Collections.sort(v[i][j]);
                    for (int l = 1; l < size; l++) {
                        shark.remove(v[i][j].get(l).num);
                    }
                }
                map[i][j] = v[i][j].get(0).num;
            }
        }
    }

    static int[] moveDetail(int x, int d, int w) {
        if (d == 0 || d == 3) {
            x = (int) Math.abs(x);
        } else if (d == 1 || d == 2) {
            x -= w - 1;
        }

        int r = x % (w - 1);
        int q = x / (w - 1);
        if (r == 0) {
            if (q % 2 == 1) {
                d = d % 2 == 0 ? d + 1 : d - 1;
            }
            if (d == 1 || d == 2) {
                x = w - 1;
            } else {
                x = 0;
            }
        } else {
            if (q % 2 == 0) {
                d = d % 2 == 0 ? d + 1 : d - 1;
            }
            if (d == 1 || d == 2) {
                x = r;
            } else {
                x = w - 1 - r;
            }
        }

        int[] ret = { x, d };
        return ret;
    }

    static void fishing(int y) {
        for (int x = 0; x < n; x++) {
            if (map[x][y] != 0) {
                int num = map[x][y];
                Shark sh = shark.get(num);
                ans += sh.z;
                shark.remove(num);
                map[x][y] = 0;
                return;
            }
        }
    }

    static class Shark implements Comparable<Shark> {
        int num;
        int x;
        int y;
        int s;
        int d;
        int z;

        Shark(int num, int x, int y, int s, int d, int z) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public int compareTo(Shark sh) {
            return sh.z - this.z;
        }
    }

}
