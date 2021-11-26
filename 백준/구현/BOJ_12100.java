import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_12100 {
    static int n;
    static int[][] map;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        int[] order = new int[5];
        dfs(0, order);

        bw.write(ans + "");
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int[] order) {
        if (depth == 5) {
            solve(order);
            return;
        }
        for (int i = 0; i < 4; i++) {
            order[depth] = i;
            dfs(depth + 1, order);
        }
    }

    static void solve(int[] order) {
        int[][] tmp = new int[n][n];

        for (int i = 0; i < n; i++) {
            tmp[i] = map[i].clone();
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < n; j++) {
                if (order[i] <= 1) {
                    tmp = upAndDown(order[i], j, tmp);
                } else {
                    tmp = leftAndRight(order[i], j, tmp);
                }
            }
        }

        findMax(tmp);
    }

    static void findMax(int[][] tmp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, tmp[i][j]);
            }
        }
    }

    static int[][] upAndDown(int flag, int i, int[][] map) {
        int idx = flag == 0 ? 0 : n - 1;
        int prev = 0;
        if (flag == 0) {
            for (int j = 0; j < n; j++) {
                if (map[j][i] != 0) {
                    if (prev == map[j][i]) {
                        map[idx - 1][i] = prev * 2;
                        prev = 0;
                        map[j][i] = 0;
                    } else {
                        prev = map[j][i];
                        map[j][i] = 0;
                        map[idx][i] = prev;
                        idx++;
                    }
                }
            }
        } else {
            for (int j = n - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    if (prev == map[j][i]) {
                        map[idx + 1][i] = prev * 2;
                        prev = 0;
                        map[j][i] = 0;
                    } else {
                        prev = map[j][i];
                        map[j][i] = 0;
                        map[idx][i] = prev;
                        idx--;
                    }
                }
            }
        }

        return map;
    }

    static int[][] leftAndRight(int flag, int i, int[][] map) {
        int idx = flag == 2 ? 0 : n - 1;
        int prev = 0;
        if (flag == 2) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {
                    if (prev == map[i][j]) {
                        map[i][idx - 1] = prev * 2;
                        prev = 0;
                        map[i][j] = 0;
                    } else {
                        prev = map[i][j];
                        map[i][j] = 0;
                        map[i][idx] = prev;
                        idx++;
                    }
                }
            }
        } else {
            for (int j = n - 1; j >= 0; j--) {
                if (map[i][j] != 0) {
                    if (prev == map[i][j]) {
                        map[i][idx + 1] = prev * 2;
                        prev = 0;
                        map[i][j] = 0;
                    } else {
                        prev = map[i][j];
                        map[i][j] = 0;
                        map[i][idx] = prev;
                        idx--;
                    }
                }
            }
        }

        return map;
    }

}
